/**
 *  Created: Mar 3, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.search;

import org.apache.commons.lang.StringUtils;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
public class QueryUtils {

    /**
     * Format a query string for searching a specific field, optionally matching
     * each word in a fuzzy manner.
     * 
     * e.g: with fuzzy matching, the string "Taras Piliptschuk" becomes field:(Taras~ Piliptschuk~)".
     * 
     * @return A formatted query string.
     */
    public static String formatQuery(String query, String field, boolean fuzzy, boolean beginsWith) {
        StringBuilder q = new StringBuilder();

        if (!StringUtils.isBlank(query)) {
            q.append(field).append(":(");

            String[] elements = StringUtils.split(query, " ");
            for (int i = 0; i < elements.length; i++) {
                if (i > 0) {
                    q.append(" ");
                }
                
                q.append(elements[i]);

                if (fuzzy) {
                    q.append("~");
                } else if (beginsWith) {
                    q.append("*");
                }
            }

            q.append(")");
            return q.toString();
            
        } else {
            return "";
        }
    }

    /**
     * Make a regular query fuzzy.
     * 
     * @param query
     */
    public static String makeFuzzy(String query) {
        StringBuilder q = new StringBuilder();

        if (!StringUtils.isBlank(query)) {
            String[] elements = StringUtils.split(query, " ");
            for (int i = 0; i < elements.length; i++) {
                String element = elements[i];
                
                if (i > 0) {
                    q.append(" ");
                }

//                q.append(elements[i]);
                q.append(element);
                
                if (!StringUtils.isNumeric(element)) {
                    // Make sure everything but numbers are treated in a fuzzy manner.
                    q.append("~");
                }
            }

            return q.toString();
            
        } else {
            return "";
        }
    }
}
