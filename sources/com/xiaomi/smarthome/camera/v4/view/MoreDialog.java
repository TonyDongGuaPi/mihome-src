package com.xiaomi.smarthome.camera.v4.view;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;
import com.mijia.app.Event;
import com.mijia.camera.MijiaCameraDevice;
import com.mijia.debug.SDKLog;
import com.mijia.model.property.CameraPropertyBase;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.camera.view.MultiStateTextView;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.library.common.util.ToastUtil;
import com.xiaomi.smarthome.miio.TitleBarUtil;

public class MoreDialog extends AlertDialog implements View.OnClickListener {
    private static final String TAG = "MoreDialog";
    /* access modifiers changed from: private */
    public Context mContext;
    private View.OnClickListener mListener;
    /* access modifiers changed from: private */
    public MijiaCameraDevice mijiaCameraDevice;
    public IMoreDialogListener moreDialogListener;
    /* access modifiers changed from: private */
    public MultiStateTextView mstvSleep;
    private boolean showAlbum = false;

    public interface IMoreDialogListener {
        void onPowerStateChanged(boolean z);
    }

    public MoreDialog(Context context, MijiaCameraDevice mijiaCameraDevice2, View.OnClickListener onClickListener) {
        super(context);
        this.mijiaCameraDevice = mijiaCameraDevice2;
        this.mListener = onClickListener;
        this.mContext = context;
    }

    public MoreDialog(Context context, int i, MijiaCameraDevice mijiaCameraDevice2, View.OnClickListener onClickListener, boolean z) {
        super(context, i);
        this.mijiaCameraDevice = mijiaCameraDevice2;
        getWindow().getAttributes().windowAnimations = R.style.dialog_anim;
        this.mListener = onClickListener;
        this.mContext = context;
        this.showAlbum = z;
    }

    public void dismiss() {
        super.dismiss();
        this.mListener = null;
    }

    public void show() {
        super.show();
        init();
        initViews();
    }

    private void init() {
        getWindow().setContentView(R.layout.dialog_more);
    }

    private void initViews() {
        TitleBarUtil.a(getWindow());
        findViewById(R.id.tvCancel).setOnClickListener(this);
        findViewById(R.id.tvPIP).setOnClickListener(this);
        findViewById(R.id.tvCalibration).setOnClickListener(this);
        findViewById(R.id.tvMotionTrack).setOnClickListener(this);
        if (this.showAlbum) {
            findViewById(R.id.tvAlbum2).setVisibility(0);
            findViewById(R.id.tvAlbum2).setOnClickListener(this);
        } else {
            findViewById(R.id.tvAlbum2).setVisibility(8);
            findViewById(R.id.tvAlbum2).setOnClickListener((View.OnClickListener) null);
        }
        this.mstvSleep = (MultiStateTextView) findViewById(R.id.mstvSleep);
        this.mstvSleep.addState(new MultiStateTextView.StateItem(R.string.wake_up, R.drawable.widget_item_more_sleep_stop, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.aF);
                if (MoreDialog.this.mijiaCameraDevice.isReadOnlyShared()) {
                    Toast.makeText(MoreDialog.this.mContext, R.string.auth_fail, 0).show();
                } else if (MoreDialog.this.mijiaCameraDevice != null && MoreDialog.this.mijiaCameraDevice.f() != null) {
                    MoreDialog.this.mijiaCameraDevice.f().a(CameraPropertyBase.l, true, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            MoreDialog.this.dismiss();
                            boolean z = true;
                            MoreDialog.this.mstvSleep.setCurrentState(1);
                            SDKLog.b(MoreDialog.TAG, "set wakeup success");
                            MoreDialog.this.mijiaCameraDevice.g().a(CameraPropertyBase.l);
                            if (MoreDialog.this.moreDialogListener != null) {
                                IMoreDialogListener iMoreDialogListener = MoreDialog.this.moreDialogListener;
                                if (MoreDialog.this.mstvSleep.getCurrentState() != 1) {
                                    z = false;
                                }
                                iMoreDialogListener.onPowerStateChanged(z);
                            }
                        }

                        public void onFailure(int i, String str) {
                            MoreDialog.this.dismiss();
                            ToastUtil.a(MoreDialog.this.getContext(), (int) R.string.action_fail);
                            SDKLog.b(MoreDialog.TAG, "set wakeup failed:" + i + " s:" + str);
                        }
                    });
                }
            }
        }));
        this.mstvSleep.addState(new MultiStateTextView.StateItem(R.string.sleep, R.drawable.widget_item_more_sleep, new View.OnClickListener() {
            public void onClick(View view) {
                Event.a(Event.l);
                Event.a(Event.aF);
                if (MoreDialog.this.mijiaCameraDevice.isReadOnlyShared()) {
                    Toast.makeText(MoreDialog.this.mContext, R.string.auth_fail, 0).show();
                } else if (MoreDialog.this.mijiaCameraDevice != null && MoreDialog.this.mijiaCameraDevice.f() != null) {
                    MoreDialog.this.mijiaCameraDevice.f().a(CameraPropertyBase.l, false, (Callback<Void>) new Callback<Void>() {
                        public void onSuccess(Void voidR) {
                            MoreDialog.this.dismiss();
                            boolean z = false;
                            MoreDialog.this.mstvSleep.setCurrentState(0);
                            MoreDialog.this.mijiaCameraDevice.g().a(CameraPropertyBase.l);
                            SDKLog.b(MoreDialog.TAG, "set sleep success");
                            if (MoreDialog.this.moreDialogListener != null) {
                                IMoreDialogListener iMoreDialogListener = MoreDialog.this.moreDialogListener;
                                if (MoreDialog.this.mstvSleep.getCurrentState() == 1) {
                                    z = true;
                                }
                                iMoreDialogListener.onPowerStateChanged(z);
                            }
                        }

                        public void onFailure(int i, String str) {
                            MoreDialog.this.dismiss();
                            ToastUtil.a(MoreDialog.this.getContext(), (int) R.string.action_fail);
                            SDKLog.b(MoreDialog.TAG, "set sleep failed:" + i + " s:" + str);
                        }
                    });
                }
            }
        }));
        if (this.mijiaCameraDevice != null && this.mijiaCameraDevice.f() != null) {
            this.mstvSleep.setCurrentState(this.mijiaCameraDevice.f().b(CameraPropertyBase.l) ? 1 : 0);
        }
    }

    public void onClick(View view) {
        if (this.mListener != null) {
            this.mListener.onClick(view);
        }
    }
}
