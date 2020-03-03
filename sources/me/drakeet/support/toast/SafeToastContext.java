package me.drakeet.support.toast;

import android.content.Context;
import android.content.ContextWrapper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

final class SafeToastContext extends ContextWrapper {
    /* access modifiers changed from: private */
    @NonNull

    /* renamed from: a  reason: collision with root package name */
    public Toast f2915a;
    /* access modifiers changed from: private */
    @Nullable
    public BadTokenListener b;

    SafeToastContext(@NonNull Context context, @NonNull Toast toast) {
        super(context);
        this.f2915a = toast;
    }

    public Context getApplicationContext() {
        return new ApplicationContextWrapper(getBaseContext().getApplicationContext());
    }

    public void a(@NonNull BadTokenListener badTokenListener) {
        this.b = badTokenListener;
    }

    private final class ApplicationContextWrapper extends ContextWrapper {
        private ApplicationContextWrapper(Context context) {
            super(context);
        }

        public Object getSystemService(@NonNull String str) {
            if ("window".equals(str)) {
                return new WindowManagerWrapper((WindowManager) getBaseContext().getSystemService(str));
            }
            return super.getSystemService(str);
        }
    }

    private final class WindowManagerWrapper implements WindowManager {
        private static final String b = "WindowManagerWrapper";
        @NonNull
        private final WindowManager c;

        private WindowManagerWrapper(WindowManager windowManager) {
            this.c = windowManager;
        }

        public Display getDefaultDisplay() {
            return this.c.getDefaultDisplay();
        }

        public void removeViewImmediate(View view) {
            this.c.removeViewImmediate(view);
        }

        public void addView(View view, ViewGroup.LayoutParams layoutParams) {
            try {
                Log.d(b, "WindowManager's addView(view, params) has been hooked.");
                this.c.addView(view, layoutParams);
            } catch (WindowManager.BadTokenException e) {
                Log.i(b, e.getMessage());
                if (SafeToastContext.this.b != null) {
                    SafeToastContext.this.b.a(SafeToastContext.this.f2915a);
                }
            } catch (Throwable th) {
                Log.e(b, "[addView]", th);
            }
        }

        public void updateViewLayout(View view, ViewGroup.LayoutParams layoutParams) {
            this.c.updateViewLayout(view, layoutParams);
        }

        public void removeView(View view) {
            this.c.removeView(view);
        }
    }
}
