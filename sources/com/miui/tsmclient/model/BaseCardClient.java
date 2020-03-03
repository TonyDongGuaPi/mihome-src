package com.miui.tsmclient.model;

import com.miui.tsmclient.net.TSMAuthManager;
import java.util.List;
import java.util.Vector;

public abstract class BaseCardClient {
    protected String mBussinessType = "";
    protected TSMAuthManager mTSMAuthManager = new TSMAuthManager();
    private List<BaseAppTask> mTaskList = new Vector();

    protected BaseCardClient() {
    }

    /* access modifiers changed from: protected */
    public BaseResponse executeTask(BaseAppTask baseAppTask) {
        this.mTaskList.add(baseAppTask);
        return baseAppTask.execute();
    }

    public void shutDown() {
        synchronized (this.mTaskList) {
            for (int i = 0; i < this.mTaskList.size(); i++) {
                this.mTaskList.get(i).terminate();
            }
            this.mTaskList.clear();
        }
    }
}
