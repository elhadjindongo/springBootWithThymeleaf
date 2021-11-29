/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/27/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.models;

import lombok.Data;

@Data
public class OrdinateurForm {

    private String type;
    private String ram;
    private String processeur;
    private String marque;
    private String capaciteDisque;
}
