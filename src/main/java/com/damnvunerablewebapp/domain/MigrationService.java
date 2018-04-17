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
        userInfoRepository.save(new UserInfo().setId(2).setEmail("user1@numisec.com").setUsername("user1").setBankBalance("£1001.00").setAdminNote("Really bad coder. Don't trust with security."));
        userInfoRepository.save(new UserInfo().setId(3).setEmail("user2@numisec.com").setUsername("user2").setBankBalance("£1002.00").setAdminNote("Really bad coder."));
        userInfoRepository.save(new UserInfo().setId(1).setEmail("admin1@numisec.com").setUsername("admin1").setBankBalance("£9001.00").setAdminNote("I'm the best1."));
        userInfoRepository.save(new UserInfo().setId(2).setEmail("admin2@numisec.com").setUsername("admin2").setBankBalance("£9002.00").setAdminNote("I'm the best2."));
    }
}
