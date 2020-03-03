package com.xiaomi.passport.ui.internal;

import com.taobao.weex.el.parse.Operators;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001J\t\u0010\u0014\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b¨\u0006\u0015"}, d2 = {"Lcom/xiaomi/passport/ui/internal/URLLicense;", "", "readableText", "", "url", "key", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getKey", "()Ljava/lang/String;", "getReadableText", "getUrl", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class URLLicense {
    @NotNull
    private final String key;
    @NotNull
    private final String readableText;
    @NotNull
    private final String url;

    @NotNull
    public static /* synthetic */ URLLicense copy$default(URLLicense uRLLicense, String str, String str2, String str3, int i, Object obj) {
        if ((i & 1) != 0) {
            str = uRLLicense.readableText;
        }
        if ((i & 2) != 0) {
            str2 = uRLLicense.url;
        }
        if ((i & 4) != 0) {
            str3 = uRLLicense.key;
        }
        return uRLLicense.copy(str, str2, str3);
    }

    @NotNull
    public final String component1() {
        return this.readableText;
    }

    @NotNull
    public final String component2() {
        return this.url;
    }

    @NotNull
    public final String component3() {
        return this.key;
    }

    @NotNull
    public final URLLicense copy(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "readableText");
        Intrinsics.f(str2, "url");
        Intrinsics.f(str3, "key");
        return new URLLicense(str, str2, str3);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof URLLicense)) {
            return false;
        }
        URLLicense uRLLicense = (URLLicense) obj;
        return Intrinsics.a((Object) this.readableText, (Object) uRLLicense.readableText) && Intrinsics.a((Object) this.url, (Object) uRLLicense.url) && Intrinsics.a((Object) this.key, (Object) uRLLicense.key);
    }

    public int hashCode() {
        String str = this.readableText;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.url;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.key;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "URLLicense(readableText=" + this.readableText + ", url=" + this.url + ", key=" + this.key + Operators.BRACKET_END_STR;
    }

    public URLLicense(@NotNull String str, @NotNull String str2, @NotNull String str3) {
        Intrinsics.f(str, "readableText");
        Intrinsics.f(str2, "url");
        Intrinsics.f(str3, "key");
        this.readableText = str;
        this.url = str2;
        this.key = str3;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ URLLicense(String str, String str2, String str3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? str : str3);
    }

    @NotNull
    public final String getKey() {
        return this.key;
    }

    @NotNull
    public final String getReadableText() {
        return this.readableText;
    }

    @NotNull
    public final String getUrl() {
        return this.url;
    }
}
