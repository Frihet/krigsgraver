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
import java.util.Date;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.Valid;
import javax.validation.constraints.Size;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.Sort;
import org.hibernate.annotations.SortType;
import org.hibernate.search.annotations.Boost;
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

    /* Auto-generate timestamp. I've tested this on postgres and oracle. */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false, insertable = false, nullable = false, 
            columnDefinition = "timestamp default current_timestamp")
    @Generated(GenerationTime.ALWAYS)
    private Date createdDate;

    @OneToOne(cascade = CascadeType.ALL)
//    @IndexedEmbedded
    @Valid
    private PersonDetails westernDetails;

    @OneToOne(cascade = CascadeType.ALL)
//    @IndexedEmbedded
    @Valid
    private PersonDetails cyrillicDetails;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @IndexedEmbedded
    private FlexibleDate dateOfBirth;

    @ManyToOne
    @IndexedEmbedded
    private Nationality nationality;

    @Field(index = Index.UN_TOKENIZED, store = Store.NO)
    private Integer prisonerNumber;

    @Field(index = Index.UN_TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String obdNumber;

    @ManyToOne
    @IndexedEmbedded
    private Rank rank;

    @ManyToOne
    @IndexedEmbedded
    private Camp camp;

    @ManyToOne
    @IndexedEmbedded
    private Stalag stalag;

    @OneToOne(cascade = CascadeType.ALL)
    @IndexedEmbedded
    private FlexibleDate dateOfDeath;

    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Size(max = 255)
    private String placeOfDeath;

    @ManyToMany(cascade = CascadeType.MERGE)
//    @ManyToMany
    @Valid
    @IndexedEmbedded
    private List<CauseOfDeath> causesOfDeath;

    @Size(max = 255)
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String causeOfDeathDescription;

    @Lob
    @Field(index = Index.TOKENIZED, store = Store.NO)
    private String remarks;

//    @OneToMany(cascade = CascadeType.ALL)
    @OneToMany(cascade = CascadeType.MERGE)
    @IndexedEmbedded
    @Sort(type = SortType.COMPARATOR, comparator = GraveComparator.class)
    private SortedSet<Grave> graves;

    
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

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public String getCauseOfDeathDescription() {
        return causeOfDeathDescription;
    }

    public void setCauseOfDeathDescription(String causeOfDeathDescription) {
        this.causeOfDeathDescription = causeOfDeathDescription;
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

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

//    public List<Grave> getGraves() {
//        
//        if (graves == null) {
//            graves = new ArrayList<Grave>();
//        }
//        
//        return graves;
//    }
//
//    public void setGraves(List<Grave> graves) {
//        this.graves = graves;
//    }

    public SortedSet<Grave> getGraves() {
        if (graves == null) {
            graves = new TreeSet<Grave>(new GraveComparator());
        }

        return graves;
    }
    
    public void setGraves(SortedSet<Grave> graves) {
        this.graves = graves;
    }
    

    public Camp getCamp() {
        return camp;
    }


    public void setCamp(Camp camp) {
        this.camp = camp;
    }

    public Stalag getStalag() {
        return stalag;
    }

    public void setStalag(Stalag stalag) {
        this.stalag = stalag;
    }

    @Transient
    @Field(index = Index.TOKENIZED, store = Store.NO)
    public String getPlaceOfBirth() {
        return mixCharsets(getWesternDetails().getPlaceOfBirth(), getCyrillicDetails().getPlaceOfBirth());
    }

    @Transient
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Boost(3.0f)
    public String getFullName() {
        return mixCharsets(getWesternDetails().toString(), getCyrillicDetails().toString());
    }

    @Transient
    @Field(index = Index.TOKENIZED, store = Store.NO)
    public String getFirstName() {
        return mixCharsets(getWesternDetails().getFirstName(), getCyrillicDetails().getFirstName());
    }

    @Transient
    @Field(index = Index.TOKENIZED, store = Store.NO)
    public String getNameOfFather() {
        return mixCharsets(getWesternDetails().getNameOfFather(), getCyrillicDetails().getNameOfFather());
    }

    @Transient
    @Field(index = Index.TOKENIZED, store = Store.NO)
    public String getLastName() {
        return mixCharsets(getWesternDetails().getLastName(), getCyrillicDetails().getLastName());
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return mixCharsets(getWesternDetails().toString(), getCyrillicDetails().toString());
    }
    
    private static String mixCharsets(String s1, String s2) {
        if (StringUtils.isNotBlank(s1)) {
            StringBuilder s = new StringBuilder();
            s.append(s1);
            
            if (StringUtils.isNotBlank(s2)) {
                s.append(" / ");
                s.append(s2);
            }

            return s.toString();
            
        } else {
            return s2;
        }
    }
}
