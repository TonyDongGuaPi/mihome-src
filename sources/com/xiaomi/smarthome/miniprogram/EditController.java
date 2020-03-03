package com.xiaomi.smarthome.miniprogram;

import android.util.SparseBooleanArray;

public class EditController {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19999a = 0;
    public static final int b = 1;
    private static EditController e;
    public int c = 1;
    public SparseBooleanArray d = new SparseBooleanArray();
    private EditModeChangedListener f;

    private EditController() {
    }

    public static EditController a() {
        if (e == null) {
            e = new EditController();
        }
        return e;
    }

    public void a(EditModeChangedListener editModeChangedListener) {
        this.f = editModeChangedListener;
    }

    public void a(int i) {
        this.c = i;
        if (this.f != null) {
            switch (i) {
                case 0:
                    this.f.onManageMode();
                    return;
                case 1:
                    this.d.clear();
                    this.f.onBrowseMode();
                    return;
                default:
                    return;
            }
        }
    }

    public void b() {
        this.d.clear();
        this.f = null;
        if (e != null) {
            e = null;
        }
    }
}
