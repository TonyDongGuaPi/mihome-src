package com.xiaomi.payment.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.mibi.common.base.BaseFragment;
import com.mibi.common.component.AutoCountDownButton;
import com.mibi.common.data.Utils;
import com.xiaomi.payment.platform.R;

public class PromptFragment extends BaseFragment {
    private static final String t = "promptFragment";
    private static final int u = 3;
    /* access modifiers changed from: private */
    public AutoCountDownButton v;
    private AutoCountDownButton.CountDownListener w = new AutoCountDownButton.CountDownListener() {
        public void a() {
            PromptFragment.this.v.setText(PromptFragment.this.getString(R.string.mibi_prompt_info_button_hint, new Object[]{3}));
        }

        public void a(int i) {
            PromptFragment.this.v.setText(PromptFragment.this.getString(R.string.mibi_prompt_info_button_hint, new Object[]{Integer.valueOf(i)}));
        }

        public void b() {
            PromptFragment.this.v.setText(PromptFragment.this.getString(R.string.mibi_prompt_info_button_text));
            PromptFragment.this.v.setTextColor(PromptFragment.this.getResources().getColor(R.color.mibi_text_color_prompt_button));
            PromptFragment.this.v.setEnabled(true);
        }
    };
    private View.OnClickListener x = new View.OnClickListener() {
        public void onClick(View view) {
            Utils.b((Context) PromptFragment.this.f7451a, PromptFragment.t, false);
            PromptFragment.this.c(-1);
            PromptFragment.this.E();
        }
    };

    public View a(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.mibi_prompt, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.promt_message)).setText(Html.fromHtml(getResources().getString(R.string.mibi_prompt_info_for_first_use)));
        this.v = (AutoCountDownButton) inflate.findViewById(R.id.button_count);
        this.v.setOnClickListener(this.x);
        return inflate;
    }

    public void c(Bundle bundle) {
        super.c(bundle);
        b(false);
        d(false);
        this.v.setText(getString(R.string.mibi_prompt_info_button_hint, new Object[]{3}));
        this.v.setTickNum(3);
        this.v.setEnabled(false);
        this.v.startTick();
        this.v.setCountDownListener(this.w);
    }

    public void C() {
        this.v.setCountDownListener((AutoCountDownButton.CountDownListener) null);
        super.C();
    }

    public static boolean a(Context context) {
        return Utils.a(context, t, true);
    }
}
