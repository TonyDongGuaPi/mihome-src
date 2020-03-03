package com.xiaomi.infra.galaxy.fds.android.model;

import com.mi.global.shop.model.Tags;

public class ThumbParam extends UserParam {
    public ThumbParam(int i, int i2) {
        this.f10145a.put(Tags.Kuwan.IMAGE_URL, "1");
        this.f10145a.put("w", Integer.toString(i));
        this.f10145a.put("h", Integer.toString(i2));
    }
}
