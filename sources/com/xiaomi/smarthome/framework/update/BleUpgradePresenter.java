package com.xiaomi.smarthome.framework.update;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.URLUtil;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;
import com.xiaomi.smarthome.bluetooth.Response;
import com.xiaomi.smarthome.bluetooth.XmBluetoothDevice;
import com.xiaomi.smarthome.bluetooth.XmBluetoothManager;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.api.BleMeshFirmwareUpdateInfo;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.XmPluginHostApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.framework.update.blemesh.IUpgradeHost;
import com.xiaomi.smarthome.framework.update.blemesh.IUpgradePresenter;
import com.xiaomi.smarthome.library.bluetooth.OTAErrorCode;
import com.xiaomi.smarthome.library.bluetooth.connect.Code;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.miio.update.ModelUpdateInfo;
import com.xiaomi.smarthome.stat.STAT;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class BleUpgradePresenter extends IUpgradePresenter {
    private static final String c = "BleUpgradePresenter";
    private static final int d = 0;
    private static final int e = 1;
    private static final int f = 2;
    private static final int w = 5000;
    private static final int x = 4;
    private static final int y = 3;
    /* access modifiers changed from: private */
    public ModelUpdateInfo g;
    /* access modifiers changed from: private */
    public Device h;
    /* access modifiers changed from: private */
    public BleMeshFirmwareUpdateInfo i;
    private int j;
    /* access modifiers changed from: private */
    public volatile boolean k;
    /* access modifiers changed from: private */
    public IUpgradeHost l;
    /* access modifiers changed from: private */
    public String m;
    /* access modifiers changed from: private */
    public String n;
    /* access modifiers changed from: private */
    public String o;
    /* access modifiers changed from: private */
    public String p;
    /* access modifiers changed from: private */
    public String q;
    /* access modifiers changed from: private */
    public long r = -1;
    /* access modifiers changed from: private */
    public long s = -1;
    /* access modifiers changed from: private */
    public long t = -1;
    /* access modifiers changed from: private */
    public int u;
    /* access modifiers changed from: private */
    public Map<Integer, Long> v = new HashMap();

    interface SuccessStep {

        /* renamed from: a  reason: collision with root package name */
        public static final int f17723a = 1;
        public static final int b = 2;
        public static final int c = 3;
        public static final int d = 4;
        public static final int e = 5;
        public static final int f = 6;
    }

    public BleUpgradePresenter(IUpgradeHost iUpgradeHost, int i2, Device device, String str, String str2, String str3) {
        this.l = iUpgradeHost;
        this.h = device;
        this.q = device.model;
        this.u = i2;
        this.g = new ModelUpdateInfo();
        this.g.f = this.h.version;
        this.g.d = this.h.name;
        this.n = URLUtil.isNetworkUrl(str) ? str : null;
        this.o = str2;
        this.p = str3;
        BluetoothLog.a("ble ota presenter model %s,testVersion name %s, file md5 %s,test Url %s", this.q, str2, str3, str);
    }

    public String a() {
        return this.g.f;
    }

    public String b() {
        return this.g.g;
    }

    public String c() {
        if (this.i != null) {
            return this.i.changeLog;
        }
        LogUtil.e(c, "getUpgradeDescription: mUpdateInfo =null");
        return "";
    }

    public String d() {
        return this.m;
    }

    public boolean e() {
        return !TextUtils.isEmpty(this.n) || !TextUtils.isEmpty(this.o);
    }

    public void f() {
        this.l.showLoading();
        this.r = System.currentTimeMillis();
        Observable.just(this.h).observeOn(Schedulers.io()).flatMap(new Function<Device, ObservableSource<Boolean>>() {
            /* renamed from: a */
            public ObservableSource<Boolean> apply(Device device) throws Exception {
                return BleUpgradePresenter.this.a(device, false);
            }
        }).flatMap(new Function<Boolean, ObservableSource<String>>() {
            /* renamed from: a */
            public ObservableSource<String> apply(Boolean bool) throws Exception {
                long currentTimeMillis = System.currentTimeMillis() - BleUpgradePresenter.this.r;
                long unused = BleUpgradePresenter.this.r = System.currentTimeMillis();
                BleUpgradePresenter.this.v.put(1, Long.valueOf(currentTimeMillis));
                IUpgradeHost b = BleUpgradePresenter.this.l;
                b.showLog("设备连接时间" + (currentTimeMillis / 1000) + "秒");
                return BleUpgradePresenter.this.b(BleUpgradePresenter.this.h);
            }
        }).flatMap(new Function<String, ObservableSource<Boolean>>() {
            /* renamed from: a */
            public ObservableSource<Boolean> apply(String str) throws Exception {
                long currentTimeMillis = System.currentTimeMillis() - BleUpgradePresenter.this.r;
                long unused = BleUpgradePresenter.this.r = System.currentTimeMillis();
                BleUpgradePresenter.this.v.put(2, Long.valueOf(currentTimeMillis));
                IUpgradeHost b = BleUpgradePresenter.this.l;
                b.showLog("读取固件版本号时间" + (currentTimeMillis / 1000) + "秒");
                return BleUpgradePresenter.this.c(BleUpgradePresenter.this.h);
            }
        }).subscribe(new Observer<Boolean>() {
            /* renamed from: a */
            public void onNext(Boolean bool) {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onError(Throwable th) {
                if (th instanceof OTAException) {
                    int code = ((OTAException) th).getCode();
                    STAT.i.a(BleUpgradePresenter.this.q, code, th.getMessage());
                    BleUpgradePresenter.this.l.onCallback(code);
                    BluetoothLog.d("checkIfNeedUpdate,code=" + code);
                }
                LogUtil.e(BleUpgradePresenter.c, "checkIfNeedUpdate error:" + Log.getStackTraceString(th));
                String unused = BleUpgradePresenter.this.m = th.getMessage();
                BleUpgradePresenter.this.l.showFailedInfo(th.getMessage());
            }

            public void onComplete() {
                long currentTimeMillis = System.currentTimeMillis() - BleUpgradePresenter.this.r;
                long unused = BleUpgradePresenter.this.r = -1;
                IUpgradeHost b = BleUpgradePresenter.this.l;
                b.showLog("读取远程版本号时间" + (currentTimeMillis / 1000) + "秒");
                BleUpgradePresenter.this.v.put(3, Long.valueOf(currentTimeMillis));
                BleUpgradePresenter.this.l.updateUpgradeInfoView();
            }
        });
    }

    /* access modifiers changed from: private */
    public boolean i() {
        return XmBluetoothManager.getInstance().getConnectStatus(this.h.mac) == 2;
    }

    public void g() {
        final long currentTimeMillis = System.currentTimeMillis();
        Observable.just(this.h).observeOn(Schedulers.io()).flatMap(new Function<Device, ObservableSource<Boolean>>() {
            /* renamed from: a */
            public ObservableSource<Boolean> apply(Device device) throws Exception {
                return BleUpgradePresenter.this.a(device, true);
            }
        }).flatMap(new Function<Boolean, ObservableSource<String>>() {
            /* renamed from: a */
            public ObservableSource<String> apply(Boolean bool) throws Exception {
                BleUpgradePresenter.this.l.hideLoaing();
                BleUpgradePresenter.this.l.showProgress(0);
                long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                if (currentTimeMillis > 1000) {
                    BleUpgradePresenter.this.v.put(1, Long.valueOf(currentTimeMillis));
                }
                return Observable.create(new ObservableOnSubscribe<String>() {
                    public void subscribe(final ObservableEmitter<String> observableEmitter) throws Exception {
                        LogUtil.c(BleUpgradePresenter.c, "start downloadFirmware ");
                        if (BleUpgradePresenter.this.k) {
                            observableEmitter.onError(new OTAException(2));
                        } else if (BleUpgradePresenter.this.i != null || BleUpgradePresenter.this.e()) {
                            BleUpgradePresenter.this.a(1);
                            int unused = BleUpgradePresenter.this.f17781a = 1;
                            String l = TextUtils.isEmpty(BleUpgradePresenter.this.n) ? BleUpgradePresenter.this.i.safeUrl : BleUpgradePresenter.this.n;
                            if (TextUtils.isEmpty(l)) {
                                BluetoothLog.d("start download Firmware,but url is null!");
                                observableEmitter.onError(new OTAException(OTAErrorCode.q));
                                return;
                            }
                            final long currentTimeMillis = System.currentTimeMillis();
                            BleUpgradePresenter.this.a(l, new Response.FirmwareUpgradeResponse() {
                                public void onResponse(int i, String str, String str2) {
                                    String m = BleUpgradePresenter.this.e() ? BleUpgradePresenter.this.p : BleUpgradePresenter.this.i.md5;
                                    BluetoothLog.a("download firmware ,remote md5 %s,download fileMd5 %s", m, str2);
                                    if (i != 0 || TextUtils.isEmpty(str)) {
                                        LogUtil.c(BleUpgradePresenter.c, String.format("downloadFirmware failed,error code: %d , path: %s", new Object[]{Integer.valueOf(i), str}));
                                        observableEmitter.onError(new OTAException(OTAErrorCode.o));
                                        return;
                                    }
                                    long currentTimeMillis = System.currentTimeMillis() - currentTimeMillis;
                                    IUpgradeHost b2 = BleUpgradePresenter.this.l;
                                    b2.showLog("下载固件时间" + (currentTimeMillis / 1000) + "秒");
                                    File file = new File(str);
                                    if (file.exists()) {
                                        IUpgradeHost b3 = BleUpgradePresenter.this.l;
                                        b3.showLog("固件大小" + (file.length() / 1024) + "kb");
                                    }
                                    if (m == null || (!TextUtils.isEmpty(m) && m.equals(str2))) {
                                        observableEmitter.onNext(str);
                                        observableEmitter.onComplete();
                                        BleUpgradePresenter.this.l.showLog("固件md5 校验通过");
                                        BleUpgradePresenter.this.v.put(4, Long.valueOf(currentTimeMillis));
                                        return;
                                    }
                                    observableEmitter.onError(new OTAException(OTAErrorCode.p));
                                }

                                public void onProgress(int i) {
                                    LogUtil.a(BleUpgradePresenter.c, "downloadFirmware,progress:" + i);
                                }
                            });
                        } else {
                            observableEmitter.onError(new OTAException(1));
                        }
                    }
                });
            }
        }).flatMap(new Function<String, ObservableSource<Boolean>>() {
            /* renamed from: a */
            public ObservableSource<Boolean> apply(final String str) throws Exception {
                if (new File(str).exists()) {
                    return Observable.create(new ObservableOnSubscribe<Boolean>() {
                        public void subscribe(final ObservableEmitter<Boolean> observableEmitter) throws Exception {
                            LogUtil.c(BleUpgradePresenter.c, "startBleMeshUpgrade ");
                            if (BleUpgradePresenter.this.k) {
                                observableEmitter.onError(new OTAException(2));
                                return;
                            }
                            BleUpgradePresenter.this.a(2);
                            long unused = BleUpgradePresenter.this.s = System.currentTimeMillis();
                            XmBluetoothManager.getInstance().startBleMeshUpgrade(BleUpgradePresenter.this.h.mac, BleUpgradePresenter.this.h.did, BleUpgradePresenter.this.g.f, str, new Response.BleUpgradeResponse() {
                                public void onProgress(int i) {
                                    LogUtil.a(BleUpgradePresenter.c, "startBleMeshUpgrade, progress:" + i);
                                    BleUpgradePresenter.this.l.showProgress(i);
                                    if (BleUpgradePresenter.this.s > 0 && i > 99) {
                                        long currentTimeMillis = System.currentTimeMillis() - BleUpgradePresenter.this.s;
                                        long unused = BleUpgradePresenter.this.s = -1;
                                        IUpgradeHost b2 = BleUpgradePresenter.this.l;
                                        b2.showLog("ota 固件传输成功到开始切换固件耗时：" + (currentTimeMillis / 1000) + "秒");
                                        BleUpgradePresenter.this.v.put(5, Long.valueOf(currentTimeMillis));
                                        long unused2 = BleUpgradePresenter.this.t = System.currentTimeMillis();
                                    }
                                }

                                /* renamed from: a */
                                public void onResponse(int i, String str) {
                                    BleUpgradePresenter.this.a(0);
                                    if (i == 0) {
                                        BleUpgradePresenter.this.g.f = BleUpgradePresenter.this.g.g;
                                        BleUpgradePresenter.this.h.version = BleUpgradePresenter.this.g.g;
                                        observableEmitter.onNext(true);
                                        observableEmitter.onComplete();
                                        return;
                                    }
                                    String format = String.format("upgrade error,error code:%d", new Object[]{Integer.valueOf(i)});
                                    LogUtil.b(BleUpgradePresenter.c, format);
                                    observableEmitter.onError(new OTAException(i, format));
                                }

                                public boolean isMeshDevice() {
                                    return BleUpgradePresenter.this.u == 5;
                                }
                            });
                        }
                    });
                }
                throw new Exception("upgrade file not exist.");
            }
        }).flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
            /* renamed from: a */
            public ObservableSource<Boolean> apply(Boolean bool) throws Exception {
                return BleUpgradePresenter.this.a(BleUpgradePresenter.this.h);
            }
        }).delay(5, TimeUnit.SECONDS).flatMap(new Function<Boolean, ObservableSource<Boolean>>() {
            /* renamed from: a */
            public ObservableSource<Boolean> apply(Boolean bool) throws Exception {
                return BleUpgradePresenter.this.d(BleUpgradePresenter.this.h);
            }
        }).subscribe(new Observer<Boolean>() {
            /* renamed from: a */
            public void onNext(Boolean bool) {
            }

            public void onSubscribe(Disposable disposable) {
            }

            public void onError(Throwable th) {
                BleUpgradePresenter.this.a(0);
                if (th instanceof OTAException) {
                    int code = ((OTAException) th).getCode();
                    BluetoothLog.b("start upgrade failed ,code =" + code);
                    STAT.i.f(BleUpgradePresenter.this.q, code);
                    BleUpgradePresenter.this.l.onCallback(code);
                }
                BluetoothLog.b("start upgrade failed, error msg:" + th.getMessage());
                BleUpgradePresenter.this.l.showLog(th.getMessage());
                BleUpgradePresenter.this.l.showFailedInfo((String) null);
            }

            public void onComplete() {
                BleUpgradePresenter.this.a(0);
                LogUtil.c(BleUpgradePresenter.c, "upgrade finished.");
                BleUpgradePresenter.this.l.onCallback(0);
                BleUpgradePresenter.this.j();
                STAT.i.f(BleUpgradePresenter.this.q, 0);
                BleUpgradePresenter.this.l.showFinishInfo(true);
            }
        });
    }

    /* access modifiers changed from: private */
    public void j() {
        for (Map.Entry next : this.v.entrySet()) {
            BluetoothLog.a("reportSuccessTime, step %d, time %s", next.getKey(), String.valueOf(next.getValue()));
            STAT.i.a(this.q, ((Integer) next.getKey()).intValue(), ((Long) next.getValue()).longValue());
        }
        this.v.clear();
    }

    /* access modifiers changed from: private */
    public Observable<Boolean> a(final Device device, final boolean z) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                if (BleUpgradePresenter.this.i()) {
                    LogUtil.a(BleUpgradePresenter.c, "ble Connect:already connected");
                    observableEmitter.onNext(true);
                    observableEmitter.onComplete();
                    return;
                }
                if (z) {
                    BleUpgradePresenter.this.l.showLoading();
                }
                BleUpgradePresenter.this.a(device, BleUpgradePresenter.this.u, observableEmitter);
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final Device device, int i2, final ObservableEmitter<Boolean> observableEmitter) {
        final String str = device.mac;
        if (i2 == 0) {
            XmBluetoothManager.getInstance().secureConnect(str, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (-10 == i) {
                        XmBluetoothManager.getInstance().removeToken(str);
                        BleUpgradePresenter.this.a(device, 0, (ObservableEmitter<Boolean>) observableEmitter);
                    } else if (i == 0) {
                        observableEmitter.onNext(true);
                        observableEmitter.onComplete();
                    } else {
                        LogUtil.b(BleUpgradePresenter.c, String.format("%s ,connect %s fail,secureConnect, error code %d", new Object[]{BleUpgradePresenter.c, str, Integer.valueOf(i)}));
                        String a2 = Code.a(i);
                        IUpgradeHost b2 = BleUpgradePresenter.this.l;
                        b2.showLog("blue tooth error: code " + i + ",msg:" + a2);
                        ObservableEmitter observableEmitter = observableEmitter;
                        observableEmitter.onError(new OTAException(i, i + ":" + a2));
                    }
                }
            });
        } else if (i2 == 1) {
            XmBluetoothManager.getInstance().securityChipConnect(str, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (i == 0) {
                        observableEmitter.onNext(true);
                        observableEmitter.onComplete();
                        return;
                    }
                    LogUtil.b(BleUpgradePresenter.c, String.format("%s ,connect %s fail,securityChipConnect, error code %d", new Object[]{BleUpgradePresenter.c, str, Integer.valueOf(i)}));
                    String a2 = Code.a(i);
                    IUpgradeHost b2 = BleUpgradePresenter.this.l;
                    b2.showLog("blue tooth error: code " + i + ",msg:" + a2);
                    ObservableEmitter observableEmitter = observableEmitter;
                    observableEmitter.onError(new OTAException(i, i + ":" + a2));
                }
            });
        } else if (i2 == 2) {
            XmBluetoothManager.getInstance().securityChipSharedDeviceConnect(str, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (i == 0) {
                        observableEmitter.onNext(true);
                        observableEmitter.onComplete();
                        return;
                    }
                    LogUtil.b(BleUpgradePresenter.c, String.format("%s ,connect %s fail,securityChipSharedDeviceConnect, error code %d", new Object[]{BleUpgradePresenter.c, str, Integer.valueOf(i)}));
                    String a2 = Code.a(i);
                    IUpgradeHost b2 = BleUpgradePresenter.this.l;
                    b2.showLog("blue tooth error: code " + i + ",msg:" + a2);
                    ObservableEmitter observableEmitter = observableEmitter;
                    observableEmitter.onError(new OTAException(i, i + ":" + a2));
                }
            });
        } else if (i2 == 3) {
            XmBluetoothManager.getInstance().connect(str, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (i == 0) {
                        observableEmitter.onNext(true);
                        observableEmitter.onComplete();
                        return;
                    }
                    LogUtil.b(BleUpgradePresenter.c, String.format("%s ,connect %s fail,normal connect, error code %d", new Object[]{BleUpgradePresenter.c, str, Integer.valueOf(i)}));
                    String a2 = Code.a(i);
                    IUpgradeHost b2 = BleUpgradePresenter.this.l;
                    b2.showLog("blue tooth error: code " + i + ",msg:" + a2);
                    ObservableEmitter observableEmitter = observableEmitter;
                    observableEmitter.onError(new OTAException(i, i + ":" + a2));
                }
            });
        } else if (i2 == 4) {
            XmBluetoothManager.getInstance().bleStandardAuthConnect(str, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (i == 0) {
                        observableEmitter.onNext(true);
                        observableEmitter.onComplete();
                        return;
                    }
                    LogUtil.b(BleUpgradePresenter.c, String.format("%s ,connect %s fail,bleStandardAuth connect, error code %d", new Object[]{BleUpgradePresenter.c, str, Integer.valueOf(i)}));
                    String a2 = Code.a(i);
                    IUpgradeHost b2 = BleUpgradePresenter.this.l;
                    b2.showLog("blue tooth error: code " + i + ",msg:" + a2);
                    ObservableEmitter observableEmitter = observableEmitter;
                    observableEmitter.onError(new OTAException(i, i + ":" + a2));
                }
            });
        } else if (i2 == 5) {
            XmBluetoothManager.getInstance().bleMeshConnect(str, device.did, new Response.BleConnectResponse() {
                /* renamed from: a */
                public void onResponse(int i, Bundle bundle) {
                    if (i == 0) {
                        observableEmitter.onNext(true);
                        observableEmitter.onComplete();
                        return;
                    }
                    LogUtil.b(BleUpgradePresenter.c, String.format("%s ,connect %s fail,bleMesh connect, error code %d", new Object[]{BleUpgradePresenter.c, str, Integer.valueOf(i)}));
                    String a2 = Code.a(i);
                    IUpgradeHost b2 = BleUpgradePresenter.this.l;
                    b2.showLog("blue tooth error: code " + i + ",msg:" + a2);
                    ObservableEmitter observableEmitter = observableEmitter;
                    observableEmitter.onError(new OTAException(i, i + ":" + a2));
                }
            });
        }
    }

    /* access modifiers changed from: private */
    public Observable<Boolean> a(final Device device) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            public void subscribe(ObservableEmitter<Boolean> observableEmitter) throws Exception {
                if (!BleUpgradePresenter.this.i()) {
                    LogUtil.a(BleUpgradePresenter.c, "disconnect:already disconnected");
                } else {
                    LogUtil.a(BleUpgradePresenter.c, "start disconnect");
                    XmBluetoothManager.getInstance().disconnect(device.mac);
                }
                observableEmitter.onNext(true);
                observableEmitter.onComplete();
            }
        });
    }

    /* access modifiers changed from: private */
    public Observable<String> b(final Device device) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            public void subscribe(final ObservableEmitter<String> observableEmitter) throws Exception {
                XmBluetoothManager.getInstance().getBleMeshFirmwareVersion(device.mac, new Response.BleReadFirmwareVersionResponse() {
                    /* renamed from: a */
                    public void onResponse(int i, String str) {
                        if (i == 0) {
                            BluetoothLog.a("ota read ble version %s", str);
                            device.version = str;
                            BleUpgradePresenter.this.g.f = str;
                            observableEmitter.onNext(str);
                            observableEmitter.onComplete();
                            return;
                        }
                        BluetoothLog.d(String.format("read version error, code:%d", new Object[]{Integer.valueOf(i)}));
                        ObservableEmitter observableEmitter = observableEmitter;
                        observableEmitter.onError(new OTAException(OTAErrorCode.m, "read firmware version fail,code=" + i));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public Observable<Boolean> c(final Device device) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            public void subscribe(final ObservableEmitter<Boolean> observableEmitter) throws Exception {
                if (BleUpgradePresenter.this.e()) {
                    BleUpgradePresenter.this.g.g = BleUpgradePresenter.this.o;
                    observableEmitter.onNext(true);
                    observableEmitter.onComplete();
                    return;
                }
                XmPluginHostApi.instance().getBleMeshFirmwareUpdateInfo(device.model, device.did, new Callback<BleMeshFirmwareUpdateInfo>() {
                    /* renamed from: a */
                    public void onSuccess(BleMeshFirmwareUpdateInfo bleMeshFirmwareUpdateInfo) {
                        if (bleMeshFirmwareUpdateInfo != null) {
                            BluetoothLog.a("ota read online version ,update info =" + bleMeshFirmwareUpdateInfo.toString(), new Object[0]);
                            BleUpgradePresenter.this.g.g = bleMeshFirmwareUpdateInfo.version;
                            BleMeshFirmwareUpdateInfo unused = BleUpgradePresenter.this.i = bleMeshFirmwareUpdateInfo;
                            observableEmitter.onNext(true);
                            observableEmitter.onComplete();
                            return;
                        }
                        observableEmitter.onError(new OTAException(1));
                    }

                    public void onFailure(int i, String str) {
                        LogUtil.e(BleUpgradePresenter.c, String.format("requestUpdateInfo error, error code: %d ,errorMsg: %s", new Object[]{Integer.valueOf(i), str}));
                        ObservableEmitter observableEmitter = observableEmitter;
                        observableEmitter.onError(new OTAException(OTAErrorCode.n, i + ":" + str));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public Observable<Boolean> d(final Device device) {
        return Observable.create(new ObservableOnSubscribe<Boolean>() {
            /* access modifiers changed from: private */
            public AtomicBoolean c = new AtomicBoolean(false);

            public void subscribe(final ObservableEmitter<Boolean> observableEmitter) throws Exception {
                XmBluetoothManager.getInstance().startScan(5000, 1, new XmBluetoothManager.BluetoothSearchResponse() {
                    public void onSearchStarted() {
                        LogUtil.a(BleUpgradePresenter.c, "onSearchStarted: start search");
                    }

                    public void onDeviceFounded(XmBluetoothDevice xmBluetoothDevice) {
                        if (AnonymousClass22.this.c.get()) {
                            LogUtil.c(BleUpgradePresenter.c, "onDeviceFounded: device already found,just ignore");
                        } else if (xmBluetoothDevice != null && xmBluetoothDevice.device != null && !TextUtils.isEmpty(device.mac) && device.mac.equalsIgnoreCase(xmBluetoothDevice.device.getAddress())) {
                            AnonymousClass22.this.c.set(true);
                            if (BleUpgradePresenter.this.t > 0) {
                                long currentTimeMillis = System.currentTimeMillis() - BleUpgradePresenter.this.t;
                                IUpgradeHost b2 = BleUpgradePresenter.this.l;
                                b2.showLog("切换固件激活耗时：" + (currentTimeMillis / 1000) + "秒");
                                BleUpgradePresenter.this.v.put(6, Long.valueOf(currentTimeMillis));
                            }
                            LogUtil.c(BleUpgradePresenter.c, "onDeviceFounded: device found, upgrade success");
                            XmBluetoothManager.getInstance().stopScan();
                            BleUpgradePresenter.this.a(device, false).subscribe(new Observer<Boolean>() {
                                /* renamed from: a */
                                public void onNext(Boolean bool) {
                                }

                                public void onSubscribe(Disposable disposable) {
                                }

                                public void onError(Throwable th) {
                                    BluetoothLog.b("switch firmware and connect fail,ignore");
                                    observableEmitter.onNext(true);
                                    observableEmitter.onComplete();
                                }

                                public void onComplete() {
                                    BluetoothLog.b("switch firmware and connect success");
                                    observableEmitter.onNext(true);
                                    observableEmitter.onComplete();
                                }
                            });
                        }
                    }

                    public void onSearchStopped() {
                        if (!AnonymousClass22.this.c.get()) {
                            LogUtil.c(BleUpgradePresenter.c, "onSearchStopped: haven't found target device yet");
                            observableEmitter.onError(new DeviceNoFoundException("onSearchStopped,haven't found target device yet"));
                        }
                    }

                    public void onSearchCanceled() {
                        if (!AnonymousClass22.this.c.get()) {
                            LogUtil.c(BleUpgradePresenter.c, "onSearchCanceled: haven't found target device yet");
                            observableEmitter.onError(new DeviceNoFoundException("onSearchCanceled,haven't found target device yet"));
                        }
                    }
                });
            }
        }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
            /* access modifiers changed from: private */
            public AtomicInteger b = new AtomicInteger(4);

            /* renamed from: a */
            public ObservableSource<?> apply(Observable<Throwable> observable) throws Exception {
                return observable.takeWhile(new Predicate<Throwable>() {
                    /* renamed from: a */
                    public boolean test(Throwable th) throws Exception {
                        if ((th instanceof DeviceNoFoundException) && AnonymousClass21.this.b.getAndDecrement() > 0) {
                            return true;
                        }
                        throw new Exception(th.getCause());
                    }
                }).flatMap(new Function<Throwable, ObservableSource<?>>() {
                    /* renamed from: a */
                    public ObservableSource<?> apply(Throwable th) throws Exception {
                        LogUtil.c(BleUpgradePresenter.c, "retry startScan after 3 seconds");
                        return Observable.timer(3, TimeUnit.SECONDS);
                    }
                });
            }
        });
    }

    private static class DeviceNoFoundException extends Exception {
        DeviceNoFoundException(String str) {
            super(str);
        }
    }

    /* access modifiers changed from: private */
    public synchronized void a(int i2) {
        this.j = i2;
    }

    private synchronized int k() {
        return this.j;
    }

    public void h() {
        this.k = true;
        switch (k()) {
            case 1:
                if (this.i != null) {
                    XmPluginHostApi.instance().cancelDownloadBleFirmware(TextUtils.isEmpty(this.n) ? this.i.safeUrl : this.n);
                    LogUtil.e(c, "onDestroy cancelDownloadBleFirmware");
                    break;
                }
                break;
            case 2:
                LogUtil.e(c, "onDestroy cancelBleMeshUpgrade");
                XmBluetoothManager.getInstance().cancelBleMeshUpgrade(this.h.mac);
                break;
        }
        LogUtil.a(c, ActivityInfo.TYPE_STR_ONDESTROY);
        XmBluetoothManager.getInstance().disconnect(this.h.mac);
    }
}
