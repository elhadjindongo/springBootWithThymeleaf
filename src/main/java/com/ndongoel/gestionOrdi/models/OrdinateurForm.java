/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/27/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor @AllArgsConstructor
public class OrdinateurForm {

    private Long idOrdinateur;
    private String type;
    private String ram;
    private String processeur;
    private String marque;
    private String capaciteDisque;
}
