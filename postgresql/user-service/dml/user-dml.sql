\connect users

INSERT INTO
	security.roles (role)
VALUES
	('ADMIN'),
	('USER');

INSERT INTO security.users(
	uuid,
	account_non_expired,
    account_non_locked,
    credentials_non_expired,
    enabled, username,
    password,
    nick,
    status,
    dt_create,
    dt_update
)
	VALUES
	    ('3f99c70b-386e-40b0-a718-1c720360d857',
	    true,
	    true,
	    true,
	    true,
	    'admin@admin.admin',
	    '$2a$10$7DjJGKOWShTdhHfBgPCjI.9OP/BScn88in1lStMQX5AdHhYE.crnG',
	    'admin',
	    'ACTIVATED',
	    current_timestamp,
	    current_timestamp
    );

INSERT INTO security.users_authorities(
	authorities_id, users_uuid)
	VALUES (
	    (SELECT
	        id
        FROM
            security.roles
        WHERE
            role = 'USER'
        ), '3f99c70b-386e-40b0-a718-1c720360d857');

INSERT INTO security.users_authorities(
	authorities_id, users_uuid)
	VALUES (
	    (SELECT
	        id
        FROM
            security.roles
        WHERE
            role = 'ADMIN'
        ), '3f99c70b-386e-40b0-a718-1c720360d857');