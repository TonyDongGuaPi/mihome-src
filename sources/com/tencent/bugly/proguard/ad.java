package com.tencent.bugly.proguard;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public final class ad implements ab {
    public final byte[] a(byte[] bArr) throws Exception {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream);
        ZipEntry zipEntry = new ZipEntry(ArchiveStreamFactory.g);
        zipEntry.setSize((long) bArr.length);
        zipOutputStream.putNextEntry(zipEntry);
        zipOutputStream.write(bArr);
        zipOutputStream.closeEntry();
        zipOutputStream.close();
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArrayOutputStream.close();
        return byteArray;
    }

    public final byte[] b(byte[] bArr) throws Exception {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        ZipInputStream zipInputStream = new ZipInputStream(byteArrayInputStream);
        byte[] bArr2 = null;
        while (zipInputStream.getNextEntry() != null) {
            byte[] bArr3 = new byte[1024];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            while (true) {
                int read = zipInputStream.read(bArr3, 0, bArr3.length);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr3, 0, read);
            }
            bArr2 = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        }
        zipInputStream.close();
        byteArrayInputStream.close();
        return bArr2;
    }
}
