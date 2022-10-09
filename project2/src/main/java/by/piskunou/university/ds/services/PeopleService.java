package by.piskunou.university.ds.services;

import java.util.List;
import java.util.Optional;

import by.piskunou.university.ds.dao.PersonDAO;
import by.piskunou.university.ds.models.Person;

public class PeopleService {
	private final PersonDAO personDAO = new PersonDAO();

	public List<Person> findAll() {
		return personDAO.findAll();
	}
	
	public Person findById(long id) {
		Optional<Person> person = personDAO.findById(id);
		if(person.isEmpty()) {
			//past exception
		}
		return person.get();
	}

	public void deleteById(long id) {
		personDAO.deleteById(id);
	}
}
