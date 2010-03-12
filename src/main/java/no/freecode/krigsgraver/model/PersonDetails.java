/**
 *  Project: Krigsgraver
 *  Created: Feb 5, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

/**
 * There may be different sources of information about the same person/grave. A
 * single reference is one source.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
//@Indexed
public class PersonDetails extends BaseEntity {

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String firstName;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String nameOfFather;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String lastName;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String placeOfBirth;


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getNameOfFather() {
        return nameOfFather;
    }
    
    public void setNameOfFather(String nameOfFather) {
        this.nameOfFather = nameOfFather;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    /**
     * @return true if none of the name fields have been set.
     */
    @Transient
    public boolean hasNoName() {
        if (StringUtils.isBlank(firstName) && StringUtils.isBlank(nameOfFather) && StringUtils.isBlank(lastName)) {
            return true;
        } else {
            return false;
        }
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return (firstName != null ? firstName : "") + (nameOfFather != null ? " " + nameOfFather : "") + (lastName != null ? " " + lastName : "");
    }
}
