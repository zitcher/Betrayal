package com.term_project.omens;

import java.util.List;
import java.util.Map;

import com.term_project.character.GameChar;
import com.term_project.game.Dice;

public class CrystalBall implements Omen {

  private String name;
  private String description;
  private String logic;

  public CrystalBall() {
    name = "Crystal Ball";
    description = "Hazy images appear in the glass.";
    logic = "Once during your turn, you can attempt a "
        + "Knowledge roll to peer into the Crystal Ball"
        + ":<br />4+ You see the truth. Gain 1 Sanity.<br />1-3 "
        + "You avert your eyes. Lose 1 Sanity.<br />0 You "
        + "stare into hell. Lose 2 Sanity.";
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getLogic() {
    return logic;
  }

  @Override
  public void add(GameChar character) {
    character.addOmen(this);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    List<Integer> rolls = Dice.roll(character.getKnowlege());

    int roll = Dice.sum(rolls);

    if (roll >= 4) {
      character.modSanity(1);
      variables.put("result", "4+ You see the truth. Gain 1 Sanity.");
    } else if (roll < 4 && roll > 0) {
      character.modSanity(-1);
      variables.put("result", "1-3 You avert your eyes. Lose 1 Sanity.");
    } else {
      character.modSanity(-2);
      variables.put("result", "0 You stare into hell. Lose 2 Sanity.");
    }
    variables.put("rolls", rolls);
  }

  @Override
  public void loss(GameChar character) {
    character.removeOmen(this);
    character.getTile().addOmen(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof CrystalBall)) {
      return false;
    }

    return this.getName().equals(((CrystalBall) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
