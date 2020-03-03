package com.xiaomi.shopviews.widget.homeluckylottery;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.xiaomi.shopviews.model.HomeAction;
import com.xiaomi.shopviews.model.LuckyDrawModel;
import com.xiaomi.shopviews.widget.R;

public class LuckyResultDialog extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    protected TextView f13284a;
    protected LinearLayout b;
    protected ImageView c;
    protected RelativeLayout d;
    protected TextView e;
    protected TextView f;
    protected TextView g;

    public LuckyResultDialog(@NonNull Context context) {
        super(context, R.style.Widget_Dialog);
        setContentView(R.layout.lucky_draw_result_dialog);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        onWindowAttributesChanged(attributes);
    }

    public void dismiss() {
        super.dismiss();
        this.d.setVisibility(8);
    }

    public void setContentView(@LayoutRes int i) {
        super.setContentView(i);
        this.b = (LinearLayout) findViewById(R.id.container);
        this.f = (TextView) findViewById(R.id.text_title_lucky_no);
        this.g = (TextView) findViewById(R.id.text_title_lucky_yes);
        this.e = (TextView) findViewById(R.id.text_title_lucky_goods);
        this.c = (ImageView) findViewById(R.id.icon_close);
        this.f13284a = (TextView) findViewById(R.id.btn_action);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root_view);
        this.d = relativeLayout;
        relativeLayout.setVisibility(8);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LuckyResultDialog.this.dismiss();
            }
        });
    }

    public void a(final LuckyDrawModel luckyDrawModel) {
        if (luckyDrawModel != null) {
            this.d.setVisibility(0);
            if (luckyDrawModel.e) {
                this.f.setVisibility(8);
                this.g.setVisibility(0);
                this.e.setVisibility(0);
                this.e.setText(luckyDrawModel.g);
                if (TextUtils.isEmpty(luckyDrawModel.b)) {
                    this.f13284a.setText(R.string.str_lucky_draw_dialog_btn_yes);
                } else {
                    this.f13284a.setText(luckyDrawModel.b);
                }
                this.f13284a.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        HomeAction homeAction = luckyDrawModel.d;
                        LuckyResultDialog.this.dismiss();
                    }
                });
                return;
            }
            this.f.setVisibility(0);
            this.g.setVisibility(8);
            this.e.setVisibility(8);
            if (luckyDrawModel.i > 0) {
                if (TextUtils.isEmpty(luckyDrawModel.f13165a)) {
                    this.f13284a.setText(R.string.str_lucky_draw_dialog_btn_no);
                } else {
                    this.f13284a.setText(luckyDrawModel.f13165a);
                }
            } else if (TextUtils.isEmpty(luckyDrawModel.b)) {
                this.f13284a.setText(R.string.str_lucky_draw_dialog_btn_yes);
            } else {
                this.f13284a.setText(luckyDrawModel.b);
            }
            this.f13284a.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    HomeAction homeAction = luckyDrawModel.d;
                    LuckyResultDialog.this.dismiss();
                }
            });
        }
    }
}
