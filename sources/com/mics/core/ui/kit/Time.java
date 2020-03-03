package com.mics.core.ui.kit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.biubiu.kit.core.AbsKit;
import com.mics.R;

public class Time extends AbsKit {

    /* renamed from: a  reason: collision with root package name */
    private TextView f7701a;

    public View a(ViewGroup viewGroup) {
        Context context = viewGroup.getContext();
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-1, -2);
        View a2 = a(LayoutInflater.from(context), viewGroup, R.layout.mics_kit_time);
        a2.setLayoutParams(layoutParams);
        this.f7701a = (TextView) a2.findViewById(R.id.tv_time);
        return a2;
    }

    public void a(int i, Object obj) {
        if (obj instanceof Data) {
            this.f7701a.setText(((Data) obj).a());
        }
    }

    public static class Data {

        /* renamed from: a  reason: collision with root package name */
        private CharSequence f7702a;

        public CharSequence a() {
            return this.f7702a;
        }

        public void a(CharSequence charSequence) {
            this.f7702a = charSequence;
        }
    }
}
