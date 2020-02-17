package manoftheyear.domain.match;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import manoftheyear.domain.match.MatchResult.Result;

public class MatchResultTest {

  @DisplayName("Match result")
  @ParameterizedTest(name = "\"{0}-{1}\" should be {2}")
  @CsvSource({ "1, 0, WIN_1", "1, 2, WIN_2", "3, 3, DRAW"})
  void resultBasedOnGoals(int goal1, int goal2, String expected) throws Exception {
    MatchResult.Result expectedResult = MatchResult.Result.valueOf(expected);
    
    MatchTeamSheet mts1 = new MatchTeamSheet(null, goal1);
    MatchTeamSheet mts2 = new MatchTeamSheet(null, goal2);
    
    assertThat(new MatchResult(mts1, mts2).result()).isEqualTo(expectedResult);
    
  }
}
