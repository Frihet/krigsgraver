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

import java.util.HashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Store;
import org.hibernate.validator.constraints.NotEmpty;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
@XStreamAlias("postalDistrict")
public class PostalDistrict {

    @Id
    @Column(unique = true, nullable = false)
    @NotNull
    private Integer postcode;

    @Column(nullable = false)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    @NotEmpty
    private String name;

//    @Column(unique = true, nullable = false)
//    @Field(index = Index.TOKENIZED, store = Store.NO)
//    @Size(max = 255)
//    @NotEmpty
//    private String municipality;
    
    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private int countyId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    @Field(name = "postcode", index = Index.TOKENIZED, store = Store.NO)
    @Transient
    public String getPostcodeString() {
        if (postcode != null) {
            return String.format("%04d", postcode);

        } else {
            return null;
        }
    }
    
    public int getCountyId() {
        return countyId;
    }

    public void setCountyId(int countyId) {
        this.countyId = countyId;
    }

    /**
     * @return The municipality for this district, or null if there is none.
     */
    @Transient
    @Field(index = Index.TOKENIZED, store = Store.NO)
    public String getCounty() {
        return COUNTY_MAP.get(getCountyId());
    }


    // Specify the Norwegian counties
    public static final Map<Integer, String> COUNTY_MAP = new HashMap<Integer, String>() {
        private static final long serialVersionUID = 1L;
        {
            put(1, "Østfold");
            put(2, "Akershus");
            put(3, "Oslo");
            put(4, "Hedmark");
            put(5, "Oppland");
            put(6, "Buskerud");
            put(7, "vestfold");
            put(8, "Telemark");
            put(9, "Aust-Agder");
            put(10, "Vest-Agder");
            put(12, "Rogaland");
            put(13, "Hordaland");
            put(14, "Sogn og Fjordane");
            put(15, "Møre og Romsdal");
            put(16, "Sør-Trøndelag");
            put(17, "Nord-Trøndelag");
            put(18, "Nordland");
            put(19, "Troms");
            put(20, "Finnmark");
            put(21, "Svalbard");
            put(22, "Jan Mayen");
            put(23, "Kontinentalsokkelen");
        }
    };
}
