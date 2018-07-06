package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class NightView extends AbstractEvent implements Event {

  public NightView(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 5) {
      character.modKnowlege(1);
      return "5+ You recognize the ghosts as former inhabitants of the house. "
          + "You call their names, and they turn to you, whispering dark "
          + "secrets of the house. Gain 1 Knowledge.";
    } else {
      return "0-4 You pull back in horror, unable to watch";
    }
  }
}
