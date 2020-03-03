package com.lidroid.xutils.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

public class PreferencesCookieStore implements CookieStore {

    /* renamed from: a  reason: collision with root package name */
    private static final String f6370a = "CookiePrefsFile";
    private static final String b = "names";
    private static final String c = "cookie_";
    private final ConcurrentHashMap<String, Cookie> d = new ConcurrentHashMap<>();
    private final SharedPreferences e;

    public PreferencesCookieStore(Context context) {
        Cookie b2;
        this.e = context.getSharedPreferences(f6370a, 0);
        String string = this.e.getString(b, (String) null);
        if (string != null) {
            for (String str : TextUtils.split(string, ",")) {
                String string2 = this.e.getString(c + str, (String) null);
                if (!(string2 == null || (b2 = b(string2)) == null)) {
                    this.d.put(str, b2);
                }
            }
            clearExpired(new Date());
        }
    }

    public void addCookie(Cookie cookie) {
        String name = cookie.getName();
        if (!cookie.isExpired(new Date())) {
            this.d.put(name, cookie);
        } else {
            this.d.remove(name);
        }
        SharedPreferences.Editor edit = this.e.edit();
        edit.putString(b, TextUtils.join(",", this.d.keySet()));
        edit.putString(c + name, a(new SerializableCookie(cookie)));
        edit.commit();
    }

    public void clear() {
        SharedPreferences.Editor edit = this.e.edit();
        for (String str : this.d.keySet()) {
            edit.remove(c + str);
        }
        edit.remove(b);
        edit.commit();
        this.d.clear();
    }

    public boolean clearExpired(Date date) {
        SharedPreferences.Editor edit = this.e.edit();
        boolean z = false;
        for (Map.Entry next : this.d.entrySet()) {
            String str = (String) next.getKey();
            Cookie cookie = (Cookie) next.getValue();
            if (cookie.getExpiryDate() == null || cookie.isExpired(date)) {
                this.d.remove(str);
                edit.remove(c + str);
                z = true;
            }
        }
        if (z) {
            edit.putString(b, TextUtils.join(",", this.d.keySet()));
        }
        edit.commit();
        return z;
    }

    public List<Cookie> getCookies() {
        return new ArrayList(this.d.values());
    }

    public Cookie a(String str) {
        return this.d.get(str);
    }

    /* access modifiers changed from: protected */
    public String a(SerializableCookie serializableCookie) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(serializableCookie);
            return a(byteArrayOutputStream.toByteArray());
        } catch (Throwable unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Cookie b(String str) {
        try {
            return ((SerializableCookie) new ObjectInputStream(new ByteArrayInputStream(c(str))).readObject()).getCookie();
        } catch (Throwable th) {
            LogUtils.b(th.getMessage(), th);
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String a(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer(bArr.length * 2);
        for (byte b2 : bArr) {
            byte b3 = b2 & 255;
            if (b3 < 16) {
                stringBuffer.append('0');
            }
            stringBuffer.append(Integer.toHexString(b3));
        }
        return stringBuffer.toString().toUpperCase();
    }

    /* access modifiers changed from: protected */
    public byte[] c(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public class SerializableCookie implements Serializable {
        private static final long serialVersionUID = 6374381828722046732L;

        /* renamed from: a  reason: collision with root package name */
        private final transient Cookie f6371a;
        private transient BasicClientCookie b;

        public SerializableCookie(Cookie cookie) {
            this.f6371a = cookie;
        }

        public Cookie getCookie() {
            return this.b != null ? this.b : this.f6371a;
        }

        private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
            objectOutputStream.writeObject(this.f6371a.getName());
            objectOutputStream.writeObject(this.f6371a.getValue());
            objectOutputStream.writeObject(this.f6371a.getComment());
            objectOutputStream.writeObject(this.f6371a.getDomain());
            objectOutputStream.writeObject(this.f6371a.getExpiryDate());
            objectOutputStream.writeObject(this.f6371a.getPath());
            objectOutputStream.writeInt(this.f6371a.getVersion());
            objectOutputStream.writeBoolean(this.f6371a.isSecure());
        }

        private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
            this.b = new BasicClientCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
            this.b.setComment((String) objectInputStream.readObject());
            this.b.setDomain((String) objectInputStream.readObject());
            this.b.setExpiryDate((Date) objectInputStream.readObject());
            this.b.setPath((String) objectInputStream.readObject());
            this.b.setVersion(objectInputStream.readInt());
            this.b.setSecure(objectInputStream.readBoolean());
        }
    }
}
