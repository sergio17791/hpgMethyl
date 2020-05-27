CREATE TABLE "user" (
    id uuid DEFAULT uuid_generate_v4(),
    first_name VARCHAR(60) NOT NULL,
    last_name VARCHAR(60) NOT NULL,
    email VARCHAR(60) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN DEFAULT true,
    PRIMARY KEY (id)
);