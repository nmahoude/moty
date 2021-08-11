package manoftheyear.domain.match;

import java.util.Random;

public class MatchResolver {
  private static final int TAPISVERT_GOALS_WIN = 3;
  private static final int TAPISVERT_GOALS_LOSE = 0;

  private Random random;
  private TeamSheet team1;
  private TeamSheet team2;
  private MatchRule rules;

  public MatchResolver(Random random, MatchRule rules, TeamSheet team1, TeamSheet team2) {
    this.random = random;
    this.rules = rules;
    this.team1 = team1;
    this.team2 = team2;
  }

  public MatchResult resolve() {
    
    if (!check(team1)) {
      return new MatchResult(new MatchTeamSheet(team1, TAPISVERT_GOALS_LOSE), new MatchTeamSheet(team2, TAPISVERT_GOALS_WIN));
    } else if (!check(team2)){
      return new MatchResult(new MatchTeamSheet(team1, TAPISVERT_GOALS_WIN), new MatchTeamSheet(team2, TAPISVERT_GOALS_LOSE));
    }

    // resolve the game 
    double p1 = 1.0 * team1.strength() / (team1.strength() + team2.strength());
    double p2 = 1.0 * team2.strength() / (team1.strength() + team2.strength());
    
    int goal1 = random.nextInt((int)(p1 * 6+1));
    int goal2 = random.nextInt((int)(p2 * 6+1));
    
    
    return new MatchResult(new MatchTeamSheet(team1, goal1), new MatchTeamSheet(team2, goal2));
  }

  private boolean check(TeamSheet team) {
    if (team == null) return false;
    
    // TODO rules from the game
    if (team.players().size() < rules.minPlayers ) return false;
    if (team.players().size() > rules.maxPlayers ) return false;
    return true;
  }

}
