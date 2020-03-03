package com.xiaomi.infrared.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.util.SimpleArrayMap;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.youpin.common.util.CloseUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.json.JSONArray;
import org.json.JSONObject;

public final class CacheUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10257a = 1;
    public static final int b = 60;
    public static final int c = 3600;
    public static final int d = 86400;
    private static final long e = Long.MAX_VALUE;
    private static final int f = Integer.MAX_VALUE;
    private static final SimpleArrayMap<String, CacheUtils> g = new SimpleArrayMap<>();
    private CacheManager h;

    public static CacheUtils a() {
        return a("", Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheUtils a(String str) {
        return a(str, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheUtils a(long j, int i) {
        return a("", j, i);
    }

    public static CacheUtils a(String str, long j, int i) {
        if (j(str)) {
            str = "cacheUtils";
        }
        return a(new File(SHApplication.getAppContext().getCacheDir(), str), j, i);
    }

    public static CacheUtils a(@NonNull File file) {
        return a(file, Long.MAX_VALUE, Integer.MAX_VALUE);
    }

    public static CacheUtils a(@NonNull File file, long j, int i) {
        String str = file.getAbsoluteFile() + JSMethod.NOT_SET + Process.myPid();
        CacheUtils cacheUtils = g.get(str);
        if (cacheUtils != null) {
            return cacheUtils;
        }
        CacheUtils cacheUtils2 = new CacheUtils(file, j, i);
        g.put(str, cacheUtils2);
        return cacheUtils2;
    }

    private CacheUtils(@NonNull File file, long j, int i) {
        if (file.exists() || file.mkdirs()) {
            this.h = new CacheManager(file, j, i);
            return;
        }
        throw new RuntimeException("can't make dirs in " + file.getAbsolutePath());
    }

    public void a(@NonNull String str, @NonNull byte[] bArr) {
        a(str, bArr, -1);
    }

    public void a(@NonNull String str, @NonNull byte[] bArr, int i) {
        if (bArr.length > 0) {
            if (i >= 0) {
                bArr = CacheHelper.b(i, bArr);
            }
            File a2 = this.h.a(str);
            CacheHelper.b(a2, bArr);
            this.h.b(a2);
            this.h.a(a2);
        }
    }

    public byte[] b(@NonNull String str) {
        return b(str, (byte[]) null);
    }

    public byte[] b(@NonNull String str, byte[] bArr) {
        File b2 = this.h.b(str);
        if (b2 == null) {
            return bArr;
        }
        byte[] a2 = CacheHelper.b(b2);
        if (CacheHelper.i(a2)) {
            boolean unused = this.h.c(str);
            return bArr;
        }
        this.h.b(b2);
        return CacheHelper.k(a2);
    }

    public void a(@NonNull String str, @NonNull String str2) {
        a(str, str2, -1);
    }

    public void a(@NonNull String str, @NonNull String str2, int i) {
        a(str, CacheHelper.b(str2), i);
    }

    public String c(@NonNull String str) {
        return b(str, (String) null);
    }

    public String b(@NonNull String str, String str2) {
        byte[] b2 = b(str);
        if (b2 == null) {
            return str2;
        }
        return CacheHelper.m(b2);
    }

    public void a(@NonNull String str, @NonNull JSONObject jSONObject) {
        a(str, jSONObject, -1);
    }

    public void a(@NonNull String str, @NonNull JSONObject jSONObject, int i) {
        a(str, CacheHelper.b(jSONObject), i);
    }

    public JSONObject d(@NonNull String str) {
        return b(str, (JSONObject) null);
    }

    public JSONObject b(@NonNull String str, JSONObject jSONObject) {
        byte[] b2 = b(str);
        if (b2 == null) {
            return jSONObject;
        }
        return CacheHelper.n(b2);
    }

    public void a(@NonNull String str, @NonNull JSONArray jSONArray) {
        a(str, jSONArray, -1);
    }

    public void a(@NonNull String str, @NonNull JSONArray jSONArray, int i) {
        a(str, CacheHelper.b(jSONArray), i);
    }

    public JSONArray e(@NonNull String str) {
        return b(str, (JSONArray) null);
    }

    public JSONArray b(@NonNull String str, JSONArray jSONArray) {
        byte[] b2 = b(str);
        if (b2 == null) {
            return jSONArray;
        }
        return CacheHelper.o(b2);
    }

    public void a(@NonNull String str, @NonNull Bitmap bitmap) {
        a(str, bitmap, -1);
    }

    public void a(@NonNull String str, @NonNull Bitmap bitmap, int i) {
        a(str, CacheHelper.b(bitmap), i);
    }

    public Bitmap f(@NonNull String str) {
        return b(str, (Bitmap) null);
    }

    public Bitmap b(@NonNull String str, Bitmap bitmap) {
        byte[] b2 = b(str);
        if (b2 == null) {
            return bitmap;
        }
        return CacheHelper.q(b2);
    }

    public void a(@NonNull String str, @NonNull Drawable drawable) {
        a(str, CacheHelper.b(drawable));
    }

    public void a(@NonNull String str, @NonNull Drawable drawable, int i) {
        a(str, CacheHelper.b(drawable), i);
    }

    public Drawable g(@NonNull String str) {
        return b(str, (Drawable) null);
    }

    public Drawable b(@NonNull String str, Drawable drawable) {
        byte[] b2 = b(str);
        if (b2 == null) {
            return drawable;
        }
        return CacheHelper.r(b2);
    }

    public void a(@NonNull String str, @NonNull Parcelable parcelable) {
        a(str, parcelable, -1);
    }

    public void a(@NonNull String str, @NonNull Parcelable parcelable, int i) {
        a(str, CacheHelper.b(parcelable), i);
    }

    public <T> T a(@NonNull String str, @NonNull Parcelable.Creator<T> creator) {
        return a(str, creator, (Object) null);
    }

    public <T> T a(@NonNull String str, @NonNull Parcelable.Creator<T> creator, T t) {
        byte[] b2 = b(str);
        if (b2 == null) {
            return t;
        }
        return CacheHelper.b(b2, creator);
    }

    public void a(@NonNull String str, @NonNull Serializable serializable) {
        a(str, serializable, -1);
    }

    public void a(@NonNull String str, @NonNull Serializable serializable, int i) {
        a(str, CacheHelper.b(serializable), i);
    }

    public Object h(@NonNull String str) {
        return a(str, (Object) null);
    }

    public Object a(@NonNull String str, Object obj) {
        if (b(str) == null) {
            return obj;
        }
        return CacheHelper.p(b(str));
    }

    public long b() {
        return this.h.a();
    }

    public int c() {
        return this.h.b();
    }

    public boolean i(@NonNull String str) {
        return this.h.c(str);
    }

    public boolean d() {
        return this.h.c();
    }

    private class CacheManager {
        /* access modifiers changed from: private */
        public final AtomicLong b;
        /* access modifiers changed from: private */
        public final AtomicInteger c;
        private final long d;
        private final int e;
        /* access modifiers changed from: private */
        public final Map<File, Long> f;
        private final File g;
        private final Thread h;

        private CacheManager(final File file, long j, int i) {
            this.f = Collections.synchronizedMap(new HashMap());
            this.g = file;
            this.d = j;
            this.e = i;
            this.b = new AtomicLong();
            this.c = new AtomicInteger();
            this.h = new Thread(new Runnable(CacheUtils.this) {
                public void run() {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        int i2 = 0;
                        for (File file : listFiles) {
                            i = (int) (((long) i) + file.length());
                            i2++;
                            CacheManager.this.f.put(file, Long.valueOf(file.lastModified()));
                        }
                        CacheManager.this.b.getAndAdd((long) i);
                        CacheManager.this.c.getAndAdd(i2);
                    }
                }
            });
            this.h.start();
        }

        /* access modifiers changed from: private */
        public long a() {
            try {
                this.h.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            return this.b.get();
        }

        /* access modifiers changed from: private */
        public int b() {
            try {
                this.h.join();
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            return this.c.get();
        }

        /* access modifiers changed from: private */
        public File a(String str) {
            File file = new File(this.g, String.valueOf(str.hashCode()));
            if (file.exists()) {
                this.c.addAndGet(-1);
                this.b.addAndGet(-file.length());
            }
            return file;
        }

        /* access modifiers changed from: private */
        public File b(String str) {
            File file = new File(this.g, String.valueOf(str.hashCode()));
            if (!file.exists()) {
                return null;
            }
            return file;
        }

        /* access modifiers changed from: private */
        public void a(File file) {
            this.c.addAndGet(1);
            this.b.addAndGet(file.length());
            while (true) {
                if (this.c.get() > this.e || this.b.get() > this.d) {
                    this.b.addAndGet(-d());
                    this.c.addAndGet(-1);
                } else {
                    return;
                }
            }
        }

        /* access modifiers changed from: private */
        public void b(File file) {
            Long valueOf = Long.valueOf(System.currentTimeMillis());
            file.setLastModified(valueOf.longValue());
            this.f.put(file, valueOf);
        }

        /* access modifiers changed from: private */
        public boolean c(String str) {
            File b2 = b(str);
            if (b2 == null) {
                return true;
            }
            if (!b2.delete()) {
                return false;
            }
            this.b.addAndGet(-b2.length());
            this.c.addAndGet(-1);
            this.f.remove(b2);
            return true;
        }

        /* access modifiers changed from: private */
        public boolean c() {
            File[] listFiles = this.g.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return true;
            }
            boolean z = true;
            for (File file : listFiles) {
                if (!file.delete()) {
                    z = false;
                } else {
                    this.b.addAndGet(-file.length());
                    this.c.addAndGet(-1);
                    this.f.remove(file);
                }
            }
            if (z) {
                this.f.clear();
                this.b.set(0);
                this.c.set(0);
            }
            return z;
        }

        private long d() {
            if (this.f.isEmpty()) {
                return 0;
            }
            Long l = Long.MAX_VALUE;
            File file = null;
            Set<Map.Entry<File, Long>> entrySet = this.f.entrySet();
            synchronized (this.f) {
                for (Map.Entry next : entrySet) {
                    Long l2 = (Long) next.getValue();
                    if (l2.longValue() < l.longValue()) {
                        file = (File) next.getKey();
                        l = l2;
                    }
                }
            }
            if (file == null) {
                return 0;
            }
            long length = file.length();
            if (!file.delete()) {
                return 0;
            }
            this.f.remove(file);
            return length;
        }
    }

    private static class CacheHelper {

        /* renamed from: a  reason: collision with root package name */
        static final int f10258a = 14;

        private CacheHelper() {
        }

        /* access modifiers changed from: private */
        public static byte[] b(int i, byte[] bArr) {
            byte[] bytes = a(i).getBytes();
            byte[] bArr2 = new byte[(bytes.length + bArr.length)];
            System.arraycopy(bytes, 0, bArr2, 0, bytes.length);
            System.arraycopy(bArr, 0, bArr2, bytes.length, bArr.length);
            return bArr2;
        }

        private static String a(int i) {
            return String.format(Locale.getDefault(), "_$%010d$_", new Object[]{Long.valueOf((System.currentTimeMillis() / 1000) + ((long) i))});
        }

        /* access modifiers changed from: private */
        public static boolean i(byte[] bArr) {
            long j = j(bArr);
            return j != -1 && System.currentTimeMillis() > j;
        }

        private static long j(byte[] bArr) {
            if (!l(bArr)) {
                return -1;
            }
            try {
                return Long.parseLong(new String(a(bArr, 2, 12))) * 1000;
            } catch (NumberFormatException unused) {
                return -1;
            }
        }

        /* access modifiers changed from: private */
        public static byte[] k(byte[] bArr) {
            return l(bArr) ? a(bArr, 14, bArr.length) : bArr;
        }

        private static byte[] a(byte[] bArr, int i, int i2) {
            int i3 = i2 - i;
            if (i3 >= 0) {
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, Math.min(bArr.length - i, i3));
                return bArr2;
            }
            throw new IllegalArgumentException(i + " > " + i2);
        }

        private static boolean l(byte[] bArr) {
            return bArr != null && bArr.length >= 14 && bArr[0] == 95 && bArr[1] == 36 && bArr[12] == 36 && bArr[13] == 95;
        }

        /* access modifiers changed from: private */
        public static void b(File file, byte[] bArr) {
            FileChannel fileChannel = null;
            try {
                FileChannel channel = new FileOutputStream(file, false).getChannel();
                try {
                    channel.write(ByteBuffer.wrap(bArr));
                    channel.force(true);
                    CloseUtils.a(channel);
                } catch (IOException e) {
                    e = e;
                    fileChannel = channel;
                    try {
                        e.printStackTrace();
                        CloseUtils.a(fileChannel);
                    } catch (Throwable th) {
                        th = th;
                        CloseUtils.a(fileChannel);
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    fileChannel = channel;
                    CloseUtils.a(fileChannel);
                    throw th;
                }
            } catch (IOException e2) {
                e = e2;
                e.printStackTrace();
                CloseUtils.a(fileChannel);
            }
        }

        /* access modifiers changed from: private */
        public static byte[] b(File file) {
            FileChannel fileChannel;
            try {
                fileChannel = new RandomAccessFile(file, "r").getChannel();
                try {
                    int size = (int) fileChannel.size();
                    byte[] bArr = new byte[size];
                    fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, (long) size).load().get(bArr, 0, size);
                    CloseUtils.a(fileChannel);
                    return bArr;
                } catch (IOException e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        CloseUtils.a(fileChannel);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        CloseUtils.a(fileChannel);
                        throw th;
                    }
                }
            } catch (IOException e2) {
                e = e2;
                fileChannel = null;
                e.printStackTrace();
                CloseUtils.a(fileChannel);
                return null;
            } catch (Throwable th2) {
                th = th2;
                fileChannel = null;
                CloseUtils.a(fileChannel);
                throw th;
            }
        }

        /* access modifiers changed from: private */
        public static byte[] b(String str) {
            if (str == null) {
                return null;
            }
            return str.getBytes();
        }

        /* access modifiers changed from: private */
        public static String m(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            return new String(bArr);
        }

        /* access modifiers changed from: private */
        public static byte[] b(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            return jSONObject.toString().getBytes();
        }

        /* access modifiers changed from: private */
        public static JSONObject n(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            try {
                return new JSONObject(new String(bArr));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: private */
        public static byte[] b(JSONArray jSONArray) {
            if (jSONArray == null) {
                return null;
            }
            return jSONArray.toString().getBytes();
        }

        /* access modifiers changed from: private */
        public static JSONArray o(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            try {
                return new JSONArray(new String(bArr));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        /* access modifiers changed from: private */
        public static byte[] b(Parcelable parcelable) {
            if (parcelable == null) {
                return null;
            }
            Parcel obtain = Parcel.obtain();
            parcelable.writeToParcel(obtain, 0);
            byte[] marshall = obtain.marshall();
            obtain.recycle();
            return marshall;
        }

        /* access modifiers changed from: private */
        public static <T> T b(byte[] bArr, Parcelable.Creator<T> creator) {
            if (bArr == null) {
                return null;
            }
            Parcel obtain = Parcel.obtain();
            obtain.unmarshall(bArr, 0, bArr.length);
            obtain.setDataPosition(0);
            T createFromParcel = creator.createFromParcel(obtain);
            obtain.recycle();
            return createFromParcel;
        }

        /* access modifiers changed from: private */
        public static byte[] b(Serializable serializable) {
            ObjectOutputStream objectOutputStream;
            ObjectOutputStream objectOutputStream2 = null;
            if (serializable == null) {
                return null;
            }
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                try {
                    objectOutputStream.writeObject(serializable);
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    CloseUtils.a(objectOutputStream);
                    return byteArray;
                } catch (Exception e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        CloseUtils.a(objectOutputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        objectOutputStream2 = objectOutputStream;
                        CloseUtils.a(objectOutputStream2);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                objectOutputStream = null;
                e.printStackTrace();
                CloseUtils.a(objectOutputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                CloseUtils.a(objectOutputStream2);
                throw th;
            }
        }

        /* access modifiers changed from: private */
        public static Object p(byte[] bArr) {
            ObjectInputStream objectInputStream;
            ObjectInputStream objectInputStream2 = null;
            if (bArr == null) {
                return null;
            }
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(bArr));
                try {
                    Object readObject = objectInputStream.readObject();
                    CloseUtils.a(objectInputStream);
                    return readObject;
                } catch (Exception e) {
                    e = e;
                    try {
                        e.printStackTrace();
                        CloseUtils.a(objectInputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        objectInputStream2 = objectInputStream;
                        CloseUtils.a(objectInputStream2);
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
                objectInputStream = null;
                e.printStackTrace();
                CloseUtils.a(objectInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                CloseUtils.a(objectInputStream2);
                throw th;
            }
        }

        /* access modifiers changed from: private */
        public static byte[] b(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }

        /* access modifiers changed from: private */
        public static Bitmap q(byte[] bArr) {
            if (bArr == null || bArr.length == 0) {
                return null;
            }
            return BitmapFactory.decodeByteArray(bArr, 0, bArr.length);
        }

        /* access modifiers changed from: private */
        public static byte[] b(Drawable drawable) {
            if (drawable == null) {
                return null;
            }
            return b(c(drawable));
        }

        /* access modifiers changed from: private */
        public static Drawable r(byte[] bArr) {
            if (bArr == null) {
                return null;
            }
            return c(q(bArr));
        }

        private static Bitmap c(Drawable drawable) {
            Bitmap bitmap;
            if (drawable instanceof BitmapDrawable) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
                if (bitmapDrawable.getBitmap() != null) {
                    return bitmapDrawable.getBitmap();
                }
            }
            if (drawable.getIntrinsicWidth() <= 0 || drawable.getIntrinsicHeight() <= 0) {
                bitmap = Bitmap.createBitmap(1, 1, drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            } else {
                bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), drawable.getOpacity() != -1 ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
            }
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
            return bitmap;
        }

        private static Drawable c(Bitmap bitmap) {
            if (bitmap == null) {
                return null;
            }
            return new BitmapDrawable(SHApplication.getAppContext().getResources(), bitmap);
        }
    }

    private static boolean j(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
