package manoftheyear.domain.club;

import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerId;

public interface PlayerRepository {
	Player find(PlayerId id);

	void register(Player player);
	
	public final class Smart {
		final PlayerRepository original;
		
		public Smart(PlayerRepository original) {
			super();
			this.original = original;
		}

		public Player findValid(PlayerId id) {
			Player player = original.find(id);
			if (player.capactiy() > 0) return player;
			else return null;
		}
	}
}
