package com.xiaomi.smarthome.feedback.view;

import android.view.View;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
final class ImagePickerPreview$makePreviewView$1 implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ ImagePickerPreview f15986a;
    final /* synthetic */ int b;

    ImagePickerPreview$makePreviewView$1(ImagePickerPreview imagePickerPreview, int i) {
        this.f15986a = imagePickerPreview;
        this.b = i;
    }

    public final void onClick(View view) {
        this.f15986a.remove(this.b);
    }
}
