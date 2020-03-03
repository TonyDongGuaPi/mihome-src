package androidx.versionedparcelable;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcelable;
import android.support.annotation.RestrictTo;
import android.util.SparseArray;
import androidx.versionedparcelable.VersionedParcel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Set;

@RestrictTo({RestrictTo.Scope.LIBRARY})
class VersionedParcelStream extends VersionedParcel {

    /* renamed from: a  reason: collision with root package name */
    private static final Charset f488a = Charset.forName("UTF-16");
    private static final int b = 0;
    private static final int c = 1;
    private static final int d = 2;
    private static final int e = 3;
    private static final int f = 4;
    private static final int g = 5;
    private static final int h = 6;
    private static final int i = 7;
    private static final int j = 8;
    private static final int k = 9;
    private static final int l = 10;
    private static final int m = 11;
    private static final int n = 12;
    private static final int o = 13;
    private static final int p = 14;
    private final DataInputStream q;
    private final DataOutputStream r;
    private final SparseArray<InputBuffer> s = new SparseArray<>();
    private DataInputStream t;
    private DataOutputStream u;
    private FieldBuffer v;
    private boolean w;

    public boolean a() {
        return true;
    }

    public IBinder i() {
        return null;
    }

    public <T extends Parcelable> T k() {
        return null;
    }

    public VersionedParcelStream(InputStream inputStream, OutputStream outputStream) {
        DataOutputStream dataOutputStream = null;
        this.q = inputStream != null ? new DataInputStream(inputStream) : null;
        this.r = outputStream != null ? new DataOutputStream(outputStream) : dataOutputStream;
        this.t = this.q;
        this.u = this.r;
    }

    public void a(boolean z, boolean z2) {
        if (z) {
            this.w = z2;
            return;
        }
        throw new RuntimeException("Serialization of this object is not allowed");
    }

    public void b() {
        if (this.v != null) {
            try {
                if (this.v.f489a.size() != 0) {
                    this.v.a();
                }
                this.v = null;
            } catch (IOException e2) {
                throw new VersionedParcel.ParcelException(e2);
            }
        }
    }

    /* access modifiers changed from: protected */
    public VersionedParcel c() {
        return new VersionedParcelStream(this.t, this.u);
    }

    public boolean b(int i2) {
        InputBuffer inputBuffer = this.s.get(i2);
        if (inputBuffer != null) {
            this.s.remove(i2);
            this.t = inputBuffer.f490a;
            return true;
        }
        while (true) {
            try {
                int readInt = this.q.readInt();
                int i3 = readInt & 65535;
                if (i3 == 65535) {
                    i3 = this.q.readInt();
                }
                InputBuffer inputBuffer2 = new InputBuffer((readInt >> 16) & 65535, i3, this.q);
                if (inputBuffer2.b == i2) {
                    this.t = inputBuffer2.f490a;
                    return true;
                }
                this.s.put(inputBuffer2.b, inputBuffer2);
            } catch (IOException unused) {
                return false;
            }
        }
    }

    public void c(int i2) {
        b();
        this.v = new FieldBuffer(i2, this.r);
        this.u = this.v.b;
    }

    public void a(byte[] bArr) {
        if (bArr != null) {
            try {
                this.u.writeInt(bArr.length);
                this.u.write(bArr);
            } catch (IOException e2) {
                throw new VersionedParcel.ParcelException(e2);
            }
        } else {
            this.u.writeInt(-1);
        }
    }

    public void a(byte[] bArr, int i2, int i3) {
        if (bArr != null) {
            try {
                this.u.writeInt(i3);
                this.u.write(bArr, i2, i3);
            } catch (IOException e2) {
                throw new VersionedParcel.ParcelException(e2);
            }
        } else {
            this.u.writeInt(-1);
        }
    }

    public void a(int i2) {
        try {
            this.u.writeInt(i2);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public void a(long j2) {
        try {
            this.u.writeLong(j2);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public void a(float f2) {
        try {
            this.u.writeFloat(f2);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public void a(double d2) {
        try {
            this.u.writeDouble(d2);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public void a(String str) {
        if (str != null) {
            try {
                byte[] bytes = str.getBytes(f488a);
                this.u.writeInt(bytes.length);
                this.u.write(bytes);
            } catch (IOException e2) {
                throw new VersionedParcel.ParcelException(e2);
            }
        } else {
            this.u.writeInt(-1);
        }
    }

    public void a(boolean z) {
        try {
            this.u.writeBoolean(z);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public void a(IBinder iBinder) {
        if (!this.w) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    public void a(Parcelable parcelable) {
        if (!this.w) {
            throw new RuntimeException("Parcelables cannot be written to an OutputStream");
        }
    }

    public void a(IInterface iInterface) {
        if (!this.w) {
            throw new RuntimeException("Binders cannot be written to an OutputStream");
        }
    }

    public int d() {
        try {
            return this.t.readInt();
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public long e() {
        try {
            return this.t.readLong();
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public float f() {
        try {
            return this.t.readFloat();
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public double g() {
        try {
            return this.t.readDouble();
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public String h() {
        try {
            int readInt = this.t.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.t.readFully(bArr);
            return new String(bArr, f488a);
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public byte[] j() {
        try {
            int readInt = this.t.readInt();
            if (readInt <= 0) {
                return null;
            }
            byte[] bArr = new byte[readInt];
            this.t.readFully(bArr);
            return bArr;
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public boolean m() {
        try {
            return this.t.readBoolean();
        } catch (IOException e2) {
            throw new VersionedParcel.ParcelException(e2);
        }
    }

    public void a(Bundle bundle) {
        if (bundle != null) {
            try {
                Set<String> keySet = bundle.keySet();
                this.u.writeInt(keySet.size());
                for (String str : keySet) {
                    a(str);
                    a(bundle.get(str));
                }
            } catch (IOException e2) {
                throw new VersionedParcel.ParcelException(e2);
            }
        } else {
            this.u.writeInt(-1);
        }
    }

    public Bundle l() {
        int d2 = d();
        if (d2 < 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        for (int i2 = 0; i2 < d2; i2++) {
            a(d(), h(), bundle);
        }
        return bundle;
    }

    private void a(Object obj) {
        if (obj == null) {
            a(0);
        } else if (obj instanceof Bundle) {
            a(1);
            a((Bundle) obj);
        } else if (obj instanceof String) {
            a(3);
            a((String) obj);
        } else if (obj instanceof String[]) {
            a(4);
            a((T[]) (String[]) obj);
        } else if (obj instanceof Boolean) {
            a(5);
            a(((Boolean) obj).booleanValue());
        } else if (obj instanceof boolean[]) {
            a(6);
            a((boolean[]) obj);
        } else if (obj instanceof Double) {
            a(7);
            a(((Double) obj).doubleValue());
        } else if (obj instanceof double[]) {
            a(8);
            a((double[]) obj);
        } else if (obj instanceof Integer) {
            a(9);
            a(((Integer) obj).intValue());
        } else if (obj instanceof int[]) {
            a(10);
            a((int[]) obj);
        } else if (obj instanceof Long) {
            a(11);
            a(((Long) obj).longValue());
        } else if (obj instanceof long[]) {
            a(12);
            a((long[]) obj);
        } else if (obj instanceof Float) {
            a(13);
            a(((Float) obj).floatValue());
        } else if (obj instanceof float[]) {
            a(14);
            a((float[]) obj);
        } else {
            throw new IllegalArgumentException("Unsupported type " + obj.getClass());
        }
    }

    private void a(int i2, String str, Bundle bundle) {
        switch (i2) {
            case 0:
                bundle.putParcelable(str, (Parcelable) null);
                return;
            case 1:
                bundle.putBundle(str, l());
                return;
            case 2:
                bundle.putBundle(str, l());
                return;
            case 3:
                bundle.putString(str, h());
                return;
            case 4:
                bundle.putStringArray(str, (String[]) b((T[]) new String[0]));
                return;
            case 5:
                bundle.putBoolean(str, m());
                return;
            case 6:
                bundle.putBooleanArray(str, n());
                return;
            case 7:
                bundle.putDouble(str, g());
                return;
            case 8:
                bundle.putDoubleArray(str, r());
                return;
            case 9:
                bundle.putInt(str, d());
                return;
            case 10:
                bundle.putIntArray(str, o());
                return;
            case 11:
                bundle.putLong(str, e());
                return;
            case 12:
                bundle.putLongArray(str, p());
                return;
            case 13:
                bundle.putFloat(str, f());
                return;
            case 14:
                bundle.putFloatArray(str, q());
                return;
            default:
                throw new RuntimeException("Unknown type " + i2);
        }
    }

    private static class FieldBuffer {

        /* renamed from: a  reason: collision with root package name */
        final ByteArrayOutputStream f489a = new ByteArrayOutputStream();
        final DataOutputStream b = new DataOutputStream(this.f489a);
        private final int c;
        private final DataOutputStream d;

        FieldBuffer(int i, DataOutputStream dataOutputStream) {
            this.c = i;
            this.d = dataOutputStream;
        }

        /* access modifiers changed from: package-private */
        public void a() throws IOException {
            this.b.flush();
            int size = this.f489a.size();
            this.d.writeInt((this.c << 16) | (size >= 65535 ? 65535 : size));
            if (size >= 65535) {
                this.d.writeInt(size);
            }
            this.f489a.writeTo(this.d);
        }
    }

    private static class InputBuffer {

        /* renamed from: a  reason: collision with root package name */
        final DataInputStream f490a;
        final int b;
        private final int c;

        InputBuffer(int i, int i2, DataInputStream dataInputStream) throws IOException {
            this.c = i2;
            this.b = i;
            byte[] bArr = new byte[this.c];
            dataInputStream.readFully(bArr);
            this.f490a = new DataInputStream(new ByteArrayInputStream(bArr));
        }
    }
}
