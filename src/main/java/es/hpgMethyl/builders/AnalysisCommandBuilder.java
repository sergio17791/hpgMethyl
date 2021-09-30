package es.hpgMethyl.builders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.entities.Configuration;
import es.hpgMethyl.types.PairedMode;

public class AnalysisCommandBuilder {
	
	private String BISULFITE_MODE = "bs";
	
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
	
	public List<String> build(Configuration configuration, AnalysisRequest analysisRequest, String outputDirectory) {
		
		List<String> command = new ArrayList<String>();
		
		String hpgMethylAbsolutePath = configuration.getHpgMethylAbsolutePath();
		
		String bwtIndexAbsolutePath = configuration.getBwtIndexAbsolutePath();
		
		command.add(hpgMethylAbsolutePath);
		command.add(BISULFITE_MODE);
		command.add(BWT_INDEX);
		command.add(bwtIndexAbsolutePath);
		command.add(OUTDIR);
		command.add(outputDirectory);
		command.add(CPU_THREADS);
		command.add(configuration.getCpuThreads().toString());
		
		Integer readBatchSize = configuration.getReadBatchSize();
		if(readBatchSize != null) {
			command.add(READ_BATCH_SIZE);
			command.add(readBatchSize.toString());
		}
		
		Integer writeBatchSize = configuration.getWriteBatchSize();
		if(writeBatchSize != null) {
			command.add(WRITE_BATCH_SIZE);
			command.add(writeBatchSize.toString());
		}
		
		String fastqFilePath = analysisRequest.getInputReadFile().getPath();
		
		command.add(FASTQ_FILE_PATH);
		command.add(fastqFilePath);
		
		if(analysisRequest.getWriteMethylationContext()) {
			command.add(WRITE_MCONTEXT);
		}
		
	    PairedMode pairedMode = analysisRequest.getPairedMode();
	    
	    command.add(PAIRED_MODE);
		command.add(pairedMode.getValue().toString());
		
	    if(PairedMode.PAIRED_END_MODE.equals(pairedMode)) {

	    	String secondFastqFilePath = analysisRequest.getPairedEndModeFile().getPath();
	    	command.add(SECOND_FASTQ_FILE_PATH);
			command.add(secondFastqFilePath);
	    	
	    	Integer pairedMaxDistance = analysisRequest.getPairedMaxDistance();
	    	if(pairedMaxDistance != null) {
	    		command.add(PAIRED_MAX_DISTANCE);
	    		command.add(pairedMaxDistance.toString());
	    	}
		    
	    	Integer pairedMinDistance = analysisRequest.getPairedMinDistance();
	    	if(pairedMinDistance != null) {
	    		command.add(PAIRED_MIN_DISTANCE);
	    		command.add(pairedMinDistance.toString());
	    	}
	    }
	    
	    BigDecimal smithWatermanMinimumScore = analysisRequest.getSwaMinimunScore();
	    if(smithWatermanMinimumScore != null) {
	    	command.add(SMITH_WATERMAN_MINIMUM_SCORE);
    		command.add(smithWatermanMinimumScore.toString());
	    }
	    
	    BigDecimal smithWatermanMatchScore = analysisRequest.getSwaMatchScore();
	    if(smithWatermanMatchScore != null) {
	    	command.add(SMITH_WATERMAN_MATCH_SCORE);
    		command.add(smithWatermanMatchScore.toString());
	    }
	    
	    BigDecimal smithWatermanMismatchScore = analysisRequest.getSwaMismatchScore();
	    if(smithWatermanMismatchScore != null) {
	    	command.add(SMITH_WATERMAN_MISMATCH_SCORE);
    		command.add(smithWatermanMismatchScore.toString());
	    }
	    
	    BigDecimal smithWatermanGapOpen = analysisRequest.getSwaGapOpen();
	    if(smithWatermanGapOpen != null) {
	    	command.add(SMITH_WATERMAN_GAP_OPEN);
    		command.add(smithWatermanGapOpen.toString());
	    }
	    
	    BigDecimal smithWatermanGapExtend = analysisRequest.getSwaGapExtend();
	    if(smithWatermanGapExtend != null) {
	    	command.add(SMITH_WATERMAN_GAP_EXTEND);
    		command.add(smithWatermanGapExtend.toString());
	    }
	    
	    Integer calFlankSize = analysisRequest.getCalFlankSize();
	    if(calFlankSize != null) {
	    	command.add(CAL_FLANK_SIZE);
    		command.add(calFlankSize.toString());
	    }
	    
	    Integer minimumCalSize = analysisRequest.getMinimumCalSize();
	    if(minimumCalSize != null) {
	    	command.add(MINIMUM_CAL_SIZE);
    		command.add(minimumCalSize.toString());
	    }
	    
	    BigDecimal calUmbralLengthFactor = analysisRequest.getCalUmbralLengthFactor();
	    if(calUmbralLengthFactor != null) {
	    	command.add(CAL_UMBRAL_LENGTH_FACTOR);
    		command.add(calUmbralLengthFactor.toString());
	    }
	    
	    Integer maximumBetweenSeeds = analysisRequest.getMaximumBetweenSeeds();
	    if(maximumBetweenSeeds != null) {
	    	command.add(MAXIMUM_DISTANCE_SEEDS);
    		command.add(maximumBetweenSeeds.toString());
	    }
	    
	    Integer maximumSeedSize = analysisRequest.getMaximumSeedSize();
	    if(maximumSeedSize != null) {
	    	command.add(MAXIMUM_SEED_SIZE);
    		command.add(maximumSeedSize.toString());
	    }
	    
	    Integer minimumSeedSize = analysisRequest.getMinimumSeedSize();
	    if(minimumSeedSize != null) {
	    	command.add(MINIMUM_SEED_SIZE);
    		command.add(minimumSeedSize.toString());
	    }
	    
	    Integer numberSeedsPerRead = analysisRequest.getNumberSeedsPerRead();
	    if(numberSeedsPerRead != null) {
	    	command.add(NUMBER_SEEDS_PER_READ);
    		command.add(numberSeedsPerRead.toString());
	    }
	    
	    Integer readMinimumDiscardLength = analysisRequest.getReadMinimumDiscardLength();
	    if(readMinimumDiscardLength != null) {
	    	command.add(READ_MINIMUM_DISCARD_LENGTH);
    		command.add(readMinimumDiscardLength.toString());
	    }
	    
	    Integer readMaximumInnerGap = analysisRequest.getReadMaximumInnerGap();
	    if(readMaximumInnerGap != null) {
	    	command.add(READ_MAXIMUM_INNER_GAP);
    		command.add(readMaximumInnerGap.toString());
	    }
	    
	    Integer minimumNumberSeeds = analysisRequest.getMinimumNumberSeeds();
	    if(minimumNumberSeeds != null) {
	    	command.add(MINIMUM_NUMBER_SEEDS);
    		command.add(minimumNumberSeeds.toString());
	    }
	    
	    Integer filterReadMappings = analysisRequest.getFilterReadMappings();
	    if(filterReadMappings != null) {
	    	command.add(FILTER_READ_MAPPINGS);
    		command.add(filterReadMappings.toString());
	    }
	    
	    Integer filterSeedMappings = analysisRequest.getFilterSeedMappings();
	    if(filterSeedMappings != null) {
	    	command.add(FILTER_SEED_MAPPINGS);
    		command.add(filterSeedMappings.toString());
	    }
	    
	    if(analysisRequest.getReportAll()) {
	    	command.add(REPORT_ALL);
	    }
	    
	    if(analysisRequest.getReportBest()) {
	    	command.add(REPORT_BEST);
	    }
	    
	    Integer reportNBest = analysisRequest.getReportNBest();
	    if(reportNBest != null) {
	    	command.add(REPORT_N_BEST);
	    	command.add(reportNBest.toString());
	    }
	    
	    Integer reportNHits = analysisRequest.getReportNHits();
	    if(reportNHits != null) {
	    	command.add(REPORT_N_HITS);
	    	command.add(reportNHits.toString());
	    }
		
		return command;
	}		
}
