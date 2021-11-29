package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.dao.EtudiantDao;
import com.ndongoel.gestionOrdi.dao.FilliereDao;
import com.ndongoel.gestionOrdi.dao.OrdinateurDao;
import com.ndongoel.gestionOrdi.entities.Etudiant;
import com.ndongoel.gestionOrdi.entities.Filliere;
import com.ndongoel.gestionOrdi.entities.Ordinateur;
import com.ndongoel.gestionOrdi.models.EtudiantForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EtudiantController {
    @Autowired
    private EtudiantDao etudiantDao;
    @Autowired
    private FilliereDao filliereDao;
    @Autowired
    private OrdinateurDao ordinateurDao;

    @GetMapping("/etudiants/add")
    public String addEtudiant(ModelMap model) {
        EtudiantForm etudiantForm = new EtudiantForm();
        //Getting the All Ordinateurs
        List<Ordinateur> ordinateurList = ordinateurDao.findAll();
        //Getting the All fillieres
        List<Filliere> filliereList = filliereDao.findAll();

        model.addAttribute("ordinateurList", ordinateurList);
        model.addAttribute("filliereList", filliereList);
        model.addAttribute("etudiantForm", etudiantForm);
        return "addEtudiant";
    }

    @GetMapping("/etudiants")
    public String getAllEtudiants(ModelMap model) {
        List<Etudiant> listEtudiants = etudiantDao.findAll();
        model.addAttribute("listEtudiants", listEtudiants);
        return "etudiants";
    }

    @PostMapping("/etudiants")
    public String saveEtudiant(@ModelAttribute("etudiantForm") EtudiantForm etudiantForm, ModelMap model) {
        //TODO: Input validation (email,phone should be a Senegal phone number,nom & prenom must be only alphabetical letter)
        if (etudiantForm.getNom().isEmpty() ||
                etudiantForm.getPrenom().isEmpty() ||
                etudiantForm.getEmail().isEmpty() ||
                etudiantForm.getPhone().isEmpty()
        ) {
            //TODO: it not working.Must find a way to indicate the error to the client (precise input) & prefill inputs with received values
            model.addAttribute("errorMessage", "Erreure! Veuillez remplir tout les champs. ");
            return "redirect:/etudiants/add";
        }

        //Recuperation de la Filliere
        Filliere filliere = filliereDao.getById(etudiantForm.getFilliereId());
        System.out.println("************************************* Filliere "+filliere);
        //Recuperation de Ordinateur
        Ordinateur ordinateur = ordinateurDao.getById(etudiantForm.getOrdinateurId());
        System.out.println("************************************* Ordinateur "+ordinateur);
        //Contruction de Etudiant
        Etudiant etudiant = new Etudiant(null, etudiantForm.getPrenom(), etudiantForm.getNom(), etudiantForm.getEmail(), etudiantForm.getPhone(),ordinateur,filliere);
        System.out.println("************************************* Etudiant "+etudiantForm);
        etudiantDao.save(etudiant);
        model.addAttribute("etudiant", etudiant);
        return "confirmerEtudiant";

    }

    public String deleteEtudiant(Long id) {
        //TODO: implement delete Etudiant
        return "redirect:/etudiant";

    }

    public String ModifyEtudiant(ModelMap model, Long id) {
        //TODO : Implements modify Etudiant
        return "null";
    }
}
