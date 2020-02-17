package manoftheyear.domain.league;

import java.util.ArrayList;
import java.util.List;

public class LeagueRound {

  private List<Match> matches = new ArrayList<>();
  public int week;

  public LeagueRound(int week) {
    this.week = week;
  }

  public List<Match> getMatches() {
    return matches ;
  }

  public LeagueRound reverse(int deltaWeek) {
    LeagueRound leagueRound = new LeagueRound(week + deltaWeek);
    for (Match m : matches) {
      leagueRound.getMatches().add(m.reverse());
    }
    return leagueRound;
  }

}
