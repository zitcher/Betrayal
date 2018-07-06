package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

public class Girl implements Omen {

  private String name;
  private String description;
  private String logic;

  public Girl() {
    name = "Girl";
    description = "Companion.<br />A girl.<br />Trapped.<br />Alone.<br />You free her!";
    logic = "Gain 1 Sanity and 1 Knowledge now.<br />Lose 1 Sanity "
        + "and 1 Knowledge if you lose custody of the Girl.<br />This "
        + "omen can't be dropped, traded, or stolen.";
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
    character.modSanity(1);
    character.modKnowlege(1);
    character.addOmen(this);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    return;
  }

  @Override
  public void loss(GameChar character) {
    character.modSanity(-1);
    character.modKnowlege(-1);
    character.removeOmen(this);
    character.getTile().addOmen(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Girl)) {
      return false;
    }

    return this.getName().equals(((Girl) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
