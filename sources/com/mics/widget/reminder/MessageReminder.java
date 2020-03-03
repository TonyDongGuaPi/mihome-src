package com.mics.widget.reminder;

import android.app.Activity;
import android.app.Application;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.coloros.mcssdk.PushManager;
import com.facebook.drawee.view.SimpleDraweeView;
import com.mics.core.MiCS;
import com.mics.core.data.business.ChatParams;
import com.mics.util.GsonUtil;
import com.mics.util.Logger;
import com.mics.widget.reminder.ReminderHeader;
import com.mics.widget.util.StatusBarUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MessageReminder {

    /* renamed from: a  reason: collision with root package name */
    private static volatile MessageReminder f7829a = null;
    private static final String b = "MICS_PUSH_BR_NOTIFICATION";
    private List<ActivityView> c;
    private ActivityView d;
    /* access modifiers changed from: private */
    public ActivityView e;
    private HeaderClick f = new HeaderClick();
    private LifecycleCallback g = new LifecycleCallback();
    private SoundPool h = null;
    /* access modifiers changed from: private */
    public int i = 0;
    /* access modifiers changed from: private */
    public Map<String, Integer> j = new HashMap();
    private int k = 0;
    private InnerPushBroadcastReceiver l;
    private IntentFilter m;
    private OnMessageReminder n;
    private IAction o;

    public interface IAction {
        void a(Activity activity, ChatParams chatParams);

        void a(String str, String str2, int i, PendingIntent pendingIntent);
    }

    public interface OnMessageReminder {
        void onMessage(boolean z, boolean z2, String str, String str2, String str3, String str4);
    }

    private MessageReminder() {
        ((Application) MiCS.a().h().getApplicationContext()).registerActivityLifecycleCallbacks(this.g);
        ReminderHeader.a((ReminderHeader.OnHideStatusChangedListener) new ReminderHeader.OnHideStatusChangedListener() {
            public void a() {
                Activity a2 = MessageReminder.this.h();
                if (a2 != null) {
                    a2.getWindow().clearFlags(128);
                }
                MessageReminder.this.e();
            }
        });
    }

    public static MessageReminder a() {
        if (f7829a == null) {
            synchronized (MessageReminder.class) {
                if (f7829a == null) {
                    f7829a = new MessageReminder();
                }
            }
        }
        return f7829a;
    }

    public static void b() {
        a();
    }

    public void c() {
        this.f = null;
        ((Application) MiCS.a().h().getApplicationContext()).unregisterActivityLifecycleCallbacks(this.g);
        f7829a = null;
    }

    public void a(boolean z, String str, String str2, String str3, String str4) {
        a(z, z, str, str2, str3, str4);
    }

    public void a(IAction iAction) {
        this.o = iAction;
    }

    public void a(OnMessageReminder onMessageReminder) {
        this.n = onMessageReminder;
    }

    public void a(boolean z, boolean z2, String str, String str2, String str3, String str4) {
        if (this.n != null) {
            this.n.onMessage(z, z2, str, str2, str3, str4);
        }
        if (b((Context) h())) {
            i();
            Logger.a("background registerReceiver().", new Object[0]);
            a((Context) h(), str, str3, str4);
            d();
            return;
        }
        g();
        if (!z && f()) {
            this.e.c = str4;
            View a2 = this.e.a();
            a(a2, str, str2, str3);
            ReminderHeader.a(a2);
            Activity h2 = h();
            if (h2 != null) {
                h2.getWindow().addFlags(128);
            }
            d();
        } else if (!z2) {
            d();
        }
    }

    private void a(Context context, String str, String str2, String str3) {
        int b2 = b(str3);
        Intent intent = new Intent();
        intent.setAction(b);
        intent.putExtra("chat_messenger", new InnerTarget(b2, str3));
        a(str, str2, b2, PendingIntent.getBroadcast(context, UUID.randomUUID().hashCode(), intent, 134217728));
    }

    private void a(String str, String str2, int i2, PendingIntent pendingIntent) {
        if (this.o != null) {
            this.o.a(str, str2, i2, pendingIntent);
        }
    }

    public static void a(Context context) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    public static void a(Context context, String str) {
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(PushManager.MESSAGE_TYPE_NOTI);
        if (notificationManager != null) {
            notificationManager.cancel(a().b(str));
            a().j.remove("" + str);
        }
    }

    private int b(String str) {
        String str2 = "" + str;
        Integer num = this.j.get(str2);
        if (num == null) {
            int i2 = this.k;
            this.k = i2 + 1;
            num = Integer.valueOf(i2);
            this.j.put(str2, num);
        }
        return num.intValue();
    }

    private class InnerPushBroadcastReceiver extends BroadcastReceiver {
        private InnerPushBroadcastReceiver() {
        }

        public void onReceive(Context context, Intent intent) {
            Context applicationContext = context.getApplicationContext();
            Intent launchIntentForPackage = applicationContext.getPackageManager().getLaunchIntentForPackage(applicationContext.getPackageName());
            context.startActivity(launchIntentForPackage);
            Object[] objArr = new Object[1];
            objArr[0] = launchIntentForPackage == null ? "null" : launchIntentForPackage.toString();
            Logger.a("Notification 拉起 APP。intent = %s.", objArr);
            if (intent.getSerializableExtra("chat_messenger") instanceof InnerTarget) {
                InnerTarget innerTarget = (InnerTarget) intent.getSerializableExtra("chat_messenger");
                if (((Integer) MessageReminder.a().j.remove("" + innerTarget.merchantId)) != null) {
                    MessageReminder.this.a(MessageReminder.this.h(), innerTarget.merchantId);
                }
            }
        }
    }

    private static class InnerTarget implements Serializable {
        int id;
        String merchantId;

        InnerTarget(int i, String str) {
            this.id = i;
            this.merchantId = str;
        }

        /* access modifiers changed from: package-private */
        public String getMerchantId() {
            return this.merchantId;
        }

        public int getId() {
            return this.id;
        }

        public String toString() {
            return "InnerTarget{merchantId='" + this.merchantId + Operators.SINGLE_QUOTE + ", id=" + this.id + Operators.BLOCK_END;
        }
    }

    private void d() {
        if (this.h == null) {
            this.h = new SoundPool(10, 5, 0);
            this.i = this.h.load(ReminderResource.d(), 1);
            this.h.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
                public void onLoadComplete(SoundPool soundPool, int i, int i2) {
                    soundPool.play(MessageReminder.this.i, 1.0f, 1.0f, 1, 0, 1.0f);
                }
            });
        } else {
            this.h.play(this.i, 1.0f, 1.0f, 1, 0, 1.0f);
        }
        if (MiCS.a().h() != null && MiCS.a().c()) {
            Vibrator vibrator = (Vibrator) MiCS.a().h().getSystemService("vibrator");
            boolean b2 = b((Context) h());
            long[] jArr = {50, (long) (b2 ? 60 : 8), 200, (long) (b2 ? 40 : 6)};
            long[] d2 = MiCS.a().d();
            if (d2 == null) {
                d2 = jArr;
            }
            if (vibrator != null) {
                if (Build.VERSION.SDK_INT >= 26) {
                    int[] iArr = new int[d2.length];
                    for (int i2 = 0; i2 < d2.length / 2; i2++) {
                        iArr[(i2 * 2) + 1] = 255;
                    }
                    vibrator.vibrate(VibrationEffect.createWaveform(d2, iArr, -1));
                } else {
                    vibrator.vibrate(d2, -1);
                }
            }
            Logger.a("震动了啊", new Object[0]);
        }
    }

    /* access modifiers changed from: private */
    public void e() {
        if (this.h != null) {
            this.h.release();
            this.h = null;
        }
    }

    private void a(View view, String str, String str2, String str3) {
        TextView textView;
        TextView textView2;
        ((SimpleDraweeView) view.findViewById(201802)).setImageURI(str2);
        if (!TextUtils.isEmpty(str) && (textView2 = (TextView) view.findViewById(201804)) != null) {
            textView2.setText(str);
        }
        if (!TextUtils.isEmpty(str3) && (textView = (TextView) view.findViewById(201805)) != null) {
            textView.setText(str3);
        }
    }

    /* access modifiers changed from: private */
    public boolean f() {
        return this.e != null && !this.e.d();
    }

    private void g() {
        ViewGroup viewGroup;
        Activity h2 = h();
        if (h2 != null && (viewGroup = (ViewGroup) h2.getWindow().getDecorView()) != null) {
            View a2 = a(h2);
            if (viewGroup.indexOfChild(a2) == -1) {
                viewGroup.addView(a2);
            }
        }
    }

    private View a(Activity activity) {
        if (this.e.a() != null) {
            return this.e.a();
        }
        View a2 = ReminderHeader.a((Context) activity);
        a(a2);
        this.e.a(a2);
        return a2;
    }

    private void a(View view) {
        view.setOnClickListener(this.f);
        int a2 = StatusBarUtils.a(view.getContext());
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(201800);
        if (relativeLayout != null) {
            relativeLayout.setPadding(relativeLayout.getPaddingLeft(), a2, relativeLayout.getPaddingRight(), relativeLayout.getPaddingBottom());
            relativeLayout.setVisibility(8);
        }
    }

    /* access modifiers changed from: private */
    public Activity h() {
        if (this.e != null) {
            return this.e.b();
        }
        return null;
    }

    public boolean b(Context context) {
        return context != null && (this.c == null || this.c.size() == 0);
    }

    private class HeaderClick implements View.OnClickListener {
        private HeaderClick() {
        }

        public void onClick(View view) {
            if (MessageReminder.this.f()) {
                MessageReminder.this.a(MessageReminder.this.e.b(), MessageReminder.this.e.c);
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Activity activity, String str) {
        if (str != null) {
            ChatParams chatParams = new ChatParams();
            chatParams.setMerchantId(str);
            a(activity, chatParams);
        }
    }

    private void a(Activity activity, ChatParams chatParams) {
        if (this.o != null) {
            this.o.a(activity, chatParams);
        }
    }

    public boolean a(String str) {
        Intent intent;
        ChatParams chatParams;
        Activity h2 = h();
        if (!(h2 == null || (intent = h2.getIntent()) == null || (chatParams = (ChatParams) GsonUtil.a(intent.getStringExtra(UrlConstants.customerService), (Type) ChatParams.class)) == null)) {
            String merchantId = chatParams.getMerchantId();
            if (str == null || !str.equals(merchantId)) {
                return false;
            }
            return true;
        }
        return false;
    }

    private class LifecycleCallback implements Application.ActivityLifecycleCallbacks {
        public void onActivityCreated(Activity activity, Bundle bundle) {
        }

        public void onActivityDestroyed(Activity activity) {
        }

        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
        }

        public void onActivityStarted(Activity activity) {
        }

        public void onActivityStopped(Activity activity) {
        }

        private LifecycleCallback() {
        }

        public void onActivityResumed(Activity activity) {
            MessageReminder.this.b(activity);
        }

        public void onActivityPaused(Activity activity) {
            MessageReminder.this.d(activity);
        }
    }

    /* access modifiers changed from: private */
    public void b(Activity activity) {
        c(activity);
        if (this.c == null) {
            this.c = new ArrayList();
        }
        if (this.c.size() > 0) {
            for (ActivityView b2 : this.c) {
                Activity b3 = b2.b();
                if (b3 != null) {
                    String name = b3.getClass().getName();
                    if (name.equals("com.xiaomi.youpin.activity.YouPinMainTabActivity") || name.equals("com.xiaomi.smarthome.SmartHomeMainActivity")) {
                        return;
                    }
                }
            }
        }
        if (this.d != null) {
            this.d.c();
            this.d = null;
        }
        this.e = new ActivityView(this, activity);
        this.d = new ActivityView(this, this.e);
        this.c.add(this.e);
    }

    private void c(Activity activity) {
        if (this.l == null) {
            this.l = new InnerPushBroadcastReceiver();
            this.m = new IntentFilter();
            this.m.addAction(b);
        }
        this.l.isOrderedBroadcast();
        activity.registerReceiver(this.l, this.m);
    }

    /* access modifiers changed from: private */
    public void d(Activity activity) {
        e(activity);
        if (this.c != null) {
            Iterator<ActivityView> it = this.c.iterator();
            while (it.hasNext()) {
                Activity b2 = it.next().b();
                if (b2 != null && b2.getClass().getName().equals(activity.getClass().getName())) {
                    it.remove();
                }
            }
        }
    }

    private void e(Activity activity) {
        try {
            activity.unregisterReceiver(this.l);
        } catch (Exception unused) {
        }
    }

    private void i() {
        Activity h2 = h();
        if (h2 != null && b((Context) h2)) {
            c(h2);
        }
    }

    private class ActivityView {

        /* renamed from: a  reason: collision with root package name */
        WeakReference<Activity> f7832a;
        WeakReference<View> b;
        String c;

        ActivityView(MessageReminder messageReminder, ActivityView activityView) {
            this(activityView.b(), activityView.a());
        }

        ActivityView(MessageReminder messageReminder, Activity activity) {
            this(activity, (View) null);
        }

        ActivityView(Activity activity, View view) {
            this.f7832a = new WeakReference<>(activity);
            this.b = new WeakReference<>(view);
        }

        /* access modifiers changed from: package-private */
        public void a(View view) {
            this.b = new WeakReference<>(view);
        }

        /* access modifiers changed from: package-private */
        public View a() {
            if (this.b == null) {
                return null;
            }
            return (View) this.b.get();
        }

        /* access modifiers changed from: package-private */
        public Activity b() {
            if (this.f7832a == null) {
                return null;
            }
            return (Activity) this.f7832a.get();
        }

        /* access modifiers changed from: package-private */
        public void c() {
            if (!d()) {
                View findViewById = ((Activity) this.f7832a.get()).findViewById(201800);
                if (findViewById != null) {
                    findViewById.clearAnimation();
                    ReminderHeader.a(findViewById, 400);
                }
                this.f7832a.clear();
            }
            this.f7832a = null;
        }

        /* access modifiers changed from: package-private */
        public boolean d() {
            return this.f7832a == null || this.f7832a.get() == null;
        }

        private void a(Activity activity, View view) {
            ViewGroup viewGroup;
            if (activity != null && (viewGroup = (ViewGroup) activity.getWindow().getDecorView()) != null) {
                viewGroup.removeView(view);
            }
        }
    }
}
