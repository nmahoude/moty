package manoftheyear.domain.league;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import manoftheyear.domain.club.Team;
import manoftheyear.domain.club.TeamTest;

public class LeagueScheduleResolverTest {

  private LeagueScheduleResolver sut;

  @BeforeEach
  public void setup() {
    sut = new LeagueScheduleResolver();
  }
  
  @Test
  void shouldCreate2RoundsFor2Teams() throws Exception {
    Team team1 = TeamTest.createATeam();
    Team team2 = TeamTest.createATeam();
    List<Team> teams = Arrays.asList(team1, team2);
    
    SeasonSchedule schedule = sut.calculate(teams);
    
    assertThat2MatchesBetween(schedule, team1, team2);
  }

  @Test
  void allRoundsHaveWeekIndexContinuousFrom1toN() throws Exception {
    Team team1 = TeamTest.createATeam();
    Team team2 = TeamTest.createATeam();
    List<Team> teams = Arrays.asList(team1, team2);
    
    SeasonSchedule schedule  = sut.calculate(teams);

    assertThat(schedule.getRound(0).week).isEqualTo(1);
    assertThat(schedule.getRound(1).week).isEqualTo(2);
  }
  
  @Test
  void shouldCreate6RoundsFor4Teams() throws Exception {
    
    List<Team> teams = createNTeams(4);

    SeasonSchedule schedule = sut.calculate(teams);
    
    assertThat(schedule.getRounds()).isEqualTo(6);
  }
  
  @Test
  void eachTeamsShouldPlay2Times() throws Exception {
    List<Team> teams = createNTeams(4);
    Team team1 = teams.get(0), team2 = teams.get(1), team3 = teams.get(2), team4 = teams.get(3);
    
    SeasonSchedule schedule = sut.calculate(teams);

    assertThat2MatchesBetween(schedule, team1, team2);
    assertThat2MatchesBetween(schedule, team1, team3);
    assertThat2MatchesBetween(schedule, team1, team4);
    assertThat2MatchesBetween(schedule, team2, team3);
    assertThat2MatchesBetween(schedule, team2, team4);
    assertThat2MatchesBetween(schedule, team3, team4);
  }

  
  private List<Team> createNTeams(int count) {
    List<Team> teams = new ArrayList<>();
    for (int i=0;i<count;i++) {
      Team team = TeamTest.createATeam();
      teams.add(team);
    }
    return teams;
  }

  private void assertThat2MatchesBetween(SeasonSchedule schedule, Team team1, Team team2) {
    List<Match> homeMatches = schedule.getMatchesBetween(team1, team2);
    assertThat(homeMatches.size()).as("One home game").isEqualTo(1);
    
    List<Match> awayMatches = schedule.getMatchesBetween(team2, team1);
    assertThat(awayMatches.size()).as("One away game").isEqualTo(1);
  }
}
