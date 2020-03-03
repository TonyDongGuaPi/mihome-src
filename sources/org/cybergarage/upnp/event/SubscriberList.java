package org.cybergarage.upnp.event;

import java.util.Vector;

public class SubscriberList extends Vector {
    public Subscriber getSubscriber(int i) {
        Object obj;
        try {
            obj = get(i);
        } catch (Exception unused) {
            obj = null;
        }
        return (Subscriber) obj;
    }
}
