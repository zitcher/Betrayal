package com.term_project.items;

import java.util.Map;

import com.term_project.character.GameChar;

public class Cat implements Item {

  private String name;
  private String description;
  private String logic;

  public Cat() {
    name = "Cat";
    description = "The Cat meows. Why is it here?";
    logic = "Gain 2 Sanity now.<br />Lose 2 Sanity when you lose the Cat.";
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
  public void add(GameChar character) {
    character.modSanity(2);
    character.addItem(this);

  }

  @Override
  public void use(GameChar character, Map<String, Object> variables) {
    return;
  }

  @Override
  public void loss(GameChar character) {
    character.modSanity(-2);
    character.removeItem(this);
    character.getTile().addItem(this);
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Cat)) {
      return false;
    }

    return this.getName().equals(((Cat) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }
}
