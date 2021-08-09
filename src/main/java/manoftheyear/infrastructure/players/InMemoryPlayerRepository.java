package manoftheyear.infrastructure.players;

import java.util.HashMap;
import java.util.Map;

import manoftheyear.domain.club.PlayerRepository;
import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerId;

public class InMemoryPlayerRepository implements PlayerRepository {
	Map<PlayerId, Player> players = new HashMap<>();
	
	
	@Override
	public Player find(PlayerId id) {
		return players.get(id);
	}

	public void register(Player player) {
		players.put(player.id, player);
	}
	
}
