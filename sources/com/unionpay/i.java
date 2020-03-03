package com.unionpay;

import java.util.Comparator;
import org.json.JSONObject;

final class i implements Comparator {

    /* renamed from: a  reason: collision with root package name */
    String f9553a = "";

    i(String str) {
        this.f9553a = str;
    }

    public final /* synthetic */ int compare(Object obj, Object obj2) {
        long optLong = ((JSONObject) obj).optLong(this.f9553a);
        long optLong2 = ((JSONObject) obj2).optLong(this.f9553a);
        if (optLong < optLong2) {
            return -1;
        }
        return optLong > optLong2 ? 1 : 0;
    }
}
