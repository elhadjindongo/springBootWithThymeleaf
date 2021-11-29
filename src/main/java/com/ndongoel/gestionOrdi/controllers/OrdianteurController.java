package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.dao.OrdinateurDao;
import com.ndongoel.gestionOrdi.entities.Ordinateur;
import com.ndongoel.gestionOrdi.models.OrdinateurForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrdianteurController {
    @Autowired
    private OrdinateurDao ordinateurDao;


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
            //TODO: prefill inputs with received value
            model.addAttribute("errorMessage", "Erreure! Veuillez remplir tout les champs. ");
            return "addOrdinateur";
        }
        Ordinateur ordinateur = new Ordinateur(null, ordinateurForm.getType(), ordinateurForm.getRam(), ordinateurForm.getProcesseur(), ordinateurForm.getMarque(), ordinateurForm.getCapaciteDisque());
        ordinateurDao.save(ordinateur);
        model.addAttribute("ordinateur", ordinateur);
        return "confirmerOrdinateur";
    }


    public String deleteOrdinateur(Long id) {
        //TODO: implement delete ordinateur
        return "redirect:/ordinateurs";

    }

    public String ModifyOrdinateurs(ModelMap model, Long id) {
        //TODO : Implements modify ordinateur
        return "null";
    }
}
