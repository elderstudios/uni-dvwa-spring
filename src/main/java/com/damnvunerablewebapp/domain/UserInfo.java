package com.damnvunerablewebapp.domain;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by rickwhalley on 02/03/2017.
 */

@Data
@Entity
@Accessors(chain = true)
public class UserInfo {

    @Id
    private Integer id;
    private String level;
    private String name;
    private String username;
    private String email;
    private String bankBalance;
    private String adminNote;

    public Integer getId() {
        return this.id;
    }

    public String getLevel() {
        return level;
    }

}
