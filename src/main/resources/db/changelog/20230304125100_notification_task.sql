-- liquibase formatted sql

-- changeset klikli:1
CREATE TABLE notification_task(
    id BIGSERIAL PRIMARY KEY ,
    chat_id BIGINT NOT NULL ,
    message TEXT NOT NULL ,
    time TIMESTAMP NOT NULL
)