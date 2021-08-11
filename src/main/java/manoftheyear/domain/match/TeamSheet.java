package manoftheyear.domain.match;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import manoftheyear.domain.club.Team;
import manoftheyear.domain.player.Player;

/* 
 * Information of the team for this match
 */
public class TeamSheet {
  private final Team team; // the real club team engaged in the match
  Set<Player> players = new HashSet<>(); // engaged players, a subset of club players that will be here for the match
  // TODO : add manager 
  
  public TeamSheet(Team team) {
    super();
    this.team = team;
  }
  
  public void signOnTeamSheet(Player player) {
    this.players.add(player);
  }

  public void signOnTeamSheet(List<Player> prepareTeam) {
    this.players.clear();
    this.players.addAll(prepareTeam);
  }

  public Set<Player> players() {
    return Collections.unmodifiableSet(players);
  }
  
  public int strength() {
    return this.players.stream().mapToInt(Player::capacity).sum() / players.size();
  }

  
}
