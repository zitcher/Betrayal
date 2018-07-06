package com.term_project.items;

import java.util.List;
import java.util.Map;

import com.term_project.character.GameChar;
import com.term_project.game.Dice;

public class Bottle implements Item {

  private String name;
  private String description;
  private String logic;

  public Bottle() {
    name = "Bottle";
    description = "An opaque vial containing a black liquid";
    logic = "Once during your turn while the haunt is revealed, "
        + "you can roll 3 dice and drink from the Bottle:<br />6 "
        + "Gain 2 Might and 3 Speed.<br />5 Gain 2 Might and 2 "
        + "Speed.<br />4 Gain 2 Knowledge and 2 Sanity.<br />3 Gain "
        + "1 Knowledge and lose 1 Might.<br />2 Lose 2 Knowledge "
        + "and 2 Sanity.<br />1 Lose 2 Might and 2 Speed.<br />0 Lose "
        + "2 from each trait.";
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public String getLogic() {
    return logic;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public void add(GameChar character) {
    character.addItem(this);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {

    List<Integer> rolls = Dice.roll(3);

    int sum = Dice.sum(rolls);

    if (sum == 6) {
      // changing for ease of use
      character.modSpeed(3);
      character.modMight(2);
      variables.put("result", "6 Gain 2 Might and 3 Speed.");
    } else if (sum == 5) {
      character.modSpeed(2);
      character.modMight(2);
      variables.put("result", "5 Gain 2 Might and 2 Speed.");
    } else if (sum == 4) {
      character.modKnowlege(2);
      character.modSanity(2);
      variables.put("result", "4 Gain 2 Knowledge and 2 Sanity.");
    } else if (sum == 3) {
      character.modKnowlege(1);
      character.modMight(-1);
      variables.put("result", "3 Gain 1 Knowledge and lose 1 Might.");
    } else if (sum == 2) {
      character.modKnowlege(-2);
      character.modSanity(-2);
      variables.put("result", "2 Lose 2 Knowledge and 2 Sanity.");
    } else if (sum == 1) {
      character.modSpeed(-2);
      character.modMight(-2);
      variables.put("result", "1 Lose 2 Might and 2 Speed.");
    } else {
      character.modSpeed(-2);
      character.modMight(-2);
      character.modKnowlege(-2);
      character.modSanity(-2);
      variables.put("result", "0 Lose 2 from each trait.");
    }
    variables.put("rolls", rolls);
  }

  @Override
  public void loss(GameChar character) {
    character.removeItem(this);
    character.getTile().addItem(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Bottle)) {
      return false;
    }

    return this.getName().equals(((Bottle) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
