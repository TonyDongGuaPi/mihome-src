package com.xiaomi.smarthome.voice.microaudio.viewutil;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.statistic.StatHelper;
import com.xiaomi.smarthome.library.common.util.StringUtil;

public class MicroCommonResultView extends RelativeLayout {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public Context f22812a;
    private TextView b;
    private ImageView c;
    private TextView d;
    View.OnClickListener mCallNowListener;

    public MicroCommonResultView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MicroCommonResultView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MicroCommonResultView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f22812a = context;
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.b = (TextView) findViewById(R.id.micro_content_result_tv);
        this.d = (TextView) findViewById(R.id.setting_tv_white);
        this.c = (ImageView) findViewById(R.id.micro_content_result_iv);
    }

    public void show(String str, int i, int i2) {
        setVisibility(0);
        this.b.setText(str);
        this.c.setImageResource(i);
        if (i2 == 0) {
            this.d.setVisibility(8);
        } else if (i2 == 1) {
            this.d.setVisibility(0);
            this.d.setText(StringUtil.a(this.f22812a, (int) R.string.mi_brain_go_setting, (int) R.string.mi_brain_no_connect_net_module, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(MicroCommonResultView.this.getResources().getColor(R.color.micro_calling_bg));
                    textPaint.setUnderlineText(false);
                }

                public void onClick(View view) {
                    MicroCommonResultView.this.f22812a.startActivity(new Intent("android.settings.SETTINGS"));
                }
            }));
            this.d.setMovementMethod(LinkMovementMethod.getInstance());
            this.d.setHighlightColor(0);
        } else if (i2 == 2) {
            this.d.setVisibility(0);
            this.d.setText(StringUtil.a(this.f22812a, (int) R.string.mi_brain_go_setting, (int) R.string.no_record_permission_module, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(MicroCommonResultView.this.getResources().getColor(R.color.micro_calling_bg));
                    textPaint.setUnderlineText(false);
                }

                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.addFlags(C.ENCODING_PCM_MU_LAW);
                    intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
                    intent.setData(Uri.fromParts("package", MicroCommonResultView.this.f22812a.getPackageName(), (String) null));
                    MicroCommonResultView.this.f22812a.startActivity(intent);
                }
            }));
            this.d.setMovementMethod(LinkMovementMethod.getInstance());
            this.d.setHighlightColor(0);
        } else if (i2 == 3) {
            this.d.setVisibility(0);
            this.d.setText(StringUtil.a(this.f22812a, (int) R.string.micro_call_now, (int) R.string.micro_call_again_module, (ClickableSpan) new ClickableSpan() {
                public void updateDrawState(TextPaint textPaint) {
                    textPaint.setColor(MicroCommonResultView.this.getResources().getColor(R.color.micro_calling_bg));
                    textPaint.setUnderlineText(false);
                }

                public void onClick(View view) {
                    if (MicroCommonResultView.this.mCallNowListener != null) {
                        MicroCommonResultView.this.mCallNowListener.onClick(view);
                        StatHelper.ax();
                    }
                }
            }));
            this.d.setMovementMethod(LinkMovementMethod.getInstance());
            this.d.setHighlightColor(0);
        }
    }

    public void hide() {
        setVisibility(8);
        this.b.setText("");
        this.d.setVisibility(8);
    }

    public void setCallNowListener(View.OnClickListener onClickListener) {
        this.mCallNowListener = onClickListener;
    }
}
