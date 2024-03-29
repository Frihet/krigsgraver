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

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import no.freecode.krigsgraver.model.Cemetery;
import no.freecode.krigsgraver.model.Grave;
import no.freecode.krigsgraver.model.PostalDistrict;
import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.model.dao.PostalDistrictDao;
import no.freecode.krigsgraver.util.GeoUtils;
import no.freecode.krigsgraver.util.WebUtils;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Edit and display {@link Cemetery} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/cemetery")
public class CemeteryController {

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private PostalDistrictDao postalDistrictDao;
    
    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("cemeteries")
    public List<Cemetery> getCemeteries() {
        return getList();
    }

    /**
     * List all the cemeteries.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_PARTNER"})
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public List<Cemetery> getList() {
        return genericDao.getAll(Cemetery.class, Order.asc("name"));
    }

//    /**
//     * Show a {@link Cemetery}.
//     */
//    @RequestMapping(method = RequestMethod.GET, value = {"{id}/view"})
//    public @ResponseBody Cemetery getView(@PathVariable long id) { //, Model model) {
//        return genericDao.get(Cemetery.class, id);
//    }
    
    /**
     * Create a new {@link Cemetery}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("cemetery", new Cemetery());
        return "cemetery/edit";
    }

    /**
     * Edit an existing {@link Cemetery}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"{id}/edit"})
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("cemetery", genericDao.get(Cemetery.class, id));
        return "cemetery/edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"edit"})
    public String getEditFormRedirect(@RequestParam("id") long id, Model model) {
        return "redirect:/cemetery/" + id + "/edit";
    }
    
    /**
     * Submit a {@link Cemetery}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("cemetery") Cemetery cemetery, BindingResult result, Model model, HttpSession session, Locale locale) {

        GeoUtils.validateGeolocational(result, cemetery);

        if (result.hasErrors()) {
            return "cemetery/edit";
        }

        /* Database validation. */
        if (cemetery.getId() == null && genericDao.containsEntry(Cemetery.class, "name", cemetery.getName())) {
            result.rejectValue("name", "errors.entry_already_exists");
            return "cemetery/edit";
        }

        genericDao.save(cemetery);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { cemetery.getName() }, locale);
        session.setAttribute("standardInfo", message);

//        return "redirect:/cemetery/" + cemetery.getId() + "/edit";
        return "redirect:/cemetery/create";
    }

    /**
     * Delete a {@link Cemetery}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteObject(@RequestParam("id") long id, Model model, HttpSession session, Locale locale) {

        Cemetery cemetery = getObject(id);
        String name = cemetery.getName();

        // Check if the element is in use to avoid a constraint violation.
        if (!genericDao.containsEntry(Grave.class, "cemetery", cemetery)) {
            genericDao.delete(cemetery);
            String message = messageSource.getMessage("info.deleted", new Object[] { name }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            String message = messageSource.getMessage("errors.elementInUseCannotDelete", new Object[] { name }, locale);
            session.setAttribute("standardError", message);
        }

        return "redirect:/cemetery/create";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "merge")
    public String merge(@RequestParam("fromId") long fromId, @RequestParam("toId") long toId, 
                Model model, HttpSession session, Locale locale) {

        WebUtils.mergeEntity(fromId, toId, Cemetery.class, "Grave", "cemetery", genericDao, messageSource,
                session, locale);

        return "redirect:/cemetery/create";
    }

    
    /**
     * Get an {@link Cemetery}, e.g. in JSON.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_PARTNER"})
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody Cemetery getObject(@PathVariable long id) {
        return genericDao.get(Cemetery.class, id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Cemetery.class, new EntityPropertyEditor(Cemetery.class, genericDao));
        
        // Create a custom property editor for PostalDistrict objects (mapping a postcode to an object).
        dataBinder.registerCustomEditor(PostalDistrict.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                if (StringUtils.isNotBlank(text)) {
                    setValue(postalDistrictDao.get(Integer.parseInt(text)));
                }
            }

            @Override
            public String getAsText() {
                PostalDistrict d = (PostalDistrict) getValue();
                if (d == null) {
                    return null;
                } else {
                    return d.getPostcodeString();
                }
            }
        });

    }
}
