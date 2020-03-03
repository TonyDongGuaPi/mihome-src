package com.xiaomiyoupin.toast.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import com.xiaomiyoupin.toast.R;
import com.xiaomiyoupin.toast.YPDToast;
import com.xiaomiyoupin.toast.dialog.MLAlertDialog;
import java.lang.ref.WeakReference;

public class MLAlertController {
    private static final int BIT_BUTTON_NEGATIVE = 2;
    private static final int BIT_BUTTON_NEUTRAL = 4;
    private static final int BIT_BUTTON_POSITIVE = 1;
    private View content;
    /* access modifiers changed from: private */
    public ListAdapter mAdapter;
    private int mAlertDialogLayout;
    /* access modifiers changed from: private */
    public boolean mAutoDismiss;
    View.OnClickListener mButtonHandler;
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
    /* access modifiers changed from: private */
    public int mCheckedItem;
    private final Context mContext;
    private boolean mCustomBgTransplant;
    private View mCustomTitleView;
    /* access modifiers changed from: private */
    public final DialogInterface mDialogInterface;
    private boolean mForceInverseBackground;
    /* access modifiers changed from: private */
    public int mGravity;
    /* access modifiers changed from: private */
    public Handler mHandler;
    private Drawable mIcon;
    private int mIconId;
    private ImageView mIconView;
    /* access modifiers changed from: private */
    public int mListItemLayout;
    /* access modifiers changed from: private */
    public int mListLayout;
    /* access modifiers changed from: private */
    public int mListLayoutWithTitle;
    /* access modifiers changed from: private */
    public ListView mListView;
    private CharSequence mMessage;
    private TextView mMessageView;
    /* access modifiers changed from: private */
    public int mMultiChoiceItemLayout;
    private ScrollView mScrollView;
    /* access modifiers changed from: private */
    public int mSingleChoiceItemLayout;
    private CharSequence mTitle;
    private TextView mTitleView;
    /* access modifiers changed from: private */
    public boolean mTransplantBg;
    /* access modifiers changed from: private */
    public View mView;
    private int mViewSpacingBottom;
    private int mViewSpacingLeft;
    private int mViewSpacingRight;
    private boolean mViewSpacingSpecified;
    private int mViewSpacingTop;
    private final Window mWindow;

    private static boolean shouldCenterSingleButton(int i) {
        return i == 1 || i == 2 || i == 4;
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

    public void sendDismissMessage() {
        this.mHandler.obtainMessage(1, this.mDialogInterface).sendToTarget();
    }

    public MLAlertController(Context context, DialogInterface dialogInterface, Window window) {
        this(context, dialogInterface, window, 80);
    }

    public MLAlertController(Context context, DialogInterface dialogInterface, Window window, int i) {
        this.mViewSpacingSpecified = false;
        this.mIconId = -1;
        this.mCheckedItem = -1;
        this.mTransplantBg = false;
        this.mAutoDismiss = true;
        this.mCustomBgTransplant = false;
        this.mButtonHandler = new View.OnClickListener() {
            public void onClick(View view) {
                Message message;
                if (view == MLAlertController.this.mButtonPositive && MLAlertController.this.mButtonPositiveMessage != null) {
                    message = Message.obtain(MLAlertController.this.mButtonPositiveMessage);
                } else if (view != MLAlertController.this.mButtonNegative || MLAlertController.this.mButtonNegativeMessage == null) {
                    message = (view != MLAlertController.this.mButtonNeutral || MLAlertController.this.mButtonNeutralMessage == null) ? null : Message.obtain(MLAlertController.this.mButtonNeutralMessage);
                } else {
                    message = Message.obtain(MLAlertController.this.mButtonNegativeMessage);
                }
                if (message != null) {
                    message.sendToTarget();
                }
                if (MLAlertController.this.mAutoDismiss) {
                    MLAlertController.this.mHandler.obtainMessage(1, MLAlertController.this.mDialogInterface).sendToTarget();
                }
            }
        };
        this.mContext = context;
        this.mDialogInterface = dialogInterface;
        this.mWindow = window;
        this.mHandler = new ButtonHandler(dialogInterface);
        this.mAlertDialogLayout = R.layout.ypd_ml_alert_dialog;
        this.mListLayout = R.layout.ypd_ml_select_dialog;
        this.mListLayoutWithTitle = R.layout.ypd_ml_select_dialog_center;
        this.mMultiChoiceItemLayout = R.layout.ypd_ml_select_dialog_multichoice;
        this.mSingleChoiceItemLayout = R.layout.ypd_ml_select_dialog_singlechoice;
        this.mListItemLayout = R.layout.ypd_ml_select_dialog_item;
        this.mGravity = i;
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
        this.mWindow.setGravity(this.mGravity);
        if (this.mView == null || !canTextInput(this.mView)) {
            this.mWindow.setFlags(131072, 131072);
        }
        this.content = LayoutInflater.from(this.mContext).inflate(this.mAlertDialogLayout, (ViewGroup) null);
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        int applyDimension = (int) TypedValue.applyDimension(1, 8.0f, this.mContext.getResources().getDisplayMetrics());
        layoutParams.bottomMargin = applyDimension;
        layoutParams.leftMargin = applyDimension;
        layoutParams.rightMargin = applyDimension;
        this.mWindow.setContentView(this.content, layoutParams);
        setupView();
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

    public void setAudoDismiss(boolean z) {
        this.mAutoDismiss = z;
    }

    public void setMessage(CharSequence charSequence) {
        this.mMessage = charSequence;
        if (this.mMessageView != null) {
            this.mMessageView.setText(charSequence);
        }
    }

    public void setView(View view) {
        this.mView = view;
        this.mViewSpacingSpecified = false;
    }

    public void setCustomTransplant(boolean z) {
        this.mCustomBgTransplant = z;
    }

    public void setView(View view, int i, int i2, int i3, int i4) {
        this.mView = view;
        this.mViewSpacingSpecified = true;
        this.mViewSpacingLeft = i;
        this.mViewSpacingTop = i2;
        this.mViewSpacingRight = i3;
        this.mViewSpacingBottom = i4;
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
                throw new IllegalArgumentException("Button does not exist");
        }
    }

    public void setIcon(int i) {
        this.mIconId = i;
        if (this.mIconView == null) {
            return;
        }
        if (i > 0) {
            this.mIconView.setImageResource(this.mIconId);
        } else if (i == 0) {
            this.mIconView.setVisibility(8);
        }
    }

    public void setIcon(Drawable drawable) {
        this.mIcon = drawable;
        if (this.mIconView != null && this.mIcon != null) {
            this.mIconView.setImageDrawable(drawable);
        }
    }

    public void setInverseBackgroundForced(boolean z) {
        this.mForceInverseBackground = z;
    }

    public ListView getListView() {
        return this.mListView;
    }

    public View getView() {
        return this.mView;
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

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 82 && this.mListView != null && this.mListView.getVisibility() == 0) {
            this.mDialogInterface.dismiss();
        }
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return this.mScrollView != null && this.mScrollView.executeKeyEvent(keyEvent);
    }

    private void setupView() {
        LinearLayout linearLayout = (LinearLayout) this.mWindow.findViewById(R.id.contentPanel);
        setupContent(linearLayout);
        boolean z = setupButtons();
        LinearLayout linearLayout2 = (LinearLayout) this.mWindow.findViewById(R.id.topPanel);
        boolean z2 = setupTitle(linearLayout2);
        View findViewById = this.mWindow.findViewById(R.id.buttonPanel);
        if (!z) {
            findViewById.setVisibility(8);
        }
        FrameLayout frameLayout = (FrameLayout) this.mWindow.findViewById(R.id.customPanel);
        if (this.mView != null) {
            FrameLayout frameLayout2 = (FrameLayout) this.mWindow.findViewById(R.id.custom);
            frameLayout2.addView(this.mView);
            if (this.mViewSpacingSpecified) {
                frameLayout2.setPadding(this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                if (this.mCustomBgTransplant) {
                    this.mTransplantBg = true;
                }
            }
            if (this.mListView != null) {
                ((LinearLayout.LayoutParams) frameLayout.getLayoutParams()).weight = 0.0f;
            }
        } else {
            frameLayout.setVisibility(8);
        }
        if (this.mTransplantBg) {
            this.mWindow.findViewById(R.id.parentPanel).setBackgroundColor(this.mContext.getResources().getColor(17170445));
        }
        if (this.mListView != null) {
            this.mWindow.findViewById(R.id.title_divider_line).setVisibility(0);
        } else {
            this.mWindow.findViewById(R.id.title_divider_line).setVisibility(8);
        }
        if (linearLayout2.getVisibility() == 8 && linearLayout.getVisibility() == 8 && frameLayout.getVisibility() == 8 && z) {
            findViewById.setPadding(findViewById.getPaddingLeft(), findViewById.getPaddingBottom(), findViewById.getPaddingRight(), findViewById.getPaddingBottom());
        }
        setBackground(linearLayout2, linearLayout, frameLayout, z, z2, findViewById);
        if (TextUtils.isEmpty(this.mTitle) && TextUtils.isEmpty(this.mMessage)) {
            this.mWindow.findViewById(R.id.empty_view).setVisibility(8);
        }
    }

    private boolean setupTitle(LinearLayout linearLayout) {
        if (this.mCustomTitleView != null) {
            linearLayout.addView(this.mCustomTitleView, 0, new LinearLayout.LayoutParams(-1, -2));
            this.mWindow.findViewById(R.id.title_template).setVisibility(8);
        } else {
            boolean z = !TextUtils.isEmpty(this.mTitle);
            this.mIconView = (ImageView) this.mWindow.findViewById(R.id.icon);
            if (z) {
                this.mTitleView = (TextView) this.mWindow.findViewById(R.id.alertTitle);
                this.mTitleView.setText(this.mTitle);
                if (this.mIconId > 0) {
                    this.mIconView.setImageResource(this.mIconId);
                } else if (this.mIcon != null) {
                    this.mIconView.setImageDrawable(this.mIcon);
                } else if (this.mIconId == 0) {
                    this.mTitleView.setPadding(this.mIconView.getPaddingLeft(), this.mIconView.getPaddingTop(), this.mIconView.getPaddingRight(), this.mIconView.getPaddingBottom());
                    this.mIconView.setVisibility(8);
                }
            } else {
                this.mWindow.findViewById(R.id.title_template).setVisibility(8);
                this.mIconView.setVisibility(8);
                linearLayout.setVisibility(8);
                return false;
            }
        }
        return true;
    }

    private void setupContent(LinearLayout linearLayout) {
        this.mScrollView = (ScrollView) this.mWindow.findViewById(R.id.scrollView);
        this.mScrollView.setFocusable(false);
        this.mMessageView = (TextView) this.mWindow.findViewById(R.id.message);
        if (this.mMessageView != null) {
            if (this.mMessage != null) {
                this.mMessageView.setText(this.mMessage);
                return;
            }
            this.mMessageView.setVisibility(8);
            this.mScrollView.removeView(this.mMessageView);
            if (this.mListView != null) {
                linearLayout.removeView(this.mWindow.findViewById(R.id.scrollView));
                linearLayout.addView(this.mListView, new LinearLayout.LayoutParams(-1, -1));
                linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
                return;
            }
            linearLayout.setVisibility(8);
        }
    }

    private boolean setupButtons() {
        int i;
        this.mButtonPositive = (Button) this.mWindow.findViewById(R.id.button1);
        this.mButtonPositive.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonPositiveText)) {
            this.mButtonPositive.setVisibility(8);
            i = 0;
        } else {
            this.mButtonPositive.setText(this.mButtonPositiveText);
            this.mButtonPositive.setVisibility(0);
            i = 1;
        }
        this.mButtonNegative = (Button) this.mWindow.findViewById(R.id.button2);
        this.mButtonNegative.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNegativeText)) {
            this.mButtonNegative.setVisibility(8);
        } else {
            this.mButtonNegative.setText(this.mButtonNegativeText);
            this.mButtonNegative.setVisibility(0);
            i |= 2;
        }
        this.mButtonNeutral = (Button) this.mWindow.findViewById(R.id.button3);
        this.mButtonNeutral.setOnClickListener(this.mButtonHandler);
        if (TextUtils.isEmpty(this.mButtonNeutralText)) {
            this.mButtonNeutral.setVisibility(8);
        } else {
            this.mButtonNeutral.setText(this.mButtonNeutralText);
            this.mButtonNeutral.setVisibility(0);
            i |= 4;
        }
        if (shouldCenterSingleButton(i)) {
            if (i == 1) {
                centerButton(this.mButtonPositive);
            } else if (i == 2) {
                centerButton(this.mButtonNegative);
            } else if (i == 4) {
                centerButton(this.mButtonNeutral);
            }
        }
        if (i != 0) {
            return true;
        }
        return false;
    }

    private void centerButton(TextView textView) {
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView.getLayoutParams();
        layoutParams.gravity = 1;
        layoutParams.weight = 0.5f;
        textView.setLayoutParams(layoutParams);
        textView.setBackgroundResource(R.drawable.ypd_common_button);
    }

    private void setBackground(LinearLayout linearLayout, LinearLayout linearLayout2, View view, boolean z, boolean z2, View view2) {
        int i;
        if (this.mTransplantBg) {
            int color = this.mContext.getResources().getColor(17170445);
            int color2 = this.mContext.getResources().getColor(17170445);
            int color3 = this.mContext.getResources().getColor(17170445);
            int color4 = this.mContext.getResources().getColor(17170445);
            int color5 = this.mContext.getResources().getColor(17170445);
            int color6 = this.mContext.getResources().getColor(17170445);
            int color7 = this.mContext.getResources().getColor(17170445);
            int color8 = this.mContext.getResources().getColor(17170445);
            int color9 = this.mContext.getResources().getColor(17170445);
            View[] viewArr = new View[4];
            boolean[] zArr = new boolean[4];
            if (z2) {
                viewArr[0] = linearLayout;
                zArr[0] = false;
                i = 1;
            } else {
                i = 0;
            }
            viewArr[i] = linearLayout2.getVisibility() == 8 ? null : linearLayout2;
            zArr[i] = this.mListView != null;
            int i2 = i + 1;
            if (view != null) {
                viewArr[i2] = view;
                zArr[i2] = this.mForceInverseBackground;
                i2++;
            }
            if (z) {
                viewArr[i2] = view2;
                zArr[i2] = true;
            }
            int i3 = color;
            View view3 = null;
            boolean z3 = false;
            boolean z4 = false;
            for (int i4 = 0; i4 < viewArr.length; i4++) {
                View view4 = viewArr[i4];
                if (view4 != null) {
                    if (view3 != null) {
                        view3.setBackgroundResource(!z4 ? z3 ? color6 : color2 : z3 ? color7 : color3);
                        z4 = true;
                    }
                    z3 = zArr[i4];
                    view3 = view4;
                }
            }
            if (view3 != null) {
                if (!z4) {
                    color8 = z3 ? color5 : i3;
                } else if (!z3) {
                    color8 = color4;
                } else if (z) {
                    color8 = color9;
                }
                view3.setBackgroundResource(color8);
            }
        }
        if (this.mListView != null && this.mAdapter != null) {
            this.mListView.setAdapter(this.mAdapter);
            if (this.mCheckedItem > -1) {
                this.mListView.setItemChecked(this.mCheckedItem, true);
                this.mListView.setSelection(this.mCheckedItem);
            }
        }
    }

    public static class AlertParams {
        public ListAdapter mAdapter;
        public boolean mAutoDismiss = true;
        public boolean mCancelable;
        public int mCheckedItem = -1;
        public boolean[] mCheckedItems;
        public final Context mContext;
        public Cursor mCursor;
        public boolean mCustomBgTransplant = false;
        public CharSequence mCustomTitle;
        public View mCustomTitleView;
        public MLAlertDialog.DismissCallBack mDismissCallBack;
        public boolean mForceInverseBackground;
        public Drawable mIcon;
        public int mIconId = 0;
        public final LayoutInflater mInflater;
        public String mIsCheckedColumn;
        public boolean mIsMultiChoice;
        public boolean mIsSingleChoice;
        public CharSequence[] mItems;
        public String mLabelColumn;
        public SpannableStringBuilder mMesageBuilder;
        public CharSequence mMessage;
        public DialogInterface.OnClickListener mNegativeButtonListener;
        public CharSequence mNegativeButtonText;
        public DialogInterface.OnClickListener mNeutralButtonListener;
        public CharSequence mNeutralButtonText;
        public DialogInterface.OnCancelListener mOnCancelListener;
        public DialogInterface.OnMultiChoiceClickListener mOnCheckboxClickListener;
        public DialogInterface.OnClickListener mOnClickListener;
        public AdapterView.OnItemSelectedListener mOnItemSelectedListener;
        public DialogInterface.OnKeyListener mOnKeyListener;
        public OnPrepareListViewListener mOnPrepareListViewListener;
        public DialogInterface.OnClickListener mPositiveButtonListener;
        public CharSequence mPositiveButtonText;
        public boolean mRecycleOnMeasure = true;
        public CharSequence mTitle;
        public View mView;
        public int mViewSpacingBottom;
        public int mViewSpacingLeft;
        public int mViewSpacingRight;
        public boolean mViewSpacingSpecified = false;
        public int mViewSpacingTop;

        public interface OnPrepareListViewListener {
            void onPrepareListView(ListView listView);
        }

        public AlertParams(Context context) {
            this.mContext = context;
            this.mCancelable = true;
            this.mInflater = LayoutInflater.from(YPDToast.getInstance().getApplicationContext());
        }

        public void apply(MLAlertController mLAlertController) {
            if (this.mCustomTitleView != null) {
                mLAlertController.setCustomTitle(this.mCustomTitleView);
            } else {
                if (this.mTitle != null) {
                    mLAlertController.setTitle(this.mTitle);
                }
                if (this.mIcon != null) {
                    mLAlertController.setIcon(this.mIcon);
                }
                if (this.mIconId >= 0) {
                    mLAlertController.setIcon(this.mIconId);
                }
            }
            if (this.mMessage != null) {
                mLAlertController.setMessage(this.mMessage);
            }
            if (this.mMesageBuilder != null) {
                mLAlertController.setMessage(this.mMesageBuilder);
            }
            if (this.mPositiveButtonText != null) {
                mLAlertController.setButton(-1, this.mPositiveButtonText, this.mPositiveButtonListener, (Message) null);
            }
            if (this.mNegativeButtonText != null) {
                mLAlertController.setButton(-2, this.mNegativeButtonText, this.mNegativeButtonListener, (Message) null);
            }
            if (this.mNeutralButtonText != null) {
                mLAlertController.setButton(-3, this.mNeutralButtonText, this.mNeutralButtonListener, (Message) null);
            }
            if (this.mForceInverseBackground) {
                mLAlertController.setInverseBackgroundForced(true);
            }
            boolean unused = mLAlertController.mTransplantBg = false;
            if (!(this.mItems == null && this.mCursor == null && this.mAdapter == null)) {
                if (mLAlertController.mGravity == 17) {
                    createCenterListView(mLAlertController);
                } else {
                    createListView(mLAlertController);
                }
            }
            if (this.mView != null) {
                if (this.mViewSpacingSpecified) {
                    mLAlertController.setView(this.mView, this.mViewSpacingLeft, this.mViewSpacingTop, this.mViewSpacingRight, this.mViewSpacingBottom);
                } else {
                    mLAlertController.setView(this.mView);
                }
            }
            mLAlertController.setAudoDismiss(this.mAutoDismiss);
            mLAlertController.setCustomTransplant(this.mCustomBgTransplant);
        }

        private void createCenterListView(final MLAlertController mLAlertController) {
            ListAdapter listAdapter;
            LinearLayout linearLayout = (LinearLayout) this.mInflater.inflate(mLAlertController.mListLayoutWithTitle, (ViewGroup) null);
            final RecycleListView recycleListView = (RecycleListView) linearLayout.findViewById(R.id.select_dialog_listview);
            int i = R.layout.ypd_ml_center_item;
            if (this.mCursor == null) {
                listAdapter = this.mAdapter != null ? this.mAdapter : new ArrayAdapter(this.mContext, i, R.id.text1, this.mItems);
            } else {
                listAdapter = new SimpleCursorAdapter(this.mContext, i, this.mCursor, new String[]{this.mLabelColumn}, new int[]{R.id.text1});
            }
            if (this.mCustomTitle != null) {
                ((TextView) linearLayout.findViewById(R.id.title)).setText(this.mCustomTitle);
            }
            if (this.mOnPrepareListViewListener != null) {
                this.mOnPrepareListViewListener.onPrepareListView(recycleListView);
            }
            ListAdapter unused = mLAlertController.mAdapter = listAdapter;
            recycleListView.setAdapter(listAdapter);
            int unused2 = mLAlertController.mCheckedItem = this.mCheckedItem;
            if (this.mOnClickListener != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        AlertParams.this.mOnClickListener.onClick(mLAlertController.mDialogInterface, i);
                        if (!AlertParams.this.mIsSingleChoice) {
                            mLAlertController.mDialogInterface.dismiss();
                        }
                    }
                });
            } else if (this.mOnCheckboxClickListener != null) {
                recycleListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView adapterView, View view, int i, long j) {
                        if (AlertParams.this.mCheckedItems != null) {
                            AlertParams.this.mCheckedItems[i] = recycleListView.isItemChecked(i);
                        }
                        AlertParams.this.mOnCheckboxClickListener.onClick(mLAlertController.mDialogInterface, i, recycleListView.isItemChecked(i));
                    }
                });
            }
            if (this.mOnItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(this.mOnItemSelectedListener);
            }
            if (this.mOnItemSelectedListener != null) {
                recycleListView.setOnItemSelectedListener(this.mOnItemSelectedListener);
            }
            if (this.mIsSingleChoice) {
                recycleListView.setChoiceMode(1);
            } else if (this.mIsMultiChoice) {
                recycleListView.setChoiceMode(2);
            }
            recycleListView.mRecycleOnMeasure = this.mRecycleOnMeasure;
            View unused3 = mLAlertController.mView = linearLayout;
            boolean unused4 = mLAlertController.mTransplantBg = true;
            mLAlertController.setCustomTransplant(this.mCustomBgTransplant);
        }

        /* JADX WARNING: type inference failed for: r9v0, types: [android.widget.ListAdapter] */
        /* JADX WARNING: type inference failed for: r9v1 */
        /* JADX WARNING: type inference failed for: r1v22, types: [android.widget.ArrayAdapter] */
        /* JADX WARNING: type inference failed for: r1v23, types: [android.widget.ListAdapter] */
        /* JADX WARNING: type inference failed for: r1v30, types: [com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$4] */
        /* JADX WARNING: type inference failed for: r1v31, types: [com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$3] */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private void createListView(final com.xiaomiyoupin.toast.dialog.MLAlertController r11) {
            /*
                r10 = this;
                android.view.LayoutInflater r0 = r10.mInflater
                int r1 = r11.mListLayout
                r2 = 0
                android.view.View r0 = r0.inflate(r1, r2)
                com.xiaomiyoupin.toast.dialog.RecycleListView r0 = (com.xiaomiyoupin.toast.dialog.RecycleListView) r0
                boolean r1 = r10.mIsMultiChoice
                r8 = 1
                if (r1 == 0) goto L_0x0038
                android.database.Cursor r1 = r10.mCursor
                if (r1 != 0) goto L_0x0029
                com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$3 r9 = new com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$3
                android.content.Context r3 = r10.mContext
                int r4 = r11.mMultiChoiceItemLayout
                int r5 = com.xiaomiyoupin.toast.R.id.text1
                java.lang.CharSequence[] r6 = r10.mItems
                r1 = r9
                r2 = r10
                r7 = r0
                r1.<init>(r3, r4, r5, r6, r7)
                goto L_0x0077
            L_0x0029:
                com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$4 r9 = new com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$4
                android.content.Context r3 = r10.mContext
                android.database.Cursor r4 = r10.mCursor
                r5 = 0
                r1 = r9
                r2 = r10
                r6 = r0
                r7 = r11
                r1.<init>(r3, r4, r5, r6, r7)
                goto L_0x0077
            L_0x0038:
                boolean r1 = r10.mIsSingleChoice
                if (r1 == 0) goto L_0x0042
                int r1 = r11.mSingleChoiceItemLayout
            L_0x0040:
                r4 = r1
                goto L_0x0047
            L_0x0042:
                int r1 = r11.mListItemLayout
                goto L_0x0040
            L_0x0047:
                android.database.Cursor r1 = r10.mCursor
                if (r1 != 0) goto L_0x005e
                android.widget.ListAdapter r1 = r10.mAdapter
                if (r1 == 0) goto L_0x0052
                android.widget.ListAdapter r1 = r10.mAdapter
                goto L_0x0076
            L_0x0052:
                android.widget.ArrayAdapter r1 = new android.widget.ArrayAdapter
                android.content.Context r2 = r10.mContext
                int r3 = com.xiaomiyoupin.toast.R.id.text1
                java.lang.CharSequence[] r5 = r10.mItems
                r1.<init>(r2, r4, r3, r5)
                goto L_0x0076
            L_0x005e:
                android.widget.SimpleCursorAdapter r1 = new android.widget.SimpleCursorAdapter
                android.content.Context r3 = r10.mContext
                android.database.Cursor r5 = r10.mCursor
                java.lang.String[] r6 = new java.lang.String[r8]
                java.lang.String r2 = r10.mLabelColumn
                r7 = 0
                r6[r7] = r2
                int[] r9 = new int[r8]
                int r2 = com.xiaomiyoupin.toast.R.id.text1
                r9[r7] = r2
                r2 = r1
                r7 = r9
                r2.<init>(r3, r4, r5, r6, r7)
            L_0x0076:
                r9 = r1
            L_0x0077:
                com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$OnPrepareListViewListener r1 = r10.mOnPrepareListViewListener
                if (r1 == 0) goto L_0x0080
                com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$OnPrepareListViewListener r1 = r10.mOnPrepareListViewListener
                r1.onPrepareListView(r0)
            L_0x0080:
                android.widget.ListAdapter unused = r11.mAdapter = r9
                int r1 = r10.mCheckedItem
                int unused = r11.mCheckedItem = r1
                android.content.DialogInterface$OnClickListener r1 = r10.mOnClickListener
                if (r1 == 0) goto L_0x0095
                com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$5 r1 = new com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$5
                r1.<init>(r11)
                r0.setOnItemClickListener(r1)
                goto L_0x00a1
            L_0x0095:
                android.content.DialogInterface$OnMultiChoiceClickListener r1 = r10.mOnCheckboxClickListener
                if (r1 == 0) goto L_0x00a1
                com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$6 r1 = new com.xiaomiyoupin.toast.dialog.MLAlertController$AlertParams$6
                r1.<init>(r0, r11)
                r0.setOnItemClickListener(r1)
            L_0x00a1:
                android.widget.AdapterView$OnItemSelectedListener r1 = r10.mOnItemSelectedListener
                if (r1 == 0) goto L_0x00aa
                android.widget.AdapterView$OnItemSelectedListener r1 = r10.mOnItemSelectedListener
                r0.setOnItemSelectedListener(r1)
            L_0x00aa:
                boolean r1 = r10.mIsSingleChoice
                if (r1 == 0) goto L_0x00b2
                r0.setChoiceMode(r8)
                goto L_0x00ba
            L_0x00b2:
                boolean r1 = r10.mIsMultiChoice
                if (r1 == 0) goto L_0x00ba
                r1 = 2
                r0.setChoiceMode(r1)
            L_0x00ba:
                boolean r1 = r10.mRecycleOnMeasure
                r0.mRecycleOnMeasure = r1
                android.widget.ListView unused = r11.mListView = r0
                boolean r0 = r10.mCustomBgTransplant
                r11.setCustomTransplant(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomiyoupin.toast.dialog.MLAlertController.AlertParams.createListView(com.xiaomiyoupin.toast.dialog.MLAlertController):void");
        }
    }
}
