package web.development.util.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import web.development.repositories.UserRepository;

@Component
public class UniqueUserNameValidator implements ConstraintValidator<UniqueUserName, String> {

    @Autowired
    private  UserRepository userRepository;

    public UniqueUserNameValidator() {

    }


    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null) {
            return true; // или false, в зависимости от вашей логики
        }
        return userRepository.findByUsername(value).isEmpty();
    }


}

