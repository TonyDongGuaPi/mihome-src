package org.slf4j.helpers;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

public class SubstituteLoggerFactory implements ILoggerFactory {

    /* renamed from: a  reason: collision with root package name */
    final List f4177a = new ArrayList();

    public Logger getLogger(String str) {
        this.f4177a.add(str);
        return NOPLogger.NOP_LOGGER;
    }

    public List a() {
        return this.f4177a;
    }
}
