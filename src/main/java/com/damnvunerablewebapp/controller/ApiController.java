package com.damnvunerablewebapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import com.damnvunerablewebapp.domain.UserInfo;
import com.damnvunerablewebapp.domain.UserInfoRepository;

import java.security.MessageDigest;

/**
 * Created by rickwhalley on 02/03/2017.
 */

@RestController
@RequestMapping(value = "/api")
public class ApiController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping(value = "/{id}")
    public UserInfo getById(@PathVariable("id") int id){

        UserInfo userInfo = userInfoRepository.findOne(id);

        return userInfo;
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.POST)
    public UserInfo updateUserById(@PathVariable("id") int id){

        UserInfo userInfo = userInfoRepository.findOne(id);

        return userInfo;
    }
    
    /*
    @RequestMapping("user/view/{id}")
    public String view(@PathVariable("id") int id, Model model){
        model.addAttribute("userInfo", userInfoRepository.findOne(id));
        return "view/user";
    }

    
    @RequestMapping(value = "/search/name/{name}")
    public String getUserByName(@PathVariable String name, Model model) {
        UserInfo userInfo =  userInfoRepository.findOneByUsername(name);
        model.addAttribute("userInfo", userInfo);

        return "view/user";
    }
    

    @RequestMapping("user/view")
    public String view(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        
        UserInfo userInfo =  userInfoRepository.findOneByUsername(username);

        model.addAttribute("userInfo", userInfo);

        System.out.println(userInfo.getId());
        //System.out.println(userInfo.getLevel());

        return "view/user";
    }

    /*
    @RequestMapping(value = "/user/save", method = RequestMethod.POST)
    public String saved(@RequestParam Map<String, String> paramWrapper, final Model model) {
        try {
            System.out.println(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "404";
    }
    */
    /*
    @RequestMapping(value = "/api/data", method = RequestMethod.GET)
    public ResponseEntity saved(@RequestBody UserInfo userInfo) {
        //UserInfo userInfo = userService.save(userInfo);
        System.out.println(userInfo);
        return new ResponseEntity(userInfo, HttpStatus.OK);
    }
    */
}
