package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class Spider extends AbstractEvent implements Event {

  public Spider(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 4) {
      character.modSpeed(1);
      return "It's gone. Gain 1 Speed.";
    } else if (roll > 0 && roll < 4) {
      character.modSpeed(-1);
      return "It bites you. Take 1 Speed damage.";
    } else {
      character.modSpeed(-2);
      return "It takes a chunk out of you. Take 2 Speed damage.";
    }
  }
}
