CREATE SCHEMA IF NOT EXISTS afisha
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS afisha.actions
(
    uuid uuid NOT NULL UNIQUE,
    title text COLLATE pg_catalog."default" NOT NULL,
    description text COLLATE pg_catalog."default" NOT NULL,
    type character varying(8) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT actions_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.events
(
    uuid uuid NOT NULL UNIQUE,
    dt_event timestamp without time zone NOT NULL,
    dt_end_of_sale timestamp without time zone NOT NULL,
    status character varying(9) COLLATE pg_catalog."default" NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp(3) without time zone NOT NULL,
    CONSTRAINT event_pkey PRIMARY KEY (uuid)
)

TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS afisha.events_actions
(
    event_uuid uuid NOT NULL,
    action_uuid uuid NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha.actions
    OWNER to postgres;
ALTER TABLE IF EXISTS afisha.events_actions
    OWNER to postgres;
ALTER TABLE IF EXISTS afisha.events
	OWNER to postgres;

ALTER TABLE
	afisha.events_actions
	ADD CONSTRAINT
		events_actions_event_uuid_event_uuid
		FOREIGN KEY
			(event_uuid)
		REFERENCES
			afisha.events(uuid);
ALTER TABLE
	afisha.events_actions
	ADD CONSTRAINT
		events_actions_actions_uuid_actions_uuid
		FOREIGN KEY
			(action_uuid)
		REFERENCES
			afisha.actions(uuid);