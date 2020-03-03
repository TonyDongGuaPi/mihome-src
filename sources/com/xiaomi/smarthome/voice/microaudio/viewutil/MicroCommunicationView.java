package com.xiaomi.smarthome.voice.microaudio.viewutil;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.xiaomi.smarthome.R;

public class MicroCommunicationView extends LinearLayout {

    /* renamed from: a  reason: collision with root package name */
    private Context f22816a;
    private boolean b;
    TextView mCommunicationTV;
    LinearLayout mTipsContent;

    public MicroCommunicationView(Context context) {
        this(context, (AttributeSet) null);
    }

    public MicroCommunicationView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MicroCommunicationView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.b = true;
        this.f22816a = context;
        setOrientation(1);
    }

    /* access modifiers changed from: protected */
    public void onFinishInflate() {
        super.onFinishInflate();
        this.mCommunicationTV = (TextView) findViewById(R.id.micro_communication_tv);
        this.mTipsContent = (LinearLayout) findViewById(R.id.micro_tips_content);
    }

    public void show() {
        if (getVisibility() == 8) {
            setVisibility(0);
        }
        if (this.b) {
            this.mCommunicationTV.setVisibility(8);
            this.mCommunicationTV.setTextColor(getResources().getColor(R.color.white));
            this.mTipsContent.setVisibility(0);
            this.b = false;
            return;
        }
        if (this.mTipsContent.getVisibility() == 0) {
            this.mTipsContent.setVisibility(8);
        }
        this.mCommunicationTV.setVisibility(0);
        this.mCommunicationTV.setTextColor(getResources().getColor(R.color.white));
    }

    public void hide() {
        if (getVisibility() == 0) {
            setVisibility(8);
        }
    }
}
