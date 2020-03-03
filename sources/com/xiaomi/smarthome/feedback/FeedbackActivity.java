package com.xiaomi.smarthome.feedback;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.alipay.mobile.common.logging.strategy.LogStrategyManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.core.client.IClientCallback;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceManager;
import com.xiaomi.smarthome.feedback.view.ImagePickerPreview;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.framework.api.UserApi;
import com.xiaomi.smarthome.framework.api.model.UploadUserData;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.library.common.dialog.XQProgressDialog;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;

public class FeedbackActivity extends BaseActivity implements View.OnClickListener {
    public static final String EXTRA_DEVICE_DID = "extra_device_did";
    public static final String EXTRA_DEVICE_MODEL = "extra_device_model";
    public static final String EXTRA_FEEDBACK_TYPE = "extra_feedback_type";
    public static final String EXTRA_TITLE = "extra_title";
    public static final int MAX_CONTACT_LENGTH = 32;
    public static final int MAX_CONTENT_LENGTH = 500;

    /* renamed from: a  reason: collision with root package name */
    private static final String f15933a = "FeedbackActivity";
    private static final int b = 703;
    /* access modifiers changed from: private */
    public ImagePickerPreview c;
    TextView mCommitBtn;
    MLAlertDialog mCommitTipsDialog;
    EditText mContactEditText;
    EditText mContentEditText;
    Context mContext;
    Device mDevice;
    TextView mDeviceName;
    String mModel;
    EditText mRouterModelEditText;
    TextView mSubmitBtn;
    TextView mSubmitWithLogBtn;
    UploadUserData mUploadUserData;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        String stringExtra = getIntent().getStringExtra("extra_device_did");
        if (!TextUtils.isEmpty(stringExtra)) {
            this.mDevice = SmartHomeDeviceManager.a().b(stringExtra);
        }
        if (this.mDevice != null) {
            this.mModel = this.mDevice.model;
        } else {
            this.mModel = getIntent().getStringExtra("extra_device_model");
        }
        setContentView(R.layout.feedback_activity);
        ((TextView) findViewById(R.id.module_a_3_return_title)).setText(R.string.feedback);
        findViewById(R.id.module_a_3_return_btn).setOnClickListener(this);
        String feedbackDeviceName = FeedbackManager.INSTANCE.getFeedbackDeviceName(getContext(), this.mModel);
        if (feedbackDeviceName == null || feedbackDeviceName.isEmpty()) {
            feedbackDeviceName = getString(R.string.feedback);
        }
        ((TextView) findViewById(R.id.feedback_device_name)).setText(feedbackDeviceName);
        this.mCommitBtn = (TextView) findViewById(R.id.module_a_3_right_tv_text);
        this.mCommitBtn.setVisibility(8);
        this.mSubmitBtn = (TextView) findViewById(R.id.submit);
        this.mSubmitWithLogBtn = (TextView) findViewById(R.id.submit_with_log);
        this.mRouterModelEditText = (EditText) findViewById(R.id.et_router_model);
        this.mContentEditText = (EditText) findViewById(R.id.content);
        this.mContactEditText = (EditText) findViewById(R.id.et_contact);
        ServerBean F = CoreApi.a().F();
        if (CoreApi.a().D() && ServerCompact.d(F)) {
            this.mSubmitWithLogBtn.setVisibility(8);
            this.mSubmitBtn.setBackgroundResource(R.drawable.normal_confirm_button);
            this.mSubmitBtn.setTextColor(-1);
        }
        a();
        this.mContentEditText.requestFocus();
        this.c = (ImagePickerPreview) findViewById(R.id.image_pick_preview);
        if (ServerCompact.g((Context) this)) {
            this.c.setVisibility(8);
            findViewById(R.id.add_new_image).setVisibility(8);
        }
        this.mSubmitBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!FeedbackActivity.this.b()) {
                    FeedbackActivity.this.c();
                } else {
                    FeedbackActivity.this.a(false);
                }
            }
        });
        this.mSubmitWithLogBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                if (!FeedbackActivity.this.b()) {
                    FeedbackActivity.this.c();
                } else {
                    FeedbackActivity.this.a(true);
                }
            }
        });
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.c.onActivityResult(this, i, i2, intent);
    }

    private void a() {
        this.mContentEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString().trim()) && editable.toString().length() > 500) {
                    Toast.makeText(FeedbackActivity.this.mContext, FeedbackActivity.this.getResources().getQuantityString(R.plurals.feedback_content_length_tips, 500, new Object[]{500}), 0).show();
                    editable.delete(501, editable.toString().length());
                }
            }
        });
        this.mContactEditText.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString().trim()) && editable.toString().length() > 32) {
                    Toast.makeText(FeedbackActivity.this.mContext, FeedbackActivity.this.getResources().getQuantityString(R.plurals.feedback_contact_length_tips, 32, new Object[]{32}), 0).show();
                    editable.delete(33, editable.toString().length());
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean b() {
        String str = null;
        String trim = this.mContactEditText.getText() == null ? null : this.mContactEditText.getText().toString().trim();
        if (this.mContentEditText.getText() != null) {
            str = this.mContentEditText.getText().toString().trim();
        }
        return !TextUtils.isEmpty(trim) || !TextUtils.isEmpty(str);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.module_a_3_return_btn) {
            finish();
        }
    }

    /* access modifiers changed from: private */
    public void a(boolean z) {
        b(z);
    }

    private void b(final boolean z) {
        final XQProgressDialog a2 = XQProgressDialog.a(this.mContext, (CharSequence) null, getString(R.string.feedbacking));
        this.mUploadUserData = null;
        if (this.c.getPickedItems().length > 0 || z) {
            UserApi.a().a((Context) this, LogStrategyManager.ACTION_TYPE_FEEDBACK, ArchiveStreamFactory.g, (AsyncCallback<UploadUserData, Error>) new AsyncCallback<UploadUserData, Error>() {
                /* renamed from: a */
                public void onSuccess(UploadUserData uploadUserData) {
                    FeedbackActivity.this.mUploadUserData = uploadUserData;
                    CoreApi.a().a(FeedbackActivity.this.mUploadUserData.f16460a, FeedbackActivity.this.mModel, FeedbackActivity.this.c.getPickedItems(), z, (IClientCallback) new IClientCallback.Stub() {
                        public void onSuccess(Bundle bundle) throws RemoteException {
                            FeedbackActivity.this.sendFeedBack(a2, FeedbackActivity.this.mUploadUserData.b);
                        }

                        public void onFailure(Bundle bundle) throws RemoteException {
                            a2.dismiss();
                            Toast.makeText(FeedbackActivity.this.mContext, R.string.uploading_log_error, 0).show();
                        }
                    });
                }

                public void onFailure(Error error) {
                    a2.dismiss();
                    Toast.makeText(FeedbackActivity.this.mContext, R.string.uploading_log_error, 0).show();
                }
            });
        } else {
            sendFeedBack(a2, "");
        }
    }

    /* access modifiers changed from: package-private */
    public void sendFeedBack(final XQProgressDialog xQProgressDialog, String str) {
        String trim = this.mContentEditText.getText().toString().trim();
        if (TextUtils.isEmpty(trim)) {
            trim = "NULL";
        }
        String str2 = trim;
        String obj = this.mRouterModelEditText.getText().toString();
        if (obj != null) {
            obj = obj.trim();
        }
        String str3 = str;
        FeedbackApi.INSTANCE.sendFeedBack2(this, this.mDevice, this.mModel, str3, this.mContactEditText.getText().toString(), str2, obj, new AsyncCallback<Void, Error>() {
            /* renamed from: a */
            public void onSuccess(Void voidR) {
                xQProgressDialog.dismiss();
                Toast.makeText(FeedbackActivity.this.mContext, R.string.feedback_succ, 0).show();
                FeedbackActivity.this.finish();
            }

            public void onFailure(Error error) {
                xQProgressDialog.dismiss();
                Toast.makeText(FeedbackActivity.this.mContext, R.string.feedback_error, 0).show();
            }
        });
    }

    /* access modifiers changed from: private */
    public void c() {
        if (this.mCommitTipsDialog == null) {
            this.mCommitTipsDialog = new MLAlertDialog.Builder(this).a((CharSequence) getString(R.string.feedback_describe_ques_specific)).a((CharSequence) getString(R.string.feedback_confirm), (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).d();
        } else {
            this.mCommitTipsDialog.show();
        }
    }
}
