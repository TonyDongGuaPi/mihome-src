package com.xiaomi.smarthome.scenenew;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.mistatistic.sdk.MiStatInterface;
import com.xiaomi.router.api.SceneManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.framework.statistic.MiStatType;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.util.FontManager;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.lite.scene.LiteSceneManager;
import com.xiaomi.smarthome.lite.scene.SceneLogUtil;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupConstants;
import com.xiaomi.smarthome.scene.CreateSceneManager;
import com.xiaomi.smarthome.scene.api.RemoteSceneApi;
import com.xiaomi.smarthome.scene.api.SceneApi;
import com.xiaomi.smarthome.scenenew.SceneLogFragment;
import com.xiaomi.smarthome.scenenew.actiivity.PluginRecommendSceneActivity;
import com.xiaomi.smarthome.scenenew.actiivity.SmarthomeCreateAutoSceneActivity;
import com.xiaomi.smarthome.scenenew.debug.MyDebugLogger;
import com.xiaomi.smarthome.scenenew.model.SceneLogInfo;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SceneLogFragment extends BaseFragment {
    public static final String b = "extra_did";
    private static final String d = "SceneLogFragment";
    private static final int e = 1;

    /* renamed from: a  reason: collision with root package name */
    View f21770a;
    PtrFrameLayout c;
    /* access modifiers changed from: private */
    public ExpandableListView f;
    /* access modifiers changed from: private */
    public LoadingMoreView g;
    /* access modifiers changed from: private */
    public ExpandAdapter h;
    /* access modifiers changed from: private */
    public ImageView i;
    /* access modifiers changed from: private */
    public String j = "";
    /* access modifiers changed from: private */
    public List<SceneLogInfo> k = new ArrayList();
    /* access modifiers changed from: private */
    public View l;
    private boolean m = false;
    /* access modifiers changed from: private */
    public HashMap<Integer, Integer> n = new HashMap<>();
    /* access modifiers changed from: private */
    public boolean o = false;
    /* access modifiers changed from: private */
    public boolean p = true;
    /* access modifiers changed from: private */
    public Handler q = new Handler();
    /* access modifiers changed from: private */
    public DataloadListener r = new DataloadListener() {
        public void a(List<SceneLogInfo> list) {
            SceneLogFragment.this.h.notifyDataSetChanged();
            SceneLogFragment.this.f.setOnScrollListener(SceneLogFragment.this.s);
            if (list.size() == 0) {
                SceneLogFragment.this.l.setVisibility(0);
                SceneLogFragment.this.f.setVisibility(8);
                SceneLogFragment.this.i.setEnabled(false);
                if (!SceneLogFragment.this.o) {
                    try {
                        SceneLogFragment.this.f.addHeaderView(LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.common_list_space_empty, SceneLogFragment.this.f, false));
                        boolean unused = SceneLogFragment.this.o = true;
                    } catch (Exception unused2) {
                    }
                }
            } else {
                SceneLogFragment.this.l.setVisibility(8);
                SceneLogFragment.this.f.setVisibility(0);
                SceneLogFragment.this.i.setEnabled(true);
                SceneLogFragment.this.f.setOnScrollListener(SceneLogFragment.this.s);
            }
            SceneLogFragment.this.g.setVisibility(8);
            SceneLogFragment.this.c.refreshComplete();
        }

        public void b(List<SceneLogInfo> list) {
            if (list == null || list.size() == 0) {
                SceneLogFragment.this.g.setVisibility(8);
                boolean unused = SceneLogFragment.this.u = true;
                return;
            }
            SceneLogFragment.this.h.notifyDataSetChanged();
        }

        public void a(int i) {
            if (SceneLogFragment.this.k == null || SceneLogFragment.this.k.size() == 0) {
                SceneLogFragment.this.c.refreshComplete();
                SceneLogFragment.this.f.setVisibility(8);
                SceneLogFragment.this.i.setEnabled(false);
                SceneLogFragment.this.l.setVisibility(0);
            } else {
                SceneLogFragment.this.f.setVisibility(0);
                SceneLogFragment.this.l.setVisibility(8);
                SceneLogFragment.this.i.setEnabled(true);
                SceneLogFragment.this.h.notifyDataSetChanged();
            }
            SceneLogFragment.this.g.displayError((int) R.string.loading_more_error, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    boolean unused = SceneLogFragment.this.d();
                }
            });
        }

        public void b(int i) {
            SceneLogFragment.this.g.displayError((int) R.string.loading_more_error, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    boolean unused = SceneLogFragment.this.d();
                }
            });
        }
    };
    /* access modifiers changed from: private */
    public EndlessScrollListener s = new EndlessScrollListener() {
        public boolean a(int i, int i2) {
            return SceneLogFragment.this.d();
        }
    };
    private long t = 0;
    /* access modifiers changed from: private */
    public boolean u = false;

    public interface DataloadListener {
        void a(int i);

        void a(List<SceneLogInfo> list);

        void b(int i);

        void b(List<SceneLogInfo> list);
    }

    public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

        /* renamed from: a  reason: collision with root package name */
        private int f21783a = 5;
        private int c = 0;
        private int d = 0;
        private boolean e = true;
        private int f = 0;

        public abstract boolean a(int i, int i2);

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public EndlessScrollListener() {
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (i3 < this.d) {
                this.c = this.f;
                this.d = i3;
                if (i3 == 0) {
                    this.e = true;
                }
            }
            if (this.e && i3 > this.d) {
                this.e = false;
                this.d = i3;
                this.c++;
            }
            if (!this.e && i3 - i2 <= i + this.f21783a) {
                this.e = a(this.c + 1, i3);
            }
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        SceneLogUtil.a("SceneLogFragment  onViewCreated");
        SharePrefsManager.a(SHApplication.getAppContext().getSharedPreferences(MyDebugLogger.f21933a, 0), MyDebugLogger.b, false);
        if (getArguments() != null && getArguments().containsKey("device_id")) {
            this.j = getArguments().getString("device_id");
        }
    }

    public void onPageSelected() {
        super.onPageSelected();
        if (this.p) {
            SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                public void run() {
                    if (SceneLogFragment.this.c != null) {
                        MyDebugLogger.a().a("SceneLogFragment 1000 after!");
                        SceneLogFragment.this.c.autoRefresh();
                        boolean unused = SceneLogFragment.this.p = false;
                    }
                }
            }, 1000);
        }
    }

    public void onViewCreated(View view, Bundle bundle) {
        SceneLogUtil.a("SceneLogFragment  onViewCreated");
        super.onViewCreated(view, bundle);
        this.m = true;
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        SceneLogUtil.a("SceneLogFragment  onCreateView");
        if (this.f21770a == null) {
            this.f21770a = layoutInflater.inflate(R.layout.fragment_log_layout, (ViewGroup) null);
            this.c = (PtrFrameLayout) this.f21770a.findViewById(R.id.pull_to_refresh);
            this.c.setPtrHandler(new PtrDefaultHandler() {
                public void onRefreshBegin(PtrFrameLayout ptrFrameLayout) {
                    SceneLogFragment.this.b();
                }
            });
            a();
            MiStatInterface.a(MiStatType.CLICK.getValue(), "scene_log_tab");
        }
        return this.f21770a;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        this.n.put(0, Integer.valueOf(R.string.scene_error_0));
        this.n.put(-2, Integer.valueOf(R.string.scene_error_2));
        this.n.put(-3, Integer.valueOf(R.string.scene_error_3));
        this.n.put(-33066, Integer.valueOf(R.string.scene_error_33066));
        this.l = this.f21770a.findViewById(R.id.common_white_empty_view);
        this.i = (ImageView) this.f21770a.findViewById(R.id.del_iv);
        this.l.setVisibility(8);
        ((TextView) this.f21770a.findViewById(R.id.common_white_empty_text)).setText(R.string.smarthome_scene_no_log_data);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SHApplication.getStateNotifier().a() == 4) {
                    new MLAlertDialog.Builder(SceneLogFragment.this.getActivity()).b((CharSequence) SceneLogFragment.this.getResources().getString(R.string.log_clear_all_logs)).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogInterface, int i) {
                            RemoteSceneApi.a().a(SceneLogFragment.this.j, (Context) SceneLogFragment.this.getActivity(), (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                /* renamed from: a */
                                public void onSuccess(Void voidR) {
                                    Toast.makeText(SHApplication.getAppContext(), R.string.log_clear_success, 0);
                                    SceneLogFragment.this.k.clear();
                                    SceneLogFragment.this.h.notifyDataSetChanged();
                                    SceneLogFragment.this.l.setVisibility(0);
                                    SceneLogFragment.this.i.setEnabled(false);
                                }

                                public void onFailure(Error error) {
                                    Toast.makeText(SHApplication.getAppContext(), R.string.log_clear_error, 0);
                                }
                            });
                        }
                    }).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).b().show();
                }
            }
        });
        this.f = (ExpandableListView) this.f21770a.findViewById(R.id.log_list);
        this.f.setGroupIndicator((Drawable) null);
        this.f.setChildDivider((Drawable) null);
        this.f.setChildIndicator((Drawable) null);
        this.h = new ExpandAdapter();
        this.f.setAdapter(this.h);
        this.l.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (SceneLogFragment.this.c != null) {
                    SceneLogFragment.this.c.autoRefresh();
                }
            }
        });
        this.g = new LoadingMoreView(getActivity());
        this.f.addFooterView(this.g);
        this.g.setVisibility(0);
    }

    /* access modifiers changed from: package-private */
    public void b() {
        long currentTimeMillis = System.currentTimeMillis();
        MyDebugLogger a2 = MyDebugLogger.a();
        a2.a("SceneLogFragment start initData!=====" + currentTimeMillis);
        if (!CoreApi.a().l()) {
            MyDebugLogger a3 = MyDebugLogger.a();
            a3.a("SceneLogFragment: core is not ready!=====" + currentTimeMillis);
        }
        if (CoreApi.a().l()) {
            this.t = 0;
            this.k.clear();
            c();
            return;
        }
        CoreApi.a().a(getContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback(currentTimeMillis) {
            private final /* synthetic */ long f$1;

            {
                this.f$1 = r2;
            }

            public final void onCoreReady() {
                SceneLogFragment.this.a(this.f$1);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(long j2) {
        MyDebugLogger a2 = MyDebugLogger.a();
        a2.a("SceneLogFragment isCoreReady  onSuccess!====" + j2);
        if (isValid()) {
            c();
        }
    }

    private void c() {
        if (this.c != null) {
            this.k.clear();
            this.t = -1;
            this.u = false;
            RemoteSceneApi.a().a(this.j, this.t, (Context) getActivity(), (AsyncCallback<List<SceneLogInfo>, Error>) new AsyncCallback<List<SceneLogInfo>, Error>() {
                /* renamed from: a */
                public void onSuccess(List<SceneLogInfo> list) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    List unused = SceneLogFragment.this.k = list;
                    SceneLogFragment.this.q.post(new Runnable(list) {
                        private final /* synthetic */ List f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            SceneLogFragment.AnonymousClass7.this.b(this.f$1);
                        }
                    });
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void b(List list) {
                    if (SceneLogFragment.this.isValid()) {
                        SceneLogFragment.this.r.a((List<SceneLogInfo>) list);
                    }
                }

                public void onFailure(Error error) {
                    if (SceneLogFragment.this.isValid()) {
                        SceneLogFragment.this.r.a(0);
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public boolean d() {
        if (!isValid() || this.k == null || this.k.size() == 0 || this.u) {
            return false;
        }
        this.g.setVisibility(0);
        this.g.displayLoding();
        this.t = this.k.get(this.k.size() - 1).f21991a - 1;
        RemoteSceneApi.a().a(this.j, this.t, (Context) getActivity(), (AsyncCallback<List<SceneLogInfo>, Error>) new AsyncCallback<List<SceneLogInfo>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<SceneLogInfo> list) {
                MyDebugLogger.a().a("SceneLogFragment getSceneLog  onSuccess!");
                if (SceneLogFragment.this.isValid()) {
                    if (list.isEmpty()) {
                        boolean unused = SceneLogFragment.this.u = true;
                    } else if (SceneLogFragment.this.k != null && SceneLogFragment.this.k.size() > 0) {
                        SceneLogInfo sceneLogInfo = (SceneLogInfo) SceneLogFragment.this.k.get(SceneLogFragment.this.k.size() - 1);
                        SceneLogInfo sceneLogInfo2 = list.get(0);
                        if (sceneLogInfo != null && sceneLogInfo2 != null && TextUtils.equals(sceneLogInfo.f, sceneLogInfo2.f) && TextUtils.equals(sceneLogInfo.d, sceneLogInfo2.d) && TextUtils.equals(sceneLogInfo.c, sceneLogInfo2.c)) {
                            list.remove(0);
                        }
                    }
                    SceneLogFragment.this.k.addAll(list);
                    SceneLogFragment.this.q.post(new Runnable(list) {
                        private final /* synthetic */ List f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            SceneLogFragment.AnonymousClass8.this.b(this.f$1);
                        }
                    });
                }
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void b(List list) {
                if (SceneLogFragment.this.isValid()) {
                    SceneLogFragment.this.r.b((List<SceneLogInfo>) list);
                }
            }

            public void onFailure(Error error) {
                if (SceneLogFragment.this.isValid()) {
                    SceneLogFragment.this.r.b(0);
                    MyDebugLogger a2 = MyDebugLogger.a();
                    a2.a("SceneLogFragment getSceneLog  onFailure!" + error.a());
                }
            }
        });
        return true;
    }

    class ViewTag {

        /* renamed from: a  reason: collision with root package name */
        RelativeLayout f21791a;
        RelativeLayout b;
        TextView c;
        TextView d;
        TextView e;
        ImageView f;
        ImageView g;
        ImageView h;
        ImageView i;
        TextView j;
        TextView k;
        View l;
        ImageView m;

        ViewTag() {
        }
    }

    class ViewTagDetail {

        /* renamed from: a  reason: collision with root package name */
        TextView f21792a;
        TextView b;
        TextView c;

        ViewTagDetail() {
        }
    }

    class ExpandAdapter extends BaseExpandableListAdapter {

        /* renamed from: a  reason: collision with root package name */
        final int[] f21784a = {R.string.sunday, R.string.monday, R.string.tuesday, R.string.wednesday, R.string.thursday, R.string.friday, R.string.saturday};
        final int[] b = {R.string.month_jan, R.string.month_feb, R.string.month_mar, R.string.month_apr, R.string.month_may, R.string.month_jun, R.string.month_jul, R.string.month_aug, R.string.month_sep, R.string.month_oct, R.string.month_nov, R.string.month_dec};
        private Typeface d = FontManager.a(SmartGroupConstants.f);

        public boolean areAllItemsEnabled() {
            return false;
        }

        public long getChildId(int i, int i2) {
            return 0;
        }

        public long getCombinedChildId(long j, long j2) {
            return 0;
        }

        public long getCombinedGroupId(long j) {
            return 0;
        }

        public long getGroupId(int i) {
            return 0;
        }

        public boolean hasStableIds() {
            return false;
        }

        public boolean isChildSelectable(int i, int i2) {
            return false;
        }

        public boolean isEmpty() {
            return false;
        }

        public void onGroupCollapsed(int i) {
        }

        public void onGroupExpanded(int i) {
        }

        public ExpandAdapter() {
        }

        public int getGroupCount() {
            if (SceneLogFragment.this.k == null) {
                return 0;
            }
            return SceneLogFragment.this.k.size();
        }

        public int getChildrenCount(int i) {
            if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).b || ((SceneLogInfo) SceneLogFragment.this.k.get(i)).j == 0 || SceneLogFragment.this.k.get(i) == null || ((SceneLogInfo) SceneLogFragment.this.k.get(i)).k == null) {
                return 0;
            }
            return ((SceneLogInfo) SceneLogFragment.this.k.get(i)).k.size();
        }

        public Object getGroup(int i) {
            return SceneLogFragment.this.k.get(i);
        }

        public Object getChild(int i, int i2) {
            if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).k != null) {
                return ((SceneLogInfo) SceneLogFragment.this.k.get(i)).k.get(i2);
            }
            return null;
        }

        public View getGroupView(final int i, boolean z, View view, ViewGroup viewGroup) {
            ViewTag viewTag;
            if (view == null) {
                view = SceneLogFragment.this.getActivity().getLayoutInflater().inflate(R.layout.scene_group_log_item, (ViewGroup) null);
                viewTag = new ViewTag();
                viewTag.f21791a = (RelativeLayout) view.findViewById(R.id.log_time_container);
                viewTag.b = (RelativeLayout) view.findViewById(R.id.log_content);
                viewTag.c = (TextView) view.findViewById(R.id.month_text);
                viewTag.d = (TextView) view.findViewById(R.id.day_text);
                viewTag.e = (TextView) view.findViewById(R.id.week_text);
                viewTag.g = (ImageView) view.findViewById(R.id.feed_item_line_top);
                viewTag.f = (ImageView) view.findViewById(R.id.feed_item_line_circle_top);
                viewTag.h = (ImageView) view.findViewById(R.id.feed_item_line_circle_bottom);
                viewTag.i = (ImageView) view.findViewById(R.id.feed_item_icon);
                viewTag.j = (TextView) view.findViewById(R.id.log_title_text);
                viewTag.k = (TextView) view.findViewById(R.id.log_detail_text);
                viewTag.l = view.findViewById(R.id.top_line_margin);
                viewTag.m = (ImageView) view.findViewById(R.id.right_arrow);
                view.setTag(viewTag);
            } else {
                viewTag = (ViewTag) view.getTag();
            }
            view.setOnClickListener((View.OnClickListener) null);
            if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).b) {
                viewTag.f21791a.setVisibility(0);
                viewTag.b.setVisibility(8);
                viewTag.h.setVisibility(0);
                viewTag.c.setTypeface(this.d);
                viewTag.c.setText(((SceneLogInfo) SceneLogFragment.this.k.get(i)).c);
                try {
                    int intValue = Integer.valueOf(((SceneLogInfo) SceneLogFragment.this.k.get(i)).d).intValue();
                    int intValue2 = Integer.valueOf(((SceneLogInfo) SceneLogFragment.this.k.get(i)).e).intValue();
                    if (intValue < 1 || intValue > 12) {
                        viewTag.d.setText("");
                    } else {
                        viewTag.d.setText(this.b[intValue - 1]);
                    }
                    if (intValue2 < 0 || intValue2 > 6) {
                        viewTag.e.setText("");
                    } else {
                        viewTag.e.setText(this.f21784a[intValue2]);
                    }
                    if (TextUtils.equals("1", ((SceneLogInfo) SceneLogFragment.this.k.get(i)).c) && TextUtils.equals("1", ((SceneLogInfo) SceneLogFragment.this.k.get(i)).d) && TextUtils.equals("1970", ((SceneLogInfo) SceneLogFragment.this.k.get(i)).f)) {
                        MyDebugLogger.a().a("SceneLogFragmentgetview：time is error");
                    }
                } catch (Exception e) {
                    viewTag.d.setText("");
                    viewTag.e.setText("");
                    MyDebugLogger.a().a("SceneLogFragment日期格式异常：" + e.getMessage());
                }
            } else {
                viewTag.f21791a.setVisibility(8);
                viewTag.b.setVisibility(0);
                viewTag.h.setVisibility(8);
                viewTag.g.setVisibility(0);
                viewTag.f.setVisibility(8);
                int i2 = i + 1;
                if (i2 >= SceneLogFragment.this.k.size() || ((SceneLogInfo) SceneLogFragment.this.k.get(i2)).b) {
                    viewTag.h.setVisibility(0);
                }
                int i3 = i - 1;
                if (i3 < 0 || !((SceneLogInfo) SceneLogFragment.this.k.get(i3)).b) {
                    viewTag.g.setVisibility(0);
                    viewTag.f.setVisibility(8);
                    viewTag.l.setVisibility(8);
                } else {
                    viewTag.g.setVisibility(0);
                    viewTag.f.setVisibility(0);
                    viewTag.l.setVisibility(8);
                }
                viewTag.j.setText(TextUtils.isEmpty(((SceneLogInfo) SceneLogFragment.this.k.get(i)).g) ? "" : ((SceneLogInfo) SceneLogFragment.this.k.get(i)).g);
                String str = "";
                if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).j == 0) {
                    str = SceneLogFragment.this.getString(R.string.log_scene_sucess);
                } else if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).j == -1) {
                    str = SceneLogFragment.this.getString(R.string.log_scene_error);
                } else if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).j == 1) {
                    str = SceneLogFragment.this.getString(R.string.log_scene_success_part);
                }
                viewTag.k.setText(((SceneLogInfo) SceneLogFragment.this.k.get(i)).h + "  " + str);
                viewTag.i.setImageResource(((SceneLogInfo) SceneLogFragment.this.k.get(i)).i);
                if (i2 < SceneLogFragment.this.k.size() && ((SceneLogInfo) SceneLogFragment.this.k.get(i2)).b) {
                    if (SceneLogFragment.this.f.isGroupExpanded(i)) {
                        viewTag.h.setVisibility(8);
                    } else {
                        viewTag.h.setVisibility(0);
                    }
                }
                if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).j == 0 || ((SceneLogInfo) SceneLogFragment.this.k.get(i)).k.size() == 0) {
                    viewTag.m.setVisibility(8);
                    viewTag.m.setOnClickListener((View.OnClickListener) null);
                } else {
                    viewTag.m.setVisibility(0);
                    viewTag.m.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            if (SceneLogFragment.this.f.isGroupExpanded(i)) {
                                SceneLogFragment.this.f.collapseGroup(i);
                            } else {
                                SceneLogFragment.this.f.expandGroup(i);
                            }
                        }
                    });
                    if (SceneLogFragment.this.f.isGroupExpanded(i)) {
                        viewTag.m.setImageResource(R.drawable.std_tittlebar_details_shop_arrow_press);
                    } else {
                        viewTag.m.setImageResource(R.drawable.std_tittlebar_details_shop_arrow_normal);
                    }
                }
                if (((SceneLogInfo) SceneLogFragment.this.k.get(i)).j == 0) {
                    viewTag.k.setTextColor(SceneLogFragment.this.getResources().getColor(R.color.black_80_transparent));
                } else {
                    viewTag.k.setTextColor(SceneLogFragment.this.getResources().getColor(R.color.red_80_transparent));
                }
                final SceneLogInfo sceneLogInfo = (SceneLogInfo) SceneLogFragment.this.k.get(i);
                view.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        if (sceneLogInfo != null) {
                            if (TextUtils.isEmpty(sceneLogInfo.l)) {
                                ToastUtil.a((int) R.string.scene_log_old_data_no_support);
                            } else {
                                ExpandAdapter.this.a(sceneLogInfo.l);
                            }
                        }
                    }
                });
            }
            return view;
        }

        public View getChildView(int i, int i2, boolean z, View view, ViewGroup viewGroup) {
            ViewTagDetail viewTagDetail;
            if (view == null) {
                view = LayoutInflater.from(SceneLogFragment.this.getActivity()).inflate(R.layout.scene_log_item_detail, (ViewGroup) null);
                viewTagDetail = new ViewTagDetail();
                viewTagDetail.f21792a = (TextView) view.findViewById(R.id.log_title_text);
                viewTagDetail.b = (TextView) view.findViewById(R.id.log_detail_text);
                viewTagDetail.c = (TextView) view.findViewById(R.id.log_detail_right_text);
                view.setTag(viewTagDetail);
            } else {
                viewTagDetail = (ViewTagDetail) view.getTag();
            }
            final SceneLogInfo.Detail detail = ((SceneLogInfo) SceneLogFragment.this.k.get(i)).k.get(i2);
            if (detail.f21992a == 0) {
                viewTagDetail.f21792a.setText(TextUtils.isEmpty(detail.b) ? "" : detail.b);
                viewTagDetail.b.setText(TextUtils.isEmpty(detail.c) ? "" : detail.c);
            } else {
                viewTagDetail.f21792a.setText(R.string.smarthome_scene_push_action);
                viewTagDetail.b.setText("");
            }
            viewTagDetail.c.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                }
            });
            if (SceneLogFragment.this.n.containsKey(Integer.valueOf(detail.e))) {
                viewTagDetail.c.setText(((Integer) SceneLogFragment.this.n.get(Integer.valueOf(detail.e))).intValue());
            } else {
                viewTagDetail.c.setText(R.string.scene_error_other);
                viewTagDetail.c.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ExpandAdapter.this.a();
                    }
                });
            }
            int i3 = i + 1;
            if (i3 >= SceneLogFragment.this.k.size() || !((SceneLogInfo) SceneLogFragment.this.k.get(i3)).b) {
                view.findViewById(R.id.feed_item_line_circle_bottom).setVisibility(8);
            } else {
                view.findViewById(R.id.feed_item_line_circle_bottom).setVisibility(0);
            }
            if (detail.e == 0) {
                viewTagDetail.c.setTextColor(SceneLogFragment.this.getResources().getColor(R.color.black_80_transparent));
            } else {
                viewTagDetail.c.setTextColor(SceneLogFragment.this.getResources().getColor(R.color.red_80_transparent));
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (detail != null && !TextUtils.isEmpty(detail.f)) {
                        ExpandAdapter.this.a(detail.f);
                    }
                }
            });
            return view;
        }

        /* access modifiers changed from: private */
        public void a(String str) {
            SceneApi.SmartHomeScene smartHomeScene;
            if (SceneManager.x().f(str) == null) {
                if (LiteSceneManager.j().a(str) != null) {
                    smartHomeScene = LiteSceneManager.j().a(str);
                } else {
                    smartHomeScene = SceneManager.x().e(str);
                }
                if (smartHomeScene == null) {
                    ToastUtil.a((int) R.string.scene_log_scene_no_exist);
                } else if (!SceneManager.x().g(smartHomeScene)) {
                    ToastUtil.a((int) R.string.cannot_edit_ios_scene);
                } else if (smartHomeScene.i == 1000) {
                    Intent intent = new Intent(SceneLogFragment.this.getActivity(), PluginRecommendSceneActivity.class);
                    intent.putExtra("sr_id", smartHomeScene.i);
                    if (!(smartHomeScene.l == null || smartHomeScene.l.size() <= 0 || smartHomeScene.l.get(0).c == null)) {
                        intent.putExtra("did", smartHomeScene.l.get(0).c.f21523a);
                    }
                    SceneLogFragment.this.getActivity().startActivity(intent);
                } else {
                    Intent intent2 = new Intent(SceneLogFragment.this.getActivity(), SmarthomeCreateAutoSceneActivity.class);
                    CreateSceneManager.a().a(smartHomeScene);
                    SceneLogFragment.this.getActivity().startActivityForResult(intent2, 999);
                }
            }
        }

        /* access modifiers changed from: private */
        public void a() {
            new MLAlertDialog.Builder(SceneLogFragment.this.getActivity()).b((int) R.string.scene_error_dialog_tips).c((int) R.string.scene_has_knowed, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                }
            }).b().show();
        }
    }

    public void a(Intent intent) {
        if (intent != null && isValid()) {
            String action = intent.getAction();
            if (!action.equalsIgnoreCase("action_on_login_success") || !this.m) {
                if (action.equalsIgnoreCase("action_on_logout") && this.m && this.l != null && this.f != null && this.h != null) {
                    this.k.clear();
                    this.h.notifyDataSetChanged();
                    this.l.setVisibility(0);
                }
            } else if (this.c != null) {
                this.c.autoRefresh();
            }
        }
    }
}
