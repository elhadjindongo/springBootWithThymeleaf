/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.models.OrdinateurForm;
import com.ndongoel.gestionOrdi.dao.OrdinateurDao;
import com.ndongoel.gestionOrdi.entities.Ordinateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class OrdianteurController {
    @Autowired
    private OrdinateurDao ordinateurDao;

    //TODO : Handle DB Exceptions

    @GetMapping("/ordinateurs/{id}")
    public Ordinateur getOrdinateur(@PathVariable Long id) {
        return ordinateurDao.getById(id);

    }

    @GetMapping("/ordinateurs/add")
    public String addOrdinateurs(ModelMap model) {
        OrdinateurForm ordinateurForm = new OrdinateurForm();
        model.addAttribute("ordinateurForm", ordinateurForm);
        return "addOrdinateur";
    }

    @GetMapping("/ordinateurs")
    public String getAllOrdinateurs(ModelMap model) {
        //TODO: Implements Pageable
        List<Ordinateur> ordinateurList = ordinateurDao.findAll();
        model.addAttribute("ordinateurList", ordinateurList);
        return "ordinateurs";
    }

    @PostMapping("/ordinateurs")
    public String saveFilliere(@ModelAttribute("ordinateurForm") OrdinateurForm ordinateurForm, ModelMap model) {
        //TODO: Field validation
        if (ordinateurForm.getRam().isEmpty() ||
                ordinateurForm.getMarque().isEmpty() ||
                ordinateurForm.getType().isEmpty() ||
                ordinateurForm.getProcesseur().isEmpty() ||
                ordinateurForm.getCapaciteDisque().isEmpty()
        ) {

            model.addAttribute("errorMessage", "Erreure! Veuillez remplir tout les champs. ");
            model.addAttribute("ordinateurForm", ordinateurForm);
            return "addOrdinateur";
        }
        Ordinateur ordinateur= null;
        if (ordinateurForm.getIdOrdinateur() == null) {
            ordinateur = new Ordinateur(null,
                    ordinateurForm.getType(),
                    ordinateurForm.getRam(),
                    ordinateurForm.getProcesseur(),
                    ordinateurForm.getMarque(),
                    ordinateurForm.getCapaciteDisque());
        }else {
             ordinateur = new Ordinateur(ordinateurForm.getIdOrdinateur(),
                    ordinateurForm.getType(),
                    ordinateurForm.getRam(),
                    ordinateurForm.getProcesseur(),
                    ordinateurForm.getMarque(),
                    ordinateurForm.getCapaciteDisque());
        }
        ordinateurDao.save(ordinateur);
        model.addAttribute("ordinateur", ordinateur);
        return "confirmerOrdinateur";
    }


    @GetMapping("/ordinateurs/delete")
    public String deleteOrdinateur(Long id) {
        ordinateurDao.deleteById(id);
        return "redirect:/ordinateurs";
    }

    @GetMapping("/ordinateurs/modify")
    public String ModifyOrdinateurs(ModelMap model, Long id) {
        Ordinateur ordinateur = ordinateurDao.getById(id);
        OrdinateurForm ordinateurForm = new OrdinateurForm(ordinateur.getIdOrdinateur(), ordinateur.getType(), ordinateur.getRam(), ordinateur.getProcesseur(), ordinateur.getMarque(), ordinateur.getCapaciteDisque());
        model.addAttribute("ordinateurForm", ordinateurForm);
        return "addOrdinateur";
    }
}
