package com.xiaomi.smarthome.family;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.mijia.camera.CameraNativePluginManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginDeviceInfo;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.family.api.entity.WXDeviceShareLinkResult;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.navigate.NavigateUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.smarthome.miio.page.ShareDeviceDetail;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.miniprogram.MiniProgramManager;
import com.xiaomi.smarthome.miniprogram.model.MyMiniProgramDevice;
import com.xiaomi.smarthome.miniprogram.model.SupportWechatAppInfos;
import com.xiaomi.smarthome.share.ShareActivity;
import com.xiaomi.smarthome.shop.share.ShareManager;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONArray;
import org.json.JSONObject;

public class ShareDeviceActivity extends BaseActivity {
    public static final String KEY_FROM_SHAREDEVICEDETAIL = "key_from_sharedevicedetail";
    public static final String KEY_SHARE_TYPE_DEFAULT = "";
    public static final String KEY_SHARE_TYPE_READONLY = "readonly";
    public static final String PARAM_KEY_USERID = "user_id";

    /* renamed from: a  reason: collision with root package name */
    private static final int f15717a = 0;
    private static final int b = 1;
    /* access modifiers changed from: private */
    public String c;
    /* access modifiers changed from: private */
    public MyMiniProgramDevice d = null;
    private ArrayList<String> e;
    /* access modifiers changed from: private */
    public Device f;
    private int g = 0;
    /* access modifiers changed from: private */
    public boolean h = false;
    /* access modifiers changed from: private */
    public boolean i = false;
    /* access modifiers changed from: private */
    public boolean j;
    /* access modifiers changed from: private */
    public MLAlertDialog k;
    /* access modifiers changed from: private */
    public XQProgressDialog l;
    /* access modifiers changed from: private */
    public ShareReceiver m;
    @BindView(2131427885)
    View mBottomDeleteBar;
    @BindView(2131428729)
    View mDeleteConfirm;
    @BindView(2131428164)
    ImageView mIvCancel;
    @BindView(2131432254)
    ImageView mIvSelectAll;
    LinearLayout mLastListView;
    BaseAdapter mLastListViewAdapter;
    View mLastTitleView;
    LayoutInflater mLayoutInflater;
    @BindView(2131432285)
    TextView mSelectedCntTv;
    String mShareType = "";
    @BindView(2131433000)
    View mTopDeleteBar;
    List<UserInfo> mUserRecordList = new ArrayList();
    View mWxShareContainer;
    @BindView(2131430982)
    ImageView manageTxt;
    /* access modifiers changed from: private */
    public boolean n = false;
    /* access modifiers changed from: private */
    public SparseBooleanArray o = new SparseBooleanArray();
    /* access modifiers changed from: private */
    public AtomicBoolean p = new AtomicBoolean(false);
    /* access modifiers changed from: private */
    public boolean q = false;
    String userName = "";

    private static class ShareReceiver extends BroadcastReceiver {
        private ShareReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (intent != null && TextUtils.equals(intent.getAction(), ShareManager.b)) {
                int intExtra = intent.getIntExtra("result_code", -2);
                intent.getStringExtra("message");
                if (intExtra != 0) {
                    ToastUtil.a((int) R.string.share_canceled);
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mLayoutInflater = LayoutInflater.from(this);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("user_id");
        if (TextUtils.isEmpty(stringExtra) || !stringExtra.equalsIgnoreCase(CoreApi.a().s())) {
            NavigateUtil.a(this);
            finish();
            return;
        }
        this.j = intent.getBooleanExtra(KEY_FROM_SHAREDEVICEDETAIL, false);
        if (Build.VERSION.SDK_INT >= 12) {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                this.c = extras.getString("did", "");
            }
        } else {
            this.c = intent.getStringExtra("did");
        }
        if (this.c == null || TextUtils.isEmpty(this.c)) {
            this.e = intent.getStringArrayListExtra(ShareActivity.ARGS_KEY_BATCH_DIDS);
        } else {
            this.f = SmartHomeDeviceManager.a().b(this.c);
        }
        if (this.f == null && this.e == null) {
            finish();
            return;
        }
        c();
        b();
        if (!CoreApi.a().D()) {
            a();
        }
        UserMamanger.a().a((AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
            public void a(int i) {
            }

            public void a(int i, Object obj) {
            }

            public void a(ShareUserRecord shareUserRecord) {
                ShareDeviceActivity.this.userName = shareUserRecord.nickName;
            }
        });
        RemoteFamilyApi.a().a((Context) this, (AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>) new AsyncCallback<HashMap<String, List<ShareDeviceDetail.ShareUser>>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(HashMap<String, List<ShareDeviceDetail.ShareUser>> hashMap) {
                if (ShareDeviceActivity.this.isValid()) {
                    Set<String> keySet = hashMap.keySet();
                    final ArrayList arrayList = new ArrayList();
                    for (String str : keySet) {
                        arrayList.addAll(hashMap.get(str));
                    }
                    SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                        public void run() {
                            ShareUserRecord.batchInsert(arrayList);
                        }
                    });
                    ShareDeviceActivity.this.updateLastUser(false);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        STAT.c.n(this.mEnterTime);
        this.q = false;
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        MiniProgramManager.a().c();
        if (this.m != null) {
            LocalBroadcastManager.getInstance(this).unregisterReceiver(this.m);
        }
    }

    public void onBackPressed() {
        if (this.n) {
            h();
        } else {
            super.onBackPressed();
        }
    }

    private void a() {
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        if (this.f != null) {
            arrayList.add(this.f.did);
            hashSet.add(this.f.model);
            this.d = new MyMiniProgramDevice(this.f);
        } else if (this.e != null && this.e.size() > 0) {
            Iterator<String> it = this.e.iterator();
            while (it.hasNext()) {
                Device b2 = SmartHomeDeviceManager.a().b(it.next());
                if (b2 != null) {
                    arrayList.add(b2.did);
                }
            }
            if (arrayList.size() <= 1 && arrayList.size() > 0) {
                Device b3 = SmartHomeDeviceManager.a().b((String) arrayList.get(0));
                this.d = new MyMiniProgramDevice(b3);
                hashSet.add(b3.model);
            } else {
                return;
            }
        }
        MiniProgramManager.a().b();
        if (this.l != null && !this.l.isShowing() && isValid()) {
            this.l.show();
        }
        MiniProgramManager.a().a((HashSet<String>) hashSet, (AsyncCallback<SupportWechatAppInfos, Error>) new AsyncCallback<SupportWechatAppInfos, Error>() {
            /* renamed from: a */
            public void onSuccess(SupportWechatAppInfos supportWechatAppInfos) {
                if (ShareDeviceActivity.this.l != null && ShareDeviceActivity.this.l.isShowing() && ShareDeviceActivity.this.isValid()) {
                    ShareDeviceActivity.this.l.dismiss();
                }
                if (supportWechatAppInfos != null) {
                    boolean unused = ShareDeviceActivity.this.h = supportWechatAppInfos.f20052a.get(0).c;
                }
                ShareDeviceActivity.this.mWxShareContainer.setVisibility(0);
                if (ShareDeviceActivity.this.m != null) {
                    LocalBroadcastManager.getInstance(ShareDeviceActivity.this).unregisterReceiver(ShareDeviceActivity.this.m);
                }
                ShareReceiver unused2 = ShareDeviceActivity.this.m = new ShareReceiver();
                LocalBroadcastManager.getInstance(ShareDeviceActivity.this).registerReceiver(ShareDeviceActivity.this.m, new IntentFilter(ShareManager.b));
            }

            public void onFailure(Error error) {
                ShareDeviceActivity.this.mWxShareContainer.setVisibility(0);
                if (ShareDeviceActivity.this.l != null && ShareDeviceActivity.this.l.isShowing() && ShareDeviceActivity.this.isValid()) {
                    ShareDeviceActivity.this.l.dismiss();
                }
            }
        });
        MiniProgramManager.a().a(this.d);
    }

    private void b() {
        PluginRecord d2;
        PluginDeviceInfo c2;
        boolean z = false;
        if (this.f != null) {
            PluginRecord d3 = CoreApi.a().d(this.f.model);
            PluginDeviceInfo pluginDeviceInfo = null;
            if (d3 != null) {
                pluginDeviceInfo = d3.c();
            }
            if (pluginDeviceInfo != null) {
                if (pluginDeviceInfo.G() == 1) {
                    z = true;
                }
                this.i = z;
            } else if (CameraNativePluginManager.a().a(this.f.model)) {
                this.i = true;
            }
        } else if (this.e != null && this.e.size() > 0) {
            Iterator<String> it = this.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = true;
                    break;
                }
                Device b2 = SmartHomeDeviceManager.a().b(it.next());
                if (b2 != null && (d2 = CoreApi.a().d(b2.model)) != null && (c2 = d2.c()) != null && c2.G() != 1) {
                    break;
                }
            }
            if (z) {
                this.i = true;
            }
        }
    }

    private void c() {
        setContentView(R.layout.share_device_activity);
        ButterKnife.bind((Activity) this);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceActivity.this.finish();
                if (ShareDeviceActivity.this.f != null) {
                    STAT.d.at();
                }
            }
        });
        this.manageTxt.setVisibility(this.f == null ? 8 : 0);
        this.manageTxt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                STAT.d.S(ShareDeviceActivity.this.f.model);
                if (ShareDeviceActivity.this.j) {
                    ShareDeviceActivity.this.finish();
                } else {
                    Intent intent = new Intent();
                    intent.setClass(ShareDeviceActivity.this, ShareDeviceDetail.class);
                    intent.putExtra("did", ShareDeviceActivity.this.c);
                    intent.putExtra("pid", ShareDeviceActivity.this.f.pid);
                    intent.putExtra(ShareDeviceDetail.KEY_FROM_SHARE_DEVICE, true);
                    ShareDeviceActivity.this.startActivity(intent);
                }
                if (ShareDeviceActivity.this.f != null) {
                    STAT.d.ay(ShareDeviceActivity.this.f.model);
                }
            }
        });
        this.mIvCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceActivity.this.h();
            }
        });
        this.mIvSelectAll.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (ShareDeviceActivity.this.o.size() == ShareDeviceActivity.this.mUserRecordList.size()) {
                    ShareDeviceActivity.this.l();
                } else {
                    ShareDeviceActivity.this.k();
                }
            }
        });
        this.mDeleteConfirm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceActivity.this.d();
            }
        });
        TextView textView = (TextView) findViewById(R.id.module_a_3_return_title);
        if (this.f != null) {
            textView.setText(getString(R.string.share_device_content_line1, new Object[]{this.f.name}));
        } else {
            textView.setText(R.string.smarthome_share_multiple_devices);
        }
        findViewById(R.id.share_friend).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceActivity.this.a("share_MI_entry");
                if (!ShareDeviceActivity.this.i) {
                    ShareDeviceActivity.this.a((UserInfo) null);
                    if (ShareDeviceActivity.this.f != null) {
                        STAT.d.av(ShareDeviceActivity.this.f.model);
                        return;
                    }
                    return;
                }
                ShareDeviceActivity.this.selectPermission(0, (UserInfo) null);
            }
        });
        findViewById(R.id.share_wx).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceActivity.this.a("share_wechat_entry");
                if (SHApplication.getIWXAPI().isWXAppInstalled()) {
                    ShareDeviceActivity.this.e();
                    if (ShareDeviceActivity.this.f != null) {
                        STAT.d.aw(ShareDeviceActivity.this.f.model);
                        return;
                    }
                    return;
                }
                ToastUtil.a(ShareDeviceActivity.this.getContext(), (CharSequence) ShareDeviceActivity.this.getString(R.string.wx_not_installed));
            }
        });
        View findViewById = findViewById(R.id.share_family);
        findViewById.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ShareDeviceActivity.this.f();
                if (ShareDeviceActivity.this.f != null) {
                    STAT.d.ax(ShareDeviceActivity.this.f.model);
                }
            }
        });
        if (HomeManager.A()) {
            findViewById.setVisibility(8);
        }
        this.mWxShareContainer = findViewById(R.id.share_wx);
        this.mWxShareContainer.setVisibility(8);
        this.mLastTitleView = findViewById(R.id.last_title);
        this.mLastListView = (LinearLayout) findViewById(R.id.last_list);
        this.mLastListViewAdapter = new SimpleAdapter();
        refreshListView();
        if (TitleBarUtil.f11582a) {
            this.mTopDeleteBar.getLayoutParams();
            int a2 = TitleBarUtil.a();
            this.mTopDeleteBar.getLayoutParams().height += a2;
            this.mTopDeleteBar.setPadding(0, this.mTopDeleteBar.getPaddingTop() + a2, 0, 0);
            this.mTopDeleteBar.setLayoutParams(this.mTopDeleteBar.getLayoutParams());
        }
        this.l = new XQProgressDialog(this);
        this.l.setMessage(getString(R.string.device_shop_dialog_loading));
        this.l.setCancelable(false);
    }

    /* access modifiers changed from: private */
    public void a(String str) {
        StringBuilder sb = new StringBuilder();
        if (!TextUtils.isEmpty(this.c)) {
            Device b2 = SmartHomeDeviceManager.a().b(this.c);
            if (b2 != null) {
                sb.append(b2.model);
            }
        } else if (this.e != null) {
            Iterator<String> it = this.e.iterator();
            while (it.hasNext()) {
                Device b3 = SmartHomeDeviceManager.a().b(it.next());
                if (b3 != null) {
                    sb.append(b3.model);
                    sb.append(",");
                }
            }
            if (sb.length() > 1) {
                sb.deleteCharAt(sb.length() - 1);
            }
        }
        if (TextUtils.equals("share_MI_entry", str)) {
            STAT.d.T(sb.toString());
        } else if (TextUtils.equals("share_wechat_entry", str)) {
            STAT.d.U(sb.toString());
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        if (this.o.size() == 0) {
            ToastUtil.a((Context) this, (int) R.string.not_select_deleted_msg);
            return;
        }
        new MLAlertDialog.Builder(this).a((int) R.string.delete_record_title).b((CharSequence) getResources().getQuantityString(R.plurals.delete_record, this.o.size(), new Object[]{Integer.valueOf(this.o.size())})).b((int) R.string.cancel, (DialogInterface.OnClickListener) null).a((int) R.string.confirm_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                final ArrayList arrayList = new ArrayList();
                for (int i2 = 0; i2 < ShareDeviceActivity.this.o.size(); i2++) {
                    int keyAt = ShareDeviceActivity.this.o.keyAt(i2);
                    if (ShareDeviceActivity.this.o.get(keyAt) && ShareDeviceActivity.this.mUserRecordList.size() > keyAt) {
                        try {
                            arrayList.add(ShareDeviceActivity.this.mUserRecordList.get(keyAt).f16462a);
                        } catch (Exception unused) {
                        }
                    }
                }
                RemoteFamilyApi.a().d((List<String>) arrayList, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                    /* renamed from: a */
                    public void onSuccess(JSONObject jSONObject) {
                        if (!jSONObject.has("code")) {
                            ToastUtil.a((int) R.string.smarthome_device_delete_fail);
                        } else if (jSONObject.optInt("code", -999) == 0) {
                            ShareDeviceActivity.this.o.clear();
                            ShareUserRecord.batchDelete(arrayList);
                            ShareDeviceActivity.this.updateLastUser(false);
                            ShareDeviceActivity.this.mSelectedCntTv.setVisibility(8);
                            ShareDeviceActivity.this.mIvSelectAll.setImageResource(R.drawable.all_select_selector);
                        }
                        ShareDeviceActivity.this.h();
                    }

                    public void onFailure(Error error) {
                        ToastUtil.a((int) R.string.smarthome_device_delete_fail);
                        ShareDeviceActivity.this.h();
                    }
                });
            }
        }).b().show();
    }

    /* access modifiers changed from: package-private */
    public void refreshListView() {
        if (this.mLastListViewAdapter != null) {
            this.mLastListView.removeAllViews();
            for (int i2 = 0; i2 < this.mLastListViewAdapter.getCount(); i2++) {
                this.mLastListView.addView(this.mLastListViewAdapter.getView(i2, (View) null, this.mLastListView));
            }
            if (this.mLastListViewAdapter.getCount() == 0) {
                this.mLastTitleView.setVisibility(8);
            } else {
                this.mLastTitleView.setVisibility(0);
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void updateLastUser(boolean z) {
        if (!this.mIsDestroyed || !this.p.getAndSet(true)) {
            this.mUserRecordList.clear();
            if (z) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_user_info_list_" + CoreApi.a().s(), 0);
                String str = null;
                if (sharedPreferences != null) {
                    str = sharedPreferences.getString("content", "");
                }
                if (!TextUtils.isEmpty(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.isNull("list")) {
                            JSONArray optJSONArray = jSONObject.optJSONArray("list");
                            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                                UserInfo a2 = UserInfo.a(optJSONArray.optJSONObject(i2));
                                if (a2 != null && !TextUtils.isEmpty(a2.f16462a)) {
                                    if (!TextUtils.equals(a2.f16462a, "-1")) {
                                        this.mUserRecordList.add(a2);
                                    }
                                }
                            }
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    if (!this.mIsDestroyed && isValid()) {
                        this.p.set(false);
                        return;
                    }
                    return;
                }
            }
            RemoteFamilyApi.a().e((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (!ShareDeviceActivity.this.mIsDestroyed && ShareDeviceActivity.this.isValid()) {
                        if (jSONObject.isNull("list")) {
                            ShareDeviceActivity.this.p.set(false);
                            return;
                        }
                        ShareDeviceActivity.this.a(RecentUser.b(jSONObject));
                    }
                }

                public void onFailure(Error error) {
                    if (!ShareDeviceActivity.this.mIsDestroyed && ShareDeviceActivity.this.isValid()) {
                        ShareDeviceActivity.this.p.set(false);
                    }
                }
            });
            refreshListView();
        }
    }

    /* access modifiers changed from: private */
    public void a(final List<RecentUser> list) {
        RemoteFamilyApi.a().m(this, CoreApi.a().s(), new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (!ShareDeviceActivity.this.mIsDestroyed && ShareDeviceActivity.this.isValid()) {
                    if (jSONObject.isNull("result")) {
                        ShareDeviceActivity.this.p.set(false);
                        return;
                    }
                    List<RecentUser> a2 = RecentUser.a(jSONObject);
                    if (a2 == null || a2.size() == 0) {
                        ShareDeviceActivity.this.p.set(false);
                    } else {
                        ShareDeviceActivity.this.a(a2, list);
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(List<RecentUser> list, List<RecentUser> list2) {
        final ArrayList arrayList = new ArrayList();
        int i2 = 20;
        if (list.size() <= 20) {
            i2 = list.size();
        }
        for (int i3 = 0; i3 < i2; i3++) {
            arrayList.add(list.get(i3).f15716a);
        }
        ArrayList arrayList2 = new ArrayList();
        for (int size = list.size() - 1; size >= 0; size--) {
            int size2 = list2.size() - 1;
            while (true) {
                if (size2 < 0) {
                    break;
                } else if (list.get(size).f15716a.equals(list2.get(size2).f15716a)) {
                    if (list.get(size).b >= list2.get(size2).b) {
                        arrayList2.add(list.get(size).f15716a);
                    } else {
                        arrayList.remove(list.get(size).f15716a);
                    }
                    list2.remove(size2);
                } else {
                    size2--;
                }
            }
        }
        if (arrayList2.size() > 0) {
            RemoteFamilyApi.a().e((List<Long>) arrayList2, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (!ShareDeviceActivity.this.mIsDestroyed && ShareDeviceActivity.this.isValid()) {
                        ShareDeviceActivity.this.b(arrayList);
                    }
                }

                public void onFailure(Error error) {
                    if (!ShareDeviceActivity.this.mIsDestroyed && ShareDeviceActivity.this.isValid()) {
                        ShareDeviceActivity.this.p.set(false);
                    }
                }
            });
        } else {
            b(arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void b(final List<Long> list) {
        RemoteFamilyApi.a().a((Context) this, list, (AsyncCallback<List<UserInfo>, Error>) new AsyncCallback<List<UserInfo>, Error>() {
            /* renamed from: a */
            public void onSuccess(List<UserInfo> list) {
                if (!ShareDeviceActivity.this.mIsDestroyed && ShareDeviceActivity.this.isValid()) {
                    ShareDeviceActivity.this.p.set(false);
                    ShareDeviceActivity.this.mUserRecordList = list;
                    ShareDeviceActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            ShareDeviceActivity.this.refreshListView();
                        }
                    });
                    if (!ShareDeviceActivity.this.q) {
                        boolean unused = ShareDeviceActivity.this.q = true;
                        if (ShareDeviceActivity.this.f != null) {
                            STAT.c.a(ShareDeviceActivity.this.f.model, list.size());
                        }
                    }
                }
            }

            public void onFailure(Error error) {
                if (!ShareDeviceActivity.this.mIsDestroyed && ShareDeviceActivity.this.isValid()) {
                    ShareDeviceActivity.this.p.set(false);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        STAT.c.n(0);
        updateLastUser(true);
    }

    /* access modifiers changed from: package-private */
    public void selectPermission(final int i2, final UserInfo userInfo) {
        AnonymousClass17 r0 = new BaseAdapter() {
            public int getCount() {
                return 2;
            }

            public Object getItem(int i) {
                return null;
            }

            public long getItemId(int i) {
                return 0;
            }

            public View getView(int i, View view, ViewGroup viewGroup) {
                if (view == null) {
                    view = ShareDeviceActivity.this.mLayoutInflater.inflate(R.layout.share_device_permission_dialog, (ViewGroup) null);
                }
                if (i == 0) {
                    ((TextView) view.findViewById(R.id.text1)).setText(R.string.share_permission_can_control);
                    ((TextView) view.findViewById(R.id.text2)).setText(R.string.share_permission_can_control_info);
                } else {
                    ((TextView) view.findViewById(R.id.text1)).setText(R.string.share_permission_cannot_control);
                    ((TextView) view.findViewById(R.id.text2)).setText(R.string.share_permission_cannot_control_info);
                }
                return view;
            }
        };
        if (this.k != null && this.k.isShowing()) {
            this.k.dismiss();
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.share_select_permission);
        builder.a((ListAdapter) r0, this.g, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                ShareDeviceActivity.this.a(i);
                if (ShareDeviceActivity.this.k != null && ShareDeviceActivity.this.k.isShowing()) {
                    dialogInterface.dismiss();
                }
                if (i2 == 0) {
                    ShareDeviceActivity.this.a(userInfo);
                } else {
                    ShareDeviceActivity.this.e();
                }
            }
        });
        this.k = builder.b();
        this.k.show();
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        this.g = i2;
        if (this.g == 0) {
            this.mShareType = "";
        } else {
            this.mShareType = KEY_SHARE_TYPE_READONLY;
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.d == null || this.d.c == null || TextUtils.isEmpty(this.d.c.did)) {
            ToastUtil.a((int) R.string.share_failed);
            return;
        }
        if (this.l != null && !this.l.isShowing() && isValid()) {
            this.l.show();
        }
        MiniProgramManager.a().a(this.d.c.did, (AsyncCallback) new AsyncCallback<WXDeviceShareLinkResult, Error>() {
            /* renamed from: a */
            public void onSuccess(WXDeviceShareLinkResult wXDeviceShareLinkResult) {
                if (wXDeviceShareLinkResult == null) {
                    return;
                }
                if (wXDeviceShareLinkResult.b != 0 || TextUtils.isEmpty(wXDeviceShareLinkResult.f15876a)) {
                    if (ShareDeviceActivity.this.l != null && ShareDeviceActivity.this.isValid() && ShareDeviceActivity.this.l.isShowing()) {
                        ShareDeviceActivity.this.l.dismiss();
                    }
                    if (!TextUtils.isEmpty(wXDeviceShareLinkResult.c)) {
                        ToastUtil.a((CharSequence) wXDeviceShareLinkResult.c);
                    } else {
                        ToastUtil.a((int) R.string.share_failed);
                    }
                } else {
                    MiniProgramManager.a().a(ShareDeviceActivity.this.d, wXDeviceShareLinkResult.f15876a, ShareDeviceActivity.this.h, ShareDeviceActivity.this.mShareType == "", (MiniProgramManager.WXShareCallbackImp) new MiniProgramManager.WXShareCallbackImp(ShareDeviceActivity.this.d.c.did) {
                        public void a() {
                            if (ShareDeviceActivity.this.l != null && ShareDeviceActivity.this.l.isShowing() && ShareDeviceActivity.this.isValid()) {
                                ShareDeviceActivity.this.l.dismiss();
                            }
                        }

                        public void a(String str) {
                            if (ShareDeviceActivity.this.l != null && ShareDeviceActivity.this.l.isShowing() && ShareDeviceActivity.this.isValid()) {
                                ShareDeviceActivity.this.l.dismiss();
                            }
                            ToastUtil.a((int) R.string.share_failed);
                        }
                    });
                }
            }

            public void onFailure(Error error) {
                ToastUtil.a((int) R.string.share_failed);
                if (ShareDeviceActivity.this.l != null && ShareDeviceActivity.this.isValid() && ShareDeviceActivity.this.l.isShowing()) {
                    ShareDeviceActivity.this.l.dismiss();
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(UserInfo userInfo) {
        Intent intent = new Intent();
        intent.setClass(getContext(), ShareActivity.class);
        Bundle bundle = new Bundle();
        if (this.c == null || TextUtils.isEmpty(this.c)) {
            bundle.putStringArrayList(ShareActivity.ARGS_KEY_BATCH_DIDS, this.e);
        } else {
            bundle.putString("did", this.c);
        }
        if (userInfo != null) {
            bundle.putParcelable(ShareActivity.ARGS_KEY_USER_INFO, userInfo);
        }
        bundle.putString("share_type", this.mShareType);
        bundle.putInt(ShareActivity.ARG_KEYS_FROM, ShareActivity.FROM_XIAOMI_ACCOUNT);
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }

    /* access modifiers changed from: private */
    public void f() {
        Intent intent = new Intent();
        intent.setClass(getContext(), ShareFamilyActivity.class);
        Bundle bundle = new Bundle();
        if (this.c == null || TextUtils.isEmpty(this.c)) {
            bundle.putStringArrayList(ShareActivity.ARGS_KEY_BATCH_DIDS, this.e);
        } else {
            bundle.putString("did", this.c);
        }
        intent.putExtras(bundle);
        getContext().startActivity(intent);
    }

    public static void openShareDeviceActivity(Activity activity, String str) {
        Intent intent = new Intent();
        intent.setClass(activity, ShareDeviceActivity.class);
        intent.putExtra("user_id", CoreApi.a().s());
        intent.putExtra("did", str);
        activity.startActivity(intent);
    }

    private class SimpleAdapter extends BaseAdapter {
        public long getItemId(int i) {
            return (long) i;
        }

        private SimpleAdapter() {
        }

        public int getCount() {
            return ShareDeviceActivity.this.mUserRecordList.size();
        }

        public Object getItem(int i) {
            return ShareDeviceActivity.this.mUserRecordList.get(i);
        }

        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder viewHolder;
            if (view == null) {
                view = ShareDeviceActivity.this.mLayoutInflater.inflate(R.layout.share_device_user_item, viewGroup, false);
                viewHolder = new ViewHolder();
                viewHolder.f15744a = (TextView) view.findViewById(R.id.name);
                viewHolder.b = (TextView) view.findViewById(R.id.id);
                viewHolder.c = (SimpleDraweeView) view.findViewById(R.id.icon);
                viewHolder.d = view.findViewById(R.id.right_arrow);
                viewHolder.e = (CheckBox) view.findViewById(R.id.ckb_edit_selected);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            final UserInfo userInfo = ShareDeviceActivity.this.mUserRecordList.get(i);
            UserMamanger.a().b(userInfo.c, viewHolder.c, (BasePostprocessor) null);
            viewHolder.f15744a.setText(userInfo.e);
            viewHolder.b.setText(userInfo.f16462a);
            if (ShareDeviceActivity.this.n) {
                viewHolder.d.setVisibility(8);
                viewHolder.e.setVisibility(0);
                if (ShareDeviceActivity.this.o.get(i)) {
                    viewHolder.e.setChecked(true);
                } else {
                    viewHolder.e.setChecked(false);
                }
            } else {
                viewHolder.d.setVisibility(0);
                viewHolder.e.setVisibility(8);
            }
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (ShareDeviceActivity.this.n) {
                        if (ShareDeviceActivity.this.o.get(i)) {
                            viewHolder.e.setChecked(false);
                            ShareDeviceActivity.this.o.delete(i);
                        } else {
                            viewHolder.e.setChecked(true);
                            ShareDeviceActivity.this.o.put(i, true);
                        }
                        ShareDeviceActivity.this.refreshListView();
                        ShareDeviceActivity.this.mSelectedCntTv.setText(ShareDeviceActivity.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, ShareDeviceActivity.this.o.size(), new Object[]{Integer.valueOf(ShareDeviceActivity.this.o.size())}));
                        ShareDeviceActivity.this.mIvSelectAll.setImageResource(ShareDeviceActivity.this.o.size() == ShareDeviceActivity.this.mUserRecordList.size() ? R.drawable.un_select_selector : R.drawable.all_select_selector);
                    } else if (!ShareDeviceActivity.this.i) {
                        ShareDeviceActivity.this.a(userInfo);
                    } else {
                        ShareDeviceActivity.this.selectPermission(0, userInfo);
                    }
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                public boolean onLongClick(View view) {
                    ShareDeviceActivity.this.o.put(i, true);
                    ShareDeviceActivity.this.g();
                    ShareDeviceActivity.this.refreshListView();
                    ShareDeviceActivity.this.mSelectedCntTv.setText(ShareDeviceActivity.this.getResources().getQuantityString(R.plurals.selected_cnt_tips, ShareDeviceActivity.this.o.size(), new Object[]{Integer.valueOf(ShareDeviceActivity.this.o.size())}));
                    ShareDeviceActivity.this.mIvSelectAll.setImageResource(ShareDeviceActivity.this.o.size() == ShareDeviceActivity.this.mUserRecordList.size() ? R.drawable.un_select_selector : R.drawable.all_select_selector);
                    return true;
                }
            });
            if (i != getCount() - 1) {
                view.setBackgroundResource(R.drawable.common_white_list_padding);
            } else {
                view.setBackgroundResource(R.drawable.common_white_list_padding_no_left_margin);
            }
            return view;
        }

        private class ViewHolder {

            /* renamed from: a  reason: collision with root package name */
            TextView f15744a;
            TextView b;
            SimpleDraweeView c;
            View d;
            CheckBox e;

            private ViewHolder() {
            }
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        this.n = true;
        this.mSelectedCntTv.setText("");
        this.mSelectedCntTv.setVisibility(0);
        refreshListView();
        i();
    }

    /* access modifiers changed from: private */
    public void h() {
        this.n = false;
        refreshListView();
        j();
        this.o.clear();
    }

    private void i() {
        this.mTopDeleteBar.setVisibility(0);
        this.mBottomDeleteBar.setVisibility(0);
        this.mTopDeleteBar.measure(0, 0);
        this.mBottomDeleteBar.measure(0, 0);
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.mTopDeleteBar, View.Y, new float[]{(float) (-this.mTopDeleteBar.getMeasuredHeight()), 0.0f});
        ViewGroup viewGroup = (ViewGroup) this.mBottomDeleteBar.getParent();
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.mBottomDeleteBar, View.Y, new float[]{(float) viewGroup.getHeight(), (float) (viewGroup.getHeight() - this.mBottomDeleteBar.getMeasuredHeight())});
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.play(ofFloat).with(ofFloat2);
        animatorSet.start();
    }

    private void j() {
        this.mTopDeleteBar.setVisibility(8);
        this.mBottomDeleteBar.setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void k() {
        int size = this.mUserRecordList.size();
        for (int i2 = 0; i2 < size; i2++) {
            this.o.put(i2, true);
        }
        this.mIvSelectAll.setImageResource(R.drawable.un_select_selector);
        refreshListView();
        this.mSelectedCntTv.setText(getResources().getQuantityString(R.plurals.selected_cnt_tips, this.o.size(), new Object[]{Integer.valueOf(this.o.size())}));
    }

    /* access modifiers changed from: private */
    public void l() {
        this.o.clear();
        this.mIvSelectAll.setImageResource(R.drawable.all_select_selector);
        refreshListView();
        this.mSelectedCntTv.setText(R.string.selected_0_cnt_tips);
    }
}
