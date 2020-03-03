package com.xiaomi.smarthome.miio.activity.face_privacy;

import android.view.View;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyEvent;
import com.xiaomi.smarthome.miio.activity.face_privacy.FacePrivacyManagerActivity;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
final class FacePrivacyManagerActivity$InnerAdapter$VH$bind$1 implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FacePrivacyManagerActivity.InnerAdapter.VH f11888a;
    final /* synthetic */ Device b;
    final /* synthetic */ Boolean c;
    final /* synthetic */ SwitchButton d;

    FacePrivacyManagerActivity$InnerAdapter$VH$bind$1(FacePrivacyManagerActivity.InnerAdapter.VH vh, Device device, Boolean bool, SwitchButton switchButton) {
        this.f11888a = vh;
        this.b = device;
        this.c = bool;
        this.d = switchButton;
    }

    public final void onClick(View view) {
        FacePresent access$getMFacePresent$p = FacePrivacyManagerActivity.access$getMFacePresent$p(FacePrivacyManagerActivity.this);
        String str = this.b.did;
        Intrinsics.b(str, "device.did");
        access$getMFacePresent$p.a((FacePrivacyEvent) new FacePrivacyEvent.ToggleFaceSwitchEvent(str, !this.c.booleanValue()));
        this.d.toggle();
    }
}
