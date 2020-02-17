package manoftheyear.domain.club;

public class Club {
  private String name;
  private Team team = new Team(this);

  public Team getMainTeam() {
    return team;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

}
