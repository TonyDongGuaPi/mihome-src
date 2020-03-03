package cn.com.fmsh.script.bean;

import cn.com.fmsh.communication.message.ITag;
import cn.com.fmsh.communication.message.exception.FMCommunicationMessageException;
import cn.com.fmsh.script.exception.FMScriptHandleException;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApduRequestList {
    final int FIRST_COMMAND_ID = 1;
    private Map<Integer, ApduRequest> list = new HashMap();
    private FMLog log = LogFactory.getInstance().getLog();
    private final String logTag = "ApduRequest";

    public byte[] toBytes() {
        return null;
    }

    public void fromTag(ITag iTag) throws FMCommunicationMessageException, FMScriptHandleException {
        if (iTag != null) {
            byte id = iTag.getId();
            if (!iTag.isValid()) {
                throw new FMCommunicationMessageException(String.format("根据解析工具提供的TAG[%X]解析请求指令时，TAG无效", new Object[]{Byte.valueOf(iTag.getId())}));
            } else if (id == -95) {
                int itemCount = iTag.getItemCount();
                for (int i = 0; i < itemCount; i++) {
                    ITag itemTagVal = iTag.getItemTagVal(i);
                    if (itemTagVal != null) {
                        ApduRequest apduRequest = new ApduRequest();
                        byte id2 = itemTagVal.getId();
                        apduRequest.setTag(id2);
                        if (id2 == -92) {
                            multipleHandle(itemTagVal.getItemTags(), apduRequest);
                        } else if (id2 == -96) {
                            singleHandle(itemTagVal.getBytesVal(), apduRequest);
                        } else {
                            this.list.clear();
                            throw new FMCommunicationMessageException(String.format("A1后面只能是A0或者A4，该Tag[%X]无效", new Object[]{Byte.valueOf(id2)}));
                        }
                        add(apduRequest);
                    }
                }
            } else if (id == -96) {
                ApduRequest apduRequest2 = new ApduRequest();
                apduRequest2.setTag(id);
                singleHandle(iTag.getBytesVal(), apduRequest2);
                add(apduRequest2);
            } else {
                throw new FMCommunicationMessageException(String.format("A0或者A1才能转换为下发脚本，TAG[%X]无效", new Object[]{Byte.valueOf(id)}));
            }
        } else {
            throw new FMCommunicationMessageException("TAG转换为指令集合时，TAG为空");
        }
    }

    private void singleHandle(byte[] bArr, ApduRequest apduRequest) {
        apduRequest.setId(bArr[0]);
        apduRequest.setApdu(Arrays.copyOfRange(bArr, 1, bArr.length - 2));
        byte[] copyOfRange = Arrays.copyOfRange(bArr, bArr.length - 2, bArr.length);
        apduRequest.addExpectationAndNext(new byte[]{copyOfRange[0], copyOfRange[1], 0});
    }

    private void multipleHandle(ITag[] iTagArr, ApduRequest apduRequest) throws FMCommunicationMessageException {
        for (ITag iTag : iTagArr) {
            byte id = iTag.getId();
            if (id != 60) {
                switch (id) {
                    case 56:
                        apduRequest.setId(iTag.getIntVal());
                        break;
                    case 57:
                        apduRequest.setApdu(iTag.getBytesVal());
                        break;
                }
            } else {
                byte[] bytesVal = iTag.getBytesVal();
                if (bytesVal != null) {
                    apduRequest.addExpectationAndNext(bytesVal);
                }
            }
        }
    }

    private void multipleHandle(byte[] bArr) throws IOException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
        while (true) {
            ApduRequest apduRequest = new ApduRequest();
            int read = byteArrayInputStream.read();
            if (read != -1) {
                apduRequest.setId(read);
                int read2 = byteArrayInputStream.read();
                if (read2 != -1) {
                    byte[] bArr2 = new byte[read2];
                    if (byteArrayInputStream.read(bArr2) != -1) {
                        apduRequest.setApdu(bArr2);
                        int read3 = byteArrayInputStream.read();
                        if (read3 != -1) {
                            if (read3 > 1) {
                                for (int i = 0; i < read3; i++) {
                                    byte[] bArr3 = new byte[3];
                                    byteArrayInputStream.read(bArr3);
                                    apduRequest.addExpectationAndNext(bArr3);
                                }
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        }
    }

    public int size() {
        return this.list.size();
    }

    public ApduRequest getFirstApduRequest() {
        return this.list.get(1);
    }

    public ApduRequest getApduRequest(int i) {
        return this.list.get(Integer.valueOf(i));
    }

    public boolean add(ApduRequest apduRequest) throws FMScriptHandleException {
        if (apduRequest == null) {
            return false;
        }
        if (this.list.size() >= 1 || 1 == apduRequest.getId()) {
            this.list.put(Integer.valueOf(apduRequest.getId()), apduRequest);
            return true;
        }
        throw new FMScriptHandleException("待执行的第一条指令的序列号不是1，复合脚本处理失败");
    }

    public void clear() {
        this.list.clear();
    }

    public ApduRequest[] getApduRequests() {
        ApduRequest[] apduRequestArr = new ApduRequest[this.list.size()];
        int i = 0;
        for (Integer num : this.list.keySet()) {
            apduRequestArr[i] = this.list.get(num);
            i++;
        }
        return apduRequestArr;
    }

    public List<byte[]> getApdus() {
        ArrayList arrayList = new ArrayList();
        for (Integer num : this.list.keySet()) {
            ApduRequest apduRequest = this.list.get(num);
            if (apduRequest != null) {
                arrayList.add(apduRequest.getApdu());
            }
        }
        return arrayList;
    }
}
