package by.piskunou.university.ds.services;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import by.piskunou.university.ds.dao.PersonDAO;
import by.piskunou.university.ds.models.Person;

import static java.util.stream.Collectors.toSet;

public class PeopleService {
	private final PersonDAO personDAO = new PersonDAO();

	public List<Person> findAll() {
		return personDAO.findAll();
	}
	
	public Person findById(long id) throws IllegalArgumentException{
		Optional<Person> person = personDAO.findById(id);
		if(person.isEmpty()) {
			throw new IllegalArgumentException("Illegal id. Person with such id doesn't exist");
		}
		return person.get();
	}

	public void deleteById(long id) {
		personDAO.deleteById(id);
	}

	public void update(Person editPerson) {
		Person personToBeUpdated = findById(editPerson.getId());
		personDAO.update(personToBeUpdated, editPerson);
	}

	public void save(Person newPerson) {
		personDAO.save(newPerson);
	}

	public void setAll(List<Person> people) {
		personDAO.setAll(people);
	}

	public Set<String> findCityAll() {
		List<Person> people = findAll();
		return people.stream().map(p -> p.getAddress().getCity()).collect(toSet());
	}

	public long findByCity(String currentCity) {
		return personDAO.findByCity(currentCity);
	}
}
