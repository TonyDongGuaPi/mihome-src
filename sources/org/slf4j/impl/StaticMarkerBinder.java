package org.slf4j.impl;

import org.slf4j.IMarkerFactory;
import org.slf4j.helpers.BasicMarkerFactory;
import org.slf4j.spi.MarkerFactoryBinder;

public class StaticMarkerBinder implements MarkerFactoryBinder {

    /* renamed from: a  reason: collision with root package name */
    public static final StaticMarkerBinder f4179a = new StaticMarkerBinder();
    private final IMarkerFactory b = new BasicMarkerFactory();

    private StaticMarkerBinder() {
    }

    public IMarkerFactory a() {
        return this.b;
    }

    public String b() {
        return BasicMarkerFactory.class.getName();
    }
}
