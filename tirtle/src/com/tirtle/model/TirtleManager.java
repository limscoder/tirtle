package com.tirtle.model;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;

import com.tirtle.form.CreateTirtleForm;

@Component
public class TirtleManager
{
    @Autowired private ObjectifyFactory objectifyFactory;
    
    public Query<Tirtle> filterFromRegisteredUser(RegisteredUser user) {
        Objectify ofy = objectifyFactory.begin();
        return ofy.query(Tirtle.class).filter("userKey", user).order("-created");
    }
    
    public Tirtle createFromForm(RegisteredUser user, CreateTirtleForm form) {
        Objectify ofy = objectifyFactory.begin();
        Tirtle tirtle = new Tirtle();
        tirtle.setUserKey(new Key<RegisteredUser>(RegisteredUser.class, user.getId()));
        tirtle.setLabel(form.getLabel());
        tirtle.setCreated(new Date());
        ofy.put(tirtle);
        return tirtle;
    }
    
    public Tirtle getById(Long tirtleId) {
        Objectify ofy = objectifyFactory.begin();
        
        Tirtle tirtle = null;
        try {
            tirtle = ofy.get(Tirtle.class, tirtleId);
        } catch (NotFoundException exc) {
            // return null if tirtle was not found.
        }
        return tirtle;
    }
    
    public void delete(Tirtle tirtle) {
        Objectify ofy = objectifyFactory.begin();
        ofy.delete(tirtle);
    }
}