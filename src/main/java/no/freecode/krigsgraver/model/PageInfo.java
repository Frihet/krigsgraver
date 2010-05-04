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

import java.util.Locale;

import javax.persistence.Entity;
import javax.persistence.Lob;

/**
 * This database class contains the information necessary to display a help page
 * for a specific page in the system.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity
public class PageInfo extends BaseEntity {

    
    
    private Locale locale;
    
    @Lob
    private String html;

    
    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
