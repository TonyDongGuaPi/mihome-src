package com.xiaomi.payment.deduct;

import android.content.Context;
import com.xiaomi.payment.recharge.Recharge;
import java.util.HashMap;

public class DeductManager {

    /* renamed from: a  reason: collision with root package name */
    private static final DeductManager f12241a = new DeductManager();
    private HashMap<String, Recharge> b = new HashMap<>();

    public enum CHANNELS {
        WXPAY(WxpayDeduct.class),
        ALIPAY(AlipayDeduct.class),
        MIPAY(MipayDeduct.class);
        
        private Recharge mDeduct;

        private CHANNELS(Class<? extends Recharge> cls) {
            this.mDeduct = null;
            try {
                this.mDeduct = (Recharge) cls.newInstance();
            } catch (InstantiationException e) {
                throw new IllegalStateException(e);
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException(e2);
            }
        }

        public Recharge getDeduct() {
            return this.mDeduct;
        }

        public String getChannel() {
            return this.mDeduct.a();
        }
    }

    private DeductManager() {
        for (CHANNELS channels : CHANNELS.values()) {
            this.b.put(channels.getChannel(), channels.getDeduct());
        }
    }

    public static DeductManager a() {
        return f12241a;
    }

    public Recharge a(String str) {
        return this.b.get(str);
    }

    public static String a(Context context) {
        StringBuilder sb = new StringBuilder();
        for (CHANNELS channels : CHANNELS.values()) {
            if (channels.getDeduct().a(context)) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(channels.getChannel());
            }
        }
        return sb.toString();
    }
}
