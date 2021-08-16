package es.hpgMethyl.builders;

import java.math.BigDecimal;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.types.PairedMode;

public class AnalysisCommandBuilder {
	
	private String BWT_INDEX = "--bwt-index";
	
	private String CAL_FLANK_SIZE = "--cal-flank-size";
	
	private String CAL_UMBRAL_LENGTH_FACTOR = "--umbral-cal-length-factor";
	
	private String CPU_THREADS = "--cpu-threads";
	
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
	
	private String READ_BATCH_SIZE = "--read-batch-size";
	
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
	
	private String WRITE_BATCH_SIZE = "--write-batch-size";
	
	private String WRITE_MCONTEXT = "--write-mcontext";
	
	public String build(Configuration configuration, AnalysisRequest analysisRequest) {
		
		String hpgMethylAbsolutePath = configuration.getHpgMethylAbsolutePath();
		
		String bwtIndexAbsolutePath = configuration.getBwtIndexAbsolutePath();
		
		String command = hpgMethylAbsolutePath + " bs " + BWT_INDEX + "  " + bwtIndexAbsolutePath;
		
		String userDirectory = analysisRequest.getUser().getId().toString();
		String analysisRequestDirectory = analysisRequest.getId().toString();
		
		String outputDirectory = concatenateDirectory(configuration.getUsersDirectoryAbsolutePath(), userDirectory);
		outputDirectory = concatenateDirectory(outputDirectory, analysisRequestDirectory);
		
		command = command + " " + OUTDIR + " " + outputDirectory + " " + CPU_THREADS + " " + configuration.getCpuThreads();
		
		Integer readBatchSize = configuration.getReadBatchSize();
		if(readBatchSize != null) {
			command = command + " " + READ_BATCH_SIZE + " " + readBatchSize;
		}
		
		Integer writeBatchSize = configuration.getWriteBatchSize();
		if(writeBatchSize != null) {
			command = command + " " + WRITE_BATCH_SIZE + " " + writeBatchSize;
		}
		
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
	    	
	    	Integer pairedMaxDistance = analysisRequest.getPairedMaxDistance();
	    	if(pairedMaxDistance != null) {
	    		command = command + " " + PAIRED_MAX_DISTANCE + " " + pairedMaxDistance;
	    	}
		    
	    	Integer pairedMinDistance = analysisRequest.getPairedMinDistance();
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
	    
	    Integer calFlankSize = analysisRequest.getCalFlankSize();
	    if(calFlankSize != null) {
	    	command = command + " " + CAL_FLANK_SIZE + " " + calFlankSize;
	    }
	    
	    Integer minimumCalSize = analysisRequest.getMinimumCalSize();
	    if(minimumCalSize != null) {
	    	command = command + " " + MINIMUM_CAL_SIZE + " " + minimumCalSize;
	    }
	    
	    BigDecimal calUmbralLengthFactor = analysisRequest.getCalUmbralLengthFactor();
	    if(calUmbralLengthFactor != null) {
	    	command = command + " " + CAL_UMBRAL_LENGTH_FACTOR + " " + calUmbralLengthFactor;
	    }
	    
	    Integer maximumBetweenSeeds = analysisRequest.getMaximumBetweenSeeds();
	    if(maximumBetweenSeeds != null) {
	    	command = command + " " + MAXIMUM_DISTANCE_SEEDS + " " + maximumBetweenSeeds;
	    }
	    
	    Integer maximumSeedSize = analysisRequest.getMaximumSeedSize();
	    if(maximumSeedSize != null) {
	    	command = command + " " + MAXIMUM_SEED_SIZE + " " + maximumBetweenSeeds;
	    }
	    
	    Integer minimumSeedSize = analysisRequest.getMinimumSeedSize();
	    if(minimumSeedSize != null) {
	    	command = command + " " + MINIMUM_SEED_SIZE + " " + minimumSeedSize;
	    }
	    
	    Integer numberSeedsPerRead = analysisRequest.getNumberSeedsPerRead();
	    if(numberSeedsPerRead != null) {
	    	command = command + " " + NUMBER_SEEDS_PER_READ + " " + numberSeedsPerRead;
	    }
	    
	    Integer readMinimumDiscardLength = analysisRequest.getReadMinimumDiscardLength();
	    if(readMinimumDiscardLength != null) {
	    	command = command + " " + READ_MINIMUM_DISCARD_LENGTH + " " + readMinimumDiscardLength;
	    }
	    
	    Integer readMaximumInnerGap = analysisRequest.getReadMaximumInnerGap();
	    if(readMaximumInnerGap != null) {
	    	command = command + " " + READ_MAXIMUM_INNER_GAP + " " + readMaximumInnerGap;
	    }
	    
	    Integer minimumNumberSeeds = analysisRequest.getMinimumNumberSeeds();
	    if(minimumNumberSeeds != null) {
	    	command = command + " " + MINIMUM_NUMBER_SEEDS + " " + minimumNumberSeeds;
	    }
	    
	    Integer filterReadMappings = analysisRequest.getFilterReadMappings();
	    if(filterReadMappings != null) {
	    	command = command + " " + FILTER_READ_MAPPINGS + " " + filterReadMappings;
	    }
	    
	    Integer filterSeedMappings = analysisRequest.getFilterSeedMappings();
	    if(filterSeedMappings != null) {
	    	command = command + " " + FILTER_SEED_MAPPINGS + " " + filterSeedMappings;
	    }
	    
	    if(analysisRequest.getReportAll()) {
	    	command = command + " " + REPORT_ALL;
	    }
	    
	    if(analysisRequest.getReportBest()) {
	    	command = command + " " + REPORT_BEST;
	    }
	    
	    Integer reportNBest = analysisRequest.getReportNBest();
	    if(reportNBest != null) {
	    	command = command + " " + REPORT_N_BEST + " " + reportNBest;
	    }
	    
	    Integer reportNHits = analysisRequest.getReportNHits();
	    if(reportNHits != null) {
	    	command = command + " " + REPORT_N_HITS + " " + reportNHits;
	    }
		
		return command;
	}
	
	private String concatenateDirectory(String path, String directory) {
		
		String completePath = path;
		
		String lastCharacter = completePath.substring(completePath.length() - 1);
		
		if(!lastCharacter.equals("/")) {
			completePath = completePath.concat("/");
		}
		
		completePath = completePath.concat(directory);
		
		return completePath;
	}
}
