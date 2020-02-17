package manoftheyear.domain.match;

import manoftheyear.domain.club.Team;

public class MatchTeamSheet {

  private Team team;
  private int goals;

  public MatchTeamSheet(Team team, int goals) {
    this.team = team;
    this.goals = goals;
  }

  public int getGoals() {
    return goals;
  }

  public Team getTeam() {
    return team;
  }

}
