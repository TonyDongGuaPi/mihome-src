package com.xiaomi.infra.galaxy.fds.exception;

import com.xiaomi.infra.galaxy.fds.FDSError;

public class GalaxyFDSException extends Exception {
    private static final long serialVersionUID = -7688381775178948719L;

    public GalaxyFDSException() {
    }

    public GalaxyFDSException(String str, Throwable th) {
        super(str, th);
    }

    public GalaxyFDSException(String str) {
        super(str, (Throwable) null);
    }

    public GalaxyFDSException(Throwable th) {
        super((String) null, th);
    }

    public FDSError getError() {
        return FDSError.InternalServerError;
    }

    public String toString() {
        String str = "Galaxy FDS Error: " + getError().description();
        if (getMessage() == null) {
            return str;
        }
        return str + " " + getMessage();
    }
}
