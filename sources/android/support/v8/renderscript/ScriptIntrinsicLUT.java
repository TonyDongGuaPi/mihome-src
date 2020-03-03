package android.support.v8.renderscript;

import android.support.v8.renderscript.Script;

public class ScriptIntrinsicLUT extends ScriptIntrinsic {
    private final byte[] mCache = new byte[1024];
    private boolean mDirty = true;
    private final Matrix4f mMatrix = new Matrix4f();
    private Allocation mTables;

    protected ScriptIntrinsicLUT(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicLUT create(RenderScript renderScript, Element element) {
        if (RenderScript.isNative) {
            RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
            return ScriptIntrinsicLUTThunker.create(renderScript, element);
        }
        ScriptIntrinsicLUT scriptIntrinsicLUT = new ScriptIntrinsicLUT(renderScript.nScriptIntrinsicCreate(3, element.getID(renderScript)), renderScript);
        scriptIntrinsicLUT.mTables = Allocation.createSized(renderScript, Element.U8(renderScript), 1024);
        for (int i = 0; i < 256; i++) {
            byte b = (byte) i;
            scriptIntrinsicLUT.mCache[i] = b;
            scriptIntrinsicLUT.mCache[i + 256] = b;
            scriptIntrinsicLUT.mCache[i + 512] = b;
            scriptIntrinsicLUT.mCache[i + 768] = b;
        }
        scriptIntrinsicLUT.setVar(0, (BaseObj) scriptIntrinsicLUT.mTables);
        return scriptIntrinsicLUT;
    }

    private void validate(int i, int i2) {
        if (i < 0 || i > 255) {
            throw new RSIllegalArgumentException("Index out of range (0-255).");
        } else if (i2 < 0 || i2 > 255) {
            throw new RSIllegalArgumentException("Value out of range (0-255).");
        }
    }

    public void setRed(int i, int i2) {
        validate(i, i2);
        this.mCache[i] = (byte) i2;
        this.mDirty = true;
    }

    public void setGreen(int i, int i2) {
        validate(i, i2);
        this.mCache[i + 256] = (byte) i2;
        this.mDirty = true;
    }

    public void setBlue(int i, int i2) {
        validate(i, i2);
        this.mCache[i + 512] = (byte) i2;
        this.mDirty = true;
    }

    public void setAlpha(int i, int i2) {
        validate(i, i2);
        this.mCache[i + 768] = (byte) i2;
        this.mDirty = true;
    }

    public void forEach(Allocation allocation, Allocation allocation2) {
        if (this.mDirty) {
            this.mDirty = false;
            this.mTables.copyFromUnchecked(this.mCache);
        }
        forEach(0, allocation, allocation2, (FieldPacker) null);
    }

    public Script.KernelID getKernelID() {
        return createKernelID(0, 3, (Element) null, (Element) null);
    }
}