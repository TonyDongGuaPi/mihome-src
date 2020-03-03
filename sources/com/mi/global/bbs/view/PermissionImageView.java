package com.mi.global.bbs.view;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import com.mi.global.bbs.R;
import com.mi.global.bbs.adapter.SimplePermissionAdapter;
import com.mi.global.bbs.utils.DialogFactory;

public class PermissionImageView extends ImageView implements View.OnClickListener {
    private static final int PERMISSION_DENIED = 3;
    private static final int PERMISSION_FRIENDS = 1;
    private static final int PERMISSION_VISIBLE = 0;
    private int permissionState;

    public PermissionImageView(Context context) {
        this(context, (AttributeSet) null);
    }

    public PermissionImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.permissionState = 0;
        setPermissionState(0);
        setClickable(true);
        setOnClickListener(this);
    }

    public int getPermissionState() {
        return this.permissionState;
    }

    public void setPermissionState(int i) {
        this.permissionState = i;
        if (i != 3) {
            switch (i) {
                case 0:
                    setImageResource(R.drawable.bbs_permission_visible);
                    return;
                case 1:
                    setImageResource(R.drawable.bbs_permission_freinds);
                    return;
                default:
                    return;
            }
        } else {
            setImageResource(R.drawable.bbs_permission_disable);
        }
    }

    public void onClick(View view) {
        int i = this.permissionState;
        if (i == 3) {
            i--;
        }
        SimplePermissionAdapter simplePermissionAdapter = new SimplePermissionAdapter(getContext(), i);
        final AlertDialog showListDialog = DialogFactory.showListDialog(getContext(), simplePermissionAdapter);
        simplePermissionAdapter.setOnPermissionItemClickListener(new SimplePermissionAdapter.OnPermissionItemClickListener() {
            public void onClick(int i) {
                if (i == 2) {
                    PermissionImageView.this.setPermissionState(3);
                } else {
                    PermissionImageView.this.setPermissionState(i);
                }
                showListDialog.dismiss();
            }
        });
    }
}
