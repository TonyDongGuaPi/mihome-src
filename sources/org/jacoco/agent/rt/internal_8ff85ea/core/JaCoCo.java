package org.jacoco.agent.rt.internal_8ff85ea.core;

import java.util.ResourceBundle;

public final class JaCoCo {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3610a;
    public static final String b;
    public static final String c;

    static {
        ResourceBundle bundle = ResourceBundle.getBundle("org.jacoco.agent.rt.internal_8ff85ea.core.jacoco");
        f3610a = bundle.getString("VERSION");
        b = bundle.getString("HOMEURL");
        c = bundle.getString("RUNTIMEPACKAGE");
    }

    private JaCoCo() {
    }
}
