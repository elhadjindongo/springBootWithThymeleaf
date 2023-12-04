/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 12/13/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionEmpl.utils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FilliereValidator implements ConstraintValidator<FilliereConstraint, String> {
    @Override
    public void initialize(FilliereConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String filliere, ConstraintValidatorContext constraintValidatorContext) {

        //[ \t\n\x0B\f\r] represent whitespace, \s is not supported by java 8
        return (filliere != null && !filliere.isEmpty() && filliere.matches("^[a-zA-Z[ \\t\\n\\x0B\\f\\r]]*$"));
    }
}
