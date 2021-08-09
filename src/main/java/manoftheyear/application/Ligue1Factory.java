package manoftheyear.application;

import java.util.ArrayList;
import java.util.List;

import manoftheyear.domain.club.Club;
import manoftheyear.domain.club.PlayerRepository;
import manoftheyear.domain.league.League;
import manoftheyear.domain.league.standardSeason.StandardSeasonFactory;
import manoftheyear.domain.player.PlayerBuilder;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;

public class Ligue1Factory {
  static PlayerRepository playerRepository = new InMemoryPlayerRepository();

  public static League buildLigue1() {
	
    List<Club> clubs = createClubs();
    
    League ligue1 = new League(new StandardSeasonFactory(), clubs.size());
    for (Club club  :clubs) {
      ligue1.register(club.mainTeam());
    }
    
    return ligue1;
  }

  private static List<Club> createClubs() {
    List<Club> clubs = new ArrayList<>();

    clubs.add(createClub("Paris", 100));
    clubs.add(createClub("Marseille", 80));
    clubs.add(createClub("Rennes", 60));
    clubs.add(createClub("Lille", 80));
    clubs.add(createClub("Montpellier", 50));
    clubs.add(createClub("Lyon", 90));
    clubs.add(createClub("Nantes", 70));
    clubs.add(createClub("Angers", 50));
    clubs.add(createClub("Monaco", 80));
    clubs.add(createClub("Reims", 60));
    clubs.add(createClub("Nice", 70));
    clubs.add(createClub("Strasbourg", 50));
    clubs.add(createClub("Bordeaux", 60));
    clubs.add(createClub("Brest", 30));
    clubs.add(createClub("Saint-Étienne", 60));
    clubs.add(createClub("Metz", 20));
    clubs.add(createClub("Dijon", 20));
    clubs.add(createClub("Amiens", 10));
    clubs.add(createClub("Nîmes", 10));
    clubs.add(createClub("Toulouse", 30));
    return clubs;
  }

  private static Club createClub(String name, int strength) {
    Club club = new Club(playerRepository, name);
    
    for (int i=0;i<25;i++) {
      club.affectPlayer(PlayerBuilder.any().withCapacity(strength).build());
    }
    return club;
  }
}
