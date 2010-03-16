/**
 *  Created: Dec 13, 2009
 *  Copyright: 2009, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model.dao;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Paginator class for getting and setting the current page info.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@XStreamAlias("paginator")
public class Paginator {

//    public static int ITEMS_PER_PAGE = 10;
    private int itemsPerPage = 10;
    
    private int pageNumber;
    
    private int numberOfResults;

    private boolean hasNext;

    
    public Paginator(Integer pageNumber) {
        if (pageNumber == null) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
    }
    
    public Paginator(Integer pageNumber, Integer itemsPerPage) {
        if (pageNumber == null) {
            this.pageNumber = 1;
        } else {
            this.pageNumber = pageNumber;
        }
        
        this.itemsPerPage = itemsPerPage;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public boolean isHasPrevious() {
        if (this.pageNumber > 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @return the previous page if there is one, otherwise <code>null</code>.
     */
    public Integer getPreviousPage() {
        if (this.pageNumber > 1) {
            return this.pageNumber - 1;
        } else {
            return null;
        }
    }

    public boolean isHasNext() {
        return hasNext;
    }

    public Integer getNextPage() {
        return this.pageNumber + 1;
    }
    
    public void setHasNext(boolean hasNext) {
        this.hasNext = hasNext;
    }

    public int getNumberOfResults() {
        return numberOfResults;
    }

    public void setNumberOfResults(int numberOfResults) {
        this.numberOfResults = numberOfResults;
    }
    
    public int getItemsPerPage() {
        return itemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        this.itemsPerPage = itemsPerPage;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public <T> List<T> paginate(List<T> results) {
        List<T> copy = new ArrayList<T>(results);

        if (copy.size() > getItemsPerPage()) {
            copy.remove(copy.size() - 1);  // remove the last result, as it was only there to see if there is another page.
            setHasNext(true);
        }
        
        return copy;
    }
}
