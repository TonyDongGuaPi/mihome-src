package com.xiaomi.smarthome.framework.page.verify;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyPermanentlyInvalidatedException;
import android.security.keystore.UserNotAuthenticatedException;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import com.coloros.mcssdk.c.a;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.verify.callback.GetCipherCallback;
import com.xiaomi.smarthome.miio.Miio;
import java.io.IOException;
import java.nio.charset.Charset;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.CertificateException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

@TargetApi(23)
final class DeviceVerifyConfigCache {

    /* renamed from: a  reason: collision with root package name */
    public static int f17052a = -1;
    public static int b = -2;
    public static int c = -3;
    public static int d = -4;
    public static int e = -5;
    private static final String f = "DeviceVerifyConfigCache";
    private static final String g = "xiaomi.sm.pin_key";
    private static final String h = "xiaomi.sm.encryted_pin";
    private static final String i = "xiaomi.sm.encryted_pin_iv";
    private static final String j = "xiaomi.sm.is_open_fingerprint_verify";
    private static volatile DeviceVerifyConfigCache k;
    private KeyStore l;
    private SharedPreferences m;

    private DeviceVerifyConfigCache(Context context) {
        this.m = context.getSharedPreferences("xiaomi.device.pincode", 0);
        a();
    }

    public static DeviceVerifyConfigCache a(Context context) {
        if (k == null) {
            synchronized (DeviceVerifyConfigCache.class) {
                if (k == null) {
                    k = new DeviceVerifyConfigCache(context);
                }
            }
        }
        return k;
    }

    private static String c(String str) {
        return g + str;
    }

    private static String d(String str) {
        return h + str;
    }

    private static String e(String str) {
        return i + str;
    }

    private static String f(String str) {
        return j + str;
    }

    private void a() {
        try {
            this.l = KeyStore.getInstance("AndroidKeyStore");
        } catch (KeyStoreException e2) {
            Miio.e("Failed to get an instance of KeyStore", e2.getMessage());
        }
    }

    public void a(String str, boolean z) {
        SharedPreferences.Editor edit = this.m.edit();
        edit.putBoolean(f(str), z);
        edit.apply();
    }

    public boolean a(String str) {
        return this.m.getBoolean(f(str), false);
    }

    public void a(String str, String str2, Cipher cipher) {
        if (cipher != null) {
            try {
                byte[] doFinal = cipher.doFinal(str2.getBytes("utf-8"));
                byte[] iv = cipher.getIV();
                String encodeToString = Base64.encodeToString(doFinal, 0);
                String encodeToString2 = Base64.encodeToString(iv, 0);
                SharedPreferences.Editor edit = this.m.edit();
                edit.putString(d(str), encodeToString);
                edit.putString(e(str), encodeToString2);
                edit.apply();
            } catch (IOException | BadPaddingException | IllegalBlockSizeException e2) {
                i(str);
                throw new RuntimeException("Failed to encrypt pin ", e2);
            }
        }
    }

    public String a(String str, Cipher cipher) {
        String string = this.m.getString(d(str), "");
        if (TextUtils.isEmpty(string) || cipher == null) {
            return "";
        }
        try {
            return new String(cipher.doFinal(Base64.decode(string, 0)), Charset.forName("UTF8"));
        } catch (BadPaddingException | IllegalBlockSizeException e2) {
            String str2 = f;
            Miio.b(str2, "Failed to decrypt the data with the generated key." + e2.getMessage());
            e2.printStackTrace();
            return "";
        }
    }

    private boolean g(String str) {
        String c2 = c(str);
        try {
            this.l.load((KeyStore.LoadStoreParameter) null);
            if (this.l.containsAlias(c2) || h(str)) {
                return true;
            }
            return false;
        } catch (IOException | NoSuchAlgorithmException | CertificateException e2) {
            Miio.e("Fail to init key store ", e2.getMessage());
            return false;
        } catch (KeyStoreException e3) {
            Miio.e("Get a KeyStoreException when create key ", e3.getMessage());
            return false;
        }
    }

    private boolean h(String str) {
        try {
            KeyGenerator instance = KeyGenerator.getInstance(a.b, "AndroidKeyStore");
            KeyGenParameterSpec.Builder encryptionPaddings = new KeyGenParameterSpec.Builder(c(str), 3).setBlockModes(new String[]{"CBC"}).setUserAuthenticationRequired(true).setEncryptionPaddings(new String[]{"PKCS7Padding"});
            if (Build.VERSION.SDK_INT >= 24) {
                encryptionPaddings.setInvalidatedByBiometricEnrollment(true);
            }
            try {
                instance.init(encryptionPaddings.build());
                try {
                    instance.generateKey();
                    return true;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            } catch (InvalidAlgorithmParameterException e3) {
                Miio.e("Failed to init KeyGenerator ", e3.getMessage());
                return false;
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e4) {
            Miio.e("Failed to get an instance of KeyGenerator", e4.getMessage());
            return false;
        }
    }

    public void a(String str, GetCipherCallback getCipherCallback) {
        if (!g(str)) {
            getCipherCallback.onGetCipherError(f17052a, "generate key error!");
            return;
        }
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            this.l.load((KeyStore.LoadStoreParameter) null);
            instance.init(1, (SecretKey) this.l.getKey(c(str), (char[]) null));
            getCipherCallback.onGetCipherSuccess(instance);
        } catch (KeyPermanentlyInvalidatedException e2) {
            Miio.e(e2.getMessage());
            if (!i(str)) {
                getCipherCallback.onGetCipherError(b, "generate key fail");
            } else {
                c(str, getCipherCallback);
            }
        } catch (Exception e3) {
            LogUtil.b(f, Log.getStackTraceString(e3));
            if (e3 instanceof UserNotAuthenticatedException) {
                getCipherCallback.onGetCipherError(d, e3.getMessage());
            } else {
                getCipherCallback.onGetCipherError(e, e3.getMessage());
            }
        }
    }

    private void c(String str, GetCipherCallback getCipherCallback) {
        try {
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            this.l.load((KeyStore.LoadStoreParameter) null);
            instance.init(1, (SecretKey) this.l.getKey(c(str), (char[]) null));
            getCipherCallback.onGetResetCipherSuccess(instance);
            b(str);
        } catch (Exception e2) {
            Miio.e(e2.getMessage());
            if (e2 instanceof UserNotAuthenticatedException) {
                getCipherCallback.onGetCipherError(d, e2.getMessage());
            } else {
                getCipherCallback.onGetCipherError(e, e2.getMessage());
            }
        }
    }

    public void b(String str, GetCipherCallback getCipherCallback) {
        if (!g(str)) {
            getCipherCallback.onGetCipherError(f17052a, "generate key error!");
            return;
        }
        try {
            byte[] decode = Base64.decode(this.m.getString(e(str), ""), 0);
            Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
            this.l.load((KeyStore.LoadStoreParameter) null);
            instance.init(2, (SecretKey) this.l.getKey(c(str), (char[]) null), new IvParameterSpec(decode));
            getCipherCallback.onGetCipherSuccess(instance);
        } catch (KeyPermanentlyInvalidatedException e2) {
            Miio.e(e2.getMessage());
            if (!i(str)) {
                getCipherCallback.onGetCipherError(b, "generate key fail");
            } else {
                c(str, getCipherCallback);
            }
        } catch (Exception e3) {
            if (e3 instanceof UserNotAuthenticatedException) {
                getCipherCallback.onGetCipherError(d, e3.getMessage());
            } else {
                getCipherCallback.onGetCipherError(e, e3.getMessage());
            }
        }
    }

    public void b(String str) {
        SharedPreferences.Editor edit = this.m.edit();
        edit.remove(d(str));
        edit.remove(e(str));
        edit.remove(f(str));
        edit.apply();
    }

    private boolean i(String str) {
        try {
            this.l.load((KeyStore.LoadStoreParameter) null);
            return h(str);
        } catch (IOException | NoSuchAlgorithmException | CertificateException e2) {
            Miio.e("Fail to init key store ", e2.getMessage());
            return false;
        }
    }
}
