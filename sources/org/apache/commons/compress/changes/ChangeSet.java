package org.apache.commons.compress.changes;

import java.io.InputStream;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import org.apache.commons.compress.archivers.ArchiveEntry;

public final class ChangeSet {

    /* renamed from: a  reason: collision with root package name */
    private final Set<Change> f3303a = new LinkedHashSet();

    public void a(String str) {
        b(new Change(str, 1));
    }

    public void b(String str) {
        b(new Change(str, 4));
    }

    public void a(ArchiveEntry archiveEntry, InputStream inputStream) {
        a(archiveEntry, inputStream, true);
    }

    public void a(ArchiveEntry archiveEntry, InputStream inputStream, boolean z) {
        a(new Change(archiveEntry, inputStream, z));
    }

    private void a(Change change) {
        if (2 == change.d() && change.b() != null) {
            if (!this.f3303a.isEmpty()) {
                Iterator<Change> it = this.f3303a.iterator();
                while (it.hasNext()) {
                    Change next = it.next();
                    if (next.d() == 2 && next.a() != null && next.a().equals(change.a())) {
                        if (change.e()) {
                            it.remove();
                            this.f3303a.add(change);
                            return;
                        }
                        return;
                    }
                }
            }
            this.f3303a.add(change);
        }
    }

    private void b(Change change) {
        String name;
        if ((1 == change.d() || 4 == change.d()) && change.c() != null) {
            String c = change.c();
            if (c != null && !this.f3303a.isEmpty()) {
                Iterator<Change> it = this.f3303a.iterator();
                while (it.hasNext()) {
                    Change next = it.next();
                    if (!(next.d() != 2 || next.a() == null || (name = next.a().getName()) == null)) {
                        if (1 == change.d() && c.equals(name)) {
                            it.remove();
                        } else if (4 == change.d()) {
                            if (name.matches(c + "/.*")) {
                                it.remove();
                            }
                        }
                    }
                }
            }
            this.f3303a.add(change);
        }
    }

    /* access modifiers changed from: package-private */
    public Set<Change> a() {
        return new LinkedHashSet(this.f3303a);
    }
}
