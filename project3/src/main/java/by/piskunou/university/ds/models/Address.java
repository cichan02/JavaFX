package by.piskunou.university.ds.models;

public class Address {
	private String country;
	protected String city;
	String street;
	public int house;
	public static long postcode = 678800L;

	public Address() {}

	public Address(String country, String city, String street, int house) {
		this.country = country;
		this.city = city;
		this.street = street;
		this.house = house;
	}

	@Override
	public String toString() {
		return "Country: " + country + "\nCity:" + city + "\nStreet:" + street + "\nHouse number: " + house;	
	}
}
