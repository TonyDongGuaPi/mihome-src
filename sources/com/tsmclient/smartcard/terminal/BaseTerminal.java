package com.tsmclient.smartcard.terminal;

import com.tsmclient.smartcard.terminal.TerminalManager;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public abstract class BaseTerminal implements IScTerminal {
    protected static ExecutorService mExecutor = Executors.newSingleThreadExecutor();
    protected boolean mInterruptible = true;
    protected String mTerminalCategory;
    protected TerminalManager.Priority mTerminalPriority;
    protected TerminalType mTerminalType;

    /* access modifiers changed from: protected */
    public void acquireLock() throws IOException, InterruptedException {
        if (this.mTerminalPriority == null) {
            this.mTerminalPriority = TerminalManager.Priority.DEFAULT;
        }
        TerminalManager.TerminalExtra terminalExtra = TerminalManager.getInstance().getTerminalExtra(this.mTerminalCategory);
        if ((terminalExtra.mTerminalPriority == null || this.mTerminalPriority.ordinal() > terminalExtra.mTerminalPriority.ordinal()) && terminalExtra.mTerminal != null) {
            terminalExtra.mTerminal.close();
        }
        if (this.mInterruptible) {
            terminalExtra.mTermSemaphore.acquire();
        } else {
            terminalExtra.mTermSemaphore.acquireUninterruptibly();
        }
        terminalExtra.mTerminal = this;
        terminalExtra.mTerminalPriority = this.mTerminalPriority;
    }

    /* access modifiers changed from: protected */
    public void releaseLock() {
        TerminalManager.TerminalExtra terminalExtra = TerminalManager.getInstance().getTerminalExtra(this.mTerminalCategory);
        if (terminalExtra.mTerminal == this) {
            terminalExtra.mTerminal = null;
            terminalExtra.mTermSemaphore.drainPermits();
            terminalExtra.mTermSemaphore.release();
        }
    }

    public TerminalType getTerminalType() {
        return this.mTerminalType;
    }

    public String getTerminalCategory() {
        return this.mTerminalCategory;
    }

    public boolean isInterruptible() {
        return this.mInterruptible;
    }

    public void setInterruptible(boolean z) {
        this.mInterruptible = z;
    }
}
