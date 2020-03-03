package com.alipay.zoloz.toyger.face;

import android.graphics.Bitmap;
import android.util.Log;
import com.alipay.zoloz.toyger.algorithm.TGDepthFrame;
import com.alipay.zoloz.toyger.algorithm.TGFrame;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

class FrameProcessor {
    public static final SimpleDateFormat DATE_FORMAT2 = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss-SSS", Locale.US);

    /* renamed from: a  reason: collision with root package name */
    private TGFrame f1203a;
    private TGDepthFrame b;

    public static void saveBestBitmap(Bitmap bitmap) {
    }

    static void saveBitmap(TGFrame tGFrame, TGDepthFrame tGDepthFrame, ToygerFaceAttr toygerFaceAttr, String str) {
    }

    public static String getDetailDateFormat(long j) {
        String format;
        synchronized (DATE_FORMAT2) {
            format = DATE_FORMAT2.format(new Date(j));
        }
        return format;
    }

    /* access modifiers changed from: package-private */
    public void initFame(TGFrame tGFrame, TGDepthFrame tGDepthFrame) {
        this.f1203a = tGFrame;
        this.b = tGDepthFrame;
    }

    public TGFrame getTgFrame() {
        return this.f1203a;
    }

    public TGDepthFrame getTgDepthFrame() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void saveBitmap(ToygerFaceAttr toygerFaceAttr, String str) {
        saveBitmap(this.f1203a, this.b, toygerFaceAttr, str);
    }

    public static void bitmap2File(Bitmap bitmap, File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 90, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            Log.w("FrameProcessor", e);
        } catch (IOException e2) {
            Log.w("FrameProcessor", e2);
        }
    }

    public static synchronized boolean save(File file, byte[] bArr) {
        boolean z;
        FileOutputStream fileOutputStream;
        synchronized (FrameProcessor.class) {
            z = false;
            if (!(file == null || bArr == null)) {
                if (file.exists()) {
                    file.delete();
                } else {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        Log.w("FrameProcessor", e);
                    }
                }
                BufferedOutputStream bufferedOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        BufferedOutputStream bufferedOutputStream2 = new BufferedOutputStream(fileOutputStream);
                        try {
                            bufferedOutputStream2.write(bArr);
                            bufferedOutputStream2.flush();
                            z = true;
                            close(bufferedOutputStream2);
                        } catch (IOException e2) {
                            e = e2;
                            bufferedOutputStream = bufferedOutputStream2;
                            try {
                                Log.w("FrameProcessor", e);
                                close(bufferedOutputStream);
                                close(fileOutputStream);
                                return z;
                            } catch (Throwable th) {
                                th = th;
                                close(bufferedOutputStream);
                                close(fileOutputStream);
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            bufferedOutputStream = bufferedOutputStream2;
                            close(bufferedOutputStream);
                            close(fileOutputStream);
                            throw th;
                        }
                    } catch (IOException e3) {
                        e = e3;
                        Log.w("FrameProcessor", e);
                        close(bufferedOutputStream);
                        close(fileOutputStream);
                        return z;
                    }
                } catch (IOException e4) {
                    e = e4;
                    fileOutputStream = null;
                    Log.w("FrameProcessor", e);
                    close(bufferedOutputStream);
                    close(fileOutputStream);
                    return z;
                } catch (Throwable th3) {
                    th = th3;
                    fileOutputStream = null;
                    close(bufferedOutputStream);
                    close(fileOutputStream);
                    throw th;
                }
                close(fileOutputStream);
            }
        }
        return z;
    }

    public static void close(OutputStream outputStream) {
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                Log.e("FrameProcessor", e.toString());
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void clearFrame() {
        this.f1203a = null;
        this.b = null;
    }
}
