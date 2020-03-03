package com.tmall.wireless.vaf.expr.engine;

import android.util.Log;
import com.libra.expr.common.ExprCode;
import com.libra.expr.common.StringSupport;
import com.tmall.wireless.vaf.expr.engine.executor.AddEqExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.AddExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.AndExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.ArrayExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.DivEqExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.DivExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.EqEqExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.EqualExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.Executor;
import com.tmall.wireless.vaf.expr.engine.executor.FunExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.GEExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.GTExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.JmpExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.JmpcExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.LEExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.LTExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.MinusExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.ModEqExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.ModExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.MulEqExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.MulExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.NotEqExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.NotExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.OrExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.SubEqExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.SubExecutor;
import com.tmall.wireless.vaf.expr.engine.executor.TerExecutor;
import java.util.ArrayList;
import java.util.List;

public class ExprEngine {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9348a = "ExprEngine_TMTEST";
    private List<Executor> b = new ArrayList();
    private int c;
    private EngineContext d = new EngineContext();

    public ExprEngine() {
        this.b.add(new AddExecutor());
        this.b.add(new SubExecutor());
        this.b.add(new MulExecutor());
        this.b.add(new DivExecutor());
        this.b.add(new ModExecutor());
        this.b.add(new EqualExecutor());
        this.b.add(new TerExecutor());
        this.b.add(new MinusExecutor());
        this.b.add(new NotExecutor());
        this.b.add(new GTExecutor());
        this.b.add(new LTExecutor());
        this.b.add(new NotEqExecutor());
        this.b.add(new EqEqExecutor());
        this.b.add(new GEExecutor());
        this.b.add(new LEExecutor());
        this.b.add(new FunExecutor());
        this.b.add(new AddEqExecutor());
        this.b.add(new SubEqExecutor());
        this.b.add(new MulEqExecutor());
        this.b.add(new DivEqExecutor());
        this.b.add(new ModEqExecutor());
        this.b.add(new JmpExecutor());
        this.b.add(new JmpcExecutor());
        this.b.add(new AndExecutor());
        this.b.add(new OrExecutor());
        this.b.add(new ArrayExecutor());
        this.c = this.b.size();
    }

    public EngineContext a() {
        return this.d;
    }

    public void b() {
        for (Executor d2 : this.b) {
            d2.d();
        }
        this.b.clear();
        this.d.a();
    }

    public void c() {
        for (Executor a2 : this.b) {
            a2.a(this.d);
        }
    }

    public void a(NativeObjectManager nativeObjectManager) {
        this.d.a(nativeObjectManager);
    }

    public void a(StringSupport stringSupport) {
        this.d.a(stringSupport);
    }

    public boolean a(Object obj, ExprCode exprCode) {
        CodeReader d2 = this.d.d();
        if (exprCode != null) {
            d2.a(exprCode);
            int i = 2;
            while (true) {
                byte d3 = d2.d();
                if (d3 > -1 && d3 < this.c) {
                    Executor executor = this.b.get(d3);
                    executor.a();
                    i = executor.a(obj);
                    if (1 == i) {
                        if (d2.c()) {
                            break;
                        }
                    } else {
                        break;
                    }
                } else {
                    Log.e(f9348a, "operator code error:" + d3);
                }
            }
            if (1 == i) {
                return true;
            }
        }
        return false;
    }
}
