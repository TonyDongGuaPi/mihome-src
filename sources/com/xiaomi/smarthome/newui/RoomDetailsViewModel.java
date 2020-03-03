package com.xiaomi.smarthome.newui;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.homeroom.HomeManager;
import com.xiaomi.smarthome.homeroom.model.Home;
import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.newui.card.ControlCardInfoManager;
import com.xiaomi.smarthome.newui.card.MiotSpecCardManager;
import com.xiaomi.smarthome.newui.roomenv.RoomEnvManager;
import com.xiaomi.smarthome.newui.roomenv.model.RoomEnvData;
import com.xiaomi.smarthome.newui.roomenv.model.RoomEnvDataItem;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomDetailsViewModel extends ViewModel {

    /* renamed from: a  reason: collision with root package name */
    static List<String> f20348a = Arrays.asList(SHApplication.getAppContext().getResources().getStringArray(R.array.room_attr_type));
    /* access modifiers changed from: private */
    public static final String b = "RoomDetailsViewModel";
    private HomeRoomRefreshReceiver c;
    /* access modifiers changed from: private */
    public DeviceListRefreshListener d;
    /* access modifiers changed from: private */
    public MutableLiveData<List<String>> e = new MutableLiveData<>();
    private MutableLiveData<List<Device>> f = new MutableLiveData<>();
    private int g = 0;

    static /* synthetic */ int b(RoomDetailsViewModel roomDetailsViewModel) {
        int i = roomDetailsViewModel.g;
        roomDetailsViewModel.g = i - 1;
        return i;
    }

    public MutableLiveData<List<String>> a(String str, String str2) {
        if (this.e.getValue() == null) {
            RoomEnvManager.a().a(str, str2, new AsyncCallback<RoomEnvData, Error>() {
                /* renamed from: a */
                public void onSuccess(RoomEnvData roomEnvData) {
                    if (roomEnvData != null) {
                        RoomDetailsViewModel.this.a(roomEnvData);
                    } else {
                        RoomDetailsViewModel.this.e.setValue(new ArrayList());
                    }
                }

                public void onFailure(Error error) {
                    RoomDetailsViewModel.this.e.setValue(new ArrayList());
                    if (!NetworkUtils.c()) {
                        ToastUtil.a((int) R.string.popup_select_loc_no_network);
                    } else {
                        ToastUtil.a((CharSequence) error.toString());
                    }
                }
            });
        }
        return this.e;
    }

    private void c(String str, String str2) {
        if (SHApplication.getStateNotifier().a() == 4) {
            RoomEnvManager.a().b(str, str2, new AsyncCallback<RoomEnvData, Error>() {
                /* renamed from: a */
                public void onSuccess(RoomEnvData roomEnvData) {
                    RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                    if (roomEnvData != null) {
                        RoomDetailsViewModel.this.a(roomEnvData);
                    } else {
                        RoomDetailsViewModel.this.e.setValue(new ArrayList());
                    }
                    String b = RoomDetailsViewModel.b;
                    LogUtil.a(b, "updateRoomEnvData onSuccess " + roomEnvData);
                }

                public void onFailure(Error error) {
                    RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                    RoomDetailsViewModel.this.e.setValue(new ArrayList());
                    if (!NetworkUtils.c()) {
                        ToastUtil.a((int) R.string.popup_select_loc_no_network);
                    } else {
                        ToastUtil.a((CharSequence) error.toString());
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(com.xiaomi.smarthome.newui.roomenv.model.RoomEnvData r13) {
        /*
            r12 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r13 = r13.c()
            if (r13 == 0) goto L_0x01e0
            int r1 = r13.size()
            if (r1 != 0) goto L_0x0013
            goto L_0x01e0
        L_0x0013:
            com.xiaomi.smarthome.newui.-$$Lambda$RoomDetailsViewModel$zs4poZE_b_jJ9KeCtpojVCLIbb8 r1 = com.xiaomi.smarthome.newui.$$Lambda$RoomDetailsViewModel$zs4poZE_b_jJ9KeCtpojVCLIbb8.INSTANCE
            java.util.Collections.sort(r13, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            r5 = 0
            r6 = 0
        L_0x002e:
            int r7 = r13.size()
            if (r6 >= r7) goto L_0x01a6
            java.lang.Object r7 = r13.get(r6)
            com.xiaomi.smarthome.newui.roomenv.model.RoomEnvDataItem r7 = (com.xiaomi.smarthome.newui.roomenv.model.RoomEnvDataItem) r7
            if (r7 == 0) goto L_0x01a2
            java.lang.String r8 = r7.b()
            boolean r8 = android.text.TextUtils.isEmpty(r8)
            if (r8 == 0) goto L_0x0048
            goto L_0x01a2
        L_0x0048:
            java.lang.String r8 = r7.f()
            r9 = -1
            int r10 = r8.hashCode()
            r11 = 1
            switch(r10) {
                case 103680: goto L_0x0088;
                case 3089326: goto L_0x007e;
                case 3442944: goto L_0x0074;
                case 3556308: goto L_0x006a;
                case 443971638: goto L_0x0060;
                case 1650990856: goto L_0x0056;
                default: goto L_0x0055;
            }
        L_0x0055:
            goto L_0x0092
        L_0x0056:
            java.lang.String r10 = "human_motion"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0092
            r8 = 5
            goto L_0x0093
        L_0x0060:
            java.lang.String r10 = "curtain_position"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0092
            r8 = 3
            goto L_0x0093
        L_0x006a:
            java.lang.String r10 = "temp"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0092
            r8 = 0
            goto L_0x0093
        L_0x0074:
            java.lang.String r10 = "pm25"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0092
            r8 = 2
            goto L_0x0093
        L_0x007e:
            java.lang.String r10 = "door"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0092
            r8 = 4
            goto L_0x0093
        L_0x0088:
            java.lang.String r10 = "hum"
            boolean r8 = r8.equals(r10)
            if (r8 == 0) goto L_0x0092
            r8 = 1
            goto L_0x0093
        L_0x0092:
            r8 = -1
        L_0x0093:
            switch(r8) {
                case 0: goto L_0x0190;
                case 1: goto L_0x0190;
                case 2: goto L_0x0190;
                case 3: goto L_0x0151;
                case 4: goto L_0x00f5;
                case 5: goto L_0x0098;
                default: goto L_0x0096;
            }
        L_0x0096:
            goto L_0x01a2
        L_0x0098:
            int r8 = r4.length()
            if (r8 <= 0) goto L_0x00a3
            java.lang.String r8 = " | "
            r4.append(r8)
        L_0x00a3:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r8 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.String r9 = r7.g()
            com.xiaomi.smarthome.device.Device r8 = r8.b((java.lang.String) r9)
            if (r8 == 0) goto L_0x01a2
            java.lang.String r9 = r7.b()
            java.lang.String r10 = "%s"
            boolean r9 = r9.contains(r10)
            if (r9 == 0) goto L_0x01a2
            java.lang.String r9 = r7.b()
            java.lang.Object[] r10 = new java.lang.Object[r11]
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r8 = r8.name
            r11.append(r8)
            java.lang.String r8 = " "
            r11.append(r8)
            long r7 = r7.c()
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            java.lang.String r7 = com.xiaomi.smarthome.newui.card.CardItem.a((java.lang.Object) r7)
            r11.append(r7)
            java.lang.String r7 = " "
            r11.append(r7)
            java.lang.String r7 = r11.toString()
            r10[r5] = r7
            java.lang.String r7 = java.lang.String.format(r9, r10)
            r4.append(r7)
            goto L_0x01a2
        L_0x00f5:
            int r8 = r3.length()
            if (r8 <= 0) goto L_0x0100
            java.lang.String r8 = " | "
            r3.append(r8)
        L_0x0100:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r8 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.String r9 = r7.g()
            com.xiaomi.smarthome.device.Device r8 = r8.b((java.lang.String) r9)
            if (r8 == 0) goto L_0x01a2
            java.lang.String r9 = r7.b()
            java.lang.String r10 = "%s"
            boolean r9 = r9.contains(r10)
            if (r9 == 0) goto L_0x01a2
            java.lang.String r9 = r7.b()
            java.lang.Object[] r10 = new java.lang.Object[r11]
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r8 = r8.name
            r11.append(r8)
            java.lang.String r8 = " "
            r11.append(r8)
            long r7 = r7.c()
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            java.lang.String r7 = com.xiaomi.smarthome.newui.card.CardItem.a((java.lang.Object) r7)
            r11.append(r7)
            java.lang.String r7 = " "
            r11.append(r7)
            java.lang.String r7 = r11.toString()
            r10[r5] = r7
            java.lang.String r7 = java.lang.String.format(r9, r10)
            r3.append(r7)
            goto L_0x01a2
        L_0x0151:
            int r8 = r2.length()
            if (r8 <= 0) goto L_0x015c
            java.lang.String r8 = " | "
            r2.append(r8)
        L_0x015c:
            com.xiaomi.smarthome.device.SmartHomeDeviceManager r8 = com.xiaomi.smarthome.device.SmartHomeDeviceManager.a()
            java.lang.String r9 = r7.g()
            com.xiaomi.smarthome.device.Device r8 = r8.b((java.lang.String) r9)
            if (r8 == 0) goto L_0x01a2
            java.lang.String r8 = r8.name
            r2.append(r8)
            java.lang.String r8 = " "
            r2.append(r8)
            long r8 = r7.c()
            java.lang.Long r8 = java.lang.Long.valueOf(r8)
            java.lang.String r8 = com.xiaomi.smarthome.newui.card.CardItem.a((java.lang.Object) r8)
            r2.append(r8)
            java.lang.String r8 = " "
            r2.append(r8)
            java.lang.String r7 = r7.b()
            r2.append(r7)
            goto L_0x01a2
        L_0x0190:
            int r8 = r1.length()
            if (r8 <= 0) goto L_0x019b
            java.lang.String r8 = " | "
            r1.append(r8)
        L_0x019b:
            java.lang.String r7 = r7.b()
            r1.append(r7)
        L_0x01a2:
            int r6 = r6 + 1
            goto L_0x002e
        L_0x01a6:
            boolean r13 = android.text.TextUtils.isEmpty(r1)
            if (r13 != 0) goto L_0x01b3
            java.lang.String r13 = r1.toString()
            r0.add(r13)
        L_0x01b3:
            boolean r13 = android.text.TextUtils.isEmpty(r2)
            if (r13 != 0) goto L_0x01c0
            java.lang.String r13 = r2.toString()
            r0.add(r13)
        L_0x01c0:
            boolean r13 = android.text.TextUtils.isEmpty(r3)
            if (r13 != 0) goto L_0x01cd
            java.lang.String r13 = r3.toString()
            r0.add(r13)
        L_0x01cd:
            boolean r13 = android.text.TextUtils.isEmpty(r4)
            if (r13 != 0) goto L_0x01da
            java.lang.String r13 = r4.toString()
            r0.add(r13)
        L_0x01da:
            android.arch.lifecycle.MutableLiveData<java.util.List<java.lang.String>> r13 = r12.e
            r13.setValue(r0)
            return
        L_0x01e0:
            android.arch.lifecycle.MutableLiveData<java.util.List<java.lang.String>> r13 = r12.e
            r13.setValue(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.RoomDetailsViewModel.a(com.xiaomi.smarthome.newui.roomenv.model.RoomEnvData):void");
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ int a(RoomEnvDataItem roomEnvDataItem, RoomEnvDataItem roomEnvDataItem2) {
        int indexOf = f20348a.indexOf(roomEnvDataItem.f());
        int indexOf2 = f20348a.indexOf(roomEnvDataItem2.f());
        return indexOf == indexOf2 ? -((int) (roomEnvDataItem.c() - roomEnvDataItem2.c())) : indexOf - indexOf2;
    }

    public MutableLiveData<List<Device>> a(String str) {
        if (this.f.getValue() == null) {
            b(str);
        }
        return this.f;
    }

    public void b(String str) {
        if (this.g <= 1) {
            LogUtil.a(b, "genarateRoomDevicesData");
            Room i = HomeManager.a().i(str);
            if (i != null) {
                ArrayList arrayList = new ArrayList();
                List<String> h = i.h();
                for (int i2 = 0; i2 < h.size(); i2++) {
                    Device b2 = SmartHomeDeviceManager.a().b(h.get(i2));
                    if (b2 != null) {
                        arrayList.add(b2);
                    }
                }
                this.f.setValue(arrayList);
                return;
            }
            this.f.setValue(null);
        }
    }

    public void b(String str, final String str2) {
        LogUtil.a(b, "start refresh");
        Home j = HomeManager.a().j(str);
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            LogUtil.a(b, "room or home id is null!" + System.currentTimeMillis());
            return;
        }
        if (this.c == null) {
            this.c = new HomeRoomRefreshReceiver();
            this.c.f20354a = str2;
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).registerReceiver(this.c, new IntentFilter(HomeManager.t));
        }
        if (this.d == null) {
            this.d = new DeviceListRefreshListener(str2);
            SmartHomeDeviceManager.a().a((SmartHomeDeviceManager.IClientDeviceListener) this.d);
        }
        if (this.g <= 0) {
            this.g = 5;
            c(str, str2);
            if (j == null || !j.p()) {
                this.g--;
            } else {
                HomeManager.a().b();
            }
            SmartHomeDeviceManager.a().p();
            ControlCardInfoManager.f().a(false, (AsyncCallback<Object, Error>) new AsyncCallback<Object, Error>() {
                public void onSuccess(Object obj) {
                    RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                    RoomDetailsViewModel.this.b(str2);
                    LogUtil.a(RoomDetailsViewModel.b, "loadPropsFromServer");
                }

                public void onFailure(Error error) {
                    RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                }
            }, "forceUpdateAllData");
            MiotSpecCardManager.f().a(false, (AsyncCallback<Object, Error>) new AsyncCallback<Object, Error>() {
                public void onSuccess(Object obj) {
                    RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                    RoomDetailsViewModel.this.b(str2);
                    LogUtil.a(RoomDetailsViewModel.b, "refreshDeviceProps");
                }

                public void onFailure(Error error) {
                    RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                }
            });
        }
    }

    class HomeRoomRefreshReceiver extends BroadcastReceiver {

        /* renamed from: a  reason: collision with root package name */
        String f20354a;

        HomeRoomRefreshReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), HomeManager.t)) {
                RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                RoomDetailsViewModel.this.b(this.f20354a);
                String b2 = RoomDetailsViewModel.b;
                LogUtil.a(b2, "home room refresh ready!" + System.currentTimeMillis());
            }
        }
    }

    class DeviceListRefreshListener implements SmartHomeDeviceManager.IClientDeviceListener {

        /* renamed from: a  reason: collision with root package name */
        String f20353a;

        public void a(int i, Device device) {
        }

        public void b(int i) {
        }

        public DeviceListRefreshListener(String str) {
            this.f20353a = str;
        }

        public void a(int i) {
            if (i == 3) {
                RoomDetailsViewModel.b(RoomDetailsViewModel.this);
                RoomDetailsViewModel.this.b(this.f20353a);
                String b2 = RoomDetailsViewModel.b;
                LogUtil.a(b2, "device list refresh ready!" + System.currentTimeMillis());
                SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) RoomDetailsViewModel.this.d);
                DeviceListRefreshListener unused = RoomDetailsViewModel.this.d = null;
            }
        }
    }

    public void a() {
        if (this.c != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).unregisterReceiver(this.c);
            this.c = null;
        }
        if (this.d != null) {
            SmartHomeDeviceManager.a().c((SmartHomeDeviceManager.IClientDeviceListener) this.d);
            this.d = null;
        }
    }
}
