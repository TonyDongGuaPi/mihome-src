package org.apache.commons.compress.changes;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.ArchiveInputStream;
import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

public class ChangeSetPerformer {

    /* renamed from: a  reason: collision with root package name */
    private final Set<Change> f3304a;

    interface ArchiveEntryIterator {
        boolean a() throws IOException;

        ArchiveEntry b();

        InputStream c() throws IOException;
    }

    public ChangeSetPerformer(ChangeSet changeSet) {
        this.f3304a = changeSet.a();
    }

    public ChangeSetResults a(ArchiveInputStream archiveInputStream, ArchiveOutputStream archiveOutputStream) throws IOException {
        return a((ArchiveEntryIterator) new ArchiveInputStreamIterator(archiveInputStream), archiveOutputStream);
    }

    public ChangeSetResults a(ZipFile zipFile, ArchiveOutputStream archiveOutputStream) throws IOException {
        return a((ArchiveEntryIterator) new ZipFileIterator(zipFile), archiveOutputStream);
    }

    private ChangeSetResults a(ArchiveEntryIterator archiveEntryIterator, ArchiveOutputStream archiveOutputStream) throws IOException {
        boolean z;
        ChangeSetResults changeSetResults = new ChangeSetResults();
        LinkedHashSet linkedHashSet = new LinkedHashSet(this.f3304a);
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            Change change = (Change) it.next();
            if (change.d() == 2 && change.e()) {
                a(change.b(), archiveOutputStream, change.a());
                it.remove();
                changeSetResults.c(change.a().getName());
            }
        }
        while (archiveEntryIterator.a()) {
            ArchiveEntry b = archiveEntryIterator.b();
            Iterator it2 = linkedHashSet.iterator();
            while (true) {
                z = false;
                if (!it2.hasNext()) {
                    z = true;
                    break;
                }
                Change change2 = (Change) it2.next();
                int d = change2.d();
                String name = b.getName();
                if (d != 1 || name == null) {
                    if (d == 4 && name != null) {
                        if (name.startsWith(change2.c() + "/")) {
                            changeSetResults.a(name);
                            break;
                        }
                    }
                } else if (name.equals(change2.c())) {
                    it2.remove();
                    changeSetResults.a(name);
                    break;
                }
            }
            if (z && !a((Set<Change>) linkedHashSet, b) && !changeSetResults.d(b.getName())) {
                a(archiveEntryIterator.c(), archiveOutputStream, b);
                changeSetResults.b(b.getName());
            }
        }
        Iterator it3 = linkedHashSet.iterator();
        while (it3.hasNext()) {
            Change change3 = (Change) it3.next();
            if (change3.d() == 2 && !change3.e() && !changeSetResults.d(change3.a().getName())) {
                a(change3.b(), archiveOutputStream, change3.a());
                it3.remove();
                changeSetResults.c(change3.a().getName());
            }
        }
        archiveOutputStream.b();
        return changeSetResults;
    }

    private boolean a(Set<Change> set, ArchiveEntry archiveEntry) {
        String name = archiveEntry.getName();
        if (set.isEmpty()) {
            return false;
        }
        for (Change next : set) {
            int d = next.d();
            String c = next.c();
            if (d == 1 && name.equals(c)) {
                return true;
            }
            if (d == 4) {
                if (name.startsWith(c + "/")) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(InputStream inputStream, ArchiveOutputStream archiveOutputStream, ArchiveEntry archiveEntry) throws IOException {
        archiveOutputStream.a(archiveEntry);
        IOUtils.a(inputStream, (OutputStream) archiveOutputStream);
        archiveOutputStream.a();
    }

    private static class ArchiveInputStreamIterator implements ArchiveEntryIterator {

        /* renamed from: a  reason: collision with root package name */
        private final ArchiveInputStream f3305a;
        private ArchiveEntry b;

        ArchiveInputStreamIterator(ArchiveInputStream archiveInputStream) {
            this.f3305a = archiveInputStream;
        }

        public boolean a() throws IOException {
            ArchiveEntry a2 = this.f3305a.a();
            this.b = a2;
            return a2 != null;
        }

        public ArchiveEntry b() {
            return this.b;
        }

        public InputStream c() {
            return this.f3305a;
        }
    }

    private static class ZipFileIterator implements ArchiveEntryIterator {

        /* renamed from: a  reason: collision with root package name */
        private final ZipFile f3306a;
        private final Enumeration<ZipArchiveEntry> b;
        private ZipArchiveEntry c;

        ZipFileIterator(ZipFile zipFile) {
            this.f3306a = zipFile;
            this.b = zipFile.c();
        }

        public boolean a() {
            return this.b.hasMoreElements();
        }

        public ArchiveEntry b() {
            this.c = this.b.nextElement();
            return this.c;
        }

        public InputStream c() throws IOException {
            return this.f3306a.c(this.c);
        }
    }
}
