package com.xiaomi.infra.galaxy.fds.exception;

import com.xiaomi.infra.galaxy.fds.FDSError;

public class BucketNotFoundException extends GalaxyFDSException {
    private static final long serialVersionUID = 5814749308983510510L;

    public BucketNotFoundException() {
    }

    public BucketNotFoundException(String str, Throwable th) {
        super(str, th);
    }

    public FDSError getError() {
        return FDSError.BucketNotFound;
    }
}
