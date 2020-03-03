package com.xiaomi.smarthome.newui.card.profile;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginError;
import com.xiaomi.smarthome.core.entity.statistic.StatType;
import com.xiaomi.smarthome.device.IRRemoteInfo;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.download.Constants;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.plugin.host.PluginHostApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.AsyncTaskUtils;
import com.xiaomi.smarthome.library.common.util.TextViewUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.miui10.MIUI10CardActivity;
import com.xiaomi.smarthome.newui.card.CardItem;
import com.xiaomi.smarthome.newui.card.other.OtherCard;
import com.xiaomi.smarthome.newui.card.other.OtherCardType;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class IRCardItem2 extends CardItem<OtherCard, OtherCardType, Object> {
    /* access modifiers changed from: private */
    public List<View> I = new ArrayList();
    private List<ImageView> J = new ArrayList();
    private List<TextView> K = new ArrayList();
    /* access modifiers changed from: private */
    public List<IRRemoteInfo> L;
    /* access modifiers changed from: private */
    public long M = 0;
    /* access modifiers changed from: private */
    public View N;
    /* access modifiers changed from: private */
    public Object O = new Object();

    /* renamed from: a  reason: collision with root package name */
    private View f20534a;

    public void a(String str, Object obj) {
    }

    public IRCardItem2(OtherCardType otherCardType) {
        super(otherCardType, (T[]) null);
    }

    public void a(ViewGroup viewGroup, int i, int i2) {
        super.a(viewGroup, i, i2);
        this.f20534a = a(viewGroup, R.layout.card_item_ir_3);
        this.N = this.f20534a.findViewById(R.id.ll_no_controller);
        a();
    }

    public void i() {
        synchronized (this.O) {
            super.i();
            a(true);
            this.E = null;
            this.I.clear();
            this.K.clear();
            this.J.clear();
            this.f20534a = null;
            this.N = null;
        }
    }

    private void a() {
        AsyncTaskUtils.a(new AsyncTask<Object, Object, List<IRRemoteInfo>>() {
            /* access modifiers changed from: protected */
            /* renamed from: a */
            public List<IRRemoteInfo> doInBackground(Object... objArr) {
                try {
                    return IRDeviceUtil.b(CommonApplication.getAppContext());
                } catch (Throwable th) {
                    th.printStackTrace();
                    return null;
                }
            }

            /* access modifiers changed from: protected */
            /* JADX WARNING: Code restructure failed: missing block: B:37:0x00d5, code lost:
                return;
             */
            /* JADX WARNING: Removed duplicated region for block: B:16:0x005e A[DONT_GENERATE] */
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0060  */
            /* renamed from: a */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onPostExecute(java.util.List<com.xiaomi.smarthome.device.IRRemoteInfo> r7) {
                /*
                    r6 = this;
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r0 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this
                    java.lang.Object r0 = r0.O
                    monitor-enter(r0)
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r1 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    boolean r1 = r1.j()     // Catch:{ all -> 0x00d6 }
                    if (r1 == 0) goto L_0x0011
                    monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
                    return
                L_0x0011:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r1 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List unused = r1.L = r7     // Catch:{ all -> 0x00d6 }
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.L     // Catch:{ all -> 0x00d6 }
                    r1 = 0
                    if (r7 == 0) goto L_0x0038
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.L     // Catch:{ all -> 0x00d6 }
                    int r7 = r7.size()     // Catch:{ all -> 0x00d6 }
                    if (r7 > 0) goto L_0x002c
                    goto L_0x0038
                L_0x002c:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    android.view.View r7 = r7.N     // Catch:{ all -> 0x00d6 }
                    r2 = 8
                    r7.setVisibility(r2)     // Catch:{ all -> 0x00d6 }
                    goto L_0x0041
                L_0x0038:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    android.view.View r7 = r7.N     // Catch:{ all -> 0x00d6 }
                    r7.setVisibility(r1)     // Catch:{ all -> 0x00d6 }
                L_0x0041:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r2 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r2 = r2.L     // Catch:{ all -> 0x00d6 }
                    r7.a((java.util.List<com.xiaomi.smarthome.device.IRRemoteInfo>) r2)     // Catch:{ all -> 0x00d6 }
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    r7.b()     // Catch:{ all -> 0x00d6 }
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.I     // Catch:{ all -> 0x00d6 }
                    int r7 = r7.size()     // Catch:{ all -> 0x00d6 }
                    r2 = 6
                    if (r7 == r2) goto L_0x0060
                    monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
                    return
                L_0x0060:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.L     // Catch:{ all -> 0x00d6 }
                    r3 = 4
                    if (r7 == 0) goto L_0x00b6
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.L     // Catch:{ all -> 0x00d6 }
                    int r7 = r7.size()     // Catch:{ all -> 0x00d6 }
                    if (r7 > 0) goto L_0x0076
                    goto L_0x00b6
                L_0x0076:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.L     // Catch:{ all -> 0x00d6 }
                    int r7 = r7.size()     // Catch:{ all -> 0x00d6 }
                    com.xiaomi.smarthome.frame.core.CoreApi r4 = com.xiaomi.smarthome.frame.core.CoreApi.a()     // Catch:{ all -> 0x00d6 }
                    boolean r4 = r4.q()     // Catch:{ all -> 0x00d6 }
                    if (r4 != 0) goto L_0x008b
                    r7 = 0
                L_0x008b:
                    if (r7 <= r2) goto L_0x008e
                    r7 = 6
                L_0x008e:
                    if (r1 >= r2) goto L_0x00d4
                    if (r1 >= r7) goto L_0x00a4
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r4 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r5 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r5 = r5.L     // Catch:{ all -> 0x00d6 }
                    java.lang.Object r5 = r5.get(r1)     // Catch:{ all -> 0x00d6 }
                    com.xiaomi.smarthome.device.IRRemoteInfo r5 = (com.xiaomi.smarthome.device.IRRemoteInfo) r5     // Catch:{ all -> 0x00d6 }
                    r4.a((int) r1, (com.xiaomi.smarthome.device.IRRemoteInfo) r5)     // Catch:{ all -> 0x00d6 }
                    goto L_0x00b3
                L_0x00a4:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r4 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r4 = r4.I     // Catch:{ all -> 0x00d6 }
                    java.lang.Object r4 = r4.get(r1)     // Catch:{ all -> 0x00d6 }
                    android.view.View r4 = (android.view.View) r4     // Catch:{ all -> 0x00d6 }
                    r4.setVisibility(r3)     // Catch:{ all -> 0x00d6 }
                L_0x00b3:
                    int r1 = r1 + 1
                    goto L_0x008e
                L_0x00b6:
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.I     // Catch:{ all -> 0x00d6 }
                    int r7 = r7.size()     // Catch:{ all -> 0x00d6 }
                    if (r1 >= r7) goto L_0x00d4
                    com.xiaomi.smarthome.newui.card.profile.IRCardItem2 r7 = com.xiaomi.smarthome.newui.card.profile.IRCardItem2.this     // Catch:{ all -> 0x00d6 }
                    java.util.List r7 = r7.I     // Catch:{ all -> 0x00d6 }
                    java.lang.Object r7 = r7.get(r1)     // Catch:{ all -> 0x00d6 }
                    android.view.View r7 = (android.view.View) r7     // Catch:{ all -> 0x00d6 }
                    r7.setVisibility(r3)     // Catch:{ all -> 0x00d6 }
                    int r1 = r1 + 1
                    goto L_0x00b6
                L_0x00d4:
                    monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
                    return
                L_0x00d6:
                    r7 = move-exception
                    monitor-exit(r0)     // Catch:{ all -> 0x00d6 }
                    throw r7
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.card.profile.IRCardItem2.AnonymousClass1.onPostExecute(java.util.List):void");
            }
        }, new Object[0]);
    }

    /* access modifiers changed from: private */
    public void b() {
        this.I.clear();
        this.J.clear();
        this.K.clear();
        View findViewById = this.f20534a.findViewById(R.id.item_1);
        if (findViewById != null) {
            this.I.add(findViewById);
            this.J.add((ImageView) findViewById.findViewById(R.id.image_1));
            this.K.add((TextView) findViewById.findViewById(R.id.text_1));
        }
        View findViewById2 = this.f20534a.findViewById(R.id.item_2);
        if (findViewById2 != null) {
            this.I.add(findViewById2);
            this.J.add((ImageView) findViewById2.findViewById(R.id.image_2));
            this.K.add((TextView) findViewById2.findViewById(R.id.text_2));
        }
        View findViewById3 = this.f20534a.findViewById(R.id.item_3);
        if (findViewById3 != null) {
            this.I.add(findViewById3);
            this.J.add((ImageView) findViewById3.findViewById(R.id.image_3));
            this.K.add((TextView) findViewById3.findViewById(R.id.text_3));
        }
        View findViewById4 = this.f20534a.findViewById(R.id.item_4);
        if (findViewById4 != null) {
            this.I.add(findViewById4);
            this.J.add((ImageView) findViewById4.findViewById(R.id.image_4));
            this.K.add((TextView) findViewById4.findViewById(R.id.text_4));
        }
        View findViewById5 = this.f20534a.findViewById(R.id.item_5);
        if (findViewById5 != null) {
            this.I.add(findViewById5);
            this.J.add((ImageView) findViewById5.findViewById(R.id.image_5));
            this.K.add((TextView) findViewById5.findViewById(R.id.text_5));
        }
        View findViewById6 = this.f20534a.findViewById(R.id.item_6);
        if (findViewById6 != null) {
            this.I.add(findViewById6);
            this.J.add((ImageView) findViewById6.findViewById(R.id.image_6));
            this.K.add((TextView) findViewById6.findViewById(R.id.text_6));
        }
    }

    public void c(View view) {
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!IRCardItem2.this.j()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - IRCardItem2.this.M >= Constants.x) {
                        long unused = IRCardItem2.this.M = currentTimeMillis;
                        if (!CoreApi.a().q()) {
                            ((PluginHostApi) PluginHostApi.instance()).login(view.getContext(), 1);
                            return;
                        }
                        String a2 = IRDeviceUtil.a();
                        if (!TextUtils.isEmpty(a2)) {
                            if (!TextUtils.isEmpty(MIUI10CardActivity.sRoomName)) {
                                STAT.d.c(a2, MIUI10CardActivity.sRoomName, false);
                            }
                            if (!CoreApi.a().c(a2)) {
                                CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                                    public void a(PluginError pluginError) {
                                    }

                                    public void a(boolean z, boolean z2) {
                                        if (!IRCardItem2.this.j() && z) {
                                            SmartHomeDeviceManager.a().r();
                                        }
                                    }
                                });
                                Toast.makeText(CommonApplication.getAppContext(), R.string.toast_failed_retry, 0).show();
                                return;
                            }
                            Intent intent = new Intent();
                            intent.putExtra("add_device", true);
                            IRDeviceUtil.f();
                            IRDeviceUtil.a(CommonApplication.getAppContext(), intent);
                        }
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(int i, final IRRemoteInfo iRRemoteInfo) {
        ImageView imageView = this.J.get(i);
        TextView textView = this.K.get(i);
        final View view = this.I.get(i);
        if (!CoreApi.a().q()) {
            textView.setText(R.string.buildin_ir_name);
            imageView.setImageDrawable(IRDeviceUtil.b(false));
            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (!IRCardItem2.this.j()) {
                        view.getContext().startActivity(new Intent(view.getContext(), GlobalSetting.B));
                    }
                }
            });
            return;
        }
        TextViewUtils.a(textView, iRRemoteInfo.b, 6, 5, textView.getResources().getText(R.string.device_card_name_replace));
        imageView.setImageDrawable(IRDeviceUtil.c(iRRemoteInfo.c, false));
        view.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!IRCardItem2.this.j()) {
                    long currentTimeMillis = System.currentTimeMillis();
                    if (currentTimeMillis - IRCardItem2.this.M >= Constants.x) {
                        long unused = IRCardItem2.this.M = currentTimeMillis;
                        if (!CoreApi.a().q()) {
                            ((PluginHostApi) PluginHostApi.instance()).login(view.getContext(), 1);
                            return;
                        }
                        String a2 = IRDeviceUtil.a();
                        if (!TextUtils.isEmpty(a2)) {
                            if (!TextUtils.isEmpty(MIUI10CardActivity.sRoomName)) {
                                STAT.d.c(a2, MIUI10CardActivity.sRoomName, false);
                            }
                            if (!CoreApi.a().c(a2)) {
                                CoreApi.a().a(true, (CoreApi.UpdateConfigCallback) new CoreApi.UpdateConfigCallback() {
                                    public void a(PluginError pluginError) {
                                    }

                                    public void a(boolean z, boolean z2) {
                                        if (!IRCardItem2.this.j() && z) {
                                            SmartHomeDeviceManager.a().r();
                                        }
                                    }
                                });
                                Toast.makeText(CommonApplication.getAppContext(), R.string.toast_failed_retry, 0).show();
                            } else if ("default".equals(iRRemoteInfo.d)) {
                                IRDeviceUtil.f();
                                IRDeviceUtil.a(CommonApplication.getAppContext(), (Intent) null);
                            } else {
                                Intent intent = new Intent();
                                intent.putExtra("remote_id", iRRemoteInfo.f14856a);
                                IRDeviceUtil.f();
                                IRDeviceUtil.a(CommonApplication.getAppContext(), intent);
                            }
                        }
                    }
                }
            }
        });
    }

    public void a(List<IRRemoteInfo> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("IrRemoteList");
        sb.append(IRDeviceUtil.e() ? "TianJia" : "DuoKan");
        String sb2 = sb.toString();
        JSONArray jSONArray = new JSONArray();
        if (list != null) {
            for (IRRemoteInfo iRRemoteInfo : list) {
                jSONArray.put(iRRemoteInfo.c);
            }
        }
        CoreApi.a().a(StatType.EVENT, sb2, jSONArray.toString(), (String) null, false);
    }
}
