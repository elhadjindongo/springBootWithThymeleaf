/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.dao;

import com.ndongoel.gestionOrdi.entities.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinateurDao extends JpaRepository<Ordinateur,Long> {
}
