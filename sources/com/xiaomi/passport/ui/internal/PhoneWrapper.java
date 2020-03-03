package com.xiaomi.passport.ui.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u000f\b\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u000f\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\u0017\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000bB\u0017\b\u0016\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000eB#\b\u0002\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\u000fJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0006\u0010\u0017\u001a\u00020\tJ\u0018\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u0016H\u0016R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\n\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u001c"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "Landroid/os/Parcelable;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "bundle", "Landroid/os/Bundle;", "(Landroid/os/Bundle;)V", "phone", "", "sid", "(Ljava/lang/String;Ljava/lang/String;)V", "activateInfo", "Lcom/xiaomi/accountsdk/account/data/ActivatorPhoneInfo;", "(Lcom/xiaomi/accountsdk/account/data/ActivatorPhoneInfo;Ljava/lang/String;)V", "(Ljava/lang/String;Lcom/xiaomi/accountsdk/account/data/ActivatorPhoneInfo;Ljava/lang/String;)V", "getActivateInfo", "()Lcom/xiaomi/accountsdk/account/data/ActivatorPhoneInfo;", "getPhone", "()Ljava/lang/String;", "getSid", "describeContents", "", "getPhoneOrMarkPhone", "writeToParcel", "", "flags", "CREATOR", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PhoneWrapper implements Parcelable {
    public static final CREATOR CREATOR = new CREATOR((DefaultConstructorMarker) null);
    @Nullable
    private final ActivatorPhoneInfo activateInfo;
    @Nullable
    private final String phone;
    @NotNull
    private final String sid;

    public int describeContents() {
        return 0;
    }

    private PhoneWrapper(String str, ActivatorPhoneInfo activatorPhoneInfo, String str2) {
        this.phone = str;
        this.activateInfo = activatorPhoneInfo;
        this.sid = str2;
    }

    @Nullable
    public final ActivatorPhoneInfo getActivateInfo() {
        return this.activateInfo;
    }

    @Nullable
    public final String getPhone() {
        return this.phone;
    }

    @NotNull
    public final String getSid() {
        return this.sid;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    @android.annotation.SuppressLint({"ParcelClassLoader"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PhoneWrapper(@org.jetbrains.annotations.NotNull android.os.Parcel r2) {
        /*
            r1 = this;
            java.lang.String r0 = "parcel"
            kotlin.jvm.internal.Intrinsics.f(r2, r0)
            android.os.Bundle r2 = r2.readBundle()
            java.lang.String r0 = "parcel.readBundle()"
            kotlin.jvm.internal.Intrinsics.b(r2, r0)
            r1.<init>((android.os.Bundle) r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.PhoneWrapper.<init>(android.os.Parcel):void");
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public PhoneWrapper(@org.jetbrains.annotations.NotNull android.os.Bundle r4) {
        /*
            r3 = this;
            java.lang.String r0 = "bundle"
            kotlin.jvm.internal.Intrinsics.f(r4, r0)
            java.lang.String r0 = "phone"
            java.lang.String r0 = r4.getString(r0)
            java.lang.String r1 = "activateInfo"
            android.os.Parcelable r1 = r4.getParcelable(r1)
            com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo r1 = (com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo) r1
            java.lang.String r2 = "sid"
            java.lang.String r4 = r4.getString(r2)
            java.lang.String r2 = "bundle.getString(\"sid\")"
            kotlin.jvm.internal.Intrinsics.b(r4, r2)
            r3.<init>(r0, r1, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.PhoneWrapper.<init>(android.os.Bundle):void");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PhoneWrapper(@NotNull String str, @NotNull String str2) {
        this(str, (ActivatorPhoneInfo) null, str2);
        Intrinsics.f(str, "phone");
        Intrinsics.f(str2, "sid");
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public PhoneWrapper(@NotNull ActivatorPhoneInfo activatorPhoneInfo, @NotNull String str) {
        this((String) null, activatorPhoneInfo, str);
        Intrinsics.f(activatorPhoneInfo, "activateInfo");
        Intrinsics.f(str, "sid");
    }

    @NotNull
    public final String getPhoneOrMarkPhone() {
        if (!TextUtils.isEmpty(this.phone)) {
            String str = this.phone;
            if (str == null) {
                Intrinsics.a();
            }
            return str;
        } else if (this.activateInfo == null) {
            return "null";
        } else {
            String str2 = this.activateInfo.phone;
            Intrinsics.b(str2, "activateInfo.phone");
            return str2;
        }
    }

    public void writeToParcel(@NotNull Parcel parcel, int i) {
        Intrinsics.f(parcel, "parcel");
        Bundle bundle = new Bundle();
        bundle.putString("phone", this.phone);
        bundle.putParcelable("activateInfo", this.activateInfo);
        bundle.putString("sid", this.sid);
        parcel.writeBundle(bundle);
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001d\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016¢\u0006\u0002\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PhoneWrapper$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "()V", "createFromParcel", "parcel", "Landroid/os/Parcel;", "newArray", "", "size", "", "(I)[Lcom/xiaomi/passport/ui/internal/PhoneWrapper;", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class CREATOR implements Parcelable.Creator<PhoneWrapper> {
        private CREATOR() {
        }

        public /* synthetic */ CREATOR(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public PhoneWrapper createFromParcel(@NotNull Parcel parcel) {
            Intrinsics.f(parcel, "parcel");
            return new PhoneWrapper(parcel);
        }

        @NotNull
        public PhoneWrapper[] newArray(int i) {
            return new PhoneWrapper[i];
        }
    }
}
