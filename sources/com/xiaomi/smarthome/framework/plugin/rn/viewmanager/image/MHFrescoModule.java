package com.xiaomi.smarthome.framework.plugin.rn.viewmanager.image;

import android.support.annotation.Nullable;
import com.facebook.drawee.ExtendPipelineControllerFactory;
import com.facebook.drawee.backends.pipeline.DraweeConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.module.annotations.ReactModule;
import com.facebook.react.modules.fresco.FrescoModule;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;

@ReactModule(name = "FrescoModule")
public class MHFrescoModule extends FrescoModule {
    private ImagePipelineConfig mConfig;

    public boolean canOverrideExistingModule() {
        return true;
    }

    public MHFrescoModule(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    public MHFrescoModule(ReactApplicationContext reactApplicationContext, boolean z) {
        super(reactApplicationContext, z);
    }

    public MHFrescoModule(ReactApplicationContext reactApplicationContext, boolean z, @Nullable ImagePipelineConfig imagePipelineConfig) {
        super(reactApplicationContext, z, imagePipelineConfig);
        this.mConfig = imagePipelineConfig;
    }

    public void initialize() {
        RnPluginLog.a("MHFrescoModule...initialize...");
        if (!hasBeenInitialized()) {
            super.initialize();
            if (this.mConfig == null) {
                this.mConfig = getDefaultConfigBuilder(getReactApplicationContext()).build();
            }
            Fresco.initialize(getReactApplicationContext().getApplicationContext(), this.mConfig, DraweeConfig.newBuilder().setPipelineDraweeControllerFactory(new ExtendPipelineControllerFactory()).build());
        }
        this.mConfig = null;
    }
}
