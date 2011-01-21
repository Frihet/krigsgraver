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
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import no.freecode.krigsgraver.model.Category;
import no.freecode.krigsgraver.model.Person;
import no.freecode.krigsgraver.model.dao.GenericDao;
import no.freecode.krigsgraver.util.WebUtils;

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
 * Edit and display {@link Category} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/category")
public class CategoryController {

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private MessageSource messageSource;

    @ModelAttribute("categories")
    public List<Category> getCategories() {
        return getList();
    }

    /**
     * List all the categories.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_PARTNER"})
    public List<Category> getList() {
        return genericDao.getAll(Category.class, Order.asc("name"));
    }
    
    /**
     * Create a new {@link Category}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "create")
    public String getCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/edit";
    }

    /**
     * Edit an existing {@link Category}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"{id}/edit"})
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("category", genericDao.get(Category.class, id));
        return "category/edit";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = {"edit"})
    public String getEditFormRedirect(@RequestParam("id") long id, Model model) {
        return "redirect:/category/" + id + "/edit";
    }
    
    /**
     * Submit a {@link Category}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.POST, value = {"create", "*/edit"})
    public String save(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model, HttpSession session, Locale locale) {

        if (result.hasErrors()) {
            return "category/edit";
        }

        /* Database validation. */
        if (category.getId() == null && genericDao.containsEntry(Category.class, "name", category.getName())) {
            result.rejectValue("name", "errors.entry_already_exists");
            return "category/edit";
        }

        genericDao.save(category);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { category.getName() }, locale);
        session.setAttribute("standardInfo", message);

//        return "redirect:/category/" + category.getId() + "/edit";
        return "redirect:/category/create";
    }

    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "merge")
    public String merge(@RequestParam("fromId") long fromId, @RequestParam("toId") long toId, 
                Model model, HttpSession session, Locale locale) {

        WebUtils.mergeEntity(fromId, toId, Category.class, "Person", "category", genericDao, messageSource,
                session, locale);

        return "redirect:/category/create";
    }
    
    /**
     * Delete a {@link Category}.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR"})
    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteObject(@RequestParam("id") long id, Model model, HttpSession session, Locale locale) {

        Category category = getObject(id);
        String name = category.getName();

        // Check if the element is in use to avoid a constraint violation.
        if (!genericDao.containsEntry(Person.class, "category", category)) {
            genericDao.delete(category);
            String message = messageSource.getMessage("info.deleted", new Object[] { name }, locale);
            session.setAttribute("standardInfo", message);

        } else {
            String message = messageSource.getMessage("errors.elementInUseCannotDelete", new Object[] { name }, locale);
            session.setAttribute("standardError", message);
        }

        return "redirect:/category/create";
    }
    
    /**
     * Get an {@link Category}, e.g. in JSON.
     */
    @Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_PARTNER"})
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody Category getObject(@PathVariable long id) {
        return genericDao.get(Category.class, id);
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(Category.class, new EntityPropertyEditor(Category.class, genericDao));
    }
}
