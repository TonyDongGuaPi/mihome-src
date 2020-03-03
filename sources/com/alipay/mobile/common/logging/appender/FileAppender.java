package com.alipay.mobile.common.logging.appender;

import android.util.Log;
import com.alipay.mobile.common.logging.api.LogContext;
import com.alipay.mobile.common.logging.api.LoggerFactory;
import com.alipay.mobile.common.logging.util.FileUtil;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.el.parse.Operators;
import java.io.File;

public class FileAppender extends Appender {
    protected static final String LOGFILE_PATH = "mdap";

    /* renamed from: a  reason: collision with root package name */
    private boolean f957a;
    private boolean b;
    protected String processName = LoggerFactory.getProcessInfo().getProcessName().replace(Operators.CONDITION_IF_MIDDLE, '-');
    protected File storageFile;

    public FileAppender(LogContext logContext, String str) {
        super(logContext, str);
    }

    /* access modifiers changed from: protected */
    public File getFile() {
        if (this.storageFile == null) {
            File file = new File(getContext().getFilesDir(), LOGFILE_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            String logCategory = getLogCategory();
            this.storageFile = new File(file, this.processName + JSMethod.NOT_SET + logCategory);
        }
        return this.storageFile;
    }

    public boolean onAppend(String str) {
        try {
            File file = getFile();
            if (file != null) {
                FileUtil.writeFile(file, str, true);
            }
            return true;
        } catch (Throwable th) {
            if (this.f957a) {
                return false;
            }
            this.f957a = true;
            Log.w(LOGFILE_PATH, th);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public boolean onAppend(byte[] bArr) {
        try {
            File file = getFile();
            if (file != null) {
                FileUtil.writeFile(file, bArr, true);
            }
            return true;
        } catch (Throwable th) {
            if (this.b) {
                return false;
            }
            this.b = true;
            Log.w(LOGFILE_PATH, th);
            return false;
        }
    }
}
