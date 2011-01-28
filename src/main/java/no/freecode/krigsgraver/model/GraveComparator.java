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

import org.apache.log4j.Logger;

/**
 * Compare two graves in order to sort them according to flexible dates.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
public class GraveComparator implements Comparator<Grave> {

    private static final Logger logger = Logger.getLogger(GraveComparator.class);

    /* (non-Javadoc)
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Grave g1, Grave g2) {
        
        FlexibleDate g1Dob = g1.getDateOfBurial();
        FlexibleDate g2Dob = g2.getDateOfBurial();
        
        Integer g1y = g1Dob.getYear(); if (g1y == null) { g1y = 0; }
        Integer g2y = g2Dob.getYear(); if (g2y == null) { g2y = 0; }
        Integer g1m = g1Dob.getMonth(); if (g1m == null) { g1m = 0; }
        Integer g2m = g2Dob.getMonth(); if (g2m == null) { g2m = 0; }
        Integer g1d = g1Dob.getDay(); if (g1d == null) { g1d = 0; }
        Integer g2d = g2Dob.getDay(); if (g2d == null) { g2d = 0; }

        if (g1y > g2y || g1m > g2m || g1d > g2d) {
            return 1;

        } else {
            return -1;
        }
    }
}
