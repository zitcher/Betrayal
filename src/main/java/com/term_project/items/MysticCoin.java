package com.term_project.items;

import java.util.List;
import java.util.Map;

import com.term_project.character.GameChar;
import com.term_project.game.Dice;

public class MysticCoin implements Item {

  private String name;
  private String description;
  private String logic;

  public MysticCoin() {
    name = "Mystic Coin";
    description = "Heads or Tails?";
    logic = "Once during your turn, you can roll 1 die to flip the "
        + "coin:<br />2 It's heads. Gain 1 point in Might.<br />1 It's tails. "
        + "Gain 1 point in Knowledge.<br />0 It landed on its edge! Lose "
        + "1 point in Might and 1 in Knowledge.";
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
  public void add(GameChar character) {
    character.addItem(this);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    List<Integer> rolls = Dice.roll(1);

    int roll = rolls.get(0);

    if (roll == 2) {

      character.modMight(1);
      variables.put("result", "2 It's heads. Gain 1 point in Might.");
    } else if (roll == 1) {

      character.modKnowlege(1);
      variables.put("result", "1 It's tails. Gain 1 point in Knowledge.");
    } else if (roll == 0) {

      character.modMight(-1);
      character.modKnowlege(-1);
      variables.put("result",
          "0 It landed on its edge! Lose 1 point in Might and 1 in Knowledge.");
    }
    variables.put("rolls", rolls);
  }

  @Override
  public void loss(GameChar character) {
    character.removeItem(this);
    character.getTile().addItem(this);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof MysticCoin)) {
      return false;
    }

    return this.getName().equals(((MysticCoin) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
