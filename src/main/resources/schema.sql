create sequence doctor_seq
    start with
        10101 increment by 1;

create sequence hibernate_sequence
    start with
        1 increment by 1;

create sequence patient_seq
    start with
        20101 increment by 1;

create table doctor (doctor_id integer not null, age integer, experience integer, first_name varchar(255), insurance varchar(255), last_name varchar(255), rating integer, primary key (doctor_id));

create table employee (employee_id integer not null, age integer, first_name varchar(255), last_name varchar(255), primary key (employee_id));

create table patient (patient_id integer not null, age integer, first_name varchar(255), insurance varchar(255), last_name varchar(255), visits integer, doctor_id integer not null, primary key (patient_id));

alter table patient add constraint fk_doctor_id foreign key (doctor_id) references doctor ;