CREATE TABLE "analysis_result" (
    id uuid DEFAULT uuid_generate_v4(),
    analysis_request uuid NOT NULL UNIQUE,
    result_file uuid NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),   
    CONSTRAINT analysis_result_pkey PRIMARY KEY (id),
    CONSTRAINT analysis_result_request_fkey FOREIGN KEY ("analysis_request") 
    	REFERENCES "analysis_request"(id) MATCH SIMPLE 
    	ON UPDATE CASCADE 
    	ON DELETE CASCADE,
    CONSTRAINT analysis_result_file_fkey FOREIGN KEY ("result_file") 
    	REFERENCES "file"(id) MATCH SIMPLE 
    	ON UPDATE CASCADE 
    	ON DELETE CASCADE
);