package a.a.a.e;

import a.a.a.b;
import a.a.a.d;
import a.a.a.e.c.g;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import in.cashify.otex.ExchangeManager;
import in.cashify.otex.PermissionActivity;
import java.util.ArrayList;

public class a extends Fragment implements View.OnClickListener {

    /* renamed from: a  reason: collision with root package name */
    public C0001a f371a;

    /* renamed from: a.a.a.e.a$a  reason: collision with other inner class name */
    public interface C0001a {
        void a(String str, ArrayList<b> arrayList);
    }

    public static Object a(@NonNull Object obj, String str, Object... objArr) {
        Class[] clsArr;
        if (objArr == null || objArr.length == 0) {
            clsArr = null;
        } else {
            clsArr = new Class[objArr.length];
            Class cls = Integer.TYPE;
            clsArr[0] = cls;
            clsArr[1] = cls;
            clsArr[2] = String.class;
        }
        return obj.getClass().getMethod(str, clsArr).invoke(obj, objArr);
    }

    public void a(b bVar) {
        try {
            ArrayList arrayList = new ArrayList();
            arrayList.add(bVar);
            a((ArrayList<b>) arrayList);
        } catch (Throwable th) {
            d.a("OTEx Diagnose", "postResult: Error" + th.getMessage());
        }
    }

    public void a(ArrayList<b> arrayList) {
        if (this.f371a != null) {
            g e = e();
            C0001a aVar = this.f371a;
            if (aVar != null) {
                aVar.a(e.f(), arrayList);
            }
        }
    }

    public boolean a(@NonNull Context context, int i) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 19 || i2 >= 23) {
            return true;
        }
        try {
            return ((Integer) a((AppOpsManager) context.getSystemService("appops"), "checkOp", Integer.valueOf(i), Integer.valueOf(Process.myUid()), context.getPackageName())).intValue() == 0;
        } catch (Exception e) {
            d.a("OTEx Diagnose", e.getMessage());
            return false;
        }
    }

    public boolean a(String str) {
        return a(str, -1);
    }

    public boolean a(String str, int i) {
        FragmentActivity activity = getActivity();
        return activity != null && ContextCompat.checkSelfPermission(activity.getApplicationContext(), str) == 0;
    }

    public void b() {
        if (getActivity() != null) {
            startActivity(new Intent(getActivity(), PermissionActivity.class));
        }
    }

    public long c() {
        if (e() == null) {
            return 2000;
        }
        return e().h();
    }

    public long d() {
        if (e() == null) {
            return 300;
        }
        return Math.max(300, e().i());
    }

    public g e() {
        throw new UnsupportedOperationException("getDiagnoseContext must override in child class " + getClass().getName());
    }

    public ExchangeManager f() {
        return (ExchangeManager) getParentFragment();
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        if (getParentFragment() instanceof C0001a) {
            this.f371a = (C0001a) getParentFragment();
            return;
        }
        throw new RuntimeException(context.toString() + " must implement ExchangeManager.OnExchangeCallback");
    }

    public void onClick(View view) {
        throw new UnsupportedOperationException("onClick must override in child class " + getClass().getName());
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return new TextView(getActivity());
    }

    public void onDetach() {
        super.onDetach();
        this.f371a = null;
    }
}
