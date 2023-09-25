package ru.itmo.dao;




import ru.itmo.models.LabWork;
import ru.itmo.utils.GeneratorID;

import java.time.LocalDate;
import java.util.ArrayDeque;

public class LabWorkFileDAO implements DAO<LabWork> {

    private final ArrayDeque<LabWork> labWorkList;
    public LabWorkFileDAO() {
        this.labWorkList = new ArrayDeque<>();
    }

    public LabWorkFileDAO(ArrayDeque<LabWork> labWorkList){
        this.labWorkList = new ArrayDeque<LabWork>();
        if (labWorkList != null) {
            for (LabWork labWork : labWorkList) {
                this.labWorkList.addLast(new LabWork(labWork));
            }
        }

    }


    @Override
    public void create(LabWork labWork) {

        LabWork createLabWork = new LabWork(labWork);
        createLabWork.setCreationDate(LocalDate.now());
        createLabWork.setId(GeneratorID.newId());

        this.labWorkList.addLast(createLabWork);
    }

    @Override
    public void update(int id, LabWork labWork) {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {

                labWorkIter.setName(labWork.getName());
                labWorkIter.setAuthor(labWork.getAuthor());
                labWorkIter.setCoordinates(labWork.getCoordinates());
                labWorkIter.setDifficulty(labWork.getDifficulty());
                labWorkIter.setMinimalPoint(labWork.getMinimalPoint());
                labWorkIter.setMaximumPoint(labWork.getMaximumPoint());

                break;
            }
        }

    }

    @Override
    public void delete(int id) {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {

                this.labWorkList.remove(labWorkIter);
            }
        }
    }

    @Override
    public void delete(LabWork labWork) {
        delete(labWork.getId());
    }

    @Override
    public void clear() {
        this.labWorkList.clear();
        GeneratorID.setId(1);
    }

    @Override
    public LabWork get(int id) {
        for (LabWork labWorkIter : this.labWorkList) {
            if (labWorkIter.getId() == id) {
                return new LabWork(labWorkIter);
            }
        }
        return null;
    }

    @Override
    public LabWork getMin() {
        LabWork minLabWork = null;

        for (LabWork labWork : this.labWorkList) {
            if (minLabWork == null) {
                minLabWork = new LabWork(labWork);
            } else {
                if (minLabWork.getName().length() > labWork.getName().length()) {
                    minLabWork = new LabWork(labWork);
                }
            }
        }

        return minLabWork;

    }

    @Override
    public LabWork getFirst() {
        return new LabWork(this.labWorkList.getFirst());
    }

    @Override
    public ArrayDeque<LabWork> getAll() {
        ArrayDeque<LabWork> labWorkList = new ArrayDeque<>();
        for (LabWork labWork : this.labWorkList) {
            labWorkList.addLast(new LabWork(labWork));
        }
        return labWorkList;
    }


}
