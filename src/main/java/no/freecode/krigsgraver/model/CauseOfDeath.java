/**
 *  Project: Krigsgraver
 *  Created: Feb 11, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * There may be different sources of information about the same person/grave. A
 * single reference is one source.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
@XStreamAlias("causeOfDeath")
public class CauseOfDeath extends BaseEntity {

    @Size(max = 255)
    @NotEmpty
//    @Id
    @Column(nullable = false, unique = true)
    @OrderBy
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String name;

    /* E.g. "Illness", "External name", etc. */
    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String causeGroup;

    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String description;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getCauseGroup() {
        return causeGroup;
    }

    public void setCauseGroup(String causeGroup) {
        this.causeGroup = causeGroup;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getName();
    }
}
