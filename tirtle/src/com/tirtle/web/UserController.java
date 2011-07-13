package com.tirtle.web;

import javax.servlet.http.HttpServletRequest;

import com.google.appengine.api.users.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tirtle.model.*;
import com.tirtle.security.GaeUserAuthenticator;

@Controller
@RequestMapping("/")
public class UserController
{
    @Autowired private GaeUserAuthenticator gaeUserAuth;
    @Autowired private RegisteredUserManager registeredUserManager;
    
    /**
     * Redirect to user's home page, signup screen, or login page.
     * 
     * @param request HttpServletRequest
     * @return String
     */
    @RequestMapping(value="/login/", method=RequestMethod.GET)
    public String login(HttpServletRequest request) {
        User user = gaeUserAuth.getUser();
        if (user == null) {
            // User is not logged in!
            // Redirect user to Google login page.
            return "redirect:" + gaeUserAuth.getLoginUrl(request);
        }
        
        // Register user if they are not already registered.
        registeredUserManager.getOrCreateFromGaeUser(user);
        
        // Redirect user to home page.
        return "redirect:/tirtles/";
    }
}