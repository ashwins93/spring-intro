package io.github.ashwins93.demoproject.daos;

import io.github.ashwins93.demoproject.models.Person;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("fakeDao")
public class FakePersonDataAccessService implements PersonDao {
    private static List<Person> DB = new ArrayList<>();

    @Override
    public int insertPerson(UUID id, Person person) {
        DB.add(new Person(id, person.getName(), person.getAge()));
        return 1;
    }

    @Override
    public List<Person> getAllPeople() {
        return DB;
    }

    @Override
    public Optional<Person> getPersonById(UUID id) {
        return DB.stream().filter(person -> person.getId().equals(id)).findFirst();
    }

    @Override
    public int deletePersonById(UUID id) {
        Optional<Person> person1 = getPersonById(id);
        if (person1.isEmpty()) {
            return 0;
        }
        DB.remove(person1.get());
        return 1;
    }

    @Override
    public int updatePersonById(UUID id, Person person) {
        return getPersonById(id).map(person1 -> {
            int index = DB.indexOf(person1);
            if (index > -1) {
                DB.set(index, new Person(id, person.getName(), person.getAge()));
                return 1;
            }
            return 0;
        }).orElse(0);
    }

}
