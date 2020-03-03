package com.xiaomi.smarthome.homeroom;

import com.xiaomi.smarthome.homeroom.device_order.RoomOrder;
import com.xiaomi.smarthome.homeroom.model.Room;
import kotlin.jvm.functions.Function2;

/* renamed from: com.xiaomi.smarthome.homeroom.-$$Lambda$HomeManager$HomeDataManager$jBL0mFysg1g8l_2uttjYOEIPzfw  reason: invalid class name */
/* compiled from: lambda */
public final /* synthetic */ class $$Lambda$HomeManager$HomeDataManager$jBL0mFysg1g8l_2uttjYOEIPzfw implements Function2 {
    public static final /* synthetic */ $$Lambda$HomeManager$HomeDataManager$jBL0mFysg1g8l_2uttjYOEIPzfw INSTANCE = new $$Lambda$HomeManager$HomeDataManager$jBL0mFysg1g8l_2uttjYOEIPzfw();

    private /* synthetic */ $$Lambda$HomeManager$HomeDataManager$jBL0mFysg1g8l_2uttjYOEIPzfw() {
    }

    public final Object invoke(Object obj, Object obj2) {
        return Boolean.valueOf(((Room) obj2).d().equals(((RoomOrder) obj).a()));
    }
}
