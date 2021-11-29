package com.ndongoel.gestionOrdi.entities;

/***********************************************************************
 * Module:  Filliere.java
 * Author:  Fatou Seck
 * Purpose: Defines the Class Filliere
 ***********************************************************************/

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Filliere {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_filliere" ,length = 11)
   private Long idFilliere;
	@Column(name ="nom_filliere",length = 250)
   private String nomFilliere;

}