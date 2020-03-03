package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsicBlend;
import android.support.v8.renderscript.Script;

class ScriptIntrinsicBlendThunker extends ScriptIntrinsicBlend {
    ScriptIntrinsicBlend mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsicBlend getNObj() {
        return this.mN;
    }

    ScriptIntrinsicBlendThunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicBlendThunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsicBlendThunker scriptIntrinsicBlendThunker = new ScriptIntrinsicBlendThunker(0, renderScript);
        try {
            scriptIntrinsicBlendThunker.mN = ScriptIntrinsicBlend.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsicBlendThunker;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachClear(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachClear(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDClear() {
        Script.KernelID createKernelID = createKernelID(0, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDClear();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachSrc(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachSrc(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDSrc() {
        Script.KernelID createKernelID = createKernelID(1, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDSrc();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachDst(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachDst(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDDst() {
        Script.KernelID createKernelID = createKernelID(2, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDDst();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachSrcOver(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachSrcOver(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDSrcOver() {
        Script.KernelID createKernelID = createKernelID(3, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDSrcOver();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachDstOver(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachDstOver(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDDstOver() {
        Script.KernelID createKernelID = createKernelID(4, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDDstOver();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachSrcIn(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachSrcIn(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDSrcIn() {
        Script.KernelID createKernelID = createKernelID(5, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDSrcIn();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachDstIn(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachDstIn(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDDstIn() {
        Script.KernelID createKernelID = createKernelID(6, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDDstIn();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachSrcOut(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachSrcOut(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDSrcOut() {
        Script.KernelID createKernelID = createKernelID(7, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDSrcOut();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachDstOut(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachDstOut(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDDstOut() {
        Script.KernelID createKernelID = createKernelID(8, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDDstOut();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachSrcAtop(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachSrcAtop(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDSrcAtop() {
        Script.KernelID createKernelID = createKernelID(9, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDSrcAtop();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachDstAtop(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachDstAtop(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDDstAtop() {
        Script.KernelID createKernelID = createKernelID(10, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDDstAtop();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachXor(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachXor(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDXor() {
        Script.KernelID createKernelID = createKernelID(11, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDXor();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachMultiply(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachMultiply(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDMultiply() {
        Script.KernelID createKernelID = createKernelID(14, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDMultiply();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachAdd(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachAdd(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDAdd() {
        Script.KernelID createKernelID = createKernelID(34, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDAdd();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void forEachSubtract(Allocation allocation, Allocation allocation2) {
        try {
            this.mN.forEachSubtract(((AllocationThunker) allocation).getNObj(), ((AllocationThunker) allocation2).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public Script.KernelID getKernelIDSubtract() {
        Script.KernelID createKernelID = createKernelID(35, 3, (Element) null, (Element) null);
        try {
            createKernelID.mN = this.mN.getKernelIDSubtract();
            return createKernelID;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
