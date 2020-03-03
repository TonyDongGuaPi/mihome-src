package android.support.v8.renderscript;

import android.renderscript.Script;
import android.util.SparseArray;
import java.io.UnsupportedEncodingException;

public class Script extends BaseObj {
    private final SparseArray<FieldID> mFIDs = new SparseArray<>();
    private final SparseArray<KernelID> mKIDs = new SparseArray<>();
    ScriptCThunker mT;

    /* access modifiers changed from: package-private */
    public android.renderscript.Script getNObj() {
        return this.mT;
    }

    public static final class KernelID extends BaseObj {
        Script.KernelID mN;
        Script mScript;
        int mSig;
        int mSlot;

        KernelID(int i, RenderScript renderScript, Script script, int i2, int i3) {
            super(i, renderScript);
            this.mScript = script;
            this.mSlot = i2;
            this.mSig = i3;
        }
    }

    /* access modifiers changed from: protected */
    public KernelID createKernelID(int i, int i2, Element element, Element element2) {
        KernelID kernelID = this.mKIDs.get(i);
        if (kernelID != null) {
            return kernelID;
        }
        RenderScript renderScript = this.mRS;
        if (RenderScript.isNative) {
            KernelID kernelID2 = new KernelID(0, this.mRS, this, i, i2);
            if (this.mT != null) {
                kernelID2.mN = this.mT.thunkCreateKernelID(i, i2, element, element2);
            }
            this.mKIDs.put(i, kernelID2);
            return kernelID2;
        }
        int nScriptKernelIDCreate = this.mRS.nScriptKernelIDCreate(getID(this.mRS), i, i2);
        if (nScriptKernelIDCreate != 0) {
            KernelID kernelID3 = new KernelID(nScriptKernelIDCreate, this.mRS, this, i, i2);
            this.mKIDs.put(i, kernelID3);
            return kernelID3;
        }
        throw new RSDriverException("Failed to create KernelID");
    }

    public static final class FieldID extends BaseObj {
        Script.FieldID mN;
        Script mScript;
        int mSlot;

        FieldID(int i, RenderScript renderScript, Script script, int i2) {
            super(i, renderScript);
            this.mScript = script;
            this.mSlot = i2;
        }
    }

    /* access modifiers changed from: protected */
    public FieldID createFieldID(int i, Element element) {
        RenderScript renderScript = this.mRS;
        if (RenderScript.isNative) {
            FieldID fieldID = new FieldID(0, this.mRS, this, i);
            if (this.mT != null) {
                fieldID.mN = this.mT.thunkCreateFieldID(i, element);
            }
            this.mFIDs.put(i, fieldID);
            return fieldID;
        }
        FieldID fieldID2 = this.mFIDs.get(i);
        if (fieldID2 != null) {
            return fieldID2;
        }
        int nScriptFieldIDCreate = this.mRS.nScriptFieldIDCreate(getID(this.mRS), i);
        if (nScriptFieldIDCreate != 0) {
            FieldID fieldID3 = new FieldID(nScriptFieldIDCreate, this.mRS, this, i);
            this.mFIDs.put(i, fieldID3);
            return fieldID3;
        }
        throw new RSDriverException("Failed to create FieldID");
    }

    /* access modifiers changed from: protected */
    public void invoke(int i) {
        if (this.mT != null) {
            this.mT.thunkInvoke(i);
        } else {
            this.mRS.nScriptInvoke(getID(this.mRS), i);
        }
    }

    /* access modifiers changed from: protected */
    public void invoke(int i, FieldPacker fieldPacker) {
        if (this.mT != null) {
            this.mT.thunkInvoke(i, fieldPacker);
        } else if (fieldPacker != null) {
            this.mRS.nScriptInvokeV(getID(this.mRS), i, fieldPacker.getData());
        } else {
            this.mRS.nScriptInvoke(getID(this.mRS), i);
        }
    }

    public void bindAllocation(Allocation allocation, int i) {
        if (this.mT != null) {
            this.mT.thunkBindAllocation(allocation, i);
            return;
        }
        this.mRS.validate();
        if (allocation != null) {
            this.mRS.nScriptBindAllocation(getID(this.mRS), allocation.getID(this.mRS), i);
        } else {
            this.mRS.nScriptBindAllocation(getID(this.mRS), 0, i);
        }
    }

    public void setTimeZone(String str) {
        if (this.mT != null) {
            this.mT.thunkSetTimeZone(str);
            return;
        }
        this.mRS.validate();
        try {
            this.mRS.nScriptSetTimeZone(getID(this.mRS), str.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    /* access modifiers changed from: protected */
    public void forEach(int i, Allocation allocation, Allocation allocation2, FieldPacker fieldPacker) {
        if (this.mT != null) {
            this.mT.thunkForEach(i, allocation, allocation2, fieldPacker);
        } else if (allocation == null && allocation2 == null) {
            throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
        } else {
            int id = allocation != null ? allocation.getID(this.mRS) : 0;
            int id2 = allocation2 != null ? allocation2.getID(this.mRS) : 0;
            byte[] bArr = null;
            if (fieldPacker != null) {
                bArr = fieldPacker.getData();
            }
            this.mRS.nScriptForEach(getID(this.mRS), i, id, id2, bArr);
        }
    }

    /* access modifiers changed from: protected */
    public void forEach(int i, Allocation allocation, Allocation allocation2, FieldPacker fieldPacker, LaunchOptions launchOptions) {
        Allocation allocation3 = allocation;
        Allocation allocation4 = allocation2;
        if (this.mT != null) {
            this.mT.thunkForEach(i, allocation, allocation2, fieldPacker, launchOptions);
        } else if (allocation3 == null && allocation4 == null) {
            throw new RSIllegalArgumentException("At least one of ain or aout is required to be non-null.");
        } else if (launchOptions == null) {
            forEach(i, allocation, allocation2, fieldPacker);
        } else {
            int id = allocation3 != null ? allocation3.getID(this.mRS) : 0;
            int id2 = allocation4 != null ? allocation4.getID(this.mRS) : 0;
            byte[] bArr = null;
            if (fieldPacker != null) {
                bArr = fieldPacker.getData();
            }
            this.mRS.nScriptForEachClipped(getID(this.mRS), i, id, id2, bArr, launchOptions.xstart, launchOptions.xend, launchOptions.ystart, launchOptions.yend, launchOptions.zstart, launchOptions.zend);
        }
    }

    Script(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public void setVar(int i, float f) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, f);
        } else {
            this.mRS.nScriptSetVarF(getID(this.mRS), i, f);
        }
    }

    public void setVar(int i, double d) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, d);
        } else {
            this.mRS.nScriptSetVarD(getID(this.mRS), i, d);
        }
    }

    public void setVar(int i, int i2) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, i2);
        } else {
            this.mRS.nScriptSetVarI(getID(this.mRS), i, i2);
        }
    }

    public void setVar(int i, long j) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, j);
        } else {
            this.mRS.nScriptSetVarJ(getID(this.mRS), i, j);
        }
    }

    public void setVar(int i, boolean z) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, z);
        } else {
            this.mRS.nScriptSetVarI(getID(this.mRS), i, z ? 1 : 0);
        }
    }

    public void setVar(int i, BaseObj baseObj) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, baseObj);
        } else {
            this.mRS.nScriptSetVarObj(getID(this.mRS), i, baseObj == null ? 0 : baseObj.getID(this.mRS));
        }
    }

    public void setVar(int i, FieldPacker fieldPacker) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, fieldPacker);
        } else {
            this.mRS.nScriptSetVarV(getID(this.mRS), i, fieldPacker.getData());
        }
    }

    public void setVar(int i, FieldPacker fieldPacker, Element element, int[] iArr) {
        if (this.mT != null) {
            this.mT.thunkSetVar(i, fieldPacker, element, iArr);
            return;
        }
        this.mRS.nScriptSetVarVE(getID(this.mRS), i, fieldPacker.getData(), element.getID(this.mRS), iArr);
    }

    public static class Builder {
        RenderScript mRS;

        Builder(RenderScript renderScript) {
            this.mRS = renderScript;
        }
    }

    public static class FieldBase {
        protected Allocation mAllocation;
        protected Element mElement;

        public void updateAllocation() {
        }

        /* access modifiers changed from: protected */
        public void init(RenderScript renderScript, int i) {
            this.mAllocation = Allocation.createSized(renderScript, this.mElement, i, 1);
        }

        /* access modifiers changed from: protected */
        public void init(RenderScript renderScript, int i, int i2) {
            this.mAllocation = Allocation.createSized(renderScript, this.mElement, i, i2 | 1);
        }

        protected FieldBase() {
        }

        public Element getElement() {
            return this.mElement;
        }

        public Type getType() {
            return this.mAllocation.getType();
        }

        public Allocation getAllocation() {
            return this.mAllocation;
        }
    }

    public static final class LaunchOptions {
        private int strategy;
        /* access modifiers changed from: private */
        public int xend = 0;
        /* access modifiers changed from: private */
        public int xstart = 0;
        /* access modifiers changed from: private */
        public int yend = 0;
        /* access modifiers changed from: private */
        public int ystart = 0;
        /* access modifiers changed from: private */
        public int zend = 0;
        /* access modifiers changed from: private */
        public int zstart = 0;

        public LaunchOptions setX(int i, int i2) {
            if (i < 0 || i2 <= i) {
                throw new RSIllegalArgumentException("Invalid dimensions");
            }
            this.xstart = i;
            this.xend = i2;
            return this;
        }

        public LaunchOptions setY(int i, int i2) {
            if (i < 0 || i2 <= i) {
                throw new RSIllegalArgumentException("Invalid dimensions");
            }
            this.ystart = i;
            this.yend = i2;
            return this;
        }

        public LaunchOptions setZ(int i, int i2) {
            if (i < 0 || i2 <= i) {
                throw new RSIllegalArgumentException("Invalid dimensions");
            }
            this.zstart = i;
            this.zend = i2;
            return this;
        }

        public int getXStart() {
            return this.xstart;
        }

        public int getXEnd() {
            return this.xend;
        }

        public int getYStart() {
            return this.ystart;
        }

        public int getYEnd() {
            return this.yend;
        }

        public int getZStart() {
            return this.zstart;
        }

        public int getZEnd() {
            return this.zend;
        }
    }
}
