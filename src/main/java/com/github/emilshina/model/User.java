package com.github.emilshina.model;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * The user model.
 */

@Data
public class User {

    private final long phoneNumber;
    private final String password;
    private final String userName;
    private final List<String> emails;

    public User(final String phone, final String pass, final String userName, final String emails) {
        this.phoneNumber = Long.parseLong(phone);
        this.password = pass;
        this.userName = userName;
        this.emails = Arrays.asList(emails.split(","));
    }

    public static User dummy() {
        return new User("380770000001L", "dummyPass",
                "dummy", "dummy@test.com");
    }
}
