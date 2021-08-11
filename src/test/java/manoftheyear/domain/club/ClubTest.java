package manoftheyear.domain.club;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.infrastructure.players.InMemoryPlayerRepository;

public class ClubTest {
  private ClubPlayerRepository players = new InMemoryPlayerRepository();
  private Club club;

  @BeforeEach
  public void setup() {
    club = new Club(players, "a club");
  }
  
  @Test
  void aClubHasAName() throws Exception {
    assertThat(club.name()).isEqualTo("a club");
  }
  
  @Test
  void aClubHasAMainTeam() throws Exception {
    assertThat(club.mainTeam()).isInstanceOf(Team.class);
  }
}
