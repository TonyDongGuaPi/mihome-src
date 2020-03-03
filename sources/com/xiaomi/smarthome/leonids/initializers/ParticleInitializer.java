package com.xiaomi.smarthome.leonids.initializers;

import com.xiaomi.smarthome.leonids.Particle;
import java.util.Random;

public interface ParticleInitializer {
    void a(Particle particle, Random random);
}
