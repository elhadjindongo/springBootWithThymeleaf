/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 11/26/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionOrdi.controllers;

import com.ndongoel.gestionOrdi.dao.FilliereDao;
import com.ndongoel.gestionOrdi.entities.Filliere;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.List;

@Controller
public class FilliereController {
    @Autowired
    private FilliereDao filliereDao;

    //TODO: Handle DB Errors

    @GetMapping("/fillieres/{id}")
    public Filliere getFilliere(@PathVariable Long id) {
        return filliereDao.getById(id);
    }

    @GetMapping("/fillieres/add")
    public String addFilliere(ModelMap model) {
        Filliere filliere = new Filliere();
        model.addAttribute("filliere", filliere);
        return "addFilliere";
    }

    @GetMapping("/fillieres")
    public String getAllFillieres(ModelMap model) {
        //TODO: Implements Pageable
        List<Filliere> listFillieres = filliereDao.findAll();
        model.addAttribute("listFillieres", listFillieres);
        return "fillieres";
    }

    @PostMapping("/fillieres")
    public String saveFilliere(@Valid Filliere filliere, ModelMap model, BindingResult bindingResult) {
        //TODO: Input validation (nomFilliere must only contains alphabetical letters)
        //TODO: The code below is not working ...user gets an error page
        if (bindingResult.hasFieldErrors()) {
            model.addAttribute("filliere", filliere);
            return "addFilliere";
        }
        filliereDao.save(filliere);
        model.addAttribute("filliere", filliere);
        return "confirmerFilliere";
    }

    @GetMapping("/fillieres/delete")
    public String deleteFilliere(Long id) {
        // On ne peut pas supprimer une filliere qui a deja des etudiants.
        // Faudrais d'abord supprimer les etudiants ou les changer de filliere avant de pouvoir supprimer la filliere

        filliereDao.deleteById(id);
        return "redirect:/fillieres";

    }

    @GetMapping("/fillieres/modify")
    public String ModifyFilliere(ModelMap model, Long id) {
        Filliere filliere = filliereDao.findById(id).get();
        model.addAttribute("filliere", filliere);
        return "addFilliere";
    }

}
