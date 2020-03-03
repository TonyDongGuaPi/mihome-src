package in.cashify.otex;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

public class OTEx {

    /* renamed from: a  reason: collision with root package name */
    public static final OTEx f2601a = new OTEx();

    public static OTEx a() {
        return f2601a;
    }

    public static void a(@NonNull FragmentManager fragmentManager, @Nullable ViewGroup viewGroup, String str, @NonNull ExchangeSetup exchangeSetup) {
        try {
            FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
            if (findFragmentByTag == null) {
                findFragmentByTag = ExchangeManager.a(exchangeSetup);
            } else if (findFragmentByTag.isAdded()) {
                return;
            }
            beginTransaction.add(viewGroup != null ? viewGroup.getId() : 0, findFragmentByTag, str);
            beginTransaction.commitAllowingStateLoss();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void a(@NonNull FragmentManager fragmentManager, String str) {
        try {
            Fragment findFragmentByTag = fragmentManager.findFragmentByTag(str);
            if (findFragmentByTag != null) {
                FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
                beginTransaction.remove(findFragmentByTag);
                beginTransaction.commitAllowingStateLoss();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
