-- liquibase formatted sql

-- changeset klikli:1
CREATE TABLE notification_task(
    user_id BIGINT,
    chat_id BIGINT,
    message TEXT,
    time_message TIMESTAMP
)