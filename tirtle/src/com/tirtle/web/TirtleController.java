package com.tirtle.web;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.validation.BindingResult;

import com.googlecode.objectify.*;

import com.tirtle.form.CreateTirtleForm;
import com.tirtle.form.SetTirtleForm;
import com.tirtle.model.*;
import com.tirtle.security.GaeUserAuthenticator;

@Controller
@RequestMapping("/")
public class TirtleController
{
    @Autowired private GaeUserAuthenticator gaeUserAuth;
    @Autowired private TirtleManager tirtleManager;
    @Autowired private TirtleItemManager tirtleItemManager;
    
    /**
     * Displays a list of all tirtles belonging to a user.
     * 
     * @return String
     */
    @RequestMapping(value="/tirtles/", method=RequestMethod.GET)
    public String tirtles(Model model) {
        RegisteredUser registeredUser = gaeUserAuth.getRegisteredUser();
        if (registeredUser == null) {
            return "redirect:" + gaeUserAuth.getRegistrationUrl();
        }
        
        return userTirtles(registeredUser, model);
    }
    
    @RequestMapping(value="/create/", method=RequestMethod.GET)
    public String createGet(Model model) {
        RegisteredUser registeredUser = gaeUserAuth.getRegisteredUser();
        if (registeredUser == null) {
            return "redirect:" + gaeUserAuth.getRegistrationUrl();
        }
        
        model.addAttribute("createTirtleForm", new CreateTirtleForm());
        return "tirtle/create";
    }
    
    @RequestMapping(value="/create/", method=RequestMethod.POST)
    public String createPost(@Valid CreateTirtleForm createTirtleForm, BindingResult result) {
        RegisteredUser registeredUser = gaeUserAuth.getRegisteredUser();
        if (registeredUser == null) {
            return "redirect:" + gaeUserAuth.getRegistrationUrl();
        }
        
        if (result.hasErrors()) {
            return "tirtle/create";
        }
        
        tirtleManager.createFromForm(registeredUser, createTirtleForm);
        return "redirect:/tirtles/";
    }
    
    @RequestMapping(value="/delete/{tirtleId}/", method=RequestMethod.GET)
    public String deleteGet(@PathVariable Long tirtleId, Model model) {
        Tirtle tirtle = tirtleManager.getById(tirtleId);
        
        RegisteredUser registeredUser = gaeUserAuth.getRegisteredUser();
        if (registeredUser == null || tirtle == null || tirtle.isOwner(registeredUser) == false) {
            return "redirect:" + gaeUserAuth.getRegistrationUrl();
        }
        
        model.addAttribute("tirtle", tirtle);
        return "tirtle/delete";
    }
    
    @RequestMapping(value="/delete/{tirtleId}/", method=RequestMethod.POST)
    public String deletePost(@PathVariable Long tirtleId) {
        Tirtle tirtle = tirtleManager.getById(tirtleId);
        
        RegisteredUser registeredUser = gaeUserAuth.getRegisteredUser();
        if (registeredUser == null || tirtle == null || tirtle.isOwner(registeredUser) == false) {
            return "redirect:" + gaeUserAuth.getRegistrationUrl();
        }
        
        tirtleManager.delete(tirtle);
        return "redirect:/tirtles/";
    }
    
    @RequestMapping(value="/set/{tirtleId}/", method=RequestMethod.GET)
    public String setGet(@PathVariable Long tirtleId, Model model) {
        Tirtle tirtle = tirtleManager.getById(tirtleId);
        
        RegisteredUser registeredUser = gaeUserAuth.getRegisteredUser();
        if (registeredUser == null || tirtle == null || tirtle.isOwner(registeredUser) == false) {
            return "redirect:" + gaeUserAuth.getRegistrationUrl();
        }
        
        model.addAttribute("setTirtleForm", new SetTirtleForm());
        model.addAttribute("tirtle", tirtle);
        return "tirtle/set";
    }
    
    @RequestMapping(value="/set/{tirtleId}/", method=RequestMethod.POST)
    public String setPost(@PathVariable Long tirtleId, @Valid SetTirtleForm setTirtleForm, BindingResult result) {
        if (result.hasErrors()) {
            return "tirtle/set";
        }
        
        Tirtle tirtle = tirtleManager.getById(tirtleId);
        RegisteredUser registeredUser = gaeUserAuth.getRegisteredUser();
        if (registeredUser == null || tirtle == null || tirtle.isOwner(registeredUser) == false) {
            return "redirect:" + gaeUserAuth.getRegistrationUrl();
        }
        
        tirtleItemManager.createFromForm(tirtle, setTirtleForm);
        return "redirect:/tirtles/";
    }
    
    private String userTirtles(RegisteredUser user, Model model) {
        // Get list of all tirtles
        Query<Tirtle> q = tirtleManager.filterFromRegisteredUser(user);
        
        // If logged in user == registeredUser, then show tirtle edit links.
        RegisteredUser currentUser = gaeUserAuth.getRegisteredUser();
        if (currentUser != null && currentUser.getId().equals(user.getId())) {
            if (q.count() < 1) {
                // Redirect user to create page.
                return "redirect:/create/";
            }
            
            model.addAttribute("editable", true);
        } else {
            model.addAttribute("editable", false);
        }
        
        List<Map<String, Object>> tirtles = new LinkedList<Map<String, Object>>();
        
        for(Tirtle tirtle : q) {
            Map<String, Object> tirtleInfo = new HashMap<String, Object>();
            tirtleInfo.put("tirtle", tirtle);
            tirtleInfo.put("lastTirtleItem", tirtleItemManager.getLastTirtleItem(tirtle));
            tirtles.add(tirtleInfo);
        }
        
        model.addAttribute("tirtles", tirtles);
        model.addAttribute("tirtleCount", tirtles.size());
        return "tirtle/tirtleList";
    }
}