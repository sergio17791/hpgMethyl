package es.hpgMethyl.builders;

import java.math.BigDecimal;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.types.PairedMode;
import es.hpgMethyl.utils.FileUtils;

public class AnalysisCommandBuilder {

	private AnalysisRequest analysisRequest;
	
	private String BWT_INDEX = "--bwt-index";
	
	private String CAL_FLANK_SIZE = "--cal-flank-size";
	
	private String CAL_UMBRAL_LENGTH_FACTOR = "--umbral-cal-length-factor"; 
	
	private String FASTQ_FILE_PATH = "--fastq";
	
	private String FILTER_READ_MAPPINGS = "--filter-read-mappings";
	
	private String FILTER_SEED_MAPPINGS = "--filter-seed-mappings";
	
	private String MAXIMUM_DISTANCE_SEEDS = "--max-distance-seeds";
	
	private String MAXIMUM_SEED_SIZE = "--seed-size";
	
	private String MINIMUM_CAL_SIZE = "--min-cal-size";
	
	private String MINIMUM_NUMBER_SEEDS = "--min-num-seeds";
	
	private String MINIMUM_SEED_SIZE = "--min-seed-size";
	
	private String NUMBER_SEEDS_PER_READ = "--num-seeds";
	
	private String OUTDIR = "--outdir";
	
	private String PAIRED_MODE = "--paired-mode";
	
	private String PAIRED_MAX_DISTANCE = "--paired-max-distance";
	
	private String PAIRED_MIN_DISTANCE = "--paired-min-distance";
	
	private String READ_MAXIMUM_INNER_GAP = "--max-inner-gap";
	
	private String READ_MINIMUM_DISCARD_LENGTH = "--min-read-discard";
	
	private String REPORT_ALL = "--report-all";
	
	private String REPORT_BEST = "--report-best";
	
	private String REPORT_N_BEST = "--report-n-best";
	
	private String REPORT_N_HITS = "--report-n-hits";
	
	private String SECOND_FASTQ_FILE_PATH = "--fastq2";
	
	private String SMITH_WATERMAN_GAP_EXTEND = "--sw-gap-extend";
	
	private String SMITH_WATERMAN_GAP_OPEN = "--sw-gap-open";
	
	private String SMITH_WATERMAN_MATCH_SCORE = "--sw-match";
	
	private String SMITH_WATERMAN_MISMATCH_SCORE = "--sw-mismatch";
	
	private String SMITH_WATERMAN_MINIMUM_SCORE = "--sw-min-score";
	
	private String WRITE_MCONTEXT = "--write-mcontext";
	
	public AnalysisCommandBuilder(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}
	
	public String build() {
		
		String command = "./opt/hpg-methyl bs " + BWT_INDEX + "  /data/tomcat/genome/bs-index/";
		
		String outputDirectory = FileUtils.USERS_FILES_BASE_PATH + analysisRequest.getId();
		
		command = command + " " + OUTDIR + " " + outputDirectory;
		
		String fastqFilePath = analysisRequest.getInputReadFile().getPath();
		
		command = command + " " + FASTQ_FILE_PATH + " " + fastqFilePath;
		
		if(analysisRequest.getWriteMethylationContext()) {
			command = command + " " + WRITE_MCONTEXT;
		}
		
	    PairedMode pairedMode = analysisRequest.getPairedMode();
	    
		command = command + " " + PAIRED_MODE + " " + pairedMode.getValue();
		
	    if(PairedMode.PAIRED_END_MODE.equals(pairedMode)) {

	    	String secondFastqFilePath = analysisRequest.getPairedEndModeFile().getPath();
	    	command = command + " " + SECOND_FASTQ_FILE_PATH + " " + secondFastqFilePath;
	    	
	    	BigDecimal pairedMaxDistance = analysisRequest.getPairedMaxDistance();
	    	if(pairedMaxDistance != null) {
	    		command = command + " " + PAIRED_MAX_DISTANCE + " " + pairedMaxDistance;
	    	}
		    
	    	BigDecimal pairedMinDistance = analysisRequest.getPairedMinDistance();
	    	if(pairedMinDistance != null) {
	    		command = command + " " + PAIRED_MIN_DISTANCE + " " + pairedMinDistance;
	    	}
	    }
	    
	    BigDecimal smithWatermanMinimumScore = analysisRequest.getSwaMinimunScore();
	    if(smithWatermanMinimumScore != null) {
	    	command = command + " " + SMITH_WATERMAN_MINIMUM_SCORE + " " + smithWatermanMinimumScore;
	    }
	    
	    BigDecimal smithWatermanMatchScore = analysisRequest.getSwaMatchScore();
	    if(smithWatermanMatchScore != null) {
	    	command = command + " " + SMITH_WATERMAN_MATCH_SCORE + " " + smithWatermanMatchScore;
	    }
	    
	    BigDecimal smithWatermanMismatchScore = analysisRequest.getSwaMismatchScore();
	    if(smithWatermanMismatchScore != null) {
	    	command = command + " " + SMITH_WATERMAN_MISMATCH_SCORE + " " + smithWatermanMismatchScore;
	    }
	    
	    BigDecimal smithWatermanGapOpen = analysisRequest.getSwaGapOpen();
	    if(smithWatermanGapOpen != null) {
	    	command = command + " " + SMITH_WATERMAN_GAP_OPEN + " " + smithWatermanGapOpen;
	    }
	    
	    BigDecimal smithWatermanGapExtend = analysisRequest.getSwaGapExtend();
	    if(smithWatermanGapExtend != null) {
	    	command = command + " " + SMITH_WATERMAN_GAP_EXTEND + " " + smithWatermanGapExtend;
	    }
	    
	    BigDecimal calFlankSize = analysisRequest.getCalFlankSize();
	    if(calFlankSize != null) {
	    	command = command + " " + CAL_FLANK_SIZE + " " + calFlankSize;
	    }
	    
	    BigDecimal minimumCalSize = analysisRequest.getMinimumCalSize();
	    if(minimumCalSize != null) {
	    	command = command + " " + MINIMUM_CAL_SIZE + " " + minimumCalSize;
	    }
	    
	    BigDecimal calUmbralLengthFactor = analysisRequest.getCalUmbralLengthFactor();
	    if(calUmbralLengthFactor != null) {
	    	command = command + " " + CAL_UMBRAL_LENGTH_FACTOR + " " + calUmbralLengthFactor;
	    }
	    
	    BigDecimal maximumBetweenSeeds = analysisRequest.getMaximumBetweenSeeds();
	    if(maximumBetweenSeeds != null) {
	    	command = command + " " + MAXIMUM_DISTANCE_SEEDS + " " + maximumBetweenSeeds;
	    }
	    
	    BigDecimal maximumSeedSize = analysisRequest.getMaximumSeedSize();
	    if(maximumSeedSize != null) {
	    	command = command + " " + MAXIMUM_SEED_SIZE + " " + maximumBetweenSeeds;
	    }
	    
	    BigDecimal minimumSeedSize = analysisRequest.getMinimumSeedSize();
	    if(minimumSeedSize != null) {
	    	command = command + " " + MINIMUM_SEED_SIZE + " " + minimumSeedSize;
	    }
	    
	    BigDecimal numberSeedsPerRead = analysisRequest.getNumberSeedsPerRead();
	    if(numberSeedsPerRead != null) {
	    	command = command + " " + NUMBER_SEEDS_PER_READ + " " + numberSeedsPerRead;
	    }
	    
	    BigDecimal readMinimumDiscardLength = analysisRequest.getReadMinimumDiscardLength();
	    if(readMinimumDiscardLength != null) {
	    	command = command + " " + READ_MINIMUM_DISCARD_LENGTH + " " + readMinimumDiscardLength;
	    }
	    
	    BigDecimal readMaximumInnerGap = analysisRequest.getReadMaximumInnerGap();
	    if(readMaximumInnerGap != null) {
	    	command = command + " " + READ_MAXIMUM_INNER_GAP + " " + readMaximumInnerGap;
	    }
	    
	    BigDecimal minimumNumberSeeds = analysisRequest.getMinimumNumberSeeds();
	    if(minimumNumberSeeds != null) {
	    	command = command + " " + MINIMUM_NUMBER_SEEDS + " " + minimumNumberSeeds;
	    }
	    
	    BigDecimal filterReadMappings = analysisRequest.getFilterReadMappings();
	    if(filterReadMappings != null) {
	    	command = command + " " + FILTER_READ_MAPPINGS + " " + filterReadMappings;
	    }
	    
	    BigDecimal filterSeedMappings = analysisRequest.getFilterSeedMappings();
	    if(filterSeedMappings != null) {
	    	command = command + " " + FILTER_SEED_MAPPINGS + " " + filterSeedMappings;
	    }
	    
	    if(analysisRequest.getReportAll()) {
	    	command = command + " " + REPORT_ALL;
	    }
	    
	    if(analysisRequest.getReportBest()) {
	    	command = command + " " + REPORT_BEST;
	    }
	    
	    BigDecimal reportNBest = analysisRequest.getReportNBest();
	    if(reportNBest != null) {
	    	command = command + " " + REPORT_N_BEST + " " + reportNBest;
	    }
	    
	    BigDecimal reportNHits = analysisRequest.getReportNHits();
	    if(reportNHits != null) {
	    	command = command + " " + REPORT_N_HITS + " " + reportNHits;
	    }
		
		return command;
	}
}