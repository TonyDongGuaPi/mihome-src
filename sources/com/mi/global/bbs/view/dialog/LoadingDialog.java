package com.mi.global.bbs.view.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.widget.ImageView;
import com.mi.global.bbs.R;

public class LoadingDialog extends Dialog {
    public LoadingDialog(Context context) {
        super(context, R.style.myDialog);
        setContentView(R.layout.bbs_loading_dialog);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        AnimationDrawable animationDrawable = (AnimationDrawable) context.getResources().getDrawable(R.drawable.loading_dialog);
        ((ImageView) findViewById(R.id.loading_img)).setImageDrawable(animationDrawable);
        animationDrawable.start();
        setCanceledOnTouchOutside(false);
    }
}
