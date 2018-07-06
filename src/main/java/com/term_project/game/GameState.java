package com.term_project.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.term_project.builders.CharacterGen;
import com.term_project.character.GameChar;
import com.term_project.game.haunts.GamePhase;
import com.term_project.game.haunts.Lobby;
import com.term_project.system.MemorySlot;

public class GameState {

  // ordered list representing turnorder
  private List<String> idTurnOrder;
  // ordered list of characters
  private List<GameChar> chars;

  private Map<String, GameChar> charMap;

  // integer representing the index in idTurnOrder of whose turn it is
  private Integer currentTurn;

  // data storage for gameState
  private MemorySlot memory;

  private GamePhase phase;

  // character gen
  private List<List<GameChar>> characters;
  private CharacterGen charGen;

  // The current state of the gamestate that is currently being displayed
  // private Display curr;

  public GameState(List<String> ids, MemorySlot memory) {
    // Randomize turn order
    chars = new ArrayList<>();
    charMap = new HashMap<>();
    idTurnOrder = new ArrayList<>(ids);

    Collections.shuffle(idTurnOrder);

    currentTurn = 0;

    // initiate character
    characters = new ArrayList<>();

    // initiate memory
    this.memory = memory;
    memory.setGameState(this);
    memory.setGameCharacters(new ArrayList<>());
    memory.setStringList(ids);
    memory.setTileMap(new HashMap<>());

    phase = new Lobby(memory);

    charGen = new CharacterGen();
    characters = charGen.build();
    Collections.shuffle(characters);
    characters.subList(0, getNumPlayers());
  }

  public Map<String, Object> buildMap(Map<String, String> qm) {
    Map<String, Object> variables = new HashMap<>();
    System.out.println("MAP BUILDING");
    phase.run(null, null, null, variables);
    System.out.println(phase.getDescription());
    return variables;
  }

  public synchronized Map<String, Object> update(Map<String, String> qm) {
    Map<String, Object> variables = new HashMap<>();

    String currentId = idTurnOrder.get(currentTurn);
    GameChar currentPlayer = chars.get(currentTurn);

    // assert(phase instanceof PreHaunt);
    phase.run(qm.get("name"), qm, currentPlayer, variables);

    return variables;
  }

  public void endTurn() {
    currentTurn = (currentTurn + 1) % getNumPlayers();
  }

  public void setPhase(GamePhase newPhase) {
    phase = newPhase;
  }

  public int getNumPlayers() {
    return idTurnOrder.size();
  }

  public int getCurrentTurn() {
    return currentTurn;
  }

  public boolean setPlayersCharacter(String id, String charName) {
    GameChar theCharacter = charGen.getCharactersByName(charName);
    charMap.put(id, theCharacter);
    memory.addGameCharacter(theCharacter);
    if (charMap.keySet().size() == idTurnOrder.size()) {
      for (int i = 0; i < idTurnOrder.size(); i++) {
        chars.add(charMap.get(idTurnOrder.get(i)));
      }
      return true;
    }
    return false;
  }

  public List<GameChar> getCharacterChoice() {
    return characters.remove(0);
  }

  public List<String> getTurnOrder() {
    return idTurnOrder;
  }

  public List<GameChar> getCharacters() {
    return chars;
  }
}
