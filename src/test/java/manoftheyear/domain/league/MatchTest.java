package manoftheyear.domain.league;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Team;

public class MatchTest {

  
  @Test
  void shouldInverseTeamsWhenMatchIsReversed() throws Exception {
    Team team1 = new Team(null);
    Team team2 = new Team(null);
    Match match = new Match(team1, team2);
    
    Match reversedMatch = match.reverse();
    
    assertThat(reversedMatch.team1).isEqualTo(team2);
    assertThat(reversedMatch.team2).isEqualTo(team1);
  }
}
