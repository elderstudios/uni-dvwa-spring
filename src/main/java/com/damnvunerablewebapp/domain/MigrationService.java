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
        userInfoRepository.save(new UserInfo().setId(1).setEmail("huddersfield@elder.co.uk").setUsername("huddersfield").setBankBalance("£1000.00").setAdminNote("Really bad coder. Don't trust with security."));
        userInfoRepository.save(new UserInfo().setId(2).setEmail("admin@elder.co.uk").setUsername("admin").setBankBalance("£9001.00").setAdminNote("I'm the best."));
    }
}
