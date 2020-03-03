package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.EncodeStrategy;

public abstract class DiskCacheStrategy {

    /* renamed from: a  reason: collision with root package name */
    public static final DiskCacheStrategy f4865a = new DiskCacheStrategy() {
        public boolean a() {
            return true;
        }

        public boolean b() {
            return true;
        }

        public boolean a(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        public boolean a(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    };
    public static final DiskCacheStrategy b = new DiskCacheStrategy() {
        public boolean a() {
            return false;
        }

        public boolean a(DataSource dataSource) {
            return false;
        }

        public boolean a(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }

        public boolean b() {
            return false;
        }
    };
    public static final DiskCacheStrategy c = new DiskCacheStrategy() {
        public boolean a() {
            return false;
        }

        public boolean a(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return false;
        }

        public boolean b() {
            return true;
        }

        public boolean a(DataSource dataSource) {
            return (dataSource == DataSource.DATA_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    };
    public static final DiskCacheStrategy d = new DiskCacheStrategy() {
        public boolean a() {
            return true;
        }

        public boolean a(DataSource dataSource) {
            return false;
        }

        public boolean b() {
            return false;
        }

        public boolean a(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return (dataSource == DataSource.RESOURCE_DISK_CACHE || dataSource == DataSource.MEMORY_CACHE) ? false : true;
        }
    };
    public static final DiskCacheStrategy e = new DiskCacheStrategy() {
        public boolean a() {
            return true;
        }

        public boolean b() {
            return true;
        }

        public boolean a(DataSource dataSource) {
            return dataSource == DataSource.REMOTE;
        }

        public boolean a(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy) {
            return ((z && dataSource == DataSource.DATA_DISK_CACHE) || dataSource == DataSource.LOCAL) && encodeStrategy == EncodeStrategy.TRANSFORMED;
        }
    };

    public abstract boolean a();

    public abstract boolean a(DataSource dataSource);

    public abstract boolean a(boolean z, DataSource dataSource, EncodeStrategy encodeStrategy);

    public abstract boolean b();
}
