package com.xiaomi.smarthome.device.choosedevice;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.taobao.weex.common.Constants;
import com.xiaomi.qrcode.ScanBarcodeActivity;
import com.xiaomi.smarthome.AppStateNotifier;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.ChooseConnectDevice;
import com.xiaomi.smarthome.device.ChooseDeviceManually;
import com.xiaomi.smarthome.device.ChooseDeviceNestedParent;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.DeviceScanManager;
import com.xiaomi.smarthome.device.LocalRouterDeviceInfo;
import com.xiaomi.smarthome.device.ModelGroupInfo;
import com.xiaomi.smarthome.device.bluetooth.connect.single.BleBindActivityV2;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceHelper;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceSearchApi;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.RouterRemoteApi;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.CommonActivity;
import com.xiaomi.smarthome.framework.permission.PermissionHelper;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.PermssionUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.ListItemView;
import com.xiaomi.smarthome.newui.card.GatewaySupportManger;
import com.xiaomi.smarthome.service.DeviceObserveService;
import com.xiaomi.smarthome.smartconfig.DeviceFinder;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.SmartConfigMainActivity;
import com.xiaomi.smarthome.stat.STAT;
import com.yanzhenjie.permission.Action;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class ChooseDeviceActivity extends BaseActivity implements ChooseDeviceManually.AddDeviceFragmentListener, ChooseDeviceManually.ChooseManuallyListener, DeviceScanManager.OnScanListener {
    public static final int FLAG_RESET_NBIOT = 1001;

    /* renamed from: a  reason: collision with root package name */
    private static final int f15308a = 1000;
    private static final String q = "ChooseDeviceActivity";
    /* access modifiers changed from: private */
    public ChooseDeviceManually b;
    private boolean c = false;
    /* access modifiers changed from: private */
    public List<PluginRecord> d = new ArrayList();
    /* access modifiers changed from: private */
    public ChooseDeviceAdapter e;
    private boolean f = false;
    private HandlerThread g = new HandlerThread(ChooseDeviceActivity.class.getName());
    private Handler h;
    /* access modifiers changed from: private */
    public Dialog i;
    private ChooseDeviceHelper j = new ChooseDeviceHelper();
    private ChooseConnectDevice k;
    private DeviceScanManager l;
    private int m = 0;
    @BindView(2131428784)
    TextView mDeviceFindCnt;
    @BindView(2131428783)
    View mDeviceFindView;
    @BindView(2131429480)
    ImageView mGoScanImg;
    @BindView(2131431010)
    ImageView mMoreBackImg;
    @BindView(2131431013)
    View mMoreHeader;
    @BindView(2131431207)
    View mNoSearchResultView;
    @BindView(2131431215)
    ImageView mNormalBackImg;
    @BindView(2131431218)
    View mNormalHeader;
    @BindView(2131430382)
    View mSearchContainerView;
    @BindView(2131432199)
    EditText mSearchEt;
    @BindView(2131432200)
    View mSearchEtClearBtn;
    @BindView(2131428785)
    ListView mSearchListView;
    @BindView(2131432919)
    ViewGroup mTitleBar;
    @BindView(2131431389)
    View mViewPager;
    /* access modifiers changed from: private */
    public ChooseDeviceSearchApi n = new ChooseDeviceSearchApi();
    private Disposable o;
    private boolean p = false;
    private final TextWatcher r = new TextWatcher() {
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        public void afterTextChanged(Editable editable) {
            String str;
            Editable text = ChooseDeviceActivity.this.mSearchEt.getText();
            if (text == null) {
                str = "";
            } else {
                str = text.toString();
            }
            boolean isEmpty = TextUtils.isEmpty(str);
            ChooseDeviceActivity.this.mSearchEtClearBtn.setVisibility(isEmpty ? 4 : 0);
            if (!isEmpty) {
                ChooseDeviceActivity.this.n.a(str);
                return;
            }
            List access$1300 = ChooseDeviceActivity.this.g();
            if (access$1300 == null || access$1300.isEmpty()) {
                ChooseDeviceActivity.this.mNoSearchResultView.setVisibility(8);
                ChooseDeviceActivity.this.mSearchListView.setVisibility(8);
                return;
            }
            ChooseDeviceActivity.this.a((List<PluginRecord>) access$1300);
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setSoftInputMode(34);
        setContentView(R.layout.activity_choose_device);
        ButterKnife.bind((Activity) this);
        GatewaySupportManger.d().a();
        DeviceObserveService.getInstance().onStartSmartConfig((String) null);
        if (getIntent() != null) {
            this.c = getIntent().getBooleanExtra(SmartConfigDataProvider.N, false);
            int intExtra = getIntent().getIntExtra("category", 0);
            if (intExtra > 0) {
                SmartConfigMainActivity.DEVICE_FROM = intExtra;
            }
        }
        this.g.start();
        this.h = new Handler(this.g.getLooper());
        this.b = new ChooseDeviceManually();
        this.b.a((ChooseDeviceManually.ChooseManuallyListener) this);
        this.b.a((ChooseDeviceManually.AddDeviceFragmentListener) this);
        this.b.a((ChooseDeviceNestedParent.OnScanViewChangeListener) new ChooseDeviceNestedParent.OnScanViewChangeListener() {
            public void a(float f) {
            }

            public void a() {
                ChooseDeviceActivity.this.a(ChooseDeviceActivity.this.b.a());
            }

            public void b() {
                ChooseDeviceActivity.this.a();
            }
        });
        this.mDeviceFindView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseDeviceActivity.this.addFragment();
            }
        });
        a((Fragment) this.b, false);
        e();
        this.mNormalBackImg.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ChooseDeviceActivity.this.c(view);
            }
        });
        this.mMoreBackImg.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ChooseDeviceActivity.this.b(view);
            }
        });
        this.mGoScanImg.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                ChooseDeviceActivity.this.a(view);
            }
        });
        this.j.a((CommonActivity) this, (ChooseDeviceHelper.OnChooseDeviceListener) new ChooseDeviceHelper.OnChooseDeviceListener() {
            public void a() {
                ChooseDeviceActivity.this.b.b();
            }

            public void a(List<ModelGroupInfo> list, List<PluginRecord> list2) {
                ChooseDeviceActivity.this.b.b(list);
                ChooseDeviceActivity.this.b.a(list2);
                ChooseDeviceActivity.this.d.addAll(list2);
                ChooseDeviceActivity.this.f();
            }
        });
        try {
            if (CoreApi.a().q()) {
                if (this.i != null && this.i.isShowing()) {
                    this.i.dismiss();
                }
                this.i = PermssionUtil.a((Activity) this);
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        LocalRouterDeviceInfo.a().a((AsyncResponseCallback<RouterRemoteApi.WifiDetail>) null);
        this.l = DeviceScanManager.instance;
        this.b.a(this.l);
        this.l.checkAll(this);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        PermissionHelper.a(this, true, new Action() {
            public final void onAction(List list) {
                ChooseDeviceActivity.this.b(list);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(List list) {
        STAT.d.k();
        STAT.d.bb();
        Intent intent = new Intent();
        intent.setClass(this, ScanBarcodeActivity.class);
        intent.putExtra("from", 200);
        startActivityForResult(intent, 1000);
    }

    /* access modifiers changed from: private */
    public void a() {
        b();
        this.mDeviceFindView.setVisibility(8);
        this.mDeviceFindCnt.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        b();
        int i2 = 8;
        this.mDeviceFindView.setVisibility(z ? 0 : 8);
        TextView textView = this.mDeviceFindCnt;
        if (this.m != 0 && z) {
            i2 = 0;
        }
        textView.setVisibility(i2);
        TextView textView2 = this.mDeviceFindCnt;
        textView2.setText(this.m + "");
    }

    private void b() {
        if (Build.VERSION.SDK_INT >= 19) {
            TransitionSet transitionSet = new TransitionSet();
            ChangeBounds changeBounds = new ChangeBounds();
            changeBounds.excludeTarget(this.mDeviceFindView, true);
            changeBounds.excludeTarget(this.mDeviceFindCnt, true);
            transitionSet.addTransition(changeBounds);
            Fade fade = new Fade(1);
            Fade fade2 = new Fade(2);
            fade.excludeTarget(this.mSearchEt, true);
            fade2.excludeTarget(this.mSearchEt, true);
            transitionSet.addTransition(fade);
            transitionSet.addTransition(fade2);
            TransitionManager.beginDelayedTransition(this.mTitleBar, transitionSet);
        }
    }

    private void c() {
        if (!this.p) {
            this.p = true;
            STAT.c.g(this.m);
        }
    }

    public void onScan(List<?> list) {
        if (this.b != null) {
            this.b.onScan(list);
        }
        if (this.k != null) {
            this.k.onScan(list);
        }
        this.m = list.size();
        if (this.mDeviceFindView.getVisibility() == 0) {
            if (this.m == 0) {
                this.mDeviceFindCnt.setText("");
                this.mDeviceFindCnt.setVisibility(8);
            } else {
                this.mDeviceFindCnt.setVisibility(0);
                TextView textView = this.mDeviceFindCnt;
                textView.setText(this.m + "");
            }
        }
        c();
    }

    public void addFragment() {
        this.k = new ChooseConnectDevice();
        this.k.a(this.l);
        a((Fragment) this.k, true);
        this.mNormalHeader.setVisibility(8);
        this.mMoreHeader.setVisibility(0);
    }

    private void a(Fragment fragment, boolean z) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        beginTransaction.add((int) R.id.pager, fragment);
        beginTransaction.setCustomAnimations(R.anim.fragment_right_in, R.anim.fragment_left_out, R.anim.fragment_left_in, R.anim.fragment_right_out);
        if (beginTransaction.isAddToBackStackAllowed() && z) {
            beginTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragment.setUserVisibleHint(true);
        beginTransaction.commitAllowingStateLoss();
    }

    public Handler getWorkerHandler() {
        return this.h;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
        }
        if (this.g != null) {
            this.g.quit();
        }
        this.o.dispose();
        this.n.b();
        CollapseStateRecord.a().b();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.l.onResume(this);
        if (this.c) {
            AppStateNotifier stateNotifier = SHApplication.getStateNotifier();
            switch (stateNotifier.a()) {
                case 2:
                    a(stateNotifier);
                    return;
                case 3:
                    d();
                    return;
                default:
                    return;
            }
        }
    }

    private void a(AppStateNotifier appStateNotifier) {
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
        }
        this.i = new XQProgressDialog(this);
        this.i.setCancelable(false);
        ((XQProgressDialog) this.i).setMessage(getResources().getString(R.string.logining_please_wait));
        this.i.show();
        appStateNotifier.a((AppStateNotifier.LoginCallback) new AppStateNotifier.LoginCallback() {
            public void a() {
                ChooseDeviceActivity.this.i.dismiss();
                if (ChooseDeviceActivity.this.d.size() == 0) {
                    CoreApi.a().P();
                }
                Toast.makeText(ChooseDeviceActivity.this, ChooseDeviceActivity.this.getString(R.string.login_success), 0).show();
            }

            public void b() {
                ChooseDeviceActivity.this.i.dismiss();
                Toast.makeText(ChooseDeviceActivity.this, ChooseDeviceActivity.this.getString(R.string.login_fail), 0).show();
                ChooseDeviceActivity.this.d();
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.i != null && this.i.isShowing()) {
            this.i.dismiss();
            this.i = null;
        }
        if (this.i == null) {
            this.i = SHApplication.showLoginDialog(this, true);
            this.i.setOnCancelListener(new DialogInterface.OnCancelListener() {
                public void onCancel(DialogInterface dialogInterface) {
                    ChooseDeviceActivity.this.finish();
                }
            });
            this.i.setCanceledOnTouchOutside(false);
        } else if (!this.i.isShowing()) {
            this.i.show();
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void e() {
        this.mSearchEtClearBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseDeviceActivity.this.mSearchEt.setText("");
            }
        });
        this.mSearchEtClearBtn.setVisibility(4);
        this.mSearchContainerView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            }
        });
        this.e = new ChooseDeviceAdapter();
        this.e.a((List<PluginRecord>) new ArrayList());
        this.mSearchListView.setAdapter(this.e);
        final int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
        this.mSearchListView.setOnTouchListener(new View.OnTouchListener() {

            /* renamed from: a  reason: collision with root package name */
            float f15319a;

            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                        this.f15319a = motionEvent.getY();
                        return false;
                    case 1:
                    case 2:
                    case 3:
                        a(motionEvent.getY() - this.f15319a);
                        return false;
                    default:
                        return false;
                }
            }

            private void a(float f) {
                InputMethodManager inputMethodManager;
                if (f < 0.0f && Math.abs(f) > ((float) scaledTouchSlop) && ChooseDeviceActivity.this.e.getCount() > 0 && (inputMethodManager = (InputMethodManager) ChooseDeviceActivity.this.getSystemService("input_method")) != null && inputMethodManager.isActive(ChooseDeviceActivity.this.mSearchEt)) {
                    inputMethodManager.hideSoftInputFromWindow(ChooseDeviceActivity.this.mSearchEt.getWindowToken(), 0);
                    ChooseDeviceActivity.this.mNormalBackImg.requestFocus();
                }
            }
        });
        this.mSearchEt.addTextChangedListener(this.r);
        this.mSearchEt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ChooseDeviceActivity.this.enterSearch();
                STAT.d.ba();
            }
        });
        this.mSearchEt.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    ChooseDeviceActivity.this.enterSearch();
                }
            }
        });
        this.o = this.n.a().observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<ChooseDeviceSearchApi.QueryResult>() {
            /* renamed from: a */
            public void accept(ChooseDeviceSearchApi.QueryResult queryResult) throws Exception {
                Editable text = ChooseDeviceActivity.this.mSearchEt.getText();
                if (text != null) {
                    String str = queryResult.f15337a;
                    if (text.toString().contains(str)) {
                        if (queryResult.c) {
                            List<Integer> list = queryResult.b;
                            ChooseDeviceActivity.this.mNoSearchResultView.setVisibility(8);
                            ChooseDeviceActivity.this.mSearchListView.setVisibility(0);
                            ArrayList arrayList = new ArrayList();
                            for (Integer intValue : list) {
                                PluginRecord access$900 = ChooseDeviceActivity.this.a(intValue.intValue());
                                if (access$900 != null) {
                                    arrayList.add(access$900);
                                }
                            }
                            if (arrayList.isEmpty()) {
                                STAT.d.aJ(str);
                            }
                            ChooseDeviceActivity.this.a((List<PluginRecord>) arrayList);
                        } else if (!TextUtils.isEmpty(queryResult.d)) {
                            ToastUtil.a(ChooseDeviceActivity.this.getContext(), (CharSequence) queryResult.d);
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public PluginRecord a(int i2) {
        for (PluginRecord next : this.d) {
            if (next.c().d() == i2) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: private */
    public void f() {
        PluginRecord pluginRecord;
        try {
            String stringExtra = getIntent().getStringExtra("url");
            if (!TextUtils.isEmpty(stringExtra)) {
                String queryParameter = Uri.parse(stringExtra).getQueryParameter("model");
                if (!TextUtils.isEmpty(queryParameter)) {
                    Iterator<PluginRecord> it = this.d.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            pluginRecord = null;
                            break;
                        }
                        pluginRecord = it.next();
                        if (queryParameter.equals(pluginRecord.o())) {
                            break;
                        }
                    }
                    if (pluginRecord == null) {
                        Iterator<PluginRecord> it2 = this.d.iterator();
                        while (true) {
                            if (!it2.hasNext()) {
                                break;
                            }
                            PluginRecord next = it2.next();
                            if (DeviceFactory.f(next.o(), queryParameter)) {
                                pluginRecord = next;
                                break;
                            }
                        }
                    }
                    if (pluginRecord != null) {
                        SmartConfigMainActivity.DEVICE_FROM = 4;
                        a(pluginRecord, (Intent) null);
                    }
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private class ChooseDeviceAdapter extends BaseAdapter {
        private List<PluginRecord> b;

        private boolean a(char c) {
            return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9');
        }

        public Object getItem(int i) {
            return null;
        }

        public long getItemId(int i) {
            return 0;
        }

        private ChooseDeviceAdapter() {
        }

        public void a(List<PluginRecord> list) {
            this.b = list;
        }

        public int getCount() {
            return this.b.size();
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = LayoutInflater.from(SHApplication.getAppContext()).inflate(R.layout.choose_device_search_list_item, viewGroup, false);
            }
            final PluginRecord pluginRecord = this.b.get(i);
            TextView textView = (TextView) view.findViewById(R.id.name);
            SimpleDraweeView simpleDraweeView = (SimpleDraweeView) view.findViewById(R.id.image);
            String p = pluginRecord.p();
            if (CoreApi.a().I() == null) {
                Locale.getDefault();
            }
            textView.setText(p);
            DeviceFactory.b(pluginRecord.o(), simpleDraweeView);
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    STAT.d.i(ChooseDeviceActivity.this.mSearchEt.getText().toString(), pluginRecord.o());
                    STAT.d.a(ChooseDeviceActivity.this.mSearchEt.getText().toString(), pluginRecord.o(), i + 1, ChooseDeviceAdapter.this.getCount());
                    ChooseDeviceActivity.this.a(pluginRecord, (Intent) null);
                    ChooseDeviceRecentSearhHistoryHelper.a(ChooseDeviceActivity.this.getContext(), pluginRecord.o());
                }
            });
            ((ListItemView) view).setPosition(i);
            return view;
        }
    }

    /* access modifiers changed from: private */
    public void a(PluginRecord pluginRecord, Intent intent) {
        PluginRecord d2 = CoreApi.a().d(pluginRecord.o());
        if (d2 != null) {
            if (this.f) {
                Log.e("click_device_list", "isMessageSending=" + this.f);
                return;
            }
            this.j.a(d2, intent, this.c);
        }
    }

    /* access modifiers changed from: private */
    public List<PluginRecord> g() {
        List<String> a2 = ChooseDeviceRecentSearhHistoryHelper.a(this);
        ArrayList arrayList = new ArrayList();
        if (a2.isEmpty()) {
            return arrayList;
        }
        for (PluginRecord next : this.d) {
            if (a2.contains(next.o())) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public void a(List<PluginRecord> list) {
        this.e.a(list);
        this.e.notifyDataSetChanged();
        if (list.isEmpty()) {
            this.mNoSearchResultView.setVisibility(0);
            this.mSearchListView.setVisibility(8);
            return;
        }
        this.mNoSearchResultView.setVisibility(8);
        this.mSearchListView.setVisibility(0);
    }

    public void enterSearch() {
        if (this.mSearchContainerView.getVisibility() != 0) {
            a();
            this.mGoScanImg.setVisibility(8);
            ((ConstraintLayout.LayoutParams) this.mSearchEt.getLayoutParams()).rightMargin = DisplayUtils.a(24.0f);
            STAT.c.c();
            STAT.c.k();
            this.mSearchContainerView.setVisibility(0);
            this.mNoSearchResultView.setVisibility(8);
            List<PluginRecord> g2 = g();
            if (g2 != null && !g2.isEmpty()) {
                a(g2);
            }
            this.mSearchEt.setFocusable(true);
            this.mSearchEt.requestFocus();
            this.mSearchEt.setFocusableInTouchMode(true);
            this.mSearchEt.requestFocusFromTouch();
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
            if (inputMethodManager != null) {
                inputMethodManager.showSoftInput(this.mSearchEt, 0);
            }
        }
    }

    public void exitSearch() {
        a(this.b.a());
        this.mGoScanImg.setVisibility(0);
        ((ConstraintLayout.LayoutParams) this.mSearchEt.getLayoutParams()).rightMargin = DisplayUtils.a(9.0f);
        this.mSearchEt.setText("");
        this.mSearchContainerView.setVisibility(8);
        this.mSearchListView.setVisibility(0);
        this.mNoSearchResultView.setVisibility(8);
        this.e.a((List<PluginRecord>) new ArrayList());
        this.e.notifyDataSetChanged();
        this.mSearchEt.setFocusable(false);
        this.mSearchEt.setFocusableInTouchMode(false);
        this.mNormalBackImg.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(this.mSearchEt.getWindowToken(), 0);
        }
    }

    public void onBackPressed() {
        if (this.mSearchContainerView.getVisibility() == 0) {
            exitSearch();
        } else {
            super.onBackPressed();
        }
        if (this.mMoreHeader.getVisibility() == 0) {
            this.mMoreHeader.setVisibility(8);
            this.mNormalHeader.setVisibility(0);
        }
    }

    public void chooseConnectDevice(PluginRecord pluginRecord) {
        a(pluginRecord, (Intent) null);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i2, int i3, Intent intent) {
        boolean z = false;
        if (i3 == -1) {
            String stringExtra = intent.getStringExtra("scan_result");
            STAT.d.aj(stringExtra);
            PluginRecord d2 = CoreApi.a().d(stringExtra);
            if (d2 != null) {
                SmartConfigMainActivity.DEVICE_FROM = 4;
                Intent intent2 = new Intent();
                if (intent.hasExtra(BleBindActivityV2.KEY_QRCODE_OOB)) {
                    intent2.putExtra(BleBindActivityV2.KEY_QRCODE_OOB, intent.getStringExtra(BleBindActivityV2.KEY_QRCODE_OOB));
                }
                a(d2, intent2);
                return;
            } else if (i2 == 1000) {
                Toast.makeText(this, getString(R.string.qr_cannot_find_device), 0).show();
                return;
            }
        }
        if (intent != null) {
            z = intent.getBooleanExtra(Constants.Event.FINISH, true);
        }
        if (z) {
            finish();
        }
    }

    public static void openChooseDevice(Context context) {
        if (!CoreApi.a().q()) {
            LoginApi.a().a(context, 1, (LoginApi.LoginCallback) null);
        } else if (DeviceFinder.a().e()) {
            Toast.makeText(context, R.string.smart_config_connecting, 0).show();
        } else {
            ((Activity) context).startActivityForResult(new Intent(context, ChooseDeviceActivity.class), 3);
        }
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.l.onPause(this);
        DeviceObserveService.getInstance().onFinishSmartConfig((String) null, true);
    }
}
