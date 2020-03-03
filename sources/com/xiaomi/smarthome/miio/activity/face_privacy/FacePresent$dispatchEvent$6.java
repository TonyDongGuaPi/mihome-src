package com.xiaomi.smarthome.miio.activity.face_privacy;

import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyApi;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyState;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a*\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0014\u0012\u000e\b\u0001\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Observable;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "kotlin.jvm.PlatformType", "isSuccess", "", "apply", "(Ljava/lang/Boolean;)Lio/reactivex/Observable;"}, k = 3, mv = {1, 1, 13})
final class FacePresent$dispatchEvent$6<T, R> implements Function<T, ObservableSource<? extends R>> {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FacePresent f11872a;
    final /* synthetic */ String b;
    final /* synthetic */ boolean c;

    FacePresent$dispatchEvent$6(FacePresent facePresent, String str, boolean z) {
        this.f11872a = facePresent;
        this.b = str;
        this.c = z;
    }

    /* renamed from: a */
    public final Observable<? extends FacePrivacyState> apply(@NotNull Boolean bool) {
        Intrinsics.f(bool, "isSuccess");
        Object value = this.f11872a.f.getValue();
        if (value == null) {
            Intrinsics.a();
        }
        Intrinsics.b(value, "mDataStateHolder.value!!");
        FacePrivacyState.FaceStatusDataState faceStatusDataState = (FacePrivacyState.FaceStatusDataState) value;
        if (!bool.booleanValue()) {
            return Observable.just(new FacePrivacyState.ToastState(R.string.failed, (String) null, 2, (DefaultConstructorMarker) null), faceStatusDataState);
        }
        Iterable<FacePrivacyApi.FaceStatus> a2 = faceStatusDataState.a();
        Collection arrayList = new ArrayList(CollectionsKt.a(a2, 10));
        for (FacePrivacyApi.FaceStatus faceStatus : a2) {
            if (Intrinsics.a((Object) faceStatus.f11882a.did, (Object) this.b)) {
                faceStatus = new FacePrivacyApi.FaceStatus(faceStatus.f11882a, Boolean.valueOf(this.c));
            }
            arrayList.add(faceStatus);
        }
        return Observable.just(new FacePrivacyState.FaceStatusDataState((List) arrayList)).doOnNext(new Consumer<FacePrivacyState.FaceStatusDataState>(this) {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ FacePresent$dispatchEvent$6 f11873a;

            {
                this.f11873a = r1;
            }

            /* renamed from: a */
            public final void accept(FacePrivacyState.FaceStatusDataState faceStatusDataState) {
                this.f11873a.f11872a.f.onNext(faceStatusDataState);
            }
        });
    }
}
