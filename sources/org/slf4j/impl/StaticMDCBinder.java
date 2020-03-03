package org.slf4j.impl;

import org.slf4j.helpers.NOPMakerAdapter;
import org.slf4j.spi.MDCAdapter;

public class StaticMDCBinder {

    /* renamed from: a  reason: collision with root package name */
    public static final StaticMDCBinder f4178a = new StaticMDCBinder();

    private StaticMDCBinder() {
    }

    public MDCAdapter a() {
        return new NOPMakerAdapter();
    }

    public String b() {
        return NOPMakerAdapter.class.getName();
    }
}
