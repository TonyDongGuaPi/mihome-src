package com.mipay.core.internal;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Trace;
import android.text.TextUtils;
import com.mipay.core.internal.registry.ExtensionRegistry;
import com.mipay.core.internal.registry.ExtensionsParser;
import com.mipay.core.runtime.OSGiBundle;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class BundleManager {
    private static BundleManager sInstance;
    private final Context mContext;
    /* access modifiers changed from: private */
    public final ExtensionRegistry mExtensionRegistry = new ExtensionRegistry();
    private final ManifestProvider mManifestProvider;
    private final BundleRegistry mRegistry = new BundleRegistry();

    private BundleManager(Context context) {
        this.mContext = context.getApplicationContext();
        this.mManifestProvider = new BuiltinManifestProvider(this.mContext);
    }

    public static void init(Context context) {
        sInstance = new BundleManager(context);
        sInstance.initialize(context);
    }

    public static BundleManager get() {
        return sInstance;
    }

    private void initialize(Context context) {
        loadBuiltinBundles(context);
    }

    private void loadBuiltinBundles(Context context) {
        ArrayList arrayList = new ArrayList();
        installBuiltinBundles(context, arrayList);
        startBuiltinBundles(arrayList);
    }

    private void installBuiltinBundles(Context context, ArrayList<OSGiBundleImpl> arrayList) {
        ArrayList arrayList2 = new ArrayList();
        try {
            Scanner scanner = new Scanner(context.getAssets().open("manifest/bundles"));
            while (scanner.hasNext()) {
                arrayList2.add(scanner.next());
            }
            scanner.close();
        } catch (IOException unused) {
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            Trace.beginSection("Bundle.install " + str);
            OSGiBundleImpl installBundle = installBundle(str);
            if (installBundle != null && installBundle.getState() == OSGiBundle.STATE.RESOLVED) {
                arrayList.add(installBundle);
            }
            Trace.endSection();
        }
        startAsyncInitProcess(arrayList);
    }

    private void startAsyncInitProcess(List<OSGiBundleImpl> list) {
        new InitTask(list).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
    }

    private static class InitTask extends AsyncTask<Void, Void, Void> {
        private List<OSGiBundleImpl> mBundleList;

        InitTask(List<OSGiBundleImpl> list) {
            this.mBundleList = list;
        }

        /* access modifiers changed from: protected */
        public Void doInBackground(Void... voidArr) {
            Trace.beginSection("installAllBundleExtension");
            synchronized (BundleManager.get().mExtensionRegistry) {
                for (OSGiBundleImpl next : this.mBundleList) {
                    Trace.beginSection("installBundleExtension" + next.getPath());
                    BundleManager.get().installBundleExtension(next);
                    Trace.endSection();
                }
            }
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void installBundleExtension(OSGiBundleImpl oSGiBundleImpl) {
        try {
            InputStream openExtensionManifest = this.mManifestProvider.openExtensionManifest(oSGiBundleImpl.getPath());
            parseExtensionManifest(openExtensionManifest, oSGiBundleImpl);
            openExtensionManifest.close();
        } catch (IOException unused) {
        }
    }

    private void startBuiltinBundles(List<OSGiBundleImpl> list) {
        for (OSGiBundleImpl start : list) {
            start.start();
        }
    }

    private OSGiBundleImpl installBundle(String str) {
        OSGiBundleImpl oSGiBundleImpl = new OSGiBundleImpl(this, str);
        oSGiBundleImpl.setState(OSGiBundle.STATE.UNINSTALLED);
        try {
            InputStream openBundleManifest = this.mManifestProvider.openBundleManifest(str);
            parseBundleManifest(openBundleManifest, oSGiBundleImpl);
            openBundleManifest.close();
        } catch (IOException unused) {
        }
        if (!checkBundle(oSGiBundleImpl)) {
            return null;
        }
        this.mRegistry.addBundle(oSGiBundleImpl);
        oSGiBundleImpl.setState(OSGiBundle.STATE.RESOLVED);
        return oSGiBundleImpl;
    }

    private void parseBundleManifest(InputStream inputStream, OSGiBundleImpl oSGiBundleImpl) {
        if (inputStream != null) {
            new BundleParser().parse(inputStream, oSGiBundleImpl);
        }
    }

    private void parseExtensionManifest(InputStream inputStream, OSGiBundleImpl oSGiBundleImpl) {
        if (inputStream != null) {
            new ExtensionsParser(this.mExtensionRegistry, oSGiBundleImpl).parse(inputStream);
        }
    }

    private boolean checkBundle(OSGiBundleImpl oSGiBundleImpl) {
        if (TextUtils.isEmpty(oSGiBundleImpl.getName()) || TextUtils.isEmpty(oSGiBundleImpl.getSymbolicName()) || this.mRegistry.hasBundle(oSGiBundleImpl.getSymbolicName())) {
            return false;
        }
        return true;
    }

    public ExtensionRegistry getExtensionRegistry() {
        return this.mExtensionRegistry;
    }

    public Context getContext() {
        return this.mContext;
    }
}
