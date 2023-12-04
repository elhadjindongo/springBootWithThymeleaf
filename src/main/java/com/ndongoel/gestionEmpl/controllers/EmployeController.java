/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 04/12/2023 ************************************/
/****** Project: gestionEmploye *********************/
/****************************************************/

package com.ndongoel.gestionEmpl.controllers;

import com.ndongoel.gestionEmpl.dao.EmployeDao;
import com.ndongoel.gestionEmpl.entities.Employe;
import com.ndongoel.gestionEmpl.models.EmployeForm;
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

@Controller
public class EmployeController {
    public static final String TEN = "10";
    public static final String ZERO = "0";
    public static final String PAGE = "page";
    public static final String SIZE = "size";
    public static final String EMPLOYE = "employe";
    public static final String OPERATION = "operation";
    public static final String MODIFICATION = "modification";
    public static final String EMPLOYE_FORM = "employeForm";
    public static final String PAGES = "pages";
    public static final String CURRENT_PAGE = "currentPage";
    public static final String LIST_EMPLOYES = "listEmployes";

    public static final String ETUDIANTS_MODIFY_URL = "/admin/employes/modify";
    public static final String EMPLOYES_DELETE_URL = "/admin/employes/delete";
    public static final String EMPLOYES_ADD_URL = "/admin/employes/add";
    public static final String EMPLOYES_URL = "/user/employes";

    public static final String EMPLOYES = "employes";
    public static final String ADD_EMPLOYE_VUE = "addEmploye";
    public static final String CONFIRMER_EMPLOYE_VUE = "confirmerEmploye";
    @Autowired
    private EmployeDao employeDao;

    //TODO: Handle DB Errors
    //TODO: pagination error(page not covered beyond the totalPages or < 0)


    @GetMapping(EMPLOYES_ADD_URL)
    public String addEmployes(ModelMap model) {
        EmployeForm employeForm = new EmployeForm();
        model.addAttribute(EMPLOYE_FORM, employeForm);
        return ADD_EMPLOYE_VUE;
    }

    @GetMapping(EMPLOYES_URL)
    public String getAllEmployes(ModelMap model,
                                 @RequestParam(name = PAGE, defaultValue = ZERO) int page,
                                 @RequestParam(name = SIZE, defaultValue = TEN) int size) {
        Page<Employe> listEmployes = employeDao.findAll(PageRequest.of(page, size));
        model.addAttribute(LIST_EMPLOYES, listEmployes.getContent());
        model.addAttribute(CURRENT_PAGE, page);
        model.addAttribute(PAGES, new int[listEmployes.getTotalPages()]);
        return EMPLOYES;
    }

    @PostMapping(EMPLOYES_ADD_URL)
    public String saveEmploye(@Valid EmployeForm employeForm, BindingResult bindingResult, ModelMap model) {

        String operation = employeForm.getIdEmploye() != null ? MODIFICATION : "";
        if (bindingResult.hasErrors()) {
            model.addAttribute(OPERATION, operation);
            return ADD_EMPLOYE_VUE;
        }

        //Contruction de Employe
        Employe employe = new Employe(employeForm.getIdEmploye() == null ? null : employeForm.getIdEmploye(),
                employeForm.getPrenom(),
                employeForm.getNom(),
                employeForm.getEmail(),
                employeForm.getPhone());

        employeDao.save(employe);
        model.addAttribute(EMPLOYE, employe);
        model.addAttribute(OPERATION, operation);
        return CONFIRMER_EMPLOYE_VUE;

    }

    @GetMapping(EMPLOYES_DELETE_URL)
    public String deleteEtudiant(Long id,
                                 @RequestParam(name = PAGE, defaultValue = ZERO) int page,
                                 @RequestParam(name = SIZE, defaultValue = TEN) int size) {
        employeDao.deleteById(id);
        return "redirect:/user/employes?page=" + page + "&size=" + size;

    }

    @GetMapping(ETUDIANTS_MODIFY_URL)
    public String ModifyEmploye(ModelMap model, Long id) {
        Employe employe = employeDao.getById(id);
        EmployeForm employeForm = new EmployeForm(
                employe.getIdEmploye(),
                employe.getPrenom(),
                employe.getNom(),
                employe.getEmail(),
                employe.getPhone());

        model.addAttribute(EMPLOYE_FORM, employeForm);
        model.addAttribute(OPERATION, MODIFICATION);
        return ADD_EMPLOYE_VUE;
    }
}
