package com.term_project.game.haunts;

import java.util.List;
import java.util.Map;

import com.term_project.character.GameChar;

public interface GamePhase {
  /**
   * Executes an action by the specified character.
   *
   * @param character
   *          The character doing the specified action.
   * @param action
   *          The action to be executed.
   * @param specs
   *          Special specifications for said action.
   */
  void run(String name, Map<String, String> qm, GameChar character,
      Map<String, Object> variables);

  void addActions(GameChar character, Map<String, Object> variables);

  List<String> getExplorersDescription();

  List<String> getTraitorDescription();

  String getDescription();

  void setup(GameChar character, Map<String, Object> variables);
}
