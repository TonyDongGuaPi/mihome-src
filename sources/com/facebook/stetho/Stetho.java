package com.facebook.stetho;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import com.facebook.stetho.common.LogUtil;
import com.facebook.stetho.common.Util;
import com.facebook.stetho.dumpapp.DumpappHttpSocketLikeHandler;
import com.facebook.stetho.dumpapp.DumpappSocketLikeHandler;
import com.facebook.stetho.dumpapp.Dumper;
import com.facebook.stetho.dumpapp.DumperPlugin;
import com.facebook.stetho.dumpapp.plugins.CrashDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.FilesDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.HprofDumperPlugin;
import com.facebook.stetho.dumpapp.plugins.SharedPreferencesDumperPlugin;
import com.facebook.stetho.inspector.DevtoolsSocketHandler;
import com.facebook.stetho.inspector.console.RuntimeReplFactory;
import com.facebook.stetho.inspector.database.DatabaseDriver2Adapter;
import com.facebook.stetho.inspector.database.DatabaseFilesProvider;
import com.facebook.stetho.inspector.database.DefaultDatabaseConnectionProvider;
import com.facebook.stetho.inspector.database.DefaultDatabaseFilesProvider;
import com.facebook.stetho.inspector.database.SqliteDatabaseDriver;
import com.facebook.stetho.inspector.elements.Document;
import com.facebook.stetho.inspector.elements.DocumentProviderFactory;
import com.facebook.stetho.inspector.elements.android.ActivityTracker;
import com.facebook.stetho.inspector.elements.android.AndroidDocumentProviderFactory;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.module.CSS;
import com.facebook.stetho.inspector.protocol.module.Console;
import com.facebook.stetho.inspector.protocol.module.DOM;
import com.facebook.stetho.inspector.protocol.module.DOMStorage;
import com.facebook.stetho.inspector.protocol.module.Database;
import com.facebook.stetho.inspector.protocol.module.DatabaseDriver2;
import com.facebook.stetho.inspector.protocol.module.Debugger;
import com.facebook.stetho.inspector.protocol.module.HeapProfiler;
import com.facebook.stetho.inspector.protocol.module.Inspector;
import com.facebook.stetho.inspector.protocol.module.Network;
import com.facebook.stetho.inspector.protocol.module.Page;
import com.facebook.stetho.inspector.protocol.module.Profiler;
import com.facebook.stetho.inspector.protocol.module.Runtime;
import com.facebook.stetho.inspector.protocol.module.Worker;
import com.facebook.stetho.inspector.runtime.RhinoDetectingRuntimeReplFactory;
import com.facebook.stetho.server.AddressNameHelper;
import com.facebook.stetho.server.LazySocketHandler;
import com.facebook.stetho.server.LocalSocketServer;
import com.facebook.stetho.server.ProtocolDetectingSocketHandler;
import com.facebook.stetho.server.ServerManager;
import com.facebook.stetho.server.SocketHandler;
import com.facebook.stetho.server.SocketHandlerFactory;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nullable;

public class Stetho {
    private Stetho() {
    }

    public static InitializerBuilder newInitializerBuilder(Context context) {
        return new InitializerBuilder(context);
    }

    public static void initializeWithDefaults(final Context context) {
        initialize(new Initializer(context) {
            /* access modifiers changed from: protected */
            public Iterable<DumperPlugin> getDumperPlugins() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }

            /* access modifiers changed from: protected */
            public Iterable<ChromeDevtoolsDomain> getInspectorModules() {
                return new DefaultInspectorModulesBuilder(context).finish();
            }
        });
    }

    public static void initialize(Initializer initializer) {
        if (!ActivityTracker.get().beginTrackingIfPossible((Application) initializer.mContext.getApplicationContext())) {
            LogUtil.w("Automatic activity tracking not available on this API level, caller must invoke ActivityTracker methods manually!");
        }
        initializer.start();
    }

    public static DumperPluginsProvider defaultDumperPluginsProvider(final Context context) {
        return new DumperPluginsProvider() {
            public Iterable<DumperPlugin> get() {
                return new DefaultDumperPluginsBuilder(context).finish();
            }
        };
    }

    public static InspectorModulesProvider defaultInspectorModulesProvider(final Context context) {
        return new InspectorModulesProvider() {
            public Iterable<ChromeDevtoolsDomain> get() {
                return new DefaultInspectorModulesBuilder(context).finish();
            }
        };
    }

    private static class PluginBuilder<T> {
        private boolean mFinished;
        private final ArrayList<T> mPlugins;
        private final Set<String> mProvidedNames;
        private final Set<String> mRemovedNames;

        private PluginBuilder() {
            this.mProvidedNames = new HashSet();
            this.mRemovedNames = new HashSet();
            this.mPlugins = new ArrayList<>();
        }

        public void provide(String str, T t) {
            throwIfFinished();
            this.mPlugins.add(t);
            this.mProvidedNames.add(str);
        }

        public void provideIfDesired(String str, T t) {
            throwIfFinished();
            if (!this.mRemovedNames.contains(str) && this.mProvidedNames.add(str)) {
                this.mPlugins.add(t);
            }
        }

        public void remove(String str) {
            throwIfFinished();
            this.mRemovedNames.remove(str);
        }

        private void throwIfFinished() {
            if (this.mFinished) {
                throw new IllegalStateException("Must not continue to build after finish()");
            }
        }

        public Iterable<T> finish() {
            this.mFinished = true;
            return this.mPlugins;
        }
    }

    public static final class DefaultDumperPluginsBuilder {
        private final Context mContext;
        private final PluginBuilder<DumperPlugin> mDelegate = new PluginBuilder<>();

        public DefaultDumperPluginsBuilder(Context context) {
            this.mContext = context;
        }

        public DefaultDumperPluginsBuilder provide(DumperPlugin dumperPlugin) {
            this.mDelegate.provide(dumperPlugin.getName(), dumperPlugin);
            return this;
        }

        private DefaultDumperPluginsBuilder provideIfDesired(DumperPlugin dumperPlugin) {
            this.mDelegate.provideIfDesired(dumperPlugin.getName(), dumperPlugin);
            return this;
        }

        public DefaultDumperPluginsBuilder remove(String str) {
            this.mDelegate.remove(str);
            return this;
        }

        public Iterable<DumperPlugin> finish() {
            provideIfDesired(new HprofDumperPlugin(this.mContext));
            provideIfDesired(new SharedPreferencesDumperPlugin(this.mContext));
            provideIfDesired(new CrashDumperPlugin());
            provideIfDesired(new FilesDumperPlugin(this.mContext));
            return this.mDelegate.finish();
        }
    }

    public static final class DefaultInspectorModulesBuilder {
        private final Application mContext;
        @Nullable
        private List<DatabaseDriver2> mDatabaseDrivers;
        @Nullable
        private DatabaseFilesProvider mDatabaseFilesProvider;
        private final PluginBuilder<ChromeDevtoolsDomain> mDelegate = new PluginBuilder<>();
        @Nullable
        private DocumentProviderFactory mDocumentProvider;
        private boolean mExcludeSqliteDatabaseDriver;
        @Nullable
        private RuntimeReplFactory mRuntimeRepl;

        public DefaultInspectorModulesBuilder(Context context) {
            this.mContext = (Application) context.getApplicationContext();
        }

        public DefaultInspectorModulesBuilder documentProvider(DocumentProviderFactory documentProviderFactory) {
            this.mDocumentProvider = documentProviderFactory;
            return this;
        }

        public DefaultInspectorModulesBuilder runtimeRepl(RuntimeReplFactory runtimeReplFactory) {
            this.mRuntimeRepl = runtimeReplFactory;
            return this;
        }

        @Deprecated
        public DefaultInspectorModulesBuilder databaseFiles(DatabaseFilesProvider databaseFilesProvider) {
            this.mDatabaseFilesProvider = databaseFilesProvider;
            return this;
        }

        @Deprecated
        public DefaultInspectorModulesBuilder provideDatabaseDriver(Database.DatabaseDriver databaseDriver) {
            provideDatabaseDriver((DatabaseDriver2) new DatabaseDriver2Adapter(databaseDriver));
            return this;
        }

        public DefaultInspectorModulesBuilder provideDatabaseDriver(DatabaseDriver2 databaseDriver2) {
            if (this.mDatabaseDrivers == null) {
                this.mDatabaseDrivers = new ArrayList();
            }
            this.mDatabaseDrivers.add(databaseDriver2);
            return this;
        }

        public DefaultInspectorModulesBuilder excludeSqliteDatabaseDriver(boolean z) {
            this.mExcludeSqliteDatabaseDriver = z;
            return this;
        }

        @Deprecated
        public DefaultInspectorModulesBuilder provide(ChromeDevtoolsDomain chromeDevtoolsDomain) {
            this.mDelegate.provide(chromeDevtoolsDomain.getClass().getName(), chromeDevtoolsDomain);
            return this;
        }

        private DefaultInspectorModulesBuilder provideIfDesired(ChromeDevtoolsDomain chromeDevtoolsDomain) {
            this.mDelegate.provideIfDesired(chromeDevtoolsDomain.getClass().getName(), chromeDevtoolsDomain);
            return this;
        }

        @Deprecated
        public DefaultInspectorModulesBuilder remove(String str) {
            this.mDelegate.remove(str);
            return this;
        }

        public Iterable<ChromeDevtoolsDomain> finish() {
            provideIfDesired(new Console());
            provideIfDesired(new Debugger());
            DocumentProviderFactory resolveDocumentProvider = resolveDocumentProvider();
            if (resolveDocumentProvider != null) {
                Document document = new Document(resolveDocumentProvider);
                provideIfDesired(new DOM(document));
                provideIfDesired(new CSS(document));
            }
            provideIfDesired(new DOMStorage(this.mContext));
            provideIfDesired(new HeapProfiler());
            provideIfDesired(new Inspector());
            provideIfDesired(new Network(this.mContext));
            provideIfDesired(new Page(this.mContext));
            provideIfDesired(new Profiler());
            provideIfDesired(new Runtime(this.mRuntimeRepl != null ? this.mRuntimeRepl : new RhinoDetectingRuntimeReplFactory(this.mContext)));
            provideIfDesired(new Worker());
            if (Build.VERSION.SDK_INT >= 11) {
                Database database = new Database();
                boolean z = false;
                if (this.mDatabaseDrivers != null) {
                    for (DatabaseDriver2 next : this.mDatabaseDrivers) {
                        database.add(next);
                        if (next instanceof SqliteDatabaseDriver) {
                            z = true;
                        }
                    }
                }
                if (!z && !this.mExcludeSqliteDatabaseDriver) {
                    database.add(new SqliteDatabaseDriver(this.mContext, this.mDatabaseFilesProvider != null ? this.mDatabaseFilesProvider : new DefaultDatabaseFilesProvider(this.mContext), new DefaultDatabaseConnectionProvider()));
                }
                provideIfDesired(database);
            }
            return this.mDelegate.finish();
        }

        @Nullable
        private DocumentProviderFactory resolveDocumentProvider() {
            if (this.mDocumentProvider != null) {
                return this.mDocumentProvider;
            }
            if (Build.VERSION.SDK_INT >= 14) {
                return new AndroidDocumentProviderFactory(this.mContext, Collections.emptyList());
            }
            return null;
        }
    }

    public static abstract class Initializer {
        /* access modifiers changed from: private */
        public final Context mContext;

        /* access modifiers changed from: protected */
        @Nullable
        public abstract Iterable<DumperPlugin> getDumperPlugins();

        /* access modifiers changed from: protected */
        @Nullable
        public abstract Iterable<ChromeDevtoolsDomain> getInspectorModules();

        protected Initializer(Context context) {
            this.mContext = context.getApplicationContext();
        }

        /* access modifiers changed from: package-private */
        public final void start() {
            new ServerManager(new LocalSocketServer("main", AddressNameHelper.createCustomAddress("_devtools_remote"), new LazySocketHandler(new RealSocketHandlerFactory()))).start();
        }

        private class RealSocketHandlerFactory implements SocketHandlerFactory {
            private RealSocketHandlerFactory() {
            }

            public SocketHandler create() {
                ProtocolDetectingSocketHandler protocolDetectingSocketHandler = new ProtocolDetectingSocketHandler(Initializer.this.mContext);
                Iterable<DumperPlugin> dumperPlugins = Initializer.this.getDumperPlugins();
                if (dumperPlugins != null) {
                    Dumper dumper = new Dumper(dumperPlugins);
                    protocolDetectingSocketHandler.addHandler(new ProtocolDetectingSocketHandler.ExactMagicMatcher(DumpappSocketLikeHandler.PROTOCOL_MAGIC), new DumpappSocketLikeHandler(dumper));
                    DumpappHttpSocketLikeHandler dumpappHttpSocketLikeHandler = new DumpappHttpSocketLikeHandler(dumper);
                    protocolDetectingSocketHandler.addHandler(new ProtocolDetectingSocketHandler.ExactMagicMatcher("GET /dumpapp".getBytes()), dumpappHttpSocketLikeHandler);
                    protocolDetectingSocketHandler.addHandler(new ProtocolDetectingSocketHandler.ExactMagicMatcher("POST /dumpapp".getBytes()), dumpappHttpSocketLikeHandler);
                }
                Iterable<ChromeDevtoolsDomain> inspectorModules = Initializer.this.getInspectorModules();
                if (inspectorModules != null) {
                    protocolDetectingSocketHandler.addHandler(new ProtocolDetectingSocketHandler.AlwaysMatchMatcher(), new DevtoolsSocketHandler(Initializer.this.mContext, inspectorModules));
                }
                return protocolDetectingSocketHandler;
            }
        }
    }

    public static class InitializerBuilder {
        final Context mContext;
        @Nullable
        DumperPluginsProvider mDumperPlugins;
        @Nullable
        InspectorModulesProvider mInspectorModules;

        private InitializerBuilder(Context context) {
            this.mContext = context.getApplicationContext();
        }

        public InitializerBuilder enableDumpapp(DumperPluginsProvider dumperPluginsProvider) {
            this.mDumperPlugins = (DumperPluginsProvider) Util.throwIfNull(dumperPluginsProvider);
            return this;
        }

        public InitializerBuilder enableWebKitInspector(InspectorModulesProvider inspectorModulesProvider) {
            this.mInspectorModules = inspectorModulesProvider;
            return this;
        }

        public Initializer build() {
            return new BuilderBasedInitializer(this);
        }
    }

    private static class BuilderBasedInitializer extends Initializer {
        @Nullable
        private final DumperPluginsProvider mDumperPlugins;
        @Nullable
        private final InspectorModulesProvider mInspectorModules;

        private BuilderBasedInitializer(InitializerBuilder initializerBuilder) {
            super(initializerBuilder.mContext);
            this.mDumperPlugins = initializerBuilder.mDumperPlugins;
            this.mInspectorModules = initializerBuilder.mInspectorModules;
        }

        /* access modifiers changed from: protected */
        @Nullable
        public Iterable<DumperPlugin> getDumperPlugins() {
            if (this.mDumperPlugins != null) {
                return this.mDumperPlugins.get();
            }
            return null;
        }

        /* access modifiers changed from: protected */
        @Nullable
        public Iterable<ChromeDevtoolsDomain> getInspectorModules() {
            if (this.mInspectorModules != null) {
                return this.mInspectorModules.get();
            }
            return null;
        }
    }
}
