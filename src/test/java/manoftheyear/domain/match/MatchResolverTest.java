package manoftheyear.domain.match;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Club;
import manoftheyear.domain.club.Team;
import manoftheyear.domain.club.TeamTest;


public class MatchResolverTest {
  private static final int TAPIS_VERT_WON = 3;
  private static final int TAPIS_VERT_LOSE = 0;
  
  private Team team1;
  private Team team2;

  @BeforeEach
  public void setup() {
    team1 = new Team(new Club());
    team2 = new Team(new Club());
  }
  
  @Test
  public void matchResolveReturnsAScore() throws Exception {
    MatchResult matchResult = new MatchResolver(null, null).resolve();
    
    assertThat(matchResult).isNotNull();
  }
  
  @Test
  void shouldDeclareTeam1WinnerWhenNoTeam2() throws Exception {
    MatchResult matchResult = new MatchResolver(team1, null).resolve();
    
    assertThat(matchResult.winner()).contains(team1);
    assertThat(matchResult.team1Goals()).isEqualTo(TAPIS_VERT_WON);
    assertThat(matchResult.team2Goals()).isEqualTo(TAPIS_VERT_LOSE);
  }
  
  @Test
  void shouldDeclareTeam2WinnerWhenNoTeam1() throws Exception {
    MatchResult matchResult = new MatchResolver(null, team2).resolve();
    
    assertThat(matchResult.winner()).contains(team2);
    assertThat(matchResult.team1Goals()).isEqualTo(TAPIS_VERT_LOSE);
    assertThat(matchResult.team2Goals()).isEqualTo(TAPIS_VERT_WON);
  }
  
  @Test
  void shouldDeclareWinnerTheTeamWithTheBiggestStrength() throws Exception {
    team1.affectPlayers(TeamTest.create11PlayerWithStrength(40));
    team2.affectPlayers(TeamTest.create11PlayerWithStrength(100));
    
    MatchResult matchResult = new MatchResolver(team1, team2).resolve();
    assertThat(matchResult.result()).isEqualTo(MatchResult.Result.WIN_2);
  }
  
}
