package com.xiaomi.smarthome.miio.page;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;

public class SettingMainPageV2_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SettingMainPageV2 f19649a;
    private View b;
    private View c;
    private View d;
    private View e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private View l;
    private View m;
    private View n;
    private View o;
    private View p;
    private View q;
    private View r;
    private View s;
    private View t;
    private View u;

    @UiThread
    public SettingMainPageV2_ViewBinding(final SettingMainPageV2 settingMainPageV2, View view) {
        this.f19649a = settingMainPageV2;
        settingMainPageV2.mToolbar = Utils.findRequiredView(view, R.id.title_bar, "field 'mToolbar'");
        View findRequiredView = Utils.findRequiredView(view, R.id.cl_usr, "field 'mLoginFrame' and method 'onClickLoginFrame'");
        settingMainPageV2.mLoginFrame = findRequiredView;
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickLoginFrame();
            }
        });
        settingMainPageV2.mLoginFrameBottom = Utils.findRequiredView(view, R.id.setting_main_appbar_bottom, "field 'mLoginFrameBottom'");
        settingMainPageV2.mTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_title, "field 'mTitle'", TextView.class);
        View findRequiredView2 = Utils.findRequiredView(view, R.id.usr_name, "field 'mNickView' and method 'onClickLoginFrame'");
        settingMainPageV2.mNickView = (TextView) Utils.castView(findRequiredView2, R.id.usr_name, "field 'mNickView'", TextView.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickLoginFrame();
            }
        });
        View findRequiredView3 = Utils.findRequiredView(view, R.id.usr_icon, "field 'mUsrIcon' and method 'onClickLoginFrame'");
        settingMainPageV2.mUsrIcon = (SimpleDraweeView) Utils.castView(findRequiredView3, R.id.usr_icon, "field 'mUsrIcon'", SimpleDraweeView.class);
        this.d = findRequiredView3;
        findRequiredView3.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickLoginFrame();
            }
        });
        settingMainPageV2.mDevCountView = (TextView) Utils.findRequiredViewAsType(view, R.id.usr_dev_count, "field 'mDevCountView'", TextView.class);
        settingMainPageV2.mCollapsingToolbar = (CollapsingToolbarLayout) Utils.findRequiredViewAsType(view, R.id.collapsing_toolbar, "field 'mCollapsingToolbar'", CollapsingToolbarLayout.class);
        settingMainPageV2.mAppBarLayout = (AppBarLayout) Utils.findRequiredViewAsType(view, R.id.appBarLayout, "field 'mAppBarLayout'", AppBarLayout.class);
        settingMainPageV2.mViewContainer = (CoordinatorLayout) Utils.findRequiredViewAsType(view, R.id.view_container, "field 'mViewContainer'", CoordinatorLayout.class);
        settingMainPageV2.mNewMsgTag = Utils.findRequiredView(view, R.id.message_center_red_dot, "field 'mNewMsgTag'");
        View findRequiredView4 = Utils.findRequiredView(view, R.id.rl_consumables, "field 'mConsumables' and method 'onClickDispatcher'");
        settingMainPageV2.mConsumables = (RelativeLayout) Utils.castView(findRequiredView4, R.id.rl_consumables, "field 'mConsumables'", RelativeLayout.class);
        this.e = findRequiredView4;
        findRequiredView4.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        settingMainPageV2.mConsumableRedDot = Utils.findRequiredView(view, R.id.consumables_red_dot, "field 'mConsumableRedDot'");
        View findRequiredView5 = Utils.findRequiredView(view, R.id.rl_voice_assistant, "field 'mVoiceAssistant' and method 'onClickDispatcher'");
        settingMainPageV2.mVoiceAssistant = (RelativeLayout) Utils.castView(findRequiredView5, R.id.rl_voice_assistant, "field 'mVoiceAssistant'", RelativeLayout.class);
        this.f = findRequiredView5;
        findRequiredView5.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView6 = Utils.findRequiredView(view, R.id.rl_third_account, "field 'mThirdAccountBtn' and method 'onClickDispatcher'");
        settingMainPageV2.mThirdAccountBtn = (RelativeLayout) Utils.castView(findRequiredView6, R.id.rl_third_account, "field 'mThirdAccountBtn'", RelativeLayout.class);
        this.g = findRequiredView6;
        findRequiredView6.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView7 = Utils.findRequiredView(view, R.id.rl_shop, "field 'mMyShopBtn' and method 'onClickDispatcher'");
        settingMainPageV2.mMyShopBtn = (RelativeLayout) Utils.castView(findRequiredView7, R.id.rl_shop, "field 'mMyShopBtn'", RelativeLayout.class);
        this.h = findRequiredView7;
        findRequiredView7.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView8 = Utils.findRequiredView(view, R.id.rl_laboratory, "field 'mLaboratory' and method 'onClickDispatcher'");
        settingMainPageV2.mLaboratory = (RelativeLayout) Utils.castView(findRequiredView8, R.id.rl_laboratory, "field 'mLaboratory'", RelativeLayout.class);
        this.i = findRequiredView8;
        findRequiredView8.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView9 = Utils.findRequiredView(view, R.id.rl_score, "field 'mScore' and method 'onClickDispatcher'");
        settingMainPageV2.mScore = (RelativeLayout) Utils.castView(findRequiredView9, R.id.rl_score, "field 'mScore'", RelativeLayout.class);
        this.j = findRequiredView9;
        findRequiredView9.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView10 = Utils.findRequiredView(view, R.id.rl_family, "field 'mFamily' and method 'onClickDispatcher'");
        settingMainPageV2.mFamily = (RelativeLayout) Utils.castView(findRequiredView10, R.id.rl_family, "field 'mFamily'", RelativeLayout.class);
        this.k = findRequiredView10;
        findRequiredView10.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView11 = Utils.findRequiredView(view, R.id.rl_voice_control, "field 'mVoiceControl' and method 'onClickDispatcher'");
        settingMainPageV2.mVoiceControl = (RelativeLayout) Utils.castView(findRequiredView11, R.id.rl_voice_control, "field 'mVoiceControl'", RelativeLayout.class);
        this.l = findRequiredView11;
        findRequiredView11.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        settingMainPageV2.mLaboratoryRedDot = Utils.findRequiredView(view, R.id.laboratory_red_dot, "field 'mLaboratoryRedDot'");
        settingMainPageV2.mImgFeedbackDot = Utils.findRequiredView(view, R.id.feedback_red_dot, "field 'mImgFeedbackDot'");
        settingMainPageV2.mImgAboutDot = Utils.findRequiredView(view, R.id.about_red_dot, "field 'mImgAboutDot'");
        settingMainPageV2.mVoiceRedDot = Utils.findRequiredView(view, R.id.voice_control_red_dot, "field 'mVoiceRedDot'");
        View findRequiredView12 = Utils.findRequiredView(view, R.id.rl_permission, "field 'mPermissionBtn' and method 'onClickDispatcher'");
        settingMainPageV2.mPermissionBtn = (ViewGroup) Utils.castView(findRequiredView12, R.id.rl_permission, "field 'mPermissionBtn'", ViewGroup.class);
        this.m = findRequiredView12;
        findRequiredView12.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        settingMainPageV2.mGroup1 = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.setting_main_page_group_1, "field 'mGroup1'", ViewGroup.class);
        settingMainPageV2.mGroup2 = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.setting_main_page_group_2, "field 'mGroup2'", ViewGroup.class);
        settingMainPageV2.mGroup3 = (ViewGroup) Utils.findRequiredViewAsType(view, R.id.setting_main_page_group_3, "field 'mGroup3'", ViewGroup.class);
        View findRequiredView13 = Utils.findRequiredView(view, R.id.rl_rn_debug, "field 'layoutRnDebug' and method 'onClickDispatcher'");
        settingMainPageV2.layoutRnDebug = findRequiredView13;
        this.n = findRequiredView13;
        findRequiredView13.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        settingMainPageV2.tvRnDebugLeftInfo2 = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_rn_debug_left_info_2, "field 'tvRnDebugLeftInfo2'", TextView.class);
        View findRequiredView14 = Utils.findRequiredView(view, R.id.iv_qrcode, "method 'onClickDispatcher'");
        this.o = findRequiredView14;
        findRequiredView14.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView15 = Utils.findRequiredView(view, R.id.rl_message_center, "method 'onClickDispatcher'");
        this.p = findRequiredView15;
        findRequiredView15.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView16 = Utils.findRequiredView(view, R.id.rl_share, "method 'onClickDispatcher'");
        this.q = findRequiredView16;
        findRequiredView16.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView17 = Utils.findRequiredView(view, R.id.rl_bluetooth_gateway, "method 'onClickDispatcher'");
        this.r = findRequiredView17;
        findRequiredView17.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView18 = Utils.findRequiredView(view, R.id.rl_feedback, "method 'onClickDispatcher'");
        this.s = findRequiredView18;
        findRequiredView18.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView19 = Utils.findRequiredView(view, R.id.rl_about, "method 'onClickDispatcher'");
        this.t = findRequiredView19;
        findRequiredView19.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
        View findRequiredView20 = Utils.findRequiredView(view, R.id.rl_privacy, "method 'onClickDispatcher'");
        this.u = findRequiredView20;
        findRequiredView20.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                settingMainPageV2.onClickDispatcher(view);
            }
        });
    }

    @CallSuper
    public void unbind() {
        SettingMainPageV2 settingMainPageV2 = this.f19649a;
        if (settingMainPageV2 != null) {
            this.f19649a = null;
            settingMainPageV2.mToolbar = null;
            settingMainPageV2.mLoginFrame = null;
            settingMainPageV2.mLoginFrameBottom = null;
            settingMainPageV2.mTitle = null;
            settingMainPageV2.mNickView = null;
            settingMainPageV2.mUsrIcon = null;
            settingMainPageV2.mDevCountView = null;
            settingMainPageV2.mCollapsingToolbar = null;
            settingMainPageV2.mAppBarLayout = null;
            settingMainPageV2.mViewContainer = null;
            settingMainPageV2.mNewMsgTag = null;
            settingMainPageV2.mConsumables = null;
            settingMainPageV2.mConsumableRedDot = null;
            settingMainPageV2.mVoiceAssistant = null;
            settingMainPageV2.mThirdAccountBtn = null;
            settingMainPageV2.mMyShopBtn = null;
            settingMainPageV2.mLaboratory = null;
            settingMainPageV2.mScore = null;
            settingMainPageV2.mFamily = null;
            settingMainPageV2.mVoiceControl = null;
            settingMainPageV2.mLaboratoryRedDot = null;
            settingMainPageV2.mImgFeedbackDot = null;
            settingMainPageV2.mImgAboutDot = null;
            settingMainPageV2.mVoiceRedDot = null;
            settingMainPageV2.mPermissionBtn = null;
            settingMainPageV2.mGroup1 = null;
            settingMainPageV2.mGroup2 = null;
            settingMainPageV2.mGroup3 = null;
            settingMainPageV2.layoutRnDebug = null;
            settingMainPageV2.tvRnDebugLeftInfo2 = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            this.d.setOnClickListener((View.OnClickListener) null);
            this.d = null;
            this.e.setOnClickListener((View.OnClickListener) null);
            this.e = null;
            this.f.setOnClickListener((View.OnClickListener) null);
            this.f = null;
            this.g.setOnClickListener((View.OnClickListener) null);
            this.g = null;
            this.h.setOnClickListener((View.OnClickListener) null);
            this.h = null;
            this.i.setOnClickListener((View.OnClickListener) null);
            this.i = null;
            this.j.setOnClickListener((View.OnClickListener) null);
            this.j = null;
            this.k.setOnClickListener((View.OnClickListener) null);
            this.k = null;
            this.l.setOnClickListener((View.OnClickListener) null);
            this.l = null;
            this.m.setOnClickListener((View.OnClickListener) null);
            this.m = null;
            this.n.setOnClickListener((View.OnClickListener) null);
            this.n = null;
            this.o.setOnClickListener((View.OnClickListener) null);
            this.o = null;
            this.p.setOnClickListener((View.OnClickListener) null);
            this.p = null;
            this.q.setOnClickListener((View.OnClickListener) null);
            this.q = null;
            this.r.setOnClickListener((View.OnClickListener) null);
            this.r = null;
            this.s.setOnClickListener((View.OnClickListener) null);
            this.s = null;
            this.t.setOnClickListener((View.OnClickListener) null);
            this.t = null;
            this.u.setOnClickListener((View.OnClickListener) null);
            this.u = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
