CREATE DATABASE users;

GRANT ALL PRIVILEGES ON DATABASE postgres TO postgres;

\connect users;
CREATE SCHEMA IF NOT EXISTS
SECURITY AUTHORIZATION postgres;

CREATE TABLE IF NOT EXISTS security.roles (
    id bigint NOT NULL UNIQUE GENERATED BY DEFAULT AS IDENTITY (INCREMENT 1 START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1),
    ROLE text COLLATE pg_catalog."default" NOT NULL UNIQUE
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS security.users (
    uuid uuid NOT NULL UNIQUE,
    username text COLLATE pg_catalog."default" NOT NULL UNIQUE,
    PASSWORD text COLLATE pg_catalog."default" NOT NULL,
    nick text COLLATE pg_catalog."default" NOT NULL,
    status text COLLATE pg_catalog."default" NOT NULL,
    dt_create timestamp WITHOUT time zone NOT NULL,
    dt_update timestamp(3) WITHOUT time zone NOT NULL
)
TABLESPACE pg_default;

CREATE TABLE IF NOT EXISTS security.users_authorities (
    authorities_id bigint NOT NULL,
    user_uuid uuid NOT NULL
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS security.users OWNER TO postgres;

ALTER TABLE IF EXISTS security.users_authorities OWNER TO postgres;

ALTER TABLE IF EXISTS security.roles OWNER TO postgres;

ALTER TABLE security.roles
    ADD CONSTRAINT pk_authorities PRIMARY KEY (id);

ALTER TABLE security.users
    ADD CONSTRAINT pk_users PRIMARY KEY (uuid);

ALTER TABLE security.users_authorities
    ADD CONSTRAINT fk_useaut_on_authority FOREIGN KEY (authorities_id) REFERENCES security.roles (id);

ALTER TABLE security.users_authorities
    ADD CONSTRAINT fk_useaut_on_user FOREIGN KEY (user_uuid) REFERENCES security.users (uuid);