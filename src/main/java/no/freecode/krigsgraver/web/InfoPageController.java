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

import no.freecode.krigsgraver.model.InfoPage;
import no.freecode.krigsgraver.model.Rank;
import no.freecode.krigsgraver.model.dao.InfoPageDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Edit and display {@link Rank} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/info")
public class InfoPageController {

    @Autowired
    private InfoPageDao infoPageDao;

    @Autowired
    private MessageSource messageSource;


    /**
     * Show or edit an info page.
     */
    @RequestMapping(method = RequestMethod.GET, value = "{pageName}")
    public String getInfoPage(@PathVariable String pageName, Model model) {
        model.addAttribute("infoPage", getInfoPage(pageName));
        return "infoPage/showOrEdit";
    }

    
    /**
     * Submit an info page.
     */
    @Secured({"ROLE_ADMIN"})
    @RequestMapping(method = RequestMethod.POST, value = {"{pageName}"})
    public String save(@RequestParam(value = "pageBody", required = true) String pageBody, @PathVariable String pageName, HttpSession session, Locale locale) {

//        if (result.hasErrors()) {
//            return "infoPage/showOrEdit";
//        }

        InfoPage infoPage = getInfoPage(pageName);
        infoPage.setHtml(pageBody);

        infoPageDao.saveInfoPage(infoPage);

        String message = messageSource.getMessage("infoPage.successfullySaved", null, locale);
        session.setAttribute("standardInfo", message);

        return "redirect:/info/" + pageName;
    }

    /**
     * Get an {@link InfoPage}, or create a new one if it hasn't been created before.
     * 
     * @param pageName
     * @return
     */
    private InfoPage getInfoPage(String pageName) {
        InfoPage infoPage = infoPageDao.getInfoPage(pageName);
        if (infoPage == null) {
            infoPage = new InfoPage();
            infoPage.setPageName(pageName);
        }
        return infoPage;
    }


//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        dataBinder.registerCustomEditor(Rank.class, new EntityPropertyEditor(InfoPage.class, genericDao));
//    }
}
