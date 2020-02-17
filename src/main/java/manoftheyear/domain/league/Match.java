package manoftheyear.domain.league;

import manoftheyear.domain.club.Team;

public class Match {
  public final Team team1;
  public final Team team2;
  
  public Match(Team team1, Team team2) {
    super();
    this.team1 = team1;
    this.team2 = team2;
  }

  public Match reverse() {
    return new Match(team2, team1);
  }
}
