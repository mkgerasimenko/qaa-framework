package com.github.emilshina.testcases;

import io.github.sskorol.core.DataSupplier;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static com.github.emilshina.listeners.ReaderListener.readerOf;
import static java.util.Optional.ofNullable;

/**
 * Data suppliers' methods for tests.
 */

@Slf4j
public class DataSuppliers {

    @SuppressWarnings("unchecked")
    @DataSupplier(flatMap = true)
    public <T> StreamEx<T> getObject(final Method method) {
        return ofNullable(method.getDeclaredAnnotation(Data.class))
                .map(data -> StreamEx.of(readerOf(data.source())
                        .readFrom(data.source(), (Class<T>) data.entity(), (Class<T[]>) data.entityArr())))
                .orElseGet(StreamEx::empty);
    }

    @DataSupplier
    public String invalidPassword() {
        return "passwordInvalid";
    }

    @DataSupplier
    public Map<String, String> userCredentials() {
        final Map<String, String> credentials = new HashMap<>();
        credentials.put("0501234567", "password1");
        credentials.put("0671234567", "password2");
        credentials.put("0991234567", "password3");
        return credentials;
    }
}
