package com.xiaomi.youpin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.widget.FrameLayout;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.miot.store.api.MiotStoreBaseReactFragmentActivity;
import com.xiaomi.miot.store.common.MiotStoreConstant;
import com.xiaomi.smarthome.R;
import com.xiaomi.youpin.log.LogUtils;
import java.util.ArrayList;
import java.util.List;

public class RnMaskActivity extends MiotStoreBaseReactFragmentActivity {
    BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (MiotStoreConstant.d.equals(intent.getAction())) {
                RnMaskActivity.this.dimissRnMaskView();
            } else if (MiotStoreConstant.e.equals(intent.getAction())) {
                RnMaskActivity.this.showRnMask(intent.getStringExtra("url"));
            }
        }
    };
    Handler mHandler = new Handler(Looper.getMainLooper());
    FrameLayout mRnMaskContainer;
    List<String> mShouldShowRnMaskUrlList = new ArrayList();

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mBroadcastReceiver);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_rn_mask);
        this.mRnMaskContainer = (FrameLayout) findViewById(R.id.rn_mask_container);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MiotStoreConstant.d);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mBroadcastReceiver, intentFilter);
        showRnMask(getIntent().getStringExtra("url"));
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        finish();
    }

    public void invokeDefaultOnBackPressed() {
        onBackPressed();
    }

    /* access modifiers changed from: package-private */
    public void showRnMask(String str) {
        boolean z;
        if (!TextUtils.isEmpty(str)) {
            LogUtils.e("BackgroundPage", "showRnMask:" + str);
            int i = 0;
            while (true) {
                if (i >= this.mShouldShowRnMaskUrlList.size()) {
                    z = false;
                    break;
                } else if (str.equals(this.mShouldShowRnMaskUrlList.get(i))) {
                    z = true;
                    break;
                } else {
                    i++;
                }
            }
            if (!z) {
                this.mShouldShowRnMaskUrlList.add(str);
            }
        }
        if (this.mRnMaskContainer.getVisibility() != 0 && this.mShouldShowRnMaskUrlList.size() != 0) {
            Fragment newMiotStoreFragment = MiotStoreApi.a().newMiotStoreFragment(this.mShouldShowRnMaskUrlList.remove(0), false);
            FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
            beginTransaction.replace(R.id.rn_mask_container, newMiotStoreFragment);
            beginTransaction.commit();
            this.mRnMaskContainer.setVisibility(0);
        }
    }

    public void dimissRnMaskView() {
        finish();
    }
}
