package by.piskunou.university.ds.models;

import java.io.Serializable;

import by.piskunou.university.ds.util.PeopleSequence;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Person implements Serializable {
	private static final long serialVersionUID = 6626431735483299785L;
	
	private long id;	
	private String firstname;
	private String surname;
	
	@EqualsAndHashCode.Exclude
	private Address address;
	
	public static Person of(String firstname, String surname, Address address) {
		long id = PeopleSequence.getId();
		return new Person(id, firstname, surname, address);
	}

	@Override
	public String toString() {
		return firstname + " " + surname + " from " + address.getCountry();
	}
	
	
}
