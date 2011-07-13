package com.tirtle.model;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.NotFoundException;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyFactory;
import com.googlecode.objectify.Query;

import com.tirtle.form.SetTirtleForm;

@Component
public class TirtleItemManager
{
    @Autowired private ObjectifyFactory objectifyFactory;
    
    public TirtleItem getLastTirtleItem(Tirtle tirtle) {
        Objectify ofy = objectifyFactory.begin();
        
        TirtleItem tirtleItem = null;
        Query<TirtleItem> q = ofy.query(TirtleItem.class).
                                  filter("tirtleKey", tirtle).
                                  order("-created").
                                  limit(1);
        if (q.count() > 0) {
            TirtleItem firstItem = q.iterator().next();
            
            // Determine if last tirtle was created today
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());
            Calendar then = Calendar.getInstance();
            then.setTime(firstItem.getCreated());
            
            if (now.get(Calendar.YEAR) == then.get(Calendar.YEAR) &&
                now.get(Calendar.MONTH) == then.get(Calendar.MONTH) &&
                now.get(Calendar.DAY_OF_MONTH) == then.get(Calendar.DAY_OF_MONTH)) {
                tirtleItem = firstItem;
            }
        }
        
        return tirtleItem;
    }
    
    public TirtleItem createFromForm(Tirtle tirtle, SetTirtleForm form) {
        Objectify ofy = objectifyFactory.begin();
        TirtleItem tirtleItem = new TirtleItem();
        tirtleItem.setTirtleKey(new Key<Tirtle>(Tirtle.class, tirtle.getId()));
        tirtleItem.setValue(form.getValue());
        tirtleItem.setCreated(new Date());
        ofy.put(tirtleItem);
        return tirtleItem;
    }
}