CREATE TABLE "configuration" (
    id uuid DEFAULT uuid_generate_v4(),
    users_directory_absolute_path VARCHAR(255) NOT NULL,
    hpgMethyl_absolute_path VARCHAR(255) NOT NULL,
    bwt_index_absolute_path VARCHAR(255) NOT NULL,
    cpu_threads integer NOT NULL,
    maximum_user_analysis_pending integer,
    maximum_user_files_stored integer,
    file_size_limit bigint,
    read_batch_size integer,
    write_batch_size integer,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),   
    CONSTRAINT configuration_pkey PRIMARY KEY (id)
);