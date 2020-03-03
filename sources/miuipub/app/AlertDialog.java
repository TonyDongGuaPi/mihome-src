package miuipub.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Message;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.miuipub.internal.variable.AlertControllerWrapper;
import java.util.ArrayList;
import miuipub.v6.R;

public class AlertDialog extends Dialog implements DialogInterface {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2921a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 7;
    /* access modifiers changed from: private */
    public AlertControllerWrapper g;

    protected AlertDialog(Context context) {
        this(context, a(context, 0));
    }

    protected AlertDialog(Context context, int i) {
        super(context, a(context, i));
        this.g = new AlertControllerWrapper(context, this, getWindow());
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.g.installContent();
    }

    public ActionBar a() {
        return this.g.getImpl().l();
    }

    static int a(Context context, int i) {
        switch (i) {
            case 2:
            case 4:
            case 6:
                return 0;
            case 3:
                return R.style.V6_Theme_Light_Dialog_Alert;
            case 5:
                return R.style.V6_Theme_Light_Dialog_Edit;
            case 7:
                return R.style.V6_Theme_Light_Dialog_Edit_Default;
            default:
                if (i >= 16777216) {
                    return i;
                }
                TypedValue typedValue = new TypedValue();
                context.getTheme().resolveAttribute(16843529, typedValue, true);
                return typedValue.resourceId;
        }
    }

    public Button a(int i) {
        return this.g.getButton(i);
    }

    public ListView b() {
        return this.g.getListView();
    }

    public void setTitle(CharSequence charSequence) {
        super.setTitle(charSequence);
        this.g.setTitle(charSequence);
    }

    public void a(View view) {
        this.g.setCustomTitle(view);
    }

    public void a(CharSequence charSequence) {
        this.g.setMessage(charSequence);
    }

    public TextView c() {
        return this.g.getMessageView();
    }

    public void b(View view) {
        this.g.setView(view);
    }

    public void a(int i, CharSequence charSequence, Message message) {
        this.g.setButton(i, charSequence, (DialogInterface.OnClickListener) null, message);
    }

    public void a(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
        this.g.setButton(i, charSequence, onClickListener, (Message) null);
    }

    public boolean[] d() {
        return this.g.getCheckedItems();
    }

    public boolean e() {
        return this.g.isChecked();
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.g.onKeyUp(i, keyEvent) || super.onKeyUp(i, keyEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.g.onKeyDown(i, keyEvent) || super.onKeyDown(i, keyEvent);
    }

    public static class Builder {

        /* renamed from: a  reason: collision with root package name */
        private AlertControllerWrapper.AlertParams f2922a;
        private int b;

        public Builder(Context context) {
            this(context, AlertDialog.a(context, 0));
        }

        public Builder(Context context, int i) {
            this.f2922a = new AlertControllerWrapper.AlertParams(new ContextThemeWrapper(context, AlertDialog.a(context, i)));
            this.f2922a.mEditMode = i >= 4;
            this.b = i;
        }

        public Context a() {
            return this.f2922a.mContext;
        }

        public Builder a(int i) {
            this.f2922a.mTitle = this.f2922a.mContext.getText(i);
            return this;
        }

        public Builder a(CharSequence charSequence) {
            this.f2922a.mTitle = charSequence;
            return this;
        }

        public Builder a(View view) {
            this.f2922a.mCustomTitleView = view;
            return this;
        }

        public Builder b(int i) {
            this.f2922a.mMessage = this.f2922a.mContext.getText(i);
            return this;
        }

        public Builder b(CharSequence charSequence) {
            this.f2922a.mMessage = charSequence;
            return this;
        }

        public Builder a(boolean z, CharSequence charSequence) {
            this.f2922a.mIsChecked = z;
            this.f2922a.mCheckBoxMessage = charSequence;
            return this;
        }

        public Builder a(int i, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mPositiveButtonText = this.f2922a.mContext.getText(i);
            this.f2922a.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder a(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mPositiveButtonText = charSequence;
            this.f2922a.mPositiveButtonListener = onClickListener;
            return this;
        }

        public Builder b(int i, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mNegativeButtonText = this.f2922a.mContext.getText(i);
            this.f2922a.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder b(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mNegativeButtonText = charSequence;
            this.f2922a.mNegativeButtonListener = onClickListener;
            return this;
        }

        public Builder c(int i, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mNeutralButtonText = this.f2922a.mContext.getText(i);
            this.f2922a.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder c(CharSequence charSequence, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mNeutralButtonText = charSequence;
            this.f2922a.mNeutralButtonListener = onClickListener;
            return this;
        }

        public Builder a(boolean z) {
            this.f2922a.mCancelable = z;
            return this;
        }

        public Builder a(DialogInterface.OnCancelListener onCancelListener) {
            this.f2922a.mOnCancelListener = onCancelListener;
            return this;
        }

        public Builder a(DialogInterface.OnDismissListener onDismissListener) {
            this.f2922a.mOnDismissListener = onDismissListener;
            return this;
        }

        public Builder a(DialogInterface.OnShowListener onShowListener) {
            this.f2922a.mOnShowListener = onShowListener;
            return this;
        }

        public Builder a(DialogInterface.OnKeyListener onKeyListener) {
            this.f2922a.mOnKeyListener = onKeyListener;
            return this;
        }

        public Builder a(CharSequence charSequence, int i, int i2) {
            if (this.f2922a.mActionItems == null) {
                this.f2922a.mActionItems = new ArrayList<>();
            }
            this.f2922a.mActionItems.add(new AlertControllerWrapper.AlertParams.ActionItem(charSequence, i, i2));
            return this;
        }

        public Builder a(int i, int i2, int i3) {
            return a(this.f2922a.mContext.getText(i), i2, i3);
        }

        public Builder a(DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mOnActionItemClickListener = onClickListener;
            return this;
        }

        public Builder d(int i, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mItems = this.f2922a.mContext.getResources().getTextArray(i);
            this.f2922a.mOnClickListener = onClickListener;
            return this;
        }

        public Builder a(CharSequence[] charSequenceArr, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mItems = charSequenceArr;
            this.f2922a.mOnClickListener = onClickListener;
            return this;
        }

        public Builder a(ListAdapter listAdapter, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mAdapter = listAdapter;
            this.f2922a.mOnClickListener = onClickListener;
            return this;
        }

        public Builder a(Cursor cursor, DialogInterface.OnClickListener onClickListener, String str) {
            this.f2922a.mCursor = cursor;
            this.f2922a.mLabelColumn = str;
            this.f2922a.mOnClickListener = onClickListener;
            return this;
        }

        public Builder a(int i, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f2922a.mItems = this.f2922a.mContext.getResources().getTextArray(i);
            this.f2922a.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f2922a.mCheckedItems = zArr;
            this.f2922a.mIsMultiChoice = true;
            return this;
        }

        public Builder a(CharSequence[] charSequenceArr, boolean[] zArr, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f2922a.mItems = charSequenceArr;
            this.f2922a.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f2922a.mCheckedItems = zArr;
            this.f2922a.mIsMultiChoice = true;
            return this;
        }

        public Builder a(Cursor cursor, String str, String str2, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f2922a.mCursor = cursor;
            this.f2922a.mOnCheckboxClickListener = onMultiChoiceClickListener;
            this.f2922a.mIsCheckedColumn = str;
            this.f2922a.mLabelColumn = str2;
            this.f2922a.mIsMultiChoice = true;
            return this;
        }

        public Builder a(ListAdapter listAdapter, DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
            this.f2922a.mAdapter = listAdapter;
            this.f2922a.mIsMultiChoice = true;
            this.f2922a.mOnCheckboxClickListener = onMultiChoiceClickListener;
            return this;
        }

        public Builder a(int i, int i2, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mItems = this.f2922a.mContext.getResources().getTextArray(i);
            this.f2922a.mOnClickListener = onClickListener;
            this.f2922a.mCheckedItem = i2;
            this.f2922a.mIsSingleChoice = true;
            return this;
        }

        public Builder a(Cursor cursor, int i, String str, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mCursor = cursor;
            this.f2922a.mOnClickListener = onClickListener;
            this.f2922a.mCheckedItem = i;
            this.f2922a.mLabelColumn = str;
            this.f2922a.mIsSingleChoice = true;
            return this;
        }

        public Builder a(CharSequence[] charSequenceArr, int i, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mItems = charSequenceArr;
            this.f2922a.mOnClickListener = onClickListener;
            this.f2922a.mCheckedItem = i;
            this.f2922a.mIsSingleChoice = true;
            return this;
        }

        public Builder a(ListAdapter listAdapter, int i, DialogInterface.OnClickListener onClickListener) {
            this.f2922a.mAdapter = listAdapter;
            this.f2922a.mOnClickListener = onClickListener;
            this.f2922a.mCheckedItem = i;
            this.f2922a.mIsSingleChoice = true;
            return this;
        }

        public Builder a(AdapterView.OnItemSelectedListener onItemSelectedListener) {
            this.f2922a.mOnItemSelectedListener = onItemSelectedListener;
            return this;
        }

        public Builder c(int i) {
            this.f2922a.mView = this.f2922a.mInflater.inflate(i, (ViewGroup) null);
            return this;
        }

        public Builder b(View view) {
            this.f2922a.mView = view;
            return this;
        }

        public AlertDialog b() {
            AlertDialog alertDialog = new AlertDialog(this.f2922a.mContext, this.b);
            this.f2922a.apply(alertDialog.g);
            alertDialog.setCancelable(this.f2922a.mCancelable);
            if (this.f2922a.mCancelable) {
                alertDialog.setCanceledOnTouchOutside(true);
            }
            alertDialog.setOnCancelListener(this.f2922a.mOnCancelListener);
            alertDialog.setOnDismissListener(this.f2922a.mOnDismissListener);
            alertDialog.setOnShowListener(this.f2922a.mOnShowListener);
            if (this.f2922a.mOnKeyListener != null) {
                alertDialog.setOnKeyListener(this.f2922a.mOnKeyListener);
            }
            return alertDialog;
        }

        public AlertDialog c() {
            AlertDialog b2 = b();
            b2.show();
            return b2;
        }
    }
}
