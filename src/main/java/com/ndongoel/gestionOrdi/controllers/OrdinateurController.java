/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.dao.OrdinateurDao;
import com.ndongoel.gestionOrdi.entities.Ordinateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class OrdinateurController {
    @Autowired
    private OrdinateurDao ordinateurDao;

    //TODO : Handle DB Exceptions

    @GetMapping("/ordinateurs/{id}")
    public Ordinateur getOrdinateur(@PathVariable Long id) {
        return ordinateurDao.getById(id);

    }

    @GetMapping("/ordinateurs/add")
    public String addOrdinateurs(ModelMap model) {
        Ordinateur ordinateur = new Ordinateur();
        model.addAttribute("ordinateur", ordinateur);
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
    public String saveFilliere(@Valid Ordinateur ordinateur, ModelMap model, BindingResult bindingResult) {
        //TODO: The code below is not working. Must catch the validation exception...user gets an error page
        if (bindingResult.hasErrors()) {
            model.addAttribute("ordinateur", ordinateur);
            return "addOrdinateur";
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
        model.addAttribute("ordinateur", ordinateur);
        return "addOrdinateur";
    }
}
