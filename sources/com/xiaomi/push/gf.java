package com.xiaomi.push;

import java.io.PrintStream;
import java.io.PrintWriter;

public class gf extends Exception {

    /* renamed from: a  reason: collision with root package name */
    private go f12749a = null;

    /* renamed from: a  reason: collision with other field name */
    private gp f86a = null;

    /* renamed from: a  reason: collision with other field name */
    private Throwable f87a = null;

    public gf() {
    }

    public gf(go goVar) {
        this.f12749a = goVar;
    }

    public gf(String str) {
        super(str);
    }

    public gf(String str, Throwable th) {
        super(str);
        this.f87a = th;
    }

    public gf(Throwable th) {
        this.f87a = th;
    }

    public Throwable a() {
        return this.f87a;
    }

    public String getMessage() {
        String message = super.getMessage();
        return (message != null || this.f86a == null) ? (message != null || this.f12749a == null) ? message : this.f12749a.toString() : this.f86a.toString();
    }

    public void printStackTrace() {
        printStackTrace(System.err);
    }

    public void printStackTrace(PrintStream printStream) {
        super.printStackTrace(printStream);
        if (this.f87a != null) {
            printStream.println("Nested Exception: ");
            this.f87a.printStackTrace(printStream);
        }
    }

    public void printStackTrace(PrintWriter printWriter) {
        super.printStackTrace(printWriter);
        if (this.f87a != null) {
            printWriter.println("Nested Exception: ");
            this.f87a.printStackTrace(printWriter);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        String message = super.getMessage();
        if (message != null) {
            sb.append(message);
            sb.append(": ");
        }
        if (this.f86a != null) {
            sb.append(this.f86a);
        }
        if (this.f12749a != null) {
            sb.append(this.f12749a);
        }
        if (this.f87a != null) {
            sb.append("\n  -- caused by: ");
            sb.append(this.f87a);
        }
        return sb.toString();
    }
}
