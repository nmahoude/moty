package manoftheyear.domain.match;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Club;
import manoftheyear.domain.club.PlayerRepository;
import manoftheyear.domain.club.Team;
import manoftheyear.domain.club.TeamTest;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;


public class MatchResolverTest {
  private PlayerRepository players = new InMemoryPlayerRepository();
	
  private static final int TAPIS_VERT_WON = 3;
  private static final int TAPIS_VERT_LOSE = 0;
  
  private Club club1;
  private Team team1;

  private Club club2;
  private Team team2;

  @BeforeEach
  public void setup() {
    club1 = new Club(players, "club 1");
    club2 = new Club(players, "club 2");
    
    
    team1 = club1.mainTeam();
    team2 = club2.mainTeam();
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
    club1.affectPlayers(TeamTest.create11PlayerWithStrength(players, 40));
    club2.affectPlayers(TeamTest.create11PlayerWithStrength(players, 100));
    
    MatchResult matchResult = new MatchResolver(team1, team2).resolve();
    assertThat(matchResult.result()).isEqualTo(MatchResult.Result.WIN_2);
  }
  
}
