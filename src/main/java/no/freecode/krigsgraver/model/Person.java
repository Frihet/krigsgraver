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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Size;

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
    private PersonDetails westernDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @IndexedEmbedded
    private PersonDetails cyrillicDetails;

    @OneToOne(cascade = CascadeType.ALL)
    // TODO: how do I index this?  (use @IndexedEmbedded, and set a @DateBridge(resolution = Resolution.DAY) in the class, probably).
    private FlexibleDate dateOfBirth;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String nationality;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    private Integer prisonerNumber;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String obdNumber;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String rank;

    @OneToOne(cascade = CascadeType.ALL)
    // TODO: how do I index this?  (use @IndexedEmbedded, and set a @DateBridge(resolution = Resolution.DAY) in the class, probably).
    private FlexibleDate dateOfDeath;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String placeOfDeath;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Valid
    private List<CauseOfDeath> causesOfDeath;

    @Lob
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String remarks;

    @OneToMany(cascade = CascadeType.ALL)
    @Valid
    private List<Grave> grave;


    public PersonDetails getWesternDetails() {
        if (westernDetails == null) {
            westernDetails = new PersonDetails();
        }
        return westernDetails;
    }

    public void setWesternDetails(PersonDetails westernDetails) {
        this.westernDetails = westernDetails;
    }

    public PersonDetails getCyrillicDetails() {
        if (cyrillicDetails == null) {
            cyrillicDetails = new PersonDetails();
        }
        return cyrillicDetails;
    }

    public void setCyrillicDetails(PersonDetails cyrillicDetails) {
        this.cyrillicDetails = cyrillicDetails;
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

    public List<CauseOfDeath> getCausesOfDeath() {
        if (causesOfDeath == null) {
            causesOfDeath = new ArrayList<CauseOfDeath>();
        }
        
        return causesOfDeath;
    }

    public void setCausesOfDeath(List<CauseOfDeath> causesOfDeath) {
        this.causesOfDeath = causesOfDeath;
    }

    public List<Grave> getGrave() {
        return grave;
    }

    public void setGrave(List<Grave> grave) {
        this.grave = grave;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
