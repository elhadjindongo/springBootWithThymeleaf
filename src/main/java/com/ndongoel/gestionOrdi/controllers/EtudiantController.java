/* Created by El Hadji M. NDONGO    */
/* on 11/26/2021                    */
/* Project: gestionOrdi             */

package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.dao.EtudiantDao;
import com.ndongoel.gestionOrdi.dao.FilliereDao;
import com.ndongoel.gestionOrdi.dao.OrdinateurDao;
import com.ndongoel.gestionOrdi.entities.Etudiant;
import com.ndongoel.gestionOrdi.entities.Filliere;
import com.ndongoel.gestionOrdi.entities.Ordinateur;
import com.ndongoel.gestionOrdi.models.EtudiantForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EtudiantController {
    public static final String FIVE = "5";
    public static final String ZERO = "0";
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String ETUDIANT = "etudiant";
    public static final String OPERATION = "operation";
    public static final String MODIFICATION = "modification";
    public static final String ADD_ETUDIANT_VUE = "addEtudiant";
    public static final String ETUDIANT_FORM = "etudiantForm";
    public static final String FILLIERE_LIST = "filliereList";
    public static final String ORDINATEUR_LIST = "ordinateurList";
    public static final String ETUDIANTS_MODIFY_URL = "/etudiants/modify";
    public static final String ETUDIANTS_DELETE_URL = "/etudiants/delete";
    public static final String CONFIRMER_ETUDIANT_VUE = "confirmerEtudiant";
    public static final String ETUDIANTS_URL = "/etudiants";
    public static final String ETUDIANTS_VUE = "etudiants";
    public static final String ETUDIANTS_ADD_URL = "/etudiants/add";
    public static final String PAGES = "pages";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LIST_ETUDIANTS = "listEtudiants";
    @Autowired
    private EtudiantDao etudiantDao;
    @Autowired
    private FilliereDao filliereDao;
    @Autowired
    private OrdinateurDao ordinateurDao;

    //TODO: Handle DB Errors
    //TODO: pagination error(page not covered beyond the totalPages or < 0)
    //BUG: Modification is not working

    @GetMapping(ETUDIANTS_ADD_URL)
    public String addEtudiant(ModelMap model) {
        EtudiantForm etudiantForm = new EtudiantForm();
        //Getting the All Ordinateurs
        List<Ordinateur> ordinateurList = ordinateurDao.findAll();
        //Getting the All fillieres
        List<Filliere> filliereList = filliereDao.findAll();

        model.addAttribute(ORDINATEUR_LIST, ordinateurList);
        model.addAttribute(FILLIERE_LIST, filliereList);
        model.addAttribute(ETUDIANT_FORM, etudiantForm);
        return ADD_ETUDIANT_VUE;
    }

    @GetMapping(ETUDIANTS_URL)
    public String getAllEtudiants(ModelMap model,
                                  @RequestParam(name = PAGE, defaultValue = ZERO) int page,
                                  @RequestParam(name = SIZE, defaultValue = FIVE) int size) {
        Page<Etudiant> listEtudiants = etudiantDao.findAll(PageRequest.of(page, size));
        model.addAttribute(LIST_ETUDIANTS, listEtudiants.getContent());
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(PAGES, new int[listEtudiants.getTotalPages()]);
        return ETUDIANTS_VUE;
    }

    @PostMapping(ETUDIANTS_URL)
    public String saveEtudiant(@Valid EtudiantForm etudiantForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            //Getting the All Ordinateurs
            List<Ordinateur> ordinateurList = ordinateurDao.findAll();
            //Getting the All fillieres
            List<Filliere> filliereList = filliereDao.findAll();

            model.addAttribute(ORDINATEUR_LIST, ordinateurList);
            model.addAttribute(FILLIERE_LIST, filliereList);
            return ADD_ETUDIANT_VUE;
        }

        //Recuperation de la Filliere
        Filliere filliere = filliereDao.getById(etudiantForm.getFilliereId());
        //Recuperation de Ordinateur
        Ordinateur ordinateur = ordinateurDao.getById(etudiantForm.getOrdinateurId());
        //Contruction de Etudiant
        Etudiant etudiant = null;
        String operation = "";
        if (etudiantForm.getIdEtudiant() == null) {
            etudiant = new Etudiant(null,
                    etudiantForm.getPrenom(),
                    etudiantForm.getNom(),
                    etudiantForm.getEmail(),
                    etudiantForm.getPhone(),
                    ordinateur, filliere);
        } else {
            etudiant = new Etudiant(etudiantForm.getIdEtudiant(),
                    etudiantForm.getPrenom(),
                    etudiantForm.getNom(),
                    etudiantForm.getEmail(),
                    etudiantForm.getPhone(),
                    ordinateur, filliere);
            operation = MODIFICATION;
        }

        etudiantDao.save(etudiant);
        model.addAttribute(ETUDIANT, etudiant);
        model.addAttribute(OPERATION, operation);
        return CONFIRMER_ETUDIANT_VUE;

    }

    @GetMapping(ETUDIANTS_DELETE_URL)
    public String deleteEtudiant(Long id,
                                 @RequestParam(name = PAGE, defaultValue = ZERO) int page,
                                 @RequestParam(name = SIZE, defaultValue = FIVE) int size) {
        etudiantDao.deleteById(id);
        return "redirect:/etudiants?page=" + page + "&size=" + size;

    }

    @GetMapping(ETUDIANTS_MODIFY_URL)
    public String ModifyEtudiant(ModelMap model, Long id) {
        Etudiant etudiant = etudiantDao.getById(id);
        EtudiantForm etudiantForm = new EtudiantForm(
                etudiant.getIdEtudiant(),
                etudiant.getPrenom(),
                etudiant.getNom(),
                etudiant.getEmail(),
                etudiant.getPhone(),
                etudiant.getOrdinateur().getIdOrdinateur(),
                etudiant.getFilliere().getIdFilliere());
        //Getting the All Ordinateurs
        List<Ordinateur> ordinateurList = ordinateurDao.findAll();
        //Getting the All fillieres
        List<Filliere> filliereList = filliereDao.findAll();

        model.addAttribute(ORDINATEUR_LIST, ordinateurList);
        model.addAttribute(FILLIERE_LIST, filliereList);
        model.addAttribute(ETUDIANT_FORM, etudiantForm);
        model.addAttribute(OPERATION, MODIFICATION);
        return ADD_ETUDIANT_VUE;
    }
}
