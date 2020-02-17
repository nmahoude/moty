package manoftheyear.domain.club;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClubTest {

  private Club club;

  @BeforeEach
  public void setup() {
    club = new Club();
  }
  
  @Test
  void aClubHasAName() throws Exception {
    club.setName("theClub");
    
    assertThat(club.getName()).isEqualTo("theClub");
  }
  
  @Test
  void aClubHasAMainTeam() throws Exception {
    assertThat(club.getMainTeam()).isInstanceOf(Team.class);
  }
}
