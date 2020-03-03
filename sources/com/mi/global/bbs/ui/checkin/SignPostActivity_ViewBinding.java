package com.mi.global.bbs.ui.checkin;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mi.global.bbs.R;
import com.mi.global.bbs.view.CheckedTextView;
import com.mi.global.bbs.view.Editor.FontEditText;

public class SignPostActivity_ViewBinding implements Unbinder {
    private SignPostActivity target;

    @UiThread
    public SignPostActivity_ViewBinding(SignPostActivity signPostActivity) {
        this(signPostActivity, signPostActivity.getWindow().getDecorView());
    }

    @UiThread
    public SignPostActivity_ViewBinding(SignPostActivity signPostActivity, View view) {
        this.target = signPostActivity;
        signPostActivity.mEmoContent = (FontEditText) Utils.findRequiredViewAsType(view, R.id.emo_content, "field 'mEmoContent'", FontEditText.class);
        signPostActivity.mShareSwitch = (CheckedTextView) Utils.findRequiredViewAsType(view, R.id.share_switch, "field 'mShareSwitch'", CheckedTextView.class);
        signPostActivity.mNumIndicate = (TextView) Utils.findRequiredViewAsType(view, R.id.num_indicate, "field 'mNumIndicate'", TextView.class);
        signPostActivity.mEmoIcon = (ImageView) Utils.findRequiredViewAsType(view, R.id.emo_icon, "field 'mEmoIcon'", ImageView.class);
    }

    @CallSuper
    public void unbind() {
        SignPostActivity signPostActivity = this.target;
        if (signPostActivity != null) {
            this.target = null;
            signPostActivity.mEmoContent = null;
            signPostActivity.mShareSwitch = null;
            signPostActivity.mNumIndicate = null;
            signPostActivity.mEmoIcon = null;
            return;
        }
        throw new IllegalStateException("Bindings already cleared.");
    }
}
