package com.mics.core.business;

import android.support.annotation.WorkerThread;
import com.mics.constant.API;
import com.mics.core.business.ChatDataSource;
import com.mics.core.data.response.SessionCreateResponse;
import com.mics.network.NetworkManager;
import com.mics.util.Logger;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

public abstract class ChatRoom {

    /* renamed from: a  reason: collision with root package name */
    protected MessageReminder f7643a;
    private long b = 0;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private long h;
    private long i;
    private String j;
    private boolean k;
    private WeakReference<IChatView> l;
    private LifecycleCallback m;
    private boolean n;

    public interface LifecycleCallback {
        void a(IChatView iChatView);

        void b();

        void b(IChatView iChatView);
    }

    public interface MessageReminder {
        void a(boolean z, boolean z2, String str, String str2, String str3, String str4);
    }

    public void A() {
    }

    public abstract List<ChatDataSource.Data> B();

    public abstract List<ChatDataSource.Data> C();

    public abstract void D();

    public abstract void E();

    public abstract void a(int i2);

    public abstract void a(int i2, String str, boolean z, String str2);

    public abstract void a(ChatDataSource.Data data);

    public void a(String str, String str2) {
    }

    public abstract void a(String str, String str2, boolean z);

    public abstract void a(File[] fileArr);

    /* access modifiers changed from: protected */
    public abstract void e(String str);

    public abstract void f(String str);

    public abstract void g(String str);

    public abstract void h(String str);

    public abstract Enum u();

    /* access modifiers changed from: protected */
    public abstract void v();

    /* access modifiers changed from: protected */
    public abstract void w();

    /* access modifiers changed from: protected */
    public abstract void x();

    public void y() {
    }

    public void z() {
    }

    protected ChatRoom(String str) {
        this.c = str;
    }

    /* access modifiers changed from: protected */
    public String a() {
        return this.c;
    }

    public void a(String str) {
        this.f = str;
    }

    /* access modifiers changed from: protected */
    public String b() {
        return this.f;
    }

    public long c() {
        return this.h;
    }

    public void a(long j2) {
        this.h = j2;
    }

    public long d() {
        return this.i;
    }

    public void b(long j2) {
        this.i = j2;
    }

    public String e() {
        return this.j;
    }

    public void b(String str) {
        this.j = str;
    }

    public String f() {
        return this.d;
    }

    public void c(String str) {
        this.d = str;
    }

    public String g() {
        return this.e;
    }

    public void d(String str) {
        this.e = str;
    }

    /* access modifiers changed from: protected */
    public String h() {
        return this.g;
    }

    public boolean i() {
        return this.k;
    }

    public void a(boolean z) {
        this.k = z;
    }

    /* access modifiers changed from: protected */
    public void c(long j2) {
        this.b = j2;
    }

    /* access modifiers changed from: protected */
    public long j() {
        return this.b;
    }

    /* access modifiers changed from: protected */
    public void a(IChatView iChatView) {
        if (iChatView == null) {
            this.l = null;
        }
        this.l = new WeakReference<>(iChatView);
    }

    public synchronized IChatView k() {
        return this.l == null ? null : (IChatView) this.l.get();
    }

    public boolean l() {
        return k() != null;
    }

    public boolean m() {
        return l() && k().q();
    }

    public boolean n() {
        return l() && this.n;
    }

    public void o() {
        this.n = true;
        if (this.m != null) {
            this.m.a(k());
        }
    }

    public void p() {
        this.n = false;
        if (this.m != null) {
            this.m.b(k());
        }
    }

    public void q() {
        this.n = false;
        if (this.m != null) {
            this.m.b();
        }
        if (this.l != null) {
            this.l.clear();
            this.l = null;
        }
    }

    public void a(LifecycleCallback lifecycleCallback) {
        this.m = lifecycleCallback;
    }

    public void a(MessageReminder messageReminder) {
        this.f7643a = messageReminder;
    }

    /* access modifiers changed from: package-private */
    @WorkerThread
    public boolean r() {
        String str;
        SessionCreateResponse a2 = ChatController.a(this.c, this.f);
        if (a2 == null || a2.getData() == null) {
            Object[] objArr = new Object[4];
            objArr[0] = this.c;
            objArr[1] = API.a();
            objArr[2] = NetworkManager.b() ? "Active" : "Error";
            if (a2 == null) {
                str = "null";
            } else {
                str = a2.toString();
            }
            objArr[3] = str;
            Logger.d("【Room:%s】[%s]失败: Network: %s; Response：%s;", objArr);
            return false;
        }
        a(a2.getData().getRoomId(), (Object) a2);
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(String str, Object obj) {
        this.g = str;
    }

    public void s() {
        this.g = null;
    }

    /* access modifiers changed from: protected */
    public boolean t() {
        return this.g != null;
    }
}
