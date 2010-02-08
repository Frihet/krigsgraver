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
import javax.persistence.OneToOne;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class Person extends BaseEntity {

    @OneToOne
    private Name vesternName;

    @OneToOne
    private Name cyrillicName;

    @OneToOne
    private FlexibleDate dateOfBirth;

    private String placeOfBirth;

    private String nationality;

    private Integer prisonerNumber;
    
    private Integer obdNumber;
    
    private String rank;

    @OneToOne
    private FlexibleDate dateOfDeath;

    private String placeOfDeath;

    private String causeOfDeath;

    private String relatives;

}
