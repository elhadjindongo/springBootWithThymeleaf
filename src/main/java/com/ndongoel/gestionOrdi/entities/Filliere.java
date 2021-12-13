/***********************************************************************
 * Module:  Filliere.java
 * Author:  El Hadji M. NDONGO
 * Purpose: Defines the Class Filliere
 ***********************************************************************/
package com.ndongoel.gestionOrdi.entities;


import com.ndongoel.gestionOrdi.utils.FilliereConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Filliere implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filliere", length = 11)
    private Long idFilliere;
    @FilliereConstraint
    @Size(min = 2, max = 150)
    @Column(name = "nom_filliere", nullable = false, length = 150)
    private String nomFilliere;

}