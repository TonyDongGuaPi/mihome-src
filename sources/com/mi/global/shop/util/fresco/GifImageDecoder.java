package com.mi.global.shop.util.fresco;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import cn.com.fmsh.tsm.business.constants.Constants;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class GifImageDecoder {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7127a = 0;
    public static final int b = 1;
    public static final int c = 2;
    private static final String n = "GifImageDecoder";
    private static final byte p = 59;
    private static final byte q = 44;
    private static final byte r = 33;
    private static final byte s = -7;
    private static final byte t = -1;
    private static final byte u = -2;
    private static final byte v = 1;
    private static final int w = 100;
    private static final int x = 20;
    private GraphicControlExtension A;
    private ImageBlock B;
    private int C;
    private int D;
    protected int d;
    protected int e;
    protected int f;
    protected Bitmap g;
    protected Bitmap h;
    protected int i = 0;
    protected int j = 0;
    protected int k = 0;
    protected ArrayList<GifFrame> l;
    protected int m;
    private final GifImageDecoder o = this;
    private int y = 0;
    private GifHeader z;

    public void a(int i2, int i3) {
        this.C = i2;
        this.D = i3;
    }

    private static class GifFrame {

        /* renamed from: a  reason: collision with root package name */
        public Bitmap f7130a;
        public int b;

        public GifFrame(Bitmap bitmap, int i) {
            this.f7130a = bitmap;
            this.b = i;
        }
    }

    public int a(int i2) {
        this.k = -1;
        if (i2 >= 0 && i2 < this.m) {
            this.k = this.l.get(i2).b;
            if (this.k < 20) {
                this.k = 100;
            }
        }
        return this.k;
    }

    public int a() {
        return this.m;
    }

    public Bitmap b() {
        return b(0);
    }

    public Bitmap b(int i2) {
        if (this.m <= 0) {
            return null;
        }
        return this.l.get(i2 % this.m).f7130a;
    }

    public int a(InputStream inputStream) throws IOException {
        c();
        if (inputStream != null) {
            byte[] a2 = GifUtils.a(inputStream);
            this.z = new GifHeader(a2, this.y);
            this.y += this.z.b;
            this.e = this.z.c();
            this.f = this.z.d();
            if (!this.z.a().equals("GIF")) {
                return 1;
            }
            while (a2[this.y] != 59) {
                if (a2[this.y] == 44) {
                    this.B = new ImageBlock(a2, this.y);
                    this.y += this.B.b;
                    this.m++;
                    this.g = e();
                    if (this.j > 0 && this.j == 3) {
                        int i2 = this.m - 2;
                        if (i2 > 0) {
                            this.h = b(i2 - 1);
                        } else {
                            this.h = null;
                        }
                    }
                    this.l.add(new GifFrame(this.g, this.k));
                    d();
                } else if (a2[this.y] != 33) {
                    throw new IOException();
                } else if (a2[this.y + 1] == -7) {
                    this.A = new GraphicControlExtension(a2, this.y);
                    this.y += this.A.b;
                    this.i = this.A.e();
                    if (this.i == 0) {
                        this.i = 1;
                    }
                    this.k = this.A.h() * 10;
                } else if (a2[this.y + 1] == -1) {
                    this.y += new ApplicationExtension(a2, this.y).b;
                } else if (a2[this.y + 1] == -2) {
                    this.y += new CommentExtension(a2, this.y).b;
                } else if (a2[this.y + 1] == 1) {
                    this.y += new PlainTextExtension(a2, this.y).b;
                } else {
                    throw new IOException();
                }
            }
        } else {
            this.d = 2;
        }
        return this.d;
    }

    /* access modifiers changed from: protected */
    public void c() {
        this.d = 0;
        this.m = 0;
        this.l = new ArrayList<>();
    }

    /* access modifiers changed from: protected */
    public void d() {
        this.j = this.i;
        this.h = this.g;
        this.i = 0;
        this.k = 0;
    }

    @SuppressLint({"NewApi"})
    private Bitmap e() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            byteArrayOutputStream.write(this.z.f7131a);
            if (this.A != null) {
                if (!(this.e == this.B.d() && this.f == this.B.e()) && this.A.g() == 0) {
                    this.A.j();
                }
                byteArrayOutputStream.write(this.A.f7132a);
            }
            byteArrayOutputStream.write(this.B.f7133a);
            byteArrayOutputStream.write(59);
            byteArrayOutputStream.flush();
            BitmapFactory.Options options = new BitmapFactory.Options();
            int i2 = 1;
            options.inMutable = true;
            options.inPreferredConfig = Bitmap.Config.RGB_565;
            int i3 = 1;
            while (this.e / i3 > this.C) {
                i3++;
            }
            while (this.f / i3 > this.D) {
                i3++;
            }
            if (i3 > 0) {
                i2 = i3;
            }
            options.inSampleSize = i2;
            Bitmap decodeStream = BitmapFactory.decodeStream(new BufferedInputStream(new ByteArrayInputStream(byteArrayOutputStream.toByteArray())), (Rect) null, options);
            if (decodeStream != null) {
                if (this.h == null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                    return decodeStream;
                }
                Bitmap createBitmap = Bitmap.createBitmap(this.C, this.D, Bitmap.Config.RGB_565);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawBitmap(this.h, 0.0f, 0.0f, (Paint) null);
                canvas.drawBitmap(decodeStream, 0.0f, 0.0f, (Paint) null);
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                return createBitmap;
            } else if (this.h != null) {
                Bitmap bitmap = this.h;
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return bitmap;
            } else {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e5) {
                    e5.printStackTrace();
                }
                return null;
            }
        } catch (IOException e6) {
            e6.printStackTrace();
            byteArrayOutputStream.close();
        } catch (Throwable th) {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e7) {
                e7.printStackTrace();
            }
            throw th;
        }
    }

    private class GifHeader {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f7131a;
        public int b;

        public GifHeader(byte[] bArr, int i) {
            int i2 = i + 10;
            boolean z = (bArr[i2] & 128) != 0;
            byte b2 = bArr[i2] & 7;
            this.b = 13;
            if (z) {
                double d = (double) this.b;
                Double.isNaN(d);
                this.b = (int) (d + (Math.pow(2.0d, (double) (b2 + 1)) * 3.0d));
            }
            this.f7131a = new byte[this.b];
            System.arraycopy(bArr, i, this.f7131a, 0, this.b);
        }

        public String a() {
            return new String(this.f7131a, 0, 3);
        }

        public String b() {
            return new String(this.f7131a, 3, 3);
        }

        public int c() {
            return (this.f7131a[6] & 255) + ((this.f7131a[7] & 255) << 8);
        }

        public int d() {
            return (this.f7131a[8] & 255) + ((this.f7131a[9] & 255) << 8);
        }

        public int e() {
            return (this.f7131a[10] & 128) >> 7;
        }

        public int f() {
            return (this.f7131a[10] & Constants.TagName.ELECTRONIC_ID) >> 4;
        }

        public int g() {
            return (this.f7131a[10] & 8) >> 3;
        }

        public int h() {
            return this.f7131a[10] & 7;
        }

        public int i() {
            return this.f7131a[11] & 255;
        }

        public int j() {
            return this.f7131a[12];
        }

        public int[] k() {
            if (e() == 0) {
                return new int[0];
            }
            int[] iArr = new int[((int) Math.pow(2.0d, (double) (h() + 1)))];
            for (int i = 0; i < iArr.length; i++) {
                int i2 = (i * 3) + 13;
                iArr[i] = ((this.f7131a[i2] & 255) << 16) + ((this.f7131a[i2 + 1] & 255) << 8) + (this.f7131a[i2 + 2] & 255);
            }
            return iArr;
        }
    }

    private class ImageBlock {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f7133a;
        public int b;

        public ImageBlock(byte[] bArr, int i) {
            int i2 = i + 9;
            boolean z = (bArr[i2] & 128) != 0;
            byte b2 = bArr[i2] & 7;
            this.b = 10;
            if (z) {
                double d = (double) this.b;
                Double.isNaN(d);
                this.b = (int) (d + (Math.pow(2.0d, (double) (b2 + 1)) * 3.0d));
            }
            this.b++;
            byte b3 = bArr[this.b + i] & 255;
            this.b++;
            while (b3 != 0) {
                this.b += b3;
                b3 = bArr[this.b + i] & 255;
                this.b++;
            }
            this.f7133a = new byte[this.b];
            System.arraycopy(bArr, i, this.f7133a, 0, this.b);
        }

        public int a() {
            return this.f7133a[0] & 255;
        }

        public int b() {
            return (this.f7133a[1] & 255) + ((this.f7133a[2] & 255) << 8);
        }

        public int c() {
            return (this.f7133a[3] & 255) + ((this.f7133a[4] & 255) << 8);
        }

        public int d() {
            return (this.f7133a[5] & 255) + ((this.f7133a[6] & 255) << 8);
        }

        public int e() {
            return (this.f7133a[7] & 255) + ((this.f7133a[8] & 255) << 8);
        }

        public int f() {
            return (this.f7133a[9] & 128) >> 7;
        }

        public int g() {
            return (this.f7133a[9] & 64) >> 6;
        }

        public int h() {
            return (this.f7133a[9] & 32) >> 5;
        }

        public int i() {
            return (this.f7133a[9] & 24) >> 2;
        }

        public int j() {
            return this.f7133a[9] & 3;
        }

        public int[] k() {
            if (f() == 0) {
                return new int[0];
            }
            int[] iArr = new int[((int) Math.pow(2.0d, (double) (j() + 1)))];
            for (int i = 0; i < iArr.length; i++) {
                int i2 = (i * 3) + 10;
                iArr[i] = ((this.f7133a[i2] & 255) << 16) + ((this.f7133a[i2 + 1] & 255) << 8) + (this.f7133a[i2 + 2] & 255);
            }
            return iArr;
        }

        public int l() {
            if (f() == 0) {
                return this.f7133a[10] & 255;
            }
            return this.f7133a[(((int) Math.pow(2.0d, (double) (j() + 1))) * 3) + 10] & 255;
        }
    }

    private class ApplicationExtension {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f7128a;
        public int b = 14;

        public ApplicationExtension(byte[] bArr, int i) {
            byte b2 = bArr[this.b + i] & 255;
            this.b++;
            while (b2 != 0) {
                this.b += b2;
                b2 = bArr[this.b + i] & 255;
                this.b++;
            }
            this.f7128a = new byte[this.b];
            System.arraycopy(bArr, i, this.f7128a, 0, this.b);
        }

        public int a() {
            return this.f7128a[0] & 255;
        }

        public int b() {
            return this.f7128a[1] & 255;
        }

        public int c() {
            return this.f7128a[2] & 255;
        }

        public String d() {
            return new String(this.f7128a, 3, 8);
        }

        public String e() {
            return new String(this.f7128a, 11, 3);
        }
    }

    private class GraphicControlExtension {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f7132a = new byte[this.b];
        public int b = 8;

        public GraphicControlExtension(byte[] bArr, int i) {
            System.arraycopy(bArr, i, this.f7132a, 0, this.b);
        }

        public int a() {
            return this.f7132a[0] & 255;
        }

        public int b() {
            return this.f7132a[1] & 255;
        }

        public int c() {
            return this.f7132a[2] & 255;
        }

        public int d() {
            return (this.f7132a[3] & 224) >> 5;
        }

        public int e() {
            return (this.f7132a[3] & 28) >> 2;
        }

        public int f() {
            return (this.f7132a[3] & 2) >> 1;
        }

        public int g() {
            return this.f7132a[3] & 1;
        }

        public int h() {
            return (this.f7132a[4] & 255) + ((this.f7132a[5] & 255) << 8);
        }

        public int i() {
            return this.f7132a[6];
        }

        public void j() {
            this.f7132a[3] = (byte) Integer.parseInt(GifUtils.a(d() | e() | f() | 1, 2), 16);
        }
    }

    private class CommentExtension {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f7129a;
        public int b = 2;

        public CommentExtension(byte[] bArr, int i) {
            byte b2 = bArr[this.b + i] & 255;
            this.b++;
            while (b2 != 0) {
                this.b += b2;
                b2 = bArr[this.b + i] & 255;
                this.b++;
            }
            this.f7129a = new byte[this.b];
            System.arraycopy(bArr, i, this.f7129a, 0, this.b);
        }
    }

    private class PlainTextExtension {

        /* renamed from: a  reason: collision with root package name */
        public byte[] f7134a;
        public int b = 15;

        public PlainTextExtension(byte[] bArr, int i) {
            byte b2 = bArr[this.b + i] & 255;
            this.b++;
            while (b2 != 0) {
                this.b += b2;
                b2 = bArr[this.b + i] & 255;
                this.b++;
            }
            this.f7134a = new byte[this.b];
            System.arraycopy(bArr, i, this.f7134a, 0, this.b);
        }
    }
}
