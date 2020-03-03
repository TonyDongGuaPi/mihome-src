package in.cashify.otex.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class DiagnoseHeaderView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    public View f2616a;

    public DiagnoseHeaderView(Context context) {
        super(context);
    }

    public DiagnoseHeaderView(Context context, int i) {
        super(context);
        a(context, i);
    }

    public void a(Context context, int i) {
        this.f2616a = LayoutInflater.from(context).inflate(i, this, true);
    }
}
