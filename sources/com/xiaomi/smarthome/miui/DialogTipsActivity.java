package com.xiaomi.smarthome.miui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.StringUtil;
import com.xiaomi.smarthome.miio.activity.AboutActivity;

public class DialogTipsActivity extends BaseActivity {
    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.find_device_tips);
        TextView textView = (TextView) findViewById(R.id.goto_setting);
        textView.setText(StringUtil.a((Context) this, (int) R.string.goto_setting, (int) R.string.goto_setting, (ClickableSpan) new ClickableSpan() {
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(DialogTipsActivity.this.getResources().getColor(R.color.black_40_transparent));
                textPaint.setUnderlineText(true);
            }

            public void onClick(View view) {
                DialogTipsActivity.this.startActivity(new Intent(DialogTipsActivity.this.getContext(), AboutActivity.class));
                DialogTipsActivity.this.finish();
                DialogTipsActivity.this.overridePendingTransition(0, 0);
            }
        }));
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setHighlightColor(0);
        findViewById(R.id.dialog_container).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogTipsActivity.this.finish();
                DialogTipsActivity.this.overridePendingTransition(0, 0);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                DialogTipsActivity.this.finish();
                DialogTipsActivity.this.overridePendingTransition(0, 0);
            }
        });
    }
}
