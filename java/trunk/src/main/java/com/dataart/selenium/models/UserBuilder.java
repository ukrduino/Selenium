package com.dataart.selenium.models;

import com.dataart.selenium.framework.Utils;


public class UserBuilder {

    public static User admin() {
        User user = new User("admin", "admin");
        user.setFname("Ivan");
        user.setLname("Petrov");
        return user;
    }

    public static User createUniqueUserWithRole(UserRoles role) {
        String nowDateKey = Utils.getStringCurrentDateTime();
        String username = "RS_"+ nowDateKey;
        String password = "1234";
        User user = new User(username, password);
        user.setFname("Sergey_" + nowDateKey);
        user.setLname("Romanyuk_"+ nowDateKey);
        user.setRole(role);
        System.out.println("New USER");
        System.out.println(user.getUsername());
        System.out.println(user.getFname());
        System.out.println(user.getLname());
        System.out.println(user.getPassword());
        System.out.println(user.getRole());
        return user;
    }




}
