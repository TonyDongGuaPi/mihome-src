package com.xiaomi.smarthome.operation.smartrecommend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.operation.BaseOperationProvider;
import com.xiaomi.smarthome.operation.Operation;
import com.xiaomi.smarthome.operation.OperationRoute;
import com.xiaomi.smarthome.stat.STAT;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class SmartRecommendOperation extends BaseOperationProvider {
    private static final String d = "action_on_smart_recommend_operation_visible_to_user";
    private WeakReference<ListView> b;
    private BroadcastReceiver c;

    /* access modifiers changed from: protected */
    public String b() {
        return "3";
    }

    public void a(ListView listView) {
        this.b = new WeakReference<>(listView);
        n();
        a(0, TimeUnit.SECONDS);
    }

    /* JADX WARNING: type inference failed for: r0v1, types: [android.view.View] */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(java.util.List<com.xiaomi.smarthome.operation.Operation> r6) {
        /*
            r5 = this;
            r0 = 0
            java.lang.Object r6 = r6.get(r0)
            com.xiaomi.smarthome.operation.Operation r6 = (com.xiaomi.smarthome.operation.Operation) r6
            java.lang.ref.WeakReference<android.widget.ListView> r1 = r5.b
            java.lang.Object r1 = r1.get()
            android.widget.ListView r1 = (android.widget.ListView) r1
            if (r1 == 0) goto L_0x0041
            r2 = 2131432154(0x7f0b12da, float:1.8486057E38)
            android.view.View r2 = r1.findViewById(r2)
            com.xiaomi.smarthome.operation.smartrecommend.PopHelpLayout r2 = (com.xiaomi.smarthome.operation.smartrecommend.PopHelpLayout) r2
            if (r2 == 0) goto L_0x0020
            a(r6, r2)
            goto L_0x0039
        L_0x0020:
            android.content.Context r2 = r1.getContext()
            android.view.LayoutInflater r2 = android.view.LayoutInflater.from(r2)
            r3 = 2130904903(0x7f030747, float:1.7416665E38)
            r4 = 0
            android.view.View r0 = r2.inflate(r3, r4, r0)
            r2 = r0
            com.xiaomi.smarthome.operation.smartrecommend.PopHelpLayout r2 = (com.xiaomi.smarthome.operation.smartrecommend.PopHelpLayout) r2
            a(r6, r2)
            r1.addHeaderView(r2)
        L_0x0039:
            java.lang.String r6 = r6.d
            r2.attachAdUrl(r6)
            r2.logAdPopUp()
        L_0x0041:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.operation.smartrecommend.SmartRecommendOperation.a(java.util.List):void");
    }

    private static void a(Operation operation, View view) {
        ((ImageView) view.findViewById(R.id.banner_img)).setImageBitmap(operation.i);
        view.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                SmartRecommendOperation.b(Operation.this, view);
            }
        });
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(Operation operation, View view) {
        OperationRoute.a(operation);
        STAT.d.al(operation.d);
    }

    /* access modifiers changed from: protected */
    public void i() {
        super.i();
        if (this.c != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.c);
        }
    }

    /* access modifiers changed from: protected */
    public boolean j() {
        return k() && super.j();
    }

    /* access modifiers changed from: protected */
    public void a() {
        View findViewById;
        ListView listView = (ListView) this.b.get();
        if (!(listView == null || (findViewById = listView.findViewById(R.id.scene_recommend_ad)) == null)) {
            listView.removeHeaderView(findViewById);
        }
        i();
    }

    private void n() {
        if (this.c == null) {
            this.c = new BroadcastReceiver() {
                public void onReceive(Context context, Intent intent) {
                    SmartRecommendOperation.this.o();
                }
            };
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.c, new IntentFilter(d));
        }
    }

    public static void m() {
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(d));
    }

    /* access modifiers changed from: private */
    public void o() {
        PopHelpLayout popHelpLayout;
        ListView listView = (ListView) this.b.get();
        if (listView != null && (popHelpLayout = (PopHelpLayout) listView.findViewById(R.id.scene_recommend_ad)) != null && popHelpLayout.isShown()) {
            popHelpLayout.logAdPopUp();
        }
    }
}
