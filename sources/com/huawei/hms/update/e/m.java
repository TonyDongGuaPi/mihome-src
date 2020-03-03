package com.huawei.hms.update.e;

import android.app.AlertDialog;
import com.huawei.android.hms.base.R;

public final class m {

    public static class b extends a {
        public b() {
            super();
        }

        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        /* access modifiers changed from: protected */
        public int h() {
            return R.string.hms_check_failure;
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
            return R.string.hms_download_failure;
        }
    }

    public static class d extends a {
        public d() {
            super();
        }

        public /* bridge */ /* synthetic */ AlertDialog a() {
            return super.a();
        }

        /* access modifiers changed from: protected */
        public int h() {
            return R.string.hms_download_no_space;
        }
    }

    private static abstract class a extends b {
        /* access modifiers changed from: protected */
        public abstract int h();

        private a() {
        }

        public AlertDialog a() {
            AlertDialog.Builder builder = new AlertDialog.Builder(f(), g());
            builder.setMessage(h());
            builder.setPositiveButton(i(), new n(this));
            return builder.create();
        }

        /* access modifiers changed from: protected */
        public int i() {
            return R.string.hms_confirm;
        }
    }
}
