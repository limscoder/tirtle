package com.tirtle.model;

import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

import com.tirtle.model.RegisteredUser;

@Entity
public class Tirtle
{
    @Id private Long id;
    private Key<RegisteredUser> userKey;
    private String label;
    private Date created;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Key<RegisteredUser> getUserKey() {
        return userKey;
    }
    
    public void setUserKey(Key<RegisteredUser> userKey) {
        this.userKey = userKey;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isOwner(RegisteredUser user) {
        if (userKey.equals(new Key<RegisteredUser>(RegisteredUser.class, user.getId()))) {
            return true;
        } else {
            return false;
        }
    }
}