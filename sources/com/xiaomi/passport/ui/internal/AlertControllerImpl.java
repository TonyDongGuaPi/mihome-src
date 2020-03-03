package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ScrollView;
import android.widget.TextView;
import com.xiaomi.passport.ui.AlertControllerWrapper;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.MenuBuilder;
import com.xiaomi.passport.ui.internal.MenuPresenter;
import com.xiaomi.passport.ui.internal.util.Build;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class AlertControllerImpl implements MenuBuilder.Callback {
    private ArrayList<AlertControllerWrapper.AlertParams.ActionItem> mActionItems;
    private ListAdapter mAdapter;
    private final int mAlertDialogLayout;
    private ViewGroup mAlertDialogView;
    private View.OnClickListener mButtonHandler = new View.OnClickListener() {
        public void onClick(View view) {
            Message message;
            if (view == AlertControllerImpl.this.mButtonPositive && AlertControllerImpl.this.mButtonPositiveMessage != null) {
                message = Message.obtain(AlertControllerImpl.this.mButtonPositiveMessage);
            } else if (view != AlertControllerImpl.this.mButtonNegative || AlertControllerImpl.this.mButtonNegativeMessage == null) {
                message = (view != AlertControllerImpl.this.mButtonNeutral || AlertControllerImpl.this.mButtonNeutralMessage == null) ? null : Message.obtain(AlertControllerImpl.this.mButtonNeutralMessage);
            } else {
                message = Message.obtain(AlertControllerImpl.this.mButtonNegativeMessage);
            }
            if (message != null) {
                message.sendToTarget();
            }
            AlertControllerImpl.this.mHandler.obtainMessage(1, AlertControllerImpl.this.mDialogInterface).sendToTarget();
        }
    };
    /* access modifiers changed from: private */
    public Button mButtonNegative;
    /* access modifiers changed from: private */
    public Message mButtonNegativeMessage;
    private CharSequence mButtonNegativeText;
    /* access modifiers changed from: private */
    public Button mButtonNeutral;
    /* access modifiers changed from: private */
    public Message mButtonNeutralMessage;
    private CharSequence mButtonNeutralText;
    /* access modifiers changed from: private */
    public Button mButtonPositive;
    /* access modifiers changed from: private */
    public Message mButtonPositiveMessage;
    private CharSequence mButtonPositiveText;
    private Button mButtonSelect;
    private int mCheckedItem = -1;
    private boolean[] mCheckedItems;
    private Context mContext;
    private View mCustomTitleView;
    /* access modifiers changed from: private */
    public DialogInterface mDialogInterface;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private final Runnable mInvalidateMenuRunnable = new Runnable() {
        public void run() {
            MenuBuilder createMenu = AlertControllerImpl.this.createMenu();
            if (!AlertControllerImpl.this.onCreatePanelMenu(createMenu) || !AlertControllerImpl.this.onPreparePanelMenu(createMenu)) {
                AlertControllerImpl.this.setMenu((MenuBuilder) null);
            } else {
                AlertControllerImpl.this.setMenu(createMenu);
            }
        }
    };
    private MenuBuilder mMenu;
    private MenuPresenter.Callback mMenuPresenterCallback = new MenuPresenter.Callback() {
        public void onCloseMenu(MenuBuilder menuBuilder, boolean z) {
        }

        public boolean onOpenSubMenu(MenuBuilder menuBuilder) {
            return false;
        }
    };
    private CharSequence mMessage;
    private TextView mMessageView;
    private DialogInterface.OnClickListener mOnActionItemClickListener;
    private ScrollView mScrollView;
    private CharSequence mTitle;
    private TextView mTitleView;
    private View mView;
    private final Window mWindow;

    /* access modifiers changed from: private */
    public boolean onPreparePanelMenu(MenuBuilder menuBuilder) {
        return true;
    }

    public void onMenuModeChange(MenuBuilder menuBuilder) {
    }

    private static final class ButtonHandler extends Handler {
        private static final int MSG_DISMISS_DIALOG = 1;
        private WeakReference<DialogInterface> mDialog;

        public ButtonHandler(DialogInterface dialogInterface) {
            this.mDialog = new WeakReference<>(dialogInterface);
        }

        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 1) {
                switch (i) {
                    case -3:
                    case -2:
                    case -1:
                        ((DialogInterface.OnClickListener) message.obj).onClick((DialogInterface) this.mDialog.get(), message.what);
                        return;
                    default:
                        return;
                }
            } else {
                ((DialogInterface) message.obj).dismiss();
            }
        }
    }

    public AlertControllerImpl(Context context, DialogInterface dialogInterface, Window window) {
        this.mContext = context;
        this.mDialogInterface = dialogInterface;
        this.mWindow = window;
        this.mHandler = new ButtonHandler(dialogInterface);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes((AttributeSet) null, R.styleable.Passport_AlertDialog, 16842845, 0);
        this.mAlertDialogLayout = obtainStyledAttributes.getResourceId(R.styleable.Passport_AlertDialog_passport_layout, R.layout.passport_alert_dialog);
        obtainStyledAttributes.recycle();
    }

    static boolean canTextInput(View view) {
        if (view.onCheckIsTextEditor()) {
            return true;
        }
        if (!(view instanceof ViewGroup)) {
            return false;
        }
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        while (childCount > 0) {
            childCount--;
            if (canTextInput(viewGroup.getChildAt(childCount))) {
                return true;
            }
        }
        return false;
    }

    public void installContent() {
        this.mWindow.requestFeature(1);
        if (this.mView == null || !canTextInput(this.mView)) {
            this.mWindow.setFlags(131072, 131072);
        }
        ensureSubDecor();
        this.mAlertDialogView = (ViewGroup) this.mWindow.findViewById(R.id.parentPanel);
        setupView();
    }

    private void ensureSubDecor() {
        this.mWindow.setContentView(this.mAlertDialogLayout);
        if (!Build.IS_TABLET) {
            this.mWindow.setGravity(80);
            this.mWindow.setLayout(-1, -2);
        }
    }

    /* access modifiers changed from: private */
    public void setMenu(MenuBuilder menuBuilder) {
        if (menuBuilder != this.mMenu) {
            this.mMenu = menuBuilder;
        }
    }

    /* access modifiers changed from: package-private */
    public MenuBuilder createMenu() {
        MenuBuilder menuBuilder = new MenuBuilder(this.mContext);
        menuBuilder.setCallback(this);
        return menuBuilder;
    }

    /* access modifiers changed from: private */
    public boolean onCreatePanelMenu(MenuBuilder menuBuilder) {
        for (int i = 0; i < this.mActionItems.size(); i++) {
            AlertControllerWrapper.AlertParams.ActionItem actionItem = this.mActionItems.get(i);
            menuBuilder.add(0, actionItem.id, 0, actionItem.label).setIcon(actionItem.icon).setShowAsAction(2);
        }
        return true;
    }

    public boolean onMenuItemSelected(MenuBuilder menuBuilder, MenuItem menuItem) {
        if (this.mOnActionItemClickListener == null) {
            return true;
        }
        this.mOnActionItemClickListener.onClick(this.mDialogInterface, menuItem.getItemId());
        return true;
    }

    public void setTitle(CharSequence charSequence) {
        this.mTitle = charSequence;
        if (this.mTitleView != null) {
            this.mTitleView.setText(charSequence);
        }
    }

    public void setCustomTitle(View view) {
        this.mCustomTitleView = view;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        if (this.mMessageView != null) {
            this.mMessageView.setText(charSequence);
        }
    }

    public void setView(View view) {
        this.mView = view;
    }

    public void setButton(int i, CharSequence charSequence, DialogInterface.OnClickListener onClickListener, Message message) {
        if (message == null && onClickListener != null) {
            message = this.mHandler.obtainMessage(i, onClickListener);
        }
        switch (i) {
            case -3:
                this.mButtonNeutralText = charSequence;
                this.mButtonNeutralMessage = message;
                return;
            case -2:
                this.mButtonNegativeText = charSequence;
                this.mButtonNegativeMessage = message;
                return;
            case -1:
                this.mButtonPositiveText = charSequence;
                this.mButtonPositiveMessage = message;
                return;
            default:
                throw new IllegalStateException("Button does not exist");
        }
    }

    public void setActionItems(ArrayList<AlertControllerWrapper.AlertParams.ActionItem> arrayList, DialogInterface.OnClickListener onClickListener) {
        this.mActionItems = arrayList;
        this.mOnActionItemClickListener = onClickListener;
    }

    public void setCheckedItems(boolean[] zArr) {
        this.mCheckedItems = zArr;
    }

    public boolean[] getCheckedItems() {
        return this.mCheckedItems;
    }

    public DialogInterface getDialogInterface() {
        return this.mDialogInterface;
    }

    public Button getButton(int i) {
        switch (i) {
            case -3:
                return this.mButtonNeutral;
            case -2:
                return this.mButtonNegative;
            case -1:
                return this.mButtonPositive;
            default:
                return null;
        }
    }

    public void setAdapter(ListAdapter listAdapter) {
        this.mAdapter = listAdapter;
    }

    public void setCheckedItem(int i) {
        this.mCheckedItem = i;
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }

    private void setupView() {
        setupTitle((ViewGroup) this.mAlertDialogView.findViewById(R.id.topPanel));
        setupContent((ViewGroup) this.mAlertDialogView.findViewById(R.id.contentPanel));
        setupCustom((FrameLayout) this.mAlertDialogView.findViewById(R.id.customPanel));
        setupButtons((ViewGroup) this.mAlertDialogView.findViewById(R.id.buttonPanel));
    }

    private void setupTitle(ViewGroup viewGroup) {
        if (this.mCustomTitleView != null) {
            viewGroup.addView(this.mCustomTitleView, 0, new LinearLayout.LayoutParams(-1, -2));
            int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.passport_dialog_title_vertical_padding);
            if (this.mCustomTitleView.getPaddingTop() != 0) {
                dimensionPixelSize = this.mCustomTitleView.getPaddingTop();
            }
            int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.passport_dialog_title_horizontal_padding);
            if (Build.VERSION.SDK_INT >= 17) {
                int paddingStart = this.mCustomTitleView.getPaddingStart() != 0 ? this.mCustomTitleView.getPaddingStart() : dimensionPixelSize2;
                if (this.mCustomTitleView.getPaddingEnd() != 0) {
                    dimensionPixelSize2 = this.mCustomTitleView.getPaddingEnd();
                }
                this.mCustomTitleView.setPaddingRelative(paddingStart, dimensionPixelSize, dimensionPixelSize2, 0);
            } else {
                int paddingLeft = this.mCustomTitleView.getPaddingLeft() != 0 ? this.mCustomTitleView.getPaddingLeft() : dimensionPixelSize2;
                if (this.mCustomTitleView.getPaddingRight() != 0) {
                    dimensionPixelSize2 = this.mCustomTitleView.getPaddingRight();
                }
                this.mCustomTitleView.setPadding(paddingLeft, dimensionPixelSize, dimensionPixelSize2, 0);
            }
            viewGroup.removeView(this.mAlertDialogView.findViewById(R.id.alertTitle));
        } else if (!TextUtils.isEmpty(this.mTitle)) {
            this.mTitleView = (TextView) viewGroup.findViewById(R.id.alertTitle);
            this.mTitleView.setText(this.mTitle);
        } else {
            viewGroup.setVisibility(8);
        }
    }

    private void setupContent(ViewGroup viewGroup) {
        this.mScrollView = (ScrollView) this.mAlertDialogView.findViewById(R.id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mMessageView = (TextView) this.mAlertDialogView.findViewById(R.id.message);
        if (this.mMessageView != null) {
            if (this.mMessage != null) {
                this.mMessageView.setText(this.mMessage);
                return;
            }
            this.mMessageView.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            viewGroup.setVisibility(8);
        }
    }

    private void setupCustom(FrameLayout frameLayout) {
        if (this.mView != null) {
            ((FrameLayout) this.mAlertDialogView.findViewById(16908331)).addView(this.mView, new ViewGroup.LayoutParams(-1, -1));
            if (this.mView instanceof ViewGroup) {
                ViewGroup viewGroup = (ViewGroup) this.mView;
                int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.passport_dialog_custom_vertical_padding);
                if (viewGroup.getPaddingTop() != 0) {
                    dimensionPixelSize = viewGroup.getPaddingTop();
                }
                int dimensionPixelSize2 = this.mContext.getResources().getDimensionPixelSize(R.dimen.passport_dialog_custom_horizontal_padding);
                if (Build.VERSION.SDK_INT >= 17) {
                    int paddingStart = viewGroup.getPaddingStart() != 0 ? viewGroup.getPaddingStart() : dimensionPixelSize2;
                    if (viewGroup.getPaddingEnd() != 0) {
                        dimensionPixelSize2 = viewGroup.getPaddingEnd();
                    }
                    viewGroup.setPaddingRelative(paddingStart, dimensionPixelSize, dimensionPixelSize2, viewGroup.getPaddingBottom());
                    frameLayout.setPaddingRelative(0, 0, 0, 0);
                    return;
                }
                int paddingLeft = viewGroup.getPaddingLeft() != 0 ? viewGroup.getPaddingLeft() : dimensionPixelSize2;
                if (viewGroup.getPaddingRight() != 0) {
                    dimensionPixelSize2 = viewGroup.getPaddingRight();
                }
                viewGroup.setPadding(paddingLeft, dimensionPixelSize, dimensionPixelSize2, viewGroup.getPaddingBottom());
                frameLayout.setPadding(0, 0, 0, 0);
                return;
            }
            return;
        }
        this.mAlertDialogView.findViewById(R.id.customPanel).setVisibility(8);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:9:0x0046  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void setupButtons(android.view.ViewGroup r7) {
        /*
            r6 = this;
            r0 = 16908313(0x1020019, float:2.38773E-38)
            android.view.View r0 = r7.findViewById(r0)
            android.widget.Button r0 = (android.widget.Button) r0
            r6.mButtonPositive = r0
            android.widget.Button r0 = r6.mButtonPositive
            r1 = 1
            r2 = 8
            r3 = 0
            if (r0 == 0) goto L_0x0036
            android.widget.Button r0 = r6.mButtonPositive
            android.view.View$OnClickListener r4 = r6.mButtonHandler
            r0.setOnClickListener(r4)
            java.lang.CharSequence r0 = r6.mButtonPositiveText
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0028
            android.widget.Button r0 = r6.mButtonPositive
            r0.setVisibility(r2)
            goto L_0x0036
        L_0x0028:
            android.widget.Button r0 = r6.mButtonPositive
            java.lang.CharSequence r4 = r6.mButtonPositiveText
            r0.setText(r4)
            android.widget.Button r0 = r6.mButtonPositive
            r0.setVisibility(r3)
            r0 = 1
            goto L_0x0037
        L_0x0036:
            r0 = 0
        L_0x0037:
            r4 = 16908314(0x102001a, float:2.3877302E-38)
            android.view.View r4 = r7.findViewById(r4)
            android.widget.Button r4 = (android.widget.Button) r4
            r6.mButtonNegative = r4
            android.widget.Button r4 = r6.mButtonNegative
            if (r4 == 0) goto L_0x0068
            android.widget.Button r4 = r6.mButtonNegative
            android.view.View$OnClickListener r5 = r6.mButtonHandler
            r4.setOnClickListener(r5)
            java.lang.CharSequence r4 = r6.mButtonNegativeText
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x005b
            android.widget.Button r4 = r6.mButtonNegative
            r4.setVisibility(r2)
            goto L_0x0068
        L_0x005b:
            android.widget.Button r0 = r6.mButtonNegative
            java.lang.CharSequence r4 = r6.mButtonNegativeText
            r0.setText(r4)
            android.widget.Button r0 = r6.mButtonNegative
            r0.setVisibility(r3)
            r0 = 1
        L_0x0068:
            r4 = 16908315(0x102001b, float:2.3877305E-38)
            android.view.View r4 = r7.findViewById(r4)
            android.widget.Button r4 = (android.widget.Button) r4
            r6.mButtonNeutral = r4
            android.widget.Button r4 = r6.mButtonNeutral
            if (r4 == 0) goto L_0x0099
            android.widget.Button r4 = r6.mButtonNeutral
            android.view.View$OnClickListener r5 = r6.mButtonHandler
            r4.setOnClickListener(r5)
            java.lang.CharSequence r4 = r6.mButtonNeutralText
            boolean r4 = android.text.TextUtils.isEmpty(r4)
            if (r4 == 0) goto L_0x008c
            android.widget.Button r1 = r6.mButtonNeutral
            r1.setVisibility(r2)
            goto L_0x0099
        L_0x008c:
            android.widget.Button r0 = r6.mButtonNeutral
            java.lang.CharSequence r4 = r6.mButtonNeutralText
            r0.setText(r4)
            android.widget.Button r0 = r6.mButtonNeutral
            r0.setVisibility(r3)
            r0 = 1
        L_0x0099:
            if (r0 != 0) goto L_0x009e
            r7.setVisibility(r2)
        L_0x009e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.passport.ui.internal.AlertControllerImpl.setupButtons(android.view.ViewGroup):void");
    }
}
