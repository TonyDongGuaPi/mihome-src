package com.xiaomi.smarthome.scenenew.actiivity;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.lite.scene.HomeSceneScrollView;

public class SmarthomeCreateAutoSceneActivity_ViewBinding implements Unbinder {

    /* renamed from: a  reason: collision with root package name */
    private SmarthomeCreateAutoSceneActivity f21863a;
    private View b;
    private View c;

    @UiThread
    public SmarthomeCreateAutoSceneActivity_ViewBinding(SmarthomeCreateAutoSceneActivity smarthomeCreateAutoSceneActivity) {
        this(smarthomeCreateAutoSceneActivity, smarthomeCreateAutoSceneActivity.getWindow().getDecorView());
    }

    @UiThread
    public SmarthomeCreateAutoSceneActivity_ViewBinding(final SmarthomeCreateAutoSceneActivity smarthomeCreateAutoSceneActivity, View view) {
        this.f21863a = smarthomeCreateAutoSceneActivity;
        smarthomeCreateAutoSceneActivity.mModuleA4ReturnBtn = (ImageView) Utils.findRequiredViewAsType(view, R.id.module_a_4_return_btn, "field 'mModuleA4ReturnBtn'", ImageView.class);
        smarthomeCreateAutoSceneActivity.mModuleA4ReturnTitle = (TextView) Utils.findRequiredViewAsType(view, R.id.module_a_4_return_title, "field 'mModuleA4ReturnTitle'", TextView.class);
        View findRequiredView = Utils.findRequiredView(view, R.id.module_a_4_commit, "field 'mModuleA4MoreBtn' and method 'gotoMorePage'");
        smarthomeCreateAutoSceneActivity.mModuleA4MoreBtn = (TextView) Utils.castView(findRequiredView, R.id.module_a_4_commit, "field 'mModuleA4MoreBtn'", TextView.class);
        this.b = findRequiredView;
        findRequiredView.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                smarthomeCreateAutoSceneActivity.gotoMorePage();
            }
        });
        View findRequiredView2 = Utils.findRequiredView(view, R.id.module_a_4_commit_btn, "field 'mConfirmBtn' and method 'completeScene'");
        smarthomeCreateAutoSceneActivity.mConfirmBtn = (ImageButton) Utils.castView(findRequiredView2, R.id.module_a_4_commit_btn, "field 'mConfirmBtn'", ImageButton.class);
        this.c = findRequiredView2;
        findRequiredView2.setOnClickListener(new DebouncingOnClickListener() {
            public void doClick(View view) {
                smarthomeCreateAutoSceneActivity.completeScene();
            }
        });
        smarthomeCreateAutoSceneActivity.mExeTimeRL = (RelativeLayout) Utils.findRequiredViewAsType(view, R.id.scene_exe_time_rl, "field 'mExeTimeRL'", RelativeLayout.class);
        smarthomeCreateAutoSceneActivity.mExeTimeTV = (TextView) Utils.findRequiredViewAsType(view, R.id.scene_exe_time_tv, "field 'mExeTimeTV'", TextView.class);
        smarthomeCreateAutoSceneActivity.mExeTimeHint = (TextView) Utils.findRequiredViewAsType(view, R.id.exe_time_tv_hint, "field 'mExeTimeHint'", TextView.class);
        smarthomeCreateAutoSceneActivity.mEditCancaelBtn = (Button) Utils.findRequiredViewAsType(view, R.id.edit_cancel_btn, "field 'mEditCancaelBtn'", Button.class);
        smarthomeCreateAutoSceneActivity.mEditCompleteBtn = (Button) Utils.findRequiredViewAsType(view, R.id.edit_complete_btn, "field 'mEditCompleteBtn'", Button.class);
        smarthomeCreateAutoSceneActivity.mConditionRV = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.auto_scene_condition_rv, "field 'mConditionRV'", RecyclerView.class);
        smarthomeCreateAutoSceneActivity.mAddContionTV = (TextView) Utils.findRequiredViewAsType(view, R.id.add_scene_condition_add_again_tv, "field 'mAddContionTV'", TextView.class);
        smarthomeCreateAutoSceneActivity.mActionRV = (RecyclerView) Utils.findRequiredViewAsType(view, R.id.auto_scene_action_rv, "field 'mActionRV'", RecyclerView.class);
        smarthomeCreateAutoSceneActivity.mAddActionTV = (TextView) Utils.findRequiredViewAsType(view, R.id.add_scene_action_add_again_tv, "field 'mAddActionTV'", TextView.class);
        smarthomeCreateAutoSceneActivity.mHandTV = (TextView) Utils.findRequiredViewAsType(view, R.id.hand_tv, "field 'mHandTV'", TextView.class);
        smarthomeCreateAutoSceneActivity.mScrollView = (HomeSceneScrollView) Utils.findRequiredViewAsType(view, R.id.home_scene_scroll_view, "field 'mScrollView'", HomeSceneScrollView.class);
    }

    @CallSuper
    public void unbind() {
        SmarthomeCreateAutoSceneActivity smarthomeCreateAutoSceneActivity = this.f21863a;
        if (smarthomeCreateAutoSceneActivity != null) {
            this.f21863a = null;
            smarthomeCreateAutoSceneActivity.mModuleA4ReturnBtn = null;
            smarthomeCreateAutoSceneActivity.mModuleA4ReturnTitle = null;
            smarthomeCreateAutoSceneActivity.mModuleA4MoreBtn = null;
            smarthomeCreateAutoSceneActivity.mConfirmBtn = null;
            smarthomeCreateAutoSceneActivity.mExeTimeRL = null;
            smarthomeCreateAutoSceneActivity.mExeTimeTV = null;
            smarthomeCreateAutoSceneActivity.mExeTimeHint = null;
            smarthomeCreateAutoSceneActivity.mEditCancaelBtn = null;
            smarthomeCreateAutoSceneActivity.mEditCompleteBtn = null;
            smarthomeCreateAutoSceneActivity.mConditionRV = null;
            smarthomeCreateAutoSceneActivity.mAddContionTV = null;
            smarthomeCreateAutoSceneActivity.mActionRV = null;
            smarthomeCreateAutoSceneActivity.mAddActionTV = null;
            smarthomeCreateAutoSceneActivity.mHandTV = null;
            smarthomeCreateAutoSceneActivity.mScrollView = null;
            this.b.setOnClickListener((View.OnClickListener) null);
            this.b = null;
            this.c.setOnClickListener((View.OnClickListener) null);
            this.c = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
