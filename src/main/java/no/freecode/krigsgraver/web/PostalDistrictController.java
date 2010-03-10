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

import java.io.IOException;

import no.freecode.krigsgraver.model.Camp;
import no.freecode.krigsgraver.model.PostalDistrict;
import no.freecode.krigsgraver.model.dao.PostalDistrictDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * Edit and display {@link Camp} objects.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Controller
@RequestMapping(value = "/postalDistrict")
public class PostalDistrictController {

    @Autowired
    private PostalDistrictDao postalDistrictDao;

    @Autowired
    private MessageSource messageSource;

    /**
     * Show a {@link PostalDistrict}.
     */
    @RequestMapping(method = RequestMethod.GET, value = {"{postcode}"})
    public @ResponseBody PostalDistrict getView(@PathVariable("postcode") int postcode) {
        return postalDistrictDao.get(postcode);
    }

    /**
     * Upload a batch of postal districts from posten.no's file format.
     * 
     * @throws IOException 
     */
    @RequestMapping(method = RequestMethod.POST, value = "upload")
    @Secured({"ROLE_ADMIN"})
    public String handleUpload(@RequestParam("file") MultipartFile file) throws IOException {

        if (!file.isEmpty()) {
            postalDistrictDao.loadCsvData(file.getInputStream());
            return "success";

        } else {
            return "redirect:uploadFailure";
        }
    }
}
