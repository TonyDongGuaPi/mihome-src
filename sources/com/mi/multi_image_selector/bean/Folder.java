package com.mi.multi_image_selector.bean;

import android.text.TextUtils;
import java.util.List;

public class Folder {

    /* renamed from: a  reason: collision with root package name */
    public String f7395a;
    public String b;
    public Image c;
    public List<Image> d;

    public boolean equals(Object obj) {
        try {
            return TextUtils.equals(((Folder) obj).b, this.b);
        } catch (ClassCastException e) {
            e.printStackTrace();
            return super.equals(obj);
        }
    }
}
