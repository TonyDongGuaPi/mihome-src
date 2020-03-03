package com.xiaomi.jr.mipay.codepay.presenter;

import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import com.xiaomi.jr.mipay.codepay.api.ApiManager;
import com.xiaomi.jr.mipay.codepay.data.DoPayResult;
import com.xiaomi.jr.mipay.codepay.data.TradeResult;
import com.xiaomi.jr.mipay.common.http.MipayHttpCallback;

public class CodePayTrader {

    /* renamed from: a  reason: collision with root package name */
    public static final int f10909a = -1;
    /* access modifiers changed from: private */
    public Handler b = new QueryHandler();
    /* access modifiers changed from: private */
    public TradeCallback c;
    private MipayHttpCallback<DoPayResult> d = new MipayHttpCallback<DoPayResult>((Fragment) null) {
        public void a(DoPayResult doPayResult) {
            CodePayTrader.this.c(doPayResult.f10892a);
        }

        public void a(int i, String str, DoPayResult doPayResult, Throwable th) {
            if (CodePayTrader.this.c != null) {
                CodePayTrader.this.c.a(i, str, doPayResult);
            }
        }
    };

    public interface TradeCallback {
        void a(int i, String str, DoPayResult doPayResult);

        void a(int i, String str, TradeResult tradeResult);
    }

    private static class QueryHandler extends Handler {
        private QueryHandler() {
        }

        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    public void a(String str, String str2, int i, String str3, int i2, String str4, boolean z, TradeCallback tradeCallback) {
        this.c = tradeCallback;
        ApiManager.a().a(str, str2, i, str3, i2, str4, z).enqueue(this.d);
    }

    public void a(String str, String str2, TradeCallback tradeCallback) {
        this.c = tradeCallback;
        ApiManager.a().b(str, str2).enqueue(this.d);
    }

    /* access modifiers changed from: private */
    /* renamed from: a */
    public void c(final String str) {
        ApiManager.a().a(str).enqueue(new MipayHttpCallback<TradeResult>((Fragment) null) {
            public void a(TradeResult tradeResult) {
                CodePayTrader.this.b.removeCallbacksAndMessages((Object) null);
                if (TextUtils.equals(tradeResult.e, "WAIT_PAY")) {
                    CodePayTrader.this.b(str);
                } else if (CodePayTrader.this.c != null) {
                    CodePayTrader.this.c.a(200, (String) null, tradeResult);
                }
            }

            public void a(int i, String str, TradeResult tradeResult, Throwable th) {
                if (CodePayTrader.this.c != null) {
                    CodePayTrader.this.c.a(i, str, tradeResult);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void b(String str) {
        this.b.postDelayed(new Runnable(str) {
            private final /* synthetic */ String f$1;

            {
                this.f$1 = r2;
            }

            public final void run() {
                CodePayTrader.this.c(this.f$1);
            }
        }, 1000);
    }

    public void a() {
        this.b.removeCallbacksAndMessages((Object) null);
        if (this.c != null) {
            this.c.a(-1, "abort trade", (TradeResult) null);
        }
    }
}
