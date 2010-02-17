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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

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
//    public @ResponseBody Grave getGrave(@PathVariable long graveId) {
    public Grave getGrave(@PathVariable long graveId) {
        return graveDao.getGrave(graveId);
    }

    /**
     * Create a new grave.
     */
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute(new Grave());
        new WebRequestInterceptor() {
            
            @Override
            public void preHandle(WebRequest request) throws Exception {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void postHandle(WebRequest request, ModelMap model) throws Exception {
                // TODO Auto-generated method stub
                
            }
            
            @Override
            public void afterCompletion(WebRequest request, Exception ex) throws Exception {
                // TODO Auto-generated method stub
                
            }
        };
        return "grave/edit";
    }

    /**
     * Edit an existing grave.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{graveId}/edit")
    public String getEditForm(@PathVariable long graveId, Model model) {
        model.addAttribute(graveDao.getGrave(graveId));
        return "grave/edit";
    }

    @RequestMapping(method = RequestMethod.POST, value = "*/edit")
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
    public String create(@Valid Grave grave, BindingResult result) {
        if (result.hasErrors()) {
            return "grave/edit";
        }

        graveDao.saveGrave(grave);
        return "redirect:/grave/create";
    }
    
    /**
     * Upload people.
     */
    @RequestMapping(method = RequestMethod.GET, value = "upload")
    public String getUploadForm() {
        return "grave/upload";
    }

}
