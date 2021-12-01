package com.ndongoel.gestionOrdi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    @Size(min = 2, max = 200)
    @NotNull
    private String prenom;
    @Column(length = 45, nullable = false)
    @NotNull
    @Size(min = 2, max = 45)
    private String nom;
    @Column(length = 200, nullable = false)
    @NotNull
    @Size(min = 2, max = 200)
    @Email
    private String email;
    @Column(length = 20, nullable = false)
    @NotNull
    @Size(min = 6, max = 20)
    private String phone;
    @OneToOne
    public Ordinateur ordinateur;
    @ManyToOne
    public Filliere filliere;

}