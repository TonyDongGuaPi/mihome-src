package com.mi.global.shop.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import com.mi.global.shop.R;
import com.mi.global.shop.buy.OTExFragment;

public class OTExActivity extends BaseActivity {
    static final String TAG = OTExActivity.class.getCanonicalName();

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.shop_activity_otex);
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.replace(R.id.layout_fragment_group, new OTExFragment());
        beginTransaction.commitAllowingStateLoss();
    }
}
