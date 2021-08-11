package manoftheyear.domain.player;

public interface PlayerRepository {
	Player find(PlayerId id);

	void register(Player player);

}
