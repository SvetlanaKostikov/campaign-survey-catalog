create table if not exists survey_catalog
(
    id            varchar(100) not null primary key unique,
    name          varchar(3000),
    status        varchar(100),
    owner_id      varchar(100),
    capabilities  varchar
    );