package com.xiaomi.smarthome.newui.adapter;

import com.xiaomi.smarthome.homeroom.model.Room;
import com.xiaomi.smarthome.newui.DeviceListPageActionInterface;
import java.util.Set;

public interface DviceEditInterface {

    public enum HostPage {
        MAIN_PAGE,
        ROOM_PAGE,
        TRANSFER_GUIDE_PAGE
    }

    boolean L_();

    void a(int i, boolean z);

    boolean b();

    HostPage c();

    void j();

    void k();

    Set<String> l();

    int m();

    int n();

    void o();

    int p();

    int q();

    Room r();

    void s();

    DeviceListPageActionInterface t();

    String u();
}
