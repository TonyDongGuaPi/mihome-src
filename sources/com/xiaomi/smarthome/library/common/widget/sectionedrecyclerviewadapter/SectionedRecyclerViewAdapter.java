package com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.xiaomi.smarthome.library.common.widget.sectionedrecyclerviewadapter.Section;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    /* renamed from: a  reason: collision with root package name */
    public static final int f19067a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = 4;
    public static final int f = 5;
    private static final int j = 6;
    private LinkedHashMap<String, Section> g = new LinkedHashMap<>();
    private HashMap<String, Integer> h = new HashMap<>();
    private int i = 0;

    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i2) {
        RecyclerView.ViewHolder viewHolder = null;
        for (Map.Entry next : this.h.entrySet()) {
            if (i2 >= ((Integer) next.getValue()).intValue() && i2 < ((Integer) next.getValue()).intValue() + 6) {
                Section section = this.g.get(next.getKey());
                switch (i2 - ((Integer) next.getValue()).intValue()) {
                    case 0:
                        viewHolder = b(viewGroup, section);
                        break;
                    case 1:
                        viewHolder = c(viewGroup, section);
                        break;
                    case 2:
                        viewHolder = a(viewGroup, section);
                        break;
                    case 3:
                        viewHolder = d(viewGroup, section);
                        break;
                    case 4:
                        viewHolder = e(viewGroup, section);
                        break;
                    case 5:
                        viewHolder = f(viewGroup, section);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid viewType");
                }
            }
        }
        return viewHolder;
    }

    private RecyclerView.ViewHolder a(ViewGroup viewGroup, Section section) {
        return section.b(LayoutInflater.from(viewGroup.getContext()).inflate(section.h(), viewGroup, false));
    }

    private RecyclerView.ViewHolder b(ViewGroup viewGroup, Section section) {
        Integer f2 = section.f();
        if (f2 != null) {
            return section.a(LayoutInflater.from(viewGroup.getContext()).inflate(f2.intValue(), viewGroup, false));
        }
        throw new NullPointerException("Missing 'header' resource id");
    }

    private RecyclerView.ViewHolder c(ViewGroup viewGroup, Section section) {
        Integer g2 = section.g();
        if (g2 != null) {
            return section.c(LayoutInflater.from(viewGroup.getContext()).inflate(g2.intValue(), viewGroup, false));
        }
        throw new NullPointerException("Missing 'footer' resource id");
    }

    private RecyclerView.ViewHolder d(ViewGroup viewGroup, Section section) {
        Integer i2 = section.i();
        if (i2 != null) {
            return section.d(LayoutInflater.from(viewGroup.getContext()).inflate(i2.intValue(), viewGroup, false));
        }
        throw new NullPointerException("Missing 'loading state' resource id");
    }

    private RecyclerView.ViewHolder e(ViewGroup viewGroup, Section section) {
        Integer j2 = section.j();
        if (j2 != null) {
            return section.e(LayoutInflater.from(viewGroup.getContext()).inflate(j2.intValue(), viewGroup, false));
        }
        throw new NullPointerException("Missing 'failed state' resource id");
    }

    private RecyclerView.ViewHolder f(ViewGroup viewGroup, Section section) {
        Integer k = section.k();
        if (k != null) {
            return section.f(LayoutInflater.from(viewGroup.getContext()).inflate(k.intValue(), viewGroup, false));
        }
        throw new NullPointerException("Missing 'empty state' resource id");
    }

    public void a(String str, Section section) {
        this.g.put(str, section);
        this.h.put(str, Integer.valueOf(this.i));
        this.i += 6;
    }

    public String a(Section section) {
        String uuid = UUID.randomUUID().toString();
        a(uuid, section);
        return uuid;
    }

    public Section a(String str) {
        return this.g.get(str);
    }

    public void b(String str) {
        this.g.remove(str);
    }

    public void a() {
        this.g.clear();
    }

    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i2) {
        int i3;
        int i4 = 0;
        for (Map.Entry<String, Section> value : this.g.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.c()) {
                int l = section.l();
                if (i2 < i4 || i2 > (i4 + l) - 1) {
                    i4 += l;
                } else if (section.d() && i2 == i4) {
                    b(i2).a(viewHolder);
                    return;
                } else if (!section.e() || i2 != i3) {
                    b(i2).b(viewHolder, d(i2));
                    return;
                } else {
                    b(i2).b(viewHolder);
                    return;
                }
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int getItemCount() {
        int i2 = 0;
        for (Map.Entry<String, Section> value : this.g.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.c()) {
                i2 += section.l();
            }
        }
        return i2;
    }

    public int getItemViewType(int i2) {
        int i3;
        int i4 = 0;
        for (Map.Entry next : this.g.entrySet()) {
            Section section = (Section) next.getValue();
            if (section.c()) {
                int l = section.l();
                if (i2 < i4 || i2 > (i4 + l) - 1) {
                    i4 += l;
                } else {
                    int intValue = this.h.get(next.getKey()).intValue();
                    if (section.d() && i2 == i4) {
                        return intValue;
                    }
                    if (section.e() && i2 == i3) {
                        return intValue + 1;
                    }
                    switch (section.b()) {
                        case LOADED:
                            return intValue + 2;
                        case LOADING:
                            return intValue + 3;
                        case FAILED:
                            return intValue + 4;
                        case EMPTY:
                            return intValue + 5;
                        default:
                            throw new IllegalStateException("Invalid state");
                    }
                }
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int a(int i2) {
        return getItemViewType(i2) % 6;
    }

    public Section b(int i2) {
        int i3 = 0;
        for (Map.Entry<String, Section> value : this.g.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.c()) {
                int l = section.l();
                if (i2 >= i3 && i2 <= (i3 + l) - 1) {
                    return section;
                }
                i3 += l;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    @Deprecated
    public int c(int i2) {
        return d(i2);
    }

    public int d(int i2) {
        int i3 = 0;
        for (Map.Entry<String, Section> value : this.g.entrySet()) {
            Section section = (Section) value.getValue();
            if (section.c()) {
                int l = section.l();
                if (i2 >= i3 && i2 <= (i3 + l) - 1) {
                    return (i2 - i3) - (section.d() ? 1 : 0);
                }
                i3 += l;
            }
        }
        throw new IndexOutOfBoundsException("Invalid position");
    }

    public int c(String str) {
        return b(m(str));
    }

    public int b(Section section) {
        int i2 = 0;
        for (Map.Entry<String, Section> value : this.g.entrySet()) {
            Section section2 = (Section) value.getValue();
            if (section2.c()) {
                if (section2 == section) {
                    return i2;
                }
                i2 += section2.l();
            }
        }
        throw new IllegalArgumentException("Invalid section");
    }

    public LinkedHashMap<String, Section> b() {
        return this.g;
    }

    public int a(String str, int i2) {
        return a(m(str), i2);
    }

    public int a(Section section, int i2) {
        return b(section) + (section.d ? 1 : 0) + i2;
    }

    public int d(String str) {
        return c(m(str));
    }

    public int c(Section section) {
        if (section.d) {
            return b(section);
        }
        throw new IllegalStateException("Section doesn't have a header");
    }

    public int e(String str) {
        return d(m(str));
    }

    public int d(Section section) {
        if (section.e) {
            return (b(section) + section.l()) - 1;
        }
        throw new IllegalStateException("Section doesn't have a footer");
    }

    public void b(String str, int i2) {
        e(a(str, i2));
    }

    public void b(Section section, int i2) {
        e(a(section, i2));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void e(int i2) {
        super.notifyItemInserted(i2);
    }

    public void a(String str, int i2, int i3) {
        a(a(str, i2), i3);
    }

    public void a(Section section, int i2, int i3) {
        a(a(section, i2), i3);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(int i2, int i3) {
        super.notifyItemRangeInserted(i2, i3);
    }

    public void c(String str, int i2) {
        f(a(str, i2));
    }

    public void c(Section section, int i2) {
        f(a(section, i2));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void f(int i2) {
        super.notifyItemRemoved(i2);
    }

    public void b(String str, int i2, int i3) {
        b(a(str, i2), i3);
    }

    public void b(Section section, int i2, int i3) {
        b(a(section, i2), i3);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void b(int i2, int i3) {
        super.notifyItemRangeRemoved(i2, i3);
    }

    public void d(String str, int i2) {
        g(a(str, i2));
    }

    public void d(Section section, int i2) {
        g(a(section, i2));
    }

    public void f(String str) {
        e(m(str));
    }

    public void e(Section section) {
        g(c(section));
    }

    public void g(String str) {
        f(m(str));
    }

    public void f(Section section) {
        g(d(section));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void g(int i2) {
        super.notifyItemChanged(i2);
    }

    public void c(String str, int i2, int i3) {
        c(a(str, i2), i3);
    }

    public void c(Section section, int i2, int i3) {
        c(a(section, i2), i3);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void c(int i2, int i3) {
        super.notifyItemRangeChanged(i2, i3);
    }

    public void a(String str, int i2, int i3, Object obj) {
        a(a(str, i2), i3, obj);
    }

    public void a(Section section, int i2, int i3, Object obj) {
        a(a(section, i2), i3, obj);
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void a(int i2, int i3, Object obj) {
        super.notifyItemRangeChanged(i2, i3, obj);
    }

    public void d(String str, int i2, int i3) {
        d(a(str, i2), a(str, i3));
    }

    public void d(Section section, int i2, int i3) {
        d(a(section, i2), a(section, i3));
    }

    /* access modifiers changed from: package-private */
    @VisibleForTesting
    public void d(int i2, int i3) {
        super.notifyItemMoved(i2, i3);
    }

    public void a(String str, Section.State state) {
        a(m(str), state);
    }

    public void a(Section section, Section.State state) {
        Section.State b2 = section.b();
        if (b2 == state) {
            throw new IllegalStateException("No state changed");
        } else if (state == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedFromLoaded");
        } else if (b2 != Section.State.LOADED) {
            d(section, 0);
        } else {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        }
    }

    public void b(String str, Section.State state) {
        b(m(str), state);
    }

    public void b(Section section, Section.State state) {
        Section.State b2 = section.b();
        if (b2 == state) {
            throw new IllegalStateException("No state changed");
        } else if (b2 == Section.State.LOADED) {
            int a2 = section.a();
            if (a2 == 0) {
                c(section, 0);
                return;
            }
            d(section, 0);
            if (a2 > 1) {
                a(section, 1, a2 - 1);
            }
        } else if (state == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedFromLoaded");
        } else {
            throw new IllegalStateException("Use notifyNotLoadedStateChanged");
        }
    }

    public void e(String str, int i2) {
        e(m(str), i2);
    }

    public void e(Section section, int i2) {
        if (section.b() == Section.State.LOADED) {
            throw new IllegalStateException("Use notifyStateChangedToLoaded");
        } else if (i2 == 0) {
            b(section, 0);
        } else {
            if (i2 > 1) {
                b(section, 1, i2 - 1);
            }
            d(section, 0);
        }
    }

    public void h(String str) {
        g(m(str));
    }

    public void g(Section section) {
        e(c(section));
    }

    public void i(String str) {
        h(m(str));
    }

    public void h(Section section) {
        e(d(section));
    }

    public void j(String str) {
        i(m(str));
    }

    public void i(Section section) {
        f(b(section));
    }

    public void k(String str) {
        j(m(str));
    }

    public void j(Section section) {
        f(b(section) + section.l());
    }

    public void l(String str) {
        k(m(str));
    }

    public void k(Section section) {
        if (section.c()) {
            a(b(section), section.l());
            return;
        }
        throw new IllegalStateException("This section is not visible.");
    }

    public void f(String str, int i2) {
        f(m(str), i2);
    }

    public void f(Section section, int i2) {
        if (!section.c()) {
            b(i2, section.l());
            return;
        }
        throw new IllegalStateException("This section is not visible.");
    }

    @NonNull
    private Section m(String str) {
        Section a2 = a(str);
        if (a2 != null) {
            return a2;
        }
        throw new IllegalArgumentException("Invalid tag: " + str);
    }

    public static class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View view) {
            super(view);
        }
    }
}
