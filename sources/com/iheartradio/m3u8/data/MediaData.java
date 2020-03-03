package com.iheartradio.m3u8.data;

import com.taobao.weex.el.parse.Operators;
import java.util.List;
import java.util.Objects;

public class MediaData {

    /* renamed from: a  reason: collision with root package name */
    private final MediaType f6043a;
    private final String b;
    private final String c;
    private final String d;
    private final String e;
    private final String f;
    private final boolean g;
    private final boolean h;
    private final boolean i;
    private final String j;
    private final List<String> k;

    private MediaData(MediaType mediaType, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, List<String> list) {
        this.f6043a = mediaType;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = z;
        this.h = z2;
        this.i = z3;
        this.j = str6;
        this.k = DataUtil.a(list);
    }

    public MediaType a() {
        return this.f6043a;
    }

    public boolean b() {
        return this.b != null && !this.b.isEmpty();
    }

    public String c() {
        return this.b;
    }

    public String d() {
        return this.c;
    }

    public boolean e() {
        return this.d != null;
    }

    public String f() {
        return this.d;
    }

    public boolean g() {
        return this.e != null;
    }

    public String h() {
        return this.e;
    }

    public String i() {
        return this.f;
    }

    public boolean j() {
        return this.g;
    }

    public boolean k() {
        return this.h;
    }

    public boolean l() {
        return this.i;
    }

    public boolean m() {
        return this.j != null;
    }

    public String n() {
        return this.j;
    }

    public boolean o() {
        return !this.k.isEmpty();
    }

    public List<String> p() {
        return this.k;
    }

    public Builder q() {
        return new Builder(this.f6043a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.e, Boolean.valueOf(this.h), this.k, Boolean.valueOf(this.g), Boolean.valueOf(this.i), this.c, this.j, this.d, this.f, this.f6043a, this.b});
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MediaData)) {
            return false;
        }
        MediaData mediaData = (MediaData) obj;
        if (this.f6043a != mediaData.f6043a || !Objects.equals(this.b, mediaData.b) || !Objects.equals(this.c, mediaData.c) || !Objects.equals(this.d, mediaData.d) || !Objects.equals(this.e, mediaData.e) || !Objects.equals(this.f, mediaData.f) || this.g != mediaData.g || this.h != mediaData.h || this.i != mediaData.i || !Objects.equals(this.j, mediaData.j) || !Objects.equals(this.k, mediaData.k)) {
            return false;
        }
        return true;
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private MediaType f6044a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private boolean g;
        private boolean h;
        private boolean i;
        private String j;
        private List<String> k;

        public Builder() {
        }

        private Builder(MediaType mediaType, String str, String str2, String str3, String str4, String str5, boolean z, boolean z2, boolean z3, String str6, List<String> list) {
            this.f6044a = mediaType;
            this.b = str;
            this.c = str2;
            this.d = str3;
            this.e = str4;
            this.f = str5;
            this.g = z;
            this.h = z2;
            this.i = z3;
            this.j = str6;
            this.k = list;
        }

        public Builder a(MediaType mediaType) {
            this.f6044a = mediaType;
            return this;
        }

        public Builder a(String str) {
            this.b = str;
            return this;
        }

        public Builder b(String str) {
            this.c = str;
            return this;
        }

        public Builder c(String str) {
            this.d = str;
            return this;
        }

        public Builder d(String str) {
            this.e = str;
            return this;
        }

        public Builder e(String str) {
            this.f = str;
            return this;
        }

        public Builder a(boolean z) {
            this.g = z;
            return this;
        }

        public Builder b(boolean z) {
            this.h = z;
            return this;
        }

        public Builder c(boolean z) {
            this.i = z;
            return this;
        }

        public Builder f(String str) {
            this.j = str;
            return this;
        }

        public Builder a(List<String> list) {
            this.k = list;
            return this;
        }

        public MediaData a() {
            return new MediaData(this.f6044a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i, this.j, this.k);
        }
    }

    public String toString() {
        return "MediaData [mType=" + this.f6043a + ", mUri=" + this.b + ", mGroupId=" + this.c + ", mLanguage=" + this.d + ", mAssociatedLanguage=" + this.e + ", mName=" + this.f + ", mDefault=" + this.g + ", mAutoSelect=" + this.h + ", mForced=" + this.i + ", mInStreamId=" + this.j + ", mCharacteristics=" + this.k + Operators.ARRAY_END_STR;
    }
}
