package com.ndongoel.gestionEmpl.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/***********************************************************************
 * Module:  Etudiant.java
 * Author:  El Hadji M. NDONGO
 * Purpose: Defines the Class Etudiant
 ***********************************************************************/

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_edutiants", length = 11)
    private Long idEtudiant;
    @Column(length = 200, nullable = false)
    private String prenom;
    @Column(length = 100, nullable = false)
    private String nom;
    @Column(length = 200, nullable = false)
    private String email;
    @Column(length = 12, nullable = false)
    private String phone;
    @OneToOne
    public Ordinateur ordinateur;
    @ManyToOne
    public Filliere filliere;

}