package com.github.emilshina.testcases;

import com.github.emilshina.data.DataReader;
import com.github.emilshina.model.User;
import com.github.emilshina.utils.ServiceLoaderUtils;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests for verification of Menu options.
 */
@Slf4j
public class MenuTests extends BaseClass {

    @BeforeMethod
    public void beforeEachMethod() {
        log.info("Before method of Menu class.");
    }

    @AfterMethod
    public void afterEachMethod() {
        log.info("After method of Menu class.");
    }

    @Test
    public void userCanOpenContactsPage() {
        Try.of(() -> StreamEx.of(ServiceLoaderUtils.load(DataReader.class, ClassLoader.getSystemClassLoader()))
                .findFirst(dataReader -> dataReader.dataType().equals("JSON"))
                .map(dataReader -> dataReader.readFrom("data.json", User.class, User[].class))
                .orElseThrow(() -> new IllegalArgumentException("Unable to find reader for such data type")))
                .onFailure(System.out::println);
        log.info("Contacts page was opened.");
    }

    @Data(source = "data.csv", dataType = "CSV")
    @Test
    public void userCanOpenAboutPage() {
        log.info("About page was opened.");
    }
}
