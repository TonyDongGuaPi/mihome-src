package android.support.v8.renderscript;

import android.content.Context;
import android.renderscript.RSRuntimeException;
import android.renderscript.RenderScript;
import android.support.v8.renderscript.RenderScript;

class RenderScriptThunker extends RenderScript {
    RenderScript mN;

    /* access modifiers changed from: package-private */
    public void validate() {
        if (this.mN == null) {
            throw new RSInvalidStateException("Calling RS with no Context active.");
        }
    }

    public void setPriority(RenderScript.Priority priority) {
        try {
            if (priority == RenderScript.Priority.LOW) {
                this.mN.setPriority(RenderScript.Priority.LOW);
            }
            if (priority == RenderScript.Priority.NORMAL) {
                this.mN.setPriority(RenderScript.Priority.NORMAL);
            }
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    RenderScriptThunker(Context context) {
        super(context);
        isNative = true;
    }

    public static RenderScript create(Context context, int i) {
        try {
            RenderScriptThunker renderScriptThunker = new RenderScriptThunker(context);
            renderScriptThunker.mN = android.renderscript.RenderScript.create(context, i);
            return renderScriptThunker;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void contextDump() {
        try {
            this.mN.contextDump();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void finish() {
        try {
            this.mN.finish();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void destroy() {
        try {
            this.mN.destroy();
            this.mN = null;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
