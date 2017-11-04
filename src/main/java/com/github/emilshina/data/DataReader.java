package com.github.emilshina.data;

/**
 * Interface for read data from different sources.
 */

public interface DataReader {

    <T> T[] readFrom(String dataSource, Class<T> entityClass, Class<T[]> entityArrClass);

    String dataType();
}
