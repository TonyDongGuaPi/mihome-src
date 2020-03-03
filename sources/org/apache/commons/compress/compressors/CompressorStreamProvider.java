package org.apache.commons.compress.compressors;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;

public interface CompressorStreamProvider {
    CompressorInputStream a(String str, InputStream inputStream, boolean z) throws CompressorException;

    CompressorOutputStream a(String str, OutputStream outputStream) throws CompressorException;

    Set<String> q();

    Set<String> r();
}
