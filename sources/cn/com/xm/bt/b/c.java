package cn.com.xm.bt.b;

import java.io.Serializable;

public enum c implements Serializable {
    VDevice(-1),
    MILI(0),
    SENSORHUB(2),
    WEIGHT(1),
    SHOES(3);
    
    private int f;

    private c(int i) {
        this.f = 0;
        this.f = i;
    }
}
