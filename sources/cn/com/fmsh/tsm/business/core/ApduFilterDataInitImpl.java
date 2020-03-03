package cn.com.fmsh.tsm.business.core;

import cn.com.fmsh.script.ApduFilterDataInit;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.script.core.FilterPolicy;
import java.util.ArrayList;
import java.util.List;

public class ApduFilterDataInitImpl implements ApduFilterDataInit {
    byte[][] aids = null;

    public ApduFilterDataInitImpl(byte[][] bArr) {
        this.aids = bArr;
    }

    public List<FilterPolicy> getFilterPolicies() {
        if (this.aids == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        FilterPolicy filterPolicy = new FilterPolicy();
        filterPolicy.setCla((byte) 0);
        filterPolicy.setIns(ScriptToolsConst.TagName.CommandMultiple);
        for (byte[] bArr : this.aids) {
            if (bArr != null) {
                filterPolicy.addFilterData(bArr);
            }
        }
        arrayList.add(filterPolicy);
        return arrayList;
    }
}
