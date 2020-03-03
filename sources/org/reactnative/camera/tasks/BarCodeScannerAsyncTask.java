package org.reactnative.camera.tasks;

import android.os.AsyncTask;
import com.xiaomi.zxing.BinaryBitmap;
import com.xiaomi.zxing.MultiFormatReader;
import com.xiaomi.zxing.NotFoundException;
import com.xiaomi.zxing.PlanarYUVLuminanceSource;
import com.xiaomi.zxing.Result;
import com.xiaomi.zxing.common.HybridBinarizer;

public class BarCodeScannerAsyncTask extends AsyncTask<Void, Void, Result> {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f4156a;
    private int b;
    private int c;
    private BarCodeScannerAsyncTaskDelegate d;
    private final MultiFormatReader e;

    public BarCodeScannerAsyncTask(BarCodeScannerAsyncTaskDelegate barCodeScannerAsyncTaskDelegate, MultiFormatReader multiFormatReader, byte[] bArr, int i, int i2) {
        this.f4156a = bArr;
        this.b = i;
        this.c = i2;
        this.d = barCodeScannerAsyncTaskDelegate;
        this.e = multiFormatReader;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Result doInBackground(Void... voidArr) {
        Result b2;
        if (isCancelled() || this.d == null) {
            return null;
        }
        try {
            return this.e.b(a(this.f4156a, this.b, this.c, false));
        } catch (NotFoundException unused) {
            try {
                b2 = this.e.b(a(a(this.f4156a, this.b, this.c), this.c, this.b, false));
            } catch (NotFoundException unused2) {
                try {
                    b2 = this.e.b(a(this.f4156a, this.b, this.c, true));
                } catch (NotFoundException unused3) {
                    try {
                        b2 = this.e.b(a(a(this.f4156a, this.b, this.c), this.c, this.b, true));
                    } catch (NotFoundException unused4) {
                        return null;
                    }
                }
            }
            return b2;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    private byte[] a(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[bArr.length];
        for (int i3 = 0; i3 < i2; i3++) {
            for (int i4 = 0; i4 < i; i4++) {
                bArr2[(((i4 * i2) + i2) - i3) - 1] = bArr[(i3 * i) + i4];
            }
        }
        return bArr2;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (result != null) {
            this.d.onBarCodeRead(result, this.b, this.c);
        }
        this.d.onBarCodeScanningTaskCompleted();
    }

    private BinaryBitmap a(byte[] bArr, int i, int i2, boolean z) {
        PlanarYUVLuminanceSource planarYUVLuminanceSource = new PlanarYUVLuminanceSource(bArr, i, i2, 0, 0, i, i2, false);
        if (z) {
            return new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSource.d()));
        }
        return new BinaryBitmap(new HybridBinarizer(planarYUVLuminanceSource));
    }
}
