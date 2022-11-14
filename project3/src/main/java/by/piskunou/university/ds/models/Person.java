package by.piskunou.university.ds.models;

public class Person {
	@SuppressWarnings("unused")
	private String firstname;
	protected String surname;
	int age;
	public Address address;
	
	public Person() {}
	
	public Person(String firstname, String surname, int age, Address address) {
		this.firstname = firstname;
		this.surname = surname;
		this.age = age;
		this.address = address;
	}
}
