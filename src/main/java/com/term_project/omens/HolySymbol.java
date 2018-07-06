package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

public class HolySymbol implements Omen {

  private String name;
  private String description;
  private String logic;

  public HolySymbol() {
    name = "Holy Symbol";
    description = "A symbol of calm in an unsettling world.";
    logic = "Gain 2 Sanity now.<br />Lose 2 Sanity if you lose the Holy Symbol.";
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
    character.addOmen(this);
    character.modSanity(2);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    return;
  }

  @Override
  public void loss(GameChar character) {
    character.modSanity(-2);
    character.removeOmen(this);
    character.getTile().addOmen(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof HolySymbol)) {
      return false;
    }

    return this.getName().equals(((HolySymbol) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}