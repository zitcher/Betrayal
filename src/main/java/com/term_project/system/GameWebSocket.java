package com.term_project.system;

import java.io.IOException;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import com.term_project.game.GameState;
import com.term_project.system.MemorySlot;
import com.term_project.character.GameChar;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.annotations.WebSocket;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketClose;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketConnect;
import org.eclipse.jetty.websocket.api.annotations.OnWebSocketMessage;

@WebSocket
public class GameWebSocket {
  private static final Gson GSON = new Gson();
  //private static final Queue<Session> sessions = new ConcurrentLinkedQueue<>();

  //my multiplayer maps
  private static List<String> availableLobbies = new CopyOnWriteArrayList<>();
  private static Map<String, String> idToLobby = new ConcurrentHashMap<>();
  private static Map<Session, String> sessionToId = new ConcurrentHashMap<>();
  private static Map<String, Queue<Session>> lobbyToSessions = new ConcurrentHashMap<>();
  private static Map<String, String> idToName = new ConcurrentHashMap<>();
  private static Map<String, GameState> lobbyToGameState = new ConcurrentHashMap<>();
  private static List<String[]> usersToCharacters = new ArrayList<>();

  private static enum MESSAGE_TYPE {
    CONNECT,
    CHATUPDATE,
    UPDATELOBBIES,
    SETNAME,
    CREATELOBBY,
    JOINLOBBY,
    UPDATELOBBY,
    STARTGAME,
    GAMEMOVE,
    CHOOSECHARACTER,
    GAMEREADY,
    ERROR
  }

  //called on intial connect
  @OnWebSocketConnect
  public void connected(Session session) throws IOException {

    // return the user their id
    JsonObject connect =  new JsonObject();
    connect.addProperty("type", MESSAGE_TYPE.CONNECT.ordinal());

    String id = UUID.randomUUID().toString();
    connect.addProperty("id", id);
    sessionToId.put(session, id);
    idToName.put(id, id);

    // TODO Send the CONNECT message
    session.getRemote().sendString(connect.toString());

    // Update everyone's lobbies
    JsonObject updateLobbies =  new JsonObject();
    updateLobbies.addProperty("type", MESSAGE_TYPE.UPDATELOBBIES.ordinal());
    updateLobbies.addProperty("lobbies", GSON.toJson(availableLobbies));

    session.getRemote().sendString(updateLobbies.toString());
  }

  //called on leave
  @OnWebSocketClose
  public void closed(Session session, int statusCode, String reason) {
  	synchronized (sessionToId) {
  	    String id = sessionToId.get(session);

  		  sessionToId.remove(session);

  	    //if person who left was in lobby
  	    if(idToLobby.get(id) != null) {
  	      String lobby = idToLobby.get(id);

  	      Queue<Session> sessionsToUpdate = lobbyToSessions.get(lobby);
  	      sessionsToUpdate.remove(session);

  	      //lobby is now empty remove it
  	      if(sessionsToUpdate.size() == 0) {
  	        lobbyToSessions.remove(lobby);
  	        availableLobbies.remove(lobby);
  	      } else {
            try {
              updateLobby(sessionsToUpdate);
            } catch(IOException e) {
              System.out.println("failed to update lobbies.");
              e.printStackTrace();
            }
          }

  	      //if the lobby was actually a game throw errors to all members
  	      if(lobbyToGameState.get(lobby) != null) {
  	        lobbyToGameState.remove(lobby);
  	        lobbyToSessions.remove(lobby);
  	        availableLobbies.remove(lobby);

  	        JsonObject update =  new JsonObject();
  	        update.addProperty("type", MESSAGE_TYPE.ERROR.ordinal());
  	        update.addProperty("ERROR", "Player has left the game. Game has been ended");

  	        for(Session sess : sessionsToUpdate) {
  	          try {
  	            sess.getRemote().sendString(update.toString());
  	          } catch(IOException e) {
  	              System.out.println("closing game failed");
  	          }
  	        }
  	      } else {
  	        try {
  	          updateLobbies();;
  	        } catch(IOException e) {
  	          System.out.println("closing lobby update failed");
  	        }
  	      }
  	    }

  	    //remove it from all 3 lists
  	    idToLobby.remove(id);
  	    idToName.remove(id);
  	}
  }

  @OnWebSocketMessage
  public void message(Session session, String message) throws IOException {
    JsonObject received = GSON.fromJson(message, JsonObject.class);
    int type = received.get("type").getAsInt();
    MESSAGE_TYPE[] vals = MESSAGE_TYPE.values();
    switch (vals[type]) {
      case CHATUPDATE: //sending a chat message in the chatbox
        chatUpdate(received, session);
        break;
      case SETNAME: //set players name
        setName(received, session);
        break;
      case CREATELOBBY: //create a lobby
        createLobby(received, session);
        break;
      case JOINLOBBY: //join a lobby
        joinLobby(received, session);
        break;
      case STARTGAME: //start a game when in a lobby
        startGame(received, session);
        break;
      case GAMEMOVE: //game actions
        gameMove(received, session);
        break;
      case CHOOSECHARACTER:
        chooseCharacter(received, session);
        break;
      default:
        assert false;
        System.out.println("ERROR: Type does not exists - " + Integer.toString(type));
        break;

    }
  }

  private synchronized void chatUpdate(JsonObject received, Session session) throws IOException {
    JsonObject payload = received.get("payload").getAsJsonObject();
    String id = payload.get("id").getAsString();
    assert id.equals(sessionToId.get(session));

    // message sent to frontend
    JsonObject update =  new JsonObject();
    update.addProperty("type", MESSAGE_TYPE.CHATUPDATE.ordinal());
    update.addProperty("id", id);
    update.addProperty("name", idToName.get(id));
    update.addProperty("message", payload.get("message").getAsString());

    String lobby = idToLobby.get(id);
    Queue<Session> sessions = lobbyToSessions.get(lobby);
    for (Session ses : sessions) {
      ses.getRemote().sendString(update.toString());
    }
  }

  private synchronized void updateLobbies() throws IOException {
    // Update everyone's lobbies
    JsonObject update =  new JsonObject();
    update.addProperty("type", MESSAGE_TYPE.UPDATELOBBIES.ordinal());
    update.addProperty("lobbies", GSON.toJson(availableLobbies));

    for(Session ses : sessionToId.keySet()) {
      ses.getRemote().sendString(update.toString());
    }
  }

  //sets players name
  private synchronized void setName(JsonObject received, Session session) throws IOException {
    JsonObject payload = received.get("payload").getAsJsonObject();
    String id = payload.get("id").getAsString();
    assert id.equals(sessionToId.get(session));

    if(idToName.values().contains("name")) {
      JsonObject update =  new JsonObject();
      update.addProperty("type", MESSAGE_TYPE.ERROR.ordinal());
      update.addProperty("ERROR", "Name already in use by another.");
      session.getRemote().sendString(update.toString());
      return;
    }

    idToName.put(id, payload.get("name").getAsString());
  }

  private synchronized void createLobby(JsonObject received, Session session) throws IOException {
    JsonObject payload = received.get("payload").getAsJsonObject();
    String id = payload.get("id").getAsString();
    String lobbyName = payload.get("lobbyName").getAsString();
    assert id.equals(sessionToId.get(session));

    //if lobby with the same name already exists throw error to frontend
    if (lobbyToSessions.keySet().contains(lobbyName)) {
      JsonObject update =  new JsonObject();
      update.addProperty("type", MESSAGE_TYPE.ERROR.ordinal());
      update.addProperty("ERROR", "Lobby with given name already exists. Please use a different name.");
      session.getRemote().sendString(update.toString());
      return;
    }

    lobbyToSessions.put(lobbyName, new ConcurrentLinkedQueue<>());
    availableLobbies.add(lobbyName);

    joinLobby(received, session);

    updateLobbies();
  }

  private synchronized void joinLobby(JsonObject received, Session session) throws IOException {
    JsonObject payload = received.get("payload").getAsJsonObject();
    String id = payload.get("id").getAsString();
    String lobbyName = payload.get("lobbyName").getAsString();
    assert id.equals(sessionToId.get(session));

    //if lobby isnt part of available lobbies to join throw error
    if (!availableLobbies.contains(lobbyName)) {
      JsonObject update =  new JsonObject();
      update.addProperty("type", MESSAGE_TYPE.ERROR.ordinal());
      update.addProperty("ERROR", "Lobby isn't available. Choose another.");
      session.getRemote().sendString(update.toString());
      return;
    }

    Queue<Session> lobbyMembers = lobbyToSessions.get(lobbyName);
    lobbyMembers.add(session);
    idToLobby.put(id, lobbyName);
    updateLobby(lobbyMembers);

    //startgame if now at max members
    if(lobbyMembers.size() > 5) {
      startGame(received, session);
      updateLobbies();
    }
  }

  //message all members in given lobby who is in it
  private synchronized void updateLobby(Queue<Session> lobbyMembers) throws IOException {
    List<String> members = new ArrayList<>();
    //loops through members
    for(Session sess : lobbyMembers) {
      String memId = sessionToId.get(sess);
      members.add(idToName.get(memId));
    }

    JsonObject memberUpdate =  new JsonObject();
    memberUpdate.addProperty("type", MESSAGE_TYPE.UPDATELOBBY.ordinal());
    memberUpdate.addProperty("members", GSON.toJson(members));

    for(Session sess : lobbyMembers) {
      sess.getRemote().sendString(memberUpdate.toString());
    }
  }

  private synchronized void startGame(JsonObject received, Session session) throws IOException {
    JsonObject payload = received.get("payload").getAsJsonObject();
    String id = payload.get("id").getAsString();
    String lobbyName = payload.get("lobbyName").getAsString();
    assert id.equals(sessionToId.get(session));
    assert availableLobbies.contains(lobbyName);

    //game is starting so no longer an available lobbyName
    availableLobbies.remove(lobbyName);

    //gamestate init needs list of ids
    Queue<Session> lobbyMembers = lobbyToSessions.get(lobbyName);
    List<String> ids = new ArrayList<>();
    for(Session sess : lobbyMembers) {
      String memId = sessionToId.get(sess);
      ids.add(memId);
    }

    lobbyToGameState.put(lobbyName, new GameState(ids, new MemorySlot()));

    for(Session sess : lobbyMembers) {
      JsonObject update =  new JsonObject();
      update.addProperty("type", MESSAGE_TYPE.CHOOSECHARACTER.ordinal());
      update.addProperty("choices", GSON.toJson(lobbyToGameState.get(lobbyName).getCharacterChoice()));

      sess.getRemote().sendString(update.toString());
    }
  }

  private synchronized void gameMove(JsonObject received, Session session) throws IOException {
    JsonObject payload = received.get("payload").getAsJsonObject();
    String id = payload.get("id").getAsString();
    assert id.equals(sessionToId.get(session));
    System.out.println(payload.get("query").toString());
    
    HashMap<String,String> queryMap = GSON.fromJson(
        payload.get("query").toString(),
        new TypeToken<HashMap<String, String>>(){}.getType()
    );

    String lobby = idToLobby.get(id);
    GameState game = lobbyToGameState.get(lobby);
    Map<String, Object> toReturn = game.update(queryMap);

    // TODO Send an UPDATE message to all users
    JsonObject update =  new JsonObject();
    update.addProperty("type", MESSAGE_TYPE.GAMEMOVE.ordinal());
    update.addProperty("player", idToName.get(id));
    update.addProperty("currentTurn", game.getCurrentTurn());
    update.addProperty("payload", GSON.toJson(toReturn));

    Queue<Session> sessions = lobbyToSessions.get(lobby);
    for (Session ses : sessions) {
      ses.getRemote().sendString(update.toString());
    }
    
  }

  private synchronized void chooseCharacter(JsonObject received, Session session) throws IOException {
    JsonObject payload = received.get("payload").getAsJsonObject();
    String id = payload.get("id").getAsString();
    assert id.equals(sessionToId.get(session));

    usersToCharacters.add(new String[]{idToName.get(id), payload.get("choice").getAsString()});

    String lobby = idToLobby.get(id);
    GameState game = lobbyToGameState.get(lobby);

    //if final character has chosen load in map
    if(game.setPlayersCharacter(id, payload.get("choice").getAsString())) {
      //get the turn order
    	
      List<String> idTurnOrder = game.getTurnOrder();
      List<String> nameTurnOrder = new ArrayList<>();
      for(String aId : idTurnOrder) {
        nameTurnOrder.add(idToName.get(aId));
      }

      //build the starting map and exit lobby phase
      Map<String, Object> init = game.buildMap(null);

      JsonObject update =  new JsonObject();
      update.addProperty("type", MESSAGE_TYPE.GAMEREADY.ordinal());
      update.addProperty("turnOrder", GSON.toJson(nameTurnOrder));
      update.addProperty("idTurnOrder", GSON.toJson(idTurnOrder));
      update.addProperty("currentTurn", game.getCurrentTurn());
      update.addProperty("payload", GSON.toJson(init));
      update.addProperty("users", GSON.toJson(usersToCharacters));

      usersToCharacters.clear();
      
      //get the sessions to send the message to
      Queue<Session> sessions = lobbyToSessions.get(lobby);
      for (Session ses : sessions) {
        ses.getRemote().sendString(update.toString());
      }
    }
  }
}
