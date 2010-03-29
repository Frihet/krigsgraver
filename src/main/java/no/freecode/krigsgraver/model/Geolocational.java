/**
 *  Project: Krigsgraver
 *  Created: Mar 29, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

/**
 * Implementing classes can be located in Google maps.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public interface Geolocational {

    Double getLatitude();

    void setLatitude(Double latitude);

    Double getLongitude();

    void setLongitude(Double longitude);
}
