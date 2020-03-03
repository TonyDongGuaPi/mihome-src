package com.xiaomiyoupin.toast.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.xiaomiyoupin.toast.R;
import com.xiaomiyoupin.toast.YPDToast;

public class IndicatorDialog {
    public static final int ICON_FAILED = 3;
    public static final int ICON_INFO = 1;
    public static final int ICON_OK = 2;
    public static final int TYPE_LOADING = 1;
    public static final int TYPE_LOADING_WITH_TEXT = 2;
    public static final int TYPE_PROGRESS = 6;
    public static final int TYPE_TOAST_ICON = 4;
    public static final int TYPE_TOAST_ICON_TEXT = 5;
    public static final int TYPE_TOAST_TEXT = 3;
    private ImageView icon;
    private boolean isCancelable = true;
    private View mContentView;
    private Context mContext;
    private Dialog mDialog;
    private int mDuration = 1;
    private int mIconType = 1;
    private CommonImageProgress mImageProgress;
    /* access modifiers changed from: private */
    public OnIndicatorDialogDismissListener mOnDismissListener;
    private Toast mToast;
    private LinearLayout mView;
    private int progress = 0;
    private ProgressBar progressbar;
    private TextView title;
    private int type = 1;

    public interface OnIndicatorDialogDismissListener {
        void onDismiss(IndicatorDialog indicatorDialog);
    }

    public IndicatorDialog(Context context) {
        this.mContext = ContextCompatUtils.loadRealContext(context);
        this.mView = (LinearLayout) LayoutInflater.from(YPDToast.getInstance().getApplicationContext()).inflate(R.layout.ypd_common_loading, (ViewGroup) null);
        this.mContentView = this.mView.findViewById(R.id.content_view);
        this.title = (TextView) this.mView.findViewById(R.id.title);
        this.icon = (ImageView) this.mView.findViewById(R.id.icon);
        this.progressbar = (ProgressBar) this.mView.findViewById(R.id.progress_bar);
        this.mImageProgress = (CommonImageProgress) this.mView.findViewById(R.id.image_progress);
    }

    public IndicatorDialog setDialogType(int i) {
        this.type = i;
        return this;
    }

    public IndicatorDialog setIconType(int i) {
        this.mIconType = i;
        return this;
    }

    public IndicatorDialog setDismissListener(OnIndicatorDialogDismissListener onIndicatorDialogDismissListener) {
        this.mOnDismissListener = onIndicatorDialogDismissListener;
        return this;
    }

    public IndicatorDialog setDialogIcon(int i) {
        this.icon.setImageResource(i);
        return this;
    }

    public IndicatorDialog setDialogIcon(Bitmap bitmap) {
        this.icon.setImageBitmap(bitmap);
        return this;
    }

    public IndicatorDialog setDialogIcon(Drawable drawable) {
        this.icon.setImageDrawable(drawable);
        return this;
    }

    public IndicatorDialog setDialogTitle(int i) {
        return setDialogTitle(this.mContext.getString(i));
    }

    public IndicatorDialog setDialogTitle(String str) {
        if (this.title.getVisibility() != 0) {
            this.title.setVisibility(0);
        }
        this.title.setText(str);
        return this;
    }

    public IndicatorDialog setToastDuration(int i) {
        this.mDuration = i;
        return this;
    }

    public IndicatorDialog setCancelable(boolean z) {
        this.isCancelable = z;
        return this;
    }

    public void showDialog() {
        dismissDialog();
        switch (this.type) {
            case 1:
            case 2:
                this.progressbar.setVisibility(0);
                this.mDialog = new Dialog(new ContextThemeWrapperPro(this.mContext), R.style.YPDNoFrameDimDialog);
                this.mDialog.setCancelable(this.isCancelable);
                this.mDialog.setCanceledOnTouchOutside(this.isCancelable);
                this.mDialog.setContentView(this.mView);
                Window window = this.mDialog.getWindow();
                if (window != null) {
                    WindowManager.LayoutParams attributes = window.getAttributes();
                    attributes.gravity = 17;
                    window.setAttributes(attributes);
                }
                this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (IndicatorDialog.this.mOnDismissListener != null) {
                            IndicatorDialog.this.mOnDismissListener.onDismiss(IndicatorDialog.this);
                        }
                    }
                });
                this.mDialog.show();
                return;
            case 3:
            case 4:
            case 5:
                this.title.setVisibility(0);
                this.icon.setVisibility(0);
                if (this.mIconType == 1) {
                    this.icon.setImageResource(R.drawable.ypd_common_info);
                } else if (this.mIconType == 2) {
                    this.icon.setImageResource(R.drawable.ypd_common_done);
                } else if (this.mIconType == 3) {
                    this.icon.setImageResource(R.drawable.ypd_common_error);
                }
                if (isShowDialogWithoutPermission()) {
                    this.mDialog = new Dialog(new ContextThemeWrapperPro(this.mContext), R.style.YPDNoFrameDimDialog);
                    this.mDialog.setCancelable(this.isCancelable);
                    this.mDialog.setCanceledOnTouchOutside(this.isCancelable);
                    this.mDialog.setContentView(this.mView);
                    Window window2 = this.mDialog.getWindow();
                    if (window2 != null) {
                        WindowManager.LayoutParams attributes2 = window2.getAttributes();
                        attributes2.gravity = 17;
                        window2.setAttributes(attributes2);
                    }
                    this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        public void onDismiss(DialogInterface dialogInterface) {
                            if (IndicatorDialog.this.mOnDismissListener != null) {
                                IndicatorDialog.this.mOnDismissListener.onDismiss(IndicatorDialog.this);
                            }
                        }
                    });
                    this.mDialog.show();
                    return;
                }
                this.mToast = new Toast(this.mContext);
                this.mToast.setDuration(this.mDuration);
                this.mToast.setView(this.mView);
                this.mToast.setGravity(17, 0, 0);
                if (Build.VERSION.SDK_INT == 25) {
                    ToastReflectUtils.showCustomToast(this.mToast);
                    return;
                } else {
                    this.mToast.show();
                    return;
                }
            case 6:
                this.mImageProgress.setVisibility(0);
                this.mDialog = new Dialog(this.mContext, R.style.YPDNoFrameDimDialog);
                int dimensionPixelSize = this.mDialog.getContext().getResources().getDimensionPixelSize(R.dimen.ypd_indicator_dialog_size);
                this.mContentView.setMinimumWidth(dimensionPixelSize);
                this.mContentView.getLayoutParams().height = dimensionPixelSize;
                this.mDialog.setCancelable(this.isCancelable);
                this.mDialog.setCanceledOnTouchOutside(this.isCancelable);
                this.mDialog.setContentView(this.mView);
                Window window3 = this.mDialog.getWindow();
                if (window3 != null) {
                    WindowManager.LayoutParams attributes3 = window3.getAttributes();
                    attributes3.gravity = 17;
                    window3.setAttributes(attributes3);
                }
                this.mDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                    public void onDismiss(DialogInterface dialogInterface) {
                        if (IndicatorDialog.this.mOnDismissListener != null) {
                            IndicatorDialog.this.mOnDismissListener.onDismiss(IndicatorDialog.this);
                        }
                    }
                });
                this.mDialog.show();
                return;
            default:
                return;
        }
    }

    private boolean isShowDialogWithoutPermission() {
        return !ToastUtils.isNotificationEnabled(this.mContext) && (!MobileManufacturer.isMIUI() || MobileManufacturer.getMIUIVersion() <= 8);
    }

    public void dismissDialog() {
        if (this.mDialog != null && this.mDialog.isShowing()) {
            try {
                this.mDialog.dismiss();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
            this.mDialog = null;
        }
        if (this.mToast != null) {
            this.mToast.cancel();
            this.mToast = null;
        }
    }

    public int getProgress() {
        return this.progress;
    }

    public void setProgress(int i) {
        this.progress = i;
        this.mImageProgress.setProgress(i);
    }
}
