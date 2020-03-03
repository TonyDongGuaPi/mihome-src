package pl.droidsonroids.relinker;

import android.content.Context;
import android.util.Log;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import pl.droidsonroids.relinker.ReLinker;
import pl.droidsonroids.relinker.elf.ElfParser;

public class ReLinkerInstance {
    private static final String g = "lib";

    /* renamed from: a  reason: collision with root package name */
    protected final Set<String> f11982a;
    protected final ReLinker.LibraryLoader b;
    protected final ReLinker.LibraryInstaller c;
    protected boolean d;
    protected boolean e;
    protected ReLinker.Logger f;

    protected ReLinkerInstance() {
        this(new SystemLibraryLoader(), new ApkLibraryInstaller());
    }

    protected ReLinkerInstance(ReLinker.LibraryLoader libraryLoader, ReLinker.LibraryInstaller libraryInstaller) {
        this.f11982a = new HashSet();
        if (libraryLoader == null) {
            throw new IllegalArgumentException("Cannot pass null library loader");
        } else if (libraryInstaller != null) {
            this.b = libraryLoader;
            this.c = libraryInstaller;
        } else {
            throw new IllegalArgumentException("Cannot pass null library installer");
        }
    }

    public ReLinkerInstance a(ReLinker.Logger logger) {
        this.f = logger;
        return this;
    }

    public ReLinkerInstance a() {
        this.d = true;
        return this;
    }

    public ReLinkerInstance b() {
        this.e = true;
        return this;
    }

    public void a(Context context, String str) {
        a(context, str, (String) null, (ReLinker.LoadListener) null);
    }

    public void a(Context context, String str, String str2) {
        a(context, str, str2, (ReLinker.LoadListener) null);
    }

    public void a(Context context, String str, ReLinker.LoadListener loadListener) {
        a(context, str, (String) null, loadListener);
    }

    public void a(Context context, String str, String str2, ReLinker.LoadListener loadListener) {
        if (context == null) {
            throw new IllegalArgumentException("Given context is null");
        } else if (!TextUtils.a(str)) {
            a("Beginning load of %s...", str);
            if (loadListener == null) {
                d(context, str, str2);
                return;
            }
            final Context context2 = context;
            final String str3 = str;
            final String str4 = str2;
            final ReLinker.LoadListener loadListener2 = loadListener;
            new Thread(new Runnable() {
                public void run() {
                    try {
                        ReLinkerInstance.this.d(context2, str3, str4);
                        loadListener2.a();
                    } catch (UnsatisfiedLinkError e2) {
                        loadListener2.a(e2);
                    } catch (MissingLibraryException e3) {
                        loadListener2.a(e3);
                    }
                }
            }).start();
        } else {
            throw new IllegalArgumentException("Given library is either null or empty");
        }
    }

    /* access modifiers changed from: private */
    public void d(Context context, String str, String str2) {
        ElfParser elfParser;
        ElfParser elfParser2;
        if (!this.f11982a.contains(str) || this.d) {
            try {
                this.b.a(str);
                this.f11982a.add(str);
                a("%s (%s) was loaded normally!", str, str2);
            } catch (UnsatisfiedLinkError e2) {
                a("Loading the library normally failed: %s", Log.getStackTraceString(e2));
                a("%s (%s) was not loaded normally, re-linking...", str, str2);
                File b2 = b(context, str, str2);
                if (!b2.exists() || this.d) {
                    if (this.d) {
                        a("Forcing a re-link of %s (%s)...", str, str2);
                    }
                    c(context, str, str2);
                    this.c.a(context, this.b.a(), this.b.c(str), b2, this);
                }
                try {
                    if (this.e) {
                        elfParser = null;
                        elfParser2 = new ElfParser(b2);
                        List<String> b3 = elfParser2.b();
                        elfParser2.close();
                        for (String d2 : b3) {
                            a(context, this.b.d(d2));
                        }
                    }
                } catch (IOException unused) {
                }
                this.b.b(b2.getAbsolutePath());
                this.f11982a.add(str);
                a("%s (%s) was re-linked!", str, str2);
            } catch (Throwable th) {
                th = th;
                elfParser = elfParser2;
                elfParser.close();
                throw th;
            }
        } else {
            a("%s already loaded previously!", str);
        }
    }

    /* access modifiers changed from: protected */
    public File a(Context context) {
        return context.getDir("lib", 0);
    }

    /* access modifiers changed from: protected */
    public File b(Context context, String str, String str2) {
        String c2 = this.b.c(str);
        if (TextUtils.a(str2)) {
            return new File(a(context), c2);
        }
        File a2 = a(context);
        return new File(a2, c2 + "." + str2);
    }

    /* access modifiers changed from: protected */
    public void c(Context context, String str, String str2) {
        File a2 = a(context);
        File b2 = b(context, str, str2);
        final String c2 = this.b.c(str);
        File[] listFiles = a2.listFiles(new FilenameFilter() {
            public boolean accept(File file, String str) {
                return str.startsWith(c2);
            }
        });
        if (listFiles != null) {
            for (File file : listFiles) {
                if (this.d || !file.getAbsolutePath().equals(b2.getAbsolutePath())) {
                    file.delete();
                }
            }
        }
    }

    public void a(String str, Object... objArr) {
        a(String.format(Locale.US, str, objArr));
    }

    public void a(String str) {
        if (this.f != null) {
            this.f.a(str);
        }
    }
}
