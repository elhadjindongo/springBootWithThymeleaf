/***********************************************************************
 * Module:  Ordinateur.java
 * Author:  El Hadji M. NDONGO
 * Purpose: Defines the Class Ordinateur
 ***********************************************************************/
package com.ndongoel.gestionOrdi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


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
    private String type;
    @Column(length = 5,name = "ram_en_go", nullable = false)
    @NotNull
    private int ramEnGo;
    @Column(length = 45, nullable = false)
    @NotNull
    @Size(min = 2, max = 45)
    private String processeur;
    @NotNull
    @Size(min = 2, max = 45)
    @Column(length = 45, nullable = false)
    private String marque;
    @NotNull
    @Column(name = "capacite_disque_en_go", nullable = false, length = 45)
    private int capaciteDisqueEnGo;

}