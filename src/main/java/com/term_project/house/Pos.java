package com.term_project.house;

import com.google.common.base.Objects;

/**
 * This class represents a position in the house.
 *
 * @author Zachary Hoffman
 */
public class Pos {
  private final int x;
  private final int y;
  private final Floor floor;

  public Pos(int x, int y, Floor floor) {
      this.x = x;
      this.y = y;
      this.floor = floor;
  }

  /** Return's an integer representing a x position.
   * @return An integer representing a x position.
   *
   */
  public int getX() {
    return x;
  }

  /** Return's an integer representing a y position.
   * @return An integer representing a y position.
   */
  public int getY() {
     return y;
   }

   /** Gets the floor of this position.
    *
    */
  public Floor getFloor() {
    return floor;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(x, y, floor);
  }

  //how to check for equality
  @Override
  public boolean equals(Object obj) {
    if (obj == this) {
      return true;
    }

    if (!(obj instanceof Pos)) {
      return false;
    }

    Pos aPos = (Pos) obj;

    //Checks if name, id, and location match
    return aPos.getX() == this.getX()
           && aPos.getY() == this.getY()
           && aPos.getFloor() == this.getFloor();
  }
}
