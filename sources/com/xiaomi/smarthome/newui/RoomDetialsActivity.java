package com.xiaomi.smarthome.newui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.google.android.exoplayer2.C;
import com.xiaomi.infrared.adapter.BaseListAdapter;
import com.xiaomi.infrared.adapter.ViewHolder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.utils.BluetoothHelper;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.GridViewData;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.language.LanguageModel;
import com.xiaomi.smarthome.language.LanguageSupport;
import com.xiaomi.smarthome.library.DarkModeCompat;
import com.xiaomi.smarthome.miio.LanguageUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miui10.MIUI10CardActivity;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.widget.CommonBlurView;
import com.xiaomi.smarthome.newui.widget.DeviceMainPageEditBarV2;
import com.xiaomi.smarthome.newui.widget.FixedTextView;
import com.xiaomi.smarthome.newui.widget.MainPageAppBarLayout;
import com.xiaomi.smarthome.newui.widget.SimplePushToRefreshHeader;
import com.xiaomi.smarthome.service.tasks.GetDeviceTask;
import com.xiaomi.smarthome.service.tasks.LoginTask;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import miuipub.view.EditActionMode;

public class RoomDetialsActivity extends BaseActivity implements DeviceListPageActionInterface {
    public static final String REFRESH_LIST_VIEW = "com.smarthome.refresh_list_view";
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static final String f20355a = "RoomDetialsActivity";
    private CommonBlurView b;
    /* access modifiers changed from: private */
    public Room c;
    private RoomEnvAdapter d;
    /* access modifiers changed from: private */
    public RoomDetailsViewModel e;
    private Device f;
    private DeviceMainPageEditBarV2.OnMenuBarPositionChangeListener g = new DeviceMainPageEditBarV2.OnMenuBarPositionChangeListener() {
        private int b = -1;
        private int c = -1;

        public void a(int i) {
            String access$000 = RoomDetialsActivity.f20355a;
            LogUtil.a(access$000, "onPositionChanged " + i);
            CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) RoomDetialsActivity.this.mRecyclerView.getLayoutParams();
            if (this.b == -1) {
                this.b = layoutParams.bottomMargin;
            }
            if (this.c == -1) {
                this.c = RoomDetialsActivity.this.getResources().getDimensionPixelOffset(R.dimen.bottom_bar_height);
            }
            layoutParams.bottomMargin = i - this.c;
            if (layoutParams.bottomMargin <= this.b) {
                layoutParams.bottomMargin = this.b;
            }
            RoomDetialsActivity.this.mRecyclerView.setLayoutParams(layoutParams);
        }
    };
    private BroadcastReceiver h = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (RoomDetialsActivity.this.isValid()) {
                LogUtil.a(RoomDetialsActivity.f20355a, intent.getAction());
                if ("com.smarthome.refresh_list_view".equals(intent.getAction()) && RoomDetialsActivity.this.mRecyclerView != null && RoomDetialsActivity.this.mRecyclerView.getAdapter() != null) {
                    LogUtil.a(RoomDetialsActivity.f20355a, "com.smarthome.refresh_list_view");
                    if (RoomDetialsActivity.this.e.a(RoomDetialsActivity.this.c.d()).getValue() == null) {
                        RoomDetialsActivity.this.finish();
                    } else {
                        RoomDetialsActivity.this.e.b(RoomDetialsActivity.this.c.d());
                    }
                }
            }
        }
    };
    private boolean i;
    private long j = 0;
    private View k;
    private View l;
    private ImageView m;
    @BindView(2131430982)
    View mAddDeviceView;
    @BindView(2131430775)
    MainPageAppBarLayout mAppBarLayout;
    @BindView(2131430975)
    TextView mAppBarTitle;
    @BindView(2131433822)
    CoordinatorLayout mCoordinatorLayout;
    public DviceEditInterface mEditInterface;
    public volatile boolean mEditMode = false;
    @BindView(2131431155)
    View mNewDot;
    @BindView(2131431796)
    DeviceRecyclerOrigin mRecyclerView;
    @BindView(2131430740)
    ListView mRoomEnvList;
    @BindView(2131433521)
    TextView mTitle;
    @BindView(2131432919)
    View mToolbar;
    protected TextView mTxtEditTitle;
    private ImageView n;

    public Activity getActivity() {
        return this;
    }

    public static void startActivity(Context context, String str) {
        if (!TextUtils.isEmpty(str) && context != null) {
            Intent intent = new Intent(context, RoomDetialsActivity.class);
            intent.putExtra("room_id", str);
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
            context.startActivity(intent);
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.i = DarkModeCompat.a((Context) this);
        this.c = HomeManager.a().i(getIntent().getStringExtra("room_id"));
        if (this.c == null || this.c.h() == null) {
            finish();
            return;
        }
        setContentView(R.layout.activity_room_detials);
        ButterKnife.bind((Activity) this);
        a();
        b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.j = STAT.c.o(0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        STAT.c.o(this.j);
    }

    @OnClick({2131427766, 2131429625})
    public void onBackPressed() {
        if (this.mEditMode) {
            exitEditMode();
            return;
        }
        super.onBackPressed();
        STAT.d.aH();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        if (this.e != null) {
            this.e.a();
        }
        if (this.mRecyclerView != null) {
            this.mRecyclerView.destory();
        }
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.h);
        this.h = null;
        super.onDestroy();
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        if (i2 == DeviceMainPage.q && i3 == -1) {
            this.e.b(this.c.f(), this.c.d());
        } else if (i2 == 6) {
            a(this.f);
            e();
        }
    }

    private void a() {
        IntentFilter intentFilter = new IntentFilter(HomeManager.S);
        intentFilter.addAction("com.smarthome.refresh_list_view");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.h, intentFilter);
        this.d = new RoomEnvAdapter(this, new ArrayList());
        this.e = (RoomDetailsViewModel) ViewModelProviders.a((FragmentActivity) this).a(RoomDetailsViewModel.class);
        this.e.a(this.c.f(), this.c.d()).observe(this, new Observer() {
            public final void onChanged(Object obj) {
                RoomDetialsActivity.this.b((List) obj);
            }
        });
        this.e.a(this.c.d()).observe(this, new Observer() {
            public final void onChanged(Object obj) {
                RoomDetialsActivity.this.a((List) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(List list) {
        if (isValid()) {
            this.mAppBarLayout.refreshComplete(this.mCoordinatorLayout);
            this.d.a(list);
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(List list) {
        if (isValid()) {
            this.mAppBarLayout.refreshComplete(this.mCoordinatorLayout);
            if (list == null) {
                finish();
                return;
            }
            String e2 = this.c.e();
            this.c = HomeManager.a().i(this.c.d());
            if (!TextUtils.equals(e2, this.c.e())) {
                c();
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Device device = (Device) it.next();
                if (device != null) {
                    arrayList.add(device);
                }
            }
            this.mRecyclerView.changeToGridOnlyMode(arrayList, 4, this.c.d(), this, this.c.e());
        }
    }

    private void b() {
        c();
        TitleBarUtil.b((Activity) this);
        this.mAppBarLayout.setOnRefreshListener(new SimplePushToRefreshHeader.OnRefreshListener() {
            public void a() {
                RoomDetialsActivity.this.e.b(RoomDetialsActivity.this.c.f(), RoomDetialsActivity.this.c.d());
            }
        });
        this.mAppBarLayout.addOnOffsetChangedListener((AppBarLayout.OnOffsetChangedListener) new AppBarLayout.OnOffsetChangedListener() {

            /* renamed from: a  reason: collision with root package name */
            Interpolator f20359a = new DecelerateInterpolator(2.0f);

            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                float abs = Math.abs(((float) i) / ((float) appBarLayout.getTotalScrollRange()));
                int i2 = 0;
                if (((double) abs) < 0.64d) {
                    RoomDetialsActivity.this.mTitle.setVisibility(8);
                } else {
                    RoomDetialsActivity.this.mTitle.setVisibility(0);
                }
                View view = RoomDetialsActivity.this.mToolbar;
                if (abs == 0.0f) {
                    i2 = 8;
                }
                view.setVisibility(i2);
                RoomDetialsActivity.this.mToolbar.setAlpha(this.f20359a.getInterpolation(abs));
                RoomDetialsActivity.this.mAddDeviceView.setVisibility(8);
                RoomDetialsActivity.this.mNewDot.setVisibility(8);
            }
        });
        this.mRoomEnvList.setAdapter(this.d);
        this.mAppBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (RoomDetialsActivity.this.mAppBarLayout.getHeight() == 0) {
                    CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) RoomDetialsActivity.this.mAppBarLayout.getLayoutParams();
                    layoutParams.height = (int) RoomDetialsActivity.this.getResources().getDimension(R.dimen.room_app_bar_layout_height);
                    RoomDetialsActivity.this.mAppBarLayout.setLayoutParams(layoutParams);
                    RoomDetialsActivity.this.mAppBarLayout.requestLayout();
                    RoomDetialsActivity.this.mAppBarLayout.init();
                }
            }
        });
    }

    private void c() {
        String e2 = this.c.e();
        if (!TextUtils.isEmpty(e2)) {
            this.mAppBarTitle.setText(this.c.e());
            this.mTitle.setText(this.c.e());
            if (this.i) {
                this.mAppBarLayout.setBackground((Drawable) null);
                this.mToolbar.setBackground((Drawable) null);
                return;
            }
            String replaceAll = e2.toLowerCase().replaceAll(" ", "");
            if (!TextUtils.isEmpty(replaceAll)) {
                for (LanguageModel next : LanguageSupport.a()) {
                    String a2 = LanguageUtil.a(next.f18427a, (int) R.string.tag_recommend_livingroom);
                    if (TextUtils.isEmpty(a2) || !replaceAll.contains(a2.toLowerCase().replaceAll(" ", ""))) {
                        String a3 = LanguageUtil.a(next.f18427a, (int) R.string.tag_recommend_bedroom);
                        if (TextUtils.isEmpty(a3) || !replaceAll.contains(a3.toLowerCase().replaceAll(" ", ""))) {
                            String a4 = LanguageUtil.a(next.f18427a, (int) R.string.tag_recommend_kitchen);
                            if (TextUtils.isEmpty(a4) || !replaceAll.contains(a4.toLowerCase().replaceAll(" ", ""))) {
                                String a5 = LanguageUtil.a(next.f18427a, (int) R.string.tag_recommend_washroom);
                                if (TextUtils.isEmpty(a5) || (!replaceAll.contains(a5.toLowerCase().replaceAll(" ", "")) && !replaceAll.contains("bathroom"))) {
                                    this.mAppBarLayout.setBackgroundResource(R.drawable.default_room_appbar_bg);
                                    this.mToolbar.setBackgroundColor(getResources().getColor(R.color.color_default_room_appbar_bg));
                                } else {
                                    this.mAppBarLayout.setBackgroundResource(R.drawable.bath_room_appbar_bg);
                                    this.mToolbar.setBackgroundColor(getResources().getColor(R.color.color_bath_room_appbar_bg));
                                    return;
                                }
                            } else {
                                this.mAppBarLayout.setBackgroundResource(R.drawable.kitchen_appbar_bg);
                                this.mToolbar.setBackgroundColor(getResources().getColor(R.color.color_kitchen_appbar_bg));
                                return;
                            }
                        } else {
                            this.mAppBarLayout.setBackgroundResource(R.drawable.bed_room_appbar_bg);
                            this.mToolbar.setBackgroundColor(getResources().getColor(R.color.color_bed_room_appbar_bg));
                            return;
                        }
                    } else {
                        this.mAppBarLayout.setBackgroundResource(R.drawable.living_room_appbar_bg);
                        this.mToolbar.setBackgroundColor(getResources().getColor(R.color.color_living_room_appbar_bg));
                        return;
                    }
                }
            }
        }
    }

    public RecyclerView getRecyclerView() {
        return this.mRecyclerView;
    }

    public void onClickCommonUseDevice(Device device, RectF rectF, String str) {
        STAT.d.b(false);
        this.f = device;
        Intent intent = new Intent();
        intent.putExtra("did", device.did);
        intent.putExtra("room_name", str);
        intent.setClass(getContext(), MIUI10CardActivity.class);
        intent.putExtra("view_position", rectF);
        startActivityForResult(intent, 6);
        d();
    }

    private void a(final Device device) {
        if (device != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(device.did);
            SmartHomeDeviceManager.a().a((List<String>) arrayList, (AsyncCallback<List<Device>, Error>) new AsyncCallback<List<Device>, Error>() {
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(List<Device> list) {
                    SmartHomeDeviceManager.a().a(device);
                    for (GridViewData next : HomeManager.a().F()) {
                        if (next.f18311a == GridViewData.GridType.TYPE_NORMAL && next.b.did.equals(device.did)) {
                            Intent intent = new Intent();
                            intent.setAction("com.xiaomi.smarthome.refresh_device");
                            intent.putExtra(LoginTask.f22078a, CoreApi.a().s());
                            intent.putExtra(GetDeviceTask.c, GetDeviceTask.a(next));
                            SHApplication.getAppContext().sendBroadcast(intent);
                            return;
                        }
                    }
                }
            });
        }
    }

    private void d() {
        if (this.b == null) {
            this.b = (CommonBlurView) ((ViewStub) findViewById(R.id.blur_view_vs)).inflate();
        }
        this.b.setBlurView(findViewById(R.id.layout_layer1), true);
    }

    private void e() {
        if (this.b != null) {
            this.b.setBlurViewGone();
        }
    }

    public void updateActionItems(DviceEditInterface dviceEditInterface, int i2, String str, boolean z) {
        if (i2 > 0) {
            try {
                this.mTxtEditTitle.setText(getResources().getQuantityString(R.plurals.edit_choosed_device, i2, new Object[]{Integer.valueOf(i2)}));
            } catch (Exception e2) {
                e2.printStackTrace();
                return;
            }
        } else {
            this.mTxtEditTitle.setText(R.string.title_choose_device);
        }
        this.mTxtEditTitle.setTypeface((Typeface) null, 0);
        if (i2 >= dviceEditInterface.p()) {
            this.n.setImageResource(R.drawable.un_select_selector);
        } else {
            this.n.setImageResource(R.drawable.all_select_selector);
        }
        ((DeviceMainPageEditBarV2) getChooseDeviceMenuBar2()).updateActionItems(dviceEditInterface, str, z);
    }

    public View getChooseDeviceTitleBar() {
        if (this.k == null) {
            this.k = ((ViewStub) findViewById(R.id.title_bar_choose_device_stub)).inflate();
            TitleBarUtil.a(TitleBarUtil.a(), this.k.findViewById(R.id.title_bar_choose_device));
        }
        return this.k;
    }

    public View getChooseDeviceMenuBar2() {
        if (this.l == null) {
            this.l = ((ViewStub) findViewById(R.id.edit_action_bar_stub_v2)).inflate();
        }
        return this.l;
    }

    public void enterEditMode(DviceEditInterface dviceEditInterface) {
        if (!this.mEditMode && dviceEditInterface != null) {
            this.mEditMode = true;
            this.mEditInterface = dviceEditInterface;
            BluetoothHelper.b();
            try {
                View chooseDeviceTitleBar = getChooseDeviceTitleBar();
                this.n = (ImageView) chooseDeviceTitleBar.findViewById(EditActionMode.f3057a);
                this.m = (ImageView) chooseDeviceTitleBar.findViewById(EditActionMode.b);
                this.m.setImageResource(R.drawable.title_right_tick_drawable);
                this.mTxtEditTitle = (TextView) chooseDeviceTitleBar.findViewById(R.id.module_a_4_return_more_title);
                this.m.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        RoomDetialsActivity.this.a(view);
                    }
                });
                this.n.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        RoomDetialsActivity.a(DviceEditInterface.this, view);
                    }
                });
                DeviceMainPageEditBarV2 deviceMainPageEditBarV2 = (DeviceMainPageEditBarV2) getChooseDeviceMenuBar2();
                deviceMainPageEditBarV2.setDeviceMainPage(this);
                deviceMainPageEditBarV2.enterEditMode(dviceEditInterface);
                deviceMainPageEditBarV2.setOnMenuBarPositionChangeListener(this.g);
                if (deviceMainPageEditBarV2.getHeight() == 0) {
                    deviceMainPageEditBarV2.post(new Runnable(deviceMainPageEditBarV2, chooseDeviceTitleBar) {
                        private final /* synthetic */ DeviceMainPageEditBarV2 f$1;
                        private final /* synthetic */ View f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void run() {
                            RoomDetialsActivity.this.a(this.f$1, this.f$2);
                        }
                    });
                } else {
                    a(deviceMainPageEditBarV2, chooseDeviceTitleBar, true);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            dviceEditInterface.j();
            STAT.c.d(dviceEditInterface.q());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        exitEditMode();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DviceEditInterface dviceEditInterface, View view) {
        boolean z = dviceEditInterface.l().size() < dviceEditInterface.p();
        for (int i2 = 0; i2 < dviceEditInterface.n(); i2++) {
            dviceEditInterface.a(i2, z);
        }
        dviceEditInterface.o();
        STAT.d.i(z ? 1 : 0);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DeviceMainPageEditBarV2 deviceMainPageEditBarV2, View view) {
        a(deviceMainPageEditBarV2, view, true);
    }

    public void exitEditMode() {
        if (this.mEditMode) {
            this.mEditMode = false;
            a((DeviceMainPageEditBarV2) getChooseDeviceMenuBar2(), getChooseDeviceTitleBar(), false);
            if (this.mEditInterface != null) {
                this.mEditInterface.k();
                this.mEditInterface.o();
                this.mEditInterface = null;
            }
        }
    }

    public void onStateChanged() {
        LogUtil.a(f20355a, "onStateChanged");
        exitEditMode();
        this.e.b(this.c.f(), this.c.d());
    }

    private void a(final DeviceMainPageEditBarV2 deviceMainPageEditBarV2, final View view, final boolean z) {
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator2;
        deviceMainPageEditBarV2.setVisibility(0);
        view.setVisibility(0);
        ViewGroup viewGroup = (ViewGroup) deviceMainPageEditBarV2.getParent();
        if (z) {
            objectAnimator = ObjectAnimator.ofFloat(deviceMainPageEditBarV2, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - deviceMainPageEditBarV2.getHeight())});
            objectAnimator2 = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
        } else {
            objectAnimator = ObjectAnimator.ofFloat(deviceMainPageEditBarV2, View.Y, new float[]{(float) (viewGroup.getHeight() - deviceMainPageEditBarV2.getHeight()), (float) viewGroup.getHeight()});
            objectAnimator2 = ObjectAnimator.ofFloat(view, View.Y, new float[]{0.0f, (float) (-view.getHeight())});
        }
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(objectAnimator).with(objectAnimator2);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (!z) {
                    view.clearAnimation();
                    view.setVisibility(8);
                    deviceMainPageEditBarV2.exitEditMode();
                }
            }
        });
    }

    public boolean isEditMode() {
        return this.mEditMode;
    }

    class RoomEnvAdapter extends BaseListAdapter<String> {
        public int a(int i) {
            return R.layout.room_details_top_env_info;
        }

        RoomEnvAdapter(Context context, List<String> list) {
            super(context, list);
        }

        public void a(ViewHolder viewHolder, String str, int i) {
            if (((String) this.b.get(i)) != null) {
                ((FixedTextView) viewHolder.a((int) R.id.tv_room_env)).setTextAutoCutOff(str, "|");
            }
        }
    }
}
