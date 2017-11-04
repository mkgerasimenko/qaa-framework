package com.github.emilshina.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AuthorizationTests extends BaseClass {

    @BeforeMethod
    public void beforeEachMethod() {
        System.out.println("Before method of Auth class.");
    }

    @AfterMethod
    public void afterEachMethod() {
        System.out.println("After method of Auth class.");
    }

    @Test
    public void userShouldBeAuthorizedWithValidCredentials() {
        System.out.println("User was authorized successfully.");
    }

    @Test
    public void userShouldNotBeAuthorizedWithInvalidLogin() {
        System.out.println("Invalid credentials.");
    }

    @Test
    public void userShouldNotBeAuthorizedWithInvalidPassword() {
        System.out.println("Invalid credentials. Try again.");
    }
}
