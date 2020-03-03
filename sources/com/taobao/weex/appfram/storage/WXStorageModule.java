package com.taobao.weex.appfram.storage;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.taobao.weex.WXSDKEngine;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.appfram.storage.IWXStorageAdapter;
import com.taobao.weex.bridge.JSCallback;
import java.util.Map;
import org.jacoco.agent.rt.internal_8ff85ea.Offline;

public class WXStorageModule extends WXSDKEngine.DestroyableModule implements IWXStorage {
    private static transient /* synthetic */ boolean[] $jacocoData;
    IWXStorageAdapter mStorageAdapter;

    private static /* synthetic */ boolean[] $jacocoInit() {
        boolean[] zArr = $jacocoData;
        if (zArr != null) {
            return zArr;
        }
        boolean[] a2 = Offline.a(7109919070889477662L, "com/taobao/weex/appfram/storage/WXStorageModule", 35);
        $jacocoData = a2;
        return a2;
    }

    public WXStorageModule() {
        $jacocoInit()[0] = true;
    }

    private IWXStorageAdapter ability() {
        boolean[] $jacocoInit = $jacocoInit();
        if (this.mStorageAdapter != null) {
            IWXStorageAdapter iWXStorageAdapter = this.mStorageAdapter;
            $jacocoInit[1] = true;
            return iWXStorageAdapter;
        }
        this.mStorageAdapter = WXSDKEngine.getIWXStorageAdapter();
        IWXStorageAdapter iWXStorageAdapter2 = this.mStorageAdapter;
        $jacocoInit[2] = true;
        return iWXStorageAdapter2;
    }

    @JSMethod(uiThread = false)
    public void setItem(String str, String str2, @Nullable final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[3] = true;
        } else if (str2 == null) {
            $jacocoInit[4] = true;
        } else {
            IWXStorageAdapter ability = ability();
            if (ability == null) {
                $jacocoInit[6] = true;
                StorageResultHandler.handleNoHandlerError(jSCallback);
                $jacocoInit[7] = true;
                return;
            }
            ability.setItem(str, str2, new IWXStorageAdapter.OnResultReceivedListener(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXStorageModule this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(-7898410547876272992L, "com/taobao/weex/appfram/storage/WXStorageModule$1", 5);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void onReceived(Map<String, Object> map) {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (jSCallback == null) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        jSCallback.invoke(map);
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                }
            });
            $jacocoInit[8] = true;
            return;
        }
        StorageResultHandler.handleInvalidParam(jSCallback);
        $jacocoInit[5] = true;
    }

    @JSMethod(uiThread = false)
    public void getItem(String str, @Nullable final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[9] = true;
            StorageResultHandler.handleInvalidParam(jSCallback);
            $jacocoInit[10] = true;
            return;
        }
        IWXStorageAdapter ability = ability();
        if (ability == null) {
            $jacocoInit[11] = true;
            StorageResultHandler.handleNoHandlerError(jSCallback);
            $jacocoInit[12] = true;
            return;
        }
        ability.getItem(str, new IWXStorageAdapter.OnResultReceivedListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXStorageModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(6491610254712295718L, "com/taobao/weex/appfram/storage/WXStorageModule$2", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onReceived(Map<String, Object> map) {
                boolean[] $jacocoInit = $jacocoInit();
                if (jSCallback == null) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    jSCallback.invoke(map);
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[13] = true;
    }

    @JSMethod(uiThread = false)
    public void removeItem(String str, @Nullable final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[14] = true;
            StorageResultHandler.handleInvalidParam(jSCallback);
            $jacocoInit[15] = true;
            return;
        }
        IWXStorageAdapter ability = ability();
        if (ability == null) {
            $jacocoInit[16] = true;
            StorageResultHandler.handleNoHandlerError(jSCallback);
            $jacocoInit[17] = true;
            return;
        }
        ability.removeItem(str, new IWXStorageAdapter.OnResultReceivedListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXStorageModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(8498715567020919574L, "com/taobao/weex/appfram/storage/WXStorageModule$3", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onReceived(Map<String, Object> map) {
                boolean[] $jacocoInit = $jacocoInit();
                if (jSCallback == null) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    jSCallback.invoke(map);
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[18] = true;
    }

    @JSMethod(uiThread = false)
    public void length(@Nullable final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStorageAdapter ability = ability();
        if (ability == null) {
            $jacocoInit[19] = true;
            StorageResultHandler.handleNoHandlerError(jSCallback);
            $jacocoInit[20] = true;
            return;
        }
        ability.length(new IWXStorageAdapter.OnResultReceivedListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXStorageModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-718774334801906796L, "com/taobao/weex/appfram/storage/WXStorageModule$4", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onReceived(Map<String, Object> map) {
                boolean[] $jacocoInit = $jacocoInit();
                if (jSCallback == null) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    jSCallback.invoke(map);
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[21] = true;
    }

    @JSMethod(uiThread = false)
    public void getAllKeys(@Nullable final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStorageAdapter ability = ability();
        if (ability == null) {
            $jacocoInit[22] = true;
            StorageResultHandler.handleNoHandlerError(jSCallback);
            $jacocoInit[23] = true;
            return;
        }
        ability.getAllKeys(new IWXStorageAdapter.OnResultReceivedListener(this) {
            private static transient /* synthetic */ boolean[] $jacocoData;
            final /* synthetic */ WXStorageModule this$0;

            private static /* synthetic */ boolean[] $jacocoInit() {
                boolean[] zArr = $jacocoData;
                if (zArr != null) {
                    return zArr;
                }
                boolean[] a2 = Offline.a(-677018976667439847L, "com/taobao/weex/appfram/storage/WXStorageModule$5", 5);
                $jacocoData = a2;
                return a2;
            }

            {
                boolean[] $jacocoInit = $jacocoInit();
                this.this$0 = r2;
                $jacocoInit[0] = true;
            }

            public void onReceived(Map<String, Object> map) {
                boolean[] $jacocoInit = $jacocoInit();
                if (jSCallback == null) {
                    $jacocoInit[1] = true;
                } else {
                    $jacocoInit[2] = true;
                    jSCallback.invoke(map);
                    $jacocoInit[3] = true;
                }
                $jacocoInit[4] = true;
            }
        });
        $jacocoInit[24] = true;
    }

    @JSMethod(uiThread = false)
    public void setItemPersistent(String str, String str2, @Nullable final JSCallback jSCallback) {
        boolean[] $jacocoInit = $jacocoInit();
        if (TextUtils.isEmpty(str)) {
            $jacocoInit[25] = true;
        } else if (str2 == null) {
            $jacocoInit[26] = true;
        } else {
            IWXStorageAdapter ability = ability();
            if (ability == null) {
                $jacocoInit[28] = true;
                StorageResultHandler.handleNoHandlerError(jSCallback);
                $jacocoInit[29] = true;
                return;
            }
            ability.setItemPersistent(str, str2, new IWXStorageAdapter.OnResultReceivedListener(this) {
                private static transient /* synthetic */ boolean[] $jacocoData;
                final /* synthetic */ WXStorageModule this$0;

                private static /* synthetic */ boolean[] $jacocoInit() {
                    boolean[] zArr = $jacocoData;
                    if (zArr != null) {
                        return zArr;
                    }
                    boolean[] a2 = Offline.a(1245607954252828669L, "com/taobao/weex/appfram/storage/WXStorageModule$6", 5);
                    $jacocoData = a2;
                    return a2;
                }

                {
                    boolean[] $jacocoInit = $jacocoInit();
                    this.this$0 = r2;
                    $jacocoInit[0] = true;
                }

                public void onReceived(Map<String, Object> map) {
                    boolean[] $jacocoInit = $jacocoInit();
                    if (jSCallback == null) {
                        $jacocoInit[1] = true;
                    } else {
                        $jacocoInit[2] = true;
                        jSCallback.invoke(map);
                        $jacocoInit[3] = true;
                    }
                    $jacocoInit[4] = true;
                }
            });
            $jacocoInit[30] = true;
            return;
        }
        StorageResultHandler.handleInvalidParam(jSCallback);
        $jacocoInit[27] = true;
    }

    public void destroy() {
        boolean[] $jacocoInit = $jacocoInit();
        IWXStorageAdapter ability = ability();
        if (ability == null) {
            $jacocoInit[31] = true;
        } else {
            $jacocoInit[32] = true;
            ability.close();
            $jacocoInit[33] = true;
        }
        $jacocoInit[34] = true;
    }
}
