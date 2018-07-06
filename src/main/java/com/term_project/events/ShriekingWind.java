package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class ShriekingWind extends AbstractEvent implements Event {

  public ShriekingWind(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 5) {
      return "You keep your footing.";
    } else if (roll > 2 && roll < 5) {
      character.modMight(-1);
      return "The wind knocks you down. Take 1 Might damage.";
    } else {
      character.modSanity(-1);
      return "The wind chills your soul. Take 1 Sanity damage.";
    }
  }
}
