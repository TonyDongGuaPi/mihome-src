package com.xiaomi.jr.mipay.safekeyboard;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import com.xiaomi.jr.mipay.safekeyboard.KeyboardObserver;
import com.xiaomi.jr.mipay.safekeyboard.SafeKeyboard;
import com.xiaomi.jr.mipay.safekeyboard.utils.KeyCode;
import com.xiaomi.jr.mipay.safekeyboard.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class SafeKeyboardView extends ViewGroup implements View.OnClickListener, View.OnLongClickListener, View.OnTouchListener {

    /* renamed from: a  reason: collision with root package name */
    private SafeKeyboard f10986a;
    private KeyboardBarView b;
    private List<KeyView> c;
    private KeyboardObserver d;
    /* access modifiers changed from: private */
    public boolean e;
    /* access modifiers changed from: private */
    public boolean f;
    private View g;
    /* access modifiers changed from: private */
    public InputConnection h;
    private boolean i;
    /* access modifiers changed from: private */
    public OnKeyEventListener j;
    /* access modifiers changed from: private */
    public Handler k;
    private KeyboardObserver.KeyboardVisibilityListener l;
    private Runnable m;

    public interface OnKeyEventListener {
        void onKeyEvent(int i);
    }

    public SafeKeyboardView(Context context) {
        this(context, (AttributeSet) null);
    }

    public SafeKeyboardView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SafeKeyboardView(Context context, AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.i = true;
        this.k = new Handler(Looper.getMainLooper());
        this.l = new KeyboardObserver.KeyboardVisibilityListener() {
            public void a(boolean z) {
                if (z) {
                    boolean unused = SafeKeyboardView.this.f = true;
                    return;
                }
                boolean unused2 = SafeKeyboardView.this.f = false;
                if (SafeKeyboardView.this.e) {
                    SafeKeyboardView.this.b();
                    boolean unused3 = SafeKeyboardView.this.e = false;
                }
            }
        };
        this.m = new Runnable() {
            public void run() {
                if (SafeKeyboardView.this.h != null || SafeKeyboardView.this.j != null) {
                    if (SafeKeyboardView.this.h != null) {
                        SafeKeyboardView.this.h.sendKeyEvent(new KeyEvent(0, 67));
                        SafeKeyboardView.this.h.sendKeyEvent(new KeyEvent(1, 67));
                    }
                    if (SafeKeyboardView.this.j != null) {
                        SafeKeyboardView.this.j.onKeyEvent(KeyCode.e);
                    }
                    SafeKeyboardView.this.k.postDelayed(this, (long) ViewConfiguration.getKeyRepeatDelay());
                }
            }
        };
        this.b = new KeyboardBarView(context);
        this.c = new ArrayList();
        this.d = new KeyboardObserver(this);
        this.d.a(this.l);
    }

    /* access modifiers changed from: protected */
    public void onDetachedFromWindow() {
        this.d.a();
        super.onDetachedFromWindow();
    }

    public boolean isHideEnabled() {
        return this.i;
    }

    public void setKeyboard(SafeKeyboard safeKeyboard) {
        if (this.f10986a != safeKeyboard) {
            this.f10986a = safeKeyboard;
            a();
        }
    }

    private void a() {
        removeAllViews();
        this.c.clear();
        if (this.f10986a != null) {
            this.b.setTitle(this.f10986a.d());
            this.b.setOnClickListener(this);
            addView(this.b);
            ArrayList<SafeKeyboard.Row> a2 = this.f10986a.a();
            if (a2 != null) {
                Context context = getContext();
                for (SafeKeyboard.Row a3 : a2) {
                    ArrayList<SafeKeyboard.Key> a4 = a3.a();
                    if (a4 != null) {
                        for (SafeKeyboard.Key next : a4) {
                            KeyView keyView = new KeyView(context);
                            if (!TextUtils.isEmpty(next.f)) {
                                keyView.setLabel(next.f, next.c, next.d);
                            } else if (next.g != null) {
                                keyView.setIcon(next.g);
                            }
                            if (next.b != -1) {
                                keyView.setBackgroundResource(next.b);
                            }
                            keyView.setEnabled(next.e);
                            keyView.setTag(next);
                            keyView.setClickable(true);
                            keyView.setOnTouchListener(this);
                            if (next.f10978a == KeyCode.e) {
                                keyView.setOnLongClickListener(this);
                            }
                            this.c.add(keyView);
                            addView(keyView);
                        }
                    }
                }
            }
        }
        requestLayout();
    }

    /* access modifiers changed from: protected */
    public void onMeasure(int i2, int i3) {
        int size;
        if (this.f10986a == null || (size = this.c.size()) <= 0) {
            super.onMeasure(i2, i3);
            return;
        }
        this.b.measure(this.f10986a.c() | 1073741824, this.f10986a.e() | 1073741824);
        for (int i4 = 0; i4 < size; i4++) {
            View view = this.c.get(i4);
            SafeKeyboard.Key key = (SafeKeyboard.Key) view.getTag();
            if (key != null) {
                view.measure(key.h | 1073741824, key.i | 1073741824);
            }
        }
        setMeasuredDimension(this.f10986a.c(), this.f10986a.b());
    }

    /* access modifiers changed from: protected */
    public void onLayout(boolean z, int i2, int i3, int i4, int i5) {
        if (this.f10986a != null) {
            this.b.layout(0, 0, this.f10986a.c(), this.f10986a.e());
            int size = this.c.size();
            for (int i6 = 0; i6 < size; i6++) {
                View view = this.c.get(i6);
                SafeKeyboard.Key key = (SafeKeyboard.Key) view.getTag();
                if (key != null) {
                    view.layout(key.k, key.l, key.k + key.h, key.l + key.i);
                }
            }
        }
    }

    public void onClick(View view) {
        if (view == this.b) {
            hide();
        }
    }

    public boolean onLongClick(View view) {
        SafeKeyboard.Key key;
        if ((this.j == null && this.h == null) || (key = (SafeKeyboard.Key) view.getTag()) == null || key.f10978a != KeyCode.e) {
            return true;
        }
        this.k.post(this.m);
        return true;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        SafeKeyboard.Key key;
        if (!((this.h == null && this.j == null) || (key = (SafeKeyboard.Key) view.getTag()) == null)) {
            KeyEvent keyEvent = null;
            int action = motionEvent.getAction();
            if (action == 0) {
                keyEvent = new KeyEvent(0, KeyCode.a(key.f10978a));
            } else if (action == 1) {
                keyEvent = new KeyEvent(1, KeyCode.a(key.f10978a));
                if (this.j != null) {
                    this.j.onKeyEvent(key.f10978a);
                }
                if (key.f10978a == KeyCode.e) {
                    this.k.removeCallbacks(this.m);
                }
            }
            if (!(this.h == null || keyEvent == null)) {
                this.h.sendKeyEvent(keyEvent);
            }
        }
        return false;
    }

    public void hide() {
        setVisibility(8);
    }

    /* access modifiers changed from: private */
    public void b() {
        setVisibility(0);
        setTranslationY(0.0f);
        if (this.g != null) {
            this.g.requestFocus();
        }
    }

    public void requestShow(View view) {
        if (this.g != view || !isShown()) {
            if (this.g != view) {
                this.g = view;
                this.h = view != null ? view.onCreateInputConnection(new EditorInfo()) : null;
            }
            if (!this.f || view == null) {
                b();
                return;
            }
            this.e = true;
            Utils.a(view.getContext(), view, false);
        }
    }

    /* access modifiers changed from: protected */
    public void onWindowVisibilityChanged(int i2) {
        if (i2 == 0 && this.g != null) {
            this.g.requestLayout();
        }
        super.onWindowVisibilityChanged(i2);
    }

    public SafeKeyboard getKeyboard() {
        return this.f10986a;
    }

    public void setEnableKey(int i2, boolean z) {
        for (KeyView next : this.c) {
            SafeKeyboard.Key key = (SafeKeyboard.Key) next.getTag();
            if (key != null && key.f10978a == i2) {
                next.setEnabled(z);
                return;
            }
        }
    }

    public void setOnKeyEventListener(OnKeyEventListener onKeyEventListener) {
        this.j = onKeyEventListener;
    }
}
