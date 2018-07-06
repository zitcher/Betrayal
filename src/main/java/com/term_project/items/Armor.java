package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class Armor implements Item {

  private String name;
  private String description;
  private String logic;

  public Armor() {

    name = "Armor";
    description = "It's just prop armor from a Renaissance fair, but it's still metal.";
    logic = "Any time you take physical damage, take 1 point less of damage.";

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
    if (!(object instanceof Armor)) {
      return false;
    }

    return this.getName().equals(((Armor) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
