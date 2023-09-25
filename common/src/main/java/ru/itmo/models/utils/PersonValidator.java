package ru.itmo.models.utils;


import ru.itmo.models.Person;

public class PersonValidator {

    public static boolean isValidPerson(Person person) {
        Person validPerson = new Person();

        return validPerson.setName(person.getName()) && validPerson.setHairColor(person.getHairColor()) &&
                LocationValidator.isValidLocation(person.getLocation()) && validPerson.setNationality(person.getNationality()) &&
                validPerson.setEyeColor(person.getEyeColor());
    }

}
