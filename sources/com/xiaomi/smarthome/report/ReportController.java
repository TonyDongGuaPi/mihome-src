package com.xiaomi.smarthome.report;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.config.SHBusinessManager;

public class ReportController {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21177a = "SmartHomeMainActivity";
    private static final String b = "report_show_shma";
    /* access modifiers changed from: private */
    public View c;

    public void a(final View view, Context context, final SHBusinessManager.BusinessContent businessContent) {
        SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.iv_report);
        simpleDraweeView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SHBusinessManager.a().b(businessContent);
                ReportController.this.a(view);
            }
        });
        a(simpleDraweeView, businessContent.b, 0);
        view.findViewById(R.id.iv_close_report).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ReportController.this.a(view);
            }
        });
        if (this.c == null) {
            this.c = ((ViewStub) view.findViewById(R.id.layout_report_vs)).inflate();
        }
        this.c.setVisibility(0);
    }

    /* access modifiers changed from: private */
    public void a(final View view) {
        view.post(new Runnable() {
            public void run() {
                if (ReportController.this.c == null) {
                    View unused = ReportController.this.c = ((ViewStub) view.findViewById(R.id.layout_report_vs)).inflate();
                }
                ReportController.this.c.setVisibility(4);
                ((ImageView) view.findViewById(R.id.iv_report)).setImageDrawable((Drawable) null);
            }
        });
    }

    private void a(SimpleDraweeView simpleDraweeView, final String str, int i) {
        if (i == 0) {
            i = R.drawable.device_list_phone_no;
        }
        if (simpleDraweeView.getHierarchy() == null) {
            simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(i)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
        }
        if (!TextUtils.isEmpty(str) && str.startsWith("http")) {
            simpleDraweeView.setImageURI(Uri.parse(str));
            DraweeController controller = simpleDraweeView.getController();
            if (controller != null && (controller instanceof AbstractDraweeController)) {
                ((AbstractDraweeController) controller).addControllerListener(new BaseControllerListener() {
                    public void onFailure(String str, Throwable th) {
                        Log.e("Failure", str + " decode failed");
                        Fresco.getImagePipeline().evictFromMemoryCache(Uri.parse(str));
                        Fresco.getImagePipeline().evictFromDiskCache(Uri.parse(str));
                    }
                });
            }
        }
    }
}
