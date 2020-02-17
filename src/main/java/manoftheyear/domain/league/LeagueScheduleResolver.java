package manoftheyear.domain.league;

import java.util.ArrayList;
import java.util.List;

import manoftheyear.domain.club.Team;

public class LeagueScheduleResolver {

  public SeasonSchedule calculate(List<Team> originalTeams) {
    List<Team> teams = new ArrayList<>();
    teams.addAll(originalTeams);
    
    List<LeagueRound> rounds = new ArrayList<>();
    
    int halfSize = teams.size()/2;
    Team team2 = teams.get(1);
    int week = 1;
    do {
      LeagueRound round = new LeagueRound(week);
      for (int i=0;i<halfSize;) {
        Match match = new Match(teams.get(i), teams.get(i+halfSize));
        round.getMatches().add(match);
        i+=1;
      }
      rounds.add(round);
      
      // round robin shift
      Team removed = teams.remove(0);
      Team first = teams.remove(0);
      teams.add(first);
      teams.add(0, removed);
    } while (teams.get(1) != team2);
    
    
    rounds.addAll(reverseRounds(rounds));
    
    return new SeasonSchedule(rounds);
  }

  private List<LeagueRound> reverseRounds(List<LeagueRound> homeRounds) {
    ArrayList<LeagueRound> awayRounds = new ArrayList<>();
    for (LeagueRound lr : homeRounds) {
      awayRounds.add(lr.reverse(homeRounds.size()));
    }
    return awayRounds;
  }

}
