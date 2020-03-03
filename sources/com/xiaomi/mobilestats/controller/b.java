package com.xiaomi.mobilestats.controller;

import com.xiaomi.mobilestats.data.SendStrategyEnum;
import com.xiaomi.mobilestats.upload.UploadManager;

class b implements Runnable {
    final /* synthetic */ LogController J;

    b(LogController logController) {
        this.J = logController;
    }

    public void run() {
        if (this.J.sendStragegy.equals(SendStrategyEnum.REAL_TIME) && UploadManager.isHasCacheFile()) {
            UploadManager.uploadCachedUploadFiles(LogController.handler);
        }
    }
}
