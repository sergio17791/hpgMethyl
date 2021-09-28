package es.hpgMethyl.beans;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.animation.Animation;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.pie.PieChartDataSet;
import org.primefaces.model.charts.pie.PieChartModel;

import es.hpgMethyl.entities.AnalysisResult;
import es.hpgMethyl.utils.FacesContextUtils;

@ManagedBean(name="analysisResultBean")
@ViewScoped
public class AnalysisResultBean implements Serializable {

	private static final long serialVersionUID = -66931227949705531L;

	private Integer totalNumberCAnalysed;
	private Integer totalMethylatedCCPGContext;
	private Integer totalMethylatedCCHGContext;
	private Integer totalMethylatedCCHHContext;
	private Integer totalCToTConversionsCPGContext;
	private Integer totalCToTConversionsCHGContext;
	private Integer totalCToTConversionsCHHContext;
	private BigDecimal cMethylatedCPGContext;
	private BigDecimal cMethylatedCHGContext;
	private BigDecimal cMethylatedCHHContext;
	private BigDecimal loadingTime;
	private BigDecimal aligmentTime;
	private BigDecimal totalTime;
	private Integer totalReadsProcessed;
	private BigDecimal readsMapped;
	private Integer totalReadsMapped;
	private BigDecimal readsUnmapped;
	private Integer totalReadsUnmapped;	
	
	private BarChartModel cytosineMethylationReport;
	private PieChartModel cMethylatedGraphic;
	private PieChartModel totalReadsProcessedGraphic;
	
	public AnalysisResultBean() {
		this.totalNumberCAnalysed = null;
		this.totalMethylatedCCPGContext = null;
		this.totalMethylatedCCHGContext = null;
		this.totalMethylatedCCHHContext = null;
		this.totalCToTConversionsCPGContext = null;
		this.totalCToTConversionsCHGContext = null;
		this.totalCToTConversionsCHHContext = null;
		this.cMethylatedCPGContext = null;
		this.cMethylatedCHGContext = null;
		this.cMethylatedCHHContext = null;
		this.loadingTime = null;
		this.aligmentTime = null;
		this.totalTime = null;
		this.totalReadsProcessed = null;
		this.readsMapped = null;
		this.totalReadsMapped = null;
		this.readsUnmapped = null;
		this.totalReadsUnmapped = null;
	}

	/**
	 * @return the totalNumberCAnalysed
	 */
	public Integer getTotalNumberCAnalysed() {
		return totalNumberCAnalysed;
	}

	/**
	 * @param totalNumberCAnalysed the totalNumberCAnalysed to set
	 */
	public void setTotalNumberCAnalysed(Integer totalNumberCAnalysed) {
		this.totalNumberCAnalysed = totalNumberCAnalysed;
	}

	/**
	 * @return the totalMethylatedCCPGContext
	 */
	public Integer getTotalMethylatedCCPGContext() {
		return totalMethylatedCCPGContext;
	}

	/**
	 * @param totalMethylatedCCPGContext the totalMethylatedCCPGContext to set
	 */
	public void setTotalMethylatedCCPGContext(Integer totalMethylatedCCPGContext) {
		this.totalMethylatedCCPGContext = totalMethylatedCCPGContext;
	}

	/**
	 * @return the totalMethylatedCCHGContext
	 */
	public Integer getTotalMethylatedCCHGContext() {
		return totalMethylatedCCHGContext;
	}

	/**
	 * @param totalMethylatedCCHGContext the totalMethylatedCCHGContext to set
	 */
	public void setTotalMethylatedCCHGContext(Integer totalMethylatedCCHGContext) {
		this.totalMethylatedCCHGContext = totalMethylatedCCHGContext;
	}

	/**
	 * @return the totalMethylatedCCHHContext
	 */
	public Integer getTotalMethylatedCCHHContext() {
		return totalMethylatedCCHHContext;
	}

	/**
	 * @param totalMethylatedCCHHContext the totalMethylatedCCHHContext to set
	 */
	public void setTotalMethylatedCCHHContext(Integer totalMethylatedCCHHContext) {
		this.totalMethylatedCCHHContext = totalMethylatedCCHHContext;
	}

	/**
	 * @return the totalCToTConversionsCPGContext
	 */
	public Integer getTotalCToTConversionsCPGContext() {
		return totalCToTConversionsCPGContext;
	}

	/**
	 * @param totalCToTConversionsCPGContext the totalCToTConversionsCPGContext to set
	 */
	public void setTotalCToTConversionsCPGContext(Integer totalCToTConversionsCPGContext) {
		this.totalCToTConversionsCPGContext = totalCToTConversionsCPGContext;
	}

	/**
	 * @return the totalCToTConversionsCHGContext
	 */
	public Integer getTotalCToTConversionsCHGContext() {
		return totalCToTConversionsCHGContext;
	}

	/**
	 * @param totalCToTConversionsCHGContext the totalCToTConversionsCHGContext to set
	 */
	public void setTotalCToTConversionsCHGContext(Integer totalCToTConversionsCHGContext) {
		this.totalCToTConversionsCHGContext = totalCToTConversionsCHGContext;
	}

	/**
	 * @return the totalCToTConversionsCHHContext
	 */
	public Integer getTotalCToTConversionsCHHContext() {
		return totalCToTConversionsCHHContext;
	}

	/**
	 * @param totalCToTConversionsCHHContext the totalCToTConversionsCHHContext to set
	 */
	public void setTotalCToTConversionsCHHContext(Integer totalCToTConversionsCHHContext) {
		this.totalCToTConversionsCHHContext = totalCToTConversionsCHHContext;
	}

	/**
	 * @return the cMethylatedCPGContext
	 */
	public BigDecimal getcMethylatedCPGContext() {
		return cMethylatedCPGContext;
	}

	/**
	 * @param cMethylatedCPGContext the cMethylatedCPGContext to set
	 */
	public void setcMethylatedCPGContext(BigDecimal cMethylatedCPGContext) {
		this.cMethylatedCPGContext = cMethylatedCPGContext;
	}

	/**
	 * @return the cMethylatedCHGContext
	 */
	public BigDecimal getcMethylatedCHGContext() {
		return cMethylatedCHGContext;
	}

	/**
	 * @param cMethylatedCHGContext the cMethylatedCHGContext to set
	 */
	public void setcMethylatedCHGContext(BigDecimal cMethylatedCHGContext) {
		this.cMethylatedCHGContext = cMethylatedCHGContext;
	}

	/**
	 * @return the cMethylatedCHHContext
	 */
	public BigDecimal getcMethylatedCHHContext() {
		return cMethylatedCHHContext;
	}

	/**
	 * @param cMethylatedCHHContext the cMethylatedCHHContext to set
	 */
	public void setcMethylatedCHHContext(BigDecimal cMethylatedCHHContext) {
		this.cMethylatedCHHContext = cMethylatedCHHContext;
	}

	/**
	 * @return the loadingTime
	 */
	public BigDecimal getLoadingTime() {
		return loadingTime;
	}

	/**
	 * @param loadingTime the loadingTime to set
	 */
	public void setLoadingTime(BigDecimal loadingTime) {
		this.loadingTime = loadingTime;
	}

	/**
	 * @return the aligmentTime
	 */
	public BigDecimal getAligmentTime() {
		return aligmentTime;
	}

	/**
	 * @param aligmentTime the aligmentTime to set
	 */
	public void setAligmentTime(BigDecimal aligmentTime) {
		this.aligmentTime = aligmentTime;
	}

	/**
	 * @return the totalTime
	 */
	public BigDecimal getTotalTime() {
		return totalTime;
	}

	/**
	 * @param totalTime the totalTime to set
	 */
	public void setTotalTime(BigDecimal totalTime) {
		this.totalTime = totalTime;
	}

	/**
	 * @return the totalReadsProcessed
	 */
	public Integer getTotalReadsProcessed() {
		return totalReadsProcessed;
	}

	/**
	 * @param totalReadsProcessed the totalReadsProcessed to set
	 */
	public void setTotalReadsProcessed(Integer totalReadsProcessed) {
		this.totalReadsProcessed = totalReadsProcessed;
	}

	/**
	 * @return the readsMapped
	 */
	public BigDecimal getReadsMapped() {
		return readsMapped;
	}

	/**
	 * @param readsMapped the readsMapped to set
	 */
	public void setReadsMapped(BigDecimal readsMapped) {
		this.readsMapped = readsMapped;
	}

	/**
	 * @return the totalReadsMapped
	 */
	public Integer getTotalReadsMapped() {
		return totalReadsMapped;
	}

	/**
	 * @param totalReadsMapped the totalReadsMapped to set
	 */
	public void setTotalReadsMapped(Integer totalReadsMapped) {
		this.totalReadsMapped = totalReadsMapped;
	}

	/**
	 * @return the readsUnmapped
	 */
	public BigDecimal getReadsUnmapped() {
		return readsUnmapped;
	}

	/**
	 * @param readsUnmapped the readsUnmapped to set
	 */
	public void setReadsUnmapped(BigDecimal readsUnmapped) {
		this.readsUnmapped = readsUnmapped;
	}

	/**
	 * @return the totalReadsUnmapped
	 */
	public Integer getTotalReadsUnmapped() {
		return totalReadsUnmapped;
	}

	/**
	 * @param totalReadsUnmapped the totalReadsUnmapped to set
	 */
	public void setTotalReadsUnmapped(Integer totalReadsUnmapped) {
		this.totalReadsUnmapped = totalReadsUnmapped;
	}
	
	/**
	 * @return the cytosineMethylationReport
	 */
	public BarChartModel getCytosineMethylationReport() {
		return cytosineMethylationReport;
	}

	/**
	 * @param cytosineMethylationReport the cytosineMethylationReport to set
	 */
	public void setCytosineMethylationReport(BarChartModel cytosineMethylationReport) {
		this.cytosineMethylationReport = cytosineMethylationReport;
	}

	/**
	 * @return the cMethylatedGraphic
	 */
	public PieChartModel getcMethylatedGraphic() {
		return cMethylatedGraphic;
	}

	/**
	 * @param cMethylatedGraphic the cMethylatedGraphic to set
	 */
	public void setcMethylatedGraphic(PieChartModel cMethylatedGraphic) {
		this.cMethylatedGraphic = cMethylatedGraphic;
	}

	/**
	 * @return the totalReadsProcessedGraphic
	 */
	public PieChartModel getTotalReadsProcessedGraphic() {
		return totalReadsProcessedGraphic;
	}

	/**
	 * @param totalReadsProcessedGraphic the totalReadsProcessedGraphic to set
	 */
	public void setTotalReadsProcessedGraphic(PieChartModel totalReadsProcessedGraphic) {
		this.totalReadsProcessedGraphic = totalReadsProcessedGraphic;
	}

	public void loadAnalysisResult(AnalysisResult analysisResult) {
		this.totalNumberCAnalysed = analysisResult.getTotalNumberCAnalysed();
		this.totalMethylatedCCPGContext = analysisResult.getTotalMethylatedCCPGContext();
		this.totalMethylatedCCHGContext = analysisResult.getTotalMethylatedCCHGContext();
		this.totalMethylatedCCHHContext = analysisResult.getTotalMethylatedCCHHContext();
		this.totalCToTConversionsCPGContext = analysisResult.getTotalCToTConversionsCPGContext();
		this.totalCToTConversionsCHGContext = analysisResult.getTotalCToTConversionsCHGContext();
		this.totalCToTConversionsCHHContext = analysisResult.getTotalCToTConversionsCHHContext();
		this.cMethylatedCPGContext = analysisResult.getcMethylatedCPGContext();
		this.cMethylatedCHGContext = analysisResult.getcMethylatedCHGContext();;
		this.cMethylatedCHHContext = analysisResult.getcMethylatedCHHContext();
		this.loadingTime = analysisResult.getLoadingTime();
		this.aligmentTime = analysisResult.getAligmentTime();
		this.totalTime = analysisResult.getTotalTime();
		this.totalReadsProcessed = analysisResult.getTotalReadsProcessed();
		this.readsMapped = analysisResult.getReadsMapped();
		this.totalReadsMapped = analysisResult.getTotalReadsMapped();
		this.readsUnmapped = analysisResult.getReadsUnmapped();
		this.totalReadsUnmapped = analysisResult.getTotalReadsUnmapped();
		
		loadCytosineMethylationReport();
		loadCMethylatedGraphic();
		loadTotalReadsProcessedGraphic();
	}
	
	private void loadCytosineMethylationReport() {
        
		this.cytosineMethylationReport = new BarChartModel();
		
		List<Number> dataSetValues = new ArrayList<>();
		dataSetValues.add(totalNumberCAnalysed);
		dataSetValues.add(totalMethylatedCCPGContext);
		dataSetValues.add(totalMethylatedCCHGContext);
		dataSetValues.add(totalMethylatedCCHHContext);
		dataSetValues.add(totalCToTConversionsCPGContext);
		dataSetValues.add(totalCToTConversionsCHGContext);
		dataSetValues.add(totalCToTConversionsCHHContext);
		
		List<String> backgroundColors = new ArrayList<>(); 
		backgroundColors.add("rgb(255, 255, 0)");
		backgroundColors.add("rgb(125, 125, 255)");
		backgroundColors.add("rgb(125, 255, 125)");
		backgroundColors.add("rgb(255, 125, 125)");
		backgroundColors.add("rgb(125, 125, 255)");
		backgroundColors.add("rgb(125, 255, 125)");
		backgroundColors.add("rgb(255, 125, 125)");
		
		List<String> borderColor = new ArrayList<>();  
		borderColor.add("rgb(200, 200, 0)");
		borderColor.add("rgb(80, 80, 200)");
		borderColor.add("rgb(80, 200, 80)");
		borderColor.add("rgb(200, 80, 80)");
		borderColor.add("rgb(80, 80, 200)");
		borderColor.add("rgb(80, 200, 80)");
		borderColor.add("rgb(200, 80, 80)");
		
		String totalNumberCAnalysedLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.totalNumberCAnalysed");
		
		String totalMethylatedCCPGContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.totalMethylatedCCPGContext");
		String totalMethylatedCCHGContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.totalMethylatedCCHGContext");
		String totalMethylatedCCHHContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.totalMethylatedCCHHContext");
		
		String totalCToTConversionsCPGContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.totalCToTConversionsCPGContext");
		String totalCToTConversionsCHGContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.totalCToTConversionsCHGContext");
		String totalCToTConversionsCHHContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.totalCToTConversionsCHHContext");
		
		List<String> labels = new ArrayList<>();
		labels.add(totalNumberCAnalysedLabel);
		labels.add(totalMethylatedCCPGContextLabel);
		labels.add(totalMethylatedCCHGContextLabel);
		labels.add(totalMethylatedCCHHContextLabel);
		labels.add(totalCToTConversionsCPGContextLabel);
		labels.add(totalCToTConversionsCHGContextLabel);
		labels.add(totalCToTConversionsCHHContextLabel);
		
		BarChartDataSet dataSet = new BarChartDataSet();
        dataSet.setData(dataSetValues);        
        dataSet.setBackgroundColor(backgroundColors);        
        dataSet.setBorderColor(borderColor);
        dataSet.setBorderWidth(1);
        
        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);               
        data.setLabels(labels);              

        String cytosineMethylationReportTitle = FacesContextUtils.geti18nMessage("analysis.detail.result.cytosineMethylationReport");
        
        Title title = new Title();
        title.setDisplay(true);
        title.setText(cytosineMethylationReportTitle); 
        
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
        
        this.cytosineMethylationReport.setData(data);
        this.cytosineMethylationReport.setOptions(options);
    }
	
	private void loadCMethylatedGraphic() {
		
		this.cMethylatedGraphic = new PieChartModel();     		
		
		BigDecimal other = new BigDecimal(100);
		other = other.subtract(cMethylatedCPGContext);
		other = other.subtract(cMethylatedCHGContext);
		other = other.subtract(cMethylatedCHHContext);
		
        List<Number> dataSetValues = new ArrayList<>();
        dataSetValues.add(cMethylatedCPGContext);
        dataSetValues.add(cMethylatedCHGContext); 
        dataSetValues.add(cMethylatedCHHContext); 
        dataSetValues.add(other); 

        List<String> backgroundColors = new ArrayList<>();
        backgroundColors.add("rgb(125, 125, 255)");
        backgroundColors.add("rgb(125, 255, 125)");
        backgroundColors.add("rgb(255, 125, 125)");
        backgroundColors.add("rgb(125, 125, 125)");
        
        String cMethylatedCPGContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.cMethylatedCPGContext");
        String cMethylatedCHGContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.cMethylatedCHGContext");
        String cMethylatedCHHContextLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.cMethylatedCHHContext");
        String otherLabeltLabel = FacesContextUtils.geti18nMessage("general.other");
        
        List<String> labels = new ArrayList<>();
        labels.add(cMethylatedCPGContextLabel);
        labels.add(cMethylatedCHGContextLabel);
        labels.add(cMethylatedCHHContextLabel);
        labels.add(otherLabeltLabel);
        
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(dataSetValues);
        dataSet.setBackgroundColor(backgroundColors);               

        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);        
        data.setLabels(labels);

        this.cMethylatedGraphic.setData(data);
	}
	
	private void loadTotalReadsProcessedGraphic() {
		
		this.totalReadsProcessedGraphic = new PieChartModel();     		
		
        List<Number> dataSetValues = new ArrayList<>();
        dataSetValues.add(totalReadsMapped);
        dataSetValues.add(totalReadsUnmapped);                

        List<String> backgroundColors = new ArrayList<>();
        backgroundColors.add("rgb(125, 255, 125)");
        backgroundColors.add("rgb(255, 125, 125)");
        
        String singleEndModeLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.readsMapped");
        String pairedEndModeLabel = FacesContextUtils.geti18nMessage("analysis.detail.result.readsUnmapped");
        
        List<String> labels = new ArrayList<>();
        labels.add(singleEndModeLabel);
        labels.add(pairedEndModeLabel);
        
        PieChartDataSet dataSet = new PieChartDataSet();
        dataSet.setData(dataSetValues);
        dataSet.setBackgroundColor(backgroundColors);               

        ChartData data = new ChartData();
        data.addChartDataSet(dataSet);        
        data.setLabels(labels);

        this.totalReadsProcessedGraphic.setData(data);
	}
	
}
