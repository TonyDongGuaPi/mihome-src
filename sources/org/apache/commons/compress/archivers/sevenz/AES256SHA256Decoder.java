package org.apache.commons.compress.archivers.sevenz;

import cn.com.fmsh.tsm.business.constants.Constants;
import com.coloros.mcssdk.c.a;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.compress.PasswordRequiredException;

class AES256SHA256Decoder extends CoderBase {
    AES256SHA256Decoder() {
        super(new Class[0]);
    }

    /* access modifiers changed from: package-private */
    public InputStream a(String str, InputStream inputStream, long j, Coder coder, byte[] bArr) throws IOException {
        final Coder coder2 = coder;
        final String str2 = str;
        final byte[] bArr2 = bArr;
        final InputStream inputStream2 = inputStream;
        return new InputStream() {
            private boolean f = false;
            private CipherInputStream g = null;

            public void close() {
            }

            private CipherInputStream a() throws IOException {
                byte[] bArr;
                if (this.f) {
                    return this.g;
                }
                byte b2 = coder2.d[0] & 255;
                byte b3 = b2 & Constants.TagName.CARD_APP_ACTIVATION_STATUS;
                byte b4 = coder2.d[1] & 255;
                int i = ((b2 >> 6) & 1) + (b4 & 15);
                int i2 = ((b2 >> 7) & 1) + (b4 >> 4);
                int i3 = i2 + 2;
                if (i3 + i <= coder2.d.length) {
                    byte[] bArr2 = new byte[i2];
                    System.arraycopy(coder2.d, 2, bArr2, 0, i2);
                    byte[] bArr3 = new byte[16];
                    System.arraycopy(coder2.d, i3, bArr3, 0, i);
                    if (bArr2 != null) {
                        if (b3 == 63) {
                            bArr = new byte[32];
                            System.arraycopy(bArr2, 0, bArr, 0, i2);
                            System.arraycopy(bArr2, 0, bArr, i2, Math.min(bArr2.length, bArr.length - i2));
                        } else {
                            try {
                                MessageDigest instance = MessageDigest.getInstance("SHA-256");
                                byte[] bArr4 = new byte[8];
                                for (long j = 0; j < (1 << b3); j++) {
                                    instance.update(bArr2);
                                    instance.update(bArr2);
                                    instance.update(bArr4);
                                    for (int i4 = 0; i4 < bArr4.length; i4++) {
                                        bArr4[i4] = (byte) (bArr4[i4] + 1);
                                        if (bArr4[i4] != 0) {
                                            break;
                                        }
                                    }
                                }
                                bArr = instance.digest();
                            } catch (NoSuchAlgorithmException e2) {
                                throw new IOException("SHA-256 is unsupported by your Java implementation", e2);
                            }
                        }
                        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr, a.b);
                        try {
                            Cipher instance2 = Cipher.getInstance("AES/CBC/NoPadding");
                            instance2.init(2, secretKeySpec, new IvParameterSpec(bArr3));
                            this.g = new CipherInputStream(inputStream2, instance2);
                            this.f = true;
                            return this.g;
                        } catch (GeneralSecurityException e3) {
                            throw new IOException("Decryption error (do you have the JCE Unlimited Strength Jurisdiction Policy Files installed?)", e3);
                        }
                    } else {
                        throw new PasswordRequiredException(str2);
                    }
                } else {
                    throw new IOException("Salt size + IV size too long in " + str2);
                }
            }

            public int read() throws IOException {
                return a().read();
            }

            public int read(byte[] bArr, int i, int i2) throws IOException {
                return a().read(bArr, i, i2);
            }
        };
    }
}
