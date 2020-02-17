package manoftheyear.domain.league;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import manoftheyear.domain.club.TeamTest;

public class LeagueTest {

  private League league;
  private SeasonFactory seasonFactory;

  @BeforeEach
  public void setup() {
    seasonFactory = Mockito.mock(SeasonFactory.class);
    league = new League(seasonFactory, 20);
  }
  
  @Test
  void shouldRegisterTeams() throws Exception {
    league.register(TeamTest.createATeam());
    
    assertThat(league.getTeamsCount()).isEqualTo(1);
  }

  @Test
  void shouldNotRegisterMoreTeamsThanCapacity() throws Exception {
    league = new League(seasonFactory, 2);
    
    league.register(TeamTest.createATeam());
    league.register(TeamTest.createATeam());
    league.register(TeamTest.createATeam());
    
    assertThat(league.getTeamsCount()).isEqualTo(2);
  }
  
  @Test
  void shouldCreateSeasonFromFactory() throws Exception {
    league.createSeason();
    
    Mockito.verify(seasonFactory).createSeason(league);
  }
}
