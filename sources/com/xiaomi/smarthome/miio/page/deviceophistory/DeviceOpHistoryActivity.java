package com.xiaomi.smarthome.miio.page.deviceophistory;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.CalendarUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CustomPullDownRefreshListView;
import com.xiaomi.smarthome.library.common.widget.PullDownDragListView;
import com.xiaomi.smarthome.library.common.widget.viewflow.LoadingMoreView;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.page.SettingMainPageV2;
import java.util.ArrayList;
import java.util.List;

public class DeviceOpHistoryActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public SparseBooleanArray A = new SparseBooleanArray();
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public PullDownDragListView f19716a;
    /* access modifiers changed from: private */
    public View b;
    /* access modifiers changed from: private */
    public List<DeviceOpHistoryGroupData> c = new ArrayList();
    private View d;
    private ImageView e;
    private ImageView f;
    private View g;
    private TextView h;
    private TextView i;
    /* access modifiers changed from: private */
    public View j;
    private TextView k;
    /* access modifiers changed from: private */
    public LoadingMoreView l;
    /* access modifiers changed from: private */
    public boolean m = false;
    SimpleAdapterNew mAdapter;
    boolean mHasMoreData = true;
    long mLastUpdataTime;
    ImageView mMoreBtn;
    XQProgressDialog mProcessDialog;
    long mTimeStamp = 0;
    private String n;
    /* access modifiers changed from: private */
    public Handler o = new Handler();
    private String p = null;
    /* access modifiers changed from: private */
    public View q;
    /* access modifiers changed from: private */
    public EndlessScrollListener r = new EndlessScrollListener() {
        public boolean a(int i, int i2) {
            return DeviceOpHistoryActivity.this.d();
        }
    };
    /* access modifiers changed from: private */
    public DataloadListener s = new DataloadListener() {
        public void a(List<DeviceOpHistoryGroupData> list) {
            DeviceOpHistoryActivity.this.A.clear();
            DeviceOpHistoryActivity.this.mAdapter.a(list, false);
            DeviceOpHistoryActivity.this.mAdapter.notifyDataSetChanged();
            if (DeviceOpHistoryActivity.this.c.size() == 0) {
                DeviceOpHistoryActivity.this.b.setVisibility(0);
                DeviceOpHistoryActivity.this.f19716a.setVisibility(8);
                DeviceOpHistoryActivity.this.hideDeleteBars();
                boolean unused = DeviceOpHistoryActivity.this.z = false;
                DeviceOpHistoryActivity.this.a(false);
            } else {
                DeviceOpHistoryActivity.this.b.setVisibility(8);
                DeviceOpHistoryActivity.this.f19716a.setVisibility(0);
                DeviceOpHistoryActivity.this.f19716a.setOnScrollListener(DeviceOpHistoryActivity.this.r);
            }
            DeviceOpHistoryActivity.this.l.setVisibility(8);
            DeviceOpHistoryActivity.this.f19716a.postRefresh();
            DeviceOpHistoryActivity.this.b();
        }

        public void b(List<DeviceOpHistoryGroupData> list) {
            if (list == null || list.size() == 0) {
                DeviceOpHistoryActivity.this.l.setVisibility(8);
                boolean unused = DeviceOpHistoryActivity.this.m = true;
                return;
            }
            DeviceOpHistoryActivity.this.mAdapter.a(list, true);
            DeviceOpHistoryActivity.this.mAdapter.notifyDataSetChanged();
        }

        public void a(int i) {
            DeviceOpHistoryActivity.this.A.clear();
            DeviceOpHistoryActivity.this.mAdapter.notifyDataSetChanged();
            DeviceOpHistoryActivity.this.f19716a.postRefresh();
            if (DeviceOpHistoryActivity.this.c.size() == 0) {
                DeviceOpHistoryActivity.this.b.setVisibility(0);
                DeviceOpHistoryActivity.this.f19716a.setVisibility(8);
                DeviceOpHistoryActivity.this.a(false);
            } else {
                DeviceOpHistoryActivity.this.b.setVisibility(8);
                DeviceOpHistoryActivity.this.f19716a.setVisibility(0);
            }
            if (i == 10) {
                DeviceOpHistoryActivity.this.f19716a.postRefresh();
            }
        }

        public void b(int i) {
            DeviceOpHistoryActivity.this.l.displayError((int) R.string.loading_more_error, (View.OnClickListener) new View.OnClickListener() {
                public void onClick(View view) {
                    boolean unused = DeviceOpHistoryActivity.this.d();
                }
            });
        }
    };
    private SimpleDraweeView t;
    private TextView u;
    private TextView v;
    /* access modifiers changed from: private */
    public int w;
    private TextView x;
    private ImageView y;
    /* access modifiers changed from: private */
    public boolean z = false;

    public interface DataloadListener {
        void a(int i);

        void a(List<DeviceOpHistoryGroupData> list);

        void b(int i);

        void b(List<DeviceOpHistoryGroupData> list);
    }

    public static void openOpHistoryActivity(Activity activity, String str) {
        Intent intent = new Intent(activity, DeviceOpHistoryActivity.class);
        intent.putExtra("did", str);
        activity.startActivity(intent);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_op_history);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.p = intent.getStringExtra("did");
        if (TextUtils.isEmpty(this.p)) {
            finish();
            return;
        }
        TitleBarUtil.b((Activity) this);
        initViews();
        this.mHasMoreData = false;
        this.mTimeStamp = 0;
        startLoad();
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        this.mHasMoreData = false;
        this.mTimeStamp = 0;
        startLoad();
    }

    /* access modifiers changed from: package-private */
    public void initViews() {
        this.f19716a = (PullDownDragListView) findViewById(R.id.share_message_list);
        this.l = new LoadingMoreView(this);
        this.f19716a.addFooterView(this.l);
        this.l.setVisibility(0);
        this.f19716a.setRefreshListener(new CustomPullDownRefreshListView.OnRefreshListener() {
            public void startRefresh() {
                DeviceOpHistoryActivity.this.mTimeStamp = 0;
                DeviceOpHistoryActivity.this.startLoad();
            }
        });
        this.b = findViewById(R.id.common_white_empty_view);
        this.b.setVisibility(8);
        ((TextView) findViewById(R.id.common_white_empty_text)).setText(R.string.no_data_tips);
        this.x = (TextView) findViewById(R.id.title_bar_title);
        if (!TextUtils.isEmpty(this.n)) {
            this.x.setText(this.n);
        } else {
            this.x.setText(R.string.device_op_history);
        }
        this.x.setVisibility(4);
        this.y = (ImageView) findViewById(R.id.title_bar_return);
        this.y.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                PreferenceUtils.b(SettingMainPageV2.b, true);
                PreferenceUtils.b("my_home_red_dot_clicked", true);
                PreferenceUtils.a(SHApplication.getAppContext(), SettingMainPageV2.c, 0);
                DeviceOpHistoryActivity.this.finish();
            }
        });
        this.mMoreBtn = (ImageView) findViewById(R.id.title_bar_more);
        this.mMoreBtn.setVisibility(8);
        this.mMoreBtn.setImageResource(R.drawable.common_title_bar_clear);
        this.mMoreBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.mAdapter = new SimpleAdapterNew();
        this.f19716a.setPullDownEnabled(false);
        this.j = findViewById(R.id.title_bar);
        this.d = findViewById(R.id.top_delete_bar);
        this.k = (TextView) this.d.findViewById(R.id.selected_cnt);
        this.e = (ImageView) this.d.findViewById(R.id.cancel_btn);
        this.e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DeviceOpHistoryActivity.this.dismissEditMode();
            }
        });
        this.f = (ImageView) this.d.findViewById(R.id.select_all_btn);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (DeviceOpHistoryActivity.this.A.size() == DeviceOpHistoryActivity.this.c.size()) {
                    DeviceOpHistoryActivity.this.unSelectAll();
                    StatHelper.a(false);
                    return;
                }
                DeviceOpHistoryActivity.this.selectAll();
                StatHelper.a(true);
            }
        });
        this.g = findViewById(R.id.bottom_delete_bar);
        this.g.setVisibility(8);
        this.h = (TextView) this.g.findViewById(R.id.delete_msg_btn);
        this.h.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.h.setVisibility(4);
        this.i = (TextView) this.g.findViewById(R.id.btm_tip_tv);
        this.i.setVisibility(0);
        this.i = (TextView) this.g.findViewById(R.id.btm_tip_tv);
        this.i.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        if (TitleBarUtil.f11582a) {
            TitleBarUtil.a(TitleBarUtil.a(), this.d);
        }
        a();
        c();
        this.f19716a.setAdapter(this.mAdapter);
    }

    private void a() {
        this.q = LayoutInflater.from(this).inflate(R.layout.device_op_history_header, this.f19716a, false);
        this.f19716a.addHeaderView(this.q);
        this.t = (SimpleDraweeView) this.q.findViewById(R.id.device_icon);
        this.u = (TextView) this.q.findViewById(R.id.device_name);
        this.v = (TextView) this.q.findViewById(R.id.device_status);
        Device b2 = SmartHomeDeviceManager.a().b(this.p);
        if (b2 != null) {
            DeviceFactory.b(b2.model, this.t);
            this.u.setText(b2.getName());
        }
    }

    /* access modifiers changed from: private */
    public void b() {
        DeviceOpHistoryGroupData deviceOpHistoryGroupData;
        DeviceOpHistoryChildData deviceOpHistoryChildData;
        if (this.c.size() > 0 && (deviceOpHistoryGroupData = this.c.get(0)) != null && deviceOpHistoryGroupData.e != null && deviceOpHistoryGroupData.e.size() != 0 && (deviceOpHistoryChildData = deviceOpHistoryGroupData.e.get(0)) != null) {
            TextView textView = this.v;
            textView.setText(CalendarUtils.c(deviceOpHistoryChildData.f19738a) + " " + deviceOpHistoryChildData.c);
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z2) {
        if (z2) {
            this.x.setVisibility(4);
            this.j.setBackgroundResource(R.drawable.transparent);
            this.x.setTextColor(getResources().getColor(R.color.white));
            this.y.setImageResource(R.drawable.std_tittlebar_main_device_back_white);
            TitleBarUtil.b((Activity) this);
        } else {
            this.x.setVisibility(0);
            this.j.setBackgroundResource(R.drawable.common_title_bar_bg);
            this.x.setTextColor(getResources().getColor(R.color.black_80_transparent));
            this.y.setImageResource(R.drawable.std_tittlebar_main_device_back);
            TitleBarUtil.a((Activity) this);
        }
        this.j.invalidate();
    }

    private void c() {
        a(true);
        getWindow().getDecorView().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                if (Build.VERSION.SDK_INT >= 16) {
                    DeviceOpHistoryActivity.this.getWindow().getDecorView().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
                int unused = DeviceOpHistoryActivity.this.w = DeviceOpHistoryActivity.this.j.getHeight();
                if (TitleBarUtil.f11582a) {
                    DeviceOpHistoryActivity.this.q.setPadding(DeviceOpHistoryActivity.this.q.getPaddingLeft(), DeviceOpHistoryActivity.this.getResources().getDimensionPixelSize(R.dimen.title_bar_top_padding), DeviceOpHistoryActivity.this.q.getPaddingRight(), DeviceOpHistoryActivity.this.q.getPaddingBottom());
                }
            }
        });
    }

    public void showDeleteBars() {
        this.d.setVisibility(0);
        this.g.setVisibility(0);
        this.h.setVisibility(0);
        this.i.setVisibility(8);
        this.d.measure(0, 0);
        this.g.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.d, View.Y, new float[]{(float) (-this.d.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.g.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.g, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.g.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    public void hideDeleteBars() {
        this.d.setVisibility(8);
        this.g.setVisibility(0);
        this.h.setVisibility(4);
        this.i.setVisibility(0);
    }

    public void onBackPressed() {
        super.onBackPressed();
        PreferenceUtils.b(SettingMainPageV2.b, true);
        PreferenceUtils.b("my_home_red_dot_clicked", true);
        PreferenceUtils.a(SHApplication.getAppContext(), SettingMainPageV2.c, 0);
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    /* access modifiers changed from: private */
    public boolean d() {
        DeviceOpHistoryGroupData deviceOpHistoryGroupData = this.c.get(this.c.size() - 1);
        if (deviceOpHistoryGroupData == null || this.m || deviceOpHistoryGroupData.e == null || deviceOpHistoryGroupData.e.size() == 0) {
            return false;
        }
        this.l.setVisibility(0);
        this.l.displayLoding();
        RemoteFamilyApi.a().a((Context) this, this.p, deviceOpHistoryGroupData.e.get(deviceOpHistoryGroupData.e.size() - 1).f19738a - 1, (AsyncCallback<List<DeviceOpHistoryGroupData>, Error>) new AsyncCallback<List<DeviceOpHistoryGroupData>, Error>() {
            /* renamed from: a */
            public void onSuccess(final List<DeviceOpHistoryGroupData> list) {
                if (list == null) {
                    list = new ArrayList<>();
                }
                if (list.isEmpty()) {
                    boolean unused = DeviceOpHistoryActivity.this.m = true;
                }
                DeviceOpHistoryActivity.this.c.addAll(list);
                DeviceOpHistoryActivity.this.o.post(new Runnable() {
                    public void run() {
                        if (DeviceOpHistoryActivity.this.isValid()) {
                            DeviceOpHistoryActivity.this.s.b((List<DeviceOpHistoryGroupData>) list);
                        }
                    }
                });
            }

            public void onFailure(Error error) {
                if (DeviceOpHistoryActivity.this.isValid()) {
                    DeviceOpHistoryActivity.this.s.a(0);
                }
            }
        });
        return true;
    }

    /* access modifiers changed from: package-private */
    public void startLoad() {
        if (this.mHasMoreData || this.mTimeStamp <= 0) {
            RemoteFamilyApi.a().a((Context) this, this.p, this.mTimeStamp, (AsyncCallback<List<DeviceOpHistoryGroupData>, Error>) new AsyncCallback<List<DeviceOpHistoryGroupData>, Error>() {
                /* renamed from: a */
                public void onSuccess(final List<DeviceOpHistoryGroupData> list) {
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    List unused = DeviceOpHistoryActivity.this.c = list;
                    DeviceOpHistoryActivity.this.o.post(new Runnable() {
                        public void run() {
                            if (DeviceOpHistoryActivity.this.isValid()) {
                                DeviceOpHistoryActivity.this.s.a((List<DeviceOpHistoryGroupData>) list);
                            }
                        }
                    });
                }

                public void onFailure(Error error) {
                    if (DeviceOpHistoryActivity.this.isValid()) {
                        DeviceOpHistoryActivity.this.s.a(0);
                    }
                }
            });
            return;
        }
        this.f19716a.postRefresh();
    }

    public void dismissEditMode() {
        this.f19716a.setPullDownEnabled(true);
        this.z = false;
        this.A.clear();
        hideDeleteBars();
        this.mAdapter.notifyDataSetChanged();
    }

    public void selectAll() {
        int size = this.c.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.A.put(i2, true);
        }
        this.f.setImageResource(R.drawable.un_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.k.setVisibility(0);
        this.k.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.A.size(), new Object[]{Integer.valueOf(this.A.size())}));
    }

    public void unSelectAll() {
        this.A.clear();
        this.f.setImageResource(R.drawable.all_select_selector);
        this.mAdapter.notifyDataSetChanged();
        this.k.setVisibility(8);
    }

    public void deleteSelected() {
        if (this.A.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.delete_msg_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_msg, this.A.size(), new Object[]{Integer.valueOf(this.A.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                new ArrayList();
            }
        }).b().show();
    }

    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 != 4 || keyEvent.getRepeatCount() != 0 || !this.z) {
            return super.onKeyUp(i2, keyEvent);
        }
        dismissEditMode();
        return true;
    }

    class ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19735a;
        TextView b;
        TextView c;
        TextView d;
        TextView e;
        ImageView f;
        View g;
        TextView h;
        TextView i;
        TextView j;

        ViewHolder() {
        }
    }

    class SimpleAdapterNew extends BaseAdapter {
        private List<Object> b = new ArrayList();
        private int[] c = {R.drawable.device_op_history_dot_2, R.drawable.device_op_history_dot_1, R.drawable.device_op_history_dot_3, R.drawable.device_op_history_dot_4, R.drawable.device_op_history_dot_5};

        public long getItemId(int i) {
            return 0;
        }

        SimpleAdapterNew() {
        }

        public void a(List<DeviceOpHistoryGroupData> list, boolean z) {
            if (list != null && list.size() != 0) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    DeviceOpHistoryGroupData deviceOpHistoryGroupData = list.get(i);
                    ArrayList<DeviceOpHistoryChildData> arrayList2 = deviceOpHistoryGroupData.e;
                    if (!(arrayList2 == null || arrayList2.size() == 0)) {
                        arrayList.add(deviceOpHistoryGroupData);
                        for (int i2 = 0; i2 < arrayList2.size(); i2++) {
                            arrayList.add(arrayList2.get(i2));
                        }
                    }
                }
                if (z) {
                    this.b.addAll(arrayList);
                } else {
                    this.b = arrayList;
                }
            }
        }

        public int getCount() {
            return this.b.size();
        }

        public Object getItem(int i) {
            if (i < 0 || i >= this.b.size()) {
                return null;
            }
            return this.b.get(i);
        }

        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                view = LayoutInflater.from(DeviceOpHistoryActivity.this).inflate(R.layout.item_device_op_history_view, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f19735a = view.findViewById(R.id.child_view);
                viewHolder.b = (TextView) viewHolder.f19735a.findViewById(R.id.hourminute);
                viewHolder.c = (TextView) viewHolder.f19735a.findViewById(R.id.action);
                viewHolder.d = (TextView) viewHolder.f19735a.findViewById(R.id.result);
                viewHolder.e = (TextView) viewHolder.f19735a.findViewById(R.id.source);
                viewHolder.e.setMaxLines(2);
                viewHolder.e.setEllipsize(TextUtils.TruncateAt.END);
                viewHolder.f = (ImageView) viewHolder.f19735a.findViewById(R.id.device_history_dot_iv);
                viewHolder.g = view.findViewById(R.id.group_view);
                viewHolder.h = (TextView) viewHolder.g.findViewById(R.id.day);
                viewHolder.i = (TextView) viewHolder.g.findViewById(R.id.month);
                viewHolder.j = (TextView) viewHolder.g.findViewById(R.id.weekday);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            Object item = getItem(i);
            if (item == null) {
                return view;
            }
            if (item instanceof DeviceOpHistoryGroupData) {
                a((DeviceOpHistoryGroupData) item, viewHolder);
            } else if (item instanceof DeviceOpHistoryChildData) {
                a((DeviceOpHistoryChildData) item, viewHolder);
            }
            return view;
        }

        private void a(DeviceOpHistoryGroupData deviceOpHistoryGroupData, ViewHolder viewHolder) {
            viewHolder.f19735a.setVisibility(8);
            viewHolder.g.setVisibility(0);
            TextView textView = viewHolder.h;
            textView.setText("" + deviceOpHistoryGroupData.f19739a);
            TextView textView2 = viewHolder.i;
            textView2.setText("" + deviceOpHistoryGroupData.b + DeviceOpHistoryActivity.this.getResources().getString(R.string.month));
            viewHolder.j.setText(deviceOpHistoryGroupData.c);
        }

        private void a(DeviceOpHistoryChildData deviceOpHistoryChildData, ViewHolder viewHolder) {
            viewHolder.f19735a.setVisibility(0);
            viewHolder.g.setVisibility(8);
            viewHolder.b.setText(deviceOpHistoryChildData.b);
            viewHolder.c.setText(deviceOpHistoryChildData.c);
            if (!TextUtils.isEmpty(deviceOpHistoryChildData.d) || !TextUtils.isEmpty(deviceOpHistoryChildData.e)) {
                if (TextUtils.isEmpty(deviceOpHistoryChildData.d)) {
                    viewHolder.d.setVisibility(8);
                } else {
                    viewHolder.d.setVisibility(0);
                    viewHolder.d.setText(deviceOpHistoryChildData.d);
                }
                if (TextUtils.isEmpty(deviceOpHistoryChildData.e)) {
                    viewHolder.e.setVisibility(8);
                } else {
                    viewHolder.e.setVisibility(0);
                    viewHolder.e.setText(deviceOpHistoryChildData.e);
                }
            } else {
                viewHolder.d.setVisibility(8);
                viewHolder.e.setVisibility(8);
            }
            if (TextUtils.isEmpty(deviceOpHistoryChildData.c)) {
                viewHolder.f.setImageResource(R.drawable.device_op_history_dot_1);
            } else {
                viewHolder.f.setImageResource(this.c[deviceOpHistoryChildData.c.hashCode() % this.c.length]);
            }
        }
    }

    public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {

        /* renamed from: a  reason: collision with root package name */
        private int f19733a = 5;
        private int c = 0;
        private int d = 0;
        private boolean e = true;
        private int f = 0;

        public abstract boolean a(int i, int i2);

        public void onScrollStateChanged(AbsListView absListView, int i) {
        }

        public EndlessScrollListener() {
        }

        public EndlessScrollListener(int i) {
            this.f19733a = i;
        }

        public EndlessScrollListener(int i, int i2) {
            this.f19733a = i;
            this.f = i2;
            this.c = i2;
        }

        public void onScroll(AbsListView absListView, int i, int i2, int i3) {
            if (DeviceOpHistoryActivity.this.q.getBottom() <= DeviceOpHistoryActivity.this.j.getBottom()) {
                DeviceOpHistoryActivity.this.a(false);
            } else {
                DeviceOpHistoryActivity.this.a(true);
            }
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
            if (!this.e && i3 - i2 <= i + this.f19733a) {
                this.e = a(this.c + 1, i3);
            }
        }
    }
}
