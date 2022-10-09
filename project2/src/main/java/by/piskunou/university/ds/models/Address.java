package by.piskunou.university.ds.models;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address implements Serializable {
	private static final long serialVersionUID = -5854210019996623647L;
	
	private String country;
	private String city;
	private String street;
	private int houseNumber;
}
