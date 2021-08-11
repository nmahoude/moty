package manoftheyear.domain.club;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerBuilder;
import manoftheyear.domain.player.PlayerRepository;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;

public class TeamTest {
  private static PlayerRepository playersDB;
  private static ClubPlayerRepository clubPlayersDB;
	
  private Club club;
  private Team team;

  @BeforeEach
  public void setup() {
    InMemoryPlayerRepository db = new InMemoryPlayerRepository();
    playersDB = db;
    clubPlayersDB = db;
    
    
    club = new Club(clubPlayersDB, "a club");
    team = new Team(club, "team A");
  }
  
  @Test
  void aTeamHasAClub() throws Exception {
    assertThat(team.club()).isInstanceOf(Club.class);
  }
  
  @Test
  void canAffectANewPlayerOnTheFly() throws Exception {
    Player anyPlayer = PlayerBuilder.any().withCapacity(40).buildInto(playersDB);
    
    club.affectPlayer(anyPlayer);
    
    assertThat(club.playersCount()).isEqualTo(1);
  }
  
  @Test
  void playersInTeamAreUnique() throws Exception {
    Player anyPlayer = PlayerBuilder.any().withCapacity(40).buildInto(playersDB);

    club.affectPlayer(anyPlayer);
    club.affectPlayer(anyPlayer);
    
    assertThat(club.playersCount()).isEqualTo(1);
  }
  
  public static List<Player> create11PlayerWithStrength(PlayerRepository playersDB, int strength) {
    List<Player> players = new ArrayList<Player>();
    for (int i=0;i<11;i++) {
      Player anyPlayer = PlayerBuilder.any().withCapacity(strength).buildInto(playersDB);
      players.add(anyPlayer);
    }
    return players;
  }
  
  
  @Test
  void teamsCanHaveSpecificName() throws Exception {
    club = new Club(clubPlayersDB, "club name");
    team = new Team(club, "team name"); 
    
    assertThat(team.name()).isEqualTo("team name");
    
  }
  
  public static Team createATeam() {
    return new Club(clubPlayersDB, "a club").mainTeam();
  }
}
