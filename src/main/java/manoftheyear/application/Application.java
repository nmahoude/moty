package manoftheyear.application;

import java.util.List;
import java.util.Random;

import manoftheyear.domain.league.League;
import manoftheyear.domain.league.LeagueRound;
import manoftheyear.domain.league.Match;
import manoftheyear.domain.league.Season;
import manoftheyear.domain.match.MatchResolver;
import manoftheyear.domain.match.MatchResult;
import manoftheyear.domain.match.MatchRule;
import manoftheyear.domain.match.TeamSheet;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;

// small test app
public class Application {
  public final static Random random = new Random(System.currentTimeMillis());
  private League ligue1;
  private Season season;

  public static void main(String[] args) {
    new Application().run();
  }

  private void run() {
    InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();
    

    ligue1 = Ligue1Factory.buildLigue1(playerRepository);
    season = ligue1.createSeason();
        
    season.getSchedule().rounds().stream().forEach(r -> {
      resolveWeek(r); 
    });
    
  }

  private void resolveWeek(LeagueRound round) {
    System.out.println("Week: " + round.week());
    List<Match> matches = round.getMatches();
    for (int i=0;i<matches.size();i++) {
      Match match = matches.get(i);
      
      // ask clubs for the players
     TeamSheet ts1 = new TeamSheet(match.team1);
     ts1.signOnTeamSheet(match.team1.club().prepareTeam(match, match.team1));
      
     TeamSheet ts2 = new TeamSheet(match.team2);
     ts2.signOnTeamSheet(match.team2.club().prepareTeam(match, match.team2));
     
     
      MatchResolver matchResolver = new MatchResolver(random, MatchRule.standard(), ts1, ts2);
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
