/********************************************************/
/****** Created by El Hadji M. NDONGO ******************/
/****** on 12/13/2021 ************************************/
/****** Project: gestionOrdi *********************/
/****************************************************/

package com.ndongoel.gestionEmpl.utils;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FilliereValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FilliereConstraint {
    String message() default "Filliere must only contains Alphabets !";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}