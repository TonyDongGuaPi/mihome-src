package org.mp4parser.support;

import java.util.logging.Level;
import java.util.logging.Logger;

public class JuliLogger extends Logger {

    /* renamed from: a  reason: collision with root package name */
    Logger f4104a;

    public JuliLogger(String str) {
        this.f4104a = Logger.getLogger(str);
    }

    public void a(String str) {
        this.f4104a.log(Level.FINE, str);
    }

    public void b(String str) {
        this.f4104a.log(Level.WARNING, str);
    }

    public void c(String str) {
        this.f4104a.log(Level.SEVERE, str);
    }
}
