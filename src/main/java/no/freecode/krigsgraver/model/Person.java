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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Store;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
@Indexed
public class Person extends IndexedEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @IndexedEmbedded
    private Name westernName;

    @OneToOne(cascade = CascadeType.ALL)
    @IndexedEmbedded
    private Name cyrillicName;

    @OneToOne(cascade = CascadeType.ALL)
    // TODO: how do I index this?  (use @IndexedEmbedded, and set a @DateBridge(resolution = Resolution.DAY) in the class, probably).
    private FlexibleDate dateOfBirth;

    @Field(index = Index.TOKENIZED, store=Store.NO)
    private String placeOfBirth;

    @Field(index = Index.TOKENIZED, store=Store.NO)
    private String nationality;

    @Field(index = Index.TOKENIZED, store=Store.NO)
    private Integer prisonerNumber;

    @Field(index = Index.TOKENIZED, store=Store.NO)
    private String obdNumber;

    @Field(index = Index.TOKENIZED, store=Store.NO)
    private String rank;

    @OneToOne(cascade = CascadeType.ALL)
    // TODO: how do I index this?  (use @IndexedEmbedded, and set a @DateBridge(resolution = Resolution.DAY) in the class, probably).
    private FlexibleDate dateOfDeath;

    @Field(index = Index.TOKENIZED, store=Store.NO)
    private String placeOfDeath;

    @Field(index = Index.TOKENIZED, store=Store.NO)
    private String causeOfDeath;

    @Lob
    @Field(index = Index.TOKENIZED, store=Store.NO)
    private String remarks;

    
    public Name getWesternName() {
        if (westernName == null) {
            westernName = new Name();
        }
        return westernName;
    }

    public void setWesternName(Name westernName) {
        this.westernName = westernName;
    }

    public Name getCyrillicName() {
        if (cyrillicName == null) {
            cyrillicName = new Name();
        }
        return cyrillicName;
    }

    public void setCyrillicName(Name cyrillicName) {
        this.cyrillicName = cyrillicName;
    }

    public FlexibleDate getDateOfBirth() {
        if (dateOfBirth == null) {
            dateOfBirth = new FlexibleDate();
        }
        
        return dateOfBirth;
    }

    public void setDateOfBirth(FlexibleDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPlaceOfBirth() {
        return placeOfBirth;
    }

    public void setPlaceOfBirth(String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Integer getPrisonerNumber() {
        return prisonerNumber;
    }

    public void setPrisonerNumber(Integer prisonerNumber) {
        this.prisonerNumber = prisonerNumber;
    }

    public String getObdNumber() {
        return obdNumber;
    }

    public void setObdNumber(String obdNumber) {
        this.obdNumber = obdNumber;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public FlexibleDate getDateOfDeath() {
        if (dateOfDeath == null) {
            dateOfDeath = new FlexibleDate();
        }

        return dateOfDeath;
    }

    public void setDateOfDeath(FlexibleDate dateOfDeath) {
        this.dateOfDeath = dateOfDeath;
    }

    public String getPlaceOfDeath() {
        return placeOfDeath;
    }

    public void setPlaceOfDeath(String placeOfDeath) {
        this.placeOfDeath = placeOfDeath;
    }

    public String getCauseOfDeath() {
        return causeOfDeath;
    }

    public void setCauseOfDeath(String causeOfDeath) {
        this.causeOfDeath = causeOfDeath;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
