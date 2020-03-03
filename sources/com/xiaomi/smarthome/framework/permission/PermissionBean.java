package com.xiaomi.smarthome.framework.permission;

import android.os.Parcel;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b\b\u0018\u0000 &2\u00020\u0001:\u0001&B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B-\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006HÆ\u0003¢\u0006\u0002\u0010\u0010J\t\u0010\u0015\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0007HÆ\u0003J\t\u0010\u0017\u001a\u00020\u000bHÆ\u0003J<\u0010\u0018\u001a\u00020\u00002\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\b\b\u0002\u0010\b\u001a\u00020\u00072\b\b\u0002\u0010\t\u001a\u00020\u00072\b\b\u0002\u0010\n\u001a\u00020\u000bHÆ\u0001¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0013\u0010\u001c\u001a\u00020\u000b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0002J\b\u0010\u001f\u001a\u00020\u001bH\u0016J\u0006\u0010 \u001a\u00020\u000bJ\u0006\u0010!\u001a\u00020\u000bJ\t\u0010\"\u001a\u00020\u0007HÖ\u0001J\u0018\u0010#\u001a\u00020$2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010%\u001a\u00020\u001bH\u0016R\u0011\u0010\t\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0012R\u0011\u0010\b\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000e¨\u0006'"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "group", "", "", "name", "desc", "isOptional", "", "([Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)V", "getDesc", "()Ljava/lang/String;", "getGroup", "()[Ljava/lang/String;", "[Ljava/lang/String;", "()Z", "getName", "component1", "component2", "component3", "component4", "copy", "([Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Z)Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "describeContents", "", "equals", "other", "", "hashCode", "isGranted", "isVirtualPermission", "toString", "writeToParcel", "", "flags", "CREATOR", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class PermissionBean implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((DefaultConstructorMarker) null);
    @NotNull

    /* renamed from: a  reason: collision with root package name */
    private final String[] f17102a;
    @NotNull
    private final String b;
    @NotNull
    private final String c;
    private final boolean d;

    @NotNull
    public static /* synthetic */ PermissionBean a(PermissionBean permissionBean, String[] strArr, String str, String str2, boolean z, int i, Object obj) {
        if ((i & 1) != 0) {
            strArr = permissionBean.f17102a;
        }
        if ((i & 2) != 0) {
            str = permissionBean.b;
        }
        if ((i & 4) != 0) {
            str2 = permissionBean.c;
        }
        if ((i & 8) != 0) {
            z = permissionBean.d;
        }
        return permissionBean.a(strArr, str, str2, z);
    }

    @NotNull
    public final PermissionBean a(@NotNull String[] strArr, @NotNull String str, @NotNull String str2, boolean z) {
        Intrinsics.f(strArr, "group");
        Intrinsics.f(str, "name");
        Intrinsics.f(str2, "desc");
        return new PermissionBean(strArr, str, str2, z);
    }

    public int describeContents() {
        return 0;
    }

    @NotNull
    public final String[] g() {
        return this.f17102a;
    }

    @NotNull
    public final String h() {
        return this.b;
    }

    @NotNull
    public final String i() {
        return this.c;
    }

    public final boolean j() {
        return this.d;
    }

    @NotNull
    public String toString() {
        return "PermissionBean(group=" + Arrays.toString(this.f17102a) + ", name=" + this.b + ", desc=" + this.c + ", isOptional=" + this.d + Operators.BRACKET_END_STR;
    }

    public PermissionBean(@NotNull String[] strArr, @NotNull String str, @NotNull String str2, boolean z) {
        Intrinsics.f(strArr, "group");
        Intrinsics.f(str, "name");
        Intrinsics.f(str2, "desc");
        this.f17102a = strArr;
        this.b = str;
        this.c = str2;
        this.d = z;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ PermissionBean(String[] strArr, String str, String str2, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(strArr, str, str2, (i & 8) != 0 ? true : z);
    }

    @NotNull
    public final String[] c() {
        return this.f17102a;
    }

    @NotNull
    public final String d() {
        return this.b;
    }

    @NotNull
    public final String e() {
        return this.c;
    }

    public final boolean f() {
        return this.d;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PermissionBean(@org.jetbrains.annotations.NotNull android.os.Parcel r6) {
        /*
            r5 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.Intrinsics.f(r6, r0)
            java.lang.String[] r0 = r6.createStringArray()
            java.lang.String r1 = "parcel.createStringArray()"
            kotlin.jvm.internal.Intrinsics.b(r0, r1)
            java.lang.String r1 = r6.readString()
            java.lang.String r2 = "parcel.readString()"
            kotlin.jvm.internal.Intrinsics.b(r1, r2)
            java.lang.String r2 = r6.readString()
            java.lang.String r3 = "parcel.readString()"
            kotlin.jvm.internal.Intrinsics.b(r2, r3)
            byte r6 = r6.readByte()
            r3 = 0
            byte r4 = (byte) r3
            if (r6 == r4) goto L_0x0029
            r3 = 1
        L_0x0029:
            r5.<init>(r0, r1, r2, r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.permission.PermissionBean.<init>(android.os.Parcel):void");
    }

    public final boolean a() {
        return this.f17102a.length == 0;
    }

    public final boolean b() {
        String str;
        if (a()) {
            return true;
        }
        String[] strArr = this.f17102a;
        int length = strArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                str = null;
                break;
            }
            str = strArr[i];
            if (!PermissionHelper.a(str)) {
                break;
            }
            i++;
        }
        if (str == null) {
            return true;
        }
        return false;
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.f(parcel, "parcel");
        parcel.writeStringArray(this.f17102a);
        parcel.writeString(this.b);
        parcel.writeString(this.c);
        parcel.writeByte(this.d ? (byte) 1 : 0);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!Intrinsics.a((Object) getClass(), (Object) obj != null ? obj.getClass() : null)) {
            return false;
        }
        if (obj != null) {
            PermissionBean permissionBean = (PermissionBean) obj;
            if (Arrays.equals(this.f17102a, permissionBean.f17102a) && !(!Intrinsics.a((Object) this.b, (Object) permissionBean.b)) && !(!Intrinsics.a((Object) this.c, (Object) permissionBean.c)) && this.d == permissionBean.d) {
                return true;
            }
            return false;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.smarthome.framework.permission.PermissionBean");
    }

    public int hashCode() {
        return (((((Arrays.hashCode(this.f17102a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Boolean.valueOf(this.d).hashCode();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/xiaomi/smarthome/framework/permission/PermissionBean$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/xiaomi/smarthome/framework/permission/PermissionBean;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class CREATOR implements Parcelable.Creator<PermissionBean> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        /* renamed from: a */
        public PermissionBean createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.f(parcel, "parcel");
            return new PermissionBean(parcel);
        }

        @NotNull
        /* renamed from: a */
        public PermissionBean[] newArray(int i) {
            return new PermissionBean[i];
        }
    }
}
