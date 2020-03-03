package miuipub.util.async.tasks.listeners;

import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import java.lang.ref.WeakReference;
import java.util.LinkedHashMap;
import miuipub.util.async.Task;
import miuipub.util.async.TaskManager;

public class ProgressDialogListener extends BaseTaskListener {

    /* renamed from: a  reason: collision with root package name */
    static final LinkedHashMap<String, ProgressDialogListener> f3047a = new LinkedHashMap<>();
    FragmentManager b;
    WeakReference<Task<?>> c;
    ProgressDialogFragment d;
    int e = 0;
    int f = 0;
    CharSequence g = null;
    int h = 0;
    CharSequence i = null;
    boolean j = false;
    boolean k = false;
    int l = 0;
    int m = 0;
    int n = 0;

    public ProgressDialogListener(FragmentManager fragmentManager) {
        this.b = fragmentManager;
    }

    public ProgressDialogListener a(int i2) {
        this.e = i2;
        return this;
    }

    public ProgressDialogListener b(int i2) {
        this.f = i2;
        this.g = null;
        return this;
    }

    public ProgressDialogListener a(CharSequence charSequence) {
        this.g = charSequence;
        this.f = 0;
        return this;
    }

    public ProgressDialogListener c(int i2) {
        this.h = i2;
        this.i = null;
        return this;
    }

    public ProgressDialogListener b(CharSequence charSequence) {
        this.i = charSequence;
        this.h = 0;
        return this;
    }

    public ProgressDialogListener a(boolean z) {
        this.j = z;
        return this;
    }

    public ProgressDialogListener d(int i2) {
        this.m = i2;
        return this;
    }

    public ProgressDialogListener b(boolean z) {
        this.k = z;
        return this;
    }

    public ProgressDialogListener e(int i2) {
        this.l = i2;
        return this;
    }

    public void a(TaskManager taskManager, Task<?> task) {
        this.c = new WeakReference<>(task);
        try {
            String str = "ProgressDialogListener@" + hashCode();
            f3047a.put(str, this);
            this.d = new ProgressDialogFragment();
            Bundle bundle = new Bundle();
            bundle.putString("ProgressDialogListener", str);
            this.d.setArguments(bundle);
            this.d.show(this.b, str);
        } catch (Exception e2) {
            Log.w("ProgressDialogListener", "cannot show dialog", e2);
            this.d = null;
            this.b = null;
            task.b((Task.Listener) this);
        }
    }

    public void a(TaskManager taskManager, Task<?> task, int i2, int i3) {
        ProgressDialogFragment progressDialogFragment = this.d;
        if (i2 >= 0) {
            if (this.k) {
                b(false);
                if (progressDialogFragment != null) {
                    progressDialogFragment.a(this.k);
                }
            }
            if (this.l != i2) {
                e(i2);
                if (progressDialogFragment != null) {
                    progressDialogFragment.b(this.l);
                }
            }
            if (this.n != i3) {
                this.n = i3;
                if (progressDialogFragment != null) {
                    progressDialogFragment.a(this.n);
                }
            }
        } else if (!this.k) {
            b(true);
            if (progressDialogFragment != null) {
                progressDialogFragment.a(this.k);
            }
        }
    }

    public void c(TaskManager taskManager, Task<?> task) {
        if (this.d != null) {
            this.d.dismiss();
        }
        LinkedHashMap<String, ProgressDialogListener> linkedHashMap = f3047a;
        linkedHashMap.remove("ProgressDialogListener@" + hashCode());
    }

    public static class ProgressDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

        /* renamed from: a  reason: collision with root package name */
        private ProgressDialogListener f3048a;

        /* access modifiers changed from: package-private */
        public void a(boolean z) {
            Dialog dialog = getDialog();
            if (dialog instanceof ProgressDialog) {
                ((ProgressDialog) dialog).setIndeterminate(z);
            }
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            Dialog dialog = getDialog();
            if (dialog instanceof ProgressDialog) {
                ((ProgressDialog) dialog).setProgress(i);
            }
        }

        /* access modifiers changed from: package-private */
        public void b(int i) {
            Dialog dialog = getDialog();
            if (dialog instanceof ProgressDialog) {
                ((ProgressDialog) dialog).setMax(i);
            }
        }

        public void onSaveInstanceState(Bundle bundle) {
            super.onSaveInstanceState(bundle);
            if (this.f3048a != null) {
                this.f3048a.d = null;
                this.f3048a.b = null;
                this.f3048a = null;
            }
        }

        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v10, resolved type: java.lang.Object} */
        /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v7, resolved type: miuipub.util.async.Task} */
        /* JADX WARNING: Multi-variable type inference failed */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onResume() {
            /*
                r3 = this;
                super.onResume()
                java.util.LinkedHashMap<java.lang.String, miuipub.util.async.tasks.listeners.ProgressDialogListener> r0 = miuipub.util.async.tasks.listeners.ProgressDialogListener.f3047a
                android.os.Bundle r1 = r3.getArguments()
                java.lang.String r2 = "ProgressDialogListener"
                java.lang.String r1 = r1.getString(r2)
                java.lang.Object r0 = r0.get(r1)
                miuipub.util.async.tasks.listeners.ProgressDialogListener r0 = (miuipub.util.async.tasks.listeners.ProgressDialogListener) r0
                r3.f3048a = r0
                miuipub.util.async.tasks.listeners.ProgressDialogListener r0 = r3.f3048a
                if (r0 != 0) goto L_0x002a
                android.app.FragmentManager r0 = r3.getFragmentManager()
                android.app.FragmentTransaction r0 = r0.beginTransaction()
                r0.remove(r3)
                r0.commit()
                goto L_0x005e
            L_0x002a:
                miuipub.util.async.tasks.listeners.ProgressDialogListener r0 = r3.f3048a
                r0.d = r3
                miuipub.util.async.tasks.listeners.ProgressDialogListener r0 = r3.f3048a
                android.app.FragmentManager r1 = r3.getFragmentManager()
                r0.b = r1
                miuipub.util.async.tasks.listeners.ProgressDialogListener r0 = r3.f3048a
                java.lang.ref.WeakReference<miuipub.util.async.Task<?>> r0 = r0.c
                r1 = 0
                if (r0 == 0) goto L_0x0044
                java.lang.Object r0 = r0.get()
                r1 = r0
                miuipub.util.async.Task r1 = (miuipub.util.async.Task) r1
            L_0x0044:
                if (r1 == 0) goto L_0x004c
                boolean r0 = r1.c()
                if (r0 != 0) goto L_0x005e
            L_0x004c:
                r3.dismiss()
                java.util.LinkedHashMap<java.lang.String, miuipub.util.async.tasks.listeners.ProgressDialogListener> r0 = miuipub.util.async.tasks.listeners.ProgressDialogListener.f3047a
                android.os.Bundle r1 = r3.getArguments()
                java.lang.String r2 = "ProgressDialogListener"
                java.lang.String r1 = r1.getString(r2)
                r0.remove(r1)
            L_0x005e:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: miuipub.util.async.tasks.listeners.ProgressDialogListener.ProgressDialogFragment.onResume():void");
        }

        public void onCancel(DialogInterface dialogInterface) {
            WeakReference<Task<?>> weakReference;
            Task task;
            if (!(this.f3048a == null || !this.f3048a.j || (weakReference = this.f3048a.c) == null || (task = (Task) weakReference.get()) == null)) {
                task.e();
            }
            super.onCancel(dialogInterface);
        }

        public Dialog onCreateDialog(Bundle bundle) {
            if (this.f3048a == null) {
                return super.onCreateDialog(bundle);
            }
            ProgressDialog progressDialog = new ProgressDialog(getActivity(), this.f3048a.e);
            if (this.f3048a.f != 0) {
                progressDialog.setTitle(this.f3048a.f);
            } else {
                progressDialog.setTitle(this.f3048a.g);
            }
            if (this.f3048a.h != 0) {
                progressDialog.setTitle(this.f3048a.h);
            } else {
                progressDialog.setMessage(this.f3048a.i);
            }
            progressDialog.setProgressStyle(this.f3048a.m);
            progressDialog.setIndeterminate(this.f3048a.k);
            if (this.f3048a.k) {
                progressDialog.setMax(this.f3048a.l);
                progressDialog.setProgress(this.f3048a.n);
            }
            if (this.f3048a.j) {
                progressDialog.setButton(-2, progressDialog.getContext().getText(17039360), this);
                progressDialog.setCancelable(true);
            } else {
                progressDialog.setButton(-2, (CharSequence) null, (DialogInterface.OnClickListener) null);
                progressDialog.setCancelable(false);
            }
            return progressDialog;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            onCancel(dialogInterface);
        }
    }
}
