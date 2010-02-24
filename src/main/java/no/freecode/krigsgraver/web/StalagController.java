/**
 *  Project: Krigsgraver
 *  Created: Feb 8, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.web;

import java.util.List;

import no.freecode.krigsgraver.model.CauseOfDeath;
import no.freecode.krigsgraver.model.Stalag;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Edit and display {@link CauseOfDeath} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/stalag")
public class StalagController {

    @Autowired
    private GenericDao genericDao;

    /**
     * List all the stalags.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public @ResponseBody List<Stalag> getList() {
        return genericDao.getAll(Stalag.class, Order.asc("name"));
    }
}
