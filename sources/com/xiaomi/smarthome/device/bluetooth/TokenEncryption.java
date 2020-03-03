package com.xiaomi.smarthome.device.bluetooth;

import android.text.TextUtils;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.bluetooth.encryption.AESEncryption;
import com.xiaomi.smarthome.library.bluetooth.encryption.Encryption;

public final class TokenEncryption implements Encryption {

    /* renamed from: a  reason: collision with root package name */
    private static final String f15114a = "token.key";
    private Encryption b;

    private TokenEncryption() {
        this.b = new AESEncryption();
    }

    public static TokenEncryption a() {
        return TokenEncryptionHolder.f15115a;
    }

    private static class TokenEncryptionHolder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static TokenEncryption f15115a = new TokenEncryption();

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
        return String.format("%s.%s", new Object[]{f15114a, CoreApi.a().s()});
    }
}
