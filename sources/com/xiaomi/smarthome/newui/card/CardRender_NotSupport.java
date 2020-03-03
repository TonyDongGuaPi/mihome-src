package com.xiaomi.smarthome.newui.card;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.common.executors.CallerThreadExecutor;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.imagepipeline.datasource.BaseBitmapDataSubscriber;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.common.util.DisplayUtils;
import com.xiaomi.smarthome.miotcard.R;
import com.xiaomi.smarthome.newui.card.Card;
import com.xiaomi.smarthome.newui.card.Card.CardType;
import com.xiaomi.smarthome.newui.widget.ProgressItemView;
import com.xiaomi.smarthome.printer.DeviceImageApi;
import java.util.ArrayList;

public class CardRender_NotSupport<C extends Card<E>, E extends Card.CardType<T>, T> extends BaseCardRender<C, E, T> {
    public static final String f = "https://static.home.mi.com/app/image/get/file/";
    private ProgressItemView g;
    private View h;
    private TextView i;

    public void b() {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.miui10_cardlayout_notsupport, this.c, false);
        this.c.addView(inflate);
        this.i = (TextView) inflate.findViewById(R.id.unsupport_text);
        a(this.c, this.e);
        if (!this.e.isOnline) {
            this.i.setTextColor(this.c.getResources().getColor(R.color.tv_card_title_offline));
        }
        b(inflate);
        a(inflate);
    }

    private void a(final View view) {
        CoreApi.a().a(CommonApplication.getAppContext(), (CoreApi.IsCoreReadyCallback) new CoreApi.IsCoreReadyCallback() {
            public void onCoreReady() {
                DeviceImageApi.a(CardRender_NotSupport.this.e.model, new AsyncCallback<DeviceImageApi.DeviceImageEntity, Error>() {
                    public void onFailure(Error error) {
                    }

                    /* renamed from: a */
                    public void onSuccess(DeviceImageApi.DeviceImageEntity deviceImageEntity) {
                        if (TextUtils.isEmpty(deviceImageEntity.f21157a)) {
                            PluginRecord d = CoreApi.a().d(CardRender_NotSupport.this.e.model);
                            if (d != null) {
                                CardRender_NotSupport.this.a(view, Uri.parse(d.t()));
                                return;
                            }
                            return;
                        }
                        CardRender_NotSupport.this.a(view, Uri.parse(deviceImageEntity.f21157a));
                    }
                });
            }
        });
    }

    /* access modifiers changed from: private */
    public void a(final View view, Uri uri) {
        ImagePipelineFactory.getInstance().getImagePipeline().fetchDecodedImage(ImageRequestBuilder.newBuilderWithSource(uri).build(), (Object) null).subscribe(new BaseBitmapDataSubscriber() {
            public void onFailureImpl(DataSource dataSource) {
            }

            public void onNewResultImpl(Bitmap bitmap) {
                if (bitmap != null && !bitmap.isRecycled()) {
                    CardRender_NotSupport.this.a(view, Bitmap.createBitmap(bitmap));
                }
            }

            public void onCancellation(DataSource<CloseableReference<CloseableImage>> dataSource) {
                super.onCancellation(dataSource);
            }
        }, CallerThreadExecutor.getInstance());
    }

    /* access modifiers changed from: private */
    public void a(final View view, final Bitmap bitmap) {
        view.post(new Runnable() {
            public void run() {
                ImageView imageView;
                if (!((Activity) CardRender_NotSupport.this.d).isFinishing() && (imageView = (ImageView) view.findViewById(R.id.icon)) != null) {
                    imageView.setImageBitmap(bitmap);
                    ObjectAnimator ofFloat = ObjectAnimator.ofFloat((TextView) view.findViewById(R.id.unsupport_text), "translationY", new float[]{0.0f, (float) DisplayUtils.a(view.getContext(), 70.0f)});
                    ofFloat.setDuration(400);
                    ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
                    ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(imageView, "alpha", new float[]{0.0f, 1.0f});
                    ofFloat2.setDuration(400);
                    AnimatorSet animatorSet = new AnimatorSet();
                    animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
                    animatorSet.start();
                }
            }
        });
    }

    public ProgressItemView c() {
        return this.g;
    }

    private void b(View view) {
        this.g = (ProgressItemView) view.findViewById(R.id.dpb_enter_device);
        this.h = view.findViewById(R.id.progress_enter_device);
    }

    public View d() {
        return this.h;
    }

    public CardRender_NotSupport(C c, ArrayList<? extends CardItem<C, E, T>> arrayList, ViewGroup viewGroup, Context context, Device device) {
        super(c, arrayList, viewGroup, context, device);
    }
}
