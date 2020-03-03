package com.ximalaya.ting.android.opensdk.model.live.radio;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RadioListById extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Radio> f2091a;

    public List<Radio> a() {
        return this.f2091a;
    }

    public void a(List<Radio> list) {
        this.f2091a = list;
    }

    public String toString() {
        return "RadioListById [radios=" + this.f2091a + Operators.ARRAY_END_STR;
    }
}
