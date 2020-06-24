--liquibase formatted sql
--changeset alexr:inserts splitStatements:true endDelimiter:;
insert into zz(id) values (1);
insert into zz(id) values (2);
insert into zz(id) values (3);
