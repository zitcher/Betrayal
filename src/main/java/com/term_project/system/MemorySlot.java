package com.term_project.system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;

import com.term_project.builders.EventsBuilder;
import com.term_project.builders.ItemsBuilder;
import com.term_project.builders.OmensBuilder;
import com.term_project.builders.TileBuilder;
import com.term_project.character.CharBean;
import com.term_project.character.GameChar;
import com.term_project.events.Event;
import com.term_project.game.GameState;
import com.term_project.house.Pos;
import com.term_project.house.Tile;
import com.term_project.house.TileBean;
import com.term_project.items.Item;
import com.term_project.omens.Omen;

/**
 * This is a class that will be used to hold a peice of information. It is used
 * to store different types of information in the Environment's hashmap.
 *
 * @author ZacharyHoffman
 */
public final class MemorySlot {
  private Map<String, Boolean> bools;
  private Map<String, Integer> ints;
  private List<String> stringList;
  private DB database;

  private Queue<Item> items;
  private Queue<Omen> omens;
  private Queue<Event> events;
  private Queue<Tile> tiles;
  private Map<Pos, Tile> tileMap;
  private List<Tile> tileList;
  private GameState gameState;
  private List<GameChar> gameCharacters;

  /*
   * Frontend needs List<Events> *SPECIAL EVENTS Attack, Win, Give* List<Omens>
   * List<item> Tilemap List<Character>
   */

  /**
   * Initializes a MemorySlot.
   */
  public MemorySlot() {
    bools = new HashMap<String, Boolean>();
    ints = new HashMap<String, Integer>();
    stringList = new ArrayList<>();
    // initiate decks
    items = new ItemsBuilder().build();
    omens = new OmensBuilder().build();
    events = new EventsBuilder().build();
    tiles = new TileBuilder(this).build();
    tileList = new ArrayList<>();
  }

  /**
   * Stores a boolean associated with the given key.
   *
   * @param key
   *          The key to associate the bool with.
   * @param newBool
   *          The boolean to store.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setBool(String key, Boolean newBool) {
    bools.put(key, newBool);
    return this;
  }

  /**
   * Stores a integer associated with the given key.
   *
   * @param key
   *          The key to associate the integer with.
   * @param newInt
   *          The integer to store.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setInt(String key, Integer newInt) {
    ints.put(key, newInt);
    return this;
  }

  /**
   * Stores a list of strings.
   *
   * @param list
   *          The list to store.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setStringList(List<String> list) {
    stringList = list;
    return this;
  }

  /**
   * Stores a database DB.
   *
   * @param db
   *          The database to store.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setDatabase(DB db) {
    database = db;
    return this;
  }

  /**
   * Stores the items deck
   *
   * @param itemsQueue
   *          The items deck.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setItems(Queue<Item> itemsQueue) {
    items = itemsQueue;
    return this;
  }

  /**
   * Stores the omens deck
   *
   * @param omensQueue
   *          The omens deck.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setOmens(Queue<Omen> omensQueue) {
    omens = omensQueue;
    return this;
  }

  /**
   * Stores the events deck
   *
   * @param eventsQueue
   *          The events deck.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setEvents(Queue<Event> eventsQueue) {
    events = eventsQueue;
    return this;
  }

  /**
   * Stores the tiles deck
   *
   * @param tilesQueue
   *          The tiles deck.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setTiles(Queue<Tile> tilesQueue) {
    tiles = tilesQueue;
    return this;
  }

  /**
   * Stores the current game map of tiles.
   *
   * @param houseMap
   *          The current tiles making up the house.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setTileMap(Map<Pos, Tile> houseMap) {
    tileMap = houseMap;
    return this;
  }

  /**
   * Stores the gamestate.
   *
   * @param game
   *          The gamestate this MemorySlot manages.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setGameState(GameState game) {
    gameState = game;
    return this;
  }

  /**
   * Stores game characters;
   *
   * @param gameCharactersList
   *          List of characters in the game.
   * @return The MemorySlot that was just modified.
   */
  public MemorySlot setGameCharacters(List<GameChar> gameCharactersList) {
    gameCharacters = gameCharactersList;
    return this;
  }

  public MemorySlot addGameCharacter(GameChar aChar) {
    gameCharacters.add(aChar);
    return this;
  }

  /**
   * Gets the list of strings this object stores.
   *
   * @return The list of strings this object stores.
   */
  public List<String> getStringList() {
    return stringList;
  }

  /**
   * Gets the boolean associated with the given key.
   *
   * @param key
   *          The key associated with the boolean.
   * @return The boolean this object stores or null if not found.
   */
  public boolean getBool(String key) {
    return bools.get(key);
  }

  /**
   * Gets the integer associated with the given key.
   *
   * @param key
   *          The key associated with the int.
   * @return The boolean this object stores or null if not found.
   */
  public int getInt(String key) {
    return ints.get(key);
  }

  /**
   * Gets the database this object stores.
   *
   * @return The database this object stores.
   */
  public DB getDatabase() {
    return database;
  }

  /**
   * Gets the items deck being stored in memory.
   *
   * @return The items deck being stored in memory.
   */
  public Queue<Item> getItems() {
    return items;
  }

  /**
   * Gets the omen deck being stored in memory.
   *
   * @return The omen deck being stored in memory.
   */
  public Queue<Omen> getOmens() {
    return omens;
  }

  /**
   * Gets the event deck being stored in memory.
   *
   * @return The event deck being stored in memory.
   */
  public Queue<Event> getEvents() {
    return events;
  }

  /**
   * Gets the tile deck being stored in memory.
   *
   * @return The tile deck being stored in memory.
   */
  public Queue<Tile> getTiles() {
    return tiles;
  }

  /**
   * Gets the tileMap being stored in memory.
   *
   * @return The tileMap being stored in memory.
   */
  public Map<Pos, Tile> getTileMap() {
    return tileMap;
  }

  /**
   * Gets the tileMap being stored in memory as tileBeans.
   *
   * @return A list of tileBeans from the tileMap.
   */
  public List<TileBean> getTileBeans() {
    List<TileBean> tb = new ArrayList<>();
    for (Entry<Pos, Tile> e : tileMap.entrySet()) {
      tb.add(e.getValue().getBean());
    }
    return tb;
  }

  /**
   * Gets the gamestate this MemorySlot manages.
   *
   * @return The gamestate this MemorySlot manages.
   */
  public GameState getGameState() {
    return gameState;
  }

  /**
   * Gets the List of Game Characters.
   *
   * @return The list of gameCharacters.
   */
  public List<GameChar> getGameCharacters() {
    return gameCharacters;
  }

  /**
   * Gets the list of characters as charBeans.
   *
   * @return A list of characters as charBeans.
   */
  public List<CharBean> getCharBeans() {
    List<CharBean> cb = new ArrayList<>();
    for (GameChar gc : gameCharacters) {
      cb.add(gc.getCharBean());
    }
    return cb;
  }

  public List<Tile> getTileList() {
    return tileList;
  }
}
