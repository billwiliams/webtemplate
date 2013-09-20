# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table template_user (
  id                        bigint not null,
  facebook_first            varchar(255),
  facebook_last             varchar(255),
  facebook_id               varchar(255),
  facebook_pic_url          varchar(255),
  facebook_access           varchar(255),
  facebook_email            varchar(255),
  facebook_auth             varchar(255),
  linkedin_first            varchar(255),
  linkedin_last             varchar(255),
  linkedin_pic_url          varchar(255),
  linkedin_auth             varchar(255),
  linkedin_email            varchar(255),
  linkedin_access           varchar(255),
  linkedin_id               varchar(255),
  linkedin_secret           varchar(255),
  constraint pk_template_user primary key (id))
;

create sequence template_user_seq;




# --- !Downs

drop table if exists template_user cascade;

drop sequence if exists template_user_seq;

