package bstu.sds.computer_equipment_rental.validator;

import bstu.sds.computer_equipment_rental.dto.RegistrationDto;
import bstu.sds.computer_equipment_rental.service.implementation.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegistrationDto.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        RegistrationDto user = (RegistrationDto) o;

        if(userService.findByName(user.getUsername()) != null) {
            errors.rejectValue("username", "", "This username is already in use");
        }
    }
}