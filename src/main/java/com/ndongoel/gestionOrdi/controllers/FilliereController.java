/* Created by El Hadji M. NDONGO    */
/* on 11/26/2021                    */
/* Project: gestionOrdi             */

package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.dao.FilliereDao;
import com.ndongoel.gestionOrdi.entities.Filliere;
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
public class FilliereController {
    public static final String ZERO = "0";
    public static final String FIVE = "5";
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String FILLIERE = "filliere";
    public static final String OPERATION = "operation";
    public static final String MODIFICATION = "modification";
    public static final String FILLIERES_DELETE_URL = "/fillieres/delete";
    public static final String CONFIRMER_FILLIERE_VUE = "confirmerFilliere";
    public static final String ADD_FILLIERE_VUE = "addFilliere";
    public static final String FILLIERES_URL = "/fillieres";
    public static final String FILLIERES_VUE = "fillieres";
    public static final String PAGES = "pages";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LIST_FILLIERES = "listFillieres";
    public static final String FILLIERES_ADD_URL = "/fillieres/add";
    public static final String FILLIERES_ID_URL = "/fillieres/{id}";
    public static final String FILLIERES_MODIFY_URL = "/fillieres/modify";
    @Autowired
    private FilliereDao filliereDao;

    //TODO: Handle DB Errors
    //TODO: pagination error(page not covered beyond the totalPages or < 0)
    //BUG: Modification is not working

    @GetMapping(FILLIERES_ID_URL)
    public Filliere getFilliere(@PathVariable Long id) {
        return filliereDao.getById(id);
    }

    @GetMapping(FILLIERES_ADD_URL)
    public String addFilliere(Filliere filliere, ModelMap model) {
        // filliere object's param is called bean-backed form, it's auto injected into the model with he same name
        return ADD_FILLIERE_VUE;
    }

    @GetMapping(FILLIERES_URL)
    public String getAllFillieres(ModelMap model, @RequestParam(name = PAGE, defaultValue = ZERO) int page, @RequestParam(name = SIZE, defaultValue = FIVE) int size) {
        Page<Filliere> listFillieres = filliereDao.findAll(PageRequest.of(page, size));
        model.addAttribute(LIST_FILLIERES, listFillieres.getContent());
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(PAGES, new int[listFillieres.getTotalPages()]);
        return FILLIERES_VUE;
    }

    @PostMapping(FILLIERES_URL)
    public String saveFilliere(@Valid Filliere filliere, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return ADD_FILLIERE_VUE;
        }
        String operation = null != filliere.getIdFilliere() ? MODIFICATION : "";
        filliereDao.save(filliere);
        model.addAttribute(FILLIERE, filliere);
        model.addAttribute(OPERATION, operation);
        return CONFIRMER_FILLIERE_VUE;
    }

    @GetMapping(FILLIERES_DELETE_URL)
    public String deleteFilliere(Long id,
                                 @RequestParam(name = PAGE, defaultValue = FilliereController.FIVE) int page,
                                 @RequestParam(name = SIZE, defaultValue = FilliereController.FIVE) int size) {
        /* On ne peut pas supprimer une filliere qui a deja des etudiants.
         Faudrais d'abord supprimer les etudiants ou les changer de filliere avant de pouvoir supprimer la filliere
        il faut personalliser l'erreur et le faire savoir a l'utilisateur*/
        filliereDao.deleteById(id);
        return "redirect:/fillieres?page=" + page + "&size=" + size;

    }

    @GetMapping(FILLIERES_MODIFY_URL)
    public String ModifyFilliere(ModelMap model, Long id) {
        Filliere filliere = filliereDao.findById(id).get();
        model.addAttribute(FILLIERE, filliere);
        model.addAttribute(OPERATION, MODIFICATION);
        return ADD_FILLIERE_VUE;
    }

}
