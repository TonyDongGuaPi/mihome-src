package org.slf4j.impl;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.Logger;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.impl.repository.Slf4jLoggerRepository;

public class MicrologLoggerAdapter extends MarkerIgnoringBase {
    private static final long serialVersionUID = 3934653965724860568L;
    private final transient Logger b;

    public boolean isErrorEnabled() {
        return true;
    }

    public boolean isWarnEnabled() {
        return true;
    }

    public void warn(String str, Object[] objArr) {
    }

    public MicrologLoggerAdapter(Logger logger) {
        this.b = logger;
        this.name = logger.getName();
        logger.setCommonRepository(Slf4jLoggerRepository.INSTANCE);
    }

    public MicrologLoggerAdapter(String str) {
        this.b = new Logger(str, Slf4jLoggerRepository.INSTANCE);
        this.name = str;
    }

    public Logger getMicrologLogger() {
        return this.b;
    }

    public String getName() {
        return this.b.getName();
    }

    public boolean isTraceEnabled() {
        return a(Level.TRACE);
    }

    public void trace(String str) {
        this.b.trace(str);
    }

    public void trace(String str, Object obj) {
        throw new UnsupportedOperationException("trace(String, Object) is not implemented yet");
    }

    public void trace(String str, Object obj, Object obj2) {
        throw new UnsupportedOperationException("trace(String, Object, Object) is not implemented yet");
    }

    public void trace(String str, Object[] objArr) {
        throw new UnsupportedOperationException("trace(String, Object[]) is not implemented yet");
    }

    public void trace(String str, Throwable th) {
        this.b.trace(str, th);
    }

    public boolean isDebugEnabled() {
        return a(Level.DEBUG);
    }

    public void debug(String str) {
        this.b.debug(str);
    }

    public void debug(String str, Object obj) {
        throw new UnsupportedOperationException("debug(String, Object) is not implemented yet");
    }

    public void debug(String str, Object obj, Object obj2) {
        throw new UnsupportedOperationException("debug(String, Object, Object) is not implemented yet");
    }

    public void debug(String str, Object[] objArr) {
        throw new UnsupportedOperationException("debug(String, Object[]) is not implemented yet");
    }

    public void debug(String str, Throwable th) {
        this.b.debug(str, th);
    }

    public boolean isInfoEnabled() {
        return a(Level.INFO);
    }

    public void info(String str) {
        this.b.info(str);
    }

    public void info(String str, Object obj) {
        throw new UnsupportedOperationException("info(String, Object) is not implemented yet");
    }

    public void info(String str, Object obj, Object obj2) {
        throw new UnsupportedOperationException("info(String, Object, Object) is not implemented yet");
    }

    public void info(String str, Object[] objArr) {
        throw new UnsupportedOperationException("info(String, Object[]) is not implemented yet");
    }

    public void info(String str, Throwable th) {
        this.b.info(str, th);
    }

    public void warn(String str) {
        this.b.warn(str);
    }

    public void warn(String str, Object obj) {
        throw new UnsupportedOperationException("warn(String, Object) is not implemented yet");
    }

    public void warn(String str, Object obj, Object obj2) {
        throw new UnsupportedOperationException("warn(String, Object, Object) is not implemented yet");
    }

    public void warn(String str, Throwable th) {
        this.b.warn(str, th);
    }

    public void error(String str) {
        this.b.error(str);
    }

    public void error(String str, Object obj) {
        throw new UnsupportedOperationException("error(String, Object) is not implemented yet");
    }

    public void error(String str, Object obj, Object obj2) {
        throw new UnsupportedOperationException("error(String, Object, Object) is not implemented yet");
    }

    public void error(String str, Object[] objArr) {
        throw new UnsupportedOperationException("error(String, Object[]) is not implemented yet");
    }

    public void error(String str, Throwable th) {
        this.b.error(str, th);
    }

    private boolean a(Level level) {
        return this.b.getEffectiveLevel().toInt() <= level.toInt();
    }
}
