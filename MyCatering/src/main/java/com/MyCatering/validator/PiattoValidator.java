package com.MyCatering.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


import com.MyCatering.model.Piatto;

import com.MyCatering.service.PiattoService;



@Component
public class PiattoValidator implements Validator {
	@Autowired
	private PiattoService piattoService;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return Piatto.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.piattoService.aldreadyExist((Piatto)target))
			errors.reject("piatto.duplicato");
		
	}

}
