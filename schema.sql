alter table Cemetery drop foreign key FKCE89E51279E16DEC
alter table Grave drop foreign key FK41DD1E5B0ABD199
alter table Grave drop foreign key FK41DD1E5AA7CB23A
alter table Person drop foreign key FK8E48877570B4C7A4
alter table Person drop foreign key FK8E488775ABCEE677
alter table Person drop foreign key FK8E488775EF7942C
alter table Person drop foreign key FK8E48877570F3F3DA
alter table Person drop foreign key FK8E4887757DF6B27A
alter table Person drop foreign key FK8E4887758A840FFA
alter table Person drop foreign key FK8E4887759894923A
alter table Person drop foreign key FK8E488775918DBD79
alter table Person_CauseOfDeath drop foreign key FK485AEADE4FAFE35A
alter table Person_CauseOfDeath drop foreign key FK485AEADE7808DA3B
alter table Person_Grave drop foreign key FKF4FA909B7A4E4C31
alter table Person_Grave drop foreign key FKF4FA909B4FAFE35A
drop table if exists Camp
drop table if exists CauseOfDeath
drop table if exists Cemetery
drop table if exists FlexibleDate
drop table if exists Grave
drop table if exists InfoPage
drop table if exists KgUser
drop table if exists Nationality
drop table if exists Person
drop table if exists PersonDetails
drop table if exists Person_CauseOfDeath
drop table if exists Person_Grave
drop table if exists PostalDistrict
drop table if exists Rank
drop table if exists Stalag
create table Camp (id bigint not null auto_increment, description longtext, latitude double precision, longitude double precision, name varchar(255) not null unique, primary key (id))
create table CauseOfDeath (id bigint not null auto_increment, causeGroup varchar(255), description varchar(255), name varchar(255) not null unique, primary key (id))
create table Cemetery (id bigint not null auto_increment, address varchar(255), latitude double precision, longitude double precision, name varchar(255) not null unique, postalDistrict_postcode integer, primary key (id))
create table FlexibleDate (id bigint not null auto_increment, approximate bit not null, day integer, month integer, year integer, primary key (id))
create table Grave (id bigint not null auto_increment, graveField varchar(255), graveNumber varchar(255), graveRow varchar(255), latitude double precision, longitude double precision, massGrave bit not null, moved bit not null, reference longtext, cemetery_id bigint, dateOfBurial_id bigint, primary key (id))
create table InfoPage (id bigint not null auto_increment, html longtext, locale varchar(255), pageName varchar(255) not null unique, primary key (id))
create table KgUser (id bigint not null auto_increment, credentialsNonExpired bit not null, enabled bit not null, name varchar(255), password varchar(255), role varchar(255), username varchar(255) unique, primary key (id))
create table Nationality (id bigint not null auto_increment, name varchar(255) unique, primary key (id))
create table Person (id bigint not null auto_increment, causeOfDeathDescription varchar(255), createdDate timestamp default current_timestamp not null, obdNumber bigint, placeOfDeath varchar(255), prisonerNumber integer, remarks longtext, camp_id bigint, cyrillicDetails_id bigint, dateOfBirth_id bigint, dateOfDeath_id bigint, nationality_id bigint, rank_id bigint, stalag_id bigint, westernDetails_id bigint, primary key (id))
create table PersonDetails (id bigint not null auto_increment, firstName varchar(255), lastName varchar(255), nameOfFather varchar(255), placeOfBirth varchar(255), primary key (id))
create table Person_CauseOfDeath (Person_id bigint not null, causesOfDeath_id bigint not null, primary key (Person_id, causesOfDeath_id))
create table Person_Grave (Person_id bigint not null, graves_id bigint not null, primary key (Person_id, graves_id), unique (graves_id))
create table PostalDistrict (postcode integer not null unique, countyId integer not null, name varchar(255) not null, primary key (postcode))
create table Rank (id bigint not null auto_increment, name varchar(255) unique, primary key (id))
create table Stalag (id bigint not null auto_increment, description longtext, latitude double precision, longitude double precision, name varchar(255) not null unique, primary key (id))
alter table Cemetery add index FKCE89E51279E16DEC (postalDistrict_postcode), add constraint FKCE89E51279E16DEC foreign key (postalDistrict_postcode) references PostalDistrict (postcode)
alter table Grave add index FK41DD1E5B0ABD199 (dateOfBurial_id), add constraint FK41DD1E5B0ABD199 foreign key (dateOfBurial_id) references FlexibleDate (id)
alter table Grave add index FK41DD1E5AA7CB23A (cemetery_id), add constraint FK41DD1E5AA7CB23A foreign key (cemetery_id) references Cemetery (id)
alter table Person add index FK8E48877570B4C7A4 (dateOfDeath_id), add constraint FK8E48877570B4C7A4 foreign key (dateOfDeath_id) references FlexibleDate (id)
alter table Person add index FK8E488775ABCEE677 (westernDetails_id), add constraint FK8E488775ABCEE677 foreign key (westernDetails_id) references PersonDetails (id)
alter table Person add index FK8E488775EF7942C (cyrillicDetails_id), add constraint FK8E488775EF7942C foreign key (cyrillicDetails_id) references PersonDetails (id)
alter table Person add index FK8E48877570F3F3DA (camp_id), add constraint FK8E48877570F3F3DA foreign key (camp_id) references Camp (id)
alter table Person add index FK8E4887757DF6B27A (nationality_id), add constraint FK8E4887757DF6B27A foreign key (nationality_id) references Nationality (id)
alter table Person add index FK8E4887758A840FFA (rank_id), add constraint FK8E4887758A840FFA foreign key (rank_id) references Rank (id)
alter table Person add index FK8E4887759894923A (stalag_id), add constraint FK8E4887759894923A foreign key (stalag_id) references Stalag (id)
alter table Person add index FK8E488775918DBD79 (dateOfBirth_id), add constraint FK8E488775918DBD79 foreign key (dateOfBirth_id) references FlexibleDate (id)
alter table Person_CauseOfDeath add index FK485AEADE4FAFE35A (Person_id), add constraint FK485AEADE4FAFE35A foreign key (Person_id) references Person (id)
alter table Person_CauseOfDeath add index FK485AEADE7808DA3B (causesOfDeath_id), add constraint FK485AEADE7808DA3B foreign key (causesOfDeath_id) references CauseOfDeath (id)
alter table Person_Grave add index FKF4FA909B7A4E4C31 (graves_id), add constraint FKF4FA909B7A4E4C31 foreign key (graves_id) references Grave (id)
alter table Person_Grave add index FKF4FA909B4FAFE35A (Person_id), add constraint FKF4FA909B4FAFE35A foreign key (Person_id) references Person (id)
