package manoftheyear.domain.player;

public class PlayerId {
	private static long nextId = 0;
	public final long id;

	public PlayerId(long id) {
		super();
		this.id = id;
	}

	public static PlayerId next() {
		return new PlayerId(nextId++);
	}
	
}
