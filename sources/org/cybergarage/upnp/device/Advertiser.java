package org.cybergarage.upnp.device;

import org.cybergarage.upnp.Device;
import org.cybergarage.util.ThreadCore;

public class Advertiser extends ThreadCore {
    private Device device;

    public Advertiser(Device device2) {
        setDevice(device2);
    }

    public void setDevice(Device device2) {
        this.device = device2;
    }

    public Device getDevice() {
        return this.device;
    }

    public void run() {
        Device device2 = getDevice();
        long leaseTime = (long) device2.getLeaseTime();
        while (isRunnable()) {
            double d = (double) ((float) leaseTime);
            Double.isNaN(d);
            try {
                Thread.sleep(((leaseTime / 4) + ((long) (d * Math.random() * 0.25d))) * 1000);
            } catch (InterruptedException unused) {
            }
            device2.announce();
        }
    }
}
