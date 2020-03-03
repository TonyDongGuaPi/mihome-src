package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v8.renderscript.Script;

class ScriptIntrinsicBlurThunker extends ScriptIntrinsicBlur {
    ScriptIntrinsicBlur mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsicBlur getNObj() {
        return this.mN;
    }

    protected ScriptIntrinsicBlurThunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicBlurThunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsicBlurThunker scriptIntrinsicBlurThunker = new ScriptIntrinsicBlurThunker(0, renderScript);
        try {
            scriptIntrinsicBlurThunker.mN = ScriptIntrinsicBlur.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsicBlurThunker;
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

    public void setRadius(float f) {
        try {
            this.mN.setRadius(f);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEach(Allocation allocation) {
        AllocationThunker allocationThunker = (AllocationThunker) allocation;
        if (allocationThunker != null) {
            try {
                this.mN.forEach(allocationThunker.getNObj());
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
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
