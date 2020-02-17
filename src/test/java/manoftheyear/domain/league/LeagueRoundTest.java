package manoftheyear.domain.league;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Team;


public class LeagueRoundTest {

  private LeagueRound sut;

  @BeforeEach
  public void setup() {
    sut = new LeagueRound(1);
  }
  
  @Test
  public void reverseShouldReverseAllMatches() throws Exception {
    Team team1 = new Team(null);
    Team team2 = new Team(null);
    
    sut.getMatches().add(new Match(team1, team2));
    sut.getMatches().add(new Match(team1, team2));
    
    LeagueRound reversed = sut.reverse(1);
    
    assertThat(reversed.getMatches().get(0).team1).isEqualTo(team2);
    assertThat(reversed.getMatches().get(0).team2).isEqualTo(team1);
   
    assertThat(reversed.getMatches().get(1).team1).isEqualTo(team2);
    assertThat(reversed.getMatches().get(1).team2).isEqualTo(team1);
  }
  
}
