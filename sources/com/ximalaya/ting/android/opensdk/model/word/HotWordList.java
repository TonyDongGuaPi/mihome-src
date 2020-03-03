package com.ximalaya.ting.android.opensdk.model.word;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class HotWordList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<HotWord> f2134a;

    public List<HotWord> a() {
        return this.f2134a;
    }

    public void a(List<HotWord> list) {
        this.f2134a = list;
    }

    public String toString() {
        return "HotWordList [hotWordList=" + this.f2134a + Operators.ARRAY_END_STR;
    }
}
