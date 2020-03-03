package com.xiaomi.smarthome.homeroom;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.multi_item.DelegateAdapter;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.newui.adapter.DeviceMainGridAdapterV2;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.adapter.NoDeviceAdapter;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.stat.StatClick;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class HomeDefaultRoomDeviceTransferGuide extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private static final int f17963a = 955;
    private RecyclerView b;
    /* access modifiers changed from: private */
    public ImageView c;
    /* access modifiers changed from: private */
    public CheckedModeAdapter d;
    /* access modifiers changed from: private */
    public boolean e = false;
    /* access modifiers changed from: private */
    public View f;

    public static void start(Context context) {
        Intent intent = new Intent(context, HomeDefaultRoomDeviceTransferGuide.class);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        context.startActivity(intent);
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_home_default_room_device_transfer_guide);
        this.c = (ImageView) findViewById(R.id.module_a_3_right_iv_setting_btn);
        this.c.setVisibility(0);
        this.c.setImageResource(R.drawable.all_select_selector);
        this.c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                int i = 1;
                boolean z = !HomeDefaultRoomDeviceTransferGuide.this.e;
                int n = HomeDefaultRoomDeviceTransferGuide.this.d.n();
                for (int i2 = 0; i2 < n; i2++) {
                    HomeDefaultRoomDeviceTransferGuide.this.d.a(i2, z);
                }
                HomeDefaultRoomDeviceTransferGuide.this.d.notifyDataSetChanged();
                HomeDefaultRoomDeviceTransferGuide.this.c.setImageResource(z ? R.drawable.un_select_selector : R.drawable.all_select_selector);
                boolean unused = HomeDefaultRoomDeviceTransferGuide.this.e = z;
                StatClick statClick = STAT.d;
                if (!z) {
                    i = 0;
                }
                statClick.j(i);
            }
        });
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(new View.OnClickListener() {
            public final void onClick(View view) {
                HomeDefaultRoomDeviceTransferGuide.this.a(view);
            }
        });
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.assign_room);
        this.f = findViewById(R.id.confirm);
        this.f.setEnabled(false);
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Set<String> l = HomeDefaultRoomDeviceTransferGuide.this.d.l();
                if (!l.isEmpty()) {
                    Intent intent = new Intent(HomeDefaultRoomDeviceTransferGuide.this, HomeRoomDeviceMoveActivity.class);
                    intent.putExtra(HomeRoomDeviceMoveActivity.KEY_CHECKED_DIDS, new ArrayList(l));
                    HomeDefaultRoomDeviceTransferGuide.this.startActivityForResult(intent, HomeDefaultRoomDeviceTransferGuide.f17963a);
                    STAT.d.aJ();
                }
            }
        });
        this.b = (RecyclerView) findViewById(R.id.recycler);
        this.b.setLayoutManager(new GridLayoutManager(this, -1) {
            public boolean supportsPredictiveItemAnimations() {
                return false;
            }
        });
        DelegateAdapter delegateAdapter = new DelegateAdapter();
        this.d = new CheckedModeAdapter(this);
        delegateAdapter.a((IAdapter) this.d);
        delegateAdapter.b((IAdapter) new NoDeviceAdapter(this));
        this.b.setAdapter(delegateAdapter);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(View view) {
        finish();
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        a();
        STAT.c.f();
    }

    private void a() {
        PageBean b2 = PageBean.b();
        this.d.a(DeviceListAssemble.f17943a.a(b2), 5, b2.f, (Object) b2.e, false);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == f17963a && intent != null) {
            PageBean b2 = PageBean.b();
            List<Device> a2 = DeviceListAssemble.f17943a.a(b2);
            if (a2.isEmpty()) {
                finish();
                return;
            }
            this.d.h().clear();
            this.d.l().clear();
            this.f.setEnabled(false);
            this.e = false;
            this.c.setImageResource(R.drawable.all_select_selector);
            this.d.a(a2, 5, b2.f, (Object) b2.e, false);
            this.b.post(new Runnable() {
                public final void run() {
                    HomeDefaultRoomDeviceTransferGuide.this.b();
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b() {
        this.d.notifyDataSetChanged();
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
    }

    private class CheckedModeAdapter extends DeviceMainGridAdapterV2 {
        public boolean L_() {
            return true;
        }

        public int a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
            return 0;
        }

        public boolean b() {
            return false;
        }

        public CheckedModeAdapter(Context context) {
            super(context);
        }

        public void a(int i, boolean z) {
            super.a(i, z);
            List<Device> i2 = i();
            boolean z2 = false;
            boolean unused = HomeDefaultRoomDeviceTransferGuide.this.e = m() == (i2 == null ? 0 : i2.size());
            HomeDefaultRoomDeviceTransferGuide.this.c.setImageResource(HomeDefaultRoomDeviceTransferGuide.this.e ? R.drawable.un_select_selector : R.drawable.all_select_selector);
            View access$300 = HomeDefaultRoomDeviceTransferGuide.this.f;
            if (m() != 0) {
                z2 = true;
            }
            access$300.setEnabled(z2);
        }

        public DviceEditInterface.HostPage c() {
            return DviceEditInterface.HostPage.TRANSFER_GUIDE_PAGE;
        }
    }
}
