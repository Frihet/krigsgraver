/**
 *  Project: Krigsgraver
 *  Created: Feb 5, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package remove;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import no.freecode.krigsgraver.model.BaseEntity;
import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.Person;

/**
 * There may be different sources of information about the same person/grave. A
 * single reference is one source.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class Reference extends BaseEntity {

    @ManyToOne(optional = false)
    private Person person;

    @ManyToOne(optional = false)
    private Grave grave;

    private String documentation;
}
