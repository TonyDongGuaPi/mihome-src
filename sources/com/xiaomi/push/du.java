package com.xiaomi.push;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.push.service.ah;

final class du implements dy {
    du() {
    }

    private void a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            try {
                if (ag.a(context, String.valueOf(12), 1)) {
                    hw hwVar = new hw();
                    hwVar.a(str + ":" + str2);
                    hwVar.a(System.currentTimeMillis());
                    hwVar.a(hq.BroadcastAction);
                    ef.a(context, hwVar);
                }
            } catch (Throwable unused) {
            }
        }
    }

    /* access modifiers changed from: private */
    public void b(Context context, Intent intent) {
        int a2;
        try {
            String dataString = intent.getDataString();
            if (!TextUtils.isEmpty(dataString)) {
                String[] split = dataString.split(":");
                if (split.length < 2) {
                    return;
                }
                if (!TextUtils.isEmpty(split[1])) {
                    String str = split[1];
                    long currentTimeMillis = System.currentTimeMillis();
                    boolean a3 = ah.a(context).a(ht.BroadcastActionCollectionSwitch.a(), true);
                    if (TextUtils.equals("android.intent.action.PACKAGE_RESTARTED", intent.getAction())) {
                        if (!ag.a(context, String.valueOf(12), 1)) {
                            return;
                        }
                        if (a3) {
                            if (TextUtils.isEmpty(ee.f12706a)) {
                                ee.f12706a += dx.b + ":";
                            }
                            ee.f12706a += str + Operators.BRACKET_START_STR + currentTimeMillis + Operators.BRACKET_END_STR + ",";
                        }
                    } else if (!TextUtils.equals("android.intent.action.PACKAGE_CHANGED", intent.getAction())) {
                        if (TextUtils.equals("android.intent.action.PACKAGE_ADDED", intent.getAction())) {
                            if (!intent.getExtras().getBoolean("android.intent.extra.REPLACING") && a3) {
                                a2 = hq.BroadcastActionAdded.a();
                            } else {
                                return;
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_REMOVED", intent.getAction())) {
                            if (!intent.getExtras().getBoolean("android.intent.extra.REPLACING") && a3) {
                                a2 = hq.BroadcastActionRemoved.a();
                            } else {
                                return;
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_REPLACED", intent.getAction())) {
                            if (a3) {
                                a2 = hq.BroadcastActionReplaced.a();
                            } else {
                                return;
                            }
                        } else if (TextUtils.equals("android.intent.action.PACKAGE_DATA_CLEARED", intent.getAction()) && a3) {
                            a2 = hq.BroadcastActionDataCleared.a();
                        } else {
                            return;
                        }
                        a(context, String.valueOf(a2), str);
                    } else if (!ag.a(context, String.valueOf(12), 1)) {
                    } else {
                        if (a3) {
                            if (TextUtils.isEmpty(ee.b)) {
                                ee.b += dx.c + ":";
                            }
                            ee.b += str + Operators.BRACKET_START_STR + currentTimeMillis + Operators.BRACKET_END_STR + ",";
                        }
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public void a(Context context, Intent intent) {
        if (intent != null) {
            ai.a(context).a((Runnable) new dv(this, context, intent));
        }
    }
}
