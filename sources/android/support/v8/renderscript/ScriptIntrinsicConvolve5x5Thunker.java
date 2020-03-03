package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsicConvolve5x5;
import android.support.v8.renderscript.Script;

class ScriptIntrinsicConvolve5x5Thunker extends ScriptIntrinsicConvolve5x5 {
    ScriptIntrinsicConvolve5x5 mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsicConvolve5x5 getNObj() {
        return this.mN;
    }

    ScriptIntrinsicConvolve5x5Thunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicConvolve5x5Thunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsicConvolve5x5Thunker scriptIntrinsicConvolve5x5Thunker = new ScriptIntrinsicConvolve5x5Thunker(0, renderScript);
        try {
            scriptIntrinsicConvolve5x5Thunker.mN = ScriptIntrinsicConvolve5x5.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsicConvolve5x5Thunker;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setInput(Allocation allocation) {
        try {
            this.mN.setInput(((AllocationThunker) allocation).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setCoefficients(float[] fArr) {
        try {
            this.mN.setCoefficients(fArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEach(Allocation allocation) {
        try {
            this.mN.forEach(((AllocationThunker) allocation).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelID() {
        Script.KernelID createKernelID = createKernelID(0, 2, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelID();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.FieldID getFieldID_Input() {
        Script.FieldID createFieldID = createFieldID(1, (Element) null);
        try {
            createFieldID.mN = this.mN.getFieldID_Input();
            return createFieldID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
