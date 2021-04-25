package es.hpgMethyl.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

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
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import es.hpgMethyl.dao.hibernate.UserDAOHibernate;
import es.hpgMethyl.entities.User;
import es.hpgMethyl.exceptions.ListObjectsException;
import es.hpgMethyl.usecases.user.ListUsers.ListUsers;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="adminDashboard")
@RequestScoped
public class AdminDashboard {

	private PieChartModel usersByRole;
	private BarChartModel usersByStatus;
	private LineChartModel signupsByMonth;
	
	private Integer totalAdminUsers;
	private Integer totalModeratorUsers;
	private Integer totalBaseUsers;
	private Integer totalEnabledUsers;
	private Integer totalDisabledUsers;
	private HashMap<String, Integer> usersByMonth;	
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
		
		loadUsersByRole();
		loadUsersByStatus();	
		loadSignupsByMonth();
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
	public LineChartModel getSignupsByMonth() {
		return signupsByMonth;
	}

	/**
	 * @param signupsByMonth the signupsByMonth to set
	 */
	public void setSignupsByMonth(LineChartModel signupsByMonth) {
		this.signupsByMonth = signupsByMonth;
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
		
		this.signupsByMonth = new LineChartModel();			        
        
        List<Object> dataSetValues = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        
        Calendar calendarReference = Calendar.getInstance();
        calendarReference.add(Calendar.YEAR, -1);
        
        for(int i = 0; i < 12; i++) {
        	calendarReference.add(Calendar.MONTH, 1);
        	String signupMonth = monthFormat.format(calendarReference.getTime());
        	labels.add(signupMonth);
        	dataSetValues.add(usersByMonth.getOrDefault(signupMonth, 0));        	
        }      
        
        LineChartDataSet dataSet = new LineChartDataSet();                        
        dataSet.setData(dataSetValues);
        dataSet.setFill(false);
        dataSet.setBorderColor("rgb(0, 71, 171)");
        dataSet.setLineTension(0.3);
        
        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);        
        data.setLabels(labels);
        
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

        LineChartOptions options = new LineChartOptions();        
        options.setTitle(title);
        options.setLegend(legend);
        options.setScales(cartesianScales);
        options.setAnimation(animation);

        this.signupsByMonth.setOptions(options);
        this.signupsByMonth.setData(data);
	}
}
