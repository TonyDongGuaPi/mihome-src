package com.xiaomi.smarthome.homeroom.initdevice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.homeroom.model.RoomConfig;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.library.common.widget.CommonFlowGroup;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.multikey.PowerMultikeyActivity;
import com.xiaomi.smarthome.newui.MoveRoomDialogHelper;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InitDeviceRoomActivity extends BaseActivity {
    public static final String INTENT_KEY_DEVICE_NAME = "device_name";
    public static final String INTENT_KEY_DID = "device_id";
    public static final String INTENT_KEY_MAC = "device_mac";
    public static final String INTENT_KEY_ROOM_NAME = "device_room";

    /* renamed from: a  reason: collision with root package name */
    private static final int f18281a = 101;
    private Device b;
    /* access modifiers changed from: private */
    public List<String> c = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> d = new ArrayList();
    /* access modifiers changed from: private */
    public List<String> e = new ArrayList();
    private String f;
    /* access modifiers changed from: private */
    public XQProgressDialog g;
    @BindView(2131428794)
    SimpleDraweeView mDeviceImg;
    @BindView(2131431774)
    CommonFlowGroup mRecommendFlowView;
    @BindView(2131430969)
    View mReturnBtn;
    @BindView(2131432050)
    CommonFlowGroup mRoomFlowView;
    @BindView(2131432499)
    View mSkipTv;
    @BindView(2131432919)
    View mTitleBar;
    @BindView(2131430975)
    TextView mTitleTv;

    public void onBackPressed() {
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_init_device_room);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            LogUtilGrey.a("InitDeviceRoomActivity", "intent is null");
            finish();
            return;
        }
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
            LogUtil.b("InitDeviceRoomActivity", "mDevice is null");
            finish();
        } else if (!SmartHomeDeviceManager.a().u() || this.b != null) {
            a();
            MiBrainManager.a().a(stringExtra, false, (AsyncCallback) null);
            HomeManager.a().a((HomeManager.IHomeOperationCallback) null);
        } else {
            LogUtil.b("InitDeviceRoomActivity", "isInited is false");
            finish();
        }
    }

    private void a() {
        this.mTitleBar.setBackground((Drawable) null);
        this.mTitleBar.setBackgroundColor(getResources().getColor(R.color.white));
        this.mReturnBtn.setVisibility(8);
        this.mTitleTv.setText(R.string.kuailian_success);
        DeviceFactory.b(this.b.model, this.mDeviceImg);
        this.mSkipTv.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceRoomActivity.this.a(view);
            }
        });
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        STAT.d.bh();
        e();
    }

    private void b() {
        if (this.b != null) {
            this.mRecommendFlowView.showAddView(false);
            this.mRoomFlowView.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
                public void a() {
                    STAT.d.bf();
                    if (InitDeviceRoomActivity.this.c.size() >= 50) {
                        ToastUtil.a((int) R.string.exceed_room_max_count);
                    } else {
                        MoveRoomDialogHelper.b((Context) InitDeviceRoomActivity.this, (List<String>) null, (MoveRoomDialogHelper.addRoomListener) new MoveRoomDialogHelper.addRoomListener() {
                            public final void onSuccess(String str) {
                                InitDeviceRoomActivity.AnonymousClass1.this.a(str);
                            }
                        });
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(String str) {
                    InitDeviceRoomActivity.this.runOnUiThread(new Runnable(str) {
                        private final /* synthetic */ String f$1;

                        {
                            this.f$1 = r2;
                        }

                        public final void run() {
                            InitDeviceRoomActivity.AnonymousClass1.this.b(this.f$1);
                        }
                    });
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void b(String str) {
                    String trim = str == null ? "" : str.trim();
                    if (InitDeviceRoomActivity.this.c.contains(trim)) {
                        InitDeviceRoomActivity.this.mRoomFlowView.setSelectIndex(InitDeviceRoomActivity.this.c.indexOf(trim));
                    } else {
                        InitDeviceRoomActivity.this.d.add(0, trim);
                        InitDeviceRoomActivity.this.c();
                        InitDeviceRoomActivity.this.mRoomFlowView.setSelectIndex(0);
                    }
                    InitDeviceRoomActivity.this.e();
                }

                public void a(int i) {
                    InitDeviceRoomActivity.this.e();
                    STAT.d.be();
                }
            });
            this.mRecommendFlowView.setOnTagClickListener(new CommonFlowGroup.TagClickListener() {
                public void a() {
                }

                public void a(int i) {
                    STAT.d.bg();
                    if (InitDeviceRoomActivity.this.c.size() >= 50) {
                        ToastUtil.a((int) R.string.exceed_room_max_count);
                        InitDeviceRoomActivity.this.mRecommendFlowView.setSelectIndex(-1);
                    } else if (InitDeviceRoomActivity.this.e.get(i) != null) {
                        InitDeviceRoomActivity.this.d.add(0, InitDeviceRoomActivity.this.e.get(i));
                        InitDeviceRoomActivity.this.c();
                        InitDeviceRoomActivity.this.mRecommendFlowView.setSelectIndex(-1);
                        InitDeviceRoomActivity.this.mRoomFlowView.setSelectIndex(0);
                        InitDeviceRoomActivity.this.e();
                    }
                }
            });
            c();
            Room q = HomeManager.a().q();
            if (q == null) {
                this.mRoomFlowView.setSelectIndex(-1);
                return;
            }
            for (int i = 0; i < this.c.size(); i++) {
                if (this.c.get(i).equals(q.e())) {
                    this.mRoomFlowView.setSelectIndex(i);
                    return;
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public void c() {
        d();
        this.mRoomFlowView.setData(this.c);
        this.mRecommendFlowView.setData(this.e);
    }

    private void d() {
        List<Room> a2 = HomeManager.a().a(HomeManager.a().l());
        ArrayList arrayList = new ArrayList();
        for (Room e2 : a2) {
            arrayList.add(e2.e());
        }
        this.d.removeAll(arrayList);
        arrayList.addAll(0, this.d);
        this.c = arrayList;
        List<RoomConfig> A = ((DeviceTagManager) SmartHomeDeviceHelper.a().b()).A();
        if (A.size() > 0) {
            A.remove(A.size() - 1);
        }
        ArrayList arrayList2 = new ArrayList();
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        for (RoomConfig next : A) {
            if (!TextUtils.isEmpty(next.a(I))) {
                arrayList2.add(next.a(I));
            }
        }
        for (String remove : this.c) {
            arrayList2.remove(remove);
        }
        this.e = arrayList2;
    }

    /* access modifiers changed from: private */
    public void e() {
        int i = 0;
        try {
            String P = CoreApi.a().d(this.b.model).c().P();
            if (!TextUtils.isEmpty(P)) {
                i = Integer.parseInt(P);
            }
        } catch (Exception e2) {
            Log.e("InitDeviceRoomActivity", "goNext", e2);
            CoreApi.a().a(0, this.b.model, Log.getStackTraceString(e2));
        }
        if (i <= 1) {
            Intent intent = new Intent(this, InitDeviceNameActivity.class);
            intent.putExtras(getIntent());
            int selectIndex = this.mRoomFlowView.getSelectIndex();
            if (selectIndex >= 0 && this.c.get(selectIndex) != null) {
                intent.putExtra("device_room", this.c.get(selectIndex));
            }
            if (!TextUtils.isEmpty(this.f)) {
                intent.putExtra("device_name", this.f);
            }
            startActivityForResult(intent, 100);
        } else if (!NetworkUtils.c()) {
            ToastUtil.a((int) R.string.popup_select_loc_no_network);
        } else {
            if (this.g == null || !this.g.isShowing()) {
                this.g = new XQProgressDialog(this);
                this.g.setCancelable(true);
                this.g.setMessage(getResources().getString(R.string.loading_share_info));
                this.g.show();
            }
            final Intent intent2 = new Intent(this, PowerMultikeyActivity.class);
            intent2.putExtras(getIntent());
            int selectIndex2 = this.mRoomFlowView.getSelectIndex();
            if (selectIndex2 >= 0) {
                String str = this.c.get(selectIndex2);
                if (!TextUtils.isEmpty(str)) {
                    Room a2 = a(str);
                    if (a2 != null) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(this.b.did);
                        HomeManager.a().a(a2, (List<String>) arrayList, (List<String>) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                            public void a() {
                                if (InitDeviceRoomActivity.this.isValid()) {
                                    InitDeviceRoomActivity.this.startActivityForResult(intent2, 101);
                                    if (InitDeviceRoomActivity.this.g != null && InitDeviceRoomActivity.this.g.isShowing()) {
                                        InitDeviceRoomActivity.this.g.dismiss();
                                    }
                                }
                            }

                            public void a(int i, Error error) {
                                if (InitDeviceRoomActivity.this.isValid() && InitDeviceRoomActivity.this.g != null && InitDeviceRoomActivity.this.g.isShowing()) {
                                    InitDeviceRoomActivity.this.g.dismiss();
                                }
                            }
                        });
                        return;
                    }
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(this.b.did);
                    HomeManager.a().a(str, (List<String>) arrayList2, (String) null, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                        public void a() {
                            if (InitDeviceRoomActivity.this.isValid()) {
                                InitDeviceRoomActivity.this.startActivityForResult(intent2, 101);
                                if (InitDeviceRoomActivity.this.g != null && InitDeviceRoomActivity.this.g.isShowing()) {
                                    InitDeviceRoomActivity.this.g.dismiss();
                                }
                            }
                        }

                        public void a(int i, Error error) {
                            if (error != null && error.a() == -35) {
                                a();
                            } else if (InitDeviceRoomActivity.this.isValid() && InitDeviceRoomActivity.this.g != null && InitDeviceRoomActivity.this.g.isShowing()) {
                                InitDeviceRoomActivity.this.g.dismiss();
                            }
                        }
                    });
                    return;
                }
            }
            HomeManager.a().a(HomeManager.a().m(), (Room) null, this.b, (HomeManager.IHomeOperationCallback) new HomeManager.IHomeOperationCallback() {
                public void a() {
                    if (InitDeviceRoomActivity.this.isValid()) {
                        InitDeviceRoomActivity.this.startActivityForResult(intent2, 101);
                        if (InitDeviceRoomActivity.this.g != null && InitDeviceRoomActivity.this.g.isShowing()) {
                            InitDeviceRoomActivity.this.g.dismiss();
                        }
                    }
                }

                public void a(int i, Error error) {
                    if (InitDeviceRoomActivity.this.isValid() && InitDeviceRoomActivity.this.g != null && InitDeviceRoomActivity.this.g.isShowing()) {
                        InitDeviceRoomActivity.this.g.dismiss();
                    }
                }
            });
        }
    }

    private Room a(String str) {
        for (Room next : HomeManager.a().a(HomeManager.a().l())) {
            if (TextUtils.equals(next.e(), str)) {
                return next;
            }
        }
        return null;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v3, resolved type: java.lang.String} */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onActivityResult(int r3, int r4, @android.support.annotation.Nullable android.content.Intent r5) {
        /*
            r2 = this;
            super.onActivityResult(r3, r4, r5)
            r0 = -1
            if (r4 != r0) goto L_0x0010
            r4 = 100
            if (r3 != r4) goto L_0x000d
            r2.startActivity(r5)
        L_0x000d:
            r2.finish()
        L_0x0010:
            r4 = 101(0x65, float:1.42E-43)
            if (r4 != r3) goto L_0x004e
            java.util.ArrayList r3 = new java.util.ArrayList
            java.util.List<java.lang.String> r4 = r2.c
            r3.<init>(r4)
            r2.d()
            java.util.List<java.lang.String> r4 = r2.c
            boolean r4 = r3.equals(r4)
            if (r4 != 0) goto L_0x004e
            com.xiaomi.smarthome.library.common.widget.CommonFlowGroup r4 = r2.mRoomFlowView
            int r4 = r4.getSelectIndex()
            r0 = 0
            if (r4 < 0) goto L_0x003c
            int r1 = r3.size()
            if (r4 >= r1) goto L_0x003c
            java.lang.Object r3 = r3.get(r4)
            r0 = r3
            java.lang.String r0 = (java.lang.String) r0
        L_0x003c:
            com.xiaomi.smarthome.library.common.widget.CommonFlowGroup r3 = r2.mRoomFlowView
            java.util.List<java.lang.String> r4 = r2.c
            r3.setData(r4)
            com.xiaomi.smarthome.library.common.widget.CommonFlowGroup r3 = r2.mRoomFlowView
            java.util.List<java.lang.String> r4 = r2.c
            int r4 = r4.indexOf(r0)
            r3.setSelectIndex(r4)
        L_0x004e:
            if (r5 == 0) goto L_0x0058
            java.lang.String r3 = "device_name"
            java.lang.String r3 = r5.getStringExtra(r3)
            r2.f = r3
        L_0x0058:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceRoomActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        STAT.c.b(this.b.model);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
    }
}
