package by.piskunou.university.ds.dao;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import by.piskunou.university.ds.models.Address;
import by.piskunou.university.ds.models.Person;

public class PersonDAO {
	private static final List<Person> people = new LinkedList<>();
	static {
		people.add(Person.of("Peter", "Bennett", new Address("Great Britain", "West Kristina", "Jacobson Corner", 26)));
		people.add(Person.of("Gary", "Brooks", new Address("South Africa", "Kennethland", "Steuver Pass", 62)));
		people.add(Person.of("James", "Fine", new Address("Austria", "Linz", "Hainschwang", 5)));
		people.add(Person.of("Tom", "Lucky", new Address("Switzerland", "Saint-Gall", "ROwe Keys", 54)));
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

	public void update(Person personToBeUpdated, Person editPerson) {
		personToBeUpdated.setFirstname(editPerson.getFirstname());
		personToBeUpdated.setSurname(editPerson.getSurname());
		personToBeUpdated.setAddress(editPerson.getAddress());
	}

	public void save(Person newPerson) {
		people.add(newPerson);
	}

	public void setAll(Collection<? extends Person> newPeople) {
		people.clear();
		people.addAll(newPeople);
	}
}
