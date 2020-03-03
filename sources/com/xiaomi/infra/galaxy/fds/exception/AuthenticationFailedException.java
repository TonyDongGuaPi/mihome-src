package com.xiaomi.infra.galaxy.fds.exception;

import com.xiaomi.infra.galaxy.fds.FDSError;

public class AuthenticationFailedException extends GalaxyFDSException {
    private static final long serialVersionUID = -8636481540481787968L;

    public AuthenticationFailedException() {
    }

    public AuthenticationFailedException(String str, Throwable th) {
        super(str, th);
    }

    public FDSError getError() {
        return FDSError.AuthenticationFailed;
    }
}
