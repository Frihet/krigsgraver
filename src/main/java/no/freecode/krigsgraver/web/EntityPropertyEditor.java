/**
 *  Project: Krigsgraver
 *  Created: Feb 23, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.web;

import java.beans.PropertyEditorSupport;

import no.freecode.krigsgraver.model.Entity;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.apache.log4j.Logger;

/**
 * Generic property editor for all DB entities.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public class EntityPropertyEditor extends PropertyEditorSupport {
    
    private static final Logger logger = Logger.getLogger(EntityPropertyEditor.class);
    
    private GenericDao genericDao;
    
    private Class<? extends Entity> clazz;
    
    /**
     * 
     */
    public EntityPropertyEditor(Class<? extends Entity> clazz, GenericDao genericDao) {
        this.clazz = clazz;
        this.genericDao = genericDao;
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if ("null".equals(text)) {
            setValue(null);

        } else {
            setValue(genericDao.get(clazz, Long.parseLong(text)));
        }
    }

    @Override
    public String getAsText() {
        Entity entity = (Entity) getValue();
        if (entity == null) {
            return null;
        } else {
            return String.valueOf(entity.getId());
        }
    }
}
