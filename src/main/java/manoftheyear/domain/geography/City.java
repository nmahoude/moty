package manoftheyear.domain.geography;

import java.util.HashMap;
import java.util.Map;

public class City {
	private static Map<String, City> cities = new HashMap<>();
	
	public final String name;
	
	private City(String name) {
		this.name = name;
	}
	
	
	public static City from(String name) {
		if (!cities.containsKey(name)) {
			City newCity = new City(name);
			cities.put(name, newCity);
		}
		return cities.get(name);
	}

}
