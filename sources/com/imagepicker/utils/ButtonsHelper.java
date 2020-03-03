package com.imagepicker.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.xiaomi.market.sdk.Constants;
import java.util.LinkedList;
import java.util.List;

public class ButtonsHelper {
    @Nullable

    /* renamed from: a  reason: collision with root package name */
    public final Item f6068a;
    @Nullable
    public final Item b;
    @Nullable
    public final Item c;
    public final List<Item> d;

    public static class Item {

        /* renamed from: a  reason: collision with root package name */
        public final String f6069a;
        public final String b;

        public Item(@NonNull String str, @NonNull String str2) {
            this.f6069a = str;
            this.b = str2;
        }
    }

    public ButtonsHelper(@Nullable Item item, @Nullable Item item2, @Nullable Item item3, @NonNull LinkedList<Item> linkedList) {
        this.f6068a = item;
        this.b = item2;
        this.c = item3;
        this.d = linkedList;
    }

    public List<String> a() {
        LinkedList linkedList = new LinkedList();
        if (this.f6068a != null) {
            linkedList.add(this.f6068a.f6069a);
        }
        if (this.b != null) {
            linkedList.add(this.b.f6069a);
        }
        for (int i = 0; i < this.d.size(); i++) {
            linkedList.add(this.d.get(i).f6069a);
        }
        return linkedList;
    }

    public List<String> b() {
        LinkedList linkedList = new LinkedList();
        if (this.f6068a != null) {
            linkedList.add(this.f6068a.b);
        }
        if (this.b != null) {
            linkedList.add(this.b.b);
        }
        for (int i = 0; i < this.d.size(); i++) {
            linkedList.add(this.d.get(i).b);
        }
        return linkedList;
    }

    public static ButtonsHelper a(@NonNull ReadableMap readableMap) {
        return new ButtonsHelper(a(readableMap, "takePhotoButtonTitle", "photo"), a(readableMap, "chooseFromLibraryButtonTitle", Constants.C), a(readableMap, "cancelButtonTitle", "cancel"), b(readableMap));
    }

    @Nullable
    private static Item a(@NonNull ReadableMap readableMap, @NonNull String str, @NonNull String str2) {
        if (!ReadableMapUtils.b(readableMap, str)) {
            return null;
        }
        return new Item(readableMap.getString(str), str2);
    }

    @NonNull
    private static LinkedList<Item> b(@NonNull ReadableMap readableMap) {
        LinkedList<Item> linkedList = new LinkedList<>();
        if (!readableMap.hasKey("customButtons")) {
            return linkedList;
        }
        ReadableArray array = readableMap.getArray("customButtons");
        for (int i = 0; i < array.size(); i++) {
            ReadableMap map = array.getMap(i);
            linkedList.add(new Item(map.getString("title"), map.getString("name")));
        }
        return linkedList;
    }
}
