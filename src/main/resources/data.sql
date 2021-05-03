DROP TABLE if exists NOTES;
DROP TABLE if exists USERS;

create table USERS
(
    ID        INT auto_increment,
    USER_NAME VARCHAR(50),
    constraint USERS_PK
        primary key (ID)
);

create unique index USERS_ID_UINDEX
    on USERS (ID);

DROP TABLE if exists NOTES;

create table NOTES
(
    ID           INT auto_increment,
    OWNER_ID     INT not null,
    PHONE_NUMBER VARCHAR(50),
    constraint NOTES_USERS_ID_FK
        foreign key (OWNER_ID) references USERS (ID)
);

create unique index NOTES_ID_UINDEX
    on NOTES (ID);

alter table NOTES
    add constraint NOTES_PK
        primary key (ID);

insert into USERS(USER_NAME)
values ('Ivan Petrov'),
       ('Alexander Savin'),
       ('Sergey Polin'),
       ('Anton Kelskiy'),
       ('Igor Serin');

insert into NOTES(OWNER_ID, PHONE_NUMBER)
values (1, '56465-45361'),
       (1, '32465-8942'),
       (1, '5643-9974'),
       (2, '364-79565'),
       (2, '4632-8943'),
       (3, '324889-43'),
       (3, '6354-54-64'),
       (3, '6556-6542'),
       (3, '65456-5642'),
       (4, '6763-63189'),
       (4, '944533-56423'),
       (4, '889423-6412');
