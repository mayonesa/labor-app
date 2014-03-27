alter table user_assigned_labors drop constraint FK901B3D60F7634DFA;
alter table user_assigned_labors drop constraint FK901B3D604F3EA015;
alter table user_wish_list drop constraint FK5EE4EDC2F7634DFA;
alter table user_wish_list drop constraint FK5EE4EDC24F3EA015;
drop table abstract_labor if exists;
drop table current_labor_term if exists;
drop table labor_term_role_hours if exists;
drop table user if exists;
drop table user_assigned_labors if exists;
drop table user_wish_list if exists;
create table abstract_labor (id bigint generated by default as identity (start with 1), version bigint not null, duration_in_hours integer not null, labor_term varchar(255) not null, laborer_capacity integer not null, name varchar(255) not null, class varchar(255) not null, day varchar(255), start_hour integer, primary key (id));
create table current_labor_term (id bigint generated by default as identity (start with 1), version bigint not null, labor_term varchar(255) not null, primary key (id));
create table labor_term_role_hours (id bigint generated by default as identity (start with 1), version bigint not null, hours integer not null, labor_term varchar(255) not null, role varchar(255) not null, primary key (id));
create table user (id bigint generated by default as identity (start with 1), version bigint not null, comment varchar(255), first_name varchar(255) not null, last_name varchar(255) not null, original_wish_list_and_schedule_conflict bit not null, password varchar(255) not null, role varchar(255) not null, room_number varchar(255) not null, username varchar(255) not null, primary key (id), unique (username));
create table user_assigned_labors (user_id bigint not null, abstract_labor_id bigint not null, primary key (user_id, abstract_labor_id));
create table user_wish_list (user_id bigint not null, abstract_labor_id bigint not null, primary key (user_id, abstract_labor_id));
alter table user_assigned_labors add constraint FK901B3D60F7634DFA foreign key (user_id) references user;
alter table user_assigned_labors add constraint FK901B3D604F3EA015 foreign key (abstract_labor_id) references abstract_labor;
alter table user_wish_list add constraint FK5EE4EDC2F7634DFA foreign key (user_id) references user;
alter table user_wish_list add constraint FK5EE4EDC24F3EA015 foreign key (abstract_labor_id) references abstract_labor;
