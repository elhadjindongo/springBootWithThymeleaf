package com.ndongoel.gestionOrdi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/***********************************************************************
 * Module:  Ordinateur.java
 * Author:  El Hadji M. NDONGO
 * Purpose: Defines the Class Ordinateur
 ***********************************************************************/


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Ordinateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ordinateur", length = 11)
    private Long idOrdinateur;
    @Column(length = 45, nullable = false)
    @NotNull
    @Size(min = 2, max = 45)
    private String type;
    @Column(length = 45, nullable = false)
    @NotNull
    @Size(min = 2, max = 45)
    private String ram;
    @Column(length = 45, nullable = false)
    @NotNull
    @Size(min = 2, max = 45)
    private String processeur;
    @NotNull
    @Size(min = 2, max = 45)
    @Column(length = 45, nullable = false)
    private String marque;
    @NotNull
    @Size(min = 2, max = 45)
    @Column(name = "capacite_disque", nullable = false, length = 45)
    private String capaciteDisque;

}