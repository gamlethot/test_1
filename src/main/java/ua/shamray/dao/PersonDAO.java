package ua.shamray.dao;

import org.springframework.stereotype.Component;
import ua.shamray.models.Person;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private static int PERSON_COUNT;
    private final List<Person> personList = new ArrayList<>();

    {
        personList.add(new Person(++PERSON_COUNT, "LOL"));
        personList.add(new Person(++PERSON_COUNT, "KEK"));
        personList.add(new Person(++PERSON_COUNT, "SVIN"));
        personList.add(new Person(++PERSON_COUNT, "PIG"));
    }

    public List<Person> getAll(){
        return personList;
    }

    public Person getById(int id){
        return personList.stream().filter(person -> person.getId() == id).findAny().orElse(null);
    }

    public Person create(Person person){
        person.setId(++PERSON_COUNT);
        personList.add(person);
        return person;
    }

}
