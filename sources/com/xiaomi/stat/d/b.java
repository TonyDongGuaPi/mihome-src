package com.xiaomi.stat.d;

import android.content.Context;
import android.os.Build;
import android.security.KeyPairGeneratorSpec;
import android.util.Base64;
import com.miuipub.internal.hybrid.SignUtils;
import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.util.GregorianCalendar;
import javax.crypto.Cipher;
import javax.security.auth.x500.X500Principal;

public class b {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23046a = "AndroidKeyStoreUtils";
    private static final String b = "AndroidKeyStore";
    private static final String c = "RSA/ECB/PKCS1Padding";
    private static final String d = "RSA_KEY";

    public static synchronized String a(Context context, String str) throws Exception {
        synchronized (b.class) {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            KeyStore instance2 = KeyStore.getInstance(b);
            instance2.load((KeyStore.LoadStoreParameter) null);
            a(context, instance2);
            Certificate certificate = instance2.getCertificate(d);
            if (certificate == null) {
                return null;
            }
            instance.init(1, certificate.getPublicKey());
            String encodeToString = Base64.encodeToString(instance.doFinal(str.getBytes("UTF-8")), 0);
            return encodeToString;
        }
    }

    public static synchronized String b(Context context, String str) throws Exception {
        String str2;
        synchronized (b.class) {
            Cipher instance = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            KeyStore instance2 = KeyStore.getInstance(b);
            instance2.load((KeyStore.LoadStoreParameter) null);
            a(context, instance2);
            instance.init(2, (PrivateKey) instance2.getKey(d, (char[]) null));
            str2 = new String(instance.doFinal(Base64.decode(str, 0)), "UTF-8");
        }
        return str2;
    }

    private static void a(Context context, KeyStore keyStore) {
        try {
            if (!keyStore.containsAlias(d) && Build.VERSION.SDK_INT >= 18) {
                if (Build.VERSION.SDK_INT < 23) {
                    a(context);
                } else {
                    a();
                }
            }
        } catch (Exception e) {
            k.d(f23046a, "createKey e", e);
        }
    }

    private static void a(Context context) throws KeyStoreException, NoSuchProviderException, NoSuchAlgorithmException, InvalidAlgorithmParameterException {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.add(1, 1);
        KeyPairGeneratorSpec build = new KeyPairGeneratorSpec.Builder(context).setAlias(d).setSubject(new X500Principal("CN=RSA_KEY")).setSerialNumber(BigInteger.valueOf(1337)).setStartDate(gregorianCalendar.getTime()).setEndDate(gregorianCalendar2.getTime()).build();
        KeyPairGenerator instance = KeyPairGenerator.getInstance(SignUtils.f8267a, b);
        instance.initialize(build);
        instance.generateKeyPair();
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v10, resolved type: java.lang.Object[]} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a() throws java.lang.ClassNotFoundException, java.lang.NoSuchFieldException, java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException, java.lang.InstantiationException, java.lang.NoSuchMethodException, java.security.InvalidAlgorithmParameterException, java.security.NoSuchProviderException, java.security.NoSuchAlgorithmException {
        /*
            java.lang.String r0 = "android.security.keystore.KeyGenParameterSpec$Builder"
            java.lang.Class r0 = java.lang.Class.forName(r0)
            if (r0 == 0) goto L_0x00e5
            r1 = 2
            java.lang.Class[] r2 = new java.lang.Class[r1]
            java.lang.Class<java.lang.String> r3 = java.lang.String.class
            r4 = 0
            r2[r4] = r3
            java.lang.Class r3 = java.lang.Integer.TYPE
            r5 = 1
            r2[r5] = r3
            java.lang.reflect.Constructor r2 = r0.getConstructor(r2)
            java.lang.String r3 = "android.security.keystore.KeyProperties"
            java.lang.Class r3 = java.lang.Class.forName(r3)
            java.lang.String r6 = "PURPOSE_ENCRYPT"
            java.lang.reflect.Field r6 = r3.getDeclaredField(r6)
            r7 = 0
            int r6 = r6.getInt(r7)
            java.lang.String r8 = "PURPOSE_DECRYPT"
            java.lang.reflect.Field r8 = r3.getDeclaredField(r8)
            int r8 = r8.getInt(r7)
            java.lang.Object[] r9 = new java.lang.Object[r1]
            java.lang.String r10 = "RSA_KEY"
            r9[r4] = r10
            r6 = r6 | r8
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)
            r9[r5] = r6
            java.lang.Object r2 = r2.newInstance(r9)
            java.lang.String r6 = "setDigests"
            java.lang.Class[] r8 = new java.lang.Class[r5]
            java.lang.Class<java.lang.String[]> r9 = java.lang.String[].class
            r8[r4] = r9
            java.lang.reflect.Method r6 = r0.getMethod(r6, r8)
            java.lang.String r8 = "DIGEST_SHA256"
            java.lang.reflect.Field r8 = r3.getDeclaredField(r8)
            java.lang.Object r8 = r8.get(r7)
            java.lang.String r8 = (java.lang.String) r8
            java.lang.String r9 = "DIGEST_SHA512"
            java.lang.reflect.Field r9 = r3.getDeclaredField(r9)
            java.lang.Object r9 = r9.get(r7)
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object[] r10 = new java.lang.Object[r5]
            java.lang.String[] r11 = new java.lang.String[r1]
            r11[r4] = r8
            r11[r5] = r9
            r10[r4] = r11
            r6.invoke(r2, r10)
            java.lang.String r6 = "setEncryptionPaddings"
            java.lang.Class[] r8 = new java.lang.Class[r5]
            java.lang.Class<java.lang.String[]> r9 = java.lang.String[].class
            r8[r4] = r9
            java.lang.reflect.Method r6 = r0.getMethod(r6, r8)
            java.lang.String r8 = "ENCRYPTION_PADDING_RSA_PKCS1"
            java.lang.reflect.Field r3 = r3.getDeclaredField(r8)
            java.lang.Object r3 = r3.get(r7)
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object[] r8 = new java.lang.Object[r5]
            java.lang.String[] r9 = new java.lang.String[r5]
            r9[r4] = r3
            r8[r4] = r9
            r6.invoke(r2, r8)
            java.lang.String r3 = "build"
            java.lang.Class[] r6 = new java.lang.Class[r4]
            java.lang.reflect.Method r0 = r0.getMethod(r3, r6)
            java.lang.Object[] r3 = new java.lang.Object[r4]
            java.lang.Object r0 = r0.invoke(r2, r3)
            java.lang.String r2 = "java.security.KeyPairGenerator"
            java.lang.Class r2 = java.lang.Class.forName(r2)
            if (r2 == 0) goto L_0x00e5
            java.lang.String r3 = "getInstance"
            java.lang.Class[] r6 = new java.lang.Class[r1]
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r6[r4] = r8
            java.lang.Class<java.lang.String> r8 = java.lang.String.class
            r6[r5] = r8
            java.lang.reflect.Method r3 = r2.getMethod(r3, r6)
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r6 = "RSA"
            r1[r4] = r6
            java.lang.String r6 = "AndroidKeyStore"
            r1[r5] = r6
            java.lang.Object r1 = r3.invoke(r7, r1)
            java.security.KeyPairGenerator r1 = (java.security.KeyPairGenerator) r1
            java.lang.String r3 = "initialize"
            java.lang.Class[] r6 = new java.lang.Class[r5]
            java.lang.Class<java.security.spec.AlgorithmParameterSpec> r7 = java.security.spec.AlgorithmParameterSpec.class
            r6[r4] = r7
            java.lang.reflect.Method r2 = r2.getMethod(r3, r6)
            java.lang.Object[] r3 = new java.lang.Object[r5]
            r3[r4] = r0
            r2.invoke(r1, r3)
            r1.generateKeyPair()
        L_0x00e5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.stat.d.b.a():void");
    }
}
