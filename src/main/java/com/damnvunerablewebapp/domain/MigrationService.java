package com.damnvunerablewebapp.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by rickwhalley on 02/03/2017.
 */

@Service
public class MigrationService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public MigrationService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;
    }

    @PostConstruct
    private void init(){
        userInfoRepository.save(new UserInfo().setId(3).setLevel("User").setName("Carmun").setEmail("carmun@users.com").setUsername("user1").setBankBalance("£1001.00").setAdminNote("Really bad coder. Don't trust with security."));
        userInfoRepository.save(new UserInfo().setId(4).setLevel("User").setName("David").setEmail("david@users.com").setUsername("user2").setBankBalance("£1002.00").setAdminNote("Really bad coder 2."));
        userInfoRepository.save(new UserInfo().setId(5).setLevel("User").setName("Nicole").setEmail("nicole@users.com").setUsername("user3").setBankBalance("£1003.00").setAdminNote("Really bad coder 3."));
        userInfoRepository.save(new UserInfo().setId(1).setLevel("Administrator").setName("Jhon").setEmail("jhon@myadmin.com").setUsername("admin1").setBankBalance("£9001.00").setAdminNote("I'm the best 1."));
        userInfoRepository.save(new UserInfo().setId(2).setLevel("Administrator").setName("Evelin").setEmail("evelin@myadmin.com").setUsername("admin2").setBankBalance("£9002.00").setAdminNote("I'm the best 2."));
    }
}
