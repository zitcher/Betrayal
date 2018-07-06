package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

public class Madman implements Omen {

  private String name;
  private String description;
  private String logic;

  public Madman() {
    name = "Madman";
    description = "Companian.<br />A raving, frothing madman.";
    logic = "Gain 2 Might and lose 1 Sanity now.<br />Lose "
        + "2 Might and gain 1 Sanity if you lose custody "
        + "of the Madman.<br />This omen can't be dropped, traded, or stolen.";
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
    character.modSanity(-1);
    character.modMight(2);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    return;
  }

  @Override
  public void loss(GameChar character) {
    character.modSanity(1);
    character.modMight(-2);
    character.removeOmen(this);
    character.getTile().addOmen(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Madman)) {
      return false;
    }

    return this.getName().equals(((Madman) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
