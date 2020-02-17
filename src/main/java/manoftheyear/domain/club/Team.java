package manoftheyear.domain.club;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Team {

  private final Club club;
  private Set<Player> players = new HashSet<Player>();

  private String name;
  private int strength; // NOTE placeholder to estimate the team strength

  
  public Team(Club club) {
    this.club = club;
  }
  
  private void recalculateStrength() {
    strength = players.stream().mapToInt(Player::getStrength).sum() / players.size();
  }

  public int getStrength() {
    return strength;
  }

  public void affectPlayers(List<Player> players) {
    this.players.addAll(players);

    recalculateStrength();
  }

  public int playersCount() {
    return players.size();
  }

  public void affectPlayer(Player player) {
    players.add(player);
    recalculateStrength();
  }

  public Club getClub() {
    return club;
  }

  public String getName() {
    return this.name != null ? this.name : this.club.getName();
  }

  public void resetName() {
    this.name = null;
  }

  public void setName(String name) {
    this.name = name;
  }
}
