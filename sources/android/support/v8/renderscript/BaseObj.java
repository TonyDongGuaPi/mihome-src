package android.support.v8.renderscript;

public class BaseObj {
    private boolean mDestroyed = false;
    private int mID;
    RenderScript mRS;

    /* access modifiers changed from: package-private */
    public android.renderscript.BaseObj getNObj() {
        return null;
    }

    BaseObj(int i, RenderScript renderScript) {
        renderScript.validate();
        this.mRS = renderScript;
        this.mID = i;
    }

    /* access modifiers changed from: package-private */
    public void setID(int i) {
        if (this.mID == 0) {
            this.mID = i;
            return;
        }
        throw new RSRuntimeException("Internal Error, reset of object ID.");
    }

    /* access modifiers changed from: package-private */
    public int getID(RenderScript renderScript) {
        this.mRS.validate();
        if (RenderScript.isNative) {
            RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
            if (getNObj() != null) {
                return getNObj().hashCode();
            }
        }
        if (this.mDestroyed) {
            throw new RSInvalidStateException("using a destroyed object.");
        } else if (this.mID == 0) {
            throw new RSRuntimeException("Internal error: Object id 0.");
        } else if (renderScript == null || renderScript == this.mRS) {
            return this.mID;
        } else {
            throw new RSInvalidStateException("using object with mismatched context.");
        }
    }

    /* access modifiers changed from: package-private */
    public void checkValid() {
        if (this.mID == 0 && getNObj() == null) {
            throw new RSIllegalArgumentException("Invalid object.");
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        if (!this.mDestroyed) {
            if (this.mID != 0 && this.mRS.isAlive()) {
                this.mRS.nObjDestroy(this.mID);
            }
            this.mRS = null;
            this.mID = 0;
            this.mDestroyed = true;
        }
        super.finalize();
    }

    public synchronized void destroy() {
        if (!this.mDestroyed) {
            this.mDestroyed = true;
            this.mRS.nObjDestroy(this.mID);
        } else {
            throw new RSInvalidStateException("Object already destroyed.");
        }
    }

    public int hashCode() {
        return this.mID;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (getClass() == obj.getClass() && this.mID == ((BaseObj) obj).mID) {
            return true;
        }
        return false;
    }
}
