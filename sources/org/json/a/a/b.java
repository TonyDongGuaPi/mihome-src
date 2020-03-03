package org.json.a.a;

public class b extends Exception {
    private Throwable cause;

    public b(String str) {
        super(str);
    }

    public b(Throwable th) {
        super(th.getMessage());
        this.cause = th;
    }

    public Throwable getCause() {
        return this.cause;
    }
}
