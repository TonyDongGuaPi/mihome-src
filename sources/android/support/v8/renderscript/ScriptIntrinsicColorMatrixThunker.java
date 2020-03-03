package android.support.v8.renderscript;

import android.renderscript.Matrix3f;
import android.renderscript.Matrix4f;
import android.renderscript.RSRuntimeException;
import android.renderscript.ScriptIntrinsicColorMatrix;
import android.support.v8.renderscript.Script;

class ScriptIntrinsicColorMatrixThunker extends ScriptIntrinsicColorMatrix {
    ScriptIntrinsicColorMatrix mN;

    /* access modifiers changed from: package-private */
    public ScriptIntrinsicColorMatrix getNObj() {
        return this.mN;
    }

    private ScriptIntrinsicColorMatrixThunker(int i, RenderScript renderScript) {
        super(i, renderScript);
    }

    public static ScriptIntrinsicColorMatrixThunker create(RenderScript renderScript, Element element) {
        RenderScriptThunker renderScriptThunker = (RenderScriptThunker) renderScript;
        ElementThunker elementThunker = (ElementThunker) element;
        ScriptIntrinsicColorMatrixThunker scriptIntrinsicColorMatrixThunker = new ScriptIntrinsicColorMatrixThunker(0, renderScript);
        try {
            scriptIntrinsicColorMatrixThunker.mN = ScriptIntrinsicColorMatrix.create(renderScriptThunker.mN, elementThunker.getNObj());
            return scriptIntrinsicColorMatrixThunker;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setColorMatrix(Matrix4f matrix4f) {
        try {
            this.mN.setColorMatrix(new Matrix4f(matrix4f.getArray()));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setColorMatrix(Matrix3f matrix3f) {
        try {
            this.mN.setColorMatrix(new Matrix3f(matrix3f.getArray()));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setGreyscale() {
        try {
            this.mN.setGreyscale();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setYUVtoRGB() {
        try {
            this.mN.setYUVtoRGB();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setRGBtoYUV() {
        try {
            this.mN.setRGBtoYUV();
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
