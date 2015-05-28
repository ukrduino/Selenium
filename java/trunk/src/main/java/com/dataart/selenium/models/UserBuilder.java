package com.dataart.selenium.models;

import com.dataart.selenium.framework.Utils;


public class UserBuilder {

    public static User admin() {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("admin");
        user.setFname("Ivan");
        user.setLname("Petrov");
        return user;
    }

    public static User createUniqueUserWithRole(UserRoles role) {
        String nowDateKey = Utils.getStringCurrentDateTime();
        User user = new User();
        user.setUsername("RS_"+ nowDateKey);
        user.setPassword("1234");
        user.setFname("Sergey_" + nowDateKey);
        user.setLname("Romanyuk_"+ nowDateKey);
        user.setRole(role);
        return user;
    }




}
