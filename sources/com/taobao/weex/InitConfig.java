package com.taobao.weex;

import com.taobao.weex.adapter.ClassLoaderAdapter;
import com.taobao.weex.adapter.IDrawableLoader;
import com.taobao.weex.adapter.IWXHttpAdapter;
import com.taobao.weex.adapter.IWXImgLoaderAdapter;
import com.taobao.weex.adapter.IWXJSExceptionAdapter;
import com.taobao.weex.adapter.IWXJsFileLoaderAdapter;
import com.taobao.weex.adapter.IWXJscProcessManager;
import com.taobao.weex.adapter.IWXSoLoaderAdapter;
import com.taobao.weex.adapter.IWXUserTrackAdapter;
import com.taobao.weex.adapter.URIAdapter;
import com.taobao.weex.appfram.storage.IWXStorageAdapter;
import com.taobao.weex.appfram.websocket.IWebSocketAdapterFactory;
import com.taobao.weex.performance.IApmGenerator;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class InitConfig {
    private static transient /* synthetic */ boolean[] $jacocoData;
    private IApmGenerator apmGenerater;
    private ClassLoaderAdapter classLoaderAdapter;
    private IDrawableLoader drawableLoader;
    private String framework;
    private IWXHttpAdapter httpAdapter;
    private IWXImgLoaderAdapter imgAdapter;
    private IWXJsFileLoaderAdapter jsFileLoaderAdapter;
    private IWXJscProcessManager jscProcessManager;
    private IWXJSExceptionAdapter mJSExceptionAdapter;
    private URIAdapter mURIAdapter;
    private IWXSoLoaderAdapter soLoader;
    private IWXStorageAdapter storageAdapter;
    private IWXUserTrackAdapter utAdapter;
    private IWebSocketAdapterFactory webSocketAdapterFactory;

    /* renamed from: com.taobao.weex.InitConfig$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        private static transient /* synthetic */ boolean[] $jacocoData;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-2317293765146125907L, "com/taobao/weex/InitConfig$1", 0);
            $jacocoData = a2;
            return a2;
        }
    }

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(4891290804722773178L, "com/taobao/weex/InitConfig", 31);
        $jacocoData = a2;
        return a2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    /* synthetic */ InitConfig(AnonymousClass1 r3) {
        this();
        boolean[] $jacocoInit = $jacocoInit();
        $jacocoInit[16] = true;
    }

    static /* synthetic */ IWXJSExceptionAdapter access$1002(InitConfig initConfig, IWXJSExceptionAdapter iWXJSExceptionAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.mJSExceptionAdapter = iWXJSExceptionAdapter;
        $jacocoInit[26] = true;
        return iWXJSExceptionAdapter;
    }

    static /* synthetic */ IWXHttpAdapter access$102(InitConfig initConfig, IWXHttpAdapter iWXHttpAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.httpAdapter = iWXHttpAdapter;
        $jacocoInit[17] = true;
        return iWXHttpAdapter;
    }

    static /* synthetic */ ClassLoaderAdapter access$1102(InitConfig initConfig, ClassLoaderAdapter classLoaderAdapter2) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.classLoaderAdapter = classLoaderAdapter2;
        $jacocoInit[27] = true;
        return classLoaderAdapter2;
    }

    static /* synthetic */ IApmGenerator access$1202(InitConfig initConfig, IApmGenerator iApmGenerator) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.apmGenerater = iApmGenerator;
        $jacocoInit[28] = true;
        return iApmGenerator;
    }

    static /* synthetic */ IWXJsFileLoaderAdapter access$1302(InitConfig initConfig, IWXJsFileLoaderAdapter iWXJsFileLoaderAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.jsFileLoaderAdapter = iWXJsFileLoaderAdapter;
        $jacocoInit[29] = true;
        return iWXJsFileLoaderAdapter;
    }

    static /* synthetic */ IWXJscProcessManager access$1402(InitConfig initConfig, IWXJscProcessManager iWXJscProcessManager) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.jscProcessManager = iWXJscProcessManager;
        $jacocoInit[30] = true;
        return iWXJscProcessManager;
    }

    static /* synthetic */ IWXImgLoaderAdapter access$202(InitConfig initConfig, IWXImgLoaderAdapter iWXImgLoaderAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.imgAdapter = iWXImgLoaderAdapter;
        $jacocoInit[18] = true;
        return iWXImgLoaderAdapter;
    }

    static /* synthetic */ IDrawableLoader access$302(InitConfig initConfig, IDrawableLoader iDrawableLoader) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.drawableLoader = iDrawableLoader;
        $jacocoInit[19] = true;
        return iDrawableLoader;
    }

    static /* synthetic */ IWXUserTrackAdapter access$402(InitConfig initConfig, IWXUserTrackAdapter iWXUserTrackAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.utAdapter = iWXUserTrackAdapter;
        $jacocoInit[20] = true;
        return iWXUserTrackAdapter;
    }

    static /* synthetic */ IWXStorageAdapter access$502(InitConfig initConfig, IWXStorageAdapter iWXStorageAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.storageAdapter = iWXStorageAdapter;
        $jacocoInit[21] = true;
        return iWXStorageAdapter;
    }

    static /* synthetic */ IWXSoLoaderAdapter access$602(InitConfig initConfig, IWXSoLoaderAdapter iWXSoLoaderAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.soLoader = iWXSoLoaderAdapter;
        $jacocoInit[22] = true;
        return iWXSoLoaderAdapter;
    }

    static /* synthetic */ String access$702(InitConfig initConfig, String str) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.framework = str;
        $jacocoInit[23] = true;
        return str;
    }

    static /* synthetic */ URIAdapter access$802(InitConfig initConfig, URIAdapter uRIAdapter) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.mURIAdapter = uRIAdapter;
        $jacocoInit[24] = true;
        return uRIAdapter;
    }

    static /* synthetic */ IWebSocketAdapterFactory access$902(InitConfig initConfig, IWebSocketAdapterFactory iWebSocketAdapterFactory) {
        boolean[] $jacocoInit = $jacocoInit();
        initConfig.webSocketAdapterFactory = iWebSocketAdapterFactory;
        $jacocoInit[25] = true;
        return iWebSocketAdapterFactory;
    }

    public IWXHttpAdapter getHttpAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXHttpAdapter iWXHttpAdapter = this.httpAdapter;
        $jacocoInit[0] = true;
        return iWXHttpAdapter;
    }

    public IWXImgLoaderAdapter getImgAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXImgLoaderAdapter iWXImgLoaderAdapter = this.imgAdapter;
        $jacocoInit[1] = true;
        return iWXImgLoaderAdapter;
    }

    public IDrawableLoader getDrawableLoader() {
        boolean[] $jacocoInit = $jacocoInit();
        IDrawableLoader iDrawableLoader = this.drawableLoader;
        $jacocoInit[2] = true;
        return iDrawableLoader;
    }

    public IWXUserTrackAdapter getUtAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXUserTrackAdapter iWXUserTrackAdapter = this.utAdapter;
        $jacocoInit[3] = true;
        return iWXUserTrackAdapter;
    }

    public IWXSoLoaderAdapter getIWXSoLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXSoLoaderAdapter iWXSoLoaderAdapter = this.soLoader;
        $jacocoInit[4] = true;
        return iWXSoLoaderAdapter;
    }

    public String getFramework() {
        boolean[] $jacocoInit = $jacocoInit();
        String str = this.framework;
        $jacocoInit[5] = true;
        return str;
    }

    public IWXStorageAdapter getStorageAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStorageAdapter iWXStorageAdapter = this.storageAdapter;
        $jacocoInit[6] = true;
        return iWXStorageAdapter;
    }

    public URIAdapter getURIAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        URIAdapter uRIAdapter = this.mURIAdapter;
        $jacocoInit[7] = true;
        return uRIAdapter;
    }

    public IWebSocketAdapterFactory getWebSocketAdapterFactory() {
        boolean[] $jacocoInit = $jacocoInit();
        IWebSocketAdapterFactory iWebSocketAdapterFactory = this.webSocketAdapterFactory;
        $jacocoInit[8] = true;
        return iWebSocketAdapterFactory;
    }

    public ClassLoaderAdapter getClassLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        ClassLoaderAdapter classLoaderAdapter2 = this.classLoaderAdapter;
        $jacocoInit[9] = true;
        return classLoaderAdapter2;
    }

    public IApmGenerator getApmGenerater() {
        boolean[] $jacocoInit = $jacocoInit();
        IApmGenerator iApmGenerator = this.apmGenerater;
        $jacocoInit[10] = true;
        return iApmGenerator;
    }

    public IWXJsFileLoaderAdapter getJsFileLoaderAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXJsFileLoaderAdapter iWXJsFileLoaderAdapter = this.jsFileLoaderAdapter;
        $jacocoInit[11] = true;
        return iWXJsFileLoaderAdapter;
    }

    public InitConfig setClassLoaderAdapter(ClassLoaderAdapter classLoaderAdapter2) {
        boolean[] $jacocoInit = $jacocoInit();
        this.classLoaderAdapter = classLoaderAdapter2;
        $jacocoInit[12] = true;
        return this;
    }

    public IWXJSExceptionAdapter getJSExceptionAdapter() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXJSExceptionAdapter iWXJSExceptionAdapter = this.mJSExceptionAdapter;
        $jacocoInit[13] = true;
        return iWXJSExceptionAdapter;
    }

    public IWXJscProcessManager getJscProcessManager() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXJscProcessManager iWXJscProcessManager = this.jscProcessManager;
        $jacocoInit[14] = true;
        return iWXJscProcessManager;
    }

    private InitConfig() {
        $jacocoInit()[15] = true;
    }

    public static class Builder {
        private static transient /* synthetic */ boolean[] $jacocoData;
        IApmGenerator apmGenerater;
        ClassLoaderAdapter classLoaderAdapter;
        IDrawableLoader drawableLoader;
        String framework;
        IWXHttpAdapter httpAdapter;
        IWXImgLoaderAdapter imgAdapter;
        private IWXJsFileLoaderAdapter jsFileLoaderAdapter;
        IWXJscProcessManager jscProcessManager;
        IWXJSExceptionAdapter mJSExceptionAdapter;
        URIAdapter mURIAdapter;
        IWXSoLoaderAdapter soLoader;
        IWXStorageAdapter storageAdapter;
        IWXUserTrackAdapter utAdapter;
        IWebSocketAdapterFactory webSocketAdapterFactory;

        private static /* synthetic */ boolean[] $jacocoInit() {
            boolean[] zArr = $jacocoData;
            if (zArr != null) {
                return zArr;
            }
            boolean[] a2 = Offline.a(-4027088260831258167L, "com/taobao/weex/InitConfig$Builder", 31);
            $jacocoData = a2;
            return a2;
        }

        public IWXJscProcessManager getJscProcessManager() {
            boolean[] $jacocoInit = $jacocoInit();
            IWXJscProcessManager iWXJscProcessManager = this.jscProcessManager;
            $jacocoInit[0] = true;
            return iWXJscProcessManager;
        }

        public Builder setJscProcessManager(IWXJscProcessManager iWXJscProcessManager) {
            boolean[] $jacocoInit = $jacocoInit();
            this.jscProcessManager = iWXJscProcessManager;
            $jacocoInit[1] = true;
            return this;
        }

        public Builder() {
            $jacocoInit()[2] = true;
        }

        public Builder setHttpAdapter(IWXHttpAdapter iWXHttpAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.httpAdapter = iWXHttpAdapter;
            $jacocoInit[3] = true;
            return this;
        }

        public Builder setImgAdapter(IWXImgLoaderAdapter iWXImgLoaderAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.imgAdapter = iWXImgLoaderAdapter;
            $jacocoInit[4] = true;
            return this;
        }

        public Builder setDrawableLoader(IDrawableLoader iDrawableLoader) {
            boolean[] $jacocoInit = $jacocoInit();
            this.drawableLoader = iDrawableLoader;
            $jacocoInit[5] = true;
            return this;
        }

        public Builder setUtAdapter(IWXUserTrackAdapter iWXUserTrackAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.utAdapter = iWXUserTrackAdapter;
            $jacocoInit[6] = true;
            return this;
        }

        public Builder setStorageAdapter(IWXStorageAdapter iWXStorageAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.storageAdapter = iWXStorageAdapter;
            $jacocoInit[7] = true;
            return this;
        }

        public Builder setURIAdapter(URIAdapter uRIAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mURIAdapter = uRIAdapter;
            $jacocoInit[8] = true;
            return this;
        }

        public Builder setJSExceptionAdapter(IWXJSExceptionAdapter iWXJSExceptionAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.mJSExceptionAdapter = iWXJSExceptionAdapter;
            $jacocoInit[9] = true;
            return this;
        }

        public Builder setSoLoader(IWXSoLoaderAdapter iWXSoLoaderAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.soLoader = iWXSoLoaderAdapter;
            $jacocoInit[10] = true;
            return this;
        }

        public Builder setFramework(String str) {
            boolean[] $jacocoInit = $jacocoInit();
            this.framework = str;
            $jacocoInit[11] = true;
            return this;
        }

        public Builder setWebSocketAdapterFactory(IWebSocketAdapterFactory iWebSocketAdapterFactory) {
            boolean[] $jacocoInit = $jacocoInit();
            this.webSocketAdapterFactory = iWebSocketAdapterFactory;
            $jacocoInit[12] = true;
            return this;
        }

        public Builder setClassLoaderAdapter(ClassLoaderAdapter classLoaderAdapter2) {
            boolean[] $jacocoInit = $jacocoInit();
            this.classLoaderAdapter = classLoaderAdapter2;
            $jacocoInit[13] = true;
            return this;
        }

        public Builder setApmGenerater(IApmGenerator iApmGenerator) {
            boolean[] $jacocoInit = $jacocoInit();
            this.apmGenerater = iApmGenerator;
            $jacocoInit[14] = true;
            return this;
        }

        public Builder setJsFileLoaderAdapter(IWXJsFileLoaderAdapter iWXJsFileLoaderAdapter) {
            boolean[] $jacocoInit = $jacocoInit();
            this.jsFileLoaderAdapter = iWXJsFileLoaderAdapter;
            $jacocoInit[15] = true;
            return this;
        }

        public InitConfig build() {
            boolean[] $jacocoInit = $jacocoInit();
            InitConfig initConfig = new InitConfig((AnonymousClass1) null);
            $jacocoInit[16] = true;
            InitConfig.access$102(initConfig, this.httpAdapter);
            $jacocoInit[17] = true;
            InitConfig.access$202(initConfig, this.imgAdapter);
            $jacocoInit[18] = true;
            InitConfig.access$302(initConfig, this.drawableLoader);
            $jacocoInit[19] = true;
            InitConfig.access$402(initConfig, this.utAdapter);
            $jacocoInit[20] = true;
            InitConfig.access$502(initConfig, this.storageAdapter);
            $jacocoInit[21] = true;
            InitConfig.access$602(initConfig, this.soLoader);
            $jacocoInit[22] = true;
            InitConfig.access$702(initConfig, this.framework);
            $jacocoInit[23] = true;
            InitConfig.access$802(initConfig, this.mURIAdapter);
            $jacocoInit[24] = true;
            InitConfig.access$902(initConfig, this.webSocketAdapterFactory);
            $jacocoInit[25] = true;
            InitConfig.access$1002(initConfig, this.mJSExceptionAdapter);
            $jacocoInit[26] = true;
            InitConfig.access$1102(initConfig, this.classLoaderAdapter);
            $jacocoInit[27] = true;
            InitConfig.access$1202(initConfig, this.apmGenerater);
            $jacocoInit[28] = true;
            InitConfig.access$1302(initConfig, this.jsFileLoaderAdapter);
            $jacocoInit[29] = true;
            InitConfig.access$1402(initConfig, this.jscProcessManager);
            $jacocoInit[30] = true;
            return initConfig;
        }
    }
}
