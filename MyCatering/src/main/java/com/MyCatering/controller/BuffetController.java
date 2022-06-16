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

import com.MyCatering.model.Buffet;
import com.MyCatering.model.Chef;
import com.MyCatering.service.BuffetService;
import com.MyCatering.service.ChefService;
import com.MyCatering.validator.BuffetValidator;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetValidator validator;
	
	@RequestMapping( value = "/buffetAll", method = RequestMethod.GET)
	public String getBuffetAll(Model model) {
		model.addAttribute("buffetAll", buffetService.tutti());
		return "elencoBuffets.html";
	}
	
	@RequestMapping(value="/buffetAll/{id}", method = RequestMethod.GET)
	public String getBuffet(@PathVariable("id") Long id, Model model) {
		//Stringa che identifica
		model.addAttribute("buffet", buffetService.getById(id));
		model.addAttribute("piatti",buffetService.getById(id).getPiatti());
    	model.addAttribute("chef", buffetService.getById(id).getChef());

		return "buffet.html";
	}
	
	@RequestMapping(value="/admin/newBuffet", method = RequestMethod.GET)
	public String newBuffet( Model model) {
		model.addAttribute("buffet", new Buffet());
        model.addAttribute("chefAll", chefService.tutti());

		return "buffetForm";
	}
	
	@RequestMapping(value= "/admin/newBuffet", method = RequestMethod.POST)
	public String getBuffet(@ModelAttribute("buffet") Buffet buffet, @RequestParam(required=false,name="chef")Long idA,			
		Model model, BindingResult bindingResult) {
	
		this.validator.validate(buffet, bindingResult);
		if (!bindingResult.hasErrors()&& idA!=null) {
			Chef chef= this.chefService.getById(idA);
			buffet.setChef(chef);
			this.buffetService.inserisci(buffet);
			model.addAttribute("buffet", buffet);
			model.addAttribute("chef", chef);
			return "buffet";
		}
		model.addAttribute("chefAll", chefService.tutti());
		return "buffetForm";
	}
	

	
	
	@RequestMapping(value="/admin/deleteBuffet", method = RequestMethod.GET)
    public String deleteBuffet(Model model) {

    	model.addAttribute("buffetAll", buffetService.tutti());
		return "doDeleteBuffet";
    }
	@RequestMapping(value="/admin/deleteBuffet", method = RequestMethod.POST)
	public String deleteDoneBuffet(Model model, @RequestParam(required=false,name="buffetDaCancellare")Long id) {
		if (id==null) {
			model.addAttribute("buffetAll", buffetService.tutti());
			return "doDeleteBuffet";
		}
		buffetService.elimina(id);
    	model.addAttribute("buffetAll", buffetService.tutti());
		return "elencoBuffets";
	}
	
	
	
}
