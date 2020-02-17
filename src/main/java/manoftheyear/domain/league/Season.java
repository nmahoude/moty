package manoftheyear.domain.league;

public class Season {

  private League league;
  private SeasonSchedule schedule;

  public Season(League league) {
    this.league = league;
  }

  public void init() {
    schedule = new LeagueScheduleResolver().calculate(league.teams);
  }

  public SeasonSchedule getSchedule() {
    return schedule;
  }
}
