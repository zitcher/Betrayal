package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class HideousShriek extends AbstractEvent implements Event {

  public HideousShriek(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 4) {
      return "4+ You resist the sound.";
    } else if (roll > 0 && roll < 4) {
      character.modSanity(-1);
      return "1-3 Take 1 Sanity damage.";
    } else {
      character.modSanity(-2);
      return "0 Take 2 Sanity damage.";
    }
  }
}
