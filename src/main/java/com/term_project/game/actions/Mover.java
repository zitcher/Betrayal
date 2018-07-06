package com.term_project.game.actions;

import java.util.Map;

import com.term_project.character.GameChar;
import com.term_project.house.Floor;
import com.term_project.house.Pos;
import com.term_project.house.Tile;
import com.term_project.system.MemorySlot;

public class Mover {
  private Boolean finished;
  private String dir;
  private Tile toAdd;
  private MemorySlot memory;

  public Mover(MemorySlot memory) {
    this.memory = memory;
    finished = true;
    dir = null;
  }

  public Boolean getFinished() {
    return finished;
  }

  public void run(String direction, GameChar character,
      Map<String, Object> variables) throws NullPointerException {
    finished = false;
    dir = direction;

    assert(direction.equals("NORTH") || direction.equals("SOUTH")
        || direction.equals("EAST") || direction.equals("WEST"))
        || direction.equals("UP") || direction.equals("DOWN");

    Tile currentTile = character.getTile();
    System.out.println(currentTile.getName());

    switch (direction) {
    case "NORTH":
      try {
        Tile newTile = currentTile.getNorth();
        moveTileHandler(newTile, character, variables);
      } catch (NullPointerException e) {
        throw new NullPointerException("No available tile to the north.");
      }
      break;

    case "SOUTH":
      try {
        Tile newTile = currentTile.getSouth();
        moveTileHandler(newTile, character, variables);
      } catch (NullPointerException e) {
        throw new NullPointerException("No available tile to the south.");
      }
      break;

    case "EAST":
      try {
        Tile newTile = currentTile.getEast();
        if (newTile == null)
          System.out.println("etile: null");
        else 
          System.out.println("etile: " + newTile.getName());
        moveTileHandler(newTile, character, variables);
      } catch (NullPointerException e) {
        throw new NullPointerException("No available tile to the east.");
      }
      break;

    case "WEST":
      try {
        Tile newTile = currentTile.getWest();
        moveTileHandler(newTile, character, variables);
      } catch (NullPointerException e) {
        throw new NullPointerException("No available tile to the west.");
      }
      break;

    case "UP":
      try {
        Tile newTile = currentTile.getUp();
        assert(newTile != null);
        moveTileHandler(newTile, character, variables);
      } catch (NullPointerException e) {
        throw new NullPointerException("No available tile to the up.");
      }
      break;

    case "DOWN":
      try {
        Tile newTile = currentTile.getDown();
        assert(newTile != null);
        moveTileHandler(newTile, character, variables);
      } catch (NullPointerException e) {
        throw new NullPointerException("No available tile to the down.");
      }
      break;
    }
  }

  private void moveTileHandler(Tile newTile, GameChar character,
      Map<String, Object> variables) {
    // if its a empty tile, we need to generate a new tile for the frontend to
    // position.
    if (newTile == null) {

      // We "prep" it to add bc player still needs to choose rotation
      prepTileToAdd(newTile, character, variables);
    } else {
      // otherwise the tile already exists and we simply end this action
      // and move the character.
      finished = true;
      character.setTile(newTile);
      newTile.enter(character);
    }
  }

  private void prepTileToAdd(Tile newTile, GameChar character,
      Map<String, Object> variables) {
    // Tile x = memory.getTiles().poll();
    // while (x != null) {
    // System.out.println("xname: " + x.getName());
    // System.out.println("xn: " + x.hasNorth());
    // System.out.println("xs: " + x.hasSouth());
    // System.out.println("xe: " + x.hasEast());
    // System.out.println("xw: " + x.hasWest());
    // x = memory.getTiles().poll();
    // }

    // pull a tile from the top of the deck
    toAdd = memory.getTiles().poll();

    // get info on the current position of character
    Tile currentTile = character.getTile();
    Pos curPos = currentTile.getPos();
    Floor currentFloor = curPos.getFloor();

    // this will be the character's new position/position to place tile
    Pos newPos;

    // find tile that fits with character's floor
    while (true) {
      // if we can place the found tile on the current floor break loop
      if (toAdd.getAvailableFloors().contains(currentFloor)) {
        break;
      } else {
        // otherwise loop throught the deck searching for a tile that can
        // be added to the current floor
        memory.getTiles().add(toAdd);
        toAdd = memory.getTiles().poll();
      }
    }

    System.out.println("xname: " + toAdd.getName());
    System.out.println("xn: " + toAdd.hasNorth());
    System.out.println("xs: " + toAdd.hasSouth());
    System.out.println("xe: " + toAdd.hasEast());
    System.out.println("xw: " + toAdd.hasWest());

    // now that we've retrieved a tile on the same floor, we must
    // set the tile's position
    switch (dir) {
    case "NORTH":
      newPos = new Pos(curPos.getX(), curPos.getY() - 1, currentFloor);
      toAdd.setPos(newPos);
      break;

    case "SOUTH":
      newPos = new Pos(curPos.getX(), curPos.getY() + 1, currentFloor);
      toAdd.setPos(newPos);
      break;

    case "EAST":
      newPos = new Pos(curPos.getX() + 1, curPos.getY(), currentFloor);
      toAdd.setPos(newPos);
      break;

    case "WEST":
      newPos = new Pos(curPos.getX() - 1, curPos.getY(), currentFloor);
      toAdd.setPos(newPos);
      break;
    }

    variables.put("newtile", toAdd.getBean());
  }

  public void addTile(GameChar character, Integer numClockwiseRotations,
      Map<Pos, Tile> tileMap) throws RuntimeException {
    for (int i = 0; i < numClockwiseRotations; i++) {
      toAdd.rotateClockwise();
    }

    // make sure door links to room we came from, otherwise throw an error
    // switch (dir) {
    // case "NORTH":
    //   if (!toAdd.hasSouth()) {
    //     throw new RuntimeException("Must link to previous door.");
    //   }
    //   break;

    // case "SOUTH":
    //   if (!toAdd.hasNorth()) {
    //     throw new RuntimeException("Must link to previous door.");
    //   }
    //   break;

    // case "EAST":
    //   if (!toAdd.hasWest()) {
    //     throw new RuntimeException("Must link to previous door.");
    //   }
    //   break;

    // case "WEST":
    //   if (!toAdd.hasEast()) {
    //     throw new RuntimeException("Must link to previous door.");
    //   }
    //   break;
    // }

    // If it does add the tile and link it to surrounding tiles.

    // position of tile being added
    Pos toAddPos = toAdd.getPos();

    // link tile north of added tile to the tile if a door exists.
    Tile northOfAdded = tileMap.get(new Pos(toAddPos.getX(),
        toAddPos.getY() - 1, toAddPos.getFloor()));
    // make sure there is a tile to link and a door to link through
    if (northOfAdded != null && northOfAdded.hasSouth()) {
      if (toAdd.hasNorth()) {
        toAdd.addNorth();
        northOfAdded.addSouth();
      }
    } else if (toAdd.hasNorth() && northOfAdded == null)
      toAdd.addNorth();

    // link tile south of added tile to the tile if a door exists.
    Tile southOfAdded = tileMap.get(new Pos(toAddPos.getX(),
        toAddPos.getY() + 1, toAddPos.getFloor()));
    if (southOfAdded != null && southOfAdded.hasNorth()) {
      if (toAdd.hasSouth()) {
        toAdd.addSouth();
        southOfAdded.addNorth();
      }
    } else if (toAdd.hasSouth() && southOfAdded == null)
      toAdd.addSouth();

    // link tiles north of added tile to the tile if a door exists.
    Tile eastOfAdded = tileMap.get(new Pos(toAddPos.getX() + 1,
        toAddPos.getY(), toAddPos.getFloor()));
    if (eastOfAdded != null && eastOfAdded.hasWest()) {

      if (toAdd.hasEast()) {
        toAdd.addEast();
        eastOfAdded.addWest();
      }
    } else if (toAdd.hasEast() && eastOfAdded == null)
      toAdd.addEast();

    // link tiles north of added tile to the tile if a door exists.
    Tile westOfAdded = tileMap.get(new Pos(toAddPos.getX() - 1,
        toAddPos.getY(), toAddPos.getFloor()));
    if (westOfAdded != null && westOfAdded.hasEast()) {
      if (toAdd.hasWest()) {
        toAdd.addWest();
        westOfAdded.addEast();
      }
    } else if (toAdd.hasWest() && westOfAdded == null)
      toAdd.addWest();

    // Add character to the tile we just added!
    character.setTile(toAdd);
    toAdd.enter(character);

    // Finally add tile to tileMap
    memory.getTileMap().put(toAdd.getPos(), toAdd);
    memory.getTileList().add(toAdd);
    finished = true;
  }
}
