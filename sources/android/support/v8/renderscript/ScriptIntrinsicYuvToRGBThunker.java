package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsicYuvToRGB;
import android.support.v8.renderscript.Script;

public class ScriptIntrinsicYuvToRGBThunker extends ScriptIntrinsicYuvToRGB {
    ScriptIntrinsicYuvToRGB mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsicYuvToRGB getNObj() {
        return this.mN;
    }

    private ScriptIntrinsicYuvToRGBThunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicYuvToRGBThunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsicYuvToRGBThunker scriptIntrinsicYuvToRGBThunker = new ScriptIntrinsicYuvToRGBThunker(0, renderScript);
        try {
            scriptIntrinsicYuvToRGBThunker.mN = ScriptIntrinsicYuvToRGB.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsicYuvToRGBThunker;
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
        Script.FieldID createFieldID = createFieldID(0, (Element) null);
        try {
            createFieldID.mN = this.mN.getFieldID_Input();
            return createFieldID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
