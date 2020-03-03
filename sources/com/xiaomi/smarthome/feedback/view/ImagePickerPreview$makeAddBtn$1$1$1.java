package com.xiaomi.smarthome.feedback.view;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import com.xiaomi.youpin.share.ShareObject;
import com.yanzhenjie.permission.Action;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\u0010\u0000\u001a\u00020\u00012*\u0010\u0002\u001a&\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004 \u0005*\u0012\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u0004\u0018\u00010\u00060\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "", "", "kotlin.jvm.PlatformType", "", "onAction"}, k = 3, mv = {1, 1, 13})
final class ImagePickerPreview$makeAddBtn$1$1$1 implements Action {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ Activity f15985a;

    ImagePickerPreview$makeAddBtn$1$1$1(Activity activity) {
        this.f15985a = activity;
    }

    public final void onAction(List<String> list) {
        Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, ShareObject.d);
        this.f15985a.startActivityForResult(intent, 703);
    }
}
