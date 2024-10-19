create table if not exists crm.transaction (
    id                          serial primary key,
    seller_id                   int not null references crm.seller (id),
    amount                      float not null,
    payment_type                varchar not null,
    transaction_date            timestamp not null
);

insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (1, 10, 30095.63, 'CASH', '2023-12-19');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (2, 9, 60619.406, 'CASH', '2023-11-18');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (3, 4, 39980.142, 'CASH', '2023-12-25');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (4, 3, 52486.088, 'CASH', '2024-06-30');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (5, 1, 65341.018, 'CASH', '2024-05-21');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (6, 2, 17888.079, 'CASH', '2024-03-18');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (7, 1, 64421.221, 'CASH', '2024-01-22');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (8, 1, 1842.631, 'CASH', '2024-03-14');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (9, 2, 89071.844, 'CASH', '2024-09-03');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (10, 3, 26195.918, 'CASH', '2024-06-05');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (11, 4, 19991.863, 'CASH', '2024-06-05');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (12, 5, 37677.819, 'CASH', '2024-01-23');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (13, 6, 92359.745, 'CASH', '2023-12-21');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (14, 7, 70002.407, 'CASH', '2024-06-20');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (15, 6, 98526.971, 'CASH', '2024-09-25');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (16, 5, 93753.674, 'CASH', '2024-10-18');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (17, 4, 14263.351, 'CASH', '2023-12-31');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (18, 8, 4570.136, 'CASH', '2024-07-13');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (19, 9, 32513.584, 'CASH', '2024-05-20');
insert into crm.transaction (id, seller_id, amount, payment_type, transaction_date) values (20, 10, 3879.705, 'CASH', '2024-10-02');

