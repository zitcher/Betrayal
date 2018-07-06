package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class Footsteps extends AbstractEvent implements Event {

  public Footsteps(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll == 4 || roll == 3) {
      character.modMight(1);
      return "3-4 Gain 1 Might";
    } else if (roll == 2) {
      character.modSanity(-1);
      return "2 Lose 1 Sanity.";
    } else if (roll == 1) {
      character.modSpeed(-1);
      return "1 Lose 1 Speed.";
    } else {
      character.modMight(-1);
      return "0 Lose 1 Might.";
    }
  }
}
