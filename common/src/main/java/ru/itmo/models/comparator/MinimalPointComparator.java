package ru.itmo.models.comparator;

import ru.itmo.models.LabWork;

import java.util.Comparator;

public class MinimalPointComparator implements Comparator<LabWork> {

    @Override
    public int compare(LabWork labWork, LabWork t1) {
        return Integer.compare(labWork.getMinimalPoint(), t1.getMinimalPoint());
    }
}
