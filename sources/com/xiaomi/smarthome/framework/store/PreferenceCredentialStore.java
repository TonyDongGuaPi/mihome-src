package com.xiaomi.smarthome.framework.store;

import android.content.Context;
import android.content.SharedPreferences;
import com.xiaomi.smarthome.framework.account.Account;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class PreferenceCredentialStore implements CredentialStore {

    /* renamed from: a  reason: collision with root package name */
    private static final String f17687a = "com.xiaomi.sh.account";
    private static final String b = "account";
    private static final String c = "wifi";
    private final SharedPreferences d;

    public PreferenceCredentialStore(Context context) {
        this.d = context.getApplicationContext().getSharedPreferences(f17687a, 0);
    }

    public void a(Account account) {
        SharedPreferences.Editor edit = this.d.edit();
        edit.putString("account", b(account));
        edit.commit();
    }

    public Account a() {
        String string = this.d.getString("account", (String) null);
        if (string != null) {
            return b(string);
        }
        return null;
    }

    public String c() {
        return this.d.getString("wifi", (String) null);
    }

    public void a(String str) {
        SharedPreferences.Editor edit = this.d.edit();
        edit.putString("wifi", str);
        edit.commit();
    }

    public void b() {
        SharedPreferences.Editor edit = this.d.edit();
        edit.remove("account");
        edit.commit();
    }

    /* access modifiers changed from: protected */
    public String b(Account account) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            new ObjectOutputStream(byteArrayOutputStream).writeObject(account);
            return a(byteArrayOutputStream.toByteArray());
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public Account b(String str) {
        try {
            return (Account) new ObjectInputStream(new ByteArrayInputStream(c(str))).readObject();
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b2 : bArr) {
            byte b3 = b2 & 255;
            if (b3 < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(b3));
        }
        return sb.toString().toUpperCase();
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
}
