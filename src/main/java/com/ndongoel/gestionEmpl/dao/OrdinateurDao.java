/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionEmpl.dao;

import com.ndongoel.gestionEmpl.entities.Ordinateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdinateurDao extends JpaRepository<Ordinateur,Long> {
}
