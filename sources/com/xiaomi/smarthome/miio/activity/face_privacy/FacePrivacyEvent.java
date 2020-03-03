package com.xiaomi.smarthome.miio.activity.face_privacy;

import com.taobao.weex.el.parse.Operators;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0002¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent;", "", "()V", "LoadFaceStatusEvent", "ToggleFaceSwitchEvent", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent$LoadFaceStatusEvent;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent$ToggleFaceSwitchEvent;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public abstract class FacePrivacyEvent {

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent$LoadFaceStatusEvent;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent;", "()V", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class LoadFaceStatusEvent extends FacePrivacyEvent {

        /* renamed from: a  reason: collision with root package name */
        public static final LoadFaceStatusEvent f11883a = new LoadFaceStatusEvent();

        private LoadFaceStatusEvent() {
            super((DefaultConstructorMarker) null);
        }
    }

    private FacePrivacyEvent() {
    }

    public /* synthetic */ FacePrivacyEvent(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u00052\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent$ToggleFaceSwitchEvent;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent;", "did", "", "exceptStatus", "", "(Ljava/lang/String;Z)V", "getDid", "()Ljava/lang/String;", "getExceptStatus", "()Z", "component1", "component2", "copy", "equals", "other", "", "hashCode", "", "toString", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class ToggleFaceSwitchEvent extends FacePrivacyEvent {
        @NotNull

        /* renamed from: a  reason: collision with root package name */
        private final String f11884a;
        private final boolean b;

        @NotNull
        public static /* synthetic */ ToggleFaceSwitchEvent a(ToggleFaceSwitchEvent toggleFaceSwitchEvent, String str, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                str = toggleFaceSwitchEvent.f11884a;
            }
            if ((i & 2) != 0) {
                z = toggleFaceSwitchEvent.b;
            }
            return toggleFaceSwitchEvent.a(str, z);
        }

        @NotNull
        public final ToggleFaceSwitchEvent a(@NotNull String str, boolean z) {
            Intrinsics.f(str, "did");
            return new ToggleFaceSwitchEvent(str, z);
        }

        @NotNull
        public final String c() {
            return this.f11884a;
        }

        public final boolean d() {
            return this.b;
        }

        public boolean equals(@Nullable Object obj) {
            if (this != obj) {
                if (obj instanceof ToggleFaceSwitchEvent) {
                    ToggleFaceSwitchEvent toggleFaceSwitchEvent = (ToggleFaceSwitchEvent) obj;
                    if (Intrinsics.a((Object) this.f11884a, (Object) toggleFaceSwitchEvent.f11884a)) {
                        if (this.b == toggleFaceSwitchEvent.b) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }

        public int hashCode() {
            String str = this.f11884a;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            boolean z = this.b;
            if (z) {
                z = true;
            }
            return hashCode + (z ? 1 : 0);
        }

        @NotNull
        public String toString() {
            return "ToggleFaceSwitchEvent(did=" + this.f11884a + ", exceptStatus=" + this.b + Operators.BRACKET_END_STR;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ToggleFaceSwitchEvent(@NotNull String str, boolean z) {
            super((DefaultConstructorMarker) null);
            Intrinsics.f(str, "did");
            this.f11884a = str;
            this.b = z;
        }

        @NotNull
        public final String a() {
            return this.f11884a;
        }

        public final boolean b() {
            return this.b;
        }
    }
}
