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
                Employe employe1 = new Employe(null, "Fatou", "Seck " , "fatou.seck@ouz-tech.sn", "778974532");
                Employe employe2 = new Employe(null, "Ousmane", "Diop ", "ousmane.diop@ouz-tech.sn", "787653423");
                Employe employe3 = new Employe(null, "Dieynaba", "Cisse ", "dieynaba.cisse@ouz-tech.sn", "767890097");
                Employe employe4 = new Employe(null, "Awa", "Ndiaye ", "awa.ndiaye@ouz-tech.sn", "767890098");
                Employe employe5 = new Employe(null, "Abdoulaye", "Fall ", "abdoulaye.fall@ouz-tech.sn", "767890099");
                log.info("Preloading -----------" + employeDao.save(employe1));
                log.info("Preloading -----------" + employeDao.save(employe2));
                log.info("Preloading -----------" + employeDao.save(employe3));
                log.info("Preloading -----------" + employeDao.save(employe4));
                log.info("Preloading -----------" + employeDao.save(employe5));
        };
    }
}
