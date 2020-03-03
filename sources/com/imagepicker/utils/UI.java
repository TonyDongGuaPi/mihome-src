package com.imagepicker.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.facebook.react.bridge.ReadableMap;
import com.imagepicker.ImagePickerModule;
import com.imagepicker.utils.SimpleListDialog;
import java.lang.ref.WeakReference;
import java.util.List;

public class UI {

    public interface OnAction {
        void a(@Nullable ImagePickerModule imagePickerModule);

        void a(@Nullable ImagePickerModule imagePickerModule, String str);

        void b(@Nullable ImagePickerModule imagePickerModule);

        void c(@Nullable ImagePickerModule imagePickerModule);
    }

    @NonNull
    public static Dialog a(@Nullable ImagePickerModule imagePickerModule, @NonNull ReadableMap readableMap, @Nullable final OnAction onAction) {
        Activity activity = imagePickerModule.getActivity();
        if (activity == null) {
            return null;
        }
        final WeakReference weakReference = new WeakReference(imagePickerModule);
        ButtonsHelper a2 = ButtonsHelper.a(readableMap);
        SimpleListDialog simpleListDialog = new SimpleListDialog(activity);
        if (ReadableMapUtils.b(readableMap, "title")) {
            simpleListDialog.a(readableMap.getString("title"));
        }
        simpleListDialog.setCancelable(true);
        simpleListDialog.setCanceledOnTouchOutside(true);
        List<String> a3 = a2.a();
        final List<String> b = a2.b();
        if (a3 != null && a3.size() > 0) {
            simpleListDialog.a((CharSequence[]) a3.toArray(new String[a3.size()]), (SimpleListDialog.IDialogInterface) new SimpleListDialog.IDialogInterface() {
                /* JADX WARNING: Removed duplicated region for block: B:17:0x003e  */
                /* JADX WARNING: Removed duplicated region for block: B:18:0x004c  */
                /* JADX WARNING: Removed duplicated region for block: B:19:0x005a  */
                /* JADX WARNING: Removed duplicated region for block: B:20:0x0068  */
                /* Code decompiled incorrectly, please refer to instructions dump. */
                public void a(int r3) {
                    /*
                        r2 = this;
                        java.util.List r0 = r3
                        java.lang.Object r3 = r0.get(r3)
                        java.lang.String r3 = (java.lang.String) r3
                        int r0 = r3.hashCode()
                        r1 = -1367724422(0xffffffffae7a2e7a, float:-5.68847E-11)
                        if (r0 == r1) goto L_0x0030
                        r1 = 106642994(0x65b3e32, float:4.1235016E-35)
                        if (r0 == r1) goto L_0x0026
                        r1 = 166208699(0x9e824bb, float:5.588651E-33)
                        if (r0 == r1) goto L_0x001c
                        goto L_0x003a
                    L_0x001c:
                        java.lang.String r0 = "library"
                        boolean r0 = r3.equals(r0)
                        if (r0 == 0) goto L_0x003a
                        r0 = 1
                        goto L_0x003b
                    L_0x0026:
                        java.lang.String r0 = "photo"
                        boolean r0 = r3.equals(r0)
                        if (r0 == 0) goto L_0x003a
                        r0 = 0
                        goto L_0x003b
                    L_0x0030:
                        java.lang.String r0 = "cancel"
                        boolean r0 = r3.equals(r0)
                        if (r0 == 0) goto L_0x003a
                        r0 = 2
                        goto L_0x003b
                    L_0x003a:
                        r0 = -1
                    L_0x003b:
                        switch(r0) {
                            case 0: goto L_0x0068;
                            case 1: goto L_0x005a;
                            case 2: goto L_0x004c;
                            default: goto L_0x003e;
                        }
                    L_0x003e:
                        com.imagepicker.utils.UI$OnAction r0 = r5
                        java.lang.ref.WeakReference r1 = r1
                        java.lang.Object r1 = r1.get()
                        com.imagepicker.ImagePickerModule r1 = (com.imagepicker.ImagePickerModule) r1
                        r0.a(r1, r3)
                        goto L_0x0075
                    L_0x004c:
                        com.imagepicker.utils.UI$OnAction r3 = r5
                        java.lang.ref.WeakReference r0 = r1
                        java.lang.Object r0 = r0.get()
                        com.imagepicker.ImagePickerModule r0 = (com.imagepicker.ImagePickerModule) r0
                        r3.c(r0)
                        goto L_0x0075
                    L_0x005a:
                        com.imagepicker.utils.UI$OnAction r3 = r5
                        java.lang.ref.WeakReference r0 = r1
                        java.lang.Object r0 = r0.get()
                        com.imagepicker.ImagePickerModule r0 = (com.imagepicker.ImagePickerModule) r0
                        r3.b(r0)
                        goto L_0x0075
                    L_0x0068:
                        com.imagepicker.utils.UI$OnAction r3 = r5
                        java.lang.ref.WeakReference r0 = r1
                        java.lang.Object r0 = r0.get()
                        com.imagepicker.ImagePickerModule r0 = (com.imagepicker.ImagePickerModule) r0
                        r3.a(r0)
                    L_0x0075:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.imagepicker.utils.UI.AnonymousClass1.a(int):void");
                }
            });
        }
        simpleListDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            public void onCancel(DialogInterface dialogInterface) {
                onAction.c((ImagePickerModule) weakReference.get());
            }
        });
        return simpleListDialog;
    }
}
