package com.xiaomi.smarthome.framework.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;

public class SettingShopPage_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SettingShopPage f16921a;

    @UiThread
    public SettingShopPage_ViewBinding(SettingShopPage settingShopPage) {
        this(settingShopPage, settingShopPage.getWindow().getDecorView());
    }

    @UiThread
    public SettingShopPage_ViewBinding(SettingShopPage settingShopPage, View view) {
        this.f16921a = settingShopPage;
        settingShopPage.mMyOrder = Utils.findRequiredView(view, R.id.my_order, "field 'mMyOrder'");
        settingShopPage.mMyShopingCart = Utils.findRequiredView(view, R.id.my_shoping_cart, "field 'mMyShopingCart'");
        settingShopPage.mShopMyFavorite = Utils.findRequiredView(view, R.id.shop_my_favorite, "field 'mShopMyFavorite'");
        settingShopPage.mShopAddress = Utils.findRequiredView(view, R.id.shop_address, "field 'mShopAddress'");
        settingShopPage.mTitleBar = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_title, "field 'mTitleBar'", TextView.class);
        settingShopPage.mFrientCode = Utils.findRequiredView(view, R.id.frient_code_enter, "field 'mFrientCode'");
        settingShopPage.mModuleA3ReturnTransparentBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_3_return_btn, "field 'mModuleA3ReturnTransparentBtn'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        SettingShopPage settingShopPage = this.f16921a;
        if (settingShopPage != null) {
            this.f16921a = null;
            settingShopPage.mMyOrder = null;
            settingShopPage.mMyShopingCart = null;
            settingShopPage.mShopMyFavorite = null;
            settingShopPage.mShopAddress = null;
            settingShopPage.mTitleBar = null;
            settingShopPage.mFrientCode = null;
            settingShopPage.mModuleA3ReturnTransparentBtn = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
