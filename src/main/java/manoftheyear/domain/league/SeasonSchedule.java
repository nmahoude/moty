package manoftheyear.domain.league;

import java.util.ArrayList;
import java.util.List;

import manoftheyear.domain.club.Team;

public class SeasonSchedule {
  private List<LeagueRound> rounds = new ArrayList<>();

  public SeasonSchedule(List<LeagueRound> rounds) {
    this.rounds.addAll(rounds);
  }

  public int getRounds() {
    return rounds.size();
  }

  public List<Match> getMatchesBetween(Team team1, Team team2) {
    List<Match> matches = new ArrayList<>();
    for (LeagueRound lr : rounds) {
      for (Match match : lr.getMatches()) {
        if (match.team1 == team1 && match.team2 == team2) {
          matches.add(match);
        }
      }
    }
    return matches;
  }

  public LeagueRound getRound(int week) {
    return rounds.get(week);
  }
}
