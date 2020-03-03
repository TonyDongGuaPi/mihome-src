package com.xiaomi.smarthome.mibrain.roomsetting;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.mibigkoo.convenientbanner.ConvenientBanner;
import com.mibigkoo.convenientbanner.holder.Holder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import java.util.Arrays;
import java.util.List;

public class XiaoAiTutorialActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private List<Banner> f10697a = Arrays.asList(new Banner[]{new Banner(0, 0, R.string.xiaoai_setting_tutorial_1, R.drawable.xiaoai_turital_illustrator_1), new Banner(1, 0, R.string.xiaoai_setting_tutorial_2, R.drawable.xiaoai_turital_illustrator_2)});
    @BindView(2131427794)
    ConvenientBanner banner;
    @BindView(2131428386)
    View close;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_xiao_ai_tutorial);
        ButterKnife.bind((Activity) this);
        a();
    }

    private void a() {
        this.close.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                XiaoAiTutorialActivity.this.a(view);
            }
        });
        this.banner.setPages($$Lambda$XiaoAiTutorialActivity$dlfEigRTdgDOxtLWjn5CkB1e1KY.INSTANCE, this.f10697a).setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL).setPageIndicator(new int[]{R.drawable.xiaoai_turital_dot_indicator_unselected, R.drawable.xiaoai_tutital_dot_indicator_selected});
        this.banner.setCanLoop(false);
        this.banner.setcurrentitem(0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ Object b() {
        return new BitmapHolder();
    }

    static class Banner {

        /* renamed from: a  reason: collision with root package name */
        int f10698a;
        @StringRes
        int b;
        @StringRes
        int c;
        @DrawableRes
        int d;

        public Banner(int i, int i2, int i3, int i4) {
            this.f10698a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }
    }

    static class BitmapHolder implements Holder<Banner> {

        /* renamed from: a  reason: collision with root package name */
        View f10699a;

        BitmapHolder() {
        }

        public View a(Context context, ViewGroup viewGroup) {
            this.f10699a = LayoutInflater.from(context).inflate(R.layout.xiaoai_banner_item, viewGroup, false);
            return this.f10699a;
        }

        public void a(Context context, int i, Banner banner) {
            ViewGroup.LayoutParams layoutParams;
            TextView textView = (TextView) this.f10699a.findViewById(R.id.desc);
            ImageView imageView = (ImageView) this.f10699a.findViewById(R.id.img);
            if (i == 0) {
                ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
                if (layoutParams2 != null) {
                    layoutParams2.width = DisplayUtils.a(context, 288.0f);
                    layoutParams2.height = DisplayUtils.a(context, 230.0f);
                    imageView.setLayoutParams(layoutParams2);
                }
            } else if (i == 1 && (layoutParams = imageView.getLayoutParams()) != null) {
                layoutParams.width = DisplayUtils.a(context, 248.0f);
                layoutParams.height = DisplayUtils.a(context, 236.0f);
                imageView.setLayoutParams(layoutParams);
            }
            textView.setText(banner.c);
            imageView.setImageResource(banner.d);
        }
    }
}
