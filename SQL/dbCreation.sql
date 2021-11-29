/*CREATE DATABASE gestion_ordi_uvs;*/
/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     11/22/2021 9:08:40 PM                        */
/*==============================================================*/


drop table if exists etudiant;

drop table if exists filliere;

drop table if exists ordinateur;

/*==============================================================*/
/* Table: etudiant                                              */
/*==============================================================*/
create table etudiant
(
   idetudiant           int(11) not null,
   idfilliere           int(11),
   idordinateur         int(11),
   prenom               varchar(250),
   nom                  varchar(150),
   email                varchar(250),
   phone                varchar(250),
   primary key (idetudiant)
);

/*==============================================================*/
/* Table: filliere                                              */
/*==============================================================*/
create table filliere
(
   idfilliere           int(11) not null,
   nom                  varchar(250),
   primary key (idfilliere)
);

/*==============================================================*/
/* Table: ordinateur                                            */
/*==============================================================*/
create table ordinateur
(
   idordinateur         int(11) not null,
   type                 varchar(250),
   ram                  varchar(45),
   processeur           varchar(45),
   marque               varchar(45),
   capacitedisque       varchar(45),
   primary key (idordinateur)
);

alter table etudiant add constraint fk_association1 foreign key (idordinateur)
      references ordinateur (idordinateur) on delete restrict on update restrict;

alter table etudiant add constraint fk_association2 foreign key (idfilliere)
      references filliere (idfilliere) on delete restrict on update restrict;

