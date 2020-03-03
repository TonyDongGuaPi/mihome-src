package com.xiaomi.shopviews.widget.homeluckydraw;

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
import com.xiaomi.shopviews.model.LuckyDrawModel;
import com.xiaomi.shopviews.widget.R;

public class LuckyResultDialogTypeTwo extends Dialog {

    /* renamed from: a  reason: collision with root package name */
    protected LinearLayout f13278a;
    protected ImageView b;
    protected RelativeLayout c;
    protected TextView d;
    protected TextView e;
    private ImageView f;

    public LuckyResultDialogTypeTwo(@NonNull Context context) {
        super(context, R.style.Widget_Dialog);
        setContentView(R.layout.lucky_draw_result_dialog_type_two);
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity = 17;
        onWindowAttributesChanged(attributes);
    }

    public void dismiss() {
        super.dismiss();
        this.c.setVisibility(8);
    }

    public void setContentView(@LayoutRes int i) {
        super.setContentView(i);
        this.f13278a = (LinearLayout) findViewById(R.id.container);
        this.e = (TextView) findViewById(R.id.text_title_lucky_title);
        this.d = (TextView) findViewById(R.id.text_title_lucky_detail);
        this.b = (ImageView) findViewById(R.id.icon_close);
        this.f = (ImageView) findViewById(R.id.igv_lucky_product);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root_view);
        this.c = relativeLayout;
        relativeLayout.setVisibility(8);
        this.b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                LuckyResultDialogTypeTwo.this.dismiss();
            }
        });
    }

    public void a(LuckyDrawModel luckyDrawModel) {
        if (luckyDrawModel != null) {
            this.c.setVisibility(0);
            if (luckyDrawModel.e) {
                this.e.setVisibility(0);
                this.d.setVisibility(0);
                if (!TextUtils.isEmpty(luckyDrawModel.h)) {
                    this.e.setText(luckyDrawModel.h);
                } else {
                    this.e.setText("手气不错,恭喜你获得");
                }
                this.d.setText(luckyDrawModel.g);
                if (!luckyDrawModel.c) {
                    this.f.setImageResource(R.drawable.icon_prize_yes);
                    return;
                }
                return;
            }
            this.f.setImageResource(R.drawable.icon_prize_no);
            this.e.setVisibility(0);
            this.d.setVisibility(4);
            this.e.setText("这次没有领到好礼噢~");
        }
    }
}
