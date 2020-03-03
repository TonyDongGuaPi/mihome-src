package com.xiaomi.smarthome.miio.activity.face_privacy;

import com.xiaomi.smarthome.miio.activity.face_privacy.FacePresent;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
final class FacePresent$bindView$1<T> implements Consumer<FacePrivacyState> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FacePresent.RenderView f11867a;

    FacePresent$bindView$1(FacePresent.RenderView renderView) {
        this.f11867a = renderView;
    }

    /* renamed from: a */
    public final void accept(FacePrivacyState facePrivacyState) {
        FacePresent.RenderView renderView = this.f11867a;
        Intrinsics.b(facePrivacyState, "it");
        renderView.render(facePrivacyState);
    }
}
