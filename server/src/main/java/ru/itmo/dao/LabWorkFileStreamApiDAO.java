package ru.itmo.dao;

import ru.itmo.models.LabWork;

import ru.itmo.utilsCommon.AbsOfCoordinates;
import ru.itmo.utils.GeneratorID;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.stream.Collectors;

public class LabWorkFileStreamApiDAO implements DAO<LabWork> {
    private final ArrayDeque<LabWork> labWorkList;

    public LabWorkFileStreamApiDAO() {
        this.labWorkList = new ArrayDeque<>();
    }

    public LabWorkFileStreamApiDAO(ArrayDeque<LabWork> labWorkList) {
        this.labWorkList = labWorkList != null ? labWorkList.stream()
                .map(LabWork::new)
                .collect(Collectors.toCollection(ArrayDeque::new)) : new ArrayDeque<>();
    }

    @Override
    public void create(LabWork labWork) {
        LabWork createLabWork = new LabWork(labWork);
        createLabWork.setCreationDate(LocalDate.now());
        createLabWork.setId(GeneratorID.newId());

        labWorkList.addLast(createLabWork);
    }

    @Override
    public void update(int id, LabWork labWork) {
        labWorkList.stream()
                .filter(lw -> lw.getId() == id)
                .findFirst()
                .ifPresent(lw -> {
                    lw.setName(labWork.getName());
                    lw.setAuthor(labWork.getAuthor());
                    lw.setCoordinates(labWork.getCoordinates());
                    lw.setDifficulty(labWork.getDifficulty());
                    lw.setMinimalPoint(labWork.getMinimalPoint());
                    lw.setMaximumPoint(labWork.getMaximumPoint());
                });
    }

    @Override
    public void delete(int id) {
        labWorkList.removeIf(lw -> lw.getId() == id);
    }

    @Override
    public void delete(LabWork labWork) {
        delete(labWork.getId());
    }

    @Override
    public void clear() {
        labWorkList.clear();
        GeneratorID.setId(1);
    }

    @Override
    public LabWork get(int id) {
        return labWorkList.stream()
                .filter(lw -> lw.getId() == id)
                .findFirst()
                .map(LabWork::new)
                .orElse(null);
    }

    @Override
    public LabWork getMin() {
        return labWorkList.stream()
                .min(Comparator.comparingInt(lw -> lw.getName().length()))
                .map(LabWork::new)
                .orElse(null);
    }

    @Override
    public LabWork getFirst() {
        return new LabWork(labWorkList.getFirst());
    }

    @Override
    public ArrayDeque<LabWork> getAll() {
        return labWorkList.stream()
                .sorted(Comparator.comparingLong(lw -> AbsOfCoordinates.getAbsOfLocation(lw.getCoordinates())))
                .map(LabWork::new)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }
}
