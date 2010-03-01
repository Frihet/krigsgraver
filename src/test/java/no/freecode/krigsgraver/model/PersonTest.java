/**
 *  Project: Krigsgraver
 *  Created: Mar 1, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

import junit.framework.TestCase;


/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
public class PersonTest extends TestCase {

    Person person;
    
    /* (non-Javadoc)
     * @see junit.framework.TestCase#setUp()
     */
    @Override
    protected void setUp() throws Exception {
        person = new Person();
    }
    
    /**
     * 
     */
    public void testToString() {
        
        person.getWesternDetails().setFirstName("Ivan");
        person.getCyrillicDetails().setFirstName("Иван");

        assertEquals("Ivan / Иван", person.toString());

        person.getCyrillicDetails().setFirstName("");
        assertEquals("Ivan", person.toString());
    }
    
}
