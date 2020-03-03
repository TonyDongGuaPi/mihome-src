package a.a.a.g;

import android.util.Base64;
import com.miuipub.internal.hybrid.SignUtils;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.security.PublicKey;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class a {

    /* renamed from: a  reason: collision with root package name */
    public final PublicKey f408a;

    public a(InputStream inputStream) {
        this.f408a = (PublicKey) new ObjectInputStream(inputStream).readObject();
    }

    public b a(String str) {
        SecretKey a2 = a();
        b bVar = new b();
        String a3 = a(a2, str);
        String a4 = a(a2);
        bVar.b(a3);
        bVar.a(a4);
        return bVar;
    }

    public final String a(SecretKey secretKey) {
        return new String(Base64.encode(a(secretKey.getEncoded(), this.f408a), 0));
    }

    public final String a(SecretKey secretKey, String str) {
        Cipher instance = Cipher.getInstance(com.coloros.mcssdk.c.a.b);
        instance.init(1, secretKey);
        return new String(Base64.encode(instance.doFinal(str.getBytes("UTF8")), 0));
    }

    public final SecretKey a() {
        KeyGenerator instance = KeyGenerator.getInstance(com.coloros.mcssdk.c.a.b);
        instance.init(128);
        return instance.generateKey();
    }

    public final byte[] a(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher instance = Cipher.getInstance(SignUtils.b);
            instance.init(1, publicKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
