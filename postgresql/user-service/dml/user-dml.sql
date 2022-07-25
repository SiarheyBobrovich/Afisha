\connect users
INSERT INTO security.roles (
    ROLE)
VALUES (
    'ADMIN'),
(
    'USER');

INSERT INTO security.users (
    uuid,
    account_non_expired,
    account_non_locked,
    credentials_non_expired,
    enabled,
    username,
    PASSWORD,
    nick,
    status,
    dt_create,
    dt_update)
VALUES (
    '3f99c70b-386e-40b0-a718-1c720360d857',
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    'admin@admin.admin',
    '$2a$10$7DjJGKOWShTdhHfBgPCjI.9OP/BScn88in1lStMQX5AdHhYE.crnG',
    'admin',
    'ACTIVATED',
    CURRENT_TIMESTAMP,
    CURRENT_TIMESTAMP);

INSERT INTO security.users_authorities (
    authorities_id,
    user_uuid)
VALUES ( (
        SELECT
            id
        FROM
            security.roles
        WHERE
            ROLE = 'USER'), '3f99c70b-386e-40b0-a718-1c720360d857');

INSERT INTO security.users_authorities (
    authorities_id,
    user_uuid)
VALUES ( (
        SELECT
            id
        FROM
            security.roles
        WHERE
            ROLE = 'ADMIN'), '3f99c70b-386e-40b0-a718-1c720360d857');

