package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.account.diagnosis.task.CollectAndUploadDiagnosisTask;
import com.xiaomi.accountsdk.request.InvalidResponseException;
import com.xiaomi.miot.support.monitor.core.BaseInfo;
import com.xiaomi.passport.ui.R;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0016\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0012\u001a\u00020\u0013J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002JV\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u000b26\u0010\u001e\u001a2\u0012\u0013\u0012\u00110\r¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(\"\u0012\u0013\u0012\u00110\r¢\u0006\f\b \u0012\b\b!\u0012\u0004\b\b(#\u0012\u0004\u0012\u00020\u000f0\u001fJ \u0010$\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020%2\u0006\u0010&\u001a\u00020\rH\u0002J \u0010'\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010(\u001a\u00020%H\u0002J\u0018\u0010)\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\rH\u0002J\u0018\u0010*\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010+\u001a\u00020%H\u0002J\u0018\u0010,\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u001a\u0010-\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u0018\u0010.\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010/\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\b¨\u00060"}, d2 = {"Lcom/xiaomi/passport/ui/internal/CommonErrorHandler;", "", "()V", "passportRepo", "Lcom/xiaomi/passport/ui/internal/PassportRepo;", "getPassportRepo", "()Lcom/xiaomi/passport/ui/internal/PassportRepo;", "setPassportRepo", "(Lcom/xiaomi/passport/ui/internal/PassportRepo;)V", "getCaptcha", "Lcom/xiaomi/passport/ui/internal/Source;", "Lcom/xiaomi/passport/ui/internal/Captcha;", "url", "", "onIOError", "", "e", "Ljava/io/IOException;", "context", "Landroid/content/Context;", "view", "Landroid/view/View;", "onUnKnowError", "tr", "", "showBigTh", "showCaptcha", "layoutInflater", "Landroid/view/LayoutInflater;", "captcha", "callback", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "code", "ick", "showError", "", "msg", "showErrorWithLog", "msgId", "showLittleTh", "showMidTh", "resId", "showUnknownError", "showUnknownHostException", "showUnknownIOExceptionWithLog", "uploadLog", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class CommonErrorHandler {
    @NotNull
    private PassportRepo passportRepo = new PassportRepoImpl();

    @NotNull
    public final PassportRepo getPassportRepo() {
        return this.passportRepo;
    }

    public final void setPassportRepo(@NotNull PassportRepo passportRepo2) {
        Intrinsics.f(passportRepo2, "<set-?>");
        this.passportRepo = passportRepo2;
    }

    /* access modifiers changed from: private */
    public final void onIOError(IOException iOException, Context context) {
        onIOError(iOException, context, (View) null);
    }

    public final void onIOError(@NotNull IOException iOException, @NotNull Context context, @Nullable View view) {
        Intrinsics.f(iOException, "e");
        Intrinsics.f(context, "context");
        if (iOException instanceof UnknownHostException) {
            showUnknownHostException(context, view);
        } else if (iOException instanceof SocketTimeoutException) {
            showErrorWithLog(context, iOException, R.string.passport_timeout_network_error);
        } else {
            showUnknownIOExceptionWithLog(context, iOException);
        }
    }

    public final void onUnKnowError(@NotNull Throwable th, @NotNull Context context) {
        Intrinsics.f(th, BaseInfo.KEY_TIME_RECORD);
        Intrinsics.f(context, "context");
        if (th instanceof RuntimeException) {
            throw th;
        } else if (!(th instanceof Error)) {
            showUnknownError(context, th);
        } else {
            throw th;
        }
    }

    private final void showUnknownIOExceptionWithLog(Context context, IOException iOException) {
        showErrorWithLog(context, iOException, R.string.passport_unknow_network_error);
    }

    private final void showUnknownHostException(Context context, View view) {
        if (view != null) {
            String string = context.getString(R.string.passport_unknow_host_network_error);
            Intrinsics.b(string, "context.getString(R.stri…nknow_host_network_error)");
            showLittleTh(view, string);
            return;
        }
        showMidTh(context, R.string.passport_unknow_host_network_error);
    }

    private final void showUnknownError(Context context, Throwable th) {
        if (th instanceof InvalidResponseException) {
            InvalidResponseException invalidResponseException = (InvalidResponseException) th;
            if (invalidResponseException.code == 10031) {
                int i = invalidResponseException.code;
                String str = invalidResponseException.codeDesc;
                Intrinsics.b(str, "tr.codeDesc");
                showError(context, i, str);
                return;
            }
        }
        showErrorWithLog(context, th, R.string.passport_unknow_error);
    }

    private final void showErrorWithLog(Context context, Throwable th, int i) {
        AlertDialog create = new AlertDialog.Builder(context).setTitle(i).setMessage((CharSequence) th.toString()).setNegativeButton(R.string.upload_error_log, (DialogInterface.OnClickListener) new CommonErrorHandler$showErrorWithLog$dialog$1(this, context, th)).setPositiveButton(R.string.passport_log_detail, (DialogInterface.OnClickListener) new CommonErrorHandler$showErrorWithLog$dialog$2(this, context, th)).create();
        boolean z = false;
        create.setCanceledOnTouchOutside(false);
        create.setCancelable(false);
        if ((context instanceof Activity) && ((Activity) context).isFinishing()) {
            z = true;
        }
        if (!z) {
            create.show();
            View findViewById = create.findViewById(16908299);
            if (findViewById != null) {
                TextView textView = (TextView) findViewById;
                textView.setTextSize(2, 10.0f);
                textView.setTextIsSelectable(true);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
    }

    private final void showError(Context context, int i, String str) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage((CharSequence) "" + str + " (" + i + Operators.BRACKET_END).setPositiveButton(17039370, (DialogInterface.OnClickListener) CommonErrorHandler$showError$dialog$1.INSTANCE).create().show();
    }

    private final void showLittleTh(View view, String str) {
        Snackbar.make(view, (CharSequence) str, 0).show();
    }

    private final void showMidTh(Context context, int i) {
        if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
            new AlertDialog.Builder(context).setMessage(i).create().show();
        }
    }

    /* access modifiers changed from: private */
    public final void showBigTh(Context context, Throwable th) {
        AlertDialog create = new AlertDialog.Builder(context).setMessage((CharSequence) Log.getStackTraceString(th)).setPositiveButton(R.string.passport_close, (DialogInterface.OnClickListener) null).setNegativeButton(R.string.upload_error_log, (DialogInterface.OnClickListener) new CommonErrorHandler$showBigTh$dialog$1(this, context, th)).setCancelable(false).create();
        create.setCanceledOnTouchOutside(false);
        create.show();
        View findViewById = create.findViewById(16908299);
        if (findViewById != null) {
            TextView textView = (TextView) findViewById;
            textView.setTextSize(2, 9.0f);
            textView.setTextIsSelectable(true);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
    }

    /* access modifiers changed from: private */
    public final void uploadLog(Context context, Throwable th) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.sending));
        progressDialog.setCancelable(false);
        progressDialog.show();
        new CollectAndUploadDiagnosisTask(new CommonErrorHandler$uploadLog$task$1(context, progressDialog), false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    public final void showCaptcha(@NotNull Context context, @NotNull LayoutInflater layoutInflater, @NotNull Captcha captcha, @NotNull Function2<? super String, ? super String, Unit> function2) {
        Intrinsics.f(context, "context");
        Intrinsics.f(layoutInflater, "layoutInflater");
        Intrinsics.f(captcha, "captcha");
        Intrinsics.f(function2, "callback");
        View inflate = layoutInflater.inflate(R.layout.dg_captcha_layout, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.captcha_input);
        if (findViewById != null) {
            EditText editText = (EditText) findViewById;
            View findViewById2 = inflate.findViewById(R.id.captcha_image);
            if (findViewById2 != null) {
                ImageView imageView = (ImageView) findViewById2;
                Ref.ObjectRef objectRef = new Ref.ObjectRef();
                objectRef.element = captcha.getIck();
                imageView.setImageBitmap(captcha.getBitmap());
                imageView.setOnClickListener(new CommonErrorHandler$showCaptcha$1(this, captcha, imageView, objectRef, context));
                new AlertDialog.Builder(context).setTitle(R.string.passport_captcha_title).setView(inflate).setPositiveButton(17039370, (DialogInterface.OnClickListener) new CommonErrorHandler$showCaptcha$dialog$1(function2, editText, objectRef)).create().show();
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.widget.ImageView");
        }
        throw new TypeCastException("null cannot be cast to non-null type android.widget.EditText");
    }

    /* access modifiers changed from: private */
    public final Source<Captcha> getCaptcha(String str) {
        return this.passportRepo.getCaptchaImage(str);
    }
}
