package com.mi.global.shop.activity;

import android.content.Intent;
import android.os.Bundle;
import com.mi.global.shop.R;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.ui.MainTabLazyWebFragment;
import com.mi.global.shop.util.ConnectionHelper;

public class ProductActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private String[] f5416a = {ConnectionHelper.an(), ConnectionHelper.av(), ConnectionHelper.ao(), ConnectionHelper.au()};
    private String[] b = {"content://com.mi.global.shop.web/web/static/index.html", "content://com.mi.global.shop.web/web/static/category.html", "content://com.mi.global.shop.web/web/static/service.html", "content://com.mi.global.shop.web/web/static/user.html"};
    private MainTabLazyWebFragment c;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.shop_activity_product);
        a();
    }

    private void a() {
        this.mBackView.setVisibility(0);
        setTitle(R.string.main_category);
        this.c = MainTabLazyWebFragment.a(this.f5416a[1], this.b[1]);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment, this.c).commit();
        this.c.setUserVisibleHint(true);
    }

    public void startCartActivity() {
        if (LocaleHelper.g()) {
            startActivityForResult(new Intent(this, ShoppingCartActivity.class), 22);
            return;
        }
        Intent intent = new Intent(this, WebActivity.class);
        intent.putExtra("url", ConnectionHelper.ay());
        startActivity(intent);
    }
}
