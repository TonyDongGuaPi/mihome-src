package com.xiaomi.shopviews.widget.homeluckydraw;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.base.utils.ScreenInfo;
import com.xiaomi.shopviews.model.HomeAction;
import com.xiaomi.shopviews.model.HomeSection;
import com.xiaomi.shopviews.model.HomeSectionBody;
import com.xiaomi.shopviews.model.IHomeItemView;
import com.xiaomi.shopviews.model.LuckyDrawModel;
import com.xiaomi.shopviews.widget.R;

public class HomeLuckyDrawView extends LinearLayout implements IHomeItemView {

    /* renamed from: a  reason: collision with root package name */
    private static final String f13275a = "HomeLuckyDrawView";
    private boolean b;
    private String c;
    private ImageView d;
    private TextView e;
    private Button f;
    private LinearLayout g;
    private RelativeLayout h;
    private TextView i;
    private TextView j;
    private TextView k;

    public void draw(HomeSection homeSection) {
    }

    public HomeLuckyDrawView(Context context) {
        super(context);
        a();
    }

    private ColorStateList a(int i2, int i3) {
        return new ColorStateList(new int[][]{new int[]{-16842910}, new int[0]}, new int[]{i3, i2});
    }

    private StateListDrawable a(Drawable drawable, Drawable drawable2) {
        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(new int[]{-16842910}, drawable2);
        stateListDrawable.addState(new int[0], drawable);
        return stateListDrawable;
    }

    private void a() {
        LayoutInflater.from(getContext()).inflate(R.layout.listitem_home_lucky_draw, this);
        this.g = (LinearLayout) findViewById(R.id.container);
        this.h = (RelativeLayout) findViewById(R.id.root_view);
        this.d = (ImageView) findViewById(R.id.bg_image);
        this.j = (TextView) findViewById(R.id.text_title_left);
        this.i = (TextView) findViewById(R.id.text_title_count);
        this.k = (TextView) findViewById(R.id.text_title_right);
        this.f = (Button) findViewById(R.id.btn_lucky);
        this.e = (TextView) findViewById(R.id.btn_jump_coupon);
        this.h.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) * 370.0f) / 1080.0f);
        this.d.getLayoutParams().height = (int) ((((float) ScreenInfo.a().e()) * 370.0f) / 1080.0f);
    }

    private void a(LuckyDrawModel luckyDrawModel) {
        if (this.b) {
            TextView textView = this.i;
            textView.setText(luckyDrawModel.i + "");
            if (luckyDrawModel.i > 0) {
                this.f.setEnabled(true);
                this.f.setText(this.c);
            } else {
                this.f.setEnabled(false);
                this.f.setText(R.string.str_lucky_draw_btn_no_count);
            }
            LuckyResultDialogTypeTwo luckyResultDialogTypeTwo = new LuckyResultDialogTypeTwo(getContext());
            luckyResultDialogTypeTwo.show();
            luckyResultDialogTypeTwo.a(luckyDrawModel);
        }
    }

    public void bind(final HomeSection homeSection, int i2, int i3) {
        TextUtils.isEmpty(homeSection.mBody.mBgColor);
        if (!TextUtils.isEmpty(homeSection.mBody.mNoticeText)) {
            String[] split = homeSection.mBody.mNoticeText.split("%remain_num");
            if (split.length > 0 && split[0] != null) {
                this.j.setText(split[0].trim());
            }
            if (split.length > 1 && split[1] != null) {
                this.k.setText(split[1].trim());
            }
        }
        TextView textView = this.i;
        textView.setText(homeSection.mBody.mRemainNum + "");
        if (homeSection.mBody.mRemainNum > 0) {
            this.f.setEnabled(true);
            this.f.setText(homeSection.mBody.mActionText);
        } else {
            this.f.setEnabled(false);
            this.f.setText(R.string.str_lucky_draw_btn_no_count);
        }
        this.c = homeSection.mBody.mActionText;
        if (!TextUtils.isEmpty(homeSection.mBody.mActionTextColor)) {
            this.f.setTextColor(a(Color.parseColor(homeSection.mBody.mActionTextColor), getResources().getColor(R.color.white)));
        }
        if (!TextUtils.isEmpty(homeSection.mBody.mActionBgColor)) {
            GradientDrawable gradientDrawable = new GradientDrawable();
            gradientDrawable.setColor(Color.parseColor(homeSection.mBody.mActionBgColor));
            gradientDrawable.setCornerRadius(3.0f);
            GradientDrawable gradientDrawable2 = new GradientDrawable();
            gradientDrawable2.setColor(Color.parseColor("#adadad"));
            gradientDrawable2.setCornerRadius(3.0f);
            this.f.setBackgroundDrawable(a((Drawable) gradientDrawable, (Drawable) gradientDrawable2));
        }
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                HomeLuckyDrawView.this.requestLuckDraw(homeSection.mBody);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (HomeLuckyDrawView.this.getContext() instanceof Activity) {
                    HomeAction homeAction = homeSection.mAction;
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = true;
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.b = false;
    }

    public void onFinishTemporaryDetach() {
        super.onFinishTemporaryDetach();
        this.b = true;
    }

    public void onStartTemporaryDetach() {
        super.onStartTemporaryDetach();
        this.b = false;
    }

    public void requestLuckDraw(HomeSectionBody homeSectionBody) {
        if (!homeSectionBody.isRequesting) {
            homeSectionBody.isRequesting = true;
            HomeAction homeAction = homeSectionBody.mAction;
        }
    }
}
