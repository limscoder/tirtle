package com.tirtle.model;

import java.util.Date;

import javax.persistence.Id;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;

import com.tirtle.model.RegisteredUser;

@Entity
public class TirtleItem
{
    @Id private Long id;
    private Key<Tirtle> tirtleKey;
    private double value;
    private Date created;
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Key<Tirtle> getTirtleKey() {
        return tirtleKey;
    }
    
    public void setTirtleKey(Key<Tirtle> tirtleKey) {
        this.tirtleKey = tirtleKey;
    }
    
    public double getValue() {
        return value;
    }
    
    public void setValue(double value) {
        this.value = value;
    }
    
    public Date getCreated() {
        return created;
    }
    
    public void setCreated(Date created) {
        this.created = created;
    }
}