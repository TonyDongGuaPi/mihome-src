package org.apache.commons.compress.archivers.zip;

import java.io.IOException;
import java.nio.ByteBuffer;

public interface ZipEncoding {
    String a(byte[] bArr) throws IOException;

    boolean a(String str);

    ByteBuffer b(String str) throws IOException;
}
