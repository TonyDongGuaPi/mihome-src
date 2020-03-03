package com.xiaomi.smarthome.light.group;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.api.DeviceApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.light.group.LightGroupInitActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class LightGroupInitActivity extends BaseActivity {
    private static final int d = 272;
    private static final int e = 256;
    private static final long f = 10000;

    /* renamed from: a  reason: collision with root package name */
    private HandlerThread f19127a;
    /* access modifiers changed from: private */
    public Handler b;
    /* access modifiers changed from: private */
    public boolean c;
    String did;
    /* access modifiers changed from: private */
    public Handler g = new Handler();
    /* access modifiers changed from: private */
    public LightGroupAdapter h;
    private ArrayList<String> i;
    /* access modifiers changed from: private */
    public String j = "0";
    private Device k;
    private TextView l;
    SmartHomeDeviceManager.IClientDeviceListener listener = new SmartHomeDeviceManager.IClientDeviceListener() {
        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public void a(int i) {
            if (i == 3 && SmartHomeDeviceManager.a().b(LightGroupInitActivity.this.did) != null) {
                SmartHomeDeviceManager.a().c(LightGroupInitActivity.this.listener);
                LightGroupInitActivity.this.a(-1);
            }
        }
    };
    private TextView m;
    private XQProgressDialog n;

    public static void open(Activity activity, String str, int i2) {
        Intent intent = new Intent();
        intent.setClass(activity, LightGroupInitActivity.class);
        intent.putExtra("did", str);
        activity.startActivityForResult(intent, i2);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_light_group_init);
        this.did = getIntent().getStringExtra("did");
        if (TextUtils.isEmpty(this.did)) {
            a(0);
        }
        this.k = SmartHomeDeviceManager.a().b(this.did);
        if (this.k == null || !LightGroupManager.a().b(this.k)) {
            a(0);
        }
        this.i = new ArrayList<>();
        this.i.add(this.did);
        f();
        e();
        d();
    }

    /* access modifiers changed from: private */
    public void a() {
        long j2 = 0;
        for (int i2 = 0; i2 < this.h.c.size(); i2++) {
            if (!TextUtils.equals(((LightGroupData) this.h.c.get(i2)).b, "1")) {
                j2 += 2000;
            }
        }
        if (j2 < 10000) {
            j2 = 10000;
        }
        LogUtil.c("zxt", "countdownTimer: " + j2);
        this.b.removeMessages(256);
        this.b.sendEmptyMessageDelayed(256, j2);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        a();
        b();
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        this.b.removeMessages(256);
        c();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        hideLoadingDialog();
        this.b.removeCallbacksAndMessages((Object) null);
        this.f19127a.quit();
        SmartHomeDeviceManager.a().c(this.listener);
    }

    public void onBackPressed() {
        if (this.h.c.size() <= 0 || !i().isEmpty()) {
            a(0);
        } else {
            a(-1);
        }
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        setResult(i2);
        finish();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.c = true;
        this.b.sendEmptyMessage(272);
    }

    /* access modifiers changed from: private */
    public void c() {
        this.c = false;
        this.b.removeMessages(272);
    }

    private void d() {
        ArrayList arrayList = new ArrayList();
        List<Device> d2 = SmartHomeDeviceManager.a().d(this.did);
        for (int i2 = 0; i2 < d2.size(); i2++) {
            Device device = d2.get(i2);
            if (device != null) {
                arrayList.add(new LightGroupData(device, "0"));
            }
        }
        this.h.a((ArrayList<LightGroupData>) arrayList);
    }

    private void e() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        findViewById(R.id.module_a_3_right_text_btn).setVisibility(8);
        findViewById(R.id.module_a_3_right_btn).setVisibility(8);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupInitActivity.this.c(view);
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.light_group_init);
        this.m = (TextView) findViewById(R.id.tv_jump_over);
        setJumpButtonEnable(false);
        this.m.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupInitActivity.this.b(view);
            }
        });
        this.l = (TextView) findViewById(R.id.tv_all_retry);
        setRetryButtonEnable(false);
        this.l.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                LightGroupInitActivity.this.a(view);
            }
        });
        this.h = new LightGroupAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(this.h);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(View view) {
        onBackPressed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(View view) {
        h();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        retry(a(true));
        setRetryButtonEnable(false);
    }

    private void f() {
        this.f19127a = new HandlerThread("check-message-coming");
        this.f19127a.start();
        this.b = new Handler(this.f19127a.getLooper()) {
            public void handleMessage(Message message) {
                if (LightGroupInitActivity.this.c && message.what == 272) {
                    LightGroupInitActivity.this.g();
                }
                if (message.what == 256) {
                    LightGroupInitActivity.this.c();
                    for (int i = 0; i < LightGroupInitActivity.this.h.c.size(); i++) {
                        if (TextUtils.equals(((LightGroupData) LightGroupInitActivity.this.h.c.get(i)).b, "0")) {
                            ((LightGroupData) LightGroupInitActivity.this.h.c.get(i)).b = "3";
                        }
                    }
                    LightGroupInitActivity.this.g.post(new Runnable() {
                        public final void run() {
                            LightGroupInitActivity.AnonymousClass1.this.a();
                        }
                    });
                    LogUtil.c("zxt", "timeout!");
                }
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a() {
                LightGroupInitActivity.this.h.notifyDataSetChanged();
                LightGroupInitActivity.this.setJumpButtonEnable(LightGroupInitActivity.this.j());
                LightGroupInitActivity.this.setRetryButtonEnable(true);
            }
        };
    }

    /* access modifiers changed from: private */
    public void g() {
        try {
            final ArrayList arrayList = new ArrayList();
            DeviceApi.getInstance().queryLightGroup(this, this.i, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    try {
                        JSONArray optJSONArray = new JSONObject(str).optJSONArray("result");
                        if (optJSONArray != null) {
                            for (int i = 0; i < optJSONArray.length(); i++) {
                                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                                String optString = optJSONObject.optString("group_did");
                                if (TextUtils.equals(optString, LightGroupInitActivity.this.did)) {
                                    String unused = LightGroupInitActivity.this.j = optJSONObject.optString("status", "0");
                                    SmartHomeDeviceManager.a().a(optString, LightGroupInitActivity.this.j);
                                    JSONObject optJSONObject2 = optJSONObject.optJSONObject("member_ship");
                                    if (optJSONObject2 != null) {
                                        Iterator<String> keys = optJSONObject2.keys();
                                        while (keys.hasNext()) {
                                            String next = keys.next();
                                            String optString2 = optJSONObject2.optString(next, "3");
                                            Device c = SmartHomeDeviceManager.a().c(next);
                                            if (c != null) {
                                                arrayList.add(new LightGroupData(c, optString2));
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (TextUtils.equals(LightGroupInitActivity.this.j, "0")) {
                            LightGroupInitActivity.this.b.sendEmptyMessageDelayed(272, 5000);
                        } else {
                            LightGroupInitActivity.this.c();
                            if (TextUtils.equals(LightGroupInitActivity.this.j, "1")) {
                                ToastUtil.a((int) R.string.light_group_init_success);
                                LightGroupInitActivity.this.b.removeMessages(256);
                                LightGroupInitActivity.this.g.postDelayed(new Runnable() {
                                    public final void run() {
                                        LightGroupInitActivity.AnonymousClass2.this.c();
                                    }
                                }, 1000);
                            } else {
                                LightGroupInitActivity.this.g.post(new Runnable() {
                                    public final void run() {
                                        LightGroupInitActivity.AnonymousClass2.this.b();
                                    }
                                });
                            }
                        }
                        if (!LightGroupInitActivity.this.h.c.isEmpty()) {
                            DiffUtil.calculateDiff(new DiffCallBack(LightGroupInitActivity.this.h.c, arrayList), false).dispatchUpdatesTo((RecyclerView.Adapter) LightGroupInitActivity.this.h);
                            LightGroupInitActivity.this.h.a((ArrayList<LightGroupData>) arrayList);
                        } else {
                            LightGroupInitActivity.this.g.post(new Runnable(arrayList) {
                                private final /* synthetic */ ArrayList f$1;

                                {
                                    this.f$1 = r2;
                                }

                                public final void run() {
                                    LightGroupInitActivity.AnonymousClass2.this.a(this.f$1);
                                }
                            });
                        }
                        LightGroupInitActivity.this.g.post(new Runnable() {
                            public final void run() {
                                LightGroupInitActivity.AnonymousClass2.this.a();
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void c() {
                    LightGroupInitActivity.this.a(-1);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void b() {
                    LightGroupInitActivity.this.setRetryButtonEnable(true);
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a(ArrayList arrayList) {
                    LightGroupInitActivity.this.h.a((ArrayList<LightGroupData>) arrayList);
                    LightGroupInitActivity.this.h.notifyDataSetChanged();
                }

                /* access modifiers changed from: private */
                public /* synthetic */ void a() {
                    LightGroupInitActivity.this.setJumpButtonEnable(LightGroupInitActivity.this.j());
                }

                public void onFailure(Error error) {
                    ToastUtil.a((CharSequence) error.b());
                    LightGroupInitActivity.this.b.sendEmptyMessageDelayed(272, 5000);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void setRetryButtonEnable(boolean z) {
        int i2;
        this.l.setEnabled(z);
        TextView textView = this.l;
        if (z) {
            i2 = getResources().getColor(R.color.std_dialog_button_normal);
        } else {
            i2 = getResources().getColor(R.color.std_list_subtitle);
        }
        textView.setTextColor(i2);
    }

    public void setJumpButtonEnable(boolean z) {
        int i2;
        this.m.setEnabled(z);
        TextView textView = this.m;
        if (z) {
            i2 = getResources().getColor(R.color.std_dialog_button_normal);
        } else {
            i2 = getResources().getColor(R.color.std_list_subtitle);
        }
        textView.setTextColor(i2);
    }

    public void retry(ArrayList<String> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            c();
            this.b.removeMessages(256);
            LogUtil.c("zxt", "retry: " + arrayList.size());
            ArrayList arrayList2 = new ArrayList();
            DeviceApi.getInstance().modLightGroup(this, this.did, arrayList, arrayList2, new AsyncCallback<String, Error>() {
                /* renamed from: a */
                public void onSuccess(String str) {
                    LightGroupInitActivity.this.b();
                    LightGroupInitActivity.this.a();
                }

                public void onFailure(Error error) {
                    ToastUtil.a((CharSequence) error.b());
                    LightGroupInitActivity.this.b();
                    LightGroupInitActivity.this.a();
                }
            }, true);
        }
    }

    private void h() {
        new MLAlertDialog.Builder(this).b((CharSequence) getString(R.string.light_grouped_jump_over)).a((int) R.string.jump_over, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                LightGroupInitActivity.this.b(dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                LightGroupInitActivity.this.a(dialogInterface, i);
            }
        }).a(getResources().getColor(R.color.std_dialog_button_red), -1).d();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(DialogInterface dialogInterface, int i2) {
        showLoadingDialog();
        c();
        this.b.removeMessages(256);
        ArrayList arrayList = new ArrayList();
        DeviceApi.getInstance().modLightGroup(this, this.did, arrayList, i(), new AsyncCallback<String, Error>() {
            /* renamed from: a */
            public void onSuccess(String str) {
                SmartHomeDeviceManager.a().a(LightGroupInitActivity.this.listener);
                SmartHomeDeviceManager.a().p();
                LightGroupInitActivity.this.hideLoadingDialog();
            }

            public void onFailure(Error error) {
                ToastUtil.a((CharSequence) error.b());
                LightGroupInitActivity.this.b();
                LightGroupInitActivity.this.a();
                LightGroupInitActivity.this.hideLoadingDialog();
            }
        }, true);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(DialogInterface dialogInterface, int i2) {
        b();
    }

    private ArrayList<String> a(boolean z) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.h.c.size(); i2++) {
            if (TextUtils.equals(((LightGroupData) this.h.c.get(i2)).b, "3")) {
                arrayList.add(((LightGroupData) this.h.c.get(i2)).f19135a.did);
                if (z) {
                    ((LightGroupData) this.h.c.get(i2)).b = "0";
                }
            }
        }
        if (arrayList.size() > 0 && z) {
            this.h.notifyDataSetChanged();
        }
        return arrayList;
    }

    private ArrayList<String> i() {
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i2 = 0; i2 < this.h.c.size(); i2++) {
            if (!TextUtils.equals(((LightGroupData) this.h.c.get(i2)).b, "1")) {
                arrayList.add(((LightGroupData) this.h.c.get(i2)).f19135a.did);
            }
        }
        return arrayList;
    }

    /* access modifiers changed from: private */
    public boolean j() {
        int i2 = 0;
        for (int i3 = 0; i3 < this.h.c.size(); i3++) {
            if (!TextUtils.equals(((LightGroupData) this.h.c.get(i3)).b, "1")) {
                i2++;
            }
        }
        if (i2 <= 0 || this.h.c.size() <= i2) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: protected */
    public void showLoadingDialog() {
        if (isValid()) {
            this.n = new XQProgressDialog(this);
            this.n.setMessage(getString(R.string.device_more_security_loading_operation));
            this.n.show();
        }
    }

    /* access modifiers changed from: protected */
    public void hideLoadingDialog() {
        if (this.n != null) {
            this.n.dismiss();
        }
    }

    class LightGroupData {

        /* renamed from: a  reason: collision with root package name */
        Device f19135a;
        String b;

        LightGroupData(Device device, String str) {
            this.f19135a = device;
            this.b = str;
        }
    }

    public class LightGroupAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private LayoutInflater b;
        /* access modifiers changed from: private */
        public List<LightGroupData> c = new ArrayList();

        LightGroupAdapter(Context context) {
            this.b = LayoutInflater.from(context);
        }

        /* renamed from: a */
        public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            return new MyViewHolder(this.b.inflate(R.layout.item_light_group_init, viewGroup, false));
        }

        /* renamed from: a */
        public void onBindViewHolder(MyViewHolder myViewHolder, int i) {
            LightGroupData lightGroupData = this.c.get(i);
            if (lightGroupData.f19135a != null && myViewHolder != null) {
                if (TextUtils.equals(lightGroupData.b, "1")) {
                    myViewHolder.e.setBackgroundResource(R.drawable.icon_light_group_checked);
                    myViewHolder.e.setVisibility(0);
                    myViewHolder.f.setVisibility(8);
                    myViewHolder.f.clearAnimation();
                    myViewHolder.e.setOnClickListener((View.OnClickListener) null);
                } else if (TextUtils.equals(lightGroupData.b, "3")) {
                    myViewHolder.e.setBackgroundResource(R.drawable.icon_light_group_failed);
                    myViewHolder.e.setVisibility(0);
                    myViewHolder.f.setVisibility(8);
                    myViewHolder.f.clearAnimation();
                    myViewHolder.e.setOnClickListener(new View.OnClickListener(lightGroupData, i) {
                        private final /* synthetic */ LightGroupInitActivity.LightGroupData f$1;
                        private final /* synthetic */ int f$2;

                        {
                            this.f$1 = r2;
                            this.f$2 = r3;
                        }

                        public final void onClick(View view) {
                            LightGroupInitActivity.LightGroupAdapter.this.a(this.f$1, this.f$2, view);
                        }
                    });
                } else {
                    myViewHolder.e.setVisibility(8);
                    myViewHolder.f.setVisibility(0);
                    myViewHolder.e.setOnClickListener((View.OnClickListener) null);
                }
                myViewHolder.b.setText(lightGroupData.f19135a.name);
                myViewHolder.c.setText(HomeManager.a().r(lightGroupData.f19135a.did) + "  " + lightGroupData.f19135a.getStatusDescription(LightGroupInitActivity.this));
                DeviceFactory.b(lightGroupData.f19135a.model, myViewHolder.d);
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void a(LightGroupData lightGroupData, int i, View view) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(lightGroupData.f19135a.did);
            lightGroupData.b = "0";
            notifyItemChanged(i);
            LightGroupInitActivity.this.retry(arrayList);
        }

        public int getItemCount() {
            return this.c.size();
        }

        public void a(ArrayList<LightGroupData> arrayList) {
            this.c = arrayList;
        }

        /* renamed from: a */
        public void onViewAttachedToWindow(MyViewHolder myViewHolder) {
            super.onViewAttachedToWindow(myViewHolder);
            if (myViewHolder.f.getVisibility() == 0) {
                myViewHolder.f.startAnimation(AnimationUtils.loadAnimation(LightGroupInitActivity.this.getContext(), R.anim.rotate_infinite));
            }
        }

        /* renamed from: b */
        public void onViewDetachedFromWindow(MyViewHolder myViewHolder) {
            super.onViewDetachedFromWindow(myViewHolder);
            myViewHolder.f.clearAnimation();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a  reason: collision with root package name */
        View f19136a;
        TextView b;
        TextView c;
        SimpleDraweeView d;
        ImageView e;
        ImageView f;

        public MyViewHolder(View view) {
            super(view);
            this.f19136a = view;
            this.b = (TextView) view.findViewById(R.id.title);
            this.c = (TextView) view.findViewById(R.id.desc);
            this.d = (SimpleDraweeView) view.findViewById(R.id.icon);
            this.e = (ImageView) view.findViewById(R.id.iv_status);
            this.f = (ImageView) view.findViewById(R.id.iv_loading);
        }
    }

    class DiffCallBack extends DiffUtil.Callback {
        private List<LightGroupData> b;
        private List<LightGroupData> c;

        DiffCallBack(List<LightGroupData> list, List<LightGroupData> list2) {
            this.b = list;
            this.c = list2;
        }

        public int getOldListSize() {
            if (this.b != null) {
                return this.b.size();
            }
            return 0;
        }

        public int getNewListSize() {
            if (this.c != null) {
                return this.c.size();
            }
            return 0;
        }

        public boolean areItemsTheSame(int i, int i2) {
            LightGroupData lightGroupData = this.b.get(i);
            LightGroupData lightGroupData2 = this.c.get(i2);
            if (lightGroupData == null || lightGroupData2 == null) {
                return false;
            }
            return TextUtils.equals(lightGroupData.f19135a.did, lightGroupData2.f19135a.did);
        }

        public boolean areContentsTheSame(int i, int i2) {
            LightGroupData lightGroupData = this.b.get(i);
            LightGroupData lightGroupData2 = this.c.get(i2);
            if (TextUtils.equals(lightGroupData.b, lightGroupData2.b) && TextUtils.equals(lightGroupData.f19135a.getStatusDescription(LightGroupInitActivity.this), lightGroupData2.f19135a.getStatusDescription(LightGroupInitActivity.this))) {
                return true;
            }
            return false;
        }
    }
}
