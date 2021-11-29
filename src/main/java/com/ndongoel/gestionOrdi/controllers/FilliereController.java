package com.ndongoel.gestionOrdi.controllers;

import java.util.List;

import com.ndongoel.gestionOrdi.models.FilliereForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;

import com.ndongoel.gestionOrdi.entities.Filliere;
import org.springframework.web.bind.annotation.*;

import com.ndongoel.gestionOrdi.dao.FilliereDao;

@Controller
public class FilliereController {
	@Autowired
	private FilliereDao filliereDao;

	@GetMapping("/fillieres/{id}")
	public Filliere getFilliere(@PathVariable Long id) {
		return filliereDao.getById(id);
	}

	@GetMapping("/fillieres/add")
	public String addFilliere(ModelMap model){
		FilliereForm filliereForm = new FilliereForm();
		model.addAttribute("filliereForm", filliereForm);
		return "addFilliere";
	}

	@GetMapping("/fillieres")
	public String getAllFillieres(ModelMap model) {
		List<Filliere> listFillieres = filliereDao.findAll();
		model.addAttribute("listFillieres", listFillieres);
		return "fillieres";
	}

	@PostMapping("/fillieres")
	public String saveFilliere(@ModelAttribute("filliereForm") FilliereForm filliereForm, ModelMap model) {
		//TODO: Input validation (nomFilliere must only contains alphabetical number and at least two caracters)
		if(filliereForm.getNomFilliere().isEmpty()){
			 //TODO: prefill input with received value
			 model.addAttribute("errorMessage", "Erreure sur le nom ");
			 return "addFilliere";
		 }
		Filliere filliere = new Filliere(null,filliereForm.getNomFilliere());
		filliereDao.save(filliere);
		model.addAttribute("filliere", filliere);
		return "confirmerFilliere";
	}

	public String deleteFilliere(Long id) {
		//TODO: implement delete Filliere
		return "redirect:/fillieres";

	}

	public String ModifyFilliere(ModelMap model, Long id) {
		//TODO : Implements modify Filliere
		return "null";
	}
}
