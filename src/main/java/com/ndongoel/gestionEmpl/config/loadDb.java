/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionEmpl.config;

import com.ndongoel.gestionEmpl.dao.EtudiantDao;
import com.ndongoel.gestionEmpl.dao.FilliereDao;
import com.ndongoel.gestionEmpl.dao.OrdinateurDao;
import com.ndongoel.gestionEmpl.entities.Etudiant;
import com.ndongoel.gestionEmpl.entities.Filliere;
import com.ndongoel.gestionEmpl.entities.Ordinateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class loadDb {
    private static final Logger log = LoggerFactory.getLogger(loadDb.class);

    @Bean
    CommandLineRunner initDatabase(EtudiantDao etudiantDao, FilliereDao filliereDao, OrdinateurDao ordinateurDao) {

        return args -> {
            for (int i = 65; i <= 90; i++) {
                //Filliere
                Filliere filliere1 = new Filliere(null, "Mathematiques Appliquees et informatiques " + (char) i);
                Filliere filliere2 = new Filliere(null, "Multimedia informatiques et Communications " + (char) i);
                Filliere filliere3 = new Filliere(null, "Internet et Developpement Application " + (char) i);
                log.info("Preloading " + filliereDao.save(filliere1));
                log.info("Preloading " + filliereDao.save(filliere2));
                log.info("Preloading " + filliereDao.save(filliere3));
                //Ordinateur
                Ordinateur ordinateur1 = new Ordinateur(null, "test " + (char) i, 2, "Duo core", "Lenonvo", 250);
                Ordinateur ordinateur2 = new Ordinateur(null, "test " + (char) i, 8, "core i5", "HP", 500);
                Ordinateur ordinateur3 = new Ordinateur(null, "test " + (char) i, 4, "core i7", "Dell", 1000);
                log.info("Preloading " + ordinateurDao.save(ordinateur1));
                log.info("Preloading " + ordinateurDao.save(ordinateur2));
                log.info("Preloading " + ordinateurDao.save(ordinateur3));
                //Etudiant
                Etudiant etudiant1 = new Etudiant(null, "Fatou", "Seck " + (char) i, "fatou.seck@uvs.edu.sn", "778974532", ordinateur3, filliere1);
                Etudiant etudiant2 = new Etudiant(null, "Ousmane", "Diop " + (char) i, "ousmane.diop@uvs.edu.sn", "787653423", ordinateur2, filliere2);
                Etudiant etudiant3 = new Etudiant(null, "Dieynaba", "Cisse " + (char) i, "dieynaba.cisse@uvs.edu.sn", "767890098", ordinateur1, filliere3);
                log.info("Preloading " + etudiantDao.save(etudiant1));
                log.info("Preloading " + etudiantDao.save(etudiant2));
                log.info("Preloading " + etudiantDao.save(etudiant3));
            }
        };
    }
}
