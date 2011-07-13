package com.tirtle.model;

import com.google.appengine.api.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.objectify.*;

/**
 * Provides class-level functions for RegisteredUser objects.
 */
@Component
public class RegisteredUserManager
{
    @Autowired private ObjectifyFactory objectifyFactory;
    
    public RegisteredUser getFromGaeUser(User user) {
        Objectify ofy = objectifyFactory.begin();
        RegisteredUser registeredUser = null;
        try {
            registeredUser = ofy.get(RegisteredUser.class, user.getUserId());
        } catch (NotFoundException exc) {
            // return null if RegisteredUser was not found.
        }
        
        return registeredUser;
    }
    
    public RegisteredUser createFromGaeUser(User user) {
        Objectify ofy = objectifyFactory.begin();
        RegisteredUser registeredUser = new RegisteredUser();
        registeredUser.setId(user.getUserId());
        registeredUser.setPublished(false);
        ofy.put(registeredUser);
        return registeredUser;
    }
    
    public RegisteredUser getOrCreateFromGaeUser(User user) {
        RegisteredUser registeredUser = getFromGaeUser(user);
        if (registeredUser == null) {
            registeredUser = createFromGaeUser(user);
        }
        return registeredUser;
    }
}