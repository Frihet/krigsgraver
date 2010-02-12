/**
 *  Project: Krigsgraver
 *  Created: Feb 8, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

import javax.persistence.Entity;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * A date implementation to make it a bit cleaner to store and express dates
 * that don't necessarily have all its fields set.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class FlexibleDate extends BaseEntity {

    @Min(1) @Max(31)
    private Integer day;

    @Min(1) @Max(12)
    private Integer month;

    @Min(1000) @Max(9999)
    private Integer year;

    public FlexibleDate() { }

    public FlexibleDate(Integer day, Integer month, Integer year) {
        setDay(day);
        setMonth(month);
        setYear(year);
    }
    
    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
