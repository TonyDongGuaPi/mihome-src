package com.xiaomi.jr.dialog;

import android.app.Activity;
import android.arch.lifecycle.DefaultLifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import com.google.android.exoplayer2.C;
import com.xiaomi.jr.common.app.ActivityChecker;
import com.xiaomi.jr.common.lifecycle.LifecycledObjects;
import com.xiaomi.jr.common.utils.MifiLog;
import com.xiaomi.jr.common.utils.Utils;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Queue;

public class DialogManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10378a = "DialogManager";
    private static Queue<DialogInfo> b = new LinkedList();
    private static DialogInfo c;
    private static Handler d = new Handler(Looper.getMainLooper());

    static class DialogInfo {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<Context> f10379a;
        DialogFragment b;
        String c;

        DialogInfo() {
        }
    }

    public static synchronized void a(DialogFragment dialogFragment, Context context, String str) {
        synchronized (DialogManager.class) {
            if (context != null) {
                d.post(new Runnable(context, dialogFragment, str) {
                    private final /* synthetic */ Context f$0;
                    private final /* synthetic */ DialogFragment f$1;
                    private final /* synthetic */ String f$2;

                    {
                        this.f$0 = r1;
                        this.f$1 = r2;
                        this.f$2 = r3;
                    }

                    public final void run() {
                        DialogManager.a(this.f$0, this.f$1, this.f$2);
                    }
                });
            } else {
                throw new IllegalArgumentException("context should not be null.");
            }
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(Context context, DialogFragment dialogFragment, String str) {
        DialogInfo dialogInfo = new DialogInfo();
        dialogInfo.f10379a = new WeakReference<>(context);
        dialogInfo.b = dialogFragment;
        dialogInfo.c = str;
        b.add(dialogInfo);
        b();
    }

    public static synchronized void a(DialogFragment dialogFragment) {
        synchronized (DialogManager.class) {
            d.post(new Runnable() {
                public final void run() {
                    DialogManager.b(DialogFragment.this);
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void b(DialogFragment dialogFragment) {
        if (c == null || c.b != dialogFragment) {
            for (DialogInfo dialogInfo : b) {
                if (dialogInfo.b == dialogFragment) {
                    b.remove(dialogInfo);
                }
                c(dialogInfo);
            }
            return;
        }
        c(c);
        c = null;
    }

    private static boolean b(DialogInfo dialogInfo) {
        Context context = (Context) dialogInfo.f10379a.get();
        if (context instanceof Activity) {
            return ActivityChecker.a((Activity) context);
        }
        return false;
    }

    static void a(DialogInfo dialogInfo) {
        if (dialogInfo == null || !b(dialogInfo)) {
            b();
            return;
        }
        dialogInfo.b.getLifecycle().a(new DialogLifecycleObserver(dialogInfo));
        Utils.a(dialogInfo.b, ((FragmentActivity) dialogInfo.f10379a.get()).getSupportFragmentManager(), dialogInfo.c);
    }

    private static class DialogLifecycleObserver implements DefaultLifecycleObserver {

        /* renamed from: a  reason: collision with root package name */
        private DialogInfo f10380a;

        public /* synthetic */ void a(@NonNull LifecycleOwner lifecycleOwner) {
            DefaultLifecycleObserver.CC.$default$a(this, lifecycleOwner);
        }

        public /* synthetic */ void b(@NonNull LifecycleOwner lifecycleOwner) {
            DefaultLifecycleObserver.CC.$default$b(this, lifecycleOwner);
        }

        public /* synthetic */ void c(@NonNull LifecycleOwner lifecycleOwner) {
            DefaultLifecycleObserver.CC.$default$c(this, lifecycleOwner);
        }

        public /* synthetic */ void d(@NonNull LifecycleOwner lifecycleOwner) {
            DefaultLifecycleObserver.CC.$default$d(this, lifecycleOwner);
        }

        public /* synthetic */ void e(@NonNull LifecycleOwner lifecycleOwner) {
            DefaultLifecycleObserver.CC.$default$e(this, lifecycleOwner);
        }

        DialogLifecycleObserver(DialogInfo dialogInfo) {
            this.f10380a = dialogInfo;
        }

        public void f(@NonNull LifecycleOwner lifecycleOwner) {
            DialogManager.b();
            FragmentActivity fragmentActivity = (FragmentActivity) this.f10380a.f10379a.get();
            if (fragmentActivity instanceof DialogActivity) {
                LifecycledObjects.a(Integer.valueOf(this.f10380a.hashCode()));
                fragmentActivity.finish();
            }
            lifecycleOwner.getLifecycle().b(this);
        }
    }

    /* access modifiers changed from: private */
    public static synchronized void b() {
        synchronized (DialogManager.class) {
            c = b.poll();
            if (c != null) {
                if (b(c)) {
                    a(c);
                } else {
                    Context context = (Context) c.f10379a.get();
                    LifecycledObjects.a(c, context);
                    Intent intent = new Intent(context, DialogActivity.class);
                    intent.putExtra(DialogActivity.KEY_DIALOG_INFO_ID, c.hashCode());
                    intent.setFlags(C.ENCODING_PCM_MU_LAW);
                    Utils.a(context, intent);
                    context.startActivity(intent);
                }
            }
        }
    }

    private static void c(DialogInfo dialogInfo) {
        DialogFragment dialogFragment = dialogInfo.b;
        try {
            dialogFragment.dismissAllowingStateLoss();
        } catch (Exception unused) {
            MifiLog.e(f10378a, "dismiss " + dialogFragment.hashCode() + " fail.");
        } catch (Throwable th) {
            dialogInfo.b = null;
            throw th;
        }
        dialogInfo.b = null;
    }
}
