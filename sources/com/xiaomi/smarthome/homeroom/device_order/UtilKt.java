package com.xiaomi.smarthome.homeroom.device_order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a;\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032#\u0010\u0004\u001a\u001f\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0006\u0012\u0004\u0018\u00010\t0\u0005\u001a[\u0010\n\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u000bj\b\u0012\u0004\u0012\u0002H\u0002`\f\"\u0004\b\u0000\u0010\u0002*\u00020\u00012\u0016\u0010\r\u001a\u0012\u0012\u0004\u0012\u0002H\u00020\u000bj\b\u0012\u0004\u0012\u0002H\u0002`\f2!\u0010\u0004\u001a\u001d\u0012\u0013\u0012\u00110\t¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000e\u0012\u0004\u0012\u0002H\u00020\u0005\u001a\u0001\u0010\u000f\u001a\b\u0012\u0004\u0012\u0002H\u00110\u0010\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012*\b\u0012\u0004\u0012\u0002H\u00020\u00102\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00110\u00102\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00120\u000526\u0010\u0015\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0016\u001a \u0001\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u0002H\u00110\u000bj\b\u0012\u0004\u0012\u0002H\u0011`\f\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0011\"\u0004\b\u0002\u0010\u0012*\u0012\u0012\u0004\u0012\u0002H\u00020\u000bj\b\u0012\u0004\u0012\u0002H\u0002`\f2\u0016\u0010\u0013\u001a\u0012\u0012\u0004\u0012\u0002H\u00110\u000bj\b\u0012\u0004\u0012\u0002H\u0011`\f2\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00120\u000526\u0010\u0015\u001a2\u0012\u0013\u0012\u0011H\u0002¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u0011H\u0011¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u0017\u0012\u0004\u0012\u00020\u00180\u0016¨\u0006\u0019"}, d2 = {"mapToJsonArray", "Lorg/json/JSONArray;", "T", "", "mapper", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "t", "", "mapToList", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "dest", "el", "resort", "Ljava/util/List;", "R", "K", "orderlessList", "orderDistinctBy", "predicate", "Lkotlin/Function2;", "r", "", "app_GooglePlayRelease"}, k = 2, mv = {1, 1, 13})
public final class UtilKt {
    @NotNull
    public static final <T> ArrayList<T> a(@NotNull JSONArray jSONArray, @NotNull ArrayList<T> arrayList, @NotNull Function1<Object, ? extends T> function1) {
        Intrinsics.f(jSONArray, "receiver$0");
        Intrinsics.f(arrayList, "dest");
        Intrinsics.f(function1, "mapper");
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            Object obj = jSONArray.get(i);
            Intrinsics.b(obj, "get(index)");
            arrayList.add(function1.invoke(obj));
        }
        return arrayList;
    }

    @NotNull
    public static final <T> JSONArray a(@NotNull List<? extends T> list, @NotNull Function1<? super T, ? extends Object> function1) {
        Intrinsics.f(list, "receiver$0");
        Intrinsics.f(function1, "mapper");
        JSONArray jSONArray = new JSONArray();
        for (Object invoke : list) {
            jSONArray.put(function1.invoke(invoke));
        }
        return jSONArray;
    }

    @NotNull
    public static final <T, R, K> List<R> a(@NotNull List<T> list, @NotNull List<R> list2, @NotNull Function1<? super T, ? extends K> function1, @NotNull Function2<? super T, ? super R, Boolean> function2) {
        Object obj;
        Intrinsics.f(list, "receiver$0");
        Intrinsics.f(list2, "orderlessList");
        Intrinsics.f(function1, "orderDistinctBy");
        Intrinsics.f(function2, "predicate");
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        ArrayList arrayList2 = new ArrayList();
        for (Object next : list) {
            if (hashSet.add(function1.invoke(next))) {
                arrayList2.add(next);
            }
        }
        for (Object next2 : arrayList2) {
            Iterator it = list2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (function2.invoke(next2, obj).booleanValue()) {
                    break;
                }
            }
            if (obj != null) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList3 = new ArrayList(list2);
        Collection collection = arrayList;
        arrayList3.removeAll(collection);
        list2.clear();
        list2.addAll(collection);
        list2.addAll(arrayList3);
        return list2;
    }

    @NotNull
    public static final <T, R, K> ArrayList<R> a(@NotNull ArrayList<T> arrayList, @NotNull ArrayList<R> arrayList2, @NotNull Function1<? super T, ? extends K> function1, @NotNull Function2<? super T, ? super R, Boolean> function2) {
        Object obj;
        Intrinsics.f(arrayList, "receiver$0");
        Intrinsics.f(arrayList2, "orderlessList");
        Intrinsics.f(function1, "orderDistinctBy");
        Intrinsics.f(function2, "predicate");
        ArrayList arrayList3 = new ArrayList();
        HashSet hashSet = new HashSet();
        ArrayList arrayList4 = new ArrayList();
        for (Object next : arrayList) {
            if (hashSet.add(function1.invoke(next))) {
                arrayList4.add(next);
            }
        }
        for (Object next2 : arrayList4) {
            Iterator it = arrayList2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    obj = null;
                    break;
                }
                obj = it.next();
                if (function2.invoke(next2, obj).booleanValue()) {
                    break;
                }
            }
            if (obj != null) {
                arrayList3.add(obj);
            }
        }
        ArrayList arrayList5 = new ArrayList(arrayList2);
        Collection collection = arrayList3;
        arrayList5.removeAll(collection);
        arrayList2.clear();
        arrayList2.addAll(collection);
        arrayList2.addAll(arrayList5);
        return arrayList2;
    }
}
