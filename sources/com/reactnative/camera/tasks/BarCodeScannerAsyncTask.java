package com.reactnative.camera.tasks;

import android.os.AsyncTask;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.PlanarYUVLuminanceSource;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

public class BarCodeScannerAsyncTask extends AsyncTask<Void, Void, Result> {

    /* renamed from: a  reason: collision with root package name */
    private byte[] f8679a;
    private int b;
    private int c;
    private BarCodeScannerAsyncTaskDelegate d;
    private final MultiFormatReader e;

    public BarCodeScannerAsyncTask(BarCodeScannerAsyncTaskDelegate barCodeScannerAsyncTaskDelegate, MultiFormatReader multiFormatReader, byte[] bArr, int i, int i2) {
        this.f8679a = bArr;
        this.b = i;
        this.c = i2;
        this.d = barCodeScannerAsyncTaskDelegate;
        this.e = multiFormatReader;
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public Result doInBackground(Void... voidArr) {
        if (isCancelled() || this.d == null) {
            return null;
        }
        try {
            return this.e.decodeWithState(a(this.f8679a, this.b, this.c));
        } catch (NotFoundException unused) {
            return null;
        } catch (Throwable th) {
            th.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: protected */
    /* renamed from: a */
    public void onPostExecute(Result result) {
        super.onPostExecute(result);
        if (result != null) {
            this.d.onBarCodeRead(result);
        }
        this.d.onBarCodeScanningTaskCompleted();
    }

    private BinaryBitmap a(byte[] bArr, int i, int i2) {
        return new BinaryBitmap(new HybridBinarizer(new PlanarYUVLuminanceSource(bArr, i, i2, 0, 0, i, i2, false)));
    }
}
