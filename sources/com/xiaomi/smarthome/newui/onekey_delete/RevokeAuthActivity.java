package com.xiaomi.smarthome.newui.onekey_delete;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.dialog.MLAlertDialog;
import com.xiaomi.smarthome.newui.onekey_delete.DeleteAllPrivacyApi;
import com.xiaomi.smarthome.newui.onekey_delete.RevokeAuthActivity;
import com.xiaomi.smarthome.operation.js_sdk.share.LoadingDialogHelper;
import com.xiaomi.smarthome.shop.utils.ToastUtil;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.Completable;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.subjects.CompletableSubject;
import java.util.concurrent.TimeUnit;

public class RevokeAuthActivity extends BaseActivity {
    public static final String ARG_AUTH_TOKEN = "arg_auth_token";

    /* renamed from: a  reason: collision with root package name */
    private static final String f20701a = "RevokeAuthActivity";
    private static final int b = 136;
    private final CompositeDisposable c = new CompositeDisposable();
    private LoadingDialogHelper d = new LoadingDialogHelper(this);
    private String e;
    private CompletableSubject f;

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(new FrameLayout(this));
        this.c.add(a().andThen((CompletableSource) b()).andThen((CompletableSource) c()).andThen((CompletableSource) d()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action() {
            public final void run() {
                RevokeAuthActivity.this.g();
            }
        }, new Consumer() {
            public final void accept(Object obj) {
                RevokeAuthActivity.this.a((Throwable) obj);
            }
        }));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void g() throws Exception {
        LogUtil.a(f20701a, "revoke finished: success: ");
        setResult(-1, new Intent());
        finish();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Throwable th) throws Exception {
        if (!(th instanceof CancelByUserException)) {
            ToastUtil.a(getContext(), (int) R.string.failed);
        }
        LogUtil.a(f20701a, "revoke failed >> " + th.getLocalizedMessage());
        finish();
    }

    private Completable a() {
        CompletableSubject create = CompletableSubject.create();
        return create.doOnSubscribe(new Consumer(create) {
            private final /* synthetic */ CompletableSubject f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                RevokeAuthActivity.this.d(this.f$1, (Disposable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void d(CompletableSubject completableSubject, Disposable disposable) throws Exception {
        STAT.c.m();
        LogUtil.a(f20701a, "showFirstAlertDialog: ");
        View inflate = View.inflate(getContext(), R.layout.revoke_auth_dialog_content, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.title)).setText(R.string.revoke_auth_title);
        ((TextView) inflate.findViewById(R.id.msg)).setText(R.string.revoke_auth_msg);
        MLAlertDialog b2 = new MLAlertDialog.Builder(getContext()).b(inflate).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                RevokeAuthActivity.d(CompletableSubject.this, dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CompletableSubject.this.onError(new RevokeAuthActivity.CancelByUserException("canceled at showFirstAlertDialog."));
            }
        }).a(false).b();
        b2.show();
        Button button = b2.getButton(-1);
        button.setEnabled(false);
        button.setTextColor(getResources().getColor(R.color.sub_title_text_color));
        Observable<R> observeOn = a(10).takeWhile(new Predicate() {
            public final boolean test(Object obj) {
                return RevokeAuthActivity.a(Disposable.this, (Long) obj);
            }
        }).map(new Function() {
            public final Object apply(Object obj) {
                return RevokeAuthActivity.this.a((Long) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread());
        button.getClass();
        $$Lambda$21RFyOA5k8VeZFT2I2pHWl4hpI r1 = new Consumer(button) {
            private final /* synthetic */ Button f$0;

            {
                this.f$0 = r1;
            }

            public final void accept(Object obj) {
                this.f$0.setText((String) obj);
            }
        };
        completableSubject.getClass();
        this.c.add(observeOn.subscribe(r1, new Consumer() {
            public final void accept(Object obj) {
                CompletableSubject.this.onError((Throwable) obj);
            }
        }, new Action(button) {
            private final /* synthetic */ Button f$0;

            {
                this.f$0 = r1;
            }

            public final void run() {
                this.f$0.postDelayed(new Runnable(this.f$0) {
                    private final /* synthetic */ Button f$0;

                    {
                        this.f$0 = r1;
                    }

                    public final void run() {
                        RevokeAuthActivity.b(this.f$0);
                    }
                }, 1000);
            }
        }));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void d(CompletableSubject completableSubject, DialogInterface dialogInterface, int i) {
        completableSubject.onComplete();
        STAT.d.bn();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ boolean a(Disposable disposable, Long l) throws Exception {
        return !disposable.isDisposed();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ String a(Long l) throws Exception {
        return getString(R.string.next) + Operators.BRACKET_START_STR + l + Operators.BRACKET_END_STR;
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(Button button) {
        button.setEnabled(true);
        button.setText(R.string.next);
    }

    private Completable b() {
        CompletableSubject create = CompletableSubject.create();
        return create.doOnSubscribe(new Consumer(create) {
            private final /* synthetic */ CompletableSubject f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                RevokeAuthActivity.this.c(this.f$1, (Disposable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void c(CompletableSubject completableSubject, Disposable disposable) throws Exception {
        LogUtil.a(f20701a, "showRequestTokenWebView: ");
        this.c.add(DeleteAllPrivacyApi.a().a("").observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer() {
            public final void accept(Object obj) {
                RevokeAuthActivity.this.b((Disposable) obj);
            }
        }).subscribe(new Consumer(completableSubject) {
            private final /* synthetic */ CompletableSubject f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                RevokeAuthActivity.this.b(this.f$1, (DeleteAllPrivacyApi.DeleteResult) obj);
            }
        }, new Consumer(completableSubject) {
            private final /* synthetic */ CompletableSubject f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                RevokeAuthActivity.this.b(this.f$1, (Throwable) obj);
            }
        }, new Action() {
            public final void run() {
                RevokeAuthActivity.this.f();
            }
        }));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(Disposable disposable) throws Exception {
        this.d.a();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(CompletableSubject completableSubject, DeleteAllPrivacyApi.DeleteResult deleteResult) throws Exception {
        if (deleteResult.f20698a == -4005001) {
            String str = deleteResult.b;
            if (!TextUtils.isEmpty(str)) {
                a(completableSubject, str);
                return;
            }
            return;
        }
        completableSubject.onError(new IllegalStateException("showRequestTokenWebView error: code = " + deleteResult.f20698a + " ; message: " + deleteResult.b));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(CompletableSubject completableSubject, Throwable th) throws Exception {
        completableSubject.onError(th);
        this.d.c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void f() throws Exception {
        this.d.c();
    }

    private Completable c() {
        CompletableSubject create = CompletableSubject.create();
        return create.doOnSubscribe(new Consumer(create) {
            private final /* synthetic */ CompletableSubject f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                RevokeAuthActivity.this.b(this.f$1, (Disposable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void b(CompletableSubject completableSubject, Disposable disposable) throws Exception {
        STAT.c.n();
        LogUtil.a(f20701a, "showConfirmAlertDialogAgain: ");
        View inflate = View.inflate(getContext(), R.layout.revoke_auth_dialog_content, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.title)).setText(R.string.reassure_dialog_title);
        ((TextView) inflate.findViewById(R.id.msg)).setText(R.string.revoke_auth_confirm_msg);
        MLAlertDialog b2 = new MLAlertDialog.Builder(getContext()).b(inflate).a((int) R.string.ok_button, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                RevokeAuthActivity.b(CompletableSubject.this, dialogInterface, i);
            }
        }).b((int) R.string.cancel, (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            public final void onClick(DialogInterface dialogInterface, int i) {
                CompletableSubject.this.onError(new RevokeAuthActivity.CancelByUserException("canceled cat showConfirmAlertDialogAgain."));
            }
        }).a(false).b();
        b2.show();
        b2.getButton(-1).setTextColor(getResources().getColor(R.color.sub_title_text_color));
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(CompletableSubject completableSubject, DialogInterface dialogInterface, int i) {
        completableSubject.onComplete();
        STAT.d.bo();
    }

    private Completable d() {
        CompletableSubject create = CompletableSubject.create();
        return create.doOnSubscribe(new Consumer(create) {
            private final /* synthetic */ CompletableSubject f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                RevokeAuthActivity.this.a(this.f$1, (Disposable) obj);
            }
        });
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(CompletableSubject completableSubject, Disposable disposable) throws Exception {
        LogUtil.a(f20701a, "requestDeleteServerData: ");
        this.c.add(DeleteAllPrivacyApi.a().a(this.e).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer() {
            public final void accept(Object obj) {
                RevokeAuthActivity.this.a((Disposable) obj);
            }
        }).subscribe(new Consumer() {
            public final void accept(Object obj) {
                RevokeAuthActivity.a(CompletableSubject.this, (DeleteAllPrivacyApi.DeleteResult) obj);
            }
        }, new Consumer(completableSubject) {
            private final /* synthetic */ CompletableSubject f$1;

            {
                this.f$1 = r2;
            }

            public final void accept(Object obj) {
                RevokeAuthActivity.this.a(this.f$1, (Throwable) obj);
            }
        }, new Action() {
            public final void run() {
                RevokeAuthActivity.this.e();
            }
        }));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(Disposable disposable) throws Exception {
        this.d.a();
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(CompletableSubject completableSubject, DeleteAllPrivacyApi.DeleteResult deleteResult) throws Exception {
        if (deleteResult.f20698a == 0) {
            completableSubject.onComplete();
            return;
        }
        completableSubject.onError(new IllegalStateException("requestDeleteServerData failed: code = " + deleteResult.f20698a + " ; message: " + deleteResult.b));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void a(CompletableSubject completableSubject, Throwable th) throws Exception {
        completableSubject.onError(th);
        this.d.c();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void e() throws Exception {
        this.d.c();
    }

    private Observable<Long> a(int i) {
        return Observable.interval(0, 1, TimeUnit.SECONDS).take((long) i).map(new Function(i) {
            private final /* synthetic */ int f$0;

            {
                this.f$0 = r1;
            }

            public final Object apply(Object obj) {
                return Long.valueOf(((long) this.f$0) - ((Long) obj).longValue());
            }
        });
    }

    private void a(CompletableSubject completableSubject, String str) {
        this.f = completableSubject;
        Intent intent = new Intent(getContext(), AuthTokenActivity.class);
        intent.putExtra("url", str);
        startActivityForResult(intent, 136);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i != 136) {
            return;
        }
        if (i2 != -1 || intent == null) {
            this.f.onError(new CancelByUserException("get token cancel."));
            return;
        }
        this.e = intent.getStringExtra(ARG_AUTH_TOKEN);
        if (TextUtils.isEmpty(this.e)) {
            this.f.onError(new AuthenticationFailureException("empty auth token!"));
        } else {
            this.f.onComplete();
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        super.onDestroy();
        this.c.clear();
    }

    private static class CancelByUserException extends Exception {
        CancelByUserException(String str) {
            super(str);
        }
    }
}
