package org.cybergarage.upnp.xml;

import org.cybergarage.upnp.control.ActionListener;
import org.cybergarage.upnp.control.ControlResponse;

public class ActionData extends NodeData {
    private ActionListener actionListener = null;
    private ControlResponse ctrlRes = null;

    public ActionListener getActionListener() {
        return this.actionListener;
    }

    public void setActionListener(ActionListener actionListener2) {
        this.actionListener = actionListener2;
    }

    public ControlResponse getControlResponse() {
        return this.ctrlRes;
    }

    public void setControlResponse(ControlResponse controlResponse) {
        this.ctrlRes = controlResponse;
    }
}
