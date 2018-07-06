package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class SomethingSlimey extends AbstractEvent implements Event {

  public SomethingSlimey(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 4) {
      character.modSpeed(1);
      return "You break free. Gain 1 Speed.";
    } else if (roll > 0 && roll < 4) {
      return "Lose 1 Might.";
    } else {
      character.modSpeed(-1);
      character.modMight(-1);
      return "Lose 1 Might and 1 Speed.";
    }
  }
}
