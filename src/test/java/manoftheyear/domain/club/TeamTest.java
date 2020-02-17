package manoftheyear.domain.club;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TeamTest {

  private Team team;

  @BeforeEach
  public void setup() {
    team = new Team(new Club());
  }
  
  @Test
  void aTeamHasAClub() throws Exception {
    assertThat(team.getClub()).isInstanceOf(Club.class);
  }
  
  @Test
  void teamStrengthIsMeanStrengthOfPlayers() throws Exception {
    List<Player> players = create11PlayerWithStrength(30);
    team.affectPlayers(players);
    
    assertThat(team.getStrength()).isEqualTo(30);
  }
  
  @Test
  void canAffectANewPlayerOnTheFly() throws Exception {
    Player anyPlayer = new Player(40);
    team.affectPlayer(anyPlayer);
    
    assertThat(team.playersCount()).isEqualTo(1);
  }
  
  @Test
  void newAffectedPlayersModifyStrnegth() throws Exception {
    Player anyPlayer = new Player(40);
    team.affectPlayer(anyPlayer);
    
    assertThat(team.getStrength()).isEqualTo(40);
  }
  
  @Test
  void playersInTeamAreUnique() throws Exception {
    Player anyPlayer = new Player(40);
    team.affectPlayer(anyPlayer);
    team.affectPlayer(anyPlayer);
    
    assertThat(team.playersCount()).isEqualTo(1);
  }
  
  public static List<Player> create11PlayerWithStrength(int strength) {
    List<Player> players = new ArrayList<Player>();
    for (int i=0;i<11;i++) {
      players.add(new Player(strength));
    }
    return players;
  }
  
  
  @Test
  void teamsWithoutNameGetClubName() throws Exception {
    team.getClub().setName("clubName");
    team.resetName();
    
    assertThat(team.getName()).isEqualTo("clubName");
  }

  @Test
  void teamsCanHaveSpecificName() throws Exception {
    team.getClub().setName("clubName");
    team.setName("teamName");
    
    assertThat(team.getName()).isEqualTo("teamName");
    
  }
  
  public static Team createATeam() {
    return new Club().getMainTeam();
  }
}
