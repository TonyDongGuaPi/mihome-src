package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.collect.ImmutableMapEntry;
import java.util.Map;
import javax.annotation.Nullable;

@GwtCompatible(emulated = true, serializable = true)
final class RegularImmutableMap<K, V> extends ImmutableMap<K, V> {
    private static final double MAX_LOAD_FACTOR = 1.2d;
    private static final long serialVersionUID = 0;
    /* access modifiers changed from: private */
    public final transient ImmutableMapEntry<K, V>[] entries;
    private final transient int mask;
    private final transient ImmutableMapEntry<K, V>[] table;

    /* access modifiers changed from: package-private */
    public boolean isPartialView() {
        return false;
    }

    RegularImmutableMap(ImmutableMapEntry.TerminalEntry<?, ?>... terminalEntryArr) {
        this(terminalEntryArr.length, terminalEntryArr);
    }

    /* JADX WARNING: type inference failed for: r5v1, types: [com.google.common.collect.RegularImmutableMap$NonTerminalMapEntry] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    RegularImmutableMap(int r7, com.google.common.collect.ImmutableMapEntry.TerminalEntry<?, ?>[] r8) {
        /*
            r6 = this;
            r6.<init>()
            com.google.common.collect.ImmutableMapEntry[] r0 = r6.createEntryArray(r7)
            r6.entries = r0
            r0 = 4608083138725491507(0x3ff3333333333333, double:1.2)
            int r0 = com.google.common.collect.Hashing.closedTableSize(r7, r0)
            com.google.common.collect.ImmutableMapEntry[] r1 = r6.createEntryArray(r0)
            r6.table = r1
            int r0 = r0 + -1
            r6.mask = r0
            r0 = 0
        L_0x001d:
            if (r0 >= r7) goto L_0x004b
            r1 = r8[r0]
            java.lang.Object r2 = r1.getKey()
            int r3 = r2.hashCode()
            int r3 = com.google.common.collect.Hashing.smear(r3)
            int r4 = r6.mask
            r3 = r3 & r4
            com.google.common.collect.ImmutableMapEntry<K, V>[] r4 = r6.table
            r4 = r4[r3]
            if (r4 != 0) goto L_0x0037
            goto L_0x003d
        L_0x0037:
            com.google.common.collect.RegularImmutableMap$NonTerminalMapEntry r5 = new com.google.common.collect.RegularImmutableMap$NonTerminalMapEntry
            r5.<init>(r1, r4)
            r1 = r5
        L_0x003d:
            com.google.common.collect.ImmutableMapEntry<K, V>[] r5 = r6.table
            r5[r3] = r1
            com.google.common.collect.ImmutableMapEntry<K, V>[] r3 = r6.entries
            r3[r0] = r1
            r6.checkNoConflictInBucket(r2, r1, r4)
            int r0 = r0 + 1
            goto L_0x001d
        L_0x004b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.collect.RegularImmutableMap.<init>(int, com.google.common.collect.ImmutableMapEntry$TerminalEntry[]):void");
    }

    RegularImmutableMap(Map.Entry<?, ?>[] entryArr) {
        int length = entryArr.length;
        this.entries = createEntryArray(length);
        int closedTableSize = Hashing.closedTableSize(length, MAX_LOAD_FACTOR);
        this.table = createEntryArray(closedTableSize);
        this.mask = closedTableSize - 1;
        for (int i = 0; i < length; i++) {
            Map.Entry<?, ?> entry = entryArr[i];
            Object key = entry.getKey();
            Object value = entry.getValue();
            CollectPreconditions.checkEntryNotNull(key, value);
            int smear = Hashing.smear(key.hashCode()) & this.mask;
            ImmutableMapEntry<K, V> immutableMapEntry = this.table[smear];
            ImmutableMapEntry<K, V> terminalEntry = immutableMapEntry == null ? new ImmutableMapEntry.TerminalEntry<>(key, value) : new NonTerminalMapEntry<>(key, value, immutableMapEntry);
            this.table[smear] = terminalEntry;
            this.entries[i] = terminalEntry;
            checkNoConflictInBucket(key, terminalEntry, immutableMapEntry);
        }
    }

    private void checkNoConflictInBucket(K k, ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
        while (immutableMapEntry2 != null) {
            checkNoConflict(!k.equals(immutableMapEntry2.getKey()), "key", immutableMapEntry, immutableMapEntry2);
            immutableMapEntry2 = immutableMapEntry2.getNextInKeyBucket();
        }
    }

    private static final class NonTerminalMapEntry<K, V> extends ImmutableMapEntry<K, V> {
        private final ImmutableMapEntry<K, V> nextInKeyBucket;

        /* access modifiers changed from: package-private */
        @Nullable
        public ImmutableMapEntry<K, V> getNextInValueBucket() {
            return null;
        }

        NonTerminalMapEntry(K k, V v, ImmutableMapEntry<K, V> immutableMapEntry) {
            super(k, v);
            this.nextInKeyBucket = immutableMapEntry;
        }

        NonTerminalMapEntry(ImmutableMapEntry<K, V> immutableMapEntry, ImmutableMapEntry<K, V> immutableMapEntry2) {
            super(immutableMapEntry);
            this.nextInKeyBucket = immutableMapEntry2;
        }

        /* access modifiers changed from: package-private */
        public ImmutableMapEntry<K, V> getNextInKeyBucket() {
            return this.nextInKeyBucket;
        }
    }

    private ImmutableMapEntry<K, V>[] createEntryArray(int i) {
        return new ImmutableMapEntry[i];
    }

    public V get(@Nullable Object obj) {
        if (obj == null) {
            return null;
        }
        for (ImmutableMapEntry<K, V> immutableMapEntry = this.table[Hashing.smear(obj.hashCode()) & this.mask]; immutableMapEntry != null; immutableMapEntry = immutableMapEntry.getNextInKeyBucket()) {
            if (obj.equals(immutableMapEntry.getKey())) {
                return immutableMapEntry.getValue();
            }
        }
        return null;
    }

    public int size() {
        return this.entries.length;
    }

    /* access modifiers changed from: package-private */
    public ImmutableSet<Map.Entry<K, V>> createEntrySet() {
        return new EntrySet();
    }

    private class EntrySet extends ImmutableMapEntrySet<K, V> {
        private EntrySet() {
        }

        /* access modifiers changed from: package-private */
        public ImmutableMap<K, V> map() {
            return RegularImmutableMap.this;
        }

        public UnmodifiableIterator<Map.Entry<K, V>> iterator() {
            return asList().iterator();
        }

        /* access modifiers changed from: package-private */
        public ImmutableList<Map.Entry<K, V>> createAsList() {
            return new RegularImmutableAsList(this, (Object[]) RegularImmutableMap.this.entries);
        }
    }
}
