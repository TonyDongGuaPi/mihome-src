package android.support.v8.renderscript;

import android.content.res.Resources;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.FieldPacker;
import android.renderscript.RSRuntimeException;
import android.renderscript.Script;
import android.renderscript.ScriptC;
import android.support.v8.renderscript.Script;

class ScriptCThunker extends ScriptC {
    private static final String TAG = "ScriptC";

    protected ScriptCThunker(RenderScriptThunker renderScriptThunker, Resources resources, int i) {
        super(renderScriptThunker.mN, resources, i);
    }

    /* access modifiers changed from: package-private */
    public Script.KernelID thunkCreateKernelID(int i, int i2, Element element, Element element2) {
        Element element3 = null;
        Element element4 = element != null ? ((ElementThunker) element).mN : null;
        if (element2 != null) {
            element3 = ((ElementThunker) element2).mN;
        }
        try {
            return createKernelID(i, i2, element4, element3);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkInvoke(int i) {
        try {
            invoke(i);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkBindAllocation(Allocation allocation, int i) {
        try {
            bindAllocation(allocation != null ? ((AllocationThunker) allocation).mN : null, i);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetTimeZone(String str) {
        try {
            setTimeZone(str);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkInvoke(int i, FieldPacker fieldPacker) {
        try {
            invoke(i, new FieldPacker(fieldPacker.getData()));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkForEach(int i, Allocation allocation, Allocation allocation2, FieldPacker fieldPacker) {
        FieldPacker fieldPacker2 = null;
        Allocation allocation3 = allocation != null ? ((AllocationThunker) allocation).mN : null;
        Allocation allocation4 = allocation2 != null ? ((AllocationThunker) allocation2).mN : null;
        if (fieldPacker != null) {
            try {
                fieldPacker2 = new FieldPacker(fieldPacker.getData());
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }
        forEach(i, allocation3, allocation4, fieldPacker2);
    }

    /* access modifiers changed from: package-private */
    public void thunkForEach(int i, Allocation allocation, Allocation allocation2, FieldPacker fieldPacker, Script.LaunchOptions launchOptions) {
        Script.LaunchOptions launchOptions2;
        FieldPacker fieldPacker2 = null;
        if (launchOptions != null) {
            try {
                Script.LaunchOptions launchOptions3 = new Script.LaunchOptions();
                if (launchOptions.getXEnd() > 0) {
                    launchOptions3.setX(launchOptions.getXStart(), launchOptions.getXEnd());
                }
                if (launchOptions.getYEnd() > 0) {
                    launchOptions3.setY(launchOptions.getYStart(), launchOptions.getYEnd());
                }
                if (launchOptions.getZEnd() > 0) {
                    launchOptions3.setZ(launchOptions.getZStart(), launchOptions.getZEnd());
                }
                launchOptions2 = launchOptions3;
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        } else {
            launchOptions2 = null;
        }
        Allocation allocation3 = allocation != null ? ((AllocationThunker) allocation).mN : null;
        Allocation allocation4 = allocation2 != null ? ((AllocationThunker) allocation2).mN : null;
        if (fieldPacker != null) {
            fieldPacker2 = new FieldPacker(fieldPacker.getData());
        }
        forEach(i, allocation3, allocation4, fieldPacker2, launchOptions2);
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, float f) {
        try {
            setVar(i, f);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, double d) {
        try {
            setVar(i, d);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, int i2) {
        try {
            setVar(i, i2);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, long j) {
        try {
            setVar(i, j);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, boolean z) {
        try {
            setVar(i, z);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, BaseObj baseObj) {
        if (baseObj == null) {
            try {
                setVar(i, 0);
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        } else {
            try {
                setVar(i, baseObj.getNObj());
            } catch (RSRuntimeException e2) {
                throw ExceptionThunker.convertException(e2);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, FieldPacker fieldPacker) {
        try {
            setVar(i, new FieldPacker(fieldPacker.getData()));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public void thunkSetVar(int i, FieldPacker fieldPacker, Element element, int[] iArr) {
        try {
            setVar(i, new FieldPacker(fieldPacker.getData()), ((ElementThunker) element).mN, iArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    /* access modifiers changed from: package-private */
    public Script.FieldID thunkCreateFieldID(int i, Element element) {
        try {
            return createFieldID(i, ((ElementThunker) element).getNObj());
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
