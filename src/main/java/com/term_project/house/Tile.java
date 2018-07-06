package com.term_project.house;

import java.util.List;
import java.util.Map;

import com.term_project.character.GameChar;
import com.term_project.events.Event;
import com.term_project.items.Item;
import com.term_project.omens.Omen;

/**
 * Interface that outlines what methods a tile needs.
 *
 * @author Zachary Hoffman
 */
public interface Tile {
  /**
   * Returns the name of the tile.
   *
   * @return The name of the tile.
   *
   */
  String getName();

  /**
   * Sets the tiles name.
   *
   * @return The tile object that the function is being called on.
   */
  Tile setName(String newName);

  /**
   * Returns the description of the tile.
   *
   * @return A description of the tile.
   *
   */
  String getDescription();

  /**
   * Sets the tiles description.
   *
   * @return The tile object that the function is being called on.
   */
  Tile setDescription(String newDescription);

  /**
   * Produces the effect described in the description on a character on entry.
   *
   * @param affected
   *          The affected character.
   */
  void enter(GameChar affected);

  /**
   * Produces the effect described in the description on a character on exit.
   *
   * @param affected
   *          The affected character.
   */
  void exit(GameChar affected);

  /**
   * Produces an effect on the given character. Used for duration effects in
   * rooms.
   *
   * @param affected
   *          The affected character.
   */
  void apply(GameChar affected);

  /**
   * Returns a list of omens on this tile.
   *
   * @return A list of Omen.
   */
  Map<String, Omen> getOmens();

  /**
   * Returns a list of items on this tile.
   *
   * @return A list of items.
   */
  Map<String, Item> getItems();

  /**
   * Returns a map of events on this tile.
   *
   * @return A map of Events.
   */
  Map<String, Event> getEvents();

  /**
   * Returns the event mapped to by the given name.
   *
   * @return The event mapped to by the given name
   */
  Event getEvent(String name);

  /**
   * Sets a list of events on this tile.
   *
   */
  void setOmens(Map<String, Omen> omens);

  /**
   * Sets a list of items on this tile.
   *
   */
  void setItems(Map<String, Item> items);

  /**
   * Adds an event.
   *
   */
  void addEvent(Event event);

  /**
   * Returns the number of items needed to be generated.
   *
   * @return the item count
   */
  int getItemCount();

  /**
   * Returns the number of omens needed to be generated.
   *
   * @return the omen count
   */
  int getOmenCount();

  /**
   * Returns the number of events needed to be generated.
   *
   * @return the event count
   */
  int getEventCount();

  /**
   * Returns the Tile's position.
   *
   * @return The tile's position
   */
  Pos getPos();

  /**
   * Returns the tile that is linked to this tile in the North.
   *
   * @return the tile that is linked to this tile in the North.
   */
  Tile getNorth() throws NullPointerException;

  /**
   * Returns the tile that is linked to this tile in the South.
   *
   *
   * @return the tile that is linked to this tile in the South.
   */
  Tile getSouth() throws NullPointerException;

  /**
   * Returns the tile that is linked to this tile in the East.
   *
   * @return the tile that is linked to this tile in the East.
   */
  Tile getEast() throws NullPointerException;

  /**
   * Returns the tile that is linked to this tile in the West.
   *
   * @return the tile that is linked to this tile in the West.
   */
  Tile getWest() throws NullPointerException;

  /**
   * Returns if the tile has a door to the north
   *
   * @return the boolean of if a door exists
   */
  boolean hasNorth();

  /**
   * Returns if the tile has a door to the south
   *
   * @return the boolean of if a door exists
   */
  boolean hasSouth();

  /**
   * Returns if the tile has a door to the east
   *
   * @return the boolean of if a door exists
   */
  boolean hasEast();

  /**
   * Returns if the tile has a door to the west
   *
   * @return the boolean of if a door exists
   */
  boolean hasWest();

  /**
   * Sets the tile that is linked to this tile in the North.
   *
   */
  void addNorth();

  /**
   * Sets the tile that is linked to this tile in the South.
   *
   */
  void addSouth();

  /**
   * Sets the tile that is linked to this tile in the East.
   *
   */
  void addEast();

  /**
   * Sets the tile that is linked to this tile in the West.
   *
   */
  void addWest();

  /**
   * Sets the tile that is linked to this tile in the Up.
   */
  void addUp();

  /**
   * Sets the tile that is linked to this tile in the Down.
   */
  void addDown();

  /**
   * Sets the tile's position.
   *
   */
  void setPos(Pos newPos);

  /**
   * Returns the floors that this tile can be placed in.
   *
   * @return The floors that this tile can be placed in.
   */
  List<Floor> getAvailableFloors();

  /**
   * Rotates the tile clockwise by 90 degrees.
   */
  void rotateClockwise();

  /**
   * Rotates the tile counter clockwise by 90 degrees.
   */
  void rotateCounterClockwise();

  /**
   * Adds an item to the room
   *
   * @param item
   *          the item
   */
  void addItem(Item item);

  /**
   * Adds an omen to the room
   *
   * @param omen
   *          the omen
   */
  void addOmen(Omen omen);

  boolean hasDown();

  boolean hasUp();

  Tile getUp() throws NullPointerException;

  Tile getDown() throws NullPointerException;

  TileBean getBean();

  Item getItem(String item);

  Omen getOmen(String omen);

  Item removeItem(Item item);

  Omen removeOmen(Omen omen);
}
