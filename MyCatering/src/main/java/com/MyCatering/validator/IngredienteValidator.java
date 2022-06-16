package com.MyCatering.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.MyCatering.model.Ingrediente;
import com.MyCatering.service.IngredienteService;



@Component
public class IngredienteValidator implements Validator {
	@Autowired
	private IngredienteService ingredienteService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Ingrediente.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.ingredienteService.aldreadyExist((Ingrediente)target))
			errors.reject("ingrediente.duplicato");
		
	}

}
