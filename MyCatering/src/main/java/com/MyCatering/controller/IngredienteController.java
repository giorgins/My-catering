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


import com.MyCatering.model.Ingrediente;
import com.MyCatering.model.Piatto;
import com.MyCatering.service.IngredienteService;
import com.MyCatering.service.PiattoService;
import com.MyCatering.validator.IngredienteValidator;

@Controller
public class IngredienteController {
	
	@Autowired
	private IngredienteService ingredienteService;
	
	@Autowired
	private PiattoService piattoService;
	@Autowired
	private IngredienteValidator validator;
	
	
	@RequestMapping( value = "/ingredienti", method = RequestMethod.GET)
	public String getIngredienti(Model model) {
		model.addAttribute("ingredienti", ingredienteService.tutti());
	
		return "elencoIngredienti.html";
	}
	
	@RequestMapping(value="/ingredienti/{id}", method = RequestMethod.GET)
	public String getIngrediente(@PathVariable("id") Long id, Model model) {
		//Stringa che identifica
		model.addAttribute("ingrediente", ingredienteService.getById(id));
    	model.addAttribute("piatto", ingredienteService.getById(id).getPiatto());


		return "ingrediente.html";
	}
	
	@RequestMapping(value="/admin/newIngrediente", method = RequestMethod.GET)
	public String newIngrediente( Model model) {
		model.addAttribute("ingrediente", new Ingrediente());
    	model.addAttribute("piatti", piattoService.tutti());

		return "ingredienteForm";
		
	}
	@RequestMapping(value= "/admin/newIngrediente", method = RequestMethod.POST)
	public String getPiatto(@ModelAttribute("ingrediente") Ingrediente ingrediente, @RequestParam(required=false,name="piatto")Long idA,			
		Model model, BindingResult bindingResult) {
	
		this.validator.validate(ingrediente, bindingResult);
		System.out.println(ingrediente.getDescrizione() + ingrediente.getNome()+ idA);
		if (!bindingResult.hasErrors() && idA!=null) {
			Piatto piatto= this.piattoService.getById(idA);
			ingrediente.setPiatto(piatto);			
			System.out.println(ingrediente.getDescrizione() + ingrediente.getNome()+ idA);
			this.ingredienteService.inserisci(ingrediente);
			model.addAttribute("ingrediente", ingrediente);
			model.addAttribute("piatto", piatto);
			return "ingrediente";
		}
		model.addAttribute("piatti", piattoService.tutti());
		return "ingredienteForm";
	}

	
	
	@RequestMapping(value="/admin/deleteIngrediente", method = RequestMethod.GET)
    public String deleteIngrediente(Model model) {

    	model.addAttribute("ingredienti", ingredienteService.tutti());
		return "doDeleteIngrediente";
    }
	@RequestMapping(value="/admin/deleteIngrediente", method = RequestMethod.POST)
	public String deleteDoneBuffet(Model model, @RequestParam(required=false,name="ingredienteDaCancellare")Long id) {
		if (id==null) {
			model.addAttribute("ingredienti", ingredienteService.tutti());
			return "doDeleteIngrediente";
		}
		ingredienteService.elimina(id);
    	model.addAttribute("ingredienti", ingredienteService.tutti());
		return "elencoIngredienti";
	}
	
}
