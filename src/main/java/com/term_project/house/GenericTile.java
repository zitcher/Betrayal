package com.term_project.house;

import java.util.List;
import java.util.Map;

import com.term_project.character.GameChar;
import com.term_project.system.MemorySlot;

/**
 * Generic tile with no entry and exit events
 *
 * @author Zachary Hoffman
 */

public class GenericTile extends AbstractTile implements Tile {
  private String description;

  public GenericTile(List<Direction> doors, int items,
      int events, int omens, List<Floor> availableFloors, MemorySlot memory) {

    super(doors, items, events, omens, availableFloors, memory);
  }

  @Override
  public Tile setDescription(String newDescription) {
    description = newDescription;
    return this;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public void enter(GameChar affected) {
    return;
  }

  @Override
  public void exit(GameChar affected) {
    return;
  }

  @Override
  public void apply(GameChar affected) {
    return;
  }
}
