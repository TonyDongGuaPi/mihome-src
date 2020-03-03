package com.xiaomi.smarthome.homeroom.initdevice;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.family.RecentUser;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceShareActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.light.group.LightGroupInitActivity;
import com.xiaomi.smarthome.miio.user.UserMamanger;
import com.xiaomi.smarthome.miui10.ActivityControl;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class InitDeviceShareActivity extends BaseActivity {
    public static final String BIND_FINISH_OPENPLUGIN = "bind_finish_openplugin";

    /* renamed from: a  reason: collision with root package name */
    private static final int f18288a = 100;
    /* access modifiers changed from: private */
    public Device b;
    /* access modifiers changed from: private */
    public ActivityControl c = new ActivityControl(this);
    /* access modifiers changed from: private */
    public boolean d;
    /* access modifiers changed from: private */
    public RecyclerView.Adapter e = new RecyclerView.Adapter() {
        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new UserViewHolder(LayoutInflater.from(InitDeviceShareActivity.this.getContext()).inflate(R.layout.item_init_device_share, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            if (i == InitDeviceShareActivity.this.mSharedUsersData.size() - 1) {
                UserViewHolder userViewHolder = (UserViewHolder) viewHolder;
                userViewHolder.f18301a.setText(InitDeviceShareActivity.this.getString(R.string.add));
                userViewHolder.b.setImageResource(R.drawable.share_add);
                userViewHolder.c.setBackground((Drawable) null);
                userViewHolder.b.setOnClickListener(new View.OnClickListener() {
                    public final void onClick(View view) {
                        InitDeviceShareActivity.AnonymousClass1.this.a(view);
                    }
                });
                return;
            }
            UserInfo userInfo = InitDeviceShareActivity.this.mSharedUsersData.get(i);
            UserViewHolder userViewHolder2 = (UserViewHolder) viewHolder;
            userViewHolder2.f18301a.setText(userInfo.e);
            DeviceFactory.a(userViewHolder2.b, userInfo.c, (int) R.drawable.user_default);
            UserMamanger.a().b(userInfo.c, userViewHolder2.b, (BasePostprocessor) null);
            if (InitDeviceShareActivity.this.mSelectRecordMap.get(userInfo) == null) {
                userViewHolder2.c.setBackground((Drawable) null);
            } else {
                userViewHolder2.c.setBackground(InitDeviceShareActivity.this.getResources().getDrawable(R.drawable.init_device_share_item_bg));
            }
            userViewHolder2.b.setOnClickListener(new View.OnClickListener(userInfo) {
                private final /* synthetic */ UserInfo f$1;

                {
                    this.f$1 = r2;
                }

                public final void onClick(View view) {
                    InitDeviceShareActivity.AnonymousClass1.this.a(this.f$1, view);
                }
            });
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(View view) {
            InitDeviceShareActivity.this.showInputUserIdDialog();
            if (InitDeviceShareActivity.this.b != null) {
                STAT.d.aA(InitDeviceShareActivity.this.b.model);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(UserInfo userInfo, View view) {
            if (InitDeviceShareActivity.this.mSelectRecordMap.get(userInfo) == null) {
                InitDeviceShareActivity.this.mSelectRecordMap.put(userInfo, true);
            } else {
                InitDeviceShareActivity.this.mSelectRecordMap.put(userInfo, (Object) null);
            }
            InitDeviceShareActivity.this.e.notifyDataSetChanged();
            if (InitDeviceShareActivity.this.b != null) {
                STAT.d.az(InitDeviceShareActivity.this.b.model);
            }
        }

        public int getItemCount() {
            return InitDeviceShareActivity.this.mSharedUsersData.size();
        }
    };
    /* access modifiers changed from: private */
    public MLAlertDialog f;
    /* access modifiers changed from: private */
    public String g = "";
    /* access modifiers changed from: private */
    public boolean h = false;
    @BindView(2131428794)
    SimpleDraweeView mDeviceImg;
    @BindView(2131430966)
    View mFinishBtn;
    @BindView(2131429477)
    Button mGoNextBtn;
    @BindView(2131431792)
    RecyclerView mRecyclerView;
    Map<UserInfo, Boolean> mSelectRecordMap = new HashMap();
    @BindView(2131432415)
    TextView mShareTitle;
    List<UserInfo> mSharedUsersData = new ArrayList();
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430986)
    TextView mTitleTv;

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(DialogInterface dialogInterface, int i) {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_init_device_share);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        String stringExtra = intent.getStringExtra("device_id");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.b = SmartHomeDeviceManager.a().b(stringExtra);
        }
        if (this.b == null) {
            String stringExtra2 = intent.getStringExtra("device_mac");
            if (!TextUtils.isEmpty(stringExtra2)) {
                this.b = SmartHomeDeviceManager.a().f(stringExtra2);
            }
        }
        if (this.b == null) {
            finish();
            return;
        }
        this.d = intent.getBooleanExtra(AppConstants.O, false);
        a();
        if (!this.b.canBeShared()) {
            this.mShareTitle.setVisibility(8);
            this.mRecyclerView.setVisibility(8);
            return;
        }
        e();
    }

    private void a() {
        this.mTitleBar.setBackground((Drawable) null);
        this.mTitleBar.setBackgroundColor(getResources().getColor(R.color.white));
        this.mTitleTv.setText(R.string.kuailian_success);
        this.mFinishBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceShareActivity.this.b(view);
            }
        });
        DeviceFactory.b(this.b.model, this.mDeviceImg);
        this.mGoNextBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceShareActivity.this.a(view);
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.mRecyclerView.setLayoutManager(linearLayoutManager);
        this.mRecyclerView.addItemDecoration(new MySpaceItemDecoration());
        this.mSharedUsersData.add(new UserInfo());
        this.mRecyclerView.setAdapter(this.e);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        b();
    }

    private class MySpaceItemDecoration extends RecyclerView.ItemDecoration {
        private MySpaceItemDecoration() {
        }

        public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
            int childLayoutPosition = recyclerView.getChildLayoutPosition(view);
            if (childLayoutPosition == 0) {
                rect.left = DisplayUtils.a(54.0f);
            } else if (childLayoutPosition == InitDeviceShareActivity.this.mSharedUsersData.size() - 1) {
                rect.left = DisplayUtils.a(42.0f);
                rect.right = DisplayUtils.a(54.0f);
            } else {
                rect.left = DisplayUtils.a(42.0f);
            }
        }
    }

    public void showInputUserIdDialog() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.dialog_input_user_id, (ViewGroup) null);
        EditText editText = (EditText) inflate.findViewById(R.id.client_remark_input_view_edit);
        MLAlertDialog b2 = new MLAlertDialog.Builder(this).b(inflate).d(true).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener(editText) {
            private final /* synthetic */ EditText f$1;

            {
                this.f$1 = r2;
            }

            public final void onClick(DialogInterface dialogInterface, int i) {
                InitDeviceShareActivity.this.a(this.f$1, dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) $$Lambda$InitDeviceShareActivity$3sBmyDUXKGvQalrKwWy49I3zDk.INSTANCE).b();
        b2.setOnShowListener(new DialogInterface.OnShowListener(editText) {
            private final /* synthetic */ EditText f$1;

            {
                this.f$1 = r2;
            }

            public final void onShow(DialogInterface dialogInterface) {
                InitDeviceShareActivity.this.a(this.f$1, dialogInterface);
            }
        });
        b2.show();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(EditText editText, DialogInterface dialogInterface, int i) {
        if (TextUtils.equals(CoreApi.a().s(), editText.getText().toString())) {
            ToastUtil.a((int) R.string.sh_share_request_fail_owner);
        } else {
            a(editText.getText().toString());
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(EditText editText, DialogInterface dialogInterface) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService("input_method");
        if (inputMethodManager != null) {
            inputMethodManager.showSoftInput(editText, 0);
        }
    }

    private void a(String str) {
        if (TextUtils.isEmpty(str)) {
            ToastUtil.a((Context) this, (int) R.string.please_inpt_account);
        } else {
            RemoteFamilyApi.a().a((Context) this, str, (AsyncCallback<UserInfo, Error>) new AsyncCallback<UserInfo, Error>() {
                /* renamed from: a */
                public void onSuccess(UserInfo userInfo) {
                    boolean z;
                    if (userInfo == null || TextUtils.isEmpty(userInfo.f16462a) || userInfo.f16462a.equalsIgnoreCase("-1") || userInfo.f16462a.equalsIgnoreCase("0")) {
                        ToastUtil.a((Context) InitDeviceShareActivity.this, (int) R.string.sh_user_not_exist);
                        return;
                    }
                    Iterator<UserInfo> it = InitDeviceShareActivity.this.mSharedUsersData.iterator();
                    while (true) {
                        if (!it.hasNext()) {
                            z = false;
                            break;
                        }
                        UserInfo next = it.next();
                        if (userInfo.f16462a.equals(next.f16462a)) {
                            InitDeviceShareActivity.this.mSelectRecordMap.put(next, true);
                            InitDeviceShareActivity.this.mRecyclerView.scrollToPosition(InitDeviceShareActivity.this.mSharedUsersData.indexOf(next));
                            z = true;
                            break;
                        }
                    }
                    if (!z) {
                        InitDeviceShareActivity.this.mSharedUsersData.add(0, userInfo);
                        InitDeviceShareActivity.this.mSelectRecordMap.put(userInfo, true);
                        InitDeviceShareActivity.this.e.notifyDataSetChanged();
                        InitDeviceShareActivity.this.mRecyclerView.scrollToPosition(0);
                    }
                }

                public void onFailure(Error error) {
                    if (error.a() == ErrorCode.ERROR_NETWORK_ERROR.getCode()) {
                        ToastUtil.a((Context) InitDeviceShareActivity.this, (int) R.string.sh_network_not_available);
                    } else {
                        ToastUtil.a((Context) InitDeviceShareActivity.this, (int) R.string.sh_user_not_exist);
                    }
                }
            });
        }
    }

    private static class UserViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        TextView f18301a;
        SimpleDraweeView b;
        View c;

        UserViewHolder(View view) {
            super(view);
            this.f18301a = (TextView) view.findViewById(R.id.name);
            this.b = (SimpleDraweeView) view.findViewById(R.id.device_image);
            this.c = view.findViewById(R.id.image_wrapper);
        }
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.mSelectRecordMap.entrySet()) {
            if (next.getValue() != null) {
                arrayList.add(next.getKey());
            }
        }
        if (arrayList.size() > 0) {
            PluginRecord d2 = CoreApi.a().d(this.b.model);
            if (d2 != null && d2.c() != null) {
                boolean z = true;
                if (d2.c().G() != 1) {
                    z = false;
                }
                if (z) {
                    c();
                } else {
                    d();
                }
            }
        } else if (this.d) {
            f();
        } else {
            g();
        }
    }

    private void c() {
        AnonymousClass3 r0 = new BaseAdapter() {
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
                    view = LayoutInflater.from(InitDeviceShareActivity.this).inflate(R.layout.share_device_permission_dialog, (ViewGroup) null);
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
        if (this.f != null && this.f.isShowing()) {
            this.f.dismiss();
        }
        MLAlertDialog.Builder builder = new MLAlertDialog.Builder(this);
        builder.a((int) R.string.share_select_permission);
        builder.a((ListAdapter) r0, 0, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0) {
                    String unused = InitDeviceShareActivity.this.g = "";
                } else {
                    String unused2 = InitDeviceShareActivity.this.g = ShareDeviceActivity.KEY_SHARE_TYPE_READONLY;
                }
                if (InitDeviceShareActivity.this.f != null && InitDeviceShareActivity.this.f.isShowing()) {
                    dialogInterface.dismiss();
                }
                InitDeviceShareActivity.this.d();
            }
        });
        this.f = builder.b();
        this.f.show();
    }

    /* access modifiers changed from: private */
    public void d() {
        this.mGoNextBtn.setEnabled(false);
        ArrayList arrayList = new ArrayList();
        for (Map.Entry next : this.mSelectRecordMap.entrySet()) {
            if (next.getValue() != null) {
                arrayList.add(((UserInfo) next.getKey()).f16462a);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.b.did);
        RemoteFamilyApi.a().a((Context) this, (List<String>) arrayList2, (List<String>) arrayList, this.g, (AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (InitDeviceShareActivity.this.isValid()) {
                    InitDeviceShareActivity.this.mGoNextBtn.setEnabled(true);
                    if (InitDeviceShareActivity.this.d) {
                        InitDeviceShareActivity.this.f();
                    } else {
                        InitDeviceShareActivity.this.g();
                    }
                }
            }

            public void onFailure(Error error) {
                if (InitDeviceShareActivity.this.isValid()) {
                    InitDeviceShareActivity.this.mGoNextBtn.setEnabled(true);
                    if (error.a() == ErrorCode.ERROR_MAXIMAL_SHARE_REQUEST.getCode()) {
                        ToastUtil.a((Context) InitDeviceShareActivity.this, (int) R.string.sh_share_fremax_request);
                    } else if (error.a() == ErrorCode.ERROR_FEQUENT_REQUEST.getCode()) {
                        ToastUtil.a((Context) InitDeviceShareActivity.this, (int) R.string.sh_share_frequent_request);
                    } else if (error.a() == ErrorCode.ERROR_REPEATED_REQUEST.getCode()) {
                        ToastUtil.a((Context) InitDeviceShareActivity.this, (int) R.string.sh_share_repeated_request);
                    } else {
                        ToastUtil.a((Context) InitDeviceShareActivity.this, (int) R.string.sh_share_request_fail);
                    }
                }
            }
        });
    }

    private void e() {
        RemoteFamilyApi.a().e((AsyncCallback<JSONObject, Error>) new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                if (!InitDeviceShareActivity.this.mIsDestroyed && InitDeviceShareActivity.this.isValid() && !jSONObject.isNull("list")) {
                    InitDeviceShareActivity.this.a(RecentUser.b(jSONObject));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final List<RecentUser> list) {
        RemoteFamilyApi.a().m(this, CoreApi.a().s(), new AsyncCallback<JSONObject, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(JSONObject jSONObject) {
                List<RecentUser> a2;
                if (!InitDeviceShareActivity.this.mIsDestroyed && InitDeviceShareActivity.this.isValid() && !jSONObject.isNull("result") && (a2 = RecentUser.a(jSONObject)) != null && a2.size() != 0) {
                    InitDeviceShareActivity.this.a(a2, (List<RecentUser>) list);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(List<RecentUser> list, List<RecentUser> list2) {
        final ArrayList arrayList = new ArrayList();
        int i = 20;
        if (list.size() <= 20) {
            i = list.size();
        }
        for (int i2 = 0; i2 < i; i2++) {
            arrayList.add(list.get(i2).f15716a);
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
                public void onFailure(Error error) {
                }

                /* renamed from: a */
                public void onSuccess(JSONObject jSONObject) {
                    if (!InitDeviceShareActivity.this.mIsDestroyed && InitDeviceShareActivity.this.isValid()) {
                        InitDeviceShareActivity.this.b((List<Long>) arrayList);
                    }
                }
            });
        } else {
            b((List<Long>) arrayList);
        }
    }

    /* access modifiers changed from: private */
    public void b(List<Long> list) {
        RemoteFamilyApi.a().a((Context) this, list, (AsyncCallback<List<UserInfo>, Error>) new AsyncCallback<List<UserInfo>, Error>() {
            public void onFailure(Error error) {
            }

            /* renamed from: a */
            public void onSuccess(final List<UserInfo> list) {
                if (!InitDeviceShareActivity.this.mIsDestroyed && InitDeviceShareActivity.this.isValid()) {
                    InitDeviceShareActivity.this.mHandler.post(new Runnable() {
                        public void run() {
                            InitDeviceShareActivity.this.mSharedUsersData.addAll(0, list);
                            InitDeviceShareActivity.this.e.notifyDataSetChanged();
                            if (!InitDeviceShareActivity.this.h) {
                                boolean unused = InitDeviceShareActivity.this.h = true;
                                if (InitDeviceShareActivity.this.b != null) {
                                    STAT.c.b(InitDeviceShareActivity.this.b.model, InitDeviceShareActivity.this.mSharedUsersData.size());
                                }
                            }
                        }
                    });
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 100) {
            g();
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        LightGroupInitActivity.open(this, this.b.did, 100);
    }

    /* access modifiers changed from: private */
    public void g() {
        final boolean z;
        PluginRecord d2 = CoreApi.a().d(this.b.model);
        if (d2 != null) {
            final XQProgressHorizontalDialog b2 = XQProgressHorizontalDialog.b(this, getResources().getString(R.string.plugin_downloading) + d2.p());
            final PluginDownloadTask pluginDownloadTask = new PluginDownloadTask();
            final XQProgressDialog xQProgressDialog = new XQProgressDialog(this);
            xQProgressDialog.setCancelable(true);
            xQProgressDialog.setMessage(getResources().getString(R.string.loading_share_info));
            if (d2.l() || d2.k()) {
                xQProgressDialog.show();
                z = false;
            } else {
                z = true;
            }
            sendBroadcast(new Intent(BIND_FINISH_OPENPLUGIN));
            final PluginRecord pluginRecord = d2;
            PluginApi.getInstance().sendMessage(this, d2, 1, new Intent(), this.b.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                private static final float j = 85.0f;

                /* renamed from: a  reason: collision with root package name */
                ValueAnimator f18290a;
                private long h;
                private final Interpolator i = new DecelerateInterpolator();

                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    if (InitDeviceShareActivity.this.isValid()) {
                        pluginDownloadTask.a(pluginDownloadTask);
                        if (b2 != null) {
                            b2.a(true);
                            b2.c();
                            b2.setCancelable(true);
                            b2.show();
                            b2.setOnCancelListener(new DialogInterface.OnCancelListener(pluginDownloadTask) {
                                private final /* synthetic */ PluginDownloadTask f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void onCancel(DialogInterface dialogInterface) {
                                    CoreApi.a().a(PluginRecord.this.o(), this.f$1, (CoreApi.CancelPluginDownloadCallback) null);
                                }
                            });
                        }
                    }
                }

                /* access modifiers changed from: package-private */
                public float a() {
                    if (this.f18290a == null) {
                        synchronized (this) {
                            double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - this.h)) / 4000.0f);
                            Double.isNaN(min);
                            this.f18290a = ValueAnimator.ofFloat(new float[]{(float) ((min * 0.14d) + 0.85d), 0.99f});
                            this.f18290a.setDuration(4000);
                            this.f18290a.setInterpolator(this.i);
                            this.f18290a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    InitDeviceShareActivity.AnonymousClass10.a(XQProgressHorizontalDialog.this, valueAnimator);
                                }
                            });
                            this.f18290a.start();
                        }
                    }
                    return ((Float) this.f18290a.getAnimatedValue()).floatValue();
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ void a(XQProgressHorizontalDialog xQProgressHorizontalDialog, ValueAnimator valueAnimator) {
                    if (xQProgressHorizontalDialog != null) {
                        xQProgressHorizontalDialog.a(100, (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f));
                    }
                }

                public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                    if (InitDeviceShareActivity.this.isValid()) {
                        if (z) {
                            int i2 = (int) (f2 * 100.0f);
                            if (i2 >= 99) {
                                if (this.h == 0) {
                                    this.h = System.currentTimeMillis();
                                }
                                i2 = 99;
                            }
                            if (i2 == 99) {
                                a();
                            } else if (b2 != null) {
                                XQProgressHorizontalDialog xQProgressHorizontalDialog = b2;
                                double d2 = (double) i2;
                                Double.isNaN(d2);
                                xQProgressHorizontalDialog.a(100, (int) (d2 * 0.85d));
                            }
                        } else if (b2 != null) {
                            b2.a(100, (int) (f2 * 100.0f));
                        }
                    }
                }

                public void onDownloadSuccess(PluginRecord pluginRecord) {
                    if (InitDeviceShareActivity.this.isValid() && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onDownloadFailure(PluginError pluginError) {
                    if (InitDeviceShareActivity.this.isValid()) {
                        if (b2 != null) {
                            b2.dismiss();
                        }
                        Toast.makeText(InitDeviceShareActivity.this, R.string.device_enter_failed, 0).show();
                    }
                }

                public void onDownloadCancel() {
                    if (InitDeviceShareActivity.this.isValid() && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onInstallSuccess(PluginRecord pluginRecord) {
                    super.onInstallSuccess(pluginRecord);
                    if (InitDeviceShareActivity.this.isValid() && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onInstallFailure(PluginError pluginError) {
                    super.onInstallFailure(pluginError);
                    if (InitDeviceShareActivity.this.isValid()) {
                        if (b2 != null) {
                            b2.dismiss();
                        }
                        Toast.makeText(InitDeviceShareActivity.this, R.string.device_enter_failed, 0).show();
                    }
                }

                public void onSendSuccess(Bundle bundle) {
                    if (InitDeviceShareActivity.this.isValid()) {
                        xQProgressDialog.dismiss();
                        if (b2 != null) {
                            b2.dismiss();
                        }
                        InitDeviceShareActivity.this.c.a();
                    }
                }

                public void onSendFailure(Error error) {
                    if (InitDeviceShareActivity.this.isValid()) {
                        if (z && b2 != null) {
                            b2.dismiss();
                        }
                        Toast.makeText(InitDeviceShareActivity.this, R.string.device_enter_failed, 0).show();
                    }
                }

                public void onSendCancel() {
                    if (InitDeviceShareActivity.this.isValid() && z && b2 != null) {
                        b2.dismiss();
                    }
                }
            });
        }
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        this.c.b();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.c.c();
        this.h = false;
    }
}
