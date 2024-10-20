create table if not exists crm.transaction (
    id                          serial primary key,
    seller_id                   int not null references crm.seller (id),
    amount                      float not null,
    payment_type                varchar not null,
    transaction_date            timestamp not null
);