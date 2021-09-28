ALTER TABLE "analysis_result" 
ADD error text,
ALTER COLUMN result_file DROP NOT NULL;
