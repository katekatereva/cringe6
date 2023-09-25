package ru.itmo.dataManager.models;



import ru.itmo.models.LabWork;
import ru.itmo.utilsCommon.XMLLocalDateAdapter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import java.util.ArrayDeque;

@XmlRootElement(name = "labworks")
public class LabWorkCollection {
    private ArrayDeque<LabWork> labWorkList;
    private LocalDate dateInitial;

    @XmlElement(name = "labwork")
    public ArrayDeque<LabWork> getLabWorks() {
        return labWorkList;
    }

    @XmlElement(name = "dateInitial")
    @XmlJavaTypeAdapter(XMLLocalDateAdapter.class)
    public LocalDate getDateInitial() {
        return dateInitial;
    }

    public void setDateInitial(LocalDate dateInitial) {
        this.dateInitial = dateInitial;
    }

    public void setLabWorks(ArrayDeque<LabWork> labWorkList) {
        this.labWorkList = labWorkList;
    }
}
