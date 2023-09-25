package ru.itmo.models;




import ru.itmo.utilsCommon.XMLLocalDateAdapter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@XmlRootElement(name = "labwork")
public class LabWork implements Serializable {

    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    @XmlJavaTypeAdapter(XMLLocalDateAdapter.class)

    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int minimalPoint; //Значение поля должно быть больше 0
    private Integer maximumPoint; //Поле не может быть null, Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author; //Поле может быть null

    public LabWork() {
    }
    public LabWork(LabWork labWork) {

        if (labWork != null) {


            if (labWork.creationDate != null) {
                this.creationDate = labWork.creationDate;
            }

            if (labWork.author != null) {
                this.author = new Person(labWork.author);
            }

            if (labWork.coordinates != null) {
                this.coordinates = new Coordinates(labWork.coordinates);
            }

            if (labWork.difficulty != null) {
                this.difficulty = labWork.difficulty;
            }
            if (labWork.maximumPoint != null) {
                this.maximumPoint = labWork.maximumPoint;

            }

            this.id = labWork.id;
            this.minimalPoint = labWork.minimalPoint;
            this.name = labWork.name;
        }

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        if (this.coordinates == null) {
            return null;
        }
        return new Coordinates(this.coordinates);
    }

    public LocalDate getDate() {
        return creationDate;
    }

    public int getMinimalPoint() {
        return minimalPoint;
    }

    public Integer getMaximumPoint() {
        return maximumPoint;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public Person getAuthor() {
        if (this.author == null) {
            return null;
        }
        return new Person(this.author);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean setName(String name) {

        if (name == null) {
            return false;
        }
        if (name.isBlank()) {
            return false;
        }
        this.name = name;
        return true;
    }

    public void setCoordinates(Coordinates coordinates)  {
        this.coordinates = new Coordinates(coordinates);
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public boolean setMinimalPoint(int minimalPoint) {
        if (minimalPoint <= 0) {
            return false;
        }
        this.minimalPoint = minimalPoint;
        return true;
    }

    public boolean setMaximumPoint(Integer maximumPoint) {
        if (maximumPoint == null) {
            return false;
        }

        if (maximumPoint <= 0) {
            return false;
        }

        this.maximumPoint = maximumPoint;
        return true;
    }
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setAuthor(Person author) {
        if (author == null) {
            this.author = null;
        } else {
            this.author = new Person(author);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LabWork labWork = (LabWork) o;
        return minimalPoint == labWork.minimalPoint && name.equals(labWork.name) && coordinates.equals(labWork.coordinates) && maximumPoint.equals(labWork.maximumPoint) && difficulty == labWork.difficulty && Objects.equals(author, labWork.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates, minimalPoint, maximumPoint, difficulty, author);
    }
}
