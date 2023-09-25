package ru.itmo.dataManager.response;


import ru.itmo.models.LabWork;

import java.util.ArrayDeque;

public class DataResponse {
    private DataResponseType responseType;
    private ArrayDeque<LabWork> labWorks;

    public DataResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(DataResponseType responseType) {
        this.responseType = responseType;
    }

    public ArrayDeque<LabWork> getLabWorks() {
        return labWorks;
    }

    public void setLabWorks(ArrayDeque<LabWork> labWorks) {
        this.labWorks = labWorks;
    }
}
