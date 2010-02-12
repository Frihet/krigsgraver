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
import javax.validation.constraints.Size;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class Cemetery extends BaseEntity {

    @Size(max = 255)
    private String name;
    
    @Size(max = 255)
    private String address;

    // TODO: replace with a separate class (PostalDistrict?) with data from posten.no
    @Size(max = 255)
    private String postalDistrict;
    
    private Integer postcode;
    
    // Geolocation
    private Double latitude;
    private Double longitude;
}
