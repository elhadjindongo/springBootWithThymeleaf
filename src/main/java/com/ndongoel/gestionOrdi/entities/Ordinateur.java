package com.ndongoel.gestionOrdi.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

/***********************************************************************
 * Module:  Ordinateur.java
 * Author:  El Hadji M. NDONGO
 * Purpose: Defines the Class Ordinateur
 ***********************************************************************/



@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Ordinateur {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordinateur",length = 11)
   private Long idOrdinateur;
   @Column(length = 250)
   private String type;
   @Column(length = 45)
   private String ram;
   @Column(length = 45)
   private String processeur;
   @Column(length = 45)
   private String marque;
   @Column(name = "capacite_disque",length = 45)
   private String capaciteDisque;

}