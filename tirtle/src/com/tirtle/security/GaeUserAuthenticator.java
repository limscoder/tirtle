package com.tirtle.security;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tirtle.model.RegisteredUser;
import com.tirtle.model.RegisteredUserManager;

/**
 * Provides helper functions to deal with GAE user accounts.
 * 
 * @todo Should be using Spring Security, but I'm too lazy to set it up.
 */
@Component
public class GaeUserAuthenticator
{
    private static final String SIGN_UP_URL = "/login/";
    
    @Autowired private RegisteredUserManager registeredUserManager;
    
    /**
     * Return current GAE user.
     * 
     * @return User
     */
    public User getUser() {
        UserService userService = UserServiceFactory.getUserService();
        return userService.getCurrentUser();
    }
    
    /**
     * Return current RegisteredUser.
     * 
     * @return RegisteredUser
     */
    public RegisteredUser getRegisteredUser() {
        User user = getUser();
        if (user == null) {
            return null;
        }
        
        return registeredUserManager.getFromGaeUser(user);
    }
    
    /**
     * Return url to redirect user to signup page.
     * 
     * @return String
     */
    public String getRegistrationUrl() {
        return SIGN_UP_URL;
    }
    
    /**
     * Return url to redirect user to login page.
     * 
     * @param request HttpServletRequest
     * @return String
     */
    public String getLoginUrl(HttpServletRequest request) {
        UserService userService = UserServiceFactory.getUserService();
        String url = request.getRequestURI();
        String query = request.getQueryString();
        if (query != null) {
            url += '?' + query;
        }
        return userService.createLoginURL(url);
    }
}