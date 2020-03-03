package cn.tongdun.android.shell;

import android.content.Context;
import cn.tongdun.android.core.gqg9qq9gqq9q9q;
import cn.tongdun.android.shell.common.CollectorError;
import cn.tongdun.android.shell.common.qgg9qgg9999g9g;
import cn.tongdun.android.shell.utils.LogUtil;

final class q9qq99qg9qqgqg9gqgg9 implements Runnable {
    final /* synthetic */ boolean g999gqq9ggqgqq;
    final /* synthetic */ String g9q9q9g9;
    final /* synthetic */ Context gqg9qq9gqq9q9q;
    final /* synthetic */ boolean gqgqgqq9gq9q9q9;
    final /* synthetic */ boolean q9gqqq99999qq;
    final /* synthetic */ String q9q99gq99gggqg9qqqgg;
    final /* synthetic */ String q9qq99qg9qqgqg9gqgg9;
    final /* synthetic */ String qgg99qqg9gq;
    final /* synthetic */ String qgg9qgg9999g9g;
    final /* synthetic */ String qgggqg999gg9qqggq;
    final /* synthetic */ boolean qq9gq9g9g99;
    final /* synthetic */ String qq9q9ggg;
    final /* synthetic */ String qqq9gg9gqq9qgg99q;

    q9qq99qg9qqgqg9gqgg9(Context context, String str, String str2, boolean z, String str3, String str4, String str5, boolean z2, boolean z3, String str6, String str7, String str8, boolean z4) {
        this.gqg9qq9gqq9q9q = context;
        this.qgg9qgg9999g9g = str;
        this.q9qq99qg9qqgqg9gqgg9 = str2;
        this.q9gqqq99999qq = z;
        this.g9q9q9g9 = str3;
        this.qqq9gg9gqq9qgg99q = str4;
        this.q9q99gq99gggqg9qqqgg = str5;
        this.g999gqq9ggqgqq = z2;
        this.gqgqgqq9gq9q9q9 = z3;
        this.qgg99qqg9gq = str6;
        this.qq9q9ggg = str7;
        this.qgggqg999gg9qqggq = str8;
        this.qq9gq9g9g99 = z4;
    }

    public void run() {
        CollectorError.clearError();
        CollectorError.addError(CollectorError.TYPE.ERROR_SHORT_INTERVAL, FMAgent.linkxxxxx("4c6654290732002f067b4f7c556d4269556419261e371d251d2e536754604934083b027f4d7e664d75466f", 28));
        long unused = FMAgent.mLastInitTime = System.currentTimeMillis();
        synchronized (FMAgent.class) {
            if (FMAgent.mFmInter == null) {
                gqg9qq9gqq9q9q unused2 = FMAgent.mFmInter = qgg9qgg9999g9g.gqg9qq9gqq9q9q(this.gqg9qq9gqq9q9q);
            }
            if (FMAgent.mFmInter != null) {
                FMAgent.mFmInter.gqg9qq9gqq9q9q(this.gqg9qq9gqq9q9q, this.qgg9qgg9999g9g, this.q9qq99qg9qqgqg9gqgg9, this.q9gqqq99999qq, this.g9q9q9g9, this.qqq9gg9gqq9qgg99q, this.q9q99gq99gggqg9qqqgg, this.g999gqq9ggqgqq, this.gqgqgqq9gq9q9q9, FMAgent.mDownLatch, this.qgg99qqg9gq, FMAgent.mBlackboxMaxSize, this.qq9q9ggg, this.qgggqg999gg9qqggq, this.qq9gq9g9g99);
                LogUtil.info(FMAgent.linkxxxxx("4c2d552456354d7b1e6c112758205f3d0b6e0d780d7e1b7e", 87));
            }
        }
    }
}
