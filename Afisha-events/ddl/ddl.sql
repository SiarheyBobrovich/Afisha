CREATE SCHEMA IF NOT EXISTS afisha AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS afisha.actions (
    uuid uuid NOT NULL UNIQUE,
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    type character varying(8) COLLATE pg_catalog."default" NOT NULL
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.events (
    uuid uuid NOT NULL UNIQUE,
    author varchar(50) NOT NULL,
    dt_event timestamp WITHOUT time zone NOT NULL,
    dt_end_of_sale timestamp WITHOUT time zone NOT NULL,
    status character varying(9) COLLATE pg_catalog."default" NOT NULL,
    dt_create timestamp WITHOUT time zone NOT NULL,
    dt_update timestamp(3) WITHOUT time zone NOT NULL
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.events_actions (
    event_uuid uuid NOT NULL,
    action_uuid uuid NOT NULL
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.concerts_descriptions (
    uuid uuid NOT NULL,
    category uuid NOT NULL
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.films_descriptions (
    country uuid NOT NULL,
    release_year integer NOT NULL,
    release_date date NOT NULL,
    duration integer NOT NULL,
    uuid uuid NOT NULL
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha.actions OWNER TO postgres;

ALTER TABLE IF EXISTS afisha.events_actions OWNER TO postgres;

ALTER TABLE IF EXISTS afisha.events OWNER TO postgres;

ALTER TABLE IF EXISTS afisha.concerts_descriptions OWNER TO postgres;

ALTER TABLE IF EXISTS afisha.films_descriptions OWNER TO postgres;

ALTER TABLE afisha.events
    ADD CONSTRAINT event_pkey PRIMARY KEY (uuid);

ALTER TABLE afisha.actions
    ADD CONSTRAINT actions_pkey PRIMARY KEY (uuid);

ALTER TABLE afisha.events_actions
    ADD CONSTRAINT events_actions_event_uuid_event_uuid FOREIGN KEY (event_uuid) REFERENCES afisha.events (uuid);

ALTER TABLE afisha.concerts_descriptions
    ADD CONSTRAINT categories_concert_uuid_key UNIQUE (uuid);

ALTER TABLE afisha.films_descriptions
    ADD CONSTRAINT films_descriptions_film_uuid_key UNIQUE (uuid);

ALTER TABLE afisha.events_actions
    ADD CONSTRAINT events_actions_actions_uuid_actions_uuid FOREIGN KEY (action_uuid) REFERENCES afisha.actions (uuid);

