package manoftheyear.infrastructure.players;

import java.util.HashMap;
import java.util.Map;

import manoftheyear.domain.club.ClubPlayerRepository;
import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerId;
import manoftheyear.domain.player.PlayerRepository;

public class InMemoryPlayerRepository implements ClubPlayerRepository, PlayerRepository {
	Map<PlayerId, Player> players = new HashMap<>();
	
	
	@Override
	public Player find(PlayerId id) {
		return players.get(id);
	}

	public void register(Player player) {
		players.put(player.id, player);
	}
	
}
