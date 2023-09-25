package ru.itmo.request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CommandRequest implements Serializable {

    private RequestType requestType;
    private Map<String, Object> parameters;
    private Object object;

    public RequestType getRequestType() {
        return requestType;
    }

    public void setRequestType(RequestType requestType) {
        this.requestType = requestType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Map<String, Object> getParameters() {
        if (parameters != null) {
            return new HashMap<>(this.parameters);
        }
        return null;
    }

    public void setParameters(Map<String, Object> parameters) {
        if (parameters != null) {
            this.parameters = new HashMap<>(parameters);
        }
    }
}
