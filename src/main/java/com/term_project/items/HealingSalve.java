package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class HealingSalve implements Item {

  private String name;
  private String description;
  private String logic;

  public HealingSalve() {
    name = "Healing Salve";
    description = "A sticky paste in a shallow bowl.";
    logic = "If your Might or Speed value is below 3, raise the stats that are below 3 by 1.";
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
    if (character.getMight() < 3) {
      character.modMight(1);
    }
    if (character.getSpeed() < 3) {
      character.modSpeed(1);
    }
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
    if (!(object instanceof HealingSalve)) {
      return false;
    }

    return this.getName().equals(((HealingSalve) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
