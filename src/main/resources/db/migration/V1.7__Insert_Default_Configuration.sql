INSERT INTO "configuration" (
    id,
    users_directory_absolute_path,
    hpgMethyl_absolute_path,
    bwt_index_absolute_path,
    cpu_threads,
    maximum_user_analysis_pending,
    maximum_user_files_stored,
    read_batch_size,
    write_batch_size,
    created_at,
    updated_at  
) VALUES (
	uuid_generate_v4(),
	'/data/tomcat/files/users/',
	'/opt/hpg-methyl',
	'/data/tomcat/genome/bs-index/',
	4,
	4,
	2,
	null,
	null,
	now(),
	now()
);