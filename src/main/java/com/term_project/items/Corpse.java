package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class Corpse implements Item {

  private String name;
  private String description;
  private String logic;

  public Corpse() {
    name = "Corpse";
    description = "It's a corpse.";
    logic = "Sacrifice this corpse in the Pentagram Chamber by bringing it "
        + "there. It is worth 4 of the 13 sacrifice points needed to "
        + "summon the dread god.";
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
    if (!(object instanceof Corpse)) {
      return false;
    }

    return this.getName().equals(((Corpse) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
