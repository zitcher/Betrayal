package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class Paint implements Item {

  private String name;
  private String description;
  private String logic;

  public Paint() {
    name = "Paint";
    description = "A bucket of paint.";
    logic = "Use this item to desecrate the pentagram. This is"
        + " done by throwing them into the Pentagram Chamber "
        + "from an adjacent room. Doing this counts as 1 space of movement.";
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
    if (!(object instanceof Paint)) {
      return false;
    }

    return this.getName().equals(((Paint) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
