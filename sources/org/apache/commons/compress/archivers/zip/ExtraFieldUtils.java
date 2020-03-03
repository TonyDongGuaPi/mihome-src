package org.apache.commons.compress.archivers.zip;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.zip.ZipException;

public class ExtraFieldUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final int f3248a = 4;
    private static final Map<ZipShort, Class<?>> b = new ConcurrentHashMap();

    static {
        a((Class<?>) AsiExtraField.class);
        a((Class<?>) X5455_ExtendedTimestamp.class);
        a((Class<?>) X7875_NewUnix.class);
        a((Class<?>) JarMarker.class);
        a((Class<?>) UnicodePathExtraField.class);
        a((Class<?>) UnicodeCommentExtraField.class);
        a((Class<?>) Zip64ExtendedInformationExtraField.class);
        a((Class<?>) X000A_NTFS.class);
        a((Class<?>) X0014_X509Certificates.class);
        a((Class<?>) X0015_CertificateIdForFile.class);
        a((Class<?>) X0016_CertificateIdForCentralDirectory.class);
        a((Class<?>) X0017_StrongEncryptionHeader.class);
        a((Class<?>) X0019_EncryptionRecipientCertificateList.class);
    }

    public static void a(Class<?> cls) {
        try {
            b.put(((ZipExtraField) cls.newInstance()).getHeaderId(), cls);
        } catch (ClassCastException unused) {
            throw new RuntimeException(cls + " doesn't implement ZipExtraField");
        } catch (InstantiationException unused2) {
            throw new RuntimeException(cls + " is not a concrete class");
        } catch (IllegalAccessException unused3) {
            throw new RuntimeException(cls + "'s no-arg constructor is not public");
        }
    }

    public static ZipExtraField a(ZipShort zipShort) throws InstantiationException, IllegalAccessException {
        Class cls = b.get(zipShort);
        if (cls != null) {
            return (ZipExtraField) cls.newInstance();
        }
        UnrecognizedExtraField unrecognizedExtraField = new UnrecognizedExtraField();
        unrecognizedExtraField.a(zipShort);
        return unrecognizedExtraField;
    }

    public static ZipExtraField[] a(byte[] bArr) throws ZipException {
        return a(bArr, true, UnparseableExtraField.d);
    }

    public static ZipExtraField[] a(byte[] bArr, boolean z) throws ZipException {
        return a(bArr, z, UnparseableExtraField.d);
    }

    public static ZipExtraField[] a(byte[] bArr, boolean z, UnparseableExtraField unparseableExtraField) throws ZipException {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        while (true) {
            if (i > bArr.length - 4) {
                break;
            }
            ZipShort zipShort = new ZipShort(bArr, i);
            int value = new ZipShort(bArr, i + 2).getValue();
            int i2 = i + 4;
            if (i2 + value > bArr.length) {
                switch (unparseableExtraField.a()) {
                    case 0:
                        StringBuilder sb = new StringBuilder();
                        sb.append("bad extra field starting at ");
                        sb.append(i);
                        sb.append(".  Block length of ");
                        sb.append(value);
                        sb.append(" bytes exceeds remaining");
                        sb.append(" data of ");
                        sb.append((bArr.length - i) - 4);
                        sb.append(" bytes.");
                        throw new ZipException(sb.toString());
                    case 1:
                        break;
                    case 2:
                        UnparseableExtraFieldData unparseableExtraFieldData = new UnparseableExtraFieldData();
                        if (z) {
                            unparseableExtraFieldData.parseFromLocalFileData(bArr, i, bArr.length - i);
                        } else {
                            unparseableExtraFieldData.parseFromCentralDirectoryData(bArr, i, bArr.length - i);
                        }
                        arrayList.add(unparseableExtraFieldData);
                        break;
                    default:
                        throw new ZipException("unknown UnparseableExtraField key: " + unparseableExtraField.a());
                }
            } else {
                try {
                    ZipExtraField a2 = a(zipShort);
                    if (z) {
                        a2.parseFromLocalFileData(bArr, i2, value);
                    } else {
                        a2.parseFromCentralDirectoryData(bArr, i2, value);
                    }
                    arrayList.add(a2);
                    i += value + 4;
                } catch (IllegalAccessException | InstantiationException e) {
                    throw ((ZipException) new ZipException(e.getMessage()).initCause(e));
                }
            }
        }
        return (ZipExtraField[]) arrayList.toArray(new ZipExtraField[arrayList.size()]);
    }

    public static byte[] a(ZipExtraField[] zipExtraFieldArr) {
        byte[] localFileDataData;
        boolean z = zipExtraFieldArr.length > 0 && (zipExtraFieldArr[zipExtraFieldArr.length - 1] instanceof UnparseableExtraFieldData);
        int length = z ? zipExtraFieldArr.length - 1 : zipExtraFieldArr.length;
        int i = length * 4;
        for (ZipExtraField localFileDataLength : zipExtraFieldArr) {
            i += localFileDataLength.getLocalFileDataLength().getValue();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            System.arraycopy(zipExtraFieldArr[i3].getHeaderId().getBytes(), 0, bArr, i2, 2);
            System.arraycopy(zipExtraFieldArr[i3].getLocalFileDataLength().getBytes(), 0, bArr, i2 + 2, 2);
            i2 += 4;
            byte[] localFileDataData2 = zipExtraFieldArr[i3].getLocalFileDataData();
            if (localFileDataData2 != null) {
                System.arraycopy(localFileDataData2, 0, bArr, i2, localFileDataData2.length);
                i2 += localFileDataData2.length;
            }
        }
        if (z && (localFileDataData = zipExtraFieldArr[zipExtraFieldArr.length - 1].getLocalFileDataData()) != null) {
            System.arraycopy(localFileDataData, 0, bArr, i2, localFileDataData.length);
        }
        return bArr;
    }

    public static byte[] b(ZipExtraField[] zipExtraFieldArr) {
        byte[] centralDirectoryData;
        boolean z = zipExtraFieldArr.length > 0 && (zipExtraFieldArr[zipExtraFieldArr.length - 1] instanceof UnparseableExtraFieldData);
        int length = z ? zipExtraFieldArr.length - 1 : zipExtraFieldArr.length;
        int i = length * 4;
        for (ZipExtraField centralDirectoryLength : zipExtraFieldArr) {
            i += centralDirectoryLength.getCentralDirectoryLength().getValue();
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3++) {
            System.arraycopy(zipExtraFieldArr[i3].getHeaderId().getBytes(), 0, bArr, i2, 2);
            System.arraycopy(zipExtraFieldArr[i3].getCentralDirectoryLength().getBytes(), 0, bArr, i2 + 2, 2);
            i2 += 4;
            byte[] centralDirectoryData2 = zipExtraFieldArr[i3].getCentralDirectoryData();
            if (centralDirectoryData2 != null) {
                System.arraycopy(centralDirectoryData2, 0, bArr, i2, centralDirectoryData2.length);
                i2 += centralDirectoryData2.length;
            }
        }
        if (z && (centralDirectoryData = zipExtraFieldArr[zipExtraFieldArr.length - 1].getCentralDirectoryData()) != null) {
            System.arraycopy(centralDirectoryData, 0, bArr, i2, centralDirectoryData.length);
        }
        return bArr;
    }

    public static final class UnparseableExtraField {

        /* renamed from: a  reason: collision with root package name */
        public static final int f3249a = 0;
        public static final int b = 1;
        public static final int c = 2;
        public static final UnparseableExtraField d = new UnparseableExtraField(0);
        public static final UnparseableExtraField e = new UnparseableExtraField(1);
        public static final UnparseableExtraField f = new UnparseableExtraField(2);
        private final int g;

        private UnparseableExtraField(int i) {
            this.g = i;
        }

        public int a() {
            return this.g;
        }
    }
}
