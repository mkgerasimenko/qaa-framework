package com.github.emilshina.utils;

import io.vavr.control.Try;
import one.util.streamex.StreamEx;

import java.util.Collections;
import java.util.List;
import java.util.ServiceLoader;

/**
 * Service loader for service provider interface.
 */

public final class ServiceLoaderUtils {

    private ServiceLoaderUtils() {
        throw new UnsupportedOperationException("Illegal access to private constructor.");
    }

    public static <T> List<T> load(final Class<T> type, final ClassLoader classLoader) {
        return Try.of(() -> StreamEx.of(ServiceLoader.load(type, classLoader).iterator()).toList())
                .getOrElseGet(ex -> Collections.emptyList());
    }
}
