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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
@XStreamAlias("cemetery")
public class Cemetery extends BaseEntity implements Geolocational {

    @Column(nullable = false, unique = true)
    @Size(max = 255)
    @OrderBy
    @NotEmpty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String name;

    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String address;

    @ManyToOne
//    @Valid
    @IndexedEmbedded
    private PostalDistrict postalDistrict;

    // Geolocation
    private Double latitude;
    private Double longitude;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public PostalDistrict getPostalDistrict() {
        return postalDistrict;
    }

    public void setPostalDistrict(PostalDistrict postalDistrict) {
        this.postalDistrict = postalDistrict;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
