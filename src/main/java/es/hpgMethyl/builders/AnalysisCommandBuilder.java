package es.hpgMethyl.builders;

import java.math.BigDecimal;

import es.hpgMethyl.entities.AnalysisRequest;
import es.hpgMethyl.types.PairedMode;

public class AnalysisCommandBuilder {

	private AnalysisRequest analysisRequest;
	
	private String BWT_INDEX = "--bwt-index";
	
	private String FASTQ_FILE_PATH = "--fastq";
	
	private String PAIRED_MODE = "--paired-mode";
	
	private String PAIRED_MAX_DISTANCE = "--paired-max-distance";
	
	private String PAIRED_MIN_DISTANCE = "--paired-min-distance";
	
	private String SECOND_FASTQ_FILE_PATH = "--fastq2";
	
	private String WRITE_MCONTEXT = "--write-mcontext";
	
	public AnalysisCommandBuilder(AnalysisRequest analysisRequest) {
		this.analysisRequest = analysisRequest;
	}
	
	public String build() {
		
		String command = "./opt/hpg-methyl bs " + BWT_INDEX + "  /data/tomcat/genome/bs-index/";
		
		String fastqFilePath = analysisRequest.getInputReadFile().getPath();
		
		command = command + " " + FASTQ_FILE_PATH + " " + fastqFilePath;
		
		if(analysisRequest.getWriteMethylationContext()) {
			command = command + " " + WRITE_MCONTEXT;
		}
		
	    //read_batch_size BOOLEAN NOT NULL DEFAULT false,
	    //write_batch_size BOOLEAN NOT NULL DEFAULT false,
		
	    PairedMode pairedMode = analysisRequest.getPairedMode();
	    
		command = command + " " + PAIRED_MODE + " " + pairedMode.getValue();
		
	    if(PairedMode.PAIRED_END_MODE.equals(pairedMode)) {

	    	String secondFastqFilePath = analysisRequest.getPairedEndModeFile().getPath();
	    	command = command + " " + SECOND_FASTQ_FILE_PATH + " " + secondFastqFilePath;
	    	
	    	//paired_max_distance integer,
	    	BigDecimal pairedMaxDistance = analysisRequest.getPairedMaxDistance();
	    	if(pairedMaxDistance != null) {
	    		command = command + " " + PAIRED_MAX_DISTANCE + " " + pairedMaxDistance;
	    	}
		    
		    //paired_min_distance integer,
	    	BigDecimal pairedMinDistance = analysisRequest.getPairedMinDistance();
	    	if(pairedMinDistance != null) {
	    		command = command + " " + PAIRED_MIN_DISTANCE + " " + pairedMinDistance;
	    	}
	    }
		
	    //swa_minimun_score numeric,
	    //swa_match_score numeric,
	    //swa_mismatch_score numeric,
	    //swa_gap_open numeric,
	    //swa_gap_extend numeric,
	    //cal_flank_size integer,
	    //minimum_cal_size integer,
	    //cal_umbral_length_factor numeric,
	    //maximum_between_seeds integer,
	    //maximum_seed_size integer,
	    //minimum_seed_size integer,
	    //number_seeds_per_read integer,
	    //read_minimum_discard_length integer,
	    //read_maximum_inner_gap integer,
	    //minimum_number_seeds integer,
	    //filter_read_mappings integer,
	    //filter_seed_mappings integer,
	    //report_all BOOLEAN NOT NULL DEFAULT false,
	    //report_best BOOLEAN NOT NULL DEFAULT true,
	    //report_n_best integer,
	    //report_n_hits integer,
				
		
		if(analysisRequest.getReadBatchSize()) {
			command = command + " --write-mcontext";
		}
		
		return command;
	}
}
