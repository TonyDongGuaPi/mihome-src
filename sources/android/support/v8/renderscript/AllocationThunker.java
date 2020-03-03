package android.support.v8.renderscript;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.renderscript.Allocation;
import android.renderscript.BaseObj;
import android.renderscript.Element;
import android.renderscript.FieldPacker;
import android.renderscript.RSRuntimeException;
import android.support.v8.renderscript.Allocation;

class AllocationThunker extends Allocation {
    static BitmapFactory.Options mBitmapOptions = new BitmapFactory.Options();
    Allocation mN;

    /* access modifiers changed from: package-private */
    public Allocation getNObj() {
        return this.mN;
    }

    static Allocation.MipmapControl convertMipmapControl(Allocation.MipmapControl mipmapControl) {
        switch (mipmapControl) {
            case MIPMAP_NONE:
                return Allocation.MipmapControl.MIPMAP_NONE;
            case MIPMAP_FULL:
                return Allocation.MipmapControl.MIPMAP_FULL;
            case MIPMAP_ON_SYNC_TO_TEXTURE:
                return Allocation.MipmapControl.MIPMAP_ON_SYNC_TO_TEXTURE;
            default:
                return null;
        }
    }

    public Type getType() {
        return TypeThunker.find(this.mN.getType());
    }

    public Element getElement() {
        return getType().getElement();
    }

    public int getUsage() {
        try {
            return this.mN.getUsage();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public int getBytesSize() {
        try {
            return this.mN.getBytesSize();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    AllocationThunker(RenderScript renderScript, Type type, int i, android.renderscript.Allocation allocation) {
        super(0, renderScript, type, i);
        this.mType = type;
        this.mUsage = i;
        this.mN = allocation;
    }

    public void syncAll(int i) {
        try {
            this.mN.syncAll(i);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void ioSend() {
        try {
            this.mN.ioSend();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void ioReceive() {
        try {
            this.mN.ioReceive();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFrom(BaseObj[] baseObjArr) {
        if (baseObjArr != null) {
            BaseObj[] baseObjArr2 = new BaseObj[baseObjArr.length];
            for (int i = 0; i < baseObjArr.length; i++) {
                baseObjArr2[i] = baseObjArr[i].getNObj();
            }
            try {
                this.mN.copyFrom(baseObjArr2);
            } catch (RSRuntimeException e) {
                throw ExceptionThunker.convertException(e);
            }
        }
    }

    public void copyFromUnchecked(int[] iArr) {
        try {
            this.mN.copyFromUnchecked(iArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFromUnchecked(short[] sArr) {
        try {
            this.mN.copyFromUnchecked(sArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFromUnchecked(byte[] bArr) {
        try {
            this.mN.copyFromUnchecked(bArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFromUnchecked(float[] fArr) {
        try {
            this.mN.copyFromUnchecked(fArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFrom(int[] iArr) {
        try {
            this.mN.copyFrom(iArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFrom(short[] sArr) {
        try {
            this.mN.copyFrom(sArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFrom(byte[] bArr) {
        try {
            this.mN.copyFrom(bArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFrom(float[] fArr) {
        try {
            this.mN.copyFrom(fArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFrom(Bitmap bitmap) {
        try {
            this.mN.copyFrom(bitmap);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyFrom(Allocation allocation) {
        try {
            this.mN.copyFrom(((AllocationThunker) allocation).mN);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setFromFieldPacker(int i, FieldPacker fieldPacker) {
        try {
            this.mN.setFromFieldPacker(i, new FieldPacker(fieldPacker.getData()));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void setFromFieldPacker(int i, int i2, FieldPacker fieldPacker) {
        try {
            this.mN.setFromFieldPacker(i, i2, new FieldPacker(fieldPacker.getData()));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void generateMipmaps() {
        try {
            this.mN.generateMipmaps();
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int i2, int[] iArr) {
        try {
            this.mN.copy1DRangeFromUnchecked(i, i2, iArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int i2, short[] sArr) {
        try {
            this.mN.copy1DRangeFromUnchecked(i, i2, sArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int i2, byte[] bArr) {
        try {
            this.mN.copy1DRangeFromUnchecked(i, i2, bArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFromUnchecked(int i, int i2, float[] fArr) {
        try {
            this.mN.copy1DRangeFromUnchecked(i, i2, fArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFrom(int i, int i2, int[] iArr) {
        try {
            this.mN.copy1DRangeFrom(i, i2, iArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFrom(int i, int i2, short[] sArr) {
        try {
            this.mN.copy1DRangeFrom(i, i2, sArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFrom(int i, int i2, byte[] bArr) {
        try {
            this.mN.copy1DRangeFrom(i, i2, bArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFrom(int i, int i2, float[] fArr) {
        try {
            this.mN.copy1DRangeFrom(i, i2, fArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy1DRangeFrom(int i, int i2, Allocation allocation, int i3) {
        try {
            this.mN.copy1DRangeFrom(i, i2, ((AllocationThunker) allocation).mN, i3);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy2DRangeFrom(int i, int i2, int i3, int i4, byte[] bArr) {
        try {
            this.mN.copy2DRangeFrom(i, i2, i3, i4, bArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy2DRangeFrom(int i, int i2, int i3, int i4, short[] sArr) {
        try {
            this.mN.copy2DRangeFrom(i, i2, i3, i4, sArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy2DRangeFrom(int i, int i2, int i3, int i4, int[] iArr) {
        try {
            this.mN.copy2DRangeFrom(i, i2, i3, i4, iArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy2DRangeFrom(int i, int i2, int i3, int i4, float[] fArr) {
        try {
            this.mN.copy2DRangeFrom(i, i2, i3, i4, fArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy2DRangeFrom(int i, int i2, int i3, int i4, Allocation allocation, int i5, int i6) {
        try {
            this.mN.copy2DRangeFrom(i, i2, i3, i4, ((AllocationThunker) allocation).mN, i5, i6);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copy2DRangeFrom(int i, int i2, Bitmap bitmap) {
        try {
            this.mN.copy2DRangeFrom(i, i2, bitmap);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyTo(Bitmap bitmap) {
        try {
            this.mN.copyTo(bitmap);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyTo(byte[] bArr) {
        try {
            this.mN.copyTo(bArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyTo(short[] sArr) {
        try {
            this.mN.copyTo(sArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyTo(int[] iArr) {
        try {
            this.mN.copyTo(iArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public void copyTo(float[] fArr) {
        try {
            this.mN.copyTo(fArr);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    static {
        mBitmapOptions.inScaled = false;
    }

    public static Allocation createTyped(RenderScript renderScript, Type type, Allocation.MipmapControl mipmapControl, int i) {
        try {
            return new AllocationThunker(renderScript, type, i, android.renderscript.Allocation.createTyped(((RenderScriptThunker) renderScript).mN, ((TypeThunker) type).mN, convertMipmapControl(mipmapControl), i));
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Allocation createFromBitmap(RenderScript renderScript, Bitmap bitmap, Allocation.MipmapControl mipmapControl, int i) {
        try {
            android.renderscript.Allocation createFromBitmap = android.renderscript.Allocation.createFromBitmap(((RenderScriptThunker) renderScript).mN, bitmap, convertMipmapControl(mipmapControl), i);
            return new AllocationThunker(renderScript, new TypeThunker(renderScript, createFromBitmap.getType()), i, createFromBitmap);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Allocation createCubemapFromBitmap(RenderScript renderScript, Bitmap bitmap, Allocation.MipmapControl mipmapControl, int i) {
        try {
            android.renderscript.Allocation createCubemapFromBitmap = android.renderscript.Allocation.createCubemapFromBitmap(((RenderScriptThunker) renderScript).mN, bitmap, convertMipmapControl(mipmapControl), i);
            return new AllocationThunker(renderScript, new TypeThunker(renderScript, createCubemapFromBitmap.getType()), i, createCubemapFromBitmap);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Allocation createCubemapFromCubeFaces(RenderScript renderScript, Bitmap bitmap, Bitmap bitmap2, Bitmap bitmap3, Bitmap bitmap4, Bitmap bitmap5, Bitmap bitmap6, Allocation.MipmapControl mipmapControl, int i) {
        try {
            android.renderscript.Allocation createCubemapFromCubeFaces = android.renderscript.Allocation.createCubemapFromCubeFaces(((RenderScriptThunker) renderScript).mN, bitmap, bitmap2, bitmap3, bitmap4, bitmap5, bitmap6, convertMipmapControl(mipmapControl), i);
            return new AllocationThunker(renderScript, new TypeThunker(renderScript, createCubemapFromCubeFaces.getType()), i, createCubemapFromCubeFaces);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Allocation createFromBitmapResource(RenderScript renderScript, Resources resources, int i, Allocation.MipmapControl mipmapControl, int i2) {
        try {
            android.renderscript.Allocation createFromBitmapResource = android.renderscript.Allocation.createFromBitmapResource(((RenderScriptThunker) renderScript).mN, resources, i, convertMipmapControl(mipmapControl), i2);
            return new AllocationThunker(renderScript, new TypeThunker(renderScript, createFromBitmapResource.getType()), i2, createFromBitmapResource);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Allocation createFromString(RenderScript renderScript, String str, int i) {
        try {
            android.renderscript.Allocation createFromString = android.renderscript.Allocation.createFromString(((RenderScriptThunker) renderScript).mN, str, i);
            return new AllocationThunker(renderScript, new TypeThunker(renderScript, createFromString.getType()), i, createFromString);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }

    public static Allocation createSized(RenderScript renderScript, Element element, int i, int i2) {
        ElementThunker elementThunker = (ElementThunker) element;
        try {
            android.renderscript.Allocation createSized = android.renderscript.Allocation.createSized(((RenderScriptThunker) renderScript).mN, (Element) element.getNObj(), i, i2);
            return new AllocationThunker(renderScript, new TypeThunker(renderScript, createSized.getType()), i2, createSized);
        } catch (RSRuntimeException e) {
            throw ExceptionThunker.convertException(e);
        }
    }
}
