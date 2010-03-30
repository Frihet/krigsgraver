/**
 *  Project: Krigsgraver
 *  Created: Mar 30, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.util;

import no.freecode.krigsgraver.model.Geolocational;

import org.springframework.validation.Errors;

/**
 * Static methods for handling {@link Geolocational} stuff.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
public abstract class GeoUtils {

    public static void validateGeolocational(Errors errors, Geolocational geolocational) {
        Double latitude = geolocational.getLatitude();
        if (latitude != null && (latitude < 0 || latitude > 90)) {
            errors.rejectValue("latitude", "errors.latitudeMustBeNorthern");
        }
    }
}
