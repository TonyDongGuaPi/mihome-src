package org.jacoco.agent.rt.internal_8ff85ea;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jacoco.agent.rt.internal_8ff85ea.core.internal.data.CRC64;

class ClassFileDumper {

    /* renamed from: a  reason: collision with root package name */
    private final File f3578a;

    ClassFileDumper(String str) {
        if (str == null) {
            this.f3578a = null;
        } else {
            this.f3578a = new File(str);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, byte[] bArr) throws IOException {
        File file;
        if (this.f3578a != null) {
            int lastIndexOf = str.lastIndexOf(47);
            if (lastIndexOf != -1) {
                file = new File(this.f3578a, str.substring(0, lastIndexOf));
                str = str.substring(lastIndexOf + 1);
            } else {
                file = this.f3578a;
            }
            file.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, String.format("%s.%016x.class", new Object[]{str, Long.valueOf(CRC64.a(bArr))})));
            fileOutputStream.write(bArr);
            fileOutputStream.close();
        }
    }
}
