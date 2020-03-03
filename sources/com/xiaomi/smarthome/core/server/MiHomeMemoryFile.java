package com.xiaomi.smarthome.core.server;

import android.os.MemoryFile;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;
import com.xiaomi.smarthome.library.common.ApiHelper;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Random;

public class MiHomeMemoryFile implements Parcelable {
    public static final Parcelable.Creator<MiHomeMemoryFile> CREATOR = new Parcelable.Creator<MiHomeMemoryFile>() {
        /* renamed from: a */
        public MiHomeMemoryFile createFromParcel(Parcel parcel) {
            return new MiHomeMemoryFile(parcel);
        }

        /* renamed from: a */
        public MiHomeMemoryFile[] newArray(int i) {
            return new MiHomeMemoryFile[i];
        }
    };

    /* renamed from: a  reason: collision with root package name */
    Object f14051a;
    MemoryFile b;
    Method c;
    Method d;
    Method e;

    public int describeContents() {
        return 0;
    }

    protected MiHomeMemoryFile(Parcel parcel) {
        if (ApiHelper.b) {
            a();
            this.f14051a = parcel.readParcelable(getClass().getClassLoader());
            return;
        }
        this.b = MemoryFileHelper.a((ParcelFileDescriptor) parcel.readParcelable(ParcelFileDescriptor.class.getClassLoader()), parcel.readInt(), 1);
    }

    public MiHomeMemoryFile() {
        if (ApiHelper.b) {
            a();
        }
    }

    /* access modifiers changed from: package-private */
    public void a() {
        try {
            Class<?> cls = Class.forName("android.os.SharedMemory");
            if (cls != null) {
                cls.getDeclaredMethods();
                this.e = cls.getDeclaredMethod("create", new Class[]{String.class, Integer.TYPE});
                this.d = cls.getDeclaredMethod("mapReadWrite", new Class[0]);
                this.c = cls.getDeclaredMethod("close", new Class[0]);
            }
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodException e3) {
            e3.printStackTrace();
        }
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        super.finalize();
        c();
    }

    public void a(byte[] bArr) {
        if (ApiHelper.b) {
            try {
                Method method = this.e;
                this.f14051a = method.invoke((Object) null, new Object[]{"shared-" + new Random().nextInt(100), Integer.valueOf(bArr.length)});
                ((ByteBuffer) this.d.invoke(this.f14051a, new Object[0])).put(bArr);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                this.b = new MemoryFile("shared-" + new Random().nextInt(100), bArr.length);
                this.b.writeBytes(bArr, 0, 0, bArr.length);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public Parcel b() {
        Parcel obtain = Parcel.obtain();
        if (ApiHelper.b) {
            try {
                ByteBuffer byteBuffer = (ByteBuffer) this.d.invoke(this.f14051a, new Object[0]);
                byte[] bArr = new byte[byteBuffer.remaining()];
                byteBuffer.get(bArr, 0, bArr.length);
                obtain.setDataCapacity(bArr.length);
                obtain.unmarshall(bArr, 0, bArr.length);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } else {
            try {
                obtain.setDataCapacity(this.b.length());
                byte[] bArr2 = new byte[this.b.length()];
                this.b.readBytes(bArr2, 0, 0, this.b.length());
                obtain.unmarshall(bArr2, 0, bArr2.length);
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
        obtain.setDataPosition(0);
        return obtain;
    }

    public void writeToParcel(Parcel parcel, int i) {
        if (ApiHelper.b) {
            parcel.writeParcelable((Parcelable) this.f14051a, i);
            return;
        }
        parcel.writeInt(this.b.length());
        parcel.writeParcelable(MemoryFileHelper.a(this.b), i);
    }

    public void c() {
        if (ApiHelper.b) {
            if (this.f14051a != null) {
                try {
                    this.c.invoke(this.f14051a, new Object[0]);
                } catch (IllegalAccessException e2) {
                    e2.printStackTrace();
                } catch (InvocationTargetException e3) {
                    e3.printStackTrace();
                }
                this.f14051a = null;
            }
        } else if (this.b != null) {
            this.b.close();
            this.b = null;
        }
    }
}
