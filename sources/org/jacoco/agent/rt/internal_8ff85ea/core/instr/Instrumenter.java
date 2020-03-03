package org.jacoco.agent.rt.internal_8ff85ea.core.instr;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassReader;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassVisitor;
import org.jacoco.agent.rt.internal_8ff85ea.asm.ClassWriter;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.ContentTypeDetector;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.Java9Support;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.Pack200Streams;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.flow.ClassProbesAdapter;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr.ClassInstrumenter;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr.ProbeArrayStrategyFactory;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr.SignatureRemover;
import org.jacoco.agent.rt.internal_8ff85ea.core.runtime.IExecutionDataAccessorGenerator;

public class Instrumenter {

    /* renamed from: a  reason: collision with root package name */
    private final IExecutionDataAccessorGenerator f3616a;
    private final SignatureRemover b = new SignatureRemover();

    public Instrumenter(IExecutionDataAccessorGenerator iExecutionDataAccessorGenerator) {
        this.f3616a = iExecutionDataAccessorGenerator;
    }

    public void a(boolean z) {
        this.b.a(z);
    }

    public byte[] a(ClassReader classReader) {
        AnonymousClass1 r0 = new ClassWriter(classReader, 0) {
            /* access modifiers changed from: protected */
            public String d(String str, String str2) {
                throw new IllegalStateException();
            }
        };
        classReader.a((ClassVisitor) new ClassProbesAdapter(new ClassInstrumenter(ProbeArrayStrategyFactory.a(classReader, this.f3616a), r0), true), 8);
        return r0.b();
    }

    public byte[] a(byte[] bArr, String str) throws IOException {
        try {
            if (!Java9Support.a(bArr)) {
                return a(new ClassReader(bArr));
            }
            byte[] a2 = a(new ClassReader(Java9Support.c(bArr)));
            Java9Support.d(a2);
            return a2;
        } catch (RuntimeException e) {
            throw a(str, e);
        }
    }

    public byte[] a(InputStream inputStream, String str) throws IOException {
        try {
            return a(Java9Support.a(inputStream), str);
        } catch (RuntimeException e) {
            throw a(str, e);
        }
    }

    public void a(InputStream inputStream, OutputStream outputStream, String str) throws IOException {
        try {
            outputStream.write(a(Java9Support.a(inputStream), str));
        } catch (RuntimeException e) {
            throw a(str, e);
        }
    }

    private IOException a(String str, RuntimeException runtimeException) {
        IOException iOException = new IOException(String.format("Error while instrumenting class %s.", new Object[]{str}));
        iOException.initCause(runtimeException);
        return iOException;
    }

    public int b(InputStream inputStream, OutputStream outputStream, String str) throws IOException {
        ContentTypeDetector contentTypeDetector = new ContentTypeDetector(inputStream);
        int b2 = contentTypeDetector.b();
        if (b2 == -889275714) {
            a(contentTypeDetector.a(), outputStream, str);
            return 1;
        } else if (b2 == -889270259) {
            return e(contentTypeDetector.a(), outputStream, str);
        } else {
            if (b2 == 529203200) {
                return d(contentTypeDetector.a(), outputStream, str);
            }
            if (b2 == 1347093252) {
                return c(contentTypeDetector.a(), outputStream, str);
            }
            a(contentTypeDetector.a(), outputStream);
            return 0;
        }
    }

    private int c(InputStream inputStream, OutputStream outputStream, String str) throws IOException {
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);
        int i = 0;
        while (true) {
            ZipEntry nextEntry = zipInputStream.getNextEntry();
            if (nextEntry != null) {
                String name = nextEntry.getName();
                if (!this.b.a(name)) {
                    zipOutputStream.putNextEntry(new ZipEntry(name));
                    if (!this.b.a(name, zipInputStream, zipOutputStream)) {
                        i += b(zipInputStream, zipOutputStream, str + "@" + name);
                    }
                    zipOutputStream.closeEntry();
                }
            } else {
                zipOutputStream.finish();
                return i;
            }
        }
    }

    private int d(InputStream inputStream, OutputStream outputStream, String str) throws IOException {
        GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(outputStream);
        int b2 = b(new GZIPInputStream(inputStream), gZIPOutputStream, str);
        gZIPOutputStream.finish();
        return b2;
    }

    private int e(InputStream inputStream, OutputStream outputStream, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int b2 = b(Pack200Streams.a(inputStream), byteArrayOutputStream, str);
        Pack200Streams.a(byteArrayOutputStream.toByteArray(), outputStream);
        return b2;
    }

    private void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr);
            if (read != -1) {
                outputStream.write(bArr, 0, read);
            } else {
                return;
            }
        }
    }
}
