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

import javax.validation.Valid;

import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.dao.GraveDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Edit and display Person objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/grave")
public class GraveController {

    @Autowired
    private GraveDao graveDao;


    /**
     * Create a new grave.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{graveId}")
    public Grave getGrave(@PathVariable long graveId) {
        return graveDao.getGrave(graveId);
    }

    /**
     * Create a new grave.
     */
    @RequestMapping(method = RequestMethod.GET, value = "create")
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    public String getCreateForm(Model model) {
        model.addAttribute(new Grave());
        return "grave/edit";
    }
    
//    /**
//     * Show a {@link Mmmm}.
//     */
//    @RequestMapping(method = RequestMethod.GET, value = {"{id}/view"})
//    public String getView(@PathVariable long id, Model model) {
//        model.addAttribute("mmmm", genericDao.get(Mmmm.class, id));
//        return "mmmm/view";
//    }

    /**
     * Edit an existing grave.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{graveId}/edit")
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    public String getEditForm(@PathVariable long graveId, Model model) {
        model.addAttribute(graveDao.getGrave(graveId));
        return "grave/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "*/edit")
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    public String save(@Valid Grave grave, BindingResult result) {
        if (result.hasErrors()) {
            return "grave/edit";
        }

        graveDao.saveGrave(grave);
        return "redirect:/grave/list";
//        return "redirect:/grave/" + grave.getId();
    }

    /**
     * Submit a grave.
     */
    @RequestMapping(method = RequestMethod.POST, value = {"create"})
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    public String create(@Valid Grave grave, BindingResult result) {
        if (result.hasErrors()) {
            return "grave/edit";
        }

        graveDao.saveGrave(grave);
        return "redirect:/grave/create";
    }
}
