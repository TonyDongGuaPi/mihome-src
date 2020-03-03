package com.mi.global.shop.imageselector.bean;

import android.text.TextUtils;
import java.util.ArrayList;

public class Folder {

    /* renamed from: a  reason: collision with root package name */
    public String f6927a;
    public String b;
    public Image c;
    public ArrayList<Image> d;

    public boolean equals(Object obj) {
        try {
            return TextUtils.equals(((Folder) obj).b, this.b);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }
}
