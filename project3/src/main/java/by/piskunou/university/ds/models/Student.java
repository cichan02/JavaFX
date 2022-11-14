package by.piskunou.university.ds.models;

public class Student extends Person {
	@SuppressWarnings("unused")
	private final String university = "BSU";
	protected static int number = 8;
	
	public Student() {
		super();
	}
	
	public Student(String firstname, String surname, int age, Address address) {
		super(firstname, surname, age, address);
	}
}
