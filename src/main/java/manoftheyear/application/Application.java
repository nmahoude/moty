package manoftheyear.application;

import java.util.List;
import java.util.Random;

import manoftheyear.domain.league.League;
import manoftheyear.domain.league.Match;
import manoftheyear.domain.league.Season;
import manoftheyear.domain.match.MatchResolver;
import manoftheyear.domain.match.MatchResult;

// small test app
public class Application {

  private static Random random;

  public static void main(String[] args) {
    long seed = System.currentTimeMillis();
    random = new Random(seed);

    League ligue1 = Ligue1Factory.buildLigue1();
    Season season = ligue1.createSeason(); 
        
    int week = random.nextInt(38);
    System.out.println("Week: " + week);
    List<Match> matches = season.getSchedule().getRound(week).getMatches();
    for (int i=0;i<matches.size();i++) {
      Match match = matches.get(i);
      MatchResolver matchResolver = new MatchResolver(match.team1,match.team2);
      MatchResult result = matchResolver.resolve();

      System.out.println(
          String.format("%20s %3d - %3d %-20s", 
              match.team1.name(), 
              result.team1Goals(), 
              result.team2Goals(), 
              match.team2.name()));
    }
  }
}
