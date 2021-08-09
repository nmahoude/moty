package manoftheyear.domain.club;

public class Team {

  private final Club club;
  private final String name;

  public Team(Club club, String name) {
    this.club = club;
    this.name = name;
  }

  public Club club() {
    return club;
  }

  public String name() {
    return this.name != null ? this.name : this.club.name();
  }

}
