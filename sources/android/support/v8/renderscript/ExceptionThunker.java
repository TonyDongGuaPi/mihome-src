package android.support.v8.renderscript;

import android.renderscript.RSDriverException;
import android.renderscript.RSIllegalArgumentException;
import android.renderscript.RSInvalidStateException;
import android.renderscript.RSRuntimeException;

class ExceptionThunker {
    ExceptionThunker() {
    }

    static RuntimeException convertException(RuntimeException runtimeException) {
        if (runtimeException instanceof RSIllegalArgumentException) {
            return new RSIllegalArgumentException(runtimeException.getMessage());
        }
        if (runtimeException instanceof RSInvalidStateException) {
            return new RSInvalidStateException(runtimeException.getMessage());
        }
        if (runtimeException instanceof RSDriverException) {
            return new RSDriverException(runtimeException.getMessage());
        }
        return runtimeException instanceof RSRuntimeException ? new RSRuntimeException(runtimeException.getMessage()) : runtimeException;
    }
}
