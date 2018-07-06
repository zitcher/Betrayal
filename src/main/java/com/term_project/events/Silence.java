package com.term_project.events;

import java.util.List;

import com.term_project.character.GameChar;
import com.term_project.game.Stats;

public class Silence extends AbstractEvent implements Event {

  public Silence(String name, String description, String function,
      List<Stats> usable) {
    super(name, description, function, usable);
  }

  @Override
  public String apply(int roll, GameChar character) {
    if (roll >= 4) {
      character.modSanity(1);
      return "You wait calmly for your hearing to return.";
    } else if (roll > 0 && roll < 4) {
      character.modKnowlege(-1);
      return "You scream a silent scream. Take 1 Knowledge damage.";
    } else {
      character.modKnowlege(-2);
      return "You freak out. Take 2 Knowledge damage.";
    }
  }
}
