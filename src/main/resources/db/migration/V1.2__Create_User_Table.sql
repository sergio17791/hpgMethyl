CREATE TABLE "user" (
    id uuid DEFAULT uuid_generate_v4(),
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(256) NOT NULL,
    password_salt VARCHAR(40) NOT NULL,
    password_recovery_question VARCHAR(60) NOT NULL,
    password_recovery_response VARCHAR(256) NOT NULL,
    password_recovery_response_salt VARCHAR(40) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT true,
    default_language VARCHAR(10) NOT NULL DEFAULT 'es',
    role VARCHAR(60) NOT NULL DEFAULT 'USER',
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    CONSTRAINT user_pkey PRIMARY KEY (id),
    CONSTRAINT role_values CHECK (role IN ('USER','MODERATOR','ADMIN'))
);