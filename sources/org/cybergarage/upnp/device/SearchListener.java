package org.cybergarage.upnp.device;

import org.cybergarage.upnp.ssdp.SSDPPacket;

public interface SearchListener {
    void deviceSearchReceived(SSDPPacket sSDPPacket);
}
