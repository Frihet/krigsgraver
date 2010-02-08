/**
 *  Project: Krigsgraver
 *  Created: Feb 8, 2010
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
public class Grave extends BaseEntity {

    private Integer graveField;

    private Integer graveRow;

    private Integer graveNumber;

    @OneToOne
    private FlexibleDate dateOfBurial;

    @OneToOne
    private FlexibleDate dateOfExhumation;  // XXX: check this one

    // Geolocation
    private Double latitude;
    private Double longitude;
}
