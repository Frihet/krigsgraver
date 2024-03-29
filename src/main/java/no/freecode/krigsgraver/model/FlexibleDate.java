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
import javax.persistence.Transient;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

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
    
    /** Used to indicate that this date is not to be completely trusted. */
    private boolean approximate;

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

    @Field(name = "day", index = Index.TOKENIZED, store = Store.NO)
    public String getDayString() {
        if (day != null) {
            return String.format("%02d", day);

        } else {
            return null;
        }
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    /**
     * Make a special indexed version to get interval searches right. 
     */
    @Field(name = "month", index = Index.TOKENIZED, store = Store.NO)
    public String getMonthString() {
        if (month != null) {
            return String.format("%02d", month);

        } else {
            return null;
        }
    }
    
    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    /**
     * Make a special indexed version to get interval searches right. 
     */
    @Field(name = "year", index = Index.TOKENIZED, store = Store.NO)
    public String getYearString() {
        if (year != null) {
            return String.format("%02d", year);

        } else {
            return null;
        }
    }
    
    public boolean isApproximate() {
        return approximate;
    }

    public void setApproximate(boolean approximate) {
        this.approximate = approximate;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {

        if (year != null || month != null || day != null) {
            return
                (year != null ? String.format("%04d", year) : "????") + "-" +
                (month != null ? String.format("%02d", month) : "??") + "-" +
                (day != null ? String.format("%02d", day) : "??");
        } else {
            return "?";
        }
    }

    @Transient
    public boolean isEmpty() {
        if (year == null && month == null && day == null) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        System.out.println(new FlexibleDate(null, null, null).isEmpty());
    }
}
