package com.xiaomi.smarthome.mibrain.activity;

import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.shop.utils.LogUtil;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 13})
final class MiBrainCardStyleStatelessActivity$setupSpeakView$1 implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ MiBrainCardStyleStatelessActivity f10618a;
    final /* synthetic */ ImageView b;

    MiBrainCardStyleStatelessActivity$setupSpeakView$1(MiBrainCardStyleStatelessActivity miBrainCardStyleStatelessActivity, ImageView imageView) {
        this.f10618a = miBrainCardStyleStatelessActivity;
        this.b = imageView;
    }

    public final void onClick(View view) {
        String str;
        String str2;
        StatClick statClick = STAT.d;
        Device access$getMDevice$p = this.f10618a.c;
        if (access$getMDevice$p == null) {
            Intrinsics.a();
        }
        String str3 = access$getMDevice$p.model;
        MiBrainCardStyleStatelessActivity miBrainCardStyleStatelessActivity = this.f10618a;
        Device access$getMDevice$p2 = this.f10618a.c;
        if (access$getMDevice$p2 == null) {
            Intrinsics.a();
        }
        String str4 = access$getMDevice$p2.model;
        Intrinsics.b(str4, "mDevice!!.model");
        statClick.t(str3, miBrainCardStyleStatelessActivity.a(str4));
        Object[] objArr = new Object[2];
        StringBuilder sb = new StringBuilder();
        sb.append(MiBrainCardStyleStatelessActivity.access$getMQueryPrefix$p(this.f10618a));
        ArrayList access$getMTips$p = this.f10618a.f;
        String str5 = null;
        if (access$getMTips$p == null || (str2 = (String) access$getMTips$p.get(0)) == null) {
            str = null;
        } else if (str2 != null) {
            str = StringsKt.b(str2).toString();
        } else {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
        }
        sb.append(str);
        objArr[0] = sb.toString();
        objArr[1] = 1;
        JSONArray jSONArray = new JSONArray(CollectionsKt.b((T[]) objArr));
        LogUtilGrey.a(MiBrainCardStyleStatelessActivity.TAG, "rpc: method：" + MiBrainCardStyleStatelessActivity.access$getMMethod$p(this.f10618a) + " ; param: " + jSONArray);
        XmPluginHostApi instance = PluginHostApi.instance();
        Device access$getMCtrlDevice$p = this.f10618a.b;
        if (access$getMCtrlDevice$p != null) {
            str5 = access$getMCtrlDevice$p.did;
        }
        instance.callMethodFromCloud(str5, MiBrainCardStyleStatelessActivity.access$getMMethod$p(this.f10618a), jSONArray, new Callback<String>() {
            /* renamed from: a */
            public void onSuccess(@Nullable String str) {
                LogUtil.a("RpcResult", "success: " + str);
                LogUtilGrey.a(MiBrainCardStyleStatelessActivity.TAG, "success: " + str);
            }

            public void onFailure(int i, @Nullable String str) {
                LogUtil.a("RpcResult", "error: " + str);
                LogUtilGrey.a(MiBrainCardStyleStatelessActivity.TAG, "error: " + str);
            }
        }, AnonymousClass2.f10619a);
        ImageView imageView = this.b;
        Intrinsics.b(imageView, "speakDecor");
        imageView.setEnabled(false);
        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setSaturation(0.0f);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(colorMatrix);
        ImageView imageView2 = this.b;
        Intrinsics.b(imageView2, "speakDecor");
        Drawable background = imageView2.getBackground();
        if (background != null) {
            background.setColorFilter(colorMatrixColorFilter);
        }
        this.b.postDelayed(new Runnable(this) {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ MiBrainCardStyleStatelessActivity$setupSpeakView$1 f10620a;

            {
                this.f10620a = r1;
            }

            public final void run() {
                ImageView imageView = this.f10620a.b;
                Intrinsics.b(imageView, "speakDecor");
                Drawable background = imageView.getBackground();
                if (background != null) {
                    background.setColorFilter((ColorFilter) null);
                }
                this.f10620a.b.setBackgroundResource(R.drawable.audio_record_btn_bg);
                ImageView imageView2 = this.f10620a.b;
                Intrinsics.b(imageView2, "speakDecor");
                imageView2.setEnabled(true);
            }
        }, 3000);
    }
}
