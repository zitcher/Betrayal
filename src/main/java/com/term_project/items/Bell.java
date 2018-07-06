package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class Bell implements Item {

  private String name;
  private String description;
  private String logic;

  public Bell() {
    name = "Bell";
    description = "A brass bell that makes a resonant clang.";
    logic = "Gain 1 Sanity now.<br />Lose 1 Sanity if you lose the Bell.";
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
    character.modSanity(1);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    return;
  }

  @Override
  public void loss(GameChar character) {
    character.modSanity(-1);
    character.removeItem(this);
    character.getTile().addItem(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Bell)) {
      return false;
    }

    return this.getName().equals(((Bell) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
