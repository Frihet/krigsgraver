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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import no.freecode.krigsgraver.model.User;
import no.freecode.krigsgraver.model.User.Role;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.PasswordEncoder;
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
 * Edit and display {@link User} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@Secured({"ROLE_ADMIN"})
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;


    @ModelAttribute("users")
    public List<User> getUsers() {
        return getList();
    }

    /**
     * List all the users.
     */
    @RequestMapping(method = RequestMethod.GET, value = "list")
    public List<User> getList() {
        return genericDao.getAll(User.class, Order.asc("username"));
    }

    /**
     * Create a new {@link User}.
     */
    @RequestMapping(method = RequestMethod.GET, value = {"create", ""})
    public String getCreateForm(Model model) {
        model.addAttribute("user", new User());
        return "user/edit";
    }

    /**
     * Edit an existing {@link User}.
     */
    @RequestMapping(method = RequestMethod.GET, value = {"{id}/edit"})
    public String getEditForm(@PathVariable long id, Model model) {
        model.addAttribute("user", genericDao.get(User.class, id));
        return "user/edit";
    }

    @RequestMapping(method = RequestMethod.GET, value = {"edit"})
    public String getEditFormRedirect(@RequestParam("id") long id, Model model) {
        return "redirect:/admin/user/" + id + "/edit";
    }

    /**
     * Submit a {@link User}.
     */
    @RequestMapping(method = RequestMethod.POST, value = {"create", "", "*/edit"})
    public String save(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session, Locale locale) {
        
        if (result.hasErrors()) {
            return "user/edit";
        }

        // Database validation.
        if (user.getId() == null && genericDao.containsEntry(User.class, "username", user.getUsername())) {
            result.rejectValue("username", "errors.entry_already_exists");
            return "user/edit";
        }

        // Use old password if the password field has been left blank.
        if (user.getId() != null && StringUtils.isBlank(user.getPassword())) {
            User u = genericDao.get(User.class, user.getId());
            user.setPassword(u.getPassword());

        } else {
            // Encode the new password before saving.
            user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        }

        genericDao.save(user);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { user.getUsername() }, locale);
        session.setAttribute("standardInfo", message);

        return "redirect:/admin/user/create";
    }

    /**
     * Delete a {@link User}.
     */
    @RequestMapping(method = RequestMethod.GET, value = "delete")
    public String deleteObject(@RequestParam("id") long id, Model model, HttpSession session, Locale locale) {

        User user = getObject(id);
        String name = user.getName();

        genericDao.delete(user);
        String message = messageSource.getMessage("info.deleted", new Object[] { name }, locale);
        session.setAttribute("standardInfo", message);

        return "redirect:/admin/user/create";
    }

    /**
     * Get a {@link User}, e.g. in JSON.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public @ResponseBody User getObject(@PathVariable long id) {
        return genericDao.get(User.class, id);
    }

    @ModelAttribute("roles")
    public Collection<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(Role.ROLE_EDITOR);
        roles.add(Role.ROLE_ADMIN);
        roles.add(Role.ROLE_PARTNER);
        return roles;
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(User.class, new EntityPropertyEditor(User.class, genericDao));
    }
}
