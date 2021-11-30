package com.ndongoel.gestionOrdi.entities;

/***********************************************************************
 * Module:  Filliere.java
 * Author:  El Hadji M. NDONGO
 * Purpose: Defines the Class Filliere
 ***********************************************************************/

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;

@Data @AllArgsConstructor @NoArgsConstructor @ToString
@Entity
public class Filliere {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id_filliere" ,length = 11)
   private Long idFilliere;
	@Column(name ="nom_filliere",length = 250)
   private String nomFilliere;

}