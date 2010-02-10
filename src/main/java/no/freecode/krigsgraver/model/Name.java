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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

/**
 * There may be different sources of information about the same person/grave. A
 * single reference is one source.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
@Indexed
public class Name extends IndexedEntity {

    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String first;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String last;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String father;


    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return (first != null ? first : "") + " " + (last != null ? last : "");
    }
}
