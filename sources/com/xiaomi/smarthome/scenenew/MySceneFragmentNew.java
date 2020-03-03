package com.xiaomi.smarthome.scenenew;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.MessageCallback;
import com.xiaomi.smarthome.device.api.SceneInfo;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.device.utils.ClientRemarkInputView;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.ApiHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.CorntabUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.AnimateLinearLayout;
import com.xiaomi.smarthome.library.common.widget.SwitchButton;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.newui.NameEditDialogHelper;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.GoLeaveHomeSceneCreateEditActivity;
import com.xiaomi.smarthome.scene.SceneFilterHelper;
import com.xiaomi.smarthome.scene.SmartHomeSceneTimerActivity;
import com.xiaomi.smarthome.scene.SmartHomeSceneUtility;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scene.view.SceneFilterManager;
import com.xiaomi.smarthome.scene.widget.SceneFilter;
import com.xiaomi.smarthome.scenenew.MySceneFragmentNew;
import com.xiaomi.smarthome.scenenew.SceneStatusManager;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeSortLiteSceneActivity;
import com.xiaomi.smarthome.scenenew.lumiscene.LocalSceneBuilder;
import com.xiaomi.smarthome.scenenew.lumiscene.SceneExtraBuilder;
import com.xiaomi.smarthome.scenenew.manager.RecommendSceneManager;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import miuipub.view.EditActionMode;
import org.json.JSONObject;

public class MySceneFragmentNew extends BaseFragment implements RecyclerViewExpandableItemManager.OnGroupCollapseListener, RecyclerViewExpandableItemManager.OnGroupExpandListener {
    public static final int g = 1;
    public static final int h = 2;
    public static final int i = 0;
    public static final int j = 999;
    private static final String y = "MySceneFragmentNew";
    private boolean A = true;
    /* access modifiers changed from: private */
    public boolean B = false;
    /* access modifiers changed from: private */
    public boolean C = false;
    /* access modifiers changed from: private */
    public boolean D = false;
    private boolean E = true;
    private RecyclerViewTouchActionGuardManager F;
    /* access modifiers changed from: private */
    public String G = null;
    /* access modifiers changed from: private */
    public SceneFilter H;
    private BroadcastReceiver I = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), HomeManager.t) && TextUtils.equals(HomeManager.x, intent.getStringExtra(HomeManager.v)) && intent.getIntExtra("result_code", -1) == ErrorCode.SUCCESS.getCode() && MySceneFragmentNew.this.H != null) {
                MySceneFragmentNew.this.H.a();
                MySceneFragmentNew.this.mPullRefreshLL.autoRefresh();
            }
        }
    };
    private BroadcastReceiver J = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), HomeManager.u) && MySceneFragmentNew.this.H != null && HomeManager.a().m().p()) {
                MySceneFragmentNew.this.H.a(HomeManager.a().m());
                MySceneFragmentNew.this.H.a();
                MySceneFragmentNew.this.H.c(HomeManager.a().m());
            }
        }
    };
    private Room K;
    private GroupData L;
    private GroupData M;
    private GroupData N;
    private SceneStatusManager.OnStatusChangeListener O = new SceneStatusManager.OnStatusChangeListener() {
        public void a(List<SceneApi.SmartHomeScene> list) {
            if (MySceneFragmentNew.this.mPullRefreshLL != null && !MySceneFragmentNew.this.mPullRefreshLL.isRefreshing()) {
                List list2 = null;
                if (MySceneFragmentNew.this.c != null) {
                    int i = 0;
                    while (true) {
                        if (i >= MySceneFragmentNew.this.c.size()) {
                            break;
                        } else if (((GroupData) MySceneFragmentNew.this.c.get(i).first).b == 15) {
                            list2 = (List) MySceneFragmentNew.this.c.get(i).second;
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
                    MySceneFragmentNew.this.l.notifyDataSetChanged();
                }
            }
        }
    };
    /* access modifiers changed from: private */
    public LiteSceneManager.IScenceListener P = new LiteSceneManager.IScenceListener() {
        public void a(int i) {
            if (MySceneFragmentNew.this.isValid()) {
                LogUtil.a(MySceneFragmentNew.y, "onRefreshScenceSuccess LiteSceneManager" + i + "LiteSceneManager.getInstance().isUpdateSuccess()" + LiteSceneManager.j().i());
                if (i == 4) {
                    MySceneFragmentNew.this.H.d();
                } else if (i == 5) {
                    boolean unused = MySceneFragmentNew.this.B = true;
                    MySceneFragmentNew.this.H.d();
                }
                if (MySceneFragmentNew.this.B && MySceneFragmentNew.this.C && MySceneFragmentNew.this.D) {
                    MySceneFragmentNew.this.h();
                    MySceneFragmentNew.this.mPullRefreshLL.refreshComplete();
                }
            }
        }

        public void b(int i) {
            if (MySceneFragmentNew.this.isValid()) {
                MySceneFragmentNew.this.mPullRefreshLL.refreshComplete();
                MySceneFragmentNew.this.l.notifyDataSetChanged();
                if (i != 4) {
                    ToastUtil.a((int) R.string.smarthome_scene_update_failed);
                }
                MySceneFragmentNew.this.h();
            }
        }
    };
    /* access modifiers changed from: private */
    public SceneManager.IScenceListener Q = new SceneManager.IScenceListener() {
        public void onRefreshScenceSuccess(int i) {
            if (MySceneFragmentNew.this.isValid()) {
                LogUtil.a(MySceneFragmentNew.y, "onRefreshScenceSuccess SceneManager" + i);
                if (i == 4) {
                    MySceneFragmentNew.this.b(i);
                    MySceneFragmentNew.this.H.d();
                    SceneLogUtil.a(getClass().getSimpleName() + "onRefreshScenceSuccess");
                } else if (i == 5) {
                    boolean unused = MySceneFragmentNew.this.C = true;
                    MySceneFragmentNew.this.H.d();
                } else if (i == 12) {
                    boolean unused2 = MySceneFragmentNew.this.D = true;
                    MySceneFragmentNew.this.H.d();
                }
                if (MySceneFragmentNew.this.B && MySceneFragmentNew.this.C && MySceneFragmentNew.this.D) {
                    MySceneFragmentNew.this.mPullRefreshLL.refreshComplete();
                    MySceneFragmentNew.this.h();
                }
            }
        }

        public void onRefreshScenceFailed(int i) {
            if (MySceneFragmentNew.this.isValid()) {
                MySceneFragmentNew.this.mPullRefreshLL.refreshComplete();
                MySceneFragmentNew.this.l.notifyDataSetChanged();
                if (!(i == 4 || i == 6)) {
                    ToastUtil.a((int) R.string.smarthome_scene_update_failed);
                }
                MySceneFragmentNew.this.h();
            }
        }
    };
    /* access modifiers changed from: private */
    public HashMap<String, Integer> R = new HashMap<>();
    /* access modifiers changed from: private */
    public boolean S = false;

    /* renamed from: a  reason: collision with root package name */
    View f21725a;
    ViewGroup b;
    List<Pair<GroupData, List<SceneApi.SmartHomeScene>>> c = new ArrayList();
    List<SceneApi.SmartHomeScene> d = new ArrayList();
    List<SceneApi.SmartHomeScene> e = new ArrayList();
    RecyclerView.Adapter<SceneChildViewHolder> f;
    SceneTabFragment k;
    SceneAdapterNew l;
    RecyclerViewExpandableItemManager m;
    @BindView(2131428501)
    TextView mEmptyTV;
    @BindView(2131428503)
    View mEmptyView;
    @BindView(2131431513)
    View mPlaceHolderView;
    @BindView(2131431674)
    PtrFrameLayout mPullRefreshLL;
    @BindView(2131432156)
    RecyclerView mSceneViewRV;
    LinearLayoutManager n = null;
    LayoutInflater o;
    public boolean p = false;
    public ImageView q;
    public ImageView r;
    public Button s;
    public Button t;
    @BindView(2131433343)
    TextView tvHomeFilter;
    @BindView(2131433470)
    TextView tvRoomTagFilter;
    public Button u;
    public Button v;
    @BindView(2131430346)
    View vFilterLayout;
    public TextView w;
    List<SceneApi.SmartHomeScene> x = new ArrayList();
    private int z = 0;

    public void a(int i2, boolean z2) {
    }

    public void b(int i2, boolean z2) {
    }

    public class AutoSceneViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private AutoSceneViewHolder f21749a;

        @UiThread
        public AutoSceneViewHolder_ViewBinding(AutoSceneViewHolder autoSceneViewHolder, View view) {
            this.f21749a = autoSceneViewHolder;
            autoSceneViewHolder.mDivideLine = Utils.findRequiredView(view, R.id.divide_line, "field 'mDivideLine'");
            autoSceneViewHolder.mDivideLineBottom = Utils.findRequiredView(view, R.id.divide_line_bottom, "field 'mDivideLineBottom'");
        }

        @CallSuper
        public void unbind() {
            AutoSceneViewHolder autoSceneViewHolder = this.f21749a;
            if (autoSceneViewHolder != null) {
                this.f21749a = null;
                autoSceneViewHolder.mDivideLine = null;
                autoSceneViewHolder.mDivideLineBottom = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public class SceneChildViewHolder_ViewBinding implements Unbinder {

        /* renamed from: a  reason: collision with root package name */
        private SceneChildViewHolder f21757a;

        @UiThread
        public SceneChildViewHolder_ViewBinding(SceneChildViewHolder sceneChildViewHolder, View view) {
            this.f21757a = sceneChildViewHolder;
            sceneChildViewHolder.mDivideLine = Utils.findRequiredView(view, R.id.divide_line, "field 'mDivideLine'");
            sceneChildViewHolder.mDivideLineBottom = Utils.findRequiredView(view, R.id.divide_line_bottom, "field 'mDivideLineBottom'");
        }

        @CallSuper
        public void unbind() {
            SceneChildViewHolder sceneChildViewHolder = this.f21757a;
            if (sceneChildViewHolder != null) {
                this.f21757a = null;
                sceneChildViewHolder.mDivideLine = null;
                sceneChildViewHolder.mDivideLineBottom = null;
                return;
            }
            throw new IllegalStateException("Bindings already cleared.");
        }
    }

    public static class GroupData {

        /* renamed from: a  reason: collision with root package name */
        public String f21750a;
        public int b;

        public GroupData(String str, int i) {
            this.f21750a = str;
            this.b = i;
        }
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SceneLogUtil.a("MySceneFragment  onCreateView");
        if (this.f21725a == null) {
            this.f21725a = layoutInflater.inflate(R.layout.fragment_my_scene_layout_new, (ViewGroup) null);
            ButterKnife.bind((Object) this, this.f21725a);
            this.o = LayoutInflater.from(getActivity());
            this.mContext = getActivity();
            this.b = (ViewGroup) ((Activity) this.mContext).getWindow().getDecorView();
            this.L = new GroupData(getResources().getString(R.string.smarthome_new_scene_by_voice), 50);
            this.M = new GroupData(getResources().getString(R.string.smarthome_new_scene_by_hand), 30);
            this.N = new GroupData(getResources().getString(R.string.smarthome_new_scene_auto), 15);
            this.H = new SceneFilter(getActivity(), (ViewGroup) ((Activity) this.mContext).getWindow().getDecorView(), this.vFilterLayout) {
                public void b(Home home) {
                    if (MySceneFragmentNew.this.tvHomeFilter == null || home == null || TextUtils.isEmpty(home.k())) {
                        MySceneFragmentNew.this.tvHomeFilter.setText(R.string.tag_all);
                    } else {
                        MySceneFragmentNew.this.tvHomeFilter.setText(home.k());
                    }
                }

                public void c(Home home) {
                    MySceneFragmentNew.this.b(0, home, (Object) null);
                }

                public void a(Home home, Room room) {
                    MySceneFragmentNew.this.b(1, home, room);
                }

                public void a(Home home, List<Room> list) {
                    MySceneFragmentNew.this.b(1, home, list);
                }

                public int b(Home home, Room room) {
                    return MySceneFragmentNew.this.c(1, home, room);
                }

                public int b(Home home, DeviceTagInterface.Category category) {
                    return MySceneFragmentNew.this.c(2, home, category);
                }

                public void a(View view) {
                    MySceneFragmentNew.this.tvRoomTagFilter.performClick();
                }

                public void a(TextView textView) {
                    MySceneFragmentNew.this.tvHomeFilter.performClick();
                }

                public void a(Home home, DeviceTagInterface.Category category) {
                    MySceneFragmentNew.this.b(2, home, category);
                }
            };
            a(bundle);
        }
        return this.f21725a;
    }

    public void onViewCreated(View view, Bundle bundle) {
        SceneLogUtil.a("MySceneFragment  onViewCreated");
        super.onViewCreated(view, bundle);
    }

    private void a(Bundle bundle) {
        this.mEmptyTV.setText(R.string.my_scene_no_data);
        i();
        b(bundle);
        e();
        if (this.E && this.mPullRefreshLL != null) {
            this.mPullRefreshLL.autoRefresh();
            this.E = false;
        }
    }

    public void onPageSelected() {
        super.onPageSelected();
        LogUtil.a(y, "onPageSelected");
        if (this.E && this.mPullRefreshLL != null) {
            this.mPullRefreshLL.autoRefresh();
            this.E = false;
        }
        if (this.l != null && (!TextUtils.isEmpty(RecommendSceneManager.a().k()) || !TextUtils.isEmpty(this.G))) {
            this.l.notifyDataSetChanged();
        }
        d();
    }

    public void a(boolean z2) {
        SHApplication.getGlobalHandler().postDelayed(new Runnable(z2) {
            private final /* synthetic */ boolean f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                MySceneFragmentNew.this.b(this.f$1);
            }
        }, 100);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(boolean z2) {
        this.H.a(this.H.c);
        this.H.e();
        if (!z2) {
            int itemCount = this.n.getItemCount();
            this.mSceneViewRV.scrollToPosition(itemCount - 1);
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    MySceneFragmentNew.this.mSceneViewRV.smoothScrollBy(0, DisplayUtils.a(99.0f));
                }
            }, 1000);
            LogUtil.a(y, "  count  " + itemCount);
        } else if (this.c != null && this.c.size() > 0 && ((GroupData) this.c.get(0).first).b == 30 && ((List) this.c.get(0).second).size() > 0) {
            this.mSceneViewRV.scrollToPosition(((List) this.c.get(0).second).size());
        }
    }

    private void b(Bundle bundle) {
        this.n = new LinearLayoutManager(this.mContext);
        this.F = new RecyclerViewTouchActionGuardManager();
        this.F.b(true);
        this.F.a(true);
        this.m = new RecyclerViewExpandableItemManager(bundle);
        this.m.a((RecyclerViewExpandableItemManager.OnGroupCollapseListener) this);
        this.m.a((RecyclerViewExpandableItemManager.OnGroupExpandListener) this);
        this.l = new SceneAdapterNew();
        this.f = this.m.a((RecyclerView.Adapter) this.l);
        this.mSceneViewRV.setLayoutManager(this.n);
        this.mSceneViewRV.setAdapter(this.f);
        this.mSceneViewRV.setOnScrollListener(new RecyclerView.OnScrollListener() {
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                FragmentActivity validActivity;
                View currentFocus;
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0 && (validActivity = MySceneFragmentNew.this.getValidActivity()) != null && MySceneFragmentNew.this.isValid() && (currentFocus = validActivity.getCurrentFocus()) != null) {
                    currentFocus.clearFocus();
                }
            }
        });
        this.F.a(this.mSceneViewRV);
        this.m.a(this.mSceneViewRV);
        this.m.d();
    }

    private void e() {
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
                        LinearLayoutManager linearLayoutManager = MySceneFragmentNew.this.n;
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
                    LinearLayoutManager linearLayoutManager2 = MySceneFragmentNew.this.n;
                    if (linearLayoutManager2 == null) {
                        return false;
                    }
                    LogUtil.a("ptrf", linearLayoutManager2.findFirstCompletelyVisibleItemPosition() + "____" + linearLayoutManager2.findFirstVisibleItemPosition());
                    if (linearLayoutManager2.findFirstCompletelyVisibleItemPosition() - 1 >= 0 || linearLayoutManager2.findFirstVisibleItemPosition() - 1 >= 0) {
                        return false;
                    }
                    return true;
                }
            }

            public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                if (SHApplication.getStateNotifier().a() != 4) {
                    MySceneFragmentNew.this.mPullRefreshLL.refreshComplete();
                    MySceneFragmentNew.this.h();
                } else if (CoreApi.a().l()) {
                    LiteSceneManager.j().b(MySceneFragmentNew.this.P);
                    SceneManager.x().a(MySceneFragmentNew.this.Q);
                    MySceneFragmentNew.this.f();
                } else {
                    CoreApi.a().a(MySceneFragmentNew.this.getContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
                        public void onCoreReady() {
                            LiteSceneManager.j().b(MySceneFragmentNew.this.P);
                            SceneManager.x().a(MySceneFragmentNew.this.Q);
                            MySceneFragmentNew.this.f();
                        }
                    });
                }
            }
        });
        this.tvHomeFilter.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MySceneFragmentNew.this.j(view);
            }
        });
        this.tvRoomTagFilter.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MySceneFragmentNew.this.i(view);
            }
        });
        this.vFilterLayout.findViewById(R.id.arrow_down_img).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MySceneFragmentNew.this.h(view);
            }
        });
        this.mEmptyView.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                MySceneFragmentNew.this.g(view);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void j(View view) {
        this.H.b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void i(View view) {
        this.H.c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void h(View view) {
        this.tvHomeFilter.performClick();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g(View view) {
        if (this.mPullRefreshLL != null) {
            this.mPullRefreshLL.refreshComplete();
            this.mPullRefreshLL.autoRefresh();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        this.B = false;
        this.C = false;
        LiteSceneManager.j().b();
        g();
        SceneManager.x().c((String) null);
        SceneManager.x().a();
    }

    private void g() {
        this.D = false;
        SceneManager.x().d();
    }

    public List<Pair<GroupData, List<SceneApi.SmartHomeScene>>> a(int i2, Home home, Object obj) {
        List<SceneApi.SmartHomeScene> list;
        List<SceneApi.SmartHomeScene> list2;
        ArrayList arrayList = new ArrayList();
        List<SceneApi.SmartHomeScene> e2 = LiteSceneManager.j().e();
        if (!LiteSceneManager.j().i() && e2.size() == 0) {
            e2 = LiteSceneManager.j().f();
        }
        if (!SceneManager.x().n()) {
            list = SceneManager.x().j();
        } else {
            list = SceneManager.x().h();
        }
        if (!SceneManager.x().o()) {
            list2 = SceneManager.x().k();
        } else {
            list2 = SceneManager.x().i();
        }
        this.d.clear();
        this.e.clear();
        this.x.clear();
        switch (i2) {
            case 0:
                this.d = SceneFilterHelper.a(home, e2);
                this.x = SceneFilterHelper.a(home, list);
                this.e = SceneFilterHelper.a(home, list2);
                break;
            case 1:
                if (!(obj instanceof Room)) {
                    List<Room> list3 = (List) obj;
                    if (list3 != null) {
                        for (Room room : list3) {
                            this.d.addAll(SceneFilterHelper.a(home, room, e2));
                            this.x.addAll(SceneFilterHelper.a(home, room, list));
                            this.e.addAll(SceneFilterHelper.a(home, room, list2));
                        }
                        break;
                    }
                } else {
                    Room room2 = (Room) obj;
                    this.d = SceneFilterHelper.a(home, room2, e2);
                    this.x = SceneFilterHelper.a(home, room2, list);
                    this.e = SceneFilterHelper.a(home, room2, list2);
                    break;
                }
                break;
            case 2:
                DeviceTagInterface.Category category = (DeviceTagInterface.Category) obj;
                this.d = SceneFilterHelper.a(home, category, e2);
                this.x = SceneFilterHelper.a(home, category, list);
                this.e = SceneFilterHelper.a(home, category, list2);
                break;
        }
        if (this.e.size() > 0) {
            arrayList.add(new Pair(this.L, this.e));
        }
        if (this.d.size() > 0) {
            arrayList.add(new Pair(this.M, this.d));
        }
        if (this.x.size() > 0) {
            arrayList.add(new Pair(this.N, this.x));
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void b(int i2, Home home, Object obj) {
        this.c.clear();
        this.c.addAll(a(i2, home, obj));
        h();
        this.l.notifyDataSetChanged();
        for (int i3 = 0; i3 < this.c.size(); i3++) {
            if (!this.m.e(i3)) {
                this.m.a(i3);
            }
        }
        if (!TextUtils.isEmpty(RecommendSceneManager.a().k())) {
            this.G = RecommendSceneManager.a().k();
            RecommendSceneManager.a().b((String) null);
            if (LiteSceneManager.j().a(this.G) != null) {
                a(true);
            } else {
                a(false);
            }
        }
    }

    /* access modifiers changed from: private */
    public int c(int i2, Home home, Object obj) {
        List<SceneApi.SmartHomeScene> list;
        List<SceneApi.SmartHomeScene> list2;
        List<SceneApi.SmartHomeScene> e2 = LiteSceneManager.j().e();
        if (!LiteSceneManager.j().i() && e2.size() == 0) {
            e2 = LiteSceneManager.j().f();
        }
        if (!SceneManager.x().n()) {
            list = SceneManager.x().j();
        } else {
            list = SceneManager.x().h();
        }
        if (!SceneManager.x().o()) {
            list2 = SceneManager.x().k();
        } else {
            list2 = SceneManager.x().i();
        }
        return SceneFilterHelper.a(i2, home, obj, e2) + 0 + SceneFilterHelper.a(i2, home, obj, list) + SceneFilterHelper.a(i2, home, obj, list2);
    }

    public void onResume() {
        SceneLogUtil.a("MySceneFragment  onResume");
        super.onResume();
        LogUtil.a(y, "onresume");
        if (this.A) {
            this.A = false;
        }
        if (this.l != null) {
            d();
            this.l.notifyDataSetChanged();
        }
    }

    public void onPause() {
        super.onPause();
        LogUtil.a(y, "onPause");
        RecommendSceneManager.a().b((String) null);
        this.G = null;
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a(y, "onCreate");
        LiteSceneManager.j().b(this.P);
        SceneManager.x().a(this.Q);
        new IntentFilter("action_on_login_success").addAction("action_on_logout");
        SceneStatusManager.a().a(this.O);
        SceneStatusManager.a().c();
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.J, new IntentFilter(HomeManager.u));
    }

    public boolean onBackPressed() {
        LogUtil.a(y, "onBackPressed");
        if (this.z == 1) {
            a();
            return true;
        }
        if (!this.H.f()) {
            getActivity().finish();
        }
        return true;
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0019 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onDestroy() {
        /*
            r2 = this;
            super.onDestroy()
            com.xiaomi.smarthome.lite.scene.LiteSceneManager r0 = com.xiaomi.smarthome.lite.scene.LiteSceneManager.j()
            com.xiaomi.smarthome.lite.scene.LiteSceneManager$IScenceListener r1 = r2.P
            r0.c((com.xiaomi.smarthome.lite.scene.LiteSceneManager.IScenceListener) r1)
            android.content.Context r0 = r2.getContext()     // Catch:{ Exception -> 0x0019 }
            android.support.v4.content.LocalBroadcastManager r0 = android.support.v4.content.LocalBroadcastManager.getInstance(r0)     // Catch:{ Exception -> 0x0019 }
            android.content.BroadcastReceiver r1 = r2.I     // Catch:{ Exception -> 0x0019 }
            r0.unregisterReceiver(r1)     // Catch:{ Exception -> 0x0019 }
        L_0x0019:
            android.content.Context r0 = r2.getContext()     // Catch:{ Exception -> 0x0026 }
            android.support.v4.content.LocalBroadcastManager r0 = android.support.v4.content.LocalBroadcastManager.getInstance(r0)     // Catch:{ Exception -> 0x0026 }
            android.content.BroadcastReceiver r1 = r2.J     // Catch:{ Exception -> 0x0026 }
            r0.unregisterReceiver(r1)     // Catch:{ Exception -> 0x0026 }
        L_0x0026:
            com.xiaomi.router.api.SceneManager r0 = com.xiaomi.router.api.SceneManager.x()
            com.xiaomi.router.api.SceneManager$IScenceListener r1 = r2.Q
            r0.b((com.xiaomi.router.api.SceneManager.IScenceListener) r1)
            com.xiaomi.smarthome.scenenew.SceneStatusManager r0 = com.xiaomi.smarthome.scenenew.SceneStatusManager.a()
            com.xiaomi.smarthome.scenenew.SceneStatusManager$OnStatusChangeListener r1 = r2.O
            r0.b((com.xiaomi.smarthome.scenenew.SceneStatusManager.OnStatusChangeListener) r1)
            com.xiaomi.smarthome.scenenew.SceneStatusManager r0 = com.xiaomi.smarthome.scenenew.SceneStatusManager.a()
            r0.b()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.scenenew.MySceneFragmentNew.onDestroy():void");
    }

    /* access modifiers changed from: private */
    public void h() {
        if (this.c.size() == 0) {
            this.mEmptyView.setVisibility(0);
            this.mSceneViewRV.setVisibility(8);
            return;
        }
        this.mSceneViewRV.setVisibility(0);
        this.mEmptyView.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void b(int i2) {
        if (i2 == 4) {
            this.x.clear();
            this.x.addAll(SceneManager.x().j());
        } else {
            this.x.clear();
            this.x.addAll(SceneManager.x().h());
        }
        SceneLogUtil.a("refreshCustomScenes---type---" + i2 + " mCustomScenes.size() " + this.x.size());
        SceneFilterManager.c().a(this.x);
    }

    class AutoSceneViewHolder extends AbstractDraggableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        LinearLayout f21748a;
        SimpleDraweeView b;
        ImageView c;
        TextView d;
        CheckBox e;
        ImageView f;
        SwitchButton g;
        View h;
        TextView i;
        TextView j;
        TextView k;
        TextView l;
        @BindView(2131428895)
        View mDivideLine;
        @BindView(2131428896)
        View mDivideLineBottom;
        private int n;

        public AutoSceneViewHolder(View view) {
            super(view);
            ButterKnife.bind((Object) this, view);
            this.c = (ImageView) view.findViewById(R.id.icon_condition_more);
            this.f = (ImageView) view.findViewById(R.id.right_view);
            this.i = (TextView) view.findViewById(R.id.new_tag);
            this.g = (SwitchButton) view.findViewById(R.id.open_check);
            this.b = (SimpleDraweeView) view.findViewById(R.id.icon_condition);
            this.f21748a = (LinearLayout) view.findViewById(R.id.action_ll);
            this.d = (TextView) view.findViewById(R.id.tv_scene_name);
            this.e = (CheckBox) view.findViewById(R.id.ckb_edit_selected);
            this.h = view.findViewById(R.id.scene_new_more_icon);
            this.j = (TextView) view.findViewById(R.id.execute_tv);
            this.k = (TextView) view.findViewById(R.id.txt_happen_time);
            if (this.k != null) {
                this.k.setTypeface(FontManager.a("fonts/KLight.ttf"));
            }
            this.l = (TextView) view.findViewById(R.id.txt_tomorrow_tag);
        }

        public void c_(int i2) {
            this.n = i2;
        }

        public int K_() {
            return this.n;
        }
    }

    class GroupViewHolder extends AbstractDraggableItemViewHolder implements ExpandableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f21751a;
        View b;
        private int d;

        public GroupViewHolder(View view) {
            super(view);
            this.f21751a = (TextView) view.findViewById(R.id.group_tv);
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
        private int b;

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
            this.b = DisplayUtils.b(MySceneFragmentNew.this.getContext());
        }

        public int a() {
            LogUtil.a(MySceneFragmentNew.y, "getGroupCount" + MySceneFragmentNew.this.c.size());
            return MySceneFragmentNew.this.c.size();
        }

        public int c(int i) {
            if (MySceneFragmentNew.this.c == null || MySceneFragmentNew.this.c.size() == 0) {
                return super.c(i);
            }
            return ((GroupData) MySceneFragmentNew.this.c.get(i).first).b;
        }

        public int a(int i) {
            if (MySceneFragmentNew.this.c == null || MySceneFragmentNew.this.c.size() == 0 || MySceneFragmentNew.this.c.get(i).second == null) {
                return 0;
            }
            return ((List) MySceneFragmentNew.this.c.get(i).second).size();
        }

        public int d(int i, int i2) {
            if (MySceneFragmentNew.this.c == null && MySceneFragmentNew.this.c.size() == 0) {
                return super.d(i, i2);
            }
            return ((GroupData) MySceneFragmentNew.this.c.get(i).first).b;
        }

        /* renamed from: c */
        public GroupViewHolder a(ViewGroup viewGroup, int i) {
            LogUtil.a(MySceneFragmentNew.y, "onCreateGroupViewHolder" + MySceneFragmentNew.this.c.size());
            return new GroupViewHolder(MySceneFragmentNew.this.o.inflate(R.layout.fragment_my_scene_group_item, (ViewGroup) null));
        }

        /* renamed from: d */
        public AutoSceneViewHolder b(ViewGroup viewGroup, int i) {
            View view;
            LogUtil.a(MySceneFragmentNew.y, "onCreateChildViewHolder" + MySceneFragmentNew.this.c.size());
            if (i == 50) {
                view = MySceneFragmentNew.this.o.inflate(R.layout.item_voice_scene, (ViewGroup) null);
            } else {
                view = MySceneFragmentNew.this.o.inflate(R.layout.my_scene_fragment_scene_item, (ViewGroup) null);
            }
            LogUtil.a("youhua", "SceneAdapteronCreateViewHolder  viewType" + i);
            return new AutoSceneViewHolder(view);
        }

        /* renamed from: a */
        public void b(GroupViewHolder groupViewHolder, int i, int i2) {
            Drawable drawable;
            LogUtil.a(MySceneFragmentNew.y, "onBindGroupViewHolder" + MySceneFragmentNew.this.c.size());
            if (MySceneFragmentNew.this.c != null && MySceneFragmentNew.this.c.size() != 0 && ((GroupData) MySceneFragmentNew.this.c.get(i).first) != null) {
                if (i2 == 30) {
                    drawable = MySceneFragmentNew.this.getResources().getDrawable(R.drawable.lite_scene_hand_icon);
                } else if (i2 == 15) {
                    drawable = MySceneFragmentNew.this.getResources().getDrawable(R.drawable.auto_scene_icon);
                } else {
                    drawable = i2 == 50 ? MySceneFragmentNew.this.getResources().getDrawable(R.drawable.voice_created_scene_icon) : null;
                }
                if (drawable != null) {
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    groupViewHolder.f21751a.setCompoundDrawables(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
                }
                groupViewHolder.b.setVisibility(0);
                groupViewHolder.f21751a.setText(((GroupData) MySceneFragmentNew.this.c.get(i).first).f21750a);
            }
        }

        /* renamed from: a */
        public void b(final AutoSceneViewHolder autoSceneViewHolder, int i, final int i2, final int i3) {
            final SceneApi.SmartHomeScene smartHomeScene = (i < 0 || i >= a() || i2 < 0 || i2 >= a(i)) ? null : (SceneApi.SmartHomeScene) ((List) MySceneFragmentNew.this.c.get(i).second).get(i2);
            if (smartHomeScene != null) {
                if (i3 == 50) {
                    autoSceneViewHolder.d.setText(smartHomeScene.g);
                    autoSceneViewHolder.d.setMaxWidth(DisplayUtils.b(MySceneFragmentNew.this.getContext()));
                    autoSceneViewHolder.l.setMaxLines(1);
                    if (SmartHomeSceneUtility.a(smartHomeScene, autoSceneViewHolder.f21748a) > 3) {
                        autoSceneViewHolder.h.setVisibility(0);
                    } else {
                        autoSceneViewHolder.h.setVisibility(8);
                    }
                    if (smartHomeScene.l == null || smartHomeScene.l.size() <= 0 || smartHomeScene.l.get(0).b == null) {
                        autoSceneViewHolder.k.setText("");
                        autoSceneViewHolder.l.setVisibility(8);
                    } else {
                        autoSceneViewHolder.k.setText(SmartHomeSceneTimerActivity.getTimerHint(MySceneFragmentNew.this.getActivity(), smartHomeScene.l.get(0).b.f21527a));
                        String b2 = smartHomeScene.l.get(0).b.f21527a.b((Context) MySceneFragmentNew.this.getActivity());
                        if (TextUtils.isEmpty(b2)) {
                            autoSceneViewHolder.l.setVisibility(8);
                        } else {
                            autoSceneViewHolder.l.setVisibility(0);
                            autoSceneViewHolder.l.setText(b2);
                            int a2 = a(autoSceneViewHolder.l) + DisplayUtils.a(10.0f);
                            if (b2.length() > 13 && a(autoSceneViewHolder.d) + a2 + DisplayUtils.a(33.0f) > this.b) {
                                autoSceneViewHolder.l.setMaxLines(2);
                                a2 = DisplayUtils.a(60.0f);
                            }
                            autoSceneViewHolder.l.setMaxWidth(a2);
                            autoSceneViewHolder.d.setMaxWidth((this.b - a2) - DisplayUtils.a(33.0f));
                        }
                    }
                    if (MySceneFragmentNew.this.p) {
                        autoSceneViewHolder.e.setVisibility(0);
                        if (MySceneFragmentNew.this.R.containsKey(smartHomeScene.f)) {
                            autoSceneViewHolder.e.setChecked(true);
                        } else {
                            autoSceneViewHolder.e.setChecked(false);
                        }
                    } else {
                        autoSceneViewHolder.e.setVisibility(4);
                    }
                    if (MySceneFragmentNew.this.p) {
                        autoSceneViewHolder.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                                LogUtil.a(MySceneFragmentNew.y, "onCheckedChanged  scene.mIsLite " + smartHomeScene.t + "  childPosition " + i2 + "  isChecked  " + z);
                                if (i3 == 30) {
                                    MySceneFragmentNew.this.a(i2, z, 30);
                                } else if (i3 == 50) {
                                    MySceneFragmentNew.this.a(i2, z, 50);
                                } else {
                                    MySceneFragmentNew.this.a(i2, z, 15);
                                }
                            }
                        });
                    }
                    autoSceneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (MySceneFragmentNew.this.p) {
                                autoSceneViewHolder.e.setChecked(!autoSceneViewHolder.e.isChecked());
                            }
                        }
                    });
                    autoSceneViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        public boolean onLongClick(View view) {
                            if (!MySceneFragmentNew.this.p) {
                                MySceneFragmentNew.this.a(1);
                                MySceneFragmentNew.this.a(i2, true, i3);
                            }
                            return true;
                        }
                    });
                    return;
                }
                if (smartHomeScene.l == null || smartHomeScene.l.size() == 0 || SceneManager.x().g(smartHomeScene)) {
                    autoSceneViewHolder.itemView.setOnClickListener(new View.OnClickListener(autoSceneViewHolder, smartHomeScene) {
                        private final /* synthetic */ MySceneFragmentNew.AutoSceneViewHolder f$1;
                        private final /* synthetic */ SceneApi.SmartHomeScene f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void onClick(View view) {
                            MySceneFragmentNew.SceneAdapterNew.this.a(this.f$1, this.f$2, view);
                        }
                    });
                    autoSceneViewHolder.d.setTextColor(MySceneFragmentNew.this.getResources().getColor(R.color.black_80_transparent));
                } else {
                    autoSceneViewHolder.itemView.setOnClickListener(new View.OnClickListener(autoSceneViewHolder) {
                        private final /* synthetic */ MySceneFragmentNew.AutoSceneViewHolder f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void onClick(View view) {
                            MySceneFragmentNew.SceneAdapterNew.this.a(this.f$1, view);
                        }
                    });
                    autoSceneViewHolder.d.setTextColor(MySceneFragmentNew.this.getResources().getColor(R.color.black_40_transparent));
                }
                if (smartHomeScene.l.size() > 0) {
                    autoSceneViewHolder.b.setVisibility(0);
                    autoSceneViewHolder.b.setHierarchy(new GenericDraweeHierarchyBuilder(autoSceneViewHolder.b.getResources()).setFadeDuration(200).setPlaceholderImage(autoSceneViewHolder.b.getResources().getDrawable(R.drawable.device_list_phone_no)).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
                    if (smartHomeScene == null || smartHomeScene.l.size() <= 0) {
                        autoSceneViewHolder.b.setImageURI(CommonUtils.c((int) R.drawable.intelligence_icon_default_nor));
                    } else {
                        SmartHomeSceneUtility.a(autoSceneViewHolder.b, smartHomeScene.l.get(0));
                    }
                    if (smartHomeScene.l.size() > 1) {
                        autoSceneViewHolder.c.setVisibility(0);
                    } else {
                        autoSceneViewHolder.c.setVisibility(8);
                    }
                    autoSceneViewHolder.c.setImageResource(SmartHomeSceneUtility.f(smartHomeScene.q));
                } else {
                    autoSceneViewHolder.b.setVisibility(8);
                    autoSceneViewHolder.c.setVisibility(8);
                }
                if (SmartHomeSceneUtility.a(smartHomeScene, autoSceneViewHolder.f21748a) > 3) {
                    autoSceneViewHolder.h.setVisibility(0);
                } else {
                    autoSceneViewHolder.h.setVisibility(8);
                }
                autoSceneViewHolder.d.setText(smartHomeScene.g);
                if (smartHomeScene.r != 0) {
                    autoSceneViewHolder.f.setImageResource(R.drawable.exclamation_mark);
                } else {
                    autoSceneViewHolder.f.setVisibility(4);
                }
                if (MySceneFragmentNew.this.p) {
                    autoSceneViewHolder.e.setVisibility(0);
                    autoSceneViewHolder.f.setVisibility(4);
                    autoSceneViewHolder.g.setVisibility(4);
                    autoSceneViewHolder.e.setOnCheckedChangeListener((CompoundButton.OnCheckedChangeListener) null);
                    autoSceneViewHolder.j.setVisibility(8);
                    LogUtil.a(MySceneFragmentNew.y, "mEditModeMap.containsKey(scene.id)" + MySceneFragmentNew.this.R.containsKey(smartHomeScene.f) + "  mEditModeMap.get(scene.id) " + MySceneFragmentNew.this.R.get(smartHomeScene.f) + " mEditModeMap.size " + MySceneFragmentNew.this.R.size() + " mEditModeMap.values " + MySceneFragmentNew.this.R.values());
                    if (MySceneFragmentNew.this.R.containsKey(smartHomeScene.f)) {
                        autoSceneViewHolder.e.setChecked(true);
                    } else {
                        autoSceneViewHolder.e.setChecked(false);
                    }
                    autoSceneViewHolder.e.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(smartHomeScene, i2, i3) {
                        private final /* synthetic */ SceneApi.SmartHomeScene f$1;
                        private final /* synthetic */ int f$2;
                        private final /* synthetic */ int f$3;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                            this.f$3 = r4;
                        }

                        public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                            MySceneFragmentNew.SceneAdapterNew.this.a(this.f$1, this.f$2, this.f$3, compoundButton, z);
                        }
                    });
                } else {
                    autoSceneViewHolder.e.setVisibility(8);
                    autoSceneViewHolder.f.setVisibility(8);
                    if (smartHomeScene.t) {
                        autoSceneViewHolder.j.setVisibility(0);
                        autoSceneViewHolder.g.setVisibility(8);
                    } else {
                        autoSceneViewHolder.j.setVisibility(8);
                        autoSceneViewHolder.g.setVisibility(0);
                    }
                }
                autoSceneViewHolder.g.setChecked(smartHomeScene.n);
                autoSceneViewHolder.g.setOnPerformCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(autoSceneViewHolder, smartHomeScene) {
                    private final /* synthetic */ MySceneFragmentNew.AutoSceneViewHolder f$1;
                    private final /* synthetic */ SceneApi.SmartHomeScene f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                        MySceneFragmentNew.SceneAdapterNew.this.a(this.f$1, this.f$2, compoundButton, z);
                    }
                });
                autoSceneViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener(smartHomeScene, i2) {
                    private final /* synthetic */ SceneApi.SmartHomeScene f$1;
                    private final /* synthetic */ int f$2;

                    {
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final boolean onLongClick(View view) {
                        return MySceneFragmentNew.SceneAdapterNew.this.a(this.f$1, this.f$2, view);
                    }
                });
                autoSceneViewHolder.j.setOnClickListener(new View.OnClickListener(smartHomeScene) {
                    private final /* synthetic */ SceneApi.SmartHomeScene f$1;

                    {
                        this.f$1 = r2;
                    }

                    public final void onClick(View view) {
                        MySceneFragmentNew.SceneAdapterNew.this.a(this.f$1, view);
                    }
                });
                if (TextUtils.equals(MySceneFragmentNew.this.G, smartHomeScene.f)) {
                    autoSceneViewHolder.i.setVisibility(0);
                } else {
                    autoSceneViewHolder.i.setVisibility(8);
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
        public /* synthetic */ void a(AutoSceneViewHolder autoSceneViewHolder, SceneApi.SmartHomeScene smartHomeScene, View view) {
            if (MySceneFragmentNew.this.p) {
                autoSceneViewHolder.e.setChecked(!autoSceneViewHolder.e.isChecked());
            } else if (SceneManager.x().b(smartHomeScene) || SceneManager.x().c(smartHomeScene)) {
                Intent intent = new Intent(MySceneFragmentNew.this.getActivity(), GoLeaveHomeSceneCreateEditActivity.class);
                intent.putExtra(GoLeaveHomeSceneCreateEditActivity.SCENE_ID, smartHomeScene.f);
                MySceneFragmentNew.this.startActivity(intent);
            } else {
                Intent intent2 = new Intent(MySceneFragmentNew.this.getActivity(), SmarthomeCreateAutoSceneActivity.class);
                CreateSceneManager.a().a(smartHomeScene);
                MySceneFragmentNew.this.getActivity().startActivityForResult(intent2, 999);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(AutoSceneViewHolder autoSceneViewHolder, View view) {
            if (MySceneFragmentNew.this.p) {
                autoSceneViewHolder.e.setChecked(!autoSceneViewHolder.e.isChecked());
            } else {
                Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.cannot_edit_ios_scene, 0).show();
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(SceneApi.SmartHomeScene smartHomeScene, int i, int i2, CompoundButton compoundButton, boolean z) {
            LogUtil.a(MySceneFragmentNew.y, "onCheckedChanged  scene.mIsLite " + smartHomeScene.t + "  childPosition " + i + "  isChecked  " + z);
            if (i2 == 30) {
                MySceneFragmentNew.this.a(i, z, 30);
            } else if (i2 == 50) {
                MySceneFragmentNew.this.a(i, z, 50);
            } else {
                MySceneFragmentNew.this.a(i, z, 15);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(AutoSceneViewHolder autoSceneViewHolder, SceneApi.SmartHomeScene smartHomeScene, CompoundButton compoundButton, boolean z) {
            if (!NetworkUtils.c()) {
                Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.smarthome_scene_set_fail, 0).show();
                autoSceneViewHolder.g.setChecked(smartHomeScene.n);
                return;
            }
            smartHomeScene.n = z;
            if (!TextUtils.isEmpty(smartHomeScene.f) && SmartHomeConfig.c) {
                boolean unused = MySceneFragmentNew.this.S = true;
                MySceneFragmentNew.this.b(smartHomeScene);
            }
            CoreApi.a().a(StatType.EVENT, "open_slip_button_click", "scene_all_activity", (String) null, false);
        }

        /* access modifiers changed from: private */
        public /* synthetic */ boolean a(SceneApi.SmartHomeScene smartHomeScene, int i, View view) {
            if (!MySceneFragmentNew.this.p) {
                MySceneFragmentNew.this.a(1);
                if (smartHomeScene.t) {
                    MySceneFragmentNew.this.a(i, true, 30);
                } else {
                    MySceneFragmentNew.this.a(i, true, 15);
                }
            }
            return true;
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(SceneApi.SmartHomeScene smartHomeScene, View view) {
            MySceneFragmentNew.this.a(smartHomeScene);
        }

        private int a(TextView textView) {
            if (textView == null) {
                return 0;
            }
            Rect rect = new Rect();
            CharSequence text = textView.getText();
            textView.getPaint().getTextBounds(text.toString(), 0, text.length(), rect);
            double measureText = (double) textView.getPaint().measureText(text, 0, text.length());
            Double.isNaN(measureText);
            return (int) (measureText + 0.5d);
        }
    }

    /* access modifiers changed from: private */
    public void a(SceneApi.SmartHomeScene smartHomeScene) {
        if (smartHomeScene != null) {
            RemoteSceneApi.a().a(SHApplication.getAppContext(), smartHomeScene.f, "click", (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                /* renamed from: a */
                public void onSuccess(Void voidR) {
                    if (MySceneFragmentNew.this.isValid()) {
                        ToastUtil.a((int) R.string.execute_success);
                    }
                }

                public void onFailure(Error error) {
                    if (MySceneFragmentNew.this.isValid()) {
                        ToastUtil.a((int) R.string.execute_fail);
                    }
                }
            });
            StatHelper.a(smartHomeScene.i, smartHomeScene.g);
        }
    }

    private void i() {
        TitleBarUtil.a(TitleBarUtil.a(), this.f21725a.findViewById(R.id.title_bar_choose_device));
        j();
    }

    private void j() {
        SceneFilterManager.c().h();
        this.x.addAll(SceneManager.x().h());
    }

    public void a(int i2) {
        SmartHomeMainActivity smartHomeMainActivity;
        if (this.z != i2) {
            this.z = i2;
            this.p = true;
            this.mPullRefreshLL.setPullToRefresh(false);
            if (!getActivity().isFinishing() && (smartHomeMainActivity = (SmartHomeMainActivity) getActivity()) != null && !smartHomeMainActivity.isFinishing()) {
                try {
                    View chooseSceneTitleBar = smartHomeMainActivity.getChooseSceneTitleBar();
                    AnimateLinearLayout animateLinearLayout = (AnimateLinearLayout) smartHomeMainActivity.getChooseSceneMenuBar();
                    this.w = (TextView) chooseSceneTitleBar.findViewById(R.id.module_a_4_return_more_title);
                    TextView textView = this.w;
                    Resources resources = getResources();
                    int size = this.R == null ? 0 : this.R.size();
                    Object[] objArr = new Object[1];
                    objArr[0] = Integer.valueOf(this.R == null ? 0 : this.R.size());
                    textView.setText(resources.getQuantityString(R.plurals.selected_cnt_tips, size, objArr));
                    this.q = (ImageView) chooseSceneTitleBar.findViewById(EditActionMode.f3057a);
                    this.r = (ImageView) chooseSceneTitleBar.findViewById(EditActionMode.b);
                    this.s = (Button) animateLinearLayout.findViewById(R.id.btn_edit_rename);
                    this.s.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            MySceneFragmentNew.this.f(view);
                        }
                    });
                    this.t = (Button) animateLinearLayout.findViewById(R.id.btn_edit_delete);
                    this.t.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            MySceneFragmentNew.this.e(view);
                        }
                    });
                    this.u = (Button) animateLinearLayout.findViewById(R.id.btn_edit_sort);
                    this.u.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            MySceneFragmentNew.this.d(view);
                        }
                    });
                    this.v = (Button) animateLinearLayout.findViewById(R.id.btn_edit_add_toLauncher);
                    this.v.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            MySceneFragmentNew.this.c(view);
                        }
                    });
                    this.q.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            MySceneFragmentNew.this.b(view);
                        }
                    });
                    this.r.setOnClickListener(new View.OnClickListener() {
                        public final void onClick(View view) {
                            MySceneFragmentNew.this.a(view);
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
                    this.l.notifyDataSetChanged();
                    if (!(this.k == null || this.k.d == null)) {
                        this.k.d.setSwipeEnabled(false);
                    }
                    if (this.mPlaceHolderView != null && this.mPlaceHolderView.getVisibility() == 8) {
                        this.mPlaceHolderView.setVisibility(0);
                    }
                    if (this.tvRoomTagFilter != null) {
                        this.tvRoomTagFilter.setEnabled(false);
                    }
                    this.mSceneViewRV.setPadding(0, 0, 0, DisplayUtils.a(44.0f));
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f(View view) {
        SceneApi.SmartHomeScene a2;
        if (this.R != null && this.R.size() != 0 && (a2 = a((String) this.R.entrySet().iterator().next().getKey(), ((Integer) this.R.entrySet().iterator().next().getValue()).intValue())) != null) {
            a(a2, this.R.get(a2.f).intValue());
            a();
            StatHelper.d();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e(View view) {
        new MLAlertDialog.Builder(getActivity()).a((int) R.string.confirm, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                MySceneFragmentNew.this.b(dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$MySceneFragmentNew$2bihslVRPQgawKwwJbHAXYDDnyw.INSTANCE).a(true).b((int) R.string.scene_confirm_delete).d();
        StatHelper.e();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        k();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        getActivity().startActivityForResult(new Intent(getActivity(), SmarthomeSortLiteSceneActivity.class), 999);
        a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        if (this.R != null && this.R.size() != 0) {
            ArrayList arrayList = new ArrayList();
            for (Map.Entry next : this.R.entrySet()) {
                SceneApi.SmartHomeScene a2 = a((String) next.getKey(), ((Integer) next.getValue()).intValue());
                if (a2 != null) {
                    arrayList.add(a2);
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                SceneApi.SmartHomeScene smartHomeScene = (SceneApi.SmartHomeScene) arrayList.get(i2);
                if (smartHomeScene != null) {
                    SmartHomeSceneUtility.a((Activity) getActivity(), smartHomeScene, SmartHomeSceneUtility.b(smartHomeScene.i));
                }
            }
            a();
            StatHelper.f();
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        LogUtil.a(y, "getCheckedCount()" + b() + "    mCustomScenes.size()" + this.x.size());
        LogUtil.a(y, "getCheckedCount()" + b() + "    mSmartHomeScenes.size()" + this.d.size());
        boolean z2 = b() < (this.x.size() + this.d.size()) + this.e.size();
        for (int i2 = 0; i2 < this.x.size(); i2++) {
            a(i2, z2, 15);
        }
        for (int i3 = 0; i3 < this.d.size(); i3++) {
            a(i3, z2, 30);
        }
        for (int i4 = 0; i4 < this.e.size(); i4++) {
            a(i4, z2, 50);
        }
        this.l.notifyDataSetChanged();
    }

    private void k() {
        MiStatInterface.a(MiStatType.CLICK.getValue(), "scene_delet_click_tab");
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<String, Integer> key : this.R.entrySet()) {
            arrayList.add(key.getKey());
        }
        b((List<String>) arrayList);
        a();
    }

    /* access modifiers changed from: private */
    public void b(final List<String> list) {
        RemoteSceneApi.a().a((Context) getActivity(), list, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                if (MySceneFragmentNew.this.isValid()) {
                    boolean z = false;
                    Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.smarthome_scene_delete_succ, 0).show();
                    for (String str : list) {
                        SceneManager.x().g(str);
                        z |= LiteSceneManager.j().b(str);
                    }
                    LogUtil.a(MySceneFragmentNew.y, "hasChangeLiteScene" + z);
                    SceneManager.x().w();
                    MySceneFragmentNew.this.H.d();
                    MySceneFragmentNew.this.b(-1);
                    MySceneFragmentNew.this.h();
                    if (z) {
                        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                            public final void run() {
                                MySceneFragmentNew.AnonymousClass11.this.a();
                            }
                        });
                    }
                }
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a() {
                if (MySceneFragmentNew.this.c(30) != null) {
                    LiteSceneManager.j().a((AsyncCallback) null);
                }
            }

            public void onFailure(Error error) {
                if (MySceneFragmentNew.this.isValid()) {
                    if (error == null || error.a() != -2) {
                        Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.smarthome_scene_delete_fail, 0).show();
                    } else {
                        MySceneFragmentNew.this.a((List<String>) list);
                    }
                }
            }
        });
    }

    public void a() {
        if (this.z != 0) {
            this.z = 0;
            this.p = false;
            if (!getActivity().isFinishing() && !getActivity().isFinishing()) {
                this.mPullRefreshLL.setPullToRefresh(true);
                SmartHomeMainActivity smartHomeMainActivity = (SmartHomeMainActivity) getActivity();
                if (smartHomeMainActivity != null && !smartHomeMainActivity.isFinishing()) {
                    try {
                        final View chooseSceneTitleBar = smartHomeMainActivity.getChooseSceneTitleBar();
                        final AnimateLinearLayout animateLinearLayout = (AnimateLinearLayout) smartHomeMainActivity.getChooseSceneMenuBar();
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
                        this.R.clear();
                        TextView textView = this.w;
                        Resources resources = getResources();
                        int size = this.R == null ? 0 : this.R.size();
                        Object[] objArr = new Object[1];
                        objArr[0] = Integer.valueOf(this.R == null ? 0 : this.R.size());
                        textView.setText(resources.getQuantityString(R.plurals.selected_cnt_tips, size, objArr));
                        this.l.notifyDataSetChanged();
                        if (!(this.k == null || this.k.d == null)) {
                            this.k.d.setSwipeEnabled(true);
                        }
                        if (this.mPlaceHolderView != null && this.mPlaceHolderView.getVisibility() == 0) {
                            this.mPlaceHolderView.setVisibility(8);
                        }
                        if (this.tvHomeFilter != null) {
                            this.tvHomeFilter.setEnabled(true);
                        }
                        if (this.tvRoomTagFilter != null) {
                            this.tvRoomTagFilter.setEnabled(true);
                        }
                        this.mSceneViewRV.setPadding(0, 0, 0, 0);
                    } catch (Exception unused) {
                    }
                }
            }
        }
    }

    public void a(int i2, boolean z2, int i3) {
        if (i3 == 15) {
            if (i2 >= this.x.size()) {
                LogUtil.b(y, "position" + i2 + "mCustomScenes.size()" + this.x.size());
                return;
            } else if (z2) {
                this.R.put(this.x.get(i2).f, Integer.valueOf(i3));
            } else {
                this.R.remove(this.x.get(i2).f);
            }
        } else if (i3 == 30) {
            if (i2 >= this.d.size()) {
                LogUtil.b(y, "position" + i2 + "mSmartHomeScenes.size()" + this.d.size());
                return;
            } else if (z2) {
                this.R.put(this.d.get(i2).f, Integer.valueOf(i3));
            } else {
                this.R.remove(this.d.get(i2).f);
            }
        } else if (i3 == 50) {
            if (i2 < this.e.size()) {
                if (z2) {
                    this.R.put(this.e.get(i2).f, Integer.valueOf(i3));
                } else {
                    this.R.remove(this.e.get(i2).f);
                }
            } else {
                return;
            }
        }
        int size = this.R == null ? 0 : this.R.size();
        this.w.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, size, new Object[]{Integer.valueOf(size)}));
        try {
            if (size >= this.x.size() + this.d.size() + this.e.size()) {
                this.r.setImageResource(R.drawable.un_select_selector);
            } else {
                this.r.setImageResource(R.drawable.all_select_selector);
            }
            n();
            o();
            m();
            l();
        } catch (Exception unused) {
        }
    }

    private void l() {
        if (this.R == null || this.R.size() == 0) {
            this.v.setEnabled(false);
        } else if (!ApiHelper.c || this.R == null || this.R.size() <= 1) {
            for (Map.Entry next : this.R.entrySet()) {
                String str = (String) next.getKey();
                if (((Integer) next.getValue()).intValue() != 30) {
                    this.v.setEnabled(false);
                    return;
                }
            }
            this.v.setEnabled(true);
        } else {
            this.v.setEnabled(false);
        }
    }

    private void m() {
        if (this.R == null || this.R.size() <= 0) {
            this.u.setEnabled(false);
            return;
        }
        for (Map.Entry<String, Integer> value : this.R.entrySet()) {
            if (((Integer) value.getValue()).intValue() != 30) {
                this.u.setEnabled(false);
                return;
            }
        }
        this.u.setEnabled(true);
    }

    private void n() {
        if (this.R == null || this.R.size() != 1 || ((Integer) this.R.entrySet().iterator().next().getValue()).intValue() == 50) {
            this.s.setEnabled(false);
        } else {
            this.s.setEnabled(true);
        }
    }

    private void o() {
        if (this.R == null || this.R.size() <= 0) {
            this.t.setEnabled(false);
        } else {
            this.t.setEnabled(true);
        }
    }

    private SceneApi.SmartHomeScene a(String str, int i2) {
        if (i2 == 15) {
            return SceneManager.x().e(str);
        }
        return LiteSceneManager.j().a(str);
    }

    /* access modifiers changed from: private */
    public List<SceneApi.SmartHomeScene> c(int i2) {
        for (int i3 = 0; i3 < this.c.size(); i3++) {
            if (((GroupData) this.c.get(i3).first).b == i2) {
                return (List) this.c.get(i3).second;
            }
        }
        return new ArrayList();
    }

    private void a(final SceneApi.SmartHomeScene smartHomeScene, int i2) {
        if (smartHomeScene != null) {
            String b2 = StringUtil.b((CharSequence) TextUtils.isEmpty(smartHomeScene.g) ? "" : smartHomeScene.g, CreateSceneManager.f21204a * 2);
            NameEditDialogHelper.a((Context) getActivity(), -1, b2, getString(R.string.smarthome_scene_set_name), b2, CreateSceneManager.f21204a, (NameEditDialogHelper.NameEditListenerV2) new NameEditDialogHelper.NameEditListenerV2() {
                public void a(String str) {
                }

                public void a(ClientRemarkInputView clientRemarkInputView, String str) {
                    final XQProgressDialog xQProgressDialog = new XQProgressDialog(MySceneFragmentNew.this.getActivity());
                    xQProgressDialog.setMessage(MySceneFragmentNew.this.getString(R.string.changeing_back_name));
                    xQProgressDialog.setCancelable(false);
                    xQProgressDialog.show();
                    final String str2 = smartHomeScene.g;
                    TextUtils.equals(str2, str);
                    smartHomeScene.g = str;
                    RemoteSceneApi.a().c((Context) MySceneFragmentNew.this.getActivity(), smartHomeScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (MySceneFragmentNew.this.isValid()) {
                                xQProgressDialog.dismiss();
                                MySceneFragmentNew.this.l.notifyDataSetChanged();
                                if (smartHomeScene.t) {
                                    LiteSceneManager.j().b();
                                } else {
                                    SceneManager.x().c((String) null);
                                }
                            }
                        }

                        public void onFailure(Error error) {
                            if (MySceneFragmentNew.this.isValid()) {
                                if (error == null || error.a() != -1) {
                                    Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.change_back_name_fail, 0).show();
                                } else {
                                    Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.smarthome_scene_has_deleted_device, 0).show();
                                }
                                xQProgressDialog.dismiss();
                                smartHomeScene.g = str2;
                            }
                        }
                    });
                }

                public String b(String str) {
                    if (TextUtils.equals(smartHomeScene.g, str)) {
                        return MySceneFragmentNew.this.getString(R.string.smarthome_scene_reset_name);
                    }
                    for (SceneApi.SmartHomeScene next : SceneManager.x().v()) {
                        if (!TextUtils.equals(next.f, smartHomeScene.f) && TextUtils.equals(next.g, str)) {
                            return MySceneFragmentNew.this.getString(R.string.scene_modify_name_error);
                        }
                    }
                    for (SceneApi.SmartHomeScene next2 : LiteSceneManager.j().e()) {
                        if (!TextUtils.equals(next2.f, smartHomeScene.f) && TextUtils.equals(next2.g, str)) {
                            return MySceneFragmentNew.this.getString(R.string.scene_modify_name_error);
                        }
                    }
                    return "";
                }
            });
        }
    }

    public int b() {
        if (this.R == null) {
            return 0;
        }
        return this.R.size();
    }

    /* access modifiers changed from: package-private */
    public void a(final List<String> list) {
        new MLAlertDialog.Builder(getActivity()).a((int) R.string.retry, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                MySceneFragmentNew.this.b((List<String>) list);
                dialogInterface.dismiss();
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).a(true).b((int) R.string.delete_scene_fail_title).d();
    }

    /* access modifiers changed from: private */
    public void b(SceneApi.SmartHomeScene smartHomeScene) {
        Device n2;
        String str;
        Device n3;
        String str2;
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
        if (this.S) {
            this.S = false;
            smartHomeScene.n = !smartHomeScene.n;
            if (this.l != null) {
                this.l.notifyDataSetChanged();
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
                MySceneFragmentNew.this.a(smartHomeScene, device.did);
            }

            public void onFailure(int i, String str) {
                LogUtil.b("lumiscene", str);
                MySceneFragmentNew.this.a(false, smartHomeScene);
            }
        });
    }

    public void a(final SceneApi.SmartHomeScene smartHomeScene, final String str) {
        if (SmartHomeConfig.c) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    RemoteSceneApi.a().a((Context) MySceneFragmentNew.this.getActivity(), smartHomeScene, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                        /* renamed from: a */
                        public void onSuccess(JSONObject jSONObject) {
                            if (MySceneFragmentNew.this.isValid()) {
                                MySceneFragmentNew.this.a(smartHomeScene, str, jSONObject);
                            }
                        }

                        public void onFailure(Error error) {
                            if (MySceneFragmentNew.this.isValid()) {
                                if (MySceneFragmentNew.this.S) {
                                    boolean unused = MySceneFragmentNew.this.S = false;
                                    smartHomeScene.n = !smartHomeScene.n;
                                    MySceneFragmentNew.this.l.notifyDataSetChanged();
                                }
                                if (error.a() == -1) {
                                    Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.smarthome_scene_has_deleted_device, 0).show();
                                } else {
                                    Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.smarthome_scene_change_switch_fail, 0).show();
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
        if (isValid()) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    if (!z2) {
                        if (MySceneFragmentNew.this.S) {
                            boolean unused = MySceneFragmentNew.this.S = false;
                            smartHomeScene.n = !smartHomeScene.n;
                            MySceneFragmentNew.this.l.notifyDataSetChanged();
                        }
                        Toast.makeText(MySceneFragmentNew.this.getActivity(), R.string.local_sync_failed, 0).show();
                    } else if (MySceneFragmentNew.this.S) {
                        boolean unused2 = MySceneFragmentNew.this.S = false;
                    }
                }
            });
        }
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
                    RemoteSceneApi.a().b((Context) MySceneFragmentNew.this.getActivity(), smartHomeScene, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                        /* renamed from: a */
                        public void onSuccess(Void voidR) {
                            if (MySceneFragmentNew.this.isValid()) {
                                MySceneFragmentNew.this.a(true, smartHomeScene);
                            }
                        }

                        public void onFailure(Error error) {
                            if (MySceneFragmentNew.this.isValid()) {
                                MySceneFragmentNew.this.a(false, smartHomeScene);
                            }
                        }
                    });
                }

                public void onFailure(int i, String str) {
                    LogUtil.b("lumiscene", str);
                    if (MySceneFragmentNew.this.isValid()) {
                        MySceneFragmentNew.this.a(false, smartHomeScene);
                    }
                }
            });
        }
    }

    public void onActivityResult(int i2, int i3, Intent intent) {
        LogUtil.a(y, "onActivityResult" + i2 + "resultCode" + i3);
        if (i2 == 999 && i3 == -1 && this.C && this.B) {
            this.H.d();
            h();
        }
    }

    public void c() {
        if (isValid()) {
            if (SHApplication.getStateNotifier().a() != 4) {
                SceneStatusManager.a().b();
                LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(this.I);
                this.K = null;
                this.vFilterLayout.setVisibility(8);
                this.H.a((Home) null);
                this.c.clear();
                h();
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(HomeManager.t);
            this.vFilterLayout.setVisibility(0);
            this.tvHomeFilter.setText("");
            if (HomeManager.a().h()) {
                this.H.a();
                this.H.d();
            } else {
                LocalBroadcastManager.getInstance(getContext()).registerReceiver(this.I, intentFilter);
            }
            SceneStatusManager.a().c();
            h();
        }
    }

    public void a(SceneTabFragment sceneTabFragment) {
        this.k = sceneTabFragment;
    }

    public static class SceneChildViewHolder extends AbstractDraggableSwipeableItemViewHolder {

        /* renamed from: a  reason: collision with root package name */
        public View f21756a;
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
            this.f21756a = view;
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
            return this.f21756a;
        }
    }

    public void d() {
        if (this.l != null && this.e != null && this.e.size() != 0) {
            CorntabUtils.CorntabParam corntabParam = new CorntabUtils.CorntabParam();
            boolean z2 = false;
            for (int size = this.e.size() - 1; size >= 0; size--) {
                if (this.e.get(size).l == null || this.e.get(size).l.size() == 0 || this.e.get(size).l.get(0).b == null || this.e.get(size).l.get(0).b.f21527a == null) {
                    this.e.remove(size);
                } else if (!SceneManager.x().a(corntabParam, this.e.get(size).l.get(0).b.f21527a)) {
                    this.e.remove(size);
                }
                z2 = true;
            }
            if (z2) {
                if (this.e.size() == 0) {
                    for (int size2 = this.c.size() - 1; size2 >= 0; size2--) {
                        if (this.c.get(size2).second == null || ((List) this.c.get(size2).second).size() == 0) {
                            this.c.remove(size2);
                        }
                    }
                }
                this.l.notifyDataSetChanged();
            }
        }
    }
}
