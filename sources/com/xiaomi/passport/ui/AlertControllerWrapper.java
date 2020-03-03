package com.xiaomi.passport.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.xiaomi.passport.ui.internal.AlertController;
import com.xiaomi.passport.ui.internal.AlertControllerImpl;
import java.util.ArrayList;

public class AlertControllerWrapper extends AlertController {
    AlertControllerImpl mAlertControllerImpl;

    public ListView getListView() {
        return null;
    }

    public void setIcon(int i) {
    }

    public void setInverseBackgroundForced(boolean z) {
    }

    public AlertControllerWrapper(Context context, DialogInterface dialogInterface, Window window) {
        super(context, dialogInterface, window);
        this.mAlertControllerImpl = new AlertControllerImpl(context, dialogInterface, window);
    }

    public AlertControllerImpl getImpl() {
        return this.mAlertControllerImpl;
    }

    public void installContent() {
        this.mAlertControllerImpl.installContent();
    }

    public void setTitle(CharSequence charSequence) {
        this.mAlertControllerImpl.setTitle(charSequence);
    }

    public void setCustomTitle(View view) {
        this.mAlertControllerImpl.setCustomTitle(view);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlertControllerImpl.setMessage(charSequence);
    }

    public void setView(View view) {
        this.mAlertControllerImpl.setView(view);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        this.mAlertControllerImpl.setButton(i, charSequence, onClickListener, message);
    }

    public Button getButton(int i) {
        return this.mAlertControllerImpl.getButton(i);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mAlertControllerImpl.onKeyDown(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mAlertControllerImpl.onKeyUp(i, keyEvent);
    }

    public boolean[] getCheckedItems() {
        return this.mAlertControllerImpl.getCheckedItems();
    }

    public static class AlertParams extends AlertController.AlertParams {
        public ArrayList<ActionItem> mActionItems;
        public boolean mEditMode;
        public DialogInterface.OnClickListener mOnActionItemClickListener;
        public DialogInterface.OnDismissListener mOnDismissListener;
        public DialogInterface.OnShowListener mOnShowListener;

        public static class ActionItem {
            public int icon;
            public int id;
            public CharSequence label;

            public ActionItem(CharSequence charSequence, int i, int i2) {
                this.label = charSequence;
                this.icon = i;
                this.id = i2;
            }
        }

        public AlertParams(Context context) {
            super(context);
        }

        public void apply(AlertController alertController) {
            if (this.mCustomTitleView != null) {
                alertController.setCustomTitle(this.mCustomTitleView);
            } else {
                if (this.mTitle != null) {
                    alertController.setTitle(this.mTitle);
                }
                if (this.mIconId >= 0) {
                    alertController.setIcon(this.mIconId);
                }
            }
            if (this.mMessage != null) {
                alertController.setMessage(this.mMessage);
            }
            if (this.mPositiveButtonText != null) {
                alertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, (Message) null);
            }
            if (this.mNegativeButtonText != null) {
                alertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, (Message) null);
            }
            if (this.mNeutralButtonText != null) {
                alertController.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, (Message) null);
            }
            if (this.mItems == null && this.mCursor == null) {
                ListAdapter listAdapter = this.mAdapter;
            }
            if (this.mView != null) {
                alertController.setView(this.mView);
            }
            if (this.mActionItems != null) {
                ((AlertControllerWrapper) alertController).getImpl().setActionItems(this.mActionItems, this.mOnActionItemClickListener);
            }
        }
    }
}
