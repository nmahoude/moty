package manoftheyear.domain.match;

public class MatchRule {
  public final int minPlayers;
  public final int maxPlayers;
  
  public MatchRule(int minPlayers, int maxPlayers) {
    super();
    this.minPlayers = minPlayers;
    this.maxPlayers = maxPlayers;
  }

  public static MatchRule standard() {
    return new MatchRule(11, 17);
  }
  
  
}
