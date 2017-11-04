package com.github.emilshina.data;

import com.github.emilshina.utils.JsonParserUtils;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;

/**
 * Class for reading data from json files.
 */

public class JsonReader implements DataReader {

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] readFrom(String dataSource, Class<T> entityClass, Class<T[]> entityArrClass) {
        final Gson gson = new Gson();
        final String path = ClassLoader.getSystemResource(dataSource).getPath();
        try (FileReader fileReader = new FileReader(path)) {
            final com.google.gson.stream.JsonReader reader = new com.google.gson.stream.JsonReader(fileReader);
            final JsonParser jsonParser = new JsonParser();
            final JsonElement json = jsonParser.parse(reader);
            return JsonParserUtils.fromJsonAsArray(gson, json, entityClass, entityArrClass);
        } catch (IOException e) {
            throw new IllegalArgumentException("Unable to read " + dataSource, e);
        }
    }

    @Override
    public String dataType() {
        return "json";
    }
}
