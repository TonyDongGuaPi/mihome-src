package com.xiaomi.smarthome.miio.activity.face_privacy;

import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 13})
final class FacePrivacyManagerActivity$dismissLoadingDialog$1 implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ FacePrivacyManagerActivity f11889a;

    FacePrivacyManagerActivity$dismissLoadingDialog$1(FacePrivacyManagerActivity facePrivacyManagerActivity) {
        this.f11889a = facePrivacyManagerActivity;
    }

    public final void run() {
        XQProgressDialog access$getMLoadingDialog$p = this.f11889a.d;
        if (access$getMLoadingDialog$p != null) {
            access$getMLoadingDialog$p.dismiss();
        }
    }
}
