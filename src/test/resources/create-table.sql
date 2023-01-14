drop table if exists road_address CASCADE;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;
create table road_address (id bigint auto_increment, city varchar(255), county varchar(255), road varchar(255), primary key (id));