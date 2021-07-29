CREATE TABLE "file" (
    id uuid DEFAULT uuid_generate_v4(),
    "user" uuid NOT NULL,
    file_name VARCHAR(255) NOT NULL,
    path VARCHAR(255) NOT NULL,
    size integer,
    content_type VARCHAR(100),
    stored BOOLEAN NOT NULL DEFAULT true,
    uploaded BOOLEAN NOT NULL DEFAULT true,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),   
    CONSTRAINT file_pkey PRIMARY KEY (id),
    CONSTRAINT file_user_fkey FOREIGN KEY ("user") 
    	REFERENCES "user"(id) MATCH SIMPLE 
    	ON UPDATE CASCADE 
    	ON DELETE CASCADE
);