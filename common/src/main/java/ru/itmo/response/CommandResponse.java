package ru.itmo.response;

import java.io.Serializable;
import java.util.Map;

public class CommandResponse implements Serializable {

    private Object object;
    private ResponseType responseType;
    private Class typeOfObject;
    private Map<String, Object> parameters;


    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public Class getTypeOfObject() {
        return typeOfObject;
    }

    public void setTypeOfObject(Class typeOfObject) {
        this.typeOfObject = typeOfObject;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }

    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }
}
