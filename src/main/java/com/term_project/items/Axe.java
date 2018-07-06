package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class Axe implements Item {

  private String name;
  private String description;
  private String logic;

  public Axe() {

    name = "Axe";
    description = "Very sharp.";
    logic = "You roll 1 additional die (maximum of 8 dice) when making a Might attack with this weapon.";
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
    return;
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
    if (!(object instanceof Axe)) {
      return false;
    }

    return this.getName().equals(((Axe) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
