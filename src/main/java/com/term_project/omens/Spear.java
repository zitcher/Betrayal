package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

public class Spear implements Omen {

  private String name;
  private String description;
  private String logic;

  public Spear() {
    name = "Spear";
    description = "A weapon pulsing with power.";
    logic = "You roll 2 additional dice (maximum of 8 dice) when making a Might attack with this weapon.";
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
    return;
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
    if (!(object instanceof Spear)) {
      return false;
    }

    return this.getName().equals(((Spear) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
