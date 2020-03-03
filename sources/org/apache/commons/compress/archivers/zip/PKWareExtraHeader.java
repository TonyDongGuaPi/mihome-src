package org.apache.commons.compress.archivers.zip;

import com.drew.metadata.exif.makernotes.FujifilmMakernoteDirectory;
import com.drew.metadata.exif.makernotes.PanasonicMakernoteDirectory;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class PKWareExtraHeader implements ZipExtraField {

    /* renamed from: a  reason: collision with root package name */
    private final ZipShort f3254a;
    private byte[] b;
    private byte[] c;

    protected PKWareExtraHeader(ZipShort zipShort) {
        this.f3254a = zipShort;
    }

    public ZipShort getHeaderId() {
        return this.f3254a;
    }

    public void a(byte[] bArr) {
        this.b = ZipUtil.b(bArr);
    }

    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.b != null ? this.b.length : 0);
    }

    public byte[] getLocalFileDataData() {
        return ZipUtil.b(this.b);
    }

    public void b(byte[] bArr) {
        this.c = ZipUtil.b(bArr);
    }

    public ZipShort getCentralDirectoryLength() {
        if (this.c != null) {
            return new ZipShort(this.c.length);
        }
        return getLocalFileDataLength();
    }

    public byte[] getCentralDirectoryData() {
        if (this.c != null) {
            return ZipUtil.b(this.c);
        }
        return getLocalFileDataData();
    }

    public void parseFromLocalFileData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        a(bArr2);
    }

    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        b(bArr2);
        if (this.b == null) {
            a(bArr2);
        }
    }

    public enum EncryptionAlgorithm {
        DES(26113),
        RC2pre52(26114),
        TripleDES168(26115),
        TripleDES192(26121),
        AES128(26126),
        AES192(26127),
        AES256(26128),
        RC2(26370),
        RC4(26625),
        UNKNOWN(65535);
        
        private static final Map<Integer, EncryptionAlgorithm> codeToEnum = null;
        private final int code;

        static {
            int i;
            HashMap hashMap = new HashMap();
            for (EncryptionAlgorithm encryptionAlgorithm : values()) {
                hashMap.put(Integer.valueOf(encryptionAlgorithm.getCode()), encryptionAlgorithm);
            }
            codeToEnum = Collections.unmodifiableMap(hashMap);
        }

        private EncryptionAlgorithm(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }

        public static EncryptionAlgorithm getAlgorithmByCode(int i) {
            return codeToEnum.get(Integer.valueOf(i));
        }
    }

    public enum HashAlgorithm {
        NONE(0),
        CRC32(1),
        MD5(FujifilmMakernoteDirectory.U),
        SHA1(PanasonicMakernoteDirectory.aQ),
        RIPEND160(PanasonicMakernoteDirectory.aT),
        SHA256(32780),
        SHA384(32781),
        SHA512(32782);
        
        private static final Map<Integer, HashAlgorithm> codeToEnum = null;
        private final int code;

        static {
            int i;
            HashMap hashMap = new HashMap();
            for (HashAlgorithm hashAlgorithm : values()) {
                hashMap.put(Integer.valueOf(hashAlgorithm.getCode()), hashAlgorithm);
            }
            codeToEnum = Collections.unmodifiableMap(hashMap);
        }

        private HashAlgorithm(int i) {
            this.code = i;
        }

        public int getCode() {
            return this.code;
        }

        public static HashAlgorithm getAlgorithmByCode(int i) {
            return codeToEnum.get(Integer.valueOf(i));
        }
    }
}
