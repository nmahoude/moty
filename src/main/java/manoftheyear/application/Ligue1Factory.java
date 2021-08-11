package manoftheyear.application;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import manoftheyear.domain.club.Club;
import manoftheyear.domain.league.League;
import manoftheyear.domain.league.standardSeason.StandardSeasonFactory;
import manoftheyear.domain.player.Player;
import manoftheyear.domain.player.PlayerBuilder;
import manoftheyear.infrastructure.players.InMemoryPlayerRepository;

public class Ligue1Factory {

  public static League buildLigue1(InMemoryPlayerRepository playerRepository) {
	
    List<Club> clubs = createClubs(playerRepository);
    
    League ligue1 = new League(new StandardSeasonFactory(), clubs.size());
    for (Club club  :clubs) {
      ligue1.register(club.mainTeam());
    }
    
    return ligue1;
  }

  private static List<Club> createClubs(InMemoryPlayerRepository playerRepository) {
    List<Club> clubs = new ArrayList<>();

    clubs.add(createClub(playerRepository, "Paris", 100));
    clubs.add(createClub(playerRepository, "Marseille", 80));
    clubs.add(createClub(playerRepository, "Rennes", 60));
    clubs.add(createClub(playerRepository, "Lille", 80));
    clubs.add(createClub(playerRepository, "Montpellier", 50));
    clubs.add(createClub(playerRepository, "Lyon", 90));
    clubs.add(createClub(playerRepository, "Nantes", 70));
    clubs.add(createClub(playerRepository, "Angers", 50));
    clubs.add(createClub(playerRepository, "Monaco", 80));
    clubs.add(createClub(playerRepository, "Reims", 60));
    clubs.add(createClub(playerRepository, "Nice", 70));
    clubs.add(createClub(playerRepository, "Strasbourg", 50));
    clubs.add(createClub(playerRepository, "Bordeaux", 60));
    clubs.add(createClub(playerRepository, "Brest", 30));
    clubs.add(createClub(playerRepository, "Saint-Étienne", 60));
    clubs.add(createClub(playerRepository, "Metz", 20));
    clubs.add(createClub(playerRepository, "Dijon", 20));
    clubs.add(createClub(playerRepository, "Amiens", 10));
    clubs.add(createClub(playerRepository, "Nîmes", 10));
    clubs.add(createClub(playerRepository, "Toulouse", 30));
    return clubs;
  }

  private static Club createClub(InMemoryPlayerRepository playerRepository, String name, int strength) {
    Club club = new Club(playerRepository, name);
    
    for (int i=0;i<25;i++) {
      Player player = PlayerBuilder.any().withCapacity(new Random().nextInt(strength)).buildInto(playerRepository);
      club.affectPlayer(player);
    }
    return club;
  }
}
