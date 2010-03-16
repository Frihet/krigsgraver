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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
@XStreamAlias("rank")
public class Rank extends BaseEntity {

    @Column(unique = true)
    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String name;

    public Rank() { }
    
    public Rank(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
