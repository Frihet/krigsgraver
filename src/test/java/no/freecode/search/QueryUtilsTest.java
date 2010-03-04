/**
 *  Project: Krigsgraver
 *  Created: Mar 3, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.search;

import static no.freecode.search.QueryUtils.formatQuery;
import static no.freecode.search.QueryUtils.makeFuzzy;
import junit.framework.TestCase;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
public class QueryUtilsTest extends TestCase {

    public void testFormatQuery() throws Exception {
        assertEquals("fullName:(Taras Piliptschuk)", formatQuery("Taras Piliptschuk", "fullName", false));
        assertEquals("fullName:(Taras~ Piliptschuk~)", formatQuery("Taras Piliptschuk", "fullName", true));
    }
    
    public void testMakeFuzzy() throws Exception {
        assertEquals("Taras~ Piliptschuk~", makeFuzzy("Taras Piliptschuk"));
        assertEquals("", makeFuzzy(" "));
    }
}
