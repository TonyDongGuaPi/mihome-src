package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsicConvolve3x3;
import android.support.v8.renderscript.Script;

class ScriptIntrinsicConvolve3x3Thunker extends ScriptIntrinsicConvolve3x3 {
    ScriptIntrinsicConvolve3x3 mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsicConvolve3x3 getNObj() {
        return this.mN;
    }

    ScriptIntrinsicConvolve3x3Thunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicConvolve3x3Thunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsicConvolve3x3Thunker scriptIntrinsicConvolve3x3Thunker = new ScriptIntrinsicConvolve3x3Thunker(0, renderScript);
        try {
            scriptIntrinsicConvolve3x3Thunker.mN = ScriptIntrinsicConvolve3x3.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsicConvolve3x3Thunker;
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
