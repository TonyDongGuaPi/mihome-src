package com.xiaomi.shopviews.widget.homepanicbuyview;

import android.os.Handler;
import android.os.Message;
import com.xiaomi.base.utils.ServerTimerUtils;
import com.xiaomi.shopviews.model.HomeSectionItem;

public class PanicBuyTimer {

    /* renamed from: a  reason: collision with root package name */
    private static final int f13310a = 900000;
    private static final int b = 1800000;
    private static final int c = 1;
    private Handler d = new Handler() {
        public void handleMessage(Message message) {
            if (message.what == 1) {
                PanicBuyTimer.this.a((HomeSectionItem) message.obj, true);
            }
        }
    };
    private PanicBuyTimerListener e;

    public interface PanicBuyTimerListener {
        void a(HomeSectionItem homeSectionItem);

        void b(HomeSectionItem homeSectionItem);

        void c(HomeSectionItem homeSectionItem);

        void d(HomeSectionItem homeSectionItem);

        void e(HomeSectionItem homeSectionItem);

        void f(HomeSectionItem homeSectionItem);

        void g(HomeSectionItem homeSectionItem);
    }

    private Message b(HomeSectionItem homeSectionItem) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = homeSectionItem;
        return obtain;
    }

    public void a() {
        this.d.removeCallbacksAndMessages((Object) null);
    }

    public void a(HomeSectionItem homeSectionItem, boolean z) {
        long a2 = ServerTimerUtils.a(homeSectionItem.mServerTime * 1000);
        long j = homeSectionItem.mStartTime * 1000;
        long j2 = homeSectionItem.mEndTime * 1000;
        long a3 = (long) ServerTimerUtils.a(j, a2);
        if (a3 > 1) {
            if (z) {
                this.d.sendMessageDelayed(b(homeSectionItem), ServerTimerUtils.b(a2));
            }
            if (this.e != null) {
                this.e.e(homeSectionItem);
            }
        } else if (a3 == 1) {
            if (z) {
                this.d.sendMessageDelayed(b(homeSectionItem), ServerTimerUtils.b(a2));
            }
            if (this.e != null) {
                this.e.g(homeSectionItem);
            }
        } else if (a3 == 0 && this.e != null) {
            this.e.f(homeSectionItem);
        }
        long j3 = j - 1800000;
        if (a2 >= j3) {
            if (a2 >= j3) {
                long j4 = j - 900000;
                if (a2 < j4) {
                    if (this.e != null) {
                        this.e.d(homeSectionItem);
                    }
                    if (z) {
                        this.d.sendMessageDelayed(b(homeSectionItem), j4 - a2);
                        return;
                    }
                    return;
                }
            }
            if (a2 < j && a2 >= j - 900000) {
                if (this.e != null) {
                    this.e.c(homeSectionItem);
                }
                if (z) {
                    this.d.sendMessageDelayed(b(homeSectionItem), j - a2);
                }
            } else if (a2 >= j && a2 < j2) {
                if (this.e != null) {
                    this.e.a(homeSectionItem);
                }
                if (z) {
                    this.d.sendMessageDelayed(b(homeSectionItem), j2 - a2);
                }
            } else if (this.e != null) {
                this.e.b(homeSectionItem);
            }
        } else if (z) {
            this.d.sendMessageDelayed(b(homeSectionItem), j3 - a2);
        }
    }

    public void a(PanicBuyTimerListener panicBuyTimerListener) {
        this.e = panicBuyTimerListener;
    }

    public void a(HomeSectionItem homeSectionItem) {
        a(homeSectionItem, true);
    }
}
