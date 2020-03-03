package com.xiaomi.smarthome.miniprogram;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshLinearLayout;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miniprogram.model.MyMiniProgramDevice;
import com.xiaomi.smarthome.shop.share.ShareManager;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class MyMiniProgramActivity extends BaseActivity implements View.OnClickListener, EditModeChangedListener, OnGettingMiniProgram, OnItemCheckedListener, OnItemLongClickListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20040a = "MyMiniProgramActivity";
    private View b;
    private int c;
    /* access modifiers changed from: private */
    public MLAlertDialog d;
    /* access modifiers changed from: private */
    public MLAlertDialog e;
    /* access modifiers changed from: private */
    public Dialog f;
    /* access modifiers changed from: private */
    public LoadingMoreView g;
    private View h;
    private MiniProgramAdapter i;
    private ShareReceiver j;
    private AbsListView.OnScrollListener k = new AbsListView.OnScrollListener() {
        private int b = 0;
        private int c = 10;

        public void onScrollStateChanged(AbsListView absListView, int i) {
            View currentFocus;
            if (1 == i && (currentFocus = MyMiniProgramActivity.this.getCurrentFocus()) != null) {
                currentFocus.clearFocus();
            }
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i3 < this.b) {
                this.b = 0;
            }
            if (!MiniProgramManager.a().f() && i3 > this.b) {
                this.b = i3;
            }
            if (MiniProgramManager.a().h() && MiniProgramManager.a().g() && !MiniProgramManager.a().f() && i3 - i2 <= i + this.c) {
                MyMiniProgramActivity.this.g.displayLoding();
                MiniProgramManager.a().e();
            }
        }
    };
    private boolean l = false;
    @BindView(2131430969)
    View mBackView;
    @BindView(2131427885)
    View mBottomDeleteBar;
    @BindView(2131428729)
    View mBottomDeleteBtn;
    @BindView(2131428164)
    View mCancelBtn;
    @BindView(2131428503)
    CustomPullDownRefreshLinearLayout mEmptyView;
    @BindView(2131428501)
    TextView mErrorTipTxt;
    @BindView(2131432254)
    ImageView mIvSelectAll;
    @BindView(2131430457)
    PullDownDragListView mListView;
    @BindView(2131430982)
    ImageView mManageBtn;
    @BindView(2131432285)
    TextView mSelectCountText;
    @BindView(2131432900)
    TextView mTipsTxt;
    @BindView(2131433000)
    View mTopManageBar;

    private static class ShareReceiver extends BroadcastReceiver {
        private ShareReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Log.v(MyMiniProgramActivity.f20040a, "ShareReceiver onReceive.");
            if (intent != null && TextUtils.equals(intent.getAction(), ShareManager.b)) {
                int intExtra = intent.getIntExtra("result_code", -2);
                String stringExtra = intent.getStringExtra("message");
                Log.d(MyMiniProgramActivity.f20040a, "received share result,errorCode:" + intExtra + ",errMsg:" + stringExtra);
                if (intExtra != 0) {
                    ToastUtil.a((int) R.string.share_canceled);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_my_mini_program);
        ButterKnife.bind((Activity) this);
        EditController.a().a((EditModeChangedListener) this);
        MiniProgramManager.a().a((OnGettingMiniProgram) this);
        c();
        a();
        this.mListView.doRefresh();
        if (this.j != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.j);
        }
        this.j = new ShareReceiver();
        LocalBroadcastManager.getInstance(this).registerReceiver(this.j, new IntentFilter(ShareManager.b));
    }

    /* access modifiers changed from: protected */
    public void onStop() {
        super.onStop();
        EditController.a().a(1);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        MiniProgramManager.a().c();
        EditController.a().b();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.j);
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 == 4) {
            if (EditController.a().c == 0) {
                EditController.a().a(1);
                return false;
            } else if (this.f != null && this.f.isShowing()) {
                this.f.dismiss();
                return false;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    public void onLowMemory() {
        super.onLowMemory();
        MiniProgramManager.a().c();
    }

    public void onTrimMemory(int i2) {
        super.onTrimMemory(i2);
        if (80 == i2) {
            MiniProgramManager.a().c();
        }
    }

    public void onBackPressed() {
        super.onBackPressed();
        MiniProgramManager.a().i();
    }

    private void a() {
        this.c = this.mListView.getHeaderViewsCount();
        this.b = LayoutInflater.from(this).inflate(R.layout.common_list_space_empty, this.mListView, false);
        this.mListView.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                MyMiniProgramActivity.this.b();
            }
        });
        this.mListView.setCanPullDown(true);
        this.g = new LoadingMoreView(this);
        FrameLayout frameLayout = new FrameLayout(this);
        frameLayout.addView(this.g, 0, new FrameLayout.LayoutParams(-1, -2));
        this.mListView.addFooterView(frameLayout);
        this.mListView.setOnScrollListener(this.k);
        RelativeLayout relativeLayout = new RelativeLayout(this);
        this.h = LayoutInflater.from(this).inflate(R.layout.view_miniprogram_tip, (ViewGroup) null);
        relativeLayout.addView(this.h, 0, new RelativeLayout.LayoutParams(-1, -2));
        this.h.setVisibility(8);
        this.mListView.addFooterView(relativeLayout);
        this.h.setOnClickListener(this);
        this.mEmptyView.setRefreshListener(new CustomPullDownRefreshLinearLayout.OnRefreshListener() {
            public void a() {
                MyMiniProgramActivity.this.b();
            }
        });
        this.mEmptyView.setScrollView((ScrollView) findViewById(R.id.scroll_view));
        this.i = new MiniProgramAdapter(this);
        this.i.a((OnItemLongClickListener) this);
        this.i.a((OnItemCheckedListener) this);
        this.mListView.setAdapter(this.i);
        this.mBottomDeleteBar.setOnClickListener(this);
        this.mSelectCountText.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, EditController.a().d.size(), new Object[]{Integer.valueOf(EditController.a().d.size())}));
        this.mTipsTxt.setOnClickListener(this);
        if (SharePrefsManager.b(SHApplication.getAppContext(), "pref_mini_program", "is_first_in", true)) {
            f();
            SharePrefsManager.a(SHApplication.getAppContext(), "pref_mini_program", "is_first_in", false);
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        this.g.setVisibility(8);
        this.h.setVisibility(8);
        MiniProgramManager.a().d();
        MiniProgramManager.a().a(true);
        MiniProgramManager.a().b();
    }

    private void c() {
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.my_mini_program);
        if (TitleBarUtil.f11582a) {
            this.mTopManageBar.getLayoutParams().height += TitleBarUtil.b();
            this.mTopManageBar.setPadding(0, this.mTopManageBar.getPaddingTop() + TitleBarUtil.b(), 0, 0);
            this.mTopManageBar.setLayoutParams(this.mTopManageBar.getLayoutParams());
        }
        this.mBackView.setOnClickListener(this);
        this.mManageBtn.setOnClickListener(this);
        this.mCancelBtn.setOnClickListener(this);
        this.mIvSelectAll.setOnClickListener(this);
    }

    private void d() {
        int count = this.i.getCount();
        EditController.a().d.clear();
        for (int i2 = 0; i2 < count; i2++) {
            EditController.a().d.put(i2, true);
        }
        this.mIvSelectAll.setImageResource(R.drawable.un_select_selector);
        this.i.notifyDataSetChanged();
        this.mSelectCountText.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, EditController.a().d.size(), new Object[]{Integer.valueOf(EditController.a().d.size())}));
    }

    private void e() {
        EditController.a().d.clear();
        this.mIvSelectAll.setImageResource(R.drawable.all_select_selector);
        this.i.notifyDataSetChanged();
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bottom_delete_bar:
                if (EditController.a().d.size() > 0) {
                    g();
                    return;
                } else {
                    ToastUtil.a((int) R.string.choose_one_tip);
                    return;
                }
            case R.id.cancel_btn:
                EditController.a().a(1);
                return;
            case R.id.footer_tips:
            case R.id.tips:
                f();
                STAT.d.aF();
                return;
            case R.id.module_a_3_return_btn:
                finish();
                STAT.d.aD();
                return;
            case R.id.module_a_3_right_iv_setting_btn:
                if (!this.mListView.isRefreshing() && !this.mEmptyView.isRefreshing()) {
                    EditController.a().a(0);
                    return;
                }
                return;
            case R.id.select_all_btn:
                if (EditController.a().d.size() == this.i.getCount()) {
                    e();
                } else {
                    d();
                }
                this.mSelectCountText.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, EditController.a().d.size(), new Object[]{Integer.valueOf(EditController.a().d.size())}));
                return;
            default:
                return;
        }
    }

    private void f() {
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
        this.d = new MLAlertDialog.Builder(this).b((View) (TextView) LayoutInflater.from(this).inflate(R.layout.dialog_mini_program_tip, (ViewGroup) null)).a((int) R.string.mini_program_isee, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (MyMiniProgramActivity.this.d != null && MyMiniProgramActivity.this.d.isShowing()) {
                    dialogInterface.dismiss();
                }
            }
        }).b();
        if (!this.d.isShowing()) {
            this.d.show();
        }
    }

    private void g() {
        if (this.e != null && this.e.isShowing()) {
            this.e.dismiss();
        }
        this.e = new MLAlertDialog.Builder(this).b((int) R.string.tip_unauth).a((int) R.string.program_unauth, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (MyMiniProgramActivity.this.e != null && MyMiniProgramActivity.this.e.isShowing()) {
                    MyMiniProgramActivity.this.e.dismiss();
                }
                Dialog unused = MyMiniProgramActivity.this.f = XQProgressDialog.a(MyMiniProgramActivity.this, "", MyMiniProgramActivity.this.getString(R.string.unauthing));
                final ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < MiniProgramManager.a().d.size(); i2++) {
                    if (EditController.a().d.get(i2)) {
                        arrayList.add(MiniProgramManager.a().d.get(i2).c.did);
                    }
                }
                if (arrayList.size() == 0) {
                    if (MyMiniProgramActivity.this.f != null && MyMiniProgramActivity.this.f.isShowing()) {
                        MyMiniProgramActivity.this.f.dismiss();
                    }
                    EditController.a().a(1);
                    return;
                }
                MiniProgramManager.a().b((List<String>) arrayList, (AsyncCallback) new AsyncCallback() {
                    public void onSuccess(Object obj) {
                        if (MyMiniProgramActivity.this.isValid()) {
                            MiniProgramManager.a().a((List<String>) arrayList, (AsyncCallback) new AsyncCallback<JSONObject, Error>() {
                                /* renamed from: a */
                                public void onSuccess(JSONObject jSONObject) {
                                    if (MyMiniProgramActivity.this.isValid()) {
                                        MiniProgramManager.a().c((List<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                                            /* renamed from: a */
                                            public void onSuccess(JSONObject jSONObject) {
                                                if (MyMiniProgramActivity.this.isValid()) {
                                                    if (MyMiniProgramActivity.this.f != null && MyMiniProgramActivity.this.f.isShowing()) {
                                                        MyMiniProgramActivity.this.f.dismiss();
                                                    }
                                                    ToastUtil.a((int) R.string.unauth_success);
                                                    EditController.a().a(1);
                                                    MyMiniProgramActivity.this.mListView.doRefresh();
                                                }
                                            }

                                            public void onFailure(Error error) {
                                                if (MyMiniProgramActivity.this.isValid()) {
                                                    if (MyMiniProgramActivity.this.f != null && MyMiniProgramActivity.this.f.isShowing()) {
                                                        MyMiniProgramActivity.this.f.dismiss();
                                                    }
                                                    ToastUtil.a((int) R.string.share_fail_retry);
                                                }
                                            }
                                        });
                                    }
                                }

                                public void onFailure(Error error) {
                                    if (MyMiniProgramActivity.this.isValid()) {
                                        if (MyMiniProgramActivity.this.f != null && MyMiniProgramActivity.this.f.isShowing()) {
                                            MyMiniProgramActivity.this.f.dismiss();
                                        }
                                        ToastUtil.a((int) R.string.share_fail_retry);
                                    }
                                }
                            });
                        }
                    }

                    public void onFailure(Error error) {
                        if (MyMiniProgramActivity.this.isValid()) {
                            if (MyMiniProgramActivity.this.f != null && MyMiniProgramActivity.this.f.isShowing()) {
                                MyMiniProgramActivity.this.f.dismiss();
                            }
                            ToastUtil.a((int) R.string.unauth_failed);
                        }
                    }
                });
            }
        }).b((int) R.string.mini_program_cancel, (DialogInterface.OnClickListener) null).b();
        if (!this.e.isShowing()) {
            this.e.show();
        }
    }

    public void onBrowseMode() {
        e();
        this.i.notifyDataSetChanged();
        this.mTopManageBar.setVisibility(8);
        this.mBottomDeleteBar.setVisibility(8);
        this.i.notifyDataSetChanged();
        this.mListView.setCanPullDown(true);
        this.mListView.setOnScrollListener(this.k);
        if (MiniProgramManager.a().d == null || MiniProgramManager.a().d.size() == 0) {
            this.mManageBtn.setVisibility(8);
        } else {
            this.mManageBtn.setVisibility(0);
        }
    }

    public void onManageMode() {
        STAT.d.aE();
        this.i.notifyDataSetChanged();
        this.mListView.setCanPullDown(false);
        this.mListView.setOnScrollListener((AbsListView.OnScrollListener) null);
        this.mTopManageBar.setVisibility(0);
        this.mBottomDeleteBar.setVisibility(0);
        this.mTopManageBar.measure(0, 0);
        this.mBottomDeleteBar.measure(0, 0);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mTopManageBar, View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.mBottomDeleteBar.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mBottomDeleteBar, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.mBottomDeleteBar.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void onLongClick() {
        if (!this.mListView.isRefreshing()) {
            EditController.a().a(0);
        }
    }

    public boolean allowPerformLongClick() {
        return EditController.a().c == 1 && !this.mListView.isRefreshing() && !this.mEmptyView.isRefreshing();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.l = false;
    }

    public void onSuccess(List<MyMiniProgramDevice> list, int i2, boolean z) {
        if (isValid()) {
            if (!this.l) {
                this.l = true;
                STAT.c.c(list.size());
            }
            this.mListView.postRefresh();
            this.mEmptyView.postRefresh();
            this.g.setVisibility(8);
            if (i2 == 0) {
                this.i.a();
            }
            this.i.b(list);
            this.i.notifyDataSetChanged();
            if (this.i.getCount() == 0) {
                this.mManageBtn.setVisibility(8);
                this.mEmptyView.setVisibility(0);
                this.i.a();
                this.mErrorTipTxt.setText(R.string.my_mini_program_no_data);
                h();
                return;
            }
            this.mEmptyView.setVisibility(8);
            this.mTipsTxt.setVisibility(8);
            this.mManageBtn.setVisibility(0);
            if (this.c == this.mListView.getHeaderViewsCount()) {
                this.mListView.addHeaderView(this.b);
            }
            if (z) {
                this.h.setVisibility(8);
                this.mTipsTxt.setVisibility(8);
                return;
            }
            h();
        }
    }

    private void h() {
        if (i()) {
            this.h.setVisibility(8);
            this.mTipsTxt.setVisibility(0);
            return;
        }
        this.h.setVisibility(0);
        this.mTipsTxt.setVisibility(8);
    }

    private boolean i() {
        WindowManager windowManager = getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        int i2 = (int) (((float) displayMetrics.heightPixels) / displayMetrics.density);
        LogUtil.b(f20040a, ((this.i.getCount() * 70) + 135) + "$$$$$" + i2);
        return (this.i.getCount() * 70) + 135 < i2;
    }

    public void onError(boolean z) {
        if (isValid()) {
            if (!this.l) {
                this.l = true;
                STAT.c.c(0);
            }
            this.mListView.postRefresh();
            this.mEmptyView.postRefresh();
            this.g.setVisibility(8);
            if (MiniProgramManager.a().e == null || MiniProgramManager.a().e.size() == 0) {
                this.mListView.removeHeaderView(this.b);
                this.mEmptyView.setVisibility(0);
                this.i.a();
                this.mErrorTipTxt.setText(R.string.my_mini_program_no_data);
                this.mManageBtn.setVisibility(8);
                h();
                return;
            }
            if (z) {
                this.g.displayError((int) R.string.loading_more_error, (View.OnClickListener) new View.OnClickListener() {
                    public void onClick(View view) {
                        MiniProgramManager.a().e();
                    }
                });
            }
            ToastUtil.a((int) R.string.load_failed);
        }
    }

    public void onCheck(int i2) {
        if (i2 == this.i.getCount()) {
            this.mIvSelectAll.setImageResource(R.drawable.un_select_selector);
        } else {
            this.mIvSelectAll.setImageResource(R.drawable.all_select_selector);
        }
        this.mSelectCountText.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, i2, new Object[]{Integer.valueOf(i2)}));
    }
}
