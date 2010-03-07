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
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
// @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Nationality extends BaseEntity {

//    @Column(unique = true)
    @Size(max = 2)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String countryCode;

    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String name;


    public Nationality() { }
    
    public Nationality(String countryCode, String name) {
        this.countryCode = countryCode;
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return countryCode;
    }
}
