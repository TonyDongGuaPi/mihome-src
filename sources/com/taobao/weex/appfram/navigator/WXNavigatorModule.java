package com.taobao.weex.appfram.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.bridge.WXBridgeManager;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.utils.WXLogUtils;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXNavigatorModule extends WXModule {
    private static transient /* synthetic */ boolean[] $jacocoData = null;
    public static final String CALLBACK_MESSAGE = "message";
    public static final String CALLBACK_RESULT = "result";
    private static final String INSTANCE_ID = "instanceId";
    public static final String MSG_FAILED = "WX_FAILED";
    public static final String MSG_PARAM_ERR = "WX_PARAM_ERR";
    public static final String MSG_SUCCESS = "WX_SUCCESS";
    private static final String TAG = "Navigator";
    private static final String URL = "url";
    private static final String WEEX = "com.taobao.android.intent.category.WEEX";

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(-6393180734367801679L, "com/taobao/weex/appfram/navigator/WXNavigatorModule", 215);
        $jacocoData = a2;
        return a2;
    }

    public WXNavigatorModule() {
        $jacocoInit()[0] = true;
    }

    @JSMethod(uiThread = true)
    public void open(JSONObject jSONObject, JSCallback jSCallback, JSCallback jSCallback2) {
        boolean[] $jacocoInit = $jacocoInit();
        if (jSONObject == null) {
            $jacocoInit[1] = true;
        } else {
            $jacocoInit[2] = true;
            String string = jSONObject.getString("url");
            $jacocoInit[3] = true;
            JSONObject jSONObject2 = new JSONObject();
            $jacocoInit[4] = true;
            if (!TextUtils.isEmpty(string)) {
                $jacocoInit[5] = true;
                Uri parse = Uri.parse(string);
                $jacocoInit[6] = true;
                String scheme = parse.getScheme();
                $jacocoInit[7] = true;
                if (TextUtils.isEmpty(scheme)) {
                    $jacocoInit[8] = true;
                } else if ("http".equalsIgnoreCase(scheme)) {
                    $jacocoInit[9] = true;
                } else if (!"https".equalsIgnoreCase(scheme)) {
                    $jacocoInit[10] = true;
                    try {
                        Intent intent = new Intent("android.intent.action.VIEW", parse);
                        $jacocoInit[13] = true;
                        this.mWXSDKInstance.getContext().startActivity(intent);
                        $jacocoInit[14] = true;
                        jSONObject2.put("result", (Object) MSG_SUCCESS);
                        $jacocoInit[15] = true;
                        jSCallback2 = jSCallback;
                    } catch (Throwable th) {
                        $jacocoInit[16] = true;
                        th.printStackTrace();
                        $jacocoInit[17] = true;
                        jSONObject2.put("result", (Object) MSG_FAILED);
                        $jacocoInit[18] = true;
                        jSONObject2.put("message", (Object) "Open page failed.");
                        $jacocoInit[19] = true;
                    }
                    $jacocoInit[20] = true;
                } else {
                    $jacocoInit[11] = true;
                }
                push(jSONObject.toJSONString(), jSCallback);
                $jacocoInit[12] = true;
                jSCallback2 = jSCallback;
                $jacocoInit[20] = true;
            } else {
                jSONObject2.put("result", (Object) MSG_PARAM_ERR);
                $jacocoInit[21] = true;
                jSONObject2.put("message", (Object) "The URL parameter is empty.");
                $jacocoInit[22] = true;
            }
            if (jSCallback2 == null) {
                $jacocoInit[23] = true;
            } else {
                $jacocoInit[24] = true;
                jSCallback2.invoke(jSONObject2);
                $jacocoInit[25] = true;
            }
        }
        $jacocoInit[26] = true;
    }

    @JSMethod(uiThread = true)
    public void close(JSONObject jSONObject, JSCallback jSCallback, JSCallback jSCallback2) {
        boolean[] $jacocoInit = $jacocoInit();
        JSONObject jSONObject2 = new JSONObject();
        $jacocoInit[27] = true;
        if (this.mWXSDKInstance.getContext() instanceof Activity) {
            $jacocoInit[28] = true;
            ((Activity) this.mWXSDKInstance.getContext()).finish();
            $jacocoInit[29] = true;
        } else {
            jSONObject2.put("result", (Object) MSG_FAILED);
            $jacocoInit[30] = true;
            jSONObject2.put("message", (Object) "Close page failed.");
            $jacocoInit[31] = true;
            jSCallback = jSCallback2;
        }
        if (jSCallback == null) {
            $jacocoInit[32] = true;
        } else {
            $jacocoInit[33] = true;
            jSCallback.invoke(jSONObject2);
            $jacocoInit[34] = true;
        }
        $jacocoInit[35] = true;
    }

    @JSMethod(uiThread = true)
    public void push(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (!TextUtils.isEmpty(str)) {
            $jacocoInit[36] = true;
            if (WXSDKEngine.getActivityNavBarSetter() == null) {
                $jacocoInit[37] = true;
            } else {
                $jacocoInit[38] = true;
                if (!WXSDKEngine.getActivityNavBarSetter().push(str)) {
                    $jacocoInit[39] = true;
                } else {
                    if (jSCallback == null) {
                        $jacocoInit[40] = true;
                    } else {
                        $jacocoInit[41] = true;
                        jSCallback.invoke(MSG_SUCCESS);
                        $jacocoInit[42] = true;
                    }
                    $jacocoInit[43] = true;
                    return;
                }
            }
            if (!(this.mWXSDKInstance.getContext() instanceof Activity)) {
                $jacocoInit[44] = true;
            } else {
                $jacocoInit[45] = true;
                Activity activity = (Activity) this.mWXSDKInstance.getContext();
                $jacocoInit[46] = true;
                if (WXSDKEngine.getNavigator() == null) {
                    $jacocoInit[47] = true;
                } else {
                    $jacocoInit[48] = true;
                    if (!WXSDKEngine.getNavigator().push(activity, str)) {
                        $jacocoInit[49] = true;
                    } else {
                        if (jSCallback == null) {
                            $jacocoInit[50] = true;
                        } else {
                            $jacocoInit[51] = true;
                            jSCallback.invoke(MSG_SUCCESS);
                            $jacocoInit[52] = true;
                        }
                        $jacocoInit[53] = true;
                        return;
                    }
                }
            }
            try {
                JSONObject parseObject = JSON.parseObject(str);
                $jacocoInit[54] = true;
                String string = parseObject.getString("url");
                $jacocoInit[55] = true;
                if (TextUtils.isEmpty(string)) {
                    $jacocoInit[56] = true;
                } else {
                    $jacocoInit[57] = true;
                    Uri parse = Uri.parse(string);
                    $jacocoInit[58] = true;
                    String scheme = parse.getScheme();
                    $jacocoInit[59] = true;
                    Uri.Builder buildUpon = parse.buildUpon();
                    $jacocoInit[60] = true;
                    if (!TextUtils.isEmpty(scheme)) {
                        $jacocoInit[61] = true;
                    } else {
                        $jacocoInit[62] = true;
                        buildUpon.scheme("http");
                        $jacocoInit[63] = true;
                    }
                    Intent intent = new Intent("android.intent.action.VIEW", buildUpon.build());
                    $jacocoInit[64] = true;
                    intent.addCategory(WEEX);
                    $jacocoInit[65] = true;
                    intent.putExtra("instanceId", this.mWXSDKInstance.getInstanceId());
                    $jacocoInit[66] = true;
                    this.mWXSDKInstance.getContext().startActivity(intent);
                    if (jSCallback == null) {
                        $jacocoInit[67] = true;
                    } else {
                        $jacocoInit[68] = true;
                        jSCallback.invoke(MSG_SUCCESS);
                        $jacocoInit[69] = true;
                    }
                }
                $jacocoInit[70] = true;
            } catch (Exception e) {
                $jacocoInit[71] = true;
                WXLogUtils.eTag(TAG, e);
                if (jSCallback == null) {
                    $jacocoInit[72] = true;
                } else {
                    $jacocoInit[73] = true;
                    jSCallback.invoke(MSG_FAILED);
                    $jacocoInit[74] = true;
                }
                $jacocoInit[75] = true;
            }
        } else if (jSCallback == null) {
            $jacocoInit[76] = true;
        } else {
            $jacocoInit[77] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[78] = true;
        }
        $jacocoInit[79] = true;
    }

    @JSMethod(uiThread = true)
    public void pop(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXSDKEngine.getActivityNavBarSetter() == null) {
            $jacocoInit[80] = true;
        } else {
            $jacocoInit[81] = true;
            if (!WXSDKEngine.getActivityNavBarSetter().pop(str)) {
                $jacocoInit[82] = true;
            } else {
                if (jSCallback == null) {
                    $jacocoInit[83] = true;
                } else {
                    $jacocoInit[84] = true;
                    jSCallback.invoke(MSG_SUCCESS);
                    $jacocoInit[85] = true;
                }
                $jacocoInit[86] = true;
                return;
            }
        }
        if (!(this.mWXSDKInstance.getContext() instanceof Activity)) {
            $jacocoInit[87] = true;
        } else {
            $jacocoInit[88] = true;
            Activity activity = (Activity) this.mWXSDKInstance.getContext();
            $jacocoInit[89] = true;
            if (WXSDKEngine.getNavigator() == null) {
                $jacocoInit[90] = true;
            } else {
                $jacocoInit[91] = true;
                if (!WXSDKEngine.getNavigator().pop(activity, str)) {
                    $jacocoInit[92] = true;
                } else {
                    if (jSCallback == null) {
                        $jacocoInit[93] = true;
                    } else {
                        $jacocoInit[94] = true;
                        jSCallback.invoke(MSG_SUCCESS);
                        $jacocoInit[95] = true;
                    }
                    $jacocoInit[96] = true;
                    return;
                }
            }
            if (jSCallback == null) {
                $jacocoInit[97] = true;
            } else {
                $jacocoInit[98] = true;
                jSCallback.invoke(MSG_SUCCESS);
                $jacocoInit[99] = true;
            }
            ((Activity) this.mWXSDKInstance.getContext()).finish();
            $jacocoInit[100] = true;
        }
        $jacocoInit[101] = true;
    }

    @JSMethod(uiThread = true)
    public void setNavBarRightItem(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[102] = true;
        } else {
            $jacocoInit[103] = true;
            if (WXSDKEngine.getActivityNavBarSetter() == null) {
                $jacocoInit[104] = true;
            } else {
                $jacocoInit[105] = true;
                if (!WXSDKEngine.getActivityNavBarSetter().setNavBarRightItem(str)) {
                    $jacocoInit[106] = true;
                } else {
                    if (jSCallback == null) {
                        $jacocoInit[107] = true;
                    } else {
                        $jacocoInit[108] = true;
                        jSCallback.invoke(MSG_SUCCESS);
                        $jacocoInit[109] = true;
                    }
                    $jacocoInit[110] = true;
                    return;
                }
            }
        }
        if (jSCallback == null) {
            $jacocoInit[111] = true;
        } else {
            $jacocoInit[112] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[113] = true;
        }
        $jacocoInit[114] = true;
    }

    @JSMethod(uiThread = true)
    public void clearNavBarRightItem(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXSDKEngine.getActivityNavBarSetter() == null) {
            $jacocoInit[115] = true;
        } else {
            $jacocoInit[116] = true;
            if (!WXSDKEngine.getActivityNavBarSetter().clearNavBarRightItem(str)) {
                $jacocoInit[117] = true;
            } else {
                if (jSCallback == null) {
                    $jacocoInit[118] = true;
                } else {
                    $jacocoInit[119] = true;
                    jSCallback.invoke(MSG_SUCCESS);
                    $jacocoInit[120] = true;
                }
                $jacocoInit[121] = true;
                return;
            }
        }
        if (jSCallback == null) {
            $jacocoInit[122] = true;
        } else {
            $jacocoInit[123] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[124] = true;
        }
        $jacocoInit[125] = true;
    }

    @JSMethod(uiThread = true)
    public void setNavBarLeftItem(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[126] = true;
        } else {
            $jacocoInit[127] = true;
            if (WXSDKEngine.getActivityNavBarSetter() == null) {
                $jacocoInit[128] = true;
            } else {
                $jacocoInit[129] = true;
                if (!WXSDKEngine.getActivityNavBarSetter().setNavBarLeftItem(str)) {
                    $jacocoInit[130] = true;
                } else {
                    if (jSCallback == null) {
                        $jacocoInit[131] = true;
                    } else {
                        $jacocoInit[132] = true;
                        jSCallback.invoke(MSG_SUCCESS);
                        $jacocoInit[133] = true;
                    }
                    $jacocoInit[134] = true;
                    return;
                }
            }
        }
        if (jSCallback == null) {
            $jacocoInit[135] = true;
        } else {
            $jacocoInit[136] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[137] = true;
        }
        $jacocoInit[138] = true;
    }

    @JSMethod(uiThread = true)
    public void clearNavBarLeftItem(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXSDKEngine.getActivityNavBarSetter() == null) {
            $jacocoInit[139] = true;
        } else {
            $jacocoInit[140] = true;
            if (!WXSDKEngine.getActivityNavBarSetter().clearNavBarLeftItem(str)) {
                $jacocoInit[141] = true;
            } else {
                if (jSCallback == null) {
                    $jacocoInit[142] = true;
                } else {
                    $jacocoInit[143] = true;
                    jSCallback.invoke(MSG_SUCCESS);
                    $jacocoInit[144] = true;
                }
                $jacocoInit[145] = true;
                return;
            }
        }
        if (jSCallback == null) {
            $jacocoInit[146] = true;
        } else {
            $jacocoInit[147] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[148] = true;
        }
        $jacocoInit[149] = true;
    }

    @JSMethod(uiThread = true)
    public void setNavBarMoreItem(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[150] = true;
        } else {
            $jacocoInit[151] = true;
            if (WXSDKEngine.getActivityNavBarSetter() == null) {
                $jacocoInit[152] = true;
            } else {
                $jacocoInit[153] = true;
                if (!WXSDKEngine.getActivityNavBarSetter().setNavBarMoreItem(str)) {
                    $jacocoInit[154] = true;
                } else {
                    if (jSCallback == null) {
                        $jacocoInit[155] = true;
                    } else {
                        $jacocoInit[156] = true;
                        jSCallback.invoke(MSG_SUCCESS);
                        $jacocoInit[157] = true;
                    }
                    $jacocoInit[158] = true;
                    return;
                }
            }
        }
        if (jSCallback == null) {
            $jacocoInit[159] = true;
        } else {
            $jacocoInit[160] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[161] = true;
        }
        $jacocoInit[162] = true;
    }

    @JSMethod(uiThread = true)
    public void clearNavBarMoreItem(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (WXSDKEngine.getActivityNavBarSetter() == null) {
            $jacocoInit[163] = true;
        } else {
            $jacocoInit[164] = true;
            if (!WXSDKEngine.getActivityNavBarSetter().clearNavBarMoreItem(str)) {
                $jacocoInit[165] = true;
            } else {
                if (jSCallback == null) {
                    $jacocoInit[166] = true;
                } else {
                    $jacocoInit[167] = true;
                    jSCallback.invoke(MSG_SUCCESS);
                    $jacocoInit[168] = true;
                }
                $jacocoInit[169] = true;
                return;
            }
        }
        if (jSCallback == null) {
            $jacocoInit[170] = true;
        } else {
            $jacocoInit[171] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[172] = true;
        }
        $jacocoInit[173] = true;
    }

    @JSMethod(uiThread = true)
    public void setNavBarTitle(String str, JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[174] = true;
        } else {
            $jacocoInit[175] = true;
            if (WXSDKEngine.getActivityNavBarSetter() == null) {
                $jacocoInit[176] = true;
            } else {
                $jacocoInit[177] = true;
                if (!WXSDKEngine.getActivityNavBarSetter().setNavBarTitle(str)) {
                    $jacocoInit[178] = true;
                } else {
                    if (jSCallback == null) {
                        $jacocoInit[179] = true;
                    } else {
                        $jacocoInit[180] = true;
                        jSCallback.invoke(MSG_SUCCESS);
                        $jacocoInit[181] = true;
                    }
                    $jacocoInit[182] = true;
                    return;
                }
            }
        }
        if (jSCallback == null) {
            $jacocoInit[183] = true;
        } else {
            $jacocoInit[184] = true;
            jSCallback.invoke(MSG_FAILED);
            $jacocoInit[185] = true;
        }
        $jacocoInit[186] = true;
    }

    @JSMethod
    public void setNavBarHidden(String str, String str2) {
        String str3;
        boolean[] $jacocoInit = $jacocoInit();
        String str4 = MSG_FAILED;
        try {
            $jacocoInit[187] = true;
            JSONObject parseObject = JSON.parseObject(str);
            $jacocoInit[188] = true;
            int intValue = parseObject.getInteger("hidden").intValue();
            $jacocoInit[189] = true;
            if (!changeVisibilityOfActionBar(this.mWXSDKInstance.getContext(), intValue)) {
                $jacocoInit[190] = true;
                str3 = str4;
            } else {
                str3 = MSG_SUCCESS;
                try {
                    $jacocoInit[191] = true;
                } catch (JSONException e) {
                    JSONException jSONException = e;
                    str4 = str3;
                    e = jSONException;
                    $jacocoInit[193] = true;
                    WXLogUtils.e(TAG, WXLogUtils.getStackTrace(e));
                    $jacocoInit[194] = true;
                    str3 = str4;
                    WXBridgeManager.getInstance().callback(this.mWXSDKInstance.getInstanceId(), str2, str3);
                    $jacocoInit[195] = true;
                }
            }
            $jacocoInit[192] = true;
        } catch (JSONException e2) {
            e = e2;
            $jacocoInit[193] = true;
            WXLogUtils.e(TAG, WXLogUtils.getStackTrace(e));
            $jacocoInit[194] = true;
            str3 = str4;
            WXBridgeManager.getInstance().callback(this.mWXSDKInstance.getInstanceId(), str2, str3);
            $jacocoInit[195] = true;
        }
        WXBridgeManager.getInstance().callback(this.mWXSDKInstance.getInstanceId(), str2, str3);
        $jacocoInit[195] = true;
    }

    private boolean changeVisibilityOfActionBar(Context context, int i) {
        boolean z;
        boolean[] $jacocoInit = $jacocoInit();
        boolean z2 = false;
        try {
            $jacocoInit[196] = true;
            Class.forName("android.support.v7.app.AppCompatActivity");
            $jacocoInit[197] = true;
            z = true;
        } catch (ClassNotFoundException e) {
            $jacocoInit[198] = true;
            e.printStackTrace();
            $jacocoInit[199] = true;
            z = false;
        }
        if (!z) {
            $jacocoInit[200] = true;
        } else if (!(this.mWXSDKInstance.getContext() instanceof AppCompatActivity)) {
            $jacocoInit[201] = true;
        } else {
            $jacocoInit[202] = true;
            ActionBar supportActionBar = ((AppCompatActivity) this.mWXSDKInstance.getContext()).getSupportActionBar();
            if (supportActionBar == null) {
                $jacocoInit[203] = true;
            } else {
                switch (i) {
                    case 0:
                        supportActionBar.show();
                        $jacocoInit[206] = true;
                        break;
                    case 1:
                        supportActionBar.hide();
                        $jacocoInit[205] = true;
                        break;
                    default:
                        $jacocoInit[204] = true;
                        break;
                }
                z2 = true;
            }
            $jacocoInit[207] = true;
            $jacocoInit[214] = true;
            return z2;
        }
        if (!(this.mWXSDKInstance.getContext() instanceof Activity)) {
            $jacocoInit[208] = true;
        } else {
            $jacocoInit[209] = true;
            android.app.ActionBar actionBar = ((Activity) this.mWXSDKInstance.getContext()).getActionBar();
            if (actionBar == null) {
                $jacocoInit[210] = true;
            } else {
                switch (i) {
                    case 0:
                        actionBar.show();
                        $jacocoInit[213] = true;
                        break;
                    case 1:
                        actionBar.hide();
                        $jacocoInit[212] = true;
                        break;
                    default:
                        $jacocoInit[211] = true;
                        break;
                }
                z2 = true;
            }
        }
        $jacocoInit[214] = true;
        return z2;
    }
}
