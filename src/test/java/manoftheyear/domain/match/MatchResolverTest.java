package manoftheyear.domain.match;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Club;
import manoftheyear.domain.club.TeamTest;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;


public class MatchResolverTest {
  private InMemoryPlayerRepository players = new InMemoryPlayerRepository();
	
  private static final int TAPIS_VERT_WON = 3;
  private static final int TAPIS_VERT_LOSE = 0;
  
  private Club club1;
  private TeamSheet team1;

  private Club club2;
  private TeamSheet team2;

  @BeforeEach
  public void setup() {
    club1 = new Club(players, "club 1");
    club2 = new Club(players, "club 2");
    
    
    team1 = new TeamSheet(club1.mainTeam());
    team2 = new TeamSheet(club2.mainTeam());
  }
  
  @Test
  public void matchResolveReturnsAScore() throws Exception {
    MatchResult matchResult = new MatchResolver(new Random(), MatchRule.standard(), null, null).resolve();
    
    assertThat(matchResult).isNotNull();
  }
  
  @Test
  void shouldDeclareTeam1WinnerWhenNoTeam2() throws Exception {
    MatchResult matchResult = new MatchResolver(new Random(2),new MatchRule(0, 1),  team1, null).resolve();
    
    assertThat(matchResult.winner()).contains(team1);
    assertThat(matchResult.team1Goals()).isEqualTo(TAPIS_VERT_WON);
    assertThat(matchResult.team2Goals()).isEqualTo(TAPIS_VERT_LOSE);
  }
  
  @Test
  void shouldDeclareTeam2WinnerWhenNoTeam1() throws Exception {
    MatchResult matchResult = new MatchResolver(new Random(), MatchRule.standard(), null, team2).resolve();
    
    assertThat(matchResult.winner()).contains(team2);
    assertThat(matchResult.team1Goals()).isEqualTo(TAPIS_VERT_LOSE);
    assertThat(matchResult.team2Goals()).isEqualTo(TAPIS_VERT_WON);
  }
  
  @Test
  void shouldDeclareWinnerTheTeamWithTheBiggestStrength() throws Exception {
    team1.signOnTeamSheet(TeamTest.create11PlayerWithStrength(players, 40));
    team2.signOnTeamSheet(TeamTest.create11PlayerWithStrength(players, 100));
    
    MatchResult matchResult = new MatchResolver(new Random(), MatchRule.standard(), team1, team2).resolve();
    assertThat(matchResult.result()).isEqualTo(MatchResult.Result.WIN_2);
  }
  
}
