package cn.fraudmetrix.octopus.aspirit.activity.mvp;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import cn.fraudmetrix.octopus.aspirit.R;
import cn.fraudmetrix.octopus.aspirit.activity.mvp.a;
import cn.fraudmetrix.octopus.aspirit.base.ui.mvp.a;
import cn.fraudmetrix.octopus.aspirit.main.OctopusManager;
import cn.fraudmetrix.octopus.aspirit.utils.c;
import cn.fraudmetrix.octopus.aspirit.utils.h;
import cn.fraudmetrix.octopus.aspirit.utils.j;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.taobao.weex.common.Constants;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class b extends a<a.b> {

    /* renamed from: a  reason: collision with root package name */
    private Handler f600a = new Handler(Looper.getMainLooper());

    @JavascriptInterface
    public String captureSacnQRCode() {
        return ((a.b) this.b).f();
    }

    @JavascriptInterface
    @Deprecated
    public void confirmAgreement() {
    }

    @JavascriptInterface
    public String encryptDataFunc(String str, String str2) {
        return str == null ? "" : str2 == null ? j.c(str) : "1".equals(str2) ? j.c(str) : "2".equals(str2) ? c.a(str) : j.c(str);
    }

    @JavascriptInterface
    public void eventAndData(final String str, final String str2) {
        h.a(" " + str + " " + str2);
        OctopusManager.b().a().execute(new Runnable() {
            public void run() {
                if (str != null && !str.isEmpty() && str2 != null && !str2.isEmpty()) {
                    cn.fraudmetrix.octopus.aspirit.main.a.a().a(str, str2);
                }
            }
        });
    }

    @JavascriptInterface
    public String getPtc() {
        return OctopusManager.b().d();
    }

    @JavascriptInterface
    public String getSettingKey(String str) {
        return TextUtils.isEmpty(str) ? "" : ((a.b) this.b).g().a(str);
    }

    @JavascriptInterface
    public String getVersion() {
        return "android_" + OctopusManager.b().h();
    }

    @JavascriptInterface
    public String hideLoading(JSONObject jSONObject) {
        ((a.b) this.b).k();
        return "";
    }

    @JavascriptInterface
    public String invoke(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return Constants.Name.UNDEFINED;
        }
        try {
            Method method = getClass().getMethod(str, new Class[]{JSONObject.class});
            if (method == null) {
                return Constants.Name.UNDEFINED;
            }
            JSONObject jSONObject = null;
            if (!TextUtils.isEmpty(str2)) {
                jSONObject = JSON.parseObject(str2);
            }
            return (String) method.invoke(this, new Object[]{jSONObject});
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return Constants.Name.UNDEFINED;
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
            return "error";
        } catch (InvocationTargetException e3) {
            e3.printStackTrace();
            return "error";
        }
    }

    @JavascriptInterface
    public String isAgree(String str) {
        if (!"0".equals(str)) {
            putSettingKeyValueType("isAgree", str, "0");
        }
        return getSettingKey("isAgree");
    }

    @JavascriptInterface
    public String jsRunSuccess(JSONObject jSONObject) {
        ((a.b) this.b).k();
        return "";
    }

    @JavascriptInterface
    @Deprecated
    public void mhSaveRequest(String str) {
    }

    @JavascriptInterface
    public void openQRImageUrlTargetStr(final String str, final String str2) {
        this.f600a.post(new Runnable() {
            public void run() {
                try {
                    String captureSacnQRCode = b.this.captureSacnQRCode();
                    if (!TextUtils.isEmpty(captureSacnQRCode)) {
                        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                            String[] split = str.split(",");
                            String[] split2 = str2.split(",");
                            String str = captureSacnQRCode;
                            int i = 0;
                            while (i < split.length) {
                                str = str.replace(split[0], i < split.length ? split2[0] : "");
                                i++;
                            }
                            captureSacnQRCode = str;
                        }
                        b.this.openURLString(captureSacnQRCode, ((a.b) b.this.b).l().getResources().getString(R.string.octopus_onekey_tb));
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ((a.b) b.this.b).c(R.string.octopus_onekey);
            }
        });
    }

    @JavascriptInterface
    public void openURLString(final String str, final String str2) {
        this.f600a.post(new Runnable() {
            public void run() {
                ((a.b) b.this.b).d(str, str2);
            }
        });
    }

    @JavascriptInterface
    public void passHtmlContent(String str, String str2) {
        ((a.b) this.b).c(str, str2);
        ((a.b) this.b).h();
    }

    @JavascriptInterface
    public void passTaskId(final String str) {
        this.f600a.post(new Runnable() {
            public void run() {
                if (TextUtils.isEmpty(str)) {
                    ((a.b) b.this.b).a(26);
                    return;
                }
                ((a.b) b.this.b).c(str);
                ((a.b) b.this.b).h();
            }
        });
    }

    @JavascriptInterface
    public void putSettingKeyValueType(String str, String str2, String str3) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str3)) {
            ((a.b) this.b).g().a(str, str2, str3);
        }
    }

    @JavascriptInterface
    public void removeSettingKey(String str) {
        if (!TextUtils.isEmpty(str)) {
            ((a.b) this.b).g().b(str);
        }
    }

    @JavascriptInterface
    public void saveHtmlPageData(String str, String str2, boolean z) {
        ((a.b) this.b).c(str, str2);
        if (!z) {
            ((a.b) this.b).h();
        }
    }

    @JavascriptInterface
    @Deprecated
    public void saveUserCredential(String str, String str2) {
    }

    @JavascriptInterface
    public void saveUserName(String str) {
        ((a.b) this.b).d(str);
    }

    @JavascriptInterface
    public void sendUrlString(final String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f600a.post(new Runnable() {
                public void run() {
                    ((a.b) b.this.b).b(str);
                }
            });
        }
    }

    @JavascriptInterface
    public void setUserAgent(final String str, final String str2) {
        this.f600a.post(new Runnable() {
            public void run() {
                ((a.b) b.this.b).b(str2, str);
            }
        });
    }

    @JavascriptInterface
    public String showLoading(JSONObject jSONObject) {
        int i = 0;
        boolean z = jSONObject != null && jSONObject.getInteger("showMask").intValue() > 0;
        if (jSONObject != null) {
            i = jSONObject.getInteger("showMillis").intValue();
        }
        ((a.b) this.b).a(z);
        if (i <= 0) {
            return "";
        }
        this.f600a.postDelayed(new Runnable() {
            public void run() {
                ((a.b) b.this.b).k();
            }
        }, (long) i);
        return "";
    }

    @JavascriptInterface
    @Deprecated
    public void showUserProtocol(String str) {
    }
}
