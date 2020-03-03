package com.xiaomi.smarthome.scene.geofence.manager;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import com.libra.Color;
import com.qti.location.sdk.IZatGeofenceService;
import com.qti.location.sdk.IZatIllegalArgumentException;
import com.qti.location.sdk.IZatManager;
import com.taobao.weex.ui.module.WXModalUIModule;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.youpin.network.annotation.DownloadStatus;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class SmartFence extends AppCompatActivity {
    /* access modifiers changed from: private */
    public static String b = "SmartFence";

    /* renamed from: a  reason: collision with root package name */
    private Context f21549a;
    /* access modifiers changed from: private */
    public IZatGeofenceService c;
    private final int d;
    String fileName;
    EditText mEditConfidence;
    EditText mEditDwellTime;
    EditText mEditDwellType;
    EditText mEditLatitude;
    EditText mEditLongitude;
    EditText mEditName;
    EditText mEditRadius;
    EditText mEditResponsiveness;
    EditText mEditTransition;
    Map<IZatGeofenceService.IZatGeofenceHandle, IZatGeofenceService.IzatGeofence> mGeofenceHandleDataMap = new LinkedHashMap();
    ArrayList<String> mGeofenceListNames = new ArrayList<>();
    ArrayAdapter<String> mGeofenceListadapter;
    int mGeofenceSelectedIdx = -1;
    TextView mLogPrint;
    String mPackName;
    AlertDialog mPermissionDialog;
    List<String> mPermissionList;
    BroadcastReceiver mReceiver;
    String[] permissions;

    public SmartFence() {
        this.permissions = Build.VERSION.SDK_INT >= 29 ? new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION", "android.permission.ACCESS_BACKGROUND_LOCATION"} : new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.ACCESS_FINE_LOCATION"};
        this.mPermissionList = new ArrayList();
        this.d = 100;
        this.mPackName = "hy.xiaomi.smartfence";
        this.fileName = "SmartFenceLog.txt";
        this.mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String str;
                String stringExtra = intent.getStringExtra("context-data");
                if (stringExtra != null) {
                    String access$000 = SmartFence.b;
                    Log.v(access$000, "FenceName:" + stringExtra);
                }
                Location location = (Location) intent.getParcelableExtra("transition-location");
                if (location != null) {
                    int intExtra = intent.getIntExtra("transition-event", 4);
                    if (intExtra == 8) {
                        str = "Inside";
                    } else if (intExtra != 16) {
                        switch (intExtra) {
                            case 1:
                                str = "Entered";
                                break;
                            case 2:
                                str = "Exited";
                                break;
                            default:
                                str = "Uncertain";
                                break;
                        }
                    } else {
                        str = "Outside";
                    }
                    String access$0002 = SmartFence.b;
                    Log.v(access$0002, "transition:" + str + "\nlocation:" + location.toString() + "\n\n");
                    SmartFence smartFence = SmartFence.this;
                    smartFence.logPrint("Inner Receiver enter:FenceName:" + stringExtra + "\ntransition:" + str + "\nlocation:" + location.toString() + "\n\n");
                }
            }
        };
    }

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        this.f21549a = getApplicationContext();
        a();
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_smartfence);
        c();
    }

    private void a() {
        this.mPermissionList.clear();
        for (int i = 0; i < this.permissions.length; i++) {
            if (ActivityCompat.checkSelfPermission(this.f21549a, this.permissions[i]) != 0) {
                this.mPermissionList.add(this.permissions[i]);
            }
        }
        if (this.mPermissionList.size() > 0) {
            ActivityCompat.requestPermissions(this, this.permissions, 100);
        }
    }

    public void onRequestPermissionsResult(int i, @NonNull String[] strArr, @NonNull int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (100 == i) {
            boolean z = false;
            for (int i2 : iArr) {
                if (i2 == -1) {
                    z = true;
                }
            }
            if (z) {
                b();
            }
        }
    }

    private void b() {
        if (this.mPermissionDialog == null) {
            this.mPermissionDialog = new AlertDialog.Builder(this).setMessage("已禁用权限，请手动授予").setPositiveButton("设置", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartFence.this.mPermissionDialog.cancel();
                    SmartFence.this.startActivity(new Intent("android.settings.APPLICATION_DETAILS_SETTINGS", Uri.parse("package:" + SmartFence.this.mPackName)));
                }
            }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    SmartFence.this.mPermissionDialog.cancel();
                }
            }).create();
        }
        this.mPermissionDialog.show();
    }

    private void c() {
        IZatManager a2 = IZatManager.a(getApplicationContext());
        this.c = a2.e();
        BreachCallback breachCallback = new BreachCallback(this.f21549a);
        String b2 = a2.b();
        String str = b;
        Log.v(str, "SDK and Service Version:" + b2);
        this.c.a((IZatGeofenceService.IZatGeofenceCallback) breachCallback);
        registerReceiver(new MIUIGeoFenceBroadcastReceiver(new AsyncCallback() {
            public void onFailure(Error error) {
            }

            public void onSuccess(Object obj) {
                String str;
                Intent intent = (Intent) obj;
                String stringExtra = intent.getStringExtra("context-data");
                if (stringExtra != null) {
                    String access$000 = SmartFence.b;
                    Log.v(access$000, "FenceName:" + stringExtra);
                }
                Location location = (Location) intent.getParcelableExtra("transition-location");
                if (location != null) {
                    int intExtra = intent.getIntExtra("transition-event", 4);
                    if (intExtra == 8) {
                        str = "Inside";
                    } else if (intExtra != 16) {
                        switch (intExtra) {
                            case 1:
                                str = "Entered";
                                break;
                            case 2:
                                str = "Exited";
                                break;
                            default:
                                str = "Uncertain";
                                break;
                        }
                    } else {
                        str = "Outside";
                    }
                    String access$0002 = SmartFence.b;
                    Log.v(access$0002, "transition:" + str + "\nlocation:" + location.toString() + "\n\n");
                    SmartFence smartFence = SmartFence.this;
                    smartFence.logPrint("Static Receiver enter:FenceName:" + stringExtra + "\ntransition:" + str + "\nlocation:" + location.toString() + "\n\n");
                }
            }
        }), new IntentFilter(MIUIGeoFenceBroadcastReceiver.GEOFENCE_ACTION));
        this.c.a(PendingIntent.getBroadcast(SHApplication.getAppContext(), 0, new Intent(MIUIGeoFenceBroadcastReceiver.GEOFENCE_ACTION), 134217728));
        ListView listView = (ListView) findViewById(R.id.geofenceList);
        this.mLogPrint = (TextView) findViewById(R.id.LogList);
        this.mLogPrint.setMovementMethod(new ScrollingMovementMethod());
        this.mGeofenceListadapter = new ArrayAdapter<>(this, 17367043, this.mGeofenceListNames);
        listView.setAdapter(this.mGeofenceListadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                SmartFence.this.mGeofenceSelectedIdx = (int) j;
            }
        });
        ((Button) findViewById(R.id.addBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click ADD Button!");
                SmartFence.this.d();
            }
        });
        ((Button) findViewById(R.id.editBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click EDIT Button!");
                if (SmartFence.this.mGeofenceSelectedIdx != -1) {
                    SmartFence.this.e();
                }
            }
        });
        ((Button) findViewById(R.id.delBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click DELETE Button!");
                if (SmartFence.this.mGeofenceSelectedIdx != -1) {
                    SmartFence.this.f();
                }
            }
        });
        ((Button) findViewById(R.id.pauseBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click PAUSE Button!");
                if (SmartFence.this.mGeofenceSelectedIdx != -1) {
                    SmartFence.this.g();
                }
            }
        });
        ((Button) findViewById(R.id.resumeBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click RESUME Button!");
                if (SmartFence.this.mGeofenceSelectedIdx != -1) {
                    SmartFence.this.h();
                }
            }
        });
        ((Button) findViewById(R.id.recoverAllBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click RECOVER ALL Button!");
                SmartFence.this.i();
            }
        });
        ((Button) findViewById(R.id.loadLogBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click LoadLog Button!");
                SmartFence.this.mLogPrint.clearComposingText();
                SmartFence.this.mLogPrint.setText(SmartFence.this.j());
            }
        });
        ((Button) findViewById(R.id.clearLogBtn)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Log.v(SmartFence.b, "Click ClearLog Button!");
                SmartFence.this.k();
                SmartFence.this.mLogPrint.setText("");
            }
        });
    }

    /* access modifiers changed from: private */
    public void d() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.prompts, (ViewGroup) null);
        a(inflate);
        this.mEditTransition.setText("3");
        this.mEditDwellType.setText("3");
        this.mEditDwellTime.setText("3");
        this.mEditConfidence.setText("3");
        this.mEditResponsiveness.setText("30000");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                String obj = SmartFence.this.mEditName.getText().toString();
                double parseDouble = Double.parseDouble(SmartFence.this.mEditLatitude.getText().toString());
                double parseDouble2 = Double.parseDouble(SmartFence.this.mEditLongitude.getText().toString());
                double parseDouble3 = Double.parseDouble(SmartFence.this.mEditRadius.getText().toString());
                int parseInt = Integer.parseInt(SmartFence.this.mEditTransition.getText().toString());
                int parseInt2 = Integer.parseInt(SmartFence.this.mEditDwellType.getText().toString());
                int parseInt3 = Integer.parseInt(SmartFence.this.mEditDwellTime.getText().toString());
                int parseInt4 = Integer.parseInt(SmartFence.this.mEditConfidence.getText().toString());
                int parseInt5 = Integer.parseInt(SmartFence.this.mEditResponsiveness.getText().toString());
                IZatGeofenceService.IzatDwellNotify izatDwellNotify = new IZatGeofenceService.IzatDwellNotify(parseInt3, parseInt2);
                IZatGeofenceService.IzatGeofence izatGeofence = new IZatGeofenceService.IzatGeofence(parseDouble, parseDouble2, parseDouble3);
                izatGeofence.a(SmartFence.this.a(parseInt));
                izatGeofence.a(SmartFence.this.b(parseInt4));
                izatGeofence.a(parseInt5);
                izatGeofence.a(izatDwellNotify);
                if (obj.isEmpty()) {
                    obj = SmartFence.this.mEditLatitude.getText().toString() + "," + SmartFence.this.mEditLongitude.getText().toString();
                }
                IZatGeofenceService.IZatGeofenceHandle a2 = SmartFence.this.c.a(obj, izatGeofence);
                if (a2 != null) {
                    SmartFence.this.mGeofenceHandleDataMap.put(a2, izatGeofence);
                    Object a3 = a2.a();
                    if (!(a3 instanceof String)) {
                        a3 = "Geofence name";
                    }
                    SmartFence.this.mGeofenceListNames.add(a3.toString());
                    SmartFence.this.mGeofenceListadapter.notifyDataSetChanged();
                }
            }
        });
        builder.setNegativeButton(WXModalUIModule.CANCEL, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void e() {
        IZatGeofenceService.IzatGeofence izatGeofence;
        View inflate = LayoutInflater.from(this).inflate(R.layout.prompts, (ViewGroup) null);
        a(inflate);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        builder.setCancelable(false);
        final IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle = (IZatGeofenceService.IZatGeofenceHandle) this.mGeofenceHandleDataMap.keySet().toArray()[this.mGeofenceSelectedIdx];
        if (!(iZatGeofenceHandle == null || (izatGeofence = this.mGeofenceHandleDataMap.get(iZatGeofenceHandle)) == null)) {
            Object a2 = iZatGeofenceHandle.a();
            if (a2 instanceof String) {
                this.mEditName.setText(a2.toString());
            }
            a(izatGeofence);
        }
        builder.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                double parseDouble = Double.parseDouble(SmartFence.this.mEditRadius.getText().toString());
                int parseInt = Integer.parseInt(SmartFence.this.mEditDwellType.getText().toString());
                int parseInt2 = Integer.parseInt(SmartFence.this.mEditDwellTime.getText().toString());
                int parseInt3 = Integer.parseInt(SmartFence.this.mEditConfidence.getText().toString());
                IZatGeofenceService.IzatDwellNotify izatDwellNotify = new IZatGeofenceService.IzatDwellNotify(parseInt2, parseInt);
                int parseInt4 = Integer.parseInt(SmartFence.this.mEditTransition.getText().toString());
                int parseInt5 = Integer.parseInt(SmartFence.this.mEditResponsiveness.getText().toString());
                IZatGeofenceService.IzatGeofence izatGeofence = new IZatGeofenceService.IzatGeofence((Double) null, (Double) null, Double.valueOf(parseDouble));
                izatGeofence.a(SmartFence.this.a(parseInt4));
                izatGeofence.a(SmartFence.this.b(parseInt3));
                izatGeofence.a(parseInt5);
                izatGeofence.a(izatDwellNotify);
                if (iZatGeofenceHandle != null) {
                    iZatGeofenceHandle.a(izatGeofence);
                }
            }
        });
        builder.setNegativeButton(WXModalUIModule.CANCEL, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        this.mEditName.setFocusable(false);
        this.mEditLatitude.setFocusable(false);
        this.mEditLongitude.setFocusable(false);
        this.mEditName.setTextColor(Color.c);
        this.mEditLatitude.setTextColor(Color.c);
        this.mEditLongitude.setTextColor(Color.c);
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void f() {
        IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle = (IZatGeofenceService.IZatGeofenceHandle) this.mGeofenceHandleDataMap.keySet().toArray()[this.mGeofenceSelectedIdx];
        if (iZatGeofenceHandle != null) {
            this.c.a(iZatGeofenceHandle);
            this.mGeofenceHandleDataMap.remove(iZatGeofenceHandle);
            this.mGeofenceListNames.remove(this.mGeofenceSelectedIdx);
            this.mGeofenceListadapter.notifyDataSetChanged();
            return;
        }
        Log.v(b, "gfHandle ==null.cannot Delete!!!");
    }

    /* access modifiers changed from: private */
    public void g() {
        IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle = (IZatGeofenceService.IZatGeofenceHandle) this.mGeofenceHandleDataMap.keySet().toArray()[this.mGeofenceSelectedIdx];
        if (iZatGeofenceHandle != null) {
            iZatGeofenceHandle.b();
            String str = b;
            Log.v(str, "After pause, pause status is: " + iZatGeofenceHandle.d());
            return;
        }
        Log.v(b, "gfHandle ==null.cannot Pause!!!");
    }

    /* access modifiers changed from: private */
    public void h() {
        IZatGeofenceService.IzatGeofence izatGeofence;
        View inflate = LayoutInflater.from(this).inflate(R.layout.prompts, (ViewGroup) null);
        a(inflate);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        builder.setCancelable(false);
        final IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle = (IZatGeofenceService.IZatGeofenceHandle) this.mGeofenceHandleDataMap.keySet().toArray()[this.mGeofenceSelectedIdx];
        if (!(iZatGeofenceHandle == null || (izatGeofence = this.mGeofenceHandleDataMap.get(iZatGeofenceHandle)) == null)) {
            Object a2 = iZatGeofenceHandle.a();
            if (a2 instanceof String) {
                this.mEditName.setText(a2.toString());
            } else {
                this.mEditName.setText("");
            }
            a(izatGeofence);
        }
        builder.setPositiveButton("Resume", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                if (iZatGeofenceHandle != null) {
                    iZatGeofenceHandle.c();
                    String access$000 = SmartFence.b;
                    Log.v(access$000, "After resume, pause status is: " + iZatGeofenceHandle.d());
                }
            }
        });
        builder.setNegativeButton(WXModalUIModule.CANCEL, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        this.mEditName.setFocusable(false);
        this.mEditLatitude.setFocusable(false);
        this.mEditLongitude.setFocusable(false);
        this.mEditRadius.setFocusable(false);
        this.mEditDwellType.setFocusable(false);
        this.mEditDwellTime.setFocusable(false);
        this.mEditConfidence.setFocusable(false);
        this.mEditTransition.setFocusable(false);
        this.mEditResponsiveness.setFocusable(false);
        this.mEditName.setTextColor(Color.c);
        this.mEditLatitude.setTextColor(Color.c);
        this.mEditLongitude.setTextColor(Color.c);
        this.mEditRadius.setTextColor(Color.c);
        this.mEditDwellType.setTextColor(Color.c);
        this.mEditDwellTime.setTextColor(Color.c);
        this.mEditConfidence.setTextColor(Color.c);
        this.mEditTransition.setTextColor(Color.c);
        this.mEditResponsiveness.setTextColor(Color.c);
        builder.create().show();
    }

    /* access modifiers changed from: private */
    public void i() {
        this.mGeofenceHandleDataMap.clear();
        this.mGeofenceListNames.clear();
        this.mGeofenceHandleDataMap.putAll(this.c.a());
        for (Map.Entry next : this.mGeofenceHandleDataMap.entrySet()) {
            IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle = (IZatGeofenceService.IZatGeofenceHandle) next.getKey();
            Object a2 = iZatGeofenceHandle.a();
            if (a2 instanceof String) {
                this.mGeofenceListNames.add(a2.toString());
                try {
                    iZatGeofenceHandle.a((IZatGeofenceService.IzatGeofence) next.getValue());
                } catch (IZatIllegalArgumentException unused) {
                    String str = b;
                    Log.e(str, "The Geofence" + a2.toString() + " Parameter is Null!!!");
                }
            }
        }
        this.mGeofenceListadapter.notifyDataSetChanged();
    }

    private void a(IZatGeofenceService.IzatGeofence izatGeofence) {
        this.mEditLatitude.setText(String.format(Locale.CHINA, "%.9f", new Object[]{Double.valueOf(izatGeofence.c())}));
        this.mEditLongitude.setText(String.format(Locale.CHINA, "%.9f", new Object[]{Double.valueOf(izatGeofence.d())}));
        this.mEditRadius.setText(String.format(Locale.CHINA, "%.9f", new Object[]{Double.valueOf(izatGeofence.e())}));
        this.mEditTransition.setText(String.format(Locale.CHINA, "%d", new Object[]{Integer.valueOf(izatGeofence.f().getValue())}));
        IZatGeofenceService.IzatDwellNotify i = izatGeofence.i();
        this.mEditDwellType.setText(String.format(Locale.CHINA, "%d", new Object[]{Integer.valueOf(i.b())}));
        this.mEditDwellTime.setText(String.format(Locale.CHINA, "%d", new Object[]{Integer.valueOf(i.a())}));
        this.mEditConfidence.setText(String.format(Locale.CHINA, "%d", new Object[]{Integer.valueOf(izatGeofence.h().getValue())}));
        this.mEditResponsiveness.setText(String.format(Locale.CHINA, "%d", new Object[]{Integer.valueOf(izatGeofence.g())}));
    }

    private void a(View view) {
        this.mEditName = (EditText) view.findViewById(R.id.editAlertName);
        this.mEditLatitude = (EditText) view.findViewById(R.id.editTextLatitude);
        this.mEditLongitude = (EditText) view.findViewById(R.id.editTextLongitude);
        this.mEditRadius = (EditText) view.findViewById(R.id.editTextRadius);
        this.mEditTransition = (EditText) view.findViewById(R.id.editTextTransition);
        this.mEditDwellType = (EditText) view.findViewById(R.id.editTextDwellType);
        this.mEditDwellTime = (EditText) view.findViewById(R.id.editDwellTime);
        this.mEditConfidence = (EditText) view.findViewById(R.id.editConfidence);
        this.mEditResponsiveness = (EditText) view.findViewById(R.id.editResponsiveness);
    }

    /* access modifiers changed from: private */
    public IZatGeofenceService.IzatGeofenceTransitionTypes a(int i) {
        switch (i) {
            case 1:
                return IZatGeofenceService.IzatGeofenceTransitionTypes.ENTERED_ONLY;
            case 2:
                return IZatGeofenceService.IzatGeofenceTransitionTypes.EXITED_ONLY;
            case 3:
                return IZatGeofenceService.IzatGeofenceTransitionTypes.ENTERED_AND_EXITED;
            default:
                return IZatGeofenceService.IzatGeofenceTransitionTypes.UNKNOWN;
        }
    }

    /* access modifiers changed from: private */
    public IZatGeofenceService.IzatGeofenceConfidence b(int i) {
        switch (i) {
            case 2:
                return IZatGeofenceService.IzatGeofenceConfidence.MEDIUM;
            case 3:
                return IZatGeofenceService.IzatGeofenceConfidence.HIGH;
            default:
                return IZatGeofenceService.IzatGeofenceConfidence.LOW;
        }
    }

    /* access modifiers changed from: protected */
    public void onNewIntent(Intent intent) {
        String str;
        super.onNewIntent(intent);
        if (intent != null) {
            String stringExtra = intent.getStringExtra("context-data");
            if (stringExtra != null) {
                String str2 = b;
                Log.v(str2, "FenceName:" + stringExtra);
            }
            Location location = (Location) intent.getParcelableExtra("transition-location");
            if (location != null) {
                int intExtra = intent.getIntExtra("transition-event", 4);
                if (intExtra == 8) {
                    str = "Inside";
                } else if (intExtra != 16) {
                    switch (intExtra) {
                        case 1:
                            str = "Entered";
                            break;
                        case 2:
                            str = "Exited";
                            break;
                        default:
                            str = "Uncertain";
                            break;
                    }
                } else {
                    str = "Outside";
                }
                String str3 = b;
                Log.v(str3, "transition:" + str + "\nlocation:" + location.toString() + "\n\n");
                logPrint("FenceName:" + stringExtra + "\ntransition:" + str + "\nlocation:" + location.toString() + "\n\n");
            }
        }
    }

    public void logPrint(String str) {
        String str2 = getCurrentTime() + str;
        this.mLogPrint.append("" + str2);
        a(str2);
    }

    public String getCurrentTime() {
        Calendar instance = Calendar.getInstance();
        int i = instance.get(1);
        int i2 = instance.get(5);
        int i3 = instance.get(11);
        int i4 = instance.get(12);
        int i5 = instance.get(13);
        Locale locale = Locale.CHINA;
        return String.format(locale, "%s", new Object[]{i + "-" + (instance.get(2) + 1) + "-" + i2 + " " + i3 + ":" + i4 + ":" + i5 + ":\n"});
    }

    public class BreachCallback implements IZatGeofenceService.IZatGeofenceCallback {
        private String b;
        private String c;
        private String d;

        public void a(IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle, int i, Location location) {
        }

        public BreachCallback(Context context) {
        }

        public void a(IZatGeofenceService.IZatGeofenceHandle iZatGeofenceHandle, int i, int i2) {
            switch (i) {
                case 1:
                    this.b = "ADD";
                    break;
                case 2:
                    this.b = "REMOVE";
                    break;
                case 3:
                    this.b = DownloadStatus.PAUSE;
                    break;
                case 4:
                    this.b = "RESUME";
                    break;
                case 5:
                    this.b = "UPDATE";
                    break;
                default:
                    this.b = "UNKNOWN";
                    break;
            }
            if (i2 == -149) {
                this.c = "Generic Error!";
            } else if (i2 == -100) {
                this.c = "Too Many Geofences!";
            } else if (i2 != 0) {
                switch (i2) {
                    case -103:
                        this.c = "Invalid Transition!";
                        break;
                    case -102:
                        this.c = "ID_Unknown!";
                        break;
                    default:
                        this.c = "Unknown Error!";
                        break;
                }
            } else {
                this.c = "SUCCESS";
            }
            String access$000 = SmartFence.b;
            Log.v(access$000, "onRequestFailed:got onRequestFailed in app, handle.obj is " + iZatGeofenceHandle.a().toString() + "; requestType is " + this.b + "; errorCode is " + this.c);
            SmartFence smartFence = SmartFence.this;
            smartFence.logPrint("onRequestFailed:got onRequestFailed in app, handle.obj is " + iZatGeofenceHandle.a().toString() + "; requestType is " + this.b + "; errorCode is " + this.c + "\n\n");
        }

        public void a(int i, Location location) {
            if (location != null) {
                String access$000 = SmartFence.b;
                Log.v(access$000, "The last location is " + location.getLatitude() + "/" + location.getLongitude());
            } else {
                Log.v(SmartFence.b, "The last location is Null");
            }
            switch (i) {
                case 1:
                    this.d = "Gnss is Unavailable!";
                    break;
                case 2:
                    this.d = "Gnss is Available!";
                    break;
                case 3:
                    this.d = "The Engine is out of service!";
                    break;
                case 4:
                    this.d = "The engine has an invalid time!";
                    break;
                default:
                    this.d = "Unknown status!";
                    break;
            }
            String access$0002 = SmartFence.b;
            Log.v(access$0002, "onEngineReportStatus:got geofence status in app, status is \n" + this.d);
            SmartFence smartFence = SmartFence.this;
            smartFence.logPrint("Got geofence status --- " + this.d + "\n\n");
        }
    }

    private void a(String str) {
        OutputStreamWriter outputStreamWriter;
        try {
            outputStreamWriter = new OutputStreamWriter(openFileOutput(this.fileName, 32768));
            outputStreamWriter.write(str);
            outputStreamWriter.close();
            return;
        } catch (IOException e) {
            String str2 = b;
            Log.e(str2, "File write failed: " + e.toString());
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }

    /* access modifiers changed from: private */
    public String j() {
        FileInputStream openFileInput;
        String str = "";
        try {
            openFileInput = openFileInput(this.fileName);
            if (openFileInput != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(openFileInput);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder sb = new StringBuilder();
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    }
                    sb.append(readLine + "\n");
                }
                bufferedReader.close();
                inputStreamReader.close();
                openFileInput.close();
                str = sb.toString();
            }
            if (openFileInput != null) {
                openFileInput.close();
            }
        } catch (FileNotFoundException e) {
            String str2 = b;
            Log.e(str2, "File not Found: " + e.toString());
        } catch (IOException e2) {
            String str3 = b;
            Log.e(str3, "Cannot Read File: " + e2.toString());
        } catch (Throwable unused) {
        }
        return str;
        throw th;
    }

    /* access modifiers changed from: private */
    public void k() {
        OutputStreamWriter outputStreamWriter;
        try {
            outputStreamWriter = new OutputStreamWriter(openFileOutput(this.fileName, 0));
            outputStreamWriter.flush();
            outputStreamWriter.close();
            return;
        } catch (IOException e) {
            String str = b;
            Log.e(str, "Cannot Find File: " + e.toString());
            return;
        } catch (Throwable unused) {
        }
        throw th;
    }
}
