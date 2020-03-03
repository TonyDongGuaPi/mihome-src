package com.xiaomi.youpin.login.api.phone;

import android.content.Context;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.accountsdk.account.data.QueryPhoneInfoParams;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.accountsdk.hasheddeviceidlib.HashedDeviceIdUtil;
import com.xiaomi.passport.uicontroller.PhoneLoginController;
import com.xiaomi.passport.v2.utils.ActivatorPhoneController;
import com.xiaomi.youpin.login.api.manager.LocalPhoneDataCache;
import com.xiaomi.youpin.login.other.log.LogUtils;
import java.util.ArrayList;

public class ActivatorPhoneManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f23470a = "ActivatorPhoneManager";
    private Context b;
    private String c;
    private PhoneLoginController d = new PhoneLoginController();
    private long e;

    private interface QueryLocalPhoneUserInfoCallback {
        void a();

        void a(LocalPhoneDetailInfo localPhoneDetailInfo);
    }

    public ActivatorPhoneManager(Context context) {
        this.b = context;
        this.c = new HashedDeviceIdUtil(context).getHashedDeviceIdNoThrow();
    }

    public void a(final boolean z) {
        final ArrayList arrayList = new ArrayList();
        ActivatorPhoneController activatorPhoneController = new ActivatorPhoneController(this.b);
        LogUtils.b(f23470a, "开始取号");
        this.e = System.currentTimeMillis();
        activatorPhoneController.getLocalActivatorPhone(new ActivatorPhoneController.PhoneNumCallback() {
            public void onDualSIM(ActivatorPhoneInfo activatorPhoneInfo, final ActivatorPhoneInfo activatorPhoneInfo2) {
                ActivatorPhoneManager.this.a();
                LogUtils.b(ActivatorPhoneManager.f23470a, "取号结果：双卡: 卡一： " + activatorPhoneInfo + " 卡二：" + activatorPhoneInfo2);
                LogUtils.b(ActivatorPhoneManager.f23470a, "开始查询用户信息");
                ActivatorPhoneManager.this.a(activatorPhoneInfo, new QueryLocalPhoneUserInfoCallback() {
                    public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
                        if (localPhoneDetailInfo != null) {
                            arrayList.add(localPhoneDetailInfo);
                        }
                        ActivatorPhoneManager.this.a(activatorPhoneInfo2, new QueryLocalPhoneUserInfoCallback() {
                            public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
                                ActivatorPhoneManager.this.a();
                                LogUtils.b(ActivatorPhoneManager.f23470a, "查询双卡用户信息成功");
                                if (localPhoneDetailInfo != null) {
                                    arrayList.add(localPhoneDetailInfo);
                                }
                                LocalPhoneDataCache.a().a(arrayList);
                            }

                            public void a() {
                                ActivatorPhoneManager.this.a();
                                LogUtils.b(ActivatorPhoneManager.f23470a, "查询双卡用户信息失败");
                                if (z) {
                                    ActivatorPhoneManager.this.a(false);
                                } else {
                                    LocalPhoneDataCache.a().a(arrayList);
                                }
                            }
                        });
                    }

                    public void a() {
                        ActivatorPhoneManager.this.a();
                        LogUtils.b(ActivatorPhoneManager.f23470a, "查询双卡用户信息失败");
                        if (z) {
                            ActivatorPhoneManager.this.a(false);
                        } else {
                            LocalPhoneDataCache.a().a(arrayList);
                        }
                    }
                });
            }

            public void onSingleSIM(ActivatorPhoneInfo activatorPhoneInfo) {
                ActivatorPhoneManager.this.a();
                LogUtils.b(ActivatorPhoneManager.f23470a, "取号结果：单卡: " + activatorPhoneInfo);
                LogUtils.b(ActivatorPhoneManager.f23470a, "开始查询用户信息");
                ActivatorPhoneManager.this.a(activatorPhoneInfo, new QueryLocalPhoneUserInfoCallback() {
                    public void a(LocalPhoneDetailInfo localPhoneDetailInfo) {
                        ActivatorPhoneManager.this.a();
                        LogUtils.b(ActivatorPhoneManager.f23470a, "查询单卡用户信息成功");
                        if (localPhoneDetailInfo != null) {
                            arrayList.add(localPhoneDetailInfo);
                        }
                        LocalPhoneDataCache.a().a(arrayList);
                    }

                    public void a() {
                        ActivatorPhoneManager.this.a();
                        LogUtils.b(ActivatorPhoneManager.f23470a, "查询单卡用户信息失败");
                        if (z) {
                            ActivatorPhoneManager.this.a(false);
                        } else {
                            LocalPhoneDataCache.a().a(arrayList);
                        }
                    }
                });
            }

            public void onNone() {
                ActivatorPhoneManager.this.a();
                LogUtils.b(ActivatorPhoneManager.f23470a, "取号结果：未检测到sim卡");
                LocalPhoneDataCache.a().a(arrayList);
            }
        }, z);
    }

    /* access modifiers changed from: private */
    public void a(final ActivatorPhoneInfo activatorPhoneInfo, final QueryLocalPhoneUserInfoCallback queryLocalPhoneUserInfoCallback) {
        if (activatorPhoneInfo != null) {
            this.d.queryPhoneUserInfo(new QueryPhoneInfoParams.Builder().phoneHashActivatorToken(activatorPhoneInfo).deviceId(this.c).build(), new PhoneLoginController.PhoneUserInfoCallback() {
                public void onRecycledOrNotRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a(new LocalPhoneDetailInfo(activatorPhoneInfo, registerUserInfo, 1));
                    }
                }

                public void onNotRecycledRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a(new LocalPhoneDetailInfo(activatorPhoneInfo, registerUserInfo, 2));
                    }
                }

                public void onProbablyRecycleRegisteredPhone(RegisterUserInfo registerUserInfo) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a(new LocalPhoneDetailInfo(activatorPhoneInfo, registerUserInfo, 3));
                    }
                }

                public void onPhoneNumInvalid() {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a((LocalPhoneDetailInfo) null);
                    }
                }

                public void onTicketOrTokenInvalid() {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a();
                    }
                }

                public void onQueryFailed(PhoneLoginController.ErrorCode errorCode, String str) {
                    if (queryLocalPhoneUserInfoCallback != null) {
                        queryLocalPhoneUserInfoCallback.a((LocalPhoneDetailInfo) null);
                    }
                }
            });
        } else if (queryLocalPhoneUserInfoCallback != null) {
            queryLocalPhoneUserInfoCallback.a((LocalPhoneDetailInfo) null);
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        LogUtils.b(f23470a, "用时：" + (System.currentTimeMillis() - this.e));
    }
}
