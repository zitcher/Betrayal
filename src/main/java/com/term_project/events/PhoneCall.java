package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class PhoneCall extends AbstractEvent implements Event {

  public PhoneCall(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll == 4) {
      character.modSanity(1);
      return "4 'Tea and cakes!' Gain 1 Sanity.";
    } else if (roll == 3) {
      character.modKnowlege(1);
      return "3 'I'm always here for you. Watching...' Gain "
          + "1 Knowledge.";
    } else if (roll == 1 || roll == 2) {
      character.modSanity(-1);
      return "1-2 I'm here! Give us a kiss! Take 1 Sanity damage.";
    } else {
      character.modMight(-1);
      character.modSpeed(-1);
      return "0 'Bad little children must be punished!' Take 1 Might and 1 Speed damage.";
    }
  }
}
