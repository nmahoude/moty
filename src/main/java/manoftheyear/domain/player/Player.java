package manoftheyear.domain.player;

import java.time.LocalDate;

import manoftheyear.domain.geography.City;

public class Player {
	// immutable
	public final PlayerId id;
	private final String name;
	private final LocalDate birthDate;
	private final City city;
	
	// mutables
	int height;
	int weight;
	int capacity; // TODO tmp info to represent player abilities

	public Player(PlayerId id, String name, LocalDate birthDate, City city, int height, int weight, int capacity) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.city = city;
		this.height = height;
		this.weight = weight;
		this.capacity = capacity;
	}
	
	public int capactiy() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
