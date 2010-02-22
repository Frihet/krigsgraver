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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * 
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class Grave extends BaseEntity {

    @ManyToOne
    private Cemetery cemetery;

    private Integer graveField;

    private Integer graveRow;

    private Integer graveNumber;

    @OneToOne(cascade = CascadeType.ALL)
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
    private boolean delete;

    public Cemetery getCemetery() {
        return cemetery;
    }

    public void setCemetery(Cemetery cemetery) {
        this.cemetery = cemetery;
    }

    public Integer getGraveField() {
        return graveField;
    }

    public void setGraveField(Integer graveField) {
        this.graveField = graveField;
    }

    public Integer getGraveRow() {
        return graveRow;
    }

    public void setGraveRow(Integer graveRow) {
        this.graveRow = graveRow;
    }

    public Integer getGraveNumber() {
        return graveNumber;
    }

    public void setGraveNumber(Integer graveNumber) {
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
