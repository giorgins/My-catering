package com.MyCatering.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.MyCatering.model.Chef;

import com.MyCatering.service.ChefService;



@Component
public class ChefValidator implements Validator {
	@Autowired
	private ChefService chefService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Chef.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cognome", "required");
		if (this.chefService.aldreadyExist((Chef)target))
			errors.reject("chef.duplicato");
		
	}

}
