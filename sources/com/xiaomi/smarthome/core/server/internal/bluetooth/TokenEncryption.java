package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.account.AccountManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.process.ProcessUtil;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.encryption.AESEncryption;
import com.xiaomi.smarthome.library.bluetooth.encryption.Encryption;

public final class TokenEncryption implements Encryption {

    /* renamed from: a  reason: collision with root package name */
    private static final String f14149a = "token.key";
    private Encryption b;

    private TokenEncryption() {
        this.b = new AESEncryption();
    }

    public static TokenEncryption a() {
        return TokenEncryptionHolder.f14150a;
    }

    private static class TokenEncryptionHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static TokenEncryption f14150a = new TokenEncryption();

        private TokenEncryptionHolder() {
        }
    }

    public String a(String str) {
        return a(b(), str);
    }

    public String b(String str) {
        return b(b(), str);
    }

    public String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        return this.b.a(str, str2);
    }

    public String b(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return "";
        }
        return this.b.b(str, str2);
    }

    private String b() {
        String str;
        Context n = BluetoothContextManager.n();
        if (n == null) {
            str = null;
        } else if (ProcessUtil.i(n)) {
            str = AccountManager.a().m();
            if (TextUtils.isEmpty(str)) {
                str = "0";
            }
        } else {
            str = CoreApi.a().s();
        }
        return String.format("%s.%s", new Object[]{f14149a, str});
    }
}
