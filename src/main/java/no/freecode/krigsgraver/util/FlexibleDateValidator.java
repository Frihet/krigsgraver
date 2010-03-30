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

import java.util.Calendar;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import no.freecode.krigsgraver.model.FlexibleDate;

import org.apache.log4j.Logger;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public class FlexibleDateValidator implements ConstraintValidator<ValidFlexibleDate, FlexibleDate> {

    private static final Logger logger = Logger.getLogger(FlexibleDateValidator.class);
    
    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
     */
    @Override
    public void initialize(ValidFlexibleDate constraintAnnotation) {
        // TODO Auto-generated method stub
    }

    /* (non-Javadoc)
     * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
     */
    @Override
    public boolean isValid(FlexibleDate date, ConstraintValidatorContext context) {

        Integer day = date.getDay();
        Integer month = date.getMonth();
        Integer year = date.getYear();

        if (day != null && month != null && year != null) {
            try {
                Calendar cal = Calendar.getInstance();
                cal.setLenient(false);
                cal.set(Calendar.DAY_OF_MONTH, day);
                cal.set(Calendar.MONTH, month - 1);
                cal.set(Calendar.YEAR, year);
                cal.getTime();

            } catch (Exception e) {
                return false;
            }

//            DateValidator.getInstance().isValid(year + "-" + month + "-" + day, "yyyy-MM-dd", false);
        }

        return true;
    }

    
    public static void main(String[] args) {
        int day = 30;
        int month = 2;
        int year = 2010;

//        GregorianCalendar.getInstance();
//        Calendar cal = new GregorianCalendar(2010, 0, 34);
        Calendar cal = Calendar.getInstance();
        cal.setLenient(false);

        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month - 1);
        cal.set(Calendar.YEAR, year);
        cal.getTime();
        
//        System.out.println(cal.getMaximum(Calendar.DAY_OF_MONTH));

//        System.out.println(new Date(10, 1, 32));
        
    }
}
