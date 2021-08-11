package manoftheyear.domain.league;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Club;
import manoftheyear.domain.club.ClubPlayerRepository;
import manoftheyear.domain.club.Team;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;

public class MatchTest {
  private ClubPlayerRepository players = new InMemoryPlayerRepository();
  
  @Test
  void shouldInverseTeamsWhenMatchIsReversed() throws Exception {
    Club club1 = new Club(players, "club 1");
    Club club2 = new Club(players, "club 2");
    
    
    Team team1 = club1.mainTeam();
    Team team2 = club2.mainTeam();
    Match match = new Match(team1, team2);
    
    Match reversedMatch = match.reverse();
    
    assertThat(reversedMatch.team1).isEqualTo(team2);
    assertThat(reversedMatch.team2).isEqualTo(team1);
  }
}
