package manoftheyear.domain.match;

import manoftheyear.domain.club.Team;

public class MatchResolver {
  private static final int TAPISVERT_WIN = 3;
  private static final int TAPISVERT_LOSE = 0;
  private Team team1;
  private Team team2;

  public MatchResolver(Team team1, Team team2) {
    this.team1 = team1;
    this.team2 = team2;
  }

  public MatchResult resolve() {
    if (team1 == null) {
      return new MatchResult(new MatchTeamSheet(team1, TAPISVERT_LOSE), new MatchTeamSheet(team2, TAPISVERT_WIN));
    } else if (team2 == null ){
      return new MatchResult(new MatchTeamSheet(team1, TAPISVERT_WIN), new MatchTeamSheet(team2, TAPISVERT_LOSE));
    } else {
      return new MatchResult(new MatchTeamSheet(team1, team1.club().strength()), new MatchTeamSheet(team2, team2.club().strength()));
    }
  }

}
