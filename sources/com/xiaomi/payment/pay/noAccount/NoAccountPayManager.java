package com.xiaomi.payment.pay.noAccount;

import android.content.Context;
import com.xiaomi.payment.recharge.AlipayRecharge;
import com.xiaomi.payment.recharge.MipayRecharge;
import com.xiaomi.payment.recharge.PaypalRecharge;
import com.xiaomi.payment.recharge.Recharge;
import com.xiaomi.payment.recharge.WXRecharge;
import java.util.HashMap;

public class NoAccountPayManager {

    /* renamed from: a  reason: collision with root package name */
    private static final NoAccountPayManager f12387a = new NoAccountPayManager();
    private HashMap<String, Recharge> b = new HashMap<>();

    public enum CHANNELS {
        WXPAY(WXRecharge.class),
        ALIPAY(AlipayRecharge.class),
        MIPAY(MipayRecharge.class),
        PAYPALPAY(PaypalRecharge.class);
        
        private Recharge mRecharge;

        private CHANNELS(Class<? extends Recharge> cls) {
            this.mRecharge = null;
            try {
                this.mRecharge = (Recharge) cls.newInstance();
            } catch (InstantiationException e) {
                throw new IllegalStateException(e);
            } catch (IllegalAccessException e2) {
                throw new IllegalStateException(e2);
            }
        }

        public Recharge getRecharge() {
            return this.mRecharge;
        }

        public String getChannel() {
            return this.mRecharge.a();
        }
    }

    private NoAccountPayManager() {
        for (CHANNELS channels : CHANNELS.values()) {
            this.b.put(channels.getChannel(), channels.getRecharge());
        }
    }

    public static NoAccountPayManager a() {
        return f12387a;
    }

    public Recharge a(String str) {
        return this.b.get(str);
    }

    public static String a(Context context) {
        StringBuilder sb = new StringBuilder();
        for (CHANNELS channels : CHANNELS.values()) {
            if (channels.getRecharge().a(context)) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(channels.getChannel());
            }
        }
        return sb.toString();
    }
}
