package cn.com.fmsh.communication.core;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import cn.com.fmsh.util.socket.DataLengthHandle;
import java.io.DataInputStream;
import java.io.IOException;

public class NFCosDataLengthHandler implements DataLengthHandle {
    private static final int DATA_SIZE_LENGTH = 4;
    private FMLog fmLog = null;
    private String logTag = NFCosDataLengthHandler.class.getName();

    public int getDataSize(DataInputStream dataInputStream) throws IOException {
        byte readByte;
        if (this.fmLog != null) {
            this.fmLog = LogFactory.getInstance().getLog();
        }
        if (dataInputStream == null) {
            return -1;
        }
        int available = dataInputStream.available();
        if (this.fmLog != null) {
            FMLog fMLog = this.fmLog;
            String str = this.logTag;
            fMLog.debug(str, "DataInputStream available:" + available);
        }
        byte readByte2 = dataInputStream.readByte();
        byte readByte3 = dataInputStream.readByte();
        while (true) {
            if (readByte2 != (readByte3 ^ -1)) {
                readByte = dataInputStream.readByte();
            } else {
                byte readByte4 = dataInputStream.readByte();
                byte readByte5 = dataInputStream.readByte();
                if (readByte3 == (readByte4 ^ readByte5)) {
                    byte[] bArr = new byte[4];
                    bArr[2] = readByte4;
                    bArr[3] = readByte5;
                    return FM_Bytes.bytesToInt(bArr);
                }
                readByte = dataInputStream.readByte();
            }
            byte b = readByte3;
            readByte3 = readByte;
            readByte2 = b;
        }
    }

    public byte[] initDataSize(int i) {
        byte[] bArr = new byte[4];
        byte[] intToBytes = FM_Bytes.intToBytes(i, 2);
        bArr[1] = (byte) (intToBytes[0] ^ intToBytes[1]);
        bArr[0] = (byte) (bArr[1] ^ -1);
        bArr[2] = intToBytes[0];
        bArr[3] = intToBytes[1];
        return bArr;
    }
}
