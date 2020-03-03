package com.tmall.wireless.vaf.expr.engine.finder;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class ObjectFinderManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9359a = "ObjectFinderManager_TMTEST";
    private Map<String, ObjectFinder> b = new HashMap();

    public void a(String str, ObjectFinder objectFinder) {
        if (!TextUtils.isEmpty(str) && objectFinder != null) {
            this.b.put(str, objectFinder);
        }
    }

    public ObjectFinder a(String str) {
        if (!TextUtils.isEmpty(str)) {
            return this.b.get(str);
        }
        return null;
    }
}
