package com.xiaomi.smarthome.preload;

import android.content.res.Resources;
import android.os.Looper;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;

public class PreloadInflateCache {

    /* renamed from: a  reason: collision with root package name */
    private static final SparseArray<View> f21152a = new SparseArray<>();

    public static void a(LayoutInflater layoutInflater) {
        if (layoutInflater != null) {
            String[][] strArr = PreloadConstant.f21151a;
            int i = 0;
            while (i < strArr.length) {
                try {
                    a(layoutInflater, strArr[i]);
                    i++;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private static void a(LayoutInflater layoutInflater, String[] strArr) {
        Resources resources = layoutInflater.getContext().getResources();
        View view = null;
        View view2 = null;
        int i = 0;
        for (String identifier : strArr) {
            int identifier2 = resources.getIdentifier(identifier, (String) null, (String) null);
            if (view == null) {
                view = layoutInflater.inflate(identifier2, (ViewGroup) null);
                view2 = view;
                i = identifier2;
            } else {
                view2 = view2.findViewById(identifier2);
                if (view2 != null && (view2 instanceof ViewStub)) {
                    ((ViewStub) view2).inflate();
                }
            }
        }
        f21152a.put(i, view);
    }

    public static View a(int i) {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            return null;
        }
        View view = f21152a.get(i);
        if (view == null) {
            return view;
        }
        f21152a.remove(i);
        return view;
    }
}
