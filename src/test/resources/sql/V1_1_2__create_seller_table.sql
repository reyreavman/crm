create table if not exists crm.seller (
    id                          serial primary key,
    name                        varchar not null,
    contact_info                varchar not null,
    registration_date           timestamp not null
);
--
insert into crm.seller (id, name, contact_info, registration_date) values (1, 'Tessa Porcher', '+86 387 685 9952', '2024-07-08');
insert into crm.seller (id, name, contact_info, registration_date) values (2, 'Athena Ludlow', '+7 120 534 9963', '2024-06-02');
insert into crm.seller (id, name, contact_info, registration_date) values (3, 'Bo Marrow', '+7 224 997 3458', '2024-06-08');
insert into crm.seller (id, name, contact_info, registration_date) values (4, 'Drud Kersting', '+81 617 892 4377', '2023-12-16');
insert into crm.seller (id, name, contact_info, registration_date) values (5, 'Belva Ossenna', '+30 974 494 2736', '2022-10-05');
insert into crm.seller (id, name, contact_info, registration_date) values (6, 'Vale Beals', '+358 853 247 8176', '2023-12-23');
insert into crm.seller (id, name, contact_info, registration_date) values (7, 'Lonna Campes', '+380 600 733 0551', '2024-08-14');
insert into crm.seller (id, name, contact_info, registration_date) values (8, 'Carol Casbon', '+66 776 437 5750', '2023-07-02');
insert into crm.seller (id, name, contact_info, registration_date) values (9, 'Walden Confort', '+385 704 939 9075', '2024-06-14');
insert into crm.seller (id, name, contact_info, registration_date) values (10, 'Linnie Winterscale', '+503 605 502 1032', '2023-05-18');
