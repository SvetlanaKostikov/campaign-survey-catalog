create table if not exists survey_passport_event
(
    event_id                varchar(100) not null primary key unique,
    event_author            varchar(100),
    event_entity_id         varchar(100),
    event_occurring_context varchar(100),
    event_occurring_time    timestamp,
    event_payload           jsonb,
    event_type              varchar(100),
    event_version           varchar(100)
);