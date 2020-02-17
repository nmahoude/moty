package manoftheyear.domain.league.standardSeason;

import manoftheyear.domain.league.League;
import manoftheyear.domain.league.Season;
import manoftheyear.domain.league.SeasonFactory;

public class StandardSeasonFactory implements SeasonFactory {

  @Override
  public Season createSeason(League league) {
    Season season = new Season(league);
    season.init();
    return season;
  }

}
