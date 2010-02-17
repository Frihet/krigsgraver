/**
 *  Project: Krigsgraver
 *  Created: Feb 10, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.search.annotations.DocumentId;

/**
 * Base class for all entity classes that are to be included in the search index.
 * 
 * @author Reidar Øksnevad (reidar.oksnevad@freecode.no)
 */
@MappedSuperclass
public abstract class IndexedEntity implements Entity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @DocumentId
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
