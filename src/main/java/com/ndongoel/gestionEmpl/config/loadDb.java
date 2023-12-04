/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 04/12/2023 ************************************/
/****** Project: gestionEmploye *********************/
/****************************************************/

package com.ndongoel.gestionEmpl.config;

import com.ndongoel.gestionEmpl.dao.EmployeDao;
import com.ndongoel.gestionEmpl.entities.Employe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class loadDb {
    private static final Logger log = LoggerFactory.getLogger(loadDb.class);

    @Bean
    CommandLineRunner initDatabase(EmployeDao employeDao) {
        log.info("----------- Preloading Data on DB -----------");
        return args -> {

                //Etudiant
                Employe etudiant1 = new Employe(null, "Fatou", "Seck " , "fatou.seck@ouz-tech.sn", "778974532");
                Employe etudiant2 = new Employe(null, "Ousmane", "Diop ", "ousmane.diop@ouz-tech.sn", "787653423");
                Employe etudiant3 = new Employe(null, "Dieynaba", "Cisse ", "dieynaba.cisse@ouz-tech.sn", "767890097");
                Employe etudiant4 = new Employe(null, "Awa", "Ndiaye ", "awa.ndiaye@ouz-tech.sn", "767890098");
                Employe etudiant5 = new Employe(null, "Abdoulaye", "Fall ", "abdoulaye.fall@ouz-tech.sn", "767890099");
                log.info("Preloading -----------" + employeDao.save(etudiant1));
                log.info("Preloading -----------" + employeDao.save(etudiant2));
                log.info("Preloading -----------" + employeDao.save(etudiant3));
                log.info("Preloading -----------" + employeDao.save(etudiant4));
                log.info("Preloading -----------" + employeDao.save(etudiant5));
        };
    }
}
