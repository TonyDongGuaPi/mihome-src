package com.miuipub.internal.variable;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.miuipub.internal.app.AlertController;
import com.miuipub.internal.app.AlertControllerImpl;
import java.util.ArrayList;

public class AlertControllerWrapper extends AlertController {
    AlertControllerImpl mAlertControllerImpl;

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
        this.mAlertControllerImpl.a();
    }

    public void setTitle(CharSequence charSequence) {
        this.mAlertControllerImpl.a(charSequence);
    }

    public void setCustomTitle(View view) {
        this.mAlertControllerImpl.b(view);
    }

    public void setMessage(CharSequence charSequence) {
        this.mAlertControllerImpl.b(charSequence);
    }

    public TextView getMessageView() {
        return this.mAlertControllerImpl.h();
    }

    public void setView(View view) {
        this.mAlertControllerImpl.c(view);
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        this.mAlertControllerImpl.a(i, charSequence, onClickListener, message);
    }

    public ListView getListView() {
        return this.mAlertControllerImpl.c();
    }

    public Button getButton(int i) {
        return this.mAlertControllerImpl.a(i);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mAlertControllerImpl.a(i, keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mAlertControllerImpl.b(i, keyEvent);
    }

    public boolean[] getCheckedItems() {
        return this.mAlertControllerImpl.i();
    }

    public void setCheckBox(boolean z, CharSequence charSequence) {
        this.mAlertControllerImpl.a(z, charSequence);
    }

    public boolean isChecked() {
        return this.mAlertControllerImpl.j();
    }

    public static class AlertParams extends AlertController.AlertParams {
        public ArrayList<ActionItem> mActionItems;
        public CharSequence mCheckBoxMessage;
        public boolean mEditMode;
        public boolean mIsChecked;
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
            } else if (this.mTitle != null) {
                alertController.setTitle(this.mTitle);
            }
            if (this.mMessage != null) {
                alertController.setMessage(this.mMessage);
            }
            if (this.mCheckBoxMessage != null) {
                ((AlertControllerWrapper) alertController).getImpl().a(this.mIsChecked, this.mCheckBoxMessage);
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
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                createListView(alertController);
            }
            if (this.mView != null) {
                alertController.setView(this.mView);
            }
            if (this.mActionItems != null) {
                ((AlertControllerWrapper) alertController).getImpl().a(this.mActionItems, this.mOnActionItemClickListener);
            }
        }

        /* JADX WARNING: type inference failed for: r7v0 */
        /* JADX WARNING: type inference failed for: r0v5, types: [com.miuipub.internal.variable.AlertControllerWrapper$AlertParams$2] */
        /* JADX WARNING: type inference failed for: r0v6, types: [com.miuipub.internal.variable.AlertControllerWrapper$AlertParams$1] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private android.widget.ListAdapter createMultiChoiceListAdapter(android.widget.ListView r9, int r10) {
            /*
                r8 = this;
                android.database.Cursor r0 = r8.mCursor
                if (r0 != 0) goto L_0x0020
                boolean r0 = r8.mEditMode
                if (r0 == 0) goto L_0x000f
                android.widget.ListAdapter r0 = r8.mAdapter
                if (r0 == 0) goto L_0x000f
                android.widget.ListAdapter r9 = r8.mAdapter
                goto L_0x002f
            L_0x000f:
                com.miuipub.internal.variable.AlertControllerWrapper$AlertParams$1 r7 = new com.miuipub.internal.variable.AlertControllerWrapper$AlertParams$1
                android.content.Context r2 = r8.mContext
                r4 = 16908308(0x1020014, float:2.3877285E-38)
                java.lang.CharSequence[] r5 = r8.mItems
                r0 = r7
                r1 = r8
                r3 = r10
                r6 = r9
                r0.<init>(r2, r3, r4, r5, r6)
                goto L_0x002e
            L_0x0020:
                com.miuipub.internal.variable.AlertControllerWrapper$AlertParams$2 r7 = new com.miuipub.internal.variable.AlertControllerWrapper$AlertParams$2
                android.content.Context r2 = r8.mContext
                android.database.Cursor r3 = r8.mCursor
                r4 = 0
                r0 = r7
                r1 = r8
                r5 = r9
                r6 = r10
                r0.<init>(r2, r3, r4, r5, r6)
            L_0x002e:
                r9 = r7
            L_0x002f:
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.miuipub.internal.variable.AlertControllerWrapper.AlertParams.createMultiChoiceListAdapter(android.widget.ListView, int):android.widget.ListAdapter");
        }

        private ListAdapter createListAdapter(int i) {
            if (this.mCursor == null) {
                return this.mAdapter != null ? this.mAdapter : new ArrayAdapter(this.mContext, i, 16908308, this.mItems);
            }
            return new SimpleCursorAdapter(this.mContext, i, this.mCursor, new String[]{this.mLabelColumn}, new int[]{16908308});
        }

        private void createListView(AlertController alertController) {
            ListAdapter listAdapter;
            int i;
            final AlertControllerImpl impl = ((AlertControllerWrapper) alertController).getImpl();
            final ListView listView = (ListView) this.mInflater.inflate(impl.d(), (ViewGroup) null);
            if (listView != null) {
                if (this.mIsMultiChoice) {
                    listAdapter = createMultiChoiceListAdapter(listView, impl.g());
                } else {
                    if (this.mIsSingleChoice) {
                        i = impl.f();
                    } else {
                        i = impl.e();
                    }
                    listAdapter = createListAdapter(i);
                }
                impl.a(listAdapter);
                impl.b(this.mCheckedItem);
                impl.a(this.mCheckedItems);
                if (this.mOnClickListener != null) {
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                            AlertParams.this.mOnClickListener.onClick(impl.k(), i);
                            if (!AlertParams.this.mIsSingleChoice) {
                                impl.k().dismiss();
                            }
                        }
                    });
                } else if (this.mOnCheckboxClickListener != null) {
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                            if (AlertParams.this.mCheckedItems != null) {
                                AlertParams.this.mCheckedItems[i] = listView.isItemChecked(i);
                            }
                            AlertParams.this.mOnCheckboxClickListener.onClick(impl.k(), i, listView.isItemChecked(i));
                        }
                    });
                }
                if (this.mOnItemSelectedListener != null) {
                    listView.setOnItemSelectedListener(this.mOnItemSelectedListener);
                }
                if (this.mIsSingleChoice) {
                    listView.setChoiceMode(1);
                } else if (this.mIsMultiChoice) {
                    listView.setChoiceMode(2);
                }
                impl.a(listView);
            }
        }
    }
}
