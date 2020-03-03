package com.xiaomi.smarthome.newui.mainpage;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.SystemClock;
import android.os.Vibrator;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.FrameLayout;
import com.xiaomi.smarthome.MainPageOpManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.SmartHomeConfig;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.library.common.util.SharePrefsManager;
import com.xiaomi.smarthome.lite.LiteSoundManager;
import com.xiaomi.smarthome.newui.DeviceListPageActionInterface;
import com.xiaomi.smarthome.newui.ShortcutResponseTimeTracer;
import com.xiaomi.smarthome.newui.adapter.DviceEditInterface;
import com.xiaomi.smarthome.newui.adapter.main_grid.CardLog;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.CardItem;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class DeviceMainGridSwitchHelper {

    /* renamed from: a  reason: collision with root package name */
    static final long f20673a = 1000;
    static final long b = 6000;
    private final Vibrator c;
    private Context d;
    /* access modifiers changed from: private */
    public DeviceGridCardViewHolder e;
    /* access modifiers changed from: private */
    public DviceEditInterface f;
    private Map<String, DeviceSwitchStatus> g = new HashMap();

    public static DeviceMainGridSwitchHelper a(DeviceGridCardViewHolder deviceGridCardViewHolder, Context context, DviceEditInterface dviceEditInterface) {
        return new DeviceMainGridSwitchHelper(context, deviceGridCardViewHolder, dviceEditInterface);
    }

    public DeviceMainGridSwitchHelper(Context context, DeviceGridCardViewHolder deviceGridCardViewHolder, DviceEditInterface dviceEditInterface) {
        this.c = (Vibrator) context.getSystemService("vibrator");
        this.d = context;
        this.e = deviceGridCardViewHolder;
        this.f = dviceEditInterface;
    }

    public DeviceSwitchStatus a(String str) {
        DeviceSwitchStatus deviceSwitchStatus = this.g.get(str);
        if (deviceSwitchStatus != null) {
            return deviceSwitchStatus;
        }
        DeviceSwitchStatus deviceSwitchStatus2 = new DeviceSwitchStatus();
        this.g.put(str, deviceSwitchStatus2);
        return deviceSwitchStatus2;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v2, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v4, resolved type: java.lang.String} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v3, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0031  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.lang.String a(com.xiaomi.smarthome.device.Device r5, com.xiaomi.smarthome.newui.card.Card.CardType r6, boolean r7) {
        /*
            com.xiaomi.smarthome.MainPageOpManager r0 = com.xiaomi.smarthome.MainPageOpManager.a()
            r1 = 0
            android.util.Pair r0 = r0.a((com.xiaomi.smarthome.device.Device) r5, (int) r1, (com.xiaomi.smarthome.newui.card.Card.CardType) r6, (boolean) r1)
            com.xiaomi.smarthome.MainPageOpManager r2 = com.xiaomi.smarthome.MainPageOpManager.a()
            r3 = 1
            android.util.Pair r5 = r2.a((com.xiaomi.smarthome.device.Device) r5, (int) r1, (com.xiaomi.smarthome.newui.card.Card.CardType) r6, (boolean) r3)
            r6 = 0
            if (r0 == 0) goto L_0x002e
            java.lang.Object r1 = r0.first
            com.xiaomi.smarthome.newui.card.CardItem$State r2 = com.xiaomi.smarthome.newui.card.CardItem.State.SELECTED
            if (r1 != r2) goto L_0x0023
            java.lang.Object r0 = r0.second
            java.lang.String r0 = (java.lang.String) r0
            r4 = r0
            r0 = r6
            r6 = r4
            goto L_0x002f
        L_0x0023:
            java.lang.Object r1 = r0.first
            com.xiaomi.smarthome.newui.card.CardItem$State r2 = com.xiaomi.smarthome.newui.card.CardItem.State.NOR
            if (r1 != r2) goto L_0x002e
            java.lang.Object r0 = r0.second
            java.lang.String r0 = (java.lang.String) r0
            goto L_0x002f
        L_0x002e:
            r0 = r6
        L_0x002f:
            if (r5 == 0) goto L_0x0048
            java.lang.Object r1 = r5.first
            com.xiaomi.smarthome.newui.card.CardItem$State r2 = com.xiaomi.smarthome.newui.card.CardItem.State.SELECTED
            if (r1 != r2) goto L_0x003d
            java.lang.Object r5 = r5.second
            r6 = r5
            java.lang.String r6 = (java.lang.String) r6
            goto L_0x0048
        L_0x003d:
            java.lang.Object r1 = r5.first
            com.xiaomi.smarthome.newui.card.CardItem$State r2 = com.xiaomi.smarthome.newui.card.CardItem.State.NOR
            if (r1 != r2) goto L_0x0048
            java.lang.Object r5 = r5.second
            r0 = r5
            java.lang.String r0 = (java.lang.String) r0
        L_0x0048:
            if (r7 == 0) goto L_0x004b
            goto L_0x004c
        L_0x004b:
            r6 = r0
        L_0x004c:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.newui.mainpage.DeviceMainGridSwitchHelper.a(com.xiaomi.smarthome.device.Device, com.xiaomi.smarthome.newui.card.Card$CardType, boolean):java.lang.String");
    }

    public void a(final Device device, final Card.CardType cardType, AtomicBoolean atomicBoolean, final View.OnClickListener onClickListener) {
        this.e.switchIvContainer.setOnClickListener((View.OnClickListener) null);
        this.e.switchIvContainer.setEnabled(false);
        FrameLayout frameLayout = this.e.switchIvContainer;
        AnonymousClass1 r2 = new Runnable() {
            public void run() {
                DeviceMainGridSwitchHelper.this.e.switchIvContainer.setEnabled(true);
                DeviceMainGridSwitchHelper.this.e.switchIvContainer.setOnClickListener(onClickListener);
            }
        };
        double allAnimDuration = (double) this.e.i.getAllAnimDuration();
        Double.isNaN(allAnimDuration);
        frameLayout.postDelayed(r2, (long) (allAnimDuration * 1.1d));
        a(device, cardType, atomicBoolean.get(), true, new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (device == null) {
                    LogUtilGrey.a("DeviceMainGridSwitchHelper", "playClickEffect:device is null");
                    return;
                }
                DeviceMainGridSwitchHelper.this.a(device.did).c = SystemClock.elapsedRealtime();
            }
        });
        if (GlobalSetting.q || GlobalSetting.u) {
            StringBuilder sb = new StringBuilder();
            sb.append("toggle start,device=");
            sb.append(device == null ? null : device.name);
            LogUtilGrey.a("SwitchAnim", sb.toString());
        }
        if (device != null) {
            final boolean z = atomicBoolean.get();
            atomicBoolean.set(!atomicBoolean.get());
            DeviceSwitchStatus a2 = a(device.did);
            long elapsedRealtime = SystemClock.elapsedRealtime();
            if (!a2.d || elapsedRealtime - a2.f20679a >= 6000) {
                long j = 1000;
                if (elapsedRealtime - a2.b > 1000 && elapsedRealtime - a2.c > 1000) {
                    j = 0;
                    a2.d = true;
                }
                Object tag = this.e.switchIvContainer.getTag();
                if (tag != null) {
                    this.e.switchIvContainer.setTag((Object) null);
                    if (tag instanceof Runnable) {
                        this.e.switchIvContainer.removeCallbacks((Runnable) tag);
                    }
                }
                AnonymousClass3 r0 = new Runnable() {
                    public void run() {
                        Pair<CardItem.State, String> a2 = MainPageOpManager.a().a(device, 0, cardType, false);
                        if (a2 != null) {
                            if (!z && a2.first == CardItem.State.SELECTED) {
                                return;
                            }
                            if (!z || a2.first != CardItem.State.NOR) {
                                DeviceMainGridSwitchHelper.this.a(device.did).d = true;
                                ShortcutResponseTimeTracer.a(device, 0, Integer.MAX_VALUE);
                                CardLog.a(device, DeviceMainGridSwitchHelper.this.f.u());
                                if (GlobalSetting.q || GlobalSetting.u) {
                                    LogUtilGrey.a("SwitchAnim", "toggle request start,device=" + device.name);
                                }
                                Pair<CardItem.State, String> a3 = MainPageOpManager.a().a(device, 0, cardType, true);
                                MainPageOpManager.a().a(device, 0, cardType, (AsyncCallback<Void, Error>) new AsyncCallback<Void, Error>() {
                                    /* renamed from: a */
                                    public void onSuccess(Void voidR) {
                                        ShortcutResponseTimeTracer.a(device, 1, 0);
                                        if (GlobalSetting.q || GlobalSetting.u) {
                                            LogUtilGrey.a("SwitchAnim", "toggle request onSuccess,device=" + device.name);
                                        }
                                        DeviceSwitchStatus a2 = DeviceMainGridSwitchHelper.this.a(device.did);
                                        a2.b = SystemClock.elapsedRealtime();
                                        a2.d = false;
                                    }

                                    public void onFailure(Error error) {
                                        String str;
                                        StringBuilder sb = new StringBuilder();
                                        sb.append("toggleButton fail ");
                                        if (error == null) {
                                            str = "null";
                                        } else {
                                            str = "code:" + error.a() + " msg:" + error.b();
                                        }
                                        sb.append(str);
                                        LogUtil.b("mijia-card", sb.toString());
                                        DeviceMainGridSwitchHelper.this.a(device.did).d = false;
                                        ShortcutResponseTimeTracer.a(device, 2, error == null ? -9999 : error.a());
                                        if (GlobalSetting.q || GlobalSetting.u) {
                                            StringBuilder sb2 = new StringBuilder();
                                            sb2.append("toggle request onFailure:");
                                            sb2.append(error == null ? null : error.toString());
                                            LogUtilGrey.a("SwitchAnim", sb2.toString());
                                        }
                                        Pair<CardItem.State, String> a2 = MainPageOpManager.a().a(device, 0, cardType, false);
                                        if (a2 != null) {
                                            DeviceMainGridSwitchHelper.this.a(a2);
                                        }
                                    }
                                });
                                if (a3 != null && a3.second != null) {
                                    DeviceMainGridSwitchHelper.this.e.switchIv.setImageBitmap(BitmapFactory.decodeFile((String) a3.second));
                                }
                            }
                        }
                    }
                };
                this.e.switchIvContainer.setTag(r0);
                this.e.switchIvContainer.postDelayed(r0, j);
                a2.f20679a = SystemClock.elapsedRealtime();
            }
        }
    }

    private void a(Device device, Card.CardType cardType, boolean z, Animator.AnimatorListener animatorListener) {
        int left = this.e.switchIvContainer.getLeft() + (this.e.switchIvContainer.getWidth() / 2);
        int top = this.e.switchIvContainer.getTop() + (this.e.switchIvContainer.getHeight() / 2);
        b(device, cardType, z);
        this.e.i.setVisibility(0);
        this.e.i.clearAnimation();
        final Animator.AnimatorListener animatorListener2 = animatorListener;
        final boolean z2 = z;
        final Device device2 = device;
        final Card.CardType cardType2 = cardType;
        this.e.i.startAnimation(left, top, z ? 4 : 3, new Animator.AnimatorListener() {
            public void onAnimationStart(Animator animator) {
                if (animatorListener2 != null) {
                    animatorListener2.onAnimationStart(animator);
                }
            }

            public void onAnimationEnd(Animator animator) {
                if (z2) {
                    DeviceMainGridSwitchHelper.this.b();
                } else {
                    DeviceMainGridSwitchHelper.this.a();
                }
                if (GlobalSetting.q || GlobalSetting.u) {
                    Log.d("SwitchAnim", "playOnOffBgAnim onAnimationEnd isCurrentOn=" + z2 + ",Status to Normal");
                }
                DeviceMainGridSwitchHelper.this.c(device2, cardType2, z2);
                if (animatorListener2 != null) {
                    animatorListener2.onAnimationEnd(animator);
                }
            }

            public void onAnimationCancel(Animator animator) {
                if (z2) {
                    DeviceMainGridSwitchHelper.this.b();
                } else {
                    DeviceMainGridSwitchHelper.this.a();
                }
                if (animatorListener2 != null) {
                    animatorListener2.onAnimationCancel(animator);
                }
            }

            public void onAnimationRepeat(Animator animator) {
                if (animatorListener2 != null) {
                    animatorListener2.onAnimationRepeat(animator);
                }
            }
        });
    }

    private void a(Device device, Card.CardType cardType, boolean z, boolean z2, Animator.AnimatorListener animatorListener) {
        if (z2) {
            if (this.c.hasVibrator()) {
                this.c.vibrate(100);
            }
            if (SharePrefsManager.b(SHApplication.getAppContext(), SmartHomeConfig.i, SmartHomeConfig.l, true)) {
                if (z) {
                    LiteSoundManager.a().e();
                } else {
                    LiteSoundManager.a().d();
                }
            }
            a(device, cardType, z, animatorListener);
        }
    }

    private void b(Device device, Card.CardType cardType, boolean z) {
        TransitionDrawable transitionDrawable;
        String a2 = a(device, cardType, true);
        String a3 = a(device, cardType, false);
        if (a2 != null && a3 != null) {
            BitmapDrawable bitmapDrawable = new BitmapDrawable(this.d.getResources(), a2);
            BitmapDrawable bitmapDrawable2 = new BitmapDrawable(this.d.getResources(), a3);
            if (z) {
                transitionDrawable = new TransitionDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable2});
            } else {
                transitionDrawable = new TransitionDrawable(new Drawable[]{bitmapDrawable2, bitmapDrawable});
            }
            this.e.switchIv.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(300);
        }
    }

    /* access modifiers changed from: private */
    public void c(Device device, Card.CardType cardType, boolean z) {
        String a2 = a(device, cardType, true);
        String a3 = a(device, cardType, false);
        if (z) {
            if (a3 != null) {
                this.e.switchIv.setImageBitmap(BitmapFactory.decodeFile(a3));
            }
        } else if (a2 != null) {
            this.e.switchIv.setImageBitmap(BitmapFactory.decodeFile(a2));
        }
    }

    /* access modifiers changed from: private */
    public void a() {
        DeviceListPageActionInterface t = this.f.t();
        if (t == null || !t.isEditMode()) {
            this.e.f20654a.setBackground(this.d.getResources().getDrawable(R.drawable.main_grid_card_bg_normal_v2));
            if (this.e.i != null) {
                this.e.i.setVisibility(8);
                return;
            }
            return;
        }
        b();
    }

    /* access modifiers changed from: private */
    public void b() {
        this.e.f20654a.setBackground(this.d.getResources().getDrawable(R.drawable.main_grid_card_bg_normal_v2));
        if (this.e.i != null) {
            this.e.i.setVisibility(8);
        }
    }

    private void c() {
        this.e.switchIv.setBackground((Drawable) null);
        this.e.switchIv.setImageDrawable(this.d.getResources().getDrawable(R.drawable.shape_main_card_item_unable));
        this.e.f20654a.setBackground(this.d.getResources().getDrawable(R.drawable.main_grid_card_bg_offline_v2));
        if (this.e.i != null) {
            this.e.i.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(Pair<CardItem.State, String> pair) {
        if (pair != null) {
            if (pair.second != null) {
                this.e.switchIvContainer.setVisibility(0);
                this.e.switchIv.setImageBitmap(BitmapFactory.decodeFile((String) pair.second));
            } else {
                this.e.switchIvContainer.setVisibility(8);
            }
            if (pair.first == CardItem.State.SELECTED) {
                a();
            } else if (pair.first == CardItem.State.NOR) {
                b();
            } else if (pair.first == CardItem.State.UNABLE) {
                c();
            } else {
                this.e.switchIvContainer.setVisibility(8);
                this.e.f20654a.setBackground(this.d.getResources().getDrawable(R.drawable.main_grid_card_bg_normal_v2));
                if (this.e.i != null) {
                    this.e.i.setVisibility(8);
                }
            }
        }
    }

    static class DeviceSwitchStatus {

        /* renamed from: a  reason: collision with root package name */
        long f20679a = 0;
        long b = 0;
        long c = 0;
        boolean d = false;

        DeviceSwitchStatus() {
        }
    }
}