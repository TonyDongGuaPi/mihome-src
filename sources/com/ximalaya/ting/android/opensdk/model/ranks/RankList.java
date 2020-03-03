package com.ximalaya.ting.android.opensdk.model.ranks;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class RankList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Rank> f2112a;

    public List<Rank> a() {
        return this.f2112a;
    }

    public void a(List<Rank> list) {
        this.f2112a = list;
    }

    public String toString() {
        return "RankList{rankList=" + this.f2112a + Operators.BLOCK_END;
    }
}
