CREATE SCHEMA IF NOT EXISTS afisha
    AUTHORIZATION postgres;

CREATE EXTENSION IF NOT EXISTS "uuid-ossp"
SCHEMA afisha
VERSION "1.1";

CREATE TABLE IF NOT EXISTS afisha.films
(
    uuid uuid NOT NULL DEFAULT afisha.uuid_generate_v1(),
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT films_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.concerts
(
    uuid uuid NOT NULL DEFAULT afisha.uuid_generate_v1(),
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT concerts_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.events
(
    uuid uuid NOT NULL DEFAULT afisha.uuid_generate_v1(),
    dt_event timestamp without time zone NOT NULL,
    dt_end_of_sale timestamp without time zone NOT NULL,
    status character varying(9) COLLATE pg_catalog."default" NOT NULL,
    currency character varying COLLATE pg_catalog."default" NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    CONSTRAINT event_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.events_films
(
    event_uuid uuid NOT NULL,
    film_uuid uuid NOT NULL
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.events_concerts
(
    event_uuid uuid NOT NULL,
    concert_uuid uuid NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha.films
    OWNER to postgres;
ALTER TABLE IF EXISTS afisha.concerts
    OWNER to postgres;
ALTER TABLE IF EXISTS afisha.events_films
    OWNER to postgres;
ALTER TABLE IF EXISTS afisha.events_concerts
    OWNER to postgres;
ALTER TABLE IF EXISTS afisha.events
	OWNER to postgres;

ALTER TABLE
	afisha.events_films
	ADD CONSTRAINT
		events_films_event_uuid_Event_uuid
		FOREIGN KEY
			(event_uuid)
		REFERENCES
			afisha.Events(uuid);
ALTER TABLE
	afisha.events_films
	ADD CONSTRAINT
		events_films_film_uuid_Films_uuid
		FOREIGN KEY
			(film_uuid)
		REFERENCES
			afisha.Films(uuid);
ALTER TABLE
	afisha.events_concerts
	ADD CONSTRAINT
		events_concerts_event_uuid_Event_uuid
		FOREIGN KEY
			(event_uuid)
		REFERENCES
			afisha.Events(uuid);
ALTER TABLE
	afisha.events_concerts
	ADD CONSTRAINT
		events_concerts_concert_uuid_Concerts_uuid
		FOREIGN KEY
			(concert_uuid)
		REFERENCES
			afisha.Concerts(uuid);
