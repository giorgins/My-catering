package com.MyCatering.validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.MyCatering.model.Buffet;

import com.MyCatering.service.BuffetService;



@Component
public class BuffetValidator implements Validator {
	
	@Autowired
	private BuffetService buffetService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Buffet.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "required");
		if (this.buffetService.aldreadyExist((Buffet)target))
			errors.reject("buffet.duplicato");
	}

}
