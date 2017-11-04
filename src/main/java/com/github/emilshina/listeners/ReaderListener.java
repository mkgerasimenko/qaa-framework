package com.github.emilshina.listeners;

import com.github.emilshina.data.DataReader;
import lombok.extern.slf4j.Slf4j;
import one.util.streamex.StreamEx;
import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.util.ArrayList;
import java.util.List;

import static com.github.emilshina.utils.ServiceLoaderUtils.load;
import static java.lang.ClassLoader.getSystemClassLoader;
import static org.apache.commons.io.FilenameUtils.getExtension;

/**
 * Listener for each suite.
 */

@Slf4j
public class ReaderListener implements ISuiteListener {

    private static final List<DataReader> READERS = new ArrayList<>();

    @Override
    public void onStart(final ISuite suite) {
        log.info("Suite is started.");
        READERS.addAll(load(DataReader.class, getSystemClassLoader()));
    }

    @Override
    public void onFinish(final ISuite suite) {
        READERS.clear();
    }

    public static DataReader readerOf(final String dataSource) {
        return StreamEx.of(load(DataReader.class, getSystemClassLoader()))
                .findFirst(dataReader -> dataReader.dataType().equals(getExtension(dataSource)))
                .orElseThrow(() -> new IllegalArgumentException("Unable to read " + dataSource));
    }
}
