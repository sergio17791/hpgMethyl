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
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT now(),
    PRIMARY KEY (id)
);