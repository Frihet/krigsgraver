/**
 *  Project: Krigsgraver
 *  Created: Feb 15, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.web;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.Person;

import org.apache.commons.collections.Factory;
import org.apache.commons.collections.list.LazyList;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
public class PersonCommandObject {
    
    @Valid
    private Person person;

    @Valid
    private List<Grave> lazyGraves;

    /**
     * 
     */
    public PersonCommandObject() { }

    /**
     * Fill the lazy grave list with content from a person.
     */
    public PersonCommandObject(Person person) {
        setPerson(person);
        
        getLazyGraves().addAll(person.getGraves());

        if (getLazyGraves().isEmpty()) {
            getLazyGraves().add(new Grave());
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @SuppressWarnings("unchecked")
    public List<Grave> getLazyGraves() {
        if (lazyGraves == null) {
            lazyGraves = LazyList.decorate(new ArrayList<Grave>(), new Factory() {
                @Override
                public Object create() {
                    return new Grave();
                }
            });
        }

        return lazyGraves;
    }

    public void setLazyGraves(List<Grave> lazyGraves) {
        this.lazyGraves = lazyGraves;
    }

    /* 
    public void setLazyGraves(List<Grave> graves) {
        List<Grave> lg = getLazyGraves();
        lg.clear();
        if (graves != null) {
            lg.addAll(graves);
        }
    }*/
}
