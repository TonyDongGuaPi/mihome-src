package com.xiaomi.smarthome.miio.user;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchy;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.BasePostprocessor;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.family.api.RemoteFamilyApi;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdActivity;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import com.xiaomi.smarthome.framework.api.model.UserInfo;
import com.xiaomi.smarthome.homeroom.UserInfoManager;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.XMStringUtils;
import com.xiaomi.smarthome.miio.db.record.ShareUserRecord;
import com.xiaomi.youpin.login.api.callback.BasePassportCallback;
import com.xiaomi.youpin.login.entity.account.MiAccountInfo;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.okhttpApi.api.AccountUserApi;
import java.io.IOException;
import java.util.Calendar;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class UserMamanger {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19980a = "action_user_info_updated";
    public static final String b = "user_mgr_user_info_updated";
    private static UserMamanger c;
    private OkHttpClient d = new OkHttpClient();
    private ShareUserRecord e;

    private UserMamanger() {
    }

    public static synchronized UserMamanger a() {
        UserMamanger userMamanger;
        synchronized (UserMamanger.class) {
            if (c == null) {
                c = new UserMamanger();
            }
            userMamanger = c;
        }
        return userMamanger;
    }

    public void a(String str, SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor) {
        a(str, simpleDraweeView, basePostprocessor, false);
    }

    public void a(String str, final SimpleDraweeView simpleDraweeView, final BasePostprocessor basePostprocessor, boolean z) {
        final ShareUserRecord shareUserRecord = ShareUserRecord.get(str);
        if (shareUserRecord == null) {
            Log.d("UserMamanger", "cannot get ShareUserRecord for " + str);
        } else if (!TextUtils.isEmpty(shareUserRecord.url)) {
            b(shareUserRecord.url, simpleDraweeView, basePostprocessor);
        } else if (SHApplication.getStateNotifier().a() == 4) {
            a(str, (AsyncResponseCallback<ShareUserRecord>) new AsyncResponseCallback<ShareUserRecord>() {
                public void a(int i) {
                }

                public void a(int i, Object obj) {
                }

                public void a(ShareUserRecord shareUserRecord) {
                    if (shareUserRecord != null && shareUserRecord.url != null) {
                        UserMamanger.this.b(shareUserRecord.url, simpleDraweeView, basePostprocessor);
                    }
                }
            }, z);
        } else {
            this.d.newCall(new Request.Builder().url(String.format("https://api.account.xiaomi.com/pass/usersCard?ids=%s", new Object[]{str})).get().build()).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                }

                public void onResponse(Call call, Response response) throws IOException {
                    try {
                        String optString = new JSONObject(response.body().string()).getJSONObject("data").getJSONArray("list").getJSONObject(0).optString("miliaoIcon");
                        shareUserRecord.url = optString;
                        ShareUserRecord.insert(shareUserRecord);
                        UserMamanger.this.b(optString, simpleDraweeView, basePostprocessor);
                    } catch (Exception unused) {
                    }
                }
            });
        }
    }

    public void a(final AsyncResponseCallback<ShareUserRecord> asyncResponseCallback) {
        AccountUserApi.a(CoreApi.a().y(), new BasePassportCallback<MiAccountInfo>() {
            public void a(MiAccountInfo miAccountInfo) {
                final ShareUserRecord shareUserRecord = new ShareUserRecord();
                shareUserRecord.nickName = miAccountInfo.b;
                if (TextUtils.isEmpty(shareUserRecord.nickName)) {
                    shareUserRecord.nickName = miAccountInfo.g;
                }
                shareUserRecord.userId = miAccountInfo.f23513a;
                shareUserRecord.url = miAccountInfo.c;
                shareUserRecord.phone = miAccountInfo.d;
                shareUserRecord.email = miAccountInfo.f;
                MiAccountInfo.Gender gender = miAccountInfo.i;
                if (gender != null && gender.name().equals("MALE")) {
                    shareUserRecord.sex = XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.sex_male);
                } else if (gender == null || !gender.name().equals("FEMALE")) {
                    shareUserRecord.sex = XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.not_set);
                } else {
                    shareUserRecord.sex = XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.sex_female);
                }
                Calendar calendar = miAccountInfo.j;
                if (calendar != null) {
                    shareUserRecord.birth = String.format("%s/%s/%s", new Object[]{Integer.valueOf(calendar.get(1)), Integer.valueOf(calendar.get(2) + 1), Integer.valueOf(calendar.get(5))});
                }
                SHApplication.getGlobalWorkerHandler().post(new Runnable() {
                    public void run() {
                        ShareUserRecord.update(shareUserRecord);
                    }
                });
                UserMamanger.this.a(shareUserRecord);
                asyncResponseCallback.a(shareUserRecord);
            }

            public void a(int i, String str) {
                asyncResponseCallback.a(-1);
            }

            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                CoreApi.a().a(miServiceTokenInfo, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                    /* renamed from: a */
                    public void onSuccess(Void voidR) {
                    }

                    public void onFailure(Error error) {
                    }
                });
            }
        });
    }

    public void a(final String str, final AsyncResponseCallback<ShareUserRecord> asyncResponseCallback, final boolean z) {
        SHApplication.getGlobalWorkerHandler().post(new Runnable() {
            public void run() {
                final ShareUserRecord shareUserRecord = ShareUserRecord.get(str);
                if ((shareUserRecord != null && TextUtils.isEmpty(shareUserRecord.url)) || z) {
                    if (!(shareUserRecord == null || TextUtils.isEmpty(shareUserRecord.url) || asyncResponseCallback == null)) {
                        SHApplication.getGlobalHandler().post(new Runnable() {
                            public void run() {
                                UserMamanger.this.a(shareUserRecord);
                                asyncResponseCallback.a(shareUserRecord);
                            }
                        });
                    }
                    RemoteFamilyApi.a().a(SHApplication.getAppContext(), str, (AsyncCallback<UserInfo, Error>) new AsyncCallback<UserInfo, Error>() {
                        /* renamed from: a */
                        public void onSuccess(UserInfo userInfo) {
                            if (shareUserRecord == null) {
                                Log.e("UserManager", "ShareUserRecord is null");
                                return;
                            }
                            if (!TextUtils.isEmpty(userInfo.c)) {
                                shareUserRecord.url = userInfo.c;
                            }
                            shareUserRecord.nickName = userInfo.e;
                            ShareUserRecord.insert(shareUserRecord);
                            if (asyncResponseCallback != null) {
                                SHApplication.getGlobalHandler().post(new Runnable() {
                                    public void run() {
                                        UserMamanger.this.a(shareUserRecord);
                                        asyncResponseCallback.a(shareUserRecord);
                                    }
                                });
                            }
                        }

                        public void onFailure(Error error) {
                            if (asyncResponseCallback != null) {
                                SHApplication.getGlobalHandler().post(new Runnable() {
                                    public void run() {
                                        asyncResponseCallback.a(ErrorCode.ERROR_INVALID_REQUEST.getCode());
                                    }
                                });
                            }
                        }
                    });
                } else if (asyncResponseCallback != null) {
                    SHApplication.getGlobalHandler().post(new Runnable() {
                        public void run() {
                            UserMamanger.this.a(shareUserRecord);
                            asyncResponseCallback.a(shareUserRecord);
                        }
                    });
                }
            }
        });
    }

    public void a(ShareUserRecord shareUserRecord) {
        if (shareUserRecord != null) {
            UserInfo userInfo = new UserInfo();
            userInfo.c = shareUserRecord.url;
            userInfo.e = shareUserRecord.nickName;
            userInfo.f16462a = shareUserRecord.userId;
            userInfo.b = shareUserRecord.nickName;
            UserInfoManager.a().a(userInfo);
        }
    }

    public void b(String str, SimpleDraweeView simpleDraweeView, BasePostprocessor basePostprocessor) {
        if (Fresco.getDraweeControllerBuilderSupplier() != null && simpleDraweeView != null) {
            if (simpleDraweeView.getHierarchy() == null) {
                simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.default_icon_user240_nor)).setActualImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).setPlaceholderImageScaleType(ScalingUtils.ScaleType.CENTER_INSIDE).build());
            }
            Object tag = simpleDraweeView.getTag();
            if (tag != null && (tag instanceof String) && LoginPwdActivity.class.getSimpleName().equals(tag)) {
                ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setPlaceholderImage(simpleDraweeView.getResources().getDrawable(R.drawable.default_icon_user_nor));
                ((GenericDraweeHierarchy) simpleDraweeView.getHierarchy()).setRoundingParams(RoundingParams.asCircle());
            }
            if (TextUtils.isEmpty(str) || str.equals("null")) {
                simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.user_default));
                return;
            }
            int lastIndexOf = str.lastIndexOf(".");
            String substring = str.substring(lastIndexOf - 4, lastIndexOf);
            if (!substring.contains("_320") && !substring.contains("_150")) {
                str = str.substring(0, lastIndexOf) + "_320" + str.substring(lastIndexOf);
            }
            if (basePostprocessor == null) {
                simpleDraweeView.setImageURI(Uri.parse(str));
                return;
            }
            simpleDraweeView.setController((PipelineDraweeController) ((PipelineDraweeControllerBuilder) ((PipelineDraweeControllerBuilder) Fresco.newDraweeControllerBuilder().setImageRequest(ImageRequestBuilder.newBuilderWithSource(Uri.parse(str)).setPostprocessor(basePostprocessor).build())).setOldController(simpleDraweeView.getController())).build());
        }
    }

    public ShareUserRecord b() {
        return this.e;
    }

    public void b(ShareUserRecord shareUserRecord) {
        this.e = shareUserRecord;
        if (this.e != null) {
            LocalBroadcastManager.getInstance(SHApplication.getAppContext()).sendBroadcast(new Intent(b));
        }
    }
}
