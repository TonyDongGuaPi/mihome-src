package com.ximalaya.ting.android.opensdk.model.announcer;

import com.ximalaya.ting.android.opensdk.datatrasfer.XimalayaResponse;
import java.util.ArrayList;
import java.util.List;

public class AnnouncerCategoryList extends XimalayaResponse {

    /* renamed from: a  reason: collision with root package name */
    List<AnnouncerCategory> f2037a = new ArrayList();

    public List<AnnouncerCategory> a() {
        return this.f2037a;
    }

    public void a(List<AnnouncerCategory> list) {
        this.f2037a = list;
    }
}
