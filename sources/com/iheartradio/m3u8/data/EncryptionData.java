package com.iheartradio.m3u8.data;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class EncryptionData {

    /* renamed from: a  reason: collision with root package name */
    private final EncryptionMethod f6037a;
    private final String b;
    private final List<Byte> c;
    private final String d;
    private final List<Integer> e;

    private EncryptionData(EncryptionMethod encryptionMethod, String str, List<Byte> list, String str2, List<Integer> list2) {
        List<Byte> list3;
        this.f6037a = encryptionMethod;
        this.b = str;
        List<Integer> list4 = null;
        if (list == null) {
            list3 = null;
        } else {
            list3 = Collections.unmodifiableList(list);
        }
        this.c = list3;
        this.d = str2;
        this.e = list2 != null ? Collections.unmodifiableList(list2) : list4;
    }

    public EncryptionMethod a() {
        return this.f6037a;
    }

    public boolean b() {
        return this.b != null && !this.b.isEmpty();
    }

    public String c() {
        return this.b;
    }

    public boolean d() {
        return this.c != null;
    }

    public List<Byte> e() {
        return this.c;
    }

    public boolean f() {
        return this.d != null;
    }

    public String g() {
        return this.d;
    }

    public boolean h() {
        return this.e != null;
    }

    public List<Integer> i() {
        return this.e;
    }

    public Builder j() {
        return new Builder(this.f6037a, this.b, this.c, this.d, this.e);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.c, this.d, this.e, this.f6037a, this.b});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EncryptionData)) {
            return false;
        }
        EncryptionData encryptionData = (EncryptionData) obj;
        if (!Objects.equals(this.c, encryptionData.c) || !Objects.equals(this.d, encryptionData.d) || !Objects.equals(this.e, encryptionData.e) || !Objects.equals(this.f6037a, encryptionData.f6037a) || !Objects.equals(this.b, encryptionData.b)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private EncryptionMethod f6038a;
        private String b;
        private List<Byte> c;
        private String d;
        private List<Integer> e;

        public Builder() {
        }

        private Builder(EncryptionMethod encryptionMethod, String str, List<Byte> list, String str2, List<Integer> list2) {
            this.f6038a = encryptionMethod;
            this.b = str;
            this.c = list;
            this.d = str2;
            this.e = list2;
        }

        public Builder a(EncryptionMethod encryptionMethod) {
            this.f6038a = encryptionMethod;
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder a(List<Byte> list) {
            this.c = list;
            return this;
        }

        public Builder b(String str) {
            this.d = str;
            return this;
        }

        public Builder b(List<Integer> list) {
            this.e = list;
            return this;
        }

        public EncryptionData a() {
            return new EncryptionData(this.f6038a, this.b, this.c, this.d, this.e);
        }
    }
}
