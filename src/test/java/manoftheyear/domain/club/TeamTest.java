package manoftheyear.domain.club;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerBuilder;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;

public class TeamTest {
  private static PlayerRepository playersDB = new InMemoryPlayerRepository();
	
  private Club club;
  private Team team;

  @BeforeEach
  public void setup() {
    club = new Club(playersDB, "a club");
    team = new Team(club, "team A");
  }
  
  @Test
  void aTeamHasAClub() throws Exception {
    assertThat(team.club()).isInstanceOf(Club.class);
  }
  
  @Test
  void teamStrengthIsMeanStrengthOfPlayers() throws Exception {
    List<Player> players = create11PlayerWithStrength(playersDB, 30);
    club.affectPlayers(players);
    
    assertThat(club.strength()).isEqualTo(30);
  }
  
  @Test
  void canAffectANewPlayerOnTheFly() throws Exception {
    Player anyPlayer = PlayerBuilder.any().withCapacity(40).build();
    playersDB.register(anyPlayer);
    
    club.affectPlayer(anyPlayer);
    
    assertThat(club.playersCount()).isEqualTo(1);
  }
  
  @Test
  void newAffectedPlayersModifyStrnegth() throws Exception {
    Player anyPlayer = PlayerBuilder.any().withCapacity(40).build();
    playersDB.register(anyPlayer);

    club.affectPlayer(anyPlayer);
    
    assertThat(club.strength()).isEqualTo(40);
  }
  
  @Test
  void playersInTeamAreUnique() throws Exception {
    Player anyPlayer = PlayerBuilder.any().withCapacity(40).build();
    playersDB.register(anyPlayer);

    club.affectPlayer(anyPlayer);
    club.affectPlayer(anyPlayer);
    
    assertThat(club.playersCount()).isEqualTo(1);
  }
  
  public static List<Player> create11PlayerWithStrength(PlayerRepository playersDB, int strength) {
    List<Player> players = new ArrayList<Player>();
    for (int i=0;i<11;i++) {
      Player anyPlayer = PlayerBuilder.any().withCapacity(strength).build();
	  playersDB.register(anyPlayer);
	  players.add(anyPlayer);
    }
    return players;
  }
  
  
  @Test
  void teamsCanHaveSpecificName() throws Exception {
    club = new Club(playersDB, "club name");
    team = new Team(club, "team name"); 
    
    assertThat(team.name()).isEqualTo("team name");
    
  }
  
  public static Team createATeam() {
    return new Club(playersDB, "a club").mainTeam();
  }
}
