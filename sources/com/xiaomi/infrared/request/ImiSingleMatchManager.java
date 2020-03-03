package com.xiaomi.infrared.request;

import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.infrared.bean.IRKeyValue;
import com.xiaomi.infrared.bean.IRType;
import com.xiaomi.infrared.bean.MJKeyEntity;
import com.xiaomi.infrared.bean.MJNodesEntity;
import java.util.LinkedList;
import java.util.List;

public class ImiSingleMatchManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f10239a = "1";
    private static final String b = "ImiSingleMatchManager";
    private IIRSingleMatchResult c;
    private IRType d;
    private LinkedList<MJNodesEntity> e = new LinkedList<>();
    private List<MJNodesEntity> f;

    public interface IIRSingleMatchResult {
        void a();

        void a(IRKeyValue iRKeyValue, int i, int i2, int i3);

        void a(String str);
    }

    public ImiSingleMatchManager(List<MJNodesEntity> list, IRType iRType, IIRSingleMatchResult iIRSingleMatchResult) {
        this.d = iRType;
        this.f = list;
        this.c = iIRSingleMatchResult;
        a("1");
    }

    public void a() {
        if (this.e.size() > 1) {
            this.e.removeLast();
            MJNodesEntity last = this.e.getLast();
            Log.d(b, "lastKeyValue: mismatched " + last.c());
            a(a(last), last.a(), last.b(), this.e.size());
        }
    }

    public void b() {
        d();
    }

    private void a(String str) {
        MJNodesEntity c2 = c(str);
        this.e.add(c2);
        if (c2 != null) {
            a(a(c2), c2.a(), c2.b(), this.e.size());
        } else {
            e();
        }
    }

    public void c() {
        String g = this.e.getLast().g();
        if ("0".equals(g) || TextUtils.isEmpty(g)) {
            b(String.valueOf(this.e.getLast().e()));
        } else {
            a(g);
        }
    }

    public void d() {
        String h = this.e.getLast().h();
        if ("0".equals(h) || TextUtils.isEmpty(h)) {
            e();
        } else {
            a(h);
        }
    }

    private void b(String str) {
        if (this.c != null) {
            this.c.a(str);
        }
    }

    private void a(IRKeyValue iRKeyValue, int i, int i2, int i3) {
        if (this.c != null) {
            this.c.a(iRKeyValue, i, i2, i3);
        }
    }

    private void e() {
        if (this.c != null) {
            this.c.a();
        }
    }

    private IRKeyValue a(MJNodesEntity mJNodesEntity) {
        String e2 = mJNodesEntity.e();
        MJKeyEntity d2 = mJNodesEntity.d();
        IRKeyValue iRKeyValue = new IRKeyValue();
        iRKeyValue.a(this.d);
        String a2 = d2.a();
        if ("0".equals(a2) || TextUtils.isEmpty(a2)) {
            iRKeyValue.f(d2.e());
        } else {
            if (iRKeyValue.b() == IRType.AC) {
                iRKeyValue.a(IRType.NO_STATE_AC);
            }
            iRKeyValue.f(String.valueOf(a2));
        }
        iRKeyValue.d(d2.b());
        iRKeyValue.e("");
        iRKeyValue.c(d2.c());
        iRKeyValue.b(e2);
        return iRKeyValue;
    }

    private MJNodesEntity c(String str) {
        int i;
        try {
            i = Integer.parseInt(str) - 1;
        } catch (Throwable unused) {
            i = 0;
        }
        if (i < this.f.size()) {
            MJNodesEntity mJNodesEntity = this.f.get(i);
            if (TextUtils.equals(mJNodesEntity.c(), str)) {
                return mJNodesEntity;
            }
        }
        for (MJNodesEntity next : this.f) {
            if (TextUtils.equals(next.c(), str)) {
                return next;
            }
        }
        return null;
    }
}
