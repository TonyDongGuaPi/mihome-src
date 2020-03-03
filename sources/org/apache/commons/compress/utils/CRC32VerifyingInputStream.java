package org.apache.commons.compress.utils;

import cn.com.fmsh.communication.core.MessageHead;
import java.io.InputStream;
import java.util.zip.CRC32;

public class CRC32VerifyingInputStream extends ChecksumVerifyingInputStream {
    public CRC32VerifyingInputStream(InputStream inputStream, long j, int i) {
        this(inputStream, j, ((long) i) & MessageHead.SERIAL_MAK);
    }

    public CRC32VerifyingInputStream(InputStream inputStream, long j, long j2) {
        super(new CRC32(), inputStream, j, j2);
    }
}
