package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class Debris extends AbstractEvent implements Event {

  public Debris(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 3) {
      character.modSpeed(1);
      return "3+ You dodge the plaster. Gain 1 Speed.";
    } else if (roll == 1 || roll == 2) {
      character.modMight(-1);
      return "1-2 You're buried in debris. Take 1 Might Damage.";
    } else {
      character.modMight(-2);
      return "1-2 You're buried in debris. Take 2 Might Damage.";
    }
  }
}
