package com.MyCatering.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import com.MyCatering.model.Chef;

import com.MyCatering.service.ChefService;

import com.MyCatering.validator.ChefValidator;


@Controller
public class ChefController {
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private ChefValidator validator;
	
	@RequestMapping( value = "/chefAll", method = RequestMethod.GET)
	public String getChefAll(Model model) {
		model.addAttribute("chefAll", chefService.tutti());
		return "elencoChef.html";
	}
	
	@RequestMapping(value="/chefAll/{id}", method = RequestMethod.GET)
	public String getChef(@PathVariable("id") Long id, Model model) {
		//Stringa che identifica
		model.addAttribute("chef", chefService.getById(id));
		model.addAttribute("buffetAll",chefService.getById(id).getBuffets());
		
		return "chef.html";
	}
	
	@RequestMapping(value="/admin/newChef", method = RequestMethod.GET)
	public String newChef( Model model) {
		model.addAttribute("chef", new Chef());
		return "chefForm";
	}
	
	@RequestMapping(value= "/admin/newChef", method = RequestMethod.POST)
	public String getChef(@ModelAttribute("chef") Chef chef,			
		Model model, BindingResult bindingResult) {
	
		this.validator.validate(chef, bindingResult);
		if (!bindingResult.hasErrors()) {
			this.chefService.inserisci(chef);
			model.addAttribute("chef", chef);
			return "chef";
		}
		return "chefForm";
	}
	

	
	
	@RequestMapping(value="/admin/deleteChef", method = RequestMethod.GET)
    public String deleteChef(Model model) {

    	model.addAttribute("chefAll", chefService.tutti());
		return "doDeleteChef";
    }
	@RequestMapping(value="/admin/deleteChef", method = RequestMethod.POST)
	public String deleteDoneBuffet(Model model, @RequestParam(required=false,name="chefDaCancellare")Long id) {
		if (id==null) {
			model.addAttribute("chefAll", chefService.tutti());
			return "doDeleteChef";
		}
		chefService.elimina(id);
    	model.addAttribute("chefAll", chefService.tutti());
		return "elencoChef";
	}
	
	
	
}
