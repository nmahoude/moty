package manoftheyear.domain.match;

public class MatchTeamSheet {

  private TeamSheet teamsheet;
  private int goals;

  public MatchTeamSheet(TeamSheet teamsheet, int goals) {
    this.teamsheet = teamsheet;
    this.goals = goals;
  }

  public int goals() {
    return goals;
  }

  public TeamSheet teamSheet() {
    return teamsheet;
  }

  public TeamSheet team() {
    return teamsheet;
  }

}
