package manoftheyear.domain.club;

import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerId;

public interface ClubPlayerRepository {
	Player find(PlayerId id);

	public final class Smart {
		final ClubPlayerRepository original;
		
		public Smart(ClubPlayerRepository original) {
			super();
			this.original = original;
		}

		public Player findValid(PlayerId id) {
			Player player = original.find(id);
			if (player.capacity() > 0) return player;
			else return null;
		}
	}
}
