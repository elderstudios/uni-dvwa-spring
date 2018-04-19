package com.damnvunerablewebapp.controller;

import com.damnvunerablewebapp.domain.UserInfo;
import com.damnvunerablewebapp.domain.UserInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by rickwhalley on 02/03/2017.
 */

@RestController
public class UserController {
    @Autowired
    private UserInfoRepository userInfoRepository;
    /*
    @RequestMapping("user/get/{id}")
    public String view(@PathVariable("id") int id, string model){
        //model.addAttribute("userInfo", userInfoRepository.findOne(id));
        return this.userInfoRepository.findOne(id);
    }

    /*
    

    @RequestMapping("user/view/{id}")
    public String view(@PathVariable("id") int id, Model model){
        model.addAttribute("userInfo", userInfoRepository.findOne(id));
        return "view/user";
    }

  

//    @RequestMapping("user/view")
//    public String view(Model model){
//
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        String username = auth.getName();
//
//        model.addAttribute("userInfo", userInfoRepository.findOneByUsername(username));
//
//        return "view/user";
//    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("admin/user/view")
    public String admin_view_all(Model model){
        model.addAttribute("userInfoList", userInfoRepository.findAll());
        for(UserInfo userInfo :userInfoRepository.findAll()){
            System.out.println(userInfo.getUsername());
        }
        return "view/admin_all_user";
    }

    @RequestMapping("admin/user/view/{id}")
    public String admin_view_one(@PathVariable("id") int id, Model model){
        model.addAttribute("userInfo", userInfoRepository.findOne(id));
        return "view/admin_one_user";
    }
    */

}

