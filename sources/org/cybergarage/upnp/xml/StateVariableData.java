package org.cybergarage.upnp.xml;

import org.cybergarage.upnp.control.QueryListener;
import org.cybergarage.upnp.control.QueryResponse;

public class StateVariableData extends NodeData {
    private QueryListener queryListener = null;
    private QueryResponse queryRes = null;
    private String value = "";

    public String getValue() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = str;
    }

    public QueryListener getQueryListener() {
        return this.queryListener;
    }

    public void setQueryListener(QueryListener queryListener2) {
        this.queryListener = queryListener2;
    }

    public QueryResponse getQueryResponse() {
        return this.queryRes;
    }

    public void setQueryResponse(QueryResponse queryResponse) {
        this.queryRes = queryResponse;
    }
}
