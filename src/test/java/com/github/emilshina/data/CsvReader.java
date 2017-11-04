package com.github.emilshina.data;

import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.joor.Reflect.on;

/**
 * Class for reading data from csv files.
 */

@Slf4j
public class CsvReader implements DataReader {

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] readFrom(String dataSource, Class<T> entityClass, Class<T[]> entityArrClass) {
        final String path = ClassLoader.getSystemResource(dataSource).getPath();
        final List<T> objects;
        try (Stream<String> stream = Files.lines(Paths.get(path))) {
            objects = StreamEx.of(stream)
                    .skip(1)
                    .map(line -> line.split(";"))
                    .map(data -> getObject(entityClass, (Object[]) data))
                    .collect(Collectors.toList());
        } catch (IOException ex) {
            throw new IllegalArgumentException("Unable to read " + dataSource, ex);
        }
        return (T[]) objects.toArray();
    }

    @Override
    public String dataType() {
        return "csv";
    }

    private <T> T getObject(final Class<T> entityClass, final Object... args) {
        return on(entityClass).create(args).get();
    }
}
