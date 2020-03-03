package com.mi.global.bbs.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import com.mi.global.bbs.R;
import com.mi.global.bbs.account.LoginManager;
import com.mi.global.bbs.base.BaseActivity;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.http.UrlAction;

public class MineActivity extends BaseActivity implements View.OnClickListener {
    public static final String REFRESH_BROADCAST_ACTION = "refresh_broadcast_action_MineFragment";
    public static boolean isCurrentShown = false;
    private BroadcastReceiver refreshReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            MineActivity.this.refreshUserInfo();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setCustomContentView(R.layout.bbs_activity_mine);
        setTitleAndBack(R.string.account_title, this.titleBackListener);
        isCurrentShown = true;
        this.notificationBtn.setVisibility(0);
        this.notificationBtn.setOnClickListener(this);
        LoginManager.getInstance().addLoginListener(this);
        registerRefreshBroadcast();
    }

    private void registerRefreshBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(REFRESH_BROADCAST_ACTION);
        registerReceiver(this.refreshReceiver, intentFilter);
    }

    /* access modifiers changed from: private */
    public void refreshUserInfo() {
        Fragment findFragmentById = getSupportFragmentManager().findFragmentById(R.id.fragment_me);
        if (findFragmentById instanceof MineFragment) {
            ((MineFragment) findFragmentById).onRefresh();
        }
    }

    private void unregisterRefreshBroadcast() {
        unregisterReceiver(this.refreshReceiver);
    }

    public static void jump(Context context) {
        context.startActivity(new Intent(context, MineActivity.class));
    }

    public void showNotificationRedDot(boolean z) {
        this.notificationRedDot.setVisibility(z ? 0 : 8);
    }

    public void onClick(View view) {
        if (view == this.notificationBtn) {
            login(new Runnable() {
                public void run() {
                    WebActivity.jump(MineActivity.this, ApiClient.getAppUrl(UrlAction.MY_MSG));
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        isCurrentShown = false;
        unregisterRefreshBroadcast();
        LoginManager.getInstance().removeLoginListener(this);
    }
}
