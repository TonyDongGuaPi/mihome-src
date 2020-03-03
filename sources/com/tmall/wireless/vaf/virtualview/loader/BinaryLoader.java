package com.tmall.wireless.vaf.virtualview.loader;

import android.text.TextUtils;
import android.util.Log;
import com.libra.virtualview.common.Common;
import com.tmall.wireless.vaf.framework.VafContext;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BinaryLoader {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9398a = "BinaryLoader_TMTEST";
    private StringLoader b;
    private UiCodeLoader c;
    private ExprCodeLoader d;
    @Deprecated
    private int[] e;

    public void a() {
        this.b = null;
        this.d = null;
        this.c = null;
    }

    public void a(VafContext vafContext) {
        this.b = vafContext.o();
    }

    public void a(ExprCodeLoader exprCodeLoader) {
        this.d = exprCodeLoader;
    }

    public void a(UiCodeLoader uiCodeLoader) {
        this.c = uiCodeLoader;
    }

    public int a(String str) {
        int i = -1;
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            byte[] bArr = new byte[fileInputStream.available()];
            fileInputStream.read(bArr);
            int a2 = a(bArr);
            try {
                fileInputStream.close();
                return a2;
            } catch (FileNotFoundException e2) {
                FileNotFoundException fileNotFoundException = e2;
                i = a2;
                e = fileNotFoundException;
                Log.e(f9398a, "error:" + e);
                e.printStackTrace();
                return i;
            } catch (IOException e3) {
                IOException iOException = e3;
                i = a2;
                e = iOException;
                Log.e(f9398a, "error:" + e);
                e.printStackTrace();
                return i;
            }
        } catch (FileNotFoundException e4) {
            e = e4;
            Log.e(f9398a, "error:" + e);
            e.printStackTrace();
            return i;
        } catch (IOException e5) {
            e = e5;
            Log.e(f9398a, "error:" + e);
            e.printStackTrace();
            return i;
        }
    }

    public int a(byte[] bArr) {
        return a(bArr, false);
    }

    public int a(byte[] bArr, boolean z) {
        boolean z2;
        if (bArr != null) {
            this.e = null;
            if (bArr.length > 27) {
                int length = Common.f6266a.length();
                String str = new String(bArr, 0, length);
                if (TextUtils.equals(Common.f6266a, str)) {
                    CodeReader codeReader = new CodeReader();
                    codeReader.a(bArr);
                    codeReader.b(length);
                    short h = codeReader.h();
                    short h2 = codeReader.h();
                    short h3 = codeReader.h();
                    codeReader.a((int) h3);
                    if (1 == h && h2 == 0) {
                        int i = codeReader.i();
                        codeReader.b(4);
                        int i2 = codeReader.i();
                        codeReader.b(4);
                        int i3 = codeReader.i();
                        codeReader.b(4);
                        int i4 = codeReader.i();
                        codeReader.b(4);
                        short h4 = codeReader.h();
                        int h5 = codeReader.h();
                        if (h5 > 0) {
                            this.e = new int[h5];
                            for (int i5 = 0; i5 < h5; i5++) {
                                this.e[i5] = codeReader.h();
                            }
                        }
                        if (!codeReader.c(i)) {
                            return -1;
                        }
                        if (!z) {
                            z2 = this.c.a(codeReader, (int) h4, (int) h3);
                        } else {
                            z2 = this.c.b(codeReader, h4, h3);
                        }
                        if (codeReader.d() != i2) {
                            Log.e(f9398a, "string pos error:" + i2 + "  read pos:" + codeReader.d());
                        } else if (this.b != null) {
                            z2 = this.b.a(codeReader, (int) h4);
                        } else {
                            Log.e(f9398a, "mStringManager is null");
                        }
                        if (codeReader.d() != i3) {
                            Log.e(f9398a, "expr pos error:" + i3 + "  read pos:" + codeReader.d());
                        } else if (this.d != null) {
                            z2 = this.d.a(codeReader, h4);
                        } else {
                            Log.e(f9398a, "mExprCodeStore is null");
                        }
                        if (codeReader.d() != i4) {
                            Log.e(f9398a, "extra pos error:" + i4 + "  read pos:" + codeReader.d());
                        }
                        if (z2) {
                            return h4;
                        }
                        return -1;
                    }
                    Log.e(f9398a, "version dismatch");
                    return -1;
                }
                Log.e(f9398a, "loadFromBuffer failed tag is invalidate:" + str);
                return -1;
            }
            Log.e(f9398a, "file len invalidate:" + bArr.length);
            return -1;
        }
        Log.e(f9398a, "buf is null");
        return -1;
    }
}
