package com.xiaomi.smarthome.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.List;

public class DeviceBigHeaderView extends HorizontalScrollView {

    /* renamed from: a  reason: collision with root package name */
    private LinearLayout f22774a;
    private List<DeviceBigHeaderModel> b;

    public DeviceBigHeaderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
    }

    private void a() {
        this.f22774a = new LinearLayout(getContext());
        this.f22774a.setOrientation(0);
        this.f22774a.setGravity(17);
        addView(this.f22774a, new FrameLayout.LayoutParams(-1, -1));
        setFillViewport(false);
        setHorizontalScrollBarEnabled(false);
    }

    public void setModel(List<DeviceBigHeaderModel> list) {
        this.b = list;
        b();
    }

    private void b() {
        if (this.b != null && !this.b.isEmpty()) {
            this.f22774a.removeAllViews();
            for (int i = 0; i < this.b.size(); i++) {
                DeviceBigHeaderModel deviceBigHeaderModel = this.b.get(i);
                if (deviceBigHeaderModel != null) {
                    View inflate = LayoutInflater.from(getContext()).inflate(R.layout.device_info_list_item, this.f22774a, false);
                    DeviceFactory.b(deviceBigHeaderModel.f22775a, (SimpleDraweeView) inflate.findViewById(R.id.device_img));
                    ((TextView) inflate.findViewById(R.id.device_name_tv)).setText(deviceBigHeaderModel.b());
                    this.f22774a.addView(inflate);
                }
            }
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.f22774a.getLayoutParams();
            if (this.f22774a.getChildCount() <= 2) {
                layoutParams.gravity = 17;
            } else {
                layoutParams.gravity = 19;
                if (this.f22774a.getChildCount() >= 3) {
                    layoutParams.leftMargin = DisplayUtils.a(45.0f);
                    this.f22774a.setPadding(this.f22774a.getPaddingLeft(), this.f22774a.getPaddingTop(), DisplayUtils.a(60.0f), this.f22774a.getPaddingBottom());
                }
            }
            requestLayout();
        }
    }

    public static class DeviceBigHeaderModel {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public final String f22775a;
        private final String b;

        public DeviceBigHeaderModel(String str, String str2) {
            this.f22775a = str;
            this.b = str2;
        }

        public String a() {
            return this.f22775a;
        }

        public String b() {
            return this.b;
        }
    }
}
