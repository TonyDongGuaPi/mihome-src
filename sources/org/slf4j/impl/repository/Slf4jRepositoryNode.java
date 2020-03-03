package org.slf4j.impl.repository;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.repository.AbstractRepositoryNode;
import java.util.Hashtable;
import org.slf4j.impl.MicrologLoggerAdapter;

public class Slf4jRepositoryNode extends AbstractRepositoryNode {

    /* renamed from: a  reason: collision with root package name */
    private Slf4jRepositoryNode f4180a;
    private Hashtable<String, Slf4jRepositoryNode> b = new Hashtable<>(17);
    private MicrologLoggerAdapter c;

    public Slf4jRepositoryNode(String str, MicrologLoggerAdapter micrologLoggerAdapter) {
        this.name = str;
        this.c = micrologLoggerAdapter;
    }

    public Slf4jRepositoryNode(String str, Slf4jRepositoryNode slf4jRepositoryNode) {
        this.name = str;
        this.f4180a = slf4jRepositoryNode;
        this.c = new MicrologLoggerAdapter(new Logger(str));
    }

    public Slf4jRepositoryNode(String str, MicrologLoggerAdapter micrologLoggerAdapter, Slf4jRepositoryNode slf4jRepositoryNode) {
        this.name = str;
        this.c = micrologLoggerAdapter;
        this.f4180a = slf4jRepositoryNode;
    }

    public void a(Slf4jRepositoryNode slf4jRepositoryNode) {
        this.b.put(slf4jRepositoryNode.getName(), slf4jRepositoryNode);
    }

    public MicrologLoggerAdapter a() {
        return this.c;
    }

    public Slf4jRepositoryNode a(String str) {
        return this.b.get(str);
    }

    public void b() {
        this.b.clear();
        this.c.getMicrologLogger().resetLogger();
        this.c.getMicrologLogger().setLevel(Level.DEBUG);
    }

    public Slf4jRepositoryNode c() {
        return this.f4180a;
    }

    public void b(Slf4jRepositoryNode slf4jRepositoryNode) {
        this.f4180a = slf4jRepositoryNode;
    }
}
