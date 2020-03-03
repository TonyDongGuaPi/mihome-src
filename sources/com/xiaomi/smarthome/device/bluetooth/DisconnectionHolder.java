package com.xiaomi.smarthome.device.bluetooth;

import com.taobao.weex.el.parse.Operators;
import java.sql.Timestamp;

public class DisconnectionHolder implements Comparable<DisconnectionHolder> {

    /* renamed from: a  reason: collision with root package name */
    public long f15107a;
    public String b;

    public DisconnectionHolder() {
    }

    public DisconnectionHolder(String str) {
        this.b = str;
    }

    /* renamed from: a */
    public int compareTo(DisconnectionHolder disconnectionHolder) {
        return (int) (this.f15107a - disconnectionHolder.f15107a);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof DisconnectionHolder)) {
            return false;
        }
        DisconnectionHolder disconnectionHolder = (DisconnectionHolder) obj;
        if (this.b != null) {
            return this.b.equals(disconnectionHolder.b);
        }
        if (disconnectionHolder.b == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (this.b != null) {
            return this.b.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "DisconnectionHolder{deadline=" + new Timestamp(this.f15107a) + ", mac='" + this.b + Operators.SINGLE_QUOTE + Operators.BLOCK_END;
    }
}
