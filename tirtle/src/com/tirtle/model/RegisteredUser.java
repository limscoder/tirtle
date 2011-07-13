package com.tirtle.model;

import javax.persistence.Id;

import com.googlecode.objectify.annotation.Entity;

@Entity
public class RegisteredUser
{
    @Id private String id;
    private String label;
    private boolean published;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUrl() {
        return label;
    }
    
    public void setUrl(String label) {
        this.label = label;
    }
    
    public boolean isPublished() {
        return published;
    }
    
    public void setPublished(boolean published) {
        this.published = published;
    } 
}