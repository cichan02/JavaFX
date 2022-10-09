package by.piskunou.university.ds.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.piskunou.university.ds.models.Address;
import by.piskunou.university.ds.models.Person;

public class PersonDAO {
	private List<Person> people = new ArrayList<>(4);
	{
		people.add(Person.of("", "", new Address("", "", "", 0)));
		people.add(Person.of("", "", new Address("", "", "", 0)));
		people.add(Person.of("", "", new Address("", "", "", 0)));
		people.add(Person.of("", "", new Address("", "", "", 0)));
	}

	public List<Person> findAll() {
		return people;
	}
	
	public Optional<Person> findById(long id) {
		return people.stream().filter(p -> p.getId() == id).findAny();
	}

	public void deleteById(long id) {
		people.removeIf(p -> p.getId() == id);
	}
}
