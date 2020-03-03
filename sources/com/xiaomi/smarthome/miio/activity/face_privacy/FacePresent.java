package com.xiaomi.smarthome.miio.activity.face_privacy;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyEvent;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyState;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00162\u00020\u0001:\u0002\u0016\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\b\u0010\u0015\u001a\u00020\u0011H\u0014R\u001c\u0010\u0003\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0006*\u0004\u0018\u00010\n0\nX\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\r0\r0\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePresent;", "Landroid/arch/lifecycle/ViewModel;", "()V", "mDataStateHolder", "Lio/reactivex/subjects/BehaviorSubject;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState$FaceStatusDataState;", "kotlin.jvm.PlatformType", "mDisposables", "Lio/reactivex/disposables/CompositeDisposable;", "mFacePrivacyApi", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyApi;", "mStateDispatcher", "Lio/reactivex/subjects/PublishSubject;", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "renderView", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePresent$RenderView;", "bindView", "", "dispatchEvent", "event", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyEvent;", "onCleared", "Companion", "RenderView", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class FacePresent extends ViewModel {

    /* renamed from: a  reason: collision with root package name */
    public static final Companion f11866a = new Companion((DefaultConstructorMarker) null);
    private static final String g = "FacePresent";
    private RenderView b;
    private final FacePrivacyApi c = FacePrivacyApi.a();
    private CompositeDisposable d = new CompositeDisposable();
    /* access modifiers changed from: private */
    public final PublishSubject<FacePrivacyState> e;
    /* access modifiers changed from: private */
    public final BehaviorSubject<FacePrivacyState.FaceStatusDataState> f;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePresent$RenderView;", "", "render", "", "state", "Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePrivacyState;", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public interface RenderView {
        void render(@NotNull FacePrivacyState facePrivacyState);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lcom/xiaomi/smarthome/miio/activity/face_privacy/FacePresent$Companion;", "", "()V", "TAG", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }

    public FacePresent() {
        PublishSubject<FacePrivacyState> create = PublishSubject.create();
        Intrinsics.b(create, "PublishSubject.create<FacePrivacyState>()");
        this.e = create;
        BehaviorSubject<FacePrivacyState.FaceStatusDataState> create2 = BehaviorSubject.create();
        Intrinsics.b(create2, "BehaviorSubject.create<F…te.FaceStatusDataState>()");
        this.f = create2;
    }

    public final void a(@NotNull RenderView renderView) {
        Intrinsics.f(renderView, "renderView");
        this.b = renderView;
        this.d.add(this.e.observeOn(AndroidSchedulers.mainThread()).subscribe(new FacePresent$bindView$1(renderView)));
    }

    public final void a(@NotNull FacePrivacyEvent facePrivacyEvent) {
        Intrinsics.f(facePrivacyEvent, "event");
        Log.d(g, "dispatchEvent: " + facePrivacyEvent);
        if (facePrivacyEvent instanceof FacePrivacyEvent.LoadFaceStatusEvent) {
            this.e.onNext(FacePrivacyState.LoadingState.f11893a);
            FacePrivacyApi facePrivacyApi = this.c;
            Intrinsics.b(facePrivacyApi, "mFacePrivacyApi");
            this.d.add(facePrivacyApi.b().map(FacePresent$dispatchEvent$1.f11868a).doOnNext(new FacePresent$dispatchEvent$2(this)).observeOn(AndroidSchedulers.mainThread()).subscribe(new FacePresent$dispatchEvent$3(this), new FacePresent$dispatchEvent$4(this)));
        } else if (facePrivacyEvent instanceof FacePrivacyEvent.ToggleFaceSwitchEvent) {
            this.e.onNext(FacePrivacyState.LoadingState.f11893a);
            FacePrivacyEvent.ToggleFaceSwitchEvent toggleFaceSwitchEvent = (FacePrivacyEvent.ToggleFaceSwitchEvent) facePrivacyEvent;
            String c2 = toggleFaceSwitchEvent.c();
            boolean d2 = toggleFaceSwitchEvent.d();
            this.d.add(this.c.a(c2, d2).flatMap(new FacePresent$dispatchEvent$6(this, c2, d2)).observeOn(AndroidSchedulers.mainThread()).subscribe(new FacePresent$dispatchEvent$7(this), new FacePresent$dispatchEvent$8(this)));
        }
    }

    /* access modifiers changed from: protected */
    public void onCleared() {
        super.onCleared();
        this.d.clear();
    }
}
