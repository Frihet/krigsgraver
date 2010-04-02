alter table Cemetery drop constraint FKCE89E51279E16DEC
alter table Grave drop constraint FK41DD1E5B0ABD199
alter table Grave drop constraint FK41DD1E5AA7CB23A
alter table Person drop constraint FK8E48877570B4C7A4
alter table Person drop constraint FK8E488775ABCEE677
alter table Person drop constraint FK8E488775EF7942C
alter table Person drop constraint FK8E48877570F3F3DA
alter table Person drop constraint FK8E4887757DF6B27A
alter table Person drop constraint FK8E4887758A840FFA
alter table Person drop constraint FK8E4887759894923A
alter table Person drop constraint FK8E488775918DBD79
alter table Person_CauseOfDeath drop constraint FK485AEADE4FAFE35A
alter table Person_CauseOfDeath drop constraint FK485AEADE7808DA3B
alter table Person_Grave drop constraint FKF4FA909B7A4E4C31
alter table Person_Grave drop constraint FKF4FA909B4FAFE35A
drop table Camp
drop table CauseOfDeath
drop table Cemetery
drop table FlexibleDate
drop table Grave
drop table KgUser
drop table Nationality
drop table Person
drop table PersonDetails
drop table Person_CauseOfDeath
drop table Person_Grave
drop table PostalDistrict
drop table Rank
drop table Stalag
drop sequence hibernate_sequence
create table Camp (id int8 not null, description text, latitude float8, longitude float8, name varchar(255) not null unique, primary key (id))
create table CauseOfDeath (id int8 not null, causeGroup varchar(255), description varchar(255), name varchar(255) not null unique, primary key (id))
create table Cemetery (id int8 not null, address varchar(255), latitude float8, longitude float8, name varchar(255) not null unique, postalDistrict_postcode int4, primary key (id))
create table FlexibleDate (id int8 not null, approximate bool not null, day int4, month int4, year int4, primary key (id))
create table Grave (id int8 not null, graveField varchar(255), graveNumber varchar(255), graveRow varchar(255), latitude float8, longitude float8, massGrave bool not null, moved bool not null, reference text, cemetery_id int8, dateOfBurial_id int8, primary key (id))
create table KgUser (id int8 not null, credentialsNonExpired bool not null, enabled bool not null, name varchar(255), password varchar(255), role varchar(255), username varchar(255) unique, primary key (id))
create table Nationality (id int8 not null, name varchar(255) unique, primary key (id))
create table Person (id int8 not null, causeOfDeathDescription varchar(255), createdDate timestamp default current_timestamp not null, obdNumber int8, placeOfDeath varchar(255), prisonerNumber int4, remarks text, camp_id int8, cyrillicDetails_id int8, dateOfBirth_id int8, dateOfDeath_id int8, nationality_id int8, rank_id int8, stalag_id int8, westernDetails_id int8, primary key (id))
create table PersonDetails (id int8 not null, firstName varchar(255), lastName varchar(255), nameOfFather varchar(255), placeOfBirth varchar(255), primary key (id))
create table Person_CauseOfDeath (Person_id int8 not null, causesOfDeath_id int8 not null)
create table Person_Grave (Person_id int8 not null, graves_id int8 not null, primary key (Person_id, graves_id), unique (graves_id))
create table PostalDistrict (postcode int4 not null unique, countyId int4 not null, name varchar(255) not null, primary key (postcode))
create table Rank (id int8 not null, name varchar(255) unique, primary key (id))
create table Stalag (id int8 not null, description text, latitude float8, longitude float8, name varchar(255) not null unique, primary key (id))
alter table Cemetery add constraint FKCE89E51279E16DEC foreign key (postalDistrict_postcode) references PostalDistrict
alter table Grave add constraint FK41DD1E5B0ABD199 foreign key (dateOfBurial_id) references FlexibleDate
alter table Grave add constraint FK41DD1E5AA7CB23A foreign key (cemetery_id) references Cemetery
alter table Person add constraint FK8E48877570B4C7A4 foreign key (dateOfDeath_id) references FlexibleDate
alter table Person add constraint FK8E488775ABCEE677 foreign key (westernDetails_id) references PersonDetails
alter table Person add constraint FK8E488775EF7942C foreign key (cyrillicDetails_id) references PersonDetails
alter table Person add constraint FK8E48877570F3F3DA foreign key (camp_id) references Camp
alter table Person add constraint FK8E4887757DF6B27A foreign key (nationality_id) references Nationality
alter table Person add constraint FK8E4887758A840FFA foreign key (rank_id) references Rank
alter table Person add constraint FK8E4887759894923A foreign key (stalag_id) references Stalag
alter table Person add constraint FK8E488775918DBD79 foreign key (dateOfBirth_id) references FlexibleDate
alter table Person_CauseOfDeath add constraint FK485AEADE4FAFE35A foreign key (Person_id) references Person
alter table Person_CauseOfDeath add constraint FK485AEADE7808DA3B foreign key (causesOfDeath_id) references CauseOfDeath
alter table Person_Grave add constraint FKF4FA909B7A4E4C31 foreign key (graves_id) references Grave
alter table Person_Grave add constraint FKF4FA909B4FAFE35A foreign key (Person_id) references Person
create sequence hibernate_sequence
