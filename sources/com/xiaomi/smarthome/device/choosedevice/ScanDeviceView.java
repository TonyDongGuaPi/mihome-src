package com.xiaomi.smarthome.device.choosedevice;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.react.uimanager.ViewProps;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.ChooseConnectDeviceAdapter;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.device.bluetooth.BLEDeviceManager;
import com.xiaomi.smarthome.device.bluetooth.BleDeviceGroup;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ScanDeviceView extends RelativeLayout {

    /* renamed from: a  reason: collision with root package name */
    private static final int f15349a = 100;
    private static final int b = 200;
    /* access modifiers changed from: private */
    public List<View> c;
    /* access modifiers changed from: private */
    public List<Object> d;
    /* access modifiers changed from: private */
    public List<ChooseConnectDeviceAdapter.ScanDeviceType> e;
    /* access modifiers changed from: private */
    public Queue<Object> f;
    /* access modifiers changed from: private */
    public ConcurrentMap<Object, ChooseConnectDeviceAdapter.ScanDeviceType> g;
    private Handler h;
    private Map<String, PushBindEntity> i;
    /* access modifiers changed from: private */
    public ScanViewListener j;
    private Map<String, PushBindEntity> k;
    private List<ScanResult> l;
    private List<ScanResult> m;
    private List<BleDevice> n;
    private List<MiTVDevice> o;
    private Map<String, Integer> p;
    private Map<String, ScanResult> q;
    private Map<String, ScanResult> r;
    private Map<String, BleDevice> s;
    private List<MiTVDevice> t;

    public interface ScanViewListener {
        void a(int i);

        void a(ChooseConnectDeviceAdapter.ScanDeviceType scanDeviceType, String str, ScanResult scanResult, BleDevice bleDevice, MiTVDevice miTVDevice, PushBindEntity pushBindEntity);
    }

    private boolean a(char c2) {
        return (c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || (c2 >= '0' && c2 <= '9');
    }

    public ScanDeviceView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    public ScanDeviceView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScanDeviceView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i2) {
        super(context, attributeSet, i2);
        this.c = new ArrayList(7);
        this.d = new ArrayList(7);
        this.e = new ArrayList(7);
        this.f = new LinkedList();
        this.g = new ConcurrentHashMap();
        this.h = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message message) {
                int i = message.what;
                if (i != 100) {
                    if (i == 200) {
                        ScanDeviceView.this.updateView();
                    }
                } else if (!ScanDeviceView.this.f.isEmpty()) {
                    Object poll = ScanDeviceView.this.f.poll();
                    if (poll == null || ScanDeviceView.this.g.get(poll) == null) {
                        sendEmptyMessage(100);
                        return;
                    }
                    int access$400 = ScanDeviceView.this.getAddPosition();
                    if (access$400 < ScanDeviceView.this.c.size()) {
                        ScanDeviceView.this.d.set(access$400, poll);
                        ScanDeviceView.this.e.set(access$400, ScanDeviceView.this.g.get(poll));
                        ScanDeviceView.this.a(poll, (ChooseConnectDeviceAdapter.ScanDeviceType) ScanDeviceView.this.g.get(poll), access$400);
                        ScanDeviceView.this.a((View) ScanDeviceView.this.c.get(access$400));
                        ScanDeviceView.this.g.remove(poll);
                    }
                    sendEmptyMessageDelayed(100, 500);
                } else if (ScanDeviceView.this.j != null) {
                    ScanDeviceView.this.a();
                }
            }
        };
        this.k = new HashMap();
        this.p = new HashMap();
        this.q = new HashMap();
        this.r = new HashMap();
        this.s = new HashMap();
        this.t = new ArrayList();
    }

    public void init() {
        LayoutInflater from = LayoutInflater.from(getContext());
        View inflate = from.inflate(R.layout.choose_connect_device_item, this, false);
        View inflate2 = from.inflate(R.layout.choose_connect_device_item, this, false);
        View inflate3 = from.inflate(R.layout.choose_connect_device_item, this, false);
        View inflate4 = from.inflate(R.layout.choose_connect_device_item, this, false);
        View inflate5 = from.inflate(R.layout.choose_connect_device_item, this, false);
        View inflate6 = from.inflate(R.layout.choose_connect_device_item, this, false);
        View inflate7 = from.inflate(R.layout.choose_connect_device_item, this, false);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.setMargins(0, DisplayUtils.a(107.0f), DisplayUtils.a(33.5f), 0);
        layoutParams.addRule(11);
        addView(inflate, layoutParams);
        inflate.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.setMargins(DisplayUtils.a(79.5f), DisplayUtils.a(165.0f), 0, 0);
        addView(inflate2, layoutParams2);
        inflate2.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(DisplayUtils.a(116.5f), 0, 0, 0);
        addView(inflate3, layoutParams3);
        inflate3.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams4.setMargins(DisplayUtils.a(139.5f), 0, 0, 0);
        layoutParams4.addRule(12);
        addView(inflate4, layoutParams4);
        inflate4.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams5 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams5.setMargins(DisplayUtils.a(8.5f), DisplayUtils.a(49.3f), 0, 0);
        addView(inflate5, layoutParams5);
        inflate5.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams6 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams6.setMargins(0, DisplayUtils.a(273.0f), DisplayUtils.a(8.5f), 0);
        layoutParams6.addRule(11);
        addView(inflate6, layoutParams6);
        inflate6.setVisibility(8);
        RelativeLayout.LayoutParams layoutParams7 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams7.setMargins(DisplayUtils.a(18.5f), DisplayUtils.a(285.3f), 0, 0);
        addView(inflate7, layoutParams7);
        inflate7.setVisibility(8);
        this.c.add(inflate);
        this.c.add(inflate2);
        this.c.add(inflate3);
        this.c.add(inflate4);
        this.c.add(inflate5);
        this.c.add(inflate6);
        this.c.add(inflate7);
        for (final int i2 = 0; i2 < this.c.size(); i2++) {
            this.c.get(i2).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    ScanDeviceView.this.a(i2);
                }
            });
        }
        this.d = Arrays.asList(new Object[]{null, null, null, null, null, null, null});
        this.e = Arrays.asList(new ChooseConnectDeviceAdapter.ScanDeviceType[]{null, null, null, null, null, null, null});
    }

    public void setListener(ScanViewListener scanViewListener) {
        this.j = scanViewListener;
    }

    /* access modifiers changed from: private */
    public void a(int i2) {
        BleDeviceGroup a2;
        Object obj = this.d.get(i2);
        ChooseConnectDeviceAdapter.ScanDeviceType scanDeviceType = this.e.get(i2);
        if (obj != null && scanDeviceType != null && this.j != null) {
            switch (scanDeviceType) {
                case AP:
                case AP_DIRECT:
                    if (obj instanceof ScanResult) {
                        ScanResult scanResult = (ScanResult) obj;
                        this.j.a(ChooseConnectDeviceAdapter.ScanDeviceType.AP, DeviceFactory.a(scanResult), scanResult, (BleDevice) null, (MiTVDevice) null, (PushBindEntity) null);
                        return;
                    }
                    return;
                case BLE:
                    if (obj instanceof BleDevice) {
                        BleDevice bleDevice = (BleDevice) obj;
                        if (DeviceFactory.a(bleDevice)) {
                            a2 = BLEDeviceManager.b(bleDevice.mac);
                        } else {
                            a2 = BLEDeviceManager.a(bleDevice.model);
                        }
                        BleDeviceGroup bleDeviceGroup = a2;
                        if (bleDeviceGroup != null) {
                            this.j.a(ChooseConnectDeviceAdapter.ScanDeviceType.BLE, bleDeviceGroup.model, (ScanResult) null, bleDeviceGroup, (MiTVDevice) null, (PushBindEntity) null);
                            return;
                        }
                        return;
                    }
                    return;
                case MI_TV:
                    if (obj instanceof MiTVDevice) {
                        MiTVDevice miTVDevice = (MiTVDevice) obj;
                        this.j.a(ChooseConnectDeviceAdapter.ScanDeviceType.MI_TV, miTVDevice.a(), (ScanResult) null, (BleDevice) null, miTVDevice, (PushBindEntity) null);
                        return;
                    }
                    return;
                case AIOT:
                    if (obj instanceof PushBindEntity) {
                        PushBindEntity pushBindEntity = (PushBindEntity) obj;
                        this.j.a(scanDeviceType, pushBindEntity.f22312a.o(), (ScanResult) null, (BleDevice) null, (MiTVDevice) null, pushBindEntity);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }

    public void updateData(List<ScanResult> list, List<ScanResult> list2, List<BleDevice> list3, List<MiTVDevice> list4, Map<String, PushBindEntity> map) {
        this.l = list;
        this.m = list2;
        this.n = list3;
        this.o = list4;
        this.i = map;
        this.h.sendEmptyMessage(200);
    }

    public void updateView() {
        Object obj;
        boolean z;
        this.h.removeMessages(200);
        this.f.clear();
        this.g.clear();
        this.q.clear();
        for (ScanResult next : this.l) {
            this.q.put(next.BSSID, next);
        }
        this.r.clear();
        for (ScanResult next2 : this.m) {
            this.r.put(next2.BSSID, next2);
        }
        this.s.clear();
        for (BleDevice next3 : this.n) {
            this.s.put(next3.model, next3);
        }
        this.t.clear();
        this.t.addAll(this.o);
        this.k.clear();
        this.k.putAll(this.i);
        for (int i2 = 0; i2 < this.d.size(); i2++) {
            if (!(this.c.get(i2).getVisibility() == 8 || (obj = this.d.get(i2)) == null || ((((z = obj instanceof ScanResult)) && this.q.get(((ScanResult) obj).BSSID) != null) || ((z && this.r.get(((ScanResult) obj).BSSID) != null) || (((obj instanceof BleDevice) && this.s.get(((BleDevice) obj).model) != null) || (((obj instanceof PushBindEntity) && this.k.get(((PushBindEntity) obj).d) != null) || ((obj instanceof MiTVDevice) && this.t.contains(obj)))))))) {
                this.d.set(i2, (Object) null);
                this.e.set(i2, (Object) null);
                a(this.c.get(i2), i2);
            }
        }
        this.p.clear();
        int i3 = 0;
        for (Object next4 : this.d) {
            if (next4 != null) {
                if (next4 instanceof ScanResult) {
                    this.p.put(((ScanResult) next4).BSSID, Integer.valueOf(i3));
                } else if (next4 instanceof BleDevice) {
                    this.p.put(((BleDevice) next4).model, Integer.valueOf(i3));
                } else if (next4 instanceof PushBindEntity) {
                    this.p.put(((PushBindEntity) next4).d, Integer.valueOf(i3));
                }
                i3++;
            }
        }
        for (Map.Entry<String, PushBindEntity> value : this.k.entrySet()) {
            PushBindEntity pushBindEntity = (PushBindEntity) value.getValue();
            if (this.p.get(pushBindEntity.d) == null) {
                if (getAddPosition() == this.c.size()) {
                    break;
                } else if (this.g.get(pushBindEntity) == null) {
                    this.f.add(pushBindEntity);
                    this.g.put(pushBindEntity, ChooseConnectDeviceAdapter.ScanDeviceType.AIOT);
                }
            }
        }
        for (ScanResult next5 : this.q.values()) {
            if (this.p.get(next5.BSSID) == null) {
                if (getAddPosition() == this.c.size()) {
                    break;
                } else if (this.g.get(next5) == null) {
                    this.f.add(next5);
                    this.g.put(next5, ChooseConnectDeviceAdapter.ScanDeviceType.AP);
                }
            }
        }
        for (ScanResult next6 : this.r.values()) {
            if (this.p.get(next6.BSSID) == null) {
                if (getAddPosition() == this.c.size()) {
                    break;
                } else if (this.g.get(next6) == null) {
                    this.f.add(next6);
                    this.g.put(next6, ChooseConnectDeviceAdapter.ScanDeviceType.AP_DIRECT);
                }
            }
        }
        for (BleDevice next7 : this.s.values()) {
            if (this.p.get(next7.model) != null) {
                Integer num = this.p.get(next7.model);
                if (num == null) {
                    num = -1;
                }
                if (num.intValue() != -1) {
                    if (next7.r() > 1) {
                        ((TextView) this.c.get(num.intValue()).findViewById(R.id.device_num)).setVisibility(0);
                        ((TextView) this.c.get(num.intValue()).findViewById(R.id.device_num)).setText(String.valueOf(next7.r()));
                    } else {
                        ((TextView) this.c.get(num.intValue()).findViewById(R.id.device_num)).setVisibility(8);
                    }
                }
            } else if (getAddPosition() == this.c.size()) {
                break;
            } else if (this.g.get(next7) == null) {
                this.f.add(next7);
                this.g.put(next7, ChooseConnectDeviceAdapter.ScanDeviceType.BLE);
            }
        }
        for (MiTVDevice next8 : this.t) {
            if (!this.d.contains(next8)) {
                if (getAddPosition() == this.c.size()) {
                    break;
                } else if (this.g.get(next8) == null) {
                    this.f.add(next8);
                    this.g.put(next8, ChooseConnectDeviceAdapter.ScanDeviceType.MI_TV);
                }
            }
        }
        if (!this.h.hasMessages(100)) {
            this.h.sendEmptyMessageDelayed(100, 800);
        }
        this.h.sendEmptyMessageDelayed(200, 3000);
    }

    /* access modifiers changed from: private */
    public void a(Object obj, ChooseConnectDeviceAdapter.ScanDeviceType scanDeviceType, int i2) {
        switch (scanDeviceType) {
            case AP:
            case AP_DIRECT:
                ScanResult scanResult = (ScanResult) obj;
                DeviceFactory.b(DeviceFactory.a(scanResult), (SimpleDraweeView) this.c.get(i2).findViewById(R.id.device_image));
                ((TextView) this.c.get(i2).findViewById(R.id.device_name)).setText(a(DeviceFactory.i(scanResult)));
                ((TextView) this.c.get(i2).findViewById(R.id.device_num)).setVisibility(8);
                return;
            case BLE:
                BleDevice bleDevice = (BleDevice) obj;
                DeviceFactory.b(bleDevice.model, (SimpleDraweeView) this.c.get(i2).findViewById(R.id.device_image));
                ((TextView) this.c.get(i2).findViewById(R.id.device_name)).setText(a(bleDevice.q()));
                if (bleDevice.r() > 1) {
                    ((TextView) this.c.get(i2).findViewById(R.id.device_num)).setVisibility(0);
                    ((TextView) this.c.get(i2).findViewById(R.id.device_num)).setText(String.valueOf(bleDevice.r()));
                    return;
                }
                ((TextView) this.c.get(i2).findViewById(R.id.device_num)).setVisibility(8);
                return;
            case MI_TV:
                MiTVDevice miTVDevice = (MiTVDevice) obj;
                DeviceFactory.b(miTVDevice.model, (SimpleDraweeView) this.c.get(i2).findViewById(R.id.device_image));
                ((TextView) this.c.get(i2).findViewById(R.id.device_name)).setText(a(miTVDevice.name));
                ((TextView) this.c.get(i2).findViewById(R.id.device_num)).setVisibility(8);
                return;
            case AIOT:
                PushBindEntity pushBindEntity = (PushBindEntity) obj;
                DeviceFactory.b(pushBindEntity.f22312a.o(), (SimpleDraweeView) this.c.get(i2).findViewById(R.id.device_image));
                ((TextView) this.c.get(i2).findViewById(R.id.device_name)).setText(a(pushBindEntity.f22312a.p()));
                ((TextView) this.c.get(i2).findViewById(R.id.device_num)).setVisibility(8);
                return;
            default:
                return;
        }
    }

    private String a(String str) {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        if (!I.getLanguage().equalsIgnoreCase("zh") || str.length() <= 6 || getResources().getConfiguration().fontScale > 1.0f) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str.substring(0, 6));
        StringBuilder sb2 = new StringBuilder(str.substring(6, str.length()));
        if (a(sb.charAt(sb.length() - 1)) && a(sb2.charAt(0))) {
            while (sb2.length() > 0 && a(sb2.charAt(0))) {
                sb.append(sb2.charAt(0));
                sb2.deleteCharAt(0);
            }
        }
        return sb.toString().trim() + "\n" + sb2.toString().trim();
    }

    /* access modifiers changed from: private */
    public int getAddPosition() {
        Iterator<Object> it = this.d.iterator();
        int i2 = 0;
        while (it.hasNext() && it.next() != null) {
            i2++;
        }
        return i2;
    }

    private void a(final View view, final int i2) {
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{1.0f, 0.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{1.0f, 0.0f})});
        ofPropertyValuesHolder.setInterpolator(new AccelerateInterpolator());
        ofPropertyValuesHolder.setDuration(300).start();
        ofPropertyValuesHolder.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                ScanDeviceView.this.a();
                if (ScanDeviceView.this.d.get(i2) == null) {
                    view.setVisibility(8);
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final View view) {
        view.setVisibility(0);
        ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(view, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{0.65f, 1.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{0.65f, 1.0f})});
        ofPropertyValuesHolder.setInterpolator(new OvershootInterpolator());
        ofPropertyValuesHolder.setDuration(500).start();
        ofPropertyValuesHolder.addListener(new Animator.AnimatorListener() {
            public void onAnimationCancel(Animator animator) {
            }

            public void onAnimationRepeat(Animator animator) {
            }

            public void onAnimationStart(Animator animator) {
            }

            public void onAnimationEnd(Animator animator) {
                ScanDeviceView.this.a();
                final ImageView imageView = (ImageView) view.findViewById(R.id.edge_anim_img);
                if (imageView != null) {
                    imageView.setVisibility(0);
                    ObjectAnimator ofPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(imageView, new PropertyValuesHolder[]{PropertyValuesHolder.ofFloat("scaleX", new float[]{0.8f, 1.0f}), PropertyValuesHolder.ofFloat("scaleY", new float[]{0.8f, 1.0f})});
                    ofPropertyValuesHolder.setDuration(600);
                    ofPropertyValuesHolder.setInterpolator(new LinearInterpolator());
                    ofPropertyValuesHolder.start();
                    ofPropertyValuesHolder.addListener(new Animator.AnimatorListener() {
                        public void onAnimationCancel(Animator animator) {
                        }

                        public void onAnimationRepeat(Animator animator) {
                        }

                        public void onAnimationStart(Animator animator) {
                        }

                        public void onAnimationEnd(Animator animator) {
                            imageView.setVisibility(4);
                        }
                    });
                    final ImageView imageView2 = (ImageView) view.findViewById(R.id.edge_circle_img);
                    if (imageView2 != null) {
                        imageView2.setVisibility(0);
                        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(imageView2, ViewProps.ROTATION, new float[]{0.0f, 360.0f});
                        ofFloat.setDuration(500);
                        ofFloat.setInterpolator(new LinearInterpolator());
                        ofFloat.start();
                        ofFloat.addListener(new Animator.AnimatorListener() {
                            public void onAnimationCancel(Animator animator) {
                            }

                            public void onAnimationRepeat(Animator animator) {
                            }

                            public void onAnimationStart(Animator animator) {
                            }

                            public void onAnimationEnd(Animator animator) {
                                imageView2.setVisibility(8);
                            }
                        });
                    }
                }
            }
        });
    }

    /* access modifiers changed from: private */
    public void a() {
        int i2 = 0;
        for (View visibility : this.c) {
            if (visibility.getVisibility() == 0) {
                i2++;
            }
        }
        if (this.j != null) {
            this.j.a(i2);
        }
    }

    public void exit() {
        this.h.removeCallbacksAndMessages((Object) null);
        this.j = null;
    }
}
