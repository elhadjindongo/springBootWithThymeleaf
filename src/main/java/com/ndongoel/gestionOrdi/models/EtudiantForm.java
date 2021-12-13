/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/27/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantForm {

    private Long idEtudiant;
    @NotNull
    @Size(min = 2, max = 200)
    private String prenom;
    @NotNull
    @Size(min = 2, max = 100)
    private String nom;
    @NotNull
    @Size(min = 5, max = 200)
    @Email
    private String email;
    @NotNull
    @Size(min = 9, max = 12)
    private String phone;
    private Long ordinateurId;
    private Long filliereId;
}
