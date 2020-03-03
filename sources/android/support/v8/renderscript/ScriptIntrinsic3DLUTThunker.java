package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsic3DLUT;
import android.support.v8.renderscript.Script;

class ScriptIntrinsic3DLUTThunker extends ScriptIntrinsic3DLUT {
    ScriptIntrinsic3DLUT mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsic3DLUT getNObj() {
        return this.mN;
    }

    private ScriptIntrinsic3DLUTThunker(int i, RenderScript renderScript, Element element) {
        super(i, renderScript, element);
    }

    public static ScriptIntrinsic3DLUTThunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsic3DLUTThunker scriptIntrinsic3DLUTThunker = new ScriptIntrinsic3DLUTThunker(0, renderScript, element);
        try {
            scriptIntrinsic3DLUTThunker.mN = ScriptIntrinsic3DLUT.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsic3DLUTThunker;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setLUT(Allocation allocation) {
        try {
            this.mN.setLUT(((AllocationThunker) allocation).getNObj());
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
