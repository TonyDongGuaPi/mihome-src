package org.jacoco.agent.rt.internal_8ff85ea.core.internal.instr;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.jar.Attributes;
import java.util.jar.Manifest;
import java.util.regex.Pattern;

public class SignatureRemover {

    /* renamed from: a  reason: collision with root package name */
    private static final Pattern f3628a = Pattern.compile("META-INF/[^/]*\\.SF|META-INF/[^/]*\\.DSA|META-INF/[^/]*\\.RSA|META-INF/SIG-[^/]*");
    private static final String b = "META-INF/MANIFEST.MF";
    private static final String c = "-Digest";
    private boolean d = true;

    public void a(boolean z) {
        this.d = z;
    }

    public boolean a(String str) {
        return this.d && f3628a.matcher(str).matches();
    }

    public boolean a(String str, InputStream inputStream, OutputStream outputStream) throws IOException {
        if (!this.d || !b.equals(str)) {
            return false;
        }
        Manifest manifest = new Manifest(inputStream);
        a(manifest.getEntries().values());
        manifest.write(outputStream);
        return true;
    }

    private void a(Collection<Attributes> collection) {
        Iterator<Attributes> it = collection.iterator();
        while (it.hasNext()) {
            Attributes next = it.next();
            a(next);
            if (next.isEmpty()) {
                it.remove();
            }
        }
    }

    private void a(Attributes attributes) {
        Iterator<Object> it = attributes.keySet().iterator();
        while (it.hasNext()) {
            if (String.valueOf(it.next()).endsWith(c)) {
                it.remove();
            }
        }
    }
}
