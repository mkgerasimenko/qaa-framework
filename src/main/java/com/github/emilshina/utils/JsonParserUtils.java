package com.github.emilshina.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Array;

/**
 * Parser for json files for deserialization.
 */
public final class JsonParserUtils {

    private JsonParserUtils() {
        throw new UnsupportedOperationException("Illegal access to private constructor.");
    }

    @SuppressWarnings("unchecked")
    public static <T> T[] fromJsonAsArray(final Gson gson, final JsonElement json, final Class<T> tClass,
                                          final Class<T[]> tArrClass)
            throws JsonParseException {
        final T[] arr;
        if (json.isJsonObject()) {
            arr = (T[]) Array.newInstance(tClass, 1);
            arr[0] = gson.fromJson(json, tClass);
        } else if (json.isJsonArray()) {
            arr = gson.fromJson(json, tArrClass);
        } else {
            throw new IllegalArgumentException("Unexpected JSON type: " + json.getClass());
        }
        return arr;
    }
}
