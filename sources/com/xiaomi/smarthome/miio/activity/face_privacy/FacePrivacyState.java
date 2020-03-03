package com.xiaomi.smarthome.miio.activity.face_privacy;

import android.content.Context;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyApi;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0004\u0003\u0004\u0005\u0006B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0001\u0004\u0007\b\t\n¨\u0006\u000b"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "", "()V", "ErrorState", "FaceStatusDataState", "LoadingState", "ToastState", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$LoadingState;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$ToastState;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$ErrorState;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$FaceStatusDataState;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public abstract class FacePrivacyState {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$LoadingState;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "()V", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class LoadingState extends FacePrivacyState {

        /* renamed from: a  reason: collision with root package name */
        public static final LoadingState f11893a = new LoadingState();

        private LoadingState() {
            super((DefaultConstructorMarker) null);
        }
    }

    private FacePrivacyState() {
    }

    public /* synthetic */ FacePrivacyState(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u0007\u001a\u00020\u0003HÂ\u0003J\t\u0010\b\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\t\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\u000e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$ToastState;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "msgRes", "", "msgStr", "", "(ILjava/lang/String;)V", "component1", "component2", "copy", "equals", "", "other", "", "getMsg", "context", "Landroid/content/Context;", "hashCode", "toString", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class ToastState extends FacePrivacyState {

        /* renamed from: a  reason: collision with root package name */
        private int f11894a;
        private final String b;

        public ToastState() {
            this(0, (String) null, 3, (DefaultConstructorMarker) null);
        }

        private final int a() {
            return this.f11894a;
        }

        @NotNull
        public static /* synthetic */ ToastState a(ToastState toastState, int i, String str, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = toastState.f11894a;
            }
            if ((i2 & 2) != 0) {
                str = toastState.b;
            }
            return toastState.a(i, str);
        }

        private final String b() {
            return this.b;
        }

        @NotNull
        public final ToastState a(int i, @NotNull String str) {
            Intrinsics.f(str, "msgStr");
            return new ToastState(i, str);
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof ToastState) {
                    ToastState toastState = (ToastState) obj;
                    if (!(this.f11894a == toastState.f11894a) || !Intrinsics.a((Object) this.b, (Object) toastState.b)) {
                        return false;
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            int i = this.f11894a * 31;
            String str = this.b;
            return i + (str != null ? str.hashCode() : 0);
        }

        @NotNull
        public String toString() {
            return "ToastState(msgRes=" + this.f11894a + ", msgStr=" + this.b + Operators.BRACKET_END_STR;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ToastState(int i, @NotNull String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.f(str, "msgStr");
            this.f11894a = i;
            this.b = str;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ ToastState(int i, String str, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? -1 : i, (i2 & 2) != 0 ? "" : str);
        }

        @NotNull
        public final String a(@NotNull Context context) {
            Intrinsics.f(context, "context");
            if (this.f11894a == -1) {
                return this.b;
            }
            String string = context.getString(this.f11894a);
            Intrinsics.b(string, "context.getString(msgRes)");
            return string;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fHÖ\u0003J\t\u0010\r\u001a\u00020\u000eHÖ\u0001J\t\u0010\u000f\u001a\u00020\u0010HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0011"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$ErrorState;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "error", "", "(Ljava/lang/Throwable;)V", "getError", "()Ljava/lang/Throwable;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class ErrorState extends FacePrivacyState {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        private final Throwable f11891a;

        @NotNull
        public static /* synthetic */ ErrorState a(ErrorState errorState, Throwable th, int i, Object obj) {
            if ((i & 1) != 0) {
                th = errorState.f11891a;
            }
            return errorState.a(th);
        }

        @NotNull
        public final ErrorState a(@NotNull Throwable th) {
            Intrinsics.f(th, "error");
            return new ErrorState(th);
        }

        @NotNull
        public final Throwable b() {
            return this.f11891a;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof ErrorState) && Intrinsics.a((Object) this.f11891a, (Object) ((ErrorState) obj).f11891a);
            }
            return true;
        }

        public int hashCode() {
            Throwable th = this.f11891a;
            if (th != null) {
                return th.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return "ErrorState(error=" + this.f11891a + Operators.BRACKET_END_STR;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ErrorState(@NotNull Throwable th) {
            super((DefaultConstructorMarker) null);
            Intrinsics.f(th, "error");
            this.f11891a = th;
        }

        @NotNull
        public final Throwable a() {
            return this.f11891a;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rHÖ\u0003J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0012"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$FaceStatusDataState;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "data", "", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyApi$FaceStatus;", "(Ljava/util/List;)V", "getData", "()Ljava/util/List;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class FaceStatusDataState extends FacePrivacyState {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        private final List<FacePrivacyApi.FaceStatus> f11892a;

        @NotNull
        public static /* synthetic */ FaceStatusDataState a(FaceStatusDataState faceStatusDataState, List<FacePrivacyApi.FaceStatus> list, int i, Object obj) {
            if ((i & 1) != 0) {
                list = faceStatusDataState.f11892a;
            }
            return faceStatusDataState.a(list);
        }

        @NotNull
        public final FaceStatusDataState a(@NotNull List<? extends FacePrivacyApi.FaceStatus> list) {
            Intrinsics.f(list, "data");
            return new FaceStatusDataState(list);
        }

        @NotNull
        public final List<FacePrivacyApi.FaceStatus> b() {
            return this.f11892a;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                return (obj instanceof FaceStatusDataState) && Intrinsics.a((Object) this.f11892a, (Object) ((FaceStatusDataState) obj).f11892a);
            }
            return true;
        }

        public int hashCode() {
            List<FacePrivacyApi.FaceStatus> list = this.f11892a;
            if (list != null) {
                return list.hashCode();
            }
            return 0;
        }

        @NotNull
        public String toString() {
            return "FaceStatusDataState(data=" + this.f11892a + Operators.BRACKET_END_STR;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public FaceStatusDataState(@NotNull List<? extends FacePrivacyApi.FaceStatus> list) {
            super((DefaultConstructorMarker) null);
            Intrinsics.f(list, "data");
            this.f11892a = list;
        }

        @NotNull
        public final List<FacePrivacyApi.FaceStatus> a() {
            return this.f11892a;
        }
    }
}
