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

import javax.persistence.Entity;
import javax.validation.constraints.Size;

/**
 * There may be different sources of information about the same person/grave. A
 * single reference is one source.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class CauseOfDeath extends BaseEntity {

    @Size(max = 255)
    private String cause;

    /* E.g. "Illness", "External cause", etc. */
    @Size(max = 255)
    private String causeGroup;
    
    @Size(max = 255)
    private String description;

//    private Set<Person>
    

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
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
        return getCause();
    }
}
