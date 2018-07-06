package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

public class Book implements Omen {

  private String name;
  private String description;
  private String logic;

  public Book() {
    name = "Book";
    description = "A diary or lab notes? Ancient script or modern ravings?";
    logic = "Gain 2 Knowledge now.<br />Lose 2 Knowledge if you lose the Book.";
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
    character.modKnowlege(2);
    character.addOmen(this);
  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    return;
  }

  @Override
  public void loss(GameChar character) {
    character.modKnowlege(-2);
    character.removeOmen(this);
    character.getTile().addOmen(this);
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Book)) {
      return false;
    }

    return this.getName().equals(((Book) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
