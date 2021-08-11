package manoftheyear.domain.league;

import java.util.ArrayList;
import java.util.List;

import manoftheyear.domain.club.Team;

public class LeagueScheduleResolver {

  public SeasonSchedule calculate(List<Team> originalTeams) {
    List<Team> teamsA = new ArrayList<>();
    List<Team> teamsB = new ArrayList<>();
    for (int i=0;i<originalTeams.size() / 2;i++) {
      teamsA.add(originalTeams.get(i));
      teamsB.add(originalTeams.get(i+originalTeams.size() / 2));
    }
    
    List<LeagueRound> rounds = new ArrayList<>();
    
    Team initialSecondTeam = teamsB.get(0);
    int week = 0;
    boolean switchTeams = false;
    do {
      week++;
      LeagueRound round = new LeagueRound(week);
      for (int i=0;i<teamsA.size();i+=1) {
        Match match;
        if (switchTeams) {
          match = new Match(teamsB.get(i), teamsA.get(i));
        } else {
          match = new Match(teamsA.get(i), teamsB.get(i));
        }
        round.getMatches().add(match);
      }
      rounds.add(round);
      
      // round robin shift
      //teamsA.add(teamsA.size(), rotatedA);
      
      Team rotatedB = teamsB.remove(0);
      teamsB.add(teamsB.size(), rotatedB);
      
      
      switchTeams = !switchTeams;
    } while (teamsB.get(0) != initialSecondTeam);
    
    
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
