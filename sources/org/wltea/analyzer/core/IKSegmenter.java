package org.wltea.analyzer.core;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.wltea.analyzer.cfg.Configuration;
import org.wltea.analyzer.cfg.DefaultConfig;
import org.wltea.analyzer.dic.Dictionary;

public final class IKSegmenter {

    /* renamed from: a  reason: collision with root package name */
    private Reader f4198a;
    private Configuration b;
    private AnalyzeContext c;
    private List<ISegmenter> d;
    private IKArbitrator e;

    public IKSegmenter(Reader reader, boolean z) {
        this.f4198a = reader;
        this.b = DefaultConfig.f();
        this.b.a(z);
        b();
    }

    public IKSegmenter(Reader reader, Configuration configuration) {
        this.f4198a = reader;
        this.b = configuration;
        b();
    }

    private void b() {
        Dictionary.a(this.b);
        this.c = new AnalyzeContext(this.b);
        this.d = c();
        this.e = new IKArbitrator();
    }

    private List<ISegmenter> c() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new LetterSegmenter());
        arrayList.add(new CN_QuantifierSegmenter());
        arrayList.add(new CJKSegmenter());
        return arrayList;
    }

    public synchronized Lexeme a() throws IOException {
        while (true) {
            Lexeme n = this.c.n();
            if (n != null) {
                return n;
            }
            if (this.c.a(this.f4198a) <= 0) {
                this.c.o();
                return null;
            }
            this.c.f();
            while (true) {
                for (ISegmenter a2 : this.d) {
                    a2.a(this.c);
                }
                if (!this.c.j()) {
                    if (!this.c.g()) {
                        break;
                    }
                } else {
                    break;
                }
            }
            for (ISegmenter a3 : this.d) {
                a3.a();
            }
            this.e.a(this.c, this.b.a());
            this.c.m();
            this.c.k();
        }
    }

    public synchronized void a(Reader reader) {
        this.f4198a = reader;
        this.c.o();
        for (ISegmenter a2 : this.d) {
            a2.a();
        }
    }
}
