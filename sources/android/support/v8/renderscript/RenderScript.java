package android.support.v8.renderscript;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.SystemProperties;
import android.renderscript.RenderScript;
import android.util.Log;
import java.io.File;
import java.lang.reflect.Method;

public class RenderScript {
    private static final String CACHE_PATH = "com.android.renderscript.cache";
    static final boolean DEBUG = false;
    static final boolean LOG_ENABLED = false;
    static final String LOG_TAG = "RenderScript_jni";
    static boolean isNative = false;
    static Object lock = new Object();
    static String mCachePath;
    static Method registerNativeAllocation;
    static Method registerNativeFree;
    static boolean sInitialized;
    static Object sRuntime;
    static boolean sUseGCHooks;
    private static int thunk = 0;
    private Context mApplicationContext;
    int mContext;
    int mDev;
    Element mElement_ALLOCATION;
    Element mElement_A_8;
    Element mElement_BOOLEAN;
    Element mElement_CHAR_2;
    Element mElement_CHAR_3;
    Element mElement_CHAR_4;
    Element mElement_DOUBLE_2;
    Element mElement_DOUBLE_3;
    Element mElement_DOUBLE_4;
    Element mElement_ELEMENT;
    Element mElement_F32;
    Element mElement_F64;
    Element mElement_FLOAT_2;
    Element mElement_FLOAT_3;
    Element mElement_FLOAT_4;
    Element mElement_I16;
    Element mElement_I32;
    Element mElement_I64;
    Element mElement_I8;
    Element mElement_INT_2;
    Element mElement_INT_3;
    Element mElement_INT_4;
    Element mElement_LONG_2;
    Element mElement_LONG_3;
    Element mElement_LONG_4;
    Element mElement_MATRIX_2X2;
    Element mElement_MATRIX_3X3;
    Element mElement_MATRIX_4X4;
    Element mElement_RGBA_4444;
    Element mElement_RGBA_5551;
    Element mElement_RGBA_8888;
    Element mElement_RGB_565;
    Element mElement_RGB_888;
    Element mElement_SAMPLER;
    Element mElement_SCRIPT;
    Element mElement_SHORT_2;
    Element mElement_SHORT_3;
    Element mElement_SHORT_4;
    Element mElement_TYPE;
    Element mElement_U16;
    Element mElement_U32;
    Element mElement_U64;
    Element mElement_U8;
    Element mElement_UCHAR_2;
    Element mElement_UCHAR_3;
    Element mElement_UCHAR_4;
    Element mElement_UINT_2;
    Element mElement_UINT_3;
    Element mElement_UINT_4;
    Element mElement_ULONG_2;
    Element mElement_ULONG_3;
    Element mElement_ULONG_4;
    Element mElement_USHORT_2;
    Element mElement_USHORT_3;
    Element mElement_USHORT_4;
    RSErrorHandler mErrorCallback = null;
    RSMessageHandler mMessageCallback = null;
    MessageThread mMessageThread;
    Sampler mSampler_CLAMP_LINEAR;
    Sampler mSampler_CLAMP_LINEAR_MIP_LINEAR;
    Sampler mSampler_CLAMP_NEAREST;
    Sampler mSampler_MIRRORED_REPEAT_LINEAR;
    Sampler mSampler_MIRRORED_REPEAT_LINEAR_MIP_LINEAR;
    Sampler mSampler_MIRRORED_REPEAT_NEAREST;
    Sampler mSampler_WRAP_LINEAR;
    Sampler mSampler_WRAP_LINEAR_MIP_LINEAR;
    Sampler mSampler_WRAP_NEAREST;

    public static class RSErrorHandler implements Runnable {
        protected String mErrorMessage;
        protected int mErrorNum;

        public void run() {
        }
    }

    public static class RSMessageHandler implements Runnable {
        protected int[] mData;
        protected int mID;
        protected int mLength;

        public void run() {
        }
    }

    /* access modifiers changed from: package-private */
    public native void nContextDeinitToClient(int i);

    /* access modifiers changed from: package-private */
    public native String nContextGetErrorMessage(int i);

    /* access modifiers changed from: package-private */
    public native int nContextGetUserMessage(int i, int[] iArr);

    /* access modifiers changed from: package-private */
    public native void nContextInitToClient(int i);

    /* access modifiers changed from: package-private */
    public native int nContextPeekMessage(int i, int[] iArr);

    /* access modifiers changed from: package-private */
    public native int nDeviceCreate();

    /* access modifiers changed from: package-private */
    public native void nDeviceDestroy(int i);

    /* access modifiers changed from: package-private */
    public native void nDeviceSetConfig(int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationCopyFromBitmap(int i, int i2, Bitmap bitmap);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationCopyToBitmap(int i, int i2, Bitmap bitmap);

    /* access modifiers changed from: package-private */
    public native int rsnAllocationCreateBitmapBackedAllocation(int i, int i2, int i3, Bitmap bitmap, int i4);

    /* access modifiers changed from: package-private */
    public native int rsnAllocationCreateBitmapRef(int i, int i2, Bitmap bitmap);

    /* access modifiers changed from: package-private */
    public native int rsnAllocationCreateFromAssetStream(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native int rsnAllocationCreateFromBitmap(int i, int i2, int i3, Bitmap bitmap, int i4);

    /* access modifiers changed from: package-private */
    public native int rsnAllocationCreateTyped(int i, int i2, int i3, int i4, int i5);

    /* access modifiers changed from: package-private */
    public native int rsnAllocationCubeCreateFromBitmap(int i, int i2, int i3, Bitmap bitmap, int i4);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData1D(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData1D(int i, int i2, int i3, int i4, int i5, float[] fArr, int i6);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData1D(int i, int i2, int i3, int i4, int i5, int[] iArr, int i6);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData1D(int i, int i2, int i3, int i4, int i5, short[] sArr, int i6);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, byte[] bArr, int i9);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float[] fArr, int i9);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, int i9);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, short[] sArr, int i9);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, Bitmap bitmap);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, byte[] bArr, int i10);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, float[] fArr, int i10);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int[] iArr, int i10);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, short[] sArr, int i10);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationElementData1D(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationGenerateMipmaps(int i, int i2);

    /* access modifiers changed from: package-private */
    public native int rsnAllocationGetType(int i, int i2);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationIoReceive(int i, int i2);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationIoSend(int i, int i2);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationRead(int i, int i2, byte[] bArr);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationRead(int i, int i2, float[] fArr);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationRead(int i, int i2, int[] iArr);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationRead(int i, int i2, short[] sArr);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationResize1D(int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationResize2D(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native void rsnAllocationSyncAll(int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public native int rsnContextCreate(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native void rsnContextDestroy(int i);

    /* access modifiers changed from: package-private */
    public native void rsnContextDump(int i, int i2);

    /* access modifiers changed from: package-private */
    public native void rsnContextFinish(int i);

    /* access modifiers changed from: package-private */
    public native void rsnContextSendMessage(int i, int i2, int[] iArr);

    /* access modifiers changed from: package-private */
    public native void rsnContextSetPriority(int i, int i2);

    /* access modifiers changed from: package-private */
    public native int rsnElementCreate(int i, int i2, int i3, boolean z, int i4);

    /* access modifiers changed from: package-private */
    public native int rsnElementCreate2(int i, int[] iArr, String[] strArr, int[] iArr2);

    /* access modifiers changed from: package-private */
    public native void rsnElementGetNativeData(int i, int i2, int[] iArr);

    /* access modifiers changed from: package-private */
    public native void rsnElementGetSubElements(int i, int i2, int[] iArr, String[] strArr, int[] iArr2);

    /* access modifiers changed from: package-private */
    public native void rsnObjDestroy(int i, int i2);

    /* access modifiers changed from: package-private */
    public native int rsnSamplerCreate(int i, int i2, int i3, int i4, int i5, int i6, float f);

    /* access modifiers changed from: package-private */
    public native void rsnScriptBindAllocation(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native int rsnScriptCCreate(int i, String str, String str2, byte[] bArr, int i2);

    /* access modifiers changed from: package-private */
    public native int rsnScriptFieldIDCreate(int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public native void rsnScriptForEach(int i, int i2, int i3, int i4, int i5);

    /* access modifiers changed from: package-private */
    public native void rsnScriptForEach(int i, int i2, int i3, int i4, int i5, byte[] bArr);

    /* access modifiers changed from: package-private */
    public native void rsnScriptForEachClipped(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11);

    /* access modifiers changed from: package-private */
    public native void rsnScriptForEachClipped(int i, int i2, int i3, int i4, int i5, byte[] bArr, int i6, int i7, int i8, int i9, int i10, int i11);

    /* access modifiers changed from: package-private */
    public native int rsnScriptGroupCreate(int i, int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5);

    /* access modifiers changed from: package-private */
    public native void rsnScriptGroupExecute(int i, int i2);

    /* access modifiers changed from: package-private */
    public native void rsnScriptGroupSetInput(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native void rsnScriptGroupSetOutput(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native int rsnScriptIntrinsicCreate(int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public native void rsnScriptInvoke(int i, int i2, int i3);

    /* access modifiers changed from: package-private */
    public native void rsnScriptInvokeV(int i, int i2, int i3, byte[] bArr);

    /* access modifiers changed from: package-private */
    public native int rsnScriptKernelIDCreate(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetTimeZone(int i, int i2, byte[] bArr);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetVarD(int i, int i2, int i3, double d);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetVarF(int i, int i2, int i3, float f);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetVarI(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetVarJ(int i, int i2, int i3, long j);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetVarObj(int i, int i2, int i3, int i4);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetVarV(int i, int i2, int i3, byte[] bArr);

    /* access modifiers changed from: package-private */
    public native void rsnScriptSetVarVE(int i, int i2, int i3, byte[] bArr, int i4, int[] iArr);

    /* access modifiers changed from: package-private */
    public native int rsnTypeCreate(int i, int i2, int i3, int i4, int i5, boolean z, boolean z2, int i6);

    /* access modifiers changed from: package-private */
    public native void rsnTypeGetNativeData(int i, int i2, int[] iArr);

    static boolean shouldThunk() {
        if (thunk == 0) {
            if (Build.VERSION.SDK_INT < 18 || SystemProperties.getInt("debug.rs.forcecompat", 0) != 0) {
                thunk = -1;
            } else {
                thunk = 1;
            }
        }
        return thunk == 1;
    }

    public static void setupDiskCache(File file) {
        File file2 = new File(file, CACHE_PATH);
        mCachePath = file2.getAbsolutePath();
        file2.mkdirs();
    }

    public enum ContextType {
        NORMAL(0),
        DEBUG(1),
        PROFILE(2);
        
        int mID;

        private ContextType(int i) {
            this.mID = i;
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized int nContextCreate(int i, int i2, int i3, int i4) {
        return rsnContextCreate(i, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nContextDestroy() {
        validate();
        rsnContextDestroy(this.mContext);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nContextSetPriority(int i) {
        validate();
        rsnContextSetPriority(this.mContext, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nContextDump(int i) {
        validate();
        rsnContextDump(this.mContext, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nContextFinish() {
        validate();
        rsnContextFinish(this.mContext);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nContextSendMessage(int i, int[] iArr) {
        validate();
        rsnContextSendMessage(this.mContext, i, iArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nObjDestroy(int i) {
        if (this.mContext != 0) {
            rsnObjDestroy(this.mContext, i);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized int nElementCreate(int i, int i2, boolean z, int i3) {
        validate();
        return rsnElementCreate(this.mContext, i, i2, z, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nElementCreate2(int[] iArr, String[] strArr, int[] iArr2) {
        validate();
        return rsnElementCreate2(this.mContext, iArr, strArr, iArr2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nElementGetNativeData(int i, int[] iArr) {
        validate();
        rsnElementGetNativeData(this.mContext, i, iArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nElementGetSubElements(int i, int[] iArr, String[] strArr, int[] iArr2) {
        validate();
        rsnElementGetSubElements(this.mContext, i, iArr, strArr, iArr2);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nTypeCreate(int i, int i2, int i3, int i4, boolean z, boolean z2, int i5) {
        int rsnTypeCreate;
        synchronized (this) {
            validate();
            rsnTypeCreate = rsnTypeCreate(this.mContext, i, i2, i3, i4, z, z2, i5);
        }
        return rsnTypeCreate;
    }

    /* access modifiers changed from: package-private */
    public synchronized void nTypeGetNativeData(int i, int[] iArr) {
        validate();
        rsnTypeGetNativeData(this.mContext, i, iArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nAllocationCreateTyped(int i, int i2, int i3, int i4) {
        validate();
        return rsnAllocationCreateTyped(this.mContext, i, i2, i3, i4);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nAllocationCreateFromBitmap(int i, int i2, Bitmap bitmap, int i3) {
        validate();
        return rsnAllocationCreateFromBitmap(this.mContext, i, i2, bitmap, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nAllocationCreateBitmapBackedAllocation(int i, int i2, Bitmap bitmap, int i3) {
        validate();
        return rsnAllocationCreateBitmapBackedAllocation(this.mContext, i, i2, bitmap, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nAllocationCubeCreateFromBitmap(int i, int i2, Bitmap bitmap, int i3) {
        validate();
        return rsnAllocationCubeCreateFromBitmap(this.mContext, i, i2, bitmap, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nAllocationCreateBitmapRef(int i, Bitmap bitmap) {
        validate();
        return rsnAllocationCreateBitmapRef(this.mContext, i, bitmap);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nAllocationCreateFromAssetStream(int i, int i2, int i3) {
        validate();
        return rsnAllocationCreateFromAssetStream(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationCopyToBitmap(int i, Bitmap bitmap) {
        validate();
        rsnAllocationCopyToBitmap(this.mContext, i, bitmap);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationSyncAll(int i, int i2) {
        validate();
        rsnAllocationSyncAll(this.mContext, i, i2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationIoSend(int i) {
        validate();
        rsnAllocationIoSend(this.mContext, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationIoReceive(int i) {
        validate();
        rsnAllocationIoReceive(this.mContext, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationGenerateMipmaps(int i) {
        validate();
        rsnAllocationGenerateMipmaps(this.mContext, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationCopyFromBitmap(int i, Bitmap bitmap) {
        validate();
        rsnAllocationCopyFromBitmap(this.mContext, i, bitmap);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData1D(int i, int i2, int i3, int i4, int[] iArr, int i5) {
        validate();
        rsnAllocationData1D(this.mContext, i, i2, i3, i4, iArr, i5);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData1D(int i, int i2, int i3, int i4, short[] sArr, int i5) {
        validate();
        rsnAllocationData1D(this.mContext, i, i2, i3, i4, sArr, i5);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData1D(int i, int i2, int i3, int i4, byte[] bArr, int i5) {
        validate();
        rsnAllocationData1D(this.mContext, i, i2, i3, i4, bArr, i5);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData1D(int i, int i2, int i3, int i4, float[] fArr, int i5) {
        validate();
        rsnAllocationData1D(this.mContext, i, i2, i3, i4, fArr, i5);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationElementData1D(int i, int i2, int i3, int i4, byte[] bArr, int i5) {
        validate();
        rsnAllocationElementData1D(this.mContext, i, i2, i3, i4, bArr, i5);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12) {
        synchronized (this) {
            validate();
            rsnAllocationData2D(this.mContext, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, byte[] bArr, int i8) {
        synchronized (this) {
            validate();
            rsnAllocationData2D(this.mContext, i, i2, i3, i4, i5, i6, i7, bArr, i8);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, short[] sArr, int i8) {
        synchronized (this) {
            validate();
            rsnAllocationData2D(this.mContext, i, i2, i3, i4, i5, i6, i7, sArr, i8);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int[] iArr, int i8) {
        synchronized (this) {
            validate();
            rsnAllocationData2D(this.mContext, i, i2, i3, i4, i5, i6, i7, iArr, i8);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData2D(int i, int i2, int i3, int i4, int i5, int i6, int i7, float[] fArr, int i8) {
        synchronized (this) {
            validate();
            rsnAllocationData2D(this.mContext, i, i2, i3, i4, i5, i6, i7, fArr, i8);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData2D(int i, int i2, int i3, int i4, int i5, Bitmap bitmap) {
        validate();
        rsnAllocationData2D(this.mContext, i, i2, i3, i4, i5, bitmap);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13) {
        synchronized (this) {
            try {
                validate();
                try {
                    rsnAllocationData3D(this.mContext, i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13);
                } catch (Throwable th) {
                    th = th;
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, byte[] bArr, int i9) {
        synchronized (this) {
            validate();
            rsnAllocationData3D(this.mContext, i, i2, i3, i4, i5, i6, i7, i8, bArr, i9);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, short[] sArr, int i9) {
        synchronized (this) {
            validate();
            rsnAllocationData3D(this.mContext, i, i2, i3, i4, i5, i6, i7, i8, sArr, i9);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int[] iArr, int i9) {
        synchronized (this) {
            validate();
            rsnAllocationData3D(this.mContext, i, i2, i3, i4, i5, i6, i7, i8, iArr, i9);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationData3D(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, float[] fArr, int i9) {
        synchronized (this) {
            validate();
            rsnAllocationData3D(this.mContext, i, i2, i3, i4, i5, i6, i7, i8, fArr, i9);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationRead(int i, byte[] bArr) {
        validate();
        rsnAllocationRead(this.mContext, i, bArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationRead(int i, short[] sArr) {
        validate();
        rsnAllocationRead(this.mContext, i, sArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationRead(int i, int[] iArr) {
        validate();
        rsnAllocationRead(this.mContext, i, iArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationRead(int i, float[] fArr) {
        validate();
        rsnAllocationRead(this.mContext, i, fArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nAllocationGetType(int i) {
        validate();
        return rsnAllocationGetType(this.mContext, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationResize1D(int i, int i2) {
        validate();
        rsnAllocationResize1D(this.mContext, i, i2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nAllocationResize2D(int i, int i2, int i3) {
        validate();
        rsnAllocationResize2D(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptBindAllocation(int i, int i2, int i3) {
        validate();
        rsnScriptBindAllocation(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetTimeZone(int i, byte[] bArr) {
        validate();
        rsnScriptSetTimeZone(this.mContext, i, bArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptInvoke(int i, int i2) {
        validate();
        rsnScriptInvoke(this.mContext, i, i2);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptForEach(int i, int i2, int i3, int i4, byte[] bArr) {
        validate();
        if (bArr == null) {
            rsnScriptForEach(this.mContext, i, i2, i3, i4);
        } else {
            rsnScriptForEach(this.mContext, i, i2, i3, i4, bArr);
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptForEachClipped(int i, int i2, int i3, int i4, byte[] bArr, int i5, int i6, int i7, int i8, int i9, int i10) {
        synchronized (this) {
            validate();
            if (bArr == null) {
                rsnScriptForEachClipped(this.mContext, i, i2, i3, i4, i5, i6, i7, i8, i9, i10);
            } else {
                rsnScriptForEachClipped(this.mContext, i, i2, i3, i4, bArr, i5, i6, i7, i8, i9, i10);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptInvokeV(int i, int i2, byte[] bArr) {
        validate();
        rsnScriptInvokeV(this.mContext, i, i2, bArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetVarI(int i, int i2, int i3) {
        validate();
        rsnScriptSetVarI(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetVarJ(int i, int i2, long j) {
        validate();
        rsnScriptSetVarJ(this.mContext, i, i2, j);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetVarF(int i, int i2, float f) {
        validate();
        rsnScriptSetVarF(this.mContext, i, i2, f);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetVarD(int i, int i2, double d) {
        validate();
        rsnScriptSetVarD(this.mContext, i, i2, d);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetVarV(int i, int i2, byte[] bArr) {
        validate();
        rsnScriptSetVarV(this.mContext, i, i2, bArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetVarVE(int i, int i2, byte[] bArr, int i3, int[] iArr) {
        validate();
        rsnScriptSetVarVE(this.mContext, i, i2, bArr, i3, iArr);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptSetVarObj(int i, int i2, int i3) {
        validate();
        rsnScriptSetVarObj(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nScriptCCreate(String str, String str2, byte[] bArr, int i) {
        validate();
        return rsnScriptCCreate(this.mContext, str, str2, bArr, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nScriptIntrinsicCreate(int i, int i2) {
        validate();
        return rsnScriptIntrinsicCreate(this.mContext, i, i2);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nScriptKernelIDCreate(int i, int i2, int i3) {
        validate();
        return rsnScriptKernelIDCreate(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nScriptFieldIDCreate(int i, int i2) {
        validate();
        return rsnScriptFieldIDCreate(this.mContext, i, i2);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nScriptGroupCreate(int[] iArr, int[] iArr2, int[] iArr3, int[] iArr4, int[] iArr5) {
        validate();
        return rsnScriptGroupCreate(this.mContext, iArr, iArr2, iArr3, iArr4, iArr5);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptGroupSetInput(int i, int i2, int i3) {
        validate();
        rsnScriptGroupSetInput(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptGroupSetOutput(int i, int i2, int i3) {
        validate();
        rsnScriptGroupSetOutput(this.mContext, i, i2, i3);
    }

    /* access modifiers changed from: package-private */
    public synchronized void nScriptGroupExecute(int i) {
        validate();
        rsnScriptGroupExecute(this.mContext, i);
    }

    /* access modifiers changed from: package-private */
    public synchronized int nSamplerCreate(int i, int i2, int i3, int i4, int i5, float f) {
        validate();
        return rsnSamplerCreate(this.mContext, i, i2, i3, i4, i5, f);
    }

    public void setMessageHandler(RSMessageHandler rSMessageHandler) {
        this.mMessageCallback = rSMessageHandler;
        if (isNative) {
            ((RenderScriptThunker) this).mN.setMessageHandler(new RenderScript.RSMessageHandler() {
                public void run() {
                    RenderScript.this.mMessageCallback.mData = this.mData;
                    RenderScript.this.mMessageCallback.mID = this.mID;
                    RenderScript.this.mMessageCallback.mLength = this.mLength;
                    RenderScript.this.mMessageCallback.run();
                }
            });
        }
    }

    public RSMessageHandler getMessageHandler() {
        return this.mMessageCallback;
    }

    public void sendMessage(int i, int[] iArr) {
        nContextSendMessage(i, iArr);
    }

    public void setErrorHandler(RSErrorHandler rSErrorHandler) {
        this.mErrorCallback = rSErrorHandler;
        if (isNative) {
            ((RenderScriptThunker) this).mN.setErrorHandler(new RenderScript.RSErrorHandler() {
                public void run() {
                    RenderScript.this.mErrorCallback.mErrorMessage = this.mErrorMessage;
                    RenderScript.this.mErrorCallback.mErrorNum = this.mErrorNum;
                    RenderScript.this.mErrorCallback.run();
                }
            });
        }
    }

    public RSErrorHandler getErrorHandler() {
        return this.mErrorCallback;
    }

    public enum Priority {
        LOW(15),
        NORMAL(-4);
        
        int mID;

        private Priority(int i) {
            this.mID = i;
        }
    }

    /* access modifiers changed from: package-private */
    public void validate() {
        if (this.mContext == 0) {
            throw new RSInvalidStateException("Calling RS with no Context active.");
        }
    }

    public void setPriority(Priority priority) {
        validate();
        nContextSetPriority(priority.mID);
    }

    static class MessageThread extends Thread {
        static final int RS_ERROR_FATAL_UNKNOWN = 4096;
        static final int RS_MESSAGE_TO_CLIENT_ERROR = 3;
        static final int RS_MESSAGE_TO_CLIENT_EXCEPTION = 1;
        static final int RS_MESSAGE_TO_CLIENT_NONE = 0;
        static final int RS_MESSAGE_TO_CLIENT_RESIZE = 2;
        static final int RS_MESSAGE_TO_CLIENT_USER = 4;
        int[] mAuxData = new int[2];
        RenderScript mRS;
        boolean mRun = true;

        MessageThread(RenderScript renderScript) {
            super("RSMessageThread");
            this.mRS = renderScript;
        }

        public void run() {
            int[] iArr = new int[16];
            this.mRS.nContextInitToClient(this.mRS.mContext);
            while (this.mRun) {
                iArr[0] = 0;
                int nContextPeekMessage = this.mRS.nContextPeekMessage(this.mRS.mContext, this.mAuxData);
                int i = this.mAuxData[1];
                int i2 = this.mAuxData[0];
                if (nContextPeekMessage == 4) {
                    if ((i >> 2) >= iArr.length) {
                        iArr = new int[((i + 3) >> 2)];
                    }
                    if (this.mRS.nContextGetUserMessage(this.mRS.mContext, iArr) != 4) {
                        throw new RSDriverException("Error processing message from RenderScript.");
                    } else if (this.mRS.mMessageCallback != null) {
                        this.mRS.mMessageCallback.mData = iArr;
                        this.mRS.mMessageCallback.mID = i2;
                        this.mRS.mMessageCallback.mLength = i;
                        this.mRS.mMessageCallback.run();
                    } else {
                        throw new RSInvalidStateException("Received a message from the script with no message handler installed.");
                    }
                } else if (nContextPeekMessage == 3) {
                    String nContextGetErrorMessage = this.mRS.nContextGetErrorMessage(this.mRS.mContext);
                    if (i2 >= 4096) {
                        throw new RSRuntimeException("Fatal error " + i2 + ", details: " + nContextGetErrorMessage);
                    } else if (this.mRS.mErrorCallback != null) {
                        this.mRS.mErrorCallback.mErrorMessage = nContextGetErrorMessage;
                        this.mRS.mErrorCallback.mErrorNum = i2;
                        this.mRS.mErrorCallback.run();
                    } else {
                        Log.e(RenderScript.LOG_TAG, "non fatal RS error, " + nContextGetErrorMessage);
                    }
                } else {
                    try {
                        sleep(1, 0);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        }
    }

    RenderScript(Context context) {
        if (context != null) {
            this.mApplicationContext = context.getApplicationContext();
        }
    }

    public final Context getApplicationContext() {
        return this.mApplicationContext;
    }

    public static RenderScript create(Context context, int i) {
        return create(context, i, ContextType.NORMAL);
    }

    public static RenderScript create(Context context, int i, ContextType contextType) {
        RenderScript renderScript = new RenderScript(context);
        if (shouldThunk()) {
            Log.v(LOG_TAG, "RS native mode");
            return RenderScriptThunker.create(context, i);
        }
        synchronized (lock) {
            if (!sInitialized) {
                try {
                    Class<?> cls = Class.forName("dalvik.system.VMRuntime");
                    sRuntime = cls.getDeclaredMethod("getRuntime", new Class[0]).invoke((Object) null, new Object[0]);
                    registerNativeAllocation = cls.getDeclaredMethod("registerNativeAllocation", new Class[]{Integer.TYPE});
                    registerNativeFree = cls.getDeclaredMethod("registerNativeFree", new Class[]{Integer.TYPE});
                    sUseGCHooks = true;
                } catch (UnsatisfiedLinkError e) {
                    Log.e(LOG_TAG, "Error loading RS jni library: " + e);
                    throw new RSRuntimeException("Error loading RS jni library: " + e);
                } catch (Exception unused) {
                    Log.e(LOG_TAG, "No GC methods");
                    sUseGCHooks = false;
                }
                System.loadLibrary("RSSupport");
                System.loadLibrary("rsjni");
                sInitialized = true;
            }
        }
        Log.v(LOG_TAG, "RS compat mode");
        renderScript.mDev = renderScript.nDeviceCreate();
        renderScript.mContext = renderScript.nContextCreate(renderScript.mDev, 0, i, contextType.mID);
        if (renderScript.mContext != 0) {
            renderScript.mMessageThread = new MessageThread(renderScript);
            renderScript.mMessageThread.start();
            return renderScript;
        }
        throw new RSDriverException("Failed to create RS context.");
    }

    public static RenderScript create(Context context) {
        return create(context, ContextType.NORMAL);
    }

    public static RenderScript create(Context context, ContextType contextType) {
        return create(context, context.getApplicationInfo().targetSdkVersion, contextType);
    }

    public void contextDump() {
        validate();
        nContextDump(0);
    }

    public void finish() {
        nContextFinish();
    }

    public void destroy() {
        validate();
        nContextDeinitToClient(this.mContext);
        this.mMessageThread.mRun = false;
        try {
            this.mMessageThread.join();
        } catch (InterruptedException unused) {
        }
        nContextDestroy();
        this.mContext = 0;
        nDeviceDestroy(this.mDev);
        this.mDev = 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isAlive() {
        return this.mContext != 0;
    }

    /* access modifiers changed from: package-private */
    public int safeID(BaseObj baseObj) {
        if (baseObj != null) {
            return baseObj.getID(this);
        }
        return 0;
    }
}
