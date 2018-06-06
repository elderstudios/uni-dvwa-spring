package com.damnvunerablewebapp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

import com.damnvunerablewebapp.domain.UserInfo;
import com.damnvunerablewebapp.domain.UserInfoRepository;

import java.security.MessageDigest;

/**
 * Created by rickwhalley on 02/03/2017.
 */

@Controller
public class UserController {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @RequestMapping("user/view/{id}")
    public String view(@PathVariable("id") int id, Model model){
        model.addAttribute("userInfo", userInfoRepository.findOne(id));
        return "view/user";
    }

    @RequestMapping(value = "/search")
    public String getUserByName(HttpServletRequest request, Model model) {
        System.out.println(request.getParameter("name"));
        String nameed = request.getParameter("name");
        UserInfo userInfo =  userInfoRepository.findOneByUsername(nameed);
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
