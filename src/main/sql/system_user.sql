create sequence seq_sysuser;
create table system_user (id bigint not null,
enabled boolean default false,
registration_date timestamp default now(),
user_login text,
user_password text
);

alter table system_user add constraint PK_SYSUSER primary key (id);