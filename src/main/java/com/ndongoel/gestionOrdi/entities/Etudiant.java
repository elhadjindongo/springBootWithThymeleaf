package com.ndongoel.gestionOrdi.entities;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/***********************************************************************
 * Module:  Etudiant.java
 * Author:  El Hadji M. NDONGO
 * Purpose: Defines the Class Etudiant
 ***********************************************************************/

@Data @NoArgsConstructor @AllArgsConstructor @ToString
@Entity
public class Etudiant {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_edutiants",length = 11)
   private Long idEtudiant;
	@Column(length = 250)
   private String prenom;
	@Column(length = 150)
   private String nom;
	@Column(length = 250)
   private String email;
	@Column(length = 250)
   private String phone;
   @OneToOne
   public Ordinateur ordinateur;
   @ManyToOne
   public Filliere filliere;

}