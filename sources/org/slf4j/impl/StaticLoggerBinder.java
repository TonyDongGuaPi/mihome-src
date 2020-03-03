package org.slf4j.impl;

import org.slf4j.ILoggerFactory;
import org.slf4j.impl.repository.Slf4jLoggerRepository;
import org.slf4j.spi.LoggerFactoryBinder;

public enum StaticLoggerBinder implements LoggerFactoryBinder {
    SINGLETON;
    
    public static String REQUESTED_API_VERSION;
    private static final String loggerFactoryClassStr = null;
    private final ILoggerFactory loggerFactory;

    static {
        REQUESTED_API_VERSION = "1.5.11";
        loggerFactoryClassStr = Slf4jLoggerRepository.class.getName();
    }

    public static final StaticLoggerBinder getSingleton() {
        return SINGLETON;
    }

    public ILoggerFactory getLoggerFactory() {
        return this.loggerFactory;
    }

    public String getLoggerFactoryClassStr() {
        return loggerFactoryClassStr;
    }
}
