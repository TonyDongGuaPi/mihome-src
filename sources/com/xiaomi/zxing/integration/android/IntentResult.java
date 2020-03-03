package com.xiaomi.zxing.integration.android;

public final class IntentResult {

    /* renamed from: a  reason: collision with root package name */
    private final String f1679a;
    private final String b;
    private final byte[] c;
    private final Integer d;
    private final String e;

    IntentResult() {
        this((String) null, (String) null, (byte[]) null, (Integer) null, (String) null);
    }

    IntentResult(String str, String str2, byte[] bArr, Integer num, String str3) {
        this.f1679a = str;
        this.b = str2;
        this.c = bArr;
        this.d = num;
        this.e = str3;
    }

    public String a() {
        return this.f1679a;
    }

    public String b() {
        return this.b;
    }

    public byte[] c() {
        return this.c;
    }

    public Integer d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Format: ");
        sb.append(this.b);
        sb.append(10);
        sb.append("Contents: ");
        sb.append(this.f1679a);
        sb.append(10);
        int length = this.c == null ? 0 : this.c.length;
        sb.append("Raw bytes: (");
        sb.append(length);
        sb.append(" bytes)\n");
        sb.append("Orientation: ");
        sb.append(this.d);
        sb.append(10);
        sb.append("EC level: ");
        sb.append(this.e);
        sb.append(10);
        return sb.toString();
    }
}
