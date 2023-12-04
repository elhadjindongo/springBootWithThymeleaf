/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 04/12/2023 ************************************/
/****** Project: gestionEmploye *********************/
/****************************************************/

package com.ndongoel.gestionEmpl.dao;

import com.ndongoel.gestionEmpl.entities.Employe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeDao extends JpaRepository<Employe,Long> {
}
