package com.term_project.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.term_project.house.Direction;
import com.term_project.house.Floor;
import com.term_project.house.GenericTile;
import com.term_project.house.Tile;
import com.term_project.system.MemorySlot;

public class TileBuilder implements Builder<Tile> {
  MemorySlot memory;

  public TileBuilder(MemorySlot memory) {
    this.memory = memory;
  }

  @Override
  public Queue<Tile> build() {
    List<Tile> tileList = new ArrayList<>();
    // tiles with all 4 doors
    List<Direction> d4 = new ArrayList<>();
    d4.add(Direction.NORTH);
    d4.add(Direction.SOUTH);
    d4.add(Direction.EAST);
    d4.add(Direction.WEST);

    // tiles with 3 doors
    List<Direction> d3 = new ArrayList<>();
    d3.add(Direction.NORTH);
    d3.add(Direction.SOUTH);
    d3.add(Direction.EAST);

    // tiles with 2 doors, across
    List<Direction> d2a = new ArrayList<>();
    d2a.add(Direction.NORTH);
    d2a.add(Direction.SOUTH);

    // tiles with 2 doors, next to each other
    List<Direction> d2s = new ArrayList<>();
    d2s.add(Direction.NORTH);
    d2s.add(Direction.EAST);

    // tiles with 1 door
    List<Direction> d1 = new ArrayList<>();
    d1.add(Direction.NORTH);

    // start of all floors
    List<Floor> bga = new ArrayList<>();
    bga.add(Floor.ATTIC);
    bga.add(Floor.BASEMENT);
    bga.add(Floor.GROUND);

    Tile junkRoom = new GenericTile(d4, 0, 0, 1, bga, memory);
    junkRoom.setName("Junk Room");
    tileList.add(junkRoom);

     Tile gameRoom = new GenericTile(d3, 0, 1, 0, bga, memory);
     gameRoom.setName("Game Room");
     tileList.add(gameRoom);

     Tile organRoom = new GenericTile(d2s, 0, 1, 0, bga, memory);
     organRoom.setName("Organ Room");
     tileList.add(organRoom);

     Tile statuaryCorridor = new GenericTile(d2a, 0, 1, 0, bga, memory);
     statuaryCorridor.setName("Statuary Corridor");
     tileList.add(statuaryCorridor);

    Tile creakyHallway = new GenericTile(d4, 0, 0, 0, bga, memory);
    creakyHallway.setName("Creaky Hallway");
    tileList.add(creakyHallway);

    Tile dustyHallway = new GenericTile(d4, 0, 0, 0, bga, memory);
    dustyHallway.setName("Dusty Hallway");
    tileList.add(dustyHallway);

    // start of upper
    List<Floor> a = new ArrayList<>();
    a.add(Floor.ATTIC);

    Tile balcony = new GenericTile(d2a, 0, 0, 1, a, memory);
    balcony.setName("Balcony");
    tileList.add(balcony);

    Tile gallery = new GenericTile(d1, 1, 0, 0, a, memory);
    gallery.setName("Gallery");
    tileList.add(gallery);

     Tile attic = new GenericTile(d1, 0, 1, 0, a, memory);
     attic.setName("Attic");
     tileList.add(attic);

    Tile masterBedroom = new GenericTile(d2s, 0, 0, 0, a, memory);
    masterBedroom.setName("Master Bedroom");
    tileList.add(masterBedroom);

     Tile bedroom = new GenericTile(d2a, 0, 1, 0, a, memory);
     bedroom.setName("Bedroom");
     tileList.add(bedroom);

     Tile tower = new GenericTile(d2a, 0, 1, 0, a, memory);
     tower.setName("Tower");
     tileList.add(tower);

    // start of ground
    List<Floor> g = new ArrayList<>();
    g.add(Floor.GROUND);

    Tile diningRoom = new GenericTile(d2s, 0, 0, 1, g, memory);
    diningRoom.setName("Dining Room");
    tileList.add(diningRoom);

     Tile coalChute = new GenericTile(d1, 0, 0, 0, g, memory);
     coalChute.setName("Coal Chute");
     tileList.add(coalChute);

     Tile graveyard = new GenericTile(d1, 0, 1, 0, g, memory);
     graveyard.setName("Graveyard");
     tileList.add(graveyard);

     Tile gardens = new GenericTile(d2a, 0, 1, 0, g, memory);
     gardens.setName("Gardens");
     tileList.add(gardens);

     Tile patio = new GenericTile(d3, 0, 1, 0, g, memory);
     patio.setName("Patio");
     tileList.add(patio);

     Tile ballroom = new GenericTile(d4, 0, 1, 0, g, memory);
     ballroom.setName("Ballroom");
     tileList.add(ballroom);

    // start of basement
    List<Floor> b = new ArrayList<>();
    b.add(Floor.BASEMENT);

    Tile stairsFromBasement = new GenericTile(d2a, 0, 0, 0, b, memory);
    stairsFromBasement.setName("Stairs From Basement");
    tileList.add(stairsFromBasement);

    Tile pentagramChamber = new GenericTile(d1, 0, 0, 1, b, memory);
    pentagramChamber.setName("Pentagram Chamber");
    tileList.add(pentagramChamber);

    Tile larder = new GenericTile(d2a, 1, 0, 0, b, memory);
    larder.setName("Larder");
    tileList.add(larder);

    Tile chasm = new GenericTile(d2a, 0, 0, 0, b, memory);
    chasm.setName("Chasm");
    tileList.add(chasm);

     Tile crypt = new GenericTile(d1, 0, 1, 0, b, memory);
     crypt.setName("Crypt");
     tileList.add(crypt);

    Tile catacombs = new GenericTile(d2a, 0, 0, 1, b, memory);
    catacombs.setName("Catacombs");
    tileList.add(catacombs);

    Tile furnaceRoom = new GenericTile(d3, 0, 0, 1, b, memory);
    furnaceRoom.setName("Furnace Room");
    tileList.add(furnaceRoom);

     Tile undergroundLake = new GenericTile(d2s, 0, 1, 0, b, memory);
     undergroundLake.setName("Underground Lake");
     tileList.add(undergroundLake);

    Tile wineCellar = new GenericTile(d2a, 1, 0, 0, b, memory);
    wineCellar.setName("Wine Cellar");
    tileList.add(wineCellar);
     
    // start of upper/basement
    List<Floor> ba = new ArrayList<>();
    ba.add(Floor.BASEMENT);
    ba.add(Floor.ATTIC);

    Tile researchLabratory = new GenericTile(d2a, 0, 0, 0, ba, memory);
    researchLabratory.setName("Research Laboratory");
    tileList.add(researchLabratory);

    Tile operatingLabratory = new GenericTile(d2s, 0, 0, 0, ba, memory);
    operatingLabratory.setName("Operating Laboratory");
    tileList.add(operatingLabratory);

    Tile gymnasium = new GenericTile(d2s, 0, 0, 1, ba, memory);
    gymnasium.setName("Gymnasium");
    tileList.add(gymnasium);

     Tile vault = new GenericTile(d1, 0, 1, 0, ba, memory);
     vault.setName("Vault");
     tileList.add(vault);

    Tile storeroom = new GenericTile(d1, 1, 0, 0, ba, memory);
    storeroom.setName("Storeroom");
    tileList.add(storeroom);

    Tile serventsQuarters = new GenericTile(d4, 0, 0, 1, ba, memory);
    serventsQuarters.setName("Servants Quarters");
    tileList.add(serventsQuarters);

    // start of upper/ground
    List<Floor> ga = new ArrayList<>();
    ga.add(Floor.GROUND);
    ga.add(Floor.ATTIC);

     Tile library = new GenericTile(d2s, 0, 1, 0, ga, memory);
     library.setName("Library");
     tileList.add(library);

    Tile collapsedRoom = new GenericTile(d4, 0, 0, 0, ga, memory);
    collapsedRoom.setName("Collapsed Room");
    tileList.add(collapsedRoom);

    Tile charredRoom = new GenericTile(d4, 1, 0, 0, ga, memory);
    charredRoom.setName("Charred Room");
    tileList.add(charredRoom);

    Tile bloodyRoom = new GenericTile(d4, 1, 0, 0, ga, memory);
    bloodyRoom.setName("Bloody Room");
    tileList.add(bloodyRoom);

     Tile conservatory = new GenericTile(d1, 0, 1, 0, ga, memory);
     conservatory.setName("Conservatory");
     tileList.add(conservatory);

     Tile chapel = new GenericTile(d1, 0, 1, 0, ga, memory);
     chapel.setName("Chapel");
     tileList.add(chapel);

    // start of ground/basement
    List<Floor> bg = new ArrayList<>();
    bg.add(Floor.GROUND);
    bg.add(Floor.BASEMENT);

    Tile kitchen = new GenericTile(d2s, 0, 0, 1, bg, memory);
    kitchen.setName("Kitchen");
    tileList.add(kitchen);

    Tile abandonedRoom = new GenericTile(d4, 0, 0, 1, bg, memory);
    abandonedRoom.setName("Abandoned Room");
    tileList.add(abandonedRoom);

    Collections.shuffle(tileList);
    Queue<Tile> tileDeck = new LinkedList<>(tileList);
    return tileDeck;
  }
}
