create table if not exists crm.seller (
    id                          serial primary key,
    name                        varchar not null,
    contact_info                varchar not null,
    registration_date           timestamp not null
);