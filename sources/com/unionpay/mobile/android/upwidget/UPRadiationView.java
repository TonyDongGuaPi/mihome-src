package com.unionpay.mobile.android.upwidget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import com.unionpay.mobile.android.global.b;
import com.unionpay.mobile.android.utils.g;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UPRadiationView extends View {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public List<a> f9711a;
    private int b;
    private int c;
    private Context d;
    /* access modifiers changed from: private */
    public Handler e;

    private class a {

        /* renamed from: a  reason: collision with root package name */
        Paint f9712a;
        int b;
        float c;
        int d;

        private a() {
        }

        /* synthetic */ a(UPRadiationView uPRadiationView, byte b2) {
            this();
        }
    }

    public UPRadiationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public UPRadiationView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = context;
        this.f9711a = Collections.synchronizedList(new ArrayList());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        DisplayMetrics displayMetrics2 = new DisplayMetrics();
        ((Activity) this.d).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics2);
        int i2 = displayMetrics2.heightPixels;
        if (i <= 0 || i2 <= 0) {
            throw new RuntimeException("size illegal");
        }
        this.b = i / 2;
        this.c = (i2 / 2) - b.n;
        this.e = new t(this);
        this.e.sendEmptyMessage(0);
    }

    private static Paint a(int i, float f) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(f);
        paint.setStyle(Paint.Style.FILL);
        paint.setAlpha(i);
        paint.setColor(-1);
        return paint;
    }

    static /* synthetic */ void a(UPRadiationView uPRadiationView) {
        if (uPRadiationView.f9711a == null) {
            return;
        }
        if (uPRadiationView.f9711a.size() == 0) {
            a aVar = new a(uPRadiationView, (byte) 0);
            aVar.b = 0;
            aVar.d = 255;
            aVar.c = (float) (aVar.b / 4);
            aVar.f9712a = a(aVar.d, aVar.c);
            uPRadiationView.f9711a.add(aVar);
            return;
        }
        for (int i = 0; i < uPRadiationView.f9711a.size(); i++) {
            a aVar2 = uPRadiationView.f9711a.get(i);
            if (aVar2.d == 0) {
                uPRadiationView.f9711a.remove(i);
                aVar2.f9712a = null;
            } else {
                aVar2.b += 10;
                aVar2.d -= 4;
                if (aVar2.d < 0) {
                    aVar2.d = 0;
                }
                aVar2.c = (float) (aVar2.b / 4);
                aVar2.f9712a.setAlpha(aVar2.d);
                aVar2.f9712a.setStrokeWidth(aVar2.c);
                if (aVar2.b == g.a(uPRadiationView.d, 60.0f)) {
                    a aVar3 = new a(uPRadiationView, (byte) 0);
                    aVar3.b = 0;
                    aVar3.d = 255;
                    aVar3.c = (float) (aVar3.b / 4);
                    aVar3.f9712a = a(aVar3.d, aVar3.c);
                    uPRadiationView.f9711a.add(aVar3);
                }
            }
        }
    }

    public final void a() {
        this.d = null;
        this.e.removeCallbacksAndMessages((Object) null);
        this.e = null;
        if (this.f9711a != null) {
            this.f9711a.clear();
        }
        this.f9711a = null;
    }

    /* access modifiers changed from: protected */
    public void onDraw(Canvas canvas) {
        for (int i = 0; i < this.f9711a.size(); i++) {
            a aVar = this.f9711a.get(i);
            canvas.drawCircle((float) this.b, (float) this.c, (float) aVar.b, aVar.f9712a);
        }
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }
}
