package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

public class Skull implements Omen {

  private String name;
  private String description;
  private String logic;

  public Skull() {
    name = "Skull";
    description = "A skull, cracked and missing teeth.";
    logic = "If you take mental damage, you can take all of it as physical damage instead.";
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
    if (!(object instanceof Skull)) {
      return false;
    }

    return this.getName().equals(((Skull) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
