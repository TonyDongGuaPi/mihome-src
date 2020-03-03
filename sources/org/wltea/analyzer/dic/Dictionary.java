package org.wltea.analyzer.dic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Collection;
import java.util.List;
import org.wltea.analyzer.cfg.Configuration;

public class Dictionary {

    /* renamed from: a  reason: collision with root package name */
    private static Dictionary f4205a;
    private DictSegment b;
    private DictSegment c;
    private DictSegment d;
    private Configuration e;

    private Dictionary(Configuration configuration) {
        this.e = configuration;
        b();
        d();
        e();
    }

    public static Dictionary a(Configuration configuration) {
        if (f4205a == null) {
            synchronized (Dictionary.class) {
                if (f4205a == null) {
                    f4205a = new Dictionary(configuration);
                    Dictionary dictionary = f4205a;
                    return dictionary;
                }
            }
        }
        return f4205a;
    }

    public static Dictionary a() {
        if (f4205a != null) {
            return f4205a;
        }
        throw new IllegalStateException("词典尚未初始化，请先调用initial方法");
    }

    public void a(Collection<String> collection) {
        if (collection != null) {
            for (String next : collection) {
                if (next != null) {
                    f4205a.b.b(next.trim().toLowerCase().toCharArray());
                }
            }
        }
    }

    public void b(Collection<String> collection) {
        if (collection != null) {
            for (String next : collection) {
                if (next != null) {
                    f4205a.b.c(next.trim().toLowerCase().toCharArray());
                }
            }
        }
    }

    public Hit a(char[] cArr) {
        return f4205a.b.a(cArr);
    }

    public Hit a(char[] cArr, int i, int i2) {
        return f4205a.b.a(cArr, i, i2);
    }

    public Hit b(char[] cArr, int i, int i2) {
        return f4205a.d.a(cArr, i, i2);
    }

    public Hit a(char[] cArr, int i, Hit hit) {
        return hit.g().a(cArr, i, 1, hit);
    }

    public boolean c(char[] cArr, int i, int i2) {
        return f4205a.c.a(cArr, i, i2).a();
    }

    private void b() {
        String readLine;
        this.b = new DictSegment(0);
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(this.e.b());
        if (resourceAsStream != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"), 512);
                do {
                    readLine = bufferedReader.readLine();
                    if (readLine != null && !"".equals(readLine.trim())) {
                        this.b.b(readLine.trim().toLowerCase().toCharArray());
                        continue;
                    }
                } while (readLine != null);
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                System.err.println("Main Dictionary loading exception.");
                e3.printStackTrace();
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            } catch (Throwable th) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
            c();
            return;
        }
        throw new RuntimeException("Main Dictionary not found!!!");
    }

    private void c() {
        String readLine;
        List<String> d2 = this.e.d();
        if (d2 != null) {
            for (String next : d2) {
                PrintStream printStream = System.out;
                printStream.println("加载扩展词典：" + next);
                InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(next);
                if (resourceAsStream != null) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"), 512);
                        do {
                            readLine = bufferedReader.readLine();
                            if (readLine != null && !"".equals(readLine.trim())) {
                                this.b.b(readLine.trim().toLowerCase().toCharArray());
                                continue;
                            }
                        } while (readLine != null);
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    } catch (IOException e3) {
                        System.err.println("Extension Dictionary loading exception.");
                        e3.printStackTrace();
                        if (resourceAsStream != null) {
                            resourceAsStream.close();
                        }
                    } catch (Throwable th) {
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (IOException e4) {
                                e4.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
        }
    }

    private void d() {
        String readLine;
        this.c = new DictSegment(0);
        List<String> e2 = this.e.e();
        if (e2 != null) {
            for (String next : e2) {
                PrintStream printStream = System.out;
                printStream.println("加载扩展停止词典：" + next);
                InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(next);
                if (resourceAsStream != null) {
                    try {
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"), 512);
                        do {
                            readLine = bufferedReader.readLine();
                            if (readLine != null && !"".equals(readLine.trim())) {
                                this.c.b(readLine.trim().toLowerCase().toCharArray());
                                continue;
                            }
                        } while (readLine != null);
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    } catch (IOException e4) {
                        System.err.println("Extension Stop word Dictionary loading exception.");
                        e4.printStackTrace();
                        if (resourceAsStream != null) {
                            resourceAsStream.close();
                        }
                    } catch (Throwable th) {
                        if (resourceAsStream != null) {
                            try {
                                resourceAsStream.close();
                            } catch (IOException e5) {
                                e5.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
            }
        }
    }

    private void e() {
        String readLine;
        this.d = new DictSegment(0);
        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream(this.e.c());
        if (resourceAsStream != null) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, "UTF-8"), 512);
                do {
                    readLine = bufferedReader.readLine();
                    if (readLine != null && !"".equals(readLine.trim())) {
                        this.d.b(readLine.trim().toLowerCase().toCharArray());
                        continue;
                    }
                } while (readLine != null);
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            } catch (IOException e3) {
                System.err.println("Quantifier Dictionary loading exception.");
                e3.printStackTrace();
                if (resourceAsStream != null) {
                    resourceAsStream.close();
                }
            } catch (Throwable th) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
                throw th;
            }
        } else {
            throw new RuntimeException("Quantifier Dictionary not found!!!");
        }
    }
}
