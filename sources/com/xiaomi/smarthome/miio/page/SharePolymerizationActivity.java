package com.xiaomi.smarthome.miio.page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.config.AndroidCommonConfigManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.page.deviceshare.ShareDeviceInfoActivity;
import com.xiaomi.smarthome.miniprogram.MyMiniProgramActivity;
import com.xiaomi.smarthome.newui.MultiHomeManagerActivity;
import com.xiaomi.smarthome.shop.analytics.MIOTStat;
import com.xiaomi.smarthome.stat.STAT;

public class SharePolymerizationActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private boolean f19695a;
    @BindView(2131428619)
    FrameLayout crShareDevice;
    @BindView(2131428620)
    FrameLayout crShareHome;
    @BindView(2131428621)
    FrameLayout crShareWx;
    @BindView(2131430969)
    ImageView moduleA3ReturnBtn;
    @BindView(2131430975)
    TextView moduleA3ReturnTitle;
    @BindView(2131430982)
    ImageView moduleA3RightIvSettingBtn;
    @BindView(2131432003)
    RelativeLayout rlShareWx;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_share_polymerization);
        ButterKnife.bind((Activity) this);
        this.f19695a = DarkModeCompat.a((Context) this);
        a();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        STAT.c.g();
    }

    private void a() {
        if (this.f19695a) {
            this.crShareHome.setBackgroundResource(R.drawable.main_grid_common_card_bg_normal);
            this.crShareDevice.setBackgroundResource(R.drawable.main_grid_common_card_bg_normal);
        }
        this.moduleA3ReturnTitle.setText(R.string.menu_edit_share);
        this.moduleA3RightIvSettingBtn.setVisibility(8);
        if (CoreApi.a().D() || !AndroidCommonConfigManager.a().g()) {
            this.rlShareWx.setVisibility(8);
        } else {
            this.rlShareWx.setVisibility(0);
        }
        this.crShareHome.setVisibility(0);
    }

    @OnClick({2131430969, 2131428620, 2131428619, 2131428621})
    public void onViewClicked(View view) {
        int id = view.getId();
        if (id != R.id.module_a_3_return_btn) {
            switch (id) {
                case R.id.cr_share_device:
                    c();
                    return;
                case R.id.cr_share_home:
                    b();
                    return;
                case R.id.cr_share_wx:
                    d();
                    return;
                default:
                    return;
            }
        } else {
            onBackPressed();
        }
    }

    private void b() {
        STAT.d.aQ();
        if (SHApplication.getStateNotifier().a() == 4) {
            Intent intent = new Intent(this, MultiHomeManagerActivity.class);
            intent.putExtra("from", 2);
            startActivity(intent);
            return;
        }
        SHApplication.showLoginDialog(this, false);
    }

    private void c() {
        MIOTStat.Log(MIOTStat.TOUCH, "share");
        STAT.d.aP();
        if (SHApplication.getStateNotifier().a() == 4) {
            Intent intent = new Intent();
            intent.setClass(this, ShareDeviceInfoActivity.class);
            intent.putExtra("user_id", CoreApi.a().s());
            intent.putExtra(ShareDeviceInfoActivity.PARAM_KEY_REF_FROM_APP, true);
            startActivity(intent);
            StatHelper.J();
            return;
        }
        SHApplication.showLoginDialog(this, false);
    }

    private void d() {
        STAT.d.aR();
        if (!SHApplication.getIWXAPI().isWXAppInstalled()) {
            ToastUtil.a(getContext(), (CharSequence) getString(R.string.wx_not_installed));
        } else if (SHApplication.getStateNotifier().a() == 4) {
            Intent intent = new Intent(this, MyMiniProgramActivity.class);
            intent.putExtra("nick_name", "");
            startActivity(intent);
        } else {
            SHApplication.showLoginDialog(this, false);
        }
    }
}
