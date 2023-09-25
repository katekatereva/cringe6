package ru.itmo.models;


import ru.itmo.utilsCommon.XMLLocalDateAdapter;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Person implements Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    @XmlJavaTypeAdapter(XMLLocalDateAdapter.class)

    private LocalDate birthday; //Поле может быть null

    private ru.itmo.models.eye.Color eyeColor; //Поле не может быть null
    private ru.itmo.models.hair.Color hairColor; //Поле может быть null
    private Country nationality; //Поле может быть null
    private Location location; //Поле не может быть null

    public Person() {}
    public Person(Person person) {

        if (person != null) {
            this.birthday = person.birthday;

            this.eyeColor = person.eyeColor;

            this.hairColor = person.hairColor;

            this.name = person.name;

            this.nationality = person.nationality;

            this.location = new Location(person.location);
        }


    }

    public String getName() {
        return name;
    }

    public LocalDate getBirthdayPerson() {
        return birthday;
    }

    public ru.itmo.models.eye.Color getEyeColor() {
        return eyeColor;
    }

    public ru.itmo.models.hair.Color getHairColor() {
        return hairColor;
    }

    public Country getNationality() {
        return nationality;
    }

    public Location getLocation(){
        if (location == null) {
            return null;
        }
        return new Location(location);
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

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean setEyeColor(ru.itmo.models.eye.Color eyeColor) {
        if (eyeColor == null) {
            return false;
        }
        this.eyeColor = eyeColor;
        return true;
    }

    public boolean setHairColor(ru.itmo.models.hair.Color hairColor) {
        this.hairColor = hairColor;
        return true;
    }

    public boolean setNationality(Country nationality) {
        this.nationality = nationality;
        return true;
    }

    public boolean setLocation(Location location){
        if (location == null) {
            return false;
        }
        this.location = new Location(location);
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(birthday, person.birthday) && eyeColor == person.eyeColor && hairColor == person.hairColor && nationality == person.nationality && Objects.equals(location, person.location);
    }

}
