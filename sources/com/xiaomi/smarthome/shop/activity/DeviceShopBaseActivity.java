package com.xiaomi.smarthome.shop.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.miio.Miio;
import com.xiaomi.smarthome.shop.analytics.MIOTStat;

public abstract class DeviceShopBaseActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final String f22160a = "DeviceShopBaseActivity";
    ViewGroup mContainer;
    ProgressBar mProgressBar;
    View mRefreshButton;
    ImageView mRefreshImage;

    public enum LoadingPageState {
        NONE,
        ERROR,
        LOADING
    }

    public void onRefreshPage() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Miio.h(f22160a, "onCreate");
    }

    /* access modifiers changed from: protected */
    public void onStart() {
        super.onStart();
        Miio.h(f22160a, "onStart");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    public void onBackPressed() {
        MIOTStat.Log(MIOTStat.TOUCH, "backPress", this.mPageId);
        super.onBackPressed();
    }

    public void onPressBackButton() {
        MIOTStat.Log(MIOTStat.TOUCH, "back", this.mPageId);
        super.onBackPressed();
    }

    /* access modifiers changed from: package-private */
    public void initRefreshPageView() {
        if (this.mContainer == null) {
            this.mContainer = (ViewGroup) findViewById(R.id.container);
            this.mRefreshImage = (ImageView) findViewById(R.id.refresh_image);
            this.mRefreshButton = findViewById(R.id.refresh_btn);
            this.mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
            if (this.mRefreshButton != null) {
                this.mRefreshButton.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        DeviceShopBaseActivity.this.setRefreshPageState(LoadingPageState.LOADING);
                        DeviceShopBaseActivity.this.onRefreshPage();
                        MIOTStat.Log(MIOTStat.TOUCH, "pageRefresh");
                    }
                });
            }
        }
    }

    public void setRefreshPageState(LoadingPageState loadingPageState) {
        initRefreshPageView();
        if (this.mContainer != null && this.mProgressBar != null && this.mRefreshButton != null && this.mRefreshImage != null) {
            switch (loadingPageState) {
                case NONE:
                    this.mContainer.setVisibility(8);
                    this.mProgressBar.setVisibility(8);
                    return;
                case ERROR:
                    this.mContainer.setVisibility(0);
                    this.mProgressBar.setVisibility(8);
                    this.mRefreshButton.setVisibility(0);
                    this.mRefreshImage.setVisibility(0);
                    return;
                case LOADING:
                    this.mContainer.setVisibility(0);
                    this.mProgressBar.setVisibility(0);
                    this.mProgressBar.setIndeterminate(true);
                    this.mRefreshButton.setVisibility(8);
                    this.mRefreshImage.setVisibility(8);
                    return;
                default:
                    return;
            }
        }
    }
}
