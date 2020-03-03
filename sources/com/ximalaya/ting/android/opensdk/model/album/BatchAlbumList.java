package com.ximalaya.ting.android.opensdk.model.album;

import com.taobao.weex.el.parse.Operators;
import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.List;

public class BatchAlbumList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    private List<Album> f2015a;

    public List<Album> a() {
        return this.f2015a;
    }

    public void a(List<Album> list) {
        this.f2015a = list;
    }

    public String toString() {
        return "BatchAlbumList{albums=" + this.f2015a + Operators.BLOCK_END;
    }
}
