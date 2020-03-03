package cn.com.fmsh.communication.message;

import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.util.FM_Bytes;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TLVParse {
    public static TLVParse intance() {
        return new TLVParse();
    }

    public List<TagEntry> parse(byte[] bArr, int i) throws FMCommunicationMessageException {
        byte[] bArr2;
        ArrayList arrayList = new ArrayList();
        if (i < 1) {
            throw new FMCommunicationMessageException("TLV数据解析时，传入的T数据长度不合法");
        } else if (bArr == null || bArr.length < i + 1) {
            throw new FMCommunicationMessageException("TLV数据解析时，传入的数据不合法");
        } else {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            byte[] bArr3 = new byte[2];
            while (true) {
                byte[] bArr4 = new byte[i];
                try {
                    if (byteArrayInputStream.read(bArr4) == -1) {
                        break;
                    }
                    int read = byteArrayInputStream.read();
                    if (read < 0) {
                        break;
                    } else if (read != 0) {
                        if (read == 255) {
                            int read2 = byteArrayInputStream.read(bArr3);
                            byte[] bArr5 = {0, bArr3[0], bArr3[1]};
                            int bytesToInt = FM_Bytes.bytesToInt(bArr5);
                            if (read2 == -1) {
                                break;
                            }
                            bArr2 = bArr5;
                            read = bytesToInt;
                        } else {
                            bArr2 = new byte[]{(byte) read};
                        }
                        byte[] bArr6 = new byte[read];
                        byteArrayInputStream.read(bArr6);
                        arrayList.add(new TagEntry(bArr4, bArr2, bArr6));
                    }
                } catch (IOException unused) {
                }
            }
            return arrayList;
        }
    }

    public class TagEntry {
        private byte[] data;
        private byte[] lengthData;
        private byte[] tag;

        public TagEntry(byte[] bArr, byte[] bArr2, byte[] bArr3) {
            this.tag = bArr;
            this.lengthData = bArr2;
            this.data = bArr3;
        }

        public byte[] getTag() {
            return this.tag;
        }

        public byte[] getLengthData() {
            return this.lengthData;
        }

        public byte[] getData() {
            return this.data;
        }
    }

    public static void main(String[] strArr) {
        try {
            intance().parse(FM_Bytes.hexStringToBytes("A10100A206110100120101A30100"), 1);
        } catch (FMCommunicationMessageException e) {
            e.printStackTrace();
        }
    }
}
