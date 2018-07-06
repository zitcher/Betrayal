package com.term_project.omens;

import java.util.Map;

import com.term_project.character.GameChar;

/**
 * Interface that outlines what methods an omen needs.
 *
 * @author Zachary Hoffman
 *
 */
public interface Omen {
  /**
   * Return's the description of the omen.
   *
   * @return A description of the omen.
   *
   */
  String getDescription();

  /**
   * Return's the name of the omen.
   *
   * @return the name of the omen
   */
  String getName();

  /**
   * Return's the logical description of the omen.
   *
   * @return The way the omen functions
   *
   */
  String getLogic();

  /**
   * Called on the pickup of an omen, producing the effect of the omen
   */
  void add(GameChar character);

  /**
   * Uses the omen producing the effect described in the description on a
   * character/die amount/gamestate.
   *
   */
  void use(GameChar character, Map<String, Object> variables);

  /**
   * Called on loss of an omen, losing the effect of that omen
   *
   */
  void loss(GameChar character);
}
