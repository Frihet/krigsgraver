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

import javax.validation.Valid;

import no.freecode.krigsgraver.model.Cemetery;
import no.freecode.krigsgraver.model.dao.CemeteryDao;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Edit and display Person objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/cemetery")
public class CemeteryController {

    @Autowired
    private CemeteryDao cemeteryDao;
    
    @Autowired
    private GenericDao genericDao;

    /**
     * Create a new cemetery.
     */
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute(new Cemetery());
        return "cemetery/edit";
    }

    /**
     * List all the cemeteries.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public @ResponseBody List<Cemetery> getList() {
        return genericDao.getAll(Cemetery.class, Order.asc("name"));
    }

    /**
     * Edit an existing cemetery.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{cemeteryId}/edit")
    public String getEditForm(@PathVariable int cemeteryId, Model model) {
        model.addAttribute(cemeteryDao.getCemetery(cemeteryId));
        return "cemetery/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "*/edit")
    public String save(@Valid Cemetery cemetery, BindingResult result) {
        if (result.hasErrors()) {
            return "cemetery/edit";
        }
        
        cemeteryDao.saveCemetery(cemetery);
        return "redirect:/cemetery/list";
    }

    /**
     * Submit a cemetery.
     */
    @RequestMapping(method = RequestMethod.POST, value = {"create"})
    public String create(@Valid Cemetery cemetery, BindingResult result) {
        /* Initial quick validation. */
        if (result.hasErrors()) {
            return "cemetery/edit";
        }
        
        /* Database validation. */
        if (genericDao.containsEntry(Cemetery.class, "name", cemetery.getName())) {
            result.rejectValue("name", "errors.entry_already_exists");
            return "cemetery/edit";
        }

        cemeteryDao.saveCemetery(cemetery);
        return "redirect:/cemetery/create";
    }
    
    /**
     * Upload people.
     */
    @RequestMapping(method = RequestMethod.GET, value = "upload")
    public String getUploadForm() {
        return "cemetery/upload";
    }

}
