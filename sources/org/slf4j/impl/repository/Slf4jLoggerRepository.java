package org.slf4j.impl.repository;

import com.google.code.microlog4android.Level;
import com.google.code.microlog4android.repository.CommonLoggerRepository;
import com.google.code.microlog4android.repository.LoggerNamesUtil;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;
import org.slf4j.impl.MicrologLoggerAdapter;

public enum Slf4jLoggerRepository implements CommonLoggerRepository, ILoggerFactory {
    INSTANCE;
    
    private Hashtable<String, Slf4jRepositoryNode> leafNodeHashtable;
    private Slf4jRepositoryNode rootNode;

    public Logger getRootLogger() {
        return this.rootNode.a();
    }

    public synchronized Logger getLogger(String str) {
        MicrologLoggerAdapter micrologLoggerAdapter;
        Slf4jRepositoryNode slf4jRepositoryNode = this.leafNodeHashtable.get(str);
        if (slf4jRepositoryNode == null) {
            micrologLoggerAdapter = new MicrologLoggerAdapter(str);
            addLogger(micrologLoggerAdapter);
        } else {
            micrologLoggerAdapter = slf4jRepositoryNode.a();
        }
        return micrologLoggerAdapter;
    }

    /* access modifiers changed from: package-private */
    public void addLogger(MicrologLoggerAdapter micrologLoggerAdapter) {
        String name = micrologLoggerAdapter.getName();
        Slf4jRepositoryNode slf4jRepositoryNode = this.rootNode;
        String[] loggerNameComponents = LoggerNamesUtil.getLoggerNameComponents(name);
        for (String str : loggerNameComponents) {
            if (slf4jRepositoryNode.a(str) == null) {
                slf4jRepositoryNode = createNewChildNode(str, slf4jRepositoryNode);
            }
        }
        if (loggerNameComponents.length > 0) {
            Slf4jRepositoryNode slf4jRepositoryNode2 = new Slf4jRepositoryNode(LoggerNamesUtil.getClassName(loggerNameComponents), micrologLoggerAdapter, slf4jRepositoryNode);
            slf4jRepositoryNode.a(slf4jRepositoryNode2);
            this.leafNodeHashtable.put(name, slf4jRepositoryNode2);
        }
    }

    public void setLevel(String str, Level level) {
        Slf4jRepositoryNode slf4jRepositoryNode = this.leafNodeHashtable.get(str);
        if (slf4jRepositoryNode != null) {
            slf4jRepositoryNode.a().getMicrologLogger().setLevel(level);
            return;
        }
        Slf4jRepositoryNode slf4jRepositoryNode2 = this.rootNode;
        for (String str2 : LoggerNamesUtil.getLoggerNameComponents(str)) {
            if (slf4jRepositoryNode2.a(str2) == null) {
                slf4jRepositoryNode2 = createNewChildNode(str2, slf4jRepositoryNode2);
            }
        }
        if (slf4jRepositoryNode2 != null) {
            slf4jRepositoryNode2.a().getMicrologLogger().setLevel(level);
        }
    }

    private Slf4jRepositoryNode createNewChildNode(String str, Slf4jRepositoryNode slf4jRepositoryNode) {
        Slf4jRepositoryNode slf4jRepositoryNode2 = new Slf4jRepositoryNode(str, slf4jRepositoryNode);
        slf4jRepositoryNode.a(slf4jRepositoryNode2);
        return slf4jRepositoryNode2;
    }

    public Level getEffectiveLevel(String str) {
        Slf4jRepositoryNode slf4jRepositoryNode = this.leafNodeHashtable.get(str);
        Level level = null;
        while (level == null && slf4jRepositoryNode != null) {
            level = slf4jRepositoryNode.a().getMicrologLogger().getLevel();
            slf4jRepositoryNode = slf4jRepositoryNode.c();
        }
        return level;
    }

    public boolean contains(String str) {
        return this.leafNodeHashtable.containsKey(str);
    }

    public int numberOfLeafNodes() {
        return this.leafNodeHashtable.size();
    }

    public void reset() {
        this.rootNode.b();
        this.leafNodeHashtable.clear();
    }

    public void shutdown() {
        Enumeration<Slf4jRepositoryNode> elements = this.leafNodeHashtable.elements();
        while (elements.hasMoreElements()) {
            MicrologLoggerAdapter a2 = elements.nextElement().a();
            if (a2 != null) {
                try {
                    a2.getMicrologLogger().close();
                } catch (IOException unused) {
                }
            }
        }
    }
}
