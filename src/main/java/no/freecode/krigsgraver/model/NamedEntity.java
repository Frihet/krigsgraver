/**
 *  Project: Krigsgraver
 *  Created: Feb 14, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

/**
 * A database/search entity with a name.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public interface NamedEntity extends Entity {

    String getName();

    void setName(String name);
}
