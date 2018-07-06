package com.term_project.game.haunts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.term_project.character.GameChar;
import com.term_project.events.Event;
import com.term_project.game.Dice;
import com.term_project.game.actions.Mover;
import com.term_project.house.Tile;
import com.term_project.house.TileBean;
import com.term_project.items.Item;
import com.term_project.omens.Omen;
import com.term_project.system.MemorySlot;

public class PreHaunt implements GamePhase {
  private MemorySlot memory;

  private String mode;
  private Integer phase;

  private Integer omenCount;

  private Mover move;
  Map<String, Integer> remaining;

  private List<String> toResolve;

  public PreHaunt(MemorySlot memory) {
    this.memory = memory;
    mode = "start";
    phase = 0;

    omenCount = 0;

    move = new Mover(memory);
    remaining = new HashMap<>();
    toResolve = new ArrayList<>();
  }

  @Override
  public void addActions(GameChar character,
      Map<String, Object> variables) {
    List<String> actions = new ArrayList<>();
    Tile tile = character.getTile();

    if (tile.getEvents().size() > 0) {
      actions.add("event");
    }

    if (tile.getItems().size() > 0) {
      actions.add("pickup item");
    }

    if (tile.getOmens().size() > 0) {
      actions.add("pickup omen");
    }

    if (character.getItems().size() > 0) {
      actions.add("drop item");
      actions.add("use item");
    }

    if (character.getOmens().size() > 0) {
      actions.add("drop omen");
      actions.add("use omen");
    }

    if (remaining.get("move") > 0) {
      actions.add("move");
    }

    variables.put("actions", actions);
  }

  @Override
  public void run(String name, Map<String, String> qm, GameChar character,
      Map<String, Object> variables) {
    // if person is starting turn reset available actions
    if (mode.equals("start")) {
      remaining.put("move", character.getSpeed());
      mode = "idle";
      phase = 0;
    }

    // make sure backend matches frontend
    if (!mode.equals("idle")) {
      if (!name.equals(mode)) {
        System.out.println(
            "NAME DOESN'T EQUAL MODE. NAME:" + name + " MODE:" + mode);
        return;
      }
    } else if (toResolve.size() > 0 && !toResolve.contains(name)) {
      System.out.println(
          "STILL NEED TO RESOLVE " + toResolve.toString() + "NOT " + name);
      return;
    }

    // make sure player has enough actions
    if (remaining.containsKey(name) && remaining.get(name) <= 0
        && phase == 0) {
      variables.put("Error",
          "Cannot perform action as no more are remaining.");
      addActions(character, variables);
      return;
    }
    System.out.println(name);
    switch (name) {
    case "move":
      if (phase == 0) {
        mode = "move";
        phase = 1;

        variables.put("phase", 0);
        // get the direction the player is trying to move in
        String direction = qm.get("direction");

        // try to move in given direction
        // fails if no door exists
        try {
          move.run(direction, character, variables);
          variables.put("direction", direction);
          variables.put("finished", move.getFinished());
          // use up one movement
          remaining.put("move", remaining.get("move") - 1);
        } catch (NullPointerException e) {
          mode = "idle";
          phase = 0;
          variables.put("Error", e.getMessage());
          return;
        }
        System.out.println("move: " + move.getFinished());
        if (move.getFinished()) {
          mode = "idle";
          phase = 0;

          List<Tile> tiles = new ArrayList<Tile>(
              memory.getTileMap().values());
          List<TileBean> tileBeans = new ArrayList<>();
          for (Tile tile : tiles) {
            tileBeans.add(tile.getBean());
          }
          variables.put("tiles", tileBeans);
          variables.put("characters", memory.getCharBeans());
          addActions(character, variables);
          return;
        }
        return;
      }

      if (phase == 1) {
        try {
          System.out.println("here here");

          move.addTile(character, Integer.parseInt(qm.get("rotations")),
              memory.getTileMap());

          phase = 0;
          mode = "idle";

          /* This adding method doesn't really make a ton of sense to me. */
          /*
           * classes are supposed to encapsulate an idea, why would the idea of
           * an item take in the variable map
           */
          /*
           * furthermore aren't we adding the item to the character -> character
           * should have an additem method not the other way round.
           */
          List<Event> eventList = new ArrayList<>();
          List<Item> itemList = new ArrayList<>();
          List<Omen> omenList = new ArrayList<>();
          // force character to pick up items/omens/events
          for (int i = 0; i < character.getTile().getItemCount(); i++) {
            Item item = memory.getItems().poll();
            item.add(character);
            itemList.add(item);
            character.getTile().addItem(item);
          }

          for (int i = 0; i < character.getTile().getEventCount(); i++) {
            Event event = memory.getEvents().poll();
            toResolve.add("event");
            phase = 1;
            eventList.add(event);
            character.getTile().addEvent(event);
          }

          for (int i = 0; i < character.getTile().getOmenCount(); i++) {
            Omen omen = memory.getOmens().poll();
            omen.add(character);
            toResolve.add("haunt");
            omenList.add(omen);
            character.getTile().addOmen(omen);
          }

          variables.put("phase", 1);
          // send frontend tile map
          variables.put("tiles", memory.getTileBeans());

          // send frontend character
          variables.put("characters", memory.getCharBeans());

          // send frontend newly added tile
          variables.put("newTile", character.getTile().getBean());

          System.out.println("item:" + itemList.size());
          System.out.println("omen:" + omenList.size());
          System.out.println("event:" + eventList.size());
          // push to front end
          variables.put("toResolve", toResolve);
          variables.put("item", itemList);
          variables.put("omen", omenList);
          variables.put("event", eventList);
          variables.put("character", character.getCharBean());
          addActions(character, variables);
        } catch (RuntimeException e) {
          variables.put("Error", e.getMessage());
          return;
        }
      }
      break;

    case "event":
      if (phase == 0) {
        String eventName = qm.get("event");
        Event event = character.getTile().getEvent(eventName);
        variables.put("event", event);
        phase = 1;
        return;
      } else if (phase == 1) {
        String eventName = qm.get("event");
        Event event = character.getTile().getEvent(eventName);
        String statToUse = qm.get("stat");

        // make sure valid stat is being used
        // if (!event.getUsableAsString().contains(statToUse)) {
        // variables.put("Error", "Invalid stat for event.");
        // return;
        // }

        // get the players relevant stat
        int statVal;
        try {
          statVal = character.getStatByName(statToUse);
        } catch (NullPointerException e) {
          variables.put("Error", e.getMessage());
          return;
        }

        // roll for that stat and add to qm
        List<Integer> rolls = Dice.roll(statVal);
        variables.put("rolls", rolls);

        // apply and return the results of event
        Integer rollSum = Dice.sum(rolls);
        String result = event.apply(rollSum, character);
        variables.put("result", result);
        variables.put("character", character.getCharBean());

        // send to frontend remaining actions.
        addActions(character, variables);
        mode = "idle";
        phase = 0;

        // we have resolved this event
        toResolve.remove("event");
        return;
      }
      break;

    case "haunt":
      omenCount += 1;
      List<Integer> rolls = Dice.roll(6);
      variables.put("rolls", rolls);

      // apply and return the results of event
      Integer rollSum = Dice.sum(rolls);

      if (omenCount > rollSum) {
        // generate a random haunt
        GamePhase haunt = new HauntOne(memory);
        character.setTraitor(true);
        haunt.setup(character, variables);
        variables.put("character", character.getCharBean());
        variables.put("description", haunt.getDescription());
        variables.put("traitor", haunt.getTraitorDescription());
        variables.put("explorers", haunt.getExplorersDescription());
        memory.getGameState().setPhase(haunt);
      }
      variables.put("omencount", omenCount);
      toResolve.remove("haunt");

      mode = "idle";
      phase = 0;
      break;

    case "use item":
      String useItemS = qm.get("item");
      Item useItem = character.getItem(useItemS);
      useItem.use(character, variables);
      variables.put("tiles", memory.getTileMap());
      variables.put("character", character.getCharBean());
      variables.put("item", useItem);
      mode = "idle";
      phase = 0;
      break;

    case "use omen":
      String useOmenS = qm.get("omen");
      Omen useOmen = character.getOmen(useOmenS);
      useOmen.use(character, variables);
      variables.put("tiles", memory.getTileMap());
      variables.put("character", character.getCharBean());
      variables.put("omen", useOmen);
      mode = "idle";
      phase = 0;
      break;

    case "pickup item":
      String pickupItemS = qm.get("item");
      Item pickupItem = character.getTile().getItem(pickupItemS);
      pickupItem.add(character);
      character.getTile().removeItem(pickupItem);
      variables.put("tiles", memory.getTileMap());
      variables.put("character", character.getCharBean());
      variables.put("item", pickupItem);
      mode = "idle";
      phase = 0;
      break;

    case "drop item":
      String dropItemS = qm.get("item");
      Item dropItem = character.getItem(dropItemS);
      dropItem.loss(character);
      character.getTile().addItem(dropItem);
      variables.put("tiles", memory.getTileMap());
      variables.put("character", character.getCharBean());
      variables.put("item", dropItem);
      mode = "idle";
      phase = 0;
      break;

    case "pickup omen":
      String pickupOmenS = qm.get("omen");
      Omen pickupOmen = character.getTile().getOmen(pickupOmenS);
      pickupOmen.add(character);
      character.getTile().removeOmen(pickupOmen);
      variables.put("tiles", memory.getTileMap());
      variables.put("character", character.getCharBean());
      variables.put("omen", pickupOmen);
      mode = "idle";
      phase = 0;
      break;

    case "drop omen":
      String dropOmenS = qm.get("omen");
      Omen dropOmen = character.getOmen(dropOmenS);
      dropOmen.loss(character);
      character.getTile().addOmen(dropOmen);
      variables.put("tiles", memory.getTileMap());
      variables.put("character", character.getCharBean());
      variables.put("omen", dropOmen);
      mode = "idle";
      phase = 0;
      break;

    case "end":
      System.out.println("ending");

      mode = "start";
      memory.getGameState().endTurn();
      System.out.println(variables);
      break;
    }
  }

  @Override
  public String getDescription() {
    return "Explore the house, search for items, and find omens!";
  }

  @Override
  public List<String> getTraitorDescription() {
    List<String> stringsTraitor = new ArrayList<>();
    stringsTraitor.add("Nothing in this phase.");
    return stringsTraitor;
  }

  @Override
  public List<String> getExplorersDescription() {
    List<String> stringsExplorer = new ArrayList<>();
    stringsExplorer.add("Nothing in this phase.");
    return stringsExplorer;
  }

  @Override
  public void setup(GameChar character, Map<String, Object> variables) {
    return;
  }
}