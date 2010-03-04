/**
 *  Project: GreenCycle Lite
 *  Created: Nov 26, 2009
 *  Copyright: 2009, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
@Controller
@org.springframework.security.access.annotation.Secured("ROLE_ADMIN")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);

//    public enum Action { edit_user, new_user }
//
//    @Autowired
//    private UserDao userDao;
//
//    @Autowired
//    private LocationDao locationDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//    
//    @Autowired
//    private MessageSource messageSource;
//    
//    /**
//     * Present a list of the users on the system.
//     */
//    public static final String URL_LIST_USERS = "/users/list.do";
//    @RequestMapping({URL_LIST_USERS})
//    public String listUsers(
//                @RequestParam(value = "page", required = false) Integer page,
//                ModelMap model) {
//
//        Paginator paginator = new Paginator(page);
//        List<User> users = userDao.getUsers(paginator);
//        model.addAttribute("paginator", paginator);
//        model.addAttribute("users", users);
//        return "listUsers";
//    }
//
//    /**
//     * Edit any user on the system.
//     */
//    public static final String URL_EDIT_ANY_USER = "/users/edit.do";
//    @RequestMapping(method = RequestMethod.GET, value = URL_EDIT_ANY_USER)
//    public String editAnyUserGet(
//                @RequestParam(value = "action", required = false) Action action,
//                @RequestParam(value = "id", required = false) User user,
//                ModelMap model) {
//
//        if (action == Action.new_user) {
//            user = new User();
//        }
//
//        model.addAttribute("user", user);
//        return "editUser";
//    }
//
//    /**
//     * Save a user.
//     */
//    @RequestMapping(method = RequestMethod.POST, value = URL_EDIT_ANY_USER)
//    public String editAnyUserPost(
//                @ModelAttribute("user") User user,
//                BindingResult result,
//                HttpSession session,
//                ModelMap model,
//                Locale locale) {
//        
//        logger.debug("locale=" + locale);
//
//        // Run validation checks on the submitted input.
//        new UserValidator().validate(user, result);
//
//        if (result.hasErrors()) {
//            model.addAttribute("user", user);
//            return "editUser";
//
//        } else {
//            // Use old password if the password field has been left blank.
//            if (!user.isNew() && StringUtils.isEmpty(user.getPassword())) {
//                User u = userDao.getUser(user.getId());
//                if (u != null) {
//                    user.setPassword(u.getPassword());
//                }
//                
//            } else {
//                // Encode the new password before saving.
//                String encodedPassword = passwordEncoder.encodePassword(user.getPassword(), null);
//                user.setPassword(encodedPassword);
//            }
//
//            userDao.saveUser(user);
//
//            // Create a success-message informing who received an e-mail, etc.
//            String message = messageSource.getMessage("admin.users.successfully_saved_user",
//                    new Object[] { user.getUsername() }, locale);
//            session.setAttribute("standardInfo", message);
//
//            return "redirect:list.do";
//        }
//    }
//
//    @ModelAttribute("newUser")
//    public User getNewUser() {
//        return new User();
//    }
//
//    @ModelAttribute("senders")
//    public Collection<Sender> getSenders() {
//        return locationDao.getLocations(Sender.class);
//    }
//
//    @ModelAttribute("transporters")
//    public Collection<Transporter> getTransporters() {
//        return locationDao.getLocations(Transporter.class);
//    }
//    
//    @ModelAttribute("receivers")
//    public Collection<Receiver> getReceivers() {
//        return locationDao.getLocations(Receiver.class);
//    }
//
//    @ModelAttribute("roles")
//    public Collection<Role> getRoles() {
//        ArrayList<Role> roles = new ArrayList<Role>();
//        roles.add(Role.ROLE_SENDER);
//        roles.add(Role.ROLE_RECEIVER);
//        roles.add(Role.ROLE_ADMIN);
//        return roles;
//    }
//    
//    @InitBinder
//    public void initBinder(WebDataBinder dataBinder) {
//        // FIXME: There's a bit of duplicate code here. This can be done with a lot less code.
//
//        // Create a custom property editor for users (mapping an id string to a User from the db).
//        dataBinder.registerCustomEditor(User.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                setValue(userDao.getUser(Integer.parseInt(text)));
//            }
//
//            @Override
//            public String getAsText() {
//                User u = (User) getValue();
//                if (u == null) {
//                    return null;
//                } else {
//                    return String.valueOf(u.getId());
//                }
//            }
//        });
//
//        // Create a custom property editor for Senders (mapping an id string to a Sender from the db).
//        dataBinder.registerCustomEditor(Sender.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                if ("null".equals(text)) {
//                    setValue(null);
//                } else {
//                    setValue(locationDao.getLocation(Sender.class, Integer.parseInt(text)));
//                }
//            }
//
//            @Override
//            public String getAsText() {
//                Sender s = (Sender) getValue();
//                if (s == null) {
//                    return null;
//                } else {
//                    return String.valueOf(s.getId());
//                }
//            }
//        });
//        
//        // Create a custom property editor for Transporters (mapping an id string to a transporter from the db).
//        dataBinder.registerCustomEditor(Transporter.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                if ("null".equals(text)) {
//                    setValue(null);
//                } else {
//                    setValue(locationDao.getTransporter(Integer.parseInt(text)));
//                }
//            }
//            
//            @Override
//            public String getAsText() {
//                Transporter t = (Transporter) getValue();
//                if (t == null) {
//                    return null;
//                } else {
//                    return String.valueOf(t.getId());
//                }
//            }
//        });
//
//        // Create a custom property editor for Receivers (mapping an id string to a receiver from the db).
//        dataBinder.registerCustomEditor(Receiver.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) throws IllegalArgumentException {
//                if ("null".equals(text)) {
//                    setValue(null);
//                } else {
//                    setValue(locationDao.getReceiver(Integer.parseInt(text)));
//                }
//            }
//
//            @Override
//            public String getAsText() {
//                Receiver t = (Receiver) getValue();
//                if (t == null) {
//                    return null;
//                } else {
//                    return String.valueOf(t.getId());
//                }
//            }
//        });
//    }
//
//
//    /**
//     * Edit own user.
//     */
//    public static final String URL_EDIT_OWN_USER = "/users/editOwnProfile.do";
//    @RequestMapping({URL_EDIT_OWN_USER})
//    @Secured({"ROLE_ADMIN", "ROLE_SENDER", "ROLE_TRANSPORTER", "ROLE_RECEIVER"})
//    public String editOwnUser() {
//        return "editUser";
//    }
}
