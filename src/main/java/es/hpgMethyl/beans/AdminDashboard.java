package es.hpgMethyl.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.hbar.HorizontalBarChartDataSet;
import org.primefaces.model.charts.hbar.HorizontalBarChartModel;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import es.hpgMethyl.dao.hibernate.AnalysisRequestDAOHibernate;
import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.types.AnalysisStatus;
import es.hpgMethyl.usecases.analysis.ListMethylationAnalysis.ListMethylationAnalysis;
import es.hpgMethyl.usecases.user.ListUsers.ListUsers;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="adminDashboard")
@RequestScoped
public class AdminDashboard {

	private PieChartModel usersByRole;
	private BarChartModel usersByStatus;
	private BarChartModel signupsByMonth;
	private PieChartModel analysisByStatus;
	private PieChartModel analysisByMode;
	private HorizontalBarChartModel topRequestingUsers;
	private BarChartModel analysisByMonth;
	
	private Integer totalAdminUsers;
	private Integer totalModeratorUsers;
	private Integer totalBaseUsers;
	private Integer totalEnabledUsers;
	private Integer totalDisabledUsers;
	private HashMap<String, Integer> usersByMonth;	
	private Integer queuedAnalysis;
	private Integer completedAnalysis;
	private Integer totalSingleEndModeAnalysis;
	private Integer totalPairedEndModeAnalysis;
	private HashMap<String, Integer> analysisByUsers;
	private HashMap<String, Integer> queuedAnalysisByMonth;
	private HashMap<String, Integer> completedAnalysisByMonth;
	
	private DateFormat monthFormat;
	
	@PostConstruct
    public void init() {
		
		List<User> users;
		
		try {
			users = new ListUsers(new UserDAOHibernate()).execute().getUsers();
		} catch (ListObjectsException e) {
			users = new ArrayList<User>();
		}
		
		totalAdminUsers = 0;
		totalModeratorUsers = 0;
		totalBaseUsers = 0;
		totalEnabledUsers = 0;
		totalDisabledUsers = 0;
		
		usersByMonth = new HashMap<String, Integer>();
		
		monthFormat = new SimpleDateFormat("yyyy-MM");  
				
		for(User user : users) {
			switch (user.getRole()) {
				case ADMIN:
					totalAdminUsers++;
					break;
				case MODERATOR:
					totalModeratorUsers++;
					break;
				default:
					totalBaseUsers++;
					break;
			}
			
			if(user.getActive()) {
				totalEnabledUsers++;
			} else {
				totalDisabledUsers++;
			}
			
			String signupMonth = monthFormat.format(user.getCreatedAt());  			
			if(usersByMonth.containsKey(signupMonth)) {
				Integer usersInMonth = usersByMonth.get(signupMonth);
				usersInMonth++;
				usersByMonth.put(signupMonth, usersInMonth);
			} else {
				usersByMonth.put(signupMonth, 1);
			}
		}	
		
		List<AnalysisRequest> analysisRequestList;
		
		try {
			analysisRequestList = new ListMethylationAnalysis(new AnalysisRequestDAOHibernate()).execute().getAnalysisRequestList();
		} catch (ListObjectsException e) {
			analysisRequestList = new ArrayList<AnalysisRequest>();
		}
		
		queuedAnalysis = 0;
		completedAnalysis = 0;
		totalSingleEndModeAnalysis = 0;
		totalPairedEndModeAnalysis = 0;
		
		analysisByUsers = new HashMap<String, Integer>();
		queuedAnalysisByMonth = new HashMap<String, Integer>();
		completedAnalysisByMonth = new HashMap<String, Integer>();
		
		for(AnalysisRequest analysisRequest : analysisRequestList) {
			switch (analysisRequest.getStatus()) {
				case COMPLETED:
					completedAnalysis++;
					break;
				case PROCESSING:
					queuedAnalysis++;
					break;
				default:
					queuedAnalysis++;
					break;
			}
			
			switch (analysisRequest.getPairedMode()) {
				case PAIRED_END_MODE:
					totalPairedEndModeAnalysis++;
					break;
				default:
					totalSingleEndModeAnalysis++;
					break;				
			}
			
			String userEmail = analysisRequest.getUser().getEmail();
			if(analysisByUsers.containsKey(userEmail)) {
				Integer userAnalysis = analysisByUsers.get(userEmail);
				userAnalysis++;
				analysisByUsers.put(userEmail, userAnalysis);
			} else {
				analysisByUsers.put(userEmail, 1);
			}
			
			String analysisRequestMonth = monthFormat.format(analysisRequest.getCreatedAt());  	
			if(queuedAnalysisByMonth.containsKey(analysisRequestMonth)) {
				Integer queuedAnalysisInMonth = queuedAnalysisByMonth.get(analysisRequestMonth);
				queuedAnalysisInMonth++;
				queuedAnalysisByMonth.put(analysisRequestMonth, queuedAnalysisInMonth);
			} else {
				queuedAnalysisByMonth.put(analysisRequestMonth, 1);
			}
			
			if(analysisRequest.getStatus() == AnalysisStatus.COMPLETED) {
				String analysisCompletedMonth = monthFormat.format(analysisRequest.getUpdatedAt()); 
				if(completedAnalysisByMonth.containsKey(analysisCompletedMonth)) {
					Integer completedAnalysisInMonth = completedAnalysisByMonth.get(analysisCompletedMonth);
					completedAnalysisInMonth++;
					completedAnalysisByMonth.put(analysisCompletedMonth, completedAnalysisInMonth);
				} else {
					completedAnalysisByMonth.put(analysisCompletedMonth, 1);
				}
			}		
		}
		
		loadUsersByRole();
		loadUsersByStatus();	
		loadSignupsByMonth();
		loadAnalysisByStatus();
		loadTopRequestingUsers();
		loadAnalysisByMode();
		loadAnalysisByMonth();
    }

	/**
	 * @return the usersByRole
	 */
	public PieChartModel getUsersByRole() {
		return usersByRole;
	}

	/**
	 * @param usersByRole the usersByRole to set
	 */
	public void setUsersByRole(PieChartModel usersByRole) {
		this.usersByRole = usersByRole;
	}

	/**
	 * @return the usersByStatus
	 */
	public BarChartModel getUsersByStatus() {
		return usersByStatus;
	}

	/**
	 * @param usersByStatus the usersByStatus to set
	 */
	public void setUsersByStatus(BarChartModel usersByStatus) {
		this.usersByStatus = usersByStatus;
	}

	/**
	 * @return the signupsByMonth
	 */
	public BarChartModel getSignupsByMonth() {
		return signupsByMonth;
	}

	/**
	 * @param signupsByMonth the signupsByMonth to set
	 */
	public void setSignupsByMonth(BarChartModel signupsByMonth) {
		this.signupsByMonth = signupsByMonth;
	}

	/**
	 * @return the analysisByStatus
	 */
	public PieChartModel getAnalysisByStatus() {
		return analysisByStatus;
	}

	/**
	 * @param analysisByStatus the analysisByStatus to set
	 */
	public void setAnalysisByStatus(PieChartModel analysisByStatus) {
		this.analysisByStatus = analysisByStatus;
	}

	/**
	 * @return the topRequestingUsers
	 */
	public HorizontalBarChartModel getTopRequestingUsers() {
		return topRequestingUsers;
	}

	/**
	 * @param topRequestingUsers the topRequestingUsers to set
	 */
	public void setTopRequestingUsers(HorizontalBarChartModel topRequestingUsers) {
		this.topRequestingUsers = topRequestingUsers;
	}

	/**
	 * @return the analysisByMode
	 */
	public PieChartModel getAnalysisByMode() {
		return analysisByMode;
	}

	/**
	 * @param analysisByMode the analysisByMode to set
	 */
	public void setAnalysisByMode(PieChartModel analysisByMode) {
		this.analysisByMode = analysisByMode;
	}

	/**
	 * @return the analysisByMonth
	 */
	public BarChartModel getAnalysisByMonth() {
		return analysisByMonth;
	}

	/**
	 * @param analysisByMonth the analysisByMonth to set
	 */
	public void setAnalysisByMonth(BarChartModel analysisByMonth) {
		this.analysisByMonth = analysisByMonth;
	}

	public void loadUsersByRole() {
				
		this.usersByRole = new PieChartModel();     		
		
        List<Number> dataSetValues = new ArrayList<>();
        dataSetValues.add(totalAdminUsers);
        dataSetValues.add(totalModeratorUsers);
        dataSetValues.add(totalBaseUsers);                

        List<String> backgroundColors = new ArrayList<>();
        backgroundColors.add("rgb(255, 125, 125)");
        backgroundColors.add("rgb(125, 125, 255)");
        backgroundColors.add("rgb(125, 255, 125)");
        
        String adminLabel = FacesContextUtils.geti18nMessage("user.attribute.role.ADMIN");
        String moderatorLabel = FacesContextUtils.geti18nMessage("user.attribute.role.MODERATOR");
        String userLabel = FacesContextUtils.geti18nMessage("user.attribute.role.USER");
        
        List<String> labels = new ArrayList<>();
        labels.add(adminLabel);
        labels.add(moderatorLabel);
        labels.add(userLabel);
        
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(dataSetValues);
        dataSet.setBackgroundColor(backgroundColors);               

        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);        
        data.setLabels(labels);

        this.usersByRole.setData(data);
    }
	
	public void loadUsersByStatus() {
		
		this.usersByStatus = new BarChartModel();        
        
        List<Number> dataSetValues = new ArrayList<>();
        dataSetValues.add(totalEnabledUsers);
        dataSetValues.add(totalDisabledUsers);
        
        List<String> backgroundColors = new ArrayList<>();        
        backgroundColors.add("rgb(125, 255, 125)");
        backgroundColors.add("rgb(255, 125, 125)");
        
        List<String> borderColor = new ArrayList<>();        
        borderColor.add("rgb(80, 200, 80)");
        borderColor.add("rgb(200, 80, 80)");
        
        String activeUser = FacesContextUtils.geti18nMessage("user.attribute.active.true");
        String inactiveUser = FacesContextUtils.geti18nMessage("user.attribute.active.false");

        List<String> labels = new ArrayList<>();
        labels.add(activeUser);
        labels.add(inactiveUser);
        
        BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setData(dataSetValues);        
        dataSet.setBackgroundColor(backgroundColors);        
        dataSet.setBorderColor(borderColor);
        dataSet.setBorderWidth(1);

        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);               
        data.setLabels(labels);
        
        this.usersByStatus.setData(data);   
        
        Title title = new Title();
        title.setDisplay(false);      

        Legend legend = new Legend();
        legend.setDisplay(false);
        
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);
        
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(false);
        ticks.setPrecision(0);
        linearAxes.setTicks(ticks);
        
        CartesianScales cartesianScales = new CartesianScales();
        cartesianScales.addYAxesData(linearAxes);
        
        Animation animation = new Animation();
        animation.setDuration(10);
        
        BarChartOptions options = new BarChartOptions();
        options.setTitle(title);
        options.setLegend(legend);
        options.setScales(cartesianScales);
        options.setAnimation(animation);

        this.usersByStatus.setOptions(options);
    }
	
	public void loadSignupsByMonth() {
		
		this.signupsByMonth = new BarChartModel();        
        
        List<Number> dataSetValues = new ArrayList<>();                          
        List<String> labels = new ArrayList<>();
        
        Calendar calendarReference = Calendar.getInstance();
        calendarReference.add(Calendar.YEAR, -1);
        
        for(int i = 0; i < 12; i++) {
        	calendarReference.add(Calendar.MONTH, 1);
        	String signupMonth = monthFormat.format(calendarReference.getTime());
        	labels.add(signupMonth);
        	dataSetValues.add(usersByMonth.getOrDefault(signupMonth, 0));        	
        }   
        
        String user = FacesContextUtils.geti18nMessage("user");
        
        BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setLabel(user);
        dataSet.setData(dataSetValues);        
        dataSet.setBackgroundColor("rgb(0, 70, 170)");        
        dataSet.setBorderColor("rgb(0, 70, 150)");
        dataSet.setBorderWidth(1);

        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);               
        data.setLabels(labels);
        
        this.signupsByMonth.setData(data);   
        
        Title title = new Title();
        title.setDisplay(false);      

        Legend legend = new Legend();
        legend.setDisplay(false);
        
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(false);
        
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(false);
        ticks.setPrecision(0);
        linearAxes.setTicks(ticks);
        
        CartesianScales cartesianScales = new CartesianScales();
        cartesianScales.addYAxesData(linearAxes);
        
        Animation animation = new Animation();
        animation.setDuration(10);
        
        BarChartOptions options = new BarChartOptions();
        options.setTitle(title);
        options.setLegend(legend);
        options.setScales(cartesianScales);
        options.setAnimation(animation);

        this.signupsByMonth.setOptions(options);
	}
	
	public void loadAnalysisByStatus() {
		
		this.analysisByStatus = new PieChartModel();     		
		
        List<Number> dataSetValues = new ArrayList<>();
        dataSetValues.add(queuedAnalysis);
        dataSetValues.add(completedAnalysis);                

        List<String> backgroundColors = new ArrayList<>();
        backgroundColors.add("rgb(125, 255, 125)");
        backgroundColors.add("rgb(125, 125, 255)");
        
        String queuedAnalysisLabel = FacesContextUtils.geti18nMessage("analysis.attribute.status.CREATED");
        String completedAnalysisLabel = FacesContextUtils.geti18nMessage("analysis.attribute.status.COMPLETED");
        
        List<String> labels = new ArrayList<>();
        labels.add(queuedAnalysisLabel);
        labels.add(completedAnalysisLabel);
        
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(dataSetValues);
        dataSet.setBackgroundColor(backgroundColors);               

        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);        
        data.setLabels(labels);

        this.analysisByStatus.setData(data);
    }
	
	public void loadAnalysisByMode() {
		
		this.analysisByMode = new PieChartModel();     		
		
        List<Number> dataSetValues = new ArrayList<>();
        dataSetValues.add(totalSingleEndModeAnalysis);
        dataSetValues.add(totalPairedEndModeAnalysis);                

        List<String> backgroundColors = new ArrayList<>();
        backgroundColors.add("rgb(125, 255, 125)");
        backgroundColors.add("rgb(255, 125, 125)");
        
        String singleEndModeLabel = FacesContextUtils.geti18nMessage("analysis.attribute.pairedMode.SINGLE_END_MODE");
        String pairedEndModeLabel = FacesContextUtils.geti18nMessage("analysis.attribute.pairedMode.PAIRED_END_MODE");
        
        List<String> labels = new ArrayList<>();
        labels.add(singleEndModeLabel);
        labels.add(pairedEndModeLabel);
        
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(dataSetValues);
        dataSet.setBackgroundColor(backgroundColors);               

        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);        
        data.setLabels(labels);

        this.analysisByMode.setData(data);
    }
	
	public void loadTopRequestingUsers() {
		
        this.topRequestingUsers = new HorizontalBarChartModel();           
        
        List<Number> dataSetValues = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        analysisByUsers.entrySet()
        .stream()
        .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
        .forEachOrdered(analysis -> {
        	labels.add(analysis.getKey());
            dataSetValues.add(analysis.getValue());
        });       
        
        String analysis = FacesContextUtils.geti18nMessage("analysis");
        
        HorizontalBarChartDataSet dataSet = new HorizontalBarChartDataSet();
        dataSet.setLabel(analysis);
        dataSet.setData(dataSetValues);
        dataSet.setBackgroundColor("rgb(0, 70, 170)");
        dataSet.setBorderColor("rgb(0, 70, 150)");
        dataSet.setBorderWidth(1);

        ChartData data = new ChartData(); 
        data.addChartDataSet(dataSet);        
        data.setLabels(labels);
        
        this.topRequestingUsers.setData(data);        
                
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(false);
        ticks.setPrecision(0);
        
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(true);                        
        linearAxes.setTicks(ticks);
        
        CartesianScales cartesianScales = new CartesianScales();
        cartesianScales.addXAxesData(linearAxes);        

        Title title = new Title();
        title.setDisplay(false);
        
        Legend legend = new Legend();
        legend.setDisplay(false);
        
        BarChartOptions options = new BarChartOptions();
        options.setTitle(title);
        options.setLegend(legend);
        options.setScales(cartesianScales);

        this.topRequestingUsers.setOptions(options);
    }
	
	public void loadAnalysisByMonth() {
		
		this.analysisByMonth = new BarChartModel();        
        
        List<Number> queuedAnalysisDataSetValues = new ArrayList<>();  
        List<Number> completedAnalysisDataSetValues = new ArrayList<>();  
        List<String> labels = new ArrayList<>();
        
        Calendar calendarReference = Calendar.getInstance();
        calendarReference.add(Calendar.YEAR, -1);
        
        for(int i = 0; i < 12; i++) {
        	calendarReference.add(Calendar.MONTH, 1);
        	String signupMonth = monthFormat.format(calendarReference.getTime());
        	labels.add(signupMonth);
        	queuedAnalysisDataSetValues.add(queuedAnalysisByMonth.getOrDefault(signupMonth, 0));  
        	completedAnalysisDataSetValues.add(completedAnalysisByMonth.getOrDefault(signupMonth, 0));  
        }   
        
        String createdAnalysis = FacesContextUtils.geti18nMessage("analysis.attribute.status.CREATED");
        
        BarChartDataSet queuedAnalysisDataSet = new BarChartDataSet();
        queuedAnalysisDataSet.setLabel(createdAnalysis);
        queuedAnalysisDataSet.setData(queuedAnalysisDataSetValues);        
        queuedAnalysisDataSet.setBackgroundColor("rgb(0, 70, 170)");        
        queuedAnalysisDataSet.setBorderColor("rgb(0, 70, 150)");
        queuedAnalysisDataSet.setBorderWidth(1);
        
        String completedAnalysis = FacesContextUtils.geti18nMessage("analysis.attribute.status.COMPLETED");
        
        BarChartDataSet completedAnalysisDataSet = new BarChartDataSet();
        completedAnalysisDataSet.setLabel(completedAnalysis);
        completedAnalysisDataSet.setData(completedAnalysisDataSetValues);        
        completedAnalysisDataSet.setBackgroundColor("rgb(255, 125, 125)");        
        completedAnalysisDataSet.setBorderColor("rgb(255, 125, 105)");
        completedAnalysisDataSet.setBorderWidth(1);

        ChartData data = new ChartData();
        data.addChartDataSet(queuedAnalysisDataSet); 
        data.addChartDataSet(completedAnalysisDataSet); 
        data.setLabels(labels);
        
        this.analysisByMonth.setData(data);   
        
        Title title = new Title();
        title.setDisplay(false);      

        Legend legend = new Legend();
        legend.setDisplay(true);
        
        CartesianLinearAxes linearAxes = new CartesianLinearAxes();
        linearAxes.setOffset(false);
        
        CartesianLinearTicks ticks = new CartesianLinearTicks();
        ticks.setBeginAtZero(false);
        ticks.setPrecision(0);
        linearAxes.setTicks(ticks);
        
        CartesianScales cartesianScales = new CartesianScales();
        cartesianScales.addYAxesData(linearAxes);
        
        Animation animation = new Animation();
        animation.setDuration(10);
        
        BarChartOptions options = new BarChartOptions();
        options.setTitle(title);
        options.setLegend(legend);
        options.setScales(cartesianScales);
        options.setAnimation(animation);

        this.analysisByMonth.setOptions(options);
	}
}
