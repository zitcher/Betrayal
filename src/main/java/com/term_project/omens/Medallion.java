package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

public class Medallion implements Omen {

  private String name;
  private String description;
  private String logic;

  public Medallion() {
    name = "Medallion";
    description = "A medallion inscribed with a pentagram.";
    logic = "You are immune to the effects of the Pentagram Chamber, Crypt, and Graveyard.";
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
    if (!(object instanceof Medallion)) {
      return false;
    }

    return this.getName().equals(((Medallion) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
