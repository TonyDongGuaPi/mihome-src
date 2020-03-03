package com.mi.global.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import com.mi.global.shop.R;
import com.mi.global.shop.user.FeedbackActivity;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.widget.CustomTextView;
import com.mi.util.MiToast;
import com.xiaomi.mishopsdk.util.Constants;

public class SuccessAcitvity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private CustomTextView f5461a;
    private CustomTextView b;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_success);
        setTitle((CharSequence) getString(R.string.success_payment));
        this.mBackView.setVisibility(0);
        findViewById(R.id.title_bar_cart_view).setVisibility(4);
        this.f5461a = (CustomTextView) findViewById(R.id.payment_success_title_txt);
        this.b = (CustomTextView) findViewById(R.id.payment_success_message_txt);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("payment_type");
        if ("cod_by_img".equals(stringExtra)) {
            this.f5461a.setText(R.string.cod_img_payment_success_title);
            this.b.setText(R.string.cod_img_payment_success_message);
        } else if ("cod_by_sms".equals(stringExtra)) {
            this.f5461a.setText(R.string.cod_sms_payment_success_title);
            this.b.setText(R.string.cod_sms_payment_success_message);
        }
        ((CustomTextView) findViewById(R.id.order_id_txt)).setText(intent.getStringExtra("com.mi.global.shop.extra_buy_confirm_orderid"));
        a();
    }

    private void a() {
        findViewById(R.id.order_details_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SuccessAcitvity.this.finish();
            }
        });
        findViewById(R.id.back_to_home_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SuccessAcitvity.this, MainTabActivity.class);
                intent.setFlags(Constants.CALLIGRAPHY_TAG_PRICE);
                intent.putExtra("switch_home_page", true);
                SuccessAcitvity.this.startActivity(intent);
            }
        });
        findViewById(R.id.could_be_better_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SuccessAcitvity.this.startActivity(new Intent(SuccessAcitvity.this, FeedbackActivity.class));
                SuccessAcitvity.this.finish();
            }
        });
        findViewById(R.id.love_it_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MiShopStatInterface.a("click_enter_market", SuccessAcitvity.class.getSimpleName());
                Intent intent = new Intent("android.intent.action.VIEW");
                intent.setData(Uri.parse("market://details?id=" + SuccessAcitvity.this.getPackageName()));
                if (intent.resolveActivity(SuccessAcitvity.this.getPackageManager()) != null) {
                    SuccessAcitvity.this.startActivity(intent);
                } else {
                    intent.setData(Uri.parse("https://play.google.com/store/apps/details?id=" + SuccessAcitvity.this.getPackageName()));
                    if (intent.resolveActivity(SuccessAcitvity.this.getPackageManager()) != null) {
                        SuccessAcitvity.this.startActivity(intent);
                    } else {
                        MiToast.a((Context) SuccessAcitvity.this, R.string.no_market_and_brower, 0);
                    }
                }
                SuccessAcitvity.this.finish();
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }
}
