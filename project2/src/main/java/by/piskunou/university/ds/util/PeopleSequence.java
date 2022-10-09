package by.piskunou.university.ds.util;

public class PeopleSequence {
	private static long seq;
	
	private PeopleSequence() {
		throw new IllegalStateException("Utility class");
	}
	
	public static long getId() {
		return ++seq;
	}
}
