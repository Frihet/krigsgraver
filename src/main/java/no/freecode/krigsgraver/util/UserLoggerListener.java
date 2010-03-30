/**
 *  Project: Krigsgraver
 *  Created: Mar 30, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.util;

import no.freecode.krigsgraver.model.User;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.util.ClassUtils;

/**
 * Log events
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
public class UserLoggerListener implements ApplicationListener<AbstractAuthenticationEvent> {

    private static final Logger logger = Logger.getLogger(UserLoggerListener.class);
    
    private static final Logger userLog = Logger.getLogger("userLog");
    
    /* (non-Javadoc)
     * @see org.springframework.context.ApplicationListener#onApplicationEvent(org.springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(AbstractAuthenticationEvent event) {
        if (event instanceof InteractiveAuthenticationSuccessEvent) {
            // Avoid duplicate logging.
            return;
        }

        if (logger.isInfoEnabled()) {
            if (event instanceof AuthenticationSuccessEvent) {
                Authentication auth = event.getAuthentication();
                Object principal = auth.getPrincipal();
                Object details = auth.getDetails();
                
                if (principal != null && principal.getClass() == User.class &&
                        details != null && details.getClass() == WebAuthenticationDetails.class) {
                    User user = (User) principal;
                    WebAuthenticationDetails webDetails = (WebAuthenticationDetails) details;
                    
                    userLog.info(webDetails.getRemoteAddress() + " Logged in: user=" + user.getUsername() + " (" + user.getName() + "), " + user.getRole() + ", sessionId=" + webDetails.getSessionId());
                }

            } else if (event instanceof AbstractAuthenticationFailureEvent) {
                final StringBuilder builder = new StringBuilder();
                builder.append("Authentication event ");
                builder.append(ClassUtils.getShortName(event.getClass()));
                builder.append(": ");
                builder.append(event.getAuthentication().getName());
                builder.append("; details: ");
                builder.append(event.getAuthentication().getDetails());
                builder.append("; exception: ");
                builder.append(((AbstractAuthenticationFailureEvent) event).getException().getMessage());
                logger.warn(builder.toString());
            }
        }
    }

}
