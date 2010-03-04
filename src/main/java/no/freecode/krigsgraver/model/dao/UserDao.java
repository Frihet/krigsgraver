/**
 *  Project: Krigsgraver
 *  Created: Mar 4, 2010
 *  Copyright: 2010, FreeCode AS
 *
 *  This file is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published
 *  by the Free Software Foundation; version 3.
 */
package no.freecode.krigsgraver.model.dao;

import java.util.List;

import no.freecode.krigsgraver.model.User;

/**
 * @author Reidar Øksnevad <reidar.oksnevad@freecode.no>
 *
 */
public interface UserDao {

    User getUser(int id);
    
    List<User> getUsers();

    void saveUser(User user);

    void deleteUser(User user);

}
