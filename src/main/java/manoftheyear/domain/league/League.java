package manoftheyear.domain.league;

import java.util.ArrayList;
import java.util.List;

import manoftheyear.domain.club.Team;

public class League {
  private int maxCapacity;
  public List<Team> teams = new ArrayList<>();
  private SeasonFactory seasonFactory;
  
  public League(SeasonFactory factory, int capacity) {
    this.seasonFactory = factory;
    maxCapacity = capacity;
  }

  public int getTeamsCount() {
    return teams.size();
  }

  public boolean register(Team team) {
    if (getTeamsCount() == maxCapacity) {
      return false;
    }

    teams.add(team);
    return true;
  }

  public Season createSeason() {
    return seasonFactory.createSeason(this);
  }

}
