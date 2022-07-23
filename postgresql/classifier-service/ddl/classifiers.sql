CREATE DATABASE classifiers;

GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres;

\connect classifiers;
CREATE SCHEMA IF NOT EXISTS classifiers AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS classifiers.categories (
    uuid uuid NOT NULL UNIQUE,
    title text COLLATE pg_catalog."default" NOT NULL UNIQUE,
    dt_create timestamp WITHOUT time zone NOT NULL,
    dt_update time WITHOUT time zone NOT NULL
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS classifiers.countries (
    uuid uuid NOT NULL UNIQUE,
    title text COLLATE pg_catalog."default" NOT NULL UNIQUE,
    description text COLLATE pg_catalog."default" NOT NULL,
    dt_create timestamp WITHOUT time zone NOT NULL,
    dt_update timestamp WITHOUT time zone NOT NULL
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS classifiers.categories OWNER TO postgres;

ALTER TABLE IF EXISTS classifiers.countries OWNER TO postgres;

ALTER TABLE classifiers.categories
    ADD CONSTRAINT categories_pkey PRIMARY KEY (uuid);

ALTER TABLE classifiers.countries
    ADD CONSTRAINT countries_pkey PRIMARY KEY (uuid);