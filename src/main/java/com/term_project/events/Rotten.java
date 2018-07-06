package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class Rotten extends AbstractEvent implements Event {

  public Rotten(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 5) {
      character.modSanity(1);
      return "5+ Troubling odors, nothing more. Gain 1 Sanity.";
    } else if (roll > 1 && roll < 5) {
      character.modMight(-1);
      return "2-4 Lose 1 Might";
    } else if (roll == 1) {
      character.modMight(-1);
      character.modSpeed(-1);
      return "1 Lose 1 Might and 1 Speed";
    } else {
      character.modMight(-1);
      character.modSpeed(-1);
      character.modKnowlege(-1);
      character.modSanity(-1);
      return "0 You double over with nausea. "
          + "Lose 1 Might, 1 Speed, 1 Sanity, and 1 Knowledge.";
    }
  }
}
