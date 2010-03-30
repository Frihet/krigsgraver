/**
 *  Project: Krigsgraver
 *  Created: Feb 5, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.apache.log4j.Logger;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * A user that can log on the system.
 * 
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 */
@Entity(name = "KgUser")
public class User extends BaseEntity implements UserDetails {

    private static final Logger logger = Logger.getLogger(User.class);
    private static final long serialVersionUID = 1L;

    @Column(unique = true)
    @Size(max = 255)
    @NotEmpty
    private String username;

    @Size(max = 255)
    private String password;

    @Size(max = 255)
    @NotEmpty
    private String name;
    
    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_EDITOR;

    private boolean credentialsNonExpired = true;

    private boolean enabled = true;


    /** Empty constructor. Needed for Hibernate. */
    public User() { }
    

    public void setUsername(String username) {
        this.username = username;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.userdetails.UserDetails#getUsername()
     */
    @Override
    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.userdetails.UserDetails#getPassword()
     */
    @Override
    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
     */
    @Transient
    @Override
    public Collection<GrantedAuthority> getAuthorities() {
         Role singleRole = getRole();
         ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        if (singleRole != null) {
            authorities.add(new GrantedAuthorityImpl(singleRole.name()));
        }
        
        return authorities;
    }    

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.userdetails.UserDetails#isAccountNonExpired
     * ()
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.userdetails.UserDetails#isAccountNonLocked()
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.security.userdetails.UserDetails#isCredentialsNonExpired
     * ()
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }


    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.security.userdetails.UserDetails#isEnabled()
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    /**
     * Define the available roles.
     */
    public enum Role {
        ROLE_ADMIN("role.admin"),
        ROLE_EDITOR("role.editor"),
        ROLE_PARTNER("role.partner");

        private String descriptionId;

        private Role(String descriptionId) {
            this.descriptionId = descriptionId;
        }

        /**
         * @return The message id for the role (for getting the localized
         *         message).
         */
        public String getDescriptionId() {
            return descriptionId;
        }
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return getName() + " (" + getUsername() + ")";
    }
}
