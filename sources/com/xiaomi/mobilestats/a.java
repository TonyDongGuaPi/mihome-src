package com.xiaomi.mobilestats;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.mobilestats.common.CommonUtil;
import com.xiaomi.mobilestats.common.CrashHandler;
import com.xiaomi.mobilestats.common.HostManager;
import com.xiaomi.mobilestats.controller.LogController;
import com.xiaomi.mobilestats.upload.UploadManager;

final class a extends Handler {
    a(Looper looper) {
        super(looper);
    }

    public void handleMessage(Message message) {
        super.handleMessage(message);
        if (message.what == 4096) {
            CommonUtil.printLog(HostManager.TAG, "MSG_TIMER");
            LogController.copyOperatorFilesToUploaDir();
            if (UploadManager.isHasCacheFile()) {
                UploadManager.uploadCachedUploadFiles(StatService.handler);
            } else {
                CrashHandler.getInstance().clearCrashMD5List();
            }
        }
    }
}
