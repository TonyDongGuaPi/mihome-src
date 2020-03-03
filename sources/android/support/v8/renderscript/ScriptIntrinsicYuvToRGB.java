package android.support.v8.renderscript;

import android.support.v8.renderscript.Script;

public class ScriptIntrinsicYuvToRGB extends ScriptIntrinsic {
    private Allocation mInput;

    ScriptIntrinsicYuvToRGB(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicYuvToRGB create(RenderScript renderScript, Element element) {
        if (!RenderScript.isNative) {
            return new ScriptIntrinsicYuvToRGB(renderScript.nScriptIntrinsicCreate(6, element.getID(renderScript)), renderScript);
        }
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        return ScriptIntrinsicYuvToRGBThunker.create(renderScript, element);
    }

    public void setInput(Allocation allocation) {
        this.mInput = allocation;
        setVar(0, (BaseObj) allocation);
    }

    public void forEach(Allocation allocation) {
        forEach(0, (Allocation) null, allocation, (FieldPacker) null);
    }

    public Script.KernelID getKernelID() {
        return createKernelID(0, 2, (Element) null, (Element) null);
    }

    public Script.FieldID getFieldID_Input() {
        return createFieldID(0, (Element) null);
    }
}
