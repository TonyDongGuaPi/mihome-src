package com.xiaomi.smarthome.miio.activity;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.feedback.FeedbackApi;
import com.xiaomi.smarthome.feedback.FeedbackCommonProblemActivity;
import com.xiaomi.smarthome.framework.page.BaseActivity;
import com.xiaomi.smarthome.framework.page.BaseFragment;
import com.xiaomi.smarthome.library.common.widget.FragmentPagerAdapter;
import com.xiaomi.smarthome.library.common.widget.ViewPager;
import com.xiaomi.smarthome.miio.TitleBarUtil;
import java.util.ArrayList;
import java.util.List;

public class BleGatewayGuideActivity extends BaseActivity {

    /* renamed from: a  reason: collision with root package name */
    private PagerAdapter f11682a;
    /* access modifiers changed from: private */
    public FirstFragment b;
    /* access modifiers changed from: private */
    public SecondFragment c;
    /* access modifiers changed from: private */
    public ThirdFragment d;
    @BindView(2131429250)
    View mFirstIndicator;
    @BindView(2131432236)
    View mSecondIndicator;
    @BindView(2131433842)
    ViewPager mViewPager;

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        overridePendingTransition(R.anim.activity_slide_in_bottom, R.anim.activity_slide_out_bottom);
        TitleBarUtil.b(getWindow());
        setContentView(R.layout.activity_bluetooth_gateway_guide_layout);
        ButterKnife.bind((Activity) this);
        this.f11682a = new PagerAdapter(getSupportFragmentManager());
        this.mViewPager.setAdapter(this.f11682a);
        this.mViewPager.setOffscreenPageLimit(1);
        this.mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageSelected(int i) {
                AccessibilityEvent obtain = AccessibilityEvent.obtain();
                AccessibilityManager accessibilityManager = (AccessibilityManager) BleGatewayGuideActivity.this.getSystemService("accessibility");
                if (i == 0) {
                    BleGatewayGuideActivity.this.mFirstIndicator.setVisibility(0);
                    BleGatewayGuideActivity.this.mSecondIndicator.setVisibility(8);
                    obtain.setEventType(16384);
                    obtain.getText().add(BleGatewayGuideActivity.this.getResources().getStringArray(R.array.ble_guide_content_description)[0]);
                    if (BleGatewayGuideActivity.this.b.f11684a != null) {
                        BleGatewayGuideActivity.this.b.f11684a.requestFocus();
                    }
                } else if (i == 1) {
                    BleGatewayGuideActivity.this.mFirstIndicator.setVisibility(8);
                    BleGatewayGuideActivity.this.mSecondIndicator.setVisibility(0);
                    obtain.setEventType(16384);
                    obtain.getText().add(BleGatewayGuideActivity.this.getResources().getStringArray(R.array.ble_guide_content_description)[1]);
                    if (BleGatewayGuideActivity.this.c.f11688a != null) {
                        BleGatewayGuideActivity.this.c.f11688a.requestFocus();
                    }
                } else {
                    BleGatewayGuideActivity.this.mFirstIndicator.setVisibility(8);
                    BleGatewayGuideActivity.this.mSecondIndicator.setVisibility(8);
                    obtain.setEventType(16384);
                    obtain.getText().add(BleGatewayGuideActivity.this.getResources().getStringArray(R.array.ble_guide_content_description)[2]);
                    if (BleGatewayGuideActivity.this.d.f11691a != null) {
                        BleGatewayGuideActivity.this.d.f11691a.requestFocus();
                    }
                }
                try {
                    accessibilityManager.sendAccessibilityEvent(obtain);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            public void onPageScrollStateChanged(int i) {
                switch (i) {
                    case 0:
                        if (BleGatewayGuideActivity.this.mViewPager.getCurrentItem() == 0) {
                            BleGatewayGuideActivity.this.b.a();
                            return;
                        } else if (BleGatewayGuideActivity.this.mViewPager.getCurrentItem() == 1) {
                            BleGatewayGuideActivity.this.c.a();
                            return;
                        } else {
                            BleGatewayGuideActivity.this.d.a();
                            return;
                        }
                    case 1:
                        BleGatewayGuideActivity.this.b.b();
                        BleGatewayGuideActivity.this.c.b();
                        BleGatewayGuideActivity.this.d.b();
                        return;
                    default:
                        return;
                }
            }
        });
    }

    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.activity_slide_in_bottom, R.anim.activity_slide_out_bottom);
    }

    private class PagerAdapter extends FragmentPagerAdapter {
        private List<BaseFragment> d = new ArrayList();

        public PagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            FirstFragment unused = BleGatewayGuideActivity.this.b = new FirstFragment();
            SecondFragment unused2 = BleGatewayGuideActivity.this.c = new SecondFragment();
            ThirdFragment unused3 = BleGatewayGuideActivity.this.d = new ThirdFragment();
            this.d.add(BleGatewayGuideActivity.this.b);
            this.d.add(BleGatewayGuideActivity.this.c);
            this.d.add(BleGatewayGuideActivity.this.d);
        }

        public int a() {
            return this.d.size();
        }

        public Fragment a(int i) {
            if (i < 0 || i >= this.d.size()) {
                return null;
            }
            return this.d.get(i);
        }
    }

    public static class FirstFragment extends BaseFragment {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public View f11684a;
        /* access modifiers changed from: private */
        public LottieAnimationView b;
        /* access modifiers changed from: private */
        public ValueAnimator c;

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            if (this.f11684a == null) {
                this.f11684a = layoutInflater.inflate(R.layout.ble_gateway_guide_first_fragment_layout, (ViewGroup) null);
                this.b = (LottieAnimationView) this.f11684a.findViewById(R.id.lottie_view_1);
                LottieComposition.Factory.fromAssetFileName(getContext(), "ble_gateway_guide/first_lottie/data.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        FirstFragment.this.b.setComposition(lottieComposition);
                        FirstFragment.this.b.setImageAssetsFolder("ble_gateway_guide/first_lottie/images");
                        ValueAnimator unused = FirstFragment.this.c = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(3500);
                        FirstFragment.this.c.setRepeatCount(-1);
                        FirstFragment.this.c.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                if (FirstFragment.this.b != null) {
                                    FirstFragment.this.b.setProgress(((Float) FirstFragment.this.c.getAnimatedValue()).floatValue());
                                }
                            }
                        });
                        FirstFragment.this.c.start();
                    }
                });
            }
            return this.f11684a;
        }

        public void a() {
            if (this.b != null && this.c != null) {
                this.c.start();
            }
        }

        public void b() {
            if (this.b != null && this.c != null) {
                this.b.cancelAnimation();
                this.c.cancel();
            }
        }

        public void onDestroyView() {
            if (this.b != null) {
                this.b.cancelAnimation();
                this.b = null;
            }
            if (this.c != null) {
                this.c.cancel();
                this.c = null;
            }
            super.onDestroyView();
        }
    }

    public static class SecondFragment extends BaseFragment {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public View f11688a;
        /* access modifiers changed from: private */
        public LottieAnimationView b;
        /* access modifiers changed from: private */
        public ValueAnimator c;

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            if (this.f11688a == null) {
                this.f11688a = layoutInflater.inflate(R.layout.ble_gateway_guide_second_fragment_layout, (ViewGroup) null);
                this.b = (LottieAnimationView) this.f11688a.findViewById(R.id.lottie_view_2);
                LottieComposition.Factory.fromAssetFileName(getContext(), "ble_gateway_guide/second_lottie/data.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        SecondFragment.this.b.setComposition(lottieComposition);
                        SecondFragment.this.b.setImageAssetsFolder("ble_gateway_guide/second_lottie/images");
                        ValueAnimator unused = SecondFragment.this.c = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(3450);
                        SecondFragment.this.c.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                if (SecondFragment.this.b != null) {
                                    SecondFragment.this.b.setProgress(((Float) SecondFragment.this.c.getAnimatedValue()).floatValue());
                                }
                            }
                        });
                    }
                });
            }
            return this.f11688a;
        }

        public void a() {
            if (this.b != null && this.c != null) {
                this.c.start();
            }
        }

        public void b() {
            if (this.b != null && this.c != null) {
                this.b.cancelAnimation();
                this.c.cancel();
            }
        }

        public void onDestroyView() {
            if (this.b != null) {
                this.b.cancelAnimation();
                this.b = null;
            }
            if (this.c != null) {
                this.c.cancel();
                this.c = null;
            }
            super.onDestroyView();
        }
    }

    public static class ThirdFragment extends BaseFragment {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public View f11691a;
        /* access modifiers changed from: private */
        public View b;
        /* access modifiers changed from: private */
        public LottieAnimationView c;
        /* access modifiers changed from: private */
        public ValueAnimator d;

        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            if (this.f11691a == null) {
                this.f11691a = layoutInflater.inflate(R.layout.ble_gateway_guide_third_fragment_layout, (ViewGroup) null);
                this.b = this.f11691a.findViewById(R.id.about_ble_gateway);
                this.b.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        ThirdFragment.this.b.setEnabled(false);
                        Intent intent = new Intent();
                        intent.setClass(view.getContext(), FeedbackCommonProblemActivity.class);
                        intent.putExtra("extra_model", FeedbackApi.BLE_GATEWAY);
                        ThirdFragment.this.startActivity(intent);
                    }
                });
                this.f11691a.findViewById(R.id.immediate_experience).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View view) {
                        FragmentActivity activity = ThirdFragment.this.getActivity();
                        if (activity != null && !activity.isFinishing()) {
                            activity.finish();
                        }
                    }
                });
                this.c = (LottieAnimationView) this.f11691a.findViewById(R.id.lottie_view_3);
                LottieComposition.Factory.fromAssetFileName(getContext(), "ble_gateway_guide/third_lottie/data.json", new OnCompositionLoadedListener() {
                    public void onCompositionLoaded(LottieComposition lottieComposition) {
                        if (ThirdFragment.this.c != null) {
                            ThirdFragment.this.c.setComposition(lottieComposition);
                            ThirdFragment.this.c.setImageAssetsFolder("ble_gateway_guide/third_lottie/images");
                            ValueAnimator unused = ThirdFragment.this.d = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f}).setDuration(3450);
                            ThirdFragment.this.d.setRepeatCount(-1);
                            ThirdFragment.this.d.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                                    if (ThirdFragment.this.c != null) {
                                        ThirdFragment.this.c.setProgress(((Float) ThirdFragment.this.d.getAnimatedValue()).floatValue());
                                    }
                                }
                            });
                        }
                    }
                });
            }
            return this.f11691a;
        }

        public void onResume() {
            super.onResume();
            this.b.setEnabled(true);
        }

        public void a() {
            if (this.c != null && this.d != null) {
                this.d.start();
            }
        }

        public void b() {
            if (this.c != null && this.d != null) {
                this.c.cancelAnimation();
                this.d.cancel();
            }
        }

        public void onDestroyView() {
            if (this.c != null) {
                this.c.cancelAnimation();
                this.c = null;
            }
            if (this.d != null) {
                this.d.cancel();
                this.d = null;
            }
            super.onDestroyView();
        }
    }
}
