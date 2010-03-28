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

import java.util.Locale;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import no.freecode.krigsgraver.model.User;
import no.freecode.krigsgraver.model.dao.GenericDao;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Edit and display {@link User} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@Secured({"ROLE_ADMIN", "ROLE_EDITOR", "ROLE_PARTNER"})
public class ProfileController {

    @Autowired
    private GenericDao genericDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MessageSource messageSource;

    
    /**
     * Edit own user profile.
     */
    @RequestMapping(method = RequestMethod.GET, value = {"/user/editProfile"})
    public String editProfile(Model model) {
        return "user/editProfile";
    }

    /**
     * Submit a {@link User}.
     */
    @RequestMapping(method = RequestMethod.POST, value = {"/user/editProfile"})
    public String editProfileSubmit(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, HttpSession session, Locale locale) {

        User dbUser = genericDao.get(User.class, getOwnUser().getId());
        
        if (result.hasErrors()) {
            return "user/editProfile";
        }

        // Use old password if the password field has been left blank.
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(dbUser.getPassword());

        } else {
            if (user.getPassword().length() < 6) {
                result.rejectValue("password", "user.error.passwordTooShort");
                return "user/editProfile";
            }

            // Encode the new password before saving.
            user.setPassword(passwordEncoder.encodePassword(user.getPassword(), null));
        }

        user.setId(getOwnUser().getId());  // probably not necessary, but just to be sure this won't be a security issue
        genericDao.save(user);

        String message = messageSource.getMessage("info.successfullySaved",
                new Object[] { user.getUsername() }, locale);
        session.setAttribute("standardInfo", message);

        return "redirect:/user/editProfile";
    }

    @ModelAttribute("user")
    public User getUser() {
//        return genericDao.get(User.class, getOwnUser().getId());
        return getOwnUser();
    }

    private User getOwnUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(User.class, new EntityPropertyEditor(User.class, genericDao));
    }
}
