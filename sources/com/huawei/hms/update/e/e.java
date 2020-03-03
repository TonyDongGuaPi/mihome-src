package com.huawei.hms.update.e;

import android.app.AlertDialog;
import com.huawei.android.hms.base.R;

public final class e {

    public static class b extends a {
        public b() {
            super();
        }

        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        /* access modifiers changed from: protected */
        public int h() {
            return R.string.hms_download_retry;
        }

        /* access modifiers changed from: protected */
        public int i() {
            return R.string.hms_retry;
        }

        /* access modifiers changed from: protected */
        public int j() {
            return R.string.hms_cancel;
        }
    }

    public static class c extends a {
        public c() {
            super();
        }

        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        /* access modifiers changed from: protected */
        public int h() {
            return R.string.hms_abort_message;
        }

        /* access modifiers changed from: protected */
        public int i() {
            return R.string.hms_abort;
        }

        /* access modifiers changed from: protected */
        public int j() {
            return R.string.hms_cancel;
        }
    }

    private static abstract class a extends b {
        /* access modifiers changed from: protected */
        public abstract int h();

        /* access modifiers changed from: protected */
        public abstract int i();

        /* access modifiers changed from: protected */
        public abstract int j();

        private a() {
        }

        public AlertDialog a() {
            AlertDialog.Builder builder = new AlertDialog.Builder(f(), g());
            builder.setMessage(h());
            builder.setPositiveButton(i(), new f(this));
            builder.setNegativeButton(j(), new g(this));
            return builder.create();
        }
    }
}
