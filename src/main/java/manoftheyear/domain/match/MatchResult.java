package manoftheyear.domain.match;

import java.util.Optional;

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

  public Optional<TeamSheet> winner() {
    return switch(result()) {
      case WIN_1 -> Optional.of(team1Sheet.team());
      case WIN_2 -> Optional.of(team2Sheet.team());
      case DRAW -> Optional.empty();
    };
  }

  public int team1Goals() {
    return team1Sheet.goals();
  }

  public int team2Goals() {
    return team2Sheet.goals();
  }

  public Result result() {
    if (team1Sheet.goals() == team2Sheet.goals()) {
      return Result.DRAW;
    } else {
      return team1Sheet.goals() > team2Sheet.goals() ? Result.WIN_1 : Result.WIN_2;
    }
  }

}
