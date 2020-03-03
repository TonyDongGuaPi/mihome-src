package com.xiaomi.infra.galaxy.fds.exception;

import com.taobao.weex.el.parse.Operators;
import com.xiaomi.infra.galaxy.fds.FDSError;

public class InvalidRangePrameterException extends GalaxyFDSException {
    private final long[] range;

    public InvalidRangePrameterException(long[] jArr) {
        this.range = jArr;
    }

    public FDSError getError() {
        return FDSError.InvalidRequestRange;
    }

    public String toString() {
        String galaxyFDSException = super.toString();
        if (this.range == null) {
            return galaxyFDSException;
        }
        return galaxyFDSException + ", range in the request:[" + this.range[0] + ", " + this.range[1] + Operators.ARRAY_END_STR;
    }
}
