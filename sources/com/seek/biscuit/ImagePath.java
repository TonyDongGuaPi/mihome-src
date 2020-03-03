package com.seek.biscuit;

public class ImagePath {

    /* renamed from: a  reason: collision with root package name */
    String f8811a;
    String b;
    String c;

    public ImagePath(String str) {
        this.f8811a = str;
        int lastIndexOf = str.lastIndexOf(".");
        int lastIndexOf2 = str.lastIndexOf("/");
        if (!(lastIndexOf == -1 || lastIndexOf2 == -1)) {
            this.c = str.substring(lastIndexOf2 + 1, lastIndexOf);
        }
        if (lastIndexOf != -1) {
            this.b = str.substring(lastIndexOf, str.length());
        }
    }
}
