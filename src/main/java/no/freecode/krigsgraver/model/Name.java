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

/**
 * There may be different sources of information about the same person/grave. A
 * single reference is one source.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class Name extends BaseEntity {

    private String first;

    private String last;

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
