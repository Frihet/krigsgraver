/**
 *  Project: Krigsgraver
 *  Created: Mar 10, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

import java.util.Comparator;

public class CauseOfDeathComparator implements Comparator<CauseOfDeath> {
    
    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(CauseOfDeath c1, CauseOfDeath c2) {
        return c1.getName().compareTo(c2.getName());
    }
}
