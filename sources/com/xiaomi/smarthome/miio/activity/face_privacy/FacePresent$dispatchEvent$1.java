package com.xiaomi.smarthome.miio.activity.face_privacy;

import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyApi;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyState;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012(\u0010\u0002\u001a$\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$FaceStatusDataState;", "faceStatuses", "", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyApi$FaceStatus;", "kotlin.jvm.PlatformType", "", "apply"}, k = 3, mv = {1, 1, 13})
final class FacePresent$dispatchEvent$1<T, R> implements Function<T, R> {

    /* renamed from: a  reason: collision with root package name */
    public static final FacePresent$dispatchEvent$1 f11868a = new FacePresent$dispatchEvent$1();

    FacePresent$dispatchEvent$1() {
    }

    @NotNull
    /* renamed from: a */
    public final FacePrivacyState.FaceStatusDataState apply(@NotNull List<FacePrivacyApi.FaceStatus> list) {
        Intrinsics.f(list, "faceStatuses");
        return new FacePrivacyState.FaceStatusDataState(list);
    }
}
