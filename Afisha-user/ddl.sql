CREATE SCHEMA IF NOT EXISTS afisha_user
    AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS afisha_user.users
(
    uuid uuid NOT NULL,
    nick text COLLATE pg_catalog."default" NOT NULL,
    mail text COLLATE pg_catalog."default" NOT NULL,
    role character varying(5) COLLATE pg_catalog."default" NOT NULL,
    status character varying(18) COLLATE pg_catalog."default" NOT NULL,
    dt_create timestamp without time zone NOT NULL,
    dt_update timestamp without time zone NOT NULL
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS afisha_user.users
    OWNER to postgres;

ALTER TABLE
    afisha_user.users
    ADD CONSTRAINT
        users_pkey
        PRIMARY KEY
            (uuid);