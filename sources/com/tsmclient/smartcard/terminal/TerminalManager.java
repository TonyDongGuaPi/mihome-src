package com.tsmclient.smartcard.terminal;

import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

public class TerminalManager {
    public static final String TERMINAL_CATEGORY_NFCEE = "com.miui.tsmclient";
    private static volatile TerminalManager sInstance;
    private Context mContext;
    private final Map<String, TerminalExtra> mTerminalInfoMap = new HashMap();

    public enum Priority {
        LOW,
        DEFAULT,
        HIGH
    }

    private TerminalManager() {
    }

    public void init(Context context) {
        this.mContext = context;
    }

    public static TerminalManager getInstance() {
        if (sInstance == null) {
            synchronized (TerminalManager.class) {
                if (sInstance == null) {
                    sInstance = new TerminalManager();
                }
            }
        }
        return sInstance;
    }

    public IScTerminal getTerminal(TerminalType terminalType) {
        return getTerminal((String) null, terminalType, Priority.DEFAULT, true);
    }

    public IScTerminal getTerminal(String str, TerminalType terminalType) {
        return getTerminal(str, terminalType, Priority.DEFAULT, true);
    }

    public IScTerminal getTerminal(String str, TerminalType terminalType, Priority priority, boolean z) {
        BaseTerminal baseTerminal;
        if (terminalType == TerminalType.PERIPHERAL) {
            baseTerminal = new PeripheralTerminal(this.mContext, str);
        } else if (terminalType == TerminalType.I2C) {
            baseTerminal = new I2CSmartMxTerminal(this.mContext);
        } else {
            baseTerminal = new SPISmartMxTerminal(this.mContext);
        }
        baseTerminal.setInterruptible(z);
        baseTerminal.mTerminalPriority = priority;
        return baseTerminal;
    }

    /* access modifiers changed from: package-private */
    public TerminalExtra getTerminalExtra(String str) {
        TerminalExtra terminalExtra;
        synchronized (this.mTerminalInfoMap) {
            terminalExtra = this.mTerminalInfoMap.get(str);
            if (terminalExtra == null) {
                terminalExtra = new TerminalExtra();
                this.mTerminalInfoMap.put(str, terminalExtra);
            }
        }
        return terminalExtra;
    }

    static class TerminalExtra {
        Semaphore mTermSemaphore = new Semaphore(1);
        IScTerminal mTerminal;
        Priority mTerminalPriority;

        TerminalExtra() {
        }
    }
}
