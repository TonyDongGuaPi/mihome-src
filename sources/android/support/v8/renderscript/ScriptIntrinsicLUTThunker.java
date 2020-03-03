package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsicLUT;
import android.support.v8.renderscript.Script;

class ScriptIntrinsicLUTThunker extends ScriptIntrinsicLUT {
    ScriptIntrinsicLUT mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsicLUT getNObj() {
        return this.mN;
    }

    private ScriptIntrinsicLUTThunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicLUTThunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsicLUTThunker scriptIntrinsicLUTThunker = new ScriptIntrinsicLUTThunker(0, renderScript);
        try {
            scriptIntrinsicLUTThunker.mN = ScriptIntrinsicLUT.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsicLUTThunker;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setRed(int i, int i2) {
        try {
            this.mN.setRed(i, i2);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setGreen(int i, int i2) {
        try {
            this.mN.setGreen(i, i2);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setBlue(int i, int i2) {
        try {
            this.mN.setBlue(i, i2);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setAlpha(int i, int i2) {
        try {
            this.mN.setAlpha(i, i2);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEach(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEach(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelID() {
        Script.KernelID createKernelID = createKernelID(0, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelID();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
