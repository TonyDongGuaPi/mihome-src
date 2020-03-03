package miuipub.net.http;

import android.content.SharedPreferences;
import android.text.TextUtils;
import com.miuipub.internal.util.PackageConstants;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import miuipub.text.ExtraTextUtils;
import miuipub.util.IOUtils;
import miuipub.util.SoftReferenceSingleton;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;

public class PersistentCookieStore implements CookieStore {

    /* renamed from: a  reason: collision with root package name */
    private static final String f2983a = "CookiePrefs";
    private static final String b = "names";
    private static final SoftReferenceSingleton<PersistentCookieStore> c = new SoftReferenceSingleton<PersistentCookieStore>() {
        /* access modifiers changed from: protected */
        /* renamed from: a */
        public PersistentCookieStore createInstance() {
            return new PersistentCookieStore();
        }
    };
    private final ConcurrentHashMap<String, Cookie> d;
    private final SharedPreferences e;

    private PersistentCookieStore() {
        Cookie a2;
        this.e = PackageConstants.a().getSharedPreferences(f2983a, 0);
        this.d = new ConcurrentHashMap<>();
        String string = this.e.getString(b, (String) null);
        if (string != null) {
            for (String str : TextUtils.split(string, ",")) {
                String string2 = this.e.getString(str, (String) null);
                if (!(string2 == null || (a2 = a(string2)) == null)) {
                    this.d.put(str, a2);
                }
            }
        }
        clearExpired(new Date());
    }

    public static PersistentCookieStore a() {
        return c.get();
    }

    public void addCookie(Cookie cookie) {
        String str = cookie.getName() + cookie.getDomain();
        if (!cookie.isExpired(new Date())) {
            this.d.put(str, cookie);
            SharedPreferences.Editor edit = this.e.edit();
            edit.putString(b, TextUtils.join(",", this.d.keySet()));
            edit.putString(str, a(cookie));
            edit.commit();
        } else if (this.d.remove(str) != null) {
            SharedPreferences.Editor edit2 = this.e.edit();
            edit2.putString(b, TextUtils.join(",", this.d.keySet()));
            edit2.remove(str);
            edit2.commit();
        }
    }

    public List<Cookie> getCookies() {
        return new ArrayList(this.d.values());
    }

    public boolean clearExpired(Date date) {
        SharedPreferences.Editor edit = this.e.edit();
        boolean z = false;
        for (Map.Entry next : this.d.entrySet()) {
            String str = (String) next.getKey();
            if (((Cookie) next.getValue()).isExpired(date)) {
                this.d.remove(str);
                edit.remove(str);
                z = true;
            }
        }
        if (z) {
            edit.putString(b, TextUtils.join(",", this.d.keySet()));
            edit.commit();
        }
        return z;
    }

    public void clear() {
        SharedPreferences.Editor edit = this.e.edit();
        for (String remove : this.d.keySet()) {
            edit.remove(remove);
        }
        edit.remove(b);
        this.d.clear();
        edit.commit();
    }

    private String a(Cookie cookie) {
        ObjectOutputStream objectOutputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            try {
                objectOutputStream.writeObject(cookie.getName());
                objectOutputStream.writeObject(cookie.getValue());
                objectOutputStream.writeObject(cookie.getComment());
                objectOutputStream.writeObject(cookie.getDomain());
                objectOutputStream.writeObject(cookie.getExpiryDate());
                objectOutputStream.writeObject(cookie.getPath());
                objectOutputStream.writeInt(cookie.getVersion());
                objectOutputStream.writeBoolean(cookie.isSecure());
                objectOutputStream.flush();
                IOUtils.a((OutputStream) objectOutputStream);
                return ExtraTextUtils.a(byteArrayOutputStream.toByteArray());
            } catch (IOException unused) {
                IOUtils.a((OutputStream) objectOutputStream);
                return null;
            } catch (Throwable th) {
                th = th;
                IOUtils.a((OutputStream) objectOutputStream);
                throw th;
            }
        } catch (IOException unused2) {
            objectOutputStream = null;
            IOUtils.a((OutputStream) objectOutputStream);
            return null;
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream = null;
            IOUtils.a((OutputStream) objectOutputStream);
            throw th;
        }
    }

    private Cookie a(String str) {
        ObjectInputStream objectInputStream;
        Throwable th;
        try {
            objectInputStream = new ObjectInputStream(new ByteArrayInputStream(ExtraTextUtils.a(str)));
            try {
                BasicClientCookie basicClientCookie = new BasicClientCookie((String) objectInputStream.readObject(), (String) objectInputStream.readObject());
                basicClientCookie.setComment((String) objectInputStream.readObject());
                basicClientCookie.setDomain((String) objectInputStream.readObject());
                basicClientCookie.setExpiryDate((Date) objectInputStream.readObject());
                basicClientCookie.setPath((String) objectInputStream.readObject());
                basicClientCookie.setVersion(objectInputStream.readInt());
                basicClientCookie.setSecure(objectInputStream.readBoolean());
                IOUtils.a((InputStream) objectInputStream);
                return basicClientCookie;
            } catch (Exception unused) {
                IOUtils.a((InputStream) objectInputStream);
                return null;
            } catch (Throwable th2) {
                th = th2;
                IOUtils.a((InputStream) objectInputStream);
                throw th;
            }
        } catch (Exception unused2) {
            objectInputStream = null;
            IOUtils.a((InputStream) objectInputStream);
            return null;
        } catch (Throwable th3) {
            objectInputStream = null;
            th = th3;
            IOUtils.a((InputStream) objectInputStream);
            throw th;
        }
    }
}
