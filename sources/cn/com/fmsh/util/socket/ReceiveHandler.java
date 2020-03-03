package cn.com.fmsh.util.socket;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;

public class ReceiveHandler {
    private static final int defaultTimeout = 5000;
    private static final String logTag = "ReceiveHandler";
    FMLog fmLog = null;
    private boolean isStop;

    private ReceiveHandler() {
    }

    public static ReceiveHandler instance() {
        return new ReceiveHandler();
    }

    public boolean isStop() {
        return this.isStop;
    }

    public void setStop(boolean z) {
        this.isStop = z;
    }

    public byte[] receive(DataLengthHandle dataLengthHandle, int i, DataInputStream dataInputStream) throws IOException {
        int i2 = 0;
        this.isStop = false;
        if (this.fmLog == null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (dataLengthHandle == null) {
            return receive(dataInputStream);
        }
        int dataSize = dataLengthHandle.getDataSize(dataInputStream);
        if (dataSize <= 0) {
            return receive(dataInputStream);
        }
        if (i <= 0) {
            i = 5000;
        }
        byte[] bArr = new byte[dataSize];
        long currentTimeMillis = System.currentTimeMillis();
        while (i2 < dataSize) {
            if (System.currentTimeMillis() - currentTimeMillis >= ((long) i)) {
                if (this.fmLog != null) {
                    this.fmLog.debug(logTag, "接受数据超时");
                }
                throw new IOException("在指定的时间[" + i + "]内未接收到指定长度的数据");
            }
            i2 += dataInputStream.read(bArr, i2, dataSize - i2);
        }
        return bArr;
    }

    public byte[] receive(DataInputStream dataInputStream) throws IOException {
        byte[] bArr = new byte[1024];
        int available = dataInputStream.available();
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            fMLog.debug(logTag, "DataInputStream available:" + available);
        }
        byte[] bArr2 = null;
        while (true) {
            int read = dataInputStream.read(bArr);
            if (read <= 0) {
                return bArr2;
            }
            if (this.fmLog != null) {
                FMLog fMLog2 = this.fmLog;
                fMLog2.debug(logTag, "in.read length:" + read);
            }
            if (bArr2 == null) {
                bArr2 = Arrays.copyOf(bArr, read);
            } else {
                FM_Bytes.join(bArr2, Arrays.copyOf(bArr, read));
            }
        }
    }

    public void cancel() {
        setStop(true);
    }
}
