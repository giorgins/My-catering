package com.MyCatering.controller;






import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Base64Utils;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.MyCatering.model.Buffet;
import com.MyCatering.model.Piatto;
import com.MyCatering.service.BuffetService;
import com.MyCatering.service.PiattoService;

import com.MyCatering.validator.PiattoValidator;

@Controller
public class PiattoController {

	@Autowired
	private PiattoService piattoService;

	@Autowired
	private BuffetService buffetService;

	@Autowired
	private PiattoValidator validator;

	@RequestMapping( value = "/piatti", method = RequestMethod.GET)
	public String getPiatti(Model model) {
		model.addAttribute("piatti", piattoService.tutti());

		return "elencoPiatti.html";
	}

	@RequestMapping(value="/piatti/{id}", method = RequestMethod.GET)
	public String getPiatto(@PathVariable("id") Long id, Model model) {
	
		Piatto piatto = piattoService.getById(id);
		model.addAttribute("piatto", piatto);

		byte[] bytes = ArrayUtils.toPrimitive(piatto.getImage());
    	model.addAttribute("image", Base64Utils.encodeToString(bytes));

		model.addAttribute("buffet", piattoService.getById(id).getBuffet());
		model.addAttribute("ingredienti", piattoService.getById(id).getIngredienti());

		return "piatto";
	}

	@RequestMapping(value="/admin/newPiatto", method = RequestMethod.GET)
	public String newPiatto( Model model) {
		model.addAttribute("piatto", new Piatto());
		model.addAttribute("buffetAll", buffetService.tutti());

		return "piattoForm";

	}

	@RequestMapping(value= "/admin/newPiatto", method = RequestMethod.POST)
	public String getPiatto(@ModelAttribute("piatto") Piatto piatto, 
			@RequestParam(required=false,name="buffet")Long idA,
			@RequestParam(required=false,name="foto")MultipartFile foto,			
			Model model, BindingResult bindingResult) {

		this.validator.validate(piatto, bindingResult);
		if (!bindingResult.hasErrors() && idA!=null) {
			Buffet buffet= this.buffetService.getById(idA);
			piatto.setBuffet(buffet);
			try {
				Byte[] byteObjects = new Byte[foto.getBytes().length];
				int i = 0;
				for (byte b : foto.getBytes()){
					byteObjects[i++] = b;
				}
				piatto.setImage(byteObjects);
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.piattoService.inserisci(piatto);
			byte[] bytes = ArrayUtils.toPrimitive(piatto.getImage());
			model.addAttribute("image", Base64Utils.encodeToString(bytes));
			model.addAttribute("piatto", piatto);
			model.addAttribute("buffet", buffet);
			return "piatto";
		}
		model.addAttribute("buffetAll", buffetService.tutti());
		return "piattoForm";
	}




	@RequestMapping(value="/admin/deletePiatto", method = RequestMethod.GET)
	public String deletePiatto(Model model) {

		model.addAttribute("piatti", piattoService.tutti());
		return "doDeletePiatto";
	}
	@RequestMapping(value="/admin/deletePiatto", method = RequestMethod.POST)
	public String deleteDoneBuffet(Model model, @RequestParam(required=false,name="piattoDaCancellare")Long id) {
		if (id==null) {
			model.addAttribute("piatti", piattoService.tutti());
			return "doDeletePiatto";
		}
		piattoService.elimina(id);
		model.addAttribute("piatti", piattoService.tutti());
		return "elencoPiatti";
	}


}
