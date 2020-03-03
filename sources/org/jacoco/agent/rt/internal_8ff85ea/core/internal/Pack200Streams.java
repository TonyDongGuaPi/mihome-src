package org.jacoco.agent.rt.internal_8ff85ea.core.internal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.jar.Pack200;

public final class Pack200Streams {
    public static InputStream a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        JarOutputStream jarOutputStream = new JarOutputStream(byteArrayOutputStream);
        Pack200.newUnpacker().unpack(new NoCloseInput(inputStream), jarOutputStream);
        jarOutputStream.finish();
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }

    public static void a(byte[] bArr, OutputStream outputStream) throws IOException {
        Pack200.newPacker().pack(new JarInputStream(new ByteArrayInputStream(bArr)), outputStream);
    }

    private static class NoCloseInput extends FilterInputStream {
        public void close() throws IOException {
        }

        protected NoCloseInput(InputStream inputStream) {
            super(inputStream);
        }
    }

    private Pack200Streams() {
    }
}
