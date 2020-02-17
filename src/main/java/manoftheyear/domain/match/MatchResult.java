package manoftheyear.domain.match;

import java.util.Optional;

import manoftheyear.domain.club.Team;

public class MatchResult {
  MatchTeamSheet team1Sheet;
  MatchTeamSheet team2Sheet;
  
  public enum Result {
    WIN_1, WIN_2, DRAW;
  }
  
  public MatchResult(MatchTeamSheet team1Sheet, MatchTeamSheet team2Sheet) {
    this.team1Sheet = team1Sheet;
    this.team2Sheet = team2Sheet;
  }

  public Optional<Team> winner() {
    return switch(result()) {
      case WIN_1 -> Optional.of(team1Sheet.getTeam());
      case WIN_2 -> Optional.of(team2Sheet.getTeam());
      case DRAW -> Optional.empty();
    };
  }

  public int team1Goals() {
    return team1Sheet.getGoals();
  }

  public int team2Goals() {
    return team2Sheet.getGoals();
  }

  public Result result() {
    if (team1Sheet.getGoals() == team2Sheet.getGoals()) {
      return Result.DRAW;
    } else {
      return team1Sheet.getGoals() > team2Sheet.getGoals() ? Result.WIN_1 : Result.WIN_2;
    }
  }

}
