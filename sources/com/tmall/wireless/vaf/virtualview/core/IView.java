package com.tmall.wireless.vaf.virtualview.core;

public interface IView {
    void comLayout(int i, int i2, int i3, int i4);

    int getComMeasuredHeight();

    int getComMeasuredWidth();

    void measureComponent(int i, int i2);

    void onComLayout(boolean z, int i, int i2, int i3, int i4);

    void onComMeasure(int i, int i2);
}
