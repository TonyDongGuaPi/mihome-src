package com.xiaomi.smarthome.miui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.NotificationManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;
import com.coloros.mcssdk.PushManager;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.drawee.drawable.ScalingUtils;
import com.facebook.drawee.generic.GenericDraweeHierarchyBuilder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.android.exoplayer2.C;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.device.DeviceUtils;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.library.common.util.CommonUtils;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.library.common.util.PreferenceUtils;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import com.xiaomi.smarthome.service.DeviceObserveService;
import com.xiaomi.smarthome.smartconfig.DevicePushBindManager;
import com.xiaomi.smarthome.smartconfig.PushBindConfigActivity;
import com.xiaomi.smarthome.smartconfig.PushBindEntity;
import com.xiaomi.smarthome.smartconfig.SmartConfigDataProvider;
import com.xiaomi.smarthome.smartconfig.stage.ConfigStage;
import com.xiaomi.smarthome.stat.STAT;
import java.util.ArrayList;

public class FindDeviceDialogActivity extends BaseActivity {
    /* access modifiers changed from: private */
    public static final int d = DisplayUtils.a(20.0f);
    /* access modifiers changed from: private */
    public static final int e = DisplayUtils.a(213.33f);
    public static boolean sResumed = false;
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public VideoView f20111a;
    /* access modifiers changed from: private */
    public boolean b = false;
    /* access modifiers changed from: private */
    public ScanResult c;
    /* access modifiers changed from: private */
    public boolean f = true;
    /* access modifiers changed from: private */
    public int g;
    private Runnable h = new Runnable() {
        public void run() {
            DeviceObserveService.getInstance().onFinishSmartConfig(FindDeviceDialogActivity.this.c == null ? null : FindDeviceDialogActivity.this.c.SSID, false);
            FindDeviceDialogActivity.this.finish();
        }
    };

    /* access modifiers changed from: protected */
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent != null) {
            int intExtra = intent.getIntExtra("strategy_id", -1);
            this.g = intExtra;
            if (intExtra > 0 || intent.getParcelableExtra("find_device") != null) {
                showDialog(intent);
                return;
            }
        }
        DeviceObserveService.getInstance().onStartCommand();
        finish();
    }

    /* access modifiers changed from: package-private */
    public void startIconAnim(final View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{1.0f});
        final ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "alpha", new float[]{0.0f, 1.0f});
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                layoutParams.height = (int) (((float) FindDeviceDialogActivity.d) + (((float) (FindDeviceDialogActivity.e - FindDeviceDialogActivity.d)) * floatValue));
                view.setLayoutParams(layoutParams);
            }
        });
        ofFloat.setDuration(300);
        ofFloat2.setDuration(300);
        ofFloat2.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (FindDeviceDialogActivity.this.b) {
                    FindDeviceDialogActivity.this.f20111a.setVisibility(0);
                    FindDeviceDialogActivity.this.f20111a.start();
                }
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                ofFloat2.start();
            }
        });
        ofFloat.start();
    }

    private void a(final ImageView imageView, final View view, Uri uri) {
        ImagePipelineFactory.getInstance().getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri).build(), (Object) null).subscribe(new BaseBitmapDataSubscriber() {
            public void onNewResultImpl(Bitmap bitmap) {
                if (bitmap != null && !bitmap.isRecycled()) {
                    final Bitmap createBitmap = Bitmap.createBitmap(bitmap);
                    view.post(new Runnable() {
                        public void run() {
                            imageView.setImageBitmap(createBitmap);
                            FindDeviceDialogActivity.this.startIconAnim(view);
                            Log.e("FindDeviceDialog", "start animation");
                        }
                    });
                }
            }

            public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
                super.onCancellation(dataSource);
                Log.e("FindDeviceDialog", "cancel download");
            }

            public void onFailureImpl(DataSource dataSource) {
                Log.e("FindDeviceDialog", "download failed");
            }
        }, CallerThreadExecutor.getInstance());
    }

    /* access modifiers changed from: package-private */
    public void showDialog(Intent intent) {
        this.c = (ScanResult) intent.getParcelableExtra("find_device");
        String stringExtra = intent.getStringExtra("device_name");
        final String stringExtra2 = intent.getStringExtra("model");
        final String stringExtra3 = intent.getStringExtra("image_url");
        final String stringExtra4 = intent.getStringExtra("video_url");
        final boolean booleanExtra = intent.getBooleanExtra(SmartConfigDataProvider.N, false);
        final ArrayList parcelableArrayListExtra = intent.getParcelableArrayListExtra(DevicePushBindManager.EXTRA_BINDWIFI);
        if (this.g > 0 || DeviceObserveService.getInstance().isValid(this.c)) {
            this.f = PreferenceUtils.a("find_device_tips", true);
            if (!booleanExtra) {
                this.f = false;
            }
            setContentView(R.layout.miui10_find_device_dialog);
            TextView textView = (TextView) findViewById(R.id.title);
            final SimpleDraweeView simpleDraweeView = (SimpleDraweeView) findViewById(R.id.icon_static);
            this.f20111a = (VideoView) findViewById(R.id.icon_video);
            final View findViewById = findViewById(R.id.icon_container);
            this.f20111a.setZOrderOnTop(true);
            NotificationManager notificationManager = (NotificationManager) getApplicationContext().getSystemService(PushManager.MESSAGE_TYPE_NOTI);
            if (!(notificationManager == null || this.c == null || this.c.SSID == null)) {
                Log.e("DeviceObserverService", "cancel notify - " + this.c.SSID.hashCode());
                notificationManager.cancel(this.c.SSID.hashCode());
            }
            Log.e("DeviceObserverService", "show device dialog - " + stringExtra);
            if (simpleDraweeView.getHierarchy() == null) {
                simpleDraweeView.setHierarchy(new GenericDraweeHierarchyBuilder(simpleDraweeView.getResources()).setFadeDuration(200).setActualImageScaleType(ScalingUtils.ScaleType.FIT_START).setPlaceholderImageScaleType(ScalingUtils.ScaleType.FIT_START).build());
            }
            if (this.g != 16) {
                if (booleanExtra) {
                    STAT.e.a(stringExtra2);
                } else {
                    STAT.e.c(stringExtra2);
                }
                a(stringExtra3, stringExtra4, simpleDraweeView, findViewById);
                textView.setText(String.format(getString(R.string.miui_find_device_title), new Object[]{stringExtra}));
            } else if (parcelableArrayListExtra == null || parcelableArrayListExtra.size() != 1) {
                textView.setText(getString(R.string.push_confirmbind_find_devices_title));
                a(stringExtra3, stringExtra4, simpleDraweeView, findViewById);
            } else {
                PushBindEntity pushBindEntity = (PushBindEntity) parcelableArrayListExtra.get(0);
                textView.setText(String.format(getString(R.string.push_confirmbind_find_device_title), new Object[]{pushBindEntity.f22312a.p()}));
                DeviceImageApi.a(pushBindEntity.f22312a.o(), new AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>() {
                    /* renamed from: a */
                    public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                        FindDeviceDialogActivity.this.a(deviceImageEntity.f21157a, deviceImageEntity.b, simpleDraweeView, findViewById);
                    }

                    public void onFailure(Error error) {
                        FindDeviceDialogActivity.this.a(stringExtra3, stringExtra4, simpleDraweeView, findViewById);
                    }
                });
            }
            findViewById(R.id.dialog_container).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FindDeviceDialogActivity.this.f20111a.getVisibility() == 0) {
                        FindDeviceDialogActivity.this.f20111a.pause();
                    }
                    if (FindDeviceDialogActivity.this.c != null) {
                        if (FindDeviceDialogActivity.this.f) {
                            PreferenceUtils.b("find_device_tips", false);
                            FindDeviceDialogActivity.this.startActivity(new Intent(FindDeviceDialogActivity.this.getContext(), DialogTipsActivity.class));
                        }
                        if (booleanExtra) {
                            STAT.d.N(stringExtra2);
                        } else {
                            STAT.d.Q(stringExtra2);
                        }
                    }
                    FindDeviceDialogActivity.this.finish();
                    FindDeviceDialogActivity.this.overridePendingTransition(0, 0);
                }
            });
            Button button = (Button) findViewById(R.id.button1);
            if (this.g <= 0 || parcelableArrayListExtra == null || parcelableArrayListExtra.size() != 1 || !DeviceUtils.b(((PushBindEntity) parcelableArrayListExtra.get(0)).f22312a.o())) {
                button.setText(R.string.begin_connection);
            } else {
                button.setText(R.string.pushbind_auth);
            }
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FindDeviceDialogActivity.this.g > 0) {
                        if (parcelableArrayListExtra == null || parcelableArrayListExtra.size() != 1) {
                            FindDeviceDialogActivity.this.startActivity(new Intent(FindDeviceDialogActivity.this.getContext(), ChooseDeviceActivity.class));
                        } else {
                            FindDeviceDialogActivity.this.startActivity(new Intent(FindDeviceDialogActivity.this.getContext(), PushBindConfigActivity.class).putExtra(DevicePushBindManager.EXTRA_BINDWIFI, (Parcelable) parcelableArrayListExtra.get(0)));
                        }
                    } else if (FindDeviceDialogActivity.this.c != null) {
                        Intent a2 = ConfigStage.a(SHApplication.getAppContext(), FindDeviceDialogActivity.this.c, stringExtra2, (String) null, (String) null);
                        if (a2 != null) {
                            a2.setFlags(C.ENCODING_PCM_MU_LAW);
                            if (booleanExtra) {
                                a2.putExtra("category", 2);
                            } else {
                                a2.putExtra("category", 5);
                            }
                            a2.putExtra(SmartConfigDataProvider.N, booleanExtra);
                            FindDeviceDialogActivity.this.startActivity(a2);
                            if (FindDeviceDialogActivity.this.f20111a.getVisibility() == 0) {
                                FindDeviceDialogActivity.this.f20111a.pause();
                            }
                        }
                        if (booleanExtra) {
                            STAT.d.M(stringExtra2);
                        } else {
                            STAT.d.P(stringExtra2);
                        }
                    }
                    FindDeviceDialogActivity.this.finish();
                    FindDeviceDialogActivity.this.overridePendingTransition(0, 0);
                }
            });
            Button button2 = (Button) findViewById(R.id.button2);
            button2.setText(R.string.ignore);
            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    if (FindDeviceDialogActivity.this.f20111a.getVisibility() == 0) {
                        FindDeviceDialogActivity.this.f20111a.pause();
                    }
                    if (FindDeviceDialogActivity.this.c != null) {
                        DeviceObserveService.getInstance().onIgnoreSSID(FindDeviceDialogActivity.this.c.SSID);
                        if (FindDeviceDialogActivity.this.f) {
                            PreferenceUtils.b("find_device_tips", false);
                            FindDeviceDialogActivity.this.startActivity(new Intent(FindDeviceDialogActivity.this.getContext(), DialogTipsActivity.class));
                        }
                        if (booleanExtra) {
                            STAT.d.L(stringExtra2);
                        } else {
                            STAT.d.O(stringExtra2);
                        }
                    }
                    FindDeviceDialogActivity.this.finish();
                    FindDeviceDialogActivity.this.overridePendingTransition(0, 0);
                }
            });
            return;
        }
        finish();
    }

    /* access modifiers changed from: private */
    public void a(String str, String str2, final SimpleDraweeView simpleDraweeView, View view) {
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        if (!TextUtils.isEmpty(str2) && audioManager != null && !audioManager.isMusicActive()) {
            simpleDraweeView.setVisibility(0);
            this.f20111a.setVisibility(8);
            this.b = true;
            a(simpleDraweeView, view, Uri.parse(str));
            this.f20111a.setVideoURI(Uri.parse(str2));
            this.f20111a.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
                    return true;
                }
            });
            this.f20111a.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mediaPlayer) {
                    Log.e("FindDeviceDialog", "on video prepared");
                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        public boolean onInfo(MediaPlayer mediaPlayer, int i, int i2) {
                            Log.e("FindDeviceDialog", "on video " + i);
                            if (i != 3) {
                                return true;
                            }
                            simpleDraweeView.setVisibility(8);
                            return true;
                        }
                    });
                }
            });
            this.f20111a.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(final MediaPlayer mediaPlayer) {
                    FindDeviceDialogActivity.this.f20111a.postDelayed(new Runnable() {
                        public void run() {
                            try {
                                if (!FindDeviceDialogActivity.this.isFinishing() && !FindDeviceDialogActivity.this.isDestroyed()) {
                                    mediaPlayer.start();
                                }
                            } catch (Exception unused) {
                            }
                        }
                    }, 2000);
                }
            });
        } else if (!TextUtils.isEmpty(str)) {
            this.f20111a.setVisibility(8);
            a(simpleDraweeView, view, Uri.parse(str));
        } else {
            this.f20111a.setVisibility(8);
            simpleDraweeView.setImageURI(CommonUtils.c((int) R.drawable.defaule_icon_default_nor));
        }
    }

    public void onBackPressed() {
        if (this.f) {
            PreferenceUtils.b("find_device_tips", false);
            startActivity(new Intent(getContext(), DialogTipsActivity.class));
        }
        finish();
        overridePendingTransition(0, 0);
    }

    /* access modifiers changed from: protected */
    public void onPause() {
        super.onPause();
        sResumed = false;
        SHApplication.getGlobalHandler().postDelayed(this.h, 1000);
    }

    /* access modifiers changed from: protected */
    public void onResume() {
        super.onResume();
        sResumed = true;
        SHApplication.getGlobalHandler().removeCallbacks(this.h);
    }
}
