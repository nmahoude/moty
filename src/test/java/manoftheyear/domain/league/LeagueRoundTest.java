package manoftheyear.domain.league;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Club;
import manoftheyear.domain.club.ClubPlayerRepository;
import manoftheyear.domain.club.Team;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;


public class LeagueRoundTest {
  private ClubPlayerRepository players = new InMemoryPlayerRepository();
  private LeagueRound sut;

  @BeforeEach
  public void setup() {
    sut = new LeagueRound(1);
  }
  
  @Test
  public void reverseShouldReverseAllMatches() throws Exception {
    Club club1 = new Club(players, "club 1");
    Club club2 = new Club(players, "club 2");
    
    Team team1 = club1.mainTeam();
    Team team2 = club2.mainTeam();
    
    sut.getMatches().add(new Match(team1, team2));
    sut.getMatches().add(new Match(team1, team2));
    
    LeagueRound reversed = sut.reverse(1);
    
    assertThat(reversed.getMatches().get(0).team1).isEqualTo(team2);
    assertThat(reversed.getMatches().get(0).team2).isEqualTo(team1);
   
    assertThat(reversed.getMatches().get(1).team1).isEqualTo(team2);
    assertThat(reversed.getMatches().get(1).team2).isEqualTo(team1);
  }
  
}
