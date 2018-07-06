package com.term_project.character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.term_project.house.Tile;
import com.term_project.items.Item;
import com.term_project.omens.Omen;

public class Cultist implements GameChar {

  private int might;
  private int speed;
  private int knowledge;
  private int sanity;
  private Tile currentTile;
  private Map<String, Item> items;
  private Map<String, Omen> omens;
  private List<Integer> mightScale;
  private List<Integer> speedScale;
  private List<Integer> knowledgeScale;
  private List<Integer> sanityScale;
  private List<Item> itemList;
  private List<Omen> omenList;
  private boolean traitor;
  private String name;

  public Cultist(String name) {
    this.name = name;
    might = 4;
    speed = 4;
    knowledge = 0;
    sanity = 4;

    currentTile = null;
    items = new HashMap<>();
    omens = new HashMap<>();
    mightScale = new ArrayList<>();
    speedScale = new ArrayList<>();
    knowledgeScale = new ArrayList<>();
    sanityScale = new ArrayList<>();
    itemList = new ArrayList<>();
    omenList = new ArrayList<>();
    traitor = false;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public String getDescription() {
    return "A cultist";
  }

  @Override
  public Tile getTile() {
    return currentTile;
  }

  @Override
  public Item getItem(String itemName) {
    return items.get(itemName);
  }

  @Override
  public Omen getOmen(String omenName) {
    return omens.get(omenName);
  }

  @Override
  public int getMight() {
    return might;
  }

  @Override
  public int getSpeed() {
    return speed;
  }

  @Override
  public int getKnowlege() {
    return knowledge;
  }

  @Override
  public int getSanity() {
    return sanity;
  }

  @Override
  public void setMight(int might) {
    this.might = might;
  }

  @Override
  public void setSpeed(int speed) {
    this.speed = speed;
  }

  @Override
  public void setKnowlege(int knowledge) {
    this.knowledge = knowledge;
  }

  @Override
  public void setSanity(int sanity) {
    this.sanity = sanity;
  }

  @Override
  public void setTile(Tile newTile) {
    this.currentTile = newTile;
  }

  @Override
  public int modMight(int mightModifier) {
    return this.might = this.might + mightModifier;
  }

  @Override
  public int modSpeed(int speedModifier) {
    return this.speed = this.speed + speedModifier;
  }

  @Override
  public int modKnowlege(int knowledgeModifier) {
    return this.knowledge = this.knowledge + knowledgeModifier;
  }

  @Override
  public int modSanity(int sanityModifier) {
    return this.sanity = this.sanity + sanityModifier;
  }

  @Override
  public void addItem(Item item) {
    items.put(item.getName(), item);
  }

  @Override
  public void addOmen(Omen omen) {
    omens.put(omen.getName(), omen);
  }

  @Override
  public void removeItem(Item item) {
    items.remove(item);
  }

  @Override
  public void removeOmen(Omen omen) {
    omens.remove(omen);
  }

  @Override
  public void setMightScale(List<Integer> mightScale) {
    this.mightScale = mightScale;
  }

  @Override
  public void setSpeedScale(List<Integer> speedScale) {
    this.speedScale = speedScale;
  }

  @Override
  public void setKnowledgeScale(List<Integer> knowledgeScale) {
    this.knowledgeScale = knowledgeScale;
  }

  @Override
  public void setSanityScale(List<Integer> sanityScale) {
    this.sanityScale = sanityScale;
  }

  @Override
  public int getStatByName(String name) {
    switch (name) {
    case "MIGHT":
      return getMight();

    case "SPEED":
      return getSpeed();

    case "SANITY":
      return getSanity();

    case "KNOWLEDGE":
      return getKnowlege();

    default:
      throw new NullPointerException(
          "Given stat " + name + " doesn't exist.");
    }
  }

  @Override
  public CharBean getCharBean() {
    return new CharBean(getName(), might, speed, knowledge, sanity,
        mightScale, speedScale, knowledgeScale, sanityScale,
        currentTile.getBean(), new ArrayList(items.values()),
        new ArrayList(omens.values()));
  }

  @Override
  public Map<String, Item> getItems() {
    return items;
  }

  @Override
  public Map<String, Omen> getOmens() {
    return omens;
  }

  @Override
  public boolean getTraitor() {
    return traitor;
  }

  @Override
  public void setTraitor(boolean traitor) {
    this.traitor = traitor;
  }

  @Override
  public boolean equals(Object object) {
    if (object == this)
      return true;
    if (!(object instanceof Cultist)) {
      return false;
    }

    return this.getName().equals(((Cultist) object).getName());
  }

  @Override
  public int hashCode() {
    return this.getName().hashCode();
  }

  @Override
  public List<Item> getItemsList() {
    return itemList;
  }

  @Override
  public List<Omen> getOmensList() {
    return omenList;
  }
}
