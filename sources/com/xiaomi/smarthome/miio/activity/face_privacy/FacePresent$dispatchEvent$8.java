package com.xiaomi.smarthome.miio.activity.face_privacy;

import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyState;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 13})
final class FacePresent$dispatchEvent$8<T> implements Consumer<Throwable> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FacePresent f11875a;

    FacePresent$dispatchEvent$8(FacePresent facePresent) {
        this.f11875a = facePresent;
    }

    /* renamed from: a */
    public final void accept(Throwable th) {
        PublishSubject b = this.f11875a.e;
        Intrinsics.b(th, "it");
        b.onNext(new FacePrivacyState.ErrorState(th));
    }
}
