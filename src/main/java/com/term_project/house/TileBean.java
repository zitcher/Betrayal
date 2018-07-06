package com.term_project.house;

import java.util.List;
import java.util.Map;

/**
 * Generic tile with no entry and exit events
 *
 * @author Zachary Hoffman
 */

public class TileBean {
  private Pos pos;
  private Map<Direction, Pos> doors;
  private List<Direction> availableDoors;
  private int itemCount;
  private int omenCount;
  private int eventCount;
  private String name;

  public TileBean(List<Direction> availableDoors, int items, int events,
      int omens, Pos pos, String name) {
    this.itemCount = items;
    this.omenCount = omens;
    this.eventCount = events;

    this.availableDoors = availableDoors;
    this.pos = pos;
    this.name = name;
  }
}
