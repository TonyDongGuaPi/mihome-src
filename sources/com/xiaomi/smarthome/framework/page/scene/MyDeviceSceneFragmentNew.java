package com.xiaomi.smarthome.framework.page.scene;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.h6ah4i.android.widget.advrecyclerview.expandable.ExpandableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.expandable.RecyclerViewExpandableItemManager;
import com.h6ah4i.android.widget.advrecyclerview.touchguard.RecyclerViewTouchActionGuardManager;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractDraggableSwipeableItemViewHolder;
import com.h6ah4i.android.widget.advrecyclerview.utils.AbstractExpandableItemAdapter;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.GoLeaveHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.SceneStatusManager;
import com.xiaomi.smarthome.scenenew.SceneTabFragment;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeSortLiteSceneActivity;
import com.xiaomi.smarthome.scenenew.lumiscene.LocalSceneBuilder;
import com.xiaomi.smarthome.scenenew.lumiscene.SceneExtraBuilder;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import com.xiaomi.smarthome.scenenew.view.SelectRoomDialogView;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import miuipub.view.EditActionMode;
import org.json.JSONObject;

public class MyDeviceSceneFragmentNew extends BaseFragment implements RecyclerViewExpandableItemManager.OnGroupCollapseListener, RecyclerViewExpandableItemManager.OnGroupExpandListener {
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 0;
    public static final int h = 999;
    private static final String x = "MySceneFragment";
    private boolean A = false;
    private boolean B = false;
    private boolean C = true;
    private RecyclerViewTouchActionGuardManager D;
    private boolean E = false;
    /* access modifiers changed from: private */
    public String F;
    private SceneManager.IScenceListener G = new SceneManager.IScenceListener() {
        public void onRefreshScenceSuccess(int i) {
            if (MyDeviceSceneFragmentNew.this.isValid() && i == 5) {
                MyDeviceSceneFragmentNew.this.a(MyDeviceSceneFragmentNew.this.H);
                MyDeviceSceneFragmentNew.this.g();
                if (MyDeviceSceneFragmentNew.this.mPullRefreshLL != null) {
                    MyDeviceSceneFragmentNew.this.mPullRefreshLL.refreshComplete();
                }
            }
        }

        public void onRefreshScenceFailed(int i) {
            if (MyDeviceSceneFragmentNew.this.isValid()) {
                MyDeviceSceneFragmentNew.this.mPullRefreshLL.refreshComplete();
                MyDeviceSceneFragmentNew.this.g();
            }
        }
    };
    /* access modifiers changed from: private */
    public Room H;
    private GroupData I;
    private GroupData J;
    private BroadcastReceiver K = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            LogUtil.a(MyDeviceSceneFragmentNew.x, intent.getAction());
            if ("action_on_login_success".equals(intent.getAction())) {
                SceneStatusManager.a().c();
            } else if ("action_on_logout".equals(intent.getAction())) {
                SceneStatusManager.a().b();
            }
        }
    };
    private SceneStatusManager.OnStatusChangeListener L = new SceneStatusManager.OnStatusChangeListener() {
        public void a(List<SceneApi.SmartHomeScene> list) {
            if (MyDeviceSceneFragmentNew.this.mPullRefreshLL != null && !MyDeviceSceneFragmentNew.this.mPullRefreshLL.isRefreshing()) {
                List list2 = null;
                if (MyDeviceSceneFragmentNew.this.b != null) {
                    int i = 0;
                    while (true) {
                        if (i >= MyDeviceSceneFragmentNew.this.b.size()) {
                            break;
                        } else if (((GroupData) MyDeviceSceneFragmentNew.this.b.get(i).first).b == 15) {
                            list2 = (List) MyDeviceSceneFragmentNew.this.b.get(i).second;
                            break;
                        } else {
                            i++;
                        }
                    }
                }
                if (list2 != null && list2.size() != 0) {
                    for (int i2 = 0; i2 < list.size(); i2++) {
                        for (int i3 = 0; i3 < list2.size(); i3++) {
                            if (TextUtils.equals(((SceneApi.SmartHomeScene) list2.get(i3)).f, list.get(i2).f)) {
                                ((SceneApi.SmartHomeScene) list2.get(i3)).n = list.get(i2).n;
                                SceneManager.x().a(((SceneApi.SmartHomeScene) list2.get(i3)).f, (SceneApi.SmartHomeScene) list2.get(i3));
                            }
                        }
                    }
                    MyDeviceSceneFragmentNew.this.j.notifyDataSetChanged();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public HashMap<String, Boolean> M = new HashMap<>();
    /* access modifiers changed from: private */
    public boolean N = false;

    /* renamed from: a  reason: collision with root package name */
    View f16976a;
    List<Pair<GroupData, List<SceneApi.SmartHomeScene>>> b = new ArrayList();
    List<SceneApi.SmartHomeScene> c = new ArrayList();
    RecyclerView.Adapter<SceneChildViewHolder> d;
    SceneTabFragment i;
    SceneAdapterNew j;
    RecyclerViewExpandableItemManager k;
    LinearLayoutManager l = null;
    List<Room> m = new ArrayList();
    @BindView(2131428501)
    TextView mEmptyTV;
    @BindView(2131428503)
    View mEmptyView;
    @BindView(2131429230)
    TextView mFilterTV;
    @BindView(2131431513)
    View mPlaceHolderView;
    @BindView(2131431674)
    PtrFrameLayout mPullRefreshLL;
    @BindView(2131432156)
    RecyclerView mSceneViewRV;
    LayoutInflater n;
    public boolean o = false;
    public ImageView p;
    public ImageView q;
    public Button r;
    public Button s;
    public Button t;
    public Button u;
    public TextView v;
    ArrayList<SceneApi.SmartHomeScene> w = new ArrayList<>();
    private int y = 0;
    private boolean z = true;

    public void a(int i2, boolean z2) {
    }

    public void b(int i2, boolean z2) {
    }

    public class SceneChildViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private SceneChildViewHolder f17021a;

        @UiThread
        public SceneChildViewHolder_ViewBinding(SceneChildViewHolder sceneChildViewHolder, View view) {
            this.f17021a = sceneChildViewHolder;
            sceneChildViewHolder.mDivideLine = Utils.findRequiredView(view, R.id.divide_line, "field 'mDivideLine'");
            sceneChildViewHolder.mDivideLineBottom = Utils.findRequiredView(view, R.id.divide_line_bottom, "field 'mDivideLineBottom'");
        }

        @CallSuper
        public void unbind() {
            SceneChildViewHolder sceneChildViewHolder = this.f17021a;
            if (sceneChildViewHolder != null) {
                this.f17021a = null;
                sceneChildViewHolder.mDivideLine = null;
                sceneChildViewHolder.mDivideLineBottom = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class AutoSceneViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private AutoSceneViewHolder f17010a;

        @UiThread
        public AutoSceneViewHolder_ViewBinding(AutoSceneViewHolder autoSceneViewHolder, View view) {
            this.f17010a = autoSceneViewHolder;
            autoSceneViewHolder.actionLL = (LinearLayout) Utils.findRequiredViewAsType(view, R.id.action_ll, "field 'actionLL'", LinearLayout.class);
            autoSceneViewHolder.mIconCondition = (SimpleDraweeView) Utils.findRequiredViewAsType(view, R.id.icon_condition, "field 'mIconCondition'", SimpleDraweeView.class);
            autoSceneViewHolder.mIconConditionMore = (ImageView) Utils.findRequiredViewAsType(view, R.id.icon_condition_more, "field 'mIconConditionMore'", ImageView.class);
            autoSceneViewHolder.tvSceneName = (TextView) Utils.findRequiredViewAsType(view, R.id.tv_scene_name, "field 'tvSceneName'", TextView.class);
            autoSceneViewHolder.mCheckBox = (CheckBox) Utils.findRequiredViewAsType(view, R.id.ckb_edit_selected, "field 'mCheckBox'", CheckBox.class);
            autoSceneViewHolder.mRightView = (ImageView) Utils.findRequiredViewAsType(view, R.id.right_view, "field 'mRightView'", ImageView.class);
            autoSceneViewHolder.mOpenCheck = (SwitchButton) Utils.findRequiredViewAsType(view, R.id.open_check, "field 'mOpenCheck'", SwitchButton.class);
            autoSceneViewHolder.mMoreIcon = Utils.findRequiredView(view, R.id.scene_new_more_icon, "field 'mMoreIcon'");
            autoSceneViewHolder.mNewTagTV = (TextView) Utils.findRequiredViewAsType(view, R.id.new_tag, "field 'mNewTagTV'", TextView.class);
            autoSceneViewHolder.mDivideLine = Utils.findRequiredView(view, R.id.divide_line, "field 'mDivideLine'");
            autoSceneViewHolder.mDivideLineBottom = Utils.findRequiredView(view, R.id.divide_line_bottom, "field 'mDivideLineBottom'");
            autoSceneViewHolder.mExecuteTV = (TextView) Utils.findRequiredViewAsType(view, R.id.execute_tv, "field 'mExecuteTV'", TextView.class);
        }

        @CallSuper
        public void unbind() {
            AutoSceneViewHolder autoSceneViewHolder = this.f17010a;
            if (autoSceneViewHolder != null) {
                this.f17010a = null;
                autoSceneViewHolder.actionLL = null;
                autoSceneViewHolder.mIconCondition = null;
                autoSceneViewHolder.mIconConditionMore = null;
                autoSceneViewHolder.tvSceneName = null;
                autoSceneViewHolder.mCheckBox = null;
                autoSceneViewHolder.mRightView = null;
                autoSceneViewHolder.mOpenCheck = null;
                autoSceneViewHolder.mMoreIcon = null;
                autoSceneViewHolder.mNewTagTV = null;
                autoSceneViewHolder.mDivideLine = null;
                autoSceneViewHolder.mDivideLineBottom = null;
                autoSceneViewHolder.mExecuteTV = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    static class GroupData {

        /* renamed from: a  reason: collision with root package name */
        public String f17011a;
        public int b;

        public GroupData(String str, int i) {
            this.f17011a = str;
            this.b = i;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SceneLogUtil.a("MySceneFragment  onCreateView");
        if (this.f16976a == null) {
            this.f16976a = layoutInflater.inflate(R.layout.fragment_my_device_scene_layout_new, (ViewGroup) null);
            ButterKnife.bind((Object) this, this.f16976a);
            this.n = LayoutInflater.from(getActivity());
            this.mContext = getActivity();
            this.I = new GroupData(getResources().getString(R.string.smarthome_new_scene_by_hand), 30);
            this.J = new GroupData(getResources().getString(R.string.smarthome_new_scene_auto), 15);
            a(bundle);
        }
        return this.f16976a;
    }

    public void onViewCreated(View view, Bundle bundle) {
        SceneLogUtil.a("MySceneFragment  onViewCreated");
        super.onViewCreated(view, bundle);
        LiteSceneManager.j().b();
    }

    private void a(Bundle bundle) {
        this.mFilterTV.setVisibility(8);
        this.mEmptyTV.setText(R.string.my_scene_no_data);
        h();
        b(bundle);
        d();
        if (getArguments() != null && getArguments().containsKey("device_id")) {
            this.F = getArguments().getString("device_id");
        }
        if (this.mPullRefreshLL == null) {
            return;
        }
        if (!TextUtils.isEmpty(this.F)) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    MyDeviceSceneFragmentNew.this.mPullRefreshLL.autoRefresh();
                }
            }, 300);
        } else {
            g();
        }
    }

    private void b(Bundle bundle) {
        this.l = new LinearLayoutManager(this.mContext);
        this.D = new RecyclerViewTouchActionGuardManager();
        this.D.b(true);
        this.D.a(true);
        this.k = new RecyclerViewExpandableItemManager(bundle);
        this.k.a((RecyclerViewExpandableItemManager.OnGroupCollapseListener) this);
        this.k.a((RecyclerViewExpandableItemManager.OnGroupExpandListener) this);
        this.j = new SceneAdapterNew();
        this.d = this.k.a((RecyclerView.Adapter) this.j);
        this.mSceneViewRV.setLayoutManager(this.l);
        this.mSceneViewRV.setAdapter(this.d);
        this.D.a(this.mSceneViewRV);
        this.k.a(this.mSceneViewRV);
        this.k.d();
    }

    private void d() {
        this.mPullRefreshLL.setPtrHandler(new PtrHandler() {
            public boolean checkCanDoRefresh(PtrFrameLayout ptrFrameLayout, View view, View view2) {
                if (Build.VERSION.SDK_INT < 14) {
                    if (view instanceof AbsListView) {
                        AbsListView absListView = (AbsListView) view;
                        if (absListView.getChildCount() <= 0 || (absListView.getFirstVisiblePosition() <= 0 && absListView.getChildAt(0).getTop() >= absListView.getPaddingTop())) {
                            return false;
                        }
                        return true;
                    } else if (view instanceof RecyclerView) {
                        LinearLayoutManager linearLayoutManager = MyDeviceSceneFragmentNew.this.l;
                        if (linearLayoutManager != null && linearLayoutManager.findFirstCompletelyVisibleItemPosition() - 1 < 0) {
                            return true;
                        }
                        return false;
                    } else if (view.getScrollY() > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else if (!(view instanceof RecyclerView)) {
                    return view.canScrollVertically(-1);
                } else {
                    LinearLayoutManager linearLayoutManager2 = MyDeviceSceneFragmentNew.this.l;
                    if (linearLayoutManager2 != null && linearLayoutManager2.findFirstCompletelyVisibleItemPosition() - 1 < 0) {
                        return true;
                    }
                    return false;
                }
            }

            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                if (!TextUtils.isEmpty(MyDeviceSceneFragmentNew.this.F)) {
                    MyDeviceSceneFragmentNew.this.f();
                } else {
                    MyDeviceSceneFragmentNew.this.g();
                }
            }
        });
        this.mFilterTV.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                MyDeviceSceneFragmentNew.this.e();
                CreateSceneManager.a().a(MyDeviceSceneFragmentNew.this.getActivity(), new SelectRoomDialogView.IOnRoomSelectCallBack() {
                    public void a(Room room) {
                        MyDeviceSceneFragmentNew.this.a(room);
                        if (room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
                            MyDeviceSceneFragmentNew.this.mFilterTV.setText(R.string.smarthome_scene_create_filter);
                        } else {
                            MyDeviceSceneFragmentNew.this.mFilterTV.setText(room.e());
                        }
                        MyDeviceSceneFragmentNew.this.g();
                    }
                }, MyDeviceSceneFragmentNew.this.H, MyDeviceSceneFragmentNew.this.m);
            }
        });
        this.mEmptyView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (MyDeviceSceneFragmentNew.this.mPullRefreshLL != null) {
                    MyDeviceSceneFragmentNew.this.mPullRefreshLL.autoRefresh();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        this.m.clear();
        Room room = new Room();
        room.e(getString(R.string.smarthome_scene_all_room));
        room.d(SelectRoomDialogView.ALL_ROOM_ID);
        this.m.add(room);
        List<Room> e2 = HomeManager.a().e();
        if (e2 != null) {
            for (int i2 = 0; i2 < e2.size(); i2++) {
                Room room2 = e2.get(i2);
                if (b(room2) > 0) {
                    this.m.add(room2);
                }
            }
        }
        Room room3 = new Room();
        room3.e(getString(R.string.tag_recommend_defaultroom));
        room3.d(SelectRoomDialogView.DEFAULT_ROOM_ID);
        if (b(room3) > 0) {
            this.m.add(room3);
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        this.A = false;
        this.B = false;
        SceneManager.x().a(this.F, this.G);
        SceneManager.x().a();
    }

    /* access modifiers changed from: private */
    public void a(Room room) {
        this.H = room;
        List<SceneApi.SmartHomeScene> d2 = SceneManager.x().d(this.F);
        this.w.clear();
        if (room == null) {
            this.w.addAll(d2);
        } else if (room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
            this.w.addAll(d2);
        } else if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
            List<Device> j2 = HomeManager.a().j();
            for (int i2 = 0; i2 < d2.size(); i2++) {
                SceneApi.SmartHomeScene smartHomeScene = d2.get(i2);
                if (b(smartHomeScene, j2)) {
                    this.w.add(smartHomeScene);
                }
            }
        } else {
            List<String> h2 = room.h();
            for (int i3 = 0; i3 < d2.size(); i3++) {
                SceneApi.SmartHomeScene smartHomeScene2 = d2.get(i3);
                if (c(smartHomeScene2, h2)) {
                    this.w.add(smartHomeScene2);
                }
            }
        }
        this.b.clear();
        if (this.c.size() > 0) {
            this.b.add(new Pair(this.I, this.c));
        }
        if (this.w.size() > 0) {
            this.b.add(new Pair(this.J, this.w));
        }
        LogUtil.a(x, "mScenes.size" + this.b.size());
        this.j.notifyDataSetChanged();
        for (int i4 = 0; i4 < this.b.size(); i4++) {
            if (!this.k.e(i4)) {
                this.k.a(i4);
            }
        }
    }

    private int b(Room room) {
        int i2;
        List<SceneApi.SmartHomeScene> d2 = SceneManager.x().d(this.F);
        int i3 = 0;
        if (room == null) {
            return d2.size() + 0;
        }
        if (room.d().equalsIgnoreCase(SelectRoomDialogView.ALL_ROOM_ID)) {
            return d2.size() + 0;
        }
        if (room.d().equalsIgnoreCase(SelectRoomDialogView.DEFAULT_ROOM_ID)) {
            List<Device> j2 = HomeManager.a().j();
            i2 = 0;
            while (i3 < d2.size()) {
                if (b(d2.get(i3), j2)) {
                    i2++;
                }
                i3++;
            }
        } else {
            List<String> h2 = room.h();
            i2 = 0;
            while (i3 < d2.size()) {
                if (c(d2.get(i3), h2)) {
                    i2++;
                }
                i3++;
            }
        }
        return i2;
    }

    private boolean b(SceneApi.SmartHomeScene smartHomeScene, List<Device> list) {
        for (int i2 = 0; i2 < smartHomeScene.l.size(); i2++) {
            SceneApi.Condition condition = smartHomeScene.l.get(i2);
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    if (list.get(i3).did.equalsIgnoreCase(condition.c.f21523a)) {
                        return true;
                    }
                }
                continue;
            }
        }
        for (int i4 = 0; i4 < smartHomeScene.k.size(); i4++) {
            SceneApi.Action action = smartHomeScene.k.get(i4);
            if (action.g instanceof SceneApi.SHSceneItemPayloadCommon) {
                for (int i5 = 0; i5 < list.size(); i5++) {
                    if (list.get(i5).did.equalsIgnoreCase(action.g.e)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    private boolean c(SceneApi.SmartHomeScene smartHomeScene, List<String> list) {
        for (int i2 = 0; i2 < smartHomeScene.l.size(); i2++) {
            SceneApi.Condition condition = smartHomeScene.l.get(i2);
            if (condition.f21522a == SceneApi.Condition.LAUNCH_TYPE.DEVICE) {
                for (int i3 = 0; i3 < list.size(); i3++) {
                    if (list.get(i3).equalsIgnoreCase(condition.c.f21523a)) {
                        return true;
                    }
                }
                continue;
            }
        }
        for (int i4 = 0; i4 < smartHomeScene.k.size(); i4++) {
            SceneApi.Action action = smartHomeScene.k.get(i4);
            if (action.g instanceof SceneApi.SHSceneItemPayloadCommon) {
                for (int i5 = 0; i5 < list.size(); i5++) {
                    if (list.get(i5).equalsIgnoreCase(action.g.e)) {
                        return true;
                    }
                }
                continue;
            }
        }
        return false;
    }

    public void onResume() {
        super.onResume();
        LogUtil.a(x, "onresume");
        if (this.z) {
            this.z = false;
        } else {
            f();
        }
    }

    public void onPause() {
        super.onPause();
        LogUtil.a(x, "onPause");
        RecommendSceneManager.a().b((String) null);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(x, "onCreate");
        IntentFilter intentFilter = new IntentFilter("action_on_login_success");
        intentFilter.addAction("action_on_logout");
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.K, intentFilter);
        SceneStatusManager.a().a(this.L);
        SceneStatusManager.a().c();
    }

    public boolean a(Activity activity) {
        if (this.y == 1) {
            a();
            return true;
        } else if (activity == null) {
            return onBackPressed();
        } else {
            activity.finish();
            return true;
        }
    }

    public void onDestroy() {
        super.onDestroy();
        SceneStatusManager.a().b(this.L);
        SceneStatusManager.a().b();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.K);
        SceneManager.x().b(this.G);
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.b.size() == 0) {
            this.mPullRefreshLL.setPullToRefresh(false);
            this.mEmptyView.setVisibility(0);
            this.mSceneViewRV.setVisibility(8);
            return;
        }
        this.mSceneViewRV.setVisibility(0);
        this.mPullRefreshLL.setPullToRefresh(true);
        this.mEmptyView.setVisibility(8);
    }

    class AutoSceneViewHolder extends AbstractDraggableItemViewHolder implements ExpandableItemViewHolder {
        @BindView(2131427494)
        LinearLayout actionLL;
        private int b;
        @BindView(2131428355)
        CheckBox mCheckBox;
        @BindView(2131428895)
        View mDivideLine;
        @BindView(2131428896)
        View mDivideLineBottom;
        @BindView(2131429084)
        TextView mExecuteTV;
        @BindView(2131429703)
        SimpleDraweeView mIconCondition;
        @BindView(2131429704)
        ImageView mIconConditionMore;
        @BindView(2131432152)
        View mMoreIcon;
        @BindView(2131431168)
        TextView mNewTagTV;
        @BindView(2131431289)
        SwitchButton mOpenCheck;
        @BindView(2131431921)
        ImageView mRightView;
        @BindView(2131433475)
        TextView tvSceneName;

        public AutoSceneViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
        }

        public void c_(int i) {
            this.b = i;
        }

        public int K_() {
            return this.b;
        }
    }

    class GroupViewHolder extends AbstractDraggableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f17012a;
        View b;
        private int d;

        public GroupViewHolder(View view) {
            super(view);
            this.f17012a = (TextView) view.findViewById(R.id.group_tv);
            this.b = view.findViewById(R.id.divide_line);
        }

        public void c_(int i) {
            this.d = i;
        }

        public int K_() {
            return this.d;
        }
    }

    class SceneAdapterNew extends AbstractExpandableItemAdapter<GroupViewHolder, AutoSceneViewHolder> {
        public boolean a(GroupViewHolder groupViewHolder, int i, int i2, int i3, boolean z) {
            return false;
        }

        public long b(int i) {
            return (long) i;
        }

        public long c(int i, int i2) {
            return (long) i2;
        }

        public SceneAdapterNew() {
            setHasStableIds(true);
        }

        public int a() {
            LogUtil.a(MyDeviceSceneFragmentNew.x, "getGroupCount" + MyDeviceSceneFragmentNew.this.b.size());
            return MyDeviceSceneFragmentNew.this.b.size();
        }

        public int a(int i) {
            return ((List) MyDeviceSceneFragmentNew.this.b.get(i).second).size();
        }

        /* renamed from: c */
        public GroupViewHolder a(ViewGroup viewGroup, int i) {
            LogUtil.a(MyDeviceSceneFragmentNew.x, "onCreateGroupViewHolder" + MyDeviceSceneFragmentNew.this.b.size());
            return new GroupViewHolder(MyDeviceSceneFragmentNew.this.n.inflate(R.layout.fragment_my_scene_group_item, (ViewGroup) null));
        }

        /* renamed from: d */
        public AutoSceneViewHolder b(ViewGroup viewGroup, int i) {
            LogUtil.a(MyDeviceSceneFragmentNew.x, "onCreateChildViewHolder" + MyDeviceSceneFragmentNew.this.b.size());
            View inflate = MyDeviceSceneFragmentNew.this.n.inflate(R.layout.my_scene_fragment_scene_item, (ViewGroup) null);
            LogUtil.a("youhua", "SceneAdapteronCreateViewHolder  viewType" + i);
            return new AutoSceneViewHolder(inflate);
        }

        /* renamed from: a */
        public void b(GroupViewHolder groupViewHolder, int i, int i2) {
            LogUtil.a(MyDeviceSceneFragmentNew.x, "onBindGroupViewHolder" + MyDeviceSceneFragmentNew.this.b.size());
            GroupData groupData = (GroupData) MyDeviceSceneFragmentNew.this.b.get(i).first;
            if (groupData.b == 30) {
                Drawable drawable = MyDeviceSceneFragmentNew.this.getResources().getDrawable(R.drawable.lite_scene_hand_icon);
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                groupViewHolder.f17012a.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                groupViewHolder.b.setVisibility(8);
            } else if (groupData.b == 15) {
                Drawable drawable2 = MyDeviceSceneFragmentNew.this.getResources().getDrawable(R.drawable.auto_scene_icon);
                drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                groupViewHolder.f17012a.setCompoundDrawables(drawable2, (Drawable) null, (Drawable) null, (Drawable) null);
                if (MyDeviceSceneFragmentNew.this.b.size() == 2) {
                    groupViewHolder.b.setVisibility(0);
                } else {
                    groupViewHolder.b.setVisibility(8);
                }
            }
            groupViewHolder.f17012a.setText(((GroupData) MyDeviceSceneFragmentNew.this.b.get(i).first).f17011a);
        }

        /* renamed from: a */
        public void b(final AutoSceneViewHolder autoSceneViewHolder, int i, final int i2, int i3) {
            final SceneApi.SmartHomeScene smartHomeScene = (SceneApi.SmartHomeScene) ((List) MyDeviceSceneFragmentNew.this.b.get(i).second).get(i2);
            if (smartHomeScene.l == null || smartHomeScene.l.size() == 0 || MyDeviceSceneFragmentNew.this.c(smartHomeScene)) {
                autoSceneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MyDeviceSceneFragmentNew.this.o) {
                            autoSceneViewHolder.mCheckBox.setChecked(!autoSceneViewHolder.mCheckBox.isChecked());
                        } else if (SceneManager.x().b(smartHomeScene) || SceneManager.x().c(smartHomeScene)) {
                            Intent intent = new Intent(MyDeviceSceneFragmentNew.this.getActivity(), GoLeaveHomeSceneCreateEditActivity.class);
                            intent.putExtra(GoLeaveHomeSceneCreateEditActivity.SCENE_ID, smartHomeScene.f);
                            MyDeviceSceneFragmentNew.this.startActivity(intent);
                        } else {
                            Intent intent2 = new Intent(MyDeviceSceneFragmentNew.this.getActivity(), SmarthomeCreateAutoSceneActivity.class);
                            intent2.putExtra(SmarthomeCreateAutoSceneActivity.EXTRA_DEVICE_ID, ((DeviceSceneActivityNew) MyDeviceSceneFragmentNew.this.getActivity()).mDid);
                            CreateSceneManager.a().a(smartHomeScene);
                            MyDeviceSceneFragmentNew.this.startActivity(intent2);
                        }
                    }
                });
                autoSceneViewHolder.tvSceneName.setTextColor(MyDeviceSceneFragmentNew.this.getResources().getColor(R.color.black_80_transparent));
            } else {
                autoSceneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (MyDeviceSceneFragmentNew.this.o) {
                            autoSceneViewHolder.mCheckBox.setChecked(!autoSceneViewHolder.mCheckBox.isChecked());
                        } else {
                            Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.cannot_edit_ios_scene, 0).show();
                        }
                    }
                });
                autoSceneViewHolder.tvSceneName.setTextColor(MyDeviceSceneFragmentNew.this.getResources().getColor(R.color.black_40_transparent));
            }
            if (smartHomeScene.l.size() > 0) {
                autoSceneViewHolder.mIconCondition.setVisibility(0);
                autoSceneViewHolder.mIconCondition.setHierarchy(new GenericDraweeHierarchyBuilder(autoSceneViewHolder.mIconCondition.getResources()).setFadeDuration(200).setPlaceholderImage(autoSceneViewHolder.mIconCondition.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                if (smartHomeScene == null || smartHomeScene.l.size() <= 0) {
                    autoSceneViewHolder.mIconCondition.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_default_nor));
                } else {
                    SmartHomeSceneUtility.a(autoSceneViewHolder.mIconCondition, smartHomeScene.l.get(0));
                }
                if (smartHomeScene.l.size() > 1) {
                    autoSceneViewHolder.mIconConditionMore.setVisibility(0);
                } else {
                    autoSceneViewHolder.mIconConditionMore.setVisibility(8);
                }
                autoSceneViewHolder.mIconConditionMore.setImageResource(SmartHomeSceneUtility.f(smartHomeScene.q));
            } else {
                autoSceneViewHolder.mIconCondition.setVisibility(8);
                autoSceneViewHolder.mIconConditionMore.setVisibility(8);
            }
            if (SmartHomeSceneUtility.a(smartHomeScene, autoSceneViewHolder.actionLL) > 3) {
                autoSceneViewHolder.mMoreIcon.setVisibility(0);
            } else {
                autoSceneViewHolder.mMoreIcon.setVisibility(8);
            }
            autoSceneViewHolder.tvSceneName.setText(smartHomeScene.g);
            if (smartHomeScene.r != 0) {
                autoSceneViewHolder.mRightView.setImageResource(R.drawable.exclamation_mark);
            } else {
                autoSceneViewHolder.mRightView.setVisibility(4);
            }
            if (MyDeviceSceneFragmentNew.this.o) {
                autoSceneViewHolder.mCheckBox.setVisibility(0);
                autoSceneViewHolder.mRightView.setVisibility(4);
                autoSceneViewHolder.mOpenCheck.setVisibility(4);
                autoSceneViewHolder.mCheckBox.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                autoSceneViewHolder.mExecuteTV.setVisibility(8);
                LogUtil.a(MyDeviceSceneFragmentNew.x, "mEditModeMap.containsKey(scene.id)" + MyDeviceSceneFragmentNew.this.M.containsKey(smartHomeScene.f) + "  mEditModeMap.get(scene.id) " + MyDeviceSceneFragmentNew.this.M.get(smartHomeScene.f) + " mEditModeMap.size " + MyDeviceSceneFragmentNew.this.M.size() + " mEditModeMap.values " + MyDeviceSceneFragmentNew.this.M.values());
                if (!MyDeviceSceneFragmentNew.this.M.containsKey(smartHomeScene.f) || !((Boolean) MyDeviceSceneFragmentNew.this.M.get(smartHomeScene.f)).booleanValue()) {
                    autoSceneViewHolder.mCheckBox.setChecked(false);
                } else {
                    autoSceneViewHolder.mCheckBox.setChecked(true);
                }
                autoSceneViewHolder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        LogUtil.a(MyDeviceSceneFragmentNew.x, "onCheckedChanged  scene.mIsLite " + smartHomeScene.t + "  childPosition " + i2 + "  isChecked  " + z);
                        if (smartHomeScene.t) {
                            MyDeviceSceneFragmentNew.this.a(i2, z, 30);
                        } else {
                            MyDeviceSceneFragmentNew.this.a(i2, z, 15);
                        }
                    }
                });
            } else {
                autoSceneViewHolder.mCheckBox.setVisibility(8);
                autoSceneViewHolder.mRightView.setVisibility(8);
                if (smartHomeScene.t) {
                    autoSceneViewHolder.mExecuteTV.setVisibility(0);
                    autoSceneViewHolder.mOpenCheck.setVisibility(8);
                } else {
                    autoSceneViewHolder.mExecuteTV.setVisibility(8);
                    autoSceneViewHolder.mOpenCheck.setVisibility(0);
                }
            }
            autoSceneViewHolder.mOpenCheck.setChecked(smartHomeScene.n);
            autoSceneViewHolder.mOpenCheck.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    if (!NetworkUtils.c()) {
                        Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.smarthome_scene_set_fail, 0).show();
                        autoSceneViewHolder.mOpenCheck.setChecked(smartHomeScene.n);
                        return;
                    }
                    smartHomeScene.n = z;
                    if (!TextUtils.isEmpty(smartHomeScene.f) && SmartHomeConfig.c) {
                        boolean unused = MyDeviceSceneFragmentNew.this.N = true;
                        MyDeviceSceneFragmentNew.this.d(smartHomeScene);
                    }
                    CoreApi.a().a(StatType.EVENT, "open_slip_button_click", "scene_all_activity", (String) null, false);
                }
            });
            autoSceneViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    if (!MyDeviceSceneFragmentNew.this.o) {
                        MyDeviceSceneFragmentNew.this.a(1);
                        if (smartHomeScene.t) {
                            MyDeviceSceneFragmentNew.this.a(i2, true, 30);
                        } else {
                            MyDeviceSceneFragmentNew.this.a(i2, true, 15);
                        }
                    }
                    return true;
                }
            });
            autoSceneViewHolder.mExecuteTV.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    MyDeviceSceneFragmentNew.this.b(smartHomeScene);
                }
            });
            if (TextUtils.equals(RecommendSceneManager.a().k(), smartHomeScene.f)) {
                autoSceneViewHolder.mNewTagTV.setVisibility(0);
            } else {
                autoSceneViewHolder.mNewTagTV.setVisibility(8);
            }
            if (i2 == a(i) - 1) {
                autoSceneViewHolder.mDivideLine.setVisibility(8);
                autoSceneViewHolder.mDivideLineBottom.setVisibility(0);
                return;
            }
            autoSceneViewHolder.mDivideLine.setVisibility(0);
            autoSceneViewHolder.mDivideLineBottom.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public void b(SceneApi.SmartHomeScene smartHomeScene) {
        RemoteSceneApi.a().a(SHApplication.getAppContext(), smartHomeScene.f, "click", (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (MyDeviceSceneFragmentNew.this.isValid()) {
                    ToastUtil.a((int) R.string.execute_success);
                }
            }

            public void onFailure(Error error) {
                if (MyDeviceSceneFragmentNew.this.isValid()) {
                    ToastUtil.a((int) R.string.execute_fail);
                }
            }
        });
        StatHelper.a(smartHomeScene.i, smartHomeScene.g);
    }

    private void h() {
        TitleBarUtil.a(TitleBarUtil.a(), this.f16976a.findViewById(R.id.title_bar_choose_device));
    }

    /* access modifiers changed from: private */
    public boolean c(SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene.l == null || smartHomeScene.l.size() == 0) {
            return false;
        }
        for (SceneApi.Condition next : smartHomeScene.l) {
            if ((next.f21522a == SceneApi.Condition.LAUNCH_TYPE.COME_LOC || next.f21522a == SceneApi.Condition.LAUNCH_TYPE.LEAVE_LOC) && next.h != null && !TextUtils.isEmpty(next.h.o)) {
                return ((next.h.o.startsWith("enter_WG_") || next.h.o.startsWith("leave_WG_")) && next.h.p != null && !next.h.p.isNull("platform") && !"android".equalsIgnoreCase(next.h.p.optString("platform"))) ? false : false;
            }
        }
        return true;
    }

    public void a(int i2) {
        DeviceSceneActivityNew deviceSceneActivityNew;
        if (this.y != i2) {
            this.y = i2;
            this.o = true;
            this.mPullRefreshLL.setPullToRefresh(false);
            if (!getActivity().isFinishing() && (deviceSceneActivityNew = (DeviceSceneActivityNew) getActivity()) != null && !deviceSceneActivityNew.isFinishing()) {
                try {
                    View chooseSceneTitleBar = deviceSceneActivityNew.getChooseSceneTitleBar();
                    AnimateLinearLayout animateLinearLayout = (AnimateLinearLayout) deviceSceneActivityNew.getChooseSceneMenuBar();
                    this.v = (TextView) chooseSceneTitleBar.findViewById(R.id.module_a_4_return_more_title);
                    this.p = (ImageView) chooseSceneTitleBar.findViewById(EditActionMode.f3057a);
                    this.q = (ImageView) chooseSceneTitleBar.findViewById(EditActionMode.b);
                    this.r = (Button) animateLinearLayout.findViewById(R.id.btn_edit_rename);
                    this.s = (Button) animateLinearLayout.findViewById(R.id.btn_edit_delete);
                    this.t = (Button) animateLinearLayout.findViewById(R.id.btn_edit_sort);
                    this.u = (Button) animateLinearLayout.findViewById(R.id.btn_edit_add_toLauncher);
                    this.u.setVisibility(8);
                    this.p.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            MyDeviceSceneFragmentNew.this.a();
                        }
                    });
                    this.q.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            LogUtil.a(MyDeviceSceneFragmentNew.x, "getCheckedCount()" + MyDeviceSceneFragmentNew.this.b() + "    mCustomScenes.size()" + MyDeviceSceneFragmentNew.this.w.size());
                            boolean z = MyDeviceSceneFragmentNew.this.b() < MyDeviceSceneFragmentNew.this.w.size() + MyDeviceSceneFragmentNew.this.c.size();
                            for (int i = 0; i < MyDeviceSceneFragmentNew.this.w.size(); i++) {
                                MyDeviceSceneFragmentNew.this.a(i, z, 15);
                            }
                            for (int i2 = 0; i2 < MyDeviceSceneFragmentNew.this.c.size(); i2++) {
                                MyDeviceSceneFragmentNew.this.a(i2, z, 30);
                            }
                            MyDeviceSceneFragmentNew.this.j.notifyDataSetChanged();
                        }
                    });
                    this.t.setVisibility(8);
                    if (this.b != null) {
                        if (this.b.size() != 0) {
                            this.t.setEnabled(true);
                            this.t.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View view) {
                                    MyDeviceSceneFragmentNew.this.getActivity().startActivityForResult(new Intent(MyDeviceSceneFragmentNew.this.getActivity(), SmarthomeSortLiteSceneActivity.class), 999);
                                    MyDeviceSceneFragmentNew.this.a();
                                }
                            });
                            chooseSceneTitleBar.setVisibility(0);
                            animateLinearLayout.setVisibility(0);
                            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(chooseSceneTitleBar, View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
                            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(animateLinearLayout, "translationY", new float[]{(float) animateLinearLayout.getHeight(), 0.0f});
                            AnimatorSet animatorSet = new AnimatorSet();
                            animatorSet.setDuration(200);
                            animatorSet.play(ofFloat).with(ofFloat2);
                            animatorSet.start();
                            animateLinearLayout.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(animateLinearLayout.getChildCount()));
                            this.j.notifyDataSetChanged();
                            if (!(this.i == null || this.i.d == null)) {
                                this.i.d.setSwipeEnabled(false);
                            }
                            if (this.mPlaceHolderView != null && this.mPlaceHolderView.getVisibility() == 8) {
                                this.mPlaceHolderView.setVisibility(0);
                            }
                            this.mSceneViewRV.setPadding(0, 0, 0, DisplayUtils.a(99.0f));
                        }
                    }
                    this.t.setEnabled(false);
                    this.t.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            MyDeviceSceneFragmentNew.this.getActivity().startActivityForResult(new Intent(MyDeviceSceneFragmentNew.this.getActivity(), SmarthomeSortLiteSceneActivity.class), 999);
                            MyDeviceSceneFragmentNew.this.a();
                        }
                    });
                    chooseSceneTitleBar.setVisibility(0);
                    animateLinearLayout.setVisibility(0);
                    ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(chooseSceneTitleBar, View.TRANSLATION_Y, new float[]{-getResources().getDimension(R.dimen.titlebar_height), 0.0f});
                    ObjectAnimator ofFloat22 = ObjectAnimator.ofFloat(animateLinearLayout, "translationY", new float[]{(float) animateLinearLayout.getHeight(), 0.0f});
                    AnimatorSet animatorSet2 = new AnimatorSet();
                    animatorSet2.setDuration(200);
                    animatorSet2.play(ofFloat3).with(ofFloat22);
                    animatorSet2.start();
                    animateLinearLayout.doMoveInAnim(0, AnimateLinearLayout.calcStepDelay(animateLinearLayout.getChildCount()));
                    this.j.notifyDataSetChanged();
                    this.i.d.setSwipeEnabled(false);
                    this.mPlaceHolderView.setVisibility(0);
                    this.mSceneViewRV.setPadding(0, 0, 0, DisplayUtils.a(99.0f));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    public void a() {
        if (this.y != 0) {
            this.y = 0;
            this.o = false;
            if (!getActivity().isFinishing() && !getActivity().isFinishing()) {
                this.mPullRefreshLL.setPullToRefresh(true);
                DeviceSceneActivityNew deviceSceneActivityNew = (DeviceSceneActivityNew) getActivity();
                if (deviceSceneActivityNew != null && !deviceSceneActivityNew.isFinishing()) {
                    try {
                        final View chooseSceneTitleBar = deviceSceneActivityNew.getChooseSceneTitleBar();
                        final AnimateLinearLayout animateLinearLayout = (AnimateLinearLayout) deviceSceneActivityNew.getChooseSceneMenuBar();
                        ViewGroup viewGroup = (ViewGroup) animateLinearLayout.getParent();
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(chooseSceneTitleBar, View.Y, new float[]{0.0f, (float) (-chooseSceneTitleBar.getHeight())});
                        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(animateLinearLayout, "translationY", new float[]{0.0f, (float) animateLinearLayout.getHeight()});
                        AnimatorSet animatorSet = new AnimatorSet();
                        animatorSet.setDuration(200);
                        animatorSet.play(ofFloat).with(ofFloat2);
                        animatorSet.start();
                        animatorSet.addListener(new Animator.AnimatorListener() {
                            public void onAnimationCancel(Animator animator) {
                            }

                            public void onAnimationRepeat(Animator animator) {
                            }

                            public void onAnimationStart(Animator animator) {
                            }

                            public void onAnimationEnd(Animator animator) {
                                chooseSceneTitleBar.clearAnimation();
                                animateLinearLayout.clearAnimation();
                                chooseSceneTitleBar.setVisibility(8);
                                animateLinearLayout.setVisibility(8);
                            }
                        });
                        this.M.clear();
                        this.j.notifyDataSetChanged();
                        if (!(this.i == null || this.i.d == null)) {
                            this.i.d.setSwipeEnabled(true);
                        }
                        if (this.mPlaceHolderView != null && this.mPlaceHolderView.getVisibility() == 0) {
                            this.mPlaceHolderView.setVisibility(8);
                        }
                        this.mSceneViewRV.setPadding(0, 0, 0, 0);
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public void a(int i2, boolean z2, final int i3) {
        final SceneApi.SmartHomeScene smartHomeScene;
        if (i3 == 15) {
            if (i2 >= this.w.size()) {
                ToastUtil.a((CharSequence) "position" + i2 + "mCustomScenes.size()" + this.w.size());
                return;
            }
            this.M.put(this.w.get(i2).f, Boolean.valueOf(z2));
            Log.e(x, "" + this.w.get(i2).f + " " + z2 + "   mEditModeMap  " + this.M.size() + "  type" + i3);
        } else if (i3 == 30) {
            if (i2 >= this.c.size()) {
                ToastUtil.a((CharSequence) "position" + i2 + "mSmartHomeScenes.size()" + this.c.size());
                return;
            }
            this.M.put(this.c.get(i2).f, Boolean.valueOf(z2));
            Log.e(x, "" + this.c.get(i2).f + " " + z2 + "   mEditModeMap  " + this.M.size() + "  type" + i3);
        }
        String str = null;
        int i4 = 0;
        for (Map.Entry next : this.M.entrySet()) {
            if (((Boolean) next.getValue()).booleanValue()) {
                str = (String) next.getKey();
                i4++;
            }
        }
        try {
            if (i4 >= this.w.size() + this.c.size()) {
                this.q.setImageResource(R.drawable.un_select_selector);
            } else {
                this.q.setImageResource(R.drawable.all_select_selector);
            }
            if (i4 <= 0) {
                this.r.setEnabled(false);
                this.s.setEnabled(false);
                this.u.setEnabled(false);
            } else if (i4 == 1) {
                if (i3 == 15) {
                    smartHomeScene = SceneManager.x().e(str);
                } else {
                    smartHomeScene = SceneManager.x().e(str);
                }
                this.r.setEnabled(true);
                this.s.setEnabled(true);
                this.r.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        MyDeviceSceneFragmentNew.this.a(smartHomeScene, i3);
                        MyDeviceSceneFragmentNew.this.a();
                        StatHelper.d();
                    }
                });
                this.s.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        new MLAlertDialog.Builder(MyDeviceSceneFragmentNew.this.getActivity()).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                MiStatInterface.a(MiStatType.CLICK.getValue(), "scene_delet_click_tab");
                                ArrayList arrayList = new ArrayList();
                                for (Map.Entry entry : MyDeviceSceneFragmentNew.this.M.entrySet()) {
                                    if (((Boolean) entry.getValue()).booleanValue()) {
                                        arrayList.add(entry.getKey());
                                    }
                                }
                                MyDeviceSceneFragmentNew.this.a(smartHomeScene, (List<String>) arrayList);
                                MyDeviceSceneFragmentNew.this.a();
                            }
                        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).a(true).b((int) R.string.scene_confirm_delete).d();
                        StatHelper.e();
                    }
                });
                if (SmartHomeSceneUtility.b(smartHomeScene)) {
                    this.u.setEnabled(true);
                } else {
                    this.u.setEnabled(false);
                }
                this.u.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        SmartHomeSceneUtility.a((Activity) MyDeviceSceneFragmentNew.this.getActivity(), smartHomeScene, SmartHomeSceneUtility.b(smartHomeScene.i));
                        MyDeviceSceneFragmentNew.this.a();
                        StatHelper.f();
                    }
                });
            } else if (i4 >= 2) {
                this.r.setEnabled(false);
                this.s.setEnabled(true);
                this.u.setEnabled(false);
            }
            this.v.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, i4, new Object[]{Integer.valueOf(i4)}));
            this.j.notifyDataSetChanged();
        } catch (Exception unused) {
        }
    }

    /* access modifiers changed from: private */
    public void a(final SceneApi.SmartHomeScene smartHomeScene, int i2) {
        String str = TextUtils.isEmpty(smartHomeScene.g) ? "" : smartHomeScene.g;
        final ClientRemarkInputView clientRemarkInputView = (ClientRemarkInputView) this.n.inflate(R.layout.client_remark_input_view, (ViewGroup) null);
        clientRemarkInputView.initialize(new ClientRemarkInputView.RenameInterface() {
            public void modifyBackName(String str) {
                final XQProgressDialog xQProgressDialog = new XQProgressDialog(MyDeviceSceneFragmentNew.this.getActivity());
                xQProgressDialog.setMessage(MyDeviceSceneFragmentNew.this.getString(R.string.changeing_back_name));
                xQProgressDialog.setCancelable(false);
                xQProgressDialog.show();
                final String str2 = smartHomeScene.g;
                smartHomeScene.g = str;
                if (SmartHomeConfig.c) {
                    RemoteSceneApi.a().c((Context) MyDeviceSceneFragmentNew.this.getActivity(), smartHomeScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (MyDeviceSceneFragmentNew.this.isValid()) {
                                SmarthomeCreateAutoSceneActivity.notifyPlugin(MyDeviceSceneFragmentNew.this.getActivity(), true, smartHomeScene, MyDeviceSceneFragmentNew.this.F);
                                xQProgressDialog.dismiss();
                                MyDeviceSceneFragmentNew.this.j.notifyDataSetChanged();
                                MyDeviceSceneFragmentNew.this.i();
                            }
                        }

                        public void onFailure(Error error) {
                            if (MyDeviceSceneFragmentNew.this.isValid()) {
                                if (error.a() == -1) {
                                    Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.smarthome_scene_has_deleted_device, 0).show();
                                } else {
                                    Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.change_back_name_fail, 0).show();
                                }
                                xQProgressDialog.dismiss();
                                smartHomeScene.g = str2;
                            }
                        }
                    });
                }
            }
        }, new MLAlertDialog.Builder(getActivity()).a((int) R.string.change_back_name).b((View) clientRemarkInputView).d(false).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(clientRemarkInputView.getEditText().getText().toString())) {
                    ToastUtil.a((int) R.string.scene_name_can_not_null);
                } else {
                    clientRemarkInputView.onConfirm(dialogInterface);
                }
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ((MLAlertDialog) dialogInterface).setAudoDismiss(true);
            }
        }).d(), str, str, false);
    }

    public int b() {
        int i2 = 0;
        if (this.M == null) {
            LogUtil.a(x, "mEditModeMap==null");
            return 0;
        }
        for (Map.Entry next : this.M.entrySet()) {
            if (((Boolean) next.getValue()).booleanValue()) {
                i2++;
            }
            LogUtil.a(x, "entry.getValue()" + next.getValue() + "  checkedCount" + i2);
        }
        return i2;
    }

    /* access modifiers changed from: package-private */
    public void a(final SceneApi.SmartHomeScene smartHomeScene) {
        new MLAlertDialog.Builder(getActivity()).a((int) R.string.retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(smartHomeScene.f);
                MyDeviceSceneFragmentNew.this.a(smartHomeScene, (List<String>) arrayList);
                dialogInterface.dismiss();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).a(true).b((int) R.string.delete_scene_fail_title).d();
    }

    /* access modifiers changed from: package-private */
    public void a(final SceneApi.SmartHomeScene smartHomeScene, final List<String> list) {
        RemoteSceneApi.a().a((Context) getActivity(), list, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (MyDeviceSceneFragmentNew.this.isValid()) {
                    SmarthomeCreateAutoSceneActivity.notifyPlugin(MyDeviceSceneFragmentNew.this.getActivity(), true, smartHomeScene, MyDeviceSceneFragmentNew.this.F);
                    Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.smarthome_scene_delete_succ, 0).show();
                    for (String g : list) {
                        SceneManager.x().g(g);
                    }
                    LogUtil.a(MyDeviceSceneFragmentNew.x, "hasChangeLiteScene" + false);
                    MyDeviceSceneFragmentNew.this.a(MyDeviceSceneFragmentNew.this.H);
                    MyDeviceSceneFragmentNew.this.g();
                    MyDeviceSceneFragmentNew.this.i();
                }
            }

            public void onFailure(Error error) {
                if (MyDeviceSceneFragmentNew.this.isValid()) {
                    if (error == null || error.a() != -2) {
                        Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.smarthome_scene_delete_fail, 0).show();
                    } else {
                        MyDeviceSceneFragmentNew.this.a(smartHomeScene);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void d(SceneApi.SmartHomeScene smartHomeScene) {
        Device n2;
        String str;
        Device n3;
        String str2;
        if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.network_fake_connected);
            return;
        }
        String str3 = null;
        boolean z2 = false;
        for (SceneApi.Condition next : smartHomeScene.l) {
            if (!(next.c == null || next.c.d == null || (n3 = SmartHomeDeviceManager.a().n(next.c.f21523a)) == null)) {
                if (n3.isSubDevice()) {
                    str2 = n3.parentId;
                } else {
                    str2 = n3.did;
                }
                if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str2))) {
                    str3 = str2;
                    z2 = true;
                }
            }
        }
        if (!z2) {
            for (SceneApi.Action next2 : smartHomeScene.k) {
                if (!(next2.e == null || next2.g.e == null || (n2 = SmartHomeDeviceManager.a().n(next2.g.e)) == null)) {
                    if (n2.isSubDevice()) {
                        str = n2.parentId;
                    } else {
                        str = n2.did;
                    }
                    if (SmartHomeSceneUtility.a(SmartHomeDeviceManager.a().n(str))) {
                        str3 = str;
                        z2 = true;
                    }
                }
            }
        }
        if (!z2 || str3 == null) {
            a(smartHomeScene, (String) null);
            return;
        }
        Device n4 = SmartHomeDeviceManager.a().n(str3);
        if (n4 == null || n4.isOnline) {
            SceneApi.a(smartHomeScene);
            a(smartHomeScene, SceneManager.f(smartHomeScene), n4);
            return;
        }
        if (this.N) {
            this.N = false;
            smartHomeScene.n = !smartHomeScene.n;
            if (this.j != null) {
                this.j.notifyDataSetChanged();
            }
        }
        Toast.makeText(getActivity(), R.string.smarthome_scene_getway_offline, 0).show();
    }

    private void a(final SceneApi.SmartHomeScene smartHomeScene, SceneInfo sceneInfo, final Device device) {
        SceneExtraBuilder.a().a(sceneInfo, (Callback<SceneInfo>) new Callback<SceneInfo>() {
            /* renamed from: a */
            public void onSuccess(SceneInfo sceneInfo) {
                if (sceneInfo != null) {
                    for (int i = 0; i < sceneInfo.mLaunchList.size(); i++) {
                        if (smartHomeScene.l.get(i).c != null && (smartHomeScene.l.get(i).c instanceof SceneApi.ConditionDeviceCommon)) {
                            ((SceneApi.ConditionDeviceCommon) smartHomeScene.l.get(i).c).m = sceneInfo.mLaunchList.get(i).mExtra;
                        }
                    }
                    for (int i2 = 0; i2 < sceneInfo.mActions.size(); i2++) {
                        if (smartHomeScene.k.get(i2).g != null && (smartHomeScene.k.get(i2).g instanceof SceneApi.SHSceneItemPayloadCommon)) {
                            ((SceneApi.SHSceneItemPayloadCommon) smartHomeScene.k.get(i2).g).f21531a = sceneInfo.mActions.get(i2).mExtra;
                        }
                    }
                }
                MyDeviceSceneFragmentNew.this.a(smartHomeScene, device.did);
            }

            public void onFailure(int i, String str) {
                LogUtil.b("lumiscene", str);
                MyDeviceSceneFragmentNew.this.a(false, smartHomeScene);
            }
        });
    }

    public void a(final SceneApi.SmartHomeScene smartHomeScene, final String str) {
        if (SmartHomeConfig.c) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RemoteSceneApi.a().a((Context) MyDeviceSceneFragmentNew.this.getActivity(), smartHomeScene, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (MyDeviceSceneFragmentNew.this.isValid()) {
                                MyDeviceSceneFragmentNew.this.a(smartHomeScene, str, jSONObject);
                            }
                        }

                        public void onFailure(Error error) {
                            if (MyDeviceSceneFragmentNew.this.isValid()) {
                                if (MyDeviceSceneFragmentNew.this.N) {
                                    boolean unused = MyDeviceSceneFragmentNew.this.N = false;
                                    smartHomeScene.n = !smartHomeScene.n;
                                    MyDeviceSceneFragmentNew.this.j.notifyDataSetChanged();
                                }
                                if (error.a() == -1) {
                                    Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.smarthome_scene_has_deleted_device, 0).show();
                                } else {
                                    Toast.makeText(MyDeviceSceneFragmentNew.this.getActivity(), R.string.smarthome_scene_change_switch_fail, 0).show();
                                }
                            }
                        }
                    });
                }
            });
        }
    }

    /* access modifiers changed from: package-private */
    public void a(final boolean z2, final SceneApi.SmartHomeScene smartHomeScene) {
        SHApplication.getGlobalHandler().post(new Runnable() {
            public void run() {
                if (!z2) {
                    if (MyDeviceSceneFragmentNew.this.N) {
                        boolean unused = MyDeviceSceneFragmentNew.this.N = false;
                        smartHomeScene.n = !smartHomeScene.n;
                        MyDeviceSceneFragmentNew.this.j.notifyDataSetChanged();
                    }
                    ToastUtil.a((int) R.string.local_sync_failed);
                } else if (MyDeviceSceneFragmentNew.this.N) {
                    boolean unused2 = MyDeviceSceneFragmentNew.this.N = false;
                }
            }
        });
    }

    /* access modifiers changed from: package-private */
    public void a(final SceneApi.SmartHomeScene smartHomeScene, String str, JSONObject jSONObject) {
        String optString = jSONObject.optString("us_id");
        boolean optBoolean = jSONObject.optBoolean("local");
        smartHomeScene.f = optString;
        if (TextUtils.isEmpty(str) || !optBoolean) {
            a(true, smartHomeScene);
        } else if (SmartHomeDeviceManager.a().n(str) != null) {
            LocalSceneBuilder.a().a(XmPluginHostApi.instance().getDeviceByDid(str), jSONObject.optJSONObject("data").toString(), (MessageCallback) new MessageCallback() {
                public void onSuccess(Intent intent) {
                    RemoteSceneApi.a().b((Context) MyDeviceSceneFragmentNew.this.getActivity(), smartHomeScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (MyDeviceSceneFragmentNew.this.isValid()) {
                                MyDeviceSceneFragmentNew.this.a(true, smartHomeScene);
                            }
                        }

                        public void onFailure(Error error) {
                            if (MyDeviceSceneFragmentNew.this.isValid()) {
                                MyDeviceSceneFragmentNew.this.a(false, smartHomeScene);
                            }
                        }
                    });
                }

                public void onFailure(int i, String str) {
                    LogUtil.b("lumiscene", str);
                    if (MyDeviceSceneFragmentNew.this.isValid()) {
                        MyDeviceSceneFragmentNew.this.a(false, smartHomeScene);
                    }
                }
            });
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        LogUtil.a(x, "onActivityResult" + i2 + "resultCode" + i3);
        if (i2 == 999 && i3 == -1 && this.B && this.A) {
            a(this.H);
            g();
        }
    }

    public HashMap<String, Boolean> c() {
        return this.M;
    }

    /* access modifiers changed from: private */
    public void i() {
        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
            public void run() {
                SceneManager.x().c((String) null);
            }
        }, 500);
    }

    public static class SceneChildViewHolder extends AbstractDraggableSwipeableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public View f17020a;
        SimpleDraweeView b;
        TextView c;
        TextView d;
        ImageView e;
        LinearLayout f;
        CheckBox g;
        SimpleDraweeView h;
        ImageView i;
        TextView j;
        @BindView(2131428895)
        View mDivideLine;
        @BindView(2131428896)
        View mDivideLineBottom;

        public SceneChildViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.f17020a = view;
            this.c = (TextView) view.findViewById(R.id.lite_scene_name_tv);
            this.d = (TextView) view.findViewById(R.id.execute_tv);
            this.e = (ImageView) view.findViewById(R.id.img_handle);
            this.f = (LinearLayout) view.findViewById(R.id.lite_scene_icon_ll);
            this.g = (CheckBox) view.findViewById(R.id.ckb_edit_selected);
            this.h = (SimpleDraweeView) view.findViewById(R.id.icon_condition);
            this.i = (ImageView) view.findViewById(R.id.scene_new_more_icon);
            this.j = (TextView) view.findViewById(R.id.new_tag);
        }

        public View k() {
            return this.f17020a;
        }
    }
}
