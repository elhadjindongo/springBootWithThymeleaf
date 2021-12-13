/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class EtudiantController {
    @Autowired
    private EtudiantDao etudiantDao;
    @Autowired
    private FilliereDao filliereDao;
    @Autowired
    private OrdinateurDao ordinateurDao;

    //TODO: Handle DB Errors

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
        //TODO: Implements Pageable
        List<Etudiant> listEtudiants = etudiantDao.findAll();
        model.addAttribute("listEtudiants", listEtudiants);
        return "etudiants";
    }

    @PostMapping("/etudiants")
    public String saveEtudiant(@Valid EtudiantForm etudiantForm, BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            //Getting the All Ordinateurs
            List<Ordinateur> ordinateurList = ordinateurDao.findAll();
            //Getting the All fillieres
            List<Filliere> filliereList = filliereDao.findAll();

            model.addAttribute("ordinateurList", ordinateurList);
            model.addAttribute("filliereList", filliereList);
            return "addEtudiant";
        }

        //Recuperation de la Filliere
        Filliere filliere = filliereDao.getById(etudiantForm.getFilliereId());
        //Recuperation de Ordinateur
        Ordinateur ordinateur = ordinateurDao.getById(etudiantForm.getOrdinateurId());
        //Contruction de Etudiant
        Etudiant etudiant = null;
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
        }
        etudiantDao.save(etudiant);
        model.addAttribute("etudiant", etudiant);
        return "confirmerEtudiant";

    }

    @GetMapping("/etudiants/delete")
    public String deleteEtudiant(Long id) {
        etudiantDao.deleteById(id);
        return "redirect:/etudiant";

    }

    @GetMapping("/etudiants/modify")
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

        model.addAttribute("ordinateurList", ordinateurList);
        model.addAttribute("filliereList", filliereList);
        model.addAttribute("etudiantForm", etudiantForm);
        return "addEtudiant";
    }
}
