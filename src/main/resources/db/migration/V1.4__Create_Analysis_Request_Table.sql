CREATE SEQUENCE analysis_number_sequence START 1;
CREATE TABLE "analysis_request" (
    id uuid DEFAULT uuid_generate_v4(),
    "user" uuid NOT NULL,
    identifier VARCHAR(60) NOT NULL,
    input_read_file uuid NOT NULL,
    write_methylation_context BOOLEAN NOT NULL DEFAULT false,
    paired_mode smallint NOT NULL DEFAULT 0,
    paired_end_mode_file uuid,
    paired_max_distance integer,
    paired_min_distance integer,
    swa_minimun_score numeric,
    swa_match_score numeric,
    swa_mismatch_score numeric,
    swa_gap_open numeric,
    swa_gap_extend numeric,
    cal_flank_size integer,
    minimum_cal_size integer,
    cal_umbral_length_factor numeric,
    maximum_between_seeds integer,
    maximum_seed_size integer,
    minimum_seed_size integer,
    number_seeds_per_read integer,
    read_minimum_discard_length integer,
    read_maximum_inner_gap integer,
    minimum_number_seeds integer,
    filter_read_mappings integer,
    filter_seed_mappings integer,
    report_all BOOLEAN NOT NULL DEFAULT false,
    report_best BOOLEAN NOT NULL DEFAULT true,
    report_n_best integer,
    report_n_hits integer,
    status VARCHAR(60) NOT NULL DEFAULT 'CREATED',
    number integer default nextval('analysis_number_sequence'),
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),   
    CONSTRAINT analysis_request_pkey PRIMARY KEY (id),
    CONSTRAINT analysis_request_user_fkey FOREIGN KEY ("user") 
    	REFERENCES "user"(id) MATCH SIMPLE 
    	ON UPDATE CASCADE 
    	ON DELETE CASCADE,
    CONSTRAINT analysis_request_input_file_fkey FOREIGN KEY ("input_read_file") 
    	REFERENCES "file"(id) MATCH SIMPLE 
    	ON UPDATE CASCADE 
    	ON DELETE CASCADE,
    CONSTRAINT analysis_request_paired_file_fkey FOREIGN KEY ("paired_end_mode_file") 
    	REFERENCES "file"(id) MATCH SIMPLE 
    	ON UPDATE CASCADE 
    	ON DELETE CASCADE,
    CONSTRAINT paired_mode_values CHECK (paired_mode IN (0, 1)),
    CONSTRAINT status_values CHECK (status IN ('CREATED','PROCESSING','COMPLETED'))
);