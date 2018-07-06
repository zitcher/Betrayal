package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class Amulet implements Item {

  private String name;
  private String description;
  private String logic;

  public Amulet() {
    name = "Amulet of the Ages";
    description = "Ancient silver and inlaid gems, inscribed with blessings.";
    logic = "Gain 1 Might, 1 Speed, 1 Knowledge, and 1 Sanity now.<br />Lose 3 "
        + "Might, 3 Speed, 3 Knowledge, and 3 Sanity if you lose the amulet.";
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
    character.modKnowlege(1);
    character.modMight(1);
    character.modSanity(1);
    character.modSpeed(1);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    return;

  }

  @Override
  public void loss(GameChar character) {
    character.modKnowlege(-3);
    character.modMight(-3);
    character.modSanity(-3);
    character.modSpeed(-3);
    character.removeItem(this);
    character.getTile().addItem(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Amulet)) {
      return false;
    }

    return this.getName().equals(((Amulet) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
