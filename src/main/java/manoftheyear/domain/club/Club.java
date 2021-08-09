package manoftheyear.domain.club;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerId;

public class Club {
  private String name;
  private Team team;

  private Set<PlayerId> players = new HashSet<>();
  private int strength; // NOTE placeholder to estimate the team strength
  private PlayerRepository playersFinder;

  public Club(PlayerRepository playerRepository, String name) {
    this.playersFinder = playerRepository;
    this.name = name;
    team = new Team(this, this.name); // TODO placeholder, a club can have multiple teams
  }

  public Team mainTeam() {
    return team;
  }

  public String name() {
    return name;
  }

  private void recalculateStrength() {
    strength = this.players.stream().map(playersFinder::find).mapToInt(Player::capactiy).sum() / players.size();
  }

  public int strength() {
    return strength;
  }

  public void affectPlayers(List<Player> players) {
    this.players = players.stream().map(p -> p.id).collect(Collectors.toSet());

    recalculateStrength();
  }

  public int playersCount() {
    return players.size();
  }

  public void affectPlayer(Player player) {
    players.add(player.id);
    recalculateStrength();
  }
}
