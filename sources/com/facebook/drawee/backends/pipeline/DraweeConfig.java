package com.facebook.drawee.backends.pipeline;

import com.facebook.common.internal.ImmutableList;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.internal.Suppliers;
import com.facebook.imagepipeline.drawable.DrawableFactory;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class DraweeConfig {
    @Nullable
    private final ImmutableList<DrawableFactory> mCustomDrawableFactories;
    private final Supplier<Boolean> mDebugOverlayEnabledSupplier;
    @Nullable
    private final PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

    private DraweeConfig(Builder builder) {
        Supplier<Boolean> supplier;
        this.mCustomDrawableFactories = builder.mCustomDrawableFactories != null ? ImmutableList.copyOf(builder.mCustomDrawableFactories) : null;
        if (builder.mDebugOverlayEnabledSupplier != null) {
            supplier = builder.mDebugOverlayEnabledSupplier;
        } else {
            supplier = Suppliers.of(false);
        }
        this.mDebugOverlayEnabledSupplier = supplier;
        this.mPipelineDraweeControllerFactory = builder.mPipelineDraweeControllerFactory;
    }

    @Nullable
    public ImmutableList<DrawableFactory> getCustomDrawableFactories() {
        return this.mCustomDrawableFactories;
    }

    @Nullable
    public PipelineDraweeControllerFactory getPipelineDraweeControllerFactory() {
        return this.mPipelineDraweeControllerFactory;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public Supplier<Boolean> getDebugOverlayEnabledSupplier() {
        return this.mDebugOverlayEnabledSupplier;
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public List<DrawableFactory> mCustomDrawableFactories;
        /* access modifiers changed from: private */
        public Supplier<Boolean> mDebugOverlayEnabledSupplier;
        /* access modifiers changed from: private */
        public PipelineDraweeControllerFactory mPipelineDraweeControllerFactory;

        public Builder addCustomDrawableFactory(DrawableFactory drawableFactory) {
            if (this.mCustomDrawableFactories == null) {
                this.mCustomDrawableFactories = new ArrayList();
            }
            this.mCustomDrawableFactories.add(drawableFactory);
            return this;
        }

        public Builder setDrawDebugOverlay(boolean z) {
            return setDebugOverlayEnabledSupplier(Suppliers.of(Boolean.valueOf(z)));
        }

        public Builder setDebugOverlayEnabledSupplier(Supplier<Boolean> supplier) {
            Preconditions.checkNotNull(supplier);
            this.mDebugOverlayEnabledSupplier = supplier;
            return this;
        }

        public Builder setPipelineDraweeControllerFactory(PipelineDraweeControllerFactory pipelineDraweeControllerFactory) {
            this.mPipelineDraweeControllerFactory = pipelineDraweeControllerFactory;
            return this;
        }

        public DraweeConfig build() {
            return new DraweeConfig(this);
        }
    }
}
