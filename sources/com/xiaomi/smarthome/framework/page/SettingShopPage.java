package com.xiaomi.smarthome.framework.page;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.shop.analytics.MIOTStat;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;

public class SettingShopPage extends BaseActivity {
    @BindView(2131429435)
    View mFrientCode;
    @BindView(2131430969)
    ImageView mModuleA3ReturnTransparentBtn;
    @BindView(2131431109)
    View mMyOrder;
    @BindView(2131431116)
    View mMyShopingCart;
    @BindView(2131432424)
    View mShopAddress;
    @BindView(2131432429)
    View mShopMyFavorite;
    @BindView(2131430975)
    TextView mTitleBar;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.my_shop_page);
        ButterKnife.bind((Activity) this);
        this.mTitleBar.setText(R.string.setting_my_shop);
        this.mMyOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MIOTStat.Log(MIOTStat.TOUCH, "order");
                UrlDispatchManger.a().c("https://home.mi.com/shop/orderlist");
            }
        });
        this.mModuleA3ReturnTransparentBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                SettingShopPage.this.finish();
            }
        });
        this.mMyShopingCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UrlDispatchManger.a().c("https://home.mi.com/shop/cart");
            }
        });
        this.mShopMyFavorite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MIOTStat.Log(MIOTStat.TOUCH, "order");
                UrlDispatchManger.a().c("https://home.mi.com/shop/favor");
            }
        });
        this.mShopAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MIOTStat.Log(MIOTStat.TOUCH, "address");
                UrlDispatchManger.a().c("https://home.mi.com/shop/address");
            }
        });
        this.mFrientCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                UrlDispatchManger.a().c("https://m.mi.com/v2.html#/fcode");
            }
        });
    }
}
