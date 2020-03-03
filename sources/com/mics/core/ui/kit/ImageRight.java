package com.mics.core.ui.kit;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.biubiu.kit.core.AbsKit;
import com.biubiu.kit.core.KitBaseAdapter;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.R;
import com.mics.core.MiCS;
import com.mics.core.ui.data.BaseData;
import com.mics.core.ui.page.ChatAdapter;
import com.mics.core.ui.page.ChatDelegate;
import com.mics.util.DraweeViewUtil;
import com.mics.util.FrescoImageLoader;
import com.taobao.weex.el.parse.Operators;
import java.util.Locale;

public class ImageRight extends AbsKit implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    private SimpleDraweeView f7695a;
    private RelativeLayout b;
    private ImageView c;
    private TextView d;
    private TextView e;
    private FrescoImageLoader.Builder f;
    private int g;
    private Data h;
    private ChatDelegate i;

    public void a(KitBaseAdapter kitBaseAdapter) {
        super.a(kitBaseAdapter);
        if (kitBaseAdapter instanceof ChatAdapter) {
            this.i = ((ChatAdapter) kitBaseAdapter).c();
        }
    }

    public View a(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        View a2 = a(LayoutInflater.from(context), viewGroup, R.layout.mics_kit_right_image);
        a2.setLayoutParams(layoutParams);
        this.f7695a = (SimpleDraweeView) a2.findViewById(R.id.iv_img);
        this.b = (RelativeLayout) a2.findViewById(R.id.rl_upload_progress);
        this.c = (ImageView) a2.findViewById(R.id.iv_upload_progress);
        this.d = (TextView) a2.findViewById(R.id.tv_upload_progress);
        this.e = (TextView) a2.findViewById(R.id.tv_has_read);
        this.f = new FrescoImageLoader.Builder();
        this.f.a(true);
        this.f.a(R.drawable.mics_icon_default_avatar);
        this.f.b(R.drawable.mics_icon_default_avatar);
        this.f.b(ScalingUtils.ScaleType.CENTER_CROP);
        this.f.a(ScalingUtils.ScaleType.CENTER_CROP);
        this.f.a((DraweeView<GenericDraweeHierarchy>) (SimpleDraweeView) a2.findViewById(R.id.iv_avatar));
        return a2;
    }

    public void a(int i2, Object obj) {
        if (obj instanceof Data) {
            Data data = (Data) obj;
            this.h = data;
            this.g = i2;
            DraweeViewUtil.a().a(this.f7695a, this.h.d());
            if (data.g() == 1 || data.g() == 2) {
                RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
                rotateAnimation.setDuration(400);
                rotateAnimation.setRepeatCount(-1);
                rotateAnimation.setRepeatMode(1);
                this.c.startAnimation(rotateAnimation);
                this.b.setVisibility(0);
                this.d.setText(String.format(Locale.getDefault(), "%.1f%s", new Object[]{Float.valueOf(data.f() * 100.0f), Operators.MOD}));
                a(data);
            } else {
                this.b.setVisibility(8);
            }
            this.e.setText(data.a());
            this.e.setTextColor(data.b());
            this.f.a(data.e());
            this.f.a().a();
            this.f7695a.setOnClickListener(this);
        }
    }

    private void a(Data data) {
        if (data.g() == 4) {
            this.e.setText(data.a());
            this.e.setTextColor(data.b());
            this.b.setVisibility(8);
        } else if (data.g() == 1 || data.g() == 2) {
            this.d.setText(String.format(Locale.getDefault(), "%.1f%s", new Object[]{Float.valueOf(data.f() * 100.0f), Operators.MOD}));
            if (data.f() >= 1.0f) {
                this.b.setVisibility(8);
            }
        }
    }

    public void onClick(View view) {
        if (view instanceof SimpleDraweeView) {
            float applyDimension = TypedValue.applyDimension(1, 4.0f, view.getResources().getDisplayMetrics());
            int[] iArr = new int[2];
            view.getLocationInWindow(iArr);
            float f2 = applyDimension * 2.0f;
            int height = (int) (((float) view.getHeight()) - f2);
            this.i.a(this.h.getId(), (int) (((float) iArr[0]) + applyDimension), (int) (((float) iArr[1]) + applyDimension), (int) (((float) view.getWidth()) - f2), height);
        }
    }

    public static class Data extends BaseData {

        /* renamed from: a  reason: collision with root package name */
        private String f7696a;
        private float b;
        private int c;
        private int d;

        /* access modifiers changed from: package-private */
        public String a() {
            if (this.d == 0) {
                return "发送中";
            }
            if (this.d == 1) {
                return "发送成功";
            }
            return this.d == -1 ? "发送失败" : "";
        }

        /* access modifiers changed from: package-private */
        public int b() {
            return Color.parseColor(this.d == 1 ? "#cccccc" : this.d == 0 ? "#dfaf7e" : "#b60909");
        }

        public boolean c() {
            return this.d == 1;
        }

        public void a(int i) {
            this.d = i;
        }

        public String d() {
            return this.f7696a;
        }

        public void a(String str, boolean z) {
            setContent("[图片]");
            if (!z) {
                this.f7696a = str;
                a(1);
            } else if (TextUtils.isEmpty(str) || !str.startsWith("file://")) {
                this.f7696a = "file://" + str;
            } else {
                this.f7696a = str;
            }
        }

        /* access modifiers changed from: package-private */
        public String e() {
            return MiCS.a().p();
        }

        public float f() {
            return this.b;
        }

        public void a(float f) {
            this.b = f % 100.0f;
        }

        public int g() {
            return this.c;
        }

        public void b(int i) {
            this.c = i;
        }
    }
}
