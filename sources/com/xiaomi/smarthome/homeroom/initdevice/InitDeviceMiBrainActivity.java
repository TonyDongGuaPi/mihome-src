package com.xiaomi.smarthome.homeroom.initdevice;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.drawee.view.SimpleDraweeView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.constants.AppConstants;
import com.xiaomi.smarthome.core.entity.plugin.PluginDownloadTask;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.PluginApi;
import com.xiaomi.smarthome.frame.plugin.RunningProcess;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity;
import com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressHorizontalDialog;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.light.group.LightGroupInitActivity;
import com.xiaomi.smarthome.mibrain.MiBrainManager;
import com.xiaomi.smarthome.mibrain.activity.MiBrainActivityCardStyle;
import com.xiaomi.smarthome.mibrain.activity.MiBrainCardStyleStatelessActivity;
import com.xiaomi.smarthome.mibrain.model.aitips.AiDevice;
import com.xiaomi.smarthome.multi_item.DelegateAdapter;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.multikey.PowerMultikeyApi;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;

public class InitDeviceMiBrainActivity extends BaseActivity {
    public static final String INTENT_KEY_DID = "device_id";

    /* renamed from: a  reason: collision with root package name */
    private static final int f18256a = 100;
    /* access modifiers changed from: private */
    public Device b;
    /* access modifiers changed from: private */
    public volatile boolean c = false;
    private boolean d;
    private XQProgressDialog e;
    @BindView(2131428794)
    SimpleDraweeView mDeviceImg;
    @BindView(2131428822)
    TextView mDeviceNameTv;
    @BindView(2131428630)
    RecyclerView mRecyclerView;
    @BindView(2131430969)
    View mReturnBtn;
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
        setContentView(R.layout.activity_init_mibrain_layout);
        ButterKnife.bind((Activity) this);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("device_id");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.b = SmartHomeDeviceManager.a().b(stringExtra);
        }
        if (this.b == null) {
            finish();
        } else if (!SmartHomeDeviceManager.a().u() || this.b != null) {
            this.d = intent.getBooleanExtra(AppConstants.O, false);
            a();
            d();
            STAT.c.a(this.b.model, a(this.b.model));
        } else {
            finish();
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0009, code lost:
        r0 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a().b();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String a(java.lang.String r3) {
        /*
            r2 = this;
            boolean r0 = android.text.TextUtils.isEmpty(r3)
            if (r0 == 0) goto L_0x0009
            java.lang.String r3 = ""
            return r3
        L_0x0009:
            com.xiaomi.smarthome.device.SmartHomeDeviceHelper r0 = com.xiaomi.smarthome.device.SmartHomeDeviceHelper.a()
            com.xiaomi.smarthome.device.utils.DeviceTagInterface r0 = r0.b()
            if (r0 == 0) goto L_0x0020
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r1 = r0.c((java.lang.String) r3)
            if (r1 == 0) goto L_0x0020
            com.xiaomi.smarthome.device.utils.DeviceTagInterface$Category r3 = r0.c((java.lang.String) r3)
            java.lang.String r3 = r3.f15435a
            return r3
        L_0x0020:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.a(java.lang.String):java.lang.String");
    }

    private void a() {
        new PowerMultikeyApi();
        this.mDeviceNameTv.setText(this.b.getName());
        this.mTitleBar.setBackgroundColor(getResources().getColor(R.color.std_main_bg));
        this.mReturnBtn.setVisibility(8);
        this.mTitleTv.setText(R.string.kuailian_success);
        c();
        this.mSkipTv.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                InitDeviceMiBrainActivity.this.a(view);
            }
        });
        b();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        if (this.d) {
            h();
        } else {
            i();
        }
    }

    private void b() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        gridLayoutManager.setOrientation(0);
        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        List<AiDevice> d2 = MiBrainManager.a().d(this.b.did);
        if (d2 == null) {
            d2 = new ArrayList<>();
        }
        ArrayList arrayList = new ArrayList();
        String l = HomeManager.a().l();
        for (AiDevice aiDevice : d2) {
            Home q = HomeManager.a().q(aiDevice.a());
            if (q != null && TextUtils.equals(l, q.j())) {
                arrayList.add(aiDevice);
            }
        }
        MyDelegateAdapter myDelegateAdapter = new MyDelegateAdapter();
        myDelegateAdapter.a((IAdapter) new DeviceXiaoAiAdapter(arrayList, MiBrainManager.a().c(this.b.did)));
        myDelegateAdapter.a((IAdapter) new PhoneXiaoAiAdapter());
        if (arrayList.size() == 0) {
            myDelegateAdapter.a((IAdapter) new YouPinXiaoAiGoodsAdapter(XiaoAiGoodRecommendApi.a().d()));
        }
        this.mRecyclerView.setAdapter(myDelegateAdapter);
        new PagerSnapHelper().attachToRecyclerView(this.mRecyclerView);
    }

    private void c() {
        DeviceFactory.b(this.b.model, this.mDeviceImg);
    }

    private void d() {
        MiBrainManager.a().a(this.b.did, true, new AsyncCallback() {
            public void onSuccess(Object obj) {
                boolean unused = InitDeviceMiBrainActivity.this.c = true;
            }

            public void onFailure(Error error) {
                boolean unused = InitDeviceMiBrainActivity.this.c = false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.c) {
            Intent intent = new Intent(this, MiBrainActivityCardStyle.class);
            intent.putExtra("device_id", this.b.did);
            intent.addFlags(536870912);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_fade_in_center, 0);
            return;
        }
        f();
        MiBrainManager.a().a(this.b.did, true, new AsyncCallback() {
            public void onSuccess(Object obj) {
                InitDeviceMiBrainActivity.this.g();
                Intent intent = new Intent(InitDeviceMiBrainActivity.this, MiBrainActivityCardStyle.class);
                intent.putExtra("device_id", InitDeviceMiBrainActivity.this.b.did);
                intent.addFlags(536870912);
                InitDeviceMiBrainActivity.this.startActivity(intent);
                InitDeviceMiBrainActivity.this.overridePendingTransition(R.anim.activity_fade_in_center, 0);
            }

            public void onFailure(Error error) {
                InitDeviceMiBrainActivity.this.g();
                Intent intent = new Intent(InitDeviceMiBrainActivity.this, MiBrainActivityCardStyle.class);
                intent.putExtra("device_id", InitDeviceMiBrainActivity.this.b.did);
                intent.addFlags(536870912);
                InitDeviceMiBrainActivity.this.startActivity(intent);
                InitDeviceMiBrainActivity.this.overridePendingTransition(R.anim.activity_fade_in_center, 0);
            }
        });
    }

    private void f() {
        if (this.e == null || !this.e.isShowing()) {
            this.e = new XQProgressDialog(this);
            this.e.setCancelable(true);
            this.e.setMessage(getResources().getString(R.string.loading_share_info));
            this.e.show();
        }
    }

    /* access modifiers changed from: private */
    public void g() {
        if (this.e != null) {
            this.e.dismiss();
        }
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1) {
            if (i == 100) {
                i();
            }
            finish();
        }
    }

    private void h() {
        LightGroupInitActivity.open(this, this.b.did, 100);
    }

    private void i() {
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
            sendBroadcast(new Intent(InitDeviceShareActivity.BIND_FINISH_OPENPLUGIN));
            final PluginRecord pluginRecord = d2;
            PluginApi.getInstance().sendMessage(this, d2, 1, new Intent(), this.b.newDeviceStat(), (RunningProcess) null, false, new PluginApi.SendMessageCallback() {
                private static final float j = 85.0f;

                /* renamed from: a  reason: collision with root package name */
                ValueAnimator f18259a;
                private long h;
                private final Interpolator i = new DecelerateInterpolator();

                public void onDownloadStart(PluginRecord pluginRecord, PluginDownloadTask pluginDownloadTask) {
                    if (InitDeviceMiBrainActivity.this.isValid()) {
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
                    if (this.f18259a == null) {
                        synchronized (this) {
                            double min = (double) Math.min(1.0f, ((float) (System.currentTimeMillis() - this.h)) / 4000.0f);
                            Double.isNaN(min);
                            this.f18259a = ValueAnimator.ofFloat(new float[]{(float) ((min * 0.14d) + 0.85d), 0.99f});
                            this.f18259a.setDuration(4000);
                            this.f18259a.setInterpolator(this.i);
                            this.f18259a.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    InitDeviceMiBrainActivity.AnonymousClass3.a(XQProgressHorizontalDialog.this, valueAnimator);
                                }
                            });
                            this.f18259a.start();
                        }
                    }
                    return ((Float) this.f18259a.getAnimatedValue()).floatValue();
                }

                /* access modifiers changed from: private */
                public static /* synthetic */ void a(XQProgressHorizontalDialog xQProgressHorizontalDialog, ValueAnimator valueAnimator) {
                    if (xQProgressHorizontalDialog != null) {
                        xQProgressHorizontalDialog.a(100, (int) (((Float) valueAnimator.getAnimatedValue()).floatValue() * 100.0f));
                    }
                }

                public void onDownloadProgress(PluginRecord pluginRecord, float f2) {
                    if (InitDeviceMiBrainActivity.this.isValid()) {
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
                    if (InitDeviceMiBrainActivity.this.isValid() && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onDownloadFailure(PluginError pluginError) {
                    if (InitDeviceMiBrainActivity.this.isValid()) {
                        if (b2 != null) {
                            b2.dismiss();
                        }
                        Toast.makeText(InitDeviceMiBrainActivity.this, R.string.device_enter_failed, 0).show();
                    }
                }

                public void onDownloadCancel() {
                    if (InitDeviceMiBrainActivity.this.isValid() && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onInstallSuccess(PluginRecord pluginRecord) {
                    super.onInstallSuccess(pluginRecord);
                    if (InitDeviceMiBrainActivity.this.isValid() && b2 != null) {
                        b2.dismiss();
                    }
                }

                public void onInstallFailure(PluginError pluginError) {
                    super.onInstallFailure(pluginError);
                    if (InitDeviceMiBrainActivity.this.isValid()) {
                        if (b2 != null) {
                            b2.dismiss();
                        }
                        Toast.makeText(InitDeviceMiBrainActivity.this, R.string.device_enter_failed, 0).show();
                    }
                }

                public void onSendSuccess(Bundle bundle) {
                    if (InitDeviceMiBrainActivity.this.isValid()) {
                        SHApplication.getGlobalHandler().postDelayed(new Runnable() {
                            public void run() {
                                xQProgressDialog.dismiss();
                                if (b2 != null) {
                                    b2.dismiss();
                                }
                                InitDeviceMiBrainActivity.this.finish();
                            }
                        }, 100);
                    }
                }

                public void onSendFailure(Error error) {
                    if (InitDeviceMiBrainActivity.this.isValid()) {
                        if (z && b2 != null) {
                            b2.dismiss();
                        }
                        Toast.makeText(InitDeviceMiBrainActivity.this, R.string.device_enter_failed, 0).show();
                    }
                }

                public void onSendCancel() {
                    if (InitDeviceMiBrainActivity.this.isValid() && z && b2 != null) {
                        b2.dismiss();
                    }
                }
            });
        }
    }

    class MyDelegateAdapter extends DelegateAdapter {
        MyDelegateAdapter() {
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            super.onBindViewHolder(viewHolder, i);
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) viewHolder.itemView.getLayoutParams();
            if (marginLayoutParams != null) {
                int b = (DisplayUtils.b((Context) InitDeviceMiBrainActivity.this) - marginLayoutParams.width) / 2;
                int a2 = DisplayUtils.a((Context) InitDeviceMiBrainActivity.this, 10.0f);
                if (getItemCount() == 1) {
                    marginLayoutParams.rightMargin = b;
                    marginLayoutParams.leftMargin = b;
                    viewHolder.itemView.setLayoutParams(marginLayoutParams);
                    return;
                }
                if (i == 0) {
                    int i2 = a2;
                    a2 = b;
                    b = i2;
                } else if (i != getItemCount() - 1) {
                    b = a2;
                }
                marginLayoutParams.leftMargin = a2;
                marginLayoutParams.rightMargin = b;
                viewHolder.itemView.setLayoutParams(marginLayoutParams);
            }
        }
    }

    class DeviceXiaoAiAdapter extends IAdapter {
        private final List<Pair<Device, AiDevice>> b = new ArrayList();
        /* access modifiers changed from: private */
        public String c;

        /* access modifiers changed from: protected */
        public int a() {
            return 1;
        }

        public DeviceXiaoAiAdapter(List<AiDevice> list, String str) {
            this.c = TextUtils.isEmpty(str) ? "" : str;
            ArrayList arrayList = new ArrayList();
            for (AiDevice next : list) {
                Device b2 = SmartHomeDeviceManager.a().b(next.a());
                if (b2 != null) {
                    arrayList.add(Pair.create(b2, next));
                }
            }
            this.b.clear();
            this.b.addAll(arrayList);
            notifyDataSetChanged();
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new DeviceXiaoAiVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kuailian_card_xiaoai_device_layout, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((DeviceXiaoAiVH) viewHolder).a(this.b.get(i));
        }

        public int getItemCount() {
            return this.b.size();
        }

        private class DeviceXiaoAiVH extends RecyclerView.ViewHolder {
            /* access modifiers changed from: private */
            public SimpleDraweeView b;
            private TextView c;
            private TextView d;
            private TextView e;

            public DeviceXiaoAiVH(View view) {
                super(view);
                this.b = (SimpleDraweeView) view.findViewById(R.id.device_icon);
                this.c = (TextView) view.findViewById(R.id.device_name);
                this.d = (TextView) view.findViewById(R.id.room_name);
                this.e = (TextView) view.findViewById(R.id.try_btn);
            }

            public void a(Pair<Device, AiDevice> pair) {
                Device device = (Device) pair.first;
                new PowerMultikeyApi().a(device, (AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>) new AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>() {
                    /* renamed from: a */
                    public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                        DeviceXiaoAiVH.this.b.setImageURI(deviceImageEntity.f21157a);
                    }

                    public void onFailure(Error error) {
                        DeviceFactory.b(InitDeviceMiBrainActivity.this.b.model, DeviceXiaoAiVH.this.b);
                    }
                });
                this.c.setText(device.getName());
                this.d.setText(HomeManager.a().r(device.did));
                this.e.setOnClickListener(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0030: INVOKE  
                      (wrap: android.widget.TextView : 0x0029: IGET  (r0v4 android.widget.TextView) = 
                      (r3v0 'this' com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH A[THIS])
                     com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.DeviceXiaoAiAdapter.DeviceXiaoAiVH.e android.widget.TextView)
                      (wrap: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po : 0x002d: CONSTRUCTOR  (r1v3 com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po) = 
                      (r3v0 'this' com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH A[THIS])
                      (r4v0 'pair' android.util.Pair<com.xiaomi.smarthome.device.Device, com.xiaomi.smarthome.mibrain.model.aitips.AiDevice>)
                     call: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po.<init>(com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH, android.util.Pair):void type: CONSTRUCTOR)
                     android.widget.TextView.setOnClickListener(android.view.View$OnClickListener):void type: VIRTUAL in method: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.DeviceXiaoAiAdapter.DeviceXiaoAiVH.a(android.util.Pair):void, dex: classes8.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x002d: CONSTRUCTOR  (r1v3 com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po) = 
                      (r3v0 'this' com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH A[THIS])
                      (r4v0 'pair' android.util.Pair<com.xiaomi.smarthome.device.Device, com.xiaomi.smarthome.mibrain.model.aitips.AiDevice>)
                     call: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po.<init>(com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH, android.util.Pair):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.DeviceXiaoAiAdapter.DeviceXiaoAiVH.a(android.util.Pair):void, dex: classes8.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 59 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	... 65 more
                    */
                /*
                    this = this;
                    java.lang.Object r0 = r4.first
                    com.xiaomi.smarthome.device.Device r0 = (com.xiaomi.smarthome.device.Device) r0
                    com.xiaomi.smarthome.multikey.PowerMultikeyApi r1 = new com.xiaomi.smarthome.multikey.PowerMultikeyApi
                    r1.<init>()
                    com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$1 r2 = new com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$1
                    r2.<init>()
                    r1.a((com.xiaomi.smarthome.device.Device) r0, (com.xiaomi.smarthome.frame.AsyncCallback<com.xiaomi.smarthome.printer.DeviceImageApi.DeviceImageEntity, com.xiaomi.smarthome.frame.Error>) r2)
                    android.widget.TextView r1 = r3.c
                    java.lang.CharSequence r2 = r0.getName()
                    r1.setText(r2)
                    android.widget.TextView r1 = r3.d
                    com.xiaomi.smarthome.homeroom.HomeManager r2 = com.xiaomi.smarthome.homeroom.HomeManager.a()
                    java.lang.String r0 = r0.did
                    java.lang.String r0 = r2.r(r0)
                    r1.setText(r0)
                    android.widget.TextView r0 = r3.e
                    com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po r1 = new com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$DeviceXiaoAiAdapter$DeviceXiaoAiVH$YsJRCgMrnWAbAnmzbXgy4Soo_Po
                    r1.<init>(r3, r4)
                    r0.setOnClickListener(r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.DeviceXiaoAiAdapter.DeviceXiaoAiVH.a(android.util.Pair):void");
            }

            /* access modifiers changed from: private */
            public /* synthetic */ void a(Pair pair, View view) {
                MiBrainCardStyleStatelessActivity.Companion.a(InitDeviceMiBrainActivity.this, (AiDevice) pair.second, DeviceXiaoAiAdapter.this.c);
                STAT.c.b(InitDeviceMiBrainActivity.this.b.model, InitDeviceMiBrainActivity.this.a(InitDeviceMiBrainActivity.this.b.model));
            }
        }
    }

    class PhoneXiaoAiAdapter extends IAdapter {
        /* access modifiers changed from: protected */
        public int a() {
            return 1;
        }

        public int getItemCount() {
            return 1;
        }

        PhoneXiaoAiAdapter() {
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new PhoneXiaoAiVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kuailian_card_xiaoai_phone_layout, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((PhoneXiaoAiVH) viewHolder).a();
        }

        private class PhoneXiaoAiVH extends RecyclerView.ViewHolder {
            private SimpleDraweeView b;
            private TextView c;
            private TextView d;
            private TextView e;

            public PhoneXiaoAiVH(View view) {
                super(view);
                this.b = (SimpleDraweeView) view.findViewById(R.id.icon);
                this.c = (TextView) view.findViewById(R.id.name);
                this.d = (TextView) view.findViewById(R.id.desc);
                this.e = (TextView) view.findViewById(R.id.try_btn);
            }

            public void a() {
                this.e.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        STAT.d.an(InitDeviceMiBrainActivity.this.b.model);
                        InitDeviceMiBrainActivity.this.e();
                    }
                });
            }
        }
    }

    class YouPinXiaoAiGoodsAdapter extends IAdapter {
        private static final String b = "YouPinXiaoAiGoodsAdapte";
        private final List<XiaoAiGoodRecommendApi.XiaoAiGood> c = new ArrayList();

        /* access modifiers changed from: protected */
        public int a() {
            return 1;
        }

        public YouPinXiaoAiGoodsAdapter(List<XiaoAiGoodRecommendApi.XiaoAiGood> list) {
            if (list != null && !list.isEmpty()) {
                this.c.addAll(list);
            }
        }

        @NonNull
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new XiaoAiGoodVH(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.kuailian_card_xiaoai_good_recommend_layout, viewGroup, false));
        }

        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
            ((XiaoAiGoodVH) viewHolder).a(this.c.get(i));
        }

        public int getItemCount() {
            return this.c.size();
        }

        private class XiaoAiGoodVH extends RecyclerView.ViewHolder {
            private SimpleDraweeView b;
            private TextView c;
            private TextView d;
            private TextView e;
            private TextView f;

            public XiaoAiGoodVH(View view) {
                super(view);
                this.b = (SimpleDraweeView) view.findViewById(R.id.icon);
                this.c = (TextView) view.findViewById(R.id.name);
                this.d = (TextView) view.findViewById(R.id.summary);
                this.e = (TextView) view.findViewById(R.id.price);
                this.f = (TextView) view.findViewById(R.id.buy);
            }

            public void a(XiaoAiGoodRecommendApi.XiaoAiGood xiaoAiGood) {
                this.b.setImageURI(xiaoAiGood.d);
                this.c.setText(xiaoAiGood.f18309a);
                this.d.setText(xiaoAiGood.b);
                TextView textView = this.e;
                textView.setText("￥" + (xiaoAiGood.c / 100));
                this.f.setOnClickListener(
                /*  JADX ERROR: Method code generation error
                    jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0037: INVOKE  
                      (wrap: android.widget.TextView : 0x0030: IGET  (r0v4 android.widget.TextView) = 
                      (r6v0 'this' com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH A[THIS])
                     com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.YouPinXiaoAiGoodsAdapter.XiaoAiGoodVH.f android.widget.TextView)
                      (wrap: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA : 0x0034: CONSTRUCTOR  (r1v5 com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA) = 
                      (r7v0 'xiaoAiGood' com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi$XiaoAiGood)
                     call: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA.<init>(com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi$XiaoAiGood):void type: CONSTRUCTOR)
                     android.widget.TextView.setOnClickListener(android.view.View$OnClickListener):void type: VIRTUAL in method: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.YouPinXiaoAiGoodsAdapter.XiaoAiGoodVH.a(com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi$XiaoAiGood):void, dex: classes8.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                    	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                    	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                    	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                    	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                    	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                    	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                    	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.addInnerClass(ClassGen.java:249)
                    	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:238)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1540)
                    	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                    	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                    	at java.base/java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:485)
                    	at java.base/java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:474)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                    	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                    	at java.base/java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                    	at java.base/java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:497)
                    	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                    	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                    	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                    	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                    	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                    	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                    	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                    	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                    	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                    Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0034: CONSTRUCTOR  (r1v5 com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA) = 
                      (r7v0 'xiaoAiGood' com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi$XiaoAiGood)
                     call: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA.<init>(com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi$XiaoAiGood):void type: CONSTRUCTOR in method: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.YouPinXiaoAiGoodsAdapter.XiaoAiGoodVH.a(com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi$XiaoAiGood):void, dex: classes8.dex
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                    	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:123)
                    	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:107)
                    	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:787)
                    	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:728)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:368)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                    	... 59 more
                    Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA, state: NOT_LOADED
                    	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:260)
                    	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:606)
                    	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                    	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:231)
                    	... 65 more
                    */
                /*
                    this = this;
                    com.facebook.drawee.view.SimpleDraweeView r0 = r6.b
                    java.lang.String r1 = r7.d
                    r0.setImageURI((java.lang.String) r1)
                    android.widget.TextView r0 = r6.c
                    java.lang.String r1 = r7.f18309a
                    r0.setText(r1)
                    android.widget.TextView r0 = r6.d
                    java.lang.String r1 = r7.b
                    r0.setText(r1)
                    android.widget.TextView r0 = r6.e
                    java.lang.StringBuilder r1 = new java.lang.StringBuilder
                    r1.<init>()
                    java.lang.String r2 = "￥"
                    r1.append(r2)
                    long r2 = r7.c
                    r4 = 100
                    long r2 = r2 / r4
                    r1.append(r2)
                    java.lang.String r1 = r1.toString()
                    r0.setText(r1)
                    android.widget.TextView r0 = r6.f
                    com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA r1 = new com.xiaomi.smarthome.homeroom.initdevice.-$$Lambda$InitDeviceMiBrainActivity$YouPinXiaoAiGoodsAdapter$XiaoAiGoodVH$oOYGuo7bBwMEuUWtKjSTOPGLaPA
                    r1.<init>(r7)
                    r0.setOnClickListener(r1)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.homeroom.initdevice.InitDeviceMiBrainActivity.YouPinXiaoAiGoodsAdapter.XiaoAiGoodVH.a(com.xiaomi.smarthome.homeroom.initdevice.XiaoAiGoodRecommendApi$XiaoAiGood):void");
            }
        }
    }
}
