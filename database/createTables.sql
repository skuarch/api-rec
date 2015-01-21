create table if not exists address (
    address_id bigint not null auto_increment,
    address_all varchar(255) not null,
    address_city varchar(255),
    address_country varchar(255) not null,
    address_registration_date TIMESTAMP default CURRENT_TIMESTAMP not null,
    address_is_soft_delete int default 0,
    address_state varchar(255) not null,
    address_zip_code integer,
    primary key (address_id)
);

create table if not exists affiliate (
    affiliate_id bigint not null auto_increment,
    affiliate_is_soft_delete int default 0,
    affiliate_registration_date TIMESTAMP default CURRENT_TIMESTAMP not null,
    address_id bigint not null,
    person_id bigint not null,
    primary key (affiliate_id)
);

create table if not exists category (
    category_id bigint not null auto_increment,
    category_is_soft_deleted int default 0,
    category_name varchar(255) not null,
    primary key (category_id)
);


create table if not exists configuration (
    configuration_id bigint not null auto_increment,
    configuration_is_soft_delete int default 0,
    configuration_smtp_host varchar(255) not null,
    configuration_smtp_password varchar(255) not null,
    configuration_smtp_port integer not null,
    configuration_smtp_username varchar(255) not null,
    primary key (configuration_id)
);

create table if not exists establishment (
    establishment_id bigint not null auto_increment,
    establishment_is_soft_deleted int default 0 not null,
    establishment_name varchar(255) not null,
    establishment_registration_date TIMESTAMP default CURRENT_TIMESTAMP not null,
    address_id bigint,
    affiliate_id bigint,
    primary key (establishment_id)
);

create table if not exists freelancer (
    freelancer_id bigint not null auto_increment,
    freelancer_is_soft_delete int default 0,
    freelance_treasury_id varchar(255),
    address_id bigint not null,
    person_id bigint not null,
    primary key (freelancer_id)
);

create table if not exists gender (
    gender_id bigint not null auto_increment,
    gender_name varchar(255) not null,
    primary key (gender_id)
);

create table if not exists mail_template (
    mail_template_id bigint not null auto_increment check (mail_template_id >= 1),
    mail_template_display_language varchar(255) not null,
    mail_template_from varchar(255) not null,
    mail_template_is_soft_delete int default 0 not null,
    mail_template_message varchar(255) not null,
    mail_template_name varchar(255) not null,
    mail_template_subject varchar(255) not null,
    primary key (mail_template_id)
);


create table if not exists person (
    person_id bigint not null auto_increment,
    person_email varchar(128) not null,
    person_is_soft_deleted int default 0 not null,
    person_name varchar(128) not null,
    person_password varchar(32) not null,
    person_phone integer not null,
    person_registration_date TIMESTAMP default CURRENT_TIMESTAMP not null,
    gender_id bigint not null,
    person_type_id bigint not null,
    primary key (person_id)
);


create table if not exists request (
    request_id bigint not null auto_increment,
    request_parameter_names varchar(255),
    request_accept varchar(255),
    request_accept_encoding varchar(255),
    request_attribute_names varchar(255),
    request_auth_type varchar(255),
    request_charactect_encoding varchar(255),
    request_connection varchar(255),
    request_content_length integer,
    request_content_type varchar(255),
    request_header_names varchar(255),
    request_local_address varchar(255),
    request_local_port integer,
    request_locale varchar(255),
    request_method varchar(255),
    request_origin varchar(255),
    request_path_info varchar(255),
    request_protocol varchar(255),
    request_remote_host varchar(255),
    request_remote_port integer,
    request_remote_user varchar(255),
    request_scheme varchar(255),
    request_servlet_path varchar(255),
    request_uri varchar(255),
    request_url varchar(255),
    request_user_agent varchar(255),
    primary key (request_id)
);


create table if not exists person_type (
    person_type_id bigint not null auto_increment,
    person_type_is_soft_delete int default 0,
    person_type_name varchar(255) not null,
    primary key (person_type_id)
);


create table if not exists transaction (
    transaction_id bigint not null auto_increment,
    transaction_is_soft_deleted int default 0 not null,
    transaction_object_id bigint not null,
    transaction_registration_date TIMESTAMP default CURRENT_TIMESTAMP not null,
    transaction_type_id bigint,
    primary key (transaction_id)
);


create table if not exists transaction_type (
    transaction_type_id bigint not null auto_increment,
    transaction_type_is_soft_deleted int default 0 not null,
    transaction_type_name varchar(255) not null,
    primary key (transaction_type_id)
);


alter table affiliate add constraint FK_e9kw7qq9s07yhbdto2al5o1ov foreign key (address_id) references address (address_id);
alter table affiliate add constraint FK_d80kivo3i0ylh2ydpt9938kw1 foreign key (person_id) references person (person_id);
alter table establishment add constraint FK_37m8vultjjon8fm3px5d7ad7l foreign key (address_id) references address (address_id);
alter table establishment add constraint FK_mehc7e037c3paump7n8f4h87v foreign key (affiliate_id) references affiliate (affiliate_id);
alter table freelancer add constraint FK_jdgju3rgb29lsjnr7obrjlinr foreign key (address_id) references address (address_id);
alter table freelancer add constraint FK_dwwl0ebtyowjj63xir0n2dpt5 foreign key (person_id) references person (person_id);
alter table person add constraint FK_7bqgqlf1ar5j6osfe7lmu1gse foreign key (gender_id) references gender (gender_id);
alter table person add constraint FK_6uq8ww4ie2npi5np5jwi8quwn foreign key (person_type_id) references person_type (person_type_id);
alter table transaction add constraint FK_lmd9i49v95aja312vekp5afvq foreign key (transaction_type_id) references transaction_type (transaction_type_id);
