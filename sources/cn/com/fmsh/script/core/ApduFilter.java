package cn.com.fmsh.script.core;

import cn.com.fmsh.util.FM_Bytes;
import cn.com.fmsh.util.log.FMLog;
import cn.com.fmsh.util.log.LogFactory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ApduFilter {
    private List<FilterPolicy> policies = new ArrayList();

    public void addFilterPolicy(FilterPolicy filterPolicy) {
        this.policies.add(filterPolicy);
    }

    public byte[] filter(byte[] bArr) {
        FMLog log = LogFactory.getInstance().getLog();
        if (bArr == null || bArr.length < 5) {
            if (log != null) {
                log.warn(ApduFilter.class.getName(), "请求过滤时，请求数据不合法");
            }
            return null;
        } else if (this.policies.size() < 1) {
            return null;
        } else {
            for (FilterPolicy next : this.policies) {
                if (next != null) {
                    if (next.getCla() == bArr[0] && next.getIns() == bArr[1]) {
                        byte[] copyOfRange = Arrays.copyOfRange(bArr, 5, bArr.length);
                        for (byte[] bArr2 : next.getFilterDatas()) {
                            if (Arrays.equals(bArr2, copyOfRange)) {
                                if (log != null) {
                                    log.debug(ApduFilter.class.getName(), "open获取的AID：" + FM_Bytes.bytesToHexString(bArr2));
                                }
                                return bArr2;
                            }
                        }
                        continue;
                    }
                }
            }
            return null;
        }
    }
}
