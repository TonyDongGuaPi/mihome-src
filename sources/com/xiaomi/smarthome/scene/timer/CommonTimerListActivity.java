package com.xiaomi.smarthome.scene.timer;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import com.h6ah4i.android.widget.advrecyclerview.animator.RefactoredDefaultItemAnimator;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.h6ah4i.android.widget.advrecyclerview.utils.WrapperAdapterUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.MultipleChoiceDialogHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.DevicePtrFrameLayout;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miniprogram.EditController;
import com.xiaomi.smarthome.miniprogram.EditModeChangedListener;
import com.xiaomi.smarthome.scene.timer.TimerCommonManager;
import com.xiaomi.smarthome.shop.utils.NetworkManager;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonTimerListActivity extends BaseActivity implements EditModeChangedListener, TimerCommonManager.PlugSceneListener {
    public static final String BOTH_TIMER_MUST_BE_SET = "both_timer_must_be_set";
    public static final String OFF_TIMER_TYPE = "off_timer_type";
    public static final String ON_TIMER_TYPE = "on_timer_type";
    public static final String PERIOD_TIMER_TYPE = "period_timer_type";
    public static final String TIMER_TIPS_OFF_TIMER = "off_timer_tips";
    public static final String TIMER_TIPS_ON_TIMER = "on_timer_tips";
    public static final String TIMER_TIPS_TIMER_LIST = "list_timer_tips";

    /* renamed from: a  reason: collision with root package name */
    private static final int f21633a = 1;
    private static final int b = 2;
    private static final int c = 3;
    private static final int d = 4;
    private static final int e = 0;
    private static final int f = 1;
    private static final int g = 2;
    /* access modifiers changed from: private */
    public String A;
    /* access modifiers changed from: private */
    public String B;
    private boolean C = true;
    private RecyclerView D;
    /* access modifiers changed from: private */
    public RecyclerView.LayoutManager E;
    /* access modifiers changed from: private */
    public RecyclerView.Adapter F;
    private RecyclerViewExpandableItemManager G;
    /* access modifiers changed from: private */
    public RecyclerTimerAdapter H;
    private PtrIndicator I;
    View emptyAddTimer;
    /* access modifiers changed from: private */
    public String h;
    /* access modifiers changed from: private */
    public String i;
    boolean isPullRefresh = false;
    /* access modifiers changed from: private */
    public String j;
    /* access modifiers changed from: private */
    public String k;
    /* access modifiers changed from: private */
    public String l;
    /* access modifiers changed from: private */
    public String m;
    View mBottomDeleteBar;
    View mBottomDeleteBtn;
    View mCancelBtn;
    ImageView mIvSelectAll;
    DevicePtrFrameLayout mPullRefresh;
    TextView mSelectCountText;
    TimerCommonManager mTimerCommonManager;
    View mTopManageBar;
    /* access modifiers changed from: private */
    public String n;
    private String o;
    private boolean p;
    private boolean q;
    private boolean r;
    /* access modifiers changed from: private */
    public Device s;
    private View t;
    private View u;
    private View v;
    /* access modifiers changed from: private */
    public XQProgressDialog w;
    /* access modifiers changed from: private */
    public boolean x = false;
    /* access modifiers changed from: private */
    public String y;
    /* access modifiers changed from: private */
    public String z;

    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.H.notifyDataSetChanged();
                this.G.d();
                this.w.dismiss();
                this.mPullRefresh.refreshComplete();
                if (this.H == null || this.H.a() <= 0) {
                    this.t.setVisibility(0);
                    this.u.setVisibility(0);
                    this.v.setVisibility(4);
                    return;
                }
                this.t.setVisibility(4);
                this.u.setVisibility(4);
                this.v.setVisibility(0);
                return;
            case 2:
                this.mPullRefresh.refreshComplete();
                this.w.dismiss();
                int i2 = message.arg1;
                ErrorCode.ERROR_INVALID_REQUEST.getCode();
                if (this.H == null || this.H.a() <= 0) {
                    this.t.setVisibility(0);
                    this.u.setVisibility(0);
                    this.v.setVisibility(4);
                    return;
                }
                this.t.setVisibility(4);
                this.u.setVisibility(4);
                this.v.setVisibility(0);
                return;
            case 3:
                this.w.dismiss();
                this.H.notifyDataSetChanged();
                this.G.d();
                if (this.H == null || this.H.a() <= 0) {
                    this.t.setVisibility(0);
                    this.u.setVisibility(0);
                    this.v.setVisibility(4);
                    return;
                }
                this.t.setVisibility(4);
                this.u.setVisibility(4);
                this.v.setVisibility(0);
                return;
            case 4:
                this.w.dismiss();
                if (this.H == null || this.H.a() <= 0) {
                    this.t.setVisibility(0);
                    this.u.setVisibility(0);
                    this.v.setVisibility(4);
                    return;
                }
                this.t.setVisibility(4);
                this.u.setVisibility(4);
                this.v.setVisibility(0);
                return;
            default:
                return;
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_time_setting_lamp);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra(TimerCommonManager.j);
        this.h = intent.getStringExtra(TimerCommonManager.g);
        this.i = intent.getStringExtra(TimerCommonManager.h);
        this.j = intent.getStringExtra(TimerCommonManager.i);
        this.s = SmartHomeDeviceManager.a().b(stringExtra);
        if (this.s == null) {
            this.s = SmartHomeDeviceManager.a().l(stringExtra);
        }
        if (this.s == null) {
            finish();
            return;
        }
        this.k = intent.getStringExtra(TimerCommonManager.o);
        this.l = intent.getStringExtra(TimerCommonManager.p);
        this.m = intent.getStringExtra(TimerCommonManager.k);
        this.n = intent.getStringExtra(TimerCommonManager.m);
        this.o = intent.getStringExtra(TimerCommonManager.v);
        this.B = intent.getStringExtra(TimerCommonManager.r);
        this.C = intent.getBooleanExtra(TimerCommonManager.s, true);
        this.p = intent.getBooleanExtra(ON_TIMER_TYPE, true);
        this.q = intent.getBooleanExtra(OFF_TIMER_TYPE, true);
        this.r = intent.getBooleanExtra(PERIOD_TIMER_TYPE, true);
        this.x = intent.getBooleanExtra("both_timer_must_be_set", false);
        if (this.x) {
            this.p = false;
            this.q = false;
            this.r = true;
        }
        this.y = intent.getStringExtra("on_timer_tips");
        this.z = intent.getStringExtra("off_timer_tips");
        this.A = intent.getStringExtra(TIMER_TIPS_TIMER_LIST);
        if (!this.C || !CommonUtils.a(this.s)) {
            this.mTimerCommonManager = TimerCommonManager.i();
            this.mTimerCommonManager.a(this.s, this.h, this.j);
        } else {
            this.mTimerCommonManager = TimerCommonGroupManager.a();
            this.mTimerCommonManager.a(this.s, this.h, this.j);
        }
        EditController.a().a((EditModeChangedListener) this);
        this.mTimerCommonManager.a((TimerCommonManager.PlugSceneListener) this);
        this.mTimerCommonManager.b(this.i);
        a();
        d();
        e();
        this.t = findViewById(R.id.empty_text);
        this.u = findViewById(R.id.empty_icon);
        this.v = findViewById(R.id.rl_timer_list_view);
        this.t.setVisibility(4);
        this.u.setVisibility(4);
        this.v.setVisibility(0);
        this.emptyAddTimer = findViewById(R.id.add_timer_id);
        this.emptyAddTimer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!CommonTimerListActivity.this.s.isOwner()) {
                    ToastUtil.a((int) R.string.gateway_user_cant_not_access);
                } else {
                    CommonTimerListActivity.this.a((CommonTimer) null);
                }
            }
        });
    }

    public void onResume() {
        super.onResume();
        this.mTimerCommonManager.a((TimerCommonManager.PlugSceneListener) this);
        this.mTimerCommonManager.b(this.i);
    }

    public void onPause() {
        super.onPause();
        this.mTimerCommonManager.b((TimerCommonManager.PlugSceneListener) this);
    }

    public void onDestroy() {
        this.mHandler.removeCallbacksAndMessages((Object) null);
        if (this.G != null) {
            this.G.b();
            this.G = null;
        }
        if (this.D != null) {
            this.D.setItemAnimator((RecyclerView.ItemAnimator) null);
            this.D.setAdapter((RecyclerView.Adapter) null);
            this.D = null;
        }
        if (this.F != null) {
            WrapperAdapterUtils.a(this.F);
            this.F = null;
        }
        this.E = null;
        super.onDestroy();
    }

    private void a() {
        this.w = new XQProgressDialog(this);
        this.w.setMessage(getString(R.string.gateway_magnet_location_updating));
        this.w.setCancelable(true);
    }

    /* access modifiers changed from: private */
    public void a(CommonTimer commonTimer) {
        Intent intent = new Intent(this, SetTimerCommonActivity.class);
        if (this.mTimerCommonManager instanceof TimerCommonGroupManager) {
            intent.putExtra("is_group", true);
        }
        intent.putExtra(TimerCommonManager.f, false);
        intent.putExtra(TimerCommonManager.h, this.i);
        intent.putExtra("on_timer_tips", this.y);
        intent.putExtra("off_timer_tips", this.z);
        if (commonTimer == null) {
            intent.putExtra(TimerCommonManager.f, true);
            commonTimer = new CommonTimer();
            commonTimer.b = this.h;
            if (commonTimer.b == null) {
                commonTimer.b = "";
            }
            commonTimer.f = this.k;
            if (commonTimer.f == null) {
                commonTimer.f = "";
            }
            commonTimer.g = this.l;
            if (commonTimer.g == null) {
                commonTimer.g = "";
            }
            commonTimer.k = this.n;
            if (commonTimer.k == null) {
                commonTimer.k = "";
            }
            commonTimer.j = this.m;
            if (commonTimer.j == null) {
                commonTimer.j = "";
            }
        }
        intent.putExtra(TimerCommonManager.e, commonTimer);
        ArrayList arrayList = new ArrayList();
        HashMap hashMap = new HashMap();
        hashMap.put(getString(R.string.plug_timer_type_period), 0);
        hashMap.put(getString(R.string.plug_timer_type_on), 1);
        hashMap.put(getString(R.string.plug_timer_type_off), 2);
        if (this.r) {
            arrayList.add(getString(R.string.plug_timer_type_period));
        }
        if (this.p) {
            arrayList.add(getString(R.string.plug_timer_type_on));
        }
        if (this.q) {
            arrayList.add(getString(R.string.plug_timer_type_off));
        }
        if (arrayList.isEmpty() || arrayList.size() != 1) {
            MultipleChoiceDialogHelper.a(getContext(), new MLAlertDialog.Builder(getContext()).a((ListAdapter) new SingleChoiceAdapter(arrayList, true), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(arrayList, hashMap, intent) {
                private final /* synthetic */ List f$1;
                private final /* synthetic */ Map f$2;
                private final /* synthetic */ Intent f$3;

                {
                    this.f$1 = r2;
                    this.f$2 = r3;
                    this.f$3 = r4;
                }

                public final void onClick(DialogInterface dialogInterface, int i) {
                    CommonTimerListActivity.this.a(this.f$1, this.f$2, this.f$3, dialogInterface, i);
                }
            }).d());
            return;
        }
        switch (((Integer) hashMap.get(arrayList.get(0))).intValue()) {
            case 0:
                intent.putExtra(TimerCommonManager.r, getString(R.string.plug_timer_type_period));
                intent.putExtra("both_timer_must_be_set", true);
                intent.putExtra(TimerCommonManager.w, 0);
                break;
            case 1:
                intent.putExtra(TimerCommonManager.r, getString(R.string.plug_timer_type_on));
                intent.putExtra("both_timer_must_be_set", false);
                intent.putExtra(TimerCommonManager.t, true);
                intent.putExtra(TimerCommonManager.w, 1);
                break;
            case 2:
                intent.putExtra(TimerCommonManager.r, getString(R.string.plug_timer_type_off));
                intent.putExtra("both_timer_must_be_set", false);
                intent.putExtra(TimerCommonManager.u, true);
                intent.putExtra(TimerCommonManager.w, 2);
                break;
        }
        startActivity(intent);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list, Map map, Intent intent, DialogInterface dialogInterface, int i2) {
        if (i2 < list.size()) {
            switch (((Integer) map.get(list.get(i2))).intValue()) {
                case 0:
                    intent.putExtra(TimerCommonManager.r, getString(R.string.plug_timer_type_period));
                    intent.putExtra("both_timer_must_be_set", true);
                    intent.putExtra(TimerCommonManager.w, 0);
                    break;
                case 1:
                    intent.putExtra(TimerCommonManager.r, getString(R.string.plug_timer_type_on));
                    intent.putExtra("both_timer_must_be_set", false);
                    intent.putExtra(TimerCommonManager.t, true);
                    intent.putExtra(TimerCommonManager.w, 1);
                    break;
                case 2:
                    intent.putExtra(TimerCommonManager.r, getString(R.string.plug_timer_type_off));
                    intent.putExtra("both_timer_must_be_set", false);
                    intent.putExtra(TimerCommonManager.u, true);
                    intent.putExtra(TimerCommonManager.w, 2);
                    break;
                default:
                    dialogInterface.cancel();
                    return;
            }
            startActivity(intent);
        }
        dialogInterface.cancel();
    }

    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || EditController.a().c != 0) {
            return super.onKeyDown(i2, keyEvent);
        }
        EditController.a().a(1);
        return false;
    }

    public void onBrowseMode() {
        b();
        this.H.notifyDataSetChanged();
        this.mTopManageBar.setVisibility(8);
        this.mBottomDeleteBar.setVisibility(8);
        this.emptyAddTimer.setVisibility(0);
        this.mPullRefresh.setEnabled(true);
    }

    public void onManageMode() {
        this.H.notifyDataSetChanged();
        this.mTopManageBar.setVisibility(0);
        this.mBottomDeleteBar.setVisibility(0);
        this.emptyAddTimer.setVisibility(8);
        this.mTopManageBar.measure(0, 0);
        this.mBottomDeleteBar.measure(0, 0);
        this.mPullRefresh.setEnabled(false);
        getWindow().getDecorView().getWindowVisibleDisplayFrame(new Rect());
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mTopManageBar, View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.mBottomDeleteBar.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mBottomDeleteBar, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.mBottomDeleteBar.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    private void b() {
        EditController.a().d.clear();
        this.mIvSelectAll.setImageResource(R.drawable.all_select_selector);
        this.H.notifyDataSetChanged();
    }

    private void c() {
        int a2 = this.H.a(0);
        EditController.a().d.clear();
        for (int i2 = 0; i2 < a2; i2++) {
            EditController.a().d.put(i2, true);
        }
        this.mIvSelectAll.setImageResource(R.drawable.un_select_selector);
        this.H.notifyDataSetChanged();
        this.mSelectCountText.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, EditController.a().d.size(), new Object[]{Integer.valueOf(EditController.a().d.size())}));
    }

    private class SingleChoiceAdapter extends BaseAdapter {
        private List<String> b = new ArrayList();
        private boolean c;

        public long getItemId(int i) {
            return (long) i;
        }

        public SingleChoiceAdapter(List<String> list, boolean z) {
            this.c = z;
            a(list, z);
        }

        public void a(List<String> list, boolean z) {
            this.b.clear();
            this.b.addAll(list);
            if (z) {
                this.b.add(CommonTimerListActivity.this.getString(R.string.cancel));
            }
        }

        public int getCount() {
            if (this.b == null) {
                return 0;
            }
            return this.b.size();
        }

        public Object getItem(int i) {
            if (this.b == null) {
                return null;
            }
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(CommonTimerListActivity.this).inflate(R.layout.dialog_single_choice_item_center, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.f21653a = (TextView) view.findViewById(R.id.text1);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.f21653a.setText(this.b.get(i));
            if (this.c) {
                if (i == getCount() - 1) {
                    viewHolder.f21653a.setTextColor(CommonTimerListActivity.this.getResources().getColor(R.color.class_Y));
                    viewHolder.f21653a.setTextSize(14.0f);
                } else {
                    viewHolder.f21653a.setTextColor(CommonTimerListActivity.this.getResources().getColor(R.color.black));
                    viewHolder.f21653a.setTextSize(15.0f);
                }
            }
            return view;
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f21653a;

            private ViewHolder() {
            }
        }
    }

    private void d() {
        View findViewById = findViewById(R.id.module_a_3_return_btn);
        if (findViewById != null) {
            findViewById.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    CommonTimerListActivity.this.finish();
                }
            });
        }
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (textView != null) {
            if (!TextUtils.isEmpty(this.o)) {
                textView.setText(this.o);
            } else {
                textView.setText(R.string.timer_title);
            }
        }
        this.mTopManageBar = findViewById(R.id.top_delete_bar);
        if (TitleBarUtil.f11582a) {
            this.mTopManageBar.getLayoutParams().height += TitleBarUtil.b();
            this.mTopManageBar.setPadding(0, this.mTopManageBar.getPaddingTop() + TitleBarUtil.b(), 0, 0);
            this.mTopManageBar.setLayoutParams(this.mTopManageBar.getLayoutParams());
        }
        this.mSelectCountText = (TextView) findViewById(R.id.selected_cnt);
        this.mCancelBtn = findViewById(R.id.cancel_btn);
        this.mCancelBtn.setOnClickListener($$Lambda$CommonTimerListActivity$RmYRH6FaNsXs0Ll7Io3aE0yNWqY.INSTANCE);
        this.mIvSelectAll = (ImageView) findViewById(R.id.select_all_btn);
        this.mIvSelectAll.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CommonTimerListActivity.this.b(view);
            }
        });
        this.mBottomDeleteBar = findViewById(R.id.bottom_delete_bar);
        this.mBottomDeleteBtn = findViewById(R.id.delete_msg_btn);
        this.mBottomDeleteBar.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                CommonTimerListActivity.this.a(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        if (EditController.a().d.size() == this.H.a(0)) {
            b();
        } else {
            c();
        }
        this.mSelectCountText.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, EditController.a().d.size(), new Object[]{Integer.valueOf(EditController.a().d.size())}));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (EditController.a().d.size() > 0) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < this.mTimerCommonManager.b().size(); i2++) {
                if (EditController.a().d.get(i2)) {
                    arrayList.add(this.mTimerCommonManager.b().get(i2));
                }
            }
            a((List<CommonTimer>) arrayList);
            return;
        }
        ToastUtil.a((int) R.string.no_one_selected);
    }

    private void e() {
        this.I = new PtrIndicator();
        this.mPullRefresh = (DevicePtrFrameLayout) findViewById(R.id.pull_down_refresh);
        this.mPullRefresh.setPtrHandler(new PtrDefaultHandler() {
            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                CommonTimerListActivity.this.isPullRefresh = true;
                CommonTimerListActivity.this.mTimerCommonManager.b(CommonTimerListActivity.this.i);
            }

            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (Build.VERSION.SDK_INT < 14) {
                    if (view instanceof AbsListView) {
                        AbsListView absListView = (AbsListView) view;
                        if (absListView.getChildCount() <= 0) {
                            return false;
                        }
                        if (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0).getTop() < absListView.getPaddingTop()) {
                            return true;
                        }
                        return false;
                    } else if (view instanceof RecyclerView) {
                        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) CommonTimerListActivity.this.E;
                        List<CommonTimer> b = CommonTimerListActivity.this.mTimerCommonManager.b();
                        if (b == null || b.size() == 0) {
                            if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() - 1 < 0) {
                                return true;
                            }
                            return false;
                        } else if (linearLayoutManager.findFirstCompletelyVisibleItemPosition() - 1 <= 0) {
                            return true;
                        } else {
                            return false;
                        }
                    } else if (view.getScrollY() > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (!(view instanceof RecyclerView)) {
                    return view.canScrollVertically(-1);
                } else {
                    LinearLayoutManager linearLayoutManager2 = (LinearLayoutManager) CommonTimerListActivity.this.E;
                    List<CommonTimer> b2 = CommonTimerListActivity.this.mTimerCommonManager.b();
                    if (b2 == null || b2.size() == 0) {
                        if (linearLayoutManager2.findFirstCompletelyVisibleItemPosition() - 1 < 0) {
                            return true;
                        }
                        return false;
                    } else if (linearLayoutManager2.findFirstCompletelyVisibleItemPosition() - 1 <= 0) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        });
        this.mPullRefresh.disableWhenHorizontalMove(true);
        this.mPullRefresh.setEnabled(true);
        this.mPullRefresh.setPtrIndicator(this.I);
        this.mPullRefresh.addPtrUIHandler(new PtrUIHandler() {
            public void onUIPositionChange(PtrFrameLayout ptrFrameLayout, boolean z, byte b, PtrIndicator ptrIndicator) {
            }

            public void onUIRefreshBegin(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshComplete(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIRefreshPrepare(PtrFrameLayout ptrFrameLayout) {
            }

            public void onUIReset(PtrFrameLayout ptrFrameLayout) {
            }
        });
        this.D = (RecyclerView) findViewById(R.id.device_grid_view);
        this.D.setOverScrollMode(2);
        this.E = new LinearLayoutManager(getContext());
        this.G = new RecyclerViewExpandableItemManager((Parcelable) null);
        this.H = new RecyclerTimerAdapter();
        this.F = this.G.a((RecyclerView.Adapter) this.H);
        RefactoredDefaultItemAnimator refactoredDefaultItemAnimator = new RefactoredDefaultItemAnimator();
        refactoredDefaultItemAnimator.setSupportsChangeAnimations(false);
        this.D.setLayoutManager(this.E);
        this.D.setAdapter(this.F);
        this.D.setItemAnimator(refactoredDefaultItemAnimator);
        this.D.setHasFixedSize(false);
        this.D.setOverScrollMode(2);
        this.G.a(this.D);
        this.G.d();
    }

    private class RecyclerTimerAdapter extends AbstractExpandableItemAdapter<GroupViewHolder, ChildViewHolder> {
        public boolean a(GroupViewHolder groupViewHolder, int i, int i2, int i3, boolean z) {
            return false;
        }

        public long b(int i) {
            return (long) i;
        }

        public long c(int i, int i2) {
            return (long) i2;
        }

        public RecyclerTimerAdapter() {
            setHasStableIds(true);
        }

        public int a() {
            List<List<CommonTimer>> d = CommonTimerListActivity.this.mTimerCommonManager.d();
            List<CommonTimer> b = CommonTimerListActivity.this.mTimerCommonManager.b();
            int i = 1;
            if (d != null) {
                int size = d.size();
                if (b == null || b.size() == 0) {
                    i = 0;
                }
                return size + i;
            } else if (b == null || b.size() == 0) {
                return 0;
            } else {
                return 1;
            }
        }

        public int a(int i) {
            List list;
            List<List<CommonTimer>> d = CommonTimerListActivity.this.mTimerCommonManager.d();
            List<CommonTimer> b = CommonTimerListActivity.this.mTimerCommonManager.b();
            if (i == 0 && b != null && b.size() > 0) {
                return b.size();
            }
            if (d == null) {
                return 0;
            }
            if (b != null && b.size() > 0) {
                i--;
            }
            if (i < 0 || i >= d.size() || (list = d.get(i)) == null) {
                return 0;
            }
            return list.size();
        }

        public int c(int i) {
            CommonTimerListActivity.this.mTimerCommonManager.d();
            List<CommonTimer> b = CommonTimerListActivity.this.mTimerCommonManager.b();
            if (b == null || b.size() == 0 || i != 0) {
                return 0;
            }
            return 1;
        }

        /* renamed from: c */
        public GroupViewHolder a(ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.timer_list_header_layout, viewGroup, false);
            if (i == 1) {
                inflate.getLayoutParams().height = 0;
            }
            return new GroupViewHolder(inflate);
        }

        /* renamed from: d */
        public ChildViewHolder b(ViewGroup viewGroup, int i) {
            return new ChildViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.plug_timer_item, viewGroup, false));
        }

        /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
            r3 = (com.xiaomi.smarthome.scene.timer.CommonTimer) r3.get(0);
         */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b(com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.RecyclerTimerAdapter.GroupViewHolder r2, int r3, int r4) {
            /*
                r1 = this;
                r0 = 1
                if (r4 != r0) goto L_0x0004
                return
            L_0x0004:
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r4 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                com.xiaomi.smarthome.scene.timer.TimerCommonManager r4 = r4.mTimerCommonManager
                java.util.List r4 = r4.d()
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r0 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                com.xiaomi.smarthome.scene.timer.TimerCommonManager r0 = r0.mTimerCommonManager
                java.util.List r0 = r0.b()
                if (r4 != 0) goto L_0x0017
                return
            L_0x0017:
                if (r0 == 0) goto L_0x0021
                int r0 = r0.size()
                if (r0 <= 0) goto L_0x0021
                int r3 = r3 + -1
            L_0x0021:
                if (r3 < 0) goto L_0x006e
                int r0 = r4.size()
                if (r0 > r3) goto L_0x002a
                goto L_0x006e
            L_0x002a:
                java.lang.Object r3 = r4.get(r3)
                java.util.List r3 = (java.util.List) r3
                if (r3 == 0) goto L_0x006d
                int r4 = r3.size()
                if (r4 != 0) goto L_0x0039
                goto L_0x006d
            L_0x0039:
                r4 = 0
                java.lang.Object r3 = r3.get(r4)
                com.xiaomi.smarthome.scene.timer.CommonTimer r3 = (com.xiaomi.smarthome.scene.timer.CommonTimer) r3
                if (r3 != 0) goto L_0x0043
                return
            L_0x0043:
                com.xiaomi.smarthome.device.SmartHomeDeviceManager r4 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
                java.lang.String r0 = r3.b
                com.xiaomi.smarthome.device.Device r4 = r4.b((java.lang.String) r0)
                if (r4 != 0) goto L_0x0052
                java.lang.String r4 = r3.b
                goto L_0x0056
            L_0x0052:
                java.lang.CharSequence r4 = r4.getName()
            L_0x0056:
                android.widget.TextView r0 = r2.f21651a
                r0.setText(r4)
                android.view.View r2 = r2.itemView
                r4 = 2131432521(0x7f0b1449, float:1.8486802E38)
                android.view.View r2 = r2.findViewById(r4)
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$1 r4 = new com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$1
                r4.<init>(r3)
                r2.setOnClickListener(r4)
                return
            L_0x006d:
                return
            L_0x006e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.RecyclerTimerAdapter.b(com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$GroupViewHolder, int, int):void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:20:0x0056, code lost:
            r12 = r2.get(r8);
         */
        /* JADX WARNING: Removed duplicated region for block: B:22:0x005f A[RETURN] */
        /* JADX WARNING: Removed duplicated region for block: B:23:0x0060  */
        /* renamed from: a */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void b(com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.RecyclerTimerAdapter.ChildViewHolder r23, int r24, int r25, int r26) {
            /*
                r22 = this;
                r6 = r22
                r7 = r23
                r0 = r24
                r8 = r25
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r1 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                com.xiaomi.smarthome.scene.timer.TimerCommonManager r1 = r1.mTimerCommonManager
                java.util.List r1 = r1.d()
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r2 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                com.xiaomi.smarthome.scene.timer.TimerCommonManager r2 = r2.mTimerCommonManager
                java.util.List r2 = r2.b()
                r9 = 1
                r10 = 0
                if (r0 != 0) goto L_0x0032
                if (r2 == 0) goto L_0x0026
                int r3 = r2.size()
                if (r3 <= 0) goto L_0x0026
                r11 = 0
                goto L_0x004f
            L_0x0026:
                if (r1 != 0) goto L_0x0029
                return
            L_0x0029:
                java.lang.Object r1 = r1.get(r0)
                r2 = r1
                java.util.List r2 = (java.util.List) r2
            L_0x0030:
                r11 = 1
                goto L_0x004f
            L_0x0032:
                if (r1 != 0) goto L_0x0035
                return
            L_0x0035:
                if (r2 == 0) goto L_0x0047
                int r2 = r2.size()
                if (r2 <= 0) goto L_0x0047
                int r2 = r0 + -1
                java.lang.Object r1 = r1.get(r2)
                r2 = r1
                java.util.List r2 = (java.util.List) r2
                goto L_0x0030
            L_0x0047:
                java.lang.Object r1 = r1.get(r0)
                r2 = r1
                java.util.List r2 = (java.util.List) r2
                goto L_0x0030
            L_0x004f:
                int r1 = r2.size()
                if (r1 > r8) goto L_0x0056
                return
            L_0x0056:
                java.lang.Object r1 = r2.get(r8)
                r12 = r1
                com.xiaomi.smarthome.scene.timer.CommonTimer r12 = (com.xiaomi.smarthome.scene.timer.CommonTimer) r12
                if (r12 != 0) goto L_0x0060
                return
            L_0x0060:
                android.view.View r1 = r7.itemView
                r13 = 0
                r1.setOnClickListener(r13)
                android.widget.TextView r1 = r7.f21650a
                android.widget.TextView r2 = r7.b
                boolean r3 = r12.e
                if (r3 == 0) goto L_0x00e6
                boolean r3 = r12.i
                if (r3 == 0) goto L_0x00e6
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r3 = r3.A
                boolean r3 = android.text.TextUtils.isEmpty(r3)
                if (r3 != 0) goto L_0x0088
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r3 = r3.A
                r1.setText(r3)
                goto L_0x008e
            L_0x0088:
                r3 = 2131497799(0x7f0c1347, float:1.8619201E38)
                r1.setText(r3)
            L_0x008e:
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                r1.<init>()
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                android.content.Context r14 = r3.getContext()
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r15 = r12.h
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r3 = r12.l
                r17 = 1
                r18 = 1
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r4 = r12.h
                int r4 = r4.c
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r5 = r12.h
                int r5 = r5.b
                r21 = 1
                r16 = r3
                r19 = r4
                r20 = r5
                java.lang.String r3 = com.xiaomi.smarthome.scene.timer.PlugTimer.a(r14, r15, r16, r17, r18, r19, r20, r21)
                r1.append(r3)
                java.lang.String r3 = "-"
                r1.append(r3)
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                android.content.Context r14 = r3.getContext()
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r15 = r12.h
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r3 = r12.l
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r4 = r12.l
                int r4 = r4.c
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r5 = r12.l
                int r5 = r5.b
                r21 = 0
                r16 = r3
                r19 = r4
                r20 = r5
                java.lang.String r3 = com.xiaomi.smarthome.scene.timer.PlugTimer.a(r14, r15, r16, r17, r18, r19, r20, r21)
                r1.append(r3)
                java.lang.String r1 = r1.toString()
                r2.setText(r1)
                goto L_0x0145
            L_0x00e6:
                boolean r3 = r12.e
                if (r3 == 0) goto L_0x0116
                boolean r3 = r12.i
                if (r3 != 0) goto L_0x0116
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r3 = r3.y
                boolean r3 = android.text.TextUtils.isEmpty(r3)
                if (r3 != 0) goto L_0x0104
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r3 = r3.y
                r1.setText(r3)
                goto L_0x010a
            L_0x0104:
                r3 = 2131497800(0x7f0c1348, float:1.8619203E38)
                r1.setText(r3)
            L_0x010a:
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r1 = r12.h
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r1 = r1.a((android.content.Context) r3)
                r2.setText(r1)
                goto L_0x0145
            L_0x0116:
                boolean r3 = r12.e
                if (r3 != 0) goto L_0x0145
                boolean r3 = r12.i
                if (r3 == 0) goto L_0x0145
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r3 = r3.z
                boolean r3 = android.text.TextUtils.isEmpty(r3)
                if (r3 != 0) goto L_0x0134
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r3 = r3.z
                r1.setText(r3)
                goto L_0x013a
            L_0x0134:
                r3 = 2131497796(0x7f0c1344, float:1.8619195E38)
                r1.setText(r3)
            L_0x013a:
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r1 = r12.l
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r1 = r1.a((android.content.Context) r3)
                r2.setText(r1)
            L_0x0145:
                android.widget.TextView r1 = r7.c
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                r2.<init>()
                java.lang.String r3 = " |   "
                r2.append(r3)
                boolean r3 = r12.e
                if (r3 == 0) goto L_0x015f
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r3 = r12.h
                java.lang.String r3 = r6.a((com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam) r3)
                r2.append(r3)
                goto L_0x0168
            L_0x015f:
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r3 = r12.l
                java.lang.String r3 = r6.a((com.xiaomi.smarthome.library.common.util.CorntabUtils.CorntabParam) r3)
                r2.append(r3)
            L_0x0168:
                boolean r3 = r12.d
                r4 = 2131497795(0x7f0c1343, float:1.8619193E38)
                r5 = 2131497798(0x7f0c1346, float:1.86192E38)
                if (r3 == 0) goto L_0x0195
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r3 = r12.h
                int r3 = r3.d()
                if (r3 != 0) goto L_0x01b7
                java.lang.String r3 = " |   "
                r2.append(r3)
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r14 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r5 = r14.getString(r5)
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r14 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r4 = r14.getString(r4)
                java.lang.String r3 = r12.a(r3, r5, r4)
                r2.append(r3)
                goto L_0x01b7
            L_0x0195:
                com.xiaomi.smarthome.library.common.util.CorntabUtils$CorntabParam r3 = r12.h
                int r3 = r3.d()
                if (r3 != 0) goto L_0x01b7
                java.lang.String r3 = " |   "
                r2.append(r3)
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r3 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r14 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r5 = r14.getString(r5)
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity r14 = com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.this
                java.lang.String r4 = r14.getString(r4)
                java.lang.String r3 = r12.a(r3, r5, r4)
                r2.append(r3)
            L_0x01b7:
                java.lang.String r2 = r2.toString()
                r1.setText(r2)
                com.xiaomi.smarthome.library.common.widget.SwitchButton r14 = r7.d
                android.view.View r15 = r7.f
                r14.setOnCheckedChangeListener(r13)
                boolean r1 = r12.d
                r14.setChecked(r1)
                int r4 = r23.getAdapterPosition()
                int r1 = r22.a()
                int r1 = r1 - r9
                if (r0 != r1) goto L_0x01ec
                int r0 = r6.a((int) r0)
                int r0 = r0 - r9
                if (r8 != r0) goto L_0x01ec
                android.view.View r0 = r7.h
                android.view.ViewGroup$LayoutParams r0 = r0.getLayoutParams()
                android.widget.RelativeLayout$LayoutParams r0 = (android.widget.RelativeLayout.LayoutParams) r0
                r0.setMargins(r10, r10, r10, r10)
                android.view.View r1 = r7.h
                r1.setLayoutParams(r0)
            L_0x01ec:
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$2 r5 = new com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$2
                r0 = r5
                r1 = r22
                r2 = r12
                r3 = r23
                r9 = r5
                r5 = r14
                r0.<init>(r2, r3, r4, r5)
                r14.setOnCheckedChangeListener(r9)
                r0 = 8
                if (r11 == 0) goto L_0x021d
                r14.setVisibility(r0)
                android.widget.CheckBox r1 = r7.e
                r1.setVisibility(r0)
                android.view.View r1 = r7.g
                r1.setVisibility(r0)
                android.view.View r1 = r7.itemView
                r1.setClickable(r10)
                android.view.View r1 = r7.itemView
                r1.setOnLongClickListener(r13)
                android.view.View r1 = r7.itemView
                r1.setBackgroundColor(r10)
                goto L_0x0280
            L_0x021d:
                android.view.View r1 = r7.itemView
                r2 = 1
                r1.setClickable(r2)
                android.view.View r1 = r7.itemView
                r1.setTag(r7)
                android.view.View r1 = r7.itemView
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$3 r2 = new com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$3
                r2.<init>(r8, r12)
                r1.setOnClickListener(r2)
                com.xiaomi.smarthome.miniprogram.EditController r1 = com.xiaomi.smarthome.miniprogram.EditController.a()
                int r1 = r1.c
                if (r1 != 0) goto L_0x0240
                android.view.View r1 = r7.itemView
                r1.setOnLongClickListener(r13)
                goto L_0x024a
            L_0x0240:
                android.view.View r1 = r7.itemView
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$4 r2 = new com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$4
                r2.<init>()
                r1.setOnLongClickListener(r2)
            L_0x024a:
                com.xiaomi.smarthome.miniprogram.EditController r1 = com.xiaomi.smarthome.miniprogram.EditController.a()
                int r1 = r1.c
                if (r1 != 0) goto L_0x025b
                r14.setVisibility(r0)
                android.widget.CheckBox r1 = r7.e
                r1.setVisibility(r10)
                goto L_0x0263
            L_0x025b:
                r14.setVisibility(r10)
                android.widget.CheckBox r1 = r7.e
                r1.setVisibility(r0)
            L_0x0263:
                com.xiaomi.smarthome.miniprogram.EditController r1 = com.xiaomi.smarthome.miniprogram.EditController.a()
                android.util.SparseBooleanArray r1 = r1.d
                boolean r1 = r1.get(r8)
                if (r1 == 0) goto L_0x0276
                android.widget.CheckBox r1 = r7.e
                r2 = 1
                r1.setChecked(r2)
                goto L_0x027b
            L_0x0276:
                android.widget.CheckBox r1 = r7.e
                r1.setChecked(r10)
            L_0x027b:
                android.view.View r1 = r7.g
                r1.setVisibility(r0)
            L_0x0280:
                com.xiaomi.smarthome.miniprogram.EditController r1 = com.xiaomi.smarthome.miniprogram.EditController.a()
                int r1 = r1.c
                if (r1 != 0) goto L_0x0294
                r14.setVisibility(r0)
                r15.setVisibility(r0)
                android.widget.CheckBox r0 = r7.e
                r0.setVisibility(r10)
                goto L_0x02ad
            L_0x0294:
                int r1 = r12.q
                if (r1 != 0) goto L_0x029f
                r14.setVisibility(r10)
                r15.setVisibility(r0)
                goto L_0x02ad
            L_0x029f:
                r14.setVisibility(r0)
                r15.setVisibility(r10)
                com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$5 r0 = new com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$5
                r0.<init>(r12)
                r15.setOnClickListener(r0)
            L_0x02ad:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scene.timer.CommonTimerListActivity.RecyclerTimerAdapter.b(com.xiaomi.smarthome.scene.timer.CommonTimerListActivity$RecyclerTimerAdapter$ChildViewHolder, int, int, int):void");
        }

        /* access modifiers changed from: package-private */
        public String a(CorntabUtils.CorntabParam corntabParam) {
            if (corntabParam.d() == 0) {
                return CommonTimerListActivity.this.getString(R.string.plug_timer_onetime);
            }
            return corntabParam.c((Context) CommonTimerListActivity.this);
        }

        public class GroupViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f21651a;

            public GroupViewHolder(View view) {
                super(view);
                this.f21651a = (TextView) view.findViewById(R.id.txt_group_title);
            }
        }

        public class ChildViewHolder extends RecyclerView.ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f21650a;
            TextView b;
            TextView c;
            SwitchButton d;
            CheckBox e;
            View f;
            View g;
            View h;

            public ChildViewHolder(View view) {
                super(view);
                this.f21650a = (TextView) view.findViewById(R.id.tv_on);
                this.b = (TextView) view.findViewById(R.id.tv_on_time);
                this.c = (TextView) view.findViewById(R.id.tv_repeat);
                this.d = (SwitchButton) view.findViewById(R.id.sb_timer);
                this.f = view.findViewById(R.id.err_btn);
                this.g = view.findViewById(R.id.group_indicator);
                this.h = view.findViewById(R.id.divider);
                this.e = (CheckBox) view.findViewById(R.id.ckb_edit_selected);
            }
        }
    }

    private void a(List<CommonTimer> list) {
        new MLAlertDialog.Builder(this).a((int) R.string.plug_timer_del).b((int) R.string.plug_timer_del_hint).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.plug_timer_delete, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(list) {
            private final /* synthetic */ List f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                CommonTimerListActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list, DialogInterface dialogInterface, int i2) {
        this.w.show();
        this.mTimerCommonManager.b((List<CommonTimer>) list, (TimerCommonManager.PlugSceneListener) new TimerCommonManager.PlugSceneListener() {
            public void onGetSceneFailed(int i) {
            }

            public void onGetSceneSuccess() {
            }

            public void onSetSceneSuccess(CommonTimer commonTimer) {
                EditController.a().a(1);
                CommonTimerListActivity.this.mPullRefresh.autoRefresh();
            }

            public void onSetSceneFailed(Error error) {
                if (!NetworkManager.b()) {
                    ToastUtil.a((CharSequence) CommonTimerListActivity.this.getString(R.string.phone_wifi_error));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(final CommonTimer commonTimer) {
        DialogInterface.OnClickListener onClickListener;
        MLAlertDialog.Builder b2 = new MLAlertDialog.Builder(this).b((int) R.string.i_know, (DialogInterface.OnClickListener) null);
        if (commonTimer.q == -1) {
            b2.b((int) R.string.set_timer_fail_delete);
            onClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CommonTimerListActivity.this.w.show();
                    CommonTimerListActivity.this.mTimerCommonManager.c(commonTimer, (TimerCommonManager.PlugSceneListener) null);
                }
            };
        } else if (commonTimer.q == 1) {
            b2.b((int) R.string.set_timer_fail_disable);
            onClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    CommonTimerListActivity.this.w.show();
                    CommonTimerListActivity.this.mTimerCommonManager.a(commonTimer, false, (TimerCommonManager.PlugSceneListener) new TimerCommonManager.PlugSceneListener() {
                        public void onGetSceneFailed(int i) {
                        }

                        public void onGetSceneSuccess() {
                        }

                        public void onSetSceneFailed(Error error) {
                        }

                        public void onSetSceneSuccess(CommonTimer commonTimer) {
                            CommonTimerListActivity.this.F.notifyDataSetChanged();
                        }
                    }, CommonTimerListActivity.this.i);
                }
            };
        } else {
            b2.b((int) R.string.set_timer_fail_edit);
            onClickListener = new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(CommonTimerListActivity.this, SetTimerCommonActivity.class);
                    if (CommonTimerListActivity.this.mTimerCommonManager instanceof TimerCommonGroupManager) {
                        intent.putExtra("is_group", true);
                    }
                    intent.putExtra(TimerCommonManager.e, commonTimer);
                    intent.putExtra(TimerCommonManager.r, CommonTimerListActivity.this.B);
                    intent.putExtra(TimerCommonManager.h, CommonTimerListActivity.this.i);
                    intent.putExtra("both_timer_must_be_set", CommonTimerListActivity.this.x);
                    intent.putExtra("on_timer_tips", CommonTimerListActivity.this.y);
                    intent.putExtra("off_timer_tips", CommonTimerListActivity.this.z);
                    CommonTimerListActivity.this.startActivity(intent);
                }
            };
        }
        b2.a((int) R.string.retry, onClickListener);
        b2.d();
    }

    public void onGetSceneSuccess() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(1));
    }

    public void onGetSceneFailed(int i2) {
        Message obtainMessage = this.mHandler.obtainMessage(2);
        obtainMessage.arg1 = i2;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void onSetSceneSuccess(CommonTimer commonTimer) {
        Message obtainMessage = this.mHandler.obtainMessage(3);
        obtainMessage.obj = commonTimer;
        this.mHandler.sendMessage(obtainMessage);
    }

    public void onSetSceneFailed(Error error) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4));
    }
}
