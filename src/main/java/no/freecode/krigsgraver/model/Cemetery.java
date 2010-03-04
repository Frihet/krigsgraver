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
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class Cemetery extends BaseEntity {

    @Column(nullable = false, unique = true)
    @Size(max = 255)
    @OrderBy
    @NotEmpty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String name;

    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String address;

    // TODO: replace with a separate class (PostalDistrict?) with data from
    // posten.no
    @Size(max = 255)
    @Field(index = Index.UN_TOKENIZED, store = Store.NO)
    private String postalDistrict;

    @Field(index = Index.UN_TOKENIZED, store = Store.NO)
    private Integer postcode;

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

    public String getPostalDistrict() {
        return postalDistrict;
    }

    public void setPostalDistrict(String postalDistrict) {
        this.postalDistrict = postalDistrict;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }
}
