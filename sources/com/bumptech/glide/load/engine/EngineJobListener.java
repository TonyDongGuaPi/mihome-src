package com.bumptech.glide.load.engine;

import com.bumptech.glide.load.Key;

interface EngineJobListener {
    void a(EngineJob<?> engineJob, Key key);

    void a(EngineJob<?> engineJob, Key key, EngineResource<?> engineResource);
}
