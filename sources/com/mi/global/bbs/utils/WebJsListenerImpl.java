package com.mi.global.bbs.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import com.mi.global.bbs.ui.WatchBigPicActivity;
import com.mi.global.bbs.ui.WebActivity;
import com.mi.global.bbs.ui.checkin.SignActivity;
import com.mi.global.bbs.ui.plate.PlateActivity;
import com.mi.global.bbs.ui.user.UserCenterActivity;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.view.webview.WebViewCookieManager;
import com.mi.log.LogUtil;
import com.taobao.weex.el.parse.Operators;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebJsListenerImpl implements WebJsListener {
    /* access modifiers changed from: private */
    public String TAG = "WebJsListenerImpl";
    /* access modifiers changed from: private */
    public WeakReference<WebActivity> mContent;
    /* access modifiers changed from: private */
    public WebView webView;

    public WebJsListenerImpl(WebActivity webActivity, WebView webView2) {
        this.mContent = new WeakReference<>(webActivity);
        this.webView = webView2;
    }

    @JavascriptInterface
    public void getHeaderMenu(String str) {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.dismissReplyPanel();
                }
            });
        }
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.has("title")) {
                    final String string = jSONObject.getString("title");
                    if (webActivity != null) {
                        webActivity.runOnUiThread(new Runnable() {
                            public void run() {
                                webActivity.setTitle((CharSequence) string);
                            }
                        });
                    }
                }
                if (jSONObject.has("icon")) {
                    final JSONArray jSONArray = jSONObject.getJSONArray("icon");
                    if (webActivity != null) {
                        webActivity.runOnUiThread(new Runnable() {
                            public void run() {
                                webActivity.refreshTitleMenu(jSONArray.toString());
                            }
                        });
                    }
                } else if (webActivity != null) {
                    webActivity.runOnUiThread(new Runnable() {
                        public void run() {
                            webActivity.refreshTitleMenu("");
                        }
                    });
                }
                if (jSONObject.has("shareTitle")) {
                    final String string2 = jSONObject.getString("shareTitle");
                    if (webActivity != null) {
                        webActivity.runOnUiThread(new Runnable() {
                            public void run() {
                                webActivity.setShareTitle(string2);
                            }
                        });
                    }
                }
            } catch (Exception e) {
                String str2 = this.TAG;
                LogUtil.b(str2, "getHeaderMenu error  : " + e.toString());
            }
        }
    }

    @JavascriptInterface
    public void showReplyPanel(final String str, final String str2) {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.showReplyPanel(str, str2);
                }
            });
        }
    }

    @JavascriptInterface
    public void showReplyPersonPanel(String str, String str2, String str3, String str4) {
        WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            final String str5 = str;
            final String str6 = str2;
            final String str7 = str3;
            final String str8 = str4;
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    ((WebActivity) WebJsListenerImpl.this.mContent.get()).jumpToReplyPerson(str5, str6, str7, str8);
                }
            });
        }
    }

    @JavascriptInterface
    public void onLogOut() {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.goToLogOut();
                }
            });
        }
    }

    @JavascriptInterface
    public void watchBigPics(final String[] strArr, final String str) {
        WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    WatchBigPicActivity.jump((Context) WebJsListenerImpl.this.mContent.get(), str, strArr);
                }
            });
        }
    }

    @JavascriptInterface
    public void shareEvent(final String str) {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.ShareEventPost(str);
                }
            });
        }
    }

    @JavascriptInterface
    public void routeToMedal() {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.startActivity(new Intent(webActivity, SignActivity.class));
                }
            });
        }
    }

    @JavascriptInterface
    public void disableRefresh(final boolean z) {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.disableRefresh(z);
                }
            });
        }
    }

    @JavascriptInterface
    public void routeToUserCenter(final String str) {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    UserCenterActivity.jump(webActivity, str);
                }
            });
        }
    }

    @JavascriptInterface
    public int getAppVersion() {
        Context context = (Context) this.mContent.get();
        if (context != null) {
            try {
                return context.getPackageManager().getPackageInfo(context.getPackageName(), 1).versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    @JavascriptInterface
    public void takePicture() {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.takePicture();
                }
            });
        }
    }

    @JavascriptInterface
    public void routeToPlate(final String str, final String str2) {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    PlateActivity.jumpWithId(webActivity, str, str2);
                }
            });
        }
    }

    @JavascriptInterface
    public void finishCurrentPage() {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.finish();
                }
            });
        }
    }

    @JavascriptInterface
    public void login() {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    webActivity.gotoAccount();
                }
            });
        }
    }

    @JavascriptInterface
    public void routeToComments() {
        final WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    GoogleTrackerUtil.sendRecordEvent(Constants.PageFragment.PAGE_DETAIL, Constants.ClickEvent.CLICK_COMMENT, "routeToComments");
                    webActivity.jumpToComments(false);
                }
            });
        }
    }

    @JavascriptInterface
    public void showLikeAndComments(String str, String str2, String str3, String str4) {
        WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            final String str5 = str;
            final String str6 = str2;
            final WebActivity webActivity2 = webActivity;
            final String str7 = str3;
            final String str8 = str4;
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    int i;
                    try {
                        i = Integer.valueOf(str5.replace(",", "")).intValue();
                        try {
                            webActivity2.showLikeAndComments(i, Integer.valueOf(str6.replace(",", "")).intValue(), str7.equals("1"), str8);
                        } catch (NumberFormatException unused) {
                            webActivity2.showLikeAndComments(i, 0, str7.equals("1"), str8);
                        } catch (Throwable th) {
                            th = th;
                            webActivity2.showLikeAndComments(i, 0, str7.equals("1"), str8);
                            throw th;
                        }
                    } catch (NumberFormatException unused2) {
                        i = 0;
                        webActivity2.showLikeAndComments(i, 0, str7.equals("1"), str8);
                    } catch (Throwable th2) {
                        th = th2;
                        i = 0;
                        webActivity2.showLikeAndComments(i, 0, str7.equals("1"), str8);
                        throw th;
                    }
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public void setCookies() {
        WebViewCookieManager.addAppCookie();
        WebViewCookieManager.setLoginCookies((Context) this.mContent.get());
        WebViewCookieManager.updateCustomCookies((Context) this.mContent.get());
        WebViewCookieManager.showAllCookie(ConnectionHelper.getWebCookieDomain());
    }

    private void reloadPage(final String str) {
        WebActivity webActivity = (WebActivity) this.mContent.get();
        if (webActivity != null) {
            webActivity.runOnUiThread(new Runnable() {
                public void run() {
                    WebJsListenerImpl.this.setCookies();
                    String access$200 = WebJsListenerImpl.this.TAG;
                    LogUtil.b(access$200, " reloadByJs  pid " + str);
                    WebJsListenerImpl.this.webView.loadUrl(String.format("javascript:replypost_success(" + str + Operators.BRACKET_END_STR, new Object[0]));
                }
            });
        }
    }
}
