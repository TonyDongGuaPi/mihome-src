package com.xiaomi.smarthome.homeroom.initdevice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.renderer.DeviceRenderer;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.DeviceInitChecker;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.RecommendDeviceNameApi;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class InitDeviceNameActivity extends BaseActivity implements RecommendDeviceNameApi.OnRecommendNameListener {

    /* renamed from: a  reason: collision with root package name */
    private static final String f18271a = "InitDeviceNameActivity";
    /* access modifiers changed from: private */
    public Device b;
    private String c;
    /* access modifiers changed from: private */
    public XQProgressDialog d;
    private SharedPreferences e;
    private String f;
    /* access modifiers changed from: private */
    public List<String> g;
    /* access modifiers changed from: private */
    public boolean h;
    @BindView(2131428794)
    SimpleDraweeView mDeviceImg;
    @BindView(2131428814)
    EditText mDeviceNameEt;
    @BindView(2131428821)
    TextView mDeviceNameTips;
    @BindView(2131428897)
    View mDivider;
    @BindView(2131429477)
    Button mGoNextBtn;
    @BindView(2131429864)
    View mInputClearBtn;
    @BindView(2131431772)
    CommonFlowGroup mRecommendTagFlow;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitleTv;

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_init_device_name);
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
        a();
        new RecommendDeviceNameApi().a(this.b.model, (RecommendDeviceNameApi.OnRecommendNameListener) this);
        MiBrainManager.a().a(stringExtra, false, (AsyncCallback) null);
        HomeManager.a().a((HomeManager.IHomeOperationCallback) null);
    }

    private void a() {
        this.mTitleBar.setBackground((Drawable) null);
        this.mTitleBar.setBackgroundColor(getResources().getColor(R.color.white));
        this.mTitleTv.setText(R.string.kuailian_success);
        DeviceFactory.b(this.b.model, this.mDeviceImg);
        this.mReturnBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceNameActivity.this.d(view);
            }
        });
        this.mInputClearBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceNameActivity.this.c(view);
            }
        });
        this.mGoNextBtn.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceNameActivity.this.b(view);
            }
        });
        this.c = getIntent().getStringExtra("device_room");
        this.mDeviceNameTips.setText("");
        this.f = getIntent().getStringExtra("device_name");
        if (!TextUtils.isEmpty(this.f)) {
            this.mDeviceNameEt.setText(this.f);
            this.mDeviceNameEt.setSelection(this.f.length());
            if (!this.f.equals(this.b.name)) {
                this.h = true;
            }
        } else if (this.b.name != null) {
            this.mDeviceNameEt.setText(this.b.name);
            this.mDeviceNameEt.setSelection(this.b.name.length());
        }
        this.mDeviceNameEt.setCursorVisible(false);
        this.mInputClearBtn.setVisibility(8);
        this.mDeviceNameEt.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceNameActivity.this.a(view);
            }
        });
        this.mDeviceNameEt.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable editable) {
            }

            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String obj = InitDeviceNameActivity.this.mDeviceNameEt.getText().toString();
                if (obj.length() > 0) {
                    boolean u = StringUtil.u(obj);
                    InitDeviceNameActivity.this.mInputClearBtn.setVisibility(0);
                    InitDeviceNameActivity.this.mDeviceNameEt.setCursorVisible(true);
                    if (u) {
                        InitDeviceNameActivity.this.mGoNextBtn.setEnabled(false);
                        InitDeviceNameActivity.this.mDeviceNameTips.setText(InitDeviceNameActivity.this.getString(R.string.tag_save_data_description));
                        InitDeviceNameActivity.this.mDeviceNameTips.setTextColor(Color.parseColor("#FFF46666"));
                    } else if (!HomeManager.A(obj)) {
                        InitDeviceNameActivity.this.mGoNextBtn.setEnabled(false);
                        InitDeviceNameActivity.this.mDeviceNameTips.setText(InitDeviceNameActivity.this.getString(R.string.room_name_too_long));
                        InitDeviceNameActivity.this.mDeviceNameTips.setTextColor(Color.parseColor("#FFF46666"));
                    } else {
                        boolean unused = InitDeviceNameActivity.this.h = true;
                        InitDeviceNameActivity.this.mGoNextBtn.setEnabled(true);
                        InitDeviceNameActivity.this.mDeviceNameTips.setText("");
                    }
                } else {
                    InitDeviceNameActivity.this.mGoNextBtn.setEnabled(false);
                    InitDeviceNameActivity.this.mInputClearBtn.setVisibility(8);
                    InitDeviceNameActivity.this.mDeviceNameTips.setText("");
                }
                if (InitDeviceNameActivity.this.mRecommendTagFlow.getVisibility() == 0 && InitDeviceNameActivity.this.g != null) {
                    InitDeviceNameActivity.this.mRecommendTagFlow.setSelectIndex(-1);
                    for (int i4 = 0; i4 < InitDeviceNameActivity.this.g.size(); i4++) {
                        if (obj.equals(InitDeviceNameActivity.this.g.get(i4))) {
                            InitDeviceNameActivity.this.mRecommendTagFlow.setSelectIndex(i4);
                            return;
                        }
                    }
                }
            }
        });
        this.mRecommendTagFlow.showAddView(false);
        this.mRecommendTagFlow.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
            public void a() {
            }

            public void a(int i) {
                if (InitDeviceNameActivity.this.g != null && InitDeviceNameActivity.this.g.size() > i) {
                    InitDeviceNameActivity.this.mDeviceNameEt.setText(InitDeviceNameActivity.this.a((String) InitDeviceNameActivity.this.g.get(i)));
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        this.mDeviceNameEt.setText("");
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        b();
        STAT.d.f(this.h ? "2" : "1", this.mDeviceNameEt.getText().toString(), this.b.model);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        this.mDeviceNameEt.setCursorVisible(true);
        if (!TextUtils.isEmpty(this.mDeviceNameEt.getText().toString())) {
            this.mInputClearBtn.setVisibility(0);
        }
    }

    public void onRecommendName(String str, List<String> list) {
        this.g = list;
        if (!this.h && !TextUtils.isEmpty(str)) {
            String a2 = a(str);
            this.mDeviceNameEt.setText(a2);
            this.mDeviceNameEt.setSelection(a2.length());
            this.mDeviceNameTips.setText(R.string.init_device_choose_name_tip);
            this.h = true;
        }
        if (list.size() > 0) {
            this.mRecommendTagFlow.setVisibility(0);
            this.mRecommendTagFlow.setData(list);
            this.mDivider.setVisibility(0);
            this.mRecommendTagFlow.setSelectIndex(-1);
            for (int i = 0; i < list.size(); i++) {
                if (this.mDeviceNameEt.getText().toString().equals(list.get(i))) {
                    this.mRecommendTagFlow.setSelectIndex(i);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String str2 = str;
        int i = 2;
        while (b(str2)) {
            str2 = str + i;
            i++;
        }
        return str2;
    }

    private boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (Device device : SmartHomeDeviceManager.a().d()) {
            if (str.equals(device.name)) {
                return true;
            }
        }
        return false;
    }

    private void b() {
        if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.popup_select_loc_no_network);
            return;
        }
        if (this.d == null || !this.d.isShowing()) {
            this.d = new XQProgressDialog(this);
            this.d.setCancelable(true);
            this.d.setMessage(getResources().getString(R.string.loading_share_info));
            this.d.show();
        }
        if (this.h) {
            c();
        } else {
            d();
        }
    }

    private void c() {
        String obj = this.mDeviceNameEt.getText().toString();
        LogUtilGrey.a(f18271a, "doSaveDeviceName:" + obj);
        if (!TextUtils.isEmpty(obj)) {
            DeviceRenderer.a(this, this.b, obj, (XQProgressDialog) null, new AsyncCallback() {
                public void onSuccess(Object obj) {
                    if (InitDeviceNameActivity.this.isValid()) {
                        InitDeviceNameActivity.this.d();
                    }
                }

                public void onFailure(Error error) {
                    if (InitDeviceNameActivity.this.isValid()) {
                        ToastUtil.a((int) R.string.change_back_name_fail);
                        if (InitDeviceNameActivity.this.d != null && InitDeviceNameActivity.this.d.isShowing()) {
                            InitDeviceNameActivity.this.d.dismiss();
                        }
                        LogUtilGrey.a(InitDeviceNameActivity.f18271a, "error" + error);
                    }
                }
            }, true);
        }
    }

    /* access modifiers changed from: private */
    public void d() {
        List<Room> a2 = HomeManager.a().a(HomeManager.a().l());
        ArrayList arrayList = new ArrayList();
        for (Room e2 : a2) {
            arrayList.add(e2.e());
        }
        LogUtilGrey.a(f18271a, "doSaveDeviceRoom  RoomName:" + this.c + " names:" + arrayList);
        if (arrayList.contains(this.c)) {
            f();
        } else if (TextUtils.isEmpty(this.c) || getString(R.string.room_default).equals(this.c)) {
            g();
        } else {
            e();
        }
    }

    private void e() {
        LogUtilGrey.a(f18271a, "createRoom  did:" + this.b.did);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b.did);
        HomeManager.a().a(this.c, (List<String>) arrayList, (String) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
            public void a() {
                if (InitDeviceNameActivity.this.isValid()) {
                    InitDeviceNameActivity.this.f();
                }
            }

            public void a(int i, Error error) {
                if (error != null && error.a() == -35) {
                    a();
                } else if (InitDeviceNameActivity.this.isValid()) {
                    ToastUtil.a((int) R.string.set_room_fail);
                    LogUtilGrey.a(InitDeviceNameActivity.f18271a, "error" + error);
                    if (InitDeviceNameActivity.this.d != null && InitDeviceNameActivity.this.d.isShowing()) {
                        InitDeviceNameActivity.this.d.dismiss();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void f() {
        Room room = null;
        for (Room next : HomeManager.a().a(HomeManager.a().l())) {
            if (TextUtils.equals(next.e(), this.c)) {
                room = next;
            }
        }
        LogUtilGrey.a(f18271a, "doSaveDeviceToRoom  room:" + room);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b.did);
        if (room == null || TextUtils.isEmpty(room.d())) {
            g();
        } else {
            HomeManager.a().a(room, (List<String>) arrayList, (List<String>) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    LogUtilGrey.a(InitDeviceNameActivity.f18271a, "doSaveDeviceToRoom.editRoom  onSuccess");
                    if (InitDeviceNameActivity.this.isValid()) {
                        InitDeviceNameActivity.this.h();
                    }
                }

                public void a(int i, Error error) {
                    if (InitDeviceNameActivity.this.isValid()) {
                        LogUtilGrey.a(InitDeviceNameActivity.f18271a, "error" + error);
                        ToastUtil.a((int) R.string.multikey_noroom_hint);
                        if (InitDeviceNameActivity.this.d != null && InitDeviceNameActivity.this.d.isShowing()) {
                            InitDeviceNameActivity.this.d.dismiss();
                        }
                    }
                }
            });
        }
    }

    private void g() {
        HomeManager.a().a(HomeManager.a().m(), (Room) null, this.b, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
            public void a() {
                LogUtilGrey.a(InitDeviceNameActivity.f18271a, "doSaveDeviceToDefaultRoom  bindDeviceToRoom.onSuccess");
                if (InitDeviceNameActivity.this.isValid()) {
                    InitDeviceNameActivity.this.h();
                }
            }

            public void a(int i, Error error) {
                if (InitDeviceNameActivity.this.isValid()) {
                    LogUtilGrey.a(InitDeviceNameActivity.f18271a, "error" + error);
                    ToastUtil.a((int) R.string.tag_recommend_room);
                    if (InitDeviceNameActivity.this.d != null && InitDeviceNameActivity.this.d.isShowing()) {
                        InitDeviceNameActivity.this.d.dismiss();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void h() {
        LogUtilGrey.a(f18271a, "goNext  did:" + this.b.did);
        ArrayList arrayList = new ArrayList();
        arrayList.add(this.b.did);
        DeviceInitChecker.a(arrayList, new AsyncCallback() {
            /* access modifiers changed from: private */
            public void a() {
                LogUtilGrey.a(InitDeviceNameActivity.f18271a, "goNextShareOrVoicePage  did:" + InitDeviceNameActivity.this.b.did);
                if (InitDeviceNameActivity.this.isValid()) {
                    if (InitDeviceNameActivity.this.d != null && InitDeviceNameActivity.this.d.isShowing()) {
                        InitDeviceNameActivity.this.d.dismiss();
                    }
                    Intent intent = new Intent(InitDeviceNameActivity.this, InitDeviceShareActivity.class);
                    if (MiBrainManager.a().a(InitDeviceNameActivity.this.b.did)) {
                        intent = new Intent(InitDeviceNameActivity.this, InitDeviceMiBrainActivity.class);
                    }
                    intent.putExtras(InitDeviceNameActivity.this.getIntent());
                    InitDeviceNameActivity.this.setResult(-1, intent);
                    InitDeviceNameActivity.this.finish();
                }
            }

            public void onSuccess(Object obj) {
                MiBrainManager.a().a(InitDeviceNameActivity.this.b.did, true, new AsyncCallback() {
                    public void onSuccess(Object obj) {
                        AnonymousClass7.this.a();
                    }

                    public void onFailure(Error error) {
                        AnonymousClass7.this.a();
                    }
                });
            }

            public void onFailure(Error error) {
                if (InitDeviceNameActivity.this.isValid()) {
                    LogUtilGrey.a(InitDeviceNameActivity.f18271a, "error" + error);
                    ToastUtil.a((int) R.string.set_failed);
                    if (InitDeviceNameActivity.this.d != null && InitDeviceNameActivity.this.d.isShowing()) {
                        InitDeviceNameActivity.this.d.dismiss();
                    }
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        STAT.c.c(this.b.model);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }

    public void onBackPressed() {
        Intent intent = new Intent();
        if (this.h) {
            intent.putExtra("device_name", this.mDeviceNameEt.getText().toString());
        }
        setResult(0, intent);
        finish();
    }
}
