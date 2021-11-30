/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/27/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class EtudiantForm {
    private Long idEtudiant;
    private String prenom;
    private String nom;
    private String email;
    private String phone;
    private Long ordinateurId;
    private Long filliereId;
}
