package org.apache.commons.compress.archivers.dump;

import com.drew.metadata.iptc.IptcDirectory;
import com.tiqiaa.constant.KeyType;
import java.io.IOException;
import java.util.Date;
import org.apache.commons.compress.archivers.zip.ZipEncoding;

public class DumpArchiveSummary {

    /* renamed from: a  reason: collision with root package name */
    private long f3216a;
    private long b;
    private int c;
    private String d;
    private int e;
    private String f;
    private String g;
    private String h;
    private int i;
    private int j;
    private int k;

    DumpArchiveSummary(byte[] bArr, ZipEncoding zipEncoding) throws IOException {
        this.f3216a = ((long) DumpArchiveUtil.b(bArr, 4)) * 1000;
        this.b = ((long) DumpArchiveUtil.b(bArr, 8)) * 1000;
        this.c = DumpArchiveUtil.b(bArr, 12);
        this.d = DumpArchiveUtil.a(zipEncoding, bArr, 676, 16).trim();
        this.e = DumpArchiveUtil.b(bArr, 692);
        this.f = DumpArchiveUtil.a(zipEncoding, bArr, IptcDirectory.av, 64).trim();
        this.g = DumpArchiveUtil.a(zipEncoding, bArr, 760, 64).trim();
        this.h = DumpArchiveUtil.a(zipEncoding, bArr, KeyType.FORWARD, 64).trim();
        this.i = DumpArchiveUtil.b(bArr, 888);
        this.j = DumpArchiveUtil.b(bArr, 892);
        this.k = DumpArchiveUtil.b(bArr, 896);
    }

    public Date a() {
        return new Date(this.f3216a);
    }

    public void a(Date date) {
        this.f3216a = date.getTime();
    }

    public Date b() {
        return new Date(this.b);
    }

    public void b(Date date) {
        this.b = date.getTime();
    }

    public int c() {
        return this.c;
    }

    public void a(int i2) {
        this.c = i2;
    }

    public int d() {
        return this.e;
    }

    public void b(int i2) {
        this.e = i2;
    }

    public String e() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String f() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public String g() {
        return this.g;
    }

    public void c(String str) {
        this.g = str;
    }

    public String h() {
        return this.h;
    }

    public void d(String str) {
        this.h = str;
    }

    public int i() {
        return this.i;
    }

    public void c(int i2) {
        this.i = i2;
    }

    public int j() {
        return this.j;
    }

    public void d(int i2) {
        this.j = i2;
    }

    public int k() {
        return this.k;
    }

    public void e(int i2) {
        this.k = i2;
    }

    public boolean l() {
        return (this.i & 1) == 1;
    }

    public boolean m() {
        return (this.i & 2) == 2;
    }

    public boolean n() {
        return (this.i & 128) == 128;
    }

    public boolean o() {
        return (this.i & 256) == 256;
    }

    public boolean p() {
        return (this.i & 32768) == 32768;
    }

    public int hashCode() {
        int hashCode = (int) (((long) (this.d != null ? this.d.hashCode() : 17)) + (this.f3216a * 31));
        if (this.h != null) {
            hashCode = (this.h.hashCode() * 31) + 17;
        }
        return this.g != null ? (this.g.hashCode() * 31) + 17 : hashCode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !obj.getClass().equals(getClass())) {
            return false;
        }
        DumpArchiveSummary dumpArchiveSummary = (DumpArchiveSummary) obj;
        return this.f3216a == dumpArchiveSummary.f3216a && h() != null && h().equals(dumpArchiveSummary.h()) && g() != null && g().equals(dumpArchiveSummary.g());
    }
}
