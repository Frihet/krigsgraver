/**
 *  Project: Krigsgraver
 *  Created: Mar 30, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = FlexibleDateValidator.class)
@Documented
public @interface ValidFlexibleDate {

    String message() default "{invalidFlexibleDate}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

//    CaseMode value();

}
