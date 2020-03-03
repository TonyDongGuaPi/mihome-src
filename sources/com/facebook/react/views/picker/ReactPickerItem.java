package com.facebook.react.views.picker;

import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import java.util.ArrayList;
import java.util.List;

class ReactPickerItem {
    @Nullable
    public final Integer color;
    public final String label;

    public ReactPickerItem(ReadableMap readableMap) {
        this.label = readableMap.getString("label");
        if (!readableMap.hasKey("color") || readableMap.isNull("color")) {
            this.color = null;
        } else {
            this.color = Integer.valueOf(readableMap.getInt("color"));
        }
    }

    @Nullable
    public static List<ReactPickerItem> createFromJsArrayMap(ReadableArray readableArray) {
        if (readableArray == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(readableArray.size());
        for (int i = 0; i < readableArray.size(); i++) {
            arrayList.add(new ReactPickerItem(readableArray.getMap(i)));
        }
        return arrayList;
    }
}
