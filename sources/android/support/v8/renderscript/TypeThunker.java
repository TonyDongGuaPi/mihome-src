package android.support.v8.renderscript;

import android.renderscript.RSRuntimeException;
import android.renderscript.Type;
import java.util.HashMap;

class TypeThunker extends Type {
    static HashMap<Type, Type> mMap = new HashMap<>();
    Type mN;

    /* access modifiers changed from: package-private */
    public Type getNObj() {
        return this.mN;
    }

    /* access modifiers changed from: package-private */
    public void internalCalc() {
        this.mDimX = this.mN.getX();
        this.mDimY = this.mN.getY();
        this.mDimZ = this.mN.getZ();
        this.mDimFaces = this.mN.hasFaces();
        this.mDimMipmaps = this.mN.hasMipmaps();
        this.mDimYuv = this.mN.getYuv();
        calcElementCount();
    }

    TypeThunker(RenderScript renderScript, Type type) {
        super(0, renderScript);
        this.mN = type;
        try {
            internalCalc();
            this.mElement = new ElementThunker(renderScript, type.getElement());
            synchronized (mMap) {
                mMap.put(this.mN, this);
            }
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    static Type find(Type type) {
        return mMap.get(type);
    }

    static Type create(RenderScript renderScript, Element element, int i, int i2, int i3, boolean z, boolean z2, int i4) {
        try {
            Type.Builder builder = new Type.Builder(((RenderScriptThunker) renderScript).mN, ((ElementThunker) element).mN);
            if (i > 0) {
                builder.setX(i);
            }
            if (i2 > 0) {
                builder.setY(i2);
            }
            if (i3 > 0) {
                builder.setZ(i3);
            }
            if (z) {
                builder.setMipmaps(z);
            }
            if (z2) {
                builder.setFaces(z2);
            }
            if (i4 > 0) {
                builder.setYuvFormat(i4);
            }
            TypeThunker typeThunker = new TypeThunker(renderScript, builder.create());
            typeThunker.internalCalc();
            return typeThunker;
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
