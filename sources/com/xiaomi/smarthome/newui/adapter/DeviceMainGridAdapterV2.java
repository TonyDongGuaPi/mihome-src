package com.xiaomi.smarthome.newui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.MiioDeviceV2;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.frame.core.ClientApiStub;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.framework.log.MyLogHelper;
import com.xiaomi.smarthome.framework.login.logic.LoginManager;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.newui.DeviceListPageActionInterface;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.adapter.main_grid.BaseDeviceGridCardViewHolder;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.card.OnStateChangedListener;
import com.xiaomi.smarthome.newui.dragsort.SimpleItemDelegateTouchHelperCallback;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class DeviceMainGridAdapterV2 extends IAdapter implements DviceEditInterface {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20379a = "DeviceMainGridAdapterV2";
    public static final int b = 0;
    public static final int c = 1;
    public static final int d = 2;
    public static final int e = 3;
    public static final int f = 4;
    public static final int g = 5;
    private ItemTouchHelper h;
    private Context i;
    private DeviceListPageActionInterface j;
    private int k = 0;
    private Object l;
    /* access modifiers changed from: private */
    public List<Device> m = new ArrayList();
    /* access modifiers changed from: private */
    public final List<Device> n = new ArrayList();
    private final Set<String> o = new LinkedHashSet();
    private boolean p = false;
    private SimpleItemDelegateTouchHelperCallback q;
    private boolean r = false;
    private boolean s = false;
    private String t;
    /* access modifiers changed from: private */
    public Map<String, Boolean> u = new ConcurrentHashMap();
    private OnStateChangedListener v = new OnStateChangedListener() {
        public final void onStateChanged(String str, String str2, Object obj) {
            DeviceMainGridAdapterV2.this.a(str, str2, obj);
        }
    };
    private boolean w = false;
    private boolean x = true;

    public int a() {
        return 2;
    }

    public DeviceMainGridAdapterV2(Context context) {
        this.i = context;
    }

    public void a(DeviceListPageActionInterface deviceListPageActionInterface) {
        this.j = deviceListPageActionInterface;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(String str, String str2, Object obj) {
        Boolean put = this.u.put(str, true);
    }

    public void f() {
        LogUtil.c(f20379a, "registerStateChangedListener: ");
        ControlCardInfoManager.f().a(this.v);
        MiotSpecCardManager.f().a(this.v);
    }

    public void g() {
        LogUtil.c(f20379a, "unregisterStateChangedListener: ");
        ControlCardInfoManager.f().b(this.v);
        MiotSpecCardManager.f().b(this.v);
    }

    @UiThread
    public void a(final List<Device> list, int i2, String str, Object obj, boolean z) {
        String str2 = f20379a;
        StringBuilder sb = new StringBuilder();
        sb.append("updateDataByDevices: editMode: ");
        sb.append(this.r);
        sb.append(" ;isSameRoom: ");
        sb.append(!this.n.isEmpty());
        LogUtilGrey.a(str2, sb.toString());
        if (this.r) {
            this.p = true;
            return;
        }
        if (!TextUtils.equals(this.t, str) || z) {
            this.m.clear();
            this.n.clear();
            this.o.clear();
            this.w = false;
        }
        this.t = str;
        this.k = i2;
        this.l = obj;
        if (this.n.isEmpty()) {
            this.m = list;
            a(list);
            notifyDataSetChanged();
            return;
        }
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                ArrayList arrayList = new ArrayList(DeviceMainGridAdapterV2.this.n);
                final List list = list;
                final DiffUtil.DiffResult calculateDiff = DiffUtil.calculateDiff(new DevicesGridDiffCallback(list, arrayList, DeviceMainGridAdapterV2.this.u));
                DeviceMainGridAdapterV2.this.a((List<Device>) list);
                SHApplication.getGlobalHandler().post(new Runnable() {
                    public void run() {
                        List unused = DeviceMainGridAdapterV2.this.m = list;
                        calculateDiff.dispatchUpdatesTo((RecyclerView.Adapter) DeviceMainGridAdapterV2.this);
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(List<Device> list) {
        this.n.clear();
        for (Device clone : list) {
            this.n.add(clone.clone());
        }
    }

    public List<Device> h() {
        return this.n;
    }

    public List<Device> i() {
        return this.m;
    }

    public void a(SimpleItemDelegateTouchHelperCallback simpleItemDelegateTouchHelperCallback, ItemTouchHelper itemTouchHelper) {
        this.q = simpleItemDelegateTouchHelperCallback;
        this.h = itemTouchHelper;
    }

    public int getItemCount() {
        w();
        v();
        if (this.m == null) {
            return 0;
        }
        return this.m.size();
    }

    private void v() {
        if (!this.w) {
            this.w = true;
            String str = f20379a;
            StringBuilder sb = new StringBuilder();
            sb.append("roomName: ");
            sb.append(u());
            sb.append(" ;deviceCount: ");
            sb.append(this.m == null ? 0 : this.m.size());
            LogUtilGrey.a(str, sb.toString());
        }
    }

    public int a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Home m2 = HomeManager.a().m();
        if (m2 == null || !m2.p()) {
            return 0;
        }
        return ItemTouchHelper.Callback.makeMovementFlags(15, 0);
    }

    private void w() {
        if (this.m.size() >= 3) {
            if ((GlobalSetting.q || GlobalSetting.u) && this.x) {
                this.x = false;
                String str = f20379a;
                LogUtilGrey.a(str, f20379a + " getItemCount in device count=" + this.m.size());
            }
            if (SHApplication.sEnableLogPerf) {
                SHApplication.sEnableLogPerf = false;
                long currentTimeMillis = System.currentTimeMillis();
                if (currentTimeMillis - SHApplication.sOnCreateTime > 4000) {
                    MyLogHelper.a(ClientApiStub.sOnCoreReadyTime - SHApplication.sOnCreateTime, LoginManager.k - SHApplication.sOnCreateTime, currentTimeMillis - SHApplication.sOnCreateTime);
                }
            }
        }
    }

    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        return new BaseDeviceGridCardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_grid_v2, viewGroup, false), this.i, this, this.q, this.h);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        ((BaseDeviceGridCardViewHolder) viewHolder).a(this.m.get(i2), i2);
    }

    private static boolean a(Device device) {
        return (device instanceof PhoneIRDevice) || (device != null && IRDeviceUtil.a(device.did));
    }

    public void j() {
        this.r = true;
        if (this.k == 2) {
            notifyDataSetChanged();
        }
        this.o.clear();
    }

    public void k() {
        this.r = false;
        this.o.clear();
        s();
        if (this.p) {
            this.p = false;
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
        }
    }

    public boolean L_() {
        return this.r;
    }

    public Set<String> l() {
        return this.o;
    }

    public int m() {
        if (this.o == null) {
            return 0;
        }
        return this.o.size();
    }

    public int n() {
        return this.m.size();
    }

    public void a(int i2, boolean z) {
        try {
            Device device = this.m.get(i2);
            if (!(device instanceof PhoneIRDevice)) {
                if (z) {
                    this.o.add(device.did);
                } else {
                    this.o.remove(device.did);
                }
                if (this.j != null) {
                    this.j.updateActionItems(this, this.o.size(), device.did, x());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void o() {
        notifyDataSetChanged();
    }

    public int p() {
        int itemCount = getItemCount();
        for (int i2 = 0; i2 < this.m.size(); i2++) {
            if (this.m.get(i2) instanceof PhoneIRDevice) {
                itemCount--;
            }
        }
        return itemCount;
    }

    public int q() {
        return this.k;
    }

    public Room r() {
        return HomeManager.a().i(this.t);
    }

    public boolean a(int i2, int i3) {
        Log.e(f20379a, "onItemMove: from: " + i2 + " ; to : " + i3);
        this.s = true;
        if (i3 < 0 || i3 >= getItemCount()) {
            return false;
        }
        if (i2 < i3) {
            int i4 = i2;
            while (i4 < i3) {
                try {
                    int i5 = i4 + 1;
                    Collections.swap(this.m, i4, i5);
                    if (i5 <= this.n.size()) {
                        Collections.swap(this.n, i4, i5);
                    }
                    i4 = i5;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    return false;
                }
            }
        } else {
            for (int i6 = i2; i6 > i3; i6--) {
                int i7 = i6 - 1;
                Collections.swap(this.m, i6, i7);
                if (i6 <= this.n.size()) {
                    Collections.swap(this.n, i6, i7);
                }
            }
        }
        notifyItemMoved(i2, i3);
        return true;
    }

    public void e() {
        if (this.r) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    DeviceMainGridAdapterV2.this.notifyDataSetChanged();
                }
            });
        }
    }

    public void s() {
        DeviceTagInterface.Category d2;
        if (this.s && this.m != null && !this.m.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (Device next : this.m) {
                if (a(next)) {
                    MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
                    miioDeviceV2.name = this.i.getString(R.string.group_type_phoneir);
                    miioDeviceV2.did = DeviceUtils.a();
                    miioDeviceV2.model = IRDeviceUtil.a();
                    arrayList2.add(miioDeviceV2);
                    arrayList.add(CommonUseDeviceDataManager.g);
                } else {
                    arrayList.add(next.did);
                    arrayList2.add(next);
                }
            }
            List<Room> e2 = HomeManager.a().e();
            int i2 = this.k;
            if (i2 != 1) {
                switch (i2) {
                    case 4:
                        Room i3 = HomeManager.a().i(this.t);
                        if (i3 != null) {
                            int i4 = 0;
                            while (true) {
                                if (i4 >= e2.size()) {
                                    break;
                                } else {
                                    Room room = e2.get(i4);
                                    if (room.d().equals(i3.d())) {
                                        room.a((List<String>) arrayList);
                                        break;
                                    } else {
                                        i4++;
                                    }
                                }
                            }
                        } else {
                            return;
                        }
                    case 5:
                        HomeManager.a().h(HomeManager.d).a((List<String>) arrayList);
                        Home m2 = HomeManager.a().m();
                        if (m2.m() == null) {
                            m2.b((List<String>) new ArrayList());
                        }
                        m2.b((List<String>) arrayList);
                        break;
                }
            } else {
                HomeManager.a().h(HomeManager.e).a((List<String>) arrayList);
            }
            if (this.k == 0) {
                CommonUseDeviceDataManager.a().a((List<String>) arrayList, HomeManager.a().m());
            } else if (this.k == 3) {
                DeviceTagInterface<Device> b2 = SmartHomeDeviceHelper.a().b();
                if (!(b2 == null || this.l == null || !(this.l instanceof String) || (d2 = b2.d((String) this.l)) == null || TextUtils.isEmpty(d2.f15435a))) {
                    HomeManager.a().a((String) null, d2.f15435a, (List<String>) arrayList);
                }
            } else {
                HomeManager.a().a((String) null, e2);
            }
            STAT.d.h(this.k);
            this.s = false;
        }
    }

    public DeviceListPageActionInterface t() {
        return this.j;
    }

    public String u() {
        return this.l instanceof String ? (String) this.l : "";
    }

    public boolean b() {
        Home m2 = HomeManager.a().m();
        if (m2 != null) {
            return m2.p();
        }
        return true;
    }

    public DviceEditInterface.HostPage c() {
        return this.j instanceof DeviceMainPage ? DviceEditInterface.HostPage.MAIN_PAGE : DviceEditInterface.HostPage.ROOM_PAGE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean x() {
        /*
            r5 = this;
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.q()
            r1 = 0
            if (r0 != 0) goto L_0x000c
            return r1
        L_0x000c:
            r0 = 1
            java.util.Set<java.lang.String> r2 = r5.o
            java.util.Iterator r2 = r2.iterator()
        L_0x0013:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0030
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r4 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            com.xiaomi.smarthome.device.Device r3 = r4.b((java.lang.String) r3)
            if (r3 == 0) goto L_0x002f
            boolean r3 = r3.canBeShared()
            if (r3 != 0) goto L_0x0013
        L_0x002f:
            r0 = 0
        L_0x0030:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.adapter.DeviceMainGridAdapterV2.x():boolean");
    }

    public void a(Rect rect, View view, int i2, RecyclerView recyclerView, RecyclerView.State state) {
        if (i2 % 2 == 0) {
            rect.left = DisplayUtils.a(5.0f);
        } else {
            rect.right = DisplayUtils.a(5.0f);
        }
        boolean z = d() == 2;
        if (i2 != 0 && i2 != 1) {
            return;
        }
        if (z) {
            rect.top = DisplayUtils.a(0.0f);
        } else {
            rect.top = DisplayUtils.a(8.0f);
        }
    }
}
