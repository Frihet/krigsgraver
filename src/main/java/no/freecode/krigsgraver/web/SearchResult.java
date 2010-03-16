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

import java.util.List;

import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.dao.Paginator;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
@XStreamAlias("searchResult")
public class SearchResult {

    private List<Person> persons;
    private Paginator paginator;


    public SearchResult(List<Person> persons, Paginator paginator) {
        this.persons = persons;
        this.paginator = paginator;

    }

    public List<Person> getPersons() {
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public Paginator getPaginator() {
        return paginator;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
    }

}
