package com.github.emilshina.testcases;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseClass {
    @BeforeClass
    public void beforeMethod() {
        System.out.println("BASE_CLASS: Run before each class.");
    }

    @AfterClass
    public void afterMethod() {
        System.out.println("BASE_CLASS: Run after each class.");
    }
}
