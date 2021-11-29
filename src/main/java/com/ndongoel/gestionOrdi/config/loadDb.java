/********************************************************/
/****** Created by Fatou Seck ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.config;

import com.ndongoel.gestionOrdi.dao.EtudiantDao;
import com.ndongoel.gestionOrdi.dao.FilliereDao;
import com.ndongoel.gestionOrdi.dao.OrdinateurDao;
import com.ndongoel.gestionOrdi.entities.Etudiant;
import com.ndongoel.gestionOrdi.entities.Filliere;
import com.ndongoel.gestionOrdi.entities.Ordinateur;
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
            //Filliere
            Filliere filliere1 = new Filliere(null, "Mathematiques Appliquees et informatiques");
            Filliere filliere2 = new Filliere(null, "Multimedia informatiques et Communications");
            Filliere filliere3 = new Filliere(null, "Internet et Developpement Application");
            log.info("Preloading " + filliereDao.save(filliere1));
            log.info("Preloading " + filliereDao.save(filliere2));
            log.info("Preloading " + filliereDao.save(filliere3));
            //Ordinateur
            Ordinateur ordinateur1 = new Ordinateur(null, "test", "2Go", "Duo core", "Lenonvo", "250 Go");
            Ordinateur ordinateur2 = new Ordinateur(null, "test", "8Go", "core i5", "HP", "500 Go");
            Ordinateur ordinateur3 = new Ordinateur(null, "test", "4Go", "core i7", "Dell", "1 To");
            log.info("Preloading " + ordinateurDao.save(ordinateur1));
            log.info("Preloading " + ordinateurDao.save(ordinateur2));
            log.info("Preloading " + ordinateurDao.save(ordinateur3));
            //Etudiant
            Etudiant etudiant1 = new Etudiant(null, "Fatou", "Seck", "fatou.seck@uvs.edu.sn", "778974532", ordinateur3, filliere1);
            Etudiant etudiant2 = new Etudiant(null, "Ousmane", "Diop", "ousmane.diop@uvs.edu.sn", "787653423", ordinateur2, filliere2);
            Etudiant etudiant3 = new Etudiant(null, "Dieynaba", "Cisse", "dieynaba.cisse@uvs.edu.sn", "767890098", ordinateur1, filliere3);
            log.info("Preloading " + etudiantDao.save(etudiant1));
            log.info("Preloading " + etudiantDao.save(etudiant2));
            log.info("Preloading " + etudiantDao.save(etudiant3));
        };
    }
}
