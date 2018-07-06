package com.term_project.builders;

import java.util.Queue;

/**
 * Interface for building a random ordered deck of the type of card passed in.
 *
 * @author jfacey
 *
 * @param <V>
 *          what type is being built in the deck
 */
public interface Builder<V> {

  /**
   * Method to build a random ordered deck and return it.
   *
   * @return the deck of the type passed in as a list
   */
  Queue<V> build();
}
