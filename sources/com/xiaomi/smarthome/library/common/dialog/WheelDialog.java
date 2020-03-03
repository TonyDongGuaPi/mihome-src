package com.xiaomi.smarthome.library.common.dialog;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.library.common.wheel.ArrayWheelAdapter;
import com.xiaomi.smarthome.library.common.wheel.OnWheelChangedListener;
import com.xiaomi.smarthome.library.common.wheel.WheelView;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WheelDialog extends MLAlertDialog {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18607a = 1900;
    private static final int[] b = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private WheelView c;
    private WheelView d;
    private WheelView e;
    private Button f;
    private Button g;
    private String[] h;
    private String[] i;
    private String[] j;
    private int k;
    /* access modifiers changed from: private */
    public View.OnClickListener l;
    private int m;
    /* access modifiers changed from: private */
    public View.OnClickListener n;
    private String o;

    public WheelDialog(Context context) {
        this(context, 2131559365);
    }

    public WheelDialog(Context context, int i2) {
        super(context, i2);
    }

    public void a(int i2, View.OnClickListener onClickListener) {
        this.k = i2;
        this.l = onClickListener;
    }

    public void b(int i2, View.OnClickListener onClickListener) {
        this.m = i2;
        this.n = onClickListener;
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        View inflate = LayoutInflater.from(this.mContext).inflate(R.layout.wheel_dialog, (ViewGroup) null);
        this.c = (WheelView) inflate.findViewById(R.id.year);
        this.d = (WheelView) inflate.findViewById(R.id.month);
        this.e = (WheelView) inflate.findViewById(R.id.day);
        this.j = XMStringUtils.b(SHApplication.getAppContext(), (int) R.array.month);
        this.h = a(1900, Calendar.getInstance().get(1));
        this.i = a(1, 31);
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter(this.h);
        ArrayWheelAdapter arrayWheelAdapter2 = new ArrayWheelAdapter(this.j);
        ArrayWheelAdapter arrayWheelAdapter3 = new ArrayWheelAdapter(this.i);
        this.c.setAdapter(arrayWheelAdapter);
        this.d.setAdapter(arrayWheelAdapter2);
        this.e.setAdapter(arrayWheelAdapter3);
        this.g = (Button) inflate.findViewById(R.id.button2);
        if (this.m > 0) {
            this.g.setText(this.m);
        }
        this.f = (Button) inflate.findViewById(R.id.button1);
        if (this.k > 0) {
            this.f.setText(this.k);
        }
        b(this.o);
        c();
        setView(inflate);
        super.onCreate(bundle);
    }

    /* access modifiers changed from: private */
    public void b() {
        int currentItem = this.e.getCurrentItem() + 1;
        int b2 = b(this.c.getCurrentItem() + 1900, this.d.getCurrentItem() + 1);
        if (currentItem > b2) {
            this.e.setCurrentItem(b2 - 1);
        }
        this.i = a(1, b2);
        this.e.setAdapter(new ArrayWheelAdapter(this.i));
    }

    public Calendar a() {
        Calendar instance = Calendar.getInstance();
        instance.set(this.c.getCurrentItem() + 1900, this.d.getCurrentItem(), this.e.getCurrentItem() + 1);
        return instance;
    }

    private void c() {
        this.c.addChangingListener(new OnWheelChangedListener() {
            public void a(WheelView wheelView, int i, int i2) {
                WheelDialog.this.b();
            }
        });
        this.d.addChangingListener(new OnWheelChangedListener() {
            public void a(WheelView wheelView, int i, int i2) {
                WheelDialog.this.b();
            }
        });
        this.e.addChangingListener(new OnWheelChangedListener() {
            public void a(WheelView wheelView, int i, int i2) {
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (WheelDialog.this.n != null) {
                    WheelDialog.this.n.onClick(view);
                }
                WheelDialog.this.dismiss();
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (WheelDialog.this.l != null) {
                    WheelDialog.this.l.onClick(view);
                }
                WheelDialog.this.dismiss();
            }
        });
    }

    public void a(String str) {
        this.o = str;
        b(str);
    }

    private void b(String str) {
        String[] split;
        if (!TextUtils.isEmpty(str) && this.c != null && this.d != null && this.e != null && (split = str.split("\\/")) != null && split.length == 3) {
            this.c.setCurrentItem(a(split[0], this.h), false);
            this.d.setCurrentItem(Integer.parseInt(split[1]) - 1, false);
            this.e.setCurrentItem(Integer.parseInt(split[2]) - 1, false);
            b();
        }
    }

    private int a(String str, String[] strArr) {
        if (TextUtils.isEmpty(str) || strArr == null) {
            return -1;
        }
        int length = strArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length && !str.equalsIgnoreCase(strArr[i2])) {
            i3++;
            i2++;
        }
        return i3;
    }

    private String[] a(int i2, int i3) {
        if (i2 > i3) {
            return null;
        }
        int i4 = (i3 - i2) + 1;
        String[] strArr = new String[i4];
        for (int i5 = 0; i5 < i4; i5++) {
            strArr[i5] = String.valueOf(i2 + i5);
        }
        return strArr;
    }

    private int b(int i2, int i3) {
        boolean isLeapYear = ((GregorianCalendar) Calendar.getInstance()).isLeapYear(i2);
        if (i3 != 2) {
            return b[i3 - 1];
        }
        return (isLeapYear ? 1 : 0) + b[i3 - 1];
    }
}
