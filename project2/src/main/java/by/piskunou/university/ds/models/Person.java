package by.piskunou.university.ds.models;

import java.io.Serializable;

import by.piskunou.university.ds.util.PeopleSequence;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
public class Person implements Serializable {
	private static final long serialVersionUID = 6626431735483299785L;
	
	@ToString.Exclude
	private long id;
	
	private String firstname;
	private String surname;
	
	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	private Address address;
	
	private Person(long id, String firstname, String surname, Address address) {
		this.id = id;
		this.firstname = firstname;
		this.surname = surname;
		this.address = address;
	}
	
	public static Person of(String firstname, String surname, Address address) {
		long id = PeopleSequence.getId();
		return new Person(id, firstname, surname, address);
	}
}
