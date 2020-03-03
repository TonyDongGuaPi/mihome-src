package com.mics.core.ui.widget;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.drawable.ColorDrawable;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.mics.R;
import com.mics.widget.util.MiCSToastManager;
import com.xiaomi.youpin.share.model.ShareChannel;

public class LongPressMenu implements PopupWindow.OnDismissListener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public PopupWindow f7752a;
    /* access modifiers changed from: private */
    public View b;
    private int c;
    private int d;
    /* access modifiers changed from: private */
    public int e;
    /* access modifiers changed from: private */
    public int f;
    private int g;
    private int h;
    private String i = "复制";

    private LongPressMenu(View view, int i2, int i3) {
        this.b = view;
        this.c = i2;
        this.d = i3;
        this.g = (int) TypedValue.applyDimension(1, (float) ((this.i.length() * 13) + 64), view.getResources().getDisplayMetrics());
        this.h = (int) TypedValue.applyDimension(1, 45.0f, view.getResources().getDisplayMetrics());
    }

    public static void a(TextView textView, int i2, int i3) {
        LongPressMenu longPressMenu = new LongPressMenu(textView, i2, i3);
        textView.setOnLongClickListener(new View.OnLongClickListener(longPressMenu) {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ LongPressMenu f7753a;

            {
                this.f7753a = r1;
            }

            public boolean onLongClick(View view) {
                this.f7753a.a();
                return true;
            }
        });
        textView.setOnTouchListener(new View.OnTouchListener(longPressMenu) {

            /* renamed from: a  reason: collision with root package name */
            final /* synthetic */ LongPressMenu f7754a;

            {
                this.f7754a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                int unused = this.f7754a.e = (int) motionEvent.getX();
                int unused2 = this.f7754a.f = (int) motionEvent.getY();
                return false;
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        if (this.f7752a == null) {
            int applyDimension = (int) TypedValue.applyDimension(1, 16.0f, this.b.getResources().getDisplayMetrics());
            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(-2, -2);
            TextView textView = new TextView(this.b.getContext());
            textView.setLayoutParams(layoutParams);
            textView.setGravity(17);
            textView.setText(this.i);
            textView.setBackgroundResource(R.drawable.mics_menu_bg);
            textView.setTextSize(1, 13.0f);
            textView.setTextColor(-16777216);
            int i2 = applyDimension * 2;
            textView.setPadding(i2, applyDimension, i2, applyDimension);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (LongPressMenu.this.f7752a != null) {
                        LongPressMenu.this.f7752a.dismiss();
                        if (LongPressMenu.this.b instanceof TextView) {
                            CharSequence text = ((TextView) LongPressMenu.this.b).getText();
                            ClipboardManager clipboardManager = (ClipboardManager) LongPressMenu.this.b.getContext().getSystemService(ShareChannel.d);
                            if (clipboardManager != null) {
                                clipboardManager.setPrimaryClip(ClipData.newPlainText((CharSequence) null, text));
                                MiCSToastManager.a().a((CharSequence) "已复制");
                            }
                        }
                    }
                }
            });
            this.f7752a = new PopupWindow(textView, -2, -2, true);
            this.f7752a.setBackgroundDrawable(new ColorDrawable(0));
            this.f7752a.setOutsideTouchable(true);
            this.f7752a.setTouchable(true);
            this.f7752a.setOnDismissListener(this);
        }
        this.f7752a.showAsDropDown(this.b, ((int) (-(((float) this.g) / 2.0f))) + this.e, ((int) (-(((float) this.b.getHeight()) + (((float) (this.h * 3)) / 2.0f)))) + this.f);
        this.b.setBackgroundResource(this.d);
    }

    public void onDismiss() {
        this.b.setBackgroundResource(this.c);
    }
}
