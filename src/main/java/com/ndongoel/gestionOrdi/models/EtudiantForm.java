/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/27/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.models;

import lombok.Data;

@Data
public class EtudiantForm {
    private String prenom;
    private String nom;
    private String email;
    private String phone;
    private Long ordinateurId;
    private Long filliereId;
}
