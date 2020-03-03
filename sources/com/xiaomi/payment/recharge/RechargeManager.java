package com.xiaomi.payment.recharge;

import android.content.Context;
import java.util.HashMap;

public class RechargeManager {

    /* renamed from: a  reason: collision with root package name */
    private static final RechargeManager f12409a = new RechargeManager();
    private HashMap<String, Recharge> b = new HashMap<>();

    public enum CHANNELS {
        WXPAY(WXRecharge.class),
        ALIPAY(AlipayRecharge.class),
        VOUCHER(VoucherPayRecharge.class),
        SZFCARDPAY(SzfCardPayRecharge.class),
        HEECARDPAY(HeeCardPayRecharge.class),
        SFTCARDPAY(SftCardPayRecharge.class),
        GYCARDPAY(GYCardPayRecharge.class),
        MIPAY(MipayRecharge.class),
        MIPAYFQ(MipayFQRecharge.class),
        WOUNICOMMSGPAY(WoUnicomMsgPayRecharge.class),
        TYUNICOMMSGPAY(TyUnicomMsgPayRecharge.class),
        APITELCOMMSGPAY(ApiTelcomMsgPayRecharge.class),
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

    public enum DIRECT_CHANNELS {
        WXPAY(WXRecharge.class),
        ALIPAY(AlipayRecharge.class),
        MIPAY(MipayRecharge.class),
        PAYPALPAY(PaypalRecharge.class);
        
        private Recharge mRecharge;

        private DIRECT_CHANNELS(Class<? extends Recharge> cls) {
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

    private RechargeManager() {
        for (CHANNELS channels : CHANNELS.values()) {
            this.b.put(channels.getChannel(), channels.getRecharge());
        }
    }

    public static RechargeManager a() {
        return f12409a;
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

    public static String b(Context context) {
        StringBuilder sb = new StringBuilder();
        for (DIRECT_CHANNELS direct_channels : DIRECT_CHANNELS.values()) {
            if (direct_channels.getRecharge().a(context)) {
                if (sb.length() != 0) {
                    sb.append(",");
                }
                sb.append(direct_channels.getChannel());
            }
        }
        return sb.toString();
    }
}
