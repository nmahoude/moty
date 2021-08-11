package manoftheyear.domain.club;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import manoftheyear.domain.league.Match;
import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerId;

public class Club {
  private String name;
  private Team team;

  private Set<PlayerId> players = new HashSet<>();
  private ClubPlayerRepository playersFinder;

  public Club(ClubPlayerRepository playerRepository, String name) {
    this.playersFinder = playerRepository;
    this.name = name;
    team = new Team(this, this.name); // TODO placeholder, a club can have multiple teams but at least one team
  }

  public Team mainTeam() {
    return team;
  }

  public String name() {
    return name;
  }


  public void affectPlayers(List<Player> players) {
    this.players = players.stream().map(p -> p.id).collect(Collectors.toSet());
  }

  public int playersCount() {
    return players.size();
  }

  public void affectPlayer(Player player) {
    players.add(player.id);
  }

  public List<Player> prepareTeam(Match match, Team team1) {
    // TODO a better algorithm to choose players :p
    return this.players.stream().map(playersFinder::find)
        // .filter(player -> !player::isInjured)
        .sorted((p1, p2) -> Integer.compare(p2.capacity(), p1.capacity()))
        .limit(11)
        .collect(Collectors.toList());
  }
}
