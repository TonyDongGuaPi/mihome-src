package com.mics.core.ui.page;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.mics.R;
import com.mics.widget.CategoryPop;
import com.mics.widget.StarViewGroup;

class ChatHumanScorePopHelper implements View.OnClickListener, PopupWindow.OnDismissListener {

    /* renamed from: a  reason: collision with root package name */
    public static final int f7727a = 1;
    public static final int b = 2;
    public static final int c = 3;
    private CategoryPop d;
    private View e;
    private OnSubmitListener f;
    private int g = 0;
    private CheckBox h;
    private CheckBox i;
    /* access modifiers changed from: private */
    public StarViewGroup j;
    /* access modifiers changed from: private */
    public TextView k;
    private EditText l;

    public interface OnSubmitListener {
        void a(int i);

        void a(int i, Score score);
    }

    ChatHumanScorePopHelper() {
    }

    /* access modifiers changed from: package-private */
    public void a(CategoryPop categoryPop) {
        this.d = categoryPop;
        this.e = categoryPop.a();
        a();
    }

    private void a() {
        this.h = (CheckBox) this.e.findViewById(R.id.resolve);
        this.i = (CheckBox) this.e.findViewById(R.id.unresolve);
        this.j = (StarViewGroup) this.e.findViewById(R.id.star);
        this.k = (TextView) this.e.findViewById(R.id.star_desc);
        this.l = (EditText) this.e.findViewById(R.id.comment_content);
        this.j.setOnStarChangeListener(new StarViewGroup.OnStarChangeListener() {
            public void a(int i) {
                if (ChatHumanScorePopHelper.this.j.getStarDesc() != null) {
                    ChatHumanScorePopHelper.this.k.setText(ChatHumanScorePopHelper.this.j.getStarDesc());
                } else {
                    ChatHumanScorePopHelper.this.k.setText("");
                }
            }
        });
        this.h.setChecked(true);
        this.i.setChecked(false);
        this.j.setCurrentStar(5);
        this.h.setOnClickListener(this);
        this.i.setOnClickListener(this);
        ((TextView) this.e.findViewById(R.id.tv_resolve)).setOnClickListener(this);
        ((TextView) this.e.findViewById(R.id.tv_unresolve)).setOnClickListener(this);
        ((TextView) this.e.findViewById(R.id.tv_comment_submit)).setOnClickListener(this);
        ((ImageView) this.e.findViewById(R.id.iv_pop_close)).setOnClickListener(this);
    }

    public void a(int i2) {
        this.g = i2;
    }

    public void a(OnSubmitListener onSubmitListener) {
        this.f = onSubmitListener;
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.resolve || id == R.id.tv_resolve) {
            this.h.setChecked(true);
            this.i.setChecked(false);
        } else if (id == R.id.unresolve || id == R.id.tv_unresolve) {
            this.h.setChecked(false);
            this.i.setChecked(true);
        } else if (id == R.id.tv_comment_submit) {
            this.d.c();
            if (this.f != null) {
                this.f.a(this.g, b());
            }
        } else if (id == R.id.iv_pop_close) {
            this.d.c();
        }
    }

    private Score b() {
        int currentStar = this.j.getCurrentStar();
        boolean isChecked = this.h.isChecked();
        String obj = this.l.getText().toString();
        Score score = new Score();
        score.c(isChecked ? "1" : "0");
        score.a(currentStar);
        score.a(obj);
        return score;
    }

    public void onDismiss() {
        if (this.f != null) {
            this.f.a(this.g);
        }
        this.g = 0;
    }

    public static class Score {

        /* renamed from: a  reason: collision with root package name */
        private String f7729a;
        private String b;
        private String c;
        private int d;

        public String a() {
            return this.f7729a;
        }

        public void a(String str) {
            this.f7729a = str;
        }

        public String b() {
            return this.b;
        }

        public void b(String str) {
            this.b = str;
        }

        public boolean c() {
            return TextUtils.equals("1", this.c);
        }

        public void c(String str) {
            this.c = str;
        }

        public int d() {
            return this.d;
        }

        public void a(int i) {
            this.d = i;
        }
    }
}
