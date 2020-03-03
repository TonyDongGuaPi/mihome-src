package android.support.v8.renderscript;

import android.support.v8.renderscript.Script;

public class ScriptIntrinsic3DLUT extends ScriptIntrinsic {
    private Element mElement;
    private Allocation mLUT;

    protected ScriptIntrinsic3DLUT(int i, RenderScript renderScript, Element element) {
        super(i, renderScript);
        this.mElement = element;
    }

    public static ScriptIntrinsic3DLUT create(RenderScript renderScript, Element element) {
        if (RenderScript.isNative) {
            RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
            return ScriptIntrinsic3DLUTThunker.create(renderScript, element);
        }
        int nScriptIntrinsicCreate = renderScript.nScriptIntrinsicCreate(8, element.getID(renderScript));
        if (element.isCompatible(Element.U8_4(renderScript))) {
            return new ScriptIntrinsic3DLUT(nScriptIntrinsicCreate, renderScript, element);
        }
        throw new RSIllegalArgumentException("Element must be compatible with uchar4.");
    }

    public void setLUT(Allocation allocation) {
        Type type = allocation.getType();
        if (type.getZ() == 0) {
            throw new RSIllegalArgumentException("LUT must be 3d.");
        } else if (type.getElement().isCompatible(this.mElement)) {
            this.mLUT = allocation;
            setVar(0, (BaseObj) this.mLUT);
        } else {
            throw new RSIllegalArgumentException("LUT element type must match.");
        }
    }

    public void forEach(Allocation allocation, Allocation allocation2) {
        forEach(0, allocation, allocation2, (FieldPacker) null);
    }

    public Script.KernelID getKernelID() {
        return createKernelID(0, 3, (Element) null, (Element) null);
    }
}
