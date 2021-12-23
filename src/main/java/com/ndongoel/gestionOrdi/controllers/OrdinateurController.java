/* Created by El Hadji M. NDONGO    */
/* on 11/26/2021                    */
/* Project: gestionOrdi             */

package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.dao.OrdinateurDao;
import com.ndongoel.gestionOrdi.entities.Ordinateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
public class OrdinateurController {
    public static final String ZERO = "0";
    public static final String PAGE_STRING = "page";
    public static final String FIVE = "5";
    public static final String SIZE_STRING = "size";
    public static final String ORDINATEUR = "ordinateur";
    public static final String OPERATION = "operation";
    public static final String MODIFICATION = "modification";
    public static final String CONFIRMER_ORDINATEUR_VUE = "confirmerOrdinateur";
    public static final String ADD_ORDINATEUR_VUE = "addOrdinateur";
    public static final String ORDINATEURS_MODIFY_URL = "/ordinateurs/modify";
    public static final String ORDINATEURS_DELETE_URL = "/ordinateurs/delete";
    public static final String ORDINATEURS_VUE = "ordinateurs";
    public static final String ORDINATEURS_URL = "/ordinateurs";
    public static final String ORDINATEURS_ADD_URL = "/ordinateurs/add";
    public static final String ORDINATEURS_ID_URL = "/ordinateurs/{id}";
    public static final String ORDINATEUR_LIST = "ordinateurList";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String PAGES = "pages";
    @Autowired
    private OrdinateurDao ordinateurDao;

    //TODO : Handle DB Exceptions
    //TODO: pagination error(page not covered beyond the totalPages or < 0)

    @GetMapping(ORDINATEURS_ID_URL)
    public Ordinateur getOrdinateur(@PathVariable Long id) {
        return ordinateurDao.getById(id);

    }

    @GetMapping(ORDINATEURS_ADD_URL)
    public String addOrdinateurs(ModelMap model) {
        Ordinateur ordinateur = new Ordinateur();
        model.addAttribute(ORDINATEUR, ordinateur);
        return ADD_ORDINATEUR_VUE;
    }


    @GetMapping(ORDINATEURS_URL)
    public String getAllOrdinateurs(ModelMap model,
                                    @RequestParam(name = PAGE_STRING, defaultValue = ZERO) int page,
                                    @RequestParam(name = SIZE_STRING, defaultValue = FIVE) int size) {

        Page<Ordinateur> ordinateurList = ordinateurDao.findAll(PageRequest.of(page, size));
        model.addAttribute(ORDINATEUR_LIST, ordinateurList.getContent());
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(PAGES, new int[ordinateurList.getTotalPages()]);
        return ORDINATEURS_VUE;
    }

    @PostMapping(ORDINATEURS_URL)
    public String saveFilliere(@Valid Ordinateur ordinateur, BindingResult bindingResult, ModelMap model) {
        String operation = null == ordinateur.getIdOrdinateur() ? "" : MODIFICATION;
        if (bindingResult.hasErrors()) {
            model.addAttribute(OPERATION, operation);
            return ADD_ORDINATEUR_VUE;
        }

        ordinateurDao.save(ordinateur);
        model.addAttribute(ORDINATEUR, ordinateur);
        model.addAttribute(OPERATION, operation);
        return CONFIRMER_ORDINATEUR_VUE;
    }


    @GetMapping(ORDINATEURS_DELETE_URL)
    public String deleteOrdinateur(Long id,
                                   @RequestParam(name = PAGE_STRING, defaultValue = ZERO) int page,
                                   @RequestParam(name = SIZE_STRING, defaultValue = FIVE) int size) {
        /* On ne peut pas supprimer un ordinateur qui a deja attribue a un etudiant.
        org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException:
        Referential integrity constraint violation: */
        ordinateurDao.deleteById(id);
        return "redirect:/ordinateurs?page=" + page + "&size=" + size;
    }

    @GetMapping(ORDINATEURS_MODIFY_URL)
    public String ModifyOrdinateurs(ModelMap model, Long id) {
        Ordinateur ordinateur = ordinateurDao.getById(id);
        model.addAttribute(ORDINATEUR, ordinateur);
        model.addAttribute(OPERATION, MODIFICATION);
        return ADD_ORDINATEUR_VUE;
    }
}
