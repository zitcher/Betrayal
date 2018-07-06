package com.term_project.character;

import java.util.List;
import java.util.Map;

import com.term_project.house.Tile;
import com.term_project.items.Item;
import com.term_project.omens.Omen;

/**
 * Interface that outlines what methods a character needs.
 *
 * @author Zachary Hoffman
 */

/*
 * THIS SHOULD BE A CLASS WE WONT BE ADDING OTHER THINGS TO CHARACTER
 */
public interface GameChar {
  /**
   * Return's the character's name.
   *
   * @return The character's name.
   *
   */
  String getName();

  /**
   * Return's the description of a character.
   *
   * @return A description of a character.
   *
   */
  String getDescription();

  /**
   * Return's the tile the Character is residing on.
   *
   * @return A Tile object representing the tile the character is on.
   *
   */
  Tile getTile();

  Item getItem(String item);

  Omen getOmen(String omen);

  /**
   * Gets the character's might.
   *
   * @return An integer representing the Character's might.
   */
  int getMight();

  /**
   * Gets the character's speed.
   *
   * @return An integer representing the Character's speed.
   */
  int getSpeed();

  /**
   * Gets the character's knowledge.
   *
   * @return An integer representing the Character's knowledge.
   */
  int getKnowlege();

  /**
   * Gets the character's sanity.
   *
   * @return An integer representing the Character's sanity.
   */
  int getSanity();

  /**
   * Sets the might value
   *
   * @param might
   *          value
   */
  void setMight(int might);

  /**
   * Sets the speed value
   *
   * @param speed
   *          value
   */
  void setSpeed(int speed);

  /**
   * Sets the knowledge value
   *
   * @param knowledge
   *          value
   */
  void setKnowlege(int knowledge);

  /**
   * Sets the sanity value
   *
   * @param sanity
   *          value
   */
  void setSanity(int sanity);

  /**
   * Changes the characters tile to the input tile.
   *
   * @param newTile
   *          the tile to move it to
   * @return the new tile
   */
  void setTile(Tile newTile);

  /**
   * Changes the characters might by the inputted amount.
   *
   * @param mightModifier
   *          the amount to change might by.
   * @return An integer representing the Character's might.
   */
  int modMight(int mightModifier);

  /**
   * Changes the characters speed by the inputted amount.
   *
   * @param speedModifier
   *          the amount to change speed by
   * @return An integer representing the Character's speed.
   */
  int modSpeed(int speedModifier);

  /**
   * Changes the characters knowledge by the inputted amount.
   *
   * @param knowledgeModifier
   *          the amount to change knowledge by.
   * @return An integer representing the Character's knowledge.
   */
  int modKnowlege(int knowledgeModifier);

  /**
   * Changes the characters sanity by the inputted amount.
   *
   * @param sanityModifier
   *          the amount to change sanity by.
   * @return An integer representing the character's sanity.
   */
  int modSanity(int sanityModifier);

  /**
   * Adds the input item to a character.
   *
   * @param item
   *          the item to add
   */
  void addItem(Item item);

  /**
   * Adds the input omen to a character.
   *
   * @param omen
   *          the omen to add
   */
  void addOmen(Omen omen);

  /**
   * Remove item from the character.
   *
   * @param item
   *          the item to drop
   */
  void removeItem(Item item);

  /**
   * Remove omen from the character.
   *
   * @param omen
   *          the omen to drop
   */
  void removeOmen(Omen omen);

  /**
   * Sets the might scale
   *
   * @param mightScale
   *          value
   */
  void setMightScale(List<Integer> mightScale);

  /**
   * Sets the speed scale
   *
   * @param speedScale
   *          value
   */
  void setSpeedScale(List<Integer> speedScale);

  /**
   * Sets the knowledge scale
   *
   * @param knowledgeScale
   *          value
   */
  void setKnowledgeScale(List<Integer> knowledgeScale);

  /**
   * Sets the sanity scale
   *
   * @param sanityScale
   *          value
   */
  void setSanityScale(List<Integer> sanityScale);

  /**
   * Gets value of character's stat by name.
   *
   * @param name
   *          The name of a stat: speed, might, knowlege, sanity.
   * @return The value of the given stat.
   */
  int getStatByName(String name);

  CharBean getCharBean();

  Map<String, Item> getItems();

  Map<String, Omen> getOmens();

  boolean getTraitor();

  void setTraitor(boolean traitor);

  List<Item> getItemsList();

  List<Omen> getOmensList();
}
