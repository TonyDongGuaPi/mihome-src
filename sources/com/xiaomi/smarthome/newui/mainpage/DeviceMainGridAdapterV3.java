package com.xiaomi.smarthome.newui.mainpage;

import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
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
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import com.xiaomi.smarthome.device.utils.IRDeviceUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.homeroom.CommonUseDeviceDataManager;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthome.multi_item.IAdapter;
import com.xiaomi.smarthome.newui.DeviceListPageActionInterface;
import com.xiaomi.smarthome.newui.DeviceMainPage;
import com.xiaomi.smarthome.newui.DropMenuStateHelper;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.dragsort.SimpleItemDelegateTouchHelperCallback;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageDeviceModel;
import com.xiaomi.smarthome.newui.mainpage.devicepage.model.MainPageViewState;
import com.xiaomi.smarthome.newui.widget.topnavi.PageBean;
import com.xiaomi.smarthome.stat.STAT;
import com.xiaomi.smarthome.ui.base.EditModeView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class DeviceMainGridAdapterV3 extends IAdapter implements DviceEditInterface {

    /* renamed from: a  reason: collision with root package name */
    private static final String f20671a = "MVI-DeviceGridAdapterV3";
    private List<MainPageDeviceModel> b = new ArrayList();
    private List<MainPageDeviceModel> c = new ArrayList();
    private SimpleItemDelegateTouchHelperCallback d;
    private ItemTouchHelper e;
    private EditModeView.EditModeModel f;
    private final Set<String> g = new LinkedHashSet();
    private int h = 0;
    private boolean i = false;
    private DeviceListPageActionInterface j;
    private boolean k = false;

    /* access modifiers changed from: protected */
    public int a() {
        return 2;
    }

    public String u() {
        return null;
    }

    public void a(MainPageViewState mainPageViewState) {
        if (!this.i) {
            this.h = mainPageViewState.g();
            List<MainPageDeviceModel> d2 = mainPageViewState.d();
            StringBuilder sb = new StringBuilder();
            sb.append("update in ");
            sb.append(d2 == null ? null : Integer.valueOf(d2.size()));
            LogUtilGrey.a(f20671a, sb.toString());
            this.c = new ArrayList(this.b);
            if (d2 == null) {
                d2 = new ArrayList<>();
            }
            this.b = d2;
            this.f = mainPageViewState.a();
            notifyDataSetChanged();
        }
    }

    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i2) {
        LogUtilGrey.a(f20671a, "onCreateViewHolder");
        return new DeviceGridCardViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_grid_v3, viewGroup, false), viewGroup.getContext(), this, this.d, this.e);
    }

    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i2) {
        ((DeviceGridCardViewHolder) viewHolder).a(this.b.get(i2), i2);
    }

    public int getItemCount() {
        return this.b.size();
    }

    public void j() {
        this.i = true;
        this.g.clear();
    }

    public void k() {
        this.i = false;
        this.g.clear();
        s();
        LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent("com.smarthome.refresh_list_view"));
    }

    public boolean L_() {
        return this.i;
    }

    public Set<String> l() {
        return this.g;
    }

    public int m() {
        if (this.g == null) {
            return 0;
        }
        return this.g.size();
    }

    public int n() {
        return this.b.size();
    }

    public void a(int i2, boolean z) {
        try {
            Device b2 = SmartHomeDeviceManager.a().b(this.b.get(i2).d());
            if (!(b2 instanceof PhoneIRDevice)) {
                if (z) {
                    this.g.add(b2.did);
                } else {
                    this.g.remove(b2.did);
                }
                if (this.j != null) {
                    this.j.updateActionItems(this, this.g.size(), b2.did, f());
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:6:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean f() {
        /*
            r5 = this;
            com.xiaomi.smarthome.frame.core.CoreApi r0 = com.xiaomi.smarthome.frame.core.CoreApi.a()
            boolean r0 = r0.q()
            r1 = 0
            if (r0 != 0) goto L_0x000c
            return r1
        L_0x000c:
            r0 = 1
            java.util.Set<java.lang.String> r2 = r5.g
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
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.mainpage.DeviceMainGridAdapterV3.f():boolean");
    }

    public void o() {
        notifyDataSetChanged();
    }

    public int p() {
        int itemCount = getItemCount();
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (SmartHomeDeviceManager.a().b(this.b.get(i2).d()) instanceof PhoneIRDevice) {
                itemCount--;
            }
        }
        return itemCount;
    }

    public int q() {
        return this.h;
    }

    public Room r() {
        PageBean b2 = DropMenuStateHelper.a().b();
        if (b2 == null) {
            return null;
        }
        return HomeManager.a().i(b2.f);
    }

    public DeviceListPageActionInterface t() {
        return this.j;
    }

    public void a(DeviceListPageActionInterface deviceListPageActionInterface) {
        this.j = deviceListPageActionInterface;
    }

    public boolean b() {
        Home m = HomeManager.a().m();
        if (m != null) {
            return m.p();
        }
        return true;
    }

    public DviceEditInterface.HostPage c() {
        return this.j instanceof DeviceMainPage ? DviceEditInterface.HostPage.MAIN_PAGE : DviceEditInterface.HostPage.ROOM_PAGE;
    }

    public void a(SimpleItemDelegateTouchHelperCallback simpleItemDelegateTouchHelperCallback, ItemTouchHelper itemTouchHelper) {
        this.d = simpleItemDelegateTouchHelperCallback;
        this.e = itemTouchHelper;
    }

    public int a(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        Home m = HomeManager.a().m();
        if (m == null || !m.p()) {
            return 0;
        }
        return ItemTouchHelper.Callback.makeMovementFlags(15, 0);
    }

    public boolean a(int i2, int i3) {
        Log.e(f20671a, "onItemMove: from: " + i2 + " ; to : " + i3);
        this.k = true;
        if (i3 < 0 || i3 >= getItemCount()) {
            return false;
        }
        if (i2 < i3) {
            int i4 = i2;
            while (i4 < i3) {
                try {
                    int i5 = i4 + 1;
                    Collections.swap(this.b, i4, i5);
                    if (i5 <= this.c.size()) {
                        Collections.swap(this.c, i4, i5);
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
                Collections.swap(this.b, i6, i7);
                if (i6 <= this.c.size()) {
                    Collections.swap(this.c, i6, i7);
                }
            }
        }
        notifyItemMoved(i2, i3);
        return true;
    }

    public void e() {
        if (this.i) {
            SHApplication.getGlobalHandler().post(new Runnable() {
                public void run() {
                    DeviceMainGridAdapterV3.this.notifyDataSetChanged();
                }
            });
        }
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

    public void s() {
        DeviceTagInterface.Category d2;
        Room room;
        if (this.k && this.b != null && !this.b.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            for (MainPageDeviceModel next : this.b) {
                if (next.b()) {
                    MiioDeviceV2 miioDeviceV2 = new MiioDeviceV2();
                    miioDeviceV2.name = SHApplication.getAppContext().getString(R.string.group_type_phoneir);
                    miioDeviceV2.did = DeviceUtils.a();
                    miioDeviceV2.model = IRDeviceUtil.a();
                    arrayList2.add(miioDeviceV2);
                    arrayList.add(CommonUseDeviceDataManager.g);
                } else {
                    arrayList.add(next.d());
                    arrayList2.add(SmartHomeDeviceManager.a().b(next.d()));
                }
            }
            List<Room> e2 = HomeManager.a().e();
            int i2 = this.h;
            if (i2 != 1) {
                switch (i2) {
                    case 4:
                        PageBean b2 = DropMenuStateHelper.a().b();
                        if (b2 != null && (room = b2.g) != null) {
                            int i3 = 0;
                            while (true) {
                                if (i3 >= e2.size()) {
                                    break;
                                } else {
                                    Room room2 = e2.get(i3);
                                    if (room2.d().equals(room.d())) {
                                        room2.a((List<String>) arrayList);
                                        break;
                                    } else {
                                        i3++;
                                    }
                                }
                            }
                        } else {
                            return;
                        }
                        break;
                    case 5:
                        HomeManager.a().h(HomeManager.d).a((List<String>) arrayList);
                        Home m = HomeManager.a().m();
                        if (m.m() == null) {
                            m.b((List<String>) new ArrayList());
                        }
                        m.b((List<String>) arrayList);
                        break;
                }
            } else {
                HomeManager.a().h(HomeManager.e).a((List<String>) arrayList);
            }
            if (this.h == 0) {
                CommonUseDeviceDataManager.a().a((List<String>) arrayList, HomeManager.a().m());
            } else if (this.h == 3) {
                DeviceTagInterface<Device> b3 = SmartHomeDeviceHelper.a().b();
                PageBean b4 = DropMenuStateHelper.a().b();
                if (b4 != null) {
                    if (!(b3 == null || b4.f == null || (d2 = b3.d(b4.e)) == null || TextUtils.isEmpty(d2.f15435a))) {
                        HomeManager.a().a((String) null, d2.f15435a, (List<String>) arrayList);
                    }
                } else {
                    return;
                }
            } else {
                HomeManager.a().a((String) null, e2);
            }
            STAT.d.h(this.h);
            this.k = false;
        }
    }
}
