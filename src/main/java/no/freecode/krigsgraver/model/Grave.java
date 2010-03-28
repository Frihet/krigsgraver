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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.hibernate.search.annotations.IndexedEmbedded;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * 
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
@XStreamAlias("grave")
@JsonIgnoreProperties(value = "delete")
public class Grave extends BaseEntity {

    @ManyToOne
    @IndexedEmbedded
    private Cemetery cemetery;

    @Size(max = 255)
    private String graveField;

    @Size(max = 255)
    private String graveRow;

    @Size(max = 255)
    private String graveNumber;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OrderBy("year, month, day")
    private FlexibleDate dateOfBurial;

    private boolean moved;

    private boolean massGrave;

    @Lob
    // @NotEmpty
    private String reference;

    // Geolocation
    private Double latitude;
    private Double longitude;

    @Transient
    @XStreamOmitField
    private boolean delete;

    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public String getGraveField() {
        return graveField;
    }

    public void setGraveField(String graveField) {
        this.graveField = graveField;
    }

    public String getGraveRow() {
        return graveRow;
    }

    public void setGraveRow(String graveRow) {
        this.graveRow = graveRow;
    }

    public String getGraveNumber() {
        return graveNumber;
    }

    public void setGraveNumber(String graveNumber) {
        this.graveNumber = graveNumber;
    }

    public FlexibleDate getDateOfBurial() {
        if (dateOfBurial == null) {
            dateOfBurial = new FlexibleDate();
        }
        
        return dateOfBurial;
    }

    public void setDateOfBurial(FlexibleDate dateOfBurial) {
        this.dateOfBurial = dateOfBurial;
    }

    public boolean isMoved() {
        return moved;
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    public boolean isMassGrave() {
        return massGrave;
    }

    public void setMassGrave(boolean massGrave) {
        this.massGrave = massGrave;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }
}
