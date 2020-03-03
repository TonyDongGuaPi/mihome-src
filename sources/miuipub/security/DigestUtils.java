package miuipub.security;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DigestUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3011a = "MD5";
    public static final String b = "SHA-1";
    private static final int c = 4096;

    protected DigestUtils() throws InstantiationException {
        throw new InstantiationException("Cannot instantiate utility class");
    }

    public static byte[] a(CharSequence charSequence, String str) {
        return a(charSequence.toString().getBytes(), str);
    }

    public static byte[] a(byte[] bArr, String str) {
        try {
            return a((InputStream) new ByteArrayInputStream(bArr), str);
        } catch (IOException e) {
            throw new RuntimeException("IO exception happend in ByteArrayInputStream", e);
        }
    }

    public static byte[] a(InputStream inputStream, String str) throws IOException {
        try {
            MessageDigest instance = MessageDigest.getInstance(str);
            byte[] bArr = new byte[4096];
            while (true) {
                int read = inputStream.read(bArr);
                if (read <= 0) {
                    return instance.digest();
                }
                instance.update(bArr, 0, read);
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("NoSuchAlgorithmException", e);
        }
    }
}
