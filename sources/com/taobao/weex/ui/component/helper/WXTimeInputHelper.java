package com.taobao.weex.ui.component.helper;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.TextView;
import com.taobao.weex.appfram.pickers.DatePickerImpl;
import com.taobao.weex.ui.component.AbstractEditComponent;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXTimeInputHelper {
    private static transient /* synthetic */ boolean[] $jacocoData;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(6717456976351363344L, "com/taobao/weex/ui/component/helper/WXTimeInputHelper", 9);
        $jacocoData = a2;
        return a2;
    }

    public WXTimeInputHelper() {
        $jacocoInit()[0] = true;
    }

    public static void pickDate(String str, String str2, final AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        final TextView textView = (TextView) abstractEditComponent.getHostView();
        $jacocoInit[1] = true;
        Context context = textView.getContext();
        $jacocoInit[2] = true;
        String charSequence = textView.getText().toString();
        AnonymousClass1 r7 = new DatePickerImpl.OnPickListener() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-2443689823077930243L, "com/taobao/weex/ui/component/helper/WXTimeInputHelper$1", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void onPick(boolean z, @Nullable String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!z) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    textView.setText(str);
                    $jacocoInit[3] = true;
                    abstractEditComponent.performOnChange(str);
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        };
        $jacocoInit[3] = true;
        DatePickerImpl.pickDate(context, charSequence, str, str2, r7, (Map<String, Object>) null);
        $jacocoInit[4] = true;
    }

    public static void pickTime(final AbstractEditComponent abstractEditComponent) {
        boolean[] $jacocoInit = $jacocoInit();
        final TextView textView = (TextView) abstractEditComponent.getHostView();
        $jacocoInit[5] = true;
        Context context = textView.getContext();
        $jacocoInit[6] = true;
        String charSequence = textView.getText().toString();
        AnonymousClass2 r5 = new DatePickerImpl.OnPickListener() {
            private static transient /* synthetic */ boolean[] $jacocoData;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(2192648362654753174L, "com/taobao/weex/ui/component/helper/WXTimeInputHelper$2", 6);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                $jacocoInit[0] = true;
            }

            public void onPick(boolean z, @Nullable String str) {
                boolean[] $jacocoInit = $jacocoInit();
                if (!z) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    textView.setText(str);
                    $jacocoInit[3] = true;
                    abstractEditComponent.performOnChange(str);
                    $jacocoInit[4] = true;
                }
                $jacocoInit[5] = true;
            }
        };
        $jacocoInit[7] = true;
        DatePickerImpl.pickTime(context, charSequence, r5, (Map<String, Object>) null);
        $jacocoInit[8] = true;
    }
}
