package com.xiaomi.smarthome.newui.widget.topnavi;

import com.xiaomi.smarthome.stat.STAT;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/xiaomi/smarthome/newui/widget/topnavi/HomeRoomShowEvent;", "Lcom/xiaomi/smarthome/newui/widget/topnavi/StickEvent;", "()V", "process", "", "app_GooglePlayRelease"}, k = 1, mv = {1, 1, 13})
public final class HomeRoomShowEvent implements StickEvent {

    /* renamed from: a  reason: collision with root package name */
    public static final HomeRoomShowEvent f20910a = new HomeRoomShowEvent();

    private HomeRoomShowEvent() {
    }

    public void a() {
        STAT.c.e();
    }
}
