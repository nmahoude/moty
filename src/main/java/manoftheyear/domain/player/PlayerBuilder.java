package manoftheyear.domain.player;

import java.time.LocalDate;
import java.util.Random;

import manoftheyear.domain.geography.City;

public class PlayerBuilder {
	Player current;
	
	public static PlayerBuilder any() {
		PlayerBuilder builder = new PlayerBuilder();
		builder.current = new Player(PlayerId.next(), 
						"any", 
						LocalDate.now(), 
						City.from("Lille"), 
						180,
						70,
						new Random().nextInt(100));
		return builder;
	}

	public PlayerBuilder withCapacity(int capacity) {
		this.current.setCapacity(capacity);
		return this;
	}

	public Player build() {
		return current;
	}
}
